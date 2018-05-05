package com.lshop.manage.lvOrder.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.exception.DAOException;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvArea;
import com.lshop.common.pojo.logic.LvOrder;
import com.lshop.common.pojo.logic.LvOrderAddress;
import com.lshop.common.pojo.logic.LvOrderDetails;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.pojo.logic.LvPubProduct;
import com.lshop.common.pojo.user.LvAccount;
import com.lshop.common.util.CodeUtils;
import com.lshop.common.util.Constants;
import com.lshop.common.util.DateUtils;
import com.lshop.common.util.StringUtil;
import com.lshop.manage.lvAccount.service.LvAccountService;
import com.lshop.manage.lvArea.service.LvAreaService;
import com.lshop.manage.lvOrder.service.LvOrderAddressService;
import com.lshop.manage.lvOrder.service.LvOrderDetailsService;
import com.lshop.manage.lvOrder.service.LvOrderService;
import com.lshop.manage.lvOrder.service.OrderImportTaskService;
import com.lshop.manage.lvOrder.util.OrderConstants;
import com.lshop.manage.lvOrder.vo.OrderImport;
import com.lshop.manage.lvProduct.service.LvProductService;
import com.lshop.manage.lvPubProduct.service.LvPubProductService;

@Service("OrderImportTaskService")
public class OrderImportTaskServiceImpl implements OrderImportTaskService {
	private static final Log logger	= LogFactory.getLog(LvOrderServiceImpl.class);
	@Resource 
	private HibernateBaseDAO dao;
	@Resource
	private LvOrderService lvOrderService;
	@Resource 
	private LvOrderDetailsService lvOrderDetailsService;
	@Resource
	private LvOrderAddressService lvOrderAddressService;
	@Resource
	private LvProductService lvProductService;
	@Resource
	private LvPubProductService lvPubProductService;
	@Resource
	private LvAreaService lvAreaService;
	@Resource
	private LvAccountService lvAccountService;

	/**
	 * 
	 * @Method: saveOrderImport 
	 * @Description:  [批量导入保存订单程序入口]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2015-3-20 下午5:14:15]   
	 * @UpdateUser:   [liaoxj]     
	 * @UpdateDate:   [2015-3-20 下午5:14:15]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param orderImport 
	 * @return void
	 */
	public String saveOrderImport(OrderImport orderImport)throws Exception{
		if (logger.isInfoEnabled()){
			logger.info("***** OrderImportTaskService.saveOrderImport() method begin*****");
		}
        //根据规则生成订单号
		String orderId=null;
		try {
			orderId = lvOrderService.createOrderNum("third", "B");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//订单号
		
		//根据email查询用户信息
		BaseDto dto=new BaseDto();
		dto.put("userEmail",orderImport.getCustomerEmail());
		LvAccount account= lvAccountService.getAccountByEmail(dto);
		
		
		
		/**
		 * 数据验证
		 */
		Boolean isFlag=true;
		//验证当前订单是否已经存在
		LvOrder lvOrder=lvOrderService.getOrderByThridNum(orderImport.getOrderNumber());
		if(ObjectUtils.isNotEmpty(lvOrder)){
			orderId=lvOrder.getOid();
			isFlag=false;
		}
		
		
		
		String result="";
		/**
		 * 一、保存订单信息
		 */
		if(isFlag){
			result=saveOrder(orderImport, orderId, account);
			if(!(result.equals(OrderConstants.ORDER_IMPORT_ERROR_10000)||result.equals(OrderConstants.ORDER_IMPORT_ERROR_10001))){
				return result;
			}
		}
		/**
		 * 二、保存订单详情
		 */
		result=saveOrderDetails(orderImport, orderId);
		if(!result.equals(OrderConstants.ORDER_IMPORT_ERROR_20000)){
			return result;
		}
		/**
		 * 三、保存订单地址信息
		 */
		result=saveOrderAddress(orderImport, orderId, account);
		if(!(result.equals(OrderConstants.ORDER_IMPORT_ERROR_30000)||result.equals(OrderConstants.ORDER_IMPORT_ERROR_30001))){
			return result;
		}
        /**
         * 四、后台订单部分添加订单日志信息：
         */
		lvOrderService.saveOrderLogs(orderImport.getOpreate(), orderId, "第三方订单导入", "thrid");
		
		
		if (logger.isInfoEnabled()){
			logger.info("***** OrderImportTaskService.saveOrderImport() method end*****");
		}
		return OrderConstants.ORDER_IMPORT_ERROR_00000;
	}


	private String saveOrder(OrderImport orderImport, String orderId,LvAccount account)throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** OrderImportTaskService.saveOrder() method begin*****");
		}

		
		/**
		 * 数据组装
		 */
		LvOrder order=new LvOrder();
		order.setOid(orderId);
		order.setCouponNum(0);
		order.setPayStatus((short)1);
		order.setStatus((short)0);
		if(ObjectUtils.isNotEmpty(account)){
			order.setMemid(account.getId());
			order.setUserEmail(account.getEmail());
		}else{
			order.setUserEmail(orderImport.getCustomerEmail());
		}
		
