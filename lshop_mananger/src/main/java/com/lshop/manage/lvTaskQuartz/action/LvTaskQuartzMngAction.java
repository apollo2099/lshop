/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvTaskQuartz.action;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;
import com.gv.core.web.action.BaseAction;
import com.lshop.common.pojo.logic.LvTaskQuartz;

/**
 * @author 好视网络-网站研发部：tangd
 * @version 1.0
 * @since 1.0
 */
 @Controller("LvTaskQuartzMngAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvTaskQuartzMngAction extends BaseAction{
	private static final Log logger	= LogFactory.getLog(LvTaskQuartzMngAction.class);
	private LvTaskQuartz lvTaskQuartz = new LvTaskQuartz();


	


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
			logger.info("***** TtaskQuartzMngAction.save() method begin*****");
		}
		lvTaskQuartz.setCreateTime(new Date());
		lvTaskQuartz.setStatus(1);
		dto.put("model", lvTaskQuartz);
		this.doService("LvTaskQuartzService", "save", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** TtaskQuartzMngAction.save() method end*****");
		}
		return AJAX;
	}
	
	public String start()throws ActionException{
		dto.put("model", lvTaskQuartz);
		lvTaskQuartz = (LvTaskQuartz)this.doService("LvTaskQuartzService", "get", dto);
		lvTaskQuartz.setStatus(1);//开启
		dto.put("model", lvTaskQuartz);
		this.doService("LvTaskQuartzService", "updatePojo", dto);
		
		dto.put("lvTaskQuartz", lvTaskQuartz);
		this.doService("TaskService", "resumeJob", dto);
		json.doNavTabTodo();
		return AJAX;
	}
	
	public String stop()throws ActionException{
		dto.put("model", lvTaskQuartz);
		lvTaskQuartz = (LvTaskQuartz)this.doService("LvTaskQuartzService", "get", dto);
		lvTaskQuartz.setStatus(0);//停止
		
		dto.put("model", lvTaskQuartz);
		this.doService("LvTaskQuartzService", "updatePojo", dto);
		
		dto.put("lvTaskQuartz", lvTaskQuartz);
		this.doService("TaskService", "pauseJob", dto);
		json.doNavTabTodo();
		return AJAX;
	}
	
	/**
	 * 列表
	 * @return
	 * @throws ActionException
	 */
	public String list()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** TtaskQuartzMngAction.list() method begin*****");
		}
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("model", lvTaskQuartz);
		
		page = (Pagination)this.doService("LvTaskQuartzService", "findPage", dto);

    	if (logger.isInfoEnabled()){
			logger.info("***** TtaskQuartzMngAction.list() method end*****");
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
			logger.info("***** TtaskQuartzMngAction.view() method begin*****");
		}
		dto.put("model", lvTaskQuartz);
		lvTaskQuartz = (LvTaskQuartz)this.doService("LvTaskQuartzService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** TtaskQuartzMngAction.view() method end*****");
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
			logger.info("***** TtaskQuartzMngAction.befEdit() method begin*****");
		}
		dto.put("model", lvTaskQuartz);
		lvTaskQuartz = (LvTaskQuartz)this.doService("LvTaskQuartzService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** TtaskQuartzMngAction.befEdit() method end*****");
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
			logger.info("***** TtaskQuartzMngAction.edit() method begin*****");
		}
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		lvTaskQuartz.setModifyUserId(users.getId());
		lvTaskQuartz.setModifyTime(new Date());
		lvTaskQuartz.setModifyUserName(users.getUserName());
		dto.put("model", lvTaskQuartz);
		this.doService("LvTaskQuartzService", "update", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** TtaskQuartzMngAction.edit() method end*****");
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
			logger.info("***** TtaskQuartzMngAction.del() method begin*****");
		}
		dto.put("model", lvTaskQuartz);
		//删除model
		this.doService("LvTaskQuartzService", "del", dto);
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** TtaskQuartzMngAction.del() method end*****");
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
			logger.info("***** TtaskQuartzMngAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
					lvTaskQuartz.setId(Integer.parseInt(temp_ids[i].trim()));
				dto.put("model", lvTaskQuartz);
				//删除model
				this.doService("LvTaskQuartzService", "del", dto);
				}
			}
		}
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** TtaskQuartzMngAction.delList() method end*****");
		}
		return AJAX;
	}

	
	public LvTaskQuartz getLvTaskQuartz() {
		return lvTaskQuartz;
	}

	public void setLvTaskQuartz(LvTaskQuartz lvTaskQuartz) {
		this.lvTaskQuartz = lvTaskQuartz;
	}
}
