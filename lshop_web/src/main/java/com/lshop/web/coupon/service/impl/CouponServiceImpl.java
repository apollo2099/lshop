package com.lshop.web.coupon.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
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
import com.gv.epg.pojo.UcEpgUserInfo;
import com.lshop.common.constant.AppDataConstant;
import com.lshop.common.coupon.constant.CouponConstant;
import com.lshop.common.coupon.constant.ReuseType;
import com.lshop.common.coupon.vo.CouponDetail;
import com.lshop.common.coupon.vo.CustomerCoupon;
import com.lshop.common.coupon.vo.CustomerCouponEditCheck;
import com.lshop.common.coupon.vo.CustomerCouponQuery;
import com.lshop.common.coupon.vo.CustomerCouponSubmitCheck;
import com.lshop.common.coupon.vo.MyCouponViewVo;
import com.lshop.common.coupon.vo.RuleItem;
import com.lshop.common.datastructure.ResultMsg;
import com.lshop.common.pojo.logic.LvCoupon;
import com.lshop.common.pojo.logic.LvCouponApply;
import com.lshop.common.pojo.logic.LvCouponType;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.pojo.logic.LvProductType;
import com.lshop.common.price.vo.ProductPrice;
import com.lshop.common.util.CodeUtils;
import com.lshop.common.util.Constants;
import com.lshop.common.util.DateUtils;
import com.lshop.common.util.FloatUtils;
import com.lshop.common.util.GenericJDBCSupport;
import com.lshop.web.activity.service.ActivityService;
import com.lshop.web.activity.service.impl.ActivityServiceImpl;
import com.lshop.web.coupon.service.CouponService;
import com.lshop.web.order.service.OrderCouponService;
import com.lshop.web.price.service.PriceService;
import com.lshop.web.product.service.ProductService;
import com.lshop.web.store.service.StoreService;
import com.lshop.web.userCenter.action.UcenterForEpg;

/**
 * 优惠券管理模块
 * 
 * @author zhengxue
 * @version 1.0 2014-06-19
 * 
 */
@Service("CouponService")
public class CouponServiceImpl implements CouponService {
	private static final Log logger = LogFactory.getLog(ActivityServiceImpl.class);
	/**
	 * 获取用户可用优惠券
	 */
	private static final String SQL_GET_CUSTOMER_COUPON = "SELECT c.code,ct.name,ct.amount,ct.relation_type,ct.code,c.coupon_code,ct.limit_amount,ct.start_time,ct.end_time FROM lv_coupon c,lv_coupon_type ct,lv_coupon_obtain co WHERE c.coupon_type_code=ct.code AND co.coupon_code=c.code AND co.obtain=? AND (c.coupon_status=? OR (c.coupon_status=? AND ct.reuse=?)) AND ct.currency=? AND ct.status=? AND ct.start_time<=? AND ct.end_time>=? ORDER BY ct.amount DESC";

	/**
	 * 根据优惠券码获取优惠券
	 */
	private static final String SQL_GET_CUSTOMER_COUPON_BY_COUPON_CODE = "SELECT c.code,c.coupon_code,ct.name,ct.amount,ct.relation_type,ct.code,ct.limit_amount,ct.start_time,ct.end_time,c.obtain,ct.reuse,c.coupon_status,ct.reuse_num FROM lv_coupon c,lv_coupon_type ct WHERE c.coupon_type_code=ct.code AND (c.coupon_status=? OR (c.coupon_status=? AND ct.reuse=?)) AND ct.currency=? AND ct.status=? AND ct.start_time<=? AND ct.end_time>=? AND c.coupon_code=?";

	private static final String SQL_GET_CUSTOMER_COUPON_BY_CODE = "SELECT c.code,c.coupon_code,ct.name,ct.amount,ct.relation_type,ct.code,ct.limit_amount,ct.start_time,ct.end_time,c.obtain,ct.reuse,c.coupon_status,ct.reuse_num FROM lv_coupon c,lv_coupon_type ct WHERE c.coupon_type_code=ct.code AND (c.coupon_status=? OR (c.coupon_status=? AND ct.reuse=?)) AND ct.currency=? AND ct.status=? AND ct.start_time<=? AND ct.end_time>=? AND c.code=?";
	// 根据优惠券号获取商品（分类）
	private static final String SQL_GET_PRODUCT_NAME_BY_TYPE = "SELECT pt.type_name, pt.store_id from lv_coupon_product cp LEFT OUTER JOIN lv_product_type pt on cp.relation_code=pt.code WHERE cp.coupon_type_code=?";
	// 根据优惠券号获取商品（商品）
	private static final String SQL_GET_PRODUCT_NAME_BY_CODE = "SELECT p.product_name,cp.store_id FROM lv_coupon_type ct,lv_coupon_product cp,lv_product p WHERE ct.code=cp.coupon_type_code AND cp.relation_code=p.code AND ct.code=? AND p.status=1 AND p.is_support=1";
	// 新增优惠券获取记录
	private static final String SQL_INSERT_COUPON_OBTAIN = "INSERT INTO lv_coupon_obtain(code,coupon_code,obtain,obtain_name,obtain_time) VALUES(?,?,?,?,?)";
	// 新增优惠券使用记录
	private static final String SQL_INSERT_COUPON_APPLY = "INSERT INTO lv_coupon_apply(code,coupon_code,apply,apply_name,apply_time,order_id) VALUES(?,?,?,?,?,?)";
	// 更新优惠券状态和使用相关信息(包括获取人)
	private static final String SQL_UPDATE_COUPON_MORE = "UPDATE lv_coupon c SET c.coupon_status=?,c.order_id=?,c.apply=?,c.apply_time=?,c.apply_name=?,c.obtain=?,c.obtain_name=?,c.obtain_time=? WHERE c.code=?";
	// 更新优惠券状态和使用相关信息
	private static final String SQL_UPDATE_COUPON = "UPDATE lv_coupon c SET c.coupon_status=?,c.order_id=?,c.apply=?,c.apply_time=?,c.apply_name=? WHERE c.code=?";

