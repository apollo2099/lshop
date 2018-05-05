package com.lshop.web.fileUpload.service.impl;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.FileUpload;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.util.Constants;
import com.lshop.web.fileUpload.service.UploadService;

@Service("UploadService")
public class UploadServiceImpl implements UploadService {
	private static final Log logger	= LogFactory.getLog(UploadServiceImpl.class);
	@Override
	public String uploadImg(Dto dto) throws ServiceException {
		String dir=(String) dto.get("dir");
		File filedata=(File) dto.get("filedata");
		String filedataFileName=(String) dto.get("filedataFileName");
		String storeFlag=(String) dto.get("storeFlag");
		
		//获取资源管理域名
		String systemFlag=Constants.STORE_TO_MALL_SYSTEM.get(storeFlag);//根据店铺标志查询商城体系
	    String domainPostfix=Constants.MALL_TO_DOMAIN_POSTFIX.get(systemFlag);//更加商城体系查找体系对于后缀域名
	    String resDomain="http://res"+domainPostfix;
	    String resPath = dto.getAsString("resPath"); //获取资源文件路径
			    
		HttpServletRequest request=(HttpServletRequest) dto.get("request");
		if(ObjectUtils.isNotEmpty(storeFlag)){//店铺标示
			String filepath=resPath+"/upload/"+storeFlag+"/res";//文件上传资源路径
			if (dir!=null&&!"".equals(dir.trim())) {
				filepath+="/"+dir;
			}
			File file=new File(filepath);
			if(!file.exists()){//判断文件目录是否存在，不存在就重新创建一个目录结构
				file.mkdirs();
			}
			FileUpload.upload(filedata, filepath, filedataFileName);
			String url = "/upload/"+storeFlag+"/res/"+dir+"/"+filedataFileName;
			return resDomain+url;
		}
        return null;
	}
}
