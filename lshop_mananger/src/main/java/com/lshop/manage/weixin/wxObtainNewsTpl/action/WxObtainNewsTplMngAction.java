/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.weixin.wxObtainNewsTpl.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.web.action.BaseAction;
import com.gv.core.util.ObjectUtils;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;
import com.lshop.manage.weixin.pojo.WxGzhConfig;
import com.lshop.manage.weixin.pojo.WxNewsMaterial;
import com.lshop.manage.weixin.pojo.WxObtainNewsTpl;
import com.lshop.manage.weixin.wxObtainNewsTpl.service.WxObtainNewsTplService;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Controller("WxObtainNewsTplMngAction")
@Scope("prototype")
@SuppressWarnings("all")
public class WxObtainNewsTplMngAction extends BaseAction{
	private static final Log logger	= LogFactory.getLog(WxObtainNewsTplMngAction.class);
	@Resource
	private WxObtainNewsTplService wxObtainNewsTplService;
	private WxObtainNewsTpl wxObtainNewsTpl = new WxObtainNewsTpl();

	public WxObtainNewsTpl getWxObtainNewsTpl() {
		return wxObtainNewsTpl;
	}

	public void setWxObtainNewsTpl(WxObtainNewsTpl wxObtainNewsTpl) {
		this.wxObtainNewsTpl = wxObtainNewsTpl;
	}
	
	/**
	 * 进入添加页面
	 * @return
	 * @throws ActionException
	 */
	public String befSave()throws ActionException{
		
		List<WxGzhConfig> configs = (List<WxGzhConfig>)this.doService("WxGzhConfigService", "getAllWxgzh", dto);
		this.request.setAttribute("wxhConfigs", configs);
		List<WxNewsMaterial>  wxNewsMaterials= (List<WxNewsMaterial>)this.doService("WxNewsMaterialService", "findAll", dto);
		request.setAttribute("newsMaterials", wxNewsMaterials);
		return "befSave";
	}
	
	/**
	 * 添加
	 * @return
	 * @throws ActionException
	 */
	public String save()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** WxObtainNewsTplMngAction.save() method begin*****");
		}
		List<WxObtainNewsTpl> list = wxObtainNewsTplService.findAll(null);
		for (WxObtainNewsTpl item: list) {
			if (wxObtainNewsTpl.getWxhConfigId() == item.getWxhConfigId().intValue()) {
				json.setStatusCode(300);
				json.setMessage("该微信号已经添加领取图文消息模版！");
				return AJAX;
			}
		}
		wxObtainNewsTpl.setCreateTime(new Date());
		wxObtainNewsTpl.setModifyTime(new Date());
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		wxObtainNewsTpl.setModifyUserId(users.getId());
		wxObtainNewsTpl.setModifyUserName(users.getUserName());
		dto.put("model", wxObtainNewsTpl);

		this.doService("WxObtainNewsTplService", "save", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** WxObtainNewsTplMngAction.save() method end*****");
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
			logger.info("***** WxObtainNewsTplMngAction.list() method begin*****");
		}
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		
		getWxgzhAndNewsMaterial();
		dto.put("model", wxObtainNewsTpl);
		page = (Pagination)this.doService("WxObtainNewsTplService", "findPage", dto);

    	if (logger.isInfoEnabled()){
			logger.info("***** WxObtainNewsTplMngAction.list() method end*****");
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
			logger.info("***** WxObtainNewsTplMngAction.view() method begin*****");
		}
		dto.put("model", wxObtainNewsTpl);
		wxObtainNewsTpl = (WxObtainNewsTpl)this.doService("WxObtainNewsTplService", "get", dto);
		getNewMaterialsByConfigId(wxObtainNewsTpl.getWxhConfigId());
		if (logger.isInfoEnabled()){
			logger.info("***** WxObtainNewsTplMngAction.view() method end*****");
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
			logger.info("***** WxObtainNewsTplMngAction.befEdit() method begin*****");
		}
		List<WxGzhConfig> configs = (List<WxGzhConfig>)this.doService("WxGzhConfigService", "getAllWxgzh", dto);
		this.request.setAttribute("wxhConfigs", configs);
		List<WxNewsMaterial>  wxNewsMaterials= (List<WxNewsMaterial>)this.doService("WxNewsMaterialService", "findAll", dto);
		request.setAttribute("newsMaterials", wxNewsMaterials);
		dto.put("model", wxObtainNewsTpl);
		wxObtainNewsTpl = (WxObtainNewsTpl)this.doService("WxObtainNewsTplService", "get", dto);
		
		if (logger.isInfoEnabled()){
			logger.info("***** WxObtainNewsTplMngAction.befEdit() method end*****");
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
			logger.info("***** WxObtainNewsTplMngAction.edit() method begin*****");
		}
		wxObtainNewsTpl.setModifyTime(new Date());
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		wxObtainNewsTpl.setModifyUserId(users.getId());
		wxObtainNewsTpl.setModifyUserName(users.getUserName());
		dto.put("model", wxObtainNewsTpl);
		this.doService("WxObtainNewsTplService", "update", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** WxObtainNewsTplMngAction.edit() method end*****");
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
			logger.info("***** WxObtainNewsTplMngAction.del() method begin*****");
		}
		dto.put("model", wxObtainNewsTpl);
		//删除model
		this.doService("WxObtainNewsTplService", "del", dto);
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** WxObtainNewsTplMngAction.del() method end*****");
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
			logger.info("***** WxObtainNewsTplMngAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				wxObtainNewsTpl.setId(Integer.parseInt(temp_ids[i].trim()));
				dto.put("model", wxObtainNewsTpl);
				//删除model
				this.doService("WxObtainNewsTplService", "del", dto);
				}
			}
		}
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** WxObtainNewsTplMngAction.delList() method end*****");
		}
		return AJAX;
	}

	/**
	 * 查询所有的公众号配置信息以及图文素材
	 */
	private void getWxgzhAndNewsMaterial(){
		WxNewsMaterial wxNewsMaterial = new WxNewsMaterial();
		dto.put("model", wxNewsMaterial);
		List<WxNewsMaterial>  wxNewsMaterials= (List<WxNewsMaterial>)this.doService("WxNewsMaterialService", "findAll", dto);
		request.setAttribute("newsMaterials", wxNewsMaterials);
		List<WxGzhConfig> list = (List<WxGzhConfig>)this.doService("WxGzhConfigService", "getAllWxgzh", dto);
		this.request.setAttribute("wxhConfigList", list);
	}

	/**
	 * 根据所选中的微信公众号配置信息 查询其下面的所有的图文素材
	 * @param id
	 */
	private void getNewMaterialsByConfigId(int id){
		WxNewsMaterial wxNewsMaterial = new WxNewsMaterial();
		wxNewsMaterial.setWxhConfigId(id);
		dto.put("model", wxNewsMaterial);
		List<WxNewsMaterial>  wxNewsMaterials= (List<WxNewsMaterial>)this.doService("WxNewsMaterialService", "findAll", dto);
		request.setAttribute("newsMaterials", wxNewsMaterials);
		WxGzhConfig config = new WxGzhConfig();
		config.setId(wxObtainNewsTpl.getWxhConfigId());
		dto.put("model", config);
		config = (WxGzhConfig)this.doService("WxGzhConfigService", "get", dto);
		this.request.setAttribute("config", config);
		
	}

}