	@Resource
	private HibernateBaseDAO lvlogicReadDao;
	@Resource
	private HibernateBaseDAO lvlogicWriteDao;
	@Resource
	private HibernateBaseDAO lvuserReadDao;
	
	@Resource
	private ActivityService activityService;

	@Resource
	private StoreService storeService;

	@Resource
	private ProductService productService;
	
	@Resource
	private OrderCouponService orderCouponService;

	

	/**
	 * 获取当前用户的优惠券列表 可传参：是否使用+是否过期状态
	 * 
	 * @param dto
	 * @return
	 * @throws ServiceException
	 * @author zhengxue
	 * @version 1.0 2014-06-19
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Pagination getCouponList(Dto dto) throws ServiceException {

		String useStatus = dto.getAsString("useStatus"); // 使用状态 ：1,未使用，未过期
															// 2，已使用 3，未使用，已过期
		String obtain = dto.getAsString("obtain"); // 领取人
		Pagination page = dto.getDefaultPage();
		HashMap param = new HashMap();

		String hql = "select c from LvCoupon c,LvCouponObtain co where c.code=co.couponCode and co.obtain=:obtain";
		param.put("obtain", obtain);

		if (StringUtils.isNotEmpty(useStatus)) {
			hql += " and c.couponStatus=:couponStatus";
			if (useStatus.equals("2")) {
				param.put("couponStatus", (short) 2); // 已领取已使用
			} else {
				param.put("couponStatus", (short) 1); // 已领取未使用
				if (useStatus.equals("1")) { // 1,未使用，未过期
					hql += " and (select ct.endTime from LvCouponType ct where ct.code=c.couponTypeCode)>=:curTime";
				} else { // 3，未使用，已过期
					hql += " and (select ct.endTime from LvCouponType ct where ct.code=c.couponTypeCode)<:curTime";
				}
				param.put("curTime", new Date());
			}
			List<String> storeFlags = Constants.MALL_SYSTEM_TO_STORE.get(dto.getAsString("mallSystem")); // 当前系统下面所有的店铺标识
			String flags = storeFlags.toString().replaceAll("\\[|\\]", "'").replaceAll("\\, ", "','");
			List codeList = lvlogicReadDao.find("select couponTypeCode from LvCouponProduct where storeId in (" + flags + ")", null);
			String codes = codeList.toString().replaceAll("\\[|\\]", "'").replaceAll("\\, ", "','");
			hql += " and c.couponTypeCode in (" + codes + ")";
		}

		page = lvlogicReadDao.find(Finder.create(hql).setParams(param), page.getPageNum(), page.getNumPerPage());
		List<LvCoupon> list = (List<LvCoupon>) page.getList();
		page.setList(change2MyCouponViewVo(list, obtain));
		return page;
	}

	public List<MyCouponViewVo> change2MyCouponViewVo(List<LvCoupon> list, String username) {
		List<MyCouponViewVo> myCouponViewVos = new ArrayList<MyCouponViewVo>();

		for (LvCoupon coupon : list) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("couponCode", coupon.getCode());
			param.put("apply", username);

			List<LvCouponApply> couponApplys = (List<LvCouponApply>) lvlogicReadDao.find(
					"select ca from LvCouponApply ca where ca.couponCode=:couponCode and ca.apply=:apply order by ca.applyTime desc", param);

			MyCouponViewVo myCouponViewVo = new MyCouponViewVo();
			myCouponViewVo.setGrantActivity(coupon.getGrantActivity());
			myCouponViewVo.setCouponTypeCode(coupon.getCouponTypeCode());
			myCouponViewVo.setGrantWay(coupon.getGrantWay());

			if (CollectionUtils.isNotEmpty(couponApplys)) {
				LvCouponApply couponApply = couponApplys.get(0);
				myCouponViewVo.setApplyTime(couponApply.getApplyTime());
				myCouponViewVo.setOrderId(couponApply.getOrderId());
			}
			myCouponViewVos.add(myCouponViewVo);
		}
		return myCouponViewVos;

	}

	/**
	 * 根据优惠码获取其对应的优惠券类型
	 * 
	 * @param dto
	 * @return
	 * @throws ServiceException
	 * @author zhengxue
	 * @version 1.0 2014-06-19
	 */
	@Override
	public LvCouponType getCouponTypeByCoupon(Dto dto) throws ServiceException {

		String code = dto.getAsString("code");
		LvCouponType couponType = (LvCouponType) lvlogicReadDao.findUniqueByProperty(LvCouponType.class, "code", code);

		return couponType;
	}

