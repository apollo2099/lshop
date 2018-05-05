package com.lshop.common.pojo.user;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.lshop.common.util.DateUtil;

public class UnifiedUser {
	private String code;
	private String account;
	private String password;
	private String sex;
	private String birthday;
	private String nickname;
	private String address;
	private String tel;
	private String countryCode;
	private String country;
	private String province;
	private String city;
	private String status;
	private String remark;
	private String pic;
	private String createTime;	// 时间格式字符串
	private String updateTime;	// 时间戳
	private String loginTime;	// 时间格式字符串
	private String qq;
	private String msn;
	private String name;
	private String phone;
	private String registerSource;
	
	//扩展属性
	private String createTimeBegin;//注册开始时间
	private String createTimeEnd;//注册结束时间
	
	
	////////////////////// add /////////////////////////////
	public Date getLoginTimeDate() {
		return DateUtil.parseDateTime(loginTime);
	}
	public Date getCreateTimeDate() {
		return DateUtil.parseDateTime(createTime);
	}
	public Date getUpdateTimeDate() {
		if (StringUtils.isBlank(updateTime)) {
			return null;
		}
		long time;
		try {
			time = Long.parseLong(updateTime);
		} catch(NumberFormatException e) {
			System.out.println("错误的时间戳格式，无法解析updateTime");
			return null;
		}
		return new Date(time);
	}
	
	///////////////////////////////////////////////////
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getMsn() {
		return msn;
	}
	public void setMsn(String msn) {
		this.msn = msn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRegisterSource() {
		return registerSource;
	}
	public void setRegisterSource(String registerSource) {
		this.registerSource = registerSource;
	}
	public String getCreateTimeBegin() {
		return createTimeBegin;
	}
	public void setCreateTimeBegin(String createTimeBegin) {
		this.createTimeBegin = createTimeBegin;
	}
	public String getCreateTimeEnd() {
		return createTimeEnd;
	}
	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}
	
	
}
