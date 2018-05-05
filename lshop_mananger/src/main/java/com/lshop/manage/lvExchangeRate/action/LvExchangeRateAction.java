/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvExchangeRate.action;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.web.action.BaseAction;
import com.gv.core.util.ObjectUtils;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;
import com.lshop.common.pojo.logic.LvExchangeRate;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.common.action.BaseManagerAction;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Controller("LvExchangeRateAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvExchangeRateAction extends  BaseManagerAction{
	private static final Log logger	= LogFactory.getLog(LvExchangeRateAction.class);
	private LvExchangeRate lvExchangeRate=new LvExchangeRate();

	/**
	 * 列表
	 * @return
	 * @throws ActionException
	 */
	public String list()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvExchangeRateAction.list() method begin*****");
		}
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("model", lvExchangeRate);
		
		page = (Pagination)this.doService("LvExchangeRateService", "findPage", dto);

    	if (logger.isInfoEnabled()){
			logger.info("***** LvExchangeRateAction.list() method end*****");
		}
		return LIST;
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
			logger.info("***** LvExchangeRateAction.save() method begin*****");
		}
		lvExchangeRate.setCode(CodeUtils.getCode());//code设置
		lvExchangeRate.setCreateTime(new Date());   //创建时间
		dto.put("model", lvExchangeRate);
		//判断兑换币种是否已经存在
		Boolean isFlag=(Boolean) this.doService("LvExchangeRateService", "isExistRate", dto);
		if(!isFlag){
			json.setMessage("该兑现币种已经存在，请重新选择!");
			json.setStatusCode(300);
			return AJAX;
		}
		
		this.doService("LvExchangeRateService", "save", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** LvExchangeRateAction.save() method end*****");
		}
		return AJAX;
	}
	


	/**
	 * 详情
	 * @return
	 * @throws ActionException
	 */
	public String view()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvExchangeRateAction.view() method begin*****");
		}
		dto.put("model", lvExchangeRate);
		lvExchangeRate = (LvExchangeRate)this.doService("LvExchangeRateService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvExchangeRateAction.view() method end*****");
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
			logger.info("***** LvExchangeRateAction.befEdit() method begin*****");
		}
		//获取登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		lvExchangeRate.setModifyUserId(users.getId());
		lvExchangeRate.setModifyUserName(users.getUserName());
		lvExchangeRate.setModifyTime(new Date());
		dto.put("model", lvExchangeRate);
		lvExchangeRate = (LvExchangeRate)this.doService("LvExchangeRateService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvExchangeRateAction.befEdit() method end*****");
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
			logger.info("***** LvExchangeRateAction.edit() method begin*****");
		}
		//获取登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		lvExchangeRate.setModifyUserId(users.getId());
		lvExchangeRate.setModifyUserName(users.getUserName());
		lvExchangeRate.setModifyTime(new Date());
		dto.put("model", lvExchangeRate);
		
		if(!lvExchangeRate.getOldCurrency().equals(lvExchangeRate.getCurrency())){
			//判断兑换币种是否已经存在
			Boolean isFlag=(Boolean) this.doService("LvExchangeRateService", "isExistRate", dto);
			if(!isFlag){
				json.setMessage("该兑现币种已经存在，请重新选择!");
				json.setStatusCode(300);
				return AJAX;
			}
		}
		
		this.doService("LvExchangeRateService", "update", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** LvExchangeRateAction.edit() method end*****");
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
			logger.info("***** LvExchangeRateAction.del() method begin*****");
		}
		dto.put("model", lvExchangeRate);
		//删除model
		this.doService("LvExchangeRateService", "del", dto);
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvExchangeRateAction.del() method end*****");
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
			logger.info("***** LvExchangeRateAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				lvExchangeRate.setId(Integer.parseInt(temp_ids[i].trim()));
				dto.put("model", lvExchangeRate);
				//删除model
				this.doService("LvExchangeRateService", "del", dto);
				}
			}
		}
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvExchangeRateAction.delList() method end*****");
		}
		return AJAX;
	}

	public LvExchangeRate getLvExchangeRate() {
		return lvExchangeRate;
	}

	public void setLvExchangeRate(LvExchangeRate lvExchangeRate) {
		this.lvExchangeRate = lvExchangeRate;
	}
}