	/**
	 * 根据优惠券类型获取指定商品或商品类别code
	 * 
	 * @param dto
	 * @return
	 * @throws ServiceException
	 * @author zhengxue
	 * @version 1.0 2014-06-19
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List getCouponProductsByType(Dto dto) throws ServiceException {

		String couponTypeCode = dto.getAsString("couponTypeCode"); // 商品类型code

		String hql = "select relationCode from LvCouponProduct where couponTypeCode=:couponTypeCode";
		HashMap param = new HashMap();
		param.put("couponTypeCode", couponTypeCode);
		List codeList = lvlogicReadDao.find(hql, param);

		return codeList;
	}

	/**
	 * 根据优惠券类型获取指定的商品
	 * 
	 * @param dto
	 * @return
	 * @throws ServiceException
	 * @author zhengxue
	 * @version 1.0 2014-06-19
	 */
	@Override
	public List<LvProduct> getProductsByCoupon(Dto dto) throws ServiceException {

		// 根据优惠券类型code获取关联的商品code（dto里面有传优惠券类型code这个参数）
		List codeList = this.getCouponProductsByType(dto);

		List<LvProduct> productList = null;
		if (null != codeList && codeList.size() > 0) {
			String codes = codeList.toString().replaceAll("\\[|\\]", "'").replaceAll("\\, ", "','");
			String hql = "from LvProduct where code in (" + codes + ")";
			productList = (List<LvProduct>) lvlogicReadDao.find(hql, null);
		}

		return productList;
	}

	/**
	 * 根据优惠券类型获取指定的商品类别
	 * 
	 * @param dto
	 * @return
	 * @throws ServiceException
	 * @author zhengxue
	 * @version 1.0 2014-06-19
	 */
	@Override
	public List<LvProductType> getProductTypesByCoupon(Dto dto) throws ServiceException {

		// 根据优惠券类型code获取关联的商品code（dto里面有传优惠券类型code这个参数）
		List codeList = this.getCouponProductsByType(dto);

		List<LvProductType> productTypeList = null;
		if (null != codeList && codeList.size() > 0) {
			String codes = codeList.toString().replaceAll("\\[|\\]", "'").replaceAll("\\, ", "','");
			String hql = "from LvProductType where code in (" + codes + ")";
			productTypeList = (List<LvProductType>) lvlogicReadDao.find(hql, null);
		}

		return productTypeList;
	}

	public HibernateBaseDAO getLvlogicReadDao() {
		return lvlogicReadDao;
	}

	public void setLvlogicReadDao(HibernateBaseDAO lvlogicReadDao) {
		this.lvlogicReadDao = lvlogicReadDao;
	}

	@Override
	public List<CustomerCoupon> getCustomerCoupon(CustomerCouponQuery customerCouponQuery) throws Exception {

		List<CustomerCoupon> list = null;

		if (checkCustomerCouponQuery(customerCouponQuery)) {
			// 用户名
			String username = customerCouponQuery.getUsername();
			// 店铺标识
			String storeId = customerCouponQuery.getStoreId();
			// 订单商品编码数量
			Map<String, Integer> productNumMap = customerCouponQuery.getProductNumMap();

			// 获取商品单价
			Map<String, ProductPrice> productPriceMap = productService.getBachProductPrice(productNumMap);

			list = getCustomerCouponList(storeId, username, productPriceMap);
		}

		return list;
	}

	public boolean checkCustomerCouponQuery(CustomerCouponQuery customerCouponQuery) {
		boolean result = false;
		if (null != customerCouponQuery) {
			/**
			 * 用户名
			 */
			String username = customerCouponQuery.getUsername();
			/**
			 * 店铺标识
			 */
			String storeId = customerCouponQuery.getStoreId();
			/**
			 * 订单商品编码与数量
			 */
			Map<String, Integer> productNumMap = customerCouponQuery.getProductNumMap();

			result = true;
			if (StringUtils.isBlank(username)) {
				result = false;
				logger.error("用户名为空");
			} else if (StringUtils.isBlank(storeId)) {
				result = false;
				logger.error("店铺标识为空");
			} else if (MapUtils.isEmpty(productNumMap)) {
				result = false;
				logger.error("商品数量为空");
			}
		}

		return result;
	}

