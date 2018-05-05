package com.lshop.common.pojo.logic;


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
 */public class LvDeveloperApp extends BasePo implements java.io.Serializable{
	
	private static final long serialVersionUID = -8203417763895638242L;
	//date formats
	public static final String FORMAT_CREATE_TIME = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_UPDATE_TIME = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_REVIEW_TIME = "yyyy-MM-dd HH:mm:ss";
	
	/**
     * 定义私有属性get、set
     */
	
      //id       db_column: id 
    
	private java.lang.Integer id;
	
      //app名称       db_column: name 
    
	public java.lang.Integer getIsAgree() {
		return isAgree;
	}
	public void setIsAgree(java.lang.Integer isAgree) {
		this.isAgree = isAgree;
	}
	private java.lang.String name;
	
      //app访问url       db_column: url 
    
	private java.lang.String url;
	
      //版本号       db_column: app_version 
    
	private java.lang.String appVersion;
	
      //app语言       db_column: lang 
    
	private java.lang.String lang;
	
      //app分类       db_column: app_type 
    
	private java.lang.String appType;
	
      //关键字       db_column: key_word 
    
	private java.lang.String keyWord;
	
      //app描述       db_column: app_desc 
    
	private java.lang.String appDesc;
	
      //签署协议图片地址       db_column: proof_url 
    
	private java.lang.String proofUrl;
	
      //app例子截图(多张用|分隔)       db_column: demo_imgs_url 
    
	private java.lang.String demoImgsUrl;
	
      //是否收费 0不收费 1收费       db_column: is_charge 
    
	private java.lang.Integer isCharge;
	
      //是否有广告 0无广告 1有广告       db_column: is_ad 
    
	private java.lang.Integer isAd;
	
      //创建时间       db_column: create_time 
    
	private java.util.Date createTime;
	
      //更新时间       db_column: update_time 
    
	private java.util.Date updateTime;
	
      //审核时间       db_column: review_time 
    
	private java.util.Date reviewTime;
	
      //开发者邮箱       db_column: email 
    
	private java.lang.String email;
	
	private java.lang.String reason;
	
	private java.lang.String code;
	
	//是否同意提交应用协议
	
	private java.lang.Integer isAgree;

	//columns END
	private Integer status;
	
	
	private java.lang.String bizline;
	
	private java.lang.String storeFlag;
	
	private String[] demoImgs;//图片显示完整地址
	
	public String[] getDemoImgs() {
		return demoImgs;
	}
	public void setDemoImgs(String[] demoImgs) {
		this.demoImgs = demoImgs;
	}
	public java.lang.String getReason() {
		return reason;
	}
	public void setReason(java.lang.String reason) {
		this.reason = reason;
	}

	public java.lang.String getBizline() {
		return bizline;
	}
	public void setBizline(java.lang.String bizline) {
		this.bizline = bizline;
	}
	public java.lang.String getStoreFlag() {
		return storeFlag;
	}
	public void setStoreFlag(java.lang.String storeFlag) {
		this.storeFlag = storeFlag;
	}
	public LvDeveloperApp(){
	}
	public LvDeveloperApp(int id)
	{
		this.id = id;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * id       db_column: id 
	 */
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	/**
	 * id       db_column: id 
	 */
	public java.lang.Integer getId() {
		return this.id;
	}
	/**
	 * app名称       db_column: name 
	 */
	public void setName(java.lang.String value) {
		this.name = value;
	}
	/**
	 * app名称       db_column: name 
	 */
	public java.lang.String getName() {
		return this.name;
	}
	/**
	 * app访问url       db_column: url 
	 */
	public void setUrl(java.lang.String value) {
		this.url = value;
	}
	/**
	 * app访问url       db_column: url 
	 */
	public java.lang.String getUrl() {
		return this.url;
	}
	/**
	 * 版本号       db_column: app_version 
	 */
	public void setAppVersion(java.lang.String value) {
		this.appVersion = value;
	}
	/**
	 * 版本号       db_column: app_version 
	 */
	public java.lang.String getAppVersion() {
		return this.appVersion;
	}
	/**
	 * app语言       db_column: lang 
	 */
	public void setLang(java.lang.String value) {
		this.lang = value;
	}
	/**
	 * app语言       db_column: lang 
	 */
	public java.lang.String getLang() {
		return this.lang;
	}
	/**
	 * app分类       db_column: app_type 
	 */
	public void setAppType(java.lang.String value) {
		this.appType = value;
	}
	/**
	 * app分类       db_column: app_type 
	 */
	public java.lang.String getAppType() {
		return this.appType;
	}
	/**
	 * 关键字       db_column: key_word 
	 */
	public void setKeyWord(java.lang.String value) {
		this.keyWord = value;
	}
	/**
	 * 关键字       db_column: key_word 
	 */
	public java.lang.String getKeyWord() {
		return this.keyWord;
	}
	/**
	 * app描述       db_column: app_desc 
	 */
	public void setAppDesc(java.lang.String value) {
		this.appDesc = value;
	}
	/**
	 * app描述       db_column: app_desc 
	 */
	public java.lang.String getAppDesc() {
		return this.appDesc;
	}
	/**
	 * 签署协议图片地址       db_column: proof_url 
	 */
	public void setProofUrl(java.lang.String value) {
		this.proofUrl = value;
	}
	/**
	 * 签署协议图片地址       db_column: proof_url 
	 */
	public java.lang.String getProofUrl() {
		return this.proofUrl;
	}
	/**
	 * app例子截图(多张用|分隔)       db_column: demo_imgs_url 
	 */
	public void setDemoImgsUrl(java.lang.String value) {
		this.demoImgsUrl = value;
	}
	/**
	 * app例子截图(多张用|分隔)       db_column: demo_imgs_url 
	 */
	public java.lang.String getDemoImgsUrl() {
		return this.demoImgsUrl;
	}
	/**
	 * 是否收费 0不收费 1收费       db_column: is_charge 
	 */
	public void setIsCharge(java.lang.Integer value) {
		this.isCharge = value;
	}
	/**
	 * 是否收费 0不收费 1收费       db_column: is_charge 
	 */
	public java.lang.Integer getIsCharge() {
		return this.isCharge;
	}
	/**
	 * 是否有广告 0无广告 1有广告       db_column: is_ad 
	 */
	public void setIsAd(java.lang.Integer value) {
		this.isAd = value;
	}
	/**
	 * 是否有广告 0无广告 1有广告       db_column: is_ad 
	 */
	public java.lang.Integer getIsAd() {
		return this.isAd;
	}
	/**
	 * 创建时间       db_column: create_time 
	 */
	public String getCreateTimeString() {
		return DateConvertUtils.format(getCreateTime(), FORMAT_CREATE_TIME);
	}
	/**
	 * 创建时间       db_column: create_time 
	 */
	public void setCreateTimeString(String value) {
		setCreateTime(DateConvertUtils.parse(value, FORMAT_CREATE_TIME,java.util.Date.class));
	}
	
	/**
	 * 创建时间       db_column: create_time 
	 */
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	/**
	 * 创建时间       db_column: create_time 
	 */
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	/**
	 * 更新时间       db_column: update_time 
	 */
	public String getUpdateTimeString() {
		return DateConvertUtils.format(getUpdateTime(), FORMAT_UPDATE_TIME);
	}
	/**
	 * 更新时间       db_column: update_time 
	 */
	public void setUpdateTimeString(String value) {
		setUpdateTime(DateConvertUtils.parse(value, FORMAT_UPDATE_TIME,java.util.Date.class));
	}
	
	/**
	 * 更新时间       db_column: update_time 
	 */
	public void setUpdateTime(java.util.Date value) {
		this.updateTime = value;
	}
	/**
	 * 更新时间       db_column: update_time 
	 */
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}
	/**
	 * 审核时间       db_column: review_time 
	 */
	public String getReviewTimeString() {
		return DateConvertUtils.format(getReviewTime(), FORMAT_REVIEW_TIME);
	}
	/**
	 * 审核时间       db_column: review_time 
	 */
	public void setReviewTimeString(String value) {
		setReviewTime(DateConvertUtils.parse(value, FORMAT_REVIEW_TIME,java.util.Date.class));
	}
	
	/**
	 * 审核时间       db_column: review_time 
	 */
	public void setReviewTime(java.util.Date value) {
		this.reviewTime = value;
	}
	/**
	 * 审核时间       db_column: review_time 
	 */
	public java.util.Date getReviewTime() {
		return this.reviewTime;
	}
	/**
	 * 开发者邮箱       db_column: email 
	 */
	public void setEmail(java.lang.String value) {
		this.email = value;
	}
	/**
	 * 开发者邮箱       db_column: email 
	 */
	public java.lang.String getEmail() {
		return this.email;
	}

	public java.lang.String getCode() {
		return code;
	}
	public void setCode(java.lang.String code) {
		this.code = code;
	}
	
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Name",getName())
			.append("Url",getUrl())
			.append("AppVersion",getAppVersion())
			.append("Lang",getLang())
			.append("AppType",getAppType())
			.append("KeyWord",getKeyWord())
			.append("AppDesc",getAppDesc())
			.append("ProofUrl",getProofUrl())
			.append("DemoImgsUrl",getDemoImgsUrl())
			.append("IsCharge",getIsCharge())
			.append("IsAd",getIsAd())
			.append("CreateTime",getCreateTime())
			.append("UpdateTime",getUpdateTime())
			.append("ReviewTime",getReviewTime())
			.append("Email",getEmail())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof LvDeveloperApp == false) return false;
		if(this == obj) return true;
		LvDeveloperApp other = (LvDeveloperApp)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	@Override
	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}
}

