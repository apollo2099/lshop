/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvStoreArea.action;
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
import com.lshop.common.pojo.logic.LvArea;
import com.lshop.common.pojo.logic.LvHelpType;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.pojo.logic.LvStoreArea;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.common.action.BaseManagerAction;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */
@Controller("LvStoreAreaAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvStoreAreaAction extends BaseManagerAction{
	private static final Log logger	= LogFactory.getLog(LvStoreAreaAction.class);
	private LvStoreArea lvStoreArea = new LvStoreArea();
	private LvArea lvArea=new LvArea();
	private String language; 
	private String parentCode;





	/**
	 * 国家列表
	 * @return
	 * @throws ActionException
	 */
	public String list()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvStoreAreaAction.list() method begin*****");
		}
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("model", lvStoreArea);
		
		page = (Pagination)this.doService("LvStoreAreaService", "findPage", dto);

		//查询所有的洲信息
		List<LvStoreArea> continentList=(List<LvStoreArea>) this.doService("LvStoreAreaService", "findAll", dto);
		this.getRequest().setAttribute("continentList", continentList);
		//查询所有店铺信息
		List<LvStore> shopList= (List<LvStore>) this.doService("LvStoreService","findAllStore", dto);
		this.getRequest().setAttribute("shopList", shopList);
    	if (logger.isInfoEnabled()){
			logger.info("***** LvStoreAreaAction.list() method end*****");
		}
		return LIST;
	}
	
	
	/**
	 * 根据国家查询城市列表
	 * @return
	 * @throws ActionException
	 */
	public String cityList()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvStoreAreaAction.list() method begin*****");
		}
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("model", lvStoreArea);
		
		page = (Pagination)this.doService("LvStoreAreaService", "findAreaCity", dto);

    	if (logger.isInfoEnabled()){
			logger.info("***** LvStoreAreaAction.list() method end*****");
		}
		return "cityList";
	}
	
	/**
	 * 进入国家添加页面
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
	 * 进入城市添加页面
	 * @return
	 * @throws ActionException
	 */
   public String befSaveCity()throws ActionException{
		return "befSaveCity";
	}

	/**
	 * 添加
	 * @return
	 * @throws ActionException
	 */
	public String save()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvStoreAreaAction.save() method begin*****");
		}
		lvStoreArea.setCode(CodeUtils.getCode());//code设置
		lvStoreArea.setCreateTime(new Date());   //创建时间
		dto.put("model", lvStoreArea);
		
		
		Boolean isFlag=(Boolean) this.doService("LvStoreAreaService", "isExistCountry", dto);
		if(isFlag){
			json.setMessage("该国家已经存在，请重新选择!");
			json.setStatusCode(300);
			return AJAX;
		}
		
		this.doService("LvStoreAreaService", "save", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** LvStoreAreaAction.save() method end*****");
		}
		return AJAX;
	}
	

	public String saveCity()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvStoreAreaAction.save() method begin*****");
		}
		lvStoreArea.setCode(CodeUtils.getCode());//code设置
		lvStoreArea.setCreateTime(new Date());   //创建时间
		dto.put("model", lvStoreArea);
		
		
		Boolean isFlag=(Boolean) this.doService("LvStoreAreaService", "isExistCity", dto);
		if(isFlag){
			json.setMessage("该城市名称已经存在，请重新填写!");
			json.setStatusCode(300);
			return AJAX;
		}
		isFlag=(Boolean) this.doService("LvStoreAreaService", "isExistCityEn", dto);
		if(isFlag){
			json.setMessage("该城市英文名称已经存在，请重新填写!");
			json.setStatusCode(300);
			return AJAX;
		}
		
		this.doService("LvStoreAreaService", "save", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** LvStoreAreaAction.save() method end*****");
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
			logger.info("***** LvStoreAreaAction.view() method begin*****");
		}
		dto.put("model", lvStoreArea);
		lvStoreArea = (LvStoreArea)this.doService("LvStoreAreaService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvStoreAreaAction.view() method end*****");
		}
		return "view";
	}
	
	/**
	 * 跳入国家编辑页面
	 * @return
	 * @throws ActionException
	 */
	public String befEdit()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvStoreAreaAction.befEdit() method begin*****");
		}
		dto.put("model", lvStoreArea);
		lvStoreArea = (LvStoreArea)this.doService("LvStoreAreaService", "get", dto);
		
		//查询所有的国家信息
		List<LvArea> areaList=(List<LvArea>) this.doService("LvAreaService", "getAll", dto);
		this.getRequest().setAttribute("areaList", areaList);
		
		//查询所有的洲信息
		List<LvStoreArea> continentList=(List<LvStoreArea>) this.doService("LvStoreAreaService", "findAll", dto);
		this.getRequest().setAttribute("continentList", continentList);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvStoreAreaAction.befEdit() method end*****");
		}
		return "edit";
	}
	

	/**
	 * 跳入城市编辑页面
	 * @return
	 * @throws ActionException
	 */
	public String befEditCity()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvStoreAreaAction.befEdit() method begin*****");
		}
		dto.put("model", lvStoreArea);
		lvStoreArea = (LvStoreArea)this.doService("LvStoreAreaService", "get", dto);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvStoreAreaAction.befEdit() method end*****");
		}
		return "editcity";
	}
	
	
	
	/**
	 * 编辑
	 * @return
	 * @throws ActionException
	 */
	public String edit()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvStoreAreaAction.edit() method begin*****");
		}
		//获取登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		lvStoreArea.setModifyUserId(users.getId());
		lvStoreArea.setModifyUserName(users.getUserName());
		lvStoreArea.setModifyTime(new Date());
		dto.put("model", lvStoreArea);
		if(!lvStoreArea.getAreaName().equals(lvStoreArea.getOldAreaName())){
			Boolean isFlag=(Boolean) this.doService("LvStoreAreaService", "isExistCity", dto);
			if(isFlag){
				json.setMessage("该城市已经存在，请重新填写!");
				json.setStatusCode(300);
				return AJAX;
			}
		}
		
		if(!lvStoreArea.getAreaNameEn().equals(lvStoreArea.getOldAreaNameEn())){
			Boolean isFlag=(Boolean) this.doService("LvStoreAreaService", "isExistCityEn", dto);
			if(isFlag){
				json.setMessage("该城市英文名称已经存在，请重新填写!");
				json.setStatusCode(300);
				return AJAX;
			}
		}
		
		
		
		this.doService("LvStoreAreaService", "update", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** LvStoreAreaAction.edit() method end*****");
		}
		return AJAX;
	}
	
	/**
	 * 删除国家
	 * @return
	 * @throws ActionException
	 */
	public String del()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvStoreAreaAction.del() method begin*****");
		}
		dto.put("model", lvStoreArea);
		Boolean  isFlag=(Boolean) this.doService("LvStoreAreaService", "isExistCityList", dto);
		if(isFlag){
			json.setMessage("该国家存在城市信息，请先删除城市信息 !");
			json.setStatusCode(300);
			return AJAX;
		}
		//删除model
		this.doService("LvStoreAreaService", "del", dto);
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvStoreAreaAction.del() method end*****");
		}
		return AJAX;
	}
	
	/**
	 * 批量删除国家
	 * @return
	 * @throws ActionException
	 */
	public String delList()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvStoreAreaAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			int num=0;
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				lvStoreArea.setId(Integer.parseInt(temp_ids[i].trim()));
				dto.put("model", lvStoreArea);
				Boolean  isFlag=(Boolean) this.doService("LvStoreAreaService", "isExistCityList", dto);
				if(!isFlag){
					//删除model
					this.doService("LvStoreAreaService", "del", dto);
					//删除累计
					num++;
				}
				}
			}
			if(num<=0){
				json.setMessage("删除国家失败，国家下都有关联内容的不能删除!");
				json.setStatusCode(300);
				json.setCallbackType(null);
			}else{
				json.setMessage("删除国家:"+num+"条成功,"+(temp_ids.length-num)+"条失败!");
				json.setStatusCode(200);
				json.setCallbackType(null);
			}
			
		}

		if (logger.isInfoEnabled()){
			logger.info("***** LvStoreAreaAction.delList() method end*****");
		}
		return AJAX;
	}
	
	
	/**
	 * 删除城市
	 * @return
	 * @throws ActionException
	 */
	public String delCity()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvStoreAreaAction.del() method begin*****");
		}
		dto.put("model", lvStoreArea);
		Boolean  isFlag=(Boolean) this.doService("LvStoreAreaService", "isExistStoreList", dto);
		if(isFlag){
			json.setMessage("该城市下存在店铺信息，请先删除店铺信息 !");
			json.setStatusCode(300);
			return AJAX;
		}
		//删除model
		this.doService("LvStoreAreaService", "del", dto);
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvStoreAreaAction.del() method end*****");
		}
		return AJAX;
	}
	
	/**
	 * 批量删除城市
	 * @return
	 * @throws ActionException
	 */
	public String delCityList()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvStoreAreaAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			int num=0;
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				lvStoreArea.setId(Integer.parseInt(temp_ids[i].trim()));
				dto.put("model", lvStoreArea);
				Boolean  isFlag=(Boolean) this.doService("LvStoreAreaService", "isExistStoreList", dto);
				if(!isFlag){
					//删除model
					this.doService("LvStoreAreaService", "del", dto);
					//删除累计
					num++;
				}
				}
			}
			if(num<=0){
				json.setMessage("删除城市失败，城市下都有关联内容的不能删除!");
				json.setStatusCode(300);
				json.setCallbackType(null);
			}else{
				json.setMessage("删除城市:"+num+"条成功,"+(temp_ids.length-num)+"条失败!");
				json.setStatusCode(200);
				json.setCallbackType(null);
			}
			
		}

		if (logger.isInfoEnabled()){
			logger.info("***** LvStoreAreaAction.delList() method end*****");
		}
		return AJAX;
	}
	
	/**
	 * 
	 * @Method: selectArea 
	 * @Description:  [查找带回选择区域城市信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2013-2-6 下午05:25:02]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2013-2-6 下午05:25:02]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return 
	 * @return String
	 */
	public String selectArea(){
		dto.put("parentCode", parentCode);
		LvStore lvStore=(LvStore) this.doService("LvStoreService", "findStoreByCode", dto);
		if(lvStore!=null){
			lvStoreArea.setStoreId(lvStore.getStoreFlag());
		}
		
		
		dto.put("lvStoreArea", lvStoreArea);
		List<LvStoreArea> areaList=(List<LvStoreArea>) this.doService("LvStoreAreaService", "getAllCity", dto);
		this.getRequest().setAttribute("areaList", areaList);
		return "selectArea";
	}
	
	/**
	 * 
	 * @Method: toArea 
	 * @Description:  [根据区域编号查询区域信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2013-3-28 下午02:31:00]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2013-3-28 下午02:31:00]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return 
	 * @return String
	 */
	public String toArea(){
		PrintWriter out = null;
		if(ObjectUtils.isNotEmpty(language)){
		    //查询所有的国家信息
			Map<String,Object>  mapTmp=new HashMap<String,Object>();
			List areaListTmp=new ArrayList();
			List<LvArea> areaList=(List<LvArea>) this.doService("LvAreaService", "getAll", dto);
			if(areaList!=null&&areaList.size()>0){
				for (LvArea lvArea : areaList) {
					Map<String,Object>  map=new HashMap<String,Object>();
					if(language.equals("zh")){
						map.put("areaName", lvArea.getNametw());
					}else if(language.equals("en")){
						map.put("areaName", lvArea.getNameen());
					}
					map.put("areaId", lvArea.getId());
					areaListTmp.add(map);
				}
				mapTmp.put("areaListTmp", areaListTmp);
			}
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
	
	/**
     *根据店铺标识查询洲信息
     */
	public String toStoreArea(){
		PrintWriter out = null;
		if(lvStoreArea!=null){
			dto.put("lvStoreArea", lvStoreArea);
			//查询所有的洲信息
			List<LvStoreArea> list=(List<LvStoreArea>) this.doService("LvStoreAreaService", "findAll", dto);
			List listTmp=new ArrayList();
			Map<String,Object>  mapTmp=new HashMap<String,Object>();
			if(list!=null&&list.size()>0){
				for (int i = 0; i < list.size(); i++) {
					LvStoreArea lvStoreArea=list.get(i);
					Map<String,Object>  map=new HashMap<String,Object>();
					map.put("storeAreaName", lvStoreArea.getAreaName());
					map.put("storeAreaCode", lvStoreArea.getCode());
					listTmp.add(map);
				}
				mapTmp.put("listTmp", listTmp);
			}
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
	
	public LvStoreArea getLvStoreArea() {
		return lvStoreArea;
	}

	public void setLvStoreArea(LvStoreArea lvStoreArea) {
		this.lvStoreArea = lvStoreArea;
	}

	public LvArea getLvArea() {
		return lvArea;
	}

	public void setLvArea(LvArea lvArea) {
		this.lvArea = lvArea;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	
}
