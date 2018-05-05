/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.weixin.wxTextMaterial.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.core.web.action.BaseAction;
import com.gv.core.util.ObjectUtils;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;
import com.lshop.manage.weixin.pojo.WxGzhConfig;
import com.lshop.manage.weixin.pojo.WxTextMaterial;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Controller("WxTextMaterialMngAction")
@Scope("prototype")
@SuppressWarnings("all")
public class WxTextMaterialMngAction extends BaseAction{
	private static final Log logger	= LogFactory.getLog(WxTextMaterialMngAction.class);
	private WxTextMaterial wxTextMaterial = new WxTextMaterial();

	public WxTextMaterial getWxTextMaterial() {
		return wxTextMaterial;
	}

	public void setWxTextMaterial(WxTextMaterial wxTextMaterial) {
		this.wxTextMaterial = wxTextMaterial;
	}
	
	/**
	 * 进入添加页面
	 * @return
	 * @throws ActionException
	 */
	public String befSave()throws ActionException{
		getWxgzh();
		return "befSave";
	}
	
	/**
	 * 添加
	 * @return
	 * @throws ActionException
	 */
	public String save()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** WxTextMaterialMngAction.save() method begin*****");
		}
		wxTextMaterial.setCreateTime(new Date());
		wxTextMaterial.setModifyTime(new Date());
		dto.put("model", wxTextMaterial);
		this.doService("WxTextMaterialService", "save", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** WxTextMaterialMngAction.save() method end*****");
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
			logger.info("***** WxTextMaterialMngAction.list() method begin*****");
		}
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("model", wxTextMaterial);
		
		page = (Pagination)this.doService("WxTextMaterialService", "findPage", dto);
		getWxgzh();
    	if (logger.isInfoEnabled()){
			logger.info("***** WxTextMaterialMngAction.list() method end*****");
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
			logger.info("***** WxTextMaterialMngAction.view() method begin*****");
		}
		dto.put("model", wxTextMaterial);
		wxTextMaterial = (WxTextMaterial)this.doService("WxTextMaterialService", "get", dto);
		getWxgzh();
		if (logger.isInfoEnabled()){
			logger.info("***** WxTextMaterialMngAction.view() method end*****");
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
			logger.info("***** WxTextMaterialMngAction.befEdit() method begin*****");
		}
		dto.put("model", wxTextMaterial);
		wxTextMaterial = (WxTextMaterial)this.doService("WxTextMaterialService", "get", dto);
		getWxgzh();
		if (logger.isInfoEnabled()){
			logger.info("***** WxTextMaterialMngAction.befEdit() method end*****");
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
			logger.info("***** WxTextMaterialMngAction.edit() method begin*****");
		}
		wxTextMaterial.setModifyTime(new Date());
		dto.put("model", wxTextMaterial);
		this.doService("WxTextMaterialService", "update", dto);
		getWxgzh();
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** WxTextMaterialMngAction.edit() method end*****");
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
			logger.info("***** WxTextMaterialMngAction.del() method begin*****");
		}
		dto.put("model", wxTextMaterial);
		//删除model
		this.doService("WxTextMaterialService", "del", dto);
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** WxTextMaterialMngAction.del() method end*****");
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
			logger.info("***** WxTextMaterialMngAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				wxTextMaterial.setId(Integer.parseInt(temp_ids[i].trim()));
				dto.put("model", wxTextMaterial);
				//删除model
				this.doService("WxTextMaterialService", "del", dto);
				}
			}
		}
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** WxTextMaterialMngAction.delList() method end*****");
		}
		return AJAX;
	}
	
	/**
	 * 查询所有的公众号配置信息
	 */
	private void getWxgzh(){
		List<WxGzhConfig> list = (List<WxGzhConfig>)this.doService("WxGzhConfigService", "getAllWxgzh", dto);
		this.request.setAttribute("wxhConfigList", list);
	}


}
