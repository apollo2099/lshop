package com.lshop.web.developer.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;

import org.hibernate.Hibernate;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ActionException;
import com.lshop.common.action.BaseAction;
import com.lshop.common.pojo.logic.LvDeveloper;
import com.lshop.common.util.PropertiesHelper;
import com.lshop.web.userCenter.UserCommon;

/**
 * 
* @author luohaidong
* @Description: (开发者模板) 
* @date 2014年6月27日 下午3:23:30 
* @version V1.0
 */
@SuppressWarnings("serial")
@Controller("DeveloperAction")
@Scope("prototype")
public class DeveloperAction extends BaseAction {

	private LvDeveloper developer; //开发者信息

    private File file;
    private String fileName;
    private String imgUrl;
	private static final String path= (String) PropertiesHelper.getConfiguration().getProperty("private.app.path");  





	/**
	 * 开发者首页
	 * @return
	 */
	public String developerIndex() throws ActionException{
		//获取当前用户邮箱
		String em =getUserLoginEmail();
		dto.put("email",em);
		String storeflag=this.getFlag();
		String bizline= UserCommon.getBizline(storeflag);
		dto.put("bizline",bizline);
		//获取当前用户的开发者信息
		 developer = (LvDeveloper)this.doService("DeveloperService", "getInfo", dto);
		//没有申请的情况
		if(developer==null){
			return "applyType";
		}
		//正常情况，跳转到我的应用
		else if(developer.getDstatus()==1){
			return "app";
		}
		//待审核情况
		else if(developer.getDstatus()==0){
			return "reviewOn";//跳转到待审核提示页面
		}
		//审核不通过情况
		else if(developer.getDstatus()==2){
			return "reviewFail";//跳转到审核失败提示页面
		}
		
		this.getRequest().setAttribute("flag", "developer");//标识菜单栏
		return "myCouponList";
	}

	
	

	/**
	 *跳转到开发者申请页面
	 * @return
	 */
	public String toApply()  throws ActionException{
		String type=this.getRequest().getParameter("type");
		String typereg="0|1";//开发者类型取值范围
		if(type==null||!type.matches(typereg)){
			return "applyType";
		}
	    if(type.equals("0")){
	    	return "personalApply";
	    }
	    else {
	    	return "companyApply";
	    }
	}
	
	/**
	 * 
	* @author:luohaidong 
	* @Description:开发者申请 
	* @param  @return 
	* @return String
	* @throws
	 */
	public String apply()  throws ActionException {
		String typereg="0|1";//开发者类型取值范围
		if(developer==null
		||developer.getDtype()==null||!developer.getDtype().toString().matches(typereg)//类型不对
		||developer.getIsAgree()==null||!developer.getIsAgree().equals(1))//没同意协议
		{
			return "applyType";//返回重填
		} 
		String storeflag=this.getFlag();
		String bizline= UserCommon.getBizline(storeflag);
		developer.setBizline(bizline);
		developer.setStoreFlag(storeflag);
		developer.setEmail(getUserLoginEmail());//设置开发者邮箱
		Blob image=upload(dto);
		Integer statuscode=dto.getAsInteger("statusCode");
		if(statuscode!=200){
        	this.getRequest().setAttribute("msg", dto.getAsString("msg"));
        	if(developer.getDtype().equals(0)){
        		return "personalApply";
        	}else {
    	    	return "companyApply";
        	}
		}
		developer.setIdUrl(image);
		//保存开发者信息
		dto.put("model", developer);
		Integer status=(Integer)this.doService("DeveloperService", "save", dto);
		//保存异常情况
        if(status!=1){
        	this.getRequest().setAttribute("status", status);
        	if(developer.getDtype().equals(0)){
        		return "personalApply";
        	}else {
    	    	return "companyApply";
        	}
        }

		return "reviewOn";//跳转到待审核提示页面
	}
	
	public Blob   upload(Dto dto)  throws ActionException{
		Blob b=null;
		if(fileName!=null&&!fileName.equals("")){
			String fname=fileName;
			String last=fname.substring(fname.lastIndexOf("."));
			 fname=fname.substring(0,fname.lastIndexOf("."));
			String newname=System.currentTimeMillis()+last;
		    int size=	(int) (file.length()/1024/1024);
			if(size>=4){
				dto.put("statusCode", 300);
				dto.put("msg", "上传文件不能超过4M");
				return b;
			}
			
			InputStream in=null;
			try {
				 in=new FileInputStream(file);
				 b=Hibernate.createBlob(in);
				 dto.put("statusCode", 200);
				dto.put("imgname", newname);
			}  catch (IOException e) {
				dto.put("statusCode", 300);
				dto.put("msg", "上传文件出错!");
				e.printStackTrace();
			}
			
		}
		return b;
	}

	
	
	
	
	public LvDeveloper getDeveloper() {
		return developer;
	}


	public void setDeveloper(LvDeveloper developer) {
		this.developer = developer;
	}
	public File getFile() {
		return file;
	}


	public void setFile(File file) {
		this.file = file;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public String getImgUrl() {
		return imgUrl;
	}


	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
}
