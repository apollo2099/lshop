/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.weixin.pojo;

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
 */public class WxObtain extends BasePo implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//date formats
	public static final String FORMAT_CREATE_TIME = "yyyy-MM-dd HH:mm:ss";
	
	/**
     * 定义私有属性get、set
     */
	
      //id       db_column: id 
    
	private java.lang.Integer id;
	
      //用户的标识，对当前公众号唯一       db_column: openid 
    
	private java.lang.String openid;
	
      //用户的昵称       db_column: nickname 
    
	private java.lang.String nickname;
	
      //领取类型：1-自己领取，2-好友支持       db_column: obtain_type 
    
	private java.lang.Integer obtainType;
	
      //公众号配置id       db_column: wxh_config_id 
    
	private java.lang.Integer wxhConfigId;
	
      //创建时间       db_column: create_time 
    
	private java.util.Date createTime;
	
      //ip地址       db_column: ip_address 
    
	private java.lang.String ipAddress;
	
	private java.lang.String friendOpenId;
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
	 * 领取类型：1-自己领取，2-好友支持       db_column: obtain_type 
	 */
	public void setObtainType(java.lang.Integer value) {
		this.obtainType = value;
	}
	/**
	 * 领取类型：1-自己领取，2-好友支持       db_column: obtain_type 
	 */
	public java.lang.Integer getObtainType() {
		return this.obtainType;
	}
	
	
	
	public java.lang.String getFriendOpenId() {
		return friendOpenId;
	}
	public void setFriendOpenId(java.lang.String friendOpenId) {
		this.friendOpenId = friendOpenId;
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
	 * ip地址       db_column: ip_address 
	 */
	public void setIpAddress(java.lang.String value) {
		this.ipAddress = value;
	}
	/**
	 * ip地址       db_column: ip_address 
	 */
	public java.lang.String getIpAddress() {
		return this.ipAddress;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Openid",getOpenid())
			.append("Nickname",getNickname())
			.append("ObtainType",getObtainType())
			.append("WxhConfigId",getWxhConfigId())
			.append("CreateTime",getCreateTime())
			.append("IpAddress",getIpAddress())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof WxObtain == false) return false;
		if(this == obj) return true;
		WxObtain other = (WxObtain)obj;
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

