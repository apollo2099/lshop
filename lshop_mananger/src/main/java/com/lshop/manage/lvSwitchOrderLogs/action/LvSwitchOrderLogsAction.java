/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvSwitchOrderLogs.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.gv.core.web.action.BaseAction;
import com.gv.core.util.ObjectUtils;
import com.gv.core.util.excel.ExcelWriter;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.pojo.logic.LvSwitchOrderLogs;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Controller("LvSwitchOrderLogsAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvSwitchOrderLogsAction extends BaseAction{
	private static final Log logger	= LogFactory.getLog(LvSwitchOrderLogsAction.class);
	private LvSwitchOrderLogs lvSwitchOrderLogs = new LvSwitchOrderLogs();
	private String userEmail;
	private String relName;
	private String storeName;
	private BufferedInputStream excelStream;
	
	/**
	 * 列表
	 * @return
	 * @throws ActionException
	 */
	public String list()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvSwitchOrderLogsAction.list() method begin*****");
		}
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("model", lvSwitchOrderLogs);
		dto.put("userEmail", userEmail);
		dto.put("relName", relName);
		dto.put("storeName", storeName);
		page = (Pagination)this.doService("LvSwitchOrderLogsService", "findPage", dto);

		
		//查询所有店铺信息
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","getAllNoShop", dto);
		this.getRequest().setAttribute("storeList", storeList);
    	if (logger.isInfoEnabled()){
			logger.info("***** LvSwitchOrderLogsAction.list() method end*****");
		}
		return LIST;
	}
	
	public String exportLogs() throws FileNotFoundException{
		if (logger.isInfoEnabled()) {
			logger.info("***** LvSwitchOrderLogsAction.list() method begin*****");
		}
		//删除文件上传临时文件夹下面所有临时文件
		deleteTempFile(request);
		
		dto.put("ids",ids);		
		List list = (List) this.doService("LvSwitchOrderLogsService", "exportLogs", dto);
		ExcelWriter writer = new ExcelWriter();
		String path = request.getRealPath("/temp") + "/" + System.currentTimeMillis() + ".tmp";
		writer.create(path, list);
		File file = new File(path);
		excelStream = new BufferedInputStream(new FileInputStream(file));
		file.delete();
		
		if (logger.isInfoEnabled()) {
			logger.info("***** LvSwitchOrderLogsAction.list() method end*****");
		}
		return "excel";
	}

	/**
	 * 
	 * @Method: deleteTempFile 
	 * @Description:  [导出文件是删除不需要的临时文件]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-12-22 下午04:49:01]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-12-22 下午04:49:01]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param request 
	 * @return void
	 */
	public void deleteTempFile(HttpServletRequest request){
		//删除文件上传临时文件夹下面所有临时文件
		String pathTmp = request.getRealPath("/temp");
		File fileTemp=new File(pathTmp);
		if(fileTemp.isDirectory()){
			 File[] f=fileTemp.listFiles();
			 for (File fs : f) {
				boolean isFlag=fs.delete();
				if (logger.isInfoEnabled()) {
					logger.info("删除文件是否成功:"+fs.getName()+"->"+isFlag);
				}
			}
		}
	}

	public LvSwitchOrderLogs getLvSwitchOrderLogs() {
		return lvSwitchOrderLogs;
	}

	public void setLvSwitchOrderLogs(LvSwitchOrderLogs lvSwitchOrderLogs) {
		this.lvSwitchOrderLogs = lvSwitchOrderLogs;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getRelName() {
		return relName;
	}

	public void setRelName(String relName) {
		this.relName = relName;
	}
	public BufferedInputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(BufferedInputStream excelStream) {
		this.excelStream = excelStream;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

}
