package com.lshop.web.shopCart.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.activity.constant.ActivityConstant;
import com.lshop.common.activity.vo.ActivityLimitOrder;
import com.lshop.common.activity.vo.ActivityMac;
import com.lshop.common.activity.vo.OrderActivity;
import com.lshop.common.cache.component.CacheComponent;
import com.lshop.common.constant.AppDataConstant;
import com.lshop.common.constant.CacheKeyContant;
import com.lshop.common.coupon.vo.CustomerCoupon;
import com.lshop.common.coupon.vo.CustomerCouponEditCheck;
import com.lshop.common.coupon.vo.CustomerCouponQuery;
import com.lshop.common.coupon.vo.CustomerCouponSubmitCheck;
import com.lshop.common.datastructure.ResultMsg;
import com.lshop.common.pojo.logic.LvAccountMac;
import com.lshop.common.pojo.logic.LvArea;
import com.lshop.common.pojo.logic.LvGroupBuy;
import com.lshop.common.pojo.logic.LvOrder;
import com.lshop.common.pojo.logic.LvOrderActivity;
import com.lshop.common.pojo.logic.LvOrderAddress;
import com.lshop.common.pojo.logic.LvOrderDetails;
import com.lshop.common.pojo.logic.LvOrderGift;
import com.lshop.common.pojo.logic.LvOrderMac;
import com.lshop.common.pojo.logic.LvOrderPackageDetails;
import com.lshop.common.pojo.logic.LvPaymentStyle;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.pojo.logic.LvProductPackage;
import com.lshop.common.pojo.logic.LvShopCart;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.pojo.logic.LvWesternInfo;
import com.lshop.common.pojo.user.LvAccount;
import com.lshop.common.pojo.user.LvAccountAddress;
import com.lshop.common.price.vo.ProductPrice;
import com.lshop.common.redis.RedisExpire;
import com.lshop.common.redis.RedisKeyConstant;
import com.lshop.common.redis.StringValueCacheDao;
import com.lshop.common.util.CodeUtils;
import com.lshop.common.util.Constants;
import com.lshop.common.util.FloatUtils;
import com.lshop.common.util.I18nCache2;
import com.lshop.common.util.OrderHelp;
import com.lshop.web.accountAddress.service.AccountAddressService;
import com.lshop.web.activity.service.ActivityAppointProductService;
import com.lshop.web.activity.service.ActivityGiftService;
import com.lshop.web.activity.service.ActivityMacService;
import com.lshop.web.accountPayment.service.AccountPaymentService;
import com.lshop.web.activity.service.ActivityService;
import com.lshop.web.coupon.service.CouponMacService;
import com.lshop.web.coupon.service.CouponService;
import com.lshop.web.mac.service.AccountMacService;
import com.lshop.web.onlineMall.service.OnlineMallService;
import com.lshop.web.order.component.OrderCacheDao;
import com.lshop.web.order.service.AsyncOrderService;
import com.lshop.web.order.service.OrderMacService;
import com.lshop.web.order.vo.OrderCahce;
import com.lshop.web.product.service.ProductService;
import com.lshop.web.shopCart.component.PaymentCacheDao;
import com.lshop.web.shopCart.component.ShopCartCachceDao;
import com.lshop.web.shopCart.service.ShopCartService;
import com.lshop.web.shopCart.vo.OrderInfoVo;
import com.lshop.web.shopCart.vo.ShopCartItem;
import com.lshop.web.shopCart.vo.ShopCartVo;
import com.lshop.web.userCenter.service.UserCenterService;

/**
 * 购物车模块
 * @author zhengxue
 * @since 2.0 2012-07-30
 *
 */
@Service("ShopCartService")
public class ShopCartServiceImpl implements ShopCartService {
	
	@Resource
	private HibernateBaseDAO lvlogicReadDao;
	
	@Resource
	private HibernateBaseDAO lvlogicWriteDao;
	
	@Resource
	private OnlineMallService onlineMallServie;
	
	@Resource
	private UserCenterService userCenterService;
	
	@Resource
	private ProductService productService;
	
	@Resource
	private CouponService couponService;
	
	@Resource AccountPaymentService accountPaymentService;
	@Resource OnlineMallService onlineMallService;
	@Resource
	private ShopCartCachceDao shopCartCachceDao;
	@Resource
	private OrderCacheDao orderCacheDao;
	@Resource
	private AsyncOrderService asyncOrderService;
	@Resource
	private StringValueCacheDao stringValueCacheDao;
	@Resource
	private PaymentCacheDao paymentCacheDao;
	@Resource
	private CacheComponent cacheComponent;
	
	@Resource 
	private AccountAddressService accountAddressService;
	
	@Resource 
	private ActivityService activityService;
	
	@Resource
	private ActivityGiftService  activityGiftService;
	
	@Resource 
	private ActivityAppointProductService activityAppointProductService;
	
	@Resource
	private CouponMacService couponMacService;
	@Resource
	private AccountMacService accountMacService;
	@Resource
	private ActivityMacService activityMacService;
	@Resource
	private OrderMacService orderMacService;
	
	
	
	private static final Log logger	= LogFactory.getLog(ShopCartServiceImpl.class);

	@Override
	public float getDeliverCarrigage(String storeFlag, Integer deliverId)
			throws ServiceException {
		String hql = null;
		Map<String, Object> param = new HashMap<String, Object>();
		int count = 0;
		while (true) {
			if (null == deliverId) {
				//没有设置运费,则是返回默认运费
				deliverId = 100000;
			}
			//从缓存加载运费
			float carriage = getCarriageFromCache(storeFlag, deliverId);
			
			if (-1 != carriage) {
				return carriage;
			} else {
				//查找上级运费
				String key = CacheKeyContant.AreaParent(deliverId);
				Integer cacheDeliver = (Integer) cacheComponent.getCache(key);
				if (null == cacheDeliver) {
					hql = "select parentid from LvArea where id=:id";
					param.clear();
					param.put("id", deliverId);
					cacheDeliver = (Integer) lvlogicReadDao.findUnique(hql, param);
					if (null == cacheDeliver) {
						cacheComponent.setCache(key, 0);
					} else {
						cacheComponent.setCache(key, cacheDeliver);
					}
				} else if (0 == cacheDeliver.intValue()) {
					cacheDeliver = null;//没有上级
				}
				deliverId = cacheDeliver;
				if (count > 3) {
					//防止程序死循环,数据错误,没有设置店铺默认运费
					logger.error(storeFlag+"店铺没有设置默认运费");
					return 0;
				}
				count++;
			}
		}
	}
	/**
	 * 从缓存获取运费,缓存中存放运费金额
	 * @param storeFlag
	 * @param deliverId
	 * @return
	 */
	private float getCarriageFromCache(String storeFlag, Integer deliverId) {
		String cacheKey = RedisKeyConstant.CarriageKey(storeFlag, String.valueOf(deliverId));
		String carri = stringValueCacheDao.get(cacheKey);
		if (StringUtils.isNotBlank(carri)) {
			if (RedisKeyConstant.EMPTY.equals(carri)) {
				return -1;//运费为空
			}
			return Float.valueOf(carri).floatValue();
		} else {
			return reloadCarriageCache(storeFlag, deliverId);
		}
	}
	
