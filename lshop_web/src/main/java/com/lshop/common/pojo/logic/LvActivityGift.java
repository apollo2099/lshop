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
 */public class LvActivityGift extends BasePo implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//date formats
	public static final String FORMAT_CREATE_TIME = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_MODIFY_TIME = "yyyy-MM-dd HH:mm:ss";
	
	/**
     * 定义私有属性get、set
     */
	
      //id       db_column: Id 
    
	private java.lang.Integer id;
	
      //活动code       db_column: activity_code 
    
	private java.lang.String activityCode;
	
      //赠品code       db_column: gift_code 
    
	private java.lang.String giftCode;
	
      //赠品名称       db_column: gift_name 
    
	private java.lang.String giftName;
	
      //每次赠送数量       db_column: gift_every_num 
    
	private java.lang.Integer giftEveryNum;
	
      //赠品总量       db_column: gift_total_num 
    
	private java.lang.Integer giftTotalNum;
	
      //已赠送数量       db_column: gift_have_num 
    
	private java.lang.Integer giftHaveNum;
	
      //剩余赠品数量       db_column: gift_serplus_num 
    
	private java.lang.Integer giftSerplusNum;
	
      //排序值       db_column: order_value 
    
	private java.lang.Integer orderValue;
	
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

	public LvActivityGift(){
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
	 * 活动code       db_column: activity_code 
	 */
	public void setActivityCode(java.lang.String value) {
		this.activityCode = value;
	}
	/**
	 * 活动code       db_column: activity_code 
	 */
	public java.lang.String getActivityCode() {
		return this.activityCode;
	}
	/**
	 * 赠品code       db_column: gift_code 
	 */
	public void setGiftCode(java.lang.String value) {
		this.giftCode = value;
	}
	/**
	 * 赠品code       db_column: gift_code 
	 */
	public java.lang.String getGiftCode() {
		return this.giftCode;
	}
	/**
	 * 赠品名称       db_column: gift_name 
	 */
	public void setGiftName(java.lang.String value) {
		this.giftName = value;
	}
	/**
	 * 赠品名称       db_column: gift_name 
	 */
	public java.lang.String getGiftName() {
		return this.giftName;
	}
	/**
	 * 每次赠送数量       db_column: gift_every_num 
	 */
	public void setGiftEveryNum(java.lang.Integer value) {
		this.giftEveryNum = value;
	}
	/**
	 * 每次赠送数量       db_column: gift_every_num 
	 */
	public java.lang.Integer getGiftEveryNum() {
		return this.giftEveryNum;
	}
	/**
	 * 赠品总量       db_column: gift_total_num 
	 */
	public void setGiftTotalNum(java.lang.Integer value) {
		this.giftTotalNum = value;
	}
	/**
	 * 赠品总量       db_column: gift_total_num 
	 */
	public java.lang.Integer getGiftTotalNum() {
		return this.giftTotalNum;
	}
	/**
	 * 已赠送数量       db_column: gift_have_num 
	 */
	public void setGiftHaveNum(java.lang.Integer value) {
		this.giftHaveNum = value;
	}
	/**
	 * 已赠送数量       db_column: gift_have_num 
	 */
	public java.lang.Integer getGiftHaveNum() {
		return this.giftHaveNum;
	}
	/**
	 * 剩余赠品数量       db_column: gift_serplus_num 
	 */
	public void setGiftSerplusNum(java.lang.Integer value) {
		this.giftSerplusNum = value;
	}
	/**
	 * 剩余赠品数量       db_column: gift_serplus_num 
	 */
	public java.lang.Integer getGiftSerplusNum() {
		return this.giftSerplusNum;
	}
	/**
	 * 排序值       db_column: order_value 
	 */
	public void setOrderValue(java.lang.Integer value) {
		this.orderValue = value;
	}
	/**
	 * 排序值       db_column: order_value 
	 */
	public java.lang.Integer getOrderValue() {
		return this.orderValue;
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
			.append("ActivityCode",getActivityCode())
			.append("GiftCode",getGiftCode())
			.append("GiftName",getGiftName())
			.append("GiftEveryNum",getGiftEveryNum())
			.append("GiftTotalNum",getGiftTotalNum())
			.append("GiftHaveNum",getGiftHaveNum())
			.append("GiftSerplusNum",getGiftSerplusNum())
			.append("OrderValue",getOrderValue())
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
		if(obj instanceof LvActivityGift == false) return false;
		if(this == obj) return true;
		LvActivityGift other = (LvActivityGift)obj;
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

