/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvBanner.action;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;
import com.gv.core.web.action.BaseAction;
import com.lshop.common.pojo.logic.LvBanner;
import com.lshop.common.util.CodeUtils;

/**
 * @author 好视网络-网站研发部：唐迪
 * @version 1.0
 * @since 1.0
 */@Controller("LvBannerMngAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvBannerMngAction extends BaseAction{
	private static final Log logger	= LogFactory.getLog(LvBannerMngAction.class);
	private LvBanner lvBanner = new LvBanner();

	public LvBanner getLvBanner() {
		return lvBanner;
	}

	public void setLvBanner(LvBanner lvBanner) {
		this.lvBanner = lvBanner;
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
			logger.info("***** LvBannerMngAction.save() method begin*****");
		}
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");//后台登录信息
		lvBanner.setModifyUserId(users.getId());
		lvBanner.setModifyUserName(users.getUserName());
		lvBanner.setCode(CodeUtils.getCode());
		lvBanner.setCreateTime(new Date());
		dto.put("model", lvBanner);
		this.doService("LvBannerService", "save", dto);
		
		json.setStatusCode(200);

		if (logger.isInfoEnabled()){
			logger.info("***** LvBannerMngAction.save() method end*****");
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
			logger.info("***** LvBannerMngAction.list() method begin*****");
		}
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("model", lvBanner);
		
		page = (Pagination)this.doService("LvBannerService", "findPage", dto);

    	if (logger.isInfoEnabled()){
			logger.info("***** LvBannerMngAction.list() method end*****");
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
			logger.info("***** LvBannerMngAction.view() method begin*****");
		}
		dto.put("model", lvBanner);
		lvBanner = (LvBanner)this.doService("LvBannerService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvBannerMngAction.view() method end*****");
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
			logger.info("***** LvBannerMngAction.befEdit() method begin*****");
		}
		dto.put("model", lvBanner);
		lvBanner = (LvBanner)this.doService("LvBannerService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvBannerMngAction.befEdit() method end*****");
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
			logger.info("***** LvBannerMngAction.edit() method begin*****");
		}
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");//后台登录信息
		lvBanner.setModifyUserId(users.getId());
		lvBanner.setModifyUserName(users.getUserName());
		lvBanner.setModifyTime(new Date());
		dto.put("model", lvBanner);
		this.doService("LvBannerService", "update", dto);
		json.setStatusCode(200);

		if (logger.isInfoEnabled()){
			logger.info("***** LvBannerMngAction.edit() method end*****");
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
			logger.info("***** LvBannerMngAction.del() method begin*****");
		}
		dto.put("model", lvBanner);
		//删除model
		this.doService("LvBannerService", "del", dto);

		json.setStatusCode(200);
//		json.setMessage("成功！");
//		json.setNavTabId("lvBanner");
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvBannerMngAction.del() method end*****");
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
			logger.info("***** LvBannerMngAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				lvBanner.setId(Integer.parseInt(temp_ids[i]));
				dto.put("model", lvBanner);
				//删除model
				this.doService("LvBannerService", "del", dto);
				}
			}
		}
		json.setStatusCode(200);
//		json.setMessage("删除成功！");
//		json.setNavTabId("lvBanner");
		json.setCallbackType(null);
//		json.reSetStatusCode(result.getAppCode(), result.getAppMsg());
		if (logger.isInfoEnabled()){
			logger.info("***** LvBannerMngAction.delList() method end*****");
		}
		return AJAX;
	}

}
