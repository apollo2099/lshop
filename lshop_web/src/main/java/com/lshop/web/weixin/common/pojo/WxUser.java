/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.web.weixin.common.pojo;

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
 */public class WxUser extends BasePo implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//date formats
	public static final String FORMAT_BIND_TIME = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_CREATE_TIME = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_MODIFY_TIME = "yyyy-MM-dd HH:mm:ss";
	
	/**
     * 定义私有属性get、set
     */
	
      //id       db_column: id 
    
	private java.lang.Integer id;
	
      //用户的标识，对当前公众号唯一       db_column: openid 
    
	private java.lang.String openid;
	
      //用户的昵称       db_column: nickname 
    
	private java.lang.String nickname;
	
      //tvpad用户email       db_column: email 
    
	private java.lang.String email;
	
      //绑定时间       db_column: bind_time 
    
	private java.util.Date bindTime;
	
      //公众号配置id       db_column: wxh_config_id 
    
	private java.lang.Integer wxhConfigId;
	
      //创建时间       db_column: create_time 
    
	private java.util.Date createTime;
	
      //修改时间       db_column: modify_time 
    
	private java.util.Date modifyTime;
	//领取总金额
	private java.lang.Integer obtainAmount;
	private java.util.Date lastObtainTime;
	//columns END


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
	 * 用户的标识，对当前公众号唯一       db_column: openid 
	 */
	public void setOpenid(java.lang.String value) {
		this.openid = value;
	}
	/**
	 * 用户的标识，对当前公众号唯一       db_column: openid 
	 */
	public java.lang.String getOpenid() {
		return this.openid;
	}

	/**
	 * 用户的昵称       db_column: nickname 
	 */
	public void setNickname(java.lang.String value) {
		this.nickname = value;
	}
	/**
	 * 用户的昵称       db_column: nickname 
	 */
	public java.lang.String getNickname() {
		return this.nickname;
	}

	/**
	 * tvpad用户email       db_column: email 
	 */
	public void setEmail(java.lang.String value) {
		this.email = value;
	}
	/**
	 * tvpad用户email       db_column: email 
	 */
	public java.lang.String getEmail() {
		return this.email;
	}

	/**
	 * 绑定时间       db_column: bind_time 
	 */
	public void setBindTime(java.util.Date value) {
		this.bindTime = value;
	}
	/**
	 * 绑定时间       db_column: bind_time 
	 */
	public java.util.Date getBindTime() {
		return this.bindTime;
	}

	/**
	 * 公众号配置id       db_column: wxh_config_id 
	 */
	public void setWxhConfigId(java.lang.Integer value) {
		this.wxhConfigId = value;
	}
	/**
	 * 公众号配置id       db_column: wxh_config_id 
	 */
	public java.lang.Integer getWxhConfigId() {
		return this.wxhConfigId;
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
	public void setModifyTime(java.util.Date value) {
		this.modifyTime = value;
	}
	/**
	 * 修改时间       db_column: modify_time 
	 */
	public java.util.Date getModifyTime() {
		return this.modifyTime;
	}

	public java.lang.Integer getObtainAmount() {
		return obtainAmount;
	}
	public void setObtainAmount(java.lang.Integer obtainAmount) {
		this.obtainAmount = obtainAmount;
	}
	public java.util.Date getLastObtainTime() {
		return lastObtainTime;
	}
	public void setLastObtainTime(java.util.Date lastObtainTime) {
		this.lastObtainTime = lastObtainTime;
	}
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Openid",getOpenid())
			.append("Nickname",getNickname())
			.append("Email",getEmail())
			.append("BindTime",getBindTime())
			.append("WxhConfigId",getWxhConfigId())
			.append("CreateTime",getCreateTime())
			.append("ModifyTime",getModifyTime())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof WxUser == false) return false;
		if(this == obj) return true;
		WxUser other = (WxUser)obj;
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

