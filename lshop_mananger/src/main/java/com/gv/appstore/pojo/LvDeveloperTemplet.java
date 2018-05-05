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
 */public class LvDeveloperTemplet extends BasePo implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//date formats
	public static final String FORMAT_CREATE_TIME = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_UPDATE_TIME = "yyyy-MM-dd HH:mm:ss";
	
	/**
     * 定义私有属性get、set
     */
	
      //id       db_column: id 
    
	private java.lang.Integer id;
	
      //模板名称       db_column: name 
    
	private java.lang.String name;
	
      //邮件标题       db_column: title 
    
	private java.lang.String title;
	
      //邮件内容       db_column: content 
    
	private java.lang.String content;
	
      //商城语言标识       db_column: mall_sign 
    
	private java.lang.String mallSign;
	
      //邮件类型1开发者通过 2开发者不通过 3应用通过 4应用不通过       db_column: t_type 
    
	private java.lang.Integer ttype;
	
      //创建时间       db_column: create_time 
    
	private java.util.Date createTime;
	
      //修改时间       db_column: update_time 
    
	private java.util.Date updateTime;
	
      //操作人名称       db_column: update_user 
    
	private java.lang.String updateUser;
	
	//状态-1 删除  1正常
	private java.lang.Integer status;
	//columns END

	public LvDeveloperTemplet(){
	}
	public LvDeveloperTemplet(int id)
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
	 * 模板名称       db_column: name 
	 */
	public void setName(java.lang.String value) {
		this.name = value;
	}
	/**
	 * 模板名称       db_column: name 
	 */
	public java.lang.String getName() {
		return this.name;
	}
	/**
	 * 邮件标题       db_column: title 
	 */
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	/**
	 * 邮件标题       db_column: title 
	 */
	public java.lang.String getTitle() {
		return this.title;
	}
	/**
	 * 邮件内容       db_column: content 
	 */
	public void setContent(java.lang.String value) {
		this.content = value;
	}
	/**
	 * 邮件内容       db_column: content 
	 */
	public java.lang.String getContent() {
		return this.content;
	}
	/**
	 * 商城语言标识       db_column: mall_sign 
	 */
	public void setMallSign(java.lang.String value) {
		this.mallSign = value;
	}
	/**
	 * 商城语言标识       db_column: mall_sign 
	 */
	public java.lang.String getMallSign() {
		return this.mallSign;
	}
	/**
	 * 邮件类型1开发者通过 2开发者不通过 3应用通过 4应用不通过       db_column: t_type 
	 */
	public void setTtype(java.lang.Integer value) {
		this.ttype = value;
	}
	/**
	 * 邮件类型1开发者通过 2开发者不通过 3应用通过 4应用不通过       db_column: t_type 
	 */
	public java.lang.Integer getTtype() {
		return this.ttype;
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
	 * 修改时间       db_column: update_time 
	 */
	public String getUpdateTimeString() {
		return DateConvertUtils.format(getUpdateTime(), FORMAT_UPDATE_TIME);
	}
	/**
	 * 修改时间       db_column: update_time 
	 */
	public void setUpdateTimeString(String value) {
		setUpdateTime(DateConvertUtils.parse(value, FORMAT_UPDATE_TIME,java.util.Date.class));
	}
	
	/**
	 * 修改时间       db_column: update_time 
	 */
	public void setUpdateTime(java.util.Date value) {
		this.updateTime = value;
	}
	/**
	 * 修改时间       db_column: update_time 
	 */
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}
	/**
	 * 操作人名称       db_column: update_user 
	 */
	public void setUpdateUser(java.lang.String value) {
		this.updateUser = value;
	}
	/**
	 * 操作人名称       db_column: update_user 
	 */
	public java.lang.String getUpdateUser() {
		return this.updateUser;
	}

	public java.lang.Integer getStatus() {
		return status;
	}
	public void setStatus(java.lang.Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "LvDeveloperTemplet [id=" + id + ", name=" + name + ", title="
				+ title + ", content=" + content + ", mallSign=" + mallSign
				+ ", ttype=" + ttype + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", updateUser=" + updateUser
				+ ", status=" + status + "]";
	}
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof LvDeveloperTemplet == false) return false;
		if(this == obj) return true;
		LvDeveloperTemplet other = (LvDeveloperTemplet)obj;
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

