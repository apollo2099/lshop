package com.lshop.manage.lvProductCommend.action;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.core.web.action.BaseAction;
import com.lshop.common.pojo.logic.LvProductCommend;
import com.lshop.common.pojo.logic.LvProductImage;
import com.lshop.common.pojo.logic.LvProductType;
import com.lshop.html.service.InitLinkDataService;
import com.lshop.manage.lvProduct.action.LvProductAction;
/**
 * 
 *  
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvProductCommend.action.LvProductCommendAction.java]  
 * @ClassName:    [LvProductCommendAction]   
 * @Description:  [产品推荐组合信息管理--action业务层]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-7-26 下午05:57:09]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-7-26 下午05:57:09]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v1.0] 
 *
 */
@Controller("LvProductCommendAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvProductCommendAction extends BaseAction{
	private static final Log logger = LogFactory.getLog(LvProductAction.class);
	private LvProductCommend lvProductCommend;
	@Resource
	private InitLinkDataService initLinkDataService;

	/**
	 * 
	 * @Method: del 
	 * @Description:  [删除产品推荐组合信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午03:48:56]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午03:48:56]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return 
	 * @return String
	 */
	public String del(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductCommendAction.del() method begin*****");
		}
		dto.put("lvProductCommend", lvProductCommend);
		this.doService("LvProductCommendService", "deleteCommend", dto);
		
		this.sendHtmlToWeb(lvProductCommend);
		if(logger.isInfoEnabled()){
			  logger.info("***** LvProductCommendAction.del() method end*****");
		}
		json.doNavTabTodo();
		return AJAX;
	}
	
	
	/**
	 * 
	 * @Method: sendHtmlToWeb 
	 * @Description:  [执行单个页面静态化任务]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-9-29 上午11:46:44]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-9-29 上午11:46:44]   
	 * @UpdateRemark: [说明本次修改内容]   
	 * @return void
	 */
	private void sendHtmlToWeb(LvProductCommend productCommend) {
		// 执行单个页面静态化任务
		String htmlPath = "/products/" + productCommend.getProductCode() + ".html";
		initLinkDataService.sendHtmlToWeb(htmlPath, productCommend.getStoreId());
	}
	public LvProductCommend getLvProductCommend() {
		return lvProductCommend;
	}

	public void setLvProductCommend(LvProductCommend lvProductCommend) {
		this.lvProductCommend = lvProductCommend;
	}


}
