package com.gv.html.core;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gv.html.component.HtmlComponent;
import com.gv.html.service.HtmlService;
import com.lshop.common.cache.component.CacheComponent;
import com.lshop.common.util.Constants;

/**
 * 静态化指导者
 * @author caicl
 *
 */
@Service
public class HtmlEngine implements Runnable{
	public final Log logger = LogFactory.getLog(this.getClass());
	
	/**
	 * 引擎是否在运行
	 */
	private volatile boolean RUNNING = false;
	@Resource
	private HtmlComponent htmlComponent;
	@Resource
	private HtmlService htmlService;
	@Resource
	private CacheComponent cacheComponent;
	/** 
	 * @Fields jobQueue : 静态化任务队列
	 */ 
	private BlockingQueue<HtmlTask> jobQueue = new LinkedBlockingQueue<HtmlTask>();
	private String[] shopFlag = new String[]{};
	/**
	 * 所属店铺
	 */
	@Value("${lshop.html.include.shop}")
	private String shops;
	/**
	 * 静态文件生成路径
	 */
	@Value("${lshop.html.path}")
	private String htmlPath;
	
	/**
	 * 工作线程数
	 */
	private final int NumWorkor = 3;
	/**
	 * 事件通知者
	 */
	private HtmlEventNotify notify;
	/**
	 * 设置引擎执行店铺
	 * @param shopFlag
	 */
	private void setShopFlag(String[] shopFlag) {
		Set<String> shopList = new HashSet<String>();
		String[] includeShop = shops.split(",");
		for(String shop : shopFlag) {
			for(String ishop : includeShop){
				if (ishop.equalsIgnoreCase(shop)) {
					shopList.add(shop);
					break;
				}
			}
		}
		this.shopFlag = shopList.toArray(new String[]{});
	}
	/**
	 * 异步执行静态化
	 */
	public synchronized boolean asynchronExcute(String[] shopFlag) {
		if (RUNNING) {
			System.out.println("html engine have bean started!");
			return false;
		}
		RUNNING = true;
		setShopFlag(shopFlag);
		Thread engin = new Thread(this);
		engin.setDaemon(true);
		engin.setName("Html Engin");
		engin.start();
		return true;
	}
	/**
	 * 全静态化
	 * @return
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	private boolean excuteAll() {
		logger.info("html engine start");
		if (shopFlag.length == 0) {
			//没有店铺,直接返回
			return true;
		}
		StringBuilder tip = new StringBuilder();
		List<Future<String>> futureList = new ArrayList<Future<String>>();
		ExecutorService service = Executors.newFixedThreadPool(NumWorkor);
		addShop(service, futureList, shopFlag);
		addComsumer(service, futureList, NumWorkor);
		try {
			for (Iterator<Future<String>> iterator = futureList.iterator(); iterator.hasNext();) {
					tip.append(iterator.next().get());
			}
		} catch (InterruptedException e) {
			logger.error(e.getMessage());
		} catch (ExecutionException e) {
			logger.error(e.getMessage());
		} finally {
			service.shutdown();
		}
		System.out.println("html engine end");
		if (tip.length()>0) {
			logger.error("以下链接静态化失败:\n");
			logger.error(tip.toString());
		}
		return true;
	}
	/**
	 * 返回静态化引擎状态
	 * @return
	 */
	public String getStatus(){
		return RUNNING ? "active" : "waiting";
	}
	private void addShop(ExecutorService service, List<Future<String>> futureList, String[] shops) {
		//提交任务
		for (int i = 0; i < shops.length; i++) {
			futureList.add(service.submit(new DefaultHtmlProducer(jobQueue, htmlService, getShopParam(shops[i]), notify)));
		}
		notify.fireEvent(HtmlEventType.ADDPROD, shops.length);
	}
	private void addComsumer(ExecutorService service, List<Future<String>> futureList, int num) {
		HtmlTaskComsumer comsumer;
		for (int i = 0; i < num; i++) {
			comsumer = new DefaultHtmlComsumer(jobQueue, htmlComponent, notify);
			futureList.add(service.submit(comsumer));
		}
		notify.fireEvent(HtmlEventType.ADDCOMS, num);
	}
	/**
	 * 返回静态化参数
	 * @param shopFlag
	 * @return
	 */
	private Map<String, String> getShopParam(String shopFlag) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("shopFlag", shopFlag);
		params.put("shopDomain", Constants.DOMAIN_STORE.get(shopFlag));
		params.put("htmlPath", htmlPath+File.separator+shopFlag);
		return params;
	}
	
	@Override
	public void run() {
		try{
			//启动监控器
			notify = HtmlMonitor.getNotify();
			notify.fireEvent(HtmlEventType.ENGINSTART);
			//清空易变缓存
			cacheComponent.clearMutableCacle();
			//生成静态页面
			excuteAll();
		} finally {
			notify.fireEvent(HtmlEventType.ENGINEXIT);
			notify = null;
			//引擎状态
			RUNNING = false;
		}
	}
	public String getHtmlPath() {
		return htmlPath;
	}
	public void setHtmlPath(String htmlPath) {
		this.htmlPath = htmlPath;
	}
	public String getShops() {
		return shops;
	}
	public void setShops(String shops) {
		this.shops = shops;
	}
}
