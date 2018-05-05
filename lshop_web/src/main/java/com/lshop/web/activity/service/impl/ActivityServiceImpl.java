package com.lshop.web.activity.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.activity.constant.ActivityConstant;
import com.lshop.common.activity.vo.Activity;
import com.lshop.common.activity.vo.ActivityAppoindProduct;
import com.lshop.common.activity.vo.ActivityLimitOrder;
import com.lshop.common.activity.vo.ActivityLimitTime;
import com.lshop.common.activity.vo.ActivityLimited;
import com.lshop.common.activity.vo.ActivityLink;
import com.lshop.common.activity.vo.GeneralActivity;
import com.lshop.common.activity.vo.OrderActivity;
import com.lshop.common.coupon.constant.CouponConstant;
import com.lshop.common.coupon.constant.ReuseType;
import com.lshop.common.coupon.vo.CouponView;
import com.lshop.common.datastructure.ResultMsg;
import com.lshop.common.pojo.logic.LvActivity;
import com.lshop.common.pojo.logic.LvActivityAppointProduct;
import com.lshop.common.pojo.logic.LvActivityGift;
import com.lshop.common.pojo.logic.LvActivityLimitOrder;
import com.lshop.common.product.constant.ProductConstant;
import com.lshop.common.util.CodeUtils;
import com.lshop.common.util.DateUtils;
import com.lshop.common.util.DoubleUtils;
import com.lshop.common.util.GenericJDBCSupport;
import com.lshop.common.util.RedisClient;
import com.lshop.common.util.RedisConstant;
import com.lshop.common.util.SqlMapUtils;
import com.lshop.common.util.StringUtil;
import com.lshop.web.activity.service.ActivityGiftService;
import com.lshop.web.activity.service.ActivityService;
import com.lshop.web.coupon.service.CouponService;
import com.lshop.web.lottery.service.LotteryChanceService;

/**
 * 
 * @Description:活动管理
 * @author CYJ
 * @date 2014-6-18 下午2:13:21
 */
@Service("ActivityService")
public class ActivityServiceImpl implements ActivityService {
	private static final Log logger = LogFactory.getLog(ActivityServiceImpl.class);
	// 获取优惠券（链接领券）
	private static final String SQL_GET_COUPON = "SELECT o.code,t.code FROM lv_coupon_type o,lv_coupon t WHERE o.code=t.coupon_type_code AND o.status=? AND t.grant_way=? AND t.coupon_status=?  AND t.code=?";
	// 获取用户优惠券数量
	private static final String SQL_GET_USER_COUPON_NUM = "SELECT t.grant_way,t.obtain,COUNT(t.coupon_code) FROM lv_coupon_type o,lv_coupon t,lv_activity a WHERE o.code=t.coupon_type_code AND t.grant_way=a.code AND (t.coupon_status=? OR t.coupon_status=?) AND t.grant_type=? AND a.type_key=? GROUP BY t.grant_way,t.obtain";
	// 根据优惠券号获取商品（商品）
	private static final String SQL_GET_PRODUCT_NAME_BY_CODE = "SELECT p.product_name,s.name FROM lv_coupon_type ct,lv_coupon_product cp,lv_product p,lv_store s WHERE ct.code=cp.coupon_type_code AND cp.relation_code=p.code AND ct.code=? AND p.status=1 AND p.is_support=1 AND p.store_id=s.store_flag AND s.isdel=0";
	// 根据优惠券号获取商品（分类）
	private static final String SQL_GET_PRODUCT_NAME_BY_TYPE = "SELECT pt.type_name,s.name FROM lv_coupon_type ct,lv_coupon_product cp,lv_store s,lv_product_type pt WHERE ct.code=cp.coupon_type_code AND cp.relation_code=pt.code AND ct.code=? AND pt.store_id=s.store_flag AND s.isdel=0";
	// 获取赠品优惠券
	private static final String SQL_GET_GIFT = "SELECT a.code,a.activity_title,alo.limit_total_price,alo.given_type_name,alo.given_product_code,alo.given_type_num,ct.amount,ct.name,ct.limit_amount FROM lv_activity a,lv_activity_limit_order alo,lv_coupon_type ct WHERE a.code=alo.activity_code AND alo.given_product_code=ct.code AND alo.store_id=? AND a.status=? AND a.start_time<=? AND a.end_time>=? AND a.type_key=? AND ct.status=? AND alo.limit_total_price<=? AND alo.uncollected_total>0 ORDER BY alo.limit_total_price DESC LIMIT 1";
	// 生成订单活动关系
	private static final String SQL_INSERT_ORDER_ACTIVITY = "INSERT INTO lv_order_activity(activity_code,activity_type,order_id,code,create_time,status) VALUES(?,?,?,?,?,?)";

	@Resource
	private HibernateBaseDAO lvlogicReadDao;
	@Resource
	private HibernateBaseDAO lvlogicWriteDao;
	@Resource
	private CouponService couponService;
	@Resource
	private LotteryChanceService lotteryChanceService;
	@Resource
	private ActivityGiftService activityGiftService;

	// 缓存(链接活动对象)
	private static Map<String, ActivityLink> linkActivityMap = new Hashtable<String, ActivityLink>();

	@Override
	public Activity getActivityVoByProductNo(String productNo) throws Exception {
		Activity activityVo = null;
		if (StringUtils.isNotBlank(productNo)) {
			// 检查商品是否开启活动
			if (GenericJDBCSupport.isExistForSlave("SELECT p.id FROM lv_product p WHERE p.code=? AND p.is_activity=?", new Object[] { productNo, ProductConstant.USE_ACTIVITY })) {

				Date nowDate = new Date();
				// 查询是否参加限时活动
				Object[] timeObjs = GenericJDBCSupport
						.findUniqueBySQLForSlave(
								"SELECT o.code,o.activity_title,o.start_time,o.end_time FROM lv_activity o,lv_activity_limit_time t WHERE o.code=t.activity_code AND t.product_code=? AND o.status=? AND o.start_time<=? AND o.end_time>=?",
								new Object[] { productNo, ActivityConstant.STATUS_USE, nowDate, nowDate });

				String activityCode;// 活动号
				String activityTitle;// 活动标题
				Date startTime;// 活动开始时间
				Date endTime;// 活动结束时间
				Integer type;// 活动类型 (1限时，2限量)

				if (!ArrayUtils.isEmpty(timeObjs)) {
					activityCode = timeObjs[0].toString();
					activityTitle = timeObjs[1].toString();
					startTime = DateUtils.convertToDateTime(timeObjs[2].toString());
					endTime = DateUtils.convertToDateTime(timeObjs[3].toString());
					type = ActivityConstant.TYPE_LIMIT_TIME;

					activityVo = new Activity(activityCode, activityTitle, startTime, endTime, type);

				} else {
					// 查询是否参加限量活动
					Object[] quantityObjs = GenericJDBCSupport.findUniqueBySQLForSlave(
							"SELECT o.code,o.activity_title FROM lv_activity o,lv_activity_limited t WHERE o.code=t.activity_code AND t.product_code=? AND o.status=?",
							new Object[] { productNo, ActivityConstant.STATUS_USE });
					if (!ArrayUtils.isEmpty(quantityObjs)) {
						activityCode = quantityObjs[0].toString();
						activityTitle = quantityObjs[1].toString();
						type = ActivityConstant.TYPE_LIMIT_QUANTITY;

						activityVo = new Activity(activityCode, activityTitle, null, null, type);
					}
				}
			}
		} else {
			logger.error("getActivityVoByProductNo 商品编码为空");
		}
		return activityVo;
	}

	@Override
	public ActivityLimitTime getActivityLimitTimeByCode(String activityCode) throws Exception {
		ActivityLimitTime activityLimitTime = null;
		if (StringUtils.isNotBlank(activityCode)) {
			Date nowDate = new Date();

			Object[] timeObjs = GenericJDBCSupport
					.findUniqueBySQLForSlave(
							"SELECT o.activity_title,o.start_time,o.end_time,t.product_code,t.activity_price,t.store_id FROM lv_activity o,lv_activity_limit_time t WHERE o.code=t.activity_code AND o.code=? AND o.status=? AND o.start_time<=? AND o.end_time>=?",
							new Object[] { activityCode, ActivityConstant.STATUS_USE, nowDate, nowDate });

			if (!ArrayUtils.isEmpty(timeObjs)) {
				String activityTitle = timeObjs[0].toString();// 活动标题
				Date startTime = DateUtils.convertToDateTime(timeObjs[1].toString());// 活动开始时间
				Date endTime = DateUtils.convertToDateTime(timeObjs[2].toString());// 活动结束时间
				String productCode = timeObjs[3].toString();// 商品编码
				Float activityPrice = Float.parseFloat(timeObjs[4].toString());// 活动价
				String storeId = timeObjs[5].toString();// 店铺编号

				activityLimitTime = new ActivityLimitTime(activityCode, activityTitle, startTime, endTime, productCode, activityPrice, storeId);
			}
		} else {
			logger.error("getActivityLimitTimeByCode 活动号为空");
		}
		return activityLimitTime;
	}

