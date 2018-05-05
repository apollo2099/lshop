package com.gv.html.core;

/**
 * 生产者信号量
 * @author caicl
 *
 */
public class ProducerSemaphore {
	private static int PRODUCER_NUM = 0;
	
	/**
	 * 增加num个生产者
	 * @param num
	 */
	public static void add(int num) {
		PRODUCER_NUM = PRODUCER_NUM + num;
	}
	/**
	 * 生产者结束
	 */
	public static void complete() {
		if (PRODUCER_NUM > 0) {
			PRODUCER_NUM--;
		}
	}
	/**
	 * 生产者是否正在工作
	 * @return
	 */
	public static boolean working() {
		return PRODUCER_NUM > 0;
	}
}
