package com.lshop.manage.sysfile.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.util.FileUpload;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.util.Constants;
import com.lshop.manage.common.action.BaseManagerAction;

@SuppressWarnings("unchecked")
@Controller("UploadManagerAction")
@Scope("prototype")
public class UploadManagerAction extends BaseManagerAction{

	private File filedata;
	private String filedataFileName;
	private String dir;
	private String url;
	private String err;
	private String marking;
	private String storeFlag;
	



	
	public String uploadData() {
//		String filepath=request.getRealPath("/res");
		//获取资源管理域名
		String systemFlag=Constants.STORE_TO_MALL_SYSTEM.get(storeFlag);//根据店铺标志查询商城体系
	    String domainPostfix=Constants.MALL_TO_DOMAIN_POSTFIX.get(systemFlag);//更加商城体系查找体系对于后缀域名
	    String resDomain="http://res"+domainPostfix;  
		String resPath = this.getText("res.domain.path"); //获取资源文件路径
		String filepath=resPath+"/upload/"+storeFlag+"/res";//文件上传资源路径
		if (dir!=null&&!"".equals(dir.trim())) {
			filepath+=dir;
		}
		File file=new File(filepath);
		if(!file.exists()){//判断文件目录是否存在，不存在就重新创建一个目录结构
			file.mkdirs();
		}
	    java.text.Format format=new SimpleDateFormat("yyyyMMddHHmmssSS");
		filedataFileName=format.format(new Date())+filedataFileName.substring(filedataFileName.lastIndexOf("."));
		FileUpload.upload(filedata, filepath, filedataFileName);
		url = "/upload/"+"/res"+dir+"/" + filedataFileName;
		BaseUsers user=	(BaseUsers) getSession().getAttribute("USER");
		String nowmain=request.getServerName();
		String domain="";
		for(Map.Entry<String, String> entry:Constants.STORE_IDS.entrySet()){   
		    if(user.getStoreFlag().equals(entry.getValue())){
		    	domain=entry.getKey();
		    	break;
		    }
		}
		if("".equals(domain)){
			domain="www"+nowmain.substring(nowmain.indexOf("."));
		}
		url=resDomain+url;
		return JSON;
	}
	/**
	 * 
	 * @Method: uploadImgData 
	 * @Description:  [文本编辑器图片文件上传(带店铺标识)]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-25 下午03:44:09]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-25 下午03:44:09]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return 
	 * @return String
	 */
	public String uploadImgData(){

		if(ObjectUtils.isEmpty(storeFlag)){
			BaseUsers user=	(BaseUsers) getSession().getAttribute("USER");
			String storeFlagTemp=user.getStoreFlag();
			String [] arr= storeFlagTemp.split(",");
			storeFlag=arr[0];
		}
		
		java.text.Format format=new SimpleDateFormat("yyyyMMddHHmmssSS");
		filedataFileName=marking+"_"+format.format(new Date())+filedataFileName.substring(filedataFileName.lastIndexOf("."));
		dto.put("dir", dir);
		dto.put("filedata", filedata);
		dto.put("request", request);
		dto.put("filedataFileName", filedataFileName);
		dto.put("storeFlag",storeFlag);
		dto.put("resPath", this.getText("res.domain.path"));
		url=(String) this.doService("UploadService", "uploadImg", dto);
		
		String nowmain=request.getServerName();
		String domain="";
		for(Map.Entry<String, String> entry:Constants.STORE_IDS.entrySet()){   
		    if(storeFlag.equals(entry.getValue())){
		    	domain=entry.getKey();
		    	break;
		    }
		}
		if("".equals(domain)){
			domain="www"+nowmain.substring(nowmain.indexOf("."));
		}
		return JSON;
	}

	public String getFiledataFileName() {
		return filedataFileName;
	}

	public String getUrl() {
		return url;
	}

	public String getErr() {
		return err;
	}

	public void setFiledataFileName(String filedataFileName) {
		this.filedataFileName = filedataFileName;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setErr(String err) {
		this.err = err;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}
	public String getMarking() {
		return marking;
	}

	public void setMarking(String marking) {
		this.marking = marking;
	}

	public File getFiledata() {
		return filedata;
	}

	public void setFiledata(File filedata) {
		this.filedata = filedata;
	}
    
	public String getStoreFlag() {
		return storeFlag;
	}
	public void setStoreFlag(String storeFlag) {
		this.storeFlag = storeFlag;
	}
}
