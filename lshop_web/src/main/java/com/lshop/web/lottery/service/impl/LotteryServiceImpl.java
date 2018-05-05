package com.lshop.web.lottery.service.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gv.core.datastructure.page.Pagination;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.activity.constant.ActivityConstant;
import com.lshop.common.constant.LotteryConstant;
import com.lshop.common.pojo.logic.LvActivity;
import com.lshop.common.pojo.logic.LvActivityLottery;
import com.lshop.common.pojo.logic.LvLotteryLogs;
import com.lshop.common.pojo.logic.LvLotteryPrize;
import com.lshop.common.util.AsynThreadExcutor;
import com.lshop.web.lottery.bo.LotteryDetail;
import com.lshop.web.lottery.bo.LotteryItem;
import com.lshop.web.lottery.bo.LotteryRecord;
import com.lshop.web.lottery.bo.LotteryResult;
import com.lshop.web.lottery.core.LotteryDraw;
import com.lshop.web.lottery.core.ProbabilityDraw;
import com.lshop.web.lottery.service.LotteryChanceService;
import com.lshop.web.lottery.service.LotteryPrizeService;
import com.lshop.web.lottery.service.LotteryService;

@Service("LotteryService")
public class LotteryServiceImpl implements LotteryService{
	private static Log log = LogFactory.getLog(LotteryServiceImpl.class);
	@Resource
	private HibernateBaseDAO lvlogicReadDao;
	@Resource
	private HibernateBaseDAO lvlogicWriteDao;
	@Resource
	private LotteryChanceService lotteryChanceService;
	@Resource
	private LotteryPrizeService lotteryPrizeService;
	@Override
	public LotteryDetail getLotteryDetail(String lotteryCode) {
		String hql = "from LvActivity where code=:lotteryCode and status=" + ActivityConstant.STATUS_USE;
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("lotteryCode", lotteryCode);
		LvActivity activity = (LvActivity) lvlogicReadDao.findUnique(hql, param);
		if (ObjectUtils.isEmpty(activity)) {
			log.info("not found lottery");
			return null;
		}
		hql = "from LvActivityLottery where activityCode=:lotteryCode";
		LvActivityLottery lottery = (LvActivityLottery) lvlogicReadDao.findUnique(hql, param);
		if (ObjectUtils.isEmpty(activity)) {
			log.info("not found lottery");
			return null;
		}
		LotteryDetail detail = new LotteryDetail();
		detail.setLotteryCode(lotteryCode);
		detail.setTitle(activity.getActivityTitle());
		detail.setStartTime(activity.getStartTime());
		detail.setEndTime(activity.getEndTime());
		detail.setRule(lottery.getActivityRule());
		detail.setMallFlag(lottery.getStoreId());
		detail.setExpiryDate(lottery.getEndTicketDate());
		//find lottery prize
		hql = "from LvLotteryPrize where activityCode=:lotteryCode order by sortId asc";
		List<LvLotteryPrize> prizes = lvlogicReadDao.find(hql, param);
		if (ObjectUtils.isNotEmpty(prizes)) {
			List<LotteryItem> items = new ArrayList<LotteryItem>();
			LotteryItem item = null;
			for (Iterator<LvLotteryPrize> iterator = prizes.iterator(); iterator.hasNext();) {
				LvLotteryPrize prize = iterator.next();
				item = new LotteryItem();
				item.setLotteryCode(lotteryCode);
				item.setItemCode(prize.getCode());
				item.setPrizeType(prize.getPrizeType().intValue());
				item.setName(prize.getPrizeName());
				item.setPrizeCode(prize.getPrizeCode());
				item.setImgUrl(prize.getPrizeImages());
				//计算剩余数量
				item.setRestNum(prize.getPrizeTotal() - prize.getGrantTotal());
				item.setUseNum(prize.getGrantTotal());
				item.setWin(prize.getIsDraw() == LotteryConstant.PRIZE_AWARD);
				
				items.add(item);
			}
			detail.setItems(items);
		}
		return detail;
	}

