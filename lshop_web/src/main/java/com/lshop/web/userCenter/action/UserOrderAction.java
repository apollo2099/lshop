package com.lshop.web.userCenter.action;

import java.io.File;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.lshop.common.action.BaseAction;
import com.lshop.common.pojo.logic.LvOrder;
import com.lshop.common.pojo.logic.LvOrderAddress;
import com.lshop.common.pojo.logic.LvOrderComment;
import com.lshop.common.pojo.logic.LvOrderMac;
import com.lshop.common.pojo.logic.LvPaymentStyle;
import com.lshop.common.pojo.logic.LvWesternInfo;
import com.lshop.common.util.Constants;
import com.lshop.common.util.HttpRequstUtil;
import com.lshop.web.order.service.OrderMacService;
import com.lshop.web.userCenter.UserConstant;

/**
 * 用户中心订单模块
 * @author zhengxue
 * @since 2.0 2012-08-03
 *
 */
@SuppressWarnings("serial")
@Controller("UserOrderAction")
@Scope("prototype")
public class UserOrderAction extends BaseAction {

	
	private LvOrder lvOrder; //订单表
	private String oid; //订单号
	private LvWesternInfo lvWesternInfo;//西联信息表
	private String isuccess;
	private LvOrderComment lvOrderComment; //订单评论信息表
	private String shopFlag; //店铺标志
	private File img;//评论图片
	private String imgFileName;//评论图片名称
	@Resource
	private OrderMacService orderMacService;

	/**
	 * 查询所有的订单列表
	 * @return
	 * @throws Exception
	 */
	public String getOrderlist() throws Exception{
		
		String em = "";
		try {
			Map<String, String> _userinfo = getCookieValueToMap(UserConstant.USER_CENTER,true);
			if (_userinfo == null || _userinfo.isEmpty()||!_userinfo.containsKey("email")) {
				return INPUT;
			}
			em = _userinfo.get("email");
		} catch (Exception e) {
			return INPUT;
		}

		//我的账户模块
		dto.put("payStatus", this.getRequest().getParameter("payStatus")); //支付状态
		dto.put("status", this.getRequest().getParameter("status")); //订单状态
		
		//订单列表搜索模块
		dto.put("orderId", this.getRequest().getParameter("orderId")); //搜索——订单号
		dto.put("storeName", this.getRequest().getParameter("storeName")); //搜索——商家名称
		dto.put("startTime", this.getRequest().getParameter("startTime")); //搜索——订单时间（起）
		dto.put("endTime", this.getRequest().getParameter("endTime")); //搜索——订单时间（止）
		dto.put("oidStatus", this.getRequest().getParameter("oidStatus")); //自定义订单状态：1，待付款 2，已付款,未确认 3，已付款,待发货 4，已付款,已发货 5，待评价  6，已完成 7，已退货
		dto.put("couponCode", this.getRequest().getParameter("couponCode"));//搜索——優惠碼
		
		String oidStatus = this.getRequest().getParameter("oidStatus");
		if(null!=oidStatus && !("").equals(oidStatus)){
			Integer sta = Integer.parseInt(oidStatus);
			if(sta==1){
				dto.put("payStatus", 0);
			}else if(sta==2){
				dto.put("payStatus", 2);
			}else if(sta==3){
				dto.put("payStatus", 1);
				dto.put("status", 0);
			}else if(sta==4){
				dto.put("payStatus", 1);
				dto.put("status", 1);
			}else if(sta==5){
				dto.put("payStatus", 1);
				dto.put("status", 2);
			}else if(sta==6){
				dto.put("payStatus", 1);
				dto.put("status", 4);
			}else if(sta==7){
				dto.put("payStatus", 1);
				dto.put("status", 3);
			}
		}
		
		dto.put("em", em);
		page.setNumPerPage(10);
		dto.setDefaultPage(page);

		String mallSystem = Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag()); //获取当前店铺所属系统（华扬orBanana）
		dto.put("mallSystem", mallSystem);
		page = (Pagination) doService("UserCenterService", "getOrderlist", dto);
		
		this.getRequest().setAttribute("flag", "order");
		this.getRequest().setAttribute("pagination", page);
		this.getRequest().setAttribute("payStatus", this.getRequest().getParameter("payStatus"));
		this.getRequest().setAttribute("status", this.getRequest().getParameter("status"));
		
