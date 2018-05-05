package com.gv.appstore.mng.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.appstore.pojo.LvDeveloperApp;
import com.gv.appstore.util.DevelopConstant;
import com.gv.appstore.util.LogUtil;
import com.gv.core.datastructure.page.Pagination;
import com.lshop.manage.common.action.BaseManagerAction;

@Controller("AppMngAction")
@Scope("prototype")
@SuppressWarnings("all")
public class AppMngAction extends BaseManagerAction{
	private static final Log logger = LogFactory.getLog(AppMngAction.class);
	private LvDeveloperApp app;
	private String createDateStart;
	private String createDateEnd;

	public String list(){
		LogUtil.log(logger, "***** AppMngAction.list() method begin*****");
		
		this.dto.put("page", this.page);
		this.dto.put("app", this.app);
		this.dto.put("createDateStart", this.createDateStart);
		this.dto.put("createDateEnd", this.createDateEnd);
		this.page = (Pagination) this.doService("DevAppMngService", "findPage", dto);
		
		LogUtil.log(logger, "***** AppMngAction.list() method end*****");
		return this.LIST;
	}
	
	public String view(){
		dto.put("id", app.getId());
		this.app = (LvDeveloperApp)this.doService("DevAppMngService", "get", dto);
		String domain = DevelopConstant.getResDomain(this.app.getBizline());
		this.app.setUrl(domain + DevelopConstant.appPath + this.app.getUrl());
		
		String allUrl = this.app.getDemoImgsUrl()==null?"":this.app.getDemoImgsUrl();
		if(!allUrl.equals("")){
			List<String> urlList = new ArrayList<String>();
			String _domain = domain + DevelopConstant.appImgPath;
			if(allUrl.contains("|")){
				String u[] = allUrl.split("\\|");
				for(String url:u){
					urlList.add(_domain + url);
				}
			}else{
				urlList.add(_domain + allUrl);
			}
			this.getRequest().setAttribute("urlList", urlList);
			this.getRequest().setAttribute("size", urlList.size()*100);
		}else{
			this.getRequest().setAttribute("size", 30);
		}
		return this.VIEW;
	}
	
	public String toverify(){

		return "verify";
	}
	
	public String verify(){
		LogUtil.log(logger, "***** AppMngAction.verify() method begin*****");
		
		this.dto.put("app", this.app);
		this.doService("DevAppMngService", "verify", dto);
		
		LogUtil.log(logger, "***** AppMngAction.verify() method end*****");
		return AJAX;
	}

	public LvDeveloperApp getApp() {
		return app;
	}

	public void setApp(LvDeveloperApp app) {
		this.app = app;
	}

	public String getCreateDateStart() {
		return createDateStart;
	}

	public void setCreateDateStart(String createDateStart) {
		this.createDateStart = createDateStart;
	}

	public String getCreateDateEnd() {
		return createDateEnd;
	}

	public void setCreateDateEnd(String createDateEnd) {
		this.createDateEnd = createDateEnd;
	}
	
}
