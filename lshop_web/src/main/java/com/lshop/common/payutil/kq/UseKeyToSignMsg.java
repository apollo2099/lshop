package com.lshop.common.payutil.kq;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMReader;
import org.bouncycastle.openssl.PasswordFinder;

/**
 * @Description: 数据加密，算法：SHA-1
 * @Company: 99bill
 */
/**
 * 该类主要用于用于生成加密串，对快钱的必要参数进行加密
 * 加密类
 * */
public class UseKeyToSignMsg {
	private static final Log logger = LogFactory.getLog(UseKeyToSignMsg.class);
	private static UseKeyToSignMsg pks=null;
	
	private String pemPath; //快钱发送的私钥的放置地址
	private String pemPassword; //快钱发送的私钥的密码
	
	public void setPathAndPwd(String pemPath,String pemPassword) {
		this.pemPath = pemPath;
		this.pemPassword = pemPassword;
	}

	/**
	 * 初始化加密类
	 * */
	public static UseKeyToSignMsg initPemKeySign(){
		if(pks==null){
			pks=new UseKeyToSignMsg();
		}
		return pks;
	}

	/**
	 * 获取PEM的KeyStore
	 */
	public KeyPair readPEMKeyPair() {
		KeyPair pair=null;
		Security.addProvider(new BouncyCastleProvider());
		InputStream is = this.getClass().getClassLoader().getResourceAsStream(pemPath);
		if(is!=null){
			Reader fRd = new BufferedReader(new InputStreamReader(is));
			try{
				try {
					PEMReader pemRd = new PEMReader(fRd, new PasswordFinder() {
						public char[] getPassword() {
							return pemPassword.toCharArray();
						}
					});
					pair = (KeyPair) pemRd.readObject();
				} finally {
					if (fRd != null)
						fRd.close();
				}
			}catch(IOException e){
				logger.error("获取私钥出错！");
				e.printStackTrace();
			}
		}
		return pair;
	}
	
	/**
	 * 此类用于生成签名串singMsg
	 * */
	public String dataToSignMsg(byte data[]){
		
		try {
			// 获取PEM的私钥
			KeyPair keyPair = readPEMKeyPair();
			PrivateKey pemKey = keyPair.getPrivate();
			Signature signature = Signature.getInstance("SHA1WithRSA");
			signature.initSign(pemKey);
			signature.update(data);
			byte[] signed = Base64.encodeBase64(signature.sign());
			return new String(signed);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (SignatureException e) {
			e.printStackTrace();
		}
		return null;
	}

}