	@Override
	public float reloadCarriageCache(String storeFlag, Integer deliverId)
			throws ServiceException {
		String cacheKey = RedisKeyConstant.CarriageKey(storeFlag, deliverId.toString());
		Map<String, Object> param = new HashMap<String, Object>();
		String hql = "select carriage from LvCarriageSet where deliveryId=:deliveryId and storeId=:storeFlag";
		param.put("deliveryId", deliverId);
		param.put("storeFlag", storeFlag);
		Float carriageSet = (Float) lvlogicReadDao.findUnique(hql, param);
		if (ObjectUtils.isNotEmpty(carriageSet)) {
			stringValueCacheDao.set(cacheKey, String.valueOf(carriageSet));
			stringValueCacheDao.expire(cacheKey, RedisExpire.CarriageDay, TimeUnit.DAYS);
			return carriageSet;
		} else {
			stringValueCacheDao.set(cacheKey, RedisKeyConstant.EMPTY);
			stringValueCacheDao.expire(cacheKey, RedisExpire.CarriageDay, TimeUnit.DAYS);
			return -1;
		}
	}
	/**
	 * 获取所有的支付方式
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LvPaymentStyle> getPaymentList(Dto dto) throws ServiceException {
		String storeFlag=dto.getAsString("storeFlag");
		List<LvPaymentStyle> paymentList = getPaymentListFromCache(storeFlag);
		if (CollectionUtils.isNotEmpty(paymentList)) {
			//以orderValue升序排序
			Collections.sort(paymentList, new Comparator<LvPaymentStyle>() {
				
				@Override
				public int compare(LvPaymentStyle o1, LvPaymentStyle o2) {
					int thisVal = o1.getOrderValue().intValue();
					int anotherVal = o2.getOrderValue().intValue();
					return (thisVal<anotherVal ? -1 : (thisVal==anotherVal ? 0 : 1));
				}
			});
		}
		return paymentList;
	}
	/**
	 * 从缓存中读取店铺支付方式
	 * @param storeFlag
	 * @return
	 */
	private List<LvPaymentStyle> getPaymentListFromCache(String storeFlag) {
		String cacheKey = RedisKeyConstant.PaymentKey(storeFlag);
		List<LvPaymentStyle> paymentList = paymentCacheDao.values(cacheKey);
		if (CollectionUtils.isEmpty(paymentList)) {
			paymentList = reloadPaymentCache(storeFlag);
		}
		return paymentList;
	}
	
