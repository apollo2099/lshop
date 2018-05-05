package com.lshop.common.util;

import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gv.core.util.StringUtil;

public class OrderHelp {
	private static final Log logger = LogFactory.getLog(OrderHelp.class);
	public static synchronized String  createOrderId(String mark){
		int no=new Random().nextInt(99999);
		while (no<10000||no>99999) {
			no=new Random().nextInt(99999);
		}
		return mark+StringUtil.dateFormat(new Date(), "yyyyMMddHHmmss")+no;
	}
	/**
	 * 生成含标识的随机码
	 * @param type
	 * @return
	 */
	public static synchronized String  createCouponCode(String type){
		String code="";
		//生成7位随机密码
		int codeint=new Random().nextInt(9999999);
		while (codeint<1000000||codeint>=10000000) {
			codeint=new Random().nextInt(9999999);
		}
		code=type+codeint;
		return code;
	}
	
	public static synchronized String  createCouponCode(){
		String code="";
		Date nowdate=new Date();
		String datecode=doBreakUpChar(StringUtil.dateFormat(nowdate, "yydd"), getRandomNum(1,true));
		datecode=doBreakUpChar(datecode, getRandomNum(1,true));
		code+=getRandomNum(2,false)+StringUtil.dateFormat(nowdate, "HHMM");
		code+=datecode;
		return code;
      }
	/**
	 * 打乱字符
	 * @param code
	 * @param separation
	 * @return
	 */
	public static String doBreakUpChar(String code,String separation){
		 StringBuffer codeBuffer=new StringBuffer(code);
		 int num=0;
		 do {
			 num=new Random().nextInt(code.length()+1);
		} while (num<0||num>code.length());
		 codeBuffer.insert(num, separation);
		 return codeBuffer.toString();
		
		
	}
	/**
	 * 生成指定位数的随机码
	 * @param count
	 * @return
	 */
	public static  String getRandomNum(int count,boolean ischar) {
	    String str="";
	    if (ischar) {
	    	  for (int i = 0; i < count;) {
	  	    	int no=new Random().nextInt(91);
  				if (no>=65&&no<=90&&no!=79) {
  					str+=((char)no);
  					i++;
  				}
	    	  }
	    }else {
	  	  for (int i = 0; i < count;) {
	  		int no=new Random().nextInt(60);
	    	if (no>=48&&no<=57) {
				str+=((char)no);
				i++;
			}
		 }
			
		}
		return str;
	}
	public static void main(String[] args) {
		while(true) {
			String codeString=	createCouponCode("9");
			if(logger.isInfoEnabled()){
				  logger.info(codeString);
			}
			if (codeString.length()!=8) {
				break;
			}
		}
}
}
