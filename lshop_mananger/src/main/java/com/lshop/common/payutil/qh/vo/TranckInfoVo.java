package com.lshop.common.payutil.qh.vo;

public class TranckInfoVo {
	//返回商户号
    private String account;
	//返回终端号
    private String terminal;
    //交易安全签名
    private String signValue;
    //支付唯一号
    private String payment_id ;
    //上传物流结果
    private String tracking_results;
    
    public TranckInfoVo(){
    	
    }
    
	public TranckInfoVo(String account, String terminal, String signValue,
			String payment_id, String tracking_results) {
		super();
		this.account = account;
		this.terminal = terminal;
		this.signValue = signValue;
		this.payment_id = payment_id;
		this.tracking_results = tracking_results;
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
	public String getPayment_id() {
		return payment_id;
	}
	public void setPayment_id(String payment_id) {
		this.payment_id = payment_id;
	}

	public String getTracking_results() {
		return tracking_results;
	}

	public void setTracking_results(String tracking_results) {
		this.tracking_results = tracking_results;
	}


	
    
    
}
