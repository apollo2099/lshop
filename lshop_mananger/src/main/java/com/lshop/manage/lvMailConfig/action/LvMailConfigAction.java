/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvMailConfig.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lshop.common.pojo.logic.LvMailConfig;
import com.lshop.common.pojo.logic.LvTplModelPublic;
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
 */@Controller("LvMailConfigAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvMailConfigAction extends BaseAction{
	private static final Log logger	= LogFactory.getLog(LvMailConfigAction.class);
	private LvMailConfig lvMailConfig = new LvMailConfig();


	
	/**
	 * 进入添加页面
	 * @return
	 * @throws ActionException
	 */
	public String befSave()throws ActionException{
		//查询商城体系信息
		List<LvTplModelPublic> mallSystemList=(List)this.doService("LvMallSystemService", "findAll", dto);
		this.getRequest().setAttribute("mallSystemList", mallSystemList);
		return "befSave";
	}
	
	/**
	 * 添加
	 * @return
	 * @throws ActionException
	 */
	public String save()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvMailConfigAction.save() method begin*****");
		}
		lvMailConfig.setCode(CodeUtils.getCode());//code设置
		lvMailConfig.setCreateTime(new Date());   //创建时间
		dto.put("lvMailConfig", lvMailConfig);
		this.doService("LvMailConfigService", "save", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** LvMailConfigAction.save() method end*****");
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
			logger.info("***** LvMailConfigAction.list() method begin*****");
		}
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("lvMailConfig", lvMailConfig);
		
		page = (Pagination)this.doService("LvMailConfigService", "findPage", dto);

    	if (logger.isInfoEnabled()){
			logger.info("***** LvMailConfigAction.list() method end*****");
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
			logger.info("***** LvMailConfigAction.view() method begin*****");
		}
		dto.put("lvMailConfig", lvMailConfig);
		lvMailConfig = (LvMailConfig)this.doService("LvMailConfigService", "get", dto);
		
		//查询商城体系信息
		List<LvTplModelPublic> mallSystemList=(List)this.doService("LvMallSystemService", "findAll", dto);
		this.getRequest().setAttribute("mallSystemList", mallSystemList);
		if (logger.isInfoEnabled()){
			logger.info("***** LvMailConfigAction.view() method end*****");
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
			logger.info("***** LvMailConfigAction.befEdit() method begin*****");
		}
		dto.put("lvMailConfig", lvMailConfig);
		lvMailConfig = (LvMailConfig)this.doService("LvMailConfigService", "get", dto);
		
		//查询商城体系信息
		List<LvTplModelPublic> mallSystemList=(List)this.doService("LvMallSystemService", "findAll", dto);
		this.getRequest().setAttribute("mallSystemList", mallSystemList);
		if (logger.isInfoEnabled()){
			logger.info("***** LvMailConfigAction.befEdit() method end*****");
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
			logger.info("***** LvMailConfigAction.edit() method begin*****");
		}
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		lvMailConfig.setModifyUserId(users.getId());
		lvMailConfig.setModifyUserName(users.getUserName());
		lvMailConfig.setModifyTime(new Date());
		dto.put("lvMailConfig", lvMailConfig);
		this.doService("LvMailConfigService", "update", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** LvMailConfigAction.edit() method end*****");
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
			logger.info("***** LvMailConfigAction.del() method begin*****");
		}
		dto.put("lvMailConfig", lvMailConfig);
		//删除lvMailConfig
		this.doService("LvMailConfigService", "del", dto);
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvMailConfigAction.del() method end*****");
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
			logger.info("***** LvMailConfigAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				lvMailConfig.setId(Integer.parseInt(temp_ids[i].trim()));
				dto.put("lvMailConfig", lvMailConfig);
				//删除lvMailConfig
				this.doService("LvMailConfigService", "del", dto);
				}
			}
		}
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvMailConfigAction.delList() method end*****");
		}
		return AJAX;
	}
	
	public LvMailConfig getLvMailConfig() {
		return lvMailConfig;
	}

	public void setLvMailConfig(LvMailConfig lvMailConfig) {
		this.lvMailConfig = lvMailConfig;
	}

}
