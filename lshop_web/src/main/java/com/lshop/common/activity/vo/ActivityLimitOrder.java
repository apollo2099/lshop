package com.lshop.common.activity.vo;

/**
 * 
 * @Description:赠券活动
 * @author CYJ
 * @date 2014-6-24 下午4:09:36
 */
public class ActivityLimitOrder {
	/**
	 * 活动号
	 */
	private String activityCode;
	/**
	 * 活动标题
	 */
	private String activityTitle;
	/**
	 * 活动限额
	 */
	private Float limitTotalPrice;
	/**
	 * 店铺编号
	 */
	private String storeId;
	/**
	 * 赠送类型名称
	 */
	private String givenTypeName;
	/**
	 * 赠送物品关联
	 */
	private String givenProductCode;
	/**
	 * 赠送类型字典(1赠送优惠券)
	 */
	private Short givenTypeNum;
	/**
	 * 优惠券金额
	 */
	private Float amount;
	/**
	 * 优惠券名称
	 */
	private String couponName;

	/**
	 * 优惠券限额
	 */
	private Float limitAmount;
	/**
	 * 赠送抽奖机会次数
	 */
	private int lotteryNum;

	public ActivityLimitOrder() {
		super();
	}

	public ActivityLimitOrder(String activityCode, String activityTitle, Float limitTotalPrice, String storeId, String givenTypeName,
			String givenProductCode, Short givenTypeNum, Float amount, String couponName, Float limitAmount) {
		super();
		this.activityCode = activityCode;
		this.activityTitle = activityTitle;
		this.limitTotalPrice = limitTotalPrice;
		this.storeId = storeId;
		this.givenTypeName = givenTypeName;
		this.givenProductCode = givenProductCode;
		this.givenTypeNum = givenTypeNum;
		this.amount = amount;
		this.couponName = couponName;
		this.limitAmount = limitAmount;
	}

	public String getActivityCode() {
		return activityCode;
	}

	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}

	public String getActivityTitle() {
		return activityTitle;
	}

	public void setActivityTitle(String activityTitle) {
		this.activityTitle = activityTitle;
	}

	public Float getLimitTotalPrice() {
		return limitTotalPrice;
	}

	public void setLimitTotalPrice(Float limitTotalPrice) {
		this.limitTotalPrice = limitTotalPrice;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getGivenTypeName() {
		return givenTypeName;
	}

	public void setGivenTypeName(String givenTypeName) {
		this.givenTypeName = givenTypeName;
	}

	public String getGivenProductCode() {
		return givenProductCode;
	}

	public void setGivenProductCode(String givenProductCode) {
		this.givenProductCode = givenProductCode;
	}

	public Short getGivenTypeNum() {
		return givenTypeNum;
	}

	public void setGivenTypeNum(Short givenTypeNum) {
		this.givenTypeNum = givenTypeNum;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public Float getLimitAmount() {
		return limitAmount;
	}

	public void setLimitAmount(Float limitAmount) {
		this.limitAmount = limitAmount;
	}

	public int getLotteryNum() {
		return lotteryNum;
	}

	public void setLotteryNum(int lotteryNum) {
		this.lotteryNum = lotteryNum;
	}

	@Override
	public String toString() {
		return "ActivityLimitOrder [activityCode=" + activityCode
				+ ", activityTitle=" + activityTitle + ", limitTotalPrice="
				+ limitTotalPrice + ", storeId=" + storeId + ", givenTypeName="
				+ givenTypeName + ", givenProductCode=" + givenProductCode
				+ ", givenTypeNum=" + givenTypeNum + ", amount=" + amount
				+ ", couponName=" + couponName + ", limitAmount=" + limitAmount
				+ ", lotteryNum=" + lotteryNum + "]";
	}

}
