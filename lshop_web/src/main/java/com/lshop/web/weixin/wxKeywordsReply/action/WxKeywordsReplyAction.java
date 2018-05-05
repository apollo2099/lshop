/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.web.weixin.wxKeywordsReply.action;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lshop.web.weixin.common.pojo.WxKeywordsReply;
import com.gv.core.web.action.BaseAction;
import com.gv.core.util.ObjectUtils;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Controller("WxKeywordsReplyAction")
@Scope("prototype")
@SuppressWarnings("all")
public class WxKeywordsReplyAction extends BaseAction{
	private static final Log logger	= LogFactory.getLog(WxKeywordsReplyAction.class);
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
		return "befSave";
	}
	
	/**
	 * 添加
	 * @return
	 * @throws ActionException
	 */
	public String save()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** WxKeywordsReplyAction.save() method begin*****");
		}

		dto.put("model", wxKeywordsReply);
		this.doService("WxKeywordsReplyService", "save", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** WxKeywordsReplyAction.save() method end*****");
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
			logger.info("***** WxKeywordsReplyAction.list() method begin*****");
		}
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("model", wxKeywordsReply);
		
		page = (Pagination)this.doService("WxKeywordsReplyService", "findPage", dto);

    	if (logger.isInfoEnabled()){
			logger.info("***** WxKeywordsReplyAction.list() method end*****");
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
			logger.info("***** WxKeywordsReplyAction.view() method begin*****");
		}
		dto.put("model", wxKeywordsReply);
		wxKeywordsReply = (WxKeywordsReply)this.doService("WxKeywordsReplyService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** WxKeywordsReplyAction.view() method end*****");
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
			logger.info("***** WxKeywordsReplyAction.befEdit() method begin*****");
		}
		dto.put("model", wxKeywordsReply);
		wxKeywordsReply = (WxKeywordsReply)this.doService("WxKeywordsReplyService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** WxKeywordsReplyAction.befEdit() method end*****");
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
			logger.info("***** WxKeywordsReplyAction.edit() method begin*****");
		}
		dto.put("model", wxKeywordsReply);
		this.doService("WxKeywordsReplyService", "update", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** WxKeywordsReplyAction.edit() method end*****");
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
			logger.info("***** WxKeywordsReplyAction.del() method begin*****");
		}
		dto.put("model", wxKeywordsReply);
		//删除model
		this.doService("WxKeywordsReplyService", "del", dto);
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** WxKeywordsReplyAction.del() method end*****");
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
			logger.info("***** WxKeywordsReplyAction.delList() method begin*****");
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
			logger.info("***** WxKeywordsReplyAction.delList() method end*****");
		}
		return AJAX;
	}

}
