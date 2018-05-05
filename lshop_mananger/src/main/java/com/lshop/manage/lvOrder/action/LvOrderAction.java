package com.lshop.manage.lvOrder.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.datastructure.page.SimplePage;
import com.gv.core.exception.ServiceException;
import com.gv.core.util.FileUpload;
import com.gv.core.util.ObjectUtils;
import com.gv.core.util.excel.ExcelWriter;
import com.lshop.common.pojo.logic.LvArea;
import com.lshop.common.pojo.logic.LvCoupon;
import com.lshop.common.pojo.logic.LvCouponType;
import com.lshop.common.pojo.logic.LvGroupBuy;
import com.lshop.common.pojo.logic.LvOrder;
import com.lshop.common.pojo.logic.LvOrderAddress;
import com.lshop.common.pojo.logic.LvOrderComment;
import com.lshop.common.pojo.logic.LvOrderCoupon;
import com.lshop.common.pojo.logic.LvOrderDetails;
import com.lshop.common.pojo.logic.LvOrderMac;
import com.lshop.common.pojo.logic.LvPaymentStyle;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.pojo.logic.LvShopProductType;
import com.lshop.common.pojo.logic.LvSpecialtyStoreType;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.pojo.logic.LvStoreArea;
import com.lshop.common.pojo.user.LvAccount;
import com.lshop.common.progressBar.utils.ProgressBarConstants;
import com.lshop.common.progressBar.utils.ProgressBarUtils;
import com.lshop.common.util.Constants;
import com.lshop.common.util.CsvFileUtil;
import com.lshop.common.util.DateUtils;
import com.lshop.common.util.OrderHelp;
import com.lshop.common.util.StringUtil;
import com.lshop.manage.common.action.BaseManagerAction;
import com.lshop.manage.lvOrder.service.LvOrderService;
import com.lshop.manage.lvOrder.service.OrderImportTaskService;
import com.lshop.manage.lvOrder.util.OrderConstants;
import com.lshop.manage.lvOrder.vo.OrderImport;
import com.lshop.manage.lvOrderMac.service.LvOrderMacService;
import com.lshop.ws.web.bz.order.ExpressResponse;
import com.lshop.ws.web.bz.order.Result;
/**
 * 
 *  
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvOrder.action.LvOrderAction.java]  
 * @ClassName:    [LvOrderAction]   
 * @Description:  [订单管理-action业务层]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-7-13 下午01:14:52]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-7-13 下午01:14:52]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v1.0] 
 *
 */
