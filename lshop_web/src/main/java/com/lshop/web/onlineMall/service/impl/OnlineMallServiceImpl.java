package com.lshop.web.onlineMall.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.constant.AppDataConstant;
import com.lshop.common.pojo.ShoppingCartBeanForCookie;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.pojo.logic.LvShopCart;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.price.vo.ProductPrice;
import com.lshop.common.redis.RedisKeyConstant;
import com.lshop.common.util.CodeUtils;
import com.lshop.common.util.Constants;
import com.lshop.common.util.FloatUtils;
import com.lshop.web.onlineMall.service.OnlineMallService;
import com.lshop.web.product.service.ProductService;
import com.lshop.web.shopCart.component.ShopCartCachceDao;
import com.lshop.web.shopCart.vo.ShopCartCache;
import com.lshop.web.shopCart.vo.ShopCartItem;
import com.lshop.web.shopCart.vo.ShopCartVo;

@Service("OnlineMallService")
public class OnlineMallServiceImpl implements OnlineMallService {
	
	@Resource
	private HibernateBaseDAO lvlogicReadDao;
	
	@Resource
	private HibernateBaseDAO lvlogicWriteDao;
	
	@Resource
	private ProductService productService;
	
	@Resource
	private ShopCartCachceDao shopCartCachceDao;
	
	private static final Log logger	= LogFactory.getLog(OnlineMallServiceImpl.class);

	/**
	 * 获取该用户的购物车信息
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LvShopCart> getShopCartList(Dto dto) throws ServiceException {
		String userCode=dto.getAsString("userCode");
		String storeFlag=dto.getAsString("storeFlag");
		//缓存获取购物车信息
		ShopCartVo shopCartVo = getShopCartByStore(userCode, storeFlag);
		if (ObjectUtils.isNotEmpty(shopCartVo)) {
			return shopCartVo.getLvShopCarts();
		}
		return null;
	}

	public LvShopCart findShopCartByCode(String productCode,String userCode,String storeFlag){
		//判断购物车这条产品数据是否存在
		LvShopCart shopCart=null;
		if(ObjectUtils.isNotEmpty(productCode)&&ObjectUtils.isNotEmpty(userCode)&&ObjectUtils.isNotEmpty(storeFlag)){
			String hql = "from LvShopCart where productCode=:productCode and userCode=:userCode and storeId=:storeFlag ";
			HashMap param = new HashMap();
			param.put("productCode", productCode);
			param.put("userCode", userCode);
			param.put("storeFlag", storeFlag);
			List<LvShopCart> list=(List<LvShopCart>)lvlogicReadDao.find(hql,param);
			if(ObjectUtils.isNotEmpty(list)){
				shopCart=list.get(0);				
			}
		}
		return shopCart;
	}
	
	
	/**
	 * 保存购物车信息
	 * @param dto
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void cacheShopCart(Dto dto) throws Exception {
		String storeFlag=dto.getAsString("storeFlag");
		LvShopCart cart=(LvShopCart)dto.getDefaultPo();
		//获取购买车中商品
		String userCode = cart.getUserCode();
		String  pcode = cart.getProductCode();
		int buyNum = cart.getShopNum().intValue();
		ShopCartCache addShopCart = new ShopCartCache(pcode, storeFlag, buyNum, CodeUtils.getCode());
		String key = RedisKeyConstant.ShopCartKey(userCode, storeFlag);
		//防止多线程导致购物车出现多份
		synchronized (this) {
			Set<ShopCartCache> shopCartCaches = shopCartCachceDao.members(key);
			mergeShopCart(addShopCart, key, shopCartCaches);
		}
	}
	
	/**
	 * 返回合并的单个购物车合并对象
	 * @param addShopCart
	 * @param redisKey
	 * @param shopCartCaches
	 * @return
	 */
	private ShopCartCache mergeShopCart(ShopCartCache addShopCart, String redisKey, Set<ShopCartCache> shopCartCaches) {
		shopCartCaches = ObjectUtils.isNotEmpty(shopCartCaches) ? shopCartCaches : new HashSet<ShopCartCache>();
		ShopCartCache tem = null;
		for (Iterator<ShopCartCache> iterator = shopCartCaches.iterator(); iterator
				.hasNext();) {
			ShopCartCache shopCartCache = iterator.next();
			if (addShopCart.getPcode().equals(shopCartCache.getPcode()) 
					&& addShopCart.getStoreId().equals(shopCartCache.getStoreId())) {
				tem = shopCartCache;
				break;
			}
		}
		//写入缓存
		if (ObjectUtils.isNotEmpty(tem)) {
			// merge
			int addNum = addShopCart.getNum() + tem.getNum();
			addNum = addNum > 9999 ? 9999 : addNum;
			shopCartCachceDao.remove(redisKey, tem);
			tem.setNum(addNum);
		} else {
			tem = addShopCart;
		}
		shopCartCachceDao.add(redisKey, tem);
		return tem;
	}
	
