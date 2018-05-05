package com.lshop.common.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class DoubleUtils {
	/**
	 * 
	 * @Description: 加法
	 * @author CYJ
	 * @date 2013-12-16 上午11:29:58
	 * @param value1
	 * @param value2
	 * @param num
	 *            小数位数
	 * @return
	 */
	public static Double add(Number value1, Number value2, int num) {
		BigDecimal b1 = new BigDecimal(Double.toString(value1.doubleValue()));
		BigDecimal b2 = new BigDecimal(Double.toString(value2.doubleValue()));
		return b1.add(b2).setScale(num, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 
	 * @Description: 减法
	 * @author CYJ
	 * @date 2013-12-16 上午11:30:12
	 * @param value1
	 * @param value2
	 * @param num
	 *            小数位数
	 * @return
	 */
	public static Double sub(Number value1, Number value2, int num) {
		BigDecimal b1 = new BigDecimal(Double.toString(value1.doubleValue()));
		BigDecimal b2 = new BigDecimal(Double.toString(value2.doubleValue()));
		return b1.subtract(b2).setScale(num, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 
	 * @Description:乘法
	 * @author CYJ
	 * @date 2014-2-27 上午10:40:14
	 * @param value1
	 * @param value2
	 * @param num
	 * @return
	 */
	public static Double mul(Number value1, Number value2, int num) {
		BigDecimal b1 = new BigDecimal(Double.toString(value1.doubleValue()));
		BigDecimal b2 = new BigDecimal(Double.toString(value2.doubleValue()));
		return b1.multiply(b2).setScale(num, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 
	 * @Description:乘法
	 * @author CYJ
	 * @date 2014-4-2 下午3:00:30
	 * @param value1
	 * @param value2
	 * @return
	 */
	public static Double mul(Number value1, Number value2) {
		BigDecimal b1 = new BigDecimal(Double.toString(value1.doubleValue()));
		BigDecimal b2 = new BigDecimal(Double.toString(value2.doubleValue()));
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * @Description:保留小数
	 * @author CYJ
	 * @date 2014-1-15 下午4:42:27
	 * @param val
	 * @param num
	 *            小数位数
	 * @return
	 */
	public static String formatDouble(Double val, int num) {
		String format = "0.00";
		if (num == 1) {
			format = "0.0";
		} else if (num == 2) {
			format = "0.00";
		} else if (num == 3) {
			format = "0.000";
		} else if (num == 4) {
			format = "0.0000";
		} else if (num == 0) {
			format = "0";
		}
		DecimalFormat df = new DecimalFormat(format);

		return df.format(val);
	}
}
