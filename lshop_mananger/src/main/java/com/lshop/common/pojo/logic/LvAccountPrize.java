package com.lshop.common.pojo.logic;

import java.util.Date;
import com.gv.core.datastructure.Key;

/**
 * LvAccountPrize entity. @author MyEclipse Persistence Tools
 */

public class LvAccountPrize extends com.gv.core.datastructure.impl.BasePo implements java.io.Serializable {

	// Fields

	private Integer id;
	private String userCode;
	private String activityCode;
	private String prizeName;
	private String prizeRelCode;
	private String prizeCode;
	private Short prizeType;
	private Date winDate;
	private Date endTicketDate;
	private Date ticketDate;
	private Short status;
    private String code;
    private String mallFlag;
	
	// Constructors





	/** default constructor */
	public LvAccountPrize() {
	}

	/** full constructor */
	public LvAccountPrize(String userCode, String activityCode,
			String prizeName, String prizeRelCode, String prizeCode,
			Short prizeType, Date winDate, Date endTicketDate,
			Date ticketDate, Short status,String code) {
		this.userCode = userCode;
		this.activityCode = activityCode;
		this.prizeName = prizeName;
		this.prizeRelCode = prizeRelCode;
		this.prizeCode = prizeCode;
		this.prizeType = prizeType;
		this.winDate = winDate;
		this.endTicketDate = endTicketDate;
		this.ticketDate = ticketDate;
		this.status = status;
		this.code=code;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserCode() {
		return this.userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
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

	public String getPrizeRelCode() {
		return this.prizeRelCode;
	}

	public void setPrizeRelCode(String prizeRelCode) {
		this.prizeRelCode = prizeRelCode;
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

	public Date getWinDate() {
		return this.winDate;
	}

	public void setWinDate(Date winDate) {
		this.winDate = winDate;
	}

	public Date getEndTicketDate() {
		return this.endTicketDate;
	}

	public void setEndTicketDate(Date endTicketDate) {
		this.endTicketDate = endTicketDate;
	}

	public Date getTicketDate() {
		return this.ticketDate;
	}

	public void setTicketDate(Date ticketDate) {
		this.ticketDate = ticketDate;
	}

	public Short getStatus() {
		return this.status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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