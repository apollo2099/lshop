package com.lshop.html.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.lshop.common.pojo.logic.LvLinkUrl;
import com.lshop.html.service.HtmlStaticService;
import com.lshop.html.util.CacheMemory;

@Service("HtmlStaticService")
public class HtmlStaticServiceImpl implements HtmlStaticService {

	private static final Log logger = LogFactory.getLog(HtmlStaticServiceImpl.class);
	private LvLinkUrl urlobj;// 静态化信息
	private Object parm;// 参数
	private String htmlpath;// 静态页面存放位置
	private String domainname;// 网页域名

	public HtmlStaticServiceImpl() {
	}

	public HtmlStaticServiceImpl(LvLinkUrl urlobj, Object parm,String htmlpath, String domainname) {
		this.urlobj = urlobj;
		this.parm = parm;
		this.htmlpath = htmlpath;
		this.domainname = domainname;

	}

	@Override
	public Boolean createHtmlByURL() throws IOException {
		if (logger.isInfoEnabled()) {
			logger
					.info("***** HtmlStaticServiceImpl.createHtmlByURL() method Begin*****");
		}
		String toPathString = "";
		String staticUrl = urlobj.getStaticurl();
		String dynamicUrl = urlobj.getDynamicurl();
		if (parm != null) {
			Object[] parms = null;
			if (parm instanceof Object[]) {
				parms = (Object[]) parm;
			} else {
				parms = new Object[1];
				parms[0] = parm;
			}
			for (int i = 0; i < parms.length; i++) {
				Object parm = parms[i];
				staticUrl = staticUrl.replaceFirst("@", parm == null ? ""
						: parm.toString());
				dynamicUrl = dynamicUrl.replaceFirst("@", parm == null ? ""
						: parm.toString());
			}
		}
		if (staticUrl != null) {
			toPathString = htmlpath + staticUrl;// 设置静态地址
			if(dynamicUrl.indexOf("http://")==-1)
			dynamicUrl = domainname + dynamicUrl;// 设置动态地址
			URL fileUrl = new URL(dynamicUrl);// 建立URL对象，并实例化为url，获得要抓取的网页地址
			File file = new File(toPathString.substring(0, toPathString.lastIndexOf("/")));
			if (!file.exists()) {
				file.mkdirs();
			}
			InputStream is = fileUrl.openStream();
			File htmlfile = new File(toPathString);
			// 更新的时候删除现有的文件
			if (htmlfile.exists()) {
				htmlfile.delete();
			}
			htmlfile.createNewFile();// 创建新文件
			FileOutputStream out = new FileOutputStream(htmlfile);
			BufferedWriter ot = new BufferedWriter(new OutputStreamWriter(out,"UTF-8"));
			InputStreamReader ir = new InputStreamReader(is, "UTF-8");
			BufferedReader bfr = new BufferedReader(ir);
			int b = 0;
			while ((b = bfr.read()) != -1) {
				ot.write(b);

			}
			ot.flush();
			ot.close();
			out.flush();
			out.close();
			bfr.close();
			ir.close();
			is.close();
		}
		if (logger.isInfoEnabled()) {
			logger
					.info("***** HtmlStaticServiceImpl.createHtmlByURL() method end*****");
		}
		return true;
	}

	public void setUrlobj(LvLinkUrl urlobj) {
		this.urlobj = urlobj;
	}

	public void setParm(Object parm) {
		this.parm = parm;
	}

	public void setHtmlpath(String htmlpath) {
		this.htmlpath = htmlpath;
	}

	public void setDomainname(String domainname) {
		this.domainname = domainname;
	}

}
