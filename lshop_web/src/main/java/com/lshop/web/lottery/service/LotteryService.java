package com.lshop.web.lottery.service;

import java.util.List;

import com.lshop.web.lottery.bo.LotteryDetail;
import com.lshop.web.lottery.bo.LotteryItem;
import com.lshop.web.lottery.bo.LotteryRecord;
import com.lshop.web.lottery.bo.LotteryResult;

/**
 * 抽奖活动服务
 * @author caicl
 *
 */
public interface LotteryService {
	/**
	 * 查看抽奖详情
	 * @param lotteryCode
	 */
	public LotteryDetail getLotteryDetail(String lotteryCode);

	/**
	 * 用户抽奖
	 * @param userCode
	 * @param lotteryCode
	 * @param userName TODO
	 * @throws Exception 
	 */
	public LotteryResult lotteryDraw(String userCode, String lotteryCode, String userName) throws Exception;
	/**
	 * 颁发奖品
	 * @param userCode
	 * @param lotteryCode
	 * @param userName
	 * @param detail
	 * @param cItem
	 * @param mallFalg TODO
	 * @throws Exception 
	 */
	void giveAwayPrize(String userCode, String lotteryCode,
			String userName, LotteryDetail detail, LotteryItem cItem, String mallFalg) throws Exception;
	/**
	 * 增加中奖记录
	 * @param userCode
	 * @param lotteryCode
	 * @param prizeCode 奖品系统编码
	 * @param userName TODO
	 * @param lotteryName TODO
	 * @param myPrizeCode 我的奖品号
	 * @param priceName
	 */
	public String addLotteryRecord(String userCode, String lotteryCode, String prizeCode, String prizeName, String userName, String lotteryName, String myPrizeCode);

	/**
	 * 获得抽奖中奖榜信息
	 * @param lotteryCode
	 */
	public List<LotteryRecord> getLatestRecord(String lotteryCode);
}
