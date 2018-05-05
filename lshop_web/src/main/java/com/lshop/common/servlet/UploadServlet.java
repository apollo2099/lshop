package com.lshop.common.servlet;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.gv.core.util.StringUtil;
import com.lshop.common.util.CommonUtil;
import com.lshop.common.util.Constants;
import com.lshop.common.util.PropertiesHelper;
import com.lshop.web.userCenter.UserCommon;

public class UploadServlet extends HttpServlet {


	private static final long serialVersionUID = -4083310116598652522L;
	
	private static final String path= (String) PropertiesHelper.getConfiguration().getProperty("res.domain.path");  

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		Map<String, Object> data = new HashMap<String, Object>();
		String resPath = path;
		
		String serverName = request.getServerName();
		String storeFlag = Constants.STORE_IDS.get(serverName.trim());//获取店铺标志
		String bizline= UserCommon.getBizline(storeFlag);
		char separator= File.separatorChar;
		StringBuffer uploadPath = new StringBuffer(path+separator+"private");//保存地址
		uploadPath.append(separator+bizline + separator);

		String newName = "";//新名字
		String p=	request.getParameter("p");
		String param[]=p.split("\\|");
		String type="";
		String email="";
		if(param.length==2){
			 type=param[0];
			 email=param[1];
		}
		String path_dir="";
		String fileTypeReg="";//文件类型检查正则
		
		if(email==null&&email.equals("")){
			return;
		}
		
		if(type.equals("1")){//应用
			path_dir="app";
			fileTypeReg=".+\\.(apk|pkg)";
		}else if(type.equals("2")){//应用截图
			path_dir="appimg";
			fileTypeReg=".+\\.(jpg|png|JPG|PNG)";
		}else if(type.equals("3")){
			path_dir="comment";
			fileTypeReg=".+\\.(jpg|png|JPG|PNG)";		
		}else{//不合法的类型 恶意操作引起
			data.put("status", "300"); 
			data.put("msg", ""); 
			JSONObject json = JSONObject.fromObject(data);
			response.getWriter().print(json);
			return ;
		}
		uploadPath.append(path_dir+separator);
		//如果目录不存在则创建目录
		new CommonUtil().checkCreateFiles(uploadPath.toString());

		DiskFileItemFactory fac = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(fac);
		upload.setHeaderEncoding("UTF-8");

		List fileList = null;
		try {
			fileList = upload.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
			return;
		}

		String oldName = "";
		String extName = "";
		String filename="";
		Long size = null;
		Iterator<FileItem> it = fileList.iterator();
		while (it.hasNext()) {
			FileItem item = it.next();
			if (!item.isFormField()) {
				size = item.getSize();
				oldName = item.getName();
				if (StringUtil.IsNullOrEmpty(oldName)) {
					continue;
				}
				
				if(oldName.length()>104){
					data.put("status", "301"); 
					data.put("msg", "文件名稱過長");
					response.getWriter().print(JSONObject.fromObject(data));
					return;
				}
				
				int last=oldName.lastIndexOf(".");
				if (last > 0) {
					extName=oldName.substring(last);
					//如果是文件类型且检查不通过  恶意操作引起
					if (!oldName.matches(fileTypeReg)) {
						data.put("status", "300"); 
						data.put("msg", "非法的文件类型");
						response.getWriter().print(JSONObject.fromObject(data));
						return;
					}
				}
				File file = null;
				
				email=email.substring(0,email.indexOf("@"));
				newName=email+"_"+System.currentTimeMillis();
				 filename=uploadPath + newName + extName;
				do {
					file = new File(filename);
				} while (file.exists());

				File saveFile = new File(filename);

				try {
					item.write(saveFile);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		 if(!CommonUtil.checkFiles(filename)){
				data.put("status", "302"); 
				data.put("msg", "上传失败");
				response.getWriter().print(JSONObject.fromObject(data));
				return;
		  }
		
		
		int index = uploadPath.indexOf(resPath);
		index += resPath.length();
		String savePath = uploadPath.substring(index) + newName + extName;
		savePath = savePath.replace("\\", "/");
//        String pristr=path.substring(path.lastIndexOf(separator)+1);
//        savePath=pristr+"/"+savePath;
        data.put("status", "200"); 
		data.put("oldName", oldName); 
		data.put("path", savePath); 
		data.put("size", size); 
		JSONObject json = JSONObject.fromObject(data);
		response.getWriter().print(json);
	}
	
	
	

}
