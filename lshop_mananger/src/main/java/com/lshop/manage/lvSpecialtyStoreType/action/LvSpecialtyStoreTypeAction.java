/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvSpecialtyStoreType.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.web.action.BaseAction;
import com.gv.core.util.ObjectUtils;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;
import com.lshop.common.pojo.logic.LvSpecialtyStore;
import com.lshop.common.pojo.logic.LvSpecialtyStoreType;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.common.action.BaseManagerAction;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Controller("LvSpecialtyStoreTypeAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvSpecialtyStoreTypeAction extends BaseManagerAction{
	private static final Log logger	= LogFactory.getLog(LvSpecialtyStoreTypeAction.class);
	private LvSpecialtyStoreType lvSpecialtyStoreType = new LvSpecialtyStoreType();
	private String parentCode;



	/**
	 * 列表
	 * @return
	 * @throws ActionException
	 */
	public String list()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvSpecialtyStoreTypeAction.list() method begin*****");
		}
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("model", lvSpecialtyStoreType);
		
		page = (Pagination)this.doService("LvSpecialtyStoreTypeService", "findPage", dto);

		//查询所有的专卖店信息
		List<LvSpecialtyStoreType> typeList=(List<LvSpecialtyStoreType>) this.doService("LvSpecialtyStoreTypeService", "findAllPareat", dto);
		this.getRequest().setAttribute("typeList", typeList);
		
    	if (logger.isInfoEnabled()){
			logger.info("***** LvSpecialtyStoreTypeAction.list() method end*****");
		}
		return LIST;
	}
	/**
	 * 进入添加页面
	 * @return
	 * @throws ActionException
	 */
	public String befSave()throws ActionException{
		//查询所有的专卖店信息
		List<LvSpecialtyStoreType> typeList=(List<LvSpecialtyStoreType>) this.doService("LvSpecialtyStoreTypeService", "findAllPareat", dto);
		this.getRequest().setAttribute("typeList", typeList);
		return "befSave";
	}
	
	/**
	 * 添加
	 * @return
	 * @throws ActionException
	 */
	public String save()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvSpecialtyStoreTypeAction.save() method begin*****");
		}
		lvSpecialtyStoreType.setCode(CodeUtils.getCode());//code设置
		lvSpecialtyStoreType.setCreateTime(new Date());   //创建时间
		dto.put("model", lvSpecialtyStoreType);
		
		Boolean isFlag=(Boolean) this.doService("LvSpecialtyStoreTypeService", "isExsitSpecialtyStoreTypeByName", dto);
		if(isFlag){
			json.setStatusCode(300);
			json.setMessage("该专卖店名称已经存在，请重新输入!");
			return AJAX;
		}
		this.doService("LvSpecialtyStoreTypeService", "save", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** LvSpecialtyStoreTypeAction.save() method end*****");
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
			logger.info("***** LvSpecialtyStoreTypeAction.view() method begin*****");
		}
		dto.put("model", lvSpecialtyStoreType);
		lvSpecialtyStoreType = (LvSpecialtyStoreType)this.doService("LvSpecialtyStoreTypeService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvSpecialtyStoreTypeAction.view() method end*****");
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
			logger.info("***** LvSpecialtyStoreTypeAction.befEdit() method begin*****");
		}
		dto.put("model", lvSpecialtyStoreType);
		lvSpecialtyStoreType = (LvSpecialtyStoreType)this.doService("LvSpecialtyStoreTypeService", "get", dto);
		
		//查询所有的专卖店信息
		List<LvSpecialtyStoreType> typeList=(List<LvSpecialtyStoreType>) this.doService("LvSpecialtyStoreTypeService", "findAllPareat", dto);
		this.getRequest().setAttribute("typeList", typeList);
		if (logger.isInfoEnabled()){
			logger.info("***** LvSpecialtyStoreTypeAction.befEdit() method end*****");
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
			logger.info("***** LvSpecialtyStoreTypeAction.edit() method begin*****");
		}
		//获取登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		lvSpecialtyStoreType.setModifyUserId(users.getId());
		lvSpecialtyStoreType.setModifyUserName(users.getUserName());
		lvSpecialtyStoreType.setModifyTime(new Date());
		dto.put("model", lvSpecialtyStoreType);
		if(!lvSpecialtyStoreType.getStoreTypeName().trim().equals(lvSpecialtyStoreType.getOldStoreTypeName().trim())){
			Boolean isFlag=(Boolean) this.doService("LvSpecialtyStoreTypeService", "isExsitSpecialtyStoreTypeByName", dto);
			if(isFlag){
				json.setStatusCode(300);
				json.setMessage("该专卖店名称已经存在，请重新输入!");
				return AJAX;
			}
		}
		this.doService("LvSpecialtyStoreTypeService", "update", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** LvSpecialtyStoreTypeAction.edit() method end*****");
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
			logger.info("***** LvSpecialtyStoreTypeAction.del() method begin*****");
		}
		dto.put("model", lvSpecialtyStoreType);
		//删除model
		Boolean isFlag=(Boolean) this.doService("LvSpecialtyStoreTypeService", "del", dto);
		if(!isFlag){
			json.setMessage("删除专卖店分类失败，分类下有关联内容的不能删除!");
			json.setStatusCode(300);
			json.doNavTabTodo();
			return AJAX;
		}
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvSpecialtyStoreTypeAction.del() method end*****");
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
			logger.info("***** LvSpecialtyStoreTypeAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			int num=0;
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				lvSpecialtyStoreType.setId(Integer.parseInt(temp_ids[i].trim()));
				dto.put("model", lvSpecialtyStoreType);
				//删除model
				Boolean isFlag=(Boolean)this.doService("LvSpecialtyStoreTypeService", "del", dto);
				if(isFlag){
					num++;
				}
				}
			}
			if(num<=0){
				json.setMessage("删除专卖店分类失败，分类下都有关联内容的不能删除!");
				json.setStatusCode(300);
				json.setCallbackType(null);
			}else{
				json.setMessage("删除专卖店分类:"+num+"条成功,"+(temp_ids.length-num)+"条失败!");
				json.setStatusCode(200);
				json.setCallbackType(null);
			}
		}

		if (logger.isInfoEnabled()){
			logger.info("***** LvSpecialtyStoreTypeAction.delList() method end*****");
		}
		return AJAX;
	}
	
	/**
	 * 
	 * @Method: toStoreType 
	 * @Description:  [根据专题动态查询专题下的分类列表信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-12-20 上午10:27:53]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-12-20 上午10:27:53]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return 
	 * @return String
	 */
	public String toStoreType(){
		PrintWriter out = null;
		dto.put("parentCode", parentCode);
		List<LvSpecialtyStoreType> list=(List<LvSpecialtyStoreType>) this.doService("LvSpecialtyStoreTypeService", "findAllByPareat", dto);
		List listTmp=new ArrayList();
		Map<String,Object>  mapTmp=new HashMap<String,Object>();
		if(list!=null&&list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				LvSpecialtyStoreType lvSpecialtyStoreType=list.get(i);
				Map<String,Object>  map=new HashMap<String,Object>();
				map.put("typeName", lvSpecialtyStoreType.getStoreTypeName());
				map.put("code", lvSpecialtyStoreType.getCode());
				listTmp.add(map);
				
			}
			mapTmp.put("listTmp", listTmp);
			try {
				response.setContentType("text/html;charset=UTF-8");   
				response.setCharacterEncoding("UTF-8");
			    out=this.getResponse().getWriter();
			    String jsonTmp = JSONObject.fromObject(mapTmp).toString();
			    logger.info("json_message"+jsonTmp);
				out.print(jsonTmp );
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	public LvSpecialtyStoreType getLvSpecialtyStoreType() {
		return lvSpecialtyStoreType;
	}

	public void setLvSpecialtyStoreType(LvSpecialtyStoreType lvSpecialtyStoreType) {
		this.lvSpecialtyStoreType = lvSpecialtyStoreType;
	}
	
	
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
}
