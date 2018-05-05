/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvTplDetail.action;

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
import com.lshop.common.pojo.logic.LvTplDetail;
import com.lshop.common.pojo.logic.LvTplModel;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.common.action.BaseManagerAction;

/**
 * @author 好视网络-网站研发部：唐迪
 * @version 1.0
 * @since 1.0
 */@Controller("LvTplDetailMngAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvTplDetailMngAction  extends BaseManagerAction{
	private static final Log logger	= LogFactory.getLog(LvTplDetailMngAction.class);
	private LvTplDetail lvTplDetail = new LvTplDetail();
	private LvTplModel lvTplModel;

	public LvTplDetail getLvTplDetail() {
		return lvTplDetail;
	}

	public void setLvTplDetail(LvTplDetail lvTplDetail) {
		this.lvTplDetail = lvTplDetail;
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
			logger.info("***** LvTplDetailMngAction.save() method begin*****");
		}
		lvTplDetail.setCreateTime(new Date());
		lvTplDetail.setIsdel(0);
		lvTplDetail.setCode(CodeUtils.getCode());
		//this.setFlag();
		lvTplDetail.setStoreFlag(lvTplModel.getStoreFlag());
		dto.put("model", lvTplDetail);
		Boolean bflag=(Boolean)this.doService("LvTplDetailService", "findCheckNameOrPath", dto);
		if(bflag==false){
		dto.put("rootPath", this.getText("lshop.web.path")+"/"+lvTplDetail.getStoreFlag()+"/tpl");
		this.doService("LvTplDetailService", "save", dto);
		json.setStatusCode(200);
		}else{
			json.setMessage("该模板明细名称或路径已存在相同数据！");
			json.setStatusCode(json.STATUS_CODE_ERROR);
		}
		if (logger.isInfoEnabled()){
			logger.info("***** LvTplDetailMngAction.save() method end*****");
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
			logger.info("***** LvTplDetailMngAction.list() method begin*****");
		}
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("model", lvTplDetail);
		page = (Pagination)this.doService("LvTplDetailService", "findPage", dto);

    	if (logger.isInfoEnabled()){
			logger.info("***** LvTplDetailMngAction.list() method end*****");
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
			logger.info("***** LvTplDetailMngAction.view() method begin*****");
		}
		dto.put("model", lvTplDetail);
		lvTplDetail = (LvTplDetail)this.doService("LvTplDetailService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvTplDetailMngAction.view() method end*****");
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
			logger.info("***** LvTplDetailMngAction.befEdit() method begin*****");
		}
		dto.put("model", lvTplDetail);
		lvTplDetail = (LvTplDetail)this.doService("LvTplDetailService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvTplDetailMngAction.befEdit() method end*****");
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
			logger.info("***** LvTplDetailMngAction.edit() method begin*****");
		}
		dto.put("model", lvTplDetail);
		dto.put("rootPath", this.getText("lshop.web.path")+"/"+lvTplDetail.getStoreFlag()+"/tpl");
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");//后台登录信息
		lvTplDetail.setModifyUserId(users.getId());
		lvTplDetail.setModifyUserName(users.getUserName());
		lvTplDetail.setModifyTime(new Date());//设置修改改时间
		this.doService("LvTplDetailService", "update", dto);
		json.setStatusCode(200);

		if (logger.isInfoEnabled()){
			logger.info("***** LvTplDetailMngAction.edit() method end*****");
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
			logger.info("***** LvTplDetailMngAction.del() method begin*****");
		}
		dto.put("model", lvTplDetail);
		//删除model
		this.doService("LvTplDetailService", "del", dto);

		json.setStatusCode(200);

		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvTplDetailMngAction.del() method end*****");
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
			logger.info("***** LvTplDetailMngAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				lvTplDetail.setId(Integer.parseInt(temp_ids[i].trim()));
				dto.put("model", lvTplDetail);
				//删除model
				this.doService("LvTplDetailService", "del", dto);
				}
			}
		}
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvTplDetailMngAction.delList() method end*****");
		}
		return AJAX;
	}

	public LvTplModel getLvTplModel() {
		return lvTplModel;
	}

	public void setLvTplModel(LvTplModel lvTplModel) {
		this.lvTplModel = lvTplModel;
	}
}