	@Override
	public ActivityLimited getActivityLimitedByCode(String activityCode) throws Exception {
		ActivityLimited activityLimited = null;
		if (StringUtils.isNotBlank(activityCode)) {

			Object[] quantityObjs = GenericJDBCSupport
					.findUniqueBySQLForSlave(
							"SELECT o.activity_title,t.product_code,t.activity_price,t.store_id,t.limit_total FROM lv_activity o,lv_activity_limited t WHERE o.code=t.activity_code AND o.code=? AND o.status=? AND t.limit_total>0",
							new Object[] { activityCode, ActivityConstant.STATUS_USE });

			if (!ArrayUtils.isEmpty(quantityObjs)) {
				String activityTitle = quantityObjs[0].toString();// 活动标题
				String productCode = quantityObjs[1].toString();// 商品编码
				Float activityPrice = Float.parseFloat(quantityObjs[2].toString());// 活动价
				String storeId = quantityObjs[3].toString();// 店铺编号
				Integer limitTotal = Integer.parseInt(quantityObjs[4].toString());// 总量

				activityLimited = new ActivityLimited(activityCode, activityTitle, productCode, activityPrice, limitTotal, storeId);
			}
		} else {
			logger.error("getActivityLimitTimeByCode 活动号为空");
		}
		return activityLimited;
	}

	public ResultMsg isActivityForLinkCoupon(String activityCode) throws Exception {
		ResultMsg resultMsg = new ResultMsg();
		resultMsg.setSuccess(false);
		boolean dataValid = true;

		if (StringUtils.isBlank(activityCode)) {
			dataValid = false;
			logger.error("活动号为空");
		}

		if (dataValid) {

			// 查缓存
			ActivityLink activityLink = getActivityLinkByCode(activityCode);

			if (null != activityLink) {

				Date nowDate = new Date();

				if (activityLink.getStatus() == ActivityConstant.STATUS_USE && DateUtils.ge(nowDate, activityLink.getStartTime())
						&& DateUtils.le(nowDate, activityLink.getEndTime())) {
					// 检查活动优惠券剩余数量是否满足
					int num = getCouponNumByCache(activityCode);
					if (num > 0) {
						resultMsg.setSuccess(true);
					} else {
						resultMsg.setSuccess(false);
						resultMsg.setReCode(ActivityConstant.RETURN_CODE_666);
						logger.error("活动号：" + activityCode + "已结束");
					}

				} else if (activityLink.getStatus() == ActivityConstant.STATUS_USE && !DateUtils.ge(nowDate, activityLink.getStartTime())
						&& DateUtils.le(nowDate, activityLink.getEndTime())) {
					resultMsg.setSuccess(false);
					resultMsg.setReCode(ActivityConstant.RETURN_CODE_000);
					logger.error("活动号：" + activityCode + " 还未开始");
				} else if (activityLink.getStatus() == ActivityConstant.STATUS_USE && DateUtils.ge(nowDate, activityLink.getStartTime())
						&& !DateUtils.le(nowDate, activityLink.getEndTime())) {
					resultMsg.setSuccess(false);
					resultMsg.setReCode(ActivityConstant.RETURN_CODE_666);
					logger.error("活动号：" + activityCode + "已结束");
				}else if(activityLink.getStatus() == ActivityConstant.STATUS_DIS_USE){
					resultMsg.setSuccess(false);
					resultMsg.setReCode(ActivityConstant.RETURN_CODE_NO_EXSITS);
					logger.error("活动号：" + activityCode + "已停用");
				}
			} else {
				resultMsg.setSuccess(false);
				resultMsg.setMsg("该活动不存在!");
				resultMsg.setReCode(ActivityConstant.RETURN_CODE_NO_EXSITS);
				logger.error("活动号：" + activityCode + " 非有效活动");
			}
		}
		return resultMsg;
	}

	/**
	 * 
	 * @Description:生成领券成功展示对象
	 * @author CYJ
	 * @date 2014-6-27 下午3:30:54
	 * @param couponDetailCode
	 * @param activityCode
	 * @return
	 * @throws Exception
	 */
	public CouponView getCouponView(String couponDetailCode, String activityCode, String flag) throws Exception {
		String sql = "SELECT o.code,o.amount,o.limit_amount,o.relation_type,o.start_time,o.end_time,o.currency,t.coupon_code,o.reuse FROM lv_coupon_type o,lv_coupon t WHERE o.code=t.coupon_type_code and t.code=?";
		Object[] objs = GenericJDBCSupport.findUniqueBySQLForSlave(sql, new Object[] { couponDetailCode });

		String couponMainCode = objs[0].toString();// 主表code
		String currency = objs[6].toString();// 币种
		String faceValue = currency + " " + DoubleUtils.formatDouble(Double.parseDouble(objs[1].toString()), 2);// 面额
		String limitAmount = currency + " " + DoubleUtils.formatDouble(Double.parseDouble(objs[2].toString()), 2);// 限額
		Integer relationType = Integer.parseInt(objs[3].toString());// 关联类型(1商品类型，2商品信息)
		String type = null;
		if (relationType == CouponConstant.SCOPE_PRODUCT) {
			type = "限商品";
			if (flag.equals("en") || flag.equals("bmen")) {
				type = "Available items";
			}
		} else if (relationType == CouponConstant.SCOPE_TYPE) {
			type = "限品類";
			if (flag.equals("en") || flag.equals("bmen")) {
				type = "Available categories";
			}
		}
		String startTime = DateUtils.formatDate(DateUtils.convertToDateTime(objs[4].toString()), "yyyy-MM-dd");
		String endTime = DateUtils.formatDate(DateUtils.convertToDateTime(objs[5].toString()), "yyyy-MM-dd");
		String validDate = startTime + "至" + endTime;
		if (flag.equals("en") || flag.equals("bmen")) {
			validDate = startTime + " to " + endTime;
		}
		String typeProduct = getTypeProduct(relationType, couponMainCode);

		ActivityLink activityLink = getActivityLinkByCode(activityCode);
		String couponCode=String.valueOf(objs[7]);

		Integer reuse = Integer.valueOf(objs[8].toString());

		String reuseName = "";
		if (reuse.intValue() == ReuseType.reuse.intValue()) {
			if (flag.equals("en") || flag.equals("bmen")) {
				reuseName = "Reusable";
			} else {
				reuseName = "可重複使用";
			}
		}

		CouponView couponView = new CouponView(activityLink.getActivityTitle(), faceValue, type, typeProduct, limitAmount, validDate, couponCode, reuseName, reuse);

		return couponView;
	}

	public ResultMsg getCouponByActivity(String activityCode, Integer type, String username) throws Exception {
		ResultMsg resultMsg = new ResultMsg();
		resultMsg.setSuccess(false);

		// 检查活动库存是否满足并获取优惠券（缓存）
		final String code = getCouponByCache(activityCode);// 优惠券业务编码

		final String activityCodeTemp = activityCode;

		final int typeTemp = type;

		final String usernameTemp = username;

		if (StringUtils.isNotBlank(code)) {
			new Thread(new Runnable() {
				public void run() {
					try {
						updateCouponByActivityForDB(activityCodeTemp, code, typeTemp, usernameTemp);
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}
				}
			}).start();

			resultMsg.setSuccess(true);
			resultMsg.setIdCode(code);
		} else {
			// 已无库存
			resultMsg.setSuccess(false);
			resultMsg.setReCode(ActivityConstant.RETURN_CODE_404);
		}

		return resultMsg;
	}

