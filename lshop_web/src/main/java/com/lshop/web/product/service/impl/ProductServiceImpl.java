package com.lshop.web.product.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.ObjectUtils;
import com.gv.html.component.HtmlComponent;
import com.lshop.common.activity.constant.ActivityConstant;
import com.lshop.common.activity.vo.ActivityAppoindProduct;
import com.lshop.common.activity.vo.ActivityLimitOrder;
import com.lshop.common.activity.vo.ActivityLimitTime;
import com.lshop.common.activity.vo.ActivityLimited;
import com.lshop.common.activity.vo.GeneralActivity;
import com.lshop.common.constant.AppDataConstant;
import com.lshop.common.pojo.logic.LvActivityGift;
import com.lshop.common.pojo.logic.LvArea;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.pojo.logic.LvProductCommend;
import com.lshop.common.pojo.logic.LvProductComment;
import com.lshop.common.pojo.logic.LvProductImage;
import com.lshop.common.pojo.logic.LvProductLadder;
import com.lshop.common.pojo.logic.LvProductPackage;
import com.lshop.common.pojo.logic.LvProductProperty;
import com.lshop.common.pojo.logic.LvProductType;
import com.lshop.common.price.vo.ProductPrice;
import com.lshop.common.price.vo.constant.PriceConstant;
import com.lshop.common.redis.RedisKeyConstant;
import com.lshop.common.util.Constants;
import com.lshop.common.util.FloatUtils;
import com.lshop.web.activity.service.ActivityGiftService;
import com.lshop.web.activity.service.ActivityService;
import com.lshop.web.onlineMall.service.OnlineMallService;
import com.lshop.web.price.service.PriceService;
import com.lshop.web.product.component.ProductCacheDao;
import com.lshop.web.product.service.ProductService;
import com.lshop.web.product.vo.ProdCache;
import com.lshop.web.product.vo.ProdDetailActivityVo;
import com.lshop.web.product.vo.ProdShowVo;
import com.lshop.web.product.vo.ProductActivityVo;

/**
 * 产品模块
 * @author zhengxue
 * @since 2.0 2012-07-20
 *
 */
@SuppressWarnings("unchecked")
@Service("ProductService")
public class ProductServiceImpl implements ProductService {

	@Resource
	private HibernateBaseDAO lvlogicReadDao;
	
	@Resource
	private HibernateBaseDAO lvlogicWriteDao;
	
	@Resource
	private OnlineMallService onlineMallService;
	
	@Resource
	private ActivityService activityService;
	
	@Resource
	private ActivityGiftService activityGiftService;
	
	@Resource
	private PriceService priceService;
	@Resource
	private ProductCacheDao productCacheDao;
	@Resource
	private HtmlComponent htmlComponent;
	
	private static final Log logger	= LogFactory.getLog(ProductServiceImpl.class);
	
