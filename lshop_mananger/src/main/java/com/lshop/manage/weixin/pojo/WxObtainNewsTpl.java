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
 */public class WxObtainNewsTpl extends BasePo implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//date formats
	public static final String FORMAT_BEGIN_TIME = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_END_TIME = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_CREATE_TIME = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_MODIFY_TIME = "yyyy-MM-dd HH:mm:ss";
	
	/**
     * 定义私有属性get、set
     */
	
      //id       db_column: id 
    
	private java.lang.Integer id;
	
      //关键词，用户发送关键词时推送活动图文消息       db_column: push_keyword 
    
	private java.lang.String pushKeyword;
	
      //查询领取金额关键词       db_column: query_keyword 
    
	private java.lang.String queryKeyword;
	
      //活动开始时间       db_column: begin_time 
    
	private java.util.Date beginTime;
	
      //活动结束时间       db_column: end_time 
    
	private java.util.Date endTime;
	
      //关联图文素材表id        db_column: news_id 
    
	private java.lang.Integer newsId;
	
      //公众号配置id       db_column: wxh_config_id 
    
	private java.lang.Integer wxhConfigId;
	
      //创建时间       db_column: create_time 
    
	private java.util.Date createTime;
	
      //修改时间       db_column: modify_time 
    
	private java.util.Date modifyTime;
	
      //修改人编号       db_column: modify_user_id 
    
	private java.lang.Integer modifyUserId;
	
      //修改人名称       db_column: modify_user_name 
    
	private java.lang.String modifyUserName;
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
	 * 关键词，用户发送关键词时推送活动图文消息       db_column: push_keyword 
	 */
	public void setPushKeyword(java.lang.String value) {
		this.pushKeyword = value;
	}
	/**
	 * 关键词，用户发送关键词时推送活动图文消息       db_column: push_keyword 
	 */
	public java.lang.String getPushKeyword() {
		return this.pushKeyword;
	}
	/**
	 * 查询领取金额关键词       db_column: query_keyword 
	 */
	public void setQueryKeyword(java.lang.String value) {
		this.queryKeyword = value;
	}
	/**
	 * 查询领取金额关键词       db_column: query_keyword 
	 */
	public java.lang.String getQueryKeyword() {
		return this.queryKeyword;
	}
	/**
	 * 活动开始时间       db_column: begin_time 
	 */
	public String getBeginTimeString() {
		return DateConvertUtils.format(getBeginTime(), FORMAT_BEGIN_TIME);
	}
	/**
	 * 活动开始时间       db_column: begin_time 
	 */
	public void setBeginTimeString(String value) {
		setBeginTime(DateConvertUtils.parse(value, FORMAT_BEGIN_TIME,java.util.Date.class));
	}
	
	/**
	 * 活动开始时间       db_column: begin_time 
	 */
	public void setBeginTime(java.util.Date value) {
		this.beginTime = value;
	}
	/**
	 * 活动开始时间       db_column: begin_time 
	 */
	public java.util.Date getBeginTime() {
		return this.beginTime;
	}
	/**
	 * 活动结束时间       db_column: end_time 
	 */
	public String getEndTimeString() {
		return DateConvertUtils.format(getEndTime(), FORMAT_END_TIME);
	}
	/**
	 * 活动结束时间       db_column: end_time 
	 */
	public void setEndTimeString(String value) {
		setEndTime(DateConvertUtils.parse(value, FORMAT_END_TIME,java.util.Date.class));
	}
	
	/**
	 * 活动结束时间       db_column: end_time 
	 */
	public void setEndTime(java.util.Date value) {
		this.endTime = value;
	}
	/**
	 * 活动结束时间       db_column: end_time 
	 */
	public java.util.Date getEndTime() {
		return this.endTime;
	}
	/**
	 * 关联图文素材表id        db_column: news_id 
	 */
	public void setNewsId(java.lang.Integer value) {
		this.newsId = value;
	}
	/**
	 * 关联图文素材表id        db_column: news_id 
	 */
	public java.lang.Integer getNewsId() {
		return this.newsId;
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
			.append("PushKeyword",getPushKeyword())
			.append("QueryKeyword",getQueryKeyword())
			.append("BeginTime",getBeginTime())
			.append("EndTime",getEndTime())
			.append("NewsId",getNewsId())
			.append("WxhConfigId",getWxhConfigId())
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
		if(obj instanceof WxObtainNewsTpl == false) return false;
		if(this == obj) return true;
		WxObtainNewsTpl other = (WxObtainNewsTpl)obj;
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

