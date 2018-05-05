package com.lshop.common.pojo.logic;



import java.util.Date;

import com.gv.core.datastructure.Key;

/**
 * LvLotteryPrize entity. @author MyEclipse Persistence Tools
 */

public class LvLotteryPrize extends com.gv.core.datastructure.impl.BasePo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String activityCode;
	private String prizeName;
	private String prizeCode;
	private Short prizeType;
	private String prizeImages;
	private Integer prizeTotal;
	private Integer grantTotal;
	private Short isDraw;
	private Short sortId;
	private String code;
	private Date createTime;
	private Date modifyTime;
	private Integer modifyUserId;
	private String modifyUserName;

	// Constructors
	private String givenProductCode;
	private String givenTypeName;
	private Integer addNumber;



	/** default constructor */
	public LvLotteryPrize() {
	}

	/** full constructor */
	public LvLotteryPrize(String activityCode, String prizeName,
			String prizeCode, Short prizeType, String prizeImages,
			Integer prizeTotal, Integer grantTotal, Short isDraw, String sortId,String code,
			Date createTime, Date modifyTime, Integer modifyUserId,
			String modifyUserName) {
		this.activityCode = activityCode;
		this.prizeName = prizeName;
		this.prizeCode = prizeCode;
		this.prizeType = prizeType;
		this.prizeImages = prizeImages;
		this.prizeTotal = prizeTotal;
		this.grantTotal = grantTotal;
		this.isDraw = isDraw;
		this.code = code;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.modifyUserId = modifyUserId;
		this.modifyUserName = modifyUserName;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getActivityCode() {
		return this.activityCode;
	}

	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}

	public String getPrizeName() {
		return this.prizeName;
	}

	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}

	public String getPrizeCode() {
		return this.prizeCode;
	}

	public void setPrizeCode(String prizeCode) {
		this.prizeCode = prizeCode;
	}

	public Short getPrizeType() {
		return this.prizeType;
	}

	public void setPrizeType(Short prizeType) {
		this.prizeType = prizeType;
	}

	public String getPrizeImages() {
		return this.prizeImages;
	}

	public void setPrizeImages(String prizeImages) {
		this.prizeImages = prizeImages;
	}

	public Integer getPrizeTotal() {
		return this.prizeTotal;
	}

	public void setPrizeTotal(Integer prizeTotal) {
		this.prizeTotal = prizeTotal;
	}

	public Integer getGrantTotal() {
		return this.grantTotal;
	}

	public void setGrantTotal(Integer grantTotal) {
		this.grantTotal = grantTotal;
	}

	public Short getIsDraw() {
		return this.isDraw;
	}

	public void setIsDraw(Short isDraw) {
		this.isDraw = isDraw;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Integer getModifyUserId() {
		return this.modifyUserId;
	}

	public void setModifyUserId(Integer modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public String getModifyUserName() {
		return this.modifyUserName;
	}

	public void setModifyUserName(String modifyUserName) {
		this.modifyUserName = modifyUserName;
	}

	
	public Integer getAddNumber() {
		return addNumber;
	}

	public void setAddNumber(Integer addNumber) {
		this.addNumber = addNumber;
	}

	public String getGivenProductCode() {
		return givenProductCode;
	}

	public void setGivenProductCode(String givenProductCode) {
		this.givenProductCode = givenProductCode;
	}

	public String getGivenTypeName() {
		return givenTypeName;
	}

	public void setGivenTypeName(String givenTypeName) {
		this.givenTypeName = givenTypeName;
	}

	public Short getSortId() {
		return sortId;
	}

	public void setSortId(Short sortId) {
		this.sortId = sortId;
	}

	@Override
	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}

}