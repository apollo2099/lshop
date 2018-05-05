package com.lshop.web.developer.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.core.datastructure.page.Pagination;
import com.gv.core.util.StringUtil;
import com.lshop.common.action.BaseAction;
import com.lshop.common.pojo.logic.LvDeveloperApp;
import com.lshop.common.util.PropertiesHelper;
import com.lshop.web.userCenter.UserCommon;

/**
 * 开发者应用
 * @author luohd
 *
 */
@SuppressWarnings("serial")
@Controller("DeveloperAppAction")
@Scope("prototype")
public class DeveloperAppAction extends BaseAction {

    private File[] file;
	private LvDeveloperApp app;
    private String[] fileName;
    private String[] imgUrl;
    private String code;

    
	private static final String path= (String) PropertiesHelper.getConfiguration().getProperty("res.domain.path");  

    

    
	/**
	 * 应用列表
	 * @return
	 */
	public String appList(){
		
		String storeflag=this.getFlag();
		String bizline= UserCommon.getBizline(storeflag);//按业务线区分商城
		String email =getUserLoginEmail();
		dto.put("page", page);
		dto.put("email", email);
		dto.put("bizline", bizline);
		page= (Pagination)this.doService("DeveloperAppService", "list", dto);
		this.getRequest().setAttribute("pagination", page);
		this.getRequest().setAttribute("flag", "coupon");//标识菜单栏
		return "list";
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 跳转到添加应用页面
	* @author:luohaidong 
	* @param  @return 
	* @return String
	* @throws
	 */
	public String toadd(){
		return "toadd";
	} 
	
	/**
	 * 提交应用
	* @author:luohaidong 
	* @param  @return 
	* @return String
	* @throws
	 */
	public String add(){
		if(app==null||!app.getIsAgree().equals(1)){//如果没同意
        	this.getRequest().setAttribute("errorstatus",2);
        	return "toadd";//重填
		}
		
		String storeflag=this.getFlag();
		String bizline= UserCommon.getBizline(storeflag);
		app.setBizline(bizline);
		app.setStoreFlag(storeflag);
		app.setEmail(getUserLoginEmail());//设置开发者邮箱
//		app.setUrl(dto.getAsString("urls"));
		//保存开发者信息
		dto.put("model", app);
		Integer status=(Integer)this.doService("DeveloperAppService", "save", dto);
		//保存正常情况
        if(status!=1){
        	this.getRequest().setAttribute("errorstatus",status);
        	return "toadd";
        }
        code=app.getCode();
		return "toedit";
	}
	
	/**
	 * 完善应用信息 
	* @author:luohaidong 
	* @param  @return 
	* @return String
	* @throws
	 */
	public String toEditInfo(){
		String codestr=this.getRequest().getParameter("code");
		dto.put("code", codestr);
		dto.put("email", getUserLoginEmail());
		app=(LvDeveloperApp)this.doService("DeveloperAppService", "info", dto);
		if(app==null||app.getStatus()!=3){//避免恶意访问
			return appList();
		}
        code=codestr;
		return "edit";
	}
	
	/**
	 * 完善应用信息 
	* @author:luohaidong 
	* @param  @return 
	* @return Strng
	* @throws
	 */
	public String editinfo(){

		StringBuffer imgs=new StringBuffer();
		boolean isFirst=true;
		for (int i = 0; i <fileName.length; i++) {
			String imgname=fileName[i];
			if(imgname!=null&&!imgname.trim().equals("")){
				if(!isFirst){
					imgs.append("|");
				}else{
					isFirst=false;
				}
				imgs.append(imgname);
			}
		}
		
		app.setDemoImgsUrl(imgs.toString());
		app.setEmail(getUserLoginEmail());//设置开发者邮箱
		dto.put("model", app);
		Integer status=(Integer)this.doService("DeveloperAppService", "saveinfo", dto);
		//保存正常情况
        if(status!=1){
        	this.getRequest().setAttribute("errorstatus",status);
        	return "toedit";
        }
		return "ok";
	}

	/**
	 * 取消上传app
	 * @author:luohaidong 
	 * @param  @throws IOException 
	 * @return void
	 * @throws
	 */
	public void cancelApp() throws IOException{
		boolean isSuccess=false;
		this.getRequest().setCharacterEncoding("utf-8");
		this.getResponse().setCharacterEncoding("utf-8");
		this.getResponse().setContentType("text/html;charset=UTF-8");
		PrintWriter out=this.getResponse().getWriter();
		String apppath=this.getRequest().getParameter("appname");
		if(!StringUtil.IsNullOrEmpty(apppath)){
			String separator=File.separator;
			StringBuffer uploadPath = new StringBuffer(path);
			String storeflag=this.getFlag();
			String bizline= UserCommon.getBizline(storeflag);
			uploadPath.append(separator);
			uploadPath.append("private");
			uploadPath.append(separator);
			uploadPath.append(bizline);
			uploadPath.append(separator);
			uploadPath.append("app"+separator+apppath);
			File f=new File(uploadPath.toString());//删除该图片
			isSuccess=f.delete();
		}
		out.print(isSuccess);
		out.flush();
		out.close();
	}
	/**
	 * 取消上传app例子图片
	* @author:luohaidong 
	* @param  @throws IOException 
	* @return void
	* @throws
	 */
	public void cancelImg() throws IOException{
		boolean isSuccess=false;
		this.getRequest().setCharacterEncoding("utf-8");
		this.getResponse().setCharacterEncoding("utf-8");
		this.getResponse().setContentType("text/html;charset=UTF-8");
		PrintWriter out=this.getResponse().getWriter();
		String imgpath=this.getRequest().getParameter("img");
		if(!StringUtil.IsNullOrEmpty(imgpath)){
			String separator=File.separator;
			StringBuffer uploadPath = new StringBuffer(path);
			String storeflag=this.getFlag();
			String bizline= UserCommon.getBizline(storeflag);
			uploadPath.append(separator);
			uploadPath.append("private");
			uploadPath.append(separator);
			uploadPath.append(bizline);
			uploadPath.append(separator);
			uploadPath.append("appimg"+separator+imgpath);
			File f=new File(uploadPath.toString());//删除该图片
			isSuccess=f.delete();
		}
		out.print(isSuccess);
		out.flush();
		out.close();
	}
	
	public String info(){
		String codestr=this.getRequest().getParameter("code");
		dto.put("code", codestr);
		dto.put("email", getUserLoginEmail());
		app=(LvDeveloperApp)this.doService("DeveloperAppService", "info", dto);
		if(app==null||app.getStatus()==3){//避免恶意访问
			return appList();
		}
		//获取显示地址
		String separator=File.separator;
		
		String demoImg=app.getDemoImgsUrl();
		String demoimgs[]=demoImg.split("\\|");
		String storeflag=this.getFlag();
		String bizline= UserCommon.getBizline(storeflag);
        //拼接应用截图的图片地址
        String appimgurl="/private/"+bizline+"/appimg/";
		for (int i = 0; i < demoimgs.length; i++) {
			demoimgs[i]=appimgurl+demoimgs[i];
		}
		app.setDemoImgs(demoimgs);
		//拼接应用下载地址
		
        code=codestr;
		return "info";
	}
	

	
	public LvDeveloperApp getApp() {
		return app;
	}

	public void setApp(LvDeveloperApp app) {
		this.app = app;
	}
	public String[] getFileName() {
		return fileName;
	}

	public void setFileName(String[] fileName) {
		this.fileName = fileName;
	}

	public String[] getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String[] imgUrl) {
		this.imgUrl = imgUrl;
	}
	public File[] getFile() {
		return file;
	}

	public void setFile(File[] file) {
		this.file = file;
	}
	

}