	/**
	 * 
	 * @Description:获取优惠券列表(存在获取人)
	 * @author CYJ
	 * @date 2014-6-25 下午4:28:27
	 * @param storeId
	 * @param username
	 * @param orderAmount
	 * @param productNoList
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<CustomerCoupon> getCustomerCouponList(String storeId, String username, Map<String, ProductPrice> productPriceMap) throws Exception {
		List<CustomerCoupon> list = null;
		Date nowDate = new Date();

		String currency = Constants.STORE_TO_CURRENCY.get(storeId);// 币种

		List<Object[]> objsList = GenericJDBCSupport.queryBySQLForSlave(SQL_GET_CUSTOMER_COUPON, new Object[] { username, CouponConstant.STATUS_GET_DISUSE,
				CouponConstant.STATUS_GET_USE, ReuseType.reuse, currency, CouponConstant.STATUS_USE, nowDate, nowDate });

		if (CollectionUtils.isNotEmpty(objsList)) {
			list = new ArrayList<CustomerCoupon>();

			Set<String> mainCodeSuccessSet = new HashSet<String>();// 满足使用条件的优惠券类型集合
			Set<String> mainCodeFailSet = new HashSet<String>();// 无法满足使用条件的优惠券类型集合

			for (Object[] objs : objsList) {

				// 优惠券业务编码
				String code = objs[0].toString();

				// 优惠券名称
				String name = objs[1].toString();

				// 优惠金额
				Float amount = Float.parseFloat(objs[2].toString());

				// 关联类型
				Integer relationType = Integer.parseInt(objs[3].toString());

				// 主表code
				String mainCode = objs[4].toString();

				// 优惠券编码
				String couponCode = objs[5].toString();

				// 指定金额
				Float amountLimit = Float.parseFloat(objs[6].toString());

				// 开始时间
				Date startTime = DateUtils.parseDateTime(objs[7].toString(), "yyyy-MM-dd HH:mm:ss");

				// 结束时间
				Date endTime = DateUtils.parseDateTime(objs[8].toString(), "yyyy-MM-dd HH:mm:ss");

				if (mainCodeFailSet.contains(mainCode)) {
					// 无法满足使用条件的优惠券类型
					continue;
				}

				if (mainCodeSuccessSet.contains(mainCode)) {
					// 满足使用条件的优惠券类型(无需重复校验)
					CustomerCoupon customerCoupon = new CustomerCoupon(code, name, amount, currency, couponCode, startTime, endTime);

					list.add(customerCoupon);
					continue;
				}

				// 是否有效优惠券，使用在当前商品。
				if (isEffectCoupon(relationType, mainCode, productPriceMap, amountLimit)) {
					CustomerCoupon customerCoupon = new CustomerCoupon(code, name, amount, currency, couponCode, startTime, endTime);
					list.add(customerCoupon);

					if (!mainCodeSuccessSet.contains(mainCode)) {
						mainCodeSuccessSet.add(mainCode);
					}
				} else {
					if (!mainCodeFailSet.contains(mainCode)) {
						mainCodeFailSet.add(mainCode);
					}
				}
			}
		}
		return list;
	}

	/**
	 * 验证是否有效优惠券
	 * 
	 * @param type
	 *            输入类型 (1=优惠券码，2=优惠券业务编码)
	 * 
	 * @param srcCode
	 *            优惠券码或业务编码
	 * @param storeId
	 *            店铺表示
	 * @param productPriceMap
	 *            订单商品金额
	 * @param username
	 *            用户名
	 * @return
	 * @throws Exception
	 */
	public ResultMsg isCoupon(int type, String srcCode, String storeId, Map<String, ProductPrice> productPriceMap, String username) throws Exception {
		ResultMsg resultMsg = new ResultMsg();
		resultMsg.setSuccess(false);
		CustomerCoupon customerCoupon = null;

		Date nowDate = new Date();

		String currency = Constants.STORE_TO_CURRENCY.get(storeId);// 币种

		List<Object[]> objsList = null;

		if (type == 1) {
			// 根据优惠券码查询
			objsList = GenericJDBCSupport.queryBySQLForSlave(SQL_GET_CUSTOMER_COUPON_BY_COUPON_CODE, new Object[] { CouponConstant.STATUS_GET_DISUSE,
					CouponConstant.STATUS_GET_USE, ReuseType.reuse, currency, CouponConstant.STATUS_USE, nowDate, nowDate, srcCode });
		} else if (type == 2) {
			// 根据优惠券业务编码查询
			objsList = GenericJDBCSupport.queryBySQLForSlave(SQL_GET_CUSTOMER_COUPON_BY_CODE, new Object[] { CouponConstant.STATUS_GET_DISUSE, CouponConstant.STATUS_GET_USE,
					ReuseType.reuse, currency, CouponConstant.STATUS_USE, nowDate, nowDate, srcCode });
		}

		if (CollectionUtils.isNotEmpty(objsList) && objsList.size() == 1) {

			Object[] objs = objsList.get(0);

			// 优惠券业务编码
			String code = objs[0].toString();

			// 优惠券业务编码
			String couponCode = objs[1].toString();

			// 优惠券名称
			String name = objs[2].toString();

			// 优惠金额
			Float amount = Float.parseFloat(objs[3].toString());

			// 关联类型
			Integer relationType = Integer.parseInt(objs[4].toString());

			// 主表code
			String mainCode = objs[5].toString();

			// 指定金额
			Float amountLimit = Float.parseFloat(objs[6].toString());

			// 开始时间
			Date startTime = DateUtils.parseDateTime(objs[7].toString(), "yyyy-MM-dd HH:mm:ss");

			// 结束时间
			Date endTime = DateUtils.parseDateTime(objs[8].toString(), "yyyy-MM-dd HH:mm:ss");

			// 获取人
			String obtain = objs[9] == null ? "" : objs[9].toString();

			// 重复使用
			Integer reuse = Integer.parseInt(objs[10].toString());

			// 优惠券状态
			Integer couponStatus = Integer.parseInt(objs[11].toString());
			
			//重复使用次数
			Integer reuseNum=Integer.parseInt(objs[12].toString());

			boolean flag = true;
			// 普通优惠券校验
			// 1.只可使用一次
			// 2.获取人非空时，必须为当前操作用户
			if (reuse.intValue() == ReuseType.use) {
				// 普通优惠券（只可使用一次）
				if (couponStatus.intValue() == CouponConstant.STATUS_GET_USE) {
					flag = false;
				} else {
					// 获取人非空（必须是当前操作用户）
					if (StringUtils.isNotBlank(obtain)) {
						if (!obtain.equals(username)) {
							flag = false;
						}
					}
				}
			}else if(reuse.intValue()==ReuseType.reuse){//重复使用优惠券校验
				//reuseNum
				//查询当前mac订单使用次数
				int num=orderCouponService.countByCoupon(couponCode);
				if(num>=reuseNum){
					flag = false;
				}
			}
			
			
			
			// 是否有效优惠券
			if (flag && isEffectCoupon(relationType, mainCode, productPriceMap, amountLimit)) {
				customerCoupon = new CustomerCoupon(code, name, amount, currency, couponCode, startTime, endTime);
				resultMsg.setSuccess(true);
				resultMsg.setReObj(customerCoupon);
				resultMsg.setReCode(obtain);
				resultMsg.setIdCode(reuse.toString());
			} else {
				logger.error("该优惠券不满足使用条件 " + type + " " + srcCode);
			}
		} else {
			logger.error("非有效优惠券 " + type + " " + srcCode);
		}

		return resultMsg;
	}

