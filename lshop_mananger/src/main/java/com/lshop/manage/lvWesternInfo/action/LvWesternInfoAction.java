package com.lshop.manage.lvWesternInfo.action;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.core.datastructure.page.Pagination;
import com.gv.core.web.action.BaseAction;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.pojo.logic.LvWesternInfo;
import com.lshop.manage.common.action.BaseManagerAction;
import com.lshop.manage.lvStore.action.LvStoreMngAction;

/**
 * 
 *  
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvWesternInfo.action.LvWesternInfoAction.java]  
 * @ClassName:    [LvWesternInfoAction]   
 * @Description:  [西联汇款管理-action业务层]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-8-3 上午11:21:34]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-8-3 上午11:21:34]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v1.0] 
 *
 */
@Controller("LvWesternInfoAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvWesternInfoAction extends BaseManagerAction{
	private static final Log logger	= LogFactory.getLog(LvWesternInfoAction.class);
	private LvWesternInfo lvWesternInfo;
	/**
	 * 
	 * @Method: list 
	 * @Description:  [分页查询西联汇款列表信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-3 上午11:22:05]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-3 上午11:22:05]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String list(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvWesternInfoAction.list() method begin*****");
		}
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("page",page);
		dto.put("lvWesternInfo", lvWesternInfo);
		page=(Pagination) this.doService("LvWesternInfoService", "getList", dto);
		
		//查询所有店铺信息
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","getAllNoShop", dto);
		this.getRequest().setAttribute("storeList", storeList);
		if(logger.isInfoEnabled()){
			  logger.info("***** LvWesternInfoAction.list() method end*****");
		}
		return LIST;
	}
	
	 /**
	  * 
	  * @Method: toPay 
	  * @Description:  [西联汇款确认付款操作]  
	  * @Author:       [liaoxiongjian]     
	  * @CreateDate:   [2012-8-3 上午11:22:37]   
	  * @UpdateUser:   [liaoxiongjian]     
	  * @UpdateDate:   [2012-8-3 上午11:22:37]   
	  * @UpdateRemark: [说明本次修改内容]  
	  * @return String
	  * @throws
	  */
	public String toPay(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvWesternInfoAction.toPay() method begin*****");
		}
		dto.put("lvWesternInfo", lvWesternInfo);
		this.doService("LvWesternInfoService", "truePay", dto);
		
		//确认支付时更改限量活动的剩余量或团购活动的已购买量
		dto.put("oid", lvWesternInfo.getOid());
		this.doService("LvShopCartService", "changeCount", dto);
		json.doNavTabTodo();
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvWesternInfoAction.toPay() method begin*****");
		}
		return AJAX;
	}
	
	public LvWesternInfo getLvWesternInfo() {
		return lvWesternInfo;
	}

	public void setLvWesternInfo(LvWesternInfo lvWesternInfo) {
		this.lvWesternInfo = lvWesternInfo;
	}

}
