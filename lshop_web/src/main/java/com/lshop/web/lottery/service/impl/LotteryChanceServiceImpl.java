package com.lshop.web.lottery.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvAccountLottery;
import com.lshop.common.util.CodeUtils;
import com.lshop.web.lottery.service.LotteryChanceService;

@Service("LotteryChanceService")
public class LotteryChanceServiceImpl implements LotteryChanceService{
	
	private static Log log = LogFactory.getLog(LotteryChanceServiceImpl.class);
	@Resource
	private HibernateBaseDAO lvlogicReadDao;
	@Resource
	private HibernateBaseDAO lvlogicWriteDao;

	@Override
	public LvAccountLottery getChance(String userCode, String lotteryCode) {
		String hql = "from LvAccountLottery where activityCode=:activityCode and userCode=:userCode";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("activityCode", lotteryCode);
		param.put("userCode", userCode);
		return (LvAccountLottery) lvlogicReadDao.findUnique(hql, param);
	}
	
	@Override
	public void addChance(String lotteryCode, int num, String userCode) {
		LvAccountLottery accountLottery = getChance(userCode, lotteryCode);
		Date curDate = new Date();
		if (ObjectUtils.isEmpty(accountLottery)) {
			// 没有获得机会记录,则新建
			accountLottery = new LvAccountLottery();
			accountLottery.setActivityCode(lotteryCode);
			accountLottery.setUserCode(userCode);
			accountLottery.setCode(CodeUtils.getCode());
			accountLottery.setLotteryCount(num);
			accountLottery.setCreateTime(curDate);
			accountLottery.setModifyTime(curDate);
		} else {
			// 已经有机会记录,则相加
			int curNum = num + accountLottery.getLotteryCount().intValue();
			accountLottery.setLotteryCount(curNum);
			accountLottery.setModifyTime(curDate);
		}
		lvlogicWriteDao.merge(accountLottery);
	}

	@Override
	public boolean useChance(String userCode, String lotteryCode) {
		int curChance = getChanceNum(userCode, lotteryCode);
		if (curChance > 0) {
			//拥有机会
			curChance--;
			String hql = "update LvAccountLottery set lotteryCount=:lotteryCount, modifyTime=:modifyTime";
			hql += " where activityCode=:activityCode and userCode=:userCode";
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("activityCode", lotteryCode);
			param.put("userCode", userCode);
			param.put("lotteryCount", curChance);
			param.put("modifyTime", new Date());
			lvlogicWriteDao.update(hql, param);
			return true;
		}
		return false;
	}

	@Override
	public int getChanceNum(String userCode, String lotteryCode) {
		LvAccountLottery accountLottery = getChance(userCode, lotteryCode);
		if (ObjectUtils.isNotEmpty(accountLottery)) {
			return accountLottery.getLotteryCount().intValue();
		}
		return 0;
	}


}