	/**
	 * 根据code查找对应的产品
	 * 需要传递参数code
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public LvProduct getProductByCode(Dto dto) throws ServiceException {
		String prodNo = dto.getAsString("pcode");
		ProdCache prodCache = null;
		try {
			prodCache = getProdctFromCache(prodNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null == prodCache ? null : prodCache.getLvProduct();
	}
	
	
	/**
	 * 
	 * @Method: getProductByPubCode 
	 * @Description:  [根据店铺标示和商品库code查询商品信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-5-6 下午1:33:27]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-5-6 下午1:33:27]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public LvProduct getProductByPubCode(Dto dto) throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("***** ProductServiceImpl.getProductByPubCode() method begin*****");
		}	
		
		String hql="from LvProduct where pubProductCode=:pubProductCode and storeId=:storeId";
		HashMap map=new HashMap();
		map.put("pubProductCode", dto.getAsString("pubCode"));
		map.put("storeId", dto.getAsString("storeFlag"));
		LvProduct product=(LvProduct)lvlogicReadDao.findUnique(hql,map);
		
		if (logger.isInfoEnabled()){
			logger.info("***** ProductServiceImpl.getProductByPubCode() method end*****");
		}	
		
		return product;
	}

	/**
	 * 根据id查找对应的产品
	 * 需要传递参数id
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public LvProduct getProductById(Dto dto) throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("***** ProductServiceImpl.getProductById() method begin*****");
		}	
		
		String id=dto.getAsString("id");
		LvProduct product=(LvProduct)lvlogicReadDao.load(LvProduct.class, Integer.parseInt(id));
		
		if (logger.isInfoEnabled()){
			logger.info("***** ProductServiceImpl.getProductById() method end*****");
		}	
		
		return product;
	}

	/**
	 * 根据产品类型查找对应的产品列表
	 * 需传递参数ptype
	 * @param dto
	 * @return
	 * @throws ServiceExcetpion
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LvProduct> getProductByType(Dto dto) throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("***** ProductServiceImpl.getProductByType() method begin*****");
		}	
		
		String storeFlag=dto.getAsString("storeFlag");
		String ptype=dto.getAsString("ptype");
		String hql = "from LvProduct where ptype=:ptype and status=1 and isSupport=1 and storeId=:storeFlag order by orderId asc";
		
		HashMap param = new HashMap();
		param.put("ptype", ptype);
		param.put("storeFlag", storeFlag);
		List<LvProduct> pList=(List<LvProduct>)lvlogicReadDao.find(hql,param);
		
		if (logger.isInfoEnabled()){
			logger.info("***** ProductServiceImpl.getProductByType() method end*****");
		}	
		
		return pList;
	}

	/**
	 * 获取所有的产品类型（产品、配件、应用）
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LvProductType> getTypes(Dto dto) throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("***** ProductServiceImpl.getTypes() method begin*****");
		}	
		
		String storeFlag=dto.getAsString("storeFlag");
		String hql = "from LvProductType where storeId=:storeFlag order by orderId asc";
		
		HashMap param = new HashMap();
		param.put("storeFlag", storeFlag);
		List<LvProductType> typeList=(List<LvProductType>)lvlogicReadDao.find(hql,param);
		
		if (logger.isInfoEnabled()){
			logger.info("***** ProductServiceImpl.getTypes() method end*****");
		}	
		
		return typeList;
	}

	@Override
	public ProductActivityVo getActivityByCode(Dto dto) throws ServiceException {
		
		String pcode=dto.getAsString("pcode");
		
		ProdCache prodCache = getProdctFromCache(pcode);
		ProductActivityVo activityVo = new ProductActivityVo();
		switch (prodCache.getActivityType()) {
		case ActivityConstant.TYPE_LIMIT_TIME:
			long curtime = System.currentTimeMillis();
			if (curtime > prodCache.getBeginTime() && curtime < prodCache.getEndTime()) {
				activityVo.setProductCode(pcode);
				activityVo.setActivityType(ActivityConstant.TYPE_LIMIT_TIME);
				activityVo.setActivityTitle(prodCache.getActivityName());
				activityVo.setActivityPrice(prodCache.getPrice());
				activityVo.setStartTime(new Date(prodCache.getBeginTime()));
				activityVo.setEndTime(new Date(prodCache.getEndTime()));
				return activityVo;
			} else {
				return null;
			}
		case ActivityConstant.TYPE_LIMIT_QUANTITY:
			ActivityLimited limited = null;
			try {
				limited = activityService.getActivityLimitedByCode(prodCache.getActivityNo());
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw new ServiceException(e.getMessage());
			}
			if (ObjectUtils.isEmpty(limited)) {
				return null;
			}
			activityVo.setProductCode(pcode);
			activityVo.setActivityTitle(prodCache.getActivityName());
			activityVo.setActivityPrice(prodCache.getPrice());
			activityVo.setActivityType(ActivityConstant.TYPE_LIMIT_QUANTITY);
			activityVo.setCounts(limited.getLimitTotal());
			return activityVo;
			default:
				return null;
		}
	}
	/**
	 * 获取该产品的搭配信息
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<LvProduct> getMatchProduct(Dto dto) throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("***** ProductServiceImpl.getMatchProduct() method begin*****");
		}	
		
		String storeFlag=dto.getAsString("storeFlag");
		
		//获取该产品的搭配code
		String hql = "from LvProductCommend where productCode=:productCode and storeId=:storeFlag";
		HashMap param = new HashMap();
		param.put("productCode", dto.getAsString("pcode"));
		param.put("storeFlag", storeFlag);
		List<LvProductCommend> pCommends=(List<LvProductCommend>)lvlogicReadDao.find(hql, param);
		
		//由搭配的产品code获取搭配产品信息
		List<LvProduct> pList=new ArrayList<LvProduct>();
		for(LvProductCommend commend:pCommends){
			Dto dto2 = new BaseDto();
			dto2.put("pcode", commend.getCommendCode());
			LvProduct product = getProductByCode(dto2);
			if (ObjectUtils.isNotEmpty(product) && product.getStatus().intValue() == 1
					&& product.getIsSupport().intValue() == 1 && product.getStoreId().equals(storeFlag)) {
				pList.add(product);
			}
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** ProductServiceImpl.getMatchProduct() method end*****");
		}	
		
		return pList;
	}

	/**
	 * 获取该产品的图片
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<LvProductImage> getProductImageByCode(Dto dto)
			throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("***** ProductServiceImpl.getProductImageByCode() method begin*****");
		}	
		
		String storeFlag=dto.getAsString("storeFlag");
		String hql = "from LvProductImage where productCode=:productCode and storeId=:storeFlag order by sortId desc";
		
		HashMap param = new HashMap();
		param.put("productCode", dto.getAsString("pcode"));
		param.put("storeFlag", storeFlag);
		List<LvProductImage> images=(List<LvProductImage>)lvlogicReadDao.find(hql, param);
		
		if (logger.isInfoEnabled()){
			logger.info("***** ProductServiceImpl.getProductImageByCode() method end*****");
		}	
		
		return images;
	}

	/**
	 * 获取该产品的属性（如规格参数）
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<LvProductProperty> getPropertyByCode(Dto dto)
			throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("***** ProductServiceImpl.getPropertyByCode() method begin*****");
		}	
		
		String storeFlag=dto.getAsString("storeFlag");
		String hql = "from LvProductProperty where productCode=:productCode and storeId=:storeFlag order by sortId desc";
		
		HashMap param = new HashMap();
		param.put("productCode", dto.getAsString("pcode"));
		param.put("storeFlag", storeFlag);
		List<LvProductProperty> propertys=(List<LvProductProperty>)lvlogicReadDao.find(hql,param);
		
		if (logger.isInfoEnabled()){
			logger.info("***** ProductServiceImpl.getPropertyByCode() method end*****");
		}	
		
		return propertys;
	}

	/**
	 * 获取所有的推荐产品
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<LvProduct> getReCommendProduct(Dto dto) throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("***** ProductServiceImpl.getReCommendProduct() method begin*****");
		}	
		
		String storeFlag=dto.getAsString("storeFlag");
		String hql = "from LvProduct where isCommend=1 and status=1 and isSupport=1 and storeId=:storeFlag order by orderId asc";
			
		HashMap param = new HashMap();
		param.put("storeFlag", storeFlag);
		List<LvProduct> products=(List<LvProduct>)lvlogicReadDao.find(hql, param);
		
		if (logger.isInfoEnabled()){
			logger.info("***** ProductServiceImpl.getReCommendProduct() method end*****");
		}	
		
		return products;
	}
	
	/**
	 * 获取评论列表
	 * 之所以是这种格式，是因为里面还封装的有国旗图标
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<Object[]> getCommentsByCode(Dto dto) throws ServiceException{
		
		if (logger.isInfoEnabled()){
			logger.info("***** ProductServiceImpl.getCommentsByCode() method begin*****");
		}	
		
		String storeFlag=dto.getAsString("storeFlag");
		Pagination page = dto.getDefaultPage();
		List<Object[]> objList=new ArrayList<Object[]>();
		
		//获取该产品的评论列表
		String hql="from LvProductComment where productCode=:productCode and storeId=:storeFlag and createTime<=:createTime and isCheck=1 and isDelete=0 and (replyId=0 or replyId=null) order by createTime desc";
		
		HashMap param = new HashMap();
		param.put("productCode", dto.getAsString("pcode"));
		param.put("createTime", new Date());
		param.put("storeFlag", storeFlag);
		page = lvlogicReadDao.find(Finder.create(hql).setParams(param), page.getPageNum(), page.getNumPerPage());
//		List<LvProductComment> comments=(List<LvProductComment>)lvlogicReadDao.find("from LvProductComment where productCode='"+dto.getAsString("pcode")+"' and replyId=0 and storeId='"+storeFlag+"' and isCheck=1 and isDelete=0 order by createTime desc", null);
		//获取每条评论对应的用户的国旗图标
		List<LvProductComment> comments=(List<LvProductComment>)page.getList();
		if(comments!=null){
			for(LvProductComment co:comments){
				String icon="";
				if(co.getContryId()!=null){
					LvArea area=(LvArea)lvlogicReadDao.findUniqueByProperty(LvArea.class, "id", co.getContryId());
					if(area!=null){
						icon=area.getIcon();
					}
				}
				Object obj[]=new Object[2];
				obj[0]=icon;
				obj[1]=co;
				objList.add(obj);
			}
		}
		page.setList(objList);
		dto.put("page", page);
		
		if (logger.isInfoEnabled()){
			logger.info("***** ProductServiceImpl.getCommentsByCode() method end*****");
		}	
		
		return objList;
	}
	
	/**
	 * 获取套餐信息列表
	 * 根据套餐编号，即为套餐情况下的产品列表code查询
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<LvProductPackage> getPackageList(Dto dto)
			throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("***** ProductServiceImpl.getPackageList() method begin*****");
		}	
		
		String storeFlag=dto.getAsString("storeFlag");
		String packageNum=dto.getAsString("packageNum");
		String hql = "from LvProductPackage where packageNum=:packageNum and storeId=:storeFlag order by storeId asc";
		
		HashMap param = new HashMap();
		param.put("packageNum", packageNum);
		param.put("storeFlag", storeFlag);
		List<LvProductPackage> packageList=(List<LvProductPackage>)lvlogicReadDao.find(hql, param);
		
		if (logger.isInfoEnabled()){
			logger.info("***** ProductServiceImpl.getPackageList() method end*****");
		}	
		
		return packageList;
	}

	/**
	 * 改变活动状态
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public Dto updateActivityStatus(Dto dto) throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("***** ProductServiceImpl.updateActivityStatus() method begin*****");
		}	
		
//		LvProduct product=this.getProductByCode(dto);
//		product.setIsActivity(0);
//		lvlogicWriteDao.update(product);
		String code=dto.getAsString("pcode");
		String hql="update LvProduct set isActivity=0 where code=:code";
		
		Map param=new HashMap();
		param.put("code", code);
		lvlogicWriteDao.update(hql, param);
		
		if (logger.isInfoEnabled()){
			logger.info("***** ProductServiceImpl.updateActivityStatus() method end*****");
		}	
		
		return null;
	}
	
	public float getProductOrignalPrice(Dto dto){
		//声明一个对象，用来存放产品价格
		float Price = 0f;
		
		//根据code获取产品信息 参数：pcode,storeFlag
		LvProduct product=this.getProductByCode(dto);
		
		if(null!=product){
			
			if(null!=product.getPrice() && !("").equals(product.getPrice())){
				Price = product.getPrice();
			}
		}
		if (logger.isInfoEnabled()){
			logger.info("***** ProductServiceImpl.getProductOrignalPrice() method end*****");
		}	
		
		return Price;
			
		
	}

	@Override
	public float getProductPrice(Dto dto) throws ServiceException {
		String prodNo = dto.getAsString("pcode");
		Integer pnum = 1;
		pnum = null != dto.get("pnum") ? pnum = dto.getAsInteger("pnum") : pnum;
		ProdCache prodCache = getProdctFromCache(prodNo);
		if (null == prodCache) {
			return -1;
		}
		ProductPrice productPrice = getPlainPrice(prodCache, pnum);
		if (ObjectUtils.isNotEmpty(productPrice)) {
			return productPrice.getPrice();
		}
		return -1;
	}

	/**
	 * 返回商品价格,不考虑商品状态
	 * @param prodNo
	 * @param pnum
	 * @return
	 */
	private ProductPrice getPlainPrice(ProdCache prodCache, int pnum) {
		//声明一个对象，用来存放产品价格
		float price = -1f;
		int type = PriceConstant.TYPE_NORMAL;
		if (ObjectUtils.isNotEmpty(prodCache)) {
			LvProduct product = prodCache.getLvProduct();
			price = product.getPrice();
			
			switch (prodCache.getActivityType()) {
			case 1:
				//检查限时活动正在进行
				long curTime = System.currentTimeMillis();
				if (curTime > prodCache.getBeginTime() && curTime < prodCache.getEndTime()) {
					price = prodCache.getActivityPrice();
					type = PriceConstant.TYPE_TIME;
				}
				break;
			case 2:
				//检查限量活动库存
				try {
					ActivityLimited limited = activityService.getActivityLimitedByCode(prodCache.getActivityNo());
					if (ObjectUtils.isNotEmpty(limited) && limited.getLimitTotal().intValue() > 0) {
						price = limited.getActivityPrice();
						type = PriceConstant.TYPE_QUANTITY;
					}
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					return null;
				}
				break;
			case 3:
				//阶梯价根据商品数量返回指定价格
				for (Iterator<LvProductLadder> iterator = prodCache.getLadderList().iterator(); iterator
						.hasNext();) {
					LvProductLadder ladder = iterator.next();
					if (ladder.getDownInterval().intValue() >= pnum && ladder.getUpInterval() <= pnum) {
						price = ladder.getPrice().floatValue();
						type = PriceConstant.TYPE_LEVEL;
						break;
					}
				}
				break;
			}
			//小计
			Float amount = FloatUtils.mul(price, pnum, 2);
			ProductPrice prodPrice =  new ProductPrice(prodCache.getPcode(), price, product.getPrice(), amount, type);
			prodPrice.setProdCache(prodCache);
			return prodPrice;
		}
		return null;
	}
	@Override
	public ProdDetailActivityVo getProdDetailActivity(String pcode) throws Exception {
		ProdCache prodCache = getProdctFromCache(pcode);
		ProdDetailActivityVo prodDetailActivityVo = new ProdDetailActivityVo();
		prodDetailActivityVo.setProdNo(pcode);
		prodDetailActivityVo.setCurTime(System.currentTimeMillis());
		if ( null == prodCache || (null!=prodCache.getStatus() && prodCache.getStatus()!=1) ||
			(null!=prodCache.getIsSupport() && prodCache.getIsSupport()!=1) ){
			//商品被禁用或者不存在
			prodDetailActivityVo.setProdStatus(0);
			return prodDetailActivityVo;
		}
		prodDetailActivityVo.setProdStatus(1);
		prodDetailActivityVo.setStoreId(prodCache.getStoreId());
		prodDetailActivityVo.setCurrency(Constants.STORE_TO_CURRENCY.get(prodCache.getStoreId()));
		prodDetailActivityVo.setOrignPrice(FloatUtils.formatDouble(prodCache.getPrice(), AppDataConstant.PRICE_SACAL));
		Float sellPrice = prodCache.getPrice();//销售价格
		//判断该产品是否参加活动
		if(prodCache.getIsActivity()!=null&&prodCache.getIsActivity()==1){
			GeneralActivity activity = activityService.getGeneralActivityByProductNo(pcode);
			if (ObjectUtils.isNotEmpty(activity)) {
				switch (activity.getType().intValue()) {
				case ActivityConstant.TYPE_LIMIT_TIME:
					prodDetailActivityVo.setActivityType(1);
					ActivityLimitTime limitTime = activity.getActivityLimitTime();
					prodDetailActivityVo.setActivityNo(limitTime.getActivityCode());
					prodDetailActivityVo.setActivityName(limitTime.getActivityTitle());
					prodDetailActivityVo.setActivityPrice(FloatUtils.formatDouble(limitTime.getActivityPrice(), AppDataConstant.PRICE_SACAL));
					prodDetailActivityVo.setBeginTime(limitTime.getStartTime().getTime());
					prodDetailActivityVo.setEndTime(limitTime.getEndTime().getTime());
					if (System.currentTimeMillis() > prodDetailActivityVo.getBeginTime()) {
						sellPrice = limitTime.getActivityPrice();
					}
					break;

				case ActivityConstant.TYPE_LIMIT_QUANTITY:
					prodDetailActivityVo.setActivityType(2);
					ActivityLimited limited = activity.getActivityLimited();
					prodDetailActivityVo.setActivityNo(limited.getActivityCode());
					prodDetailActivityVo.setActivityName(limited.getActivityTitle());
					prodDetailActivityVo.setActivityPrice(FloatUtils.formatDouble(limited.getActivityPrice(), AppDataConstant.PRICE_SACAL));
					prodDetailActivityVo.setLimitCount(limited.getLimitTotal().intValue());
					if (prodDetailActivityVo.getLimitCount() > 0) {
						sellPrice = limited.getActivityPrice();
					}
					break;
				case ActivityConstant.TYPE_APPOINT_PRODUCT:
					prodDetailActivityVo.setActivityType(4);
					ActivityAppoindProduct acPointProduct = activity.getActivityAppoindProduct();
					prodDetailActivityVo.setActivityNo(acPointProduct.getActivityCode());
					prodDetailActivityVo.setActivityName(acPointProduct.getActivityTitle());
					prodDetailActivityVo.setActivityNameEn(acPointProduct.getActivityTitleEn());
					prodDetailActivityVo.setGivenTypeNum(acPointProduct.getGivenTypeNum());
					Short givenTypeNum= acPointProduct.getGivenTypeNum();
					if(givenTypeNum==ActivityConstant.GIVEN_TYPE_NUM_COUPON){
						prodDetailActivityVo.setGivenTypeName(acPointProduct.getGivenTypeName());
						prodDetailActivityVo.setGivenProductCode(acPointProduct.getGivenProductCode());
						prodDetailActivityVo.setIsUseCoupon(acPointProduct.getIsUseCoupon());
						prodDetailActivityVo.setUncollectedTotal(acPointProduct.getUncollectedTotal());
					}else if(givenTypeNum==ActivityConstant.GIVEN_TYPE_NUM_LOTTORY){
						prodDetailActivityVo.setGivenTypeName(acPointProduct.getGivenTypeName());
						prodDetailActivityVo.setGivenProductCode(acPointProduct.getGivenProductCode());
						prodDetailActivityVo.setLotteryNum(acPointProduct.getLotteryNum());
					}else if(givenTypeNum==ActivityConstant.GIVEN_TYPE_NUM_GIFT){
						//根据商品编码查询商品赠品关联
						List giftList=activityGiftService.findGiftByActivityCode(acPointProduct.getActivityCode(), pcode);
						prodDetailActivityVo.setGiftList(giftList);
					}
					break;
				}
			}
		} else if(null!=prodCache.getIsLadder() && prodCache.getIsLadder()==1){
			if (ObjectUtils.isNotEmpty(prodCache.getLadderList())) {
				prodDetailActivityVo.setActivityType(3);
			}
		}
		//商品价格满足的订单满就送活动(优惠券)活动
		ActivityLimitOrder activityLimitOrder = activityService.getActivityLimitOrder(prodCache.getStoreId(), sellPrice);
		if(null != activityLimitOrder){
			prodDetailActivityVo.setOrderActivityTitle(activityLimitOrder.getActivityTitle());
		}
		
		return prodDetailActivityVo;
	}
	
