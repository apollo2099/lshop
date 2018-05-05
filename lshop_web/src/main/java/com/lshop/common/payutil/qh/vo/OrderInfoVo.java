package com.lshop.common.payutil.qh.vo;

public class OrderInfoVo {
	//返回商户号
    private String account;
	//返回终端号
    private String terminal;
    //交易安全签名
    private String signValue;
	//返回网站订单号
    private String order_number;
    //返回交易币种
    private String order_currency;
    //返回支付金额
    private String order_amount ;
    //订单来源
    private String order_sourceUrl ;
    //支付唯一号
    private String payment_id ;
    //支付结果
    private String payment_results ;
    //支付日期时间
    private String payment_dateTime ;
    //授权状态
    private String auth_status ;
    
    public OrderInfoVo(){
    	
    }
    
    
	public OrderInfoVo(String account, String terminal, String signValue,
			String order_number, String order_currency, String order_amount,
			String order_sourceUrl, String payment_id, String payment_results,
			String payment_dateTime, String auth_status) {
		super();
		this.account = account;
		this.terminal = terminal;
		this.signValue = signValue;
		this.order_number = order_number;
		this.order_currency = order_currency;
		this.order_amount = order_amount;
		this.order_sourceUrl = order_sourceUrl;
		this.payment_id = payment_id;
		this.payment_results = payment_results;
		this.payment_dateTime = payment_dateTime;
		this.auth_status = auth_status;
	}
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	public String getSignValue() {
		return signValue;
	}
	public void setSignValue(String signValue) {
		this.signValue = signValue;
	}
	public String getOrder_number() {
		return order_number;
	}
	public void setOrder_number(String order_number) {
		this.order_number = order_number;
	}
	public String getOrder_currency() {
		return order_currency;
	}
	public void setOrder_currency(String order_currency) {
		this.order_currency = order_currency;
	}
	public String getOrder_amount() {
		return order_amount;
	}
	public void setOrder_amount(String order_amount) {
		this.order_amount = order_amount;
	}
	public String getOrder_sourceUrl() {
		return order_sourceUrl;
	}
	public void setOrder_sourceUrl(String order_sourceUrl) {
		this.order_sourceUrl = order_sourceUrl;
	}
	public String getPayment_id() {
		return payment_id;
	}
	public void setPayment_id(String payment_id) {
		this.payment_id = payment_id;
	}
	public String getPayment_results() {
		return payment_results;
	}
	public void setPayment_results(String payment_results) {
		this.payment_results = payment_results;
	}
	public String getPayment_dateTime() {
		return payment_dateTime;
	}
	public void setPayment_dateTime(String payment_dateTime) {
		this.payment_dateTime = payment_dateTime;
	}
	public String getAuth_status() {
		return auth_status;
	}
	public void setAuth_status(String auth_status) {
		this.auth_status = auth_status;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result
				+ ((auth_status == null) ? 0 : auth_status.hashCode());
		result = prime * result
				+ ((order_amount == null) ? 0 : order_amount.hashCode());
		result = prime * result
				+ ((order_currency == null) ? 0 : order_currency.hashCode());
		result = prime * result
				+ ((order_number == null) ? 0 : order_number.hashCode());
		result = prime * result
				+ ((order_sourceUrl == null) ? 0 : order_sourceUrl.hashCode());
		result = prime
				* result
				+ ((payment_dateTime == null) ? 0 : payment_dateTime.hashCode());
		result = prime * result
				+ ((payment_id == null) ? 0 : payment_id.hashCode());
		result = prime * result
				+ ((payment_results == null) ? 0 : payment_results.hashCode());
		result = prime * result
				+ ((signValue == null) ? 0 : signValue.hashCode());
		result = prime * result
				+ ((terminal == null) ? 0 : terminal.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderInfoVo other = (OrderInfoVo) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (auth_status == null) {
			if (other.auth_status != null)
				return false;
		} else if (!auth_status.equals(other.auth_status))
			return false;
		if (order_amount == null) {
			if (other.order_amount != null)
				return false;
		} else if (!order_amount.equals(other.order_amount))
			return false;
		if (order_currency == null) {
			if (other.order_currency != null)
				return false;
		} else if (!order_currency.equals(other.order_currency))
			return false;
		if (order_number == null) {
			if (other.order_number != null)
				return false;
		} else if (!order_number.equals(other.order_number))
			return false;
		if (order_sourceUrl == null) {
			if (other.order_sourceUrl != null)
				return false;
		} else if (!order_sourceUrl.equals(other.order_sourceUrl))
			return false;
		if (payment_dateTime == null) {
			if (other.payment_dateTime != null)
				return false;
		} else if (!payment_dateTime.equals(other.payment_dateTime))
			return false;
		if (payment_id == null) {
			if (other.payment_id != null)
				return false;
		} else if (!payment_id.equals(other.payment_id))
			return false;
		if (payment_results == null) {
			if (other.payment_results != null)
				return false;
		} else if (!payment_results.equals(other.payment_results))
			return false;
		if (signValue == null) {
			if (other.signValue != null)
				return false;
		} else if (!signValue.equals(other.signValue))
			return false;
		if (terminal == null) {
			if (other.terminal != null)
				return false;
		} else if (!terminal.equals(other.terminal))
			return false;
		return true;
	}
	
	
}
