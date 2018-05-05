package com.lshop.web.lottery.service;

import com.lshop.common.pojo.logic.LvAccountLottery;

/**
 * 抽奖机会服务
 * @author caicl
 *
 */
public interface LotteryChanceService {
	public LvAccountLottery getChance(String userCode, String lotteryCode);
	/**
	 * 增加抽奖机会
	 * @param lotteryCode
	 * @param num
	 * @param userCode
	 */
	public void addChance(String lotteryCode, int num, String userCode);

	/**
	 * 使用抽奖机会抽奖
	 * @param userCode
	 * @param lotteryCode
	 */
	public boolean useChance(String userCode, String lotteryCode);

	/**
	 * 查看用户抽奖机会次数
	 * @param userCode
	 * @param lotteryCode
	 */
	public int getChanceNum(String userCode, String lotteryCode);
}