	@Override
	public LotteryResult lotteryDraw(String userCode, String lotteryCode, String userName) throws Exception {
		LotteryResult result = new LotteryResult();
		//检查抽奖状态
		LotteryDetail detail = getLotteryDetail(lotteryCode);
		if (ObjectUtils.isEmpty(detail)) {
			log.error("抽奖被禁用或者不存在!");
			result.setStatus(LotteryConstant.DRAW_NOT_EXIST);
			return result;
		}
		if (System.currentTimeMillis() < detail.getStartTime().getTime()) {
			log.info("没有还没有开始");
			result.setStatus(LotteryConstant.DRAW_ACTIVITY_NOT_START);
			return result;
		}
		if (System.currentTimeMillis() > detail.getEndTime().getTime()) {
			log.info("活动已经结束");
			result.setStatus(LotteryConstant.DRAW_ACTIVITY_END);
			return result;
		}
		//查看抽奖剩余奖品
		Map<String, Integer> lotteryMap = new HashMap<String, Integer>();
		for (Iterator<LotteryItem> iterator = detail.getItems().iterator(); iterator.hasNext();) {
			LotteryItem item = iterator.next();
			int rest = item.getRestNum();
			if (rest > 0) {
				lotteryMap.put(item.getItemCode(), rest);
			}
		}
		if (lotteryMap.size() == 0) {
			//没有奖品
			log.info("抽奖活动没有奖品");
			result.setStatus(LotteryConstant.DRAW_NOT_REWORD);
			return result;
		}
		//查看抽奖机会并使用
		boolean useChance = lotteryChanceService.useChance(userCode, lotteryCode);
		if (!useChance) {
			log.info("用户没有抽奖机会!");
			result.setStatus(LotteryConstant.DRAW_NOT_CHANCE);
			return result;
		}
		//概率抽奖
		LotteryDraw draw = new ProbabilityDraw();
		String award = draw.draw(lotteryMap);
		LotteryItem cItem = null;
		for (Iterator<LotteryItem> iterator = detail.getItems().iterator(); iterator.hasNext();) {
			LotteryItem item = iterator.next();
			if (award.equalsIgnoreCase(item.getItemCode())) {
				result.setPrizeNo(item.getItemCode());
				result.setPrizeName(item.getName());
				result.setUserName(userName);
				result.setStatus(item.isWin() ? LotteryConstant.DRAW_WIN : LotteryConstant.DRAW_NOT_WIN);
				cItem = item;
				break;
			}
		}
		//更新奖项奖品库存
		String hql = "update LvLotteryPrize set grantTotal="+(cItem.getUseNum()+1)+" where code=:itemCode";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("itemCode", cItem.getItemCode());
		lvlogicWriteDao.update(hql, param);
		System.out.println(userName+"抽中"+cItem.getName());
		//若抽中奖,则增加记录
		if (LotteryConstant.DRAW_WIN.equals(result.getStatus())) {
			Method method = LotteryService.class.getDeclaredMethod("giveAwayPrize", String.class, String.class, String.class, LotteryDetail.class, LotteryItem.class, String.class);
			AsynThreadExcutor.getInstance().asyncInvoke(method, this, userCode, lotteryCode, userName, detail, cItem, detail.getMallFlag());
		}
		return result;
	}
	@Override
	public void giveAwayPrize(String userCode, String lotteryCode,
			String userName, LotteryDetail detail, LotteryItem cItem, String mallFalg)
			throws Exception {
		String myPrizeCode = lotteryPrizeService.addPrize(userCode, cItem.getPrizeCode(), cItem.getName(), cItem.getPrizeType(), lotteryCode, cItem.getItemCode(), detail.getExpiryDate(), mallFalg);
		addLotteryRecord(userCode, lotteryCode, cItem.getPrizeCode(), cItem.getName(), userName, detail.getTitle(), myPrizeCode);
	}

	@Override
	public String addLotteryRecord(String userCode, String lotteryCode,
			String prizeCode, String prizeName, String userName, String lotteryName, String myPrizeCode) {
		LvLotteryLogs record = new LvLotteryLogs();
		record.setActivityCode(lotteryCode);
		record.setActivityName(lotteryName);
		record.setPrizeName(prizeName);
		record.setPrizeCode(prizeCode);
		record.setUserName(userName);
		record.setUserCode(userCode);
		record.setCreateTime(new Date());
		record.setIsSystemFlag(LotteryConstant.LOTTERY_RECORD_FLAG);
		record.setPrizeNum(1);
		record.setAccountPrizeCode(myPrizeCode);
		lvlogicWriteDao.save(record);
		return record.getId().toString();
	}

	@Override
	public List<LotteryRecord> getLatestRecord(String lotteryCode) {
		String hql = "from LvLotteryLogs where activityCode=:activityCode order by createTime desc";
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("activityCode", lotteryCode);
		Pagination pagination = lvlogicReadDao.find(Finder.create(hql).setParam("activityCode", lotteryCode), 1, 7);
		List<LvLotteryLogs> logs = (List<LvLotteryLogs>) pagination.getList();
		List<LotteryRecord> records = new ArrayList<LotteryRecord>();
		LotteryRecord lr = null;
		String starName = null;
		for (Iterator<LvLotteryLogs> iterator = logs.iterator(); iterator.hasNext();) {
			LvLotteryLogs lvLotteryLogs = iterator.next();
			//加星号处理用户号
			if (lvLotteryLogs.getUserName().length() > 3) {
				starName = lvLotteryLogs.getUserName().substring(0, 3)+"*****";
			} else {
				starName = lvLotteryLogs.getUserName()+"*****";
			}
			lr = new LotteryRecord(starName, lvLotteryLogs.getPrizeName());
			records.add(lr);
		}
		return records;
	}

	
}
