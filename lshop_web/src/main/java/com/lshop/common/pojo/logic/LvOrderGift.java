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
 */public class LvOrderGift extends BasePo implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//date formats
	public static final String FORMAT_CREATE_TIME = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_MODIFY_TIME = "yyyy-MM-dd HH:mm:ss";
	
	/**
     * 定义私有属性get、set
     */
	
      //序号       db_column: id 
    
	private java.lang.Integer id;
	
      //订单号       db_column: order_id 
    
	private java.lang.String orderId;
	
      //产品code       db_column: product_code 
    
	private java.lang.String productCode;
	
      //赠品code       db_column: gift_code 
    
	private java.lang.String giftCode;
	
      //赠品数量       db_column: gift_num 
    
	private java.lang.Integer giftNum;
	
      //SAS对接code       db_column: pcode 
    
	private java.lang.String pcode;
	
      //赠品详情状态(0=>不删除,-1=>删除)       db_column: is_delete 
    
	private Integer isDelete;
	
      //店铺编号       db_column: store_id 
    
	private java.lang.String storeId;
	
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

	public LvOrderGift(){
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
	 * 订单号       db_column: order_id 
	 */
	public void setOrderId(java.lang.String value) {
		this.orderId = value;
	}
	/**
	 * 订单号       db_column: order_id 
	 */
	public java.lang.String getOrderId() {
		return this.orderId;
	}
	/**
	 * 产品code       db_column: product_code 
	 */
	public void setProductCode(java.lang.String value) {
		this.productCode = value;
	}
	/**
	 * 产品code       db_column: product_code 
	 */
	public java.lang.String getProductCode() {
		return this.productCode;
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
	 * 赠品数量       db_column: gift_num 
	 */
	public void setGiftNum(java.lang.Integer value) {
		this.giftNum = value;
	}
	/**
	 * 赠品数量       db_column: gift_num 
	 */
	public java.lang.Integer getGiftNum() {
		return this.giftNum;
	}
	/**
	 * SAS对接code       db_column: pcode 
	 */
	public void setPcode(java.lang.String value) {
		this.pcode = value;
	}
	/**
	 * SAS对接code       db_column: pcode 
	 */
	public java.lang.String getPcode() {
		return this.pcode;
	}
	/**
	 * 赠品详情状态(0=>不删除,-1=>删除)       db_column: is_delete 
	 */
	public void setIsDelete(Integer value) {
		this.isDelete = value;
	}
	/**
	 * 赠品详情状态(0=>不删除,-1=>删除)       db_column: is_delete 
	 */
	public Integer getIsDelete() {
		return this.isDelete;
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
			.append("OrderId",getOrderId())
			.append("ProductCode",getProductCode())
			.append("GiftCode",getGiftCode())
			.append("GiftNum",getGiftNum())
			.append("Pcode",getPcode())
			.append("IsDelete",getIsDelete())
			.append("StoreId",getStoreId())
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
		if(obj instanceof LvOrderGift == false) return false;
		if(this == obj) return true;
		LvOrderGift other = (LvOrderGift)obj;
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

