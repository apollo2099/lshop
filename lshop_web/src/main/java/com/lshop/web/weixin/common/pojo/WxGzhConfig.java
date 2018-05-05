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
 */public class WxGzhConfig extends BasePo implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//date formats
	public static final String FORMAT_ACCESS_TOKEN_TIME = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_CREATE_TIME = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_MODIFY_TIME = "yyyy-MM-dd HH:mm:ss";
	
	/**
     * 定义私有属性get、set
     */
	
      //id       db_column: id 
    
	private java.lang.Integer id;
	
      //微信公众号名称       db_column: wxh_name 
    
	private java.lang.String wxhName;
	
      //URL前缀       db_column: pre_url 
    
	private java.lang.String preUrl;
	
      //token       db_column: token 
    
	private java.lang.String token;
	
      //消息加密密钥       db_column: encoding_aes_key 
    
	private java.lang.String encodingAesKey;
	
      //应用ID       db_column: app_id 
    
	private java.lang.String appId;
	
      //应用密钥       db_column: app_secret 
    
	private java.lang.String appSecret;
	
      //access_token凭证       db_column: access_token 
    
	private java.lang.String accessToken;
	
      //凭证失效时间，单位：秒       db_column: access_token_expires 
    
	private java.lang.Integer accessTokenExpires;
	
      //凭证获取时间       db_column: access_token_time 
    
	private java.util.Date accessTokenTime;
	
      //code，第3方对接时使用，内部系统关联还是使用id       db_column: code 
    
	private java.lang.String code;
	
      //创建时间       db_column: create_time 
    
	private java.util.Date createTime;
	
      //修改时间       db_column: modify_time 
    
	private java.util.Date modifyTime;
	
      //修改人编号       db_column: modify_user_id 
    
	private java.lang.Integer modifyUserId;
	
      //修改人名称       db_column: modify_user_name 
    
	private java.lang.String modifyUserName;
	private java.lang.String storeId;
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
	 * 微信公众号名称       db_column: wxh_name 
	 */
	public void setWxhName(java.lang.String value) {
		this.wxhName = value;
	}
	/**
	 * 微信公众号名称       db_column: wxh_name 
	 */
	public java.lang.String getWxhName() {
		return this.wxhName;
	}

	/**
	 * URL前缀       db_column: pre_url 
	 */
	public void setPreUrl(java.lang.String value) {
		this.preUrl = value;
	}
	/**
	 * URL前缀       db_column: pre_url 
	 */
	public java.lang.String getPreUrl() {
		return this.preUrl;
	}

	/**
	 * token       db_column: token 
	 */
	public void setToken(java.lang.String value) {
		this.token = value;
	}
	/**
	 * token       db_column: token 
	 */
	public java.lang.String getToken() {
		return this.token;
	}

	/**
	 * 消息加密密钥       db_column: encoding_aes_key 
	 */
	public void setEncodingAesKey(java.lang.String value) {
		this.encodingAesKey = value;
	}
	/**
	 * 消息加密密钥       db_column: encoding_aes_key 
	 */
	public java.lang.String getEncodingAesKey() {
		return this.encodingAesKey;
	}

	/**
	 * 应用ID       db_column: app_id 
	 */
	public void setAppId(java.lang.String value) {
		this.appId = value;
	}
	/**
	 * 应用ID       db_column: app_id 
	 */
	public java.lang.String getAppId() {
		return this.appId;
	}

	/**
	 * 应用密钥       db_column: app_secret 
	 */
	public void setAppSecret(java.lang.String value) {
		this.appSecret = value;
	}
	/**
	 * 应用密钥       db_column: app_secret 
	 */
	public java.lang.String getAppSecret() {
		return this.appSecret;
	}

	/**
	 * access_token凭证       db_column: access_token 
	 */
	public void setAccessToken(java.lang.String value) {
		this.accessToken = value;
	}
	/**
	 * access_token凭证       db_column: access_token 
	 */
	public java.lang.String getAccessToken() {
		return this.accessToken;
	}

	/**
	 * 凭证失效时间，单位：秒       db_column: access_token_expires 
	 */
	public void setAccessTokenExpires(java.lang.Integer value) {
		this.accessTokenExpires = value;
	}
	/**
	 * 凭证失效时间，单位：秒       db_column: access_token_expires 
	 */
	public java.lang.Integer getAccessTokenExpires() {
		return this.accessTokenExpires;
	}

	/**
	 * 凭证获取时间       db_column: access_token_time 
	 */
	public void setAccessTokenTime(java.util.Date value) {
		this.accessTokenTime = value;
	}
	/**
	 * 凭证获取时间       db_column: access_token_time 
	 */
	public java.util.Date getAccessTokenTime() {
		return this.accessTokenTime;
	}

	/**
	 * code，第3方对接时使用，内部系统关联还是使用id       db_column: code 
	 */
	public void setCode(java.lang.String value) {
		this.code = value;
	}
	/**
	 * code，第3方对接时使用，内部系统关联还是使用id       db_column: code 
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

	public java.lang.String getStoreId() {
		return storeId;
	}
	public void setStoreId(java.lang.String storeId) {
		this.storeId = storeId;
	}
	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("WxhName",getWxhName())
			.append("PreUrl",getPreUrl())
			.append("Token",getToken())
			.append("EncodingAesKey",getEncodingAesKey())
			.append("AppId",getAppId())
			.append("AppSecret",getAppSecret())
			.append("AccessToken",getAccessToken())
			.append("AccessTokenExpires",getAccessTokenExpires())
			.append("AccessTokenTime",getAccessTokenTime())
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
		if(obj instanceof WxGzhConfig == false) return false;
		if(this == obj) return true;
		WxGzhConfig other = (WxGzhConfig)obj;
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