	@Override
	public List<LvPaymentStyle> reloadPaymentCache(String storeFlag) throws ServiceException {
		String cacheKey = RedisKeyConstant.PaymentKey(storeFlag);
		String hql = "from LvPaymentStyle where isActivity=1  and storeFlag=:storeFlag and payType="+ Constants.PAYTYPE_LINEPAY;
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("storeFlag", storeFlag);
		List<LvPaymentStyle> paymentList = (List<LvPaymentStyle>)lvlogicReadDao.find(hql,param);
		if (CollectionUtils.isNotEmpty(paymentList)) {
			Map<String, LvPaymentStyle> m = new HashMap<String, LvPaymentStyle>();
			for (Iterator<LvPaymentStyle> iterator = paymentList.iterator(); iterator
					.hasNext();) {
				LvPaymentStyle lvPaymentStyle = iterator.next();
				m.put(lvPaymentStyle.getId().toString(), lvPaymentStyle);
			}
			paymentCacheDao.delete(cacheKey);
			paymentCacheDao.putAll(cacheKey, m);
			paymentCacheDao.expire(cacheKey, RedisExpire.PaymentDay, TimeUnit.DAYS);
		}
		return paymentList;
	}
	@Override
	public boolean reloadPaymentCache(String storeFlag, String paymentCode)
			throws ServiceException {
		paymentCacheDao.delete(RedisKeyConstant.PaymentKey(storeFlag), paymentCode);
		String hql = "from LvPaymentStyle where isActivity=1  and storeFlag=:storeFlag and payType="+ Constants.PAYTYPE_LINEPAY +" and code=:code";
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("storeFlag", storeFlag);
		param.put("code", paymentCode);
		LvPaymentStyle paymentStyle = (LvPaymentStyle) lvlogicReadDao.findUnique(hql, param);
		if (ObjectUtils.isNotEmpty(paymentStyle)) {
			getPaymentListFromCache(storeFlag);//防止没有完全加载
			paymentCacheDao.put(RedisKeyConstant.PaymentKey(storeFlag), paymentCode, paymentStyle);
		}
		return true;
	}
	/**
	 * 获取国家列表
	 * @param dto
	 * @return
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<LvArea> getContryList(Dto dto) {
		List<LvArea> countryList = (List<LvArea>) cacheComponent.getCache(CacheKeyContant.COUNTRY_LIST);
		if (null == countryList) {
			loadCountryList();
		}
		return countryList;
	}

	@Override
	public void loadCountryList() {
		//load from db
		String hql = "from LvArea where parentid is null order by nameen desc";
		List<LvArea> countryList = (List<LvArea>)lvlogicReadDao.find(hql, null);
		cacheComponent.setCache(CacheKeyContant.COUNTRY_LIST, countryList);
	}
	/**
	 * 保存订单信息
	 * @param dto
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public ResultMsg saveOrder(Dto dto) throws Exception {
		String storeFlagWeb = dto.getAsString("storeFlagWeb");
		//获取参数
		String addressCode = dto.getAsString("addressCode");
		String couponCode=dto.getAsString("couponCode");
		String storeFlag = dto.getAsString("storeFlag");
		String userCode = dto.getAsString("userCode");
		Short paymethod = dto.getAsInteger("paymethod").shortValue();
		Integer bestDeliveryValue=dto.getAsInteger("bestDeliveryValue");
		String userCarriage = dto.getAsString("carriage");
		String userProdAmount = dto.getAsString("prodAmount");
		String orderRemark = dto.getAsString("orderRemark");
		String mac=(String) dto.get("mac");
		//购物车列表
		Dto param = new BaseDto();
		param.put("userCode", userCode);
		param.put("storeFlag", storeFlag);
		List<LvShopCart> shopCarts = onlineMallServie.getShopCartList(param);
		if (ObjectUtils.isEmpty(shopCarts)) {
			//商品列表为空
			ResultMsg resultMsg = new ResultMsg();//返回结果信息
			resultMsg.setSuccess(false);
			resultMsg.setMsg(I18nCache2.getValue("shopCart.empty", storeFlagWeb));
			return resultMsg;
		}
		return createOrder(addressCode, couponCode, storeFlag,
				userCode, paymethod, bestDeliveryValue, userCarriage,
				userProdAmount, orderRemark, shopCarts, storeFlagWeb,mac);
	}
	/*
	 * 根据购物车信息生成订单
	 */
	private ResultMsg createOrder( String addressCode,
			String couponCode, String storeFlag, String userCode,
			Short paymethod, Integer bestDeliveryValue, String userCarriage,
			String userProdAmount, String orderRemark, List<LvShopCart> shopCarts, String storeFlagWeb,String mac) throws Exception {
		ResultMsg resultMsg = new ResultMsg();//返回结果信息
		Map<String, Integer> productNumMap = getShopcartMap(shopCarts);
		//获取购物车商品列表价格列表
		Map<String, ProductPrice> prodPrice = productService.getBachProductPrice(productNumMap);
		//校验商品价格变化
		if (StringUtils.isNotBlank(userProdAmount)) {
			float calcProdPrice = calcShopcart(prodPrice, 1);
			if (0.0f == calcProdPrice || calcProdPrice != Float.valueOf(userProdAmount).floatValue()) {
				resultMsg.setSuccess(false);
				resultMsg.setMsg(I18nCache2.getValue("shopCart.price.changed", storeFlagWeb));
				return resultMsg;
			}
		}
		//生成订单号
		String orderNo="";
		if(ObjectUtils.isNotEmpty(mac)){
			orderNo = createOrderNo("CN",storeFlag);
		}else{
			orderNo = createOrderNo("C",storeFlag);
		}
		
		//获取当前店铺币种
		String currency = Constants.STORE_TO_CURRENCY.get(storeFlag);
		//获取订单支付方式的币种
		String paymentCurrency = getPaymentCurrency(paymethod);
		//获得汇率
		float currencyRate = getShopPaymentCurrentRate(currency, paymentCurrency);
		//获取收货地址信息
		Dto param = new BaseDto();
		param.put("userCode", userCode);
		param.put("addressCode", addressCode);
		LvAccountAddress accountAddress = accountAddressService.getAddressByCode(param);
		if (ObjectUtils.isEmpty(accountAddress) || (accountAddress.getContryId() == null) || (accountAddress.getContryId() < 0)
				|| (accountAddress.getContryName() == null)|| (accountAddress.getContryName().isEmpty())) {
			resultMsg.setSuccess(false);
			resultMsg.setMsg(I18nCache2.getValue("shopCart.account.address.country.incomplete", storeFlagWeb));
			return resultMsg;			
		}
		//获取运费信息，由地址的区域编号确定
		Float carriage=getCarrige(accountAddress, storeFlag);
		float realCarriageAmount = FloatUtils.mul(carriage, currencyRate, AppDataConstant.PRICE_SACAL);
		//校验运费变化
		if (StringUtils.isNotBlank(userCarriage)) {
			if (carriage != Float.valueOf(userCarriage).floatValue()) {
				resultMsg.setSuccess(false);
				resultMsg.setMsg(I18nCache2.getValue("shopCart.freight.changed", storeFlagWeb));
				return resultMsg;
			}
		}
		//获取优惠码面值，由优惠码确定
		Float couponPrice=0f;
		float realCouponAmount = 0f;
		if (StringUtils.isNotBlank(couponCode)) {
			//使用并验证优惠券
			CustomerCouponSubmitCheck customerCouponSubmitCheck = new CustomerCouponSubmitCheck(couponCode, userCode, storeFlag, productNumMap);
			ResultMsg couponMsg = couponService.useCustomerCoupon(orderNo, customerCouponSubmitCheck);
			if (couponMsg.isSuccess()) {
				couponPrice = (Float) couponMsg.getReObj();
				realCouponAmount = FloatUtils.mul(couponPrice, currencyRate, AppDataConstant.PRICE_SACAL);
			} else {
				//优惠券使用失败
				resultMsg.setSuccess(false);
				resultMsg.setMsg(I18nCache2.getValue("shopCart.failed.discount", storeFlagWeb));
				return resultMsg;
			}
		}
				
		//生成订单缓存
		OrderCahce orderCahce = createOrderCache(userCode, orderNo, paymethod, paymentCurrency, storeFlag, accountAddress,
				bestDeliveryValue, prodPrice, productNumMap, currencyRate, realCouponAmount,
				couponPrice, carriage, realCarriageAmount, orderRemark,mac,shopCarts);
		//保存订单礼品详情信息
		boolean isGift = saveOrderGift(shopCarts, orderNo);
		if (isGift) {
			orderCahce.getOrder().setIsGift((short)1);
		} else {
			orderCahce.getOrder().setIsGift((short)0);
		}
		//保存写入订单缓存
		orderCacheDao.set(RedisKeyConstant.OrderKey(orderNo), orderCahce);
		//异步同步订单
		asyncOrderService.asyncOrder(orderCahce);
		//生成订单后,活动回调接口
		OrderActivity orderActivity = new OrderActivity(orderNo, storeFlag, orderCahce.getOrder().getTotalPrice(), prodPrice.keySet());
		activityService.createOrderActivity(orderActivity);
		//保存之后，删除店铺购物车
		shopCartCachceDao.delete(RedisKeyConstant.ShopCartKey(userCode, storeFlag));
		//返回结果
		resultMsg.setSuccess(true);
		resultMsg.setReObj(orderCahce.getOrder());
		return resultMsg;
	}
	/*
	 * 创建订单对象
	 */
	private OrderCahce createOrderCache(String userCode, String orderNo, Short paymethod, String paymentCurrency, String storeFlag,
			LvAccountAddress address, Integer bestDeliveryValue,Map<String, ProductPrice> prodPrice, 
			Map<String, Integer> productNumMap, float currencyRate, float realCouponAmount, float couponPrice, 
			float carriage, float realCarriageAmount, String orderRemark,String mac,List<LvShopCart> shopCarts) {
		Dto param = new BaseDto();
		Date currentDate = new Date();
		//获取登陆用户信息
		param.clear();
		param.put("uid", userCode);
		LvAccount account=userCenterService.getAccount(param);
		//保存订单信息LvOrder
		LvOrder order = new LvOrder();
		order.setOid(orderNo);
		order.setPaymethod(paymethod);
		order.setPayStatus(Short.parseShort("0"));
		order.setStatus(Short.parseShort("0"));
		order.setMemid(account.getId());
		order.setUserEmail(account.getEmail());
		order.setIsServiceAudit(Short.parseShort("0"));
		order.setIsFinanceAudit(0);
		order.setCurrency(paymentCurrency);
		order.setIsdelete(Short.parseShort("0"));
		order.setFlag(0);
		order.setCode(CodeUtils.getCode());
		order.setCreateTime(currentDate);
		order.setStoreId(storeFlag);
		order.setOrderRemark(orderRemark);//订单留言
		order.setPostprice(realCarriageAmount); //邮费
		order.setCouponTotalPrice(realCouponAmount);//优惠券总额
		
		//保存订单地址表LvOrderAddress
		LvOrderAddress lvOrderAddress = saveOrderAddress(address, orderNo, storeFlag, bestDeliveryValue);
		float allAmount = 0f; //存放购物车商品总金额(没有按支付方式转换)
		float realAllAmount = 0f;//实际商品总金额
		float prodAmount = 0f;//商品小计
		
		//保存订单详情表LvOrderDetails
		List<LvOrderPackageDetails> lvOrderPackageDetails = null;
		List<LvOrderDetails> lvOrderDetails = new ArrayList<LvOrderDetails>();
		for (Iterator<String> iterator = prodPrice.keySet().iterator(); iterator.hasNext();) {
			String prodNo = iterator.next();
			int prodNum = productNumMap.get(prodNo);
			param.clear();
			param.put("pcode", prodNo);
			LvProduct product = productService.getProductByCode(param);
			//汇率转换
			Float shopPrice = prodPrice.get(prodNo).getPrice();
			prodAmount = FloatUtils.mul(shopPrice, productNumMap.get(prodNo));
			allAmount = FloatUtils.add(allAmount, prodAmount, AppDataConstant.PRICE_SACAL);
			shopPrice = FloatUtils.mul(shopPrice, currencyRate, AppDataConstant.PRICE_SACAL);
			prodAmount = FloatUtils.mul(shopPrice, productNumMap.get(prodNo));
			realAllAmount = FloatUtils.add(realAllAmount, prodAmount, AppDataConstant.PRICE_SACAL);
			//orderDetails
			LvOrderDetails orderDetail = saveOrderDetail(order, product, prodNum, storeFlag, shopPrice);
			lvOrderDetails.add(orderDetail);
			//如果是套餐，刚还要保存订单详情套餐表LvOrderPackageDetails
			if(null!=orderDetail.getIsPackage()&&orderDetail.getIsPackage()==1){
				if (ObjectUtils.isEmpty(lvOrderPackageDetails)) {
					lvOrderPackageDetails = new ArrayList<LvOrderPackageDetails>();
				}
				lvOrderPackageDetails.addAll(saveOrderPackage(prodNo, orderDetail, paymentCurrency, currencyRate));
			}
		}
		//计算应付金额
		float orderAmount = calcOrderAmount(allAmount, carriage, couponPrice);
		float realOrderAmount = calcOrderAmount(realAllAmount, realCarriageAmount, realCouponAmount);
		
		
		float macAmount=0f;
		//保存输入mac就送订单信息
		macAmount = saveOrderMac(mac, shopCarts, orderNo, account.getEmail(),currencyRate, macAmount);
		//计算输入mac就送优惠金额
		realOrderAmount=FloatUtils.sub(realOrderAmount, macAmount, AppDataConstant.PRICE_SACAL);
		
		
		order.setTotalPrice(realOrderAmount); //订单总金额
		//保存写入订单缓存
		return new OrderCahce(order, lvOrderDetails, lvOrderAddress, lvOrderPackageDetails, orderAmount);
	}

