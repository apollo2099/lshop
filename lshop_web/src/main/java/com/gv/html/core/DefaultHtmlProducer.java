package com.gv.html.core;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import com.gv.html.service.HtmlService;
import com.lshop.common.pojo.logic.LvLinkUrl;

public class DefaultHtmlProducer implements HtmlTaskProducer{
	protected BlockingQueue<HtmlTask> queue;
	protected HtmlService htmlService;
	protected Map<String, String> params;
	protected HtmlEventNotify notify;
	public DefaultHtmlProducer(BlockingQueue<HtmlTask> queue, HtmlService htmlService, Map<String, String> params, HtmlEventNotify notify) {
		this.queue = queue;
		this.htmlService = htmlService;
		this.params = params;
		this.notify = notify;
	}
	
	@Override
	public String call() throws Exception {
		// get shop urls
		String shopFlag = params.get("shopFlag");
		String shopDomain = params.get("shopDomain");
		String htmlPath = params.get("htmlPath");
		List<LvLinkUrl> urls = htmlService.getAllLinkByShop(shopFlag);
		LvLinkUrl lvLinkUrl = null;
		for (Iterator<LvLinkUrl> iterator = urls.iterator(); iterator.hasNext();) {
			lvLinkUrl = iterator.next();
			queue.put(convertUrl2Task(lvLinkUrl, shopDomain, htmlPath));
		}
		notify.fireEvent(HtmlEventType.ADDTASK, urls.size());
		notify.fireEvent(HtmlEventType.PRODEXIT);
		return "";
	}

	/**
	 * 生成html任务
	 * @param url
	 * @param domain
	 * @param htmlpath
	 * @return
	 */
	private HtmlTask convertUrl2Task(LvLinkUrl url, String domain, String htmlpath) {
		HtmlTask task = new HtmlTask();
		String durl = "http://"+domain+url.getDynamicurl();
		String hfile = htmlpath + url.getStaticurl();
		task.setDynamicUrl(durl);
		task.setHtmlfile(hfile);
		return task;
	}
}
