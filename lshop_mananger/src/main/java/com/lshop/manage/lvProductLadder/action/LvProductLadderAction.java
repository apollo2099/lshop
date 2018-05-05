package com.lshop.manage.lvProductLadder.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.web.action.BaseAction;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.pojo.logic.LvProductCommend;
import com.lshop.common.pojo.logic.LvProductLadder;
import com.lshop.common.pojo.logic.LvProductProperty;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.util.CodeUtils;
import com.lshop.html.service.InitLinkDataService;
import com.lshop.manage.common.action.BaseManagerAction;
import com.lshop.manage.message.service.ProductMsgService;

/**
 * 
 * 
 * Simple to Introduction
 * 
 * @ProjectName: [lshop_new]
 * @Package: 
 *           [com.lshop.manage.lvProductLadder.action.LvProductLadderAction.java]
 * @ClassName: [LvProductLadderAction]
 * @Description: [产品阶梯价管理-action业务层]
 * @Author: [liaoxiongjian]
 * @CreateDate: [2012-7-5 上午11:05:45]
 * @UpdateUser: [liaoxiongjian]
 * @UpdateDate: [2012-7-5 上午11:05:45]
 * @UpdateRemark: [说明本次修改内容]
 * @Version: [v1.0]
 * 
 */
@Controller("LvProductLadderAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvProductLadderAction extends BaseManagerAction {
	private static final Log logger = LogFactory
			.getLog(LvProductLadderAction.class);
	private LvProductLadder lvProductLadder;
	@Resource
	private InitLinkDataService initLinkDataService;

	@Resource(name = "productMsgService")
	private ProductMsgService productMsgService;

	/**
	 * 
	 * @Method: list
	 * @Description: [分页查询产品阶梯价列表]
	 * @Author: [liaoxiongjian]
	 * @CreateDate: [2012-7-4 上午11:56:55]
	 * @UpdateUser: [liaoxiongjian]
	 * @UpdateDate: [2012-7-4 上午11:56:55]
	 * @UpdateRemark: [说明本次修改内容]
	 * @return String
	 * @throws
	 */
	public String list() {
		if (logger.isInfoEnabled()) {
			logger.info("***** LvProductLadderAction.list() method begin*****");
		}
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("page", page);
		dto.put("lvProductLadder", lvProductLadder);
		// 产品阶梯价分页集合对象
		page = (Pagination) this.doService("LvProductLadderService", "getList",
				dto);

		// 查询产品列表信息
		List<LvProduct> list = (List<LvProduct>) this.doService(
				"LvProductService", "getAll", dto);
		this.getRequest().setAttribute("productList", list);

		// 根据当前用户查询对应用户的店铺信息（可支持多角色操作）
		List<LvStore> storeList = (List<LvStore>) this.doService(
				"LvStoreService", "getAllNoShop", dto);
		this.getRequest().setAttribute("storeList", storeList);

		if (logger.isInfoEnabled()) {
			logger.info("***** lvProductLadderAction.list() method end*****");
		}
		return LIST;
	}

	/**
	 * 
	 * @Method: befEdit
	 * @Description: [跳转到编辑产品阶梯价页面]
	 * @Author: [liaoxiongjian]
	 * @CreateDate: [2012-7-4 上午11:57:18]
	 * @UpdateUser: [liaoxiongjian]
	 * @UpdateDate: [2012-7-4 上午11:57:18]
	 * @UpdateRemark: [说明本次修改内容]
	 * @return String
	 * @throws
	 */
	public String befEdit() {
		if (logger.isInfoEnabled()) {
			logger.info("***** lvProductLadderAction.befEdit() method begin*****");
		}
		// 查询产品阶梯价信息
		dto.put("lvProductLadder", lvProductLadder);
		lvProductLadder = (LvProductLadder) this.doService(
				"LvProductLadderService", "get", dto);
		this.getRequest().setAttribute("lvProductLadder", lvProductLadder);

		// 查询产品列表信息
		LvProduct lvProduct = new LvProduct();
		lvProduct.setStoreId(lvProductLadder.getStoreId());
		dto.put("lvProduct", lvProduct);
		List<LvProduct> list = (List<LvProduct>) this.doService(
				"LvProductService", "getLadderProduct", dto);
		this.getRequest().setAttribute("productList", list);

		if (logger.isInfoEnabled()) {
			logger.info("***** lvProductLadderAction.befEdit() method end*****");
		}
		return "edit";
	}

	public String befShopEdit() {
		if (logger.isInfoEnabled()) {
			logger.info("***** lvProductLadderAction.befEdit() method begin*****");
		}
		// 查询产品阶梯价信息
		dto.put("lvProductLadder", lvProductLadder);
		lvProductLadder = (LvProductLadder) this.doService(
				"LvProductLadderService", "get", dto);
		this.getRequest().setAttribute("lvProductLadder", lvProductLadder);
		if (logger.isInfoEnabled()) {
			logger.info("***** lvProductLadderAction.befEdit() method end*****");
		}
		return "befShopEdit";
	}

	/**
	 * 
	 * @Method: edit
	 * @Description: [修改产品阶梯价]
	 * @Author: [liaoxiongjian]
	 * @CreateDate: [2012-7-4 上午11:57:22]
	 * @UpdateUser: [liaoxiongjian]
	 * @UpdateDate: [2012-7-4 上午11:57:22]
	 * @UpdateRemark: [说明本次修改内容]
	 * @return String
	 * @throws
	 */
	public String edit() {
		if (logger.isInfoEnabled()) {
			logger.info("***** lvProductLadderAction.edit() method begin*****");
		}
		// 获取登录用户信息
		BaseUsers users = (BaseUsers) getSession().getAttribute("USER");
		lvProductLadder.setModifyUserId(users.getId());
		lvProductLadder.setModifyUserName(users.getUserName());
		lvProductLadder.setModifyTime(new Date());
		dto.put("lvProductLadder", lvProductLadder);
		Boolean isFlag = (Boolean) this.doService("LvProductLadderService",
				"update", dto);
		if (!isFlag) {
			this.json.setMessage("该产品设置的阶梯价存在重复区间，请重新设置！");
			this.json.setStatusCode(300);
			return AJAX;
		} else {
			this.sendHtmlToWeb(lvProductLadder);
		}
		//发送消息给前台更新缓存
		productMsgService.sendProductMsg(lvProductLadder.getProductCode());
		
		if (logger.isInfoEnabled()) {
			logger.info("***** lvProductLadderAction.edit() method end*****");
		}
		return AJAX;
	}

	/**
	 * 
	 * @Method: befAdd
	 * @Description: [跳转到产品阶梯价新增页面]
	 * @Author: [liaoxiongjian]
	 * @CreateDate: [2012-7-4 上午11:57:27]
	 * @UpdateUser: [liaoxiongjian]
	 * @UpdateDate: [2012-7-4 上午11:57:27]
	 * @UpdateRemark: [说明本次修改内容]
	 * @return String
	 * @throws
	 */
	public String befAdd() {
		if (logger.isInfoEnabled()) {
			logger.info("***** lvProductLadderAction.befAdd() method begin*****");
		}
		// 查询产品列表信息
		List<LvProduct> list = (List<LvProduct>) this.doService(
				"LvProductService", "getLadderProduct", dto);
		this.getRequest().setAttribute("productList", list);

		if (logger.isInfoEnabled()) {
			logger.info("***** lvProductLadderAction.befAdd() method add*****");
		}
		return "befAdd";
	}

	public String befShopAdd() {
		return "befShopAdd";
	}

	/**
	 * 
	 * @Method: add
	 * @Description: [新增产品阶梯价]
	 * @Author: [liaoxiongjian]
	 * @CreateDate: [2012-7-4 上午11:57:31]
	 * @UpdateUser: [liaoxiongjian]
	 * @UpdateDate: [2012-7-4 上午11:57:31]
	 * @UpdateRemark: [说明本次修改内容]
	 * @return String
	 * @throws
	 */
	public String add() {
		if (logger.isInfoEnabled()) {
			logger.info("***** lvProductLadderAction.add() method begin*****");
		}
		lvProductLadder.setCode(CodeUtils.getCode());// code设置
		lvProductLadder.setCreateTime(new Date()); // 创建时间
		dto.put("lvProductLadder", lvProductLadder);
		Boolean isFlag = (Boolean) this.doService("LvProductLadderService",
				"save", dto);
		if (!isFlag) {
			this.json.setMessage("该产品设置的阶梯价存在重复区间，请重新设置！");
			this.json.setStatusCode(300);
			return AJAX;
		} else {
			this.sendHtmlToWeb(lvProductLadder);
			// 发送消息通知前台修改缓存
			productMsgService.sendProductMsg(lvProductLadder.getProductCode());
		}

		if (logger.isInfoEnabled()) {
			logger.info("***** lvProductLadderAction.add() method end*****");
		}
		return AJAX;
	}

	/**
	 * 
	 * @Method: view
	 * @Description: [查看产品阶梯价详情]
	 * @Author: [liaoxiongjian]
	 * @CreateDate: [2012-7-4 上午11:57:35]
	 * @UpdateUser: [liaoxiongjian]
	 * @UpdateDate: [2012-7-4 上午11:57:35]
	 * @UpdateRemark: [说明本次修改内容]
	 * @return String
	 * @throws
	 */
	public String view() {
		if (logger.isInfoEnabled()) {
			logger.info("***** lvProductLadderAction.view() method begin*****");
		}
		// 查询产品阶梯价信息
		dto.put("lvProductLadder", lvProductLadder);
		lvProductLadder = (LvProductLadder) this.doService(
				"LvProductLadderService", "get", dto);
		this.getRequest().setAttribute("lvProductLadder", lvProductLadder);

		// 查询产品列表信息
		List<LvProduct> list = (List<LvProduct>) this.doService(
				"LvProductService", "getAll", dto);
		this.getRequest().setAttribute("productList", list);

		if (logger.isInfoEnabled()) {
			logger.info("***** lvProductLadderAction.view() method end*****");
		}
		return "view";
	}

	/**
	 * 
	 * @Method: view
	 * @Description: [查看产品阶梯价详情]
	 * @Author: [liaoxiongjian]
	 * @CreateDate: [2012-7-4 上午11:57:35]
	 * @UpdateUser: [liaoxiongjian]
	 * @UpdateDate: [2012-7-4 上午11:57:35]
	 * @UpdateRemark: [说明本次修改内容]
	 * @return String
	 * @throws
	 */
	public String shopView() {
		if (logger.isInfoEnabled()) {
			logger.info("***** lvProductLadderAction.view() method begin*****");
		}
		// 查询产品阶梯价信息
		dto.put("lvProductLadder", lvProductLadder);
		lvProductLadder = (LvProductLadder) this.doService(
				"LvProductLadderService", "get", dto);
		this.getRequest().setAttribute("lvProductLadder", lvProductLadder);

		if (logger.isInfoEnabled()) {
			logger.info("***** lvProductLadderAction.view() method end*****");
		}
		return "shopView";
	}

	/**
	 * 
	 * @Method: del
	 * @Description: [删除产品阶梯价]
	 * @Author: [liaoxiongjian]
	 * @CreateDate: [2012-7-4 上午11:57:41]
	 * @UpdateUser: [liaoxiongjian]
	 * @UpdateDate: [2012-7-4 上午11:57:41]
	 * @UpdateRemark: [说明本次修改内容]
	 * @return String
	 * @throws
	 */
	public String del() {
		if (logger.isInfoEnabled()) {
			logger.info("***** lvProductLadderAction.del() method begin*****");
		}
		dto.put("lvProductLadder", lvProductLadder);
		this.doService("LvProductLadderService", "delete", dto);
		json.doNavTabTodo();

		//发送消息给前台更新缓存
		productMsgService.sendProductMsg(lvProductLadder.getProductCode());
		
		if (logger.isInfoEnabled()) {
			logger.info("***** lvProductLadderAction.del() method end*****");
		}
		return AJAX;
	}

	/**
	 * 
	 * @Method: delList
	 * @Description: [批量删除产品阶梯价]
	 * @Author: [liaoxiongjian]
	 * @CreateDate: [2012-7-4 上午11:57:47]
	 * @UpdateUser: [liaoxiongjian]
	 * @UpdateDate: [2012-7-4 上午11:57:47]
	 * @UpdateRemark: [说明本次修改内容]
	 * @return String
	 * @throws
	 */
	public String delList() {
		if (logger.isInfoEnabled()) {
			logger.info("***** lvProductLadderAction.delList() method begin*****");
		}
		if (ids != null && ids.length() > 0) {
			StringBuilder codes = new StringBuilder();
			String[] temp_ids = ids.split(",");
			for (int i = 0; i < temp_ids.length; i++) {
				if (temp_ids[i].length() > 0) {
					codes.append(temp_ids[i].split(":")[1].trim()).append(",");
					int id = Integer.parseInt(temp_ids[i].split(":")[0].trim());
					lvProductLadder = new LvProductLadder();
					lvProductLadder.setId(id);
					dto.put("lvProductLadder", lvProductLadder);
					// 删除model
					this.doService("LvProductLadderService", "delete", dto);
				}
			}
			//发送消息给前台更新缓存信息
			productMsgService.sendProductMsg(codes.deleteCharAt(codes.length()-1).toString());
		}
		json.doNavTabTodo();
		if (logger.isInfoEnabled()) {
			logger.info("***** lvProductLadderAction.delList() method end*****");
		}
		return AJAX;
	}

	/**
	 * 
	 * @Method: sendHtmlToWeb
	 * @Description: [执行单个页面静态化任务]
	 * @Author: [liaoxiongjian]
	 * @CreateDate: [2014-9-29 上午11:46:44]
	 * @UpdateUser: [liaoxiongjian]
	 * @UpdateDate: [2014-9-29 上午11:46:44]
	 * @UpdateRemark: [说明本次修改内容]
	 * @return void
	 */
	private void sendHtmlToWeb(LvProductLadder productLadder) {
		// 执行单个页面静态化任务
		String htmlPath = "/products/" + productLadder.getProductCode()
				+ ".html";
		initLinkDataService.sendHtmlToWeb(htmlPath, productLadder.getStoreId());
	}

	public LvProductLadder getLvProductLadder() {
		return lvProductLadder;
	}

	public void setLvProductLadder(LvProductLadder lvProductLadder) {
		this.lvProductLadder = lvProductLadder;
	}
}
