package com.lshop.web.userCenter.pojo;

import java.io.Serializable;

import com.gv.business.ws.boss.userinfo.AnnualSumDto;


public class BossUser implements Serializable {
	private int status;
	private String msg;
	private String accountno;
	private Integer userid;
	private String registerdate;
	private Integer userstatus;
	private Integer activestatus;
	private Integer balance;
	private AnnualSumDto annualsum;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getAccountno() {
		return accountno;
	}
	public void setAccountno(String accountno) {
		this.accountno = accountno;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getRegisterdate() {
		return registerdate;
	}
	public void setRegisterdate(String registerdate) {
		this.registerdate = registerdate;
	}
	public Integer getUserstatus() {
		return userstatus;
	}
	public void setUserstatus(Integer userstatus) {
		this.userstatus = userstatus;
	}
	public Integer getActivestatus() {
		return activestatus;
	}
	public void setActivestatus(Integer activestatus) {
		this.activestatus = activestatus;
	}
	public Integer getBalance() {
		return balance;
	}
	public void setBalance(Integer balance) {
		this.balance = balance;
	}
	public AnnualSumDto getAnnualsum() {
		return annualsum;
	}
	public void setAnnualsum(AnnualSumDto annualsum) {
		this.annualsum = annualsum;
	}
	
}
