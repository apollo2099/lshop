package com.lshop.ws.server.bz.order.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.jws.WebService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.service.impl.BaseServiceImpl;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvOrderDetails;
import com.lshop.common.pojo.logic.LvOrderPackageDetails;
import com.lshop.common.pojo.logic.LvPubProduct;
import com.lshop.common.util.DateUtils;
import com.lshop.manage.lvPubProduct.service.LvPubProductService;
import com.lshop.ws.server.bz.order.bean.LvOrderDto;
import com.lshop.ws.server.bz.order.bean.LvOrderDtoResposne;
import com.lshop.ws.server.bz.order.bean.LvProductDto;
import com.lshop.ws.server.bz.order.bean.Result;


@Service("WSOrderService")
@WebService(endpointInterface = "com.lshop.ws.server.bz.order.service.WSOrderService")
public class WSOrderServiceImpl extends BaseServiceImpl implements WSOrderService {

	@Resource
	private HibernateBaseDAO dao;
	@Resource
	private LvPubProductService lvPubProductService;
	
	private static final Log logger	= LogFactory.getLog(WSOrderServiceImpl.class);
	
	/**
	 * 同步已经审核的订单到商务接口定义
	 * @Method: synAuditOrderToBZ 
	 * @Description:  
	 * @param startTime  审核开始时间（查询范围起始时间，格式：yyyy-mm-dd HH:mm:ss）
	 * @param endTime    审核结束时间（查询范围结束时间，格式：yyyy-mm-dd HH:mm:ss）
	 * @return OrderList
	 */
	@Override
	public LvOrderDtoResposne synAuditOrderToBZ(String startTime, String endTime) {
		if(logger.isInfoEnabled()){
			  logger.info("*****WSOrderServiceImpl.synAuditOrderToBZ() method begin*****");
		}
		
		LvOrderDtoResposne lvOrderDtoResposne=new LvOrderDtoResposne();
		List<LvOrderDto> orderList=new ArrayList<LvOrderDto>();
		//判断查询日期时间差
		
		//根据审核时间查询已经审核订单集合
		try {
//			if(ObjectUtils.isNotEmpty(startTime)&&ObjectUtils.isNotEmpty(endTime)){
//				String hql=" select DISTINCT ls.ord as ord from  LvOrderLogs ls,LvOrder o " +
//						" where ls.ord=o.oid " +
//						" and o.isServiceAudit=1 " +
//						" and o.isFinanceAudit=1 " +
//						" and o.isdelete=0 " +
//						" and ls.createTime>=:startTime and ls.createTime <=:endTime ";
//				Map param=new HashMap();
//				param.put("startTime", DateUtils.convertToDateTime(startTime));
//				param.put("endTime", DateUtils.convertToDateTime(endTime));
//				List listTmp = dao.getMapListByHql(hql, param).getList();
//				if(ObjectUtils.isNotEmpty(listTmp)){
//					for (int i = 0; i < listTmp.size(); i++) {
//						Map map=(Map) listTmp.get(i);
//						//查询订单信息
//						LvOrderDto lvOrderDto=this.getOrderInfo(String.valueOf(map.get("ord")));
//					    orderList.add(lvOrderDto);
//					}
//				}
//			}
			lvOrderDtoResposne.setOrderList(orderList);
			lvOrderDtoResposne.getResult().setStatus(Result.STATUS_SUCCEED);
			lvOrderDtoResposne.getResult().setMessage("成功");
		} catch (Exception e) {
			lvOrderDtoResposne.getResult().setStatus(Result.STATUS_FAIL);
			lvOrderDtoResposne.getResult().setMessage(e.getMessage());
			e.printStackTrace();
		}
		
		if(logger.isInfoEnabled()){
			logger.info("*****WSOrderServiceImpl.synAuditOrderToBZ() method end*****");
		}
		return lvOrderDtoResposne;
	}
	
	
	/**
	 * @Method: getOrderDetailsByOid 
	 * @Description: 根据订单号查询订单详情及对应的产品信息
	 * @param oid  订单号
	 * @return List<LvProductDto>
	 */
	protected List<LvProductDto> getOrderDetailsByOid(String oid){
		//订单详情(产品信息)
		//"pcode":"产品型号Code","oprice":"单价","pnum":"数量","coupons":"优惠码","specialprice":"优惠金额"
		String hql="from LvOrderDetails as ld where ld.orderId=:orderId";
		Map param=new HashMap();
		param.put("orderId", oid);
		List<LvOrderDetails> arrList  =dao.find(hql, param);
		List<LvProductDto> products=new ArrayList<LvProductDto>();
		for(LvOrderDetails orderDetails : arrList){//遍历订单map信息
			if(orderDetails!=null){
			if(ObjectUtils.isNotEmpty(orderDetails.getIsPackage())&&orderDetails.getIsPackage()==1){//为套餐
				hql="from LvOrderPackageDetails where orderDetailsCode='"+orderDetails.getCode()+"'";
				List<LvOrderPackageDetails> tmpList  =dao.find(hql, null);
				for (LvOrderPackageDetails lvOrderPackageDetails : tmpList) {
					if(lvOrderPackageDetails!=null){
						LvProductDto lvProductDto=new  LvProductDto();	
						//修改20140509,修正商务对接编码从商品库中获取
						LvPubProduct pubProduct = lvPubProductService.findByProductCode(lvOrderPackageDetails.getProductCode());
						if(pubProduct!=null&&ObjectUtils.isNotEmpty(pubProduct.getPcode())){
							lvProductDto.setPcode(pubProduct.getPcode());
						}else{
							lvProductDto.setPcode("");
						}
						if (ObjectUtils.isNotEmpty(lvOrderPackageDetails.getOprice())) {
							lvProductDto.setOprice(lvOrderPackageDetails.getOprice());
						}else{
							lvProductDto.setOprice(0.0f);
						}
						if (ObjectUtils.isNotEmpty(lvOrderPackageDetails.getPnum())) {
							lvProductDto.setPunm(lvOrderPackageDetails.getPnum());
						}else{
							lvProductDto.setPunm(0);
						}
						if (ObjectUtils.isNotEmpty(lvOrderPackageDetails.getCouponCode())) {
							lvProductDto.setCoupons(lvOrderPackageDetails.getCouponCode());
						}else{
							lvProductDto.setCoupons("");
						}
						if (ObjectUtils.isNotEmpty(lvOrderPackageDetails.getCouponPrice())) {
							lvProductDto.setSpecialprice(lvOrderPackageDetails.getCouponPrice());
						}else{
							lvProductDto.setSpecialprice(0.0f);
						}
						products.add(lvProductDto);
					}
				}
			}else{//非套餐
				LvProductDto lvProductDto=new  LvProductDto();
				
				//修改20140509,修正商务对接编码从商品库中获取
				LvPubProduct pubProduct = lvPubProductService.findByProductCode(orderDetails.getProductCode());
				if(pubProduct!=null&&ObjectUtils.isNotEmpty(pubProduct.getPcode())){
					lvProductDto.setPcode(pubProduct.getPcode());
				}else{
					lvProductDto.setPcode("");
				}
				if (ObjectUtils.isNotEmpty(orderDetails.getOprice())) {
					lvProductDto.setOprice(orderDetails.getOprice());
				}else{
					lvProductDto.setOprice(0.0f);
				}
				if (ObjectUtils.isNotEmpty(orderDetails.getPnum())) {
					lvProductDto.setPunm(orderDetails.getPnum());
				}else{
					lvProductDto.setPunm(0);
				}
				if (ObjectUtils.isNotEmpty(orderDetails.getCouponCode())) {
					lvProductDto.setCoupons(orderDetails.getCouponCode());
				}else{
					lvProductDto.setCoupons("");
				}
				if (ObjectUtils.isNotEmpty(orderDetails.getCouponPrice())) {
					lvProductDto.setSpecialprice(orderDetails.getCouponPrice());
				}else{
					lvProductDto.setSpecialprice(0.0f);
				}
				products.add(lvProductDto);
			}
			}
		}
		return products;
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
	 * @Method: getOrderInfo 
	 * @Description: 根据订单号查询订单
	 * @param oid  订单号
	 * @return List<LvProductDto>
	 */
	protected LvOrderDto getOrderInfo(String oid){
		 LvOrderDto lvOrderDto = new LvOrderDto();
		if(ObjectUtils.isNotEmpty(oid)){
			//根据订单号查询订单信息
			String hql = "SELECT lvorder.oid AS oid,lvOrderAdress.relName AS relname,la.code as contryId ," +
			"lvOrderAdress.contryName AS contryname,lvOrderAdress.provinceName AS provincename,lvOrderAdress.cityName AS cityname," +
			"lvOrderAdress.adress AS adress,lvOrderAdress.postCode AS postcode,lvOrderAdress.tel AS tel,lvOrderAdress.mobile AS mobile, " +
			"lvorder.userEmail AS email, lvorder.postprice AS postage, lvorder.totalPrice AS totalPrice," +
			"lvorder.paymethod AS paymethod,lvorder.overtime AS overtime,lvorder.thirdPartyOrder as paynumber,lvorder.storeId as source, " +
			"lvorder.orderRemark AS oremark,lvorder.createTime AS createtime," +
			"lvorder.sendRemark AS sendremark,'' AS description,lvorder.currency as currency " +
			"FROM LvOrder AS lvorder,LvOrderAddress AS lvOrderAdress,LvArea as la  " +
			"WHERE   lvOrderAdress.orderId=lvorder.oid  " +
			"AND lvOrderAdress.contryId=la.id  " +
			"AND lvorder.oid=:oid";
			Map param=new HashMap();
			param.put("oid", oid);
			List orderListTmp=dao.getMapListByHql(hql, param).getList();
			if(ObjectUtils.isNotEmpty(orderListTmp)){
			  for (int k = 0; k < orderListTmp.size(); k++) {
				  Map orderMap = (Map) orderListTmp.get(k);
				  if(ObjectUtils.isNotEmpty(orderMap.get("oid"))){//订单号
					  lvOrderDto.setOid(String.valueOf(orderMap.get("oid")));  
				  }else{
					  lvOrderDto.setOid("");
				  }
				  if(ObjectUtils.isNotEmpty(orderMap.get("relname"))){//客户名称
					  lvOrderDto.setRelname(String.valueOf(orderMap.get("relname")));  
				  }else{
					  lvOrderDto.setRelname("");  
				  }
				  if(ObjectUtils.isNotEmpty(orderMap.get("contryId"))){//国家编码
					  lvOrderDto.setContryId(String.valueOf(orderMap.get("contryId"))); 
				  }else{
					  lvOrderDto.setContryId("");
				  }
				  if(ObjectUtils.isNotEmpty(orderMap.get("contryname"))){//国家
					  lvOrderDto.setContryname(String.valueOf(orderMap.get("contryname")));  
				  }else{
					  lvOrderDto.setContryname("");
				  }
				  if(ObjectUtils.isNotEmpty(orderMap.get("provincename"))){//洲/省
					  lvOrderDto.setProvincename(String.valueOf(orderMap.get("provincename")));
				  }else{
					  lvOrderDto.setProvincename("");
				  }
				  if(ObjectUtils.isNotEmpty(orderMap.get("cityname"))){//城市
					  lvOrderDto.setCityname(String.valueOf(orderMap.get("cityname")));
				  }else{
					  lvOrderDto.setCityname("");
				  }
				  if(ObjectUtils.isNotEmpty(orderMap.get("adress"))){//详细地址
					  lvOrderDto.setAdress(String.valueOf(orderMap.get("adress")));
				  }else{
					  lvOrderDto.setAdress("");
				  }
				  if(ObjectUtils.isNotEmpty(orderMap.get("postcode"))){//邮编
					  lvOrderDto.setPostcode(String.valueOf(orderMap.get("postcode")));
				  }else{
					  lvOrderDto.setPostcode("");
				  }
				  if(ObjectUtils.isNotEmpty(orderMap.get("tel"))){//固话
					  lvOrderDto.setTel(String.valueOf(orderMap.get("tel")));
				  }else{
					  lvOrderDto.setTel("");
				  }
				  if(ObjectUtils.isNotEmpty(orderMap.get("mobile"))){//手机
					  lvOrderDto.setMobile(String.valueOf(orderMap.get("mobile")));
				  }else{
					  lvOrderDto.setMobile("");
				  }
				  if(ObjectUtils.isNotEmpty(orderMap.get("email"))){//邮箱
					  lvOrderDto.setEmail(String.valueOf(orderMap.get("email")));
				  }else{
					  lvOrderDto.setEmail("");
				  }
				  if(ObjectUtils.isNotEmpty(orderMap.get("postage"))){//邮费
					  lvOrderDto.setPostage(String.valueOf(orderMap.get("postage")));
				  }else{
					  lvOrderDto.setPostage("");
				  }
				  
				  if(ObjectUtils.isNotEmpty(orderMap.get("totalPrice"))){//总价
					  lvOrderDto.setTotalPrice(Float.valueOf(orderMap.get("totalPrice").toString()));
				  }else{
					  lvOrderDto.setTotalPrice(0.0f);
				  }
				  if(ObjectUtils.isNotEmpty(orderMap.get("paymethod"))){//支付方式
					  lvOrderDto.setPaymethod(Integer.valueOf(orderMap.get("paymethod").toString()));
				  }else{
					  lvOrderDto.setPaymethod(0);
				  }
				  if(ObjectUtils.isNotEmpty(orderMap.get("overtime"))){//支付时间
					  lvOrderDto.setOvertime((Date) orderMap.get("overtime"));
				  }
				  if(ObjectUtils.isNotEmpty(orderMap.get("paynumber"))){//支付单号
					  lvOrderDto.setPaynumber(String.valueOf(orderMap.get("paynumber")));
				  }else{
					  lvOrderDto.setPaynumber("");
				  }
				  
				  lvOrderDto.setSource("1");//??数据来源

				  if(ObjectUtils.isNotEmpty(orderMap.get("sendtime"))){//消息发送时间
					  lvOrderDto.setSendtime((Date)orderMap.get("sendtime"));
				  }else{
					  lvOrderDto.setSendtime(null);
				  }
				  if(ObjectUtils.isNotEmpty(orderMap.get("oremark"))){
					  lvOrderDto.setOremark(String.valueOf(orderMap.get("oremark")));
				  }else{
					  lvOrderDto.setOremark("");
				  }
				  if(ObjectUtils.isNotEmpty(orderMap.get("createtime"))){//下单时间
					  lvOrderDto.setCreatetime((Date)orderMap.get("createtime"));
				  }else{
					  lvOrderDto.setCreatetime(null); 
				  }
				  if(ObjectUtils.isNotEmpty(orderMap.get("description"))){//优惠信息
					  lvOrderDto.setDescription(String.valueOf(orderMap.get("description")));
				  }else{
					  lvOrderDto.setDescription("");
				  }
				  if(ObjectUtils.isNotEmpty(orderMap.get("currency"))){//币种
					  lvOrderDto.setCurrency(String.valueOf(orderMap.get("currency")));
				  }else{
					  lvOrderDto.setCurrency("");
				  }
				  if(ObjectUtils.isNotEmpty(orderMap.get("sendremark"))){//发货备注
					  lvOrderDto.setSendremark(String.valueOf(orderMap.get("sendremark")));
				  }else{
					  lvOrderDto.setSendremark("");
				  }
				  
				 
				  

				  //查询订单详情
				  List<LvProductDto> products=this.getOrderDetailsByOid(String.valueOf(oid));
			      lvOrderDto.setProducts(products);

			  }	
			}
		}
		return lvOrderDto;
	}
}
