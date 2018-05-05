package com.gv.html.component.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.dispatcher.mapper.ActionMapping;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.gv.html.component.HtmlComponent;
import com.gv.html.service.HtmlService;
import com.gv.html.struts.ApplicationHolder;
import com.gv.html.struts.MyHtmlRequest;
import com.gv.html.struts.MyHtmlRespone;
import com.lshop.common.cache.component.CacheComponent;
import com.lshop.common.util.CodeUtils;
import com.lshop.common.util.Constants;
@Component
public class HtmlComponentImpl implements HtmlComponent{
	public static final Log logger = LogFactory.getLog(HtmlComponentImpl.class);
	
	/**
	 * 静态文件生成路径
	 */
	@Value("${lshop.html.path}")
	private String htmlPath;
	
	@Resource
	private HtmlService htmlService;
	@Resource
	private CacheComponent cacheComponent;
	
	@Override
	public boolean doStatic(String url, String abHtml) {
		HttpServletRequest request = null;
		HttpServletResponse response = null;
		String tempFile = htmlPath+File.separator+CodeUtils.getCode();
		try {
			request = new MyHtmlRequest(url);
			response = new MyHtmlRespone(tempFile);
			ApplicationHolder.prepare.createActionContext(request, response);
			ApplicationHolder.prepare.assignDispatcherToThread();
			if (request.getRequestURI().endsWith("action")) {
				request = ApplicationHolder.prepare.wrapRequest(request);
				// call stuts2 ActionMapping
				ActionMapping mapping = parseActionMapping(request.getRequestURI());
				ApplicationHolder.execute.executeAction(request, response, mapping);
			} else if (request.getRequestURI().endsWith("jsp")) {
				// jsp load filter set attribute
				String serverName = request.getServerName();
			    String storeFlag = Constants.STORE_IDS.get(serverName.trim());//获取店铺标志
			    //添加资源管理域名配置
				String systemFlag=Constants.STORE_TO_MALL_SYSTEM.get(storeFlag);//根据店铺标志查询商城体系
			    String domainPostfix=Constants.MALL_TO_DOMAIN_POSTFIX.get(systemFlag);//更加商城体系查找体系对于后缀域名
			    String resDomain="http://res"+domainPostfix;
			    request.setAttribute("resDomain", resDomain);
			    
				request.getRequestDispatcher(request.getRequestURI()).forward(request, response);
			} else {
				// static resource, eg: html .etc
				request.getRequestDispatcher(request.getRequestURI()).forward(request, response);
			}
			//复制文件
			File abFile = new File(abHtml);
			if (!abFile.getParentFile().exists()) {
				abFile.getParentFile().mkdirs();
			}
			doCopyAndTrim(new File(tempFile), abFile);
			logger.info("create html File:"+abHtml);
			System.out.println("create html File:"+abHtml);
		} catch (Exception e) {
			logger.error(url + " : 静态化失败");
			logger.error(e.getMessage(), e);
			return false;
		} finally {
			ApplicationHolder.prepare.cleanupRequest(request);
			//clean object
			request = null;
			try {
				response.getWriter().close();
			} catch (IOException e) {
				logger.error(e.getMessage());
				return false;
			}
			response = null;
			new File(tempFile).delete();
		}
		return true;
	}
	/**
	 * 复制和过滤空格行
	 * @param srcFile
	 * @param destFile
	 * @throws IOException
	 */
	private void doCopyAndTrim(File srcFile, File destFile) throws IOException{
		FileReader fileReader = new FileReader(srcFile);
		BufferedReader bf = new BufferedReader(fileReader);
		FileWriter fileWriter = new FileWriter(destFile);
		BufferedWriter bw = new BufferedWriter(fileWriter);
		String line = null;
		String lisp = System.getProperty("line.separator");
		while((line = bf.readLine()) != null) {
			line = line.trim();
			if (!line.isEmpty()) {
				bw.append(line);
				bw.append(lisp);
			}
		}
		bw.flush();
		fileReader.close();
		fileWriter.close();
		bf.close();
		bw.close();
	}
	/**
	 * 通过uri返回ActionMapping
	 * @param uri
	 * @return
	 */
	private ActionMapping parseActionMapping(String uri) {
		ActionMapping mapping = new ActionMapping();
		int i = uri.lastIndexOf("/");
		String ns = uri.substring(i+1);
		String[] md = ns.split("!");
		String[] ex = md[1].split("\\.");
		mapping.setName(md[0]);
		mapping.setNamespace(uri.substring(0, i));
		mapping.setMethod(ex[0]);
		mapping.setExtension(ex[1]);
		return mapping;
	}
	
	@Override
	public String processHtmlRequest(String huri, String shopFlag) {
		String duri = htmlService.getHtmlDynamicUri(huri, shopFlag);
		if (StringUtils.isNotBlank(duri)) {
			//执行静态化
			String pre = "http://"+Constants.DOMAIN_STORE.get(shopFlag);
			String abHtml = htmlPath+File.separator+shopFlag+huri;
			doStatic(pre+duri, abHtml);
			return abHtml;
		}
		return null;
	}
	
	
	@Override
	@Async
	public void asyncProcessHtml(final String huri, final String shopFlag) {
		//清空易变缓存
		cacheComponent.clearMutableCacle();
		//单页面静态化
		processHtmlRequest(huri, shopFlag);
	}
}
