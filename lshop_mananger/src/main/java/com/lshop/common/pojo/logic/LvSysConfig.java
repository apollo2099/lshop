package com.lshop.common.pojo.logic;

/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */


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
 */public class LvSysConfig extends BasePo implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//date formats
	public static final String FORMAT_CREATE_TIME = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_UPDATE_TIME = "yyyy-MM-dd HH:mm:ss";
	
	/**
     * 定义私有属性get、set
     */
	
      //id       db_column: id 
    
	private java.lang.Integer id;
	
      //code       db_column: code 
    
	private java.lang.String code;
	
      //配置项名称(同类型下不能出现相同名称)       db_column: name 
    
	private java.lang.String name;
	
      //配置值       db_column: val 
    
	private java.lang.String val;
	
      //配置描述       db_column: c_desc 
    
	private java.lang.String cdesc;
	
      //配置类型 0公共配置 1为tvpad项目 2为banana项目       db_column: type 
    
	private java.lang.Integer type;
	
      //-1删除 1正常       db_column: status 
    
	private java.lang.Integer status;
	
      //生成时间       db_column: create_time 
    
	private java.util.Date createTime;
	
      //更新时间       db_column: update_time 
    
	private java.util.Date updateTime;
	
      //修改人code       db_column: modify_user_code 
    
	private java.lang.String modifyUserCode;
	
      //修改人名称       db_column: modify_user_name 
    
	private java.lang.String modifyUserName;
	//columns END

	public LvSysConfig(){
	}
	public LvSysConfig(int id)
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
	 * 配置项名称(同类型下不能出现相同名称)       db_column: name 
	 */
	public void setName(java.lang.String value) {
		this.name = value;
	}
	/**
	 * 配置项名称(同类型下不能出现相同名称)       db_column: name 
	 */
	public java.lang.String getName() {
		return this.name;
	}
	/**
	 * 配置值       db_column: val 
	 */
	public void setVal(java.lang.String value) {
		this.val = value;
	}
	/**
	 * 配置值       db_column: val 
	 */
	public java.lang.String getVal() {
		return this.val;
	}
	/**
	 * 配置描述       db_column: c_desc 
	 */
	public void setCdesc(java.lang.String value) {
		this.cdesc = value;
	}
	/**
	 * 配置描述       db_column: c_desc 
	 */
	public java.lang.String getCdesc() {
		return this.cdesc;
	}
	/**
	 * 配置类型 0公共配置 1为tvpad项目 2为banana项目       db_column: type 
	 */
	public void setType(java.lang.Integer value) {
		this.type = value;
	}
	/**
	 * 配置类型 0公共配置 1为tvpad项目 2为banana项目       db_column: type 
	 */
	public java.lang.Integer getType() {
		return this.type;
	}
	/**
	 * -1删除 1正常       db_column: status 
	 */
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	/**
	 * -1删除 1正常       db_column: status 
	 */
	public java.lang.Integer getStatus() {
		return this.status;
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
	 * 修改人code       db_column: modify_user_code 
	 */
	public void setModifyUserCode(java.lang.String value) {
		this.modifyUserCode = value;
	}
	/**
	 * 修改人code       db_column: modify_user_code 
	 */
	public java.lang.String getModifyUserCode() {
		return this.modifyUserCode;
	}
	/**
	 * 修改人名称       db_column: modify_user_name 
	 */
	public void setModifyUserName(java.lang.String value) {
		this.modifyUserName = value;
	}
	/**
	 * 修改人名称       db_column: modify_user_name 
	 */
	public java.lang.String getModifyUserName() {
		return this.modifyUserName;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Code",getCode())
			.append("Name",getName())
			.append("Val",getVal())
			.append("Cdesc",getCdesc())
			.append("Type",getType())
			.append("Status",getStatus())
			.append("CreateTime",getCreateTime())
			.append("UpdateTime",getUpdateTime())
			.append("ModifyUserCode",getModifyUserCode())
			.append("ModifyUserName",getModifyUserName())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof LvSysConfig == false) return false;
		if(this == obj) return true;
		LvSysConfig other = (LvSysConfig)obj;
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