	private float saveOrderMac(String mac, List<LvShopCart> shopCarts,String orderNo, String userEmail, float currencyRate,float macAmount) {
		float realMacAmount=0f;
		if(ObjectUtils.isNotEmpty(mac)){
			for (int i = 0; i < shopCarts.size(); i++) {
				LvShopCart cart= shopCarts.get(i);
				ActivityMac  activityMac= activityMacService.findByProduct(cart.getProductCode());
				if(ObjectUtils.isNotEmpty(activityMac)){
					macAmount = FloatUtils.add(macAmount, activityMac.getAmount(), AppDataConstant.PRICE_SACAL);
					realMacAmount = FloatUtils.mul(macAmount, currencyRate, AppDataConstant.PRICE_SACAL);
					String ip="";
					String tel="";
					String userMacCode="";
					String sourceUrl="";
					//根据用户email和mac查找当前用户输入的mac情况信息
					LvAccountMac accountMac=accountMacService.findByMac(userEmail, mac);
					if(ObjectUtils.isNotEmpty(accountMac)){
						if(ObjectUtils.isNotEmpty(accountMac.getIp())){
							ip=accountMac.getIp().trim();
						}
						if(ObjectUtils.isNotEmpty(accountMac.getContactPhone())){
							tel=accountMac.getContactPhone().trim();
						}else if(ObjectUtils.isNotEmpty(accountMac.getContactTel())){
							tel=accountMac.getContactTel().trim();
						}
						if(ObjectUtils.isNotEmpty(accountMac.getCode())){
							userMacCode=accountMac.getCode().trim();
						}
						if(ObjectUtils.isNotEmpty(accountMac.getSourceUrl())){
							sourceUrl=accountMac.getSourceUrl().trim();
						}
					}
					
					//拼装输入mac就送订单数据
					LvOrderMac orderMac=new LvOrderMac();
					orderMac.setOrderId(orderNo);
					orderMac.setMac(mac);
					orderMac.setDiscountAmount(realMacAmount);
					orderMac.setUserEmail(userEmail);
					orderMac.setIp(ip);
					orderMac.setTel(tel);
					orderMac.setUserMacCode(userMacCode);		
					orderMac.setSourceUrl(sourceUrl);
					orderMac.setCreateTime(new Date());
					orderMac.setStatus((short)1);
					orderMacService.saveOrderMac(orderMac);
					
					//修改用户输入mac状态，防止重复输入
					accountMacService.updateStatus(userEmail, mac);
				}
			}
		}
		return realMacAmount;
	}

