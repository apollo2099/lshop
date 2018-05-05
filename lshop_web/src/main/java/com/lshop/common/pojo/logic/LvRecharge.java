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
 */
 public class LvRecharge extends BasePo implements java.io.Serializable{
	private static final long serialVersionUID = 5454155825314635342L;
	
	//date formats
	public static final String FORMAT_CREATE_DATE = "yyyy-MM-dd HH:mm:ss";
	
	/**
     * 定义私有属性get、set
     */
	
      //id       db_column: id 
    
	private java.lang.Integer id;
	
      //充值code       db_column: code 
    
	private java.lang.String code;
	
      //充值流水账id号       db_column: r_num 
    
	private java.lang.String rnum;
      //充值金额       db_column: money 
    
	private java.lang.Float money;
	
      //充值方式(1充值卡, 2 VISA,3 MASTER,4 JCB 5 支付宝)       db_column: r_type 
    
	private java.lang.Integer rtype;
	
      //充值卡号       db_column: r_card_num 
    
	private java.lang.String rcardNum;
	
      //充入的V币数量       db_column: vb_num 
    
	private java.lang.Integer vbNum;
	
      //充值账号       db_column: accounts 
    
	private java.lang.String accounts;
	//支付状态（0未支付，1支付成功）
	private java.lang.Integer payStatus;
	
      //订单状态（0未发货，1已发货，2充值失败，3已取消）       db_column: status 
    
	private java.lang.Integer status;
	
	private java.lang.String currency;//币种
	private java.util.Date payDate;//支付成功时间
      //充值时间       db_column: create_date 
	private java.util.Date createDate;
	
      //是否给别人充值 0否 1 是       db_column: is_for_other 
    
	private Integer isForOther;
	
      //用户code       db_column: c_user_code 
    
	private java.lang.String cuserEmail;
	
      //操作人code       db_column: op_c_user_code 
    
	private java.lang.String opCuserCode;
	
	//操作人code       db_column: op_c_user_code 
	
	private java.lang.String opAccount;
	
	private java.lang.String storeFlag;
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
	 * 充值code       db_column: code 
	 */
	public void setCode(java.lang.String value) {
		this.code = value;
	}
	/**
	 * 充值code       db_column: code 
	 */
	public java.lang.String getCode() {
		return this.code;
	}
	/**
	 * 充值流水账id号       db_column: r_num 
	 */
	public void setRnum(java.lang.String value) {
		this.rnum = value;
	}
	/**
	 * 充值流水账id号       db_column: r_num 
	 */
	public java.lang.String getRnum() {
		return this.rnum;
	}
	/**
	 * 充值金额       db_column: money 
	 */
	public void setMoney(java.lang.Float value) {
		this.money = value;
	}
	/**
	 * 充值金额       db_column: money 
	 */
	public java.lang.Float getMoney() {
		return this.money;
	}
	/**
	 * 充值方式(1充值卡, 2 VISA,3 MASTER,4 JCB 5 支付宝)       db_column: r_type 
	 */
	public void setRtype(java.lang.Integer value) {
		this.rtype = value;
	}
	/**
	 * 充值方式(1充值卡, 2 VISA,3 MASTER,4 JCB 5 支付宝)       db_column: r_type 
	 */
	public java.lang.Integer getRtype() {
		return this.rtype;
	}
	/**
	 * 充值卡号       db_column: r_card_num 
	 */
	public void setRcardNum(java.lang.String value) {
		this.rcardNum = value;
	}
	/**
	 * 充值卡号       db_column: r_card_num 
	 */
	public java.lang.String getRcardNum() {
		return this.rcardNum;
	}
	/**
	 * 充入的V币数量       db_column: vb_num 
	 */
	public void setVbNum(java.lang.Integer value) {
		this.vbNum = value;
	}
	/**
	 * 充入的V币数量       db_column: vb_num 
	 */
	public java.lang.Integer getVbNum() {
		return this.vbNum;
	}
	/**
	 * 充值账号       db_column: accounts 
	 */
	public void setAccounts(java.lang.String value) {
		this.accounts = value;
	}
	/**
	 * 充值账号       db_column: accounts 
	 */
	public java.lang.String getAccounts() {
		return this.accounts;
	}
	/**
	 * 支付状态（0失败，1成功,2未支付，3已取消）       db_column: status 
	 */
	public void setStatus(java.lang.Integer value) {
		this.status = value;
	}
	/**
	 * 支付状态（0未支付，1成功,2支付失败，3已取消）       db_column: status 
	 */
	public java.lang.Integer getStatus() {
		return this.status;
	}
	/**
	 * 充值时间       db_column: create_date 
	 */
	public String getCreateDateString() {
		return DateConvertUtils.format(getCreateDate(), FORMAT_CREATE_DATE);
	}
	/**
	 * 充值时间       db_column: create_date 
	 */
	public void setCreateDateString(String value) {
		setCreateDate(DateConvertUtils.parse(value, FORMAT_CREATE_DATE,java.util.Date.class));
	}
	
	/**
	 * 充值时间       db_column: create_date 
	 */
	public void setCreateDate(java.util.Date value) {
		this.createDate = value;
	}
	/**
	 * 充值时间       db_column: create_date 
	 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	/**
	 * 是否给别人充值 0否 1 是       db_column: is_for_other 
	 */
	public void setIsForOther(Integer value) {
		this.isForOther = value;
	}
	/**
	 * 是否给别人充值 0否 1 是       db_column: is_for_other 
	 */
	public Integer getIsForOther() {
		return this.isForOther;
	}
	
	/**
	 * 操作人code       db_column: op_c_user_code 
	 */
	public void setOpCuserCode(java.lang.String value) {
		this.opCuserCode = value;
	}
	/**
	 * 操作人code       db_column: op_c_user_code 
	 */
	public java.lang.String getOpCuserCode() {
		return this.opCuserCode;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
			.append("Id",getId())
			.append("Code",getCode())
			.append("Rnum",getRnum())
			.append("Money",getMoney())
			.append("Rtype",getRtype())
			.append("RcardNum",getRcardNum())
			.append("VbNum",getVbNum())
			.append("Accounts",getAccounts())
			.append("Status",getStatus())
			.append("CreateDate",getCreateDate())
			.append("IsForOther",getIsForOther())
			.append("OpCuserCode",getOpCuserCode())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof LvRecharge == false) return false;
		if(this == obj) return true;
		LvRecharge other = (LvRecharge)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	@Override
	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}
	public java.lang.String getCurrency() {
		return currency;
	}
	public void setCurrency(java.lang.String currency) {
		this.currency = currency;
	}
	public java.util.Date getPayDate() {
		return payDate;
	}
	public void setPayDate(java.util.Date payDate) {
		this.payDate = payDate;
	}
	public java.lang.String getCuserEmail() {
		return cuserEmail;
	}
	public void setCuserEmail(java.lang.String cuserEmail) {
		this.cuserEmail = cuserEmail;
	}
	public java.lang.Integer getPayStatus() {
		return payStatus;
	}
	public void setPayStatus(java.lang.Integer payStatus) {
		this.payStatus = payStatus;
	}
	public java.lang.String getOpAccount() {
		return opAccount;
	}
	public void setOpAccount(java.lang.String opAccount) {
		this.opAccount = opAccount;
	}
	public java.lang.String getStoreFlag() {
		return storeFlag;
	}
	public void setStoreFlag(java.lang.String storeFlag) {
		this.storeFlag = storeFlag;
	}
	
}

