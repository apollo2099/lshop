package com.lshop.web.lottery.core;

import java.util.Map;

/**
 * 抽奖算法
 * @author caicl
 *
 */
public interface LotteryDraw {
	/**
	 * 进行抽奖
	 * @param lotteryItem 奖项及其奖券数键值对,奖券数必须大于0
	 * @return 奖项号
	 */
	public String draw(Map<String, Integer> lotteryItem);
}
