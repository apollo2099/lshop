/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.web.weixin.wxNewsMaterialItem.action;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lshop.web.weixin.common.pojo.WxNewsMaterialItem;
import com.gv.core.web.action.BaseAction;
import com.gv.core.util.ObjectUtils;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Controller("WxNewsMaterialItemAction")
@Scope("prototype")
@SuppressWarnings("all")
public class WxNewsMaterialItemAction extends BaseAction{
	private static final Log logger	= LogFactory.getLog(WxNewsMaterialItemAction.class);
	private WxNewsMaterialItem wxNewsMaterialItem = new WxNewsMaterialItem();

	public WxNewsMaterialItem getWxNewsMaterialItem() {
		return wxNewsMaterialItem;
	}

	public void setWxNewsMaterialItem(WxNewsMaterialItem wxNewsMaterialItem) {
		this.wxNewsMaterialItem = wxNewsMaterialItem;
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
			logger.info("***** WxNewsMaterialItemAction.save() method begin*****");
		}

		dto.put("model", wxNewsMaterialItem);
		this.doService("WxNewsMaterialItemService", "save", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** WxNewsMaterialItemAction.save() method end*****");
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
			logger.info("***** WxNewsMaterialItemAction.list() method begin*****");
		}
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("model", wxNewsMaterialItem);
		
		page = (Pagination)this.doService("WxNewsMaterialItemService", "findPage", dto);

    	if (logger.isInfoEnabled()){
			logger.info("***** WxNewsMaterialItemAction.list() method end*****");
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
			logger.info("***** WxNewsMaterialItemAction.view() method begin*****");
		}
		dto.put("model", wxNewsMaterialItem);
		wxNewsMaterialItem = (WxNewsMaterialItem)this.doService("WxNewsMaterialItemService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** WxNewsMaterialItemAction.view() method end*****");
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
			logger.info("***** WxNewsMaterialItemAction.befEdit() method begin*****");
		}
		dto.put("model", wxNewsMaterialItem);
		wxNewsMaterialItem = (WxNewsMaterialItem)this.doService("WxNewsMaterialItemService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** WxNewsMaterialItemAction.befEdit() method end*****");
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
			logger.info("***** WxNewsMaterialItemAction.edit() method begin*****");
		}
		dto.put("model", wxNewsMaterialItem);
		this.doService("WxNewsMaterialItemService", "update", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** WxNewsMaterialItemAction.edit() method end*****");
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
			logger.info("***** WxNewsMaterialItemAction.del() method begin*****");
		}
		dto.put("model", wxNewsMaterialItem);
		//删除model
		this.doService("WxNewsMaterialItemService", "del", dto);
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** WxNewsMaterialItemAction.del() method end*****");
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
			logger.info("***** WxNewsMaterialItemAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				wxNewsMaterialItem.setId(Integer.parseInt(temp_ids[i].trim()));
				dto.put("model", wxNewsMaterialItem);
				//删除model
				this.doService("WxNewsMaterialItemService", "del", dto);
				}
			}
		}
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** WxNewsMaterialItemAction.delList() method end*****");
		}
		return AJAX;
	}

}
