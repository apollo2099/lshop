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
 */public class LvMappCardsTmp extends BasePo implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//date formats
	public static final String FORMAT_CREATE_TIME = "yyyy-MM-dd HH:mm:ss";
	
	/**
     * 定义私有属性get、set
     */
	
      //id       db_column: id 
    
	private java.lang.Integer id;
	
      //模板名称       db_column: name 
    
	private java.lang.String name;
	
      //生成日期       db_column: create_time 
    
	private java.util.Date createTime;
	
      //code       db_column: code 
    
	private java.lang.String code;
	
      //css样式名称       db_column: css_name 
    
	private java.lang.String cssName;
	
	private java.lang.Integer status;
	
	private java.lang.String title;
	//columns END

	public LvMappCardsTmp(){}

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
	 * 生成日期       db_column: create_time 
	 */
	public String getCreateTimeString() {
		return DateConvertUtils.format(getCreateTime(), FORMAT_CREATE_TIME);
	}
	/**
	 * 生成日期       db_column: create_time 
	 */
	public void setCreateTimeString(String value) {
		setCreateTime(DateConvertUtils.parse(value, FORMAT_CREATE_TIME,java.util.Date.class));
	}
	
	/**
	 * 生成日期       db_column: create_time 
	 */
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	/**
	 * 生成日期       db_column: create_time 
	 */
	public java.util.Date getCreateTime() {
		return this.createTime;
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
	 * css样式名称       db_column: css_name 
	 */
	public void setCssName(java.lang.String value) {
		this.cssName = value;
	}
	/**
	 * css样式名称       db_column: css_name 
	 */
	public java.lang.String getCssName() {
		return this.cssName;
	}
	

	public java.lang.Integer getStatus() {
		return status;
	}

	public void setStatus(java.lang.Integer status) {
		this.status = status;
	}

	public java.lang.String getTitle() {
		return title;
	}

	public void setTitle(java.lang.String title) {
		this.title = title;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof LvMappCardsTmp == false) return false;
		if(this == obj) return true;
		LvMappCardsTmp other = (LvMappCardsTmp)obj;
		return new EqualsBuilder()
			.isEquals();
	}
	@Override
	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}
}

