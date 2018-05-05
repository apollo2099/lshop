package com.lshop.manage.weixin.util;

import java.util.Date;


public class DateUtil {
	/***
	 * 计算两个日期之间相差多少秒
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public  static int calLastedTime(Date startDate,Date endDate) {
		  long a = endDate.getTime();
		  long b = startDate.getTime();
		  int c = (int)((a - b) / 1000);
		  return c;
		 }
	
	

}
