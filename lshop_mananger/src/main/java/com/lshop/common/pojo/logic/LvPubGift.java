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
 */public class LvPubGift extends BasePo implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//date formats
	public static final String FORMAT_CREATE_TIME = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_MODIFY_TIME = "yyyy-MM-dd HH:mm:ss";
	
	/**
     * 定义私有属性get、set
     */
	
      //id       db_column: Id 
    
	private java.lang.Integer id;
	
      //赠品中文名称       db_column: gift_name 
    
	private java.lang.String giftName;
	
      //赠品英文名称       db_column: gift_name_en 
    
	private java.lang.String giftNameEn;
	
      //启创对接code       db_column: pcode 
    
	private java.lang.String pcode;
	
      //状态(1启用，0停用，-1删除)       db_column: status 
    
	private Short status;
	
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

	public LvPubGift(){
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
	 * 赠品中文名称       db_column: gift_name 
	 */
	public void setGiftName(java.lang.String value) {
		this.giftName = value;
	}
	/**
	 * 赠品中文名称       db_column: gift_name 
	 */
	public java.lang.String getGiftName() {
		return this.giftName;
	}
	/**
	 * 赠品英文名称       db_column: gift_name_en 
	 */
	public void setGiftNameEn(java.lang.String value) {
		this.giftNameEn = value;
	}
	/**
	 * 赠品英文名称       db_column: gift_name_en 
	 */
	public java.lang.String getGiftNameEn() {
		return this.giftNameEn;
	}
	/**
	 * 启创对接code       db_column: pcode 
	 */
	public void setPcode(java.lang.String value) {
		this.pcode = value;
	}
	/**
	 * 启创对接code       db_column: pcode 
	 */
	public java.lang.String getPcode() {
		return this.pcode;
	}
	/**
	 * 状态(1启用，0停用，-1删除)       db_column: status 
	 */
	public void setStatus(Short value) {
		this.status = value;
	}
	/**
	 * 状态(1启用，0停用，-1删除)       db_column: status 
	 */
	public Short getStatus() {
		return this.status;
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
			.append("GiftName",getGiftName())
			.append("GiftNameEn",getGiftNameEn())
			.append("Pcode",getPcode())
			.append("Status",getStatus())
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
		if(obj instanceof LvPubGift == false) return false;
		if(this == obj) return true;
		LvPubGift other = (LvPubGift)obj;
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

