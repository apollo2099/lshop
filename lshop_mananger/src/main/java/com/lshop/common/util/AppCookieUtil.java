package com.lshop.common.util;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lshop.common.pojo.ShoppingCartBeanForCookie;

/**
 * cookie操作（目前只限于购物车模块）
 * @author zhengxue
 * @since 2.0 2012-08-21
 *
 */
public class AppCookieUtil{
	private static final Log logger = LogFactory.getLog(AppCookieUtil.class);
	private static final long serialVersionUID = -1685947521178427981L;

	/**
	 * str为从cookie中获取的字符串，分别按regex1和regex2进行拆分，并将id和数量以ShoppingCartBeanForCookie对象
	 * 形式保存到list中
	 */
	public static List<ShoppingCartBeanForCookie> parseString(String str,String regex1,String regex2,String regex3){
		List<ShoppingCartBeanForCookie> list = new ArrayList<ShoppingCartBeanForCookie>();
		ShoppingCartBeanForCookie cart;
		String s = str.substring(1, str.length());
		//判断有几条数据
		if(s.indexOf(regex1)!=-1){
			if(s.contains(regex1) ==true){
				String string[] = s.split(regex1);
				for(String ss:string){
					if(ss.contains(regex2) ==true){
						cart = new ShoppingCartBeanForCookie();
						String result[] = ss.split(regex2);
						cart.setStoreId(result[0]);
						if(result[1].toString().contains(regex3)==true){
							String re[] = result[1].split(regex3);
							cart.setCode(re[0]);
							cart.setShopNum(Integer.parseInt(re[1]));
						}
						list.add(cart);
						
					}else{
						logger.error("字符串不匹配！");
						break;
					}
				}
				
			}else{
				logger.error("字符串不匹配！");
			}
		}else{
			if(s.contains(regex2) ==true){
				cart = new ShoppingCartBeanForCookie();
				String result[] = s.split(regex2);
				cart.setStoreId(result[0]);
				if(result[1].toString().contains(regex3)==true){
					String re[] = result[1].split(regex3);
					cart.setCode(re[0]);
					cart.setShopNum(Integer.parseInt(re[1]));
				}
				list.add(cart);
			}else{
				logger.error("字符串不匹配！");
			}
		}
		return list;
	}
	
	/**
	 * 将给定的字符串变成#000abc:0088格式,id最多6位，数量最大4位
	 */
	public static String toCookieString(String id,String num){
			StringBuilder sbd=new StringBuilder();
			String sid = getSuitableString(id,6);
			String snum = getSuitableString(num,4);
			sbd.append("#").append(sid).append(":").append(snum);
		return sbd.toString().trim();
	}
	
	/**
	 * source为从cookie获取到的字符串，tag为#id:num形式，是要添加至cookie的字符串
	 * 如果cookie中已经存在tag中的id，则不直接添加，只修改source中已经存在对应id的数量
	 */
	public static String addString2Cookie(String source,String tag){
		StringBuilder sbd=new StringBuilder();
		String combId = tag.substring(0, 7);//获取id的字符串，为#id格式
		if(null==source){
			sbd.append(tag);
		}else{
			if(source.contains(combId)){
				int i = source.indexOf(combId);
				String p = source.substring(i+8,i+12);
				Integer price = Integer.valueOf(p)+Integer.valueOf(tag.substring(8,12));			
				String target = source.substring(i, i+12);
				String replacement = tag.substring(0, 8)+getSuitableString(String.valueOf(price),4);
				sbd.append(source.replace(target, replacement));
			}else{
				sbd.append(source).append(tag);
			}
		}
		return sbd.toString().trim();
	}
	
	/**
	 * 根据id删除cookie中保存保存的相应id对应的字符串
	 */
	public static String deleteTagString(String source,String id){
		StringBuilder sbd=new StringBuilder();
		String tag = sbd.append("#").append(getSuitableString(id,6)).toString().trim();
		if(source.contains(tag)){
			int i = source.indexOf(tag);
			logger.info(source.substring(i,i+12));
			source = source.replace(source.substring(i,i+12), "");
		}else{
			logger.error("指定的id不存在！");
		}
		return source.trim();
	}
	
	/**
	 * 获取指定的cookie字符串
	 */
	public static String getCookieByKey(HttpServletRequest request,String key){
		
		String s=null;
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(Cookie c:cookies){
				if(c.getName().equals(key)){
				s = c.getValue();
				}
			}
		}
		return s;
	}
	
	/**
	 * 根据给定的位数len获取适合的字符串
	 */
	public static String getSuitableString(String source,int len){
		StringBuilder sbd=new StringBuilder();
		int length = source.length();
		if(len<length){
			logger.error("给定长度小于给定字符串！");
		}else{
			for(int i=0;i<len-length;i++){
				sbd.append(0);
			}
			sbd.append(source);
		}	
		return sbd.toString().trim();
	}
	
	/**
	 * 根据指定的id和num修改cookie中的num值
	 */
	public static String updateCookieString(String source,String id,String num){
		String sid = "#"+getSuitableString(id, 6);
		String snum = getSuitableString(num, 4);
		if(source.contains(sid)){
			int i = source.indexOf(sid);
			source = source.replace(source.substring(i+8, i+12), snum);
		}else{
			
		}
		return source;
	}
}
