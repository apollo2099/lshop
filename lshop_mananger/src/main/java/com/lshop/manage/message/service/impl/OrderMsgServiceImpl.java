package com.lshop.manage.message.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.message.jms.JmsMessageQueueSender;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvOrder;
import com.lshop.common.pojo.logic.LvOrderDetails;
import com.lshop.common.pojo.logic.LvOrderPackageDetails;
import com.lshop.common.pojo.logic.LvPubProduct;
import com.lshop.common.util.DateUtils;
import com.lshop.manage.lvOrder.service.LvEmailService;
import com.lshop.manage.lvOrder.service.LvOrderService;
import com.lshop.manage.lvPubProduct.service.LvPubProductService;
import com.lshop.manage.message.service.OrderMsgService;

@Service("OrderMsgService")
public class OrderMsgServiceImpl implements OrderMsgService {
	private static final Log logger = LogFactory.getLog(OrderMsgServiceImpl.class);

	@Resource
	private HibernateBaseDAO dao;
	@Resource
	private JmsMessageQueueSender messageQueueSender;
	@Resource
	private LvEmailService lvEmailService;
	@Resource
	private LvOrderService lvOrderService;
	@Resource
	private LvPubProductService lvPubProductService;

	/**
	 * *****************************************************************************
	 * 1，接收商务订单发货数据参数:
	 * oid 订单号
	 * expressCompany 物流公司key
	 * expressName 物流公司名称
	 * expressNum 物流单号
	 * status 订单发货状态
	 * shipTime 发货时间
	 * 
	 * *****************************************************************************
	 * @Method: processOrderMSGToWMS 
	 * @Description:  [接收商务订单发货数据]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-21 下午02:53:01]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-21 下午02:53:01]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void processOrderMSGToWMS(Dto dataDto) {
		LvOrder lvOrder = new LvOrder();
		lvOrder.setOid(dataDto.getAsString("oid"));
		// 查询物流信息
		String companyName = "";
		String key = dataDto.getAsString("expressCompany");
		try {
			if(ObjectUtils.isNotEmpty(key) ){
				companyName = (String) dao.findUnique("SELECT companyName FROM LvLogistics WHERE key='"+key+"'", null);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		if (companyName != null) {
			lvOrder.setExpressName(companyName);
		} else {
			lvOrder.setExpressName(dataDto.getAsString("expressCompany"));
		}
		lvOrder.setExpressCompany(key);
		lvOrder.setExpressNum(dataDto.getAsString("expressNum"));
		lvOrder.setSendRemark(dataDto.getAsString("sendremark"));
		if(ObjectUtils.isNotEmpty(dataDto.get("shipTime"))){
			lvOrder.setShipTime(new Date(Long.parseLong(dataDto.get("shipTime").toString())));
		}else{
			lvOrder.setShipTime(new Date());
		}
		
		try {
			lvOrder.setStatus(Short.parseShort(dataDto.getAsString("status")));
		} catch (Exception e) {
			logger.error(e.getMessage());
			lvOrder.setStatus((short) 1);
		}
		Dto param = new BaseDto();
		param.put("uName", "BZSystem");
		param.put("lvOrder", lvOrder);

		Integer statuscode;
		try {
			statuscode = (Integer) lvOrderService.doOrder(param);
			if (statuscode == 1) {
				//LvOrder _obj=lvOrderService.getOrder(param);
				//param.put("flag", _obj.getStoreId());
				lvEmailService.doNoticeFa(param);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}

	/**
	 * *****************************************************************************
	 *  2，发送商务订单数据格式：
	 *  {"oid":"订单号","relname":"客户名称","contryId":"国家编码","contryname":"国家","provincename":"洲/省","cityname":"城市","adress":"详细地址","postcode":"邮编","tel":"固话","mobile":"手机",
	 *  "email":"邮箱","postage":"邮费","totalPrice":"总价","paymethod":"支付方式","overtime":"成功支付时间","paynumber":"支付单号","source":"来源","sendtime":"消息发送时间","oremark":"订单备注",
	 *  "createtime":"下单时间","sendremark":"发货备注","description":"优惠信息","currency":"币种","products":[{"pcode":"产品型号Code","oprice":"单价","pnum":"数量","coupons":"优惠码","specialprice":"优惠金额"},
	 *  {"pcode":"产品型号Code","oprice":"单价","pnum":"数量","coupons":"优惠码","specialprice":"优惠金额"},......]	
	 *  }
	 * 
	 * *****************************************************************************
	 * @Method: sendOrderMSGToWMS 
	 * @Description:  [订单审核发送到商务系统的数据对接]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-21 下午02:43:48]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-21 下午02:43:48]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public void sendOrderMSGToWMS(String oid,String source) {
		final String _oid = oid;
		final String _source = source;
		new Thread() {
			@SuppressWarnings("unchecked")
			@Override
			public void run() {
				String hql = "SELECT lvorder.oid AS oid,lvOrderAdress.relName AS relname,la.code as contryId ," +
						" lvOrderAdress.contryName AS contryname,lvOrderAdress.provinceName AS provincename,lvOrderAdress.cityName AS cityname," +
						"lvOrderAdress.adress AS adress,lvOrderAdress.postCode AS postcode,lvOrderAdress.tel AS tel,lvOrderAdress.mobile AS mobile, " +
						"lvorder.userEmail AS email, lvorder.postprice AS postage, lvorder.totalPrice AS totalPrice," +
						"lvorder.paymethod AS paymethod,lvorder.overtime AS overtime,lvorder.thirdPartyOrder as paynumber,lvorder.storeId as source, " +
						"lvorder.orderRemark AS oremark,lvorder.createTime AS createtime," +
						"lvorder.sendRemark AS sendremark,'' AS description,lvorder.currency as currency " +
						"FROM LvOrder AS lvorder,LvOrderAddress AS lvOrderAdress,LvArea as la  " +
						"WHERE   lvOrderAdress.orderId=lvorder.oid  " +
						"AND lvOrderAdress.contryId=la.id  " +
						"AND lvorder.oid='"+ _oid + "'";
				List list = dao.getMapListByHql(hql, 1, 1, null).getList();
				if (list != null && !list.isEmpty()) {
					Map param = (Map) list.get(0);
					Date createtime = (Date) param.get("createtime");

					if (ObjectUtils.isNotEmpty(createtime)) {
						param.put("createtime", DateUtils.formatDate(createtime, "yyyy-MM-dd HH:mm:ss"));
					}
					Date overtime = (Date) param.get("overtime");
					if (overtime != null) {
						param.put("overtime", DateUtils.formatDate(overtime, "yyyy-MM-dd HH:mm:ss"));
					} else {
						param.put("overtime", DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
					}
					String description = (String) param.get("description");
					param.put("description", description == null ? "" : description);
					String sendremark = (String) param.get("sendremark");
					param.put("sendremark", sendremark == null ? "" : sendremark);
					param.put("sendtimestamp", new Date().getTime());
					//source标识说明：1旧英文版后台，2韩文后台，店铺标识对应商家后台
					param.put("source", 1);//来源标记,用华扬1.0英文版队列代替华扬3.0队列发送消息，华扬英文版已经集成到华扬2.0中。
					param.put("sendtime", DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
					
					if(ObjectUtils.isNullOrEmptyString(param.get("oid"))){
						param.put("oid", "");
					}
					if(ObjectUtils.isNullOrEmptyString(param.get("relname"))){
						param.put("relname", "");
					}
					if(ObjectUtils.isNullOrEmptyString(param.get("contryId"))){
						param.put("contryId", "");
					}
					if(ObjectUtils.isNullOrEmptyString(param.get("contryname"))){
						param.put("contryname", "");
					}
					if(ObjectUtils.isNullOrEmptyString(param.get("cityname"))){
						param.put("cityname", "");
					}
					if(ObjectUtils.isNullOrEmptyString(param.get("adress"))){
						param.put("adress", "");
					}
					if(ObjectUtils.isNullOrEmptyString(param.get("postcode"))){
						param.put("postcode", "");
					}
					if(ObjectUtils.isNullOrEmptyString(param.get("tel"))){
						param.put("tel", "");
					}
					if(ObjectUtils.isNullOrEmptyString(param.get("mobile"))){
						param.put("mobile", "");
					}
					if(ObjectUtils.isNullOrEmptyString(param.get("email"))){
						param.put("email", "");
					}
					if(ObjectUtils.isNullOrEmptyString(param.get("paynumber"))){
						param.put("paynumber", "");
					}
					if(ObjectUtils.isNullOrEmptyString(param.get("postage"))){
						param.put("postage", "");
					}
					if(ObjectUtils.isNullOrEmptyString(param.get("totalPrice"))){
						param.put("totalPrice", "");
					}
					if(ObjectUtils.isNullOrEmptyString(param.get("paymethod"))){
						param.put("paymethod", "");
					}

					
					//订单详情(产品信息)
					//"pcode":"产品型号Code","oprice":"单价","pnum":"数量","coupons":"优惠码","specialprice":"优惠金额"
					hql="from LvOrderDetails as ld where ld.orderId='"+_oid+"' ";
					List<LvOrderDetails> arrList  =dao.find(hql, null);
					List<Map>  objList =new ArrayList();
					for(LvOrderDetails orderDetails : arrList){//遍历订单map信息
						if(orderDetails!=null){
						if(ObjectUtils.isNotEmpty(orderDetails.getIsPackage())&&orderDetails.getIsPackage()==1){//为套餐
							hql="from LvOrderPackageDetails where orderDetailsCode='"+orderDetails.getCode()+"'";
							List<LvOrderPackageDetails> tmpList  =dao.find(hql, null);
							for (LvOrderPackageDetails lvOrderPackageDetails : tmpList) {
								if(lvOrderPackageDetails!=null){
									Map<String,Object>  map=new HashMap<String,Object>();
									//修改20140509,修正商务对接编码从商品库中获取
									LvPubProduct pubProduct = lvPubProductService.findByProductCode(lvOrderPackageDetails.getProductCode());
									if(pubProduct!=null&&ObjectUtils.isNotEmpty(pubProduct.getPcode())){
										map.put("pcode", pubProduct.getPcode());
									}else{
										map.put("pcode", "");
									}
									if (ObjectUtils.isNotEmpty(lvOrderPackageDetails.getOprice())) {
										map.put("oprice", lvOrderPackageDetails.getOprice());
									}else{
										map.put("oprice", "");
									}
									if (ObjectUtils.isNotEmpty(lvOrderPackageDetails.getPnum())) {
										map.put("pnum", lvOrderPackageDetails.getPnum());
									}else{
										map.put("pnum", "");
									}
									if (ObjectUtils.isNotEmpty(lvOrderPackageDetails.getCouponCode())) {
										map.put("coupons", lvOrderPackageDetails.getCouponCode());
									}else{
										map.put("coupons", "");
									}
									if (ObjectUtils.isNotEmpty(lvOrderPackageDetails.getCouponPrice())) {
										map.put("specialprice", lvOrderPackageDetails.getCouponPrice());
									}else{
										map.put("specialprice","");
									}
									objList.add(map);
								}
							}
						}else{//非套餐
							Map<String,Object>  map=new HashMap<String,Object>();
							//修改20140509,修正商务对接编码从商品库中获取
							LvPubProduct pubProduct = lvPubProductService.findByProductCode(orderDetails.getProductCode());
							if(pubProduct!=null&&ObjectUtils.isNotEmpty(pubProduct.getPcode())){
								map.put("pcode", pubProduct.getPcode());
							}else{
								map.put("pcode", "");
							}
							if (ObjectUtils.isNotEmpty(orderDetails.getOprice())) {
								map.put("oprice", orderDetails.getOprice());
							}else{
								map.put("oprice", "");
							}
							if (ObjectUtils.isNotEmpty(orderDetails.getPnum())) {
								map.put("pnum", orderDetails.getPnum());
							}else{
								map.put("pnum", "");
							}
							if (ObjectUtils.isNotEmpty(orderDetails.getCouponCode())) {
								map.put("coupons", orderDetails.getCouponCode());
							}else{
								map.put("coupons", "");
							}
							if (ObjectUtils.isNotEmpty(orderDetails.getCouponPrice())) {
								map.put("specialprice", orderDetails.getCouponPrice());
							}else{
								map.put("specialprice","");
							}
							
							objList.add(map);
						}
						}
					}
					
					param.put("products",objList);
					
					String json = JSONObject.fromObject(param).toString();
//					String json="{\"tel\":\"\",\"postcode\":\"33\",\"currency\":\"USD\",\"postage\":3,\"createtime\":\"2012-11-08 11:13:52\",\"adress\":\"3333333\",\"provincename\":\"44\",\"source\":\"tvpadcn\",\"email\":\"790006897@qq.com\",\"description\":\"\",\"oid\":\"T2012110811135214653\",\"paynumber\":null,\"sendtimestamp\":1352347168720,\"oremark\":\"\",\"paymethod\":4,\"cityname\":\"44\",\"sendtime\":\"2012-11-08 11:59:28\",\"sendremark\":\"\",\"relname\":\"344\",\"products\":[{\"specialprice\":null,\"coupons\":\"\",\"pnum\":1,\"oprice\":222,\"pcode\":\"135096452561272UUU1678TT\"}],\"contryname\":\"China\",\"overtime\":\"2012-11-08 11:14:24\",\"mobile\":\"wwwwwwwwwww\",\"totalPrice\":225}";
					messageQueueSender.sendObject(json);
					if (logger.isInfoEnabled()){
						logger.info("json message=>"+json);
					}
				}
			}

		}.start();

	}

}
