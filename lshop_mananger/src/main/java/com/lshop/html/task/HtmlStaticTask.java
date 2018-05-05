package com.lshop.html.task;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gv.core.thread.CommonTask;
import com.lshop.html.service.HtmlStaticService;

public class HtmlStaticTask implements CommonTask {

	private int threadCnt = 1;
	private HtmlStaticService taskserver;
	private static final Log logger = LogFactory.getLog(HtmlStaticTask.class);

	@Override
	public void doTask() {
		if (taskserver != null) {
			try {
				taskserver.createHtmlByURL();
			} catch (IOException e) {
				logger.error(e);
			}
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

	@Override
	public void setParam(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		
	}

}
