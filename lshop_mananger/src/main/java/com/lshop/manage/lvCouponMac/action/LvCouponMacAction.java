/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvCouponMac.action;

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

import com.lshop.common.pojo.logic.LvCouponMac;
import com.lshop.common.pojo.logic.LvStore;
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
@Controller("LvCouponMacAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvCouponMacAction extends BaseAction{
	private static final Log logger	= LogFactory.getLog(LvCouponMacAction.class);
	private LvCouponMac lvCouponMac = new LvCouponMac();
	private BufferedInputStream excelStream;
	
	/**
	 * 列表
	 * @return
	 * @throws ActionException
	 */
	public String list()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponMacAction.list() method begin*****");
		}
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("lvCouponMac", lvCouponMac);
		
		page = (Pagination)this.doService("LvCouponMacService", "findPage", dto);

    	if (logger.isInfoEnabled()){
			logger.info("***** LvCouponMacAction.list() method end*****");
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
			logger.info("***** LvCouponMacAction.view() method begin*****");
		}
		dto.put("lvCouponMac", lvCouponMac);
		lvCouponMac = (LvCouponMac)this.doService("LvCouponMacService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponMacAction.view() method end*****");
		}
		return "view";
	}
	
	
	public String exportCouponMac() throws FileNotFoundException{
		if (logger.isInfoEnabled()) {
			logger.info("***** LvCouponMacAction.exportCouponMac() method begin*****");
		}
		//删除文件上传临时文件夹下面所有临时文件
		//deleteTempFile(request);
		
		dto.put("ids",ids);		
		List list = (List) this.doService("LvCouponMacService", "exportCouponMac", dto);
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
			logger.info("***** LvCouponMacAction.del() method begin*****");
		}
		dto.put("lvCouponMac", lvCouponMac);
		//删除lvCouponMac
		this.doService("LvCouponMacService", "del", dto);
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponMacAction.del() method end*****");
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
			logger.info("***** LvCouponMacAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				lvCouponMac.setId(Integer.parseInt(temp_ids[i].trim()));
				dto.put("lvCouponMac", lvCouponMac);
				//删除lvCouponMac
				this.doService("LvCouponMacService", "del", dto);
				}
			}
		}
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvCouponMacAction.delList() method end*****");
		}
		return AJAX;
	}

	
	public LvCouponMac getLvCouponMac() {
		return lvCouponMac;
	}

	public void setLvCouponMac(LvCouponMac lvCouponMac) {
		this.lvCouponMac = lvCouponMac;
	}

	public BufferedInputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(BufferedInputStream excelStream) {
		this.excelStream = excelStream;
	}
	
}
