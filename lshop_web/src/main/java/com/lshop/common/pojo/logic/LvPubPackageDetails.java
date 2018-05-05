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
 */public class LvPubPackageDetails extends BasePo implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//date formats
	public static final String FORMAT_CREATE_TIME = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_MODIFY_TIME = "yyyy-MM-dd HH:mm:ss";
	
	/**
     * 定义私有属性get、set
     */
	
      //id       db_column: Id 
    
	private java.lang.Integer id;
	
      //公共套餐code       db_column: pub_package_code 
    
	private java.lang.String pubPackageCode;
	
      //公共商品信息code       db_column: pub_product_code 
    
	private java.lang.String pubProductCode;
	
      //数量       db_column: product_num 
    
	private java.lang.Integer productNum;
	
      //code       db_column: code 
    
	private java.lang.String code;
	
      //排序值       db_column: order_value 
    
	private java.lang.Integer orderValue;
	
      //创建时间       db_column: create_time 
    
	private java.util.Date createTime;
	
      //修改时间       db_column: modify_time 
    
	private java.util.Date modifyTime;
	
      //修改人编号       db_column: modify_user_id 
    
	private java.lang.Integer modifyUserId;
	
      //修改人名称       db_column: modify_user_name 
    
	private java.lang.String modifyUserName;
	//columns END

	public LvPubPackageDetails(){
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
	 * 公共套餐code       db_column: pub_package_code 
	 */
	public void setPubPackageCode(java.lang.String value) {
		this.pubPackageCode = value;
	}
	/**
	 * 公共套餐code       db_column: pub_package_code 
	 */
	public java.lang.String getPubPackageCode() {
		return this.pubPackageCode;
	}
	/**
	 * 公共商品信息code       db_column: pub_product_code 
	 */
	public void setPubProductCode(java.lang.String value) {
		this.pubProductCode = value;
	}
	/**
	 * 公共商品信息code       db_column: pub_product_code 
	 */
	public java.lang.String getPubProductCode() {
		return this.pubProductCode;
	}
	/**
	 * 数量       db_column: product_num 
	 */
	public void setProductNum(java.lang.Integer value) {
		this.productNum = value;
	}
	/**
	 * 数量       db_column: product_num 
	 */
	public java.lang.Integer getProductNum() {
		return this.productNum;
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
			.append("PubPackageCode",getPubPackageCode())
			.append("PubProductCode",getPubProductCode())
			.append("ProductNum",getProductNum())
			.append("Code",getCode())
			.append("OrderValue",getOrderValue())
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
		if(obj instanceof LvPubPackageDetails == false) return false;
		if(this == obj) return true;
		LvPubPackageDetails other = (LvPubPackageDetails)obj;
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

