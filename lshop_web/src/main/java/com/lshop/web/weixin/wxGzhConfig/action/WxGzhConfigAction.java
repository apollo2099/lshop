/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.web.weixin.wxGzhConfig.action;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lshop.web.weixin.common.pojo.WxGzhConfig;
import com.gv.core.web.action.BaseAction;
import com.gv.core.util.ObjectUtils;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Controller("WxGzhConfigAction")
@Scope("prototype")
@SuppressWarnings("all")
public class WxGzhConfigAction extends BaseAction{
	private static final Log logger	= LogFactory.getLog(WxGzhConfigAction.class);
	private WxGzhConfig wxGzhConfig = new WxGzhConfig();

	public WxGzhConfig getWxGzhConfig() {
		return wxGzhConfig;
	}

	public void setWxGzhConfig(WxGzhConfig wxGzhConfig) {
		this.wxGzhConfig = wxGzhConfig;
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
			logger.info("***** WxGzhConfigAction.save() method begin*****");
		}

		dto.put("model", wxGzhConfig);
		this.doService("WxGzhConfigService", "save", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** WxGzhConfigAction.save() method end*****");
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
			logger.info("***** WxGzhConfigAction.list() method begin*****");
		}
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("model", wxGzhConfig);
		
		page = (Pagination)this.doService("WxGzhConfigService", "findPage", dto);

    	if (logger.isInfoEnabled()){
			logger.info("***** WxGzhConfigAction.list() method end*****");
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
			logger.info("***** WxGzhConfigAction.view() method begin*****");
		}
		dto.put("model", wxGzhConfig);
		wxGzhConfig = (WxGzhConfig)this.doService("WxGzhConfigService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** WxGzhConfigAction.view() method end*****");
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
			logger.info("***** WxGzhConfigAction.befEdit() method begin*****");
		}
		dto.put("model", wxGzhConfig);
		wxGzhConfig = (WxGzhConfig)this.doService("WxGzhConfigService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** WxGzhConfigAction.befEdit() method end*****");
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
			logger.info("***** WxGzhConfigAction.edit() method begin*****");
		}
		dto.put("model", wxGzhConfig);
		this.doService("WxGzhConfigService", "update", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** WxGzhConfigAction.edit() method end*****");
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
			logger.info("***** WxGzhConfigAction.del() method begin*****");
		}
		dto.put("model", wxGzhConfig);
		//删除model
		this.doService("WxGzhConfigService", "del", dto);
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** WxGzhConfigAction.del() method end*****");
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
			logger.info("***** WxGzhConfigAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				wxGzhConfig.setId(Integer.parseInt(temp_ids[i].trim()));
				dto.put("model", wxGzhConfig);
				//删除model
				this.doService("WxGzhConfigService", "del", dto);
				}
			}
		}
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** WxGzhConfigAction.delList() method end*****");
		}
		return AJAX;
	}

}
