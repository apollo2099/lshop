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
 */public class WxNewsMaterialItem extends BasePo implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//date formats
	public static final String FORMAT_CREATE_TIME = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_MODIFY_TIME = "yyyy-MM-dd HH:mm:ss";
	
	/**
     * 定义私有属性get、set
     */
	
      //id       db_column: id 
    
	private java.lang.Integer id;
	
      //图文消息类型：1-单图文；2-多图文；       db_column: news_type 
    
	private java.lang.Integer newsType;
	
      //关联图文素材id       db_column: news_id 
    
	private java.lang.Integer newsId;
	
      //标题       db_column: title 
    
	private java.lang.String title;
	
      //描述       db_column: description 
    
	private java.lang.String description;
	
      //点击后跳转的链接       db_column: url 
    
	private java.lang.String url;
	
      //图文消息的图片链接       db_column: picurl 
    
	private java.lang.String picurl;
	
      //公众号配置id       db_column: wxh_config_id 
    
	private java.lang.Integer wxhConfigId;
	
      //创建时间       db_column: create_time 
    
	private java.util.Date createTime;
	
      //修改时间       db_column: modify_time 
    
	private java.util.Date modifyTime;
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
	 * 图文消息类型：1-单图文；2-多图文；       db_column: news_type 
	 */
	public void setNewsType(java.lang.Integer value) {
		this.newsType = value;
	}
	/**
	 * 图文消息类型：1-单图文；2-多图文；       db_column: news_type 
	 */
	public java.lang.Integer getNewsType() {
		return this.newsType;
	}
	/**
	 * 关联图文素材id       db_column: news_id 
	 */
	public void setNewsId(java.lang.Integer value) {
		this.newsId = value;
	}
	/**
	 * 关联图文素材id       db_column: news_id 
	 */
	public java.lang.Integer getNewsId() {
		return this.newsId;
	}
	/**
	 * 标题       db_column: title 
	 */
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	/**
	 * 标题       db_column: title 
	 */
	public java.lang.String getTitle() {
		return this.title;
	}
	/**
	 * 描述       db_column: description 
	 */
	public void setDescription(java.lang.String value) {
		this.description = value;
	}
	/**
	 * 描述       db_column: description 
	 */
	public java.lang.String getDescription() {
		return this.description;
	}
	/**
	 * 点击后跳转的链接       db_column: url 
	 */
	public void setUrl(java.lang.String value) {
		this.url = value;
	}
	/**
	 * 点击后跳转的链接       db_column: url 
	 */
	public java.lang.String getUrl() {
		return this.url;
	}
	/**
	 * 图文消息的图片链接       db_column: picurl 
	 */
	public void setPicurl(java.lang.String value) {
		this.picurl = value;
	}
	/**
	 * 图文消息的图片链接       db_column: picurl 
	 */
	public java.lang.String getPicurl() {
		return this.picurl;
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

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("NewsType",getNewsType())
			.append("NewsId",getNewsId())
			.append("Title",getTitle())
			.append("Description",getDescription())
			.append("Url",getUrl())
			.append("Picurl",getPicurl())
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
		if(obj instanceof WxNewsMaterialItem == false) return false;
		if(this == obj) return true;
		WxNewsMaterialItem other = (WxNewsMaterialItem)obj;
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

