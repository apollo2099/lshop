package com.gv.appstore.pojo;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.gv.core.datastructure.Key;
import com.gv.core.datastructure.impl.BasePo;
import com.gv.core.util.DateConvertUtils;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */
public class LvDeveloper extends BasePo implements java.io.Serializable {
	private static final long serialVersionUID = 5454155825314635342L;

	// date formats
	public static final String FORMAT_CREATE_TIME = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_REVIEW_TIME = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_UPDATE_TIME = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 定义私有属性get、set
	 */

	// id db_column: id

	private java.lang.Integer id;

	// 开发者邮箱 db_column: email

	private java.lang.String email;

	// 联系人名称（姓名） db_column: contact_name

	private java.lang.String contactName;

	// 公司名称 db_column: company

	private java.lang.String company;

	// 开发者状态(0.待审核 1.审核通过 2不通过) db_column: d_status

	private java.lang.Integer dstatus;

	// 创建时间 db_column: create_time

	private java.util.Date createTime;

	// 审核时间 db_column: review_time

	private java.util.Date reviewTime;

	// 更新时间 db_column: update_time

	private java.util.Date updateTime;

	// 开发者类型 0个人 1企业 db_column: d_type

	private java.lang.Integer dtype;

	// 身份证/营业执照 号码 db_column: id_num

	private java.lang.String idNum;

	// 身份证/营业执照 图片url地址 db_column: id_url

	private java.sql.Blob idUrl;

	// 联系电话 db_column: tel

	private java.lang.String tel;

	// 公司地址 db_column: address

	private java.lang.String address;

	// 协议截图
	private String agreeImgsUrl;

	// 不通过原因
	private String reason;

	private String code;

	// 业务线
	private String bizline;

	// 店铺标识
	private String storeFlag;
	//是否同意注册开发者协议（0 不同意 1同意）
	private java.lang.Integer isAgree;

	// columns END

	public LvDeveloper() {
	}

	public LvDeveloper(int id) {
		this.id = id;
	}

	/**
	 * id db_column: id
	 */
	public void setId(java.lang.Integer value) {
		this.id = value;
	}

	/**
	 * id db_column: id
	 */
	public java.lang.Integer getId() {
		return this.id;
	}

	/**
	 * 开发者邮箱 db_column: email
	 */
	public void setEmail(java.lang.String value) {
		this.email = value;
	}

	/**
	 * 开发者邮箱 db_column: email
	 */
	public java.lang.String getEmail() {
		return this.email;
	}

	/**
	 * 联系人名称（姓名） db_column: contact_name
	 */
	public void setContactName(java.lang.String value) {
		this.contactName = value;
	}

	/**
	 * 联系人名称（姓名） db_column: contact_name
	 */
	public java.lang.String getContactName() {
		return this.contactName;
	}

	/**
	 * 公司名称 db_column: company
	 */
	public void setCompany(java.lang.String value) {
		this.company = value;
	}

	/**
	 * 公司名称 db_column: company
	 */
	public java.lang.String getCompany() {
		return this.company;
	}

	/**
	 * 开发者状态(0.待审核 1.审核通过 2不通过) db_column: d_status
	 */
	public void setDstatus(java.lang.Integer value) {
		this.dstatus = value;
	}

	/**
	 * 开发者状态(0.待审核 1.审核通过 2不通过) db_column: d_status
	 */
	public java.lang.Integer getDstatus() {
		return this.dstatus;
	}

	/**
	 * 创建时间 db_column: create_time
	 */
	public String getCreateTimeString() {
		return DateConvertUtils.format(getCreateTime(), FORMAT_CREATE_TIME);
	}

	/**
	 * 创建时间 db_column: create_time
	 */
	public void setCreateTimeString(String value) {
		setCreateTime(DateConvertUtils.parse(value, FORMAT_CREATE_TIME,
				java.util.Date.class));
	}

