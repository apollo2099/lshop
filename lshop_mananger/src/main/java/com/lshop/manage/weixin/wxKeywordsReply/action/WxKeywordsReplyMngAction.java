/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.weixin.wxKeywordsReply.action;

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
import com.lshop.manage.weixin.pojo.WxKeywordsReply;
import com.lshop.manage.weixin.pojo.WxNewsMaterial;
import com.lshop.manage.weixin.pojo.WxTextMaterial;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Controller("WxKeywordsReplyMngAction")
@Scope("prototype")
@SuppressWarnings("all")
public class WxKeywordsReplyMngAction extends BaseAction{
	private static final Log logger	= LogFactory.getLog(WxKeywordsReplyMngAction.class);
	private WxKeywordsReply wxKeywordsReply = new WxKeywordsReply();

	public WxKeywordsReply getWxKeywordsReply() {
		return wxKeywordsReply;
	}

	public void setWxKeywordsReply(WxKeywordsReply wxKeywordsReply) {
		this.wxKeywordsReply = wxKeywordsReply;
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
			logger.info("***** WxKeywordsReplyMngAction.save() method begin*****");
		}
		wxKeywordsReply.setCreateTime(new Date());
		wxKeywordsReply.setModifyTime(new Date());
		dto.put("model", wxKeywordsReply);
		this.doService("WxKeywordsReplyService", "save", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** WxKeywordsReplyMngAction.save() method end*****");
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
			logger.info("***** WxKeywordsReplyMngAction.list() method begin*****");
		}
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		getMaterials();
		getWxgzh();
		dto.put("model", wxKeywordsReply);
		
		page = (Pagination)this.doService("WxKeywordsReplyService", "findPage", dto);

    	if (logger.isInfoEnabled()){
			logger.info("***** WxKeywordsReplyMngAction.list() method end*****");
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
			logger.info("***** WxKeywordsReplyMngAction.view() method begin*****");
		}
		dto.put("model", wxKeywordsReply);
		wxKeywordsReply = (WxKeywordsReply)this.doService("WxKeywordsReplyService", "get", dto);
		getNewMaterialsByConfigId(wxKeywordsReply.getWxhConfigId());
		if (logger.isInfoEnabled()){
			logger.info("***** WxKeywordsReplyMngAction.view() method end*****");
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
			logger.info("***** WxKeywordsReplyMngAction.befEdit() method begin*****");
		}
		getMaterials();
		getWxgzh();
		dto.put("model", wxKeywordsReply);
		wxKeywordsReply = (WxKeywordsReply)this.doService("WxKeywordsReplyService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** WxKeywordsReplyMngAction.befEdit() method end*****");
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
			logger.info("***** WxKeywordsReplyMngAction.edit() method begin*****");
		}
		wxKeywordsReply.setModifyTime(new Date());
		dto.put("model", wxKeywordsReply);
		this.doService("WxKeywordsReplyService", "update", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** WxKeywordsReplyMngAction.edit() method end*****");
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
			logger.info("***** WxKeywordsReplyMngAction.del() method begin*****");
		}
		dto.put("model", wxKeywordsReply);
		//删除model
		this.doService("WxKeywordsReplyService", "del", dto);
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** WxKeywordsReplyMngAction.del() method end*****");
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
			logger.info("***** WxKeywordsReplyMngAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				wxKeywordsReply.setId(Integer.parseInt(temp_ids[i].trim()));
				dto.put("model", wxKeywordsReply);
				//删除model
				this.doService("WxKeywordsReplyService", "del", dto);
				}
			}
		}
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** WxKeywordsReplyMngAction.delList() method end*****");
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
		config.setId(wxKeywordsReply.getWxhConfigId());
		dto.put("model", config);
		config = (WxGzhConfig)this.doService("WxGzhConfigService", "get", dto);
		this.request.setAttribute("config", config);
		
	}


}
