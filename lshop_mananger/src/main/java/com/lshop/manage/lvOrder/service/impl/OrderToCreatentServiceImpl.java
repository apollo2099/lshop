package com.lshop.manage.lvOrder.service.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvOrderCoupon;
import com.lshop.common.pojo.logic.LvOrderDetails;
import com.lshop.common.pojo.logic.LvOrderPackageDetails;
import com.lshop.common.pojo.logic.LvPatternCountry;
import com.lshop.common.pojo.logic.LvPubProduct;
import com.lshop.common.pojo.user.LvAccount;
import com.lshop.common.util.DateUtils;
import com.lshop.manage.lvAccount.service.LvAccountService;
import com.lshop.manage.lvOrder.service.LvOrderCouponService;
import com.lshop.manage.lvOrder.service.LvOrderDetailsService;
import com.lshop.manage.lvOrder.service.LvOrderGiftService;
import com.lshop.manage.lvOrder.service.LvOrderService;
import com.lshop.manage.lvOrder.service.OrderToCreatentService;
import com.lshop.manage.lvPatternCountry.service.LvPatternCountryService;
import com.lshop.manage.lvPubProduct.service.LvPubProductService;
import com.lshop.ws.web.creatent.order.ThreeOrderDto;
import com.lshop.ws.web.creatent.order.ThreeOrderItemDto;
import com.lshop.ws.web.creatent.order.WSThreeOrderService;

@Service("OrderToCreatentService")
public class OrderToCreatentServiceImpl implements OrderToCreatentService {
	
	private static final Log logger = LogFactory.getLog(OrderToCreatentServiceImpl.class);
	@Resource
	private HibernateBaseDAO dao;
	@Resource
	private LvOrderService lvOrderService;
	@Resource
	private LvPubProductService lvPubProductService;
	@Resource
	private LvAccountService lvAccountService;
	@Resource
	private LvOrderCouponService lvOrderCouponService;
	@Resource
	private LvOrderGiftService lvOrderGiftService;
	@Resource
	private LvOrderDetailsService lvOrderDetailsService;
	@Resource
	private LvPatternCountryService lvPatternCountryService;//规格和地区关联服务
	@Resource
	private WSThreeOrderService threeOrderService;//代理商订单接口服务
	
	
	