	/**
	 * 从缓存获取商品,若无则从数据库中抓取
	 * @param prodNo
	 * @return
	 * @throws Exception 
	 */
	private ProdCache getProdctFromCache(String prodNo) throws ServiceException {
		String key = RedisKeyConstant.ProducKey(prodNo);
		ProdCache prodCache = productCacheDao.get(key);
		if (ObjectUtils.isEmpty(prodCache)) {
			//load from db
			LvProduct product = (LvProduct) lvlogicReadDao.findUniqueByProperty(LvProduct.class, "code", prodNo);
			if (ObjectUtils.isEmpty(product)) return null;//商品不存在
			prodCache = new ProdCache();
			prodCache.setLvProduct(product);
			//判断该产品是否参加活动
			if(product.getIsActivity()!=null&&product.getIsActivity()==1){
				GeneralActivity activity = null;
				try {
					activity = activityService.getGeneralActivityByProductNo(prodNo);
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					throw new ServiceException(e.getMessage());
				}
				if (ObjectUtils.isNotEmpty(activity)) {
					switch (activity.getType().intValue()) {
					case ActivityConstant.TYPE_LIMIT_TIME:
						prodCache.setActivityType(1);
						ActivityLimitTime limitTime = activity.getActivityLimitTime();
						prodCache.setActivityNo(limitTime.getActivityCode());
						prodCache.setActivityName(limitTime.getActivityTitle());
						prodCache.setActivityPrice(Float.valueOf(FloatUtils.formatDouble(limitTime.getActivityPrice(), AppDataConstant.PRICE_SACAL)).floatValue());
						prodCache.setBeginTime(limitTime.getStartTime().getTime());
						prodCache.setEndTime(limitTime.getEndTime().getTime());
						break;
					case ActivityConstant.TYPE_LIMIT_QUANTITY:
						prodCache.setActivityType(2);
						ActivityLimited limited = activity.getActivityLimited();
						prodCache.setActivityNo(limited.getActivityCode());
						prodCache.setActivityName(limited.getActivityTitle());
						prodCache.setActivityPrice(Float.valueOf(FloatUtils.formatDouble(limited.getActivityPrice(), AppDataConstant.PRICE_SACAL)).floatValue());
						break;
					}
				}
			} else if(null!=product.getIsLadder() && product.getIsLadder()==1){
				//加载阶梯价
				String hql = "from LvProductLadder where productCode=:pcode order by upInterval asc";
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("pcode", prodNo);
				List<LvProductLadder> ladderList = lvlogicReadDao.find(hql, param);
				if(null!=ladderList && ladderList.size()>0){
					prodCache.setActivityType(3);
					prodCache.setLadderList(ladderList);
				}
			}
			//缓存商品
			productCacheDao.set(key, prodCache);
		}
		return prodCache;
	}


