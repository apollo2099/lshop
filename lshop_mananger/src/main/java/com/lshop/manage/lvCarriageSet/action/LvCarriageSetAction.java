package com.lshop.manage.lvCarriageSet.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import redis.clients.jedis.Jedis;

import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;
import com.gv.core.util.ObjectUtils;
import com.gv.core.web.action.BaseAction;
import com.lshop.common.cache.LvAreaCache;
import com.lshop.common.pojo.logic.LvArea;
import com.lshop.common.pojo.logic.LvCarriageSet;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.util.CodeUtils;
import com.lshop.common.util.Constants;
import com.lshop.common.util.RedisClient;
import com.lshop.manage.common.action.BaseManagerAction;
import com.lshop.manage.lvApp.action.LvAppAction;
import com.lshop.manage.lvArea.common.AreaConstant;
/**
 * 
 *  
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvCarriageSet.action.LvCarriageSetAction.java]  
 * @ClassName:    [LvCarriageSetAction]   
 * @Description:  [一句话描述该类的功能]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-8-3 下午01:41:51]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-8-3 下午01:41:51]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v1.0] 
 *
 */
@Controller("LvCarriageSetAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvCarriageSetAction  extends BaseManagerAction{
	private static final Log logger = LogFactory.getLog(LvCarriageSetAction.class);
	private LvCarriageSet lvCarriageSet;
	private Integer areaId;
	private Integer provinceId;
	private Integer cityId;
	
	//设置redis缓存客户端调用对象
	//private Jedis jedis = RedisClient.getRedis();
    /**
     * 
     * @Method: list 
     * @Description:  [一句话描述该类的功能]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-8-3 下午01:42:04]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-8-3 下午01:42:04]   
     * @UpdateRemark: [说明本次修改内容]  
     * @return String
     * @throws
     */
	public String list(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvCarriageSetAction.list() method begin*****");
		}
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("page",page);
		dto.put("lvCarriageSet", lvCarriageSet);
		//产品信息分页集合对象
		page=(Pagination) this.doService("LvCarriageSetService", "getList", dto);

    	//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","getAllNoShop", dto);
		this.getRequest().setAttribute("storeList", storeList);
		
		if(logger.isInfoEnabled()){
			  logger.info("***** lvCarriageSetAction.list() method end*****");
		}
		return LIST;
	}
	
    /**
     * 
     * @Method: befEdit 
     * @Description:  [跳转到编辑运费设置管理信息页面]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-8-3 下午01:02:30]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-8-3 下午01:02:30]   
     * @UpdateRemark: [说明本次修改内容]  
     * @return String
     * @throws
     */
	public String befEdit(){
		if(logger.isInfoEnabled()){
			  logger.info("***** lvCarriageSetAction.befEdit() method begin*****");
		}
		
		try {
			//查询地区配置信息
			dto.put("lvCarriageSet", lvCarriageSet);
			lvCarriageSet=(LvCarriageSet) this.doService("LvCarriageSetService", "get", dto);
			
			//查询区域信息
			if(lvCarriageSet.getDeliveryId()==100000){
				lvCarriageSet.setDeliveryName("运费默认值地区");
			}else{
				List<LvArea> areaList= LvAreaCache.list;
				for (int i = 0; i < areaList.size(); i++) {
					LvArea area=areaList.get(i);
					if(lvCarriageSet.getDeliveryId().equals(area.getId())){
						lvCarriageSet.setDeliveryName(area.getNamecn());
						break;
					}
				}
			}
			

			this.getRequest().setAttribute("lvCarriageSet", lvCarriageSet);
			
			//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
			List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","getAllNoShop", dto);
			this.getRequest().setAttribute("storeList", storeList);
		} catch (ActionException e) {
			e.printStackTrace();
		}
		
		if(logger.isInfoEnabled()){
			  logger.info("***** lvCarriageSetAction.befEdit() method end*****");
		}
		return "edit";
	}
	
    /**
     * 
     * @Method: edit 
     * @Description:  [修改运费设置管理信息]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-8-3 下午01:03:07]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-8-3 下午01:03:07]   
     * @UpdateRemark: [说明本次修改内容]  
     * @return String
     * @throws
     */
	public String edit(){
		if(logger.isInfoEnabled()){
			  logger.info("***** lvCarriageSetAction.edit() method begin*****");
		}
		//获取登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		lvCarriageSet.setModifyUserId(users.getId());
		lvCarriageSet.setModifyUserName(users.getUserName());
		lvCarriageSet.setModifyTime(new Date());
		//根据店铺标示获取币种
		String currency=Constants.STORE_TO_CURRENCY.get(lvCarriageSet.getStoreId());
		lvCarriageSet.setCurrency(currency);
		dto.put("lvCarriageSet", lvCarriageSet);
		dto.put("areaId", areaId);
		Boolean flag=(Boolean) this.doService("LvCarriageSetService", "update", dto);
		if(!flag){
			json.setMessage("该地区已经配置了费用，请重新选择配送地区！");
			json.setStatusCode(300);
			return AJAX;
		}
		
		if(logger.isInfoEnabled()){
			  logger.info("***** lvCarriageSetAction.edit() method end*****");
		}
		return AJAX;
	}
    /**
     * 
     * @Method: befAdd 
     * @Description:  [跳转到运费设置管理信息新增页面]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-8-3 下午01:03:38]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-8-3 下午01:03:38]   
     * @UpdateRemark: [说明本次修改内容]  
     * @return String
     * @throws
     */
	public String befAdd(){
		if(logger.isInfoEnabled()){
			  logger.info("***** lvCarriageSetAction.befAdd() method begin*****");
		}
		//查询所有的区域信息
		List<LvArea> areaList=(List<LvArea>) this.doService("LvAreaService", "getAll", dto);
    	this.getRequest().setAttribute("areaList", areaList);
		
    	//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","getAllNoShop", dto);
		this.getRequest().setAttribute("storeList", storeList);
		
		if(logger.isInfoEnabled()){
			  logger.info("***** lvCarriageSetAction.befAdd() method add*****");
		}
		return "befAdd";
	}
    /**
     * 
     * @Method: add 
     * @Description:  [新增运费设置管理信息]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-8-3 下午01:04:06]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-8-3 下午01:04:06]   
     * @UpdateRemark: [说明本次修改内容]  
     * @return String
     * @throws
     */
	public String add(){
		if(logger.isInfoEnabled()){
			  logger.info("***** lvCarriageSetAction.add() method begin*****");
		}
		lvCarriageSet.setCode(CodeUtils.getCode());//code设置
		lvCarriageSet.setCreateTime(new Date());   //创建时间
		//根据店铺标示获取币种
		String currency=Constants.STORE_TO_CURRENCY.get(lvCarriageSet.getStoreId());
		lvCarriageSet.setCurrency(currency);
		if(ObjectUtils.isNotEmpty(provinceId)){
			if(ObjectUtils.isNotEmpty(cityId)){
				lvCarriageSet.setDeliveryId(cityId);
			}else{
				lvCarriageSet.setDeliveryId(provinceId);
			}
		}
		dto.put("lvCarriageSet", lvCarriageSet);
		Boolean isFlag=(Boolean) this.doService("LvCarriageSetService", "save", dto);
		if(!isFlag){
			json.setMessage("该地区已经配置了费用，请重新选择配送地区！");
			json.setStatusCode(300);
			return AJAX;
		}
		
		if(logger.isInfoEnabled()){
			  logger.info("***** lvCarriageSetAction.add() method end*****");
		}
		return AJAX;
	}
    /**
     * 
     * @Method: view 
     * @Description:  [查看运费设置管理信息详情]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-8-3 下午01:04:36]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-8-3 下午01:04:36]   
     * @UpdateRemark: [说明本次修改内容]  
     * @return String
     * @throws
     */
	public String view(){
		if(logger.isInfoEnabled()){
			  logger.info("***** lvCarriageSetAction.view() method begin*****");
		}
		//查询地区配置信息
		dto.put("lvCarriageSet", lvCarriageSet);
		lvCarriageSet=(LvCarriageSet) this.doService("LvCarriageSetService", "get", dto);
		this.getRequest().setAttribute("lvCarriageSet", lvCarriageSet);
		
		//查询所有的区域信息
		List<LvArea> areaList=(List<LvArea>) this.doService("LvAreaService", "getAll", dto);
    	this.getRequest().setAttribute("areaList", areaList);
		
		if(logger.isInfoEnabled()){
			  logger.info("***** lvCarriageSetAction.view() method end*****");
		}
		return "view";
	}
   /**
    * 
    * @Method: del 
    * @Description:  [删除运费设置管理信息]  
    * @Author:       [liaoxiongjian]     
    * @CreateDate:   [2012-8-3 下午01:01:07]   
    * @UpdateUser:   [liaoxiongjian]     
    * @UpdateDate:   [2012-8-3 下午01:01:07]   
    * @UpdateRemark: [说明本次修改内容]  
    * @return String
    * @throws
    */
	public String del(){
		if(logger.isInfoEnabled()){
			  logger.info("***** lvCarriageSetAction.del() method begin*****");
		}
		dto.put("lvCarriageSet", lvCarriageSet);
		this.doService("LvCarriageSetService", "delete", dto);
		json.doNavTabTodo();
		
		if(logger.isInfoEnabled()){
			  logger.info("***** lvCarriageSetAction.del() method end*****");
		}
		return AJAX;
	}
    /**
     * 
     * @Method: delList 
     * @Description:  [批量删除运费设置管理信息]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-8-3 下午01:01:36]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-8-3 下午01:01:36]   
     * @UpdateRemark: [说明本次修改内容]  
     * @return String
     * @throws
     */
	public String delList(){
		if(logger.isInfoEnabled()){
			  logger.info("***** lvCarriageSetAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				int id=Integer.parseInt(temp_ids[i].trim());
				lvCarriageSet=new LvCarriageSet();
				lvCarriageSet.setId(id);
				dto.put("lvCarriageSet", lvCarriageSet);
				//删除model
				this.doService("LvCarriageSetService", "delete", dto);
				}
			}
		}
		json.doNavTabTodo();
		
		if(logger.isInfoEnabled()){
			  logger.info("***** lvCarriageSetAction.delList() method end*****");
		}
		return AJAX;
	}
	
	public LvCarriageSet getLvCarriageSet() {
		return lvCarriageSet;
	}

	public void setLvCarriageSet(LvCarriageSet lvCarriageSet) {
		this.lvCarriageSet = lvCarriageSet;
	}
	
	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	
	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}


}