	/**
	 * 
	 * @Method: saveOrderGift 
	 * @Description:  [一句话描述该类的功能]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-16 上午11:59:34]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-16 上午11:59:34]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param shopCarts
	 * @param orderNo 
	 * @return void
	 * @throws Exception 
	 */
	private boolean saveOrderGift(List<LvShopCart> shopCarts, String orderNo) throws Exception {
		String [] productArr=new String[shopCarts.size()];
		for (int i = 0; i < shopCarts.size(); i++) {
			LvShopCart lvShopCart=shopCarts.get(i);
			productArr[i]=lvShopCart.getProductCode();
		}
		List list= activityAppointProductService.findActivityByProductCode(productArr);
		if(ObjectUtils.isNotEmpty(list)){
			List giftList=new ArrayList();
			for (int i = 0; i < list.size(); i++) {
			    Map activityMap=(Map) list.get(i);
			    if(ObjectUtils.isNotEmpty(activityMap.get("givenTypeNum"))){
			    	Short givenTypeNum=Short.parseShort(activityMap.get("givenTypeNum").toString());
			    	String activityCode=(String) activityMap.get("activityCode");
			    	if(givenTypeNum==ActivityConstant.GIVEN_TYPE_NUM_GIFT){
			    		//根据活动编码和商品编码查询赠品信息
			    		List giftListTemp=activityGiftService.findGiftByProductCodeStr(activityCode,productArr);
			    		giftList.addAll(giftListTemp);
			    		
			    		if(ObjectUtils.isNotEmpty(giftListTemp)){
			    			for (int j = 0; j < giftListTemp.size(); j++) {
								Map giftMap=(Map) giftListTemp.get(j);
								String giftCode="";
								Integer giftEveryNum=0;
								if(ObjectUtils.isNotEmpty(giftMap.get("giftCode"))){
									giftCode=String.valueOf(giftMap.get("giftCode"));
								}
								if(ObjectUtils.isNotEmpty(giftMap.get("giftEveryNum"))){
									giftEveryNum=Integer.valueOf(giftMap.get("giftEveryNum").toString());
								}
								//更新活动赠品领取数目
			    				activityGiftService.updateGiftHaveNum(activityCode, giftCode, giftEveryNum);	
			    			}
			    		}
			    	}
			    }
			}
			//不同活动相同赠品去重
			List glist= activityGiftService.giftDeduplication(giftList);
			
	   		if(ObjectUtils.isNotEmpty(glist)){
    			for (int j = 0; j < glist.size(); j++) {
					Map giftMap=(Map) glist.get(j);
					String giftCode="";
					Integer giftEveryNum=0;
					if(ObjectUtils.isNotEmpty(giftMap.get("giftCode"))){
						giftCode=String.valueOf(giftMap.get("giftCode"));
					}
					if(ObjectUtils.isNotEmpty(giftMap.get("giftEveryNum"))){
						giftEveryNum=Integer.valueOf(giftMap.get("giftEveryNum").toString());
					}
					
					LvOrderGift orderGift=new LvOrderGift();
    				orderGift.setOrderId(orderNo);
    				orderGift.setGiftCode(giftCode);
    				orderGift.setGiftNum(giftEveryNum);
    				orderGift.setCode(CodeUtils.getCode());
    				orderGift.setCreateTime(new Date());
    				//保存订单赠品信息
    				lvlogicWriteDao.save(orderGift);					
				}
    			return true;
    		}
		}
		return false;
	}
	
	
	/**
	 * 返回购物车商品号,数量键值对
	 * @param userCode
	 * @param storeFlag
	 * @return
	 */
	private Map<String, Integer> getShopcartMap(List<LvShopCart> shopCarts) {
		Map<String, Integer> productNumMap = new HashMap<String, Integer>();
		
		for (Iterator<LvShopCart> iterator = shopCarts.iterator(); iterator.hasNext();) {
			LvShopCart lvShopCart = iterator.next();
			productNumMap.put(lvShopCart.getProductCode(), lvShopCart.getShopNum());
		}
		return productNumMap;
	}
	/**
	 * 新生成普通订单号
	 * @param storeFlag
	 * @return
	 */
	private String createOrderNo(String markPre,String storeFlag) {
		//订单编号规则变更：C+店铺id（2位）+时间（14位）+随机码（5位）
    	Integer id=Constants.STORE_FLAG_TO_IDS.get(storeFlag);
    	String mark=null;
    	if(ObjectUtils.isNotEmpty(id)){
    		if(id<10){
        		mark=markPre+"0"+id.toString();
        	}else{
        		mark=markPre+id.toString();
        	}
    	}else{
    		mark=markPre;
    	}
    	return OrderHelp.createOrderId(mark);
	}
	private LvOrderAddress saveOrderAddress(LvAccountAddress address, String orderNo, String storeFlag, Integer bestDeliveryValue) {
		LvOrderAddress orderAddress=new LvOrderAddress();
		orderAddress.setOrderId(orderNo);
		orderAddress.setRelCode(address.getRelCode());
		orderAddress.setRelName(address.getRelName());
		orderAddress.setPostCode(address.getPostCode());
		orderAddress.setMobile(address.getMobile());
		orderAddress.setTel(address.getTel());
		orderAddress.setContryId(address.getContryId());
		orderAddress.setContryName(address.getContryName());
		orderAddress.setProvinceId(address.getProvinceId());
		orderAddress.setProvinceName(address.getProvinceName());
		orderAddress.setCityId(address.getCityId());
		orderAddress.setCityName(address.getCityName());
		orderAddress.setAdress(address.getAdress());
		orderAddress.setCode(CodeUtils.getCode());
		orderAddress.setCreateTime(new Date());
		orderAddress.setStoreId(storeFlag);
		orderAddress.setBestDeliveryTime(bestDeliveryValue);
		return orderAddress;
	}
	/**
	 * 保存订单详情
	 * @param order
	 * @param product
	 * @param prodNum
	 * @param storeFlag
	 * @param buyPrice
	 * @return
	 */
	private LvOrderDetails saveOrderDetail(LvOrder order, LvProduct product, int prodNum, String storeFlag, float buyPrice) {
		LvOrderDetails orderDetail=new LvOrderDetails();
		orderDetail.setOrderId(order.getOid());
		orderDetail.setProductCode(product.getCode());
		orderDetail.setOprice(buyPrice);
		orderDetail.setOremark(order.getOrderRemark());
		orderDetail.setPnum(prodNum);
		orderDetail.setPostPrice(order.getPostprice());
		orderDetail.setCurrency(order.getCurrency());
		orderDetail.setIsDelete(0);
		orderDetail.setIsPackage(product.getIsSetMeal());
		orderDetail.setCode(CodeUtils.getCode());
		orderDetail.setCreateTime(new Date());
		orderDetail.setStoreId(storeFlag);
		return orderDetail;
	}
	/**
	 * 保存订单中套餐明细
	 * @param prodNo
	 * @param orderDetail
	 * @param currency 订单币种
	 * @param currencyRate 订单汇率
	 * @return
	 */
	private List<LvOrderPackageDetails> saveOrderPackage(String prodNo, LvOrderDetails orderDetail, String currency, float currencyRate){
		Dto dto = new BaseDto();
		dto.put("packageNum", prodNo);
		dto.put("storeFlag", orderDetail.getStoreId());
		List<LvProductPackage> packageList=productService.getPackageList(dto);
		List<LvOrderPackageDetails> res = new ArrayList<LvOrderPackageDetails>();
		for(LvProductPackage productPackage:packageList){
			LvOrderPackageDetails packageDetail=new LvOrderPackageDetails();
			packageDetail.setOrderDetailsCode(orderDetail.getCode());
			packageDetail.setPnum(orderDetail.getPnum()*productPackage.getPnum()); //订单套餐详情表中的产品台数是总产品台数，即等于套餐数*每份套餐中的产品数
			packageDetail.setCurrency(currency);
			float tmPrice = FloatUtils.mul(productPackage.getPrice(), currencyRate, AppDataConstant.PRICE_SACAL);
			packageDetail.setOprice(tmPrice);
			packageDetail.setProductCode(productPackage.getProductCode());
			packageDetail.setPcode(productPackage.getPcode());
			packageDetail.setCode(CodeUtils.getCode());
			packageDetail.setCreateTime(new Date());
			res.add(packageDetail);
		}
		return res;
	}
	/**
	 * 保存西联汇款
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Dto saveWesternInfo(Dto dto) throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("***** ShopCartServiceImpl.saveWesternInfo() method begin*****");
		}	
		
		LvWesternInfo info=(LvWesternInfo)dto.getDefaultPo();
		String hql = "from LvWesternInfo where oid=:oid and storeId=:storeId";
		HashMap param = new HashMap();
		param.put("oid", info.getOid());
		param.put("storeId", info.getStoreId());
		List list=lvlogicReadDao.find(hql, param);
		
		Integer flag=0; //用来标识是否重复提交
		if(list!=null&&list.size()>0){
			//已存在，不可再保存
			flag=1;
		}else{
			info.setStatus(Short.parseShort("0"));
			info.setCode(CodeUtils.getCode());
			info.setCreateTime(new Date());
			lvlogicWriteDao.save(info);
		}
		
        //修改订单支付状态为：已支付，未确认
        if(ObjectUtils.isNotEmpty(info.getOid())){
        	hql="update LvOrder set payStatus=2 where oid=:oid";
        	param.clear();
        	param.put("oid", info.getOid());
        	lvlogicWriteDao.update(hql, param);
        }
        dto.put("flag", flag);
        
		if (logger.isInfoEnabled()){
			logger.info("***** ShopCartServiceImpl.saveWesternInfo() method end*****");
		}	
		
		return dto;
	}

	
	
	
	
	/**
	 * 支付时更改剩余量或已购买量
	 * 判断产品类型，如果是团购产品则更改已购买人数；如果是限量产品，则更改剩余产品量
	 * 传入参数订单号oid
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Dto changeCount(Dto dto) throws ServiceException {

		if (logger.isInfoEnabled()){
			logger.info("***** ShopCartServiceImpl.changeCount() method begin*****");
		}	
		
		String oid=dto.getAsString("oid"); //获取订单号
//		String storeFlag=dto.getAsString("storeFlag"); //获取店铺标志
		String hql = "";
		HashMap param = new HashMap();
		
		//获取订单详情，以便找到相应的产品信息
		LvOrder order=(LvOrder)lvlogicReadDao.findUniqueByProperty(LvOrder.class, "oid", oid);
		
		hql = "from LvOrderDetails where orderId=:oid and isDelete=0 and storeId=:storeId";
		param.put("oid", oid);
		param.put("storeId", order.getStoreId());
		List<LvOrderDetails> detailList=(List<LvOrderDetails>)lvlogicReadDao.find(hql, param);
		
		if(detailList!=null&detailList.size()>0){
			for(LvOrderDetails detail:detailList){
//				//判断是否是团购产品，以订单号首字母来判断
//				String firstStr=oid.substring(0, 1).toString();
				if(null!=order.getIsGroup() && order.getIsGroup()==1){ //说明是团购产品
					hql = "from LvGroupBuy where code=:code and status=1 and endTime>=:endTime and storeId=:storeId order by endTime desc";
					param.clear();
					param.put("code", order.getGroupCode());
					param.put("endTime", order.getOvertime());
					param.put("storeId", order.getStoreId());
					List<LvGroupBuy> groupList=(List<LvGroupBuy>)lvlogicReadDao.find(hql, param);
					
					if(groupList!=null&&groupList.size()>0){
						LvGroupBuy groupBuy=groupList.get(0);
						hql="update LvGroupBuy set purchasedNum=:purchasedNum where code=:code";
						param.clear();
//						param.put("purchasedNum", groupBuy.getPurchasedNum()+detail.getPnum());
						param.put("purchasedNum", groupBuy.getPurchasedNum()+1); //统计已购买人数，一个用户只能下一个订单，但订单中的产品数不限制
						param.put("code", groupBuy.getCode());
						lvlogicWriteDao.update(hql, param);
					}
				}else{ //不是团购产品，就判断是否是限量产品
					dto.put("pcode", detail.getProductCode());
					dto.put("storeFlag", order.getStoreId());
					LvProduct product=productService.getProductByCode(dto);
					//判断是否参加活动
					if(product.getIsActivity()!=null&&product.getIsActivity()==1){
						//TODO 应该从订单活动中查询订单已经参加的活动
//						LvActivity ac=productService.getActivityByCode(dto);
//						if(ac!=null){
//							if(ac.getActivityType()==2){ //如果是限量活动
//								Integer oldCount=ac.getCounts();
//								ac.setCounts(oldCount-detail.getPnum());
//								hql = "update LvActivityInfo set propertyValue=:propertyValue where activityCode=:activityCode and propertyKey='counts' and storeId=:storeId ";
//								param.clear();
//								param.put("propertyValue", String.valueOf(ac.getCounts()));
//								param.put("activityCode", ac.getCode());
//								param.put("storeId", order.getStoreId());
//								lvlogicWriteDao.update(hql, param);
//							}
//						}
					}
				}
			}
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** ShopCartServiceImpl.changeCount() method end*****");
		}	
		
		return null;
	}

	/**
	 * 获取商品应付金额及商品条数
	 * 在保存订单前比较商品金额是否发生改变，以及判断是否所有产品下架或删除情况
	 * @param dto
	 * @return
	 * @throws Exception 
	 */
	@Override
	public float getTotalPrice(Dto dto) throws Exception {

		String addressCode = dto.getAsString("addressCode");
		String couponCode=dto.getAsString("couponCode");
		String storeFlag = dto.getAsString("storeFlag");
		String userCode = dto.getAsString("userCode");
		
		//汇率, 因未考虑支付方式,汇率为1
		float currencyRate = 1f;
		//获取运费信息，由地址的区域编号确定
		dto.put("addressCode", addressCode);
		//获取收货地址信息
		LvAccountAddress address=userCenterService.getAddressByCode(dto); 
		Float carriage=getCarrige(address, storeFlag);
		//获取使用优惠
		Float couponPrice=0f;
		if (StringUtils.isNotBlank(couponCode)) {
			
		}
		
		return caculateOrder(userCode, storeFlag, carriage, couponPrice, currencyRate);
	}
	/**
	 * 返回支付方式方法对应的币种
	 * @param payment
	 * @return
	 */
	private String getPaymentCurrency(short payment) {
		switch (payment) {
		case 3:
		case 13:
		case 17:
		case 18:
			return "CNY";

		default:
			return "USD";
		}
	}
	/**
	 * 返回在店铺使用其它支付方式的汇率
	 * @param storeFlag
	 * @param payment
	 * @return
	 */
	private float getShopPaymentCurrentRate(String shopCurrency, String payCurrency) {
		float rate = 1f;
		if (!shopCurrency.equalsIgnoreCase(payCurrency)) {
			if (payCurrency.equalsIgnoreCase("USD")) {
				rate = Constants.rateNumCNY;
			} else if (payCurrency.equalsIgnoreCase("CNY") || payCurrency.equalsIgnoreCase("RMB")) {
				rate = Constants.rateNum;
			}
		}
		return rate;
	}
	@Override
	public float getCarrige(LvAccountAddress address, String storeFlag) {
		//获取运费信息，由地址的区域编号确定
		Float carriage=0f;
		
		if(address!=null){
			Integer deliverId = address.getContryId();
			//bannana体系中可计算到市,省级运费
			if (storeFlag.equals("bscn") || storeFlag.equals("bsen") || storeFlag.equals("mbscn")) {
				deliverId = null==address.getProvinceId() ? deliverId : address.getProvinceId();
				deliverId = null==address.getCityId() ? deliverId : address.getCityId();
			}
			return getDeliverCarrigage(storeFlag, deliverId);
		}
		return carriage;
	}
	
