/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

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
 */public class LvMallSystem extends BasePo implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//date formats
	public static final String FORMAT_CREATE_TIME = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_MODIFY_TIME = "yyyy-MM-dd HH:mm:ss";
	
	/**
     * 定义私有属性get、set
     */
	
      //id       db_column: Id 
    
	private java.lang.Integer id;
	
      //商城体系名称       db_column: mall_system_name 
    
	private java.lang.String mallSystemName;
	
      //商城体系标示       db_column: mall_system_flag 
    
	private java.lang.String mallSystemFlag;
	private String oldMallSystemFlag;
	
      //商城体系域名后缀       db_column: domain_postfix 
    

	private java.lang.String domainPostfix;
	
      //code       db_column: code 
    
	private java.lang.String code;
	
      //创建时间       db_column: create_time 
    
	private java.util.Date createTime;
	
      //修改时间       db_column: modify_time 
    
	private java.util.Date modifyTime;
	
      //修改人编号       db_column: modify_user_id 
    
	private java.lang.Integer modifyUserId;
	
      //修改人名称       db_column: modify_user_name 
    
	private java.lang.String modifyUserName;
	//columns END

	public LvMallSystem(){
	}

	/**
	 * id       db_column: Id 
	 */
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	/**
	 * id       db_column: Id 
	 */
	public java.lang.Integer getId() {
		return this.id;
	}
	/**
	 * 商城体系名称       db_column: mall_system_name 
	 */
	public void setMallSystemName(java.lang.String value) {
		this.mallSystemName = value;
	}
	/**
	 * 商城体系名称       db_column: mall_system_name 
	 */
	public java.lang.String getMallSystemName() {
		return this.mallSystemName;
	}
	/**
	 * 商城体系标示       db_column: mall_system_flag 
	 */
	public void setMallSystemFlag(java.lang.String value) {
		this.mallSystemFlag = value;
	}
	/**
	 * 商城体系标示       db_column: mall_system_flag 
	 */
	public java.lang.String getMallSystemFlag() {
		return this.mallSystemFlag;
	}
	/**
	 * 商城体系域名后缀       db_column: domain_postfix 
	 */
	public void setDomainPostfix(java.lang.String value) {
		this.domainPostfix = value;
	}
	/**
	 * 商城体系域名后缀       db_column: domain_postfix 
	 */
	public java.lang.String getDomainPostfix() {
		return this.domainPostfix;
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
	 * 修改时间       db_column: modify_time 
	 */
	public String getModifyTimeString() {
		return DateConvertUtils.format(getModifyTime(), FORMAT_MODIFY_TIME);
	}
	/**
	 * 修改时间       db_column: modify_time 
	 */
	public void setModifyTimeString(String value) {
		setModifyTime(DateConvertUtils.parse(value, FORMAT_MODIFY_TIME,java.util.Date.class));
	}
	
	/**
	 * 修改时间       db_column: modify_time 
	 */
	public void setModifyTime(java.util.Date value) {
		this.modifyTime = value;
	}
	/**
	 * 修改时间       db_column: modify_time 
	 */
	public java.util.Date getModifyTime() {
		return this.modifyTime;
	}
	/**
	 * 修改人编号       db_column: modify_user_id 
	 */
	public void setModifyUserId(java.lang.Integer value) {
		this.modifyUserId = value;
	}
	/**
	 * 修改人编号       db_column: modify_user_id 
	 */
	public java.lang.Integer getModifyUserId() {
		return this.modifyUserId;
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
			.append("MallSystemName",getMallSystemName())
			.append("MallSystemFlag",getMallSystemFlag())
			.append("DomainPostfix",getDomainPostfix())
			.append("Code",getCode())
			.append("CreateTime",getCreateTime())
			.append("ModifyTime",getModifyTime())
			.append("ModifyUserId",getModifyUserId())
			.append("ModifyUserName",getModifyUserName())
			.toString();
	}
	
	public String getOldMallSystemFlag() {
		return oldMallSystemFlag;
	}

	public void setOldMallSystemFlag(String oldMallSystemFlag) {
		this.oldMallSystemFlag = oldMallSystemFlag;
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof LvMallSystem == false) return false;
		if(this == obj) return true;
		LvMallSystem other = (LvMallSystem)obj;
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

