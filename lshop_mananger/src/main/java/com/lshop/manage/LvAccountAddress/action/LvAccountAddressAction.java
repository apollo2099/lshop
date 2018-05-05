package com.lshop.manage.LvAccountAddress.action;

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
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.util.ObjectUtils;
import com.gv.core.web.action.BaseAction;
import com.lshop.common.pojo.logic.LvArea;
import com.lshop.common.pojo.logic.LvBlogContent;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.pojo.user.LvAccountAddress;
import com.lshop.manage.common.action.BaseManagerAction;
import com.lshop.manage.lvAccount.action.LvAccountAction;

/**
 * 
 *  
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.LvAccountAddress.action.LvAccountAddressAction.java]  
 * @ClassName:    [LvAccountAddressAction]   
 * @Description:  [用户收货地址管理-action业务层]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-7-2 下午06:14:57]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-7-2 下午06:14:57]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0] 
 *
 */
@Controller("LvAccountAddressAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvAccountAddressAction extends BaseManagerAction{
	private static final Log logger = LogFactory.getLog(LvAccountAddressAction.class);
	private LvAccountAddress lvAccountAddress;
	
	

	/**
	 * 
	 * @Method: list 
	 * @Description:  [分页查询用户收货地址信息列表]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-2 下午06:34:40]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-2 下午06:34:40]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String list(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvAccountAddressAction.list() method begin*****");
		}
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("page",page);
		dto.put("lvAccountAddress", lvAccountAddress);
		page=(Pagination) this.doService("LvAccountAddressService", "getList", dto);
		
	    //查询所有的国家信息
		List<LvArea> areaList=(List<LvArea>) this.doService("LvAreaService", "getAll", dto);
		this.getRequest().setAttribute("areaList", areaList);
		return LIST;
	}
	
	
	/**
	 * 
	 * @Method: befEdit 
	 * @Description:  [跳转到用户收货地址修改页面]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-2 下午06:36:02]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-2 下午06:36:02]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String befEdit(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvAccountAddressAction.befEdit() method begin*****");
		}
		dto.put("lvAccountAddress", lvAccountAddress);
		lvAccountAddress=(LvAccountAddress) this.doService("LvAccountAddressService", "get", dto);
		this.getRequest().setAttribute("lvAccountAddress", lvAccountAddress);
		
		//查询所以国家区域信息
		List<LvArea> areaList=(List<LvArea>) this.doService("LvAreaService", "getAll", dto);
    	this.getRequest().setAttribute("areaList", areaList);
		if(logger.isInfoEnabled()){
			  logger.info("***** LvAccountAddressAction.befEdit() method end*****");
		}
		
		return "edit";
	}
	
	/**
	 * 
	 * @Method: edit 
	 * @Description:  [修改用户收货地址信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-2 下午06:36:35]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-2 下午06:36:35]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String edit(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvAccountAddressAction.edit() method begin*****");
		}
		//获取登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		lvAccountAddress.setModifyUserId(users.getId());
		lvAccountAddress.setModifyUserName(users.getUserName());
		lvAccountAddress.setModifyTime(new Date());
		
		dto.put("lvAccountAddress", lvAccountAddress);
		lvAccountAddress=(LvAccountAddress) this.doService("LvAccountAddressService", "update", dto);
		if(logger.isInfoEnabled()){
			  logger.info("***** LvAccountAddressAction.edit() method end*****");
		}
		return AJAX;
	}
	
	/**
	 * 
	 * @Method: view 
	 * @Description:  [查看用户收货地址详情]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-2 下午06:37:01]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-2 下午06:37:01]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String view(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvAccountAddressAction.view() method begin*****");
		}
		dto.put("lvAccountAddress", lvAccountAddress);
		lvAccountAddress=(LvAccountAddress) this.doService("LvAccountAddressService", "get", dto);
		this.getRequest().setAttribute("lvAccountAddress", lvAccountAddress);
		if(logger.isInfoEnabled()){
			  logger.info("***** LvAccountAddressAction.view() method end*****");
		}
		return VIEW;
	}
	
	/**
	 * 
	 * @Method: del 
	 * @Description:  [删除用户收货地址信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-2 下午06:37:34]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-2 下午06:37:34]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String del(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvAccountAddressAction.del() method begin*****");
		}
		dto.put("lvAccountAddress", lvAccountAddress);
		this.doService("LvAccountAddressService", "delete", dto);
		json.doNavTabTodo();
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvAccountAddressAction.del() method end*****");
		}
		return AJAX;
	}
	/**
	 * 
	 * @Method: delList 
	 * @Description:  [批量删除用户收货地址信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-3 上午10:21:33]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-3 上午10:21:33]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String delList(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvAccountAddressAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				int id=Integer.parseInt(temp_ids[i].trim());
				lvAccountAddress=new LvAccountAddress();
				lvAccountAddress.setId(id);
				dto.put("lvAccountAddress", lvAccountAddress);
				//删除model
				this.doService("LvAccountAddressService", "delete", dto);
				}
			}
		}
	
		json.doNavTabTodo();	
		if(logger.isInfoEnabled()){
			  logger.info("***** LvAccountAddressAction.delList() method end*****");
		}
		return AJAX;
	}


	public LvAccountAddress getLvAccountAddress() {
		return lvAccountAddress;
	}

	public void setLvAccountAddress(LvAccountAddress lvAccountAddress) {
		this.lvAccountAddress = lvAccountAddress;
	}
}