		//订单列表搜索模块
		this.getRequest().setAttribute("orderId", this.getRequest().getParameter("orderId")); //搜索——订单号
		this.getRequest().setAttribute("storeName", this.getRequest().getParameter("storeName")); //搜索——商家名称
		this.getRequest().setAttribute("startTime", this.getRequest().getParameter("startTime")); //搜索——订单时间（起）
		this.getRequest().setAttribute("endTime", this.getRequest().getParameter("endTime")); //搜索——订单时间（止）
		this.getRequest().setAttribute("oidStatus", this.getRequest().getParameter("oidStatus")); //自定义订单状态：1，待付款 2，已付款,未确认 3，已付款,待发货 4，已付款,已发货 5，待评价  6，已完成 7，已退货
		this.getRequest().setAttribute("couponCode", this.getRequest().getParameter("couponCode")); //搜索——優惠碼
		
		return "orderList";
	}

	/**
	 * 查看订单详情
	 * @return
	 */
	public String viewOrderInfo(){
		
//		dto.put("storeFlag", shopFlag);
		dto.put("oid", oid);
//		dto.put("request", this.getRequest());
		Dto orderDto = (Dto) doService("UserOrderService", "viewOrderInfo", dto);
		HttpRequstUtil.setAttributes(getRequest(), orderDto);

		//根据订单号查询mac兑换详情
		LvOrderMac  orderMac =orderMacService.findByOrderId(oid);
		this.getRequest().setAttribute("orderMac", orderMac);
		this.getRequest().setAttribute("flag", "order");
		return "orderInfo";
	}
	
	/**
	 * 确认支付信息
	 * @return
	 */
	public String toConfirmPay(){
		
//		dto.put("storeFlag", shopFlag);
		dto.put("oid", oid);
//		dto.put("request", this.getRequest());
//		
//		dto=(Dto) doService("UserOrderService", "toConfirmPay", dto);
		
		Dto orderDto = (Dto) doService("UserOrderService", "viewOrderInfo", dto);
		HttpRequstUtil.setAttributes(getRequest(), orderDto);
		//根据订单号查询mac兑换详情
		LvOrderMac  orderMac =orderMacService.findByOrderId(oid);
		this.getRequest().setAttribute("orderMac", orderMac);
		
		this.getRequest().setAttribute("flag", "order");
		return "confirmOrderInfo";
	}
	
	/**
	 * 传递订单信息给支付页面
	 * @return
	 */
	public String toPay(){
	
		String oid=this.getRequest().getParameter("oid");
		dto.put("oid", oid);
		this.doService("UserOrderService", "toPay", dto);
		
		//传递订单和收货地址信息
		LvOrder lvOrder=(LvOrder)dto.get("lvOrder");
		this.getRequest().setAttribute("lvOrder", lvOrder);
		this.getRequest().setAttribute("lvOrderAdress", (LvOrderAddress)dto.get("lvOrderAdress"));
		this.getRequest().setAttribute("mark", 2); //mark:1,表示支付成功后跳转到购物车支付成功页面；2，表示支付成功后跳转到用户中心支付成功页面
		
		//根据订单信息查询支付方式详情
		dto.put("payValue", lvOrder.getPaymethod());
		dto.put("storeFlag", lvOrder.getStoreId());
		LvPaymentStyle lvPaymentStyle=(com.lshop.common.pojo.logic.LvPaymentStyle) this.doService("PayService", "getLvPaymentStyle", dto);
		this.getRequest().setAttribute("lvPaymentStyle", lvPaymentStyle);
		
//		//判断是否新的支付方式(支付宝国际卡)
//		if(Constants.PAY_METHOD_ALIPAY_INITIAL==lvPaymentStyle.getPayValue()){
//			String checkoutId;
//			try {
//				checkoutId = alipayInitialService.prepareCheckout(oid);
//				if(ObjectUtils.isNotEmpty(checkoutId)){
//					this.getRequest().setAttribute("checkoutId",checkoutId); 
//					return "pay";
//				}else {
//					return "e404";
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}

		return "payMothodCode";
	}
	
	/**
	 * 提交西联信息
	 * @return
	 */
	public String saveWesternInfo(){
		//保存对象
		dto.setDefaultPo(lvWesternInfo);
		this.doService("ShopCartService", "saveWesternInfo", dto);
		
		this.getRequest().setAttribute("oid",lvWesternInfo.getOid() );
		this.getRequest().setAttribute("shopFlag",lvWesternInfo.getStoreId() );
		this.getRequest().setAttribute("flag", "order");
		oid = lvWesternInfo.getOid();
		return "westernSuccess";
	}
	
	/**
	 * 
	 * 跳转至西联信息页面
	 * @return
	 */
	public String toOrderWestern(){
		
		dto.put("oid",  this.getRequest().getParameter("oid"));
		this.doService("UserOrderService", "getOrderInfo", dto);
		
		//传递订单和收货地址信息
		LvOrder order=(LvOrder)dto.get("lvOrder");
		this.getRequest().setAttribute("lvOrder",order );
		this.getRequest().setAttribute("lvOrderAdress", (LvOrderAddress)dto.get("lvOrderAdress"));
		
		this.getRequest().setAttribute("flag", "order");
		return "orderWestern";
	}
	
	/**
	 * 确认收货
	 * @return
	 */
	public String confirmShouhuo(){
		dto.put("code", this.getRequest().getParameter("code"));
		doService("UserOrderService", "confirmShouhuo", dto);
	    return "getOrderlist";
	}
	
	/**
	 * 查看物流信息
	 * @return
	 */
	public String doLogisticsTrackingInfo(){
	
		dto.put("oid", oid);
		dto=(Dto) doService("UserOrderService", "doLogisticsTrackingInfo", dto);
		getRequest().setAttribute("doc", dto.get("doc"));
		
		dto.put("request", this.getRequest());
		dto.put("storeFlag", shopFlag);
		dto.put("oid", oid);
		Dto orderDto = (Dto) doService("UserOrderService", "viewOrderInfo", dto);
		HttpRequstUtil.setAttributes(getRequest(), orderDto);
		this.getRequest().setAttribute("flag", "order");
	
		return "express";
	}
	
	/**
	 * 跳转到用户评价页面
	 * @return
	 */
	public String toOrderComment(){
		
		dto.put("storeFlag", shopFlag);
		dto.put("oid", oid);
		dto.put("request", this.getRequest());
		Dto orderDto = (Dto) doService("UserOrderService", "viewOrderInfo", dto);
		HttpRequstUtil.setAttributes(getRequest(), orderDto);
		
		this.getRequest().setAttribute("flag", "order");
		return "orderComment";
	}
	
	/**
	 *  保存评论信息
	 * @return
	 * @throws Exception
	 */
	public String saveComment() throws Exception{
		
		//评论图片主图片上传
		if(img!=null){			
			dto.put("dir", "comment");
			dto.put("storeFlag", shopFlag);  
			dto.put("filedata", img);
			dto.put("filedataFileName", imgFileName);
			dto.put("request", this.getRequest());
			dto.put("resPath", this.getText("res.domain.path"));
			String imgPath=(String) this.doService("UploadService", "uploadImg", dto);
			lvOrderComment.setCommentImages(imgPath);
		}
		
		
		dto.put("storeFlag", shopFlag);
		//获取用户的code和nickname
		String uid=this.getCookieValueToMap(UserConstant.USER_CENTER, true).get("uid");
		String nickname=this.getCookieValueToMap(UserConstant.USER_CENTER, true).get("nickname");
		
		lvOrderComment.setUid(uid);
		lvOrderComment.setNickname(nickname);
		dto.setDefaultPo(lvOrderComment);
		this.doService("UserOrderService", "saveComment", dto);
		
		return "getCommentList";
	}

	/**
	 * 提醒卖家发货
	 * @return
	 * @throws Exception
	 */
	public String remindOrder() throws Exception{
		//获取当前登陆用户信息
		Map<String, String> user = getCookieValueToMap(UserConstant.USER_CENTER,true);
		String userCode=user.get("uid");
		Integer uid=Integer.parseInt(user.get("id"));
		String userName=user.get("nickname");
		
		dto.put("userCode", userCode);
		dto.put("uid", uid);
		dto.put("userName", userName);
		dto.put("storeFlag", shopFlag);
		dto.put("oid", this.getRequest().getParameter("oid"));
		doService("UserOrderService", "remindOrder", dto);
		
		return null;
	}
	
	
	public String toOrderInfo(){
		String em = "";
		try {
			Map<String, String> _userinfo = getCookieValueToMap(UserConstant.USER_CENTER,true);
			if (_userinfo == null || _userinfo.isEmpty()||!_userinfo.containsKey("email")) {
				return INPUT;
			}
			em = _userinfo.get("email");
		} catch (Exception e) {
			return INPUT;
		}

		//我的账户模块
		dto.put("payStatus", this.getRequest().getParameter("payStatus")); //支付状态
		dto.put("status", this.getRequest().getParameter("status")); //订单状态
		
		//订单列表搜索模块
		dto.put("orderId", this.getRequest().getParameter("orderId")); //搜索——订单号
		dto.put("storeName", this.getRequest().getParameter("storeName")); //搜索——商家名称
		dto.put("startTime", this.getRequest().getParameter("startTime")); //搜索——订单时间（起）
		dto.put("endTime", this.getRequest().getParameter("endTime")); //搜索——订单时间（止）
		dto.put("oidStatus", this.getRequest().getParameter("oidStatus")); //自定义订单状态：1，待付款 2，已付款,未确认 3，已付款,待发货 4，已付款,已发货 5，待评价  6，已完成 7，已退货
		
		String oidStatus = this.getRequest().getParameter("oidStatus");
		if(null!=oidStatus && !("").equals(oidStatus)){
			Integer sta = Integer.parseInt(oidStatus);
			if(sta==1){
				dto.put("payStatus", 0);
			}else if(sta==2){
				dto.put("payStatus", 2);
			}else if(sta==3){
				dto.put("payStatus", 1);
				dto.put("status", 0);
			}else if(sta==4){
				dto.put("payStatus", 1);
				dto.put("status", 1);
			}else if(sta==5){
				dto.put("payStatus", 1);
				dto.put("status", 2);
			}else if(sta==6){
				dto.put("payStatus", 1);
				dto.put("status", 4);
			}else if(sta==7){
				dto.put("payStatus", 1);
				dto.put("status", 3);
			}
		}
		
		dto.put("em", em);
		page.setNumPerPage(10);
		dto.setDefaultPage(page);

		String mallSystem = Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag()); //获取当前店铺所属系统（华扬orBanana）
		dto.put("mallSystem", mallSystem);
		page = (Pagination) doService("UserCenterService", "getOrderlist", dto);
		
		this.getRequest().setAttribute("flag", "order");
		this.getRequest().setAttribute("pagination", page);
		this.getRequest().setAttribute("payStatus", this.getRequest().getParameter("payStatus"));
		this.getRequest().setAttribute("status", this.getRequest().getParameter("status"));
		
		//订单列表搜索模块
		this.getRequest().setAttribute("orderId", this.getRequest().getParameter("orderId")); //搜索——订单号
		this.getRequest().setAttribute("storeName", this.getRequest().getParameter("storeName")); //搜索——商家名称
		this.getRequest().setAttribute("startTime", this.getRequest().getParameter("startTime")); //搜索——订单时间（起）
		this.getRequest().setAttribute("endTime", this.getRequest().getParameter("endTime")); //搜索——订单时间（止）
		this.getRequest().setAttribute("oidStatus", this.getRequest().getParameter("oidStatus")); //自定义订单状态：1，待付款 2，已付款,未确认 3，已付款,待发货 4，已付款,已发货 5，待评价  6，已完成 7，已退货
		
		return "pageOrderList";
	}
	
	public LvOrder getLvOrder() {
		return lvOrder;
	}

	public void setLvOrder(LvOrder lvOrder) {
		this.lvOrder = lvOrder;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public LvWesternInfo getLvWesternInfo() {
		return lvWesternInfo;
	}

	public void setLvWesternInfo(LvWesternInfo lvWesternInfo) {
		this.lvWesternInfo = lvWesternInfo;
	}

	public String getIsuccess() {
		return isuccess;
	}

	public void setIsuccess(String isuccess) {
		this.isuccess = isuccess;
	}

	public LvOrderComment getLvOrderComment() {
		return lvOrderComment;
	}

	public void setLvOrderComment(LvOrderComment lvOrderComment) {
		this.lvOrderComment = lvOrderComment;
	}

	public String getShopFlag() {
		return shopFlag;
	}

	public void setShopFlag(String shopFlag) {
		this.shopFlag = shopFlag;
	}

	public File getImg() {
		return img;
	}

	public void setImg(File img) {
		this.img = img;
	}

	public String getImgFileName() {
		return imgFileName;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}
}
