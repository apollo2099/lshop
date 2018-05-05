package com.lshop.common.constant;

/**
 * 抽奖常量
 * @author caicl
 *
 */
public class LotteryConstant {
	/**
	 * 未兑奖
	 */
	public final static int PRIZE_STATUS_NOT = 0;
	/**
	 * 已提交兑奖,未发货
	 */
	public final static int PRIZE_STATUS_SUBMIT = 1;
	/**
	 * 已兑奖已发货
	 */
	public final static int PRIZE_STATUS_SHIPPED = 2;
	/**
	 * 奖项中奖
	 */
	public final static short PRIZE_AWARD = 1;
	/**
	 * 奖品类型
	 */
	public final static int PRIZE_COUPON_TYPE = 1;
	public final static int PRIZE_MATERIAL_TYPE = 2;
	/**
	 * 用户抽奖记录标志
	 */
	public final static short LOTTERY_RECORD_FLAG = 0;
	/**
	 * 抽奖结果状态
	 * 抽奖结果:1表示中奖,2表示没有抽中,3表示用户没有抽奖机会,4表示活动还没有开始,5表示活动已经结束,6表示活动已经没有奖品
	 */
	public final static Integer DRAW_WIN = 1;
	public final static Integer DRAW_NOT_WIN = 2;
	public final static Integer DRAW_NOT_CHANCE = 3;
	public final static Integer DRAW_ACTIVITY_NOT_START = 4;
	public final static Integer DRAW_ACTIVITY_END = 5;
	public final static Integer DRAW_NOT_REWORD = 6;
	public final static Integer DRAW_NOT_EXIST = 7;
}
