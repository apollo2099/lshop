package com.lshop.common.payCenter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.gv.core.util.StringUtil;
import com.gv.core.util.cryption.MD5;
public class PayHelper {
	/**
	 * 构造提交表单HTML数据
	 * 
	 * @param params
	 *            请求参数数组
	 * @param gateway
	 *            网关地址
	 * @param strMethod
	 *            提交方式。两个值可选：post、get
	 * @param strButtonName
	 *            确认按钮显示文字
	 * @return 提交表单HTML文本 target from表单提交方式
	 */
	public static String buildForm(Map<String, String> params, String gateway,
			String strMethod, String target) {
		StringBuffer sbHtml = new StringBuffer();
		sbHtml.append("<form   name=\"paysubmit\" method=\"" + strMethod
				+ "\" action=\"" + gateway + "\" target=\"" + target + "\">");
		Iterator<String> it = params.keySet().iterator();
		while (it.hasNext()) {
			String name = it.next();
			String value = params.get(name);
			if("extraParam".equals(name)){//针对json格式
				if(value!=null){
					value=value.replace("'", "");
				}
				sbHtml.append("<input type='hidden' name='" +name + "' value='"
						+ value + "'/>");
			}else{
			    sbHtml.append("<input type=\"hidden\" name=\"" + name + "\" value=\""
					+ value + "\"/>");
			}
		}
		sbHtml.append("</form>");
		return sbHtml.toString();
	}
    /**
     * 生成签名
     * @param str
     * @return
     */
	public static String sign(String str) {
		return MD5.convert32(str);
	}

	/**
	 * post发送url
	 * 
	 * @param urlstr
	 * @param urlparam
	 * @return
	 * @throws Exception
	 */
	public static String postUrl(String urlstr, String urlparam)
			throws Exception {
		URL url = new URL(urlstr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true); // 可以发送数据
		conn.setDoInput(true); // 可以接收数据
		conn.setRequestMethod("POST"); // POST方法
		conn.setRequestProperty("User-Agent",
				"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
		conn.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		conn.connect();
		// 写入的POST数据
		OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream(),
				"UTF-8");
		osw.write(urlparam);
		osw.flush();
		osw.close();
		// 读取响应数据
		BufferedReader in = new BufferedReader(new InputStreamReader(conn
				.getInputStream()));
		String s;
		StringBuffer buf = new StringBuffer("");
		while ((s = in.readLine()) != null) {
			buf.append(s);
		}
		return buf.toString();
	}

	/**
	 * 检查url地址是否合法
	 * 
	 * @param pInput
	 * @return
	 */
	public static boolean checkUrl(String url) {
		if (url == null) {
			return false;
		}
		String regEx = "[a-zA-z]+://[^\\s]*";
		Pattern p = Pattern.compile(regEx);
		Matcher matcher = p.matcher(url);
		return matcher.matches();
	}

	/**
	 * 把IP地址转换成一串数字
	 * 
	 * @param strIp
	 * @return
	 */
	public static long ipToLong(String strIp) {
		long[] ip = new long[4];
		// 先找到IP地址字符串中.的位置
		int position1 = strIp.indexOf(".");
		int position2 = strIp.indexOf(".", position1 + 1);
		int position3 = strIp.indexOf(".", position2 + 1);
		// 将每个.之间的字符串转换成整型
		ip[0] = Long.parseLong(strIp.substring(0, position1));
		ip[1] = Long.parseLong(strIp.substring(position1 + 1, position2));
		ip[2] = Long.parseLong(strIp.substring(position2 + 1, position3));
		ip[3] = Long.parseLong(strIp.substring(position3 + 1));
		return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
	}

	/**
	 * 系统根据时间+4位随机数产生订单交易号
	 * 
	 * @param mark
	 * @return
	 */
	public static synchronized String sysCreateTradeNo() {
		int no = new Random().nextInt(8999);
		no += 1000;
		return StringUtil.dateFormat(new Date(), "yyyyMMddHHmmss") + no;
	}

	/**
	 * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
	 * 
	 * @param params
	 *            需要排序并参与字符拼接的参数组
	 * @return 拼接后字符串
	 */
	public static String createLinkString(Map<String, String> params) {

		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);

		String prestr = "";

		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);

			if (i == keys.size() - 1) {// 拼接时，不包括最后一个&字符
				prestr = prestr + key + "=" + value;
			} else {
				prestr = prestr + key + "=" + value + "&";
			}
		}

		return prestr;
	}

}
