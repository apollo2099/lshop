/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvShopProductType.action;

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
import com.lshop.common.pojo.logic.LvShopProductType;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.common.action.BaseManagerAction;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Controller("LvShopProductTypeAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvShopProductTypeAction extends BaseManagerAction{
	private static final Log logger	= LogFactory.getLog(LvShopProductTypeAction.class);
	private LvShopProductType lvShopProductType = new LvShopProductType();

	/**
	 * 列表
	 * @return
	 * @throws ActionException
	 */
	public String list()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvShopProductTypeAction.list() method begin*****");
		}
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("model", lvShopProductType);
		
		page = (Pagination)this.doService("LvShopProductTypeService", "findPage", dto);
		
		//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
		List<LvStore> shopList= (List<LvStore>) this.doService("LvStoreService","findAllStore", dto);
		this.getRequest().setAttribute("shopList", shopList);

    	if (logger.isInfoEnabled()){
			logger.info("***** LvShopProductTypeAction.list() method end*****");
		}
		return LIST;
	}

	
	/**
	 * 进入添加页面
	 * @return
	 * @throws ActionException
	 */
	public String befSave()throws ActionException{
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
			logger.info("***** LvShopProductTypeAction.save() method begin*****");
		}
		lvShopProductType.setCode(CodeUtils.getCode());//code设置
		lvShopProductType.setCreateTime(new Date());   //创建时间
		dto.put("model", lvShopProductType);
		
		
		Boolean isFlag=(Boolean) this.doService("LvShopProductTypeService", "IsExistType", dto);
		if(!isFlag){
			json.setStatusCode(300);
			json.setMessage("该商品分类已经存在，请重新输入!");
			return AJAX;
		}
		
		this.doService("LvShopProductTypeService", "save", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** LvShopProductTypeAction.save() method end*****");
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
			logger.info("***** LvShopProductTypeAction.view() method begin*****");
		}
		dto.put("model", lvShopProductType);
		lvShopProductType = (LvShopProductType)this.doService("LvShopProductTypeService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvShopProductTypeAction.view() method end*****");
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
			logger.info("***** LvShopProductTypeAction.befEdit() method begin*****");
		}
		dto.put("model", lvShopProductType);
		lvShopProductType = (LvShopProductType)this.doService("LvShopProductTypeService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvShopProductTypeAction.befEdit() method end*****");
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
			logger.info("***** LvShopProductTypeAction.edit() method begin*****");
		}
		//获取登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		lvShopProductType.setModifyUserId(users.getId());
		lvShopProductType.setModifyUserName(users.getUserName());
		lvShopProductType.setModifyTime(new Date());
		dto.put("model", lvShopProductType);
		if(!lvShopProductType.getOldTypeName().equals(lvShopProductType.getTypeName())){
			Boolean isFlag=(Boolean) this.doService("LvShopProductTypeService", "IsExistType", dto);
			if(!isFlag){
				json.setStatusCode(300);
				json.setMessage("该商品分类已经存在，请重新输入!");
				return AJAX;
			}
		}
		this.doService("LvShopProductTypeService", "update", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** LvShopProductTypeAction.edit() method end*****");
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
			logger.info("***** LvShopProductTypeAction.del() method begin*****");
		}
		dto.put("model", lvShopProductType);
		//判断该分类下是否存在商品
		Boolean isFlag=(Boolean) this.doService("LvShopProductTypeService", "IsExistShopProductType", dto);
		if(!isFlag){
			json.setStatusCode(300);
			json.setMessage("该商品分类已经存在商品信息，不能删除!");
			return AJAX;
		}
		//删除model
		this.doService("LvShopProductTypeService", "del", dto);
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvShopProductTypeAction.del() method end*****");
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
			logger.info("***** LvShopProductTypeAction.delList() method begin*****");
		}
		
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			Integer num=0;
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				lvShopProductType.setId(Integer.parseInt(temp_ids[i].trim()));
				dto.put("model", lvShopProductType);
				lvShopProductType = (LvShopProductType)this.doService("LvShopProductTypeService", "get", dto);
				dto.put("model", lvShopProductType);
				Boolean isFlag=(Boolean) this.doService("LvShopProductTypeService", "IsExistShopProductType", dto);
				if(isFlag){
					//删除model
					this.doService("LvShopProductTypeService", "del", dto);
					num++;
				}
				}
			}
			if(num<=0){
				json.setMessage("批量删除失败，选择分类都已经存在商品信息，不能删除!");
				json.setStatusCode(300);
				json.setCallbackType(null);
			}else{
				json.setMessage("批量删除:"+num+"条成功,"+(temp_ids.length-num)+"条失败");
				json.setStatusCode(200);
				json.setCallbackType(null);
			}
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvShopProductTypeAction.delList() method end*****");
		}
		return AJAX;
	}
	
	public LvShopProductType getLvShopProductType() {
		return lvShopProductType;
	}

	public void setLvShopProductType(LvShopProductType lvShopProductType) {
		this.lvShopProductType = lvShopProductType;
	}

}