	/**
	 * 
	 * @Description:是否有效优惠券，使用在当前商品。
	 * @author CYJ
	 * @date 2014-6-25 下午3:31:13
	 * @param relationType
	 * @param mainCode
	 * @param productNoList
	 * @return
	 * @throws Exception
	 */
	public boolean isEffectCoupon(Integer relationType, String mainCode, Map<String, ProductPrice> productPriceMap, Float amountLimit) throws Exception {
		boolean result = false;

		String sql = null;

		if (relationType == CouponConstant.SCOPE_PRODUCT) {
			// 根据商品编码关联
			sql = "SELECT cp.relation_code FROM lv_coupon_type ct,lv_coupon_product cp WHERE ct.code=cp.coupon_type_code AND ct.code=?";
		} else if (relationType == CouponConstant.SCOPE_TYPE) {
			// 根据商品类型关联
			sql = "SELECT  p.code FROM lv_coupon_type ct,lv_coupon_product cp,lv_product p WHERE ct.code=cp.coupon_type_code AND cp.relation_code=p.ptype AND ct.code=? AND p.status=1 AND p.is_support=1";
		}

		List<Object[]> list = GenericJDBCSupport.queryBySQLForSlave(sql, new Object[] { mainCode });// 查询优惠券对应的商品

		if (CollectionUtils.isNotEmpty(list)) {

			// 订单商品总金额（可以使用当前优惠券的商品）
			Float orderAmount = Float.valueOf(0);

			for (Object[] objs : list) {
				String cProductNo = objs[0].toString();// 当前优惠券对应的商品编码
				if (productPriceMap.containsKey(cProductNo)) {
					// 订单商品适用(累计金额)
					ProductPrice productPrice = productPriceMap.get(cProductNo);
					orderAmount = FloatUtils.add(orderAmount, productPrice.getAmount(), 2);
				}
			}
			if (orderAmount >= amountLimit) {
				// 该优惠券可使用
				result = true;
			}
		}

		return result;
	}

	@Override
	public ResultMsg checkCustomerCoupon(CustomerCouponEditCheck customerCouponEditCheck) throws Exception {
		ResultMsg resultMsg = new ResultMsg();
		resultMsg.setSuccess(false);

		if (checkCustomerCouponEditCheck(customerCouponEditCheck)) {
			/**
			 * 优惠券编码
			 */
			String couponCode = customerCouponEditCheck.getCouponCode();
			/**
			 * 用户名
			 */
			String username = customerCouponEditCheck.getUsername();
			/**
			 * 店铺标识
			 */
			String storeId = customerCouponEditCheck.getStoreId();
			/**
			 * 订单商品编码数量
			 */
			Map<String, Integer> productNumMap = customerCouponEditCheck.getProductNumMap();

			// 获取商品单价
			Map<String, ProductPrice> productPriceMap = productService.getBachProductPrice(productNumMap);

			// 验证是否有效优惠券
			ResultMsg isCouponRs = isCoupon(1, couponCode, storeId, productPriceMap, username);
			if (isCouponRs.isSuccess()) {
				resultMsg.setSuccess(true);
				resultMsg.setReObj(isCouponRs.getReObj());
			}
		}

		return resultMsg;
	}

