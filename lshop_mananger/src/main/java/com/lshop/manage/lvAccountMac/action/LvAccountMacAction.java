/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvAccountMac.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lshop.common.pojo.logic.LvAccountMac;
import com.gv.core.web.action.BaseAction;
import com.gv.core.util.ObjectUtils;
import com.gv.core.util.excel.ExcelWriter;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Controller("LvAccountMacAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvAccountMacAction extends BaseAction{
	private static final Log logger	= LogFactory.getLog(LvAccountMacAction.class);
	private LvAccountMac lvAccountMac = new LvAccountMac();
	private BufferedInputStream excelStream;

	/**
	 * 进入添加页面
	 * @return
	 * @throws ActionException
	 */
	public String befSave()throws ActionException{
		return "befSave";
	}
	
	/**
	 * 添加
	 * @return
	 * @throws ActionException
	 */
	public String save()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvAccountMacAction.save() method begin*****");
		}

		dto.put("lvAccountMac", lvAccountMac);
		this.doService("LvAccountMacService", "save", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** LvAccountMacAction.save() method end*****");
		}
		return AJAX;
	}
	
	/**
	 * 列表
	 * @return
	 * @throws ActionException
	 */
	public String list()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvAccountMacAction.list() method begin*****");
		}
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("lvAccountMac", lvAccountMac);
		
		page = (Pagination)this.doService("LvAccountMacService", "findPage", dto);

    	if (logger.isInfoEnabled()){
			logger.info("***** LvAccountMacAction.list() method end*****");
		}
		return LIST;
	}

	/**
	 * 详情
	 * @return
	 * @throws ActionException
	 */
	public String view()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvAccountMacAction.view() method begin*****");
		}
		dto.put("lvAccountMac", lvAccountMac);
		lvAccountMac = (LvAccountMac)this.doService("LvAccountMacService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvAccountMacAction.view() method end*****");
		}
		return "view";
	}
	
	/**
	 * 跳入编辑页面
	 * @return
	 * @throws ActionException
	 */
	public String befEdit()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvAccountMacAction.befEdit() method begin*****");
		}
		dto.put("lvAccountMac", lvAccountMac);
		lvAccountMac = (LvAccountMac)this.doService("LvAccountMacService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvAccountMacAction.befEdit() method end*****");
		}
		return "edit";
	}
	/**
	 * 编辑
	 * @return
	 * @throws ActionException
	 */
	public String edit()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvAccountMacAction.edit() method begin*****");
		}
		dto.put("lvAccountMac", lvAccountMac);
		this.doService("LvAccountMacService", "update", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** LvAccountMacAction.edit() method end*****");
		}
		return AJAX;
	}
	
	/**
	 * 删除
	 * @return
	 * @throws ActionException
	 */
	public String del()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvAccountMacAction.del() method begin*****");
		}
		dto.put("lvAccountMac", lvAccountMac);
		//删除lvAccountMac
		this.doService("LvAccountMacService", "del", dto);
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvAccountMacAction.del() method end*****");
		}
		return AJAX;
	}
	
	/**
	 * 批量删除
	 * @return
	 * @throws ActionException
	 */
	public String delList()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvAccountMacAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				lvAccountMac.setId(Integer.parseInt(temp_ids[i].trim()));
				dto.put("lvAccountMac", lvAccountMac);
				//删除lvAccountMac
				this.doService("LvAccountMacService", "del", dto);
				}
			}
		}
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvAccountMacAction.delList() method end*****");
		}
		return AJAX;
	}

	
	public String exportAccountMac() throws FileNotFoundException{
		if (logger.isInfoEnabled()) {
			logger.info("***** LvAccountMacAction.exportAccountMac() method begin*****");
		}
		//删除文件上传临时文件夹下面所有临时文件
		//deleteTempFile(request);
		
		dto.put("ids",ids);		
		List list = (List) this.doService("LvAccountMacService", "exportAccountMac", dto);
		ExcelWriter writer = new ExcelWriter();
		String path = request.getRealPath("/temp") + "/" + System.currentTimeMillis() + ".tmp";
		writer.create(path, list);
		File file = new File(path);
		excelStream = new BufferedInputStream(new FileInputStream(file));
		file.delete();
		
		if (logger.isInfoEnabled()) {
			logger.info("***** LvAccountMacAction.exportAccountMac() method end*****");
		}
		return "excel";
	}
	
	public LvAccountMac getLvAccountMac() {
		return lvAccountMac;
	}

	public void setLvAccountMac(LvAccountMac lvAccountMac) {
		this.lvAccountMac = lvAccountMac;
	}

	public BufferedInputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(BufferedInputStream excelStream) {
		this.excelStream = excelStream;
	}
	
	
	
}
