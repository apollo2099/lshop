package com.gv.html.core;

public class HtmlMonitor implements Runnable, HtmlEventListener{
	private static int producerNum;
	private static int comsumerNum;
	private static int findTask;
	private static int finishTask;
	/**
	 * 运行周期
	 */
	private long period = 2000;
	/**
	 * 引擎是否开始运行
	 */
	private volatile boolean running = false;
	/**
	 * 引擎开始运行时间
	 */
	private long startTime;
	private HtmlMonitor() {
		
	}
	public static String report(){
		StringBuilder sb = new StringBuilder();
		sb.append("p: ");
		sb.append(producerNum);
		sb.append(", c: ");
		sb.append(comsumerNum);
		sb.append(", f:");
		sb.append(findTask);
		sb.append(", d:");
		sb.append(finishTask);
		System.out.println(sb.toString());
		return sb.toString();
	}
	@Override
	public void run() {
		while (running) {
			report();
			try {
				Thread.sleep(period);
			} catch (InterruptedException e) {
				return;
			}
		}
	}
	
	/**
	 * 每隔一周期执行的操作
	 * @param period
	 */
	public static synchronized HtmlEventNotify getNotify() {
		HtmlMonitor monitor = new HtmlMonitor();
		monitor.running = true;
		Thread mt = new Thread(monitor);
		mt.setName("HtmlMonitor");
		mt.setDaemon(true);
		mt.start();
		return new HtmlEventNotify(monitor);
	}
	@Override
	public synchronized void onAddComsEvent(int num) {
		comsumerNum = comsumerNum + num;
	}
	@Override
	public synchronized void onAddProdEvent(int num) {
		producerNum = producerNum + num;
	}
	@Override
	public synchronized void onAddTaskEvent(int num) {
		findTask += num;
	}
	@Override
	public synchronized void onComsExitEvent() {
		comsumerNum--;
	}
	@Override
	public synchronized void onProdExitEvent() {
		producerNum--;
	}
	@Override
	public synchronized void onComsTaskEvent() {
		finishTask++;
	}
	@Override
	public void onEnginStart() {
		startTime = System.currentTimeMillis();
		findTask = 0;
		finishTask = 0;
	}
	@Override
	public void onEngineStop() {
		running = false;
		long cost = System.currentTimeMillis() - startTime;
		System.err.println("html cost "+cost+"ms to finish "+finishTask+" task");
	}
	/**
	 * 判断生成者是否正在运行
	 * @return
	 */
	public static synchronized boolean isProducerWorking(){
		return producerNum > 0;
	}
}