	public boolean checkCustomerCouponEditCheck(CustomerCouponEditCheck customerCouponEditCheck) {
		boolean result = false;
		if (null != customerCouponEditCheck) {
			/**
			 * 优惠券编码
			 */
			String couponCode = customerCouponEditCheck.getCouponCode();
			/**
			 * 用户名
			 */
			String username = customerCouponEditCheck.getUsername();
			/**
			 * 店铺标识
			 */
			String storeId = customerCouponEditCheck.getStoreId();
			/**
			 * 订单商品编码数量
			 */
			Map<String, Integer> productNumMap = customerCouponEditCheck.getProductNumMap();

			result = true;
			if (StringUtils.isBlank(couponCode)) {
				result = false;
				logger.error("优惠券为空");
			} else if (StringUtils.isBlank(username)) {
				result = false;
				logger.error("用户名为空");
			} else if (StringUtils.isBlank(storeId)) {
				result = false;
				logger.error("店铺标识为空");
			} else if (MapUtils.isEmpty(productNumMap)) {
				result = false;
				logger.error("商品为空");
			}
		}

		return result;
	}

	@Override
	public ResultMsg useCustomerCoupon(String orderNo, CustomerCouponSubmitCheck customerCouponSubmitCheck) throws Exception {
		ResultMsg resultMsg = new ResultMsg();
		resultMsg.setSuccess(false);

		ResultMsg checkRs = checkCustomerCouponSubmit(customerCouponSubmitCheck);
		if (checkRs.isSuccess()) {

			Map<String, List<Object[]>> sqlBatchs = new HashMap<String, List<Object[]>>();

			Date nowDate = new Date();
			String code = customerCouponSubmitCheck.getCode();

			// 更新用户优惠券状态
			UcEpgUserInfo ucEpgUserInfo = getUcEpgUserInfo(customerCouponSubmitCheck.getUsername());
			String applyName = null;
			if (null != ucEpgUserInfo) {
				applyName = ucEpgUserInfo.getName();
			} else {
				logger.error("无法获取用户信息， 用户名" + customerCouponSubmitCheck.getUsername());
			}

			int couponStatus = CouponConstant.STATUS_GET_USE;
			Integer reuse = Integer.parseInt(checkRs.getIdCode());

			String obtain = checkRs.getReCode();

			String username = customerCouponSubmitCheck.getUsername();

			if (StringUtils.isNotBlank(obtain)) {
				// 已存在获取人
				if (reuse.intValue() == ReuseType.reuse) {
					// 重复使用优惠券
					if (obtain.equals(username)) {
						// 获取人与使用人相同，无需更新获取人。
						List<Object[]> updateParam = new ArrayList<Object[]>();
						updateParam.add(new Object[] { couponStatus, orderNo, username, nowDate, applyName, code });
						sqlBatchs.put(SQL_UPDATE_COUPON, updateParam);
					} else {
						// 获取人与使用人不相同，需更新获取人。
						List<Object[]> updateParam = new ArrayList<Object[]>();
						updateParam.add(new Object[] { couponStatus, orderNo, username, nowDate, applyName, username, applyName, nowDate, code });
						sqlBatchs.put(SQL_UPDATE_COUPON_MORE, updateParam);

						// 新增获取记录
						List<Object[]> insertObtainParam = new ArrayList<Object[]>();
						insertObtainParam.add(new Object[] { CodeUtils.getCode(), code, username, applyName, nowDate });
						sqlBatchs.put(SQL_INSERT_COUPON_OBTAIN, insertObtainParam);
					}
				} else {
					// 普通优惠券
					List<Object[]> updateParam = new ArrayList<Object[]>();

					updateParam.add(new Object[] { couponStatus, orderNo, username, nowDate, applyName, code });
					sqlBatchs.put(SQL_UPDATE_COUPON, updateParam);
				}
			} else {
				// 未存在获取人
				String sql_update_coupon = "UPDATE lv_coupon c SET c.coupon_status=?,c.order_id=?,c.apply=?,c.apply_time=?,c.apply_name=?,c.obtain=?,c.obtain_name=?,c.obtain_time=? WHERE c.code=?";
				List<Object[]> updateParam = new ArrayList<Object[]>();

				updateParam.add(new Object[] { couponStatus, orderNo, username, nowDate, applyName, username, applyName, nowDate, code });
				sqlBatchs.put(sql_update_coupon, updateParam);

				// 新增获取记录
				List<Object[]> insertObtainParam = new ArrayList<Object[]>();
				insertObtainParam.add(new Object[] { CodeUtils.getCode(), code, username, applyName, nowDate });
				sqlBatchs.put(SQL_INSERT_COUPON_OBTAIN, insertObtainParam);
			}

			// 新增使用记录
			List<Object[]> insertApplyParam = new ArrayList<Object[]>();
			insertApplyParam.add(new Object[] { CodeUtils.getCode(), code, username, applyName, nowDate, orderNo });
			sqlBatchs.put(SQL_INSERT_COUPON_APPLY, insertApplyParam);

			// 新增订单优惠券关联
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("code", code);
			LvCouponType lvCouponType = (LvCouponType) lvlogicReadDao
					.findUnique("SELECT ct FROM LvCoupon c,LvCouponType ct WHERE c.couponTypeCode=ct.code AND c.code=:code", param);

			LvCoupon lvCoupon = (LvCoupon) lvlogicReadDao.findUnique("SELECT c FROM LvCoupon c WHERE c.code=:code", param);

			String sql_insert_order_coupon = "INSERT INTO lv_order_coupon(order_id,coupon_type_code,coupon_name,coupon_price,coupon_code,code,create_time) VALUES(?,?,?,?,?,?,?)";
			List<Object[]> insertParam = new ArrayList<Object[]>();
			insertParam.add(new Object[] { orderNo, lvCouponType.getCode(), lvCouponType.getName(), lvCouponType.getAmount(), lvCoupon.getCouponCode(), CodeUtils.getCode(),
					nowDate });
			sqlBatchs.put(sql_insert_order_coupon, insertParam);

			// 维护优惠券主表相关数据
			if (reuse == ReuseType.reuse) {
				String apply = lvCoupon.getApply();
				if (StringUtils.isBlank(apply)) {
					// 首次使用更新
					String sql_update_coupon_main = "UPDATE lv_coupon_type ct,lv_coupon c SET ct.used_number=IFNULL(ct.used_number,0)+?,ct.grant_number=IFNULL(ct.grant_number,0)-? WHERE c.coupon_type_code=ct.code AND c.code=?";
					List<Object[]> updateMainParam = new ArrayList<Object[]>();
					updateMainParam.add(new Object[] { 1, 1, code });
					sqlBatchs.put(sql_update_coupon_main, updateMainParam);
				}
			}

			boolean result = GenericJDBCSupport.executeBatch(sqlBatchs);
			resultMsg.setSuccess(result);
			resultMsg.setReObj(lvCouponType.getAmount());
		}
		return resultMsg;
	}

