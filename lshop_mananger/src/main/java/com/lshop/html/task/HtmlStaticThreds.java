package com.lshop.html.task;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.lshop.html.service.HtmlStaticService;

public class HtmlStaticThreds extends Thread {

	private int threadCnt = 1;
	private HtmlStaticService taskserver;
	private static final Log logger = LogFactory.getLog(HtmlStaticThreds.class);



	@Override
	public void run() {
		try {
			taskserver.createHtmlByURL();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
		}
	}

	public int getThreadCnt() {
		return threadCnt;
	}

	public HtmlStaticService getTaskserver() {
		return taskserver;
	}

	public void setThreadCnt(int threadCnt) {
		this.threadCnt = threadCnt;
	}

	public void setTaskserver(HtmlStaticService taskserver) {
		this.taskserver = taskserver;
	}

}
