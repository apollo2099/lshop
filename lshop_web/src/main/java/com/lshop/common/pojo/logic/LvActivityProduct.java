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
 */public class LvActivityProduct extends BasePo implements java.io.Serializable{
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
	
      //商品code       db_column: product_code 
    
	private java.lang.String productCode;
	
      //商品名称       db_column: product_name 
    
	private java.lang.String productName;
	
      //币种       db_column: currency 
    
	private java.lang.String currency;
	
      //单价       db_column: price 
    
	private java.lang.Double price;
	
      //店铺标识       db_column: store_id 
    
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

	public LvActivityProduct(){
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
	 * 商品code       db_column: product_code 
	 */
	public void setProductCode(java.lang.String value) {
		this.productCode = value;
	}
	/**
	 * 商品code       db_column: product_code 
	 */
	public java.lang.String getProductCode() {
		return this.productCode;
	}
	/**
	 * 商品名称       db_column: product_name 
	 */
	public void setProductName(java.lang.String value) {
		this.productName = value;
	}
	/**
	 * 商品名称       db_column: product_name 
	 */
	public java.lang.String getProductName() {
		return this.productName;
	}
	/**
	 * 币种       db_column: currency 
	 */
	public void setCurrency(java.lang.String value) {
		this.currency = value;
	}
	/**
	 * 币种       db_column: currency 
	 */
	public java.lang.String getCurrency() {
		return this.currency;
	}
	/**
	 * 单价       db_column: price 
	 */
	public void setPrice(java.lang.Double value) {
		this.price = value;
	}
	/**
	 * 单价       db_column: price 
	 */
	public java.lang.Double getPrice() {
		return this.price;
	}
	/**
	 * 店铺标识       db_column: store_id 
	 */
	public void setStoreId(java.lang.String value) {
		this.storeId = value;
	}
	/**
	 * 店铺标识       db_column: store_id 
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
			.append("ActivityCode",getActivityCode())
			.append("ProductCode",getProductCode())
			.append("ProductName",getProductName())
			.append("Currency",getCurrency())
			.append("Price",getPrice())
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
		if(obj instanceof LvActivityProduct == false) return false;
		if(this == obj) return true;
		LvActivityProduct other = (LvActivityProduct)obj;
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