	/**
	 * 缓存获取用户信息
	 * 
	 * @param userName
	 *            用户名
	 * @return
	 */
	public UcEpgUserInfo getUcEpgUserInfo(String userName) {
		Dto dto = new BaseDto();
		dto.put("usercode", userName);
		UcEpgUserInfo ucEpgUserInfo = UcenterForEpg.getUserInfo(dto);
		return ucEpgUserInfo;
	}

	public ResultMsg checkCustomerCouponSubmit(CustomerCouponSubmitCheck customerCouponSubmitCheck) throws Exception {
		ResultMsg resultMsg = new ResultMsg();
		resultMsg.setSuccess(false);

		if (checkCustomerCouponSubmitCheck(customerCouponSubmitCheck)) {
			/**
			 * 优惠券业务编码
			 */
			String code = customerCouponSubmitCheck.getCode();
			/**
			 * 用户名
			 */
			String username = customerCouponSubmitCheck.getUsername();
			/**
			 * 店铺标识
			 */
			String storeId = customerCouponSubmitCheck.getStoreId();
			/**
			 * 订单商品编码数量
			 */
			Map<String, Integer> productNumMap = customerCouponSubmitCheck.getProductNumMap();

			// 获取商品单价
			Map<String, ProductPrice> productPriceMap = productService.getBachProductPrice(productNumMap);

			// 验证是否有效优惠券
			resultMsg = isCoupon(2, code, storeId, productPriceMap, username);
		}

		return resultMsg;
	}

	public boolean checkCustomerCouponSubmitCheck(CustomerCouponSubmitCheck customerCouponSubmitCheck) {
		boolean result = false;
		if (null != customerCouponSubmitCheck) {
			/**
			 * 优惠券业务编码
			 */
			String code = customerCouponSubmitCheck.getCode();
			/**
			 * 用户名
			 */
			String username = customerCouponSubmitCheck.getUsername();
			/**
			 * 店铺标识
			 */
			String storeId = customerCouponSubmitCheck.getStoreId();
			/**
			 * 订单商品编码数量
			 */
			Map<String, Integer> productNumMap = customerCouponSubmitCheck.getProductNumMap();

			result = true;
			if (StringUtils.isBlank(code)) {
				result = false;
				logger.error("优惠券业务编码为空");
			} else if (StringUtils.isBlank(username)) {
				result = false;
				logger.error("用户名为空");
			} else if (StringUtils.isBlank(storeId)) {
				result = false;
				logger.error("店铺标识为空");
			} else if (MapUtils.isEmpty(productNumMap)) {
				result = false;
				logger.error("商品为空");
			}
		}

		return result;
	}

	@Override
	public Map<String, List<Object[]>> costCouponSqlMap(Map<String, Integer> couponQuantityMap) throws Exception {
		Map<String, List<Object[]>> sqlMap = new HashMap<String, List<Object[]>>();
		String updateMain = "UPDATE lv_coupon_type o SET o.grant_number=IFNULL(o.grant_number,0)+?,o.no_grant_number=IFNULL(o.no_grant_number,0)-? WHERE o.code=?";
		List<Object[]> updateMainParam = new ArrayList<Object[]>();

		for (Entry<String, Integer> couponQuantity : couponQuantityMap.entrySet()) {
			String code = couponQuantity.getKey();// 优惠券类型业务编码
			Integer quantity = couponQuantity.getValue();// 优惠券数量
			updateMainParam.add(new Object[] { quantity, quantity, code });
		}

		sqlMap.put(updateMain, updateMainParam);

		return sqlMap;
	}