	/**
	 * 获取购物车中的数量
	 * @param dto
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Integer getShopCartNum(Dto dto) throws ServiceException{
		String userCode=dto.getAsString("userCode");
		String mallSystem = Constants.STORE_TO_MALL_SYSTEM.get(dto.getAsString("storeFlag"));//当前店铺所属系统（华扬orBanana）
		List<String> storeFlags = Constants.MALL_SYSTEM_TO_STORE.get(mallSystem); //当前系统下面所有的店铺标识
		int cartNum = 0;
		for (Iterator<String> iterator = storeFlags.iterator(); iterator.hasNext();) {
			String storeId = iterator.next();
			//缓存获取购物车信息
			ShopCartVo shopCartVo = getShopCartByStore(userCode, storeId);
			if (ObjectUtils.isNotEmpty(shopCartVo)) {
				List<LvShopCart> shopCarts = shopCartVo.getLvShopCarts();
				if (ObjectUtils.isNotEmpty(shopCarts)) {
					cartNum = cartNum + shopCarts.size();
				}
			}
		}
		return cartNum;
	}

	/**
	 * 删除某条购物车信息
	 * 根据购物车表code
	 * @param dto
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Boolean delCartByCode(Dto dto) {
		String mallSystem = dto.getAsString("mallSystem"); //当前店铺所属系统（华扬orBanana）
		String code=dto.getAsString("code");
		String userCode = dto.getAsString("userCode");
		ShopCartCache cache = getShopCartCacheByCode(mallSystem, userCode, code);
		if (ObjectUtils.isNotEmpty(cache)) {
			String key = RedisKeyConstant.ShopCartKey(userCode, cache.getStoreId());
			shopCartCachceDao.remove(key, cache);
			return true;
		}
		return false;
	}
	/**
	 * 获得redis缓存中的指定商品
	 * @param mallSystem
	 * @param userCode
	 * @param shopCartCode
	 * @return
	 */
	private ShopCartCache getShopCartCacheByCode(String mallSystem, String userCode, String shopCartCode) {
		List<String> list = Constants.MALL_SYSTEM_TO_STORE.get(mallSystem); //当前系统下面所有的店铺标识
		if(null==list || list.size()<1){
			return null;
		}
		for (Iterator<String> iterator = list.iterator(); iterator.hasNext();) {
			String storeId = iterator.next();
			String key = RedisKeyConstant.ShopCartKey(userCode, storeId);
			Set<ShopCartCache> shopCartCaches = shopCartCachceDao.members(key);
			if (ObjectUtils.isEmpty(shopCartCaches)) {
				continue;
			}
			for (Iterator<ShopCartCache> iterator2 = shopCartCaches.iterator(); iterator2
					.hasNext();) {
				ShopCartCache shopCartCache = iterator2.next();
				if (shopCartCode.equals(shopCartCache.getCode())) {
					return shopCartCache;
				}
			}
		}
		return null;
	}
	/**
	 * 更改购物车产品数量
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Dto changCartNum(Dto dto) throws ServiceException {
		String mallSystem = dto.getAsString("mallSystem"); //当前店铺所属系统（华扬orBanana）
		String code=dto.getAsString("code");
		String userCode = dto.getAsString("userCode");
		Integer shopNum=dto.getAsInteger("shopNum");
		if(shopNum>9999){
			shopNum = 9999;
		}
		ShopCartCache cache = getShopCartCacheByCode(mallSystem, userCode, code);
		if (ObjectUtils.isNotEmpty(cache)) {
			String key = RedisKeyConstant.ShopCartKey(userCode, cache.getStoreId());
			shopCartCachceDao.remove(key, cache);
			cache.setNum(shopNum);
			shopCartCachceDao.add(key, cache);
		}
		return null;
	}

	/**
	 * 获取所有的购物车列表，分店铺展示
	 * @param dto（用户code）
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List getAllShopCartInfo(Dto dto) throws ServiceException {
		
		String mallSystem = dto.getAsString("mallSystem"); //当前店铺所属系统（华扬orBanana）
		List<String> list = Constants.MALL_SYSTEM_TO_STORE.get(mallSystem); //当前系统下面所有的店铺标识
		if(null==list || list.size()<1){
			return null;
		}
		
		//声明一个list，用来存放店铺信息以及所对应的购物车信息
		List<Object[]> objList=new ArrayList<Object[]>(); //存放总列表信息
		//获取每个店铺信息以及店铺下面所对应的购物车列表
		if(null!=list && list.size()>0){
			for(int i=0; i<list.size(); i++){
				//获取店铺信息
				String storeId = list.get(i).toString();
				String userCode = dto.getAsString("userCode");
				//缓存获取购物车信息
				ShopCartVo shopCartVo = getShopCartByStore(userCode, storeId);
				if (ObjectUtils.isNotEmpty(shopCartVo)) {
					objList.add(transFromShopCartView(shopCartVo));
				}
			}//end-for
		}

		return objList;
	}

	/**
	 * 获取cookie中所有的购物车信息，分店铺展示
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List getAllShopCartInfoForCookie(Dto dto) throws ServiceException {
		List<ShoppingCartBeanForCookie> list = (List<ShoppingCartBeanForCookie>)dto.get("list");
		if (ObjectUtils.isEmpty(list)) {
			logger.info("购物车中没有商品");
			return null;
		}
		//cookie购物车店铺分类
		Map<String, Set<ShopCartCache>> storeCartMap = new HashMap<String, Set<ShopCartCache>>();
		for (Iterator<ShoppingCartBeanForCookie> iterator = list.iterator(); iterator.hasNext();) {
			ShoppingCartBeanForCookie cookie = iterator.next();
			String storeId = cookie.getStoreId();
			Set<ShopCartCache> caches = storeCartMap.get(storeId);
			if (ObjectUtils.isEmpty(caches)) {
				caches = new HashSet<ShopCartCache>();
				storeCartMap.put(storeId, caches);
			}
			caches.add(new ShopCartCache(cookie.getCode(), storeId, cookie.getShopNum(), null));
		}
		//声明一个list，用来存放店铺信息以及所对应的购物车信息
		List<Object[]> objList=new ArrayList<Object[]>(); //存放总列表信息
		//分店铺返回购物车视图
		for (Iterator<String> iterator = storeCartMap.keySet().iterator(); iterator.hasNext();) {
			String storeId = iterator.next();
			ShopCartVo shopCartVo = getShopCartFromCache(null, storeId, storeCartMap.get(storeId), false);
			if (ObjectUtils.isNotEmpty(shopCartVo)) {
				objList.add(transFromShopCartView(shopCartVo));
			}
		}

		return objList;
	}
	
	/**
	 * 把购物车业务数据转成购物车视图
	 * @param shopCartVo
	 * @return
	 */
	private Object[] transFromShopCartView(ShopCartVo shopCartVo) {
		//声明一个list，用来存放购物车信息及其所对应的商品信息
		List<Object[]> infoList=new ArrayList<Object[]>();
		for (Iterator<ShopCartItem> iterator = shopCartVo.getItems().iterator(); iterator
				.hasNext();) {
			ShopCartItem item = iterator.next();
			Object[] ob=new Object[3];//存放两个对象：1，购物车 2，产品信息 3,产品条目小计
			ob[0] = null == item.getShopCart() ? item.getCookie() : item.getShopCart();
			ob[1] = item.getProduct();
			ob[2] = FloatUtils.formatDouble(item.getSum(), AppDataConstant.PRICE_SACAL);
			infoList.add(ob);
		}
		//获取当前店铺标志 (可从缓存中获取):包括名称,货币符号
		LvStore store = new LvStore();
		store.setStoreFlag(shopCartVo.getStoreId());
		store.setCurrency(Constants.STORE_TO_CURRENCY.get(shopCartVo.getStoreId()));
		store.setName(Constants.STORE_TO_NAME.get(shopCartVo.getStoreId()));
		Object[] obj=new Object[4]; //存放三个对象：1，店铺信息 2，购物车信息 3，购物车总金额 4,购物车商品总数
		obj[0]=store;
		obj[1]=infoList;
		obj[2]=FloatUtils.formatDouble(shopCartVo.getTotalAmount(), AppDataConstant.PRICE_SACAL);
		obj[3]=shopCartVo.getTotalNum();
		
		return obj;
	}
	@Override
	public ShopCartVo getShopCartByStore(String userCode, String storeId)
			throws ServiceException {
		String key = RedisKeyConstant.ShopCartKey(userCode, storeId);
		Set<ShopCartCache> shopCartCaches = shopCartCachceDao.members(key);
		return getShopCartFromCache(userCode, storeId, shopCartCaches, true);
	}

