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
 */public class LvActivityAppointProduct extends BasePo implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//date formats
	public static final String FORMAT_CREATE_TIME = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_MODIFY_TIME = "yyyy-MM-dd HH:mm:ss";
	
	/**
     * 定义私有属性get、set
     */
	
      //序号       db_column: id 
    
	private java.lang.Integer id;
	
      //活动code       db_column: activity_code 
    
	private java.lang.String activityCode;
	
      //是否使用优惠券(1是，0否)       db_column: is_use_coupon 
    
	private Short isUseCoupon;
	
      //店铺编号       db_column: store_id 
    
	private java.lang.String storeId;
	
      //givenTypeName       db_column: given_type_name 
    
	private java.lang.String givenTypeName;
	
      //赠送物品关联       db_column: given_product_code 
    
	private java.lang.String givenProductCode;
	
      //赠送物品已发放总数       db_column: grant_total 
    
	private java.lang.Integer grantTotal;
	
      //赠送物品占用总数       db_column: occupied_total 
    
	private java.lang.Integer occupiedTotal;
	
      //赠送物品未领取总数       db_column: uncollected_total 
    
	private java.lang.Integer uncollectedTotal;
	
      //赠送类型字典(1赠送优惠券,2赠送抽奖机会,3赠送赠品)       db_column: given_type_num 
    
	private Short givenTypeNum;
	
      //抽奖机会次数       db_column: lottery_total 
    
	private java.lang.Integer lotteryTotal;
	
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

	public LvActivityAppointProduct(){
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
	 * 是否使用优惠券(1是，0否)       db_column: is_use_coupon 
	 */
	public void setIsUseCoupon(Short value) {
		this.isUseCoupon = value;
	}
	/**
	 * 是否使用优惠券(1是，0否)       db_column: is_use_coupon 
	 */
	public Short getIsUseCoupon() {
		return this.isUseCoupon;
	}
	/**
	 * 店铺编号       db_column: store_id 
	 */
	public void setStoreId(java.lang.String value) {
		this.storeId = value;
	}
	/**
	 * 店铺编号       db_column: store_id 
	 */
	public java.lang.String getStoreId() {
		return this.storeId;
	}
	/**
	 * givenTypeName       db_column: given_type_name 
	 */
	public void setGivenTypeName(java.lang.String value) {
		this.givenTypeName = value;
	}
	/**
	 * givenTypeName       db_column: given_type_name 
	 */
	public java.lang.String getGivenTypeName() {
		return this.givenTypeName;
	}
	/**
	 * 赠送物品关联       db_column: given_product_code 
	 */
	public void setGivenProductCode(java.lang.String value) {
		this.givenProductCode = value;
	}
	/**
	 * 赠送物品关联       db_column: given_product_code 
	 */
	public java.lang.String getGivenProductCode() {
		return this.givenProductCode;
	}
	/**
	 * 赠送物品已发放总数       db_column: grant_total 
	 */
	public void setGrantTotal(java.lang.Integer value) {
		this.grantTotal = value;
	}
	/**
	 * 赠送物品已发放总数       db_column: grant_total 
	 */
	public java.lang.Integer getGrantTotal() {
		return this.grantTotal;
	}
	/**
	 * 赠送物品占用总数       db_column: occupied_total 
	 */
	public void setOccupiedTotal(java.lang.Integer value) {
		this.occupiedTotal = value;
	}
	/**
	 * 赠送物品占用总数       db_column: occupied_total 
	 */
	public java.lang.Integer getOccupiedTotal() {
		return this.occupiedTotal;
	}
	/**
	 * 赠送物品未领取总数       db_column: uncollected_total 
	 */
	public void setUncollectedTotal(java.lang.Integer value) {
		this.uncollectedTotal = value;
	}
	/**
	 * 赠送物品未领取总数       db_column: uncollected_total 
	 */
	public java.lang.Integer getUncollectedTotal() {
		return this.uncollectedTotal;
	}
	/**
	 * 赠送类型字典(1赠送优惠券,2赠送抽奖机会,3赠送赠品)       db_column: given_type_num 
	 */
	public void setGivenTypeNum(Short value) {
		this.givenTypeNum = value;
	}
	/**
	 * 赠送类型字典(1赠送优惠券,2赠送抽奖机会,3赠送赠品)       db_column: given_type_num 
	 */
	public Short getGivenTypeNum() {
		return this.givenTypeNum;
	}
	/**
	 * 抽奖机会次数       db_column: lottery_total 
	 */
	public void setLotteryTotal(java.lang.Integer value) {
		this.lotteryTotal = value;
	}
	/**
	 * 抽奖机会次数       db_column: lottery_total 
	 */
	public java.lang.Integer getLotteryTotal() {
		return this.lotteryTotal;
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
			.append("IsUseCoupon",getIsUseCoupon())
			.append("StoreId",getStoreId())
			.append("GivenTypeName",getGivenTypeName())
			.append("GivenProductCode",getGivenProductCode())
			.append("GrantTotal",getGrantTotal())
			.append("OccupiedTotal",getOccupiedTotal())
			.append("UncollectedTotal",getUncollectedTotal())
			.append("GivenTypeNum",getGivenTypeNum())
			.append("LotteryTotal",getLotteryTotal())
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
		if(obj instanceof LvActivityAppointProduct == false) return false;
		if(this == obj) return true;
		LvActivityAppointProduct other = (LvActivityAppointProduct)obj;
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