	/**
	 * 创建时间 db_column: create_time
	 */
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}

	/**
	 * 创建时间 db_column: create_time
	 */
	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * 审核时间 db_column: review_time
	 */
	public String getReviewTimeString() {
		return DateConvertUtils.format(getReviewTime(), FORMAT_REVIEW_TIME);
	}

	/**
	 * 审核时间 db_column: review_time
	 */
	public void setReviewTimeString(String value) {
		setReviewTime(DateConvertUtils.parse(value, FORMAT_REVIEW_TIME,
				java.util.Date.class));
	}

	/**
	 * 审核时间 db_column: review_time
	 */
	public void setReviewTime(java.util.Date value) {
		this.reviewTime = value;
	}

	/**
	 * 审核时间 db_column: review_time
	 */
	public java.util.Date getReviewTime() {
		return this.reviewTime;
	}

	/**
	 * 更新时间 db_column: update_time
	 */
	public String getUpdateTimeString() {
		return DateConvertUtils.format(getUpdateTime(), FORMAT_UPDATE_TIME);
	}

	/**
	 * 更新时间 db_column: update_time
	 */
	public void setUpdateTimeString(String value) {
		setUpdateTime(DateConvertUtils.parse(value, FORMAT_UPDATE_TIME,
				java.util.Date.class));
	}

	/**
	 * 更新时间 db_column: update_time
	 */
	public void setUpdateTime(java.util.Date value) {
		this.updateTime = value;
	}

	/**
	 * 更新时间 db_column: update_time
	 */
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * 开发者类型 0个人 1企业 db_column: d_type
	 */
	public void setDtype(java.lang.Integer value) {
		this.dtype = value;
	}

	/**
	 * 开发者类型 0个人 1企业 db_column: d_type
	 */
	public java.lang.Integer getDtype() {
		return this.dtype;
	}

	/**
	 * 身份证/营业执照 号码 db_column: id_num
	 */
	public void setIdNum(java.lang.String value) {
		this.idNum = value;
	}

	/**
	 * 身份证/营业执照 号码 db_column: id_num
	 */
	public java.lang.String getIdNum() {
		return this.idNum;
	}

	/**
	 * 身份证/营业执照 图片url地址 db_column: id_url
	 */
	public void setIdUrl(java.sql.Blob value) {
		this.idUrl = value;
	}

	/**
	 * 身份证/营业执照 图片url地址 db_column: id_url
	 */
	public java.sql.Blob getIdUrl() {
		return this.idUrl;
	}

	/**
	 * 联系电话 db_column: tel
	 */
	public void setTel(java.lang.String value) {
		this.tel = value;
	}

	/**
	 * 联系电话 db_column: tel
	 */
	public java.lang.String getTel() {
		return this.tel;
	}

	/**
	 * 公司地址 db_column: address
	 */
	public void setAddress(java.lang.String value) {
		this.address = value;
	}

	/**
	 * 公司地址 db_column: address
	 */
	public java.lang.String getAddress() {
		return this.address;
	}

	public String getAgreeImgsUrl() {
		return agreeImgsUrl;
	}

	public void setAgreeImgsUrl(String agreeImgsUrl) {
		this.agreeImgsUrl = agreeImgsUrl;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getBizline() {
		return bizline;
	}

	public void setBizline(String bizline) {
		this.bizline = bizline;
	}

	public String getStoreFlag() {
		return storeFlag;
	}

	public void setStoreFlag(String storeFlag) {
		this.storeFlag = storeFlag;
	}

	public java.lang.Integer getIsAgree() {
		return isAgree;
	}

	public void setIsAgree(java.lang.Integer isAgree) {
		this.isAgree = isAgree;
	}

	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("Id", getId()).append("Email", getEmail())
				.append("ContactName", getContactName())
				.append("Company", getCompany())
				.append("Dstatus", getDstatus())
				.append("CreateTime", getCreateTime())
				.append("ReviewTime", getReviewTime())
				.append("UpdateTime", getUpdateTime())
				.append("Dtype", getDtype()).append("IdNum", getIdNum())
				.append("IdUrl", getIdUrl()).append("Tel", getTel())
				.append("Address", getAddress()).toString();
	}

	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

	public boolean equals(Object obj) {
		if (obj instanceof LvDeveloper == false)
			return false;
		if (this == obj)
			return true;
		LvDeveloper other = (LvDeveloper) obj;
		return new EqualsBuilder().append(getId(), other.getId()).isEquals();
	}

	@Override
	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}
}
