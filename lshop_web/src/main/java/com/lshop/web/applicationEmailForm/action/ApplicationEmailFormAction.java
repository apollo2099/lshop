package com.lshop.web.applicationEmailForm.action;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.core.util.BeanUtils;
import com.lshop.common.action.BaseAction;
import com.lshop.common.pojo.logic.LvDealerApplication;
import com.lshop.common.util.Constants;
import com.lshop.common.util.PropertiesHelper;

/**
 * 经销商申请
 * @author zhengxue
 *
 */
@Controller("ApplicationEmailFormAction")
@Scope("prototype")
public class ApplicationEmailFormAction extends BaseAction {
	
private LvDealerApplication app;
private boolean appFlag=false;

public String applyApplicationFromEmail() throws Exception{
	
	if(app==null){
		appFlag=false;
		return "app";
	}	
	
	//保存数据
	if(!"其他".equals(app.getType()) && !("Other").equals(app.getType())){
		app.setOtherText("");
	}
	
	app.setCreateTime(new Date());
	app.setStoreId(this.getFlag());
	dto.setDefaultPo(app);
	Boolean flag = (Boolean)this.doService("TvpadTopicService", "saveApplication", dto);
	
	////发送邮件
	String contentTplPath=this.getRequest().getRealPath("/")+"/web/"+this.getFlag()+"/business/fromEmailTpl.html";
	InputStreamReader read = new InputStreamReader(new FileInputStream(contentTplPath), "UTF-8");// 考虑到编码格式
	BufferedReader bufferedReader = new BufferedReader(read);
	StringBuffer buf = new StringBuffer("");
	String lineTxt = null;
	while ((lineTxt = bufferedReader.readLine()) != null) {
		buf.append(lineTxt);
	}
	read.close();
	String content=buf.toString();
	Map<String,Object>map=BeanUtils.convertToMap(app);
	for(String key :map.keySet()){
		Object objValue=map.get(key);
		if(objValue!=null){
		String value=objValue.toString().replaceAll("(\r\n|\r|\n|\n\r)", "<br/>").replaceAll("( )", "&nbsp;");
		content=content.replace("#"+key, value);
		}else{
		content=content.replace("#"+key, "");
		}
	}
	
	//获取当前店铺所属系统（华扬orBanana）
	String mallSystem = Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag()); 
	String strEmail = "lshop."+mallSystem+".email.receive";
	dto.put("mallFlag",mallSystem);
	dto.put("title", "意向经销商申请表");// 邮件标题
	dto.put("userEmail", PropertiesHelper.getProperty(strEmail));// 用户email
	dto.put("content",content);// 邮件内容
	doService("emailSendService", "sendEmailNotice", dto);
	appFlag=true;
	return this.SUCCESS;
}


public LvDealerApplication getApp() {
	return app;
}
public void setApp(LvDealerApplication app) {
	this.app = app;
}
public boolean isAppFlag() {
	return appFlag;
}
public void setAppFlag(boolean appFlag) {
	this.appFlag = appFlag;
}
}
