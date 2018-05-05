package com.lshop.common.payutil.qh.vo;

public class OrderPayReturnVo {
	private String response_type;
	private String account;
	private String terminal;
	private String signValue;
	private String order_number;
	private String order_amount;
	private String order_currency;
	private String order_notes;
	private String card_number;
	private String payment_id;
	private String payment_authType;
	private String payment_status;
	private String payment_details;
	private String payment_risk;
	
	public OrderPayReturnVo(){
		
	}
	
	public OrderPayReturnVo(String response_type, String account,
			String terminal, String signValue, String order_number,
			String order_currency, String order_notes, String card_number,
			String payment_id, String payment_authType, String payment_status,
			String payment_details, String payment_risk) {
		super();
		this.response_type = response_type;
		this.account = account;
		this.terminal = terminal;
		this.signValue = signValue;
		this.order_number = order_number;
		this.order_currency = order_currency;
		this.order_notes = order_notes;
		this.card_number = card_number;
		this.payment_id = payment_id;
		this.payment_authType = payment_authType;
		this.payment_status = payment_status;
		this.payment_details = payment_details;
		this.payment_risk = payment_risk;
	}
	public String getResponse_type() {
		return response_type;
	}
	public void setResponse_type(String response_type) {
		this.response_type = response_type;
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
	
	public String getOrder_amount() {
		return order_amount;
	}

	public void setOrder_amount(String order_amount) {
		this.order_amount = order_amount;
	}

	public String getOrder_currency() {
		return order_currency;
	}
	public void setOrder_currency(String order_currency) {
		this.order_currency = order_currency;
	}
	public String getOrder_notes() {
		return order_notes;
	}
	public void setOrder_notes(String order_notes) {
		this.order_notes = order_notes;
	}
	public String getCard_number() {
		return card_number;
	}
	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}
	public String getPayment_id() {
		return payment_id;
	}
	public void setPayment_id(String payment_id) {
		this.payment_id = payment_id;
	}
	public String getPayment_authType() {
		return payment_authType;
	}
	public void setPayment_authType(String payment_authType) {
		this.payment_authType = payment_authType;
	}
	public String getPayment_status() {
		return payment_status;
	}
	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}
	public String getPayment_details() {
		return payment_details;
	}
	public void setPayment_details(String payment_details) {
		this.payment_details = payment_details;
	}
	public String getPayment_risk() {
		return payment_risk;
	}
	public void setPayment_risk(String payment_risk) {
		this.payment_risk = payment_risk;
	}
	
}
