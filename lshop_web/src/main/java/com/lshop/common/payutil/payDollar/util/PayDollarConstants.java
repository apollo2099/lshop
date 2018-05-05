package com.lshop.common.payutil.payDollar.util;

import java.util.HashMap;
import java.util.Map;

public class PayDollarConstants {
	//币种
	public static String CURRENCY_HKD="HKD";//港元
	public static String CURRENCY_USD="USD";//美元
	public static String CURRENCY_SGD="SGD";//新加坡元
	public static String CURRENCY_CNY="CNY";//人民币
	public static String CURRENCY_RMB="RMB";//人民币
	public static String CURRENCY_JPY="JPY";//日元
	public static String CURRENCY_TWD="TWD";//新台币
	public static String CURRENCY_AUD="AUD";//澳元
	public static String CURRENCY_EUR="EUR";//港元
	public static String CURRENCY_GBP="GBP";//英镑
	public static String CURRENCY_CAD="CAD";//加拿大元
	
	
	//币种对应接口币种码
	public static Map<String ,String> CURRCODES=new HashMap<String ,String>();
	static{
		CURRCODES.put(CURRENCY_HKD, "344");
		CURRCODES.put(CURRENCY_USD, "840");
		CURRCODES.put(CURRENCY_SGD, "702");
		CURRCODES.put(CURRENCY_CNY, "156");
		CURRCODES.put(CURRENCY_RMB, "156");
		CURRCODES.put(CURRENCY_JPY, "392");
		CURRCODES.put(CURRENCY_TWD, "901");
		CURRCODES.put(CURRENCY_AUD, "036");
		CURRCODES.put(CURRENCY_EUR, "978");
		CURRCODES.put(CURRENCY_GBP, "806"); 
		CURRCODES.put(CURRENCY_CAD, "124");
	}
	
	//语言
	public static String LANG_C="C";//繁体中文
	public static String LANG_E="E";//英文
	public static String LANG_X="X";//简体中文
	public static String LANG_K="K";//朝鲜语
	public static String LANG_J="J";//日语
	public static String LANG_T="T";//泰国语
	
	//支付类型
	public static String PAYTYPE_N="N";//消费交易
	public static String PAYTYPE_H="H";//预授权交易
	
	//支付方式
	public static String PAYMETHOD_ALL="ALL";//所有有效的支付方式
	public static String PAYMETHOD_CC="CC";//信用卡支付
	public static String PAYMETHOD_PPS="PPS";//PayDollar的pps支付
	public static String PAYMETHOD_PAYPAL="PAYPAL";//PayDollar的paypal支付
	public static String PAYMETHOD_CHINAPAY="CHINAPAY";//PayDollar的China unoinPay支付
	public static String PAYMETHOD_ALLPAY="ALIPAY";//PayDollar的ALIPAY支付
	public static String PAYMETHOD_TENPAY="TENPAY";//PayDollar的TENPAY支付
	public static String PAYMETHOD_99BILL="99BILL";//PayDollar的99BILL支付
	
	
	//多币种处理服务(MPS)模式
	public static String MPSMode_NIL="NIL";//关闭MPS模式
	public static String MPSMode_SCP="SCP";//开启MPS简单货币转换
	public static String MPSMode_DCC="DCC";//开启MPS动态货币转换
	public static String MPSMode_MCP="MCP";//开启MPS多币种计价
	
	//返回支付接受情况
	public static String SUCCESSCODE_0="0";//成功
	public static String SUCCESSCODE_1="1";//失败
	
	
	//主要相应码主要对应付款交易状态
	public static String PRC_0="0";//成功
	public static String PRC_1="1";//被付款银行拒绝
	public static String PRC_3="3";//付款人认证失败
	public static String PRC_01="-1";//参数传输不正确
	public static String PRC_02="-2";//服务器访问错误
	public static String PRC_08="-8";//被传款易内部/防欺诈检查拒绝
	public static String PRC_09="-9";//主机访问错误
	public static String PRC_010="-10";//系统错误
}
