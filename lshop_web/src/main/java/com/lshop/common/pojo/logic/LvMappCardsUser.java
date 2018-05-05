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
 */public class LvMappCardsUser extends BasePo implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//date formats
	public static final String FORMAT_CREATE_TIME = "yyyy-MM-dd HH:mm:ss";
	
	/**
     * 定义私有属性get、set
     */
	
      //id       db_column: id 
    
	private java.lang.Integer id;
	
      //收件人       db_column: recipient 
    
	private java.lang.String recipient;
	
      //贺卡内容       db_column: c_desc 
    
	private java.lang.String cdesc;
	
      //code       db_column: code 
    
	private java.lang.String code;
	
      //发件人       db_column: sender 
    
	private java.lang.String sender;
	
      //模板code       db_column: temp_code 
    
	private java.lang.String tempCode;
	
      //飘落的类型(暂无用,叶子，花瓣等 )       db_column: fall_type 
    
	private java.lang.Integer fallType;
	
      //文字效果类型 暂无       db_column: text_type 
    
	private java.lang.Integer textType;
	
      //背景音乐类型       db_column: music_type 
    
	private java.lang.Integer musicType;
	
      //生成时间       db_column: create_time 
    
	private java.util.Date createTime;
	
      //合成图片路径       db_column: img_url 
    
	private java.lang.String imgUrl;
	//columns END

	public LvMappCardsUser(){
	}
	public LvMappCardsUser(int id)
	{
		this.id = id;
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
	 * 收件人       db_column: recipient 
	 */
	public void setRecipient(java.lang.String value) {
		this.recipient = value;
	}
	/**
	 * 收件人       db_column: recipient 
	 */
	public java.lang.String getRecipient() {
		return this.recipient;
	}
	/**
	 * 贺卡内容       db_column: c_desc 
	 */
	public void setCdesc(java.lang.String value) {
		this.cdesc = value;
	}
	/**
	 * 贺卡内容       db_column: c_desc 
	 */
	public java.lang.String getCdesc() {
		return this.cdesc;
	}
	/**
	 * code       db_column: code 
	 */
	public void setCode(java.lang.String value) {
		this.code = value;
	}
	/**
	 * code       db_column: code 
	 */
	public java.lang.String getCode() {
		return this.code;
	}
	/**
	 * 发件人       db_column: sender 
	 */
	public void setSender(java.lang.String value) {
		this.sender = value;
	}
	/**
	 * 发件人       db_column: sender 
	 */
	public java.lang.String getSender() {
		return this.sender;
	}
	/**
	 * 模板code       db_column: temp_code 
	 */
	public void setTempCode(java.lang.String value) {
		this.tempCode = value;
	}
	/**
	 * 模板code       db_column: temp_code 
	 */
	public java.lang.String getTempCode() {
		return this.tempCode;
	}
	/**
	 * 飘落的类型(暂无用,叶子，花瓣等 )       db_column: fall_type 
	 */
	public void setFallType(java.lang.Integer value) {
		this.fallType = value;
	}
	/**
	 * 飘落的类型(暂无用,叶子，花瓣等 )       db_column: fall_type 
	 */
	public java.lang.Integer getFallType() {
		return this.fallType;
	}
	/**
	 * 文字效果类型 暂无       db_column: text_type 
	 */
	public void setTextType(java.lang.Integer value) {
		this.textType = value;
	}
	/**
	 * 文字效果类型 暂无       db_column: text_type 
	 */
	public java.lang.Integer getTextType() {
		return this.textType;
	}
	/**
	 * 背景音乐类型       db_column: music_type 
	 */
	public void setMusicType(java.lang.Integer value) {
		this.musicType = value;
	}
	/**
	 * 背景音乐类型       db_column: music_type 
	 */
	public java.lang.Integer getMusicType() {
		return this.musicType;
	}
	/**
	 * 生成时间       db_column: create_time 
	 */
	public String getCreateTimeString() {
		return DateConvertUtils.format(getCreateTime(), FORMAT_CREATE_TIME);
	}
	/**
	 * 生成时间       db_column: create_time 
	 */
	public void setCreateTimeString(String value) {
		setCreateTime(DateConvertUtils.parse(value, FORMAT_CREATE_TIME,java.util.Date.class));
	}
	
	/**
	 * 生成时间       db_column: create_time 
	 */
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	/**
	 * 生成时间       db_column: create_time 
	 */
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
	/**
	 * 合成图片路径       db_column: img_url 
	 */
	public void setImgUrl(java.lang.String value) {
		this.imgUrl = value;
	}
	/**
	 * 合成图片路径       db_column: img_url 
	 */
	public java.lang.String getImgUrl() {
		return this.imgUrl;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Recipient",getRecipient())
			.append("Cdesc",getCdesc())
			.append("Code",getCode())
			.append("Sender",getSender())
			.append("TempCode",getTempCode())
			.append("FallType",getFallType())
			.append("TextType",getTextType())
			.append("MusicType",getMusicType())
			.append("CreateTime",getCreateTime())
			.append("ImgUrl",getImgUrl())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof LvMappCardsUser == false) return false;
		if(this == obj) return true;
		LvMappCardsUser other = (LvMappCardsUser)obj;
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

