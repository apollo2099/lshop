/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.weixin.wxObtain.action;

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
import com.lshop.manage.weixin.pojo.WxNewsMaterial;
import com.lshop.manage.weixin.pojo.WxObtain;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Controller("WxObtainMngAction")
@Scope("prototype")
@SuppressWarnings("all")
public class WxObtainMngAction extends BaseAction{
	private static final Log logger	= LogFactory.getLog(WxObtainMngAction.class);
	private WxObtain wxObtain = new WxObtain();

	public WxObtain getWxObtain() {
		return wxObtain;
	}

	public void setWxObtain(WxObtain wxObtain) {
		this.wxObtain = wxObtain;
	}
	
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
			logger.info("***** WxObtainMngAction.save() method begin*****");
		}

		dto.put("model", wxObtain);
		this.doService("WxObtainService", "save", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** WxObtainMngAction.save() method end*****");
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
			logger.info("***** WxObtainMngAction.list() method begin*****");
		}
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("model", wxObtain);
		
		page = (Pagination)this.doService("WxObtainService", "findPage", dto);
		getWxgzh();
    	if (logger.isInfoEnabled()){
			logger.info("***** WxObtainMngAction.list() method end*****");
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
			logger.info("***** WxObtainMngAction.view() method begin*****");
		}
		dto.put("model", wxObtain);
		wxObtain = (WxObtain)this.doService("WxObtainService", "get", dto);
		getWxgzh();
		if (logger.isInfoEnabled()){
			logger.info("***** WxObtainMngAction.view() method end*****");
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
			logger.info("***** WxObtainMngAction.befEdit() method begin*****");
		}
		dto.put("model", wxObtain);
		wxObtain = (WxObtain)this.doService("WxObtainService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** WxObtainMngAction.befEdit() method end*****");
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
			logger.info("***** WxObtainMngAction.edit() method begin*****");
		}
		dto.put("model", wxObtain);
		this.doService("WxObtainService", "update", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** WxObtainMngAction.edit() method end*****");
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
			logger.info("***** WxObtainMngAction.del() method begin*****");
		}
		dto.put("model", wxObtain);
		//删除model
		this.doService("WxObtainService", "del", dto);
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** WxObtainMngAction.del() method end*****");
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
			logger.info("***** WxObtainMngAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				wxObtain.setId(Integer.parseInt(temp_ids[i].trim()));
				dto.put("model", wxObtain);
				//删除model
				this.doService("WxObtainService", "del", dto);
				}
			}
		}
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** WxObtainMngAction.delList() method end*****");
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
