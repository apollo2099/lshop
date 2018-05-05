/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvHelpType.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;
import com.gv.core.util.ObjectUtils;
import com.gv.core.web.action.BaseAction;
import com.lshop.common.pojo.logic.LvHelpType;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.common.action.BaseManagerAction;

/**
 * @author 好视网络-软件二部：唐迪
 * @version 1.0
 * @since 1.0
 */@Controller("LvHelpTypeMngAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvHelpTypeMngAction extends BaseManagerAction{
	private static final Log logger	= LogFactory.getLog(LvHelpTypeMngAction.class);
	private LvHelpType lvHelpType=new LvHelpType();

	public LvHelpType getLvHelpType() {
		return lvHelpType;
	}

	public void setLvHelpType(LvHelpType lvHelpType) {
		this.lvHelpType = lvHelpType;
	}
	
	/**
	 * 进入添加页面
	 * @return
	 * @throws ActionException
	 */
	public String befSave()throws ActionException{
		//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","findAllStore", dto);
		this.getRequest().setAttribute("storeList", storeList);
		return "befSave";
	}
	
	/**
	 * 添加
	 * @return
	 * @throws ActionException
	 */
	public String save()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvHelpTypeMngAction.save() method begin*****");
		}
		lvHelpType.setCode(CodeUtils.getCode());//code设置
		lvHelpType.setCreateTime(new Date());   //创建时间
		dto.put("model", lvHelpType);
		this.doService("LvHelpTypeService", "save", dto);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvHelpTypeMngAction.save() method end*****");
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
			logger.info("***** LvHelpTypeMngAction.list() method begin*****");
		}

		dto.put("page", page);
		dto.put("model", lvHelpType);
		
		page = (Pagination)this.doService("LvHelpTypeService", "findPage", dto);
		
		//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","findAllStore", dto);
		this.getRequest().setAttribute("storeList", storeList);
		
    	if (logger.isInfoEnabled()){
			logger.info("***** LvHelpTypeMngAction.list() method end*****");
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
			logger.info("***** LvHelpTypeMngAction.view() method begin*****");
		}
		dto.put("model", lvHelpType);
		lvHelpType = (LvHelpType)this.doService("LvHelpTypeService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvHelpTypeMngAction.view() method end*****");
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
			logger.info("***** LvHelpTypeMngAction.befEdit() method begin*****");
		}
		dto.put("model", lvHelpType);
		lvHelpType = (LvHelpType)this.doService("LvHelpTypeService", "get", dto);
		
		//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","findAllStore", dto);
		this.getRequest().setAttribute("storeList", storeList);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvHelpTypeMngAction.befEdit() method end*****");
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
			logger.info("***** LvHelpTypeMngAction.edit() method begin*****");
		}
		//获取登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		lvHelpType.setModifyUserId(users.getId());
		lvHelpType.setModifyUserName(users.getUserName());
		lvHelpType.setModifyTime(new Date());
		dto.put("model", lvHelpType);
		this.doService("LvHelpTypeService", "update", dto);

		if (logger.isInfoEnabled()){
			logger.info("***** LvHelpTypeMngAction.edit() method end*****");
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
			logger.info("***** LvHelpTypeMngAction.del() method begin*****");
		}
		dto.put("model", lvHelpType);
		//删除model
		this.doService("LvHelpTypeService", "del", dto);

		json.doNavTabTodo();
		if (logger.isInfoEnabled()){
			logger.info("***** LvHelpTypeMngAction.del() method end*****");
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
			logger.info("***** LvHelpTypeMngAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				lvHelpType.setId(Integer.parseInt(temp_ids[i].trim()));
				dto.put("model", lvHelpType);
				//删除model
				this.doService("LvHelpTypeService", "del", dto);
				}
			}
		}
		json.doNavTabTodo();
		if (logger.isInfoEnabled()){
			logger.info("***** LvHelpTypeMngAction.delList() method end*****");
		}
		return AJAX;
	}

}