		if(orderImport.getOrderPaymentMethod().equals("1")){
			order.setPaymethod(Constants.PAY_METHOD_PAYPAL);
		}else if(orderImport.getOrderPaymentMethod().equals("2")){
			order.setPaymethod(Constants.PAY_METHOD_PAYDOLLAR);
		}
		order.setOrderRemark(orderImport.getOrderRemark());
		order.setSendRemark(orderImport.getOrderRemark());
		if(ObjectUtils.isNotEmpty(orderImport.getOrderPayDate())){
			Date payDate=DateUtils.parseDateTime(orderImport.getOrderPayDate(), "yyyy-MM-dd HH:mm");
			order.setOvertime(payDate);
		}else{
			order.setOvertime(new Date());
		}
		order.setPostprice(orderImport.getOrderShip());
		order.setTotalPrice(orderImport.getOrderGrandTotal());
		order.setCouponTotalPrice(0.0f);
		order.setThirdPartyOrder(orderImport.getOrderPayNumber());
		order.setThirdOrderNum(orderImport.getOrderNumber());
		order.setThirdOrderSource(orderImport.getOrderSources());
		order.setCurrency(orderImport.getCurrency());
		order.setIsdelete((short)0);
		order.setIsFinanceAudit(0);
		order.setIsServiceAudit((short)0);
		order.setFlag(1);
		order.setIsBalance((short)0);
		order.setIsBalanceFirst((short)0);
		order.setIsSyncSas((short)0);
		order.setIsGift((short)0);
		order.setStoreId("third");
		order.setCode(CodeUtils.getCode());
		if(ObjectUtils.isNotEmpty(orderImport.getOrderDate())){
			String createTimeString=orderImport.getOrderDate();
			Date createTime=DateUtils.parseDateTime(createTimeString, "yyyy-MM-dd HH:mm");
			order.setCreateTime(createTime);
		}else{
			order.setCreateTime(new Date());
		}
		/**
		 * 保存订单信息
		 */
		dao.save(order);
		
