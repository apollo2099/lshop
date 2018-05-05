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
import com.gv.core.util.DateConvertUtils;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */public class LvVbPlans extends BasePo implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//date formats
	public static final String FORMAT_CREATE_TIME = "yyyy-MM-dd HH:mm:ss";
	public static final String FORMAT_UPDATE_TIME = "yyyy-MM-dd HH:mm:ss";
	
	/**
     * 定义私有属性get、set
     */
	
      //id       db_column: id 
    
	private java.lang.Integer id;
	
      //商城标识       db_column: mall_flag 
    
	private java.lang.String mallFlag;
	
      //选项名称       db_column: title 
    
	private java.lang.String title;
	
      //类型 0选择 1输入       db_column: p_type 
    
	private java.lang.Boolean ptype;
	
      //vb个数(类型为选择才出现)       db_column: vb_num 
    
	private java.lang.Integer vbNum;
	
      //可输入的最小值（类型为输入才出现）       db_column: min_num 
    
	private java.lang.Integer minNum;
	
      //是否倍数(类型为输入才出现)       db_column: is_mul 
    
	private java.lang.Boolean isMul;
	
      //创建时间       db_column: create_time 
    
	private java.util.Date createTime;
	
      //更新时间       db_column: update_time 
    
	private java.util.Date updateTime;
	
      //最后修改人       db_column: modify_user_name 
    
	private java.lang.String modifyUserName;
	
      //修改人编号       db_column: modify_user_id 
    
	private java.lang.Integer modifyUserId;
	
      //排序值       db_column: p_order 
    
	private java.lang.Integer porder;
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
	 * 商城标识       db_column: mall_flag 
	 */
	public void setMallFlag(java.lang.String value) {
		this.mallFlag = value;
	}
	/**
	 * 商城标识       db_column: mall_flag 
	 */
	public java.lang.String getMallFlag() {
		return this.mallFlag;
	}
	/**
	 * 选项名称       db_column: title 
	 */
	public void setTitle(java.lang.String value) {
		this.title = value;
	}
	/**
	 * 选项名称       db_column: title 
	 */
	public java.lang.String getTitle() {
		return this.title;
	}
	/**
	 * 类型 0选择 1输入       db_column: p_type 
	 */
	public void setPtype(java.lang.Boolean value) {
		this.ptype = value;
	}
	/**
	 * 类型 0选择 1输入       db_column: p_type 
	 */
	public java.lang.Boolean getPtype() {
		return this.ptype;
	}
	/**
	 * vb个数(类型为选择才出现)       db_column: vb_num 
	 */
	public void setVbNum(java.lang.Integer value) {
		this.vbNum = value;
	}
	/**
	 * vb个数(类型为选择才出现)       db_column: vb_num 
	 */
	public java.lang.Integer getVbNum() {
		return this.vbNum;
	}
	/**
	 * 可输入的最小值（类型为输入才出现）       db_column: min_num 
	 */
	public void setMinNum(java.lang.Integer value) {
		this.minNum = value;
	}
	/**
	 * 可输入的最小值（类型为输入才出现）       db_column: min_num 
	 */
	public java.lang.Integer getMinNum() {
		return this.minNum;
	}
	/**
	 * 是否倍数(类型为输入才出现)       db_column: is_mul 
	 */
	public void setIsMul(java.lang.Boolean value) {
		this.isMul = value;
	}
	/**
	 * 是否倍数(类型为输入才出现)       db_column: is_mul 
	 */
	public java.lang.Boolean getIsMul() {
		return this.isMul;
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
	 * 更新时间       db_column: update_time 
	 */
	public String getUpdateTimeString() {
		return DateConvertUtils.format(getUpdateTime(), FORMAT_UPDATE_TIME);
	}
	/**
	 * 更新时间       db_column: update_time 
	 */
	public void setUpdateTimeString(String value) {
		setUpdateTime(DateConvertUtils.parse(value, FORMAT_UPDATE_TIME,java.util.Date.class));
	}
	
	/**
	 * 更新时间       db_column: update_time 
	 */
	public void setUpdateTime(java.util.Date value) {
		this.updateTime = value;
	}
	/**
	 * 更新时间       db_column: update_time 
	 */
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}
	/**
	 * 最后修改人       db_column: modify_user_name 
	 */
	public void setModifyUserName(java.lang.String value) {
		this.modifyUserName = value;
	}
	/**
	 * 最后修改人       db_column: modify_user_name 
	 */
	public java.lang.String getModifyUserName() {
		return this.modifyUserName;
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
	 * 排序值       db_column: p_order 
	 */
	public void setPorder(java.lang.Integer value) {
		this.porder = value;
	}
	/**
	 * 排序值       db_column: p_order 
	 */
	public java.lang.Integer getPorder() {
		return this.porder;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("MallFlag",getMallFlag())
			.append("Title",getTitle())
			.append("Ptype",getPtype())
			.append("VbNum",getVbNum())
			.append("MinNum",getMinNum())
			.append("IsMul",getIsMul())
			.append("CreateTime",getCreateTime())
			.append("UpdateTime",getUpdateTime())
			.append("ModifyUserName",getModifyUserName())
			.append("ModifyUserId",getModifyUserId())
			.append("Porder",getPorder())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof LvVbPlans == false) return false;
		if(this == obj) return true;
		LvVbPlans other = (LvVbPlans)obj;
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