	@Override
	public boolean reflashProdctCache(String prodNo) throws Exception {
		//先更新redis缓存
		productCacheDao.delete(RedisKeyConstant.ProducKey(prodNo));
		ProdCache prodCache = getProdctFromCache(prodNo);
		if (ObjectUtils.isEmpty(prodCache)) {
			logger.error("更新缓存找不到商品:"+prodNo);
		}
		//再更新html文件缓存
		htmlComponent.processHtmlRequest("/products/"+prodNo+".html", prodCache.getStoreId());
		return true;
	}
	/**
	 * 获取阶梯价格
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<LvProductLadder> getLadderPrice(Dto dto) throws ServiceException {
		String productCode=dto.getAsString("productCode");
		//获取上区间和下区间
		ProdCache prodCache = getProdctFromCache(productCode);
		List<LvProductLadder> ladderList = null;
		if (ObjectUtils.isNotEmpty(prodCache)) {
			return prodCache.getLadderList();
		}
		return ladderList;
	}

	/**
	 * 根据购买台数获取当前的阶梯价
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public LvProductLadder getLadderByPnum(Dto dto) throws ServiceException {
		String productCode=dto.getAsString("productCode");
		int pnum=dto.getAsInteger("pnum").intValue();
		ProdCache prodCache = getProdctFromCache(productCode);
		if (ObjectUtils.isNotEmpty(prodCache) && ObjectUtils.isNotEmpty(prodCache.getLadderList())) {
			for (Iterator<LvProductLadder> iterator = prodCache.getLadderList().iterator(); iterator.hasNext();) {
				LvProductLadder ladder = iterator.next();
				if (ladder.getDownInterval().intValue() >= pnum && ladder.getUpInterval().intValue() <= pnum) {
					return ladder;
				}
				
			}
		}
		return null;
	}


	@Override
	public ProductPrice getSalePrice(String prodNo, int pnum)
			throws ServiceException {
		ProdCache prodCache = getProdctFromCache(prodNo);
		//检查商品状态
		if (ObjectUtils.isEmpty(prodCache)) {
			logger.error("商品不存在");
			return null;
		}
		LvProduct product = prodCache.getLvProduct();
		if(null!=product.getStatus() && product.getStatus()!=1){
			logger.error("商品已停用");
			return null;
		}
		if(null!=product.getIsSupport() && product.getIsSupport()!=1){
			logger.error("商品已下架");
			return null;
		}
		//返回商品价格
		return getPlainPrice(prodCache, pnum);
	}


	@Override
	public Map<String, ProductPrice> getBachProductPrice(
			Map<String, Integer> productNumMap) throws ServiceException {
		Map<String, ProductPrice> priceMap = new HashMap<String, ProductPrice>();
		for (Iterator<String> iterator = productNumMap.keySet().iterator(); iterator.hasNext();) {
			String key = iterator.next();
			ProductPrice price = getSalePrice(key, productNumMap.get(key));
			if (ObjectUtils.isNotEmpty(price)) {
				priceMap.put(key, price);
			}
		}
		return priceMap;
	}


	@Override
	public String getPricesJson(String shopFlag, String[] pcodeArr) throws Exception {
		Integer pNum = 1;
		Map<String,Object> param=new HashMap<String, Object>();
		List<ProdShowVo> productList=new ArrayList<ProdShowVo>();
		//查询店铺币种
		String currency = Constants.STORE_TO_CURRENCY.get(shopFlag);
		
		if(ObjectUtils.isNotEmpty(pcodeArr)){
			
			for (int i = 0; i < pcodeArr.length; i++) {
			 	String productCode=pcodeArr[i];
			 	ProdCache prodCache = getProdctFromCache(productCode);
				//检查商品状态
				if (ObjectUtils.isEmpty(prodCache)) {
					logger.info("商品不存在");
					continue;
				}
			 	ProductPrice productPrice = getPlainPrice(prodCache, pNum);
			 	//查询是否有活动赠品标志
			 	List list= activityGiftService.findGiftByProductCode(productCode);
			 	boolean isGift = false;
			 	if(ObjectUtils.isNotEmpty(list)){
			 		isGift = true;
			 	}
			 	productList.add(new ProdShowVo(productCode, productPrice.getType(), isGift, currency,
			 			FloatUtils.formatDouble(productPrice.getPlainPrice(), AppDataConstant.PRICE_SACAL),
			 			FloatUtils.formatDouble(productPrice.getPrice(), AppDataConstant.PRICE_SACAL)));
			}
		}
		param.put("productList", productList);
		return JSONObject.fromObject(param).toString();		
	}

}
