package com.lshop.common.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import ognl.DefaultTypeConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.dispatcher.StrutsRequestWrapper;


/**
 * 自定义ongl转换
 * @author tangl
 *@
 */
public class DefineConverter extends DefaultTypeConverter {

	private Log logger=LogFactory.getLog(this.getClass());
	
	@Override
	public Object convertValue(Map context, Object value, Class toType) {
		StrutsRequestWrapper req=(StrutsRequestWrapper) context.get("com.opensymphony.xwork2.dispatcher.HttpServletRequest");
		Object ret=null;
		if(Date.class == toType){
			if(req!=null){
			ret=dateType(((String[])value)[0],req.getParameter("pattern"));
			}else{
			ret=dateType(((String[])value)[0],"yyyy-MM-dd HH:mm:ss");
			}
		}
		return ret;
	}

	/**
	 * 日期转换器
	 * @param date 有效的日期格式
	 * @param pattern 前台界面参定的格式，放在参数中，如果没有指定，使用默认的yyyy-MM-dd
	 * @return 返回格式化后的日期，如果有异常，返回当前日期
	 */
	private Date dateType(String date,String pattern){
		logger.info("date convert:date pattern["+pattern+"]");
		if(pattern==null || pattern.equals("")){
			pattern="yyyy-MM-dd";
			logger.warn("don't pattern use default pattern 'yyyy-MM-dd'");
		}
		DateFormat df=new SimpleDateFormat(pattern);
		Date ret=null;
		try {
			if (date!=null&&!date.equals("")) {
				ret=df.parse(date);
			}
		} catch (ParseException e) {
			logger.error("error date:",e);
			ret=Calendar.getInstance().getTime();
		}
		return ret;
	}

	
	
}
