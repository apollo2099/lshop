/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.weixin.wxPassiveReply.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.core.web.action.BaseAction;
import com.gv.core.util.ObjectUtils;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;
import com.lshop.manage.weixin.pojo.WxGzhConfig;
import com.lshop.manage.weixin.pojo.WxNewsMaterial;
import com.lshop.manage.weixin.pojo.WxPassiveReply;
import com.lshop.manage.weixin.pojo.WxTextMaterial;
import com.lshop.manage.weixin.wxPassiveReply.service.WxPassiveReplyService;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Controller("WxPassiveReplyMngAction")
@Scope("prototype")
@SuppressWarnings("all")
public class WxPassiveReplyMngAction extends BaseAction{
	private static final Log logger	= LogFactory.getLog(WxPassiveReplyMngAction.class);
	@Resource
	private WxPassiveReplyService wxPassiveReplyService;
	private WxPassiveReply wxPassiveReply = new WxPassiveReply();

	public WxPassiveReply getWxPassiveReply() {
		return wxPassiveReply;
	}

	public void setWxPassiveReply(WxPassiveReply wxPassiveReply) {
		this.wxPassiveReply = wxPassiveReply;
	}
	
	/**
	 * 进入添加页面
	 * @return
	 * @throws ActionException
	 */
	public String befSave()throws ActionException{
		getMaterials();
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
			logger.info("***** WxPassiveReplyMngAction.save() method begin*****");
		}
		List<WxPassiveReply> list = wxPassiveReplyService.findAll(null);
		for (WxPassiveReply item: list) {
			if (wxPassiveReply.getWxhConfigId() == item.getWxhConfigId().intValue()) {
				json.setStatusCode(300);
				json.setMessage("该微信号已经添加被动回复消息！");
				return AJAX;				
			}
		}
		wxPassiveReply.setCreateTime(new Date());
		wxPassiveReply.setModifyTime(new Date());
		dto.put("model", wxPassiveReply);
		this.doService("WxPassiveReplyService", "save", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** WxPassiveReplyMngAction.save() method end*****");
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
			logger.info("***** WxPassiveReplyMngAction.list() method begin*****");
		}
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		getMaterials();
		getWxgzh();
		dto.put("model", wxPassiveReply);
		
		page = (Pagination)this.doService("WxPassiveReplyService", "findPage", dto);

    	if (logger.isInfoEnabled()){
			logger.info("***** WxPassiveReplyMngAction.list() method end*****");
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
			logger.info("***** WxPassiveReplyMngAction.view() method begin*****");
		}
		dto.put("model", wxPassiveReply);
		wxPassiveReply = (WxPassiveReply)this.doService("WxPassiveReplyService", "get", dto);
		getNewMaterialsByConfigId(wxPassiveReply.getWxhConfigId());
		if (logger.isInfoEnabled()){
			logger.info("***** WxPassiveReplyMngAction.view() method end*****");
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
			logger.info("***** WxPassiveReplyMngAction.befEdit() method begin*****");
		}
		getMaterials();
		getWxgzh();
		dto.put("model", wxPassiveReply);
		wxPassiveReply = (WxPassiveReply)this.doService("WxPassiveReplyService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** WxPassiveReplyMngAction.befEdit() method end*****");
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
			logger.info("***** WxPassiveReplyMngAction.edit() method begin*****");
		}
		wxPassiveReply.setModifyTime(new Date());
		dto.put("model", wxPassiveReply);
		this.doService("WxPassiveReplyService", "update", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** WxPassiveReplyMngAction.edit() method end*****");
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
			logger.info("***** WxPassiveReplyMngAction.del() method begin*****");
		}
		dto.put("model", wxPassiveReply);
		//删除model
		this.doService("WxPassiveReplyService", "del", dto);
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** WxPassiveReplyMngAction.del() method end*****");
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
			logger.info("***** WxPassiveReplyMngAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				wxPassiveReply.setId(Integer.parseInt(temp_ids[i].trim()));
				dto.put("model", wxPassiveReply);
				//删除model
				this.doService("WxPassiveReplyService", "del", dto);
				}
			}
		}
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** WxPassiveReplyMngAction.delList() method end*****");
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
	
	private void getMaterials(){
		List<WxTextMaterial> textMaterials = (List<WxTextMaterial>)this.doService("WxTextMaterialService", "findAll", dto);
		List<WxNewsMaterial> newsMaterials = (List<WxNewsMaterial>)this.doService("WxNewsMaterialService", "findAll", dto);
		this.request.setAttribute("textMaterials", textMaterials);
		this.request.setAttribute("newsMaterials", newsMaterials);
		
	}
	
	/**
	 * 根据所选中的微信公众号配置信息 查询其下面的所有的素材 包括图文以及文本
	 * @param id
	 */
	private void getNewMaterialsByConfigId(int id){
		WxNewsMaterial wxNewsMaterial = new WxNewsMaterial();
		wxNewsMaterial.setWxhConfigId(id);
		dto.put("model", wxNewsMaterial);
		List<WxNewsMaterial>  wxNewsMaterials= (List<WxNewsMaterial>)this.doService("WxNewsMaterialService", "findAll", dto);
		request.setAttribute("newsMaterials", wxNewsMaterials);
		WxTextMaterial wxTextMaterial = new WxTextMaterial();
		wxTextMaterial.setWxhConfigId(id);
		dto.put("model", wxTextMaterial);
		List<WxTextMaterial> textMaterials = (List<WxTextMaterial>)this.doService("WxTextMaterialService", "findAll", dto);
		this.request.setAttribute("textMaterials", textMaterials);
		WxGzhConfig config = new WxGzhConfig();
		config.setId(wxPassiveReply.getWxhConfigId());
		dto.put("model", config);
		config = (WxGzhConfig)this.doService("WxGzhConfigService", "get", dto);
		this.request.setAttribute("config", config);
		
	}



}