	/**
	 * 从购物车缓存中得到购物车视图
	 * @param userCode
	 * @param storeId 
	 * @param shopCartCaches
	 * @return
	 */
	private ShopCartVo getShopCartFromCache(String userCode, String storeId,
			Set<ShopCartCache> shopCartCaches, boolean loginShopCart) {
		ShopCartVo shopCartVo = null;
		if (ObjectUtils.isNotEmpty(shopCartCaches)) {
			List<ShopCartItem> items = new ArrayList<ShopCartItem>();
			int totalNum = 0;
			float totalAmount = 0f;
			for (Iterator<ShopCartCache> iterator = shopCartCaches.iterator(); iterator
					.hasNext();) {
				ShopCartCache shopCartCache = iterator.next();
				ProductPrice productPrice = productService.getSalePrice(shopCartCache.getPcode(), shopCartCache.getNum());
				if (ObjectUtils.isEmpty(productPrice)) {
					continue;
				}
				
				Dto dto = new BaseDto();
				dto.put("pcode", shopCartCache.getPcode());
				LvProduct product = productPrice.getProdCache().getLvProduct();
				float prodPrice = productPrice.getPrice();
				float sum = productPrice.getAmount();
				totalNum = totalNum + shopCartCache.getNum();
				totalAmount = FloatUtils.add(totalAmount, sum, AppDataConstant.PRICE_SACAL);
				
				if (loginShopCart) {
					LvShopCart shopCart = new LvShopCart();
					shopCart.setShopPrice(prodPrice);
					shopCart.setUserCode(userCode);
					shopCart.setStoreId(storeId);
					shopCart.setProductCode(shopCartCache.getPcode());
					shopCart.setShopNum(shopCartCache.getNum());
					shopCart.setCode(shopCartCache.getCode());
					
					items.add(new ShopCartItem(shopCart, product, sum));
				} else {
					//convert ShopCartCache to ShoppingCartBeanForCookie
					ShoppingCartBeanForCookie cookie = new ShoppingCartBeanForCookie(storeId, shopCartCache.getPcode(), shopCartCache.getNum(), prodPrice);
					items.add(new ShopCartItem(cookie, product, sum));
				}
			}
			//返回对象
			if (ObjectUtils.isNotEmpty(items)) {
				shopCartVo = new ShopCartVo(storeId, items, totalNum, totalAmount);
			}
		}
		return shopCartVo;
	}