	public boolean sendOrderMSGToCreatent(String orderNum){
		if (logger.isInfoEnabled()){
			logger.info("***** OrderToCreatentServiceImpl.sendOrderMSGToCreatent() method begin*****");
			logger.info("***** OrderToCreatentServiceImpl.sendOrderMSGToCreatent() B2C销售订单号orderNum="+orderNum);
		}
		ThreeOrderDto threeOrder=initThreeOrderData(orderNum);
		
		try {
			//调用启创新增销售订单接口程序
			com.lshop.ws.web.creatent.order.Result result=threeOrderService.saveOrder(threeOrder);
			if(result.getStatus()==1){
				
				if (logger.isInfoEnabled()){
					logger.info("***** OrderToCreatentServiceImpl.sendOrderMSGToCreatent() method end*****");
					logger.info("***** OrderToCreatentServiceImpl.sendOrderMSGToCreatent() 同步启创成功,B2C销售订单号orderNum="+orderNum);
				}
				//修改订单同步状态
				lvOrderService.updateSyncSasFlag(orderNum, (short)1);
				return true;
			}else if(result.getStatus()==2){
				if (logger.isInfoEnabled()){
					logger.info("***** OrderToCreatentServiceImpl.sendOrderMSGToCreatent() method end*****");
					logger.info("***** OrderToCreatentServiceImpl.sendOrderMSGToCreatent() 同步启创失败,B2C销售订单号已经在启创中存在orderNum="+orderNum);
				}
				//修改订单同步状态
				lvOrderService.updateSyncSasFlag(orderNum, (short)1);
				return true;
			}else{
				if (logger.isInfoEnabled()){
					logger.info("***** OrderToCreatentServiceImpl.sendOrderMSGToCreatent() method end*****");
					logger.info("***** OrderToCreatentServiceImpl.sendOrderMSGToCreatent() 同步启创失败 ,B2C销售订单号orderNum="+orderNum);
				}
				//修改订单同步状态
				//lvOrderService.updateSyncSasFlag(orderNum, (short)0);
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.info("***** OrderToCreatentServiceImpl.sendOrderMSGToCreatent() 同步启创异常,B2C销售订单号orderNum="+orderNum);
			logger.error("***** OrderToCreatentServiceImpl.sendOrderMSGToCreatent() 同步启创异常=>"+e.getMessage());
			return false;
		}
	}
	
	
	public ThreeOrderDto initThreeOrderData(String orderNum){
		if (logger.isInfoEnabled()){
			logger.info("***** OrderToCreatentServiceImpl.initThreeOrderData() method begin*****");
		}
		ThreeOrderDto threeOrder=new ThreeOrderDto();
		String hql = "SELECT lvorder.oid AS oid,lvOrderAdress.relName AS relname,la.code as contryCode ,lvOrderAdress.contryId as contryId," +
				" lvOrderAdress.contryName AS contryname,lvOrderAdress.provinceName AS provincename,lvOrderAdress.provinceId as provinceId," +
				" lvOrderAdress.cityName AS cityname,lvOrderAdress.cityId as cityId," +
				" lvOrderAdress.adress AS adress,lvOrderAdress.postCode AS postcode,lvOrderAdress.tel AS tel,lvOrderAdress.mobile AS mobile, " +
				" lvorder.userEmail AS email, lvorder.postprice AS postage, lvorder.totalPrice AS totalPrice," +
				" lvorder.paymethod AS paymethod,lvorder.overtime AS overtime,lvorder.thirdPartyOrder as paynumber,lvorder.storeId as source, " +
				" lvorder.orderRemark AS oremark,lvorder.createTime AS createtime,lvorder.isGift as isGift," +
				" lvorder.sendRemark AS sendremark,'' AS description,lvorder.currency as currency " +
				"FROM LvOrder AS lvorder,LvOrderAddress AS lvOrderAdress,LvArea as la  " +
				"WHERE   lvOrderAdress.orderId=lvorder.oid  " +
				"AND lvOrderAdress.contryId=la.id  " +
				"AND lvorder.oid='"+ orderNum + "'";
		List list = dao.getMapListByHql(hql, 1, 1, null).getList();
		if (list != null && !list.isEmpty()) {
			
			
			Map resultMap = (Map) list.get(0);
			if(ObjectUtils.isNotEmpty(resultMap.get("oid"))){//订单号
				threeOrder.setOrderNum(String.valueOf(resultMap.get("oid")));
			}
			if(ObjectUtils.isNotEmpty(resultMap.get("relname"))){//收货人名称
				threeOrder.setRelname(String.valueOf(resultMap.get("relname")));
			}
			if(ObjectUtils.isNotEmpty(resultMap.get("contryId"))){//国家ID
				threeOrder.setContryId(Integer.parseInt(resultMap.get("contryId").toString()));
			}
			if(ObjectUtils.isNotEmpty(resultMap.get("contryCode"))){//国家CODE
				threeOrder.setContryCode(String.valueOf(resultMap.get("contryCode")));	
			}
			if(ObjectUtils.isNotEmpty(resultMap.get("contryname"))){//国家名称
				threeOrder.setContryname(String.valueOf(resultMap.get("contryname")));
			}
			if(ObjectUtils.isNotEmpty(resultMap.get("provinceId"))){//省份id
				threeOrder.setProvinceId(Integer.parseInt(resultMap.get("provinceId").toString()));
			}
			if(ObjectUtils.isNotEmpty(resultMap.get("provincename"))){//省份名称
				threeOrder.setProvincename(String.valueOf(resultMap.get("provincename")));
			}
			if(ObjectUtils.isNotEmpty(resultMap.get("cityId"))){//城市id
				threeOrder.setCityId(Integer.parseInt(resultMap.get("cityId").toString()));
			}
			if(ObjectUtils.isNotEmpty(resultMap.get("cityname"))){//城市名称
				threeOrder.setCityname(String.valueOf(resultMap.get("cityname")));
			}
			if(ObjectUtils.isNotEmpty(resultMap.get("adress"))){//详细地址
				threeOrder.setAdress(String.valueOf(resultMap.get("adress")));
			}
			if(ObjectUtils.isNotEmpty(resultMap.get("postcode"))){//邮编
				threeOrder.setPostcode(String.valueOf(resultMap.get("postcode")));
			}
			if(ObjectUtils.isNotEmpty(resultMap.get("tel"))){//电话
				threeOrder.setTel(String.valueOf(resultMap.get("tel")));
			}
			if(ObjectUtils.isNotEmpty(resultMap.get("mobile"))){//手机
				threeOrder.setMobile(String.valueOf(resultMap.get("mobile")));
			}
			if(ObjectUtils.isNotEmpty(resultMap.get("email"))){//用户Email
				threeOrder.setEmail(String.valueOf(resultMap.get("email")));
				
				BaseDto dto=new BaseDto();
				dto.put("userEmail", String.valueOf(resultMap.get("email")));
				//根据用户email查询用户信息
				LvAccount lvAccount= lvAccountService.getAccountByEmail(dto);
				if(ObjectUtils.isNotEmpty(lvAccount)){
					if(ObjectUtils.isNotEmpty(lvAccount.getCode())){//用户CODE
						threeOrder.setUserCode(lvAccount.getCode());
					}
				}
			}
			
			if(ObjectUtils.isNotEmpty(resultMap.get("postage"))){//运费
				threeOrder.setPostage(Float.valueOf(resultMap.get("postage").toString()));
			}
			if(ObjectUtils.isNotEmpty(resultMap.get("totalPrice"))){//订单总金额
				threeOrder.setTotalPrice(Float.valueOf(resultMap.get("totalPrice").toString()));
			}
			if(ObjectUtils.isNotEmpty(resultMap.get("paymethod"))){//支付方式
				threeOrder.setPaymethod(Integer.valueOf(resultMap.get("paymethod").toString()));
			}
			if(ObjectUtils.isNotEmpty(resultMap.get("overtime"))){//支付时间
				Date overtime = (Date) resultMap.get("overtime");
				threeOrder.setPayTime(DateUtils.formatDate(overtime, "yyyy-MM-dd HH:mm:ss"));
			}
			if(ObjectUtils.isNotEmpty(resultMap.get("paynumber"))){//支付单号
				threeOrder.setPaynumber(String.valueOf(resultMap.get("paynumber")));
			}
			if(ObjectUtils.isNotEmpty(resultMap.get("source"))){//订单来源（1ITvpad商城,2Banana商城,3其他渠道）
				String source=(String) resultMap.get("source");
				if(source.equals("tvpadcn")||source.equals("tvpaden")||source.equals("mtscn")||source.equals("third")){
					threeOrder.setSource("1");
				}else if(source.equals("bscn")||source.equals("bsen")||source.equals("mbscn")){
					threeOrder.setSource("2");
				}
			}
			if(ObjectUtils.isNotEmpty(resultMap.get("createtime"))){//下单时间
				threeOrder.setCreatetime(String.valueOf(resultMap.get("createtime")));
			}
			if(ObjectUtils.isNotEmpty(resultMap.get("oremark"))){//订单备注
				threeOrder.setOremark(String.valueOf(resultMap.get("oremark")));;
			}
			if(ObjectUtils.isNotEmpty(resultMap.get("sendremark"))){//发货备注
				threeOrder.setSendremark(String.valueOf(resultMap.get("sendremark")));
			}
			if(ObjectUtils.isNotEmpty(resultMap.get("currency"))){//币种
				threeOrder.setCurrency(String.valueOf(resultMap.get("currency")));
			}
			
			//根据订单号查询订单优惠券关联信息[暂时只是处理1:1的情况]
			initOrderCoupon(orderNum, threeOrder);

			//根据国家编号查询对应的规格信息
			Short patternCode=0;
			LvPatternCountry lvPatternCountry= lvPatternCountryService.findByCountryId(threeOrder.getContryId());
			if(ObjectUtils.isNotEmpty(lvPatternCountry)&&ObjectUtils.isNotEmpty(lvPatternCountry.getPatternCode())){
				patternCode=lvPatternCountry.getPatternCode();
			}
			
			List<ThreeOrderItemDto> orderItems=threeOrder.getOrderItems();
			//根据订单号查询订单详情
			List<LvOrderDetails> orderDetailsList=lvOrderDetailsService.getOrderDetails(orderNum);
			for(LvOrderDetails orderDetails : orderDetailsList){//遍历订单map信息
				if(orderDetails!=null){
					if(ObjectUtils.isNotEmpty(orderDetails.getIsPackage())&&orderDetails.getIsPackage()==1){//为套餐
						//拆分套餐明细,组装订单商品信息
						initOrderPackageItem(orderItems, orderDetails,patternCode);
					}else{//非套餐
						//组装订单商品信息
						initOrderItem(orderItems, orderDetails,patternCode);
					}
				}
			}
			
			//组装订单赠品信息
			if(ObjectUtils.isNotEmpty(resultMap.get("isGift"))){
			   Short isGift=(Short) resultMap.get("isGift");
			   initOrderGiftItem(orderNum, isGift, orderItems,patternCode);
			}
		}

		if (logger.isInfoEnabled()){
			logger.info("***** OrderToCreatentServiceImpl.initThreeOrderData() method end*****");
		}
		return threeOrder;
	}


	/**
	 * 
	 * @Method: initOrderItem 
	 * @Description:  [组装商品信息(非套餐时)
	 *                 数据格式："pcode":"产品型号Code","oprice":"单价","pnum":"数量"  ]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-8 下午5:41:45]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-8 下午5:41:45]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param orderItems 商品集合数据
	 * @param orderDetails 订单商品详情
	 * @param patternCode 规格
	 * @return void
	 */
	private void initOrderItem(List<ThreeOrderItemDto> orderItems,LvOrderDetails orderDetails,Short patternCode) {
		if (logger.isInfoEnabled()){
			logger.info("***** OrderToCreatentServiceImpl.initOrderItem() method begin*****");
		}
		Map<String,Object>  map=new HashMap<String,Object>();
		//修改20140509,修正商务对接编码从商品库中获取
		LvPubProduct pubProduct = lvPubProductService.findByProductCode(orderDetails.getProductCode());
		ThreeOrderItemDto item=new ThreeOrderItemDto();
		if(pubProduct!=null&&ObjectUtils.isNotEmpty(pubProduct.getPcode())){
	        //判断当前编码是否存在规格替换符合
			String pcode=pubProduct.getPcode();
			if(pcode.contains("@")&&patternCode>0){
				pcode=pcode.replaceAll("@",patternCode.toString());
			}
			item.setPcode(pcode);
		}
		if (ObjectUtils.isNotEmpty(orderDetails.getOprice())) {
			item.setOprice(Double.valueOf(orderDetails.getOprice()));
		}
		if (ObjectUtils.isNotEmpty(orderDetails.getPnum())) {
			map.put("pnum", orderDetails.getPnum());
			item.setPunm(orderDetails.getPnum());
		}
		orderItems.add(item);
		
		if (logger.isInfoEnabled()){
			logger.info("***** OrderToCreatentServiceImpl.initOrderItem() method end*****");
		}
	}
	
	/**
	 * 
	 * @Method: initOrderPackageItem 
	 * @Description:  [组装商品信息(套餐时)
	 *                 数据格式："pcode":"产品型号Code","oprice":"单价","pnum":"数量"  ]    
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-8 下午5:43:54]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-8 下午5:43:54]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param orderItems 商品集合数据
	 * @param orderDetails 订单商品详情
	 * @param patternCode 规格
	 * @return void
	 */
	private void initOrderPackageItem(List<ThreeOrderItemDto> orderItems,LvOrderDetails orderDetails,Short patternCode) {
		if (logger.isInfoEnabled()){
			logger.info("***** OrderToCreatentServiceImpl.initOrderPackageItem() method begin*****");
		}
		String hql="from LvOrderPackageDetails where orderDetailsCode='"+orderDetails.getCode()+"'";
		List<LvOrderPackageDetails> tmpList  =dao.find(hql, null);
		for (LvOrderPackageDetails lvOrderPackageDetails : tmpList) {
			if(lvOrderPackageDetails!=null){
				Map<String,Object>  map=new HashMap<String,Object>();
				//修改20140509,修正商务对接编码从商品库中获取
				LvPubProduct pubProduct = lvPubProductService.findByProductCode(lvOrderPackageDetails.getProductCode());
				ThreeOrderItemDto item=new ThreeOrderItemDto();
				
				if(pubProduct!=null&&ObjectUtils.isNotEmpty(pubProduct.getPcode())){
					String pcode=pubProduct.getPcode();
					if(pcode.contains("@")&&patternCode>0){
						pcode=pcode.replaceAll("@",patternCode.toString());
					}
					item.setPcode(pcode);
				}
				if (ObjectUtils.isNotEmpty(lvOrderPackageDetails.getOprice())) {
					item.setOprice(Double.valueOf(lvOrderPackageDetails.getOprice()));
				}
				if (ObjectUtils.isNotEmpty(lvOrderPackageDetails.getPnum())) {
					item.setPunm(lvOrderPackageDetails.getPnum());
				}
				orderItems.add(item);
			}
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** OrderToCreatentServiceImpl.initOrderPackageItem() method end*****");
		}
	}


	/**
	 * 
	 * @Method: initOrderGiftItem 
	 * @Description:  [一句话描述该类的功能]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-8 下午5:45:32]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-8 下午5:45:32]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param orderNum 订单号
	 * @param isGift  是否存在赠品标识
	 * @param orderItems 商品集合数据
	 * @return void
	 */
	private void initOrderGiftItem(String orderNum, Short isGift,List<ThreeOrderItemDto> orderItems,Short patternCode) {
		if (logger.isInfoEnabled()){
			logger.info("***** OrderToCreatentServiceImpl.initOrderGiftItem() method begin*****");
		}
		if(ObjectUtils.isNotEmpty(isGift)){
			if(isGift==1){
				List orderGiftList=lvOrderGiftService.findAllByOrderId(orderNum);
				if(ObjectUtils.isNotEmpty(orderGiftList)){
					for (int i = 0; i < orderGiftList.size(); i++) {
						Map giftMap=(Map) orderGiftList.get(i);
						ThreeOrderItemDto item=new ThreeOrderItemDto();
						if(ObjectUtils.isNotEmpty(giftMap.get("pcode"))){//sas对接code
							String pcode=String.valueOf(giftMap.get("pcode"));
							if(pcode.contains("@")&&patternCode>0){
								pcode=pcode.replaceAll("@",patternCode.toString());
							}
							item.setPcode(pcode);
						}
						if (ObjectUtils.isNotEmpty(giftMap.get("giftNum"))) {//赠品数目
							item.setPunm(Integer.parseInt(giftMap.get("giftNum").toString()));
						}
						item.setOprice(0.0);
						orderItems.add(item);
					}
				}
			}
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** OrderToCreatentServiceImpl.initOrderGiftItem() method end*****");
		}
	}

	/**
	 * 
	 * @Method: initOrderCoupon 
	 * @Description:  [组装订单优惠券数据(暂时只是处理1:1的情况)]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-8 下午5:47:16]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-8 下午5:47:16]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param orderNum
	 * @param threeOrder 
	 * @return void
	 */
	private void initOrderCoupon(String orderNum, ThreeOrderDto threeOrder) {
		if (logger.isInfoEnabled()){
			logger.info("***** OrderToCreatentServiceImpl.initOrderCoupon() method begin*****");
		}
		BaseDto dto=new BaseDto();
		dto.put("orderId", orderNum);
		List<LvOrderCoupon> orderCouponList=lvOrderCouponService.findDetailsByOrderId(dto);
		if(ObjectUtils.isNotEmpty(orderCouponList)&&orderCouponList.size()>0){
			LvOrderCoupon orderCoupon=orderCouponList.get(0);
			if(ObjectUtils.isNotEmpty(orderCoupon)){
				if(ObjectUtils.isNotEmpty(orderCoupon.getCouponName())){//优惠券名称
				    threeOrder.setCouponName(orderCoupon.getCouponName());
				}			
				if(ObjectUtils.isNotEmpty(orderCoupon.getCouponPrice())){//优惠券金额
					threeOrder.setCouponPrice(orderCoupon.getCouponPrice());
				}			
				if(ObjectUtils.isNotEmpty(orderCoupon.getCouponCode())){//优惠码
					threeOrder.setCouponCode(orderCoupon.getCouponCode());
				}
			}
		}
		
		
		if (logger.isInfoEnabled()){
			logger.info("***** OrderToCreatentServiceImpl.initOrderCoupon() method end*****");
		}
	}



}
