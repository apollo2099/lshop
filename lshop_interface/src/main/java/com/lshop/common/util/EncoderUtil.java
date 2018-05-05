/**
 * <p>Title: EncoderUtil.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */
package com.lshop.common.util;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author dengby
 *
 */
public class EncoderUtil
{
	/**
	 * UTF-8设置编码
	 * @param input
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static final String encode(String input) throws UnsupportedEncodingException{
		return URLEncoder.encode(input,Constants.CHARACTER_ENCODEING);
	}
	
	
	/**
	 * UTF-8解码
	 * @param input
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static final String dencode(String input) throws UnsupportedEncodingException{
		return URLDecoder.decode(input, Constants.CHARACTER_ENCODEING);
	}
	
	public static void main(String args[]) throws UnsupportedEncodingException{
		System.out.println(EncoderUtil.encode("邓兵野"));
		System.out.println(EncoderUtil.dencode(EncoderUtil.encode("邓兵野")));
	}
}
