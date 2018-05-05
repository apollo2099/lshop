package com.lshop.manage.lvOperationLogs.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.util.ObjectUtils;
import com.gv.core.util.excel.ExcelWriter;
import com.lshop.common.pojo.logic.LvOperationLogs;
import com.lshop.common.pojo.logic.LvOrderLogs;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.util.FileUtil;
import com.lshop.manage.common.action.BaseManagerAction;
import com.lshop.manage.lvOrderLogs.action.LvOrderLogsAction;

/**
 * 操作日志模块
 * @author zhengxue 2014-06-24
 *
 */
@Controller("LvOperationLogsAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvOperationLogsAction extends BaseManagerAction{
	
	private static final Log logger = LogFactory.getLog(LvOrderLogsAction.class);
	private LvOperationLogs lvOperationLogs;
	private BufferedInputStream excelStream;

	/**
	 * 操作日志——查询
	 * @return
	 */
	public String list(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvOperationLogsAction.list() method begin*****");
		}

		dto.put("page",page);
		dto.put("lvOperationLogs",lvOperationLogs);
		page=(Pagination) this.doService("LvOperationLogsService", "getList", dto);
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvOperationLogsAction.list() method end*****");
		}
		return LIST;
	}
	
	/**
	 * 操作日志——查看详情
	 * @return
	 */
	public String view(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvOperationLogsAction.view() method begin*****");
		}
		dto.put("lvOperationLogs",lvOperationLogs);
		lvOperationLogs=(LvOperationLogs) this.doService("LvOperationLogsService", "view", dto);
		this.getRequest().setAttribute("lvOperationLogs", lvOperationLogs);
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvOperationLogsAction.view() method end*****");
		}
		return "view";
	}
	
	/**
	 * 操作日志——单个删除
	 * @return
	 */
	public String del(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvOperationLogsAction.del() method begin*****");
		}
		dto.put("lvOperationLogs", lvOperationLogs);
		this.doService("LvOperationLogsService", "delete", dto);
		json.doNavTabTodo();
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvOperationLogsAction.del() method end*****");
		}
		return AJAX;
	}
	
	/**
	 * 操作日志——导出excel
	 * @return
	 * @throws FileNotFoundException 
	 */
	public String exportOperationLogs() throws FileNotFoundException{
		if (logger.isInfoEnabled()) {
			logger.info("***** LvOperationLogsAction.exportOperationLogs() method begin*****");
		}
		
		//删除文件上传临时文件夹下面所有临时文件
		FileUtil.deleteTempFile(request);
		
		dto.put("ids",ids);
		List list = (List) this.doService("LvOperationLogsService", "exportOperationLogs", dto);
		
		ExcelWriter writer = new ExcelWriter();
		String path = request.getRealPath("/temp") + "/" + System.currentTimeMillis() + ".tmp";
		writer.create(path, list);
		File file = new File(path);
		excelStream = new BufferedInputStream(new FileInputStream(file));
		file.delete();
		
		if (logger.isInfoEnabled()) {
			logger.info("***** LvOperationLogsAction.exportOperationLogs() method end*****");
		}
		
		return "excel";
	}
	
	public LvOperationLogs getLvOperationLogs() {
		return lvOperationLogs;
	}
	public void setLvOperationLogs(LvOperationLogs lvOperationLogs) {
		this.lvOperationLogs = lvOperationLogs;
	}
	
	public BufferedInputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(BufferedInputStream excelStream) {
		this.excelStream = excelStream;
	}

}
