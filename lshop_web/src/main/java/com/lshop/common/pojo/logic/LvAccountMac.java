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
 */public class LvAccountMac extends BasePo implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//date formats
	public static final String FORMAT_CREATE_TIME = "yyyy-MM-dd HH:mm:ss";
	
	/**
     * 定义私有属性get、set
     */
	
      //序号       db_column: id 
    
	private java.lang.Integer id;
	
      //用户编码       db_column: user_code 
    
	private java.lang.String userCode;
	
      //用户邮箱       db_column: user_email 
    
	private java.lang.String userEmail;
	
      //mac       db_column: mac 
    
	private java.lang.String mac;
	
      //创建时间       db_column: create_time 
    
	private java.util.Date createTime;
	//columns END

	private String contactPhone;
	private String contactTel;
	private String ip;
	private Short status;
	private String code;
	private String sourceUrl;
	
	public LvAccountMac(){
	}
	/**
	 * 序号       db_column: id 
	 */
	public void setId(java.lang.Integer value) {
		this.id = value;
	}
	/**
	 * 序号       db_column: id 
	 */
	public java.lang.Integer getId() {
		return this.id;
	}
	/**
	 * 用户编码       db_column: user_code 
	 */
	public void setUserCode(java.lang.String value) {
		this.userCode = value;
	}
	/**
	 * 用户编码       db_column: user_code 
	 */
	public java.lang.String getUserCode() {
		return this.userCode;
	}
	/**
	 * 用户邮箱       db_column: user_email 
	 */
	public void setUserEmail(java.lang.String value) {
		this.userEmail = value;
	}
	/**
	 * 用户邮箱       db_column: user_email 
	 */
	public java.lang.String getUserEmail() {
		return this.userEmail;
	}
	/**
	 * mac       db_column: mac 
	 */
	public void setMac(java.lang.String value) {
		this.mac = value;
	}
	/**
	 * mac       db_column: mac 
	 */
	public java.lang.String getMac() {
		return this.mac;
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

	
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	
	public String getContactTel() {
		return contactTel;
	}
	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getSourceUrl() {
		return sourceUrl;
	}
	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("UserCode",getUserCode())
			.append("UserEmail",getUserEmail())
			.append("Mac",getMac())
			.append("CreateTime",getCreateTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof LvAccountMac == false) return false;
		if(this == obj) return true;
		LvAccountMac other = (LvAccountMac)obj;
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