	@Override
	public float caculateShopcart(String userCode, String shopFlag, float currencyRate) throws Exception {
		Dto dto = new BaseDto();
		dto.put("userCode", userCode);
		dto.put("storeFlag", shopFlag);
		List<LvShopCart> shopCarts = onlineMallServie.getAllShopCartInfo(dto);
		if (ObjectUtils.isEmpty(shopCarts)) {
			return 0f;
		}
		
		//获取商品价格
		Map<String, Integer> prodNum = new HashMap<String, Integer>();
		for (Iterator<LvShopCart> iterator = shopCarts.iterator(); iterator.hasNext();) {
			LvShopCart cartItem = iterator.next();
			prodNum.put(cartItem.getProductCode(), cartItem.getShopNum());
		}
		Map<String, ProductPrice> prodPrice = productService.getBachProductPrice(prodNum);
		return calcShopcart(prodPrice, currencyRate);
	}
	/**
	 * 购物车价格计算
	 * @param prodPrice
	 * @param currencyRate
	 * @return
	 */
	private float calcShopcart(Map<String, ProductPrice> prodPrice, float currencyRate) {
		float prodAmount;//小计金额
		float shopcarAmount = 0f;//购物车金额
		ProductPrice price;
		for (Iterator<String> iterator = prodPrice.keySet().iterator(); iterator.hasNext();) {
			price = prodPrice.get(iterator.next());
			prodAmount = FloatUtils.mul(price.getAmount(), currencyRate, AppDataConstant.PRICE_SACAL);
			shopcarAmount = FloatUtils.add(prodAmount, shopcarAmount, AppDataConstant.PRICE_SACAL);
		}
		return shopcarAmount;
	}
	@Override
	public float caculateOrder(String userCode, String shopFlag,
			float carrigate, float couponAmount, float currencyRate)
			throws Exception {
		float prodAmount = caculateShopcart(userCode, shopFlag, currencyRate);
		return calcOrderAmount(prodAmount, FloatUtils.mul(carrigate, currencyRate, AppDataConstant.PRICE_SACAL),
				FloatUtils.mul(couponAmount, currencyRate, AppDataConstant.PRICE_SACAL));
	}
	/**
	 * 订单计算公式
	 * @param prodAmount
	 * @param carrigate
	 * @param couponAmout
	 * @return
	 */
	private float calcOrderAmount(float prodAmount, float carrigate, float couponAmout) {
		float orderAmount = FloatUtils.add(prodAmount, carrigate, AppDataConstant.PRICE_SACAL);
		orderAmount = FloatUtils.sub(orderAmount, couponAmout, AppDataConstant.PRICE_SACAL);
		return orderAmount;
	}

