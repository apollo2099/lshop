package com.lshop.common.progressBar.utils;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.core.web.action.BaseAction;

/**
 * 功能描述：读取进度条
 * @author Administrator
 *
 */
@Controller("ProgressBarAction")
@Scope("prototype")
public class ProgressBarAction extends BaseAction {
	
	private static final Log logger	= LogFactory.getLog(ProgressBarAction.class);
	private JSONObject  jsonObject;
	private String progressName;
	private String actionUrl;

	public JSONObject getJsonObject() {
		return jsonObject;
	}

	public void setJsonObject(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}

	public String getProgressName() {
		return progressName;
	}

	public void setProgressName(String progressName) {
		this.progressName = progressName;
	}

	public String getActionUrl() {
		return actionUrl;
	}

	public void setActionUrl(String actionUrl) {
		this.actionUrl = actionUrl;
	}

	public String execute() throws Exception {
		
		ProgressBar progressBar = (ProgressBar)request.getSession().getAttribute(progressName);

		if(progressBar != null) {
			jsonObject = JSONObject.fromObject(progressBar);
		}
		
		return JSON;
	}
}