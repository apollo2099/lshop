/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvShopSubject.action;

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
import com.lshop.common.pojo.logic.LvShopSubject;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.common.action.BaseManagerAction;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Controller("LvShopSubjectAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvShopSubjectAction extends BaseManagerAction{
	private static final Log logger	= LogFactory.getLog(LvShopSubjectAction.class);
	private LvShopSubject lvShopSubject = new LvShopSubject();

	public LvShopSubject getLvShopSubject() {
		return lvShopSubject;
	}

	public void setLvShopSubject(LvShopSubject lvShopSubject) {
		this.lvShopSubject = lvShopSubject;
	}
	
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
			logger.info("***** LvShopSubjectAction.save() method begin*****");
		}
		lvShopSubject.setCode(CodeUtils.getCode());//code设置
		lvShopSubject.setCreateTime(new Date());   //创建时间
		dto.put("model", lvShopSubject);
		Boolean isFlag=(Boolean) this.doService("LvShopSubjectService", "IsExistSubject", dto);
		if(isFlag){
			json.setStatusCode(300);
			json.setMessage("该栏目名称已经存在，请重新填写!");
			return AJAX;
		}
		
		this.doService("LvShopSubjectService", "save", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** LvShopSubjectAction.save() method end*****");
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
			logger.info("***** LvShopSubjectAction.list() method begin*****");
		}
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("model", lvShopSubject);
		
		page = (Pagination)this.doService("LvShopSubjectService", "findPage", dto);

		//查询所有商城信息
//		List<LvStore> shopList= (List<LvStore>) this.doService("LvStoreService","getShopList", dto);
//		this.getRequest().setAttribute("shopList", shopList);
		//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
		List<LvStore> shopList= (List<LvStore>) this.doService("LvStoreService","findAllStore", dto);
		this.getRequest().setAttribute("shopList", shopList);
    	if (logger.isInfoEnabled()){
			logger.info("***** LvShopSubjectAction.list() method end*****");
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
			logger.info("***** LvShopSubjectAction.view() method begin*****");
		}
		dto.put("model", lvShopSubject);
		lvShopSubject = (LvShopSubject)this.doService("LvShopSubjectService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvShopSubjectAction.view() method end*****");
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
			logger.info("***** LvShopSubjectAction.befEdit() method begin*****");
		}
		dto.put("model", lvShopSubject);
		lvShopSubject = (LvShopSubject)this.doService("LvShopSubjectService", "get", dto);
		
		//查询所有商城信息
//		List<LvStore> shopList= (List<LvStore>) this.doService("LvStoreService","getShopList", dto);
//		this.getRequest().setAttribute("shopList", shopList);
		//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
		List<LvStore> shopList= (List<LvStore>) this.doService("LvStoreService","findAllStore", dto);
		this.getRequest().setAttribute("shopList", shopList);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvShopSubjectAction.befEdit() method end*****");
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
			logger.info("***** LvShopSubjectAction.edit() method begin*****");
		}
		//获取登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		lvShopSubject.setModifyUserId(users.getId());
		lvShopSubject.setModifyUserName(users.getUserName());
		lvShopSubject.setModifyTime(new Date());
		dto.put("model", lvShopSubject);
		if(!lvShopSubject.getSubjectName().equals(lvShopSubject.getOldSubjectName())){
			Boolean isFlag=(Boolean) this.doService("LvShopSubjectService", "IsExistSubject", dto);
			if(isFlag){
				json.setStatusCode(300);
				json.setMessage("该栏目名称已经存在，请重新填写!");
				return AJAX;
			}
		}

		this.doService("LvShopSubjectService", "update", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** LvShopSubjectAction.edit() method end*****");
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
			logger.info("***** LvShopSubjectAction.del() method begin*****");
		}
		dto.put("model", lvShopSubject);
		
		Boolean isFlag=(Boolean) this.doService("LvShopSubjectService", "isExistShopProductList", dto);
		if(isFlag){
			json.setMessage("删除失败,选择栏目下存在商品关系,请先删除商品信息!");
			json.setStatusCode(300);
            return AJAX;
		}
		//删除model
		this.doService("LvShopSubjectService", "del", dto);
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvShopSubjectAction.del() method end*****");
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
			logger.info("***** LvShopSubjectAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			Integer num=0;
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				lvShopSubject.setId(Integer.parseInt(temp_ids[i].trim()));
				dto.put("model", lvShopSubject);
				Boolean isFlag=(Boolean) this.doService("LvShopSubjectService", "isExistShopProductList", dto);
				if(!isFlag){
					//删除model
					this.doService("LvShopSubjectService", "del", dto);
					num++;
				}
				}
			}
			if(num<=0){
				json.setMessage("删除失败，选择栏目下都存在商品关系,请先删除商品信息!");
				json.setStatusCode(300);
				json.setCallbackType(null);
			}else{
				json.setMessage("删除栏目："+num+"条成功,"+(temp_ids.length-num)+"条失败!");
				json.setStatusCode(200);
				json.setCallbackType(null);
			}
			
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvShopSubjectAction.delList() method end*****");
		}
		return AJAX;
	}

}
