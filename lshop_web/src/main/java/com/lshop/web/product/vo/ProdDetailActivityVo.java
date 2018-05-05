package com.lshop.web.product.vo;

import java.io.Serializable;
import java.util.List;

import com.lshop.common.pojo.logic.LvActivityGift;

/**
 * 商品详情活动接口
 * @author caicl
 *
 */
public class ProdDetailActivityVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3868081673187821599L;
	/**
	 * 商品状态:0表示禁用,1表示启用
	 */
	private int prodStatus;
	/**
	 * 商品号
	 */
	private String prodNo;
	/**
	 * 店铺标识
	 */
	private String storeId;
	/**
	 * 店铺对应币种
	 */
	private String currency;
	/**
	 * 商品原价
	 */
	private String orignPrice;
	/**
	 * 关联活动号
	 */
	private String activityNo;
	/**
	 * 活动名称
	 */
	private String activityName;
	/**
	 * 活动名称
	 */
	private String activityNameEn;
	/**
	 * 商品活动价格
	 */
	private String activityPrice;
	/**
	 * 商品活动类型(1表示限时,2表示限量,3表示阶梯价,4购买指定商品就送活到那个)
	 */
	private int activityType;
	/**
	 * 限时活动开始时间戳
	 */
	private long beginTime;
	/**
	 * 限时活动结束时间戳
	 */
	private long endTime;
	/**
	 * 当前时间戳
	 */
	private long curTime;
	/**
	 * 限量活动剩余库存
	 */
	private int limitCount;
	/**
	 * 商品单价满足的订单赠券活动标题
	 */
	private String orderActivityTitle;
	
	
	
	/**
	 * 赠送类型名称
	 */
	private String givenTypeName;
	/**
	 * 赠送物品关联
	 */
	private String givenProductCode;
	/**
	 * 赠送类型字典(1赠送优惠券,赠送抽奖机会，3赠送礼品)
	 */
	private Short givenTypeNum;
	/**
	 * 优惠券限额
	 */
	private Float limitAmount;
	/**
	 * 赠送抽奖机会次数
	 */
	private int lotteryNum;
	/**
	 * 是否使用优惠券
	 */
	private Short isUseCoupon; 
	/**
	 * 赠送物品未领取总数
	 */
	private java.lang.Integer uncollectedTotal;
	
	/**
	 * 赠品集合
	 */
	List<LvActivityGift> giftList;
	
	
	public String getProdNo() {
		return prodNo;
	}
	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public int getActivityType() {
		return activityType;
	}
	public void setActivityType(int activityType) {
		this.activityType = activityType;
	}
	public long getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(long beginTime) {
		this.beginTime = beginTime;
	}
	public long getEndTime() {
		return endTime;
	}
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	public long getCurTime() {
		return curTime;
	}
	public void setCurTime(long curTime) {
		this.curTime = curTime;
	}
	public int getLimitCount() {
		return limitCount;
	}
	public void setLimitCount(int limitCount) {
		this.limitCount = limitCount;
	}
	public String getOrignPrice() {
		return orignPrice;
	}
	public void setOrignPrice(String orignPrice) {
		this.orignPrice = orignPrice;
	}
	public String getActivityPrice() {
		return activityPrice;
	}
	public void setActivityPrice(String activityPrice) {
		this.activityPrice = activityPrice;
	}
	public int getProdStatus() {
		return prodStatus;
	}
	public void setProdStatus(int prodStatus) {
		this.prodStatus = prodStatus;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getActivityNo() {
		return activityNo;
	}
	public void setActivityNo(String activityNo) {
		this.activityNo = activityNo;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getOrderActivityTitle() {
		return orderActivityTitle;
	}
	public void setOrderActivityTitle(String orderActivityTitle) {
		this.orderActivityTitle = orderActivityTitle;
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
	public Short getIsUseCoupon() {
		return isUseCoupon;
	}
	public void setIsUseCoupon(Short isUseCoupon) {
		this.isUseCoupon = isUseCoupon;
	}
	public List<LvActivityGift> getGiftList() {
		return giftList;
	}
	public void setGiftList(List<LvActivityGift> giftList) {
		this.giftList = giftList;
	}
	public java.lang.Integer getUncollectedTotal() {
		return uncollectedTotal;
	}
	public void setUncollectedTotal(java.lang.Integer uncollectedTotal) {
		this.uncollectedTotal = uncollectedTotal;
	}
	public String getActivityNameEn() {
		return activityNameEn;
	}
	public void setActivityNameEn(String activityNameEn) {
		this.activityNameEn = activityNameEn;
	}
	
	
	
	
}
