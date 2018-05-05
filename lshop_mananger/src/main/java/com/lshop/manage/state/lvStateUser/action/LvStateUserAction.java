package com.lshop.manage.state.lvStateUser.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionServlet;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.gv.core.datastructure.page.Pagination;
import com.gv.core.util.ObjectUtils;
import com.gv.core.util.excel.ExcelWriter;
import com.lshop.common.pojo.logic.LvStateUser;
import com.lshop.common.pojo.logic.LvWesternInfo;
import com.lshop.common.util.PropertiesHelper;
import com.lshop.manage.common.action.BaseManagerAction;
import com.opensymphony.xwork2.ActionContext;

@Controller("LvStateUserAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvStateUserAction extends BaseManagerAction{
	private static final Log logger	= LogFactory.getLog(LvStateUserAction.class);
	private LvStateUser lvStateUser;
	private BufferedInputStream excelStream;
	
	public String list(){
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("page",page);
		dto.put("lvStateUser",lvStateUser);
		//产品效果图分页集合对象
		page=(Pagination) this.doService("LvStateUserService", "getList", dto);
		return LIST;
	}
	
	public synchronized String synchronousData(){
		String userStateFlag=(String) PropertiesHelper.getConfiguration().getProperty("com.lshop.manage.state.lvStateUser.stateUserFlag");
		if(ObjectUtils.isNotEmpty(userStateFlag)&&userStateFlag.equals("1")){
			json.setMessage("数据同步失败,同步任务真正进行中，请勿重复操作!");
			json.setStatusCode(200);
			json.doNavTabTodo();
			return AJAX;
		}
		dto.put("request", this.getRequest());
		Boolean isFlag=(Boolean) this.doService("LvStateUserService", "synchronousData", dto);
		if(isFlag){
			json.setMessage("正在进行数据同步...");
			
		}else{
			json.setMessage("数据同步失败!");
			json.setStatusCode(200);
		}
		json.doNavTabTodo();
		return AJAX;
	}
	
	public String exportUserStat() throws FileNotFoundException{
		if (logger.isInfoEnabled()) {
			logger.info("***** LvStateUserAction.exportUserStat() method begin*****");
		}
		//删除文件上传临时文件夹下面所有临时文件
		deleteTempFile(request);
		
		dto.put("page",page);
		dto.put("ids",ids);
		dto.put("lvStateUser", lvStateUser);
		List list = (List) this.doService("LvStateUserService", "exportUserOrder", dto);
		ExcelWriter writer = new ExcelWriter();
		String path = request.getRealPath("/temp") + "/" + System.currentTimeMillis() + ".tmp";
		writer.create(path, list);
		File file = new File(path);
		excelStream = new BufferedInputStream(new FileInputStream(file));
		file.delete();
		
		if (logger.isInfoEnabled()) {
			logger.info("***** LvStateUserAction.exportUserStat() method end*****");
		}
		return "exportUserOrder";
	}
	
	public void deleteTempFile(HttpServletRequest request){
		//删除文件上传临时文件夹下面所有临时文件
		String pathTmp = request.getRealPath("/temp");
		File fileTemp=new File(pathTmp);
		if(fileTemp.isDirectory()){
			 File[] f=fileTemp.listFiles();
			 for (File fs : f) {
				boolean isFlag=fs.delete();
				if(logger.isInfoEnabled()){
					  logger.info("删除文件是否成功:"+fs.getName()+"->"+isFlag);
				}
			}
		}
	}
	
	public LvStateUser getLvStateUser() {
		return lvStateUser;
	}

	public void setLvStateUser(LvStateUser lvStateUser) {
		this.lvStateUser = lvStateUser;
	}

	public BufferedInputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(BufferedInputStream excelStream) {
		this.excelStream = excelStream;
	}


}
