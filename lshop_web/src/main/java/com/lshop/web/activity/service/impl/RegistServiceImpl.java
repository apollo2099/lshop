package com.lshop.web.activity.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.activity.constant.ActivityConstant;
import com.lshop.common.activity.vo.RegistActivity;
import com.lshop.common.util.GenericJDBCSupport;
import com.lshop.web.activity.service.ActivityService;
import com.lshop.web.activity.service.RegistService;
import com.lshop.web.lottery.service.LotteryChanceService;
@Service("RegistService")
public class RegistServiceImpl implements RegistService{
	private static final Log logger = LogFactory.getLog(RegistService.class);
	@Resource
	private HibernateBaseDAO lvlogicReadDao;
	@Resource
	private HibernateBaseDAO lvlogicWriteDao;
	@Resource
	private ActivityService activityService;
	@Resource
	private LotteryChanceService lotteryChanceService;
	@Override
	public List<RegistActivity> getAllRegistActivitie(String mallFlag) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT a.code, a.activity_title, a.start_time, a.end_time");
		sql.append(", r.given_type_num, r.given_type_name , r.uncollected_total, r.given_product_code, r.lottery_total");
		sql.append(" FROM lv_activity a LEFT OUTER JOIN lv_activity_register r ON a.code = r.activity_code");
		sql.append(" WHERE a.type_key=? and a.status=? and a.start_time<=? and a.end_time>=?");
		sql.append(" and (r.given_type_num!=1 OR (r.given_type_num=1 and r.uncollected_total>0))");
		sql.append(" and r.store_id=?");
		Date now = new Date();
		List<Object[]> mapObj = GenericJDBCSupport.queryBySQLForSlave(sql.toString(),
				new Object[]{ActivityConstant.TYPE_REGIST, ActivityConstant.STATUS_USE, now, now, mallFlag});
		return map2Detail(mapObj);
	}
	/**
	 * sql返回对象mapper转换
	 * @param arrs
	 * @return
	 */
	private List<RegistActivity> map2Detail(List<Object[]> arrs) {
		List<RegistActivity> activities = new ArrayList<RegistActivity>();
		if (null == arrs) {
			return activities;
		}
		Object[] obj = null;
		RegistActivity det = null;
		for (Iterator<Object[]> iterator = arrs.iterator(); iterator.hasNext();) {
			obj = iterator.next();
			det = new RegistActivity();
			det.setActivityCode(obj[0].toString());
			det.setActivityTitle(obj[1].toString());
			det.setStartTime((Date) obj[2]);
			det.setEndTime((Date) obj[3]);
			det.setGiftType(Integer.valueOf(obj[4].toString()));
			det.setGiftName(obj[5].toString());
			if (null != obj[6]) {
				det.setRestNum(Integer.valueOf(obj[6].toString()));
			}
			det.setGiftCode(obj[7].toString());
			if (null != obj[8]) {
				det.setPerNum(Integer.valueOf(obj[8].toString()));
			}
			activities.add(det);
		}
		return activities;
	}
	@Override
	public boolean userRegisted(String userCode, String mallFlag)
			throws Exception {
		List<RegistActivity> activities = getAllRegistActivitie(mallFlag);
		//因一个用户的一个抽奖活动的机会次数只能保存一个,故把增加抽奖机会移出循环
		Map<String, Integer> chanceMap = new HashMap<String, Integer>();
		for (Iterator<RegistActivity> iterator = activities.iterator(); iterator.hasNext();) {
			RegistActivity ra = iterator.next();
			switch (ra.getGiftType()) {
			case ActivityConstant.GIFT_TYPE_COUPON:
				//通过活动获得优惠券
				activityService.getCouponByActivity(ra.getActivityCode(), ActivityConstant.TYPE_REGIST, userCode);
				break;
			case ActivityConstant.GIFT_TYPE_LOTTERYP:
				//赠送抽奖机会
				if (chanceMap.containsKey(ra.getGiftCode())) {
					Integer num = chanceMap.get(ra.getGiftCode()) + ra.getPerNum();
					chanceMap.put(ra.getGiftCode(), num);
				} else {
					chanceMap.put(ra.getGiftCode(), ra.getPerNum());
				}
				break;
			}
		}
		//赠送抽奖机会
		if (ObjectUtils.isNotEmpty(chanceMap.entrySet())) {
			for (Iterator<String> iterator = chanceMap.keySet().iterator(); iterator.hasNext();) {
				String key = iterator.next();
				lotteryChanceService.addChance(key, chanceMap.get(key), userCode);
			}
		}
		return true;
	}
	@Override
	@Async
	public void asyncRegisted(String userCode, String mallFlag) {
		try {
			userRegisted(userCode, mallFlag);
		} catch (Exception e1) {
			logger.error(e1.getMessage());
		}
	}

}
