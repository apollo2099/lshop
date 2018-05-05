package com.lshop.manage.LvVbPlans.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;
import com.lshop.common.pojo.logic.LvExchangeRate;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.pojo.logic.LvVbPlans;
import com.lshop.manage.common.action.BaseManagerAction;

@Controller("VbPlansMngAction")
@Scope("prototype")
@SuppressWarnings("all")
public class VbPlansMngAction extends BaseManagerAction {
	private static final Log logger	= LogFactory.getLog(VbPlansMngAction.class);
	private LvVbPlans lvVbPlans = new LvVbPlans();

	public LvVbPlans getLvVbPlans() {
		return lvVbPlans;
	}

	public void setLvVbPlans(LvVbPlans lvVbPlans) {
		this.lvVbPlans = lvVbPlans;
	}
	
	/**
	 * 进入添加页面
	 * @return
	 * @throws ActionException
	 */
	public String befSave()throws ActionException{
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","findAllStore", dto);
		this.getRequest().setAttribute("storeList", storeList);
		List exchangeRateList=(List<LvExchangeRate>)this.doService("LvVbPlansService","getVBExchangRate", dto);
		this.request.setAttribute("exchangeRateList", exchangeRateList);
		return "befSave";
	}
	
	/**
	 * 添加
	 * @return
	 * @throws ActionException
	 */
	public String save()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvVbPlansMngAction.save() method begin*****");
		}
		lvVbPlans.setCreateTime(new Date());
		dto.put("model", lvVbPlans);
		LvVbPlans plans=(LvVbPlans)doService("LvVbPlansService","findCheckLvVbPlansNum",dto);
		if(plans!=null){
			json.setMessage("同一个商城里V币数量不能重复");
			json.setStatusCode(json.STATUS_CODE_ERROR);
			return AJAX;
		}
		Object obj=this.doService("LvVbPlansService", "save", dto);
		if(obj==null){
			json.setMessage("同一个商城里输入类型的数据只能添加一条");
			json.setStatusCode(json.STATUS_CODE_ERROR);
			return AJAX;
		}
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** LvVbPlansMngAction.save() method end*****");
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
			logger.info("***** LvVbPlansMngAction.list() method begin*****");
		}
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("model", lvVbPlans);
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","findAllStore", dto);
		this.getRequest().setAttribute("storeList", storeList);
		page = (Pagination)this.doService("LvVbPlansService", "findPage", dto);
        
		List exchangeRateList=(List<LvExchangeRate>)this.doService("LvVbPlansService","getVBExchangRate", dto);
		this.request.setAttribute("exchangeRateList", exchangeRateList);
    	if (logger.isInfoEnabled()){
			logger.info("***** LvVbPlansMngAction.list() method end*****");
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
			logger.info("***** LvVbPlansMngAction.view() method begin*****");
		}
		dto.put("model", lvVbPlans);
		lvVbPlans = (LvVbPlans)this.doService("LvVbPlansService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvVbPlansMngAction.view() method end*****");
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
			logger.info("***** LvVbPlansMngAction.befEdit() method begin*****");
		}
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","findAllStore", dto);
		this.getRequest().setAttribute("storeList", storeList);
		List exchangeRateList=(List<LvExchangeRate>)this.doService("LvVbPlansService","getVBExchangRate", dto);
		this.request.setAttribute("exchangeRateList", exchangeRateList);
		dto.put("model", lvVbPlans);
		lvVbPlans = (LvVbPlans)this.doService("LvVbPlansService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvVbPlansMngAction.befEdit() method end*****");
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
			logger.info("***** LvVbPlansMngAction.edit() method begin*****");
		}
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");//后台登录信息
		lvVbPlans.setModifyUserName(users.getUserName());
		lvVbPlans.setModifyUserId(users.getId());
		lvVbPlans.setUpdateTime(new Date());
		dto.put("model", lvVbPlans);
		LvVbPlans plans=(LvVbPlans)doService("LvVbPlansService","findCheckLvVbPlansNum",dto);
		if(plans!=null&&!plans.getId().equals(lvVbPlans.getId())){
			json.setMessage("同一个商城里V币数量不能重复");
			json.setStatusCode(json.STATUS_CODE_ERROR);
			return AJAX;
		}
		this.doService("LvVbPlansService", "update", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** LvVbPlansMngAction.edit() method end*****");
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
			logger.info("***** LvVbPlansMngAction.del() method begin*****");
		}
		dto.put("model", lvVbPlans);
		//删除model
		this.doService("LvVbPlansService", "del", dto);
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvVbPlansMngAction.del() method end*****");
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
			logger.info("***** LvVbPlansMngAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				lvVbPlans.setId(Integer.parseInt(temp_ids[i].trim()));
				dto.put("model", lvVbPlans);
				//删除model
				this.doService("LvVbPlansService", "del", dto);
				}
			}
		}
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvVbPlansMngAction.delList() method end*****");
		}
		return AJAX;
	}

	



}
