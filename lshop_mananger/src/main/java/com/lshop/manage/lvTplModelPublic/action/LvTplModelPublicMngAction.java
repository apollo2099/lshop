/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvTplModelPublic.action;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;
import com.gv.core.util.ObjectUtils;
import com.gv.core.web.action.BaseAction;
import com.lshop.common.pojo.logic.LvTplModelPublic;
import com.lshop.common.util.CodeUtils;

/**
 * @author 好视网络-网站研发部：唐迪
 * @version 1.0
 * @since 1.0
 */@Controller("LvTplModelPublicMngAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvTplModelPublicMngAction extends BaseAction{
	private static final Log logger	= LogFactory.getLog(LvTplModelPublicMngAction.class);
	private LvTplModelPublic lvTplModelPublic = new LvTplModelPublic();

	public LvTplModelPublic getLvTplModelPublic() {
		return lvTplModelPublic;
	}

	public void setLvTplModelPublic(LvTplModelPublic lvTplModelPublic) {
		this.lvTplModelPublic = lvTplModelPublic;
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
			logger.info("***** LvTplModelPublicMngAction.save() method begin*****");
		}
		 BaseUsers users =(BaseUsers)getSession().getAttribute("USER");//后台登录信息
		 lvTplModelPublic.setModifyUserId(users.getId());
		 lvTplModelPublic.setModifyUserName(users.getUserName());
		 lvTplModelPublic.setCreateTime(new Date());
		 lvTplModelPublic.setCode(CodeUtils.getCode());//设置关联关系值
		 dto.put("model", lvTplModelPublic);
		 Boolean bflag=(Boolean)this.doService("LvTplModelPublicService", "findCheckName", dto);
		 if(false==bflag){
		 this.doService("LvTplModelPublicService", "save", dto);
		 json.setStatusCode(200);
		 }else{
		 json.setMessage("该模板类的名称存在！");
		 json.setStatusCode(json.STATUS_CODE_ERROR);
		 }
		if (logger.isInfoEnabled()){
			logger.info("***** LvTplModelPublicMngAction.save() method end*****");
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
			logger.info("***** LvTplModelPublicMngAction.list() method begin*****");
		}

		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("model", lvTplModelPublic);
		
		page = (Pagination)this.doService("LvTplModelPublicService", "findPage", dto);

    	if (logger.isInfoEnabled()){
			logger.info("***** LvTplModelPublicMngAction.list() method end*****");
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
			logger.info("***** LvTplModelPublicMngAction.view() method begin*****");
		}
		dto.put("model", lvTplModelPublic);
		lvTplModelPublic = (LvTplModelPublic)this.doService("LvTplModelPublicService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvTplModelPublicMngAction.view() method end*****");
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
			logger.info("***** LvTplModelPublicMngAction.befEdit() method begin*****");
		}
		dto.put("model", lvTplModelPublic);
		lvTplModelPublic = (LvTplModelPublic)this.doService("LvTplModelPublicService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvTplModelPublicMngAction.befEdit() method end*****");
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
			logger.info("***** LvTplModelPublicMngAction.edit() method begin*****");
		}
		lvTplModelPublic.setModifyTime(new Date());
		dto.put("model", lvTplModelPublic);
		this.doService("LvTplModelPublicService", "update", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** LvTplModelPublicMngAction.edit() method end*****");
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
			logger.info("***** LvTplModelPublicMngAction.del() method begin*****");
		}
		dto.put("model", lvTplModelPublic);
		//删除model
		Integer isuccess=(Integer)this.doService("LvTplModelPublicService", "del", dto);
		if(isuccess==2){
			json.setStatusCode(json.STATUS_CODE_ERROR);
			json.setMessage("该模板已存在明细数据，不能删除！");
		
		}else{
		json.setStatusCode(200);
		}
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvTplModelPublicMngAction.del() method end*****");
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
			logger.info("***** LvTplModelPublicMngAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				lvTplModelPublic.setId(Integer.parseInt(temp_ids[i]));
				dto.put("model", lvTplModelPublic);
				//删除model
				this.doService("LvTplModelPublicService", "del", dto);
				}
			}
		}
		json.setStatusCode(200);
//		json.setMessage("删除成功！");
//		json.setNavTabId("lvTplModelPublic");
		json.setCallbackType(null);
//		json.reSetStatusCode(result.getAppCode(), result.getAppMsg());
		if (logger.isInfoEnabled()){
			logger.info("***** LvTplModelPublicMngAction.delList() method end*****");
		}
		return AJAX;
	}

}
