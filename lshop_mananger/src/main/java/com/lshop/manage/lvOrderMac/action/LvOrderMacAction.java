/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvOrderMac.action;

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

import com.lshop.common.pojo.logic.LvOrderMac;
import com.gv.core.web.action.BaseAction;
import com.gv.core.util.ObjectUtils;
import com.gv.core.util.excel.ExcelWriter;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */
@Controller("LvOrderMacAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvOrderMacAction extends BaseAction{
	private static final Log logger	= LogFactory.getLog(LvOrderMacAction.class);
	private LvOrderMac lvOrderMac = new LvOrderMac();
	private BufferedInputStream excelStream;

	
	/**
	 * 列表
	 * @return
	 * @throws ActionException
	 */
	public String list()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderMacAction.list() method begin*****");
		}
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("lvOrderMac", lvOrderMac);
		
		page = (Pagination)this.doService("LvOrderMacService", "findPage", dto);

    	if (logger.isInfoEnabled()){
			logger.info("***** LvOrderMacAction.list() method end*****");
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
			logger.info("***** LvOrderMacAction.view() method begin*****");
		}
		dto.put("lvOrderMac", lvOrderMac);
		lvOrderMac = (LvOrderMac)this.doService("LvOrderMacService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderMacAction.view() method end*****");
		}
		return "view";
	}
	
	
	/**
	 * 
	 * @Method: exportOrderMac 
	 * @Description:  [一句话描述该类的功能]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2015年7月11日 下午2:27:09]   
	 * @UpdateUser:   [liaoxj]     
	 * @UpdateDate:   [2015年7月11日 下午2:27:09]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws FileNotFoundException 
	 * @return String
	 */
	public String exportOrderMac() throws FileNotFoundException{
		if (logger.isInfoEnabled()) {
			logger.info("***** LvCouponMacAction.exportCouponMac() method begin*****");
		}
		//删除文件上传临时文件夹下面所有临时文件
		//deleteTempFile(request);
		
		dto.put("ids",ids);		
		List list = (List) this.doService("LvOrderMacService", "exportOrderMac", dto);
		ExcelWriter writer = new ExcelWriter();
		String path = request.getRealPath("/temp") + "/" + System.currentTimeMillis() + ".tmp";
		writer.create(path, list);
		File file = new File(path);
		excelStream = new BufferedInputStream(new FileInputStream(file));
		file.delete();
		
		if (logger.isInfoEnabled()) {
			logger.info("***** LvCouponMacAction.exportCouponMac() method end*****");
		}
		return "excel";
	}
	
	
	/**
	 * 删除
	 * @return
	 * @throws ActionException
	 */
	public String del()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderMacAction.del() method begin*****");
		}
		dto.put("lvOrderMac", lvOrderMac);
		//删除lvOrderMac
		this.doService("LvOrderMacService", "del", dto);
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderMacAction.del() method end*****");
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
			logger.info("***** LvOrderMacAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				lvOrderMac.setId(Integer.parseInt(temp_ids[i].trim()));
				dto.put("lvOrderMac", lvOrderMac);
				//删除lvOrderMac
				this.doService("LvOrderMacService", "del", dto);
				}
			}
		}
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderMacAction.delList() method end*****");
		}
		return AJAX;
	}
	
	
	
	public LvOrderMac getLvOrderMac() {
		return lvOrderMac;
	}

	public void setLvOrderMac(LvOrderMac lvOrderMac) {
		this.lvOrderMac = lvOrderMac;
	}

	public BufferedInputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(BufferedInputStream excelStream) {
		this.excelStream = excelStream;
	}
	
}