	@Override
	public boolean loginMergeShopCart(List<ShoppingCartBeanForCookie> cookies, String userCode)
			throws ServiceException {
		//cookie购物车店铺分类
		Map<String, Set<ShopCartCache>> storeCartMap = new HashMap<String, Set<ShopCartCache>>();
		for (Iterator<ShoppingCartBeanForCookie> iterator = cookies.iterator(); iterator.hasNext();) {
			ShoppingCartBeanForCookie cookie = iterator.next();
			String storeId = cookie.getStoreId();
			Set<ShopCartCache> caches = storeCartMap.get(storeId);
			if (ObjectUtils.isEmpty(caches)) {
				caches = new HashSet<ShopCartCache>();
				storeCartMap.put(storeId, caches);
			}
			caches.add(new ShopCartCache(cookie.getCode(), storeId, cookie.getShopNum(), CodeUtils.getCode()));
		}
		//批量合并所有店铺购物车
		for (Iterator<String> iterator = storeCartMap.keySet().iterator(); iterator.hasNext();) {
			String storeId = iterator.next();
			Set<ShopCartCache> cookieCart = storeCartMap.get(storeId);
			//过滤停用的商品
			ShopCartVo shopCartVo = getShopCartFromCache(userCode, storeId, cookieCart, true);
			if (ObjectUtils.isEmpty(shopCartVo)) {
				continue;
			}
			cookieCart = shopCartVo.getCaches();
			if (ObjectUtils.isEmpty(cookieCart)) {
				continue;
			}
			//合并店铺购物车
			String redisKey = RedisKeyConstant.ShopCartKey(userCode, storeId);
			synchronized (this) {
				Set<ShopCartCache> shopCartCaches = shopCartCachceDao.members(redisKey);
				for (Iterator<ShopCartCache> iterator2 = cookieCart.iterator(); iterator2
						.hasNext();) {
					ShopCartCache addCartCache = iterator2.next();
					mergeShopCart(addCartCache, redisKey, shopCartCaches);
				}
			}
		}
		return true;
	}
	
}
