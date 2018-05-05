package com.gv.html.core;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import com.gv.html.component.HtmlComponent;

public class DefaultHtmlComsumer implements HtmlTaskComsumer{
	protected BlockingQueue<HtmlTask> queue;
	private HtmlComponent htmlComponent;
	private HtmlEventNotify notify;
	/**
	 * 最大等待队列时间/s
	 */
	private final static int WAIT_TIME = 500;
	public DefaultHtmlComsumer(BlockingQueue<HtmlTask> queue, HtmlComponent htmlComponent, HtmlEventNotify notify) {
		this.queue = queue;
		this.htmlComponent = htmlComponent;
		this.notify = notify;
	}
	@Override
	public String call() throws Exception {
		StringBuilder res = new StringBuilder();
		HtmlTask task = null;
		do {
			task = queue.poll(WAIT_TIME, TimeUnit.MILLISECONDS);
			if (null != task) {
				if (!htmlComponent.doStatic(task.getDynamicUrl(), task.getHtmlfile())) {
					res.append(task.getDynamicUrl());
					res.append(System.getProperty("line.separator"));
				}
				notify.fireEvent(HtmlEventType.COMSTASK);
				task = null;
			}
		} while (!queue.isEmpty() || HtmlMonitor.isProducerWorking());
		notify.fireEvent(HtmlEventType.COMSEXIT);
		return res.toString();
	}

}
