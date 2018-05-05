/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvShopTags.action;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.web.action.BaseAction;
import com.gv.core.util.ObjectUtils;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;
import com.lshop.common.pojo.logic.LvShopTags;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.common.action.BaseManagerAction;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Controller("LvShopTagsAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvShopTagsAction extends BaseManagerAction{
	private static final Log logger	= LogFactory.getLog(LvShopTagsAction.class);
	private LvShopTags lvShopTags = new LvShopTags();

	
	
	/**
	 * 进入添加页面
	 * @return
	 * @throws ActionException
	 */
	public String befSave()throws ActionException{
		
		//查询所有商城信息
//		List<LvStore> shopList= (List<LvStore>) this.doService("LvStoreService","getShopList", dto);
//		this.getRequest().setAttribute("shopList", shopList);
		//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
		List<LvStore> shopList= (List<LvStore>) this.doService("LvStoreService","findAllStore", dto);
		this.getRequest().setAttribute("shopList", shopList);
		return "befSave";
	}
	
	/**
	 * 添加
	 * @return
	 * @throws ActionException
	 */
	public String save()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvShopTagsAction.save() method begin*****");
		}
		lvShopTags.setCode(CodeUtils.getCode());//code设置
		lvShopTags.setCreateTime(new Date());   //创建时间
		dto.put("model", lvShopTags);
		this.doService("LvShopTagsService", "save", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** LvShopTagsAction.save() method end*****");
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
			logger.info("***** LvShopTagsAction.list() method begin*****");
		}
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("model", lvShopTags);
		
		page = (Pagination)this.doService("LvShopTagsService", "findPage", dto);

		//查询所有商城信息
//		List<LvStore> shopList= (List<LvStore>) this.doService("LvStoreService","getShopList", dto);
//		this.getRequest().setAttribute("shopList", shopList);
		//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
		List<LvStore> shopList= (List<LvStore>) this.doService("LvStoreService","findAllStore", dto);
		this.getRequest().setAttribute("shopList", shopList);
		
    	if (logger.isInfoEnabled()){
			logger.info("***** LvShopTagsAction.list() method end*****");
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
			logger.info("***** LvShopTagsAction.view() method begin*****");
		}
		dto.put("model", lvShopTags);
		lvShopTags = (LvShopTags)this.doService("LvShopTagsService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvShopTagsAction.view() method end*****");
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
			logger.info("***** LvShopTagsAction.befEdit() method begin*****");
		}
		dto.put("model", lvShopTags);
		lvShopTags = (LvShopTags)this.doService("LvShopTagsService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvShopTagsAction.befEdit() method end*****");
		}
		
		//查询所有商城信息
//		List<LvStore> shopList= (List<LvStore>) this.doService("LvStoreService","getShopList", dto);
//		this.getRequest().setAttribute("shopList", shopList);
		//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
		List<LvStore> shopList= (List<LvStore>) this.doService("LvStoreService","findAllStore", dto);
		this.getRequest().setAttribute("shopList", shopList);
		return "edit";
	}
	/**
	 * 编辑
	 * @return
	 * @throws ActionException
	 */
	public String edit()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvShopTagsAction.edit() method begin*****");
		}
		//获取登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		lvShopTags.setModifyUserId(users.getId());
		lvShopTags.setModifyUserName(users.getUserName());
		lvShopTags.setModifyTime(new Date());
		dto.put("model", lvShopTags);
		this.doService("LvShopTagsService", "update", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** LvShopTagsAction.edit() method end*****");
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
			logger.info("***** LvShopTagsAction.del() method begin*****");
		}
		dto.put("model", lvShopTags);
		//删除model
		this.doService("LvShopTagsService", "del", dto);
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvShopTagsAction.del() method end*****");
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
			logger.info("***** LvShopTagsAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				lvShopTags.setId(Integer.parseInt(temp_ids[i].trim()));
				dto.put("model", lvShopTags);
				//删除model
				this.doService("LvShopTagsService", "del", dto);
				}
			}
		}
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvShopTagsAction.delList() method end*****");
		}
		return AJAX;
	}
	
	public LvShopTags getLvShopTags() {
		return lvShopTags;
	}

	public void setLvShopTags(LvShopTags lvShopTags) {
		this.lvShopTags = lvShopTags;
	}

}
