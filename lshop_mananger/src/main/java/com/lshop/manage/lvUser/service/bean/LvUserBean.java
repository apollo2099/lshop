package com.lshop.manage.lvUser.service.bean;

import java.util.Date;

public class LvUserBean {
	// Fields
	private Integer id;
	private String email;
	private String nickname;
	private Date createTime;
	private Date lasttime;
	private Long pnums;//购买的总台数
	private int notPlayNum;//未支付订单数
	private Long rplayNum;//重复购买订单数;
	private Long onPlayNum;//已支付订单数;
	
	public Long getRplayNum() {
		return rplayNum;
	}

	public void setRplayNum(Long rplayNum) {
		this.rplayNum = rplayNum;
	}

	// Property accessors


	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return this.nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLasttime() {
		return this.lasttime;
	}

	public void setLasttime(Date lasttime) {
		this.lasttime = lasttime;
	}

	public int getNotPlayNum() {
		return notPlayNum;
	}

	public void setNotPlayNum(int notPlayNum) {
		this.notPlayNum = notPlayNum;
	}

	public Long getPnums() {
		return pnums;
	}

	public Long getOnPlayNum() {
		return onPlayNum;
	}

	public void setPnums(Long pnums) {
		this.pnums = pnums;
	}

	public void setOnPlayNum(Long onPlayNum) {
		this.onPlayNum = onPlayNum;
	}


}
