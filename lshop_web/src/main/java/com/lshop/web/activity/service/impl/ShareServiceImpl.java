package com.lshop.web.activity.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.activity.constant.ActivityConstant;
import com.lshop.common.activity.vo.ShareActivity;
import com.lshop.common.pojo.logic.LvActivity;
import com.lshop.common.pojo.logic.LvActivityShare;
import com.lshop.common.pojo.user.LvAccount;
import com.lshop.common.util.GenericJDBCSupport;
import com.lshop.common.util.RedisClient;
import com.lshop.common.util.RedisConstant;
import com.lshop.common.util.StringUtil;
import com.lshop.web.activity.service.ActivityService;
import com.lshop.web.activity.service.ShareService;
import com.lshop.web.lottery.service.LotteryChanceService;
@Service("ShareService")
public class ShareServiceImpl implements ShareService{
	
	private static Log log = LogFactory.getLog(ShareServiceImpl.class);
	@Resource
	private HibernateBaseDAO lvlogicReadDao;
	@Resource
	private HibernateBaseDAO lvlogicWriteDao;
	@Resource
	private HibernateBaseDAO lvuserReadDao;
	@Resource
	private ActivityService activityService;
	@Resource
	private LotteryChanceService lotteryChanceService;
	@Override
	public ShareActivity getFirstLotteryShare(String lotteryCode) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT a.activity_title, a.code, a.start_time, a.end_time, s.share_image, s.share_desc");
		sql.append(" FROM lv_activity_share s");
		sql.append(" LEFT OUTER JOIN lv_activity a on s.activity_code=a.code");
		sql.append(" WHERE s.given_type_num=? AND s.given_product_code=?");
		sql.append(" and a.status=? and a.start_time<=? and a.end_time>=?");
		sql.append(" ORDER BY s.create_time DESC LIMIT 0,1");
		Date now = new Date();
		List<Object[]> mapShare = GenericJDBCSupport.queryBySQLForSlave(sql.toString(), new Object[]{
			ActivityConstant.GIFT_TYPE_LOTTERYP, lotteryCode, ActivityConstant.STATUS_USE, now, now
		});
		if (ObjectUtils.isEmpty(mapShare)) {
			log.info("没有找到抽奖相关联的分享活动");
			return null;
		}
		//找到相关联的分享活动
		ShareActivity sa = new ShareActivity();
		Object[] mo = mapShare.get(0);
		sa.setActivityTitle(mo[0].toString());
		sa.setActivityCode(mo[1].toString());
		sa.setStartTime((Date) mo[2]);
		sa.setEndTime((Date) mo[3]);
		sa.setShareImg(StringUtil.isNotEmpty(mo[4]));
		sa.setShareText(StringUtil.isNotEmpty(mo[5]));
		sa.setShareLink(StringUtil.isNotEmpty(mo[6]));
		return sa;
	}

	@Override
	public boolean shareSuccess(String userCode, String shareCode) throws Exception {
		//检查用户状态
		String hql = "from LvAccount where code=:code and status=1";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("code", userCode);
		LvAccount account = (LvAccount) lvuserReadDao.findUnique(hql, param);
		if (ObjectUtils.isEmpty(account)) {
			log.info("用户不存在或者已被禁用");
			return false;
		}
		//检查活动状态
		hql = "from LvActivity where code=:code and status=1 and typeKey=:type";
		param = new HashMap<String, Object>();
		param.put("code", shareCode);
		param.put("type", ActivityConstant.TYPE_SHARE);
		LvActivity activity = (LvActivity) lvlogicReadDao.findUnique(hql, param);
		if (ObjectUtils.isEmpty(activity)) {
			log.info("活动不存在或者已经被禁用");
			return false;
		}
		hql = "from LvActivityShare where activityCode=:activityCode";
		param = new HashMap<String, Object>();
		param.put("activityCode", shareCode);
		LvActivityShare activityShare = (LvActivityShare) lvlogicReadDao.findUnique(hql, param);
		if (ObjectUtils.isEmpty(activityShare)) {
			log.info("分享活动不存在或者已经被禁用");
			return false;
		}
		//活动赠送
		switch (activityShare.getGivenTypeNum()) {
		case ActivityConstant.GIFT_TYPE_COUPON:
			activityService.getCouponByActivity(shareCode, ActivityConstant.TYPE_SHARE, userCode);
			break;
		case ActivityConstant.GIFT_TYPE_LOTTERYP:
			//查看用户之前分享
			if (!updataeShareNumCache(shareCode, userCode, activityShare.getPartakeNum())) {
				log.info("没有参加活动资格");
				return false;
			}
			lotteryChanceService.addChance(activityShare.getGivenProductCode(), activityShare.getLotteryTotal(), userCode);
			break;
		}
		return true;
	}
	/**
	 * 更新分享次数缓存,返回false表示用户没有参加分享活动资格
	 * @param shareCode
	 * @param userCode
	 * @param maxNum
	 * @return
	 * @throws Exception 
	 */
	private boolean updataeShareNumCache(String shareCode, String userCode, int maxNum) throws Exception {
		Jedis jedis = null;
		JedisPool jedisPool = null;
		try {
			jedisPool = RedisClient.getInstance().getJedisPool(RedisConstant.getRedisPoolConfigAC());
			jedis = jedisPool.getResource();
			String shareNumKey = ActivityConstant.getActivityShareNumKey(shareCode, userCode);
			String shareNum = jedis.get(shareNumKey);
			if (null == shareNum) {
				//不存在分享次数记录
				int ex = 60*60*24;//键值过期时间
				jedis.setex(shareNumKey, ex, "1");
			} else if (Integer.valueOf(shareNum).intValue() < maxNum) {
				jedis.incr(shareNumKey);
			} else {
				//已经达到最大分享次数,不参加分享活动
				return false;
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		} finally {
			RedisClient.closeRedis(jedisPool, jedis);
		}
		return true;
	}

	@Override
	public ShareActivity getShareDetail(String activityCode) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT a.activity_title, a.code, a.start_time, a.end_time, s.share_image, s.share_desc, share_link");
		sql.append(" FROM lv_activity_share s");
		sql.append(" LEFT OUTER JOIN lv_activity a on s.activity_code=a.code");
		sql.append(" WHERE a.code=?");
		sql.append(" and a.status=? and a.start_time<=? and a.end_time>=?");
		Date now = new Date();
		List<Object[]> mapShare = GenericJDBCSupport.queryBySQLForSlave(sql.toString(), new Object[]{
			activityCode, ActivityConstant.STATUS_USE, now, now
		});
		if (ObjectUtils.isEmpty(mapShare)) {
			log.info("没有找到该分享活动");
			return null;
		}
		//找到分享活动
		ShareActivity sa = new ShareActivity();
		Object[] mo = mapShare.get(0);
		sa.setActivityTitle(mo[0].toString());
		sa.setActivityCode(mo[1].toString());
		sa.setStartTime((Date) mo[2]);
		sa.setEndTime((Date) mo[3]);
		sa.setShareImg(StringUtil.isNotEmpty(mo[4]));
		sa.setShareText(StringUtil.isNotEmpty(mo[5]));
		sa.setShareLink(StringUtil.isNotEmpty(mo[6]));
		return sa;
	}
}
