package com.lshop.common.payutil.kq;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @title: VeriSign
 * @Description: 数据验签，算法：SHA-1
 * @Company: 99bill
 */
/**
 * 该类主要用于验证返回的字符串是否正确
 * 验签类
 * */
public class VerifySignature {
	private static final Log logger = LogFactory.getLog(VerifySignature.class);
	private static VerifySignature verifySignature=null;
	
	private String publickey; //快钱公钥地址

	public void setPublickey(String publickey) {
		this.publickey = publickey;
	}

	/**
	 * 初始化验签类
	 * */
	public static VerifySignature initVerifySignature(){
		if(verifySignature==null){
			verifySignature=new VerifySignature();
		}
		return verifySignature;
	}
	
	/**
	 * 获取公钥
	 * publickey:公钥地址
	 * @throws IOException
	 * @throws CertificateException
	 * */
	private PublicKey getPublicKey() {		
		try {
			// 开始获取快钱提供的公钥
			InputStream is = this.getClass().getClassLoader().getResourceAsStream(publickey);
			if (is != null) {
				// 通过加密算法获取公钥
				Certificate cert = null;
				try {
					CertificateFactory cf = CertificateFactory
							.getInstance("X.509"); // 指定证书类型
					cert = cf.generateCertificate(is); // 获取证书
					return cert.getPublicKey(); // 获得公钥
				} finally {
					if (is != null) {
						is.close();
					}
				}
			}
		} catch (IOException e) {
			logger.error("无法获取url连接");
			e.printStackTrace();
		} catch (CertificateException e) {
			logger.error("获取证书失败");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 验签
	 * signature:快钱返回的加密串参数signature
	 * data_sign：快钱返回的其他参数值的拼接串
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws SignatureException
	 */
	public boolean verifySign(String data_sign,String signature,String charset) {
		
		PublicKey publicKey = getPublicKey(); // 加载公钥
		
		// 验签
		Signature sig=null;
		byte[] signed;
		try {
			byte[] data = data_sign.getBytes(charset); // 对返回的组合字符串进行编码
			byte[] signData = signature.getBytes(charset); // 对返回的签名字符串进行编码
			//开始进行验签
			sig = Signature.getInstance("SHA1WithRSA"); //初始化加密算法
			signed = Base64.decodeBase64(signData);     //数据加密
			sig.initVerify(publicKey);     //初始化公钥用于验证的对象
			sig.update(data);  //验证数据
			return sig.verify(signed);  //验证传入的签名
		}catch (UnsupportedEncodingException e) {
			logger.error("编码错误");
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			logger.error("初始化加密算法时报错");
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			logger.error("初始化公钥时报错");
			e.printStackTrace();
		} catch (SignatureException e) {
			logger.error("验证数据时报错");
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 签名字符串验证
	 * @param request
	 * @return true=>合法；false=>不合法
	 */
	public boolean verifySign(HttpServletRequest request) {
		// 外卡账户（接收款项的商户外卡账户编号 ） 
        String merchantAcctId = request.getParameter("merchantAcctId");

        // 终端编号（外卡网关终端编号）
        String termId = request.getParameter("termId");

        // 网关版本（固定值 v1.0  注意为小写字母） 
        String version = request.getParameter("version");

        // 网关页面显示语言种类（固定值：2，2代表英文显示）
        String language = request.getParameter("language");

        // 签名类型（固定值：1，1代表SHA-1 签名）
        String signType = request.getParameter("signType");

        // 支付方式（固定值：20，20：外卡支付网关保留专）
        String payType = request.getParameter("payType");

        //银行代码（返回用户在实际支付时所使用的银行代码。扩展字段）
        String bankId = request.getParameter("bankId");

        // 商户订单号（只允许使用字母、数字、- 、_,并以字母或数字开头每商户提交的订单号，必须在自身账户交易中唯一）
        String orderId = request.getParameter("orderId");

        // 商户订单提交时间（格式为：年[4 位]月[2 位]日[2 位]时[2 位]分[2 位]秒[2 位]例如：20071117020101）
        String orderTime = request.getParameter("orderTime");

        // 订单报价币别（货币符号，如USD、EUR 等）
        String pricingCurrency = request.getParameter("pricingCurrency");

        // 订单报价金额 （金额乘以100。例如，美元298.30，提交时金额应为29830）
        String pricingAmount = request.getParameter("pricingAmount");

        // 订单扣款币别（货币符号，如USD、EUR 等 ）
        String billingCurrency = request.getParameter("billingCurrency");

        // 订单扣款金额（金额乘以100。例如，美元298.30，提交时金额应为29830）
        String billingAmount = request.getParameter("billingAmount");

        // 汇率提供标志（0 由快钱统一提供汇率 ，1 由商户提交交易时提供 ）
        String exchangeRateFlag = request.getParameter("exchangeRateFlag");

        // 汇率方向（0 报价币别/扣款币别   1 扣款币别/报价币别，如果参数exchangeRateFlag 为整型数字 0，则由快钱返回。 如果exchangeRateFlag为1，则按商户提交信息原样返回。）
        String exchangeRateDirection = request.getParameter("exchangeRateDirection");

        // 汇率（交易时汇率 。如果参数exchangeRateFlag 为整型数字 0，则由快钱返回。 如果exchangeRateFlag为1，则按商户提交信息原样返回。）
        String exchangeRate = request.getParameter("exchangeRate");

        // 快钱交易号（该交易在快钱系统中对应的交易号）
        String dealId = request.getParameter("dealId");

        // 银行交易交易号（该交易在银行支付时对应的交易号，如果不是通过银行卡支付，则为空 。扩展字段 ）
        String bankDealID = request.getParameter("bankDealID");

        // 快钱交易时间（格式为：年[4 位]月[2 位]日[2 位]时[2 位]分[2 位]秒[2位] ，例如：20071117020101）
        String dealTime = request.getParameter("dealTime");

        // 实际支付金额（返回在使用优惠券等情况后，用户实际支付的金额 。金额乘以100。例如，美元298.30，提交时金额应为29830）
        String payAmount = request.getParameter("payAmount");

        // 支付币别
        String payCurrency = request.getParameter("payCurrency");

        // 费用（快钱收取商户的手续费，单位为分。 ）
        String fee = request.getParameter("fee");

        // 处理结果（10：支付成功   11：支付失败 ）
        String payResult = request.getParameter("payResult");

        // 错误代码（如果处理结果返回11，则有errCode 和errMessage 。错误代码和错误信息详细资料见参考资料。）
        String errCode = request.getParameter("errCode");

        // 错误信息（错误代码的英文描述。 如果处理结果返回11，则有errCode和errMessage 错误代码和错误信息详细资料见图表 4 错误代码表）
        String errMessage = request.getParameter("errMessage");

        // 扩展字段一（支付完成后，按照原样返回给商户 ）
        String ext1 = request.getParameter("ext1");

        // 扩展字段二（支付完成后，按照原样返回给商户 ）
        String ext2 = request.getParameter("ext2");

        // 签名字符串（快钱利用证书对发送数据进行签名 ）
        String signMsg = request.getParameter("signMsg");
        
        // 将不为空参数值组成字符串
        // 快钱返回给商户时的签名串示例如下（假定全部参数值都不为空）：
        StringBuffer signMsgVal = null;	// 将非空的参数组成字符串
        signMsgVal = ParamConn.appendParam(signMsgVal, "merchantAcctId", merchantAcctId);
        signMsgVal = ParamConn.appendParam(signMsgVal, "termId", termId);
        signMsgVal = ParamConn.appendParam(signMsgVal, "version", version);
        signMsgVal = ParamConn.appendParam(signMsgVal, "language", language);
        signMsgVal = ParamConn.appendParam(signMsgVal, "signType", signType);
        signMsgVal = ParamConn.appendParam(signMsgVal, "payType", payType);
        signMsgVal = ParamConn.appendParam(signMsgVal, "bankId", bankId);
        signMsgVal = ParamConn.appendParam(signMsgVal, "orderId", orderId);
        signMsgVal = ParamConn.appendParam(signMsgVal, "orderTime", orderTime);
        signMsgVal = ParamConn.appendParam(signMsgVal, "pricingCurrency", pricingCurrency);
        signMsgVal = ParamConn.appendParam(signMsgVal, "pricingAmount", pricingAmount);
        signMsgVal = ParamConn.appendParam(signMsgVal, "billingCurrency", billingCurrency);
        signMsgVal = ParamConn.appendParam(signMsgVal, "billingAmount", billingAmount);
        signMsgVal = ParamConn.appendParam(signMsgVal, "exchangeRateFlag", exchangeRateFlag);
        signMsgVal = ParamConn.appendParam(signMsgVal, "exchangeRateDirection", exchangeRateDirection);
        signMsgVal = ParamConn.appendParam(signMsgVal, "exchangeRate", exchangeRate);
        signMsgVal = ParamConn.appendParam(signMsgVal, "dealId", dealId);
        signMsgVal = ParamConn.appendParam(signMsgVal, "bankDealID", bankDealID);
        signMsgVal = ParamConn.appendParam(signMsgVal, "dealTime", dealTime);
        signMsgVal = ParamConn.appendParam(signMsgVal, "payAmount", payAmount);
        signMsgVal = ParamConn.appendParam(signMsgVal, "payCurrency", payCurrency);
        signMsgVal = ParamConn.appendParam(signMsgVal, "fee", fee);
        signMsgVal = ParamConn.appendParam(signMsgVal, "payResult", payResult);
        signMsgVal = ParamConn.appendParam(signMsgVal, "errCode", errCode);
        signMsgVal = ParamConn.appendParam(signMsgVal, "ext1", ext1);
        signMsgVal = ParamConn.appendParam(signMsgVal, "ext2", ext2);
        
		return verifySign(signMsgVal.toString(), signMsg, "utf-8");
	}
}