	@Override
	public List<CustomerCoupon> getUserShopcartCoupons(Dto dto)
			throws Exception {
		List<LvShopCart> shopCarts = onlineMallServie.getShopCartList(dto);
		if (ObjectUtils.isEmpty(shopCarts)) {
			return null;
		}
		Map<String, Integer> productNum = new HashMap<String, Integer>();
		for (Iterator<LvShopCart> iterator = shopCarts.iterator(); iterator.hasNext();) {
			LvShopCart lvShopCart = iterator.next();
			productNum.put(lvShopCart.getProductCode(), lvShopCart.getShopNum());
		}
		String usercode = dto.getAsString("userCode");
		String shopFlag = dto.getAsString("shopFlag");
		CustomerCouponQuery customerCouponQuery = new CustomerCouponQuery(usercode, shopFlag, productNum);
		
		return couponService.getCustomerCoupon(customerCouponQuery);
	}

	@Override
	public ResultMsg checkUserCouponCode(Dto dto) throws Exception {
		String userCode=dto.getAsString("userCode");
		String shopFlag=dto.getAsString("shopFlag");
		String couponCode = dto.getAsString("couponCode");
		Dto dto2 = new BaseDto();
		dto2.put("userCode", userCode);
		dto2.put("storeFlag", shopFlag);
		List<LvShopCart> shopCarts = onlineMallServie.getShopCartList(dto2);
		if (ObjectUtils.isEmpty(shopCarts)) {
			return null;
		}
		Map<String, Integer> productNum = new HashMap<String, Integer>();
		for (Iterator<LvShopCart> iterator = shopCarts.iterator(); iterator.hasNext();) {
			LvShopCart lvShopCart = iterator.next();
			productNum.put(lvShopCart.getProductCode(), lvShopCart.getShopNum());
		}
		CustomerCouponEditCheck customerCouponEditCheck = new CustomerCouponEditCheck(couponCode, userCode, shopFlag, productNum);
		return couponService.checkCustomerCoupon(customerCouponEditCheck);
	}

