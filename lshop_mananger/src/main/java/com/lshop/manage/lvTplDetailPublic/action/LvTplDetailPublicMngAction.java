/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvTplDetailPublic.action;

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
import com.lshop.common.pojo.logic.LvTplDetailPublic;
import com.lshop.common.pojo.logic.LvTplModelPublic;
import com.lshop.common.util.CodeUtils;

/**
 * @author 好视网络-网站研发部：唐迪
 * @version 1.0
 * @since 1.0
 */@Controller("LvTplDetailPublicMngAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvTplDetailPublicMngAction extends BaseAction{
	private static final Log logger	= LogFactory.getLog(LvTplDetailPublicMngAction.class);
	private LvTplDetailPublic lvTplDetailPublic = new LvTplDetailPublic();
    private LvTplModelPublic lvTplModelPublic;
	public LvTplDetailPublic getLvTplDetailPublic() {
		return lvTplDetailPublic;
	}

	public void setLvTplDetailPublic(LvTplDetailPublic lvTplDetailPublic) {
		this.lvTplDetailPublic = lvTplDetailPublic;
	}
	
	/**
	 * 进入添加页面
	 * @return
	 * @throws ActionException
	 */
	public String befSave()throws ActionException{
		dto.put("model", lvTplModelPublic);
		lvTplModelPublic = (LvTplModelPublic)this.doService("LvTplModelPublicService", "get", dto);
		return "befSave";
	}
	
	/**
	 * 添加
	 * @return
	 * @throws ActionException
	 */
	public String save()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvTplDetailPublicMngAction.save() method begin*****");
		}
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");//后台登录信息
		lvTplDetailPublic.setModifyUserId(users.getId());
		lvTplDetailPublic.setModifyUserName(users.getUserName());
		lvTplDetailPublic.setCreateTime(new Date());
		lvTplDetailPublic.setCode(CodeUtils.getCode());
		dto.put("model", lvTplDetailPublic);
		Boolean bflag=(Boolean)this.doService("LvTplDetailPublicService", "findCheckNameOrPath", dto);
		if(bflag==false){
		this.doService("LvTplDetailPublicService", "save", dto);
		json.setStatusCode(200);
		}else{
			json.setMessage("该模板明细名称或路径已存在相同数据！");
			json.setStatusCode(json.STATUS_CODE_ERROR);
		}
		if (logger.isInfoEnabled()){
			logger.info("***** LvTplDetailPublicMngAction.save() method end*****");
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
			logger.info("***** LvTplDetailPublicMngAction.list() method begin*****");
		}
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("page", page);
		lvTplDetailPublic.setTplModelCode(lvTplModelPublic.getCode());
		dto.put("model", lvTplDetailPublic);
		page = (Pagination)this.doService("LvTplDetailPublicService", "findPage", dto);
         
		List modelPublicList=(List<LvTplModelPublic>)this.doService("LvTplModelPublicService", "findAll", dto);
		this.getRequest().setAttribute("modelPublicList", modelPublicList);
    	if (logger.isInfoEnabled()){
			logger.info("***** LvTplDetailPublicMngAction.list() method end*****");
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
			logger.info("***** LvTplDetailPublicMngAction.view() method begin*****");
		}
		dto.put("model", lvTplDetailPublic);
		lvTplDetailPublic = (LvTplDetailPublic)this.doService("LvTplDetailPublicService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvTplDetailPublicMngAction.view() method end*****");
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
			logger.info("***** LvTplDetailPublicMngAction.befEdit() method begin*****");
		}
		dto.put("model", lvTplDetailPublic);
		lvTplDetailPublic = (LvTplDetailPublic)this.doService("LvTplDetailPublicService", "get", dto);
		List modelPublicList=(List<LvTplModelPublic>)this.doService("LvTplModelPublicService", "findAll", dto);
		this.getRequest().setAttribute("modelPublicList", modelPublicList);
		if (logger.isInfoEnabled()){
			logger.info("***** LvTplDetailPublicMngAction.befEdit() method end*****");
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
			logger.info("***** LvTplDetailPublicMngAction.edit() method begin*****");
		}
		
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");//后台登录信息
		lvTplDetailPublic.setModifyUserId(users.getId());
		lvTplDetailPublic.setModifyUserName(users.getUserName());
		lvTplDetailPublic.setModifyTime(new Date());//设置修改改时间
		dto.put("model", lvTplDetailPublic);
		this.doService("LvTplDetailPublicService", "update", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** LvTplDetailPublicMngAction.edit() method end*****");
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
			logger.info("***** LvTplDetailPublicMngAction.del() method begin*****");
		}
		dto.put("model", lvTplDetailPublic);
		//删除model
		this.doService("LvTplDetailPublicService", "del", dto);

		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvTplDetailPublicMngAction.del() method end*****");
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
			logger.info("***** LvTplDetailPublicMngAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				lvTplDetailPublic.setId(Integer.parseInt(temp_ids[i].trim()));
				dto.put("model", lvTplDetailPublic);
				//删除model
				this.doService("LvTplDetailPublicService", "del", dto);
				}
			}
		}
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvTplDetailPublicMngAction.delList() method end*****");
		}
		return AJAX;
	}

	public LvTplModelPublic getLvTplModelPublic() {
		return lvTplModelPublic;
	}

	public void setLvTplModelPublic(LvTplModelPublic lvTplModelPublic) {
		this.lvTplModelPublic = lvTplModelPublic;
	}

}
