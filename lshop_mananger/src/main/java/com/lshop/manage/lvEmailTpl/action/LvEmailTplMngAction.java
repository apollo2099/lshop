/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvEmailTpl.action;

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
import com.lshop.common.pojo.logic.LvEmailTpl;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.common.action.BaseManagerAction;

/**
 * @author 好视网络-软件二部：唐迪
 * @version 1.0
 * @since 1.0
 */
@Controller("LvEmailTplMngAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvEmailTplMngAction extends BaseManagerAction {
	private static final Log logger = LogFactory.getLog(LvEmailTplMngAction.class);
	private LvEmailTpl lvEmailTpl = new LvEmailTpl();

	

	/**
	 * 进入添加页面
	 * 
	 * @return
	 * @throws ActionException
	 */
	public String befSave() throws ActionException {
		//查询所有店铺信息
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","getAll", dto);
		this.getRequest().setAttribute("storeList", storeList);
		return "befSave";
	}

	/**
	 * 添加
	 * 
	 * @return
	 * @throws ActionException
	 */
	public String save() throws ActionException {
		if (logger.isInfoEnabled()) {
			logger.info("***** LvEmailTplMngAction.save() method begin*****");
		}
		lvEmailTpl.setCode(CodeUtils.getCode());//code设置
		lvEmailTpl.setCreateTime(new Date());   //创建时间
		lvEmailTpl.setTplKey(lvEmailTpl.getTplKey().trim());// 去掉左右空格
		dto.put("model", lvEmailTpl);
		
		Boolean isFlag=(Boolean) this.doService("LvEmailTplService", "isExistEmailTpl", dto);
		if(!isFlag){
			json.setMessage("该邮件模板的key已经存在，请重新输入!");
			json.setStatusCode(300);
			return AJAX;
		}
		
		this.doService("LvEmailTplService", "save", dto);
		if (logger.isInfoEnabled()) {
			logger.info("***** LvEmailTplMngAction.save() method end*****");
		}
		return AJAX;
	}

	/**
	 * 列表
	 * 
	 * @return
	 * @throws ActionException
	 */
	public String list() throws ActionException {
		if (logger.isInfoEnabled()) {
			logger.info("***** LvEmailTplMngAction.list() method begin*****");
		}
		// 设置显示页数
		if (ObjectUtils.isNotEmpty(page.getNumPerPage())) {
			// page.setPageNumShown(page.getNumPerPage());
		}
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("page", page);
		dto.put("model", lvEmailTpl);
		page = (Pagination) this.doService("LvEmailTplService", "findPage", dto);
		
		//查询所有店铺信息
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","getAll", dto);
		this.getRequest().setAttribute("storeList", storeList);
		
		if (logger.isInfoEnabled()) {
			logger.info("***** LvEmailTplMngAction.list() method end*****");
		}
		return LIST;
	}

	/**
	 * 详情
	 * 
	 * @return
	 * @throws ActionException
	 */
	public String view() throws ActionException {
		if (logger.isInfoEnabled()) {
			logger.info("***** LvEmailTplMngAction.view() method begin*****");
		}
		dto.put("model", lvEmailTpl);
		lvEmailTpl = (LvEmailTpl) this.doService("LvEmailTplService", "get", dto);
		if (logger.isInfoEnabled()) {
			logger.info("***** LvEmailTplMngAction.view() method end*****");
		}
		return "view";
	}

	/**
	 * 跳入编辑页面
	 * 
	 * @return
	 * @throws ActionException
	 */
	public String befEdit() throws ActionException {
		if (logger.isInfoEnabled()) {
			logger.info("***** LvEmailTplMngAction.befEdit() method begin*****");
		}
		dto.put("model", lvEmailTpl);
		lvEmailTpl = (LvEmailTpl) this.doService("LvEmailTplService", "get", dto);
		
		//查询所有店铺信息
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","getAll", dto);
		this.getRequest().setAttribute("storeList", storeList);
		
		if (logger.isInfoEnabled()) {
			logger.info("***** LvEmailTplMngAction.befEdit() method end*****");
		}
		return "edit";
	}

	/**
	 * 编辑
	 * 
	 * @return
	 * @throws ActionException
	 */
	public String edit() throws ActionException {
		if (logger.isInfoEnabled()) {
			logger.info("***** LvEmailTplMngAction.edit() method begin*****");
		}
		//获取登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		lvEmailTpl.setModifyUserId(users.getId());
		lvEmailTpl.setModifyUserName(users.getUserName());
		lvEmailTpl.setModifyTime(new Date());
		lvEmailTpl.setTplKey(lvEmailTpl.getTplKey().trim());// 去掉左右空格
		dto.put("model", lvEmailTpl);
		if(!lvEmailTpl.getOldTplKey().trim().equals(lvEmailTpl.getTplKey())){
			Boolean isFlag=(Boolean) this.doService("LvEmailTplService", "isExistEmailTpl", dto);
			if(!isFlag){
				json.setMessage("该邮件模板的key已经存在，请重新输入!");
				json.setStatusCode(300);
				return AJAX;
			}
		}
		this.doService("LvEmailTplService", "update", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()) {
			logger.info("***** LvEmailTplMngAction.edit() method end*****");
		}
		return AJAX;
	}

	/**
	 * 删除
	 * 
	 * @return
	 * @throws ActionException
	 */
	public String del() throws ActionException {
		if (logger.isInfoEnabled()) {
			logger.info("***** LvEmailTplMngAction.del() method begin*****");
		}
		dto.put("model", lvEmailTpl);
		// 删除model
		this.doService("LvEmailTplService", "del", dto);
		json.doNavTabTodo();
		json.setCallbackType(null);
		if (logger.isInfoEnabled()) {
			logger.info("***** LvEmailTplMngAction.del() method end*****");
		}
		return AJAX;
	}

	/**
	 * 批量删除
	 * 
	 * @return
	 * @throws ActionException
	 */
	public String delList() throws ActionException {
		if (logger.isInfoEnabled()) {
			logger.info("***** LvEmailTplMngAction.delList() method begin*****");
		}
		if (ids != null && ids.length() > 0) {
			String[] temp_ids = ids.split(",");
			for (int i = 0; i < temp_ids.length; i++) {
				if (temp_ids[i].length() > 0) {
					lvEmailTpl.setId(Integer.parseInt(temp_ids[i].trim()));
					dto.put("model", lvEmailTpl);
					// 删除model
					this.doService("LvEmailTplService", "del", dto);
				}
			}
		}
		json.doNavTabTodo();
		if (logger.isInfoEnabled()) {
			logger.info("***** LvEmailTplMngAction.delList() method end*****");
		}
		return AJAX;
	}
	
	public LvEmailTpl getLvEmailTpl() {
		return lvEmailTpl;
	}
	
	public void setLvEmailTpl(LvEmailTpl lvEmailTpl) {
		this.lvEmailTpl = lvEmailTpl;
	}

}
