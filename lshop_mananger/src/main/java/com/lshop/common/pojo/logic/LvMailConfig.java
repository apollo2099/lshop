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

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */public class LvMailConfig extends BasePo implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//date formats
	public static final String FORMAT_CREATE_TIME = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_MODIFY_TIME = "yyyy-MM-dd HH:mm:ss";
	
	/**
     * 定义私有属性get、set
     */
	
      //id       db_column: Id 
    
	private java.lang.Integer id;
	
      //发送服务端       db_column: send_smtp_host 
    
	private java.lang.String sendSmtpHost;
	
      //发送服务端用户列表       db_column: send_user_name 
    
	private java.lang.String sendUserName;
	
      //发送端用户密码       db_column: send_password 
    
	private java.lang.String sendPassword;
	
      //发送来源       db_column: mail_from 
    
	private java.lang.String mailFrom;
	
      //商城标示       db_column: mall_system 
    
	private java.lang.String mallSystem;
	
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

	public LvMailConfig(){
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
	 * 发送服务端       db_column: send_smtp_host 
	 */
	public void setSendSmtpHost(java.lang.String value) {
		this.sendSmtpHost = value;
	}
	/**
	 * 发送服务端       db_column: send_smtp_host 
	 */
	public java.lang.String getSendSmtpHost() {
		return this.sendSmtpHost;
	}
	/**
	 * 发送服务端用户列表       db_column: send_user_name 
	 */
	public void setSendUserName(java.lang.String value) {
		this.sendUserName = value;
	}
	/**
	 * 发送服务端用户列表       db_column: send_user_name 
	 */
	public java.lang.String getSendUserName() {
		return this.sendUserName;
	}
	/**
	 * 发送端用户密码       db_column: send_password 
	 */
	public void setSendPassword(java.lang.String value) {
		this.sendPassword = value;
	}
	/**
	 * 发送端用户密码       db_column: send_password 
	 */
	public java.lang.String getSendPassword() {
		return this.sendPassword;
	}
	/**
	 * 发送来源       db_column: mail_from 
	 */
	public void setMailFrom(java.lang.String value) {
		this.mailFrom = value;
	}
	/**
	 * 发送来源       db_column: mail_from 
	 */
	public java.lang.String getMailFrom() {
		return this.mailFrom;
	}
	/**
	 * 商城标示       db_column: mall_system 
	 */
	public void setMallSystem(java.lang.String value) {
		this.mallSystem = value;
	}
	/**
	 * 商城标示       db_column: mall_system 
	 */
	public java.lang.String getMallSystem() {
		return this.mallSystem;
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
			.append("SendSmtpHost",getSendSmtpHost())
			.append("SendUserName",getSendUserName())
			.append("SendPassword",getSendPassword())
			.append("MailFrom",getMailFrom())
			.append("MallSystem",getMallSystem())
			.append("Code",getCode())
			.append("CreateTime",getCreateTime())
			.append("ModifyTime",getModifyTime())
			.append("ModifyUserId",getModifyUserId())
			.append("ModifyUserName",getModifyUserName())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof LvMailConfig == false) return false;
		if(this == obj) return true;
		LvMailConfig other = (LvMailConfig)obj;
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

