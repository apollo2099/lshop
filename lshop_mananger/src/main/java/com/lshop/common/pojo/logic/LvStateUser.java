package com.lshop.common.pojo.logic;


import java.util.Date;

import com.gv.core.datastructure.Key;

/**
 * LvStateUser entity. @author MyEclipse Persistence Tools
 */

public class LvStateUser extends com.gv.core.datastructure.impl.BasePo
		implements java.io.Serializable {

	// Fields

	private Integer id;
	private String userEmail;
	private String nickName;
	private Integer countPay;
	private Integer countUnPay;
	private Integer countAll;
	private Float totalPrice;
	private Date registerTime;
	private Long firstdDay;
	private Long secondDay;
	private String storeId;
	private String code;
	private Integer orderId;
	private Date createTime;
	private Date modifyTime;
	private Integer modifyUserId;
	private String modifyUserName;
	private String atrr;
	private String atrr1;
	private String atrr2;
	private String atrr3;

	
    //实体扩展属性
    private String startTime;//下单开始时间
	private String endTime;  //下单结束时间

	// Constructors

	/** default constructor */
	public LvStateUser() {
	}

	/** full constructor */
	public LvStateUser(String userEmail, String nickName, Integer countPay,
			Integer countUnPay, Integer countAll, Float totalPrice,
			Date registerTime, String storeId, String code,
			Integer orderId, Date createTime, Date modifyTime,
			Integer modifyUserId, String modifyUserName,Long firstdDay,Long secondDay
			,String atrr,String atrr1, String atrr2, String atrr3) {
		this.userEmail = userEmail;
		this.nickName = nickName;
		this.countPay = countPay;
		this.countUnPay = countUnPay;
		this.countAll = countAll;
		this.totalPrice = totalPrice;
		this.registerTime = registerTime;
		this.storeId = storeId;
		this.code = code;
		this.orderId = orderId;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.modifyUserId = modifyUserId;
		this.modifyUserName = modifyUserName;
		this.firstdDay=firstdDay;
		this.secondDay=secondDay;
		this.atrr = atrr;
		this.atrr1 = atrr1;
		this.atrr2 = atrr2;
		this.atrr3 = atrr3;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getCountPay() {
		return this.countPay;
	}

	public void setCountPay(Integer countPay) {
		this.countPay = countPay;
	}

	public Integer getCountUnPay() {
		return this.countUnPay;
	}

	public void setCountUnPay(Integer countUnPay) {
		this.countUnPay = countUnPay;
	}

	public Integer getCountAll() {
		return this.countAll;
	}

	public void setCountAll(Integer countAll) {
		this.countAll = countAll;
	}

	public Float getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getRegisterTime() {
		return this.registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public String getStoreId() {
		return this.storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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

	public String getAtrr() {
		return this.atrr;
	}

	public void setAtrr(String atrr) {
		this.atrr = atrr;
	}

	public String getAtrr1() {
		return this.atrr1;
	}

	public void setAtrr1(String atrr1) {
		this.atrr1 = atrr1;
	}

	public String getAtrr2() {
		return this.atrr2;
	}

	public void setAtrr2(String atrr2) {
		this.atrr2 = atrr2;
	}

	public String getAtrr3() {
		return this.atrr3;
	}

	public void setAtrr3(String atrr3) {
		this.atrr3 = atrr3;
	}
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Long getFirstdDay() {
		return firstdDay;
	}

	public void setFirstdDay(Long firstdDay) {
		this.firstdDay = firstdDay;
	}

	public Long getSecondDay() {
		return secondDay;
	}

	public void setSecondDay(Long secondDay) {
		this.secondDay = secondDay;
	}
	
	@Override
	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}

}