	/**
	 * 更新数据库优惠券活动
	 * 
	 * @param activityCode
	 * @param code
	 * @param type
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public boolean updateCouponByActivityForDB(String activityCode, String code, int type, String username) throws Exception {
		Map<String, List<Object[]>> sqlBatchs = new HashMap<String, List<Object[]>>();

		// 获取优惠券
		Object[] objs = GenericJDBCSupport.findUniqueBySQLForSlave(SQL_GET_COUPON, new Object[] { CouponConstant.STATUS_USE, activityCode, CouponConstant.STATUS_DISGET_DISUSE,code });

		// 更新优惠券
		String couponMainCode = objs[0].toString();// 主表code
		String couponDetailCode = objs[1].toString();// 明细表code

		// 减优惠券可用库存
		Map<String, Integer> couponQuantityMap = new HashMap<String, Integer>();
		couponQuantityMap.put(couponMainCode, 1);
		Map<String, List<Object[]>> costCouponSqlMap = couponService.costCouponSqlMap(couponQuantityMap);

		SqlMapUtils.merge(sqlBatchs, costCouponSqlMap);

		// 绑定优惠券用户
		if(ObjectUtils.isNotEmpty(username)){
			Map<String, List<Object[]>> userCouponSqlMap = couponService.updateUserCouponSqlMap(couponDetailCode, username);
			SqlMapUtils.merge(sqlBatchs, userCouponSqlMap);
		}else{
			Map<String, List<Object[]>> userCouponSqlMap = couponService.updateUserCouponSqlMap(couponDetailCode);
			SqlMapUtils.merge(sqlBatchs, userCouponSqlMap);
		}
	
		
		switch (type) {
		case ActivityConstant.TYPE_LINK:
			// 维护链接领券活动相关数据
			Map<String, List<Object[]>> costLinkActivitySqlMap = costLinkActivitySqlMap(activityCode, 1);
			SqlMapUtils.merge(sqlBatchs, costLinkActivitySqlMap);
			break;
		case ActivityConstant.TYPE_LIMIT_ORDER:
			// 订单满就送活动
			Map<String, List<Object[]>> costOrderLevelActivitySqlMap = costOrderLevelActivitySqlMap(activityCode, 1);
			SqlMapUtils.merge(sqlBatchs, costOrderLevelActivitySqlMap);
			break;
		case ActivityConstant.TYPE_REGIST:
			//注册活动
			Map<String, List<Object[]>> costRegistActivitySqlMap = costRegistActivitySqlMap(activityCode, 1);
			SqlMapUtils.merge(sqlBatchs, costRegistActivitySqlMap);
			break;
		case ActivityConstant.TYPE_SHARE:
			//分享活动
			Map<String, List<Object[]>> costShareActivitySqlMap = costShareActivitySqlMap(activityCode, 1);
			SqlMapUtils.merge(sqlBatchs, costShareActivitySqlMap);
			break;
		case ActivityConstant.TYPE_APPOINT_PRODUCT:
			//购买指定商品就送
			Map<String, List<Object[]>> costAppointProductActivitySqlMap= costAppointProductActivitySqlMap(activityCode, 1);
			SqlMapUtils.merge(sqlBatchs, costAppointProductActivitySqlMap);
			break;
		}	
		boolean result = GenericJDBCSupport.executeBatch(sqlBatchs);
		return result;
	}

	/**
	 * 从缓存获取优惠券业务编码
	 * 
	 * @param activityCode
	 * @return
	 * @throws Exception
	 */
	@Override
	public String getCouponByCache(String activityCode) throws Exception {
		String code = null;// 优惠券业务编码

		Jedis jedis = null;
		JedisPool jedisPool = null;
		try {
			jedisPool = RedisClient.getInstance().getJedisPool(RedisConstant.getRedisPoolConfigAC());
			jedis = jedisPool.getResource();
			String activityKey = ActivityConstant.getActivityCouponKey(activityCode);
			Set<String> codeSet = jedis.smembers(activityKey);// 获取该活动所有优惠券业务编码SET
			if (CollectionUtils.isNotEmpty(codeSet)) {

				// 使用随机取值策略，减少资源竞争。
				List<String> codeList = new ArrayList<String>();
				codeList.addAll(codeSet);

				int listSize = codeList.size();

				int index = (int) (Math.random() * listSize);// 生成List随机索引

				for (int i = 0; i < listSize; i++) {

					String tempCode = codeList.get(index);

					long s = jedis.srem(activityKey, tempCode);// 状态码，成功返回1，成员不存在返回0
					if (s == 1) {
						code = tempCode;
						break;
					} else if (s == 0) {
						logger.error("活动：" + activityCode + "," + "竞争获取优惠券失败，继续获取下一张！");
					}
				}
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		} finally {
			RedisClient.closeRedis(jedisPool, jedis);
		}
		return code;
	}

	/**
	 * 获取当前活动优惠券数量
	 * 
	 * @param activityCode
	 * @return
	 * @throws Exception
	 */
	public int getCouponNumByCache(String activityCode) throws Exception {

		int num = 0;

		Jedis jedis = null;
		JedisPool jedisPool = null;
		try {
			jedisPool = RedisClient.getInstance().getJedisPool(RedisConstant.getRedisPoolConfigAC());
			jedis = jedisPool.getResource();
			String activityKey = ActivityConstant.getActivityCouponKey(activityCode);
			Set<String> codeSet = jedis.smembers(activityKey);// 获取该活动所有优惠券业务编码SET
			if (CollectionUtils.isNotEmpty(codeSet)) {
				num = codeSet.size();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		} finally {
			RedisClient.closeRedis(jedisPool, jedis);
		}
		return num;
	}

	/**
	 * 
	 * @Description:维护满送活动相关数据
	 * @author CYJ
	 * @date 2014-6-30 下午3:51:25
	 * @param activityCode
	 * @param quantity
	 * @return
	 * @throws Exception
	 */
	public Map<String, List<Object[]>> costOrderLevelActivitySqlMap(String activityCode, Integer quantity) throws Exception {
		Map<String, List<Object[]>> sqlMap = new HashMap<String, List<Object[]>>();
		String update = "UPDATE lv_activity_limit_order o SET o.grant_total=IFNULL(o.grant_total,0)+?,o.occupied_total=IFNULL(o.occupied_total,0)-? WHERE o.activity_code=?";
		List<Object[]> updateParam = new ArrayList<Object[]>();

		updateParam.add(new Object[] { quantity, quantity, activityCode });

		sqlMap.put(update, updateParam);

		return sqlMap;
	}

	/**
	 * 
	 * @Description:维护领券活动相关数据
	 * @author CYJ
	 * @date 2014-6-30 下午3:06:59
	 * @param activityCode
	 * @param quantity
	 * @return
	 * @throws Exception
	 */
	public Map<String, List<Object[]>> costLinkActivitySqlMap(String activityCode, Integer quantity) throws Exception {
		Map<String, List<Object[]>> sqlMap = new HashMap<String, List<Object[]>>();
		String update = "UPDATE lv_activity_link o SET o.grant_total=IFNULL(o.grant_total,0)+?,o.uncollected_total=IFNULL(o.uncollected_total,0)-? WHERE o.activity_code=?";
		List<Object[]> updateParam = new ArrayList<Object[]>();

		updateParam.add(new Object[] { quantity, quantity, activityCode });

		sqlMap.put(update, updateParam);

		return sqlMap;
	}
	
	/**
	 * 修改注册活动优惠券赠品库存
	 * @param activityCode
	 * @param quantity
	 * @return
	 * @throws Exception
	 */
	private Map<String, List<Object[]>> costRegistActivitySqlMap(String activityCode, Integer quantity) throws Exception {
		Map<String, List<Object[]>> sqlMap = new HashMap<String, List<Object[]>>();
		String update = "UPDATE lv_activity_register o SET o.grant_total=IFNULL(o.grant_total,0)+?,o.uncollected_total=IFNULL(o.uncollected_total,0)-? WHERE o.activity_code=?";
		List<Object[]> updateParam = new ArrayList<Object[]>();

		updateParam.add(new Object[] { quantity, quantity, activityCode });

		sqlMap.put(update, updateParam);

		return sqlMap;
	}
	/**
	 * 维护分享活动优惠券赠品库存
	 * @param activityCode
	 * @param quantity
	 * @return
	 * @throws Exception
	 */
	private Map<String, List<Object[]>> costShareActivitySqlMap(String activityCode, Integer quantity) throws Exception {
		Map<String, List<Object[]>> sqlMap = new HashMap<String, List<Object[]>>();
		String update = "UPDATE lv_activity_share o SET o.grant_total=IFNULL(o.grant_total,0)+?,o.uncollected_total=IFNULL(o.uncollected_total,0)-? WHERE o.activity_code=?";
		List<Object[]> updateParam = new ArrayList<Object[]>();

		updateParam.add(new Object[] { quantity, quantity, activityCode });

		sqlMap.put(update, updateParam);

		return sqlMap;
	}
	
	/**
	 * 
	 * @Method: costAppointProductActivitySqlMap 
	 * @Description:  [维护购买指定商品就送活动优惠券库存信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-16 下午5:27:42]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-16 下午5:27:42]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param activityCode 活动编号
	 * @param quantity 数量
	 * @throws Exception 
	 * @return Map<String,List<Object[]>>
	 */
	private Map<String, List<Object[]>> costAppointProductActivitySqlMap(String activityCode, Integer quantity) throws Exception {
		Map<String, List<Object[]>> sqlMap = new HashMap<String, List<Object[]>>();
		String update = "UPDATE lv_activity_appoint_product o SET o.grant_total=IFNULL(o.grant_total,0)+?,o.uncollected_total=IFNULL(o.uncollected_total,0)-? WHERE o.activity_code=?";
		List<Object[]> updateParam = new ArrayList<Object[]>();
		updateParam.add(new Object[] { quantity, quantity, activityCode });
		sqlMap.put(update, updateParam);
		return sqlMap;
	}
	
	public String getTypeProduct(Integer relationType, String couponMainCode) throws Exception {

		String result = "";

		List<Object[]> productNameList = new ArrayList<Object[]>();
		if (relationType == CouponConstant.SCOPE_TYPE) {
			// 关联类型(商品类型)
			productNameList = GenericJDBCSupport.queryBySQLForSlave(SQL_GET_PRODUCT_NAME_BY_TYPE, new Object[] { couponMainCode });
		} else if (relationType == CouponConstant.SCOPE_PRODUCT) {
			// 关联类型(商品)
			productNameList = GenericJDBCSupport.queryBySQLForSlave(SQL_GET_PRODUCT_NAME_BY_CODE, new Object[] { couponMainCode });
		}
		
		if(ObjectUtils.isNotEmpty(productNameList)){
			for (Object[] objs : productNameList) {
				result += objs[0].toString() + "(" + objs[1].toString() + "),";

			}
			result=result.substring(0, result.length() - 1);
		}
		
		return result;
	}

	@Override
	public ActivityLimitOrder getActivityLimitOrder(String storeId, Float dAmount) throws Exception {
		ActivityLimitOrder activityLimitOrder = null;

		Date nowDate = new Date();

		Object[] giftObjs = GenericJDBCSupport.findUniqueBySQLForSlave(SQL_GET_GIFT, new Object[] { storeId, ActivityConstant.STATUS_USE, nowDate, nowDate,
				ActivityConstant.TYPE_LIMIT_ORDER, CouponConstant.STATUS_USE, dAmount });

		if (!ArrayUtils.isEmpty(giftObjs)) {
			// 活动号
			String activityCode = giftObjs[0].toString();

			// 活动标题
			String activityTitle = giftObjs[1].toString();

			// 活动限额
			Float limitTotalPrice = Float.parseFloat(giftObjs[2].toString());

			// 赠送类型名称
			String givenTypeName = giftObjs[3].toString();

			// 赠送物品关联
			String givenProductCode = giftObjs[4].toString();

			// 赠送类型字典(1赠送优惠券)
			Short givenTypeNum = Short.parseShort(giftObjs[5].toString());

			// 优惠券金额
			Float amount = Float.parseFloat(giftObjs[6].toString());

			// 优惠券名称
			String couponName = giftObjs[7].toString();

			// 限额
			Float limitAmount = Float.parseFloat(giftObjs[8].toString());

			activityLimitOrder = new ActivityLimitOrder(activityCode, activityTitle, limitTotalPrice, storeId, givenTypeName, givenProductCode, givenTypeNum, amount, couponName,
					limitAmount);

		}

		return activityLimitOrder;
	}

	@Override
	public ActivityLimitOrder getActivityLimitOrderLottery(String storeId,
			Float dAmount) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select a.code, a.activity_title, o.lottery_total, o.limit_total_price");
		sql.append(" from lv_activity a LEFT OUTER JOIN lv_activity_limit_order o ON a.code = o.activity_code");
		sql.append(" WHERE a.type_key=3 AND o.given_type_num=2 AND a.status=1 and o.store_id=? AND o.limit_total_price<=?");
		sql.append(" AND a.start_time <=? AND a.end_time >= ? ORDER BY o.limit_total_price desc LIMIT 1;");
		Date now = new Date();
		Object[] giftObjs = GenericJDBCSupport.findUniqueBySQLForSlave(sql.toString(), new Object[]{
			storeId, dAmount, now, now
		});
		if (ArrayUtils.isEmpty(giftObjs)) {
			return null;
		}
		ActivityLimitOrder limitOrder = new ActivityLimitOrder();
		limitOrder.setActivityCode(giftObjs[0].toString());
		limitOrder.setActivityTitle(giftObjs[1].toString());
		limitOrder.setGivenTypeNum((short) ActivityConstant.GIFT_TYPE_LOTTERYP);
		limitOrder.setLotteryNum(Integer.valueOf(giftObjs[2].toString()).intValue());
		limitOrder.setLimitAmount(Float.valueOf(giftObjs[3].toString()));
		return limitOrder;
	}

	/**
	 * 
	 * @Method: getGeneralActivityByProductNo 
	 * @Description:  [根据商品编码判断是否存在有效活动入口]    
	 * @UpdateDate:   [2015-1-16 下午4:15:59]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public GeneralActivity getGeneralActivityByProductNo(String productNo) throws Exception {
		GeneralActivity generalActivity = null;
		ActivityLimitTime activityLimitTime = null;
		ActivityLimited activityLimited = null;

		if (StringUtils.isNotBlank(productNo)) {
			// 检查商品是否开启活动
			if (GenericJDBCSupport.isExistForSlave("SELECT p.id FROM lv_product p WHERE p.code=? AND p.is_activity=?", new Object[] { productNo, ProductConstant.USE_ACTIVITY })) {

				Date nowDate = new Date();
				// 查询是否参加限时活动
				Object[] timeObjs = GenericJDBCSupport.findUniqueBySQLForSlave("SELECT o.code,o.activity_title,o.start_time,o.end_time,t.activity_price,t.store_id FROM lv_activity o,lv_activity_limit_time t WHERE o.code=t.activity_code AND t.product_code=? AND o.status=? AND ((o.start_time<=? AND o.end_time>=?) OR o.start_time>?) ORDER BY o.start_time ASC LIMIT 1",
								new Object[] { productNo, ActivityConstant.STATUS_USE, nowDate, nowDate, nowDate });

				String activityCode;// 活动号
				String activityTitle;// 活动标题
				Date startTime;// 活动开始时间
				Date endTime;// 活动结束时间
				Integer type;// 活动类型 (1限时，2限量)
				Float activityPrice;// 活动价
				String storeId;// 店铺编号

				if (!ArrayUtils.isEmpty(timeObjs)) {
					activityCode = timeObjs[0].toString();
					activityTitle = timeObjs[1].toString();
					startTime = DateUtils.convertToDateTime(timeObjs[2].toString());
					endTime = DateUtils.convertToDateTime(timeObjs[3].toString());
					type = ActivityConstant.TYPE_LIMIT_TIME;
					activityPrice = Float.parseFloat(timeObjs[4].toString());
					storeId = timeObjs[5].toString();

					activityLimitTime = new ActivityLimitTime(activityCode, activityTitle, startTime, endTime, productNo, activityPrice, storeId);
					generalActivity = new GeneralActivity(type, activityLimitTime, activityLimited);

				} else {
					// 查询是否参加限量活动
					Object[] quantityObjs = GenericJDBCSupport.findUniqueBySQLForSlave(
									"SELECT o.code,o.activity_title,t.activity_price,t.limit_total,t.store_id FROM lv_activity o,lv_activity_limited t WHERE o.code=t.activity_code AND t.product_code=? AND o.status=?",
									new Object[] { productNo, ActivityConstant.STATUS_USE });
					if (!ArrayUtils.isEmpty(quantityObjs)) {
						activityCode = quantityObjs[0].toString();
						activityTitle = quantityObjs[1].toString();
						type = ActivityConstant.TYPE_LIMIT_QUANTITY;
						activityPrice = Float.parseFloat(quantityObjs[2].toString());
						Integer limitTotal = Integer.parseInt(quantityObjs[3].toString());
						storeId = quantityObjs[4].toString();

						activityLimited = new ActivityLimited(activityCode, activityTitle, productNo, activityPrice, limitTotal, storeId);
						generalActivity = new GeneralActivity(type, activityLimitTime, activityLimited);
					}else{
						generalActivity = getActivityAppintProductByProductCode(productNo);			
					}
				}
			}
		} else {
			logger.error("getGeneralActivityByProductNo 商品编码为空");
		}
		return generalActivity;
	}

	/**
	 * 
	 * @Method: getActivityAppintProductByProductCode 
	 * @Description:  [根据商品编码查询是否存在购买商品就送活动信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-16 下午4:17:44]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-16 下午4:17:44]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param productNo 商品编码
	 * @throws Exception 
	 * @return GeneralActivity
	 */
	private GeneralActivity getActivityAppintProductByProductCode(String productNo) throws Exception {
		String activityCode;
		String activityTitle;
		String activityTitleEn = null;
		Integer type;
		Date nowDate = new Date();
		GeneralActivity generalActivity =null;
		Object[] productObjs = GenericJDBCSupport.findUniqueBySQLForSlave(
				" SELECT o.code,o.activity_title,t.is_use_coupon,t.given_type_name,t.given_product_code,t.given_type_num,t.lottery_total,t.uncollected_total,o.activity_title_en" +
				" FROM lv_activity o,lv_activity_appoint_product t " +
				" WHERE o.code=t.activity_code " +
				" AND EXISTS (SELECT  1 FROM lv_activity_product ap WHERE o.code=ap.activity_code AND ap.product_code=?) " +
				" AND o.status=?" +
				" and (o.start_time<=? AND o.end_time>=?)",
				new Object[] { productNo, ActivityConstant.STATUS_USE,nowDate,nowDate });
		if(ObjectUtils.isNotEmpty(productObjs)){
			activityCode = productObjs[0].toString();
			activityTitle = productObjs[1].toString();
			Short isUseCoupon = 0;
			if(ObjectUtils.isNotEmpty(productObjs[2])){
				isUseCoupon=Short.parseShort(productObjs[2].toString());
			}
			String givenTypeName="";
			if(ObjectUtils.isNotEmpty(productObjs[3])){
				givenTypeName = productObjs[3].toString();
			}
			String givenProductCode="";
			if(ObjectUtils.isNotEmpty(productObjs[4])){
				givenProductCode=productObjs[4].toString();
			}
			Short givenTypeNum=Short.parseShort(productObjs[5].toString());
			int lotteryNum=0;
			if(ObjectUtils.isNotEmpty(productObjs[6])){
				lotteryNum=Integer.parseInt(productObjs[6].toString());
			}
			Integer uncollectedTotal=0;
			if(ObjectUtils.isNotEmpty(productObjs[7])){
				uncollectedTotal=Integer.parseInt(productObjs[7].toString());
			}
			if(ObjectUtils.isNotEmpty(productObjs[8])){
				activityTitleEn=String.valueOf(productObjs[8]);
			}
			//组装购买订单就活动数据
			ActivityAppoindProduct acProduct=new ActivityAppoindProduct();
			acProduct.setActivityCode(activityCode);
			acProduct.setActivityTitle(activityTitle);
			acProduct.setIsUseCoupon(isUseCoupon);
			acProduct.setGivenTypeName(givenTypeName);
			acProduct.setGivenProductCode(givenProductCode);
			acProduct.setGivenTypeNum(givenTypeNum);
			acProduct.setLotteryNum(lotteryNum);
			acProduct.setUncollectedTotal(uncollectedTotal);
			acProduct.setActivityTitleEn(activityTitleEn);
			
			
			
			//组装公共活到返回
			generalActivity = new GeneralActivity();
			generalActivity.setActivityAppoindProduct(acProduct);
			type = ActivityConstant.TYPE_APPOINT_PRODUCT;
			generalActivity.setType(type);
			
		}
		return generalActivity;
	}

	@Override
	public GeneralActivity getNowGeneralActivityByProductNo(String productNo) throws Exception {
		GeneralActivity generalActivity = null;
		ActivityLimitTime activityLimitTime = null;
		ActivityLimited activityLimited = null;

		if (StringUtils.isNotBlank(productNo)) {
			// 检查商品是否开启活动
			if (GenericJDBCSupport.isExistForSlave("SELECT p.id FROM lv_product p WHERE p.code=? AND p.is_activity=?", new Object[] { productNo, ProductConstant.USE_ACTIVITY })) {

				Date nowDate = new Date();
				// 查询是否参加限时活动
				Object[] timeObjs = GenericJDBCSupport
						.findUniqueBySQLForSlave(
								"SELECT o.code,o.activity_title,o.start_time,o.end_time,t.activity_price,t.store_id FROM lv_activity o,lv_activity_limit_time t WHERE o.code=t.activity_code AND t.product_code=? AND o.status=? AND o.start_time<=? AND o.end_time>=?",
								new Object[] { productNo, ActivityConstant.STATUS_USE, nowDate, nowDate });

				String activityCode;// 活动号
				String activityTitle;// 活动标题
				Date startTime;// 活动开始时间
				Date endTime;// 活动结束时间
				Integer type;// 活动类型 (1限时，2限量)
				Float activityPrice;// 活动价
				String storeId;// 店铺编号

				if (!ArrayUtils.isEmpty(timeObjs)) {
					activityCode = timeObjs[0].toString();
					activityTitle = timeObjs[1].toString();
					startTime = DateUtils.convertToDateTime(timeObjs[2].toString());
					endTime = DateUtils.convertToDateTime(timeObjs[3].toString());
					type = ActivityConstant.TYPE_LIMIT_TIME;
					activityPrice = Float.parseFloat(timeObjs[4].toString());
					storeId = timeObjs[5].toString();

					activityLimitTime = new ActivityLimitTime(activityCode, activityTitle, startTime, endTime, productNo, activityPrice, storeId);
					generalActivity = new GeneralActivity(type, activityLimitTime, activityLimited);

				} else {
					// 查询是否参加限量活动
					Object[] quantityObjs = GenericJDBCSupport
							.findUniqueBySQLForSlave(
									"SELECT o.code,o.activity_title,t.activity_price,t.limit_total,t.store_id FROM lv_activity o,lv_activity_limited t WHERE o.code=t.activity_code AND t.product_code=? AND o.status=? AND t.limit_total>0",
									new Object[] { productNo, ActivityConstant.STATUS_USE });
					if (!ArrayUtils.isEmpty(quantityObjs)) {
						activityCode = quantityObjs[0].toString();
						activityTitle = quantityObjs[1].toString();
						type = ActivityConstant.TYPE_LIMIT_QUANTITY;
						activityPrice = Float.parseFloat(quantityObjs[2].toString());
						Integer limitTotal = Integer.parseInt(quantityObjs[3].toString());
						storeId = quantityObjs[4].toString();

						activityLimited = new ActivityLimited(activityCode, activityTitle, productNo, activityPrice, limitTotal, storeId);
						generalActivity = new GeneralActivity(type, activityLimitTime, activityLimited);
					}else{
						//查询购买指定商品就送活动
						generalActivity = getActivityAppintProductByProductCode(productNo);
					}
				}
			}
		} else {
			logger.error("getGeneralActivityByProductNo 商品编码为空");
		}
		return generalActivity;
	}

	@Override
	public synchronized ResultMsg finishOrderActivity(String orderNo, String username, Map<String, Integer> productQuantity) throws Exception {
		ResultMsg resultMsg = new ResultMsg();
		resultMsg.setSuccess(false);

		String sql = "SELECT o.activity_code,o.activity_type,o.code,o.status " +
				" FROM lv_order_activity o " +
				" WHERE o.order_id=? " +
				" AND (o.activity_type=? OR o.activity_type=? OR o.activity_type=? OR o.activity_type=?)";
		List<Object[]> list = GenericJDBCSupport.queryBySQLForSlave(sql, 
				new Object[] { orderNo, 
				ActivityConstant.TYPE_LIMIT_ORDER, 
				ActivityConstant.TYPE_LIMIT_QUANTITY,
				ActivityConstant.TYPE_LIMIT_TIME,
				ActivityConstant.TYPE_APPOINT_PRODUCT});
		if (CollectionUtils.isNotEmpty(list)) {

			Set<String> limitActivitySet = new HashSet<String>();
			Set<String> codeSet = new HashSet<String>();

			for (Object[] objs : list) {
				if (!ArrayUtils.isEmpty(objs)) {
					String activityCode = objs[0].toString();// 活动号
					Integer type = Integer.parseInt(objs[1].toString());// 活动类型
					String code = objs[2].toString();// code
					Integer status = Integer.parseInt(objs[3].toString());// 订单活动状态
					if (status == ActivityConstant.STATUS_ORDER_ACTIVITY_FINISH) {
						logger.error("订单：" + orderNo + " 活动：" + activityCode + "订单参与已完毕");
						continue;
					}

					if (type == ActivityConstant.TYPE_LIMIT_ORDER) {
						//查看订单满就送活动赠送类型
						String hql = "from LvActivityLimitOrder where activityCode=:activityCode";
						Map<String, Object> param = new HashMap<String, Object>();
						param.put("activityCode", activityCode);
						LvActivityLimitOrder activity = (LvActivityLimitOrder) lvlogicReadDao.findUnique(hql, param);
						switch (activity.getGivenTypeNum()) {
						case ActivityConstant.GIFT_TYPE_COUPON:
							// 满送优惠券活动
							resultMsg = getCouponByActivity(activityCode, ActivityConstant.TYPE_LIMIT_ORDER, username);
							if (resultMsg.isSuccess()) {
								codeSet.add(code);
							}
							break;
						case ActivityConstant.GIFT_TYPE_LOTTERYP:
							//送抽奖机会
							lotteryChanceService.addChance(activity.getGivenProductCode(), activity.getLotteryTotal(), username);
							codeSet.add(code);
							break;
						}
					} else if (type == ActivityConstant.TYPE_LIMIT_QUANTITY) {
						// 限量活动
						limitActivitySet.add(activityCode);

						codeSet.add(code);
					} else if (type == ActivityConstant.TYPE_LIMIT_TIME) {
						// 限时活动
						codeSet.add(code);
					} else if(type == ActivityConstant.TYPE_APPOINT_PRODUCT){
						//查看订单满就送活动赠送类型
						String hql = "from LvActivityAppointProduct where activityCode=:activityCode";
						Map<String, Object> param = new HashMap<String, Object>();
						param.put("activityCode", activityCode);
						LvActivityAppointProduct activity = (LvActivityAppointProduct) lvlogicReadDao.findUnique(hql, param);
						switch (activity.getGivenTypeNum()) {
						case ActivityConstant.GIFT_TYPE_COUPON:
							// 满送优惠券活动
							resultMsg = getCouponByActivity(activityCode, ActivityConstant.TYPE_APPOINT_PRODUCT, username);
							if (resultMsg.isSuccess()) {
								codeSet.add(code);
							}
							break;
						case ActivityConstant.GIFT_TYPE_LOTTERYP:
							//送抽奖机会
							lotteryChanceService.addChance(activity.getGivenProductCode(), activity.getLotteryTotal(), username);
							codeSet.add(code);
							break;
						}
					}
				}
			}
			// 限量活动扣减库存
			resultMsg = costLimitActivity(limitActivitySet, productQuantity);

			// 更新订单活动状态
			updateOrderActivityStatus(codeSet, ActivityConstant.STATUS_ORDER_ACTIVITY_FINISH);
		}

		return resultMsg;
	}

	/**
	 * 
	 * @param codeSet
	 *            更新订单活动状态
	 * @return
	 * @throws Exception
	 */
	public ResultMsg updateOrderActivityStatus(Set<String> codeSet, int status) throws Exception {
		ResultMsg resultMsg = new ResultMsg();
		resultMsg.setSuccess(false);
		String updateSql = "UPDATE lv_order_activity o SET o.status=? WHERE o.code=?";
		if (CollectionUtils.isNotEmpty(codeSet)) {
			List<Object[]> updateParam = new ArrayList<Object[]>();
			for (String code : codeSet) {
				updateParam.add(new Object[] { status, code });
			}

			Map<String, List<Object[]>> sqlBatchs = new HashMap<String, List<Object[]>>();
			sqlBatchs.put(updateSql, updateParam);
			boolean result = GenericJDBCSupport.executeBatch(sqlBatchs);

			resultMsg.setSuccess(result);
		}
		return resultMsg;
	}

	/**
	 * 
	 * @Description: 限量活动扣减库存
	 * @author CYJ
	 * @date 2014-6-30 上午11:06:41
	 * @param limitActivitySet
	 * @param productQuantity
	 * @return
	 * @throws Exception
	 */
	public ResultMsg costLimitActivity(Set<String> limitActivitySet, Map<String, Integer> productQuantity) throws Exception {
		ResultMsg resultMsg = new ResultMsg();
		Map<String, List<Object[]>> sqlBatchs = new HashMap<String, List<Object[]>>();
		String update = "UPDATE lv_activity_limited o SET o.limit_total=IFNULL(o.limit_total,0)-? WHERE o.activity_code=?";
		List<Object[]> updateParam = new ArrayList<Object[]>();

		String sql = "SELECT o.product_code FROM lv_activity_limited o WHERE o.activity_code=?";
		for (String activityCode : limitActivitySet) {
			Object[] objs = GenericJDBCSupport.findUniqueBySQLForSlave(sql, new Object[] { activityCode });
			String productNo = objs[0].toString();// 活动对应的商品编码
			if (productQuantity.containsKey(productNo)) {
				Integer quantity = productQuantity.get(productNo);
				updateParam.add(new Object[] { quantity, activityCode });
			} else {
				// 活动商品在订单中未存在
				logger.error("活动商品在订单中未存在" + productNo);
			}

		}

		sqlBatchs.put(update, updateParam);
		boolean result = GenericJDBCSupport.executeBatch(sqlBatchs);
		resultMsg.setSuccess(result);

		return resultMsg;
	}

	/**
	 * 
	 * @Description: 预占库存（订单满就送活动）
	 * @author CYJ
	 * @date 2014-6-29 上午10:58:01
	 * @param activityLimitOrder
	 * @return
	 * @throws Exception
	 */
	public Map<String, List<Object[]>> preCouponQuantitySqlMap(ActivityLimitOrder activityLimitOrder) throws Exception {
		Map<String, List<Object[]>> sqlBatchs = new HashMap<String, List<Object[]>>();

		// 满足订单送优惠券活动,预占该活动优惠券数量
		String sql_update = "UPDATE lv_activity_limit_order o SET o.occupied_total=IFNULL(o.occupied_total,0)+1,o.uncollected_total=IFNULL(o.uncollected_total,0)-1 WHERE o.activity_code=?";

		List<Object[]> updateParam = new ArrayList<Object[]>();
		updateParam.add(new Object[] { activityLimitOrder.getActivityCode() });

		sqlBatchs.put(sql_update, updateParam);

		return sqlBatchs;
	}

	@Override
	public ResultMsg getCouponByActivityLink(String activityCode, String username, String flag) throws Exception {
		ResultMsg resultMsg = new ResultMsg();
		resultMsg.setSuccess(false);

		boolean dataValid = true;

		if (StringUtils.isBlank(activityCode)) {
			dataValid = false;
			logger.error("活动号为空");
		} 
//		else if (StringUtils.isBlank(username)) {
//			dataValid = false;
//			logger.error("用户名为空");
//		}
		if (dataValid) {
			// 是否有效领券活动（缓存）
			ResultMsg isActivityForLinkCouponRs = isActivityForLinkCoupon(activityCode);

			CouponView couponView = new CouponView();

			if (isActivityForLinkCouponRs.isSuccess()) {

				// 获取活动规则（缓存）
				ActivityLink activityLink = getActivityLinkByCode(activityCode);
				
				couponView.setActivityTitle(activityLink.getActivityTitle());

				if(activityLink.getStrategyType()==ActivityConstant.STRATEG_TYPE_LIMIT_LOGIN){
					if (StringUtils.isBlank(username)) {
						dataValid = false;
						logger.error("用户名为空");
					}
					// 检查用户是否满足领取数量限制，并预占缓存（缓存）
					ResultMsg isbelowCouponLimitRs = isbelowCouponLimit(activityCode, username, activityLink.getLimitNum());
					if (!isbelowCouponLimitRs.isSuccess()) {
						logger.error("用户:" + username + "，已领券！");
						resultMsg.setSuccess(false);
						resultMsg.setMsg("您已經領取過了，下次再來吧");
						resultMsg.setReCode(ActivityConstant.RETURN_CODE_888);
						resultMsg.setReObj(couponView);
					} else if (isbelowCouponLimitRs.isSuccess()) {
						// 领取优惠券
						ResultMsg getCouponForActivityRs = getCouponByActivity(activityCode, ActivityConstant.TYPE_LINK, username);
						if (getCouponForActivityRs.isSuccess()) {
							// 成功领取到优惠券
							couponView = getCouponView(getCouponForActivityRs.getIdCode(), activityCode, flag);
							resultMsg.setSuccess(true);
							resultMsg.setReObj(couponView);
						} else {
							if (getCouponForActivityRs.getReCode().equals(ActivityConstant.RETURN_CODE_404)) {
								resultMsg.setSuccess(false);
								resultMsg.setMsg("您来迟了一步，优惠券已经被领完了");
								resultMsg.setReCode(ActivityConstant.RETURN_CODE_404);
								resultMsg.setReObj(couponView);
							}
						}

					}
				}else if(activityLink.getStrategyType()==ActivityConstant.STRATEG_TYPE_LIMIT_NO){
					// 领取优惠券
					ResultMsg getCouponForActivityRs = getCouponByActivity(activityCode, ActivityConstant.TYPE_LINK, username);
					if (getCouponForActivityRs.isSuccess()) {
						// 成功领取到优惠券
						couponView = getCouponView(getCouponForActivityRs.getIdCode(), activityCode, flag);
						resultMsg.setSuccess(true);
						resultMsg.setReObj(couponView);
					} else {
						if (getCouponForActivityRs.getReCode().equals(ActivityConstant.RETURN_CODE_404)) {
							resultMsg.setSuccess(false);
							resultMsg.setMsg("您来迟了一步，优惠券已经被领完了");
							resultMsg.setReCode(ActivityConstant.RETURN_CODE_404);
							resultMsg.setReObj(couponView);
						}
					}
				}
			} else {
				if (isActivityForLinkCouponRs.getReCode().equals(ActivityConstant.RETURN_CODE_000)) {
					resultMsg.setSuccess(false);
					resultMsg.setMsg("您来早了，活动还没开始");
					resultMsg.setReCode(ActivityConstant.RETURN_CODE_000);

					ActivityLink activityLink = getActivityLinkByCode(activityCode);

					couponView.setActivityTitle(activityLink.getActivityTitle());

					resultMsg.setReObj(couponView);
				} else if (isActivityForLinkCouponRs.getReCode().equals(ActivityConstant.RETURN_CODE_666)) {
					resultMsg.setSuccess(false);
					resultMsg.setMsg("活动已结束");
					resultMsg.setReCode(ActivityConstant.RETURN_CODE_666);

					ActivityLink activityLink = getActivityLinkByCode(activityCode);

					couponView.setActivityTitle(activityLink.getActivityTitle());

					resultMsg.setReObj(couponView);
				}else if(isActivityForLinkCouponRs.getReCode().equals(ActivityConstant.RETURN_CODE_NO_EXSITS)){
					resultMsg.setSuccess(false);
					resultMsg.setMsg("该活动不存在");
					resultMsg.setReCode(ActivityConstant.RETURN_CODE_NO_EXSITS);
					resultMsg.setReObj(couponView);
				}
			}
		}

		return resultMsg;
	}

	public ResultMsg isbelowCouponLimit(String activityCode, String username, int limitNum) throws Exception {
		ResultMsg resultMsg = new ResultMsg();
		resultMsg.setSuccess(false);

		Jedis jedis = null;

		JedisPool jedisPool = null;

		for (int i = 0; i < limitNum; i++) {
			try {
				jedisPool = RedisClient.getInstance().getJedisPool(RedisConstant.getRedisPoolConfigAC());
				jedis = jedisPool.getResource();
				String key = ActivityConstant.getActivityUserKey(activityCode, username);
				jedis.watch(key);

				String numStr = jedis.get(key);// 当前领取数量
				int num = 0;

				if (StringUtils.isNotBlank(numStr)) {
					num = Integer.parseInt(numStr) + 1;
				} else {
					num = 1;
				}

				if (num <= limitNum) {
					// 满足最大领取数量限制
					Transaction tx = jedis.multi();
					tx.set(key, String.valueOf(num));
					if (null != tx.exec()) {
						// 成功预占缓存
						resultMsg.setSuccess(true);
					} else {
						// 并发,重试
						logger.error("活动：" + activityCode + ",用户名：" + username + "，并发设置用户领券数量");
						resultMsg.setSuccess(false);
						resultMsg.setReCode(ActivityConstant.RETURN_CODE_LIMIT_505);
						continue;
					}
					jedis.unwatch();

					if (resultMsg.isSuccess()) {
						break;
					}
				} else {
					// 超出最大领取数量限制
					resultMsg.setSuccess(false);
					resultMsg.setReCode(ActivityConstant.RETURN_CODE_LIMIT_888);
					break;
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw e;
			} finally {
				RedisClient.closeRedis(jedisPool, jedis);
			}
		}

		return resultMsg;
	}

	@Override
	public ResultMsg createOrderActivity(OrderActivity orderActivity) throws Exception {
		ResultMsg resultMsg = new ResultMsg();
		resultMsg.setSuccess(false);

		Map<String, List<Object[]>> sqlBatchs = new HashMap<String, List<Object[]>>();
		// 订单号
		String orderNo = orderActivity.getOrderNo();
		// 店铺标识
		String storeId = orderActivity.getStoreId();
		// 订单总金额
		Float dAmount = orderActivity.getdAmount();
		// 订单商品数量
		Set<String> productSet = orderActivity.getProductSet();

		// 是否参加活动标识
		boolean isActivity = false;

		// 订单满就送赠送优惠券活动
		ActivityLimitOrder activityLimitOrder = getActivityLimitOrder(storeId, dAmount);
		if (null != activityLimitOrder) {
			// 参与了赠券活动
			Map<String, List<Object[]>> preCouponQuantitySqlMap = preCouponQuantitySqlMap(activityLimitOrder);// 预占库存
			SqlMapUtils.merge(sqlBatchs, preCouponQuantitySqlMap);

			Map<String, List<Object[]>> orderActivitySqlMap = createOrderActivitySqlMap(ActivityConstant.TYPE_LIMIT_ORDER, activityLimitOrder.getActivityCode(), orderNo);// 生成订单活动关系
			SqlMapUtils.merge(sqlBatchs, orderActivitySqlMap);

			isActivity = true;
		}

		//订单满就送赠送抽奖机会
		ActivityLimitOrder lottery = getActivityLimitOrderLottery(storeId, dAmount);
		if (null != lottery) {
			Map<String, List<Object[]>> orderActivitySqlMap = createOrderActivitySqlMap(ActivityConstant.TYPE_LIMIT_ORDER, lottery.getActivityCode(), orderNo);// 生成订单活动关系
			SqlMapUtils.merge(sqlBatchs, orderActivitySqlMap);
			isActivity = true;
		}
		//订单商品活动
		for (String productNo : productSet) {
			GeneralActivity generalActivity = getNowGeneralActivityByProductNo(productNo);

			if (null != generalActivity) {
				if (generalActivity.getType() == ActivityConstant.TYPE_LIMIT_TIME) {
					// 限时
					ActivityLimitTime activityLimitTime = generalActivity.getActivityLimitTime();
					Map<String, List<Object[]>> orderActivitySqlMap = createOrderActivitySqlMap(ActivityConstant.TYPE_LIMIT_TIME, activityLimitTime.getActivityCode(), orderNo);
					SqlMapUtils.merge(sqlBatchs, orderActivitySqlMap);
					isActivity = true;
				} else if (generalActivity.getType() == ActivityConstant.TYPE_LIMIT_QUANTITY) {
					// 限量
					ActivityLimited activityLimited = generalActivity.getActivityLimited();
					Map<String, List<Object[]>> orderActivitySqlMap = createOrderActivitySqlMap(ActivityConstant.TYPE_LIMIT_QUANTITY, activityLimited.getActivityCode(), orderNo);
					SqlMapUtils.merge(sqlBatchs, orderActivitySqlMap);
					isActivity = true;
				}else if(generalActivity.getType() == ActivityConstant.TYPE_APPOINT_PRODUCT){
					//购买指定商品就送活动
					ActivityAppoindProduct appointProduct=generalActivity.getActivityAppoindProduct();
					Short givenTypeNum=appointProduct.getGivenTypeNum();
					if(givenTypeNum==ActivityConstant.GIVEN_TYPE_NUM_COUPON){
						if(ObjectUtils.isNotEmpty(appointProduct.getUncollectedTotal())&&appointProduct.getUncollectedTotal()>0){
							isActivity = true;
						}
					}else if(givenTypeNum==ActivityConstant.GIVEN_TYPE_NUM_LOTTORY){
						    isActivity = true;
					}else if(givenTypeNum==ActivityConstant.GIVEN_TYPE_NUM_GIFT){
						//根据商品编码查询商品赠品关联
						List giftList=activityGiftService.findGiftByActivityCode(appointProduct.getActivityCode());
						if(ObjectUtils.isNotEmpty(giftList)&&giftList.size()>0){
							isActivity = true;
						}
						
					}
					if(isActivity){
						Map<String, List<Object[]>> orderActivitySqlMap = createOrderActivitySqlMap(ActivityConstant.TYPE_APPOINT_PRODUCT, appointProduct.getActivityCode(), orderNo);
						SqlMapUtils.merge(sqlBatchs, orderActivitySqlMap);
					}
					
				}
			}
		}

		if (isActivity) {
			boolean result = GenericJDBCSupport.executeBatch(sqlBatchs);
			resultMsg.setSuccess(result);
		}

		return resultMsg;
	}

	public Map<String, List<Object[]>> createOrderActivitySqlMap(Integer type, String activityCode, String orderNo) throws Exception {
		Map<String, List<Object[]>> sqlBatchs = new HashMap<String, List<Object[]>>();

		List<Object[]> insertParam = new ArrayList<Object[]>();
		insertParam.add(new Object[] { activityCode, type, orderNo, CodeUtils.getCode(), new Date(), ActivityConstant.STATUS_ORDER_ACTIVITY });
		sqlBatchs.put(SQL_INSERT_ORDER_ACTIVITY, insertParam);

		return sqlBatchs;
	}

	public synchronized ResultMsg updateLinkActivityMap(ActivityLink activityLink) throws Exception {
		ResultMsg resultMsg = new ResultMsg();
		resultMsg.setSuccess(false);
		if (null != activityLink) {

			if (checkActivityLink(activityLink)) {
				String activityCode = activityLink.getActivityCode();// 活动号

				if (linkActivityMap.containsKey(activityCode)) {
					linkActivityMap.remove(activityCode);
				}
				linkActivityMap.put(activityCode, activityLink);
			}

		}
		return resultMsg;
	}

	/**
	 * 
	 * @Description:检查链接领券活动对象数据有效性
	 * @author CYJ
	 * @date 2014-7-3 下午2:23:45
	 * @param activityLink
	 * @return
	 */
	public boolean checkActivityLink(ActivityLink activityLink) {
		boolean result = true;
		String activityTitle = activityLink.getActivityTitle();
		if (StringUtils.isBlank(activityTitle)) {
			logger.error("checkJmsObj activityTitle is null");
			result = false;
		}

		Date startTime = activityLink.getStartTime();
		if (null == startTime) {
			logger.error("checkJmsObj startTime is null");
			result = false;
		}

		Date endTime = activityLink.getEndTime();
		if (null == endTime) {
			logger.error("checkJmsObj endTime is null");
			result = false;
		}
		Integer status = activityLink.getStatus();
		if (null == status) {
			logger.error("checkJmsObj status is null");
			result = false;
		}
		Integer limitNum = activityLink.getLimitNum();
		if (null == limitNum) {
			logger.error("checkJmsObj limitNum is null");
			result = false;
		}
		String givenProductCode = activityLink.getGivenProductCode();
		if (StringUtils.isBlank(givenProductCode)) {
			logger.error("checkJmsObj givenProductCode is null");
			result = false;
		}
		Integer grantTotal = activityLink.getGrantTotal();
		if (null == grantTotal) {
			logger.error("checkJmsObj grantTotal is null");
			result = false;
		}
		Integer uncollectedTotal = activityLink.getUncollectedTotal();
		if (null == uncollectedTotal) {
			logger.error("checkJmsObj uncollectedTotal is null");
			result = false;
		}

		return result;
	}

	public synchronized void initLinkActivityMap() throws Exception {
		if (MapUtils.isEmpty(linkActivityMap)) {
			List<Object[]> list = GenericJDBCSupport
					.queryBySQLForSlave(
							"SELECT a.code,a.activity_title,a.start_time,a.end_time,a.status,al.limit_num,al.given_product_code,al.grant_total,al.uncollected_total,al.strategy_type FROM lv_activity a,lv_activity_link al WHERE a.code=al.activity_code AND a.type_key=? AND a.status=?",
							new Object[] { ActivityConstant.TYPE_LINK, ActivityConstant.STATUS_USE });

			if (CollectionUtils.isNotEmpty(list)) {
				for (Object[] objs : list) {
					String activityCode = objs[0].toString();
					String activityTitle = objs[1].toString();
					Date startTime = DateUtils.convertToDateTime(objs[2].toString());
					Date endTime = DateUtils.convertToDateTime(objs[3].toString());
					Integer status = Integer.parseInt(objs[4].toString());
					Integer limitNum = Integer.parseInt(null == objs[5] ? "0" : objs[5].toString());
					String givenProductCode = objs[6].toString();
					Integer grantTotal = Integer.parseInt(null == objs[7] ? "0" : objs[7].toString());
					Integer uncollectedTotal = Integer.parseInt(null == objs[8] ? "0" : objs[8].toString());
                    Integer strategyType=Integer.parseInt(objs[9].toString());
					ActivityLink activityLink = new ActivityLink(activityCode, activityTitle, startTime, endTime, status, limitNum, givenProductCode, grantTotal, uncollectedTotal,strategyType);

					linkActivityMap.put(activityCode, activityLink);
				}
			}

		}
	}

	/**
	 * 
	 * @Description:从缓存获取领券活动
	 * @author CYJ
	 * @date 2014-7-1 下午4:49:24
	 * @param activityCode
	 * @return
	 * @throws Exception
	 */
	public ActivityLink getActivityLinkByCode(String activityCode) throws Exception {
		ActivityLink activityLink = null;
		if (MapUtils.isEmpty(linkActivityMap)) {
			initLinkActivityMap();
		}

		if (linkActivityMap.containsKey(activityCode)) {
			activityLink = linkActivityMap.get(activityCode);
		} else {
			// 查询库并加入缓存
			activityLink = getActivityLinkByCodeForDB(activityCode);
			if (null != activityLink) {
				linkActivityMap.put(activityCode, activityLink);
			}
		}

		return activityLink;
	}

	/**
	 * 根据活动号查询领券活动
	 * 
	 * @param activityCode
	 * @return
	 * @throws Exception
	 */
	public ActivityLink getActivityLinkByCodeForDB(String activityCode) throws Exception {
		ActivityLink activityLink = null;
		if (StringUtils.isNotBlank(activityCode)) {
			List<Object[]> list = GenericJDBCSupport
					.queryBySQLForSlave(
							"SELECT a.activity_title,a.start_time,a.end_time,a.status,al.limit_num,al.given_product_code,al.grant_total,al.uncollected_total,al.strategy_type FROM lv_activity a,lv_activity_link al WHERE a.code=al.activity_code AND a.type_key=? AND a.status=? AND a.code=?",
							new Object[] { ActivityConstant.TYPE_LINK, ActivityConstant.STATUS_USE, activityCode });

			if (CollectionUtils.isNotEmpty(list) && list.size() == 1) {
				for (Object[] objs : list) {
					String activityTitle = objs[0].toString();
					Date startTime = DateUtils.convertToDateTime(objs[1].toString());
					Date endTime = DateUtils.convertToDateTime(objs[2].toString());
					Integer status = Integer.parseInt(objs[3].toString());
					Integer limitNum = Integer.parseInt(null == objs[4] ? "0" : objs[4].toString());
					String givenProductCode = objs[5].toString();
					Integer grantTotal = Integer.parseInt(null == objs[6] ? "0" : objs[6].toString());
					Integer uncollectedTotal = Integer.parseInt(null == objs[7] ? "0" : objs[7].toString());
					Integer strategyType=Integer.parseInt("8");
					activityLink = new ActivityLink(activityCode, activityTitle, startTime, endTime, status, limitNum, givenProductCode, grantTotal, uncollectedTotal,strategyType);

				}
			}
		}
		return activityLink;
	}

	public synchronized void initActivityUserCache() throws Exception {
		List<Object[]> couponList = GenericJDBCSupport.queryBySQLForSlave(SQL_GET_USER_COUPON_NUM, new Object[] { CouponConstant.STATUS_GET_DISUSE, CouponConstant.STATUS_GET_USE,
				CouponConstant.TYPE_ONLINE, ActivityConstant.TYPE_LINK });
		if (CollectionUtils.isNotEmpty(couponList)) {
			Jedis jedis = null;
			JedisPool jedisPool = null;
			try {
				jedisPool = RedisClient.getInstance().getJedisPool(RedisConstant.getRedisPoolConfigAC());
				jedis = jedisPool.getResource();
				for (Object[] objs : couponList) {
					String activityCode = objs[0].toString();// 活动号
					String username = objs[1].toString();// 用户名
					String num = objs[2].toString();// 数量

					String key = ActivityConstant.getActivityUserKey(activityCode, username);

					jedis.set(key, num);
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				throw e;
			} finally {
				RedisClient.closeRedis(jedisPool, jedis);
			}
		}
	}

	@Override
	public ActivityLimitOrder getActivityLimitOrderByCode(String activityCode)
			throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT a.code, a.activity_title, o.limit_total_price, o.given_type_name");
		sql.append(", o.given_product_code, o.given_type_num");
		sql.append(" FROM lv_activity_limit_order o LEFT OUTER JOIN lv_activity a");
		sql.append(" ON o.activity_code = a.code");
		sql.append(" WHERE o.activity_code=?");
		Object[] obj = GenericJDBCSupport.findUniqueBySQLForSlave(sql.toString(), new Object[]{activityCode});
		if (ObjectUtils.isEmpty(obj)) {
			logger.info("找不到该订单活动");
			return null;
		}
		ActivityLimitOrder order = new ActivityLimitOrder();
		order.setActivityCode(obj[0].toString());
		order.setActivityTitle(obj[1].toString());
		order.setLimitTotalPrice(Float.valueOf(obj[2].toString()));
		order.setGivenTypeName(StringUtil.isNotEmpty(obj[3]));
		order.setGivenProductCode(StringUtil.isNotEmpty(obj[4]));
		if (ObjectUtils.isNotEmpty(obj[5])) {
			order.setGivenTypeNum(Short.valueOf(obj[5].toString()));
		}
		
		return order;
	}

	@Override
	public LvActivity getActivity(String activityCode) throws Exception {
		return (LvActivity) lvlogicReadDao.findUniqueByProperty(LvActivity.class, "code", activityCode);
	}
}
