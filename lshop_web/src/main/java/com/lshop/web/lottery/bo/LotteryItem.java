package com.lshop.web.lottery.bo;

import java.io.Serializable;

/**
 * 抽奖奖品项
 * @author caicl
 *
 */
public class LotteryItem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1957098298970133621L;
	private String lotteryCode;
	/**
	 * 奖项号
	 */
	private String itemCode;
	private String name;
	private String imgUrl;
	private Integer prizeType;
	/**
	 * 剩余奖品数
	 */
	private Integer restNum;
	/**
	 * 已经中奖数
	 */
	private Integer useNum;
	/**
	 * 系统奖品业务编号
	 */
	private String prizeCode;
	/**
	 * 是否中奖
	 */
	private boolean win;
	public String getLotteryCode() {
		return lotteryCode;
	}
	public void setLotteryCode(String lotteryCode) {
		this.lotteryCode = lotteryCode;
	}
	public String getItemCode() {
		return itemCode;
	}
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public Integer getPrizeType() {
		return prizeType;
	}
	public void setPrizeType(Integer prizeType) {
		this.prizeType = prizeType;
	}
	public Integer getRestNum() {
		return restNum;
	}
	public void setRestNum(Integer restNum) {
		this.restNum = restNum;
	}
	public Integer getUseNum() {
		return useNum;
	}
	public void setUseNum(Integer useNum) {
		this.useNum = useNum;
	}
	public String getPrizeCode() {
		return prizeCode;
	}
	public void setPrizeCode(String prizeCode) {
		this.prizeCode = prizeCode;
	}
	public boolean isWin() {
		return win;
	}
	public void setWin(boolean win) {
		this.win = win;
	}
	@Override
	public String toString() {
		return "LotteryItem [lotteryCode=" + lotteryCode + ", itemCode="
				+ itemCode + ", name=" + name + ", imgUrl=" + imgUrl
				+ ", prizeType=" + prizeType + ", prizeCode=" + prizeCode + "]";
	}
}
