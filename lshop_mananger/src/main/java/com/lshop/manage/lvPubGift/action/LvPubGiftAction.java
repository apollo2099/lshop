/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvPubGift.action;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lshop.common.pojo.logic.LvPubGift;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.lvActivity.service.LvActivityGiftService;
import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.web.action.BaseAction;
import com.gv.core.util.ObjectUtils;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */
@Controller("LvPubGiftAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvPubGiftAction extends BaseAction{
	private static final Log logger	= LogFactory.getLog(LvPubGiftAction.class);
	private LvPubGift lvPubGift = new LvPubGift();

	@Resource
	private LvActivityGiftService lvActivityGiftService;
	
	/**
	 * 列表
	 * @return
	 * @throws ActionException
	 */
	public String list()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvPubGiftAction.list() method begin*****");
		}
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("lvPubGift", lvPubGift);
		
		page = (Pagination)this.doService("LvPubGiftService", "findPage", dto);

    	if (logger.isInfoEnabled()){
			logger.info("***** LvPubGiftAction.list() method end*****");
		}
		return LIST;
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
			logger.info("***** LvPubGiftAction.save() method begin*****");
		}

		lvPubGift.setCode(CodeUtils.getCode());//code设置
		lvPubGift.setCreateTime(new Date());   //创建时间
		dto.put("lvPubGift", lvPubGift);
		this.doService("LvPubGiftService", "save", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** LvPubGiftAction.save() method end*****");
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
			logger.info("***** LvPubGiftAction.view() method begin*****");
		}
		dto.put("lvPubGift", lvPubGift);
		lvPubGift = (LvPubGift)this.doService("LvPubGiftService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvPubGiftAction.view() method end*****");
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
			logger.info("***** LvPubGiftAction.befEdit() method begin*****");
		}
		dto.put("lvPubGift", lvPubGift);
		lvPubGift = (LvPubGift)this.doService("LvPubGiftService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvPubGiftAction.befEdit() method end*****");
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
			logger.info("***** LvPubGiftAction.edit() method begin*****");
		}
		//获取登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		lvPubGift.setModifyUserId(users.getId());
		lvPubGift.setModifyUserName(users.getUserName());
		lvPubGift.setModifyTime(new Date());
		
		dto.put("lvPubGift", lvPubGift);
		this.doService("LvPubGiftService", "update", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** LvPubGiftAction.edit() method end*****");
		}
		return AJAX;
	}
	
	/**
	 * 
	 * @Method: updateStatus 
	 * @Description:  [赠品的启用/停用功能实现]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-6 下午6:27:40]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-6 下午6:27:40]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws ActionException 
	 * @return String
	 */
	public String updateStatus()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvPubGiftAction.updateStatus() method begin*****");
		}
		//获取登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		lvPubGift.setModifyUserId(users.getId());
		lvPubGift.setModifyUserName(users.getUserName());
		lvPubGift.setModifyTime(new Date());
		
		dto.put("lvPubGift", lvPubGift);
		this.doService("LvPubGiftService", "updateStatus", dto);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvPubGiftAction.updateStatus() method end*****");
		}
		json.doNavTabTodo();
		return AJAX;
	}
	
	
	/**
	 * 
	 * @Method: selectProduct 
	 * @Description:  [根据商品信息，返回分页列表(可以选择多个商品带回)]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-6-18 下午4:17:18]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-6-18 下午4:17:18]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws ActionException 
	 * @return String
	 */
	public String selectMultipleGift()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvPubGiftAction.selectMultipleGift() method begin*****");
		}
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("lvPubGift", lvPubGift);
		
		page = (Pagination)this.doService("LvPubGiftService", "findPage", dto);

    	if (logger.isInfoEnabled()){
			logger.info("***** LvPubGiftAction.selectMultipleGift() method end*****");
		}
		return "selectMultipleGift";
	}
	
	/**
	 * 删除
	 * @return
	 * @throws ActionException
	 */
	public String del()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvPubGiftAction.del() method begin*****");
		}
		Boolean isFlag=lvActivityGiftService.isExsitsAcivityByGiftCode(lvPubGift.getCode());
		if(isFlag){
			json.setMessage("该赠品存在进行中的活动，请先停用活动！");
			json.setStatusCode(300);
			return AJAX;
		}
		
		//获取登录用户信息
		BaseUsers users = (BaseUsers) getSession().getAttribute("USER");
		lvPubGift.setModifyUserId(users.getId());
		lvPubGift.setModifyUserName(users.getUserName());
		lvPubGift.setModifyTime(new Date());
		lvPubGift.setStatus(Short.parseShort("-1"));

		dto.put("lvPubGift", lvPubGift);
		this.doService("LvPubGiftService", "updateStatus", dto);
		
		
		json.doNavTabTodo();
		if (logger.isInfoEnabled()){
			logger.info("***** LvPubGiftAction.del() method end*****");
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
			logger.info("***** LvPubGiftAction.delList() method begin*****");
		}
		
		//获取登录用户信息
		BaseUsers users = (BaseUsers) getSession().getAttribute("USER");
		lvPubGift.setModifyUserId(users.getId());
		lvPubGift.setModifyUserName(users.getUserName());
		lvPubGift.setModifyTime(new Date());
		lvPubGift.setStatus(Short.parseShort("-1"));
		
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				lvPubGift.setId(Integer.parseInt(temp_ids[i].trim()));
				dto.put("lvPubGift", lvPubGift);
				this.doService("LvPubGiftService", "updateStatus", dto);
				}
			}
		}
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvPubGiftAction.delList() method end*****");
		}
		return AJAX;
	}
	

	public LvPubGift getLvPubGift() {
		return lvPubGift;
	}

	public void setLvPubGift(LvPubGift lvPubGift) {
		this.lvPubGift = lvPubGift;
	}

}
