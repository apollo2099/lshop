package com.lshop.common.payCenter;

import java.util.HashMap;
import java.util.Map;

public class PayConstants {
	
	//支付状态定义
	public static short PAY_STATUS_OK= 1;		//支付成功 
	public static short PAY_STATUS_CANCLE= 2;	//交易取消
	public static short PAY_STATUS_FAILURE = -2;	//支付失败
	
	
	//第三方支付平台列表
	public static String THIRD_PARTY_PAYMENT_PAYDOLLAR= "1";//传款易
	public static String THIRD_PARTY_PAYMENT_ALIPAY= "2";//国内支付宝
	public static String THIRD_PARTY_PAYMENT_ALIPAY_MOBILE= "10";//国内手机支付宝支付
	public static String THIRD_PARTY_PAYMENT_ALIPAYOUT_VISA= "3";//支付宝外卡VISA
	public static String THIRD_PARTY_PAYMENT_ALIPAYOUT_MASTER= "4";//支付宝外卡MASTER
	public static String THIRD_PARTY_PAYMENT_ALIPAYOUT_JCB= "5";//支付宝外卡 JCB
	public static String THIRD_PARTY_PAYMENT_GLOBEBILL= "6";//钱宝
	public static String THIRD_PARTY_PAYMENT_WORLDPAY= "7";//worldpay
	public static String THIRD_PARTY_PAYMENT_MOTO_VISA= "8";//moto支付宝外卡VISA
	public static String THIRD_PARTY_PAYMENT_MOTO_MASTER= "9";//moto支付宝外卡MASTER
	

	
    //币种编码定义
	public static short CURRENCY_CNY=156;
	public static short CURRENCY_USD=840;
	public static short CURRENCY_EUR=978;
	
	
	// 返回错误消息定义
	public static Map<String, String> ERROR_MESSAGE = new HashMap<String, String>();
	static {
		ERROR_MESSAGE.put("not_support_platform", "不支持该支付平台");
		ERROR_MESSAGE.put("not_open_platfrom", "商户尚未开通该平台");
		ERROR_MESSAGE.put("platform_empty", "支付平台不能为空");
		ERROR_MESSAGE.put("sign_error", "签名错误");
		ERROR_MESSAGE.put("not_exist_merchant", "不存在该商户");
		ERROR_MESSAGE.put("info_incomplete", "请求交易参数不完整");
		ERROR_MESSAGE.put("field_length_none", "请求交易参数字段长度未通过");
		ERROR_MESSAGE.put("url_verify_none", "回调url验证未通过 ");
		ERROR_MESSAGE.put("paltform_different", "两次订单平台不一致");
		ERROR_MESSAGE.put("amount_different", "两次订单金额不一致");
		ERROR_MESSAGE.put("amount_min_none", "最小金额不能小于0.01");
		ERROR_MESSAGE.put("amount_decimal_none", "金额小数点后不能超过3位");
		ERROR_MESSAGE.put("faild", "失败，未通过。");
		ERROR_MESSAGE.put("extra_param_none", "公有参数验证未通过");
		ERROR_MESSAGE.put("extra_param_different", "两次公有参数信息内容不一致");
		ERROR_MESSAGE.put("number_verify_none", "数字验证未通过");
		ERROR_MESSAGE.put("currency_different", "两次订单币种不一至");
		ERROR_MESSAGE.put("amount_max_none", "最大金额不能超过10万");
		ERROR_MESSAGE.put("only_support_currency_rmb", "只支持RMB币种");
	}

}