@Controller("LvOrderAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvOrderAction extends BaseManagerAction{
	private static final Log logger = LogFactory.getLog(LvOrderAction.class);
	@Resource
    private OrderImportTaskService orderImportTaskService;
	@Resource
	private LvOrderMacService lvOrderMacService;
	
	private LvOrder lvOrder;
	private LvOrderAddress lvOrderAddress;
	private LvOrderDetails lvOrderDetails;
	private LvOrderComment lvOrderComment;
	private LvShopProductType lvShopProductType;
	private LvProduct lvProduct;
	private LvAccount lvAccount;
	private LvStore lvStore;
	private String relName;
	private String couponCode;// 优惠码
	private String userEmail; // 用户邮箱
	private String content;   // 邮件内容
	private String title;     // 邮件标题
	private BufferedInputStream excelStream;
	private String overtime;  // 支付时间
	private Short oldStatus;
	private Short oldPayStatus;
	private Integer orderType;
	private Integer countryId;
	private Date versionTime;
	private String targetFlag;
	private File excelFile;//文件流
	private String excelFileFileName;//文件名称
    private String progressName;//进度条名称
    private String mac;//mac编码



	/**
	 * 
	 * @Method: list 
	 * @Description:  [一句话描述该类的功能]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-13 下午01:15:18]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-13 下午01:15:18]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String list(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvOrderAction.list() method begin*****");
		}
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("page",page);
		dto.put("lvOrder", lvOrder);
		dto.put("relName", relName);
		dto.put("couponCode",couponCode);
		dto.put("orderType", orderType);
		dto.put("countryId", countryId);
		dto.put("mac", mac);
		dto.put("lvOrderAddress", lvOrderAddress);
		//产品效果图分页集合对象
		page=(Pagination) this.doService("LvOrderService", "getList", dto);
		
		
		//查询对应订单的优惠码信息
		List tempList=new ArrayList();
		List list=page.getList();
		for (int i = 0; i < list.size(); i++) {
		    String tempCouponCode="";
			Map map= (Map) list.get(i);
			dto.put("orderId", map.get("oid"));
			List<LvOrderCoupon> macList= (List) this.doService("LvOrderCouponService", "findDetailsByOrderId", dto);
			if(macList!=null){
			   for(int num=0;num<macList.size();num++){
				   LvOrderCoupon lvOrderCoupon= macList.get(num);
					if(ObjectUtils.isNotEmpty(lvOrderCoupon.getCouponCode())){
						tempCouponCode=lvOrderCoupon.getCouponCode();
					}
				}
			}
			
			map.put("couponCodeList", tempCouponCode);
			tempList.add(map);
		}
		page.setList(tempList);
		
	    //查询所有的国家信息
		List<LvArea> areaList=(List<LvArea>) this.doService("LvAreaService", "getAll", dto);
		this.getRequest().setAttribute("areaList", areaList);
		
		//查询所有店铺信息
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","getAllNoShop", dto);
		this.getRequest().setAttribute("storeList", storeList);
    	
		//查询激活的支付方式
    	List<LvPaymentStyle> payStyleList=(List<LvPaymentStyle>) this.doService("LvPaymentStyleService", "findAll", dto);
    	this.getRequest().setAttribute("payStyleList", payStyleList);
		return LIST;
	} 
	
	
	public String shopOrderList(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvOrderAction.list() method begin*****");
		}
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("page",page);
		dto.put("lvOrder", lvOrder);
		dto.put("relName", relName);
		dto.put("couponCode",couponCode);
		dto.put("orderType", orderType);
		dto.put("countryId", countryId);
		dto.put("lvOrderAddress", lvOrderAddress);
		//产品效果图分页集合对象
		page=(Pagination) this.doService("LvOrderService", "getList", dto);
		
		
		//查询对应订单的优惠码信息
		List tempList=new ArrayList();
		List list=page.getList();
		for (int i = 0; i < list.size(); i++) {
		    String tempCouponCode="";
			Map map= (Map) list.get(i);
			dto.put("orderId", map.get("oid"));
			List<LvOrderCoupon> macList= (List) this.doService("LvOrderCouponService", "findDetailsByOrderId", dto);
			if(macList!=null){
			   for(int num=0;num<macList.size();num++){
				   LvOrderCoupon lvOrderCoupon= macList.get(num);
					if(ObjectUtils.isNotEmpty(lvOrderCoupon.getCouponCode())){
						tempCouponCode=lvOrderCoupon.getCouponCode();
					}
				}
			}
			
			map.put("couponCodeList", tempCouponCode);
			tempList.add(map);
		}
		page.setList(tempList);
		
	    //查询所有的国家信息
		List<LvArea> areaList=(List<LvArea>) this.doService("LvAreaService", "getAll", dto);
		this.getRequest().setAttribute("areaList", areaList);
		
		//查询所有店铺信息
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","getAllNoShop", dto);
		this.getRequest().setAttribute("storeList", storeList);
    	
		//查询激活的支付方式
    	List<LvPaymentStyle> payStyleList=(List<LvPaymentStyle>) this.doService("LvPaymentStyleService", "findAll", dto);
    	this.getRequest().setAttribute("payStyleList", payStyleList);
		return "shopOrderList";
	} 
	/**
	 * 
	 * @Method: befEdit 
	 * @Description:  [跳转到订单信息编辑订单状态和订单总金额页面]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-17 上午09:53:54]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-17 上午09:53:54]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String befEdit(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvOrderAction.befEdit() method begin*****");
		}
		//查询订单基本信息
		dto.put("lvOrder", lvOrder);
		lvOrder=(LvOrder) this.doService("LvOrderService", "get", dto);
		this.getRequest().setAttribute("lvOrder",lvOrder);
		
		//查询订单详情(包括对应的产品信息)
		dto.put("orderId", lvOrder.getOid());
		List<LvOrderDetails> orderDetailsList=(List<LvOrderDetails>) this.doService("LvOrderDetailsService", "getOrderDetails", dto);
		this.getRequest().setAttribute("orderDetailsList", orderDetailsList);
		
		//查询所有的产品信息
		List<LvProduct>  productList=(List<LvProduct>) this.doService("LvProductService", "getAll", dto);
		this.getRequest().setAttribute("productList", productList);
		
		//查看订单收货人信息
		dto.put("orderId", lvOrder.getOid());
		lvOrderAddress=(LvOrderAddress) this.doService("LvOrderAddressService", "getOrderAddress", dto);
		this.getRequest().setAttribute("lvOrderAddress", lvOrderAddress);
		
		//查询所以国家区域信息
		List<LvArea> areaList=(List<LvArea>) this.doService("LvAreaService", "getAll", dto);
    	this.getRequest().setAttribute("areaList", areaList);
		
		//查询订单赠品信息
    	if(ObjectUtils.isNotEmpty(lvOrder.getIsGift())&&lvOrder.getIsGift()==1){
			List orderGiftList=  (List) this.doService("LvOrderGiftService","findAllByOrderId", dto);
			this.getRequest().setAttribute("orderGiftList", orderGiftList);
		}
    	
		if(logger.isInfoEnabled()){
			  logger.info("***** LvOrderAction.befEdit() method end*****");
		}
		return "edit";
	}
	
	/**
	 * 
	 * @Method: edit 
	 * @Description:  [编辑订单状态和订单总金额]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-17 上午09:53:50]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-17 上午09:53:50]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String edit(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvOrderAction.edit() method begin*****");
		}
		//查询登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		lvOrder.setModifyUserId(users.getId());
		lvOrder.setModifyUserName(users.getUserName());
		lvOrder.setModifyTime(new Date());
		//保存订单信息
//		dto.put("lvOrder", lvOrder);
//		this.doService("LvOrderService", "update", dto);
		
		//保存订单地址详情
		lvOrderAddress.setModifyUserId(users.getId());
		lvOrderAddress.setModifyUserName(users.getUserName());
		lvOrderAddress.setModifyTime(new Date());
		dto.put("lvOrderAddress", lvOrderAddress);
		this.doService("LvOrderAddressService", "update", dto);
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvOrderAction.edit() method end*****");
		}
		return AJAX;
	}
	/**
	 * 
	 * @Method: view 
	 * @Description:  [查询订单详细信息：包括订单基本信息，产品信息，收货人信息，费用信息等]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-13 下午01:15:25]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-13 下午01:15:25]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String view(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvOrderAction.view() method begin*****");
		}
		//查询订单基本信息
		dto.put("lvOrder", lvOrder);
		lvOrder=(LvOrder) this.doService("LvOrderService", "getOrder", dto);
		this.getRequest().setAttribute("lvOrder",lvOrder);
		
		//查询订单详情(包括对应的产品信息)
		dto.put("orderId", lvOrder.getOid());
		List<LvOrderDetails> orderDetailsList=(List<LvOrderDetails>) this.doService("LvOrderDetailsService", "getOrderDetails", dto);
		this.getRequest().setAttribute("orderDetailsList", orderDetailsList);
		
		//查询订单优惠券关联信息[暂时只是处理1:1的情况]
		List<LvOrderCoupon> orderCouponList=(List<LvOrderCoupon>) this.doService("LvOrderCouponService", "findDetailsByOrderId", dto);
        if(ObjectUtils.isNotEmpty(orderCouponList)&&orderCouponList.size()>0){
        	LvOrderCoupon lvOrderCoupon=orderCouponList.get(0);
    		this.getRequest().setAttribute("lvOrderCoupon", lvOrderCoupon);
        }
		
		//查询所有的产品信息
		List<LvProduct>  productList=(List<LvProduct>) this.doService("LvProductService", "getAll", dto);
		this.getRequest().setAttribute("productList", productList);
		
		//查看订单收货人信息
		dto.put("orderId", lvOrder.getOid());
		lvOrderAddress=(LvOrderAddress) this.doService("LvOrderAddressService", "getOrderAddress", dto);
		this.getRequest().setAttribute("lvOrderAddress", lvOrderAddress);
		
		//查询所有店铺信息
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","getAllNoShop", dto);
		this.getRequest().setAttribute("storeList", storeList);
		
		
		//查询订单赠品信息
		if(ObjectUtils.isNotEmpty(lvOrder.getIsGift())&&lvOrder.getIsGift()==1){
			List orderGiftList=  (List) this.doService("LvOrderGiftService","findAllByOrderId", dto);
			this.getRequest().setAttribute("orderGiftList", orderGiftList);
		}
		
		//查询mac订单信息
		LvOrderMac lvOrderMac=lvOrderMacService.findByOrderId(lvOrder.getOid());
		this.getRequest().setAttribute("lvOrderMac", lvOrderMac);
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvOrderAction.view() method end*****");
		}
		return "view";
	}
	
	/**
	 * 
	 * @Method: del 
	 * @Description:  [软删除订单信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-16 下午02:40:41]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-16 下午02:40:41]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String del(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvOrderAction.del() method begin*****");
		}
		//查询登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		dto.put("users", users);
		dto.put("ids", ids);
		dto.put("lvOrder", lvOrder);
		this.doService("LvOrderService", "delete", dto);
		json.doNavTabTodo();
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvOrderAction.del() method begin*****");
		}
		return AJAX;
	}
	
	/**
	 * 
	 * @Method: revertDel 
	 * @Description:  [恢复软删除的订单]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-17 上午09:43:08]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-17 上午09:43:08]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String revertDel(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvOrderAction.audit() method begin*****");
		}
		//查询登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		lvOrder.setModifyUserId(users.getId());
		lvOrder.setModifyUserName(users.getUserName());
		lvOrder.setModifyTime(new Date());
		
		dto.put("lvOrder", lvOrder);
		this.doService("LvOrderService", "revertDel", dto);
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvOrderAction.audit() method end*****");
		}
		return AJAX;
	}
	
	/**
	 * 
	 * @Method: befAudit 
	 * @Description:  [跳转到订单审核页面]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-17 上午09:45:42]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-17 上午09:45:42]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String befAudit(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvOrderAction.befAudit() method begin*****");
		}

		
		//查询订单基本信息
		dto.put("lvOrder", lvOrder);
		lvOrder=(LvOrder) this.doService("LvOrderService", "get", dto);
		this.getRequest().setAttribute("lvOrder",lvOrder);
		
		//查询订单详情(包括对应的产品信息)
		dto.put("orderId", lvOrder.getOid());
		List<LvOrderDetails> orderDetailsList=(List<LvOrderDetails>) this.doService("LvOrderDetailsService", "getOrderDetails", dto);
		this.getRequest().setAttribute("orderDetailsList", orderDetailsList);
		
		//查询订单优惠券关联信息[暂时只是处理1:1的情况]
		List<LvOrderCoupon> orderCouponList=(List<LvOrderCoupon>) this.doService("LvOrderCouponService", "findDetailsByOrderId", dto);
        if(ObjectUtils.isNotEmpty(orderCouponList)&&orderCouponList.size()>0){
        	LvOrderCoupon lvOrderCoupon=orderCouponList.get(0);
    		this.getRequest().setAttribute("lvOrderCoupon", lvOrderCoupon);
        }
		
		//查询所有的产品信息
		List<LvProduct>  productList=(List<LvProduct>) this.doService("LvProductService", "getAll", dto);
		this.getRequest().setAttribute("productList", productList);
		
		//查看订单收货人信息
		dto.put("orderId", lvOrder.getOid());
		lvOrderAddress=(LvOrderAddress) this.doService("LvOrderAddressService", "getOrderAddress", dto);
		this.getRequest().setAttribute("lvOrderAddress", lvOrderAddress);
		
		//查询订单赠品信息
		if(ObjectUtils.isNotEmpty(lvOrder.getIsGift())&&lvOrder.getIsGift()==1){
			List orderGiftList=  (List) this.doService("LvOrderGiftService","findAllByOrderId", dto);
			this.getRequest().setAttribute("orderGiftList", orderGiftList);
		}
		
		//查询mac订单信息
		LvOrderMac lvOrderMac=lvOrderMacService.findByOrderId(lvOrder.getOid());
		this.getRequest().setAttribute("lvOrderMac", lvOrderMac);
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvOrderAction.befAudit() method end*****");
		}
		return "audit";
	}
	/**
	 * 
	 * @Method: audit 
	 * @Description:  [
	 *                1.财务审核和客服审核通过
	 *                2.审核通过发送订单信息到启创平台 
	 *                3.修改订单同步启创标志状态]
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-17 上午09:45:49]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-17 上午09:45:49]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String audit(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvOrderAction.audit() method begin*****");
		}
		//查询登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		lvOrder.setModifyUserId(users.getId());
		lvOrder.setModifyUserName(users.getUserName());
		lvOrder.setModifyTime(new Date());
		dto.put("versionTime", versionTime);
		dto.put("lvOrder", lvOrder);
		Integer isFlag=(Integer) this.doService("LvOrderService", "updateAudit", dto);
		if(isFlag==-1){
			this.json.setMessage("审核失败，该订单已经删除！");
			this.json.setStatusCode(300);
			return AJAX;
		}else if(isFlag==1){
			this.json.setMessage("审核失败，当前操作失效,请重新打开页面！");
			this.json.setStatusCode(300);
			return AJAX;
		}else if(isFlag==2){
			this.json.setMessage("审核失败，该订单地区未配置产品规格，请配置规格再审核订单！");
			this.json.setStatusCode(300);
			return AJAX;
		}

		//审核通过发送订单信息到启创平台 
		this.doService("LvOrderService", "sendOrderMSGToWMS", dto);
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvOrderAction.audit() method end*****");
		}
		return AJAX;
	}
	
	
	public String befSendRemark(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvOrderAction.befSendRemark() method begin*****");
		}
		//查询订单基本信息
		dto.put("lvOrder", lvOrder);
		lvOrder=(LvOrder) this.doService("LvOrderService", "get", dto);
		this.getRequest().setAttribute("lvOrder",lvOrder);
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvOrderAction.befSendRemark() method begin*****");
		}
		return "sendRemark";
	}
	/**
	 * 
	 * @Method: editSendRemark 
	 * @Description:  [发货备注]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-17 上午09:57:13]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-17 上午09:57:13]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String editSendRemark(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvOrderAction.editSendRemark() method begin*****");
		}
		//查询登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		lvOrder.setModifyUserId(users.getId());
		lvOrder.setModifyUserName(users.getUserName());
		lvOrder.setModifyTime(new Date());
		dto.put("versionTime", versionTime);
		dto.put("lvOrder", lvOrder);
		Integer isFlag=(Integer) this.doService("LvOrderService", "updateSendRemark", dto);
		if(isFlag==-1){
			this.json.setMessage("操作失败,该订单已经删除!");
		}else if(isFlag==1){
			this.json.setMessage("版本失效,请重新打开页面!");
			this.json.setStatusCode(300);
			return AJAX;
		}
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvOrderAction.editSendRemark() method end*****");
		}
		return AJAX;
	}
	
	
	public String befBreakRemark(){
		//查询订单基本信息
		dto.put("lvOrder", lvOrder);
		lvOrder=(LvOrder) this.doService("LvOrderService", "get", dto);
		this.getRequest().setAttribute("lvOrder",lvOrder);
		
		return "breakRemark";
	}
	/**
	 * 
	 * @Method: editBreakRemark 
	 * @Description:  [退货备注]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-17 上午09:57:30]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-17 上午09:57:30]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String editBreakRemark(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvOrderAction.editSendRemark() method begin*****");
		}
		//查询登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		lvOrder.setModifyUserId(users.getId());
		lvOrder.setModifyUserName(users.getUserName());
		lvOrder.setModifyTime(new Date());
		dto.put("versionTime", versionTime);
		dto.put("lvOrder", lvOrder);
		Integer isFlag=(Integer) this.doService("LvOrderService", "updateBreakRemark", dto);
		if(isFlag==-1){
			this.json.setMessage("操作失败,该订单已经删除!");
		}else if(isFlag==1){
			this.json.setMessage("版本失效,请重新打开页面!");
			this.json.setStatusCode(300);
			return AJAX;
		}
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvOrderAction.editSendRemark() method end*****");
		}
		return AJAX;
	}
	/**
	 * 
	 * @Method: befUpdatePrice 
	 * @Description:  [跳转到修改订单总金额页面]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-15 下午01:30:36]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-15 下午01:30:36]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return 
	 * @return String
	 */
	public String befUpdatePrice(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvOrderAction.befUpdatePrice() method begin*****");
		}
		//查询订单基本信息
		dto.put("lvOrder", lvOrder);
		lvOrder=(LvOrder) this.doService("LvOrderService", "getOrder", dto);
		this.getRequest().setAttribute("lvOrder",lvOrder);
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvOrderAction.befUpdatePrice() method end*****");
		}
		return "updatePrice";
	}
	
	/**
	 * 
	 * @Method: updatePrice 
	 * @Description:  [修改订单总金额]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-15 下午01:30:18]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-15 下午01:30:18]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return 
	 * @return String
	 */
	public String updatePrice(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvOrderAction.updatePrice() method begin*****");
		}
		
		//拦截日志修改金额前的订单金额 
		dto.put("lvOrder", lvOrder);
		LvOrder oldOrder = (LvOrder)this.doService("LvOrderService", "getOrder", dto);
		request.setAttribute("oldPrice", oldOrder.getTotalPrice());
		
		//查询登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		lvOrder.setModifyUserId(users.getId());
		lvOrder.setModifyUserName(users.getUserName());
		lvOrder.setModifyTime(new Date());
		dto.put("versionTime", versionTime);
		dto.put("lvOrder", lvOrder);
		Integer isFlag=(Integer) this.doService("LvOrderService", "updatePrice", dto);
		if(isFlag==-1){
			this.json.setMessage("操作失败,该订单已经删除!");
		}else if(isFlag==1){
			this.json.setMessage("版本失效,请重新打开页面!");
			this.json.setStatusCode(300);
			return AJAX;
		}
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvOrderAction.updatePrice() method end*****");
		}
		return AJAX;
	}
	
	/**
	 * 
	 * @Method: befUpdateStatus 
	 * @Description:  [跳转到修改订单状态页面]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-15 下午01:27:48]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-15 下午01:27:48]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return 
	 * @return String
	 */
	public String befUpdateStatus(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvOrderAction.befUpdateStatus() method begin*****");
		}
		//查询订单基本信息
		dto.put("lvOrder", lvOrder);
		lvOrder=(LvOrder) this.doService("LvOrderService", "getOrder", dto);
		this.getRequest().setAttribute("lvOrder",lvOrder);
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvOrderAction.befUpdateStatus() method end*****");
		}
		return "updateStatus";
	}
	
	/**
	 * 
	 * @Method: updateOrderStatus 
	 * @Description:  [修改订单状态]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-15 下午01:29:40]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-15 下午01:29:40]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return 
	 * @return String
	 */
	public String updateOrderStatus(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvOrderAction.updateOrderStatus() method begin*****");
		}
		//查询登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		lvOrder.setModifyUserId(users.getId());
		lvOrder.setModifyUserName(users.getUserName());
		lvOrder.setModifyTime(new Date());
		dto.put("versionTime", versionTime);
		dto.put("oldStatus", oldStatus);
		dto.put("oldPayStatus", oldPayStatus);
		dto.put("lvOrder", lvOrder);
		Integer isFlag= (Integer) this.doService("LvOrderService", "updateStatus", dto);
		if(isFlag==-1){
			this.json.setMessage("操作失败,该订单已经删除!");
			return AJAX;
		}else if(isFlag==1){
			this.json.setMessage("版本失效,请重新打开页面!");
			this.json.setStatusCode(300);
			return AJAX;
		}
		
		//后台管理员支付，修改团购的已购买人数和限量打折数目
		if(ObjectUtils.isNotEmpty(lvOrder.getPayStatus())
				&&lvOrder.getPayStatus().equals(1)
				&&!lvOrder.getPayStatus().equals(oldPayStatus)){
		   dto.put("oid",lvOrder.getOid());
		   this.doService("ShopCartService", "changeCount", dto);
		}  
		if(logger.isInfoEnabled()){
			  logger.info("***** LvOrderAction.updateOrderStatus() method end*****");
		}
		return AJAX;
	}
	/**
	 * 
	 * @Method: updateStatus 
	 * @Description:  [修改订单状态(确认收货)]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-17 下午04:33:57]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-17 下午04:33:57]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String updateStatus(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvOrderAction.updateStatus() method begin*****");
		}
		//查询登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		lvOrder.setModifyUserId(users.getId());
		lvOrder.setModifyUserName(users.getUserName());
		lvOrder.setModifyTime(new Date());
		dto.put("versionTime", versionTime);
		dto.put("lvOrder", lvOrder);
		Integer isFlag=(Integer) this.doService("LvOrderService", "updateStatus", dto);
		if(isFlag==-1){
			this.json.setMessage("操作失败,该订单已经删除!");
		}else if(isFlag==1){
			this.json.setMessage("版本失效,请刷新数据列表!");
			this.json.setStatusCode(300);
			json.doNavTabTodo();
			return AJAX;
		}
		
		json.doNavTabTodo();
		if(logger.isInfoEnabled()){
			  logger.info("***** LvOrderAction.updateStatus() method end*****");
		}
		return AJAX;
	}

	
	/**
	 * 
	 * @Method: receiving 
	 * @Description:  [恢复软删除的订单]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-17 下午05:41:31]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-17 下午05:41:31]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String receiving(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvOrderAction.receiving() method begin*****");
		}
		//查询登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		lvOrder.setModifyUserId(users.getId());
		lvOrder.setModifyUserName(users.getUserName());
		lvOrder.setModifyTime(new Date());
		dto.put("versionTime", versionTime);
		dto.put("lvOrder", lvOrder);
		Boolean isFlag=(Boolean) this.doService("LvOrderService", "receiving", dto);
		if(!isFlag){
			this.json.setMessage("版本失效,请刷新数据列表!");
			this.json.setStatusCode(300);
			json.doNavTabTodo();
			return AJAX;
		}
		
		json.setStatusCode(200);
		json.setMessage("恢复成功！");
		json.doNavTabTodo();
		
		if(logger.isInfoEnabled()){
			  logger.info("***** LvOrderAction.receiving() method end*****");
		}
		return AJAX;
	}
	
	/**
	 * 
	 * @Method: busShop 
	 * @Description:  [跳转到商家自主下单页面]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-18 上午10:34:01]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-18 上午10:34:01]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
    public String busShop(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvOrderAction.busShop() method begin*****");
		}
		//查询所有上架的产品信息
    	List<LvProduct> productList=(List<LvProduct>) this.doService("LvProductService", "getAllProduct", dto);
    	this.getRequest().setAttribute("productList", productList);
    	//查询区域信息
    	List<LvArea> areaList=(List<LvArea>) this.doService("LvAreaService", "getAll", dto);
    	this.getRequest().setAttribute("areaList", areaList);
    	    	
    	//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","getAllNoShop", dto);
		this.getRequest().setAttribute("storeList", storeList);
    	
		if(logger.isInfoEnabled()){
			  logger.info("***** LvOrderAction.busShop() method end*****");
		}
    	return "busShop";
    }
    
    /**
     * 
     * @Method: doShop 
     * @Description:  [商家自主下单(订单信息/订单地址信息/订单详情)]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-7-18 上午10:37:09]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-7-18 上午10:37:09]   
     * @UpdateRemark: [说明本次修改内容]  
     * @return String
     * @throws
     */
    public String doShop(){
    	if(logger.isInfoEnabled()){
			  logger.info("***** LvOrderAction.doShop() method begin*****");
		}
    	BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
    	dto.put("users", users);
    	//按照规则生成唯一规则的订单编号
    	String oid = createThirdOrderNum(lvOrder.getStoreId());
    	//保存订单信息/订单地址信息/订单详情 信息
    	dto.put("oid", oid);
    	dto.put("overtime", overtime);
    	dto.put("lvOrderAddress", lvOrderAddress);
    	dto.put("lvOrder", lvOrder);
    	dto.put("couponCode", couponCode);
    	this.doService("LvOrderService", "save", dto);
		
    	if(logger.isInfoEnabled()){
			  logger.info("***** LvOrderAction.doShop() method end*****");
		}
    	
    	//发送通知邮件
    	return AJAX;
    }


    /**
     * 
     * @Method: createThirdOrderNum 
     * @Description:  [创建第三方订单号规则]  
     * @Author:       [liaoxj]     
     * @CreateDate:   [2015-3-20 下午5:24:38]   
     * @UpdateUser:   [liaoxj]     
     * @UpdateDate:   [2015-3-20 下午5:24:38]   
     * @UpdateRemark: [说明本次修改内容]  
     * @param storeId 店铺标志
     * @return String 订单号
     */
	private String createThirdOrderNum(String storeId) {
		//订单编号规则变更：B+店铺id（2位）+时间（14位）+随机码（5位）
    	Integer id=Constants.STORE_FLAG_TO_IDS.get(storeId);
    	String mark=null;
    	if(ObjectUtils.isNotEmpty(id)){
    		if(id<10){
        		mark="B"+"0"+id.toString();
        	}else{
        		mark="B"+id.toString();
        	}
    	}else{
    		mark="B";
    	}
    	String oid = OrderHelp.createOrderId(mark);
		return oid;
	}
	
    
    /**
     * 
     * @Method: befComment 
     * @Description:  [一句话描述该类的功能]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-7-18 下午02:35:04]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-7-18 下午02:35:04]   
     * @UpdateRemark: [说明本次修改内容]  
     * @return String
     * @throws
     */
    public String befComment(){
    	this.getRequest().setAttribute("versionTime", new Date());
    	this.getRequest().setAttribute("ids", ids);
    	return "comment";
    }
    
    /**
     * 
     * @Method: comment 
     * @Description:  [一句话描述该类的功能]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-7-18 下午02:35:08]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-7-18 下午02:35:08]   
     * @UpdateRemark: [说明本次修改内容]  
     * @return String
     * @throws
     */
    public String comment(){
    	
		//查询登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		if(lvOrder==null){
		   lvOrder=new LvOrder();
		}
		lvOrder.setModifyUserId(users.getId());
		lvOrder.setModifyUserName(users.getUserName());
		lvOrder.setModifyTime(new Date());
		dto.put("lvOrder", lvOrder);
		
		//根据评分判断评论等级(评论等级 1=>差评,2=>中评,3=>好评)
		if(ObjectUtils.isNotEmpty(lvOrderComment.getScore())){
			if(lvOrderComment.getScore()<3){
				lvOrderComment.setGrade(Short.parseShort("1"));
			}else if(lvOrderComment.getScore()==3){
				lvOrderComment.setGrade(Short.parseShort("2"));
			}else if(lvOrderComment.getScore()>3){
				lvOrderComment.setGrade(Short.parseShort("3"));
			}
		}
		dto.put("versionTime", versionTime);
    	dto.put("ids", ids);
    	dto.put("lvOrderComment", lvOrderComment);
    	this.doService("LvOrderService", "saveSysComment", dto);
    	return AJAX;
    }
    
    
    /**
     * 
     * @Method: notice 
     * @Description:  [进入邮件通知页面]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-7-19 下午01:33:51]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-7-19 下午01:33:51]   
     * @UpdateRemark: [说明本次修改内容]  
     * @return String
     * @throws
     */
	public String notice(){
		//查询订单基本信息
		dto.put("lvOrder", lvOrder);
		lvOrder=(LvOrder) this.doService("LvOrderService", "get", dto);
		this.getRequest().setAttribute("lvOrder",lvOrder);
		
		//根据订单编号查询订单详情信息
		dto.put("orderId", lvOrder.getOid());
		lvOrderAddress = (LvOrderAddress) this.doService("LvOrderAddressService", "getOrderAddress", dto);// 查看订单收货地址
		this.getRequest().setAttribute("lvOrderAddress", lvOrderAddress);
		return "notice";
	}

    /**
     * 
     * @Method: doNotice 
     * @Description:  [发送邮件通知]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-7-19 下午01:34:05]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-7-19 下午01:34:05]   
     * @UpdateRemark: [说明本次修改内容]  
     * @return String
     * @throws
     */
	public String doNotice(){
		dto.put("mallFlag", Constants.STORE_TO_MALL_SYSTEM.get(lvOrder.getStoreId()));
		dto.put("title", title);// 邮件标题
		dto.put("userEmail", userEmail);// 用户email
		this.content += "<p>" + DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss") + "</p>";
		dto.put("content", content);// 邮件内容
		doService("emailSendService", "sendEmailNotice", dto);
		json.setMessage("邮件发送成功！");
		json.setStatusCode(200);
		return AJAX;
	}
	
	public String doShopNotice(){
		dto.put("ids", ids);
		dto.put("lvOrder", lvOrder);
		doService("LvOrderService", "doShopNotice", dto);
		json.doNavTabTodo();
		return AJAX;
	}
	
	/**
	 * 
	 * @Method: befSendOrder 
	 * @Description:  [跳转到店铺发货页面]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-11-26 下午04:20:10]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-11-26 下午04:20:10]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 */
	public String befSendOrder(){
		dto.put("lvOrder", lvOrder);
		lvOrder=(LvOrder) this.doService("LvOrderService", "getOrder", dto);// 查看订单收货地址
		this.getRequest().setAttribute("lvOrder", lvOrder);
		
		//根据订单编号查询订单详情信息
		dto.put("orderId", lvOrder.getOid());
		lvOrderAddress = (LvOrderAddress) this.doService("LvOrderAddressService", "getOrderAddress", dto);// 查看订单收货地址
		this.getRequest().setAttribute("lvOrderAddress", lvOrderAddress);
		return "sendOrder";
	}
	
	/**
	 * 
	 * @Method: sendOrder 
	 * @Description:  [店铺自主发货(不经过华扬商务系统)]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-11-26 下午04:20:53]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-11-26 下午04:20:53]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 */
	public String sendOrder(){
		//查询登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		lvOrder.setModifyUserId(users.getId());
		lvOrder.setModifyUserName(users.getUserName());
		lvOrder.setModifyTime(new Date());
		//发货修改状态和发货相关物流信息
		dto.put("lvOrder", lvOrder);
		dto.put("versionTime", versionTime);
		Integer isFlag=(Integer) this.doService("LvOrderService", "updateSendOrder", dto);
		if(isFlag==-1){
			this.json.setMessage("操作失败,该订单已经删除! !");
			return AJAX;
		}else if(isFlag==1){
			this.json.setMessage("版本失效,请重新打开页面!");
			this.json.setStatusCode(300);
			return AJAX;
		}
		
		//发货完成后发送邮件信息
		dto.put("mallFlag", Constants.STORE_TO_MALL_SYSTEM.get(lvOrder.getStoreId()));
		dto.put("title", title);// 邮件标题
		dto.put("userEmail", lvOrder.getUserEmail());// 用户email
		this.content += "<p>" + DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss") + "</p>";
		dto.put("content", content);// 邮件内容
		doService("emailSendService", "sendEmailNotice", dto);
		json.setMessage("发货成功！");
		json.setStatusCode(200);
		return AJAX;
	}
	
	/**
	 * 
	 * @Method: befChangeOrder 
	 * @Description:  [跳转到转单页面(第一步，选择目标店铺)]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2013-2-22 下午06:06:08]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2013-2-22 下午06:06:08]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return 
	 * @return String
	 */
	public String befChangeStore(){
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("model", lvStore);
		dto.put("lvOrder", lvOrder);
		page = (Pagination)this.doService("LvStoreService", "getList", dto);
        
		//查询所有的国家信息
		List<LvArea> areaList=(List<LvArea>) this.doService("LvAreaService", "getAll", dto);
		this.getRequest().setAttribute("areaList", areaList);
		return "befChangeStore";
	}
	
	/**
	 * 
	 * @Method: befChangeProduct 
	 * @Description:  [跳转到转单页面二(第二步，选择目标店铺匹配产品)]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2013-2-27 下午03:19:07]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2013-2-27 下午03:19:07]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return 
	 * @return String
	 */
	public String befChangeProduct(){
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("page",page);
		dto.put("ids", ids);
		dto.put("lvStore", lvStore);
		dto.put("lvOrder", lvOrder);
		dto.put("targetFlag", targetFlag);
		dto.put("orderId", lvOrder.getOid());
		if (ObjectUtils.isEmpty(targetFlag)) {
			json.setMessage("请先选择转单的目标店铺信息！");
			json.setStatusCode(300);
			return AJAX;
			
		}
		
		
		
		//查询当前订单的订单产品详情
		page=(Pagination) this.doService("LvOrderDetailsService", "getChangeOrderDetails", dto);
		
		
		//查询目标店铺的产品列表
		dto.put("targetFlag", targetFlag);
		List<LvProduct> productList=(List) this.doService("LvProductService", "changeProductList", dto);
		this.getRequest().setAttribute("productList", productList);
		
		//查询店铺信息
		dto.put("storeFlag", lvOrder.getStoreId());
		LvStore store= (LvStore) this.doService("LvStoreService", "findStore", dto);//原店铺信息
		dto.put("storeFlag", targetFlag);
		LvStore targetStore= (LvStore) this.doService("LvStoreService", "findStore", dto);//目标店铺信息
		this.getRequest().setAttribute("store", store);
		this.getRequest().setAttribute("targetStore", targetStore);
		return "befChangeProduct";
	}
	

	
	/**
	 * 
	 * @Method: changeOrder 
	 * @Description:  [转单处理]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2013-2-22 下午06:06:28]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2013-2-22 下午06:06:28]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return 
	 * @return String
	 */
	public String changeOrder(){
		//查询登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		dto.put("users", users);
		dto.put("ids", ids);
		dto.put("lvStore", lvStore);
		dto.put("lvOrder", lvOrder);
		dto.put("targetFlag", targetFlag);
		
		this.doService("LvOrderService", "changeOrder", dto);
		return AJAX;
	}
	
	/**
	 * 跳转到第三方订单导入页面
	 */
	public String befOrderImport(){
		return "befOrderImport";
	}
	
	/**
	 * 第三方订单导入入口程序
	 * @throws IOException 
	 */
	public String orderImport() throws IOException{
		//查询登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		Integer result = 0;// 1表示错误；2表示重复；其他表示成功
		String resourcePath = ServletActionContext.getServletContext().getRealPath("/upload");  

		
		//上传临时文件
		java.text.Format format=new SimpleDateFormat("yyyyMMddHHmmssSS");
		String SuffixName=excelFileFileName.substring(excelFileFileName.lastIndexOf("."));
		String filedataFileName=format.format(new Date())+SuffixName;
		FileUpload.upload(excelFile, resourcePath, filedataFileName);
		
		//解析excel
		List<String []> importList =CsvFileUtil.readCsvFile(resourcePath+"/"+filedataFileName, "UTF-8");
		//初始化进度条
		if(ObjectUtils.isEmpty(importList)){
			ProgressBarUtils.iniProgressBar(ProgressBarConstants.PROGRESS_FLAT_RUN_ERROR, progressName, 0, this.getSession());
		}
		
		
		
		//组装订单数据
		List<OrderImport> orderImportList=null;
		try {
			orderImportList =orderImportTaskService.buildOrderData(importList);
			//加载进度条
			ProgressBarUtils.iniProgressBar(ProgressBarConstants.PROGRESS_FLAT_RUN, progressName, orderImportList.size(), this.getSession());
		} catch (Exception e) {
			logger.error("初始化数据出错，错误信息:"+e.getMessage());
		}
		
		
		
		List<String[]> argList=new ArrayList<String[]>();
		//保存订单信息
		for (int i = 0; i < orderImportList.size(); i++) {
			OrderImport orderImport=orderImportList.get(i);
			orderImport.setOpreate(users.getUserName());
			/**
			 * 调用订单导入入口程序
			 */
			if(orderImport.getErrorCode().equals(OrderConstants.ORDER_IMPORT_ERROR_00000)){
				//
				try {
					String  errorMsg=orderImportTaskService.saveOrderImport(orderImport);
					if (!errorMsg.equals(OrderConstants.ORDER_IMPORT_ERROR_00000)) {
						result = 1;
						String[] argArr=new String[2];
						argArr[0]=orderImport.getOrderNumber();
						argArr[1]=String.valueOf(OrderConstants.ORDER_IMPORT_ERROR.get(errorMsg));
						argList.add(argArr);
					}else{
						result = 0;
						String[] argArr=new String[2];
						argArr[0]=orderImport.getOrderNumber();
						argArr[1]=String.valueOf(OrderConstants.ORDER_IMPORT_ERROR.get(OrderConstants.ORDER_IMPORT_ERROR_00000));
						argList.add(argArr);
					}
				} catch (Exception e) {//程序调用异常
					logger.error("保存订单错误，错误信息:"+e.getMessage());
					//
					result = 1;
					String[] argArr=new String[2];
					argArr[0]=orderImport.getOrderNumber();
					argArr[1]=String.valueOf(OrderConstants.ORDER_IMPORT_ERROR.get(OrderConstants.ORDER_IMPORT_ERROR_EXP));
					argList.add(argArr);
					
					//描绘进度条
					ProgressBarUtils.drawProgressBar(result, progressName, this.getSession());
					continue;
				}
			}else{
				result = 1;
				String[] argArr=new String[2];
				argArr[0]=orderImport.getOrderNumber();
				argArr[1]=String.valueOf(OrderConstants.ORDER_IMPORT_ERROR.get(orderImport.getErrorCode()));
				argList.add(argArr);
			}
			//描绘进度条
			ProgressBarUtils.drawProgressBar(result, progressName, this.getSession());
		}
		
		
		String reportFileName=format.format(new Date())+SuffixName;
		try {
			resourcePath=ServletActionContext.getServletContext().getRealPath("/report");  
			CsvFileUtil.writeCsvFile(argList, resourcePath+"/", reportFileName, true);
		} catch (Exception e) {
			logger.error("输出导出报告错误:"+e.getMessage());
		}
		
		
		String serverName= this.getRequest().getServerName();
		int serverPort= this.getRequest().getServerPort();
		String serverPath= this.getRequest().getServletPath();
		String resourceDomain="http://"+serverName+":"+serverPort+"/manager/download.action?fileName="+reportFileName;
		
		//终止进度条
		ProgressBarUtils.stopProgressBar(progressName,resourceDomain,this.getSession());
		this.json.doNavTabTodo();
		return null;
	}
	
	/**
	 *  手动终止进度条
	 * @Method: stopProgressBar   
	 * @return 
	 * @return String
	 */
	public String stopProgressBar(){
		
		String resourceDomain=this.getText("lshop.base.resource.domain");//资源服务器域名
		//终止进度条
		ProgressBarUtils.stopProgressBar(progressName,"",this.getSession());
		return null;
	}
	/**
	 * 
	 * @Method: synSendOrder 
	 * @Description:  [同步商务发货订单及物流信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2013-5-17 上午10:05:54]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2013-5-17 上午10:05:54]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return 
	 * @return String
	 */
	public String synSendOrder(){
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");//后台登录信息
		dto.put("lvOrder", lvOrder);
		dto.put("users",users);
		ExpressResponse expressResponse=(ExpressResponse) this.doService("LvOrderService", "synSendOrder", dto);
		if("1".equals(expressResponse.getResult().getResultCode())&&"1".equals(expressResponse.getExpressInfo().getStatus())){//同步发货成功
			json.setMessage(expressResponse.getResult().getResultMsg());
			json.setStatusCode(200);
			json.doNavTabTodo();
		}else if("1".equals(expressResponse.getResult().getResultCode())&&"0".equals(expressResponse.getExpressInfo().getStatus())){//同步发货失败,商务未发货
			json.setMessage("同步发货失败,商务未发货!");
			json.setStatusCode(300);
			json.doNavTabTodo();
		}else if("0".equals(expressResponse.getResult().getResultCode())){//同步发货失败
			json.setMessage(expressResponse.getResult().getResultMsg());
			json.setStatusCode(300);
			json.doNavTabTodo();
		}
		return AJAX;
	}
	
	/**
	 * 
	 * @Method: synSendSasOrder 
	 * @Description:  [手动同步异常订单到启创Sas代理商系统(推送订单信息)]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-12-17 下午6:05:08]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-12-17 下午6:05:08]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return 
	 * @return String
	 */
	public String synSendSasOrder(){
		dto.put("lvOrder", lvOrder);
		Boolean isFlag=(Boolean) this.doService("LvOrderService", "sendOrderMSGToWMS", dto);
		if(isFlag){
			json.setMessage("同步启创订单成功！");
			json.setStatusCode(200);
			json.doNavTabTodo();
		}else{
			json.setMessage("同步启创订单失败，请排查订单是否正确！");
			json.setStatusCode(300);
			json.doNavTabTodo();
		}
		
		return AJAX;
	}
	
	/**
	 * 
	 * @Method: orderStat 
	 * @Description:  [数据报表-订单销售统计]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-30 上午11:19:20]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-30 上午11:19:20]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String orderStat(){	
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("page",page);
		dto.put("model", lvStore);
		page=(Pagination) this.doService("LvStoreService", "findPage", dto);
		List list=page.getList();
		List tempList=new ArrayList();
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				Map map=new HashMap();
				LvStore lvStore=(LvStore) list.get(i);
				dto.put("lvOrder", lvOrder);
				dto.put("storeFlag", lvStore.getStoreFlag());
				//总下单数量
				Integer countAll=(Integer) this.doService("LvOrderService", "countAll", dto);
				this.getRequest().setAttribute("countAll",countAll);
				//已付款订单数
				Integer countPay=(Integer) this.doService("LvOrderService", "countPay", dto);
				this.getRequest().setAttribute("countPay",countPay);
				//未付款订单数
				Integer countUnPay=(Integer) this.doService("LvOrderService", "countUnPay", dto);
				this.getRequest().setAttribute("countUnPay",countUnPay);
				//销售额(已经支付且未退货的订单才统计,需要区分币种按汇率换算成美元累加)
				Float totalPriceUsd=(Float) this.doService("LvOrderService", "totalPriceUsd", dto);//美元销售累计
				Float totalPriceRmb=(Float) this.doService("LvOrderService", "totalPriceRmb", dto);//人民币销售累计
				Float totalPrice=totalPriceUsd+(totalPriceRmb*Constants.rateNumCNY);
				this.getRequest().setAttribute("totalPrice",totalPrice);
				
				//已完成订单数
				Integer countFinish=(Integer) this.doService("LvOrderService", "countFinish", dto);
				this.getRequest().setAttribute("countFinish",countFinish);
				//已删除订单数
				Integer countDelete=(Integer) this.doService("LvOrderService", "countDelete", dto);
				this.getRequest().setAttribute("countDelete",countDelete);
				//已退货订单数
				Integer countBack=(Integer) this.doService("LvOrderService", "countBack", dto);
				this.getRequest().setAttribute("countBack",countBack);

				//将统计的数据重新组装到map中
				map.put("storeFlag", lvStore.getStoreFlag());
				map.put("name", lvStore.getName());
				map.put("countAll", countAll);
				map.put("countPay", countPay);
				map.put("countUnPay", countUnPay);
				map.put("totalPrice", totalPrice);
				map.put("countFinish", countFinish);
				map.put("countDelete", countDelete);
				map.put("countBack", countBack);
				tempList.add(map);
			}
		}
		page.setList(tempList);		
		
		//查询所有店铺信息
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","getAllNoShop", dto);
		this.getRequest().setAttribute("storeList", storeList);
		return "orderStat";
	}
	
	/**
	 * 
	 * @Method: productStat 
	 * @Description:  [产品销售统计]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-30 下午03:43:21]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-30 下午03:43:21]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
    public String productStat(){
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("page",page);
		dto.put("lvOrder", lvOrder);
		dto.put("lvProduct", lvProduct);
    	page=(Pagination) this.doService("LvOrderService", "stateProduct", dto);
    	
		//查询所有店铺信息
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","getAllNoShop", dto);
		this.getRequest().setAttribute("storeList", storeList);
    	
		return "productStat";
	}
    
    /**
     * 
     * @Method: shopTypeState 
     * @Description:  [商品分类统计]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-12-27 上午10:05:39]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-12-27 上午10:05:39]   
     * @UpdateRemark: [说明本次修改内容]  
     * @return 
     * @return String
     */
    public String shopTypeState(){
    	dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("page",page);
		dto.put("lvOrder", lvOrder);
		dto.put("lvShopProductType", lvShopProductType);
    	page=(Pagination) this.doService("LvOrderService", "shopTypeState", dto);
    	
    	//查询所有店铺信息
		List<LvShopProductType> typeList= (List<LvShopProductType>) this.doService("LvShopProductTypeService","findAll", dto);
		this.getRequest().setAttribute("typeList", typeList);
    	return "shopTypeState";
    }
    
    /**
     * 
     * @Method: userOrderStat 
     * @Description:  [用户订单统计]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-7-31 上午11:19:54]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-7-31 上午11:19:54]   
     * @UpdateRemark: [说明本次修改内容]  
     * @return String
     * @throws
     */
    public String userOrderStat(){
    	dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("page",page);
		dto.put("lvAccount", lvAccount);
    	page=(Pagination) this.doService("LvOrderService", "stateUser", dto);
    	
    	//查询用户订单支付订单数目和未支付订单数目
    	List list=page.getList();
    	List tmpList=new ArrayList();
    	if(list!=null&&list.size()>0){
    	  for(int i=0;i<list.size();i++){
    		 if(lvOrder==null){
    			lvOrder=new LvOrder();
    		 } 
    		 Map map=(Map) list.get(i);
    		 lvOrder.setUserEmail(map.get("userEmail").toString());
    		 dto.put("lvOrder", lvOrder);
    		 //统计支付订单数目
    		 Integer countPay= (Integer) this.doService("LvOrderService", "countPay", dto);
    		 map.put("countPay", countPay);
    		 //统计未支付订单数目
    		 Integer countUnPay= (Integer) this.doService("LvOrderService", "countUnPay", dto);
    		 map.put("countUnPay", countUnPay);
    		 //统计总订单数
    		 Integer countAll=(Integer) this.doService("LvOrderService", "countAll", dto);
    		 map.put("countAll", countAll);
    		 //销售额
    		 Float totalPriceUsd=(Float) this.doService("LvOrderService", "totalPriceUsd", dto);//美元销售累计
    		 Float totalPriceRmb=(Float) this.doService("LvOrderService", "totalPriceRmb", dto);//人民币销售累计
    		 Float totalPrice=totalPriceUsd+(totalPriceRmb*Constants.rateNumCNY);
    		 map.put("totalPrice",totalPrice);
    		 tmpList.add(map);
    	  }
    	}
    	page.setList(tmpList);
		return "userOrderStat";
    }
    
	
	public String userOrderList(){
		if(logger.isInfoEnabled()){
			  logger.info("***** LvAccountAction.list() method begin*****");
		}
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("page",page);
		dto.put("lvOrder", lvOrder);
		page=(Pagination) this.doService("LvOrderService", "getList", dto);
		
		//查询对应订单的优惠码信息
		List tempList=new ArrayList();
		List list=page.getList();
		for (int i = 0; i < list.size(); i++) {
		    String tempCouponCode="";
			Map map= (Map) list.get(i);
			dto.put("orderId", map.get("oid"));
			List macList= (List) this.doService("LvOrderDetailsService", "getCouponCode", dto);
			if(macList!=null){
			   for(int num=0;num<macList.size();num++){
					Map mapTmp= (Map) macList.get(num);
					if(ObjectUtils.isNotEmpty(mapTmp.get("couponCode"))){
						if(num==macList.size()-1){
							tempCouponCode+=mapTmp.get("couponCode");
						}else{
							tempCouponCode+=mapTmp.get("couponCode")+",";
						}
					}
				}
			}
			
			map.put("couponCodeList", tempCouponCode);
			tempList.add(map);
		}
		page.setList(tempList);
		
		//查询所有店铺信息
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","getAllNoShop", dto);
		this.getRequest().setAttribute("storeList", storeList);
		return "userOrderList";
	}
	

	/**
	 * 
	 * 
	 * @Method: exportOrder 
	 * @Description:  [导出订单信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-16 下午03:21:43]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-16 下午03:21:43]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws FileNotFoundException 
	 */
	public String exportOrder() throws FileNotFoundException{
		if (logger.isInfoEnabled()) {
			logger.info("***** LvOrderManageAction.exportOrder() method begin*****");
		}
		//删除文件上传临时文件夹下面所有临时文件
		deleteTempFile(request);
		
		dto.put("ids",ids);
		//根据店铺标识查询店铺信息
		if(ObjectUtils.isNotEmpty(dto.get("flag"))){
			dto.put("storeFlag", dto.get("flag"));
			LvStore lvStore= (LvStore) this.doService("LvStoreService", "findStore", dto);
			dto.put("lvStore",lvStore);
		}
		
		List list = (List) this.doService("LvOrderService", "exportOrder", dto);
		ExcelWriter writer = new ExcelWriter();
		String path = request.getRealPath("/temp") + "/" + System.currentTimeMillis() + ".tmp";
		writer.create(path, list);
		File file = new File(path);
		excelStream = new BufferedInputStream(new FileInputStream(file));
		file.delete();
		
		if (logger.isInfoEnabled()) {
			logger.info("***** LvOrderManageAction.exportOrder() method end*****");
		}
		return "excel";
	}
	
	/**
	 * 
	 * @Method: exportOrderStat 
	 * @Description:  [数据统计-导出订单统计信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-30 下午03:42:17]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-30 下午03:42:17]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String exportOrderStat() throws FileNotFoundException{
		if (logger.isInfoEnabled()) {
			logger.info("***** LvOrderManageAction.exportOrderStat() method begin*****");
		}
		//删除文件上传临时文件夹下面所有临时文件
		deleteTempFile(request);
		
		dto.put("ids",ids);
		dto.put("lvOrder", lvOrder);
		//根据店铺标识查询店铺信息
		if(ObjectUtils.isNotEmpty(this.getFlag())){
			dto.put("storeFlag", this.getFlag());
			LvStore lvStore= (LvStore) this.doService("LvStoreService", "findStore", dto);
			dto.put("lvStore", lvStore);
		}
		List list = (List) this.doService("LvOrderService", "exportStateOrder", dto);
		ExcelWriter writer = new ExcelWriter();
		String path = request.getRealPath("/temp") + "/" + System.currentTimeMillis() + ".tmp";
		writer.create(path, list);
		File file = new File(path);
		excelStream = new BufferedInputStream(new FileInputStream(file));
		file.delete();
		
		if (logger.isInfoEnabled()) {
			logger.info("***** LvOrderManageAction.exportOrderStat() method end*****");
		}
		return "exportOrderStat";
	}
	
	/**
	 * @throws IOException 
	 * 
	 * @Method: exportProductStat 
	 * @Description:  [产品销售统计-导出excel表格]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-31 上午10:19:03]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-31 上午10:19:03]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String exportProductStat() throws IOException{
		if (logger.isInfoEnabled()) {
			logger.info("***** LvOrderManageAction.exportProductStat() method begin*****");
		}
		//删除文件上传临时文件夹下面所有临时文件
		deleteTempFile(request);
		
		dto.put("page",page);
		dto.put("ids",ids);
		dto.put("lvOrder", lvOrder);
		dto.put("lvProduct", lvProduct);
		List list = (List) this.doService("LvOrderService", "exportStateProduct", dto);
		ExcelWriter writer = new ExcelWriter();
		String path = request.getRealPath("/temp") + "/" + System.currentTimeMillis() + ".tmp";
		writer.create(path, list);
		File file = new File(path);
		excelStream = new BufferedInputStream(new FileInputStream(file));
		file.delete();

		if (logger.isInfoEnabled()) {
			logger.info("***** LvOrderManageAction.exportProductStat() method end*****");
		}
		return "exportProductStat";
	}
	
	
	
	/**
	 * 
	 * @Method: exportUserStat 
	 * @Description:  [用户订单统计-导出excel]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-31 上午11:13:40]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-31 上午11:13:40]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String exportUserStat() throws FileNotFoundException{
		if (logger.isInfoEnabled()) {
			logger.info("***** LvOrderManageAction.exportUserStat() method begin*****");
		}
		//删除文件上传临时文件夹下面所有临时文件
		deleteTempFile(request);
		
		dto.put("page",page);
		dto.put("ids",ids);
		dto.put("lvAccount", lvAccount);
		List list = (List) this.doService("LvOrderService", "exportUserOrder", dto);
		ExcelWriter writer = new ExcelWriter();
		String path = request.getRealPath("/temp") + "/" + System.currentTimeMillis() + ".tmp";
		writer.create(path, list);
		File file = new File(path);
		excelStream = new BufferedInputStream(new FileInputStream(file));
		file.delete();
		
		if (logger.isInfoEnabled()) {
			logger.info("***** LvOrderManageAction.exportUserStat() method end*****");
		}
		return "exportUserOrder";
	}
	
	/**
	 * 
	 * @Method: exportShopTypeState 
	 * @Description:  [导出商城商品分类统计数据]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-12-28 上午11:07:52]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-12-28 上午11:07:52]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws FileNotFoundException 
	 * @return String
	 */
	public String exportShopTypeState()throws FileNotFoundException{
		if (logger.isInfoEnabled()) {
			logger.info("***** LvOrderManageAction.exportShopTypeState() method begin*****");
		}
		//删除文件上传临时文件夹下面所有临时文件
		deleteTempFile(request);
		
		dto.put("lvOrder", lvOrder);
		dto.put("lvShopProductType", lvShopProductType);
		dto.put("page",page);
		dto.put("ids",ids);
		List list = (List) this.doService("LvOrderService", "exportShopTypeState", dto);
		ExcelWriter writer = new ExcelWriter();
		String path = request.getRealPath("/temp") + "/" + System.currentTimeMillis() + ".tmp";
		writer.create(path, list);
		File file = new File(path);
		excelStream = new BufferedInputStream(new FileInputStream(file));
		file.delete();
		
		if (logger.isInfoEnabled()) {
			logger.info("***** LvOrderManageAction.exportShopTypeState() method end*****");
		}
		return "exportShopTypeState";
	}
	/**
	 * 
	 * @Method: checkCouponCode 
	 * @Description:  [验证优惠码是否有效]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-12-22 下午04:48:38]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-12-22 下午04:48:38]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return 
	 * @return String
	 */
	public String checkCouponCode(){
		dto.put("storeFlag", this.getFlag());
		dto.put("couponCode", this.getRequest().getParameter("couponCode"));
		PrintWriter out = null;
		try {
			 String jsonTmp=null;
			 out=this.getResponse().getWriter();
			 dto=(Dto) doService("LvOrderService", "vaildCoupon", dto);
			 String couponFlag=dto.getAsString("couponFlag");
			 LvCoupon coupon=(LvCoupon)dto.get("lvCoupon");
			 LvCouponType couponType=(LvCouponType)dto.get("lvCouponType");
			 if(ObjectUtils.isNotEmpty(couponFlag)){
				 jsonTmp="{'couponFlag':"+couponFlag+"}";
			 }else{
				 if(ObjectUtils.isNotEmpty(couponType)){
					 SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			         String validatyDate=date.format(couponType.getEndTime());
					 jsonTmp="{'money':'"+couponType.getAmount()+"','validatydate':'"+validatyDate+"'}"; 
				 }
			 }			 
			 out.print(jsonTmp);
			 out.close();
		} catch (IOException e) {
		   e.getMessage();
		} finally{
			if(out!=null){
				   out.close();
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @Method: toProvince 
	 * @Description:  [根据国家查询洲/省信息返回前端]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-12-28 上午11:06:30]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-12-28 上午11:06:30]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 */
	public String toProvince(){
		PrintWriter out = null;
		if(ObjectUtils.isNotEmpty(countryId)){
			dto.put("countryId", countryId);
			List<LvArea> list=(List<LvArea>) this.doService("LvAreaService", "getProvince", dto);
			List listTmp=new ArrayList();
			Map<String,Object>  mapTmp=new HashMap<String,Object>();
			if(list!=null&&list.size()>0){
				for (int i = 0; i < list.size(); i++) {
					LvArea lvArea=list.get(i);
					Map<String,Object>  map=new HashMap<String,Object>();
					map.put("provinceName", lvArea.getNamecn());
					map.put("provinceId", lvArea.getId());
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
		}
	
		return null;
	}
	

	
	/**
	 * 
	 * @Method: toProvince 
	 * @Description:  [根据国家查询洲/省信息返回前端(EN)]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-12-28 上午11:06:30]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-12-28 上午11:06:30]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 */
	public String toProvinceEn(){
		PrintWriter out = null;
		if(ObjectUtils.isNotEmpty(countryId)){
			dto.put("countryId", countryId);
			List<LvArea> list=(List<LvArea>) this.doService("LvAreaService", "getProvince", dto);
			List listTmp=new ArrayList();
			Map<String,Object>  mapTmp=new HashMap<String,Object>();
			if(list!=null&&list.size()>0){
				for (int i = 0; i < list.size(); i++) {
					LvArea lvArea=list.get(i);
					Map<String,Object>  map=new HashMap<String,Object>();
					map.put("provinceName", lvArea.getNameen());
					map.put("provinceId", lvArea.getId());
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
		}
		
		return null;
	}
	
	

	/**
	 * 
	 * @Method: toStoreArea 
	 * @Description:  [根据国家编号查询店铺区域信息下的城市信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2013-2-25 上午10:41:45]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2013-2-25 上午10:41:45]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return 
	 * @return String
	 */
	public String toStoreArea(){
		PrintWriter out = null;
		dto.put("countryId", countryId);
		List<LvStoreArea> list=(List<LvStoreArea>) this.doService("LvStoreAreaService", "getStoreArea", dto);
		List listTmp=new ArrayList();
		Map<String,Object>  mapTmp=new HashMap<String,Object>();
		if(list!=null&&list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				LvStoreArea lvStoreArea=list.get(i);
				Map<String,Object>  map=new HashMap<String,Object>();
				map.put("provinceName", lvStoreArea.getAreaName());
				map.put("provinceCode",lvStoreArea.getCode());
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
	
	
	public String toBusShop(){
		PrintWriter out = null;
		dto.put("lvProduct", lvProduct);
		if(lvProduct!=null&&ObjectUtils.isNotEmpty(lvProduct.getStoreId())){
			//查询店铺产品
			List<LvProduct> list=(List<LvProduct>) this.doService("LvProductService", "getAllProduct", dto);
			List listTmp=new ArrayList();
			Map<String,Object>  mapTmp=new HashMap<String,Object>();
			if(list!=null&&list.size()>0){
				for (int i = 0; i < list.size(); i++) {
					LvProduct lvProduct=list.get(i);
					Map<String,Object>  map=new HashMap<String,Object>();
					map.put("productCode", lvProduct.getCode());
					map.put("productName", lvProduct.getProductName());
					listTmp.add(map);
				}
			}
			mapTmp.put("productList", listTmp);
			//查询店铺支付方式
			List listPayTmp=new ArrayList();
			dto.put("storeFlag", lvProduct.getStoreId());
			List<LvPaymentStyle> listPay=(List<LvPaymentStyle>) this.doService("LvPaymentStyleService", "findAllByStoreFlag", dto);
			if(list!=null&&listPay.size()>0){
				for (int i = 0; i < listPay.size(); i++) {
					LvPaymentStyle lvPaymentStyle=listPay.get(i);
					Map<String,Object>  map=new HashMap<String,Object>();
					map.put("payValue", lvPaymentStyle.getPayValue());
					map.put("payName", lvPaymentStyle.getPayName());
					listPayTmp.add(map);
				}
			}
			mapTmp.put("paymentStyleList", listPayTmp);
			//根据店铺查询币种
			String currency=Constants.STORE_TO_CURRENCY.get(lvProduct.getStoreId());
			mapTmp.put("currency", currency);
			
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
	 * 
	 * @Method: deleteTempFile 
	 * @Description:  [导出文件是删除不需要的临时文件]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-12-22 下午04:49:01]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-12-22 下午04:49:01]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param request 
	 * @return void
	 */
	public void deleteTempFile(HttpServletRequest request){
		//删除文件上传临时文件夹下面所有临时文件
		String pathTmp = request.getRealPath("/temp");
		File fileTemp=new File(pathTmp);
		if(fileTemp.isDirectory()){
			 File[] f=fileTemp.listFiles();
			 for (File fs : f) {
				boolean isFlag=fs.delete();
				if (logger.isInfoEnabled()) {
					logger.info("删除文件是否成功:"+fs.getName()+"->"+isFlag);
				}
			}
		}
	}
	
	
	public LvOrder getLvOrder() {
		return lvOrder;
	}

	public void setLvOrder(LvOrder lvOrder) {
		this.lvOrder = lvOrder;
	}

	public String getRelName() {
		return relName;
	}

	public void setRelName(String relName) {
		this.relName = relName;
	}

	public LvOrderAddress getLvOrderAddress() {
		return lvOrderAddress;
	}

	public void setLvOrderAddress(LvOrderAddress lvOrderAddress) {
		this.lvOrderAddress = lvOrderAddress;
	}

	public LvOrderDetails getLvOrderDetails() {
		return lvOrderDetails;
	}

	public void setLvOrderDetails(LvOrderDetails lvOrderDetails) {
		this.lvOrderDetails = lvOrderDetails;
	}

	public BufferedInputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(BufferedInputStream excelStream) {
		this.excelStream = excelStream;
	}

	public LvOrderComment getLvOrderComment() {
		return lvOrderComment;
	}

	public void setLvOrderComment(LvOrderComment lvOrderComment) {
		this.lvOrderComment = lvOrderComment;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOvertime() {
		return overtime;
	}

	public void setOvertime(String overtime) {
		this.overtime = overtime;
	}

	public LvAccount getLvAccount() {
		return lvAccount;
	}

	public void setLvAccount(LvAccount lvAccount) {
		this.lvAccount = lvAccount;
	}

	public void setOldStatus(Short oldStatus) {
		this.oldStatus = oldStatus;
	}

	public void setOldPayStatus(Short oldPayStatus) {
		this.oldPayStatus = oldPayStatus;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Short getOldStatus() {
		return oldStatus;
	}

	public Short getOldPayStatus() {
		return oldPayStatus;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public Date getVersionTime() {
		return versionTime;
	}

	public void setVersionTime(Date versionTime) {
		this.versionTime = versionTime;
	}

	public LvStore getLvStore() {
		return lvStore;
	}

	public void setLvStore(LvStore lvStore) {
		this.lvStore = lvStore;
	}

	public LvShopProductType getLvShopProductType() {
		return lvShopProductType;
	}

	public void setLvShopProductType(LvShopProductType lvShopProductType) {
		this.lvShopProductType = lvShopProductType;
	}

	public LvProduct getLvProduct() {
		return lvProduct;
	}

	public void setLvProduct(LvProduct lvProduct) {
		this.lvProduct = lvProduct;
	}

	public String getTargetFlag() {
		return targetFlag;
	}

	public void setTargetFlag(String targetFlag) {
		this.targetFlag = targetFlag;
	}

	public File getExcelFile() {
		return excelFile;
	}

	public void setExcelFile(File excelFile) {
		this.excelFile = excelFile;
	}

	public String getExcelFileFileName() {
		return excelFileFileName;
	}

	public void setExcelFileFileName(String excelFileFileName) {
		this.excelFileFileName = excelFileFileName;
	}

	public String getProgressName() {
		return progressName;
	}

	public void setProgressName(String progressName) {
		this.progressName = progressName;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

}