		if (logger.isInfoEnabled()){
			logger.info("***** OrderImportTaskService.saveOrder() method end*****");
		}
		return OrderConstants.ORDER_IMPORT_ERROR_10000;
	}


	private String saveOrderDetails(OrderImport orderImport, String orderId) throws ServiceException{
		if (logger.isInfoEnabled()){
			logger.info("***** OrderImportTaskService.saveOrderDetails() method begin*****");
		}
		
		/**
		 * 数据验证
		 */		
        String productCode="";
		//根据对接的产品编码查找第三方店铺产品编码信息
		LvPubProduct lvPubProduct= lvPubProductService.findByPcode(orderImport.getItemSKU());
		if(ObjectUtils.isNotEmpty(lvPubProduct)){
			BaseDto dto=new BaseDto();
			dto.put("pubProductCode", lvPubProduct.getCode());
			dto.put("storeId", "third");
			LvProduct lvProduct=lvProductService.findByPubProductCode(dto);
			if(ObjectUtils.isNotEmpty(lvProduct)){
				//查找当前商品是否已经存在订单详情
				Boolean isFlag= lvOrderDetailsService.isExistsByProductCode(lvProduct.getCode(), orderImport.getOrderNumber());
				if(isFlag){
					return OrderConstants.ORDER_IMPORT_ERROR_20003;
				}
				productCode=lvProduct.getCode();
				
			}else{
				return OrderConstants.ORDER_IMPORT_ERROR_20002;
			}
		}else{
			return OrderConstants.ORDER_IMPORT_ERROR_20001;
		}
		
		
		
		/**
		 * 数据组装
		 */
		LvOrderDetails orderDetails=new LvOrderDetails();
		orderDetails.setOrderId(orderId);
		orderDetails.setProductCode(productCode);//店铺产品编码
		orderDetails.setOprice(orderImport.getItemPrice());
		orderDetails.setPnum(orderImport.getItemQty());
		orderDetails.setPostPrice(orderImport.getOrderShip());
		orderDetails.setCurrency(orderImport.getCurrency());
		orderDetails.setIsDelete(0);
		orderDetails.setIsPackage(0);
		orderDetails.setStoreId("third");
		String detailsCode=CodeUtils.getCode();
		orderDetails.setCode(detailsCode);
		dao.save(orderDetails);
		
		if (logger.isInfoEnabled()){
			logger.info("***** OrderImportTaskService.saveOrderDetails() method end*****");
		}
		return OrderConstants.ORDER_IMPORT_ERROR_20000;
	}


	private String saveOrderAddress(OrderImport orderImport,String orderId, LvAccount account) throws ServiceException{
		if (logger.isInfoEnabled()){
			logger.info("***** OrderImportTaskService.saveOrderAddress() method begin*****");
		}
		/**
		 * 数据验证
		 */	
		
		//验证当前订单地址信息是否已经存在
		Boolean isFlag= lvOrderAddressService.isExistsByThirdOrderNum(orderImport.getOrderNumber());
		if(isFlag){
			return OrderConstants.ORDER_IMPORT_ERROR_30001;
		}
		
		//验证当前国家区域信息是否正确
		//根据国家code查询国家id
		LvArea countryArea= lvAreaService.findCountryByCode(orderImport.getCountry());
		if(ObjectUtils.isEmpty(countryArea)){
			return OrderConstants.ORDER_IMPORT_ERROR_30002;
		}
		
		
		
		
		/**
		 * 数据组装
		 */
		LvOrderAddress orderAddress=new LvOrderAddress();
		orderAddress.setOrderId(orderId);
		if(ObjectUtils.isNotEmpty(account)){
			orderAddress.setRelCode(account.getCode());
		}
		orderAddress.setRelName(orderImport.getShippingName());
		orderAddress.setPostCode(orderImport.getZip());
		orderAddress.setMobile(orderImport.getPhoneNumber());
		orderAddress.setBestDeliveryTime(1);
		orderAddress.setContryId(countryArea.getId());
		orderAddress.setContryName(countryArea.getNameen());
		
		//获取区域省份信息
		LvArea priviceArea = findProvince(orderImport, countryArea.getId());
		if(ObjectUtils.isNotEmpty(priviceArea)){
			orderAddress.setProvinceId(priviceArea.getId());
			orderAddress.setProvinceName(priviceArea.getNameen());
		}else{
			orderAddress.setProvinceId(null);
			orderAddress.setProvinceName(orderImport.getStateName());
		}
		orderAddress.setCityName(orderImport.getCity());
		orderAddress.setAdress(orderImport.getStreet());
		orderAddress.setStoreId("thrid");
		orderAddress.setCode(CodeUtils.getCode());
		if(ObjectUtils.isNotEmpty(orderImport.getOrderDate())){
			Date createTime=DateUtils.parseDateTime(orderImport.getOrderDate(), "yyyy-MM-dd HH:mm");
			orderAddress.setCreateTime(createTime);
		}else{
			orderAddress.setCreateTime(new Date());
		}
		dao.save(orderAddress);
		
		
		if (logger.isInfoEnabled()){
			logger.info("***** OrderImportTaskService.saveOrderAddress() method end*****");
		}
		return OrderConstants.ORDER_IMPORT_ERROR_30000;
	}


	/**
	 * 
	 * @Method: findProvince 
	 * @Description:  [获取区域省份信息]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2015-4-1 下午2:18:43]   
	 * @UpdateUser:   [liaoxj]     
	 * @UpdateDate:   [2015-4-1 下午2:18:43]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param orderImport
	 * @param countryId 
	 * @return LvArea
	 */
	private LvArea findProvince(OrderImport orderImport, Integer countryId) {
		//根据省份code查找州省信息
		LvArea priviceArea =lvAreaService.findProvinceByCode(countryId, orderImport.getState());
		if(ObjectUtils.isEmpty(priviceArea)){
			//根据省份名称查找省份信息
			priviceArea=lvAreaService.findProvinceByName(countryId, orderImport.getStateName());
		}
		return priviceArea;
	}
	
	
	/**
	 * 
	 * @Method: buildOrderData 
	 * @Description:  [组装excel解析的订单数据]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2015-3-20 下午4:25:58]   
	 * @UpdateUser:   [liaoxj]     
	 * @UpdateDate:   [2015-3-20 下午4:25:58]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param importList 表格数据
	 * @return List<OrderImport> 组装订单
	 */
	public List<OrderImport> buildOrderData(List<String []> importList){
		List<OrderImport> orderList=new ArrayList<OrderImport>();
		if(ObjectUtils.isNotEmpty(importList)){
			  for (int i = 0; i < importList.size(); i++) {
		        	String[] orderArr= importList.get(i);
		        	OrderImport order=new OrderImport();
		        	/**
		        	 * 关键数据验证
		        	 */
		        	if(ObjectUtils.isEmpty(orderArr[0])){
		        		order.setErrorCode(OrderConstants.ORDER_IMPORT_ERROR_00001);
		        		orderList.add(order);
		        		continue;
		        	}else if(orderArr[0].length()>32){
		        		
		        	}
		        	order.setOrderNumber(orderArr[0]);
		        	
		        	if(ObjectUtils.isEmpty(orderArr[1])){
		        		order.setErrorCode(OrderConstants.ORDER_IMPORT_ERROR_00002);
		        		orderList.add(order);
		        		continue;
		        	}
		        	if(ObjectUtils.isEmpty(orderArr[3])){
		        		order.setErrorCode(OrderConstants.ORDER_IMPORT_ERROR_00003);
		        		orderList.add(order);
		        		continue;
		        	}else{
		        		if(!(orderArr[3].equals("1")||orderArr[3].equals("2"))){
		        			order.setErrorCode(OrderConstants.ORDER_IMPORT_ERROR_00004);
		        			orderList.add(order);
		        			continue;
		        		}
		        	}
		        	if(ObjectUtils.isEmpty(orderArr[5])){
		        		order.setErrorCode(OrderConstants.ORDER_IMPORT_ERROR_00005);
		        		orderList.add(order);
		        		continue;
		        	}
		        	if(ObjectUtils.isEmpty(orderArr[7])){
		        		order.setErrorCode(OrderConstants.ORDER_IMPORT_ERROR_00006);
		        		orderList.add(order);
		        		continue;
		        	}
		        	if(ObjectUtils.isEmpty(orderArr[10])){
		        		order.setErrorCode(OrderConstants.ORDER_IMPORT_ERROR_00007);
		        		orderList.add(order);
		        		continue;
		        	}
		        	if(ObjectUtils.isEmpty(orderArr[11])){
		        		order.setErrorCode(OrderConstants.ORDER_IMPORT_ERROR_00008);
		        		orderList.add(order);
		        		continue;
		        	}
		        	if(ObjectUtils.isEmpty(orderArr[12])){
		        		order.setErrorCode(OrderConstants.ORDER_IMPORT_ERROR_00009);
		        		orderList.add(order);
		        		continue;
		        	}
		        	if(ObjectUtils.isEmpty(orderArr[13])){
		        		order.setErrorCode(OrderConstants.ORDER_IMPORT_ERROR_00010);
		        		orderList.add(order);
		        		continue;
		        	}
		        	if(ObjectUtils.isEmpty(orderArr[14])){
		        		order.setErrorCode(OrderConstants.ORDER_IMPORT_ERROR_00011);
		        		orderList.add(order);
		        		continue;
		        	}
		           	if(ObjectUtils.isEmpty(orderArr[16])){
		        		order.setErrorCode(OrderConstants.ORDER_IMPORT_ERROR_00012);
		        		orderList.add(order);
		        		continue;
		        	}
		          	if(ObjectUtils.isEmpty(orderArr[17])){
		        		order.setErrorCode(OrderConstants.ORDER_IMPORT_ERROR_00013);
		        		orderList.add(order);
		        		continue;
		        	}
		
		          	if(ObjectUtils.isEmpty(orderArr[21])){
		        		order.setErrorCode(OrderConstants.ORDER_IMPORT_ERROR_00014);
		        		orderList.add(order);
		        		continue;
		        	}
		         	if(ObjectUtils.isEmpty(orderArr[22])){
		        		order.setErrorCode(OrderConstants.ORDER_IMPORT_ERROR_00015);
		        		orderList.add(order);
		        		continue;
		        	}
		         	if(ObjectUtils.isEmpty(orderArr[23])){
		        		order.setErrorCode(OrderConstants.ORDER_IMPORT_ERROR_00016);
		        		orderList.add(order);
		        		continue;
		        	}
		         	String orderPayNumber="";
		         	if(ObjectUtils.isEmpty(orderArr[26])){
//		        		order.setErrorCode(OrderConstants.ORDER_IMPORT_ERROR_00017);
//		        		orderList.add(order);
//		        		continue;
		        	}else{
		        		orderPayNumber=String.valueOf(orderArr[26]);
		        	}
		         	if(ObjectUtils.isEmpty(orderArr[27])){
		        		order.setErrorCode(OrderConstants.ORDER_IMPORT_ERROR_00018);
		        		orderList.add(order);
		        		continue;
		        	}
		         	if(ObjectUtils.isEmpty(orderArr[28])){
		        		order.setErrorCode(OrderConstants.ORDER_IMPORT_ERROR_00019);
		        		orderList.add(order);
		        		continue;
		        	}
		         	if(ObjectUtils.isEmpty(orderArr[19])){
		        		order.setErrorCode(OrderConstants.ORDER_IMPORT_ERROR_00020);
		        		orderList.add(order);
		        		continue;
		        	}
		        	
		        	/**
		        	 * 数据组装
		        	 */
		         	order.setErrorCode(OrderConstants.ORDER_IMPORT_ERROR_00000);
	        		order.setOrderDate(orderArr[1]);
                    order.setOrderStatus(orderArr[2]);
	        		order.setOrderPaymentMethod(orderArr[3]);
	        		order.setOrderShippingMethod(orderArr[4]);
	        		if(ObjectUtils.isNotEmpty(orderArr[5])){
	        		    //判断是否包含特殊字符，如果存在替换掉
	        			String result= StringUtil.removeSpecialChar(orderArr[5].toString(), "$");
	        			result= StringUtil.removeSpecialChar(result, ",");
	        			if(StringUtil.isFloatPointNumber(result)){
	        				order.setOrderShip(Float.valueOf(result));
	        			}else{
	        				order.setErrorCode(OrderConstants.ORDER_IMPORT_ERROR_00021);
			        		orderList.add(order);
			        		continue;
	        			}
	        			
	        		}
	        		//Order Discount
	        		if(ObjectUtils.isNotEmpty(orderArr[7])){
	        			String result= StringUtil.removeSpecialChar(orderArr[7].toString(), "$");
	        			result= StringUtil.removeSpecialChar(result, ",");
	        			if(StringUtil.isFloatPointNumber(result)){
	        				order.setOrderGrandTotal(Float.valueOf(result));
	        			}else{
	        				order.setErrorCode(OrderConstants.ORDER_IMPORT_ERROR_00022);
			        		orderList.add(order);
			        		continue;
	        			}
	        		}
	        		//Total Qty Items Ordered
	        		order.setCustomerName(orderArr[9]);
	        		order.setCustomerEmail(orderArr[10]);
	        		order.setShippingName(orderArr[11]);
	        		order.setStreet(orderArr[12]);
	        		order.setZip(orderArr[13]);
	        		order.setCity(orderArr[14]);
	        		order.setState(orderArr[15]);
	        		order.setStateName(orderArr[16]);
	        		order.setCountry(orderArr[17]);
	        		order.setCountryName(orderArr[18]);
	        		order.setPhoneNumber(orderArr[19]);
	        		order.setItemName(orderArr[20]);
	        		order.setItemSKU(orderArr[21]);
	        		if(ObjectUtils.isNotEmpty(orderArr[22])){
	        			String result= StringUtil.removeSpecialChar(orderArr[22].toString(), "$");
	        			result= StringUtil.removeSpecialChar(result, ",");
	        			if(StringUtil.isFloatPointNumber(result)){
	        				order.setItemPrice(Float.valueOf(result));
	        			}else{
	        				order.setErrorCode(OrderConstants.ORDER_IMPORT_ERROR_00023);
			        		orderList.add(order);
			        		continue;
	        			}
	        		}
	        		if(ObjectUtils.isNotEmpty(orderArr[23])){
	        			order.setItemQty(Integer.parseInt(orderArr[23].toString()));
	        		}
	        		if(ObjectUtils.isNotEmpty(orderArr[24])){
	        			String result= StringUtil.removeSpecialChar(orderArr[24].toString(), "$");
	        			result= StringUtil.removeSpecialChar(result, ",");
	        			if(StringUtil.isFloatPointNumber(result)){
	        				order.setItemTotal(Float.valueOf(result));
	        			}else{
	        				order.setErrorCode(OrderConstants.ORDER_IMPORT_ERROR_00024);
			        		orderList.add(order);
			        		continue;
	        			}
	        		}
	        		order.setOrderRemark(orderArr[25]);
	        		order.setOrderPayNumber(orderPayNumber);
	        		order.setCurrency(orderArr[27]);
	        		if(ObjectUtils.isNotEmpty(orderArr[28])){
	        			order.setOrderSources(Short.parseShort(orderArr[28].toString()));	 
	        		}
                    orderList.add(order);
				}
		}
		return orderList;
	}
}