	@Override
	public Map<String, List<Object[]>> updateUserCouponSqlMap(String couponCode, String username) throws Exception {
		Map<String, List<Object[]>> sqlMap = new HashMap<String, List<Object[]>>();
		String updateDetail = "UPDATE lv_coupon o SET o.coupon_status=?,o.obtain=?,o.obtain_time=?,obtain_name=? WHERE o.code=?";
		List<Object[]> updateDetailParam = new ArrayList<Object[]>();

		// 缓存获取用户信息
		UcEpgUserInfo ucEpgUserInfo = getUcEpgUserInfo(username);
		String obtainName = null;
		if (null != ucEpgUserInfo) {
			obtainName = ucEpgUserInfo.getName();
		} else {
			logger.error("无法获取用户信息，用户名" + username);
		}
		Date nowDate = new Date();

		updateDetailParam.add(new Object[] { CouponConstant.STATUS_GET_DISUSE, username, nowDate, obtainName, couponCode });

		sqlMap.put(updateDetail, updateDetailParam);

		// 新增获取记录
		List<Object[]> insertObtainParam = new ArrayList<Object[]>();
		insertObtainParam.add(new Object[] { CodeUtils.getCode(), couponCode, username, obtainName, nowDate });
		sqlMap.put(SQL_INSERT_COUPON_OBTAIN, insertObtainParam);

		return sqlMap;
	}

	public Map<String, List<Object[]>> updateUserCouponSqlMap(String couponCode) throws Exception {
		Map<String, List<Object[]>> sqlMap = new HashMap<String, List<Object[]>>();
		String updateDetail = "UPDATE lv_coupon o SET o.coupon_status=?,o.obtain_time=? WHERE o.code=?";
		List<Object[]> updateDetailParam = new ArrayList<Object[]>();
		updateDetailParam.add(new Object[] { CouponConstant.STATUS_GET_DISUSE, new Date(), couponCode });
		sqlMap.put(updateDetail, updateDetailParam);
		return sqlMap;
	}

	@Override
	public CouponDetail getCouponDetail(String couponNo) throws Exception {
		LvCoupon coupon = (LvCoupon) lvlogicReadDao.findUniqueByProperty(LvCoupon.class, "code", couponNo);
		if (ObjectUtils.isEmpty(coupon)) {
			return null;
		}
		LvCouponType couponType = (LvCouponType) lvlogicReadDao.findUniqueByProperty(LvCouponType.class, "code", coupon.getCouponTypeCode());
		if (ObjectUtils.isEmpty(couponType)) {
			return null;
		}
		CouponDetail detail = new CouponDetail();
		detail.setCode(coupon.getCode());
		detail.setCouponCode(coupon.getCouponCode());
		detail.setCouponName(couponType.getName());
		detail.setCurrency(couponType.getCurrency());
		detail.setFaceValue(FloatUtils.formatDouble(couponType.getAmount(), AppDataConstant.PRICE_SACAL));
		detail.setStartTime(couponType.getStartTime());
		detail.setEndTime(couponType.getEndTime());
		detail.setLimitedType(couponType.getRelationType());
		// get product or catalog
		detail.setItems(getCouponRuleItemt(couponType.getRelationType().intValue(), couponType.getCode()));
		detail.setLimitedAmount(FloatUtils.formatDouble(couponType.getLimitAmount(), AppDataConstant.PRICE_SACAL));
		return detail;
	}

	/**
	 * @param relationType
	 * @param couponMainCode
	 * @return
	 * @throws Exception
	 */
	private List<RuleItem> getCouponRuleItemt(Integer relationType, String couponMainCode) throws Exception {

		List<Object[]> productNameList = new ArrayList<Object[]>();
		if (relationType == CouponConstant.SCOPE_TYPE) {
			// 关联类型(商品类型)
			productNameList = GenericJDBCSupport.queryBySQLForSlave(SQL_GET_PRODUCT_NAME_BY_TYPE, new Object[] { couponMainCode });
		} else if (relationType == CouponConstant.SCOPE_PRODUCT) {
			// 关联类型(商品)
			productNameList = GenericJDBCSupport.queryBySQLForSlave(SQL_GET_PRODUCT_NAME_BY_CODE, new Object[] { couponMainCode });
		}

		List<RuleItem> items = new ArrayList<RuleItem>();
		for (Object[] objs : productNameList) {
			items.add(new RuleItem(objs[0].toString(), Constants.STORE_TO_NAME.get(objs[1].toString())));
		}

		return items;
	}

	@Override
	public boolean obtainCouponByLottery(String couponCode) {
		String hql = "select couponTypeCode from LvCoupon where code=:code";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("code", couponCode);
		String typeCode = (String) lvlogicReadDao.findUnique(hql, param);
		// 更新优惠券类型
		hql = "update LvCouponType set grantNumber=grantNumber+1, noGrantNumber=noGrantNumber-1 where code=:code";
		param.put("code", typeCode);
		lvlogicWriteDao.update(hql, param);
		// 更新优惠券
		hql = "update LvCoupon set couponStatus=:status,obtainTime=:obtainTime where code=:code";
		param = new HashMap<String, Object>();
		param.put("status", (short) CouponConstant.STATUS_GET_DISUSE);
		param.put("obtainTime", new Date());
		param.put("code", couponCode);
		lvlogicWriteDao.update(hql, param);
		return true;
	}
}
