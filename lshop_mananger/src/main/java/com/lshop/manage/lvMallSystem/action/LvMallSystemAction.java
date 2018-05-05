/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvMallSystem.action;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lshop.common.pojo.logic.LvMallSystem;
import com.lshop.common.util.CodeUtils;
import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.web.action.BaseAction;
import com.gv.core.util.ObjectUtils;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Controller("LvMallSystemAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvMallSystemAction extends BaseAction{
	private static final Log logger	= LogFactory.getLog(LvMallSystemAction.class);
	private LvMallSystem lvMallSystem = new LvMallSystem();

	public LvMallSystem getLvMallSystem() {
		return lvMallSystem;
	}

	public void setLvMallSystem(LvMallSystem lvMallSystem) {
		this.lvMallSystem = lvMallSystem;
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
			logger.info("***** LvMallSystemAction.save() method begin*****");
		}
		dto.put("mallSystemFlag", lvMallSystem.getMallSystemFlag());
		Boolean flag=(Boolean) this.doService("LvMallSystemService", "isExistMallSystemFlag", dto);
		if(flag){
			json.setMessage("该商城体系标示已经存在,请重新添加！");
			json.setStatusCode(300);
			return AJAX;
		}
		lvMallSystem.setMallSystemFlag(lvMallSystem.getMallSystemFlag().trim());
		lvMallSystem.setDomainPostfix(lvMallSystem.getDomainPostfix().trim());
		lvMallSystem.setCode(CodeUtils.getCode());//code设置
		lvMallSystem.setCreateTime(new Date());   //创建时间
		dto.put("lvMallSystem", lvMallSystem);
		this.doService("LvMallSystemService", "save", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** LvMallSystemAction.save() method end*****");
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
			logger.info("***** LvMallSystemAction.list() method begin*****");
		}
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("lvMallSystem", lvMallSystem);
		
		page = (Pagination)this.doService("LvMallSystemService", "findPage", dto);

    	if (logger.isInfoEnabled()){
			logger.info("***** LvMallSystemAction.list() method end*****");
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
			logger.info("***** LvMallSystemAction.view() method begin*****");
		}
		dto.put("lvMallSystem", lvMallSystem);
		lvMallSystem = (LvMallSystem)this.doService("LvMallSystemService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvMallSystemAction.view() method end*****");
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
			logger.info("***** LvMallSystemAction.befEdit() method begin*****");
		}
		dto.put("lvMallSystem", lvMallSystem);
		lvMallSystem = (LvMallSystem)this.doService("LvMallSystemService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvMallSystemAction.befEdit() method end*****");
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
			logger.info("***** LvMallSystemAction.edit() method begin*****");
		}
		if(ObjectUtils.isNotEmpty(lvMallSystem.getMallSystemFlag())&&ObjectUtils.isNotEmpty(lvMallSystem.getOldMallSystemFlag())){
			if(!lvMallSystem.getMallSystemFlag().trim().equals(lvMallSystem.getOldMallSystemFlag().trim())){
				dto.put("mallSystemFlag", lvMallSystem.getMallSystemFlag());
				Boolean flag=(Boolean) this.doService("LvMallSystemService", "isExistMallSystemFlag", dto);
				if(flag){
					json.setMessage("该商城体系标示已经存在,请重新添加！");
					json.setStatusCode(300);
					return AJAX;
				}
			}
		}
		
		
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		lvMallSystem.setModifyUserId(users.getId());
		lvMallSystem.setModifyUserName(users.getUserName());
		lvMallSystem.setModifyTime(new Date());
		lvMallSystem.setMallSystemFlag(lvMallSystem.getMallSystemFlag().trim());
		lvMallSystem.setDomainPostfix(lvMallSystem.getDomainPostfix().trim());
		dto.put("lvMallSystem", lvMallSystem);
		this.doService("LvMallSystemService", "update", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** LvMallSystemAction.edit() method end*****");
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
			logger.info("***** LvMallSystemAction.del() method begin*****");
		}
		dto.put("lvMallSystem", lvMallSystem);
		//删除lvMallSystem
		this.doService("LvMallSystemService", "del", dto);
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvMallSystemAction.del() method end*****");
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
			logger.info("***** LvMallSystemAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				lvMallSystem.setId(Integer.parseInt(temp_ids[i].trim()));
				dto.put("lvMallSystem", lvMallSystem);
				//删除lvMallSystem
				this.doService("LvMallSystemService", "del", dto);
				}
			}
		}
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvMallSystemAction.delList() method end*****");
		}
		return AJAX;
	}

	
	public String initMallSystem()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvMallSystemAction.initMallSystem() method end*****");
		}
		this.doService("LvMallSystemService", "initMallSystem", dto);
		json.setStatusCode(200);
		json.setCallbackType(null);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvMallSystemAction.initMallSystem() method end*****");
		}
		return AJAX;
	}
}
