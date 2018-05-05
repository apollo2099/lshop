package com.lshop.common.pojo.logic;



import java.util.Date;

import com.gv.core.datastructure.Key;

/**
 * LvActivityShare entity. @author MyEclipse Persistence Tools
 */

public class LvActivityShare extends com.gv.core.datastructure.impl.BasePo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String activityCode;
	private String givenTypeName;
	private String givenProductCode;
	private Integer grantTotal;
	private Integer uncollectedTotal;
	private Short givenTypeNum;
	private Integer lotteryTotal;
	private Integer partakeNum;
	private String shareImage;
	private String shareDesc;
	private String shareLink;
	private String code;
	private Date createTime;
	private Date modifyTime;
	private Integer modifyUserId;
	private String modifyUserName;
    private String mallFlag;
	


	// Constructors
	private String acLotteryCode;
	private String acLotteryName;
    private Integer addTotal;
	


	/** default constructor */
	public LvActivityShare() {
	}

	/** full constructor */
	public LvActivityShare(String activityCode,String givenTypeName, String givenProductCode,
			Integer grantTotal, Integer uncollectedTotal, Short givenTypeNum,
			Integer lotteryTotal, Integer partakeNum, String shareImage,
			String shareDesc, String shareLink,String code, Date createTime,
			Date modifyTime, Integer modifyUserId, String modifyUserName) {
		this.activityCode=activityCode;
		this.givenTypeName = givenTypeName;
		this.givenProductCode = givenProductCode;
		this.grantTotal = grantTotal;
		this.uncollectedTotal = uncollectedTotal;
		this.givenTypeNum = givenTypeNum;
		this.lotteryTotal = lotteryTotal;
		this.partakeNum = partakeNum;
		this.shareImage = shareImage;
		this.shareDesc = shareDesc;
		this.shareLink= shareLink;
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
		return activityCode;
	}

	public void setActivityCode(String activityCode) {
		this.activityCode = activityCode;
	}
	
	public String getGivenTypeName() {
		return this.givenTypeName;
	}

	public void setGivenTypeName(String givenTypeName) {
		this.givenTypeName = givenTypeName;
	}

	public String getGivenProductCode() {
		return this.givenProductCode;
	}

	public void setGivenProductCode(String givenProductCode) {
		this.givenProductCode = givenProductCode;
	}

	public Integer getGrantTotal() {
		return this.grantTotal;
	}

	public void setGrantTotal(Integer grantTotal) {
		this.grantTotal = grantTotal;
	}

	public Integer getUncollectedTotal() {
		return this.uncollectedTotal;
	}

	public void setUncollectedTotal(Integer uncollectedTotal) {
		this.uncollectedTotal = uncollectedTotal;
	}

	public Short getGivenTypeNum() {
		return this.givenTypeNum;
	}

	public void setGivenTypeNum(Short givenTypeNum) {
		this.givenTypeNum = givenTypeNum;
	}

	public Integer getLotteryTotal() {
		return this.lotteryTotal;
	}

	public void setLotteryTotal(Integer lotteryTotal) {
		this.lotteryTotal = lotteryTotal;
	}

	public Integer getPartakeNum() {
		return this.partakeNum;
	}

	public void setPartakeNum(Integer partakeNum) {
		this.partakeNum = partakeNum;
	}

	public String getShareImage() {
		return this.shareImage;
	}

	public void setShareImage(String shareImage) {
		this.shareImage = shareImage;
	}

	public String getShareDesc() {
		return this.shareDesc;
	}

	public void setShareDesc(String shareDesc) {
		this.shareDesc = shareDesc;
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

	public Integer getAddTotal() {
		return addTotal;
	}

	public void setAddTotal(Integer addTotal) {
		this.addTotal = addTotal;
	}

	public String getAcLotteryCode() {
		return acLotteryCode;
	}

	public void setAcLotteryCode(String acLotteryCode) {
		this.acLotteryCode = acLotteryCode;
	}

	public String getAcLotteryName() {
		return acLotteryName;
	}

	public void setAcLotteryName(String acLotteryName) {
		this.acLotteryName = acLotteryName;
	}

	public String getShareLink() {
		return shareLink;
	}

	public void setShareLink(String shareLink) {
		this.shareLink = shareLink;
	}

	public String getMallFlag() {
		return mallFlag;
	}

	public void setMallFlag(String mallFlag) {
		this.mallFlag = mallFlag;
	}
	@Override
	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}

}