	@Override
	public List<ActivityLimitOrder> getOrderActivities(String orderNo) throws Exception {
		String hql = "from LvOrderActivity where activityType=:activityType and orderId=:orderId";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("activityType", ActivityConstant.TYPE_LIMIT_ORDER);
		param.put("orderId", orderNo);
		List<LvOrderActivity> activities = lvlogicReadDao.find(hql, param);
		if (ObjectUtils.isEmpty(activities)) {
			return null;
		}
		List<ActivityLimitOrder> activityLimitOrders = new ArrayList<ActivityLimitOrder>();
		for (Iterator<LvOrderActivity> iterator = activities.iterator(); iterator.hasNext();) {
			LvOrderActivity lvOrderActivity = (LvOrderActivity) iterator.next();
			activityLimitOrders.add(activityService.getActivityLimitOrderByCode(lvOrderActivity.getActivityCode()));
		}
		return activityLimitOrders;
	}
	@Override
	public OrderInfoVo getOrderInfoVo(String userCode, String shopFlag) throws Exception {
		//购物车信息
		ShopCartVo shopCartVo = onlineMallService.getShopCartByStore(userCode, shopFlag);
		if (null == shopCartVo || null == shopCartVo.getItems()) {
			return null;//购物车为空
		}
		//获取该用户的非默认地址列表，显示在前台页面上注意不要与默认收货地址重复
		List<LvAccountAddress> addressList = accountAddressService.getUserAddress(userCode);
		//获取该用户的默认收货地址
		LvAccountAddress dAddress = null;
		if (ObjectUtils.isNotEmpty(addressList)) {
			//如果没有默认收货地址，则默认为最后添加的一个
			dAddress = addressList.get(0);
			for (Iterator<LvAccountAddress> iterator = addressList.iterator(); iterator.hasNext();) {
				LvAccountAddress lvAccountAddress = iterator.next();
				if (AppDataConstant.DEFAULT_ADDRESS == lvAccountAddress.getIsDefault().shortValue()) {
					dAddress = lvAccountAddress;
					break;
				}
			}
		}
		//获取运费信息，默认运费是由默认地址的区域编号确定
		float carriage = 0f;
		if(null!=dAddress){
			carriage = getCarrige(dAddress, shopFlag);
		}
		//获取该店铺的支付方式
		Dto dto = new BaseDto();
		dto.put("storeFlag", shopFlag);
		List<LvPaymentStyle> paymentList = getPaymentList(dto);
		//获得用户店铺默认支付方式
		dto.put("userCode", userCode);
		dto.put("paymentList", paymentList);
		LvPaymentStyle dPayment = accountPaymentService.getUserPaystyle(dto);
		//获取国家列表
		List<LvArea> contryList = getContryList(dto);
		//获取当前店铺标志 (可从缓存中获取):包括名称,货币符号
		LvStore store = new LvStore();
		store.setStoreFlag(shopFlag);
		store.setCurrency(Constants.STORE_TO_CURRENCY.get(shopFlag));
		store.setName(Constants.STORE_TO_NAME.get(shopFlag));
		//可使用优惠券列表
		Map<String, ProductPrice> productPriceMap = new HashMap<String, ProductPrice>();//不带商品缓存信息
		for (Iterator<ShopCartItem> iterator = shopCartVo.getItems().iterator(); iterator.hasNext();) {
			ShopCartItem shopCartItem = iterator.next();
			LvShopCart lvShopCart = shopCartItem.getShopCart();
			ProductPrice productPrice = new ProductPrice();
			productPrice.setProductNo(lvShopCart.getProductCode());
			productPrice.setPrice(lvShopCart.getShopPrice());
			productPrice.setAmount(shopCartItem.getSum());
			productPriceMap.put(lvShopCart.getProductCode(), productPrice);
		}
		List<CustomerCoupon> couponList = couponService.getCustomerCouponList(shopFlag, userCode, productPriceMap);
		//购物车总额 + 运费 - 优惠券金额 = 订单总金额
		float orderAmount = FloatUtils.add(shopCartVo.getTotalAmount(), carriage, AppDataConstant.PRICE_SACAL);
		return new OrderInfoVo(shopCartVo, addressList, dAddress, paymentList, dPayment, contryList, store,
				FloatUtils.formatDouble(carriage, AppDataConstant.PRICE_SACAL), FloatUtils.formatDouble(orderAmount, AppDataConstant.PRICE_SACAL), couponList);
	}
}
