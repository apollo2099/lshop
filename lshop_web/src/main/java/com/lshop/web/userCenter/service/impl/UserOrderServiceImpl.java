package com.lshop.web.userCenter.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.proxy.ServiceConstants;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvActivityGift;
import com.lshop.common.pojo.logic.LvOrder;
import com.lshop.common.pojo.logic.LvOrderAddress;
import com.lshop.common.pojo.logic.LvOrderComment;
import com.lshop.common.pojo.logic.LvOrderCoupon;
import com.lshop.common.pojo.logic.LvOrderDetails;
import com.lshop.common.pojo.logic.LvOrderPackageDetails;
import com.lshop.common.pojo.logic.LvOrderRemind;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.pojo.logic.LvProductComment;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.pojo.logic.LvWesternInfo;
import com.lshop.common.redis.RedisKeyConstant;
import com.lshop.common.util.CacheMemory;
import com.lshop.common.util.CodeUtils;
import com.lshop.common.util.Constants;
import com.lshop.web.order.service.OrderGiftService;
import com.lshop.web.order.component.OrderCacheDao;
import com.lshop.web.order.vo.OrderCahce;
import com.lshop.web.product.service.ProductService;
import com.lshop.web.userCenter.service.LogisticsTrackingService;
import com.lshop.web.userCenter.service.UserOrderService;
import com.lshop.ws.web.creatent.order.WSThreeOrderService;

/**
 * 用户中心订单模块
 * @author zhengxue
 * @since 2.0 2012-08-03
 *
 */
@Service("UserOrderService")
public class UserOrderServiceImpl implements UserOrderService {
	
	@Resource
	private HibernateBaseDAO lvlogicReadDao;
	
	@Resource
	private HibernateBaseDAO lvlogicWriteDao;
	
	@Resource
	private ProductService productService;
	@Resource
	private WSThreeOrderService wsThreeOrderService;
	@Resource
	private OrderGiftService orderGiftService;
	@Resource
	private OrderCacheDao orderCacheDao;
	
	private static final Log logger = LogFactory.getLog(UserOrderServiceImpl.class);
	
	/**
	 * 查看订单详情
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Dto viewOrderInfo(Dto dto) {
		
		if (logger.isInfoEnabled()){
			logger.info("***** UserOrderServiceImpl.viewOrderInfo() method begin*****");
		}	
		
		Dto orderDto = new BaseDto();
		String oid = dto.getAsString("oid");
		Map param = new HashMap();
		String hql = "";
		
		LvOrder lvOrder = null;
		LvOrderAddress lvOrderAdress = null;
		List<LvOrderDetails> orderDetailList= null;
		//read from cache
		OrderCahce orderCahce = orderCacheDao.get(RedisKeyConstant.OrderKey(oid));
		if (ObjectUtils.isNotEmpty(orderCahce) && !orderCahce.isSync()) {
			//未同步到数据库中的订单
			lvOrder = orderCahce.getOrder();
			lvOrderAdress = orderCahce.getAddress();
			orderDetailList = orderCahce.getDetails();
		} else {
			hql = "FROM LvOrder WHERE oid=:oid";
			param.put("oid", oid);
			lvOrder = (LvOrder) lvlogicReadDao.findUnique(hql, param);
			
			hql = "from LvOrderAddress where orderId=:oid";
			param.clear();
			param.put("oid", oid);
			lvOrderAdress = (LvOrderAddress) lvlogicReadDao.findUnique(hql, param);
			
			//获取订单详情信息
			hql = "from LvOrderDetails where orderId=:oid  and isDelete=0";
			param.clear();
			param.put("oid", oid);
			orderDetailList=(List<LvOrderDetails> )lvlogicReadDao.find(hql, param);
		}

		BigDecimal allAmount=new   BigDecimal(Float.toString(0f)); //记录原总金额
		List<Object[]> objList=new ArrayList<Object[]>();
		
		if(orderDetailList!=null&&orderDetailList.size()>0){
			for(LvOrderDetails detail:orderDetailList){
				//获取该条订单详情所对应的产品(包括单个产品和套餐)
				dto.put("pcode", detail.getProductCode());
				dto.put("storeFlag", lvOrder.getStoreId());
				LvProduct product=(LvProduct)productService.getProductByCode(dto);
				//将使用优惠券及运费前的金额累加
				BigDecimal   b1   =   allAmount;  
	            BigDecimal   b2   =   new   BigDecimal(Float.toString(detail.getOprice()));  
	            BigDecimal   b3   =   new   BigDecimal(Integer.toString(detail.getPnum()));
	            allAmount = b2.multiply(b3).add(b1); 
				//获取商品清单
				Object[] obj=new Object[3];
				obj[0]=product; 
				obj[1]=detail; 
				obj[2]=b2.multiply(b3).toString();//小计
				objList.add(obj);
			}
		}
		
		//如果是西联汇款且已支付，则另外要显示西联汇款信息
		if (lvOrder != null && Constants.PAY_METHOD_WESTERNUNION == lvOrder.getPaymethod() && lvOrder.getPayStatus() != Constants.PAY_STATUS_NO) {
			hql = "from LvWesternInfo where oid=:oid order by transferTime desc";
			param.clear();
			param.put("oid", oid);
			List list = lvlogicReadDao.find(hql, param);
			if(list!=null&&list.size()>0){
				LvWesternInfo info = (LvWesternInfo)list.get(0);
				orderDto.put("lvWesternInfo", info);
			}
			
		}
		
		//获取商家信息
        hql = "from LvStore where storeFlag=:storeFlag";
        param.clear();
        param.put("storeFlag", lvOrder.getStoreId());
        LvStore lvStore=(LvStore)lvlogicReadDao.findUnique(hql, param);
        
        //获取使用的优惠券信息
        
        hql = "from LvOrderCoupon where orderId=:orderNo";
        param.clear();
        param.put("orderNo", oid);
        List<LvOrderCoupon> coupons = lvlogicReadDao.find(hql, param);
        if (ObjectUtils.isNotEmpty(coupons)) {
			orderDto.put("coupon", coupons.get(0));
		}
        
        //查询赠品信息
        if(ObjectUtils.isNotEmpty(lvOrder.getIsGift())&&lvOrder.getIsGift()==1){
        	 List giftList=orderGiftService.findAllByOrderId(lvOrder.getOid());
        	 //订单相同礼品去重
        	 List giftListTmp=orderGiftService.giftDeduplication(giftList);
        	 orderDto.put("giftList", giftListTmp); 
        }
        
        
		orderDto.put("allAmount", allAmount.toString());
		orderDto.put("objList", objList);
		orderDto.put("lvOrder", lvOrder);
		orderDto.put("lvOrderAdress", lvOrderAdress);
		orderDto.put("lvStore", lvStore);
		orderDto.put("moneyMark", lvOrder.getCurrency());
		
		if (logger.isInfoEnabled()){
			logger.info("***** UserOrderServiceImpl.viewOrderInfo() method end*****");
		}	
		return orderDto;
	}
	
	
	/**
	 * 
	 * @Method: giftDeduplication 
	 * @Description:  [赠品信息去重，赠送数量叠加]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-16 上午10:45:12]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-16 上午10:45:12]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param giftList
	 * @return List<LvActivityGift>
	 */
	public List<LvActivityGift> giftDeduplication(List<LvActivityGift> giftList){
		List<LvActivityGift> resultList=new ArrayList<LvActivityGift>();
		Map<String,LvActivityGift> map = new HashMap<String,LvActivityGift>(); 
		for (LvActivityGift gift : giftList) {
			if (map.containsKey(gift.getGiftCode())) { 
					LvActivityGift giftTemp=map.get(gift.getGiftCode());
					giftTemp.setGiftEveryNum(gift.getGiftEveryNum()+giftTemp.getGiftEveryNum());
					map.put(gift.getGiftCode(), giftTemp); 
			} else { 
				    map.put(gift.getGiftCode(), gift); 
			} 
		} 
		return new ArrayList<LvActivityGift>( map.values());	
	}

	/**
	 * 支付前确认订单信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Dto toConfirmPay(Dto dto) throws ServiceException{
		
		if (logger.isInfoEnabled()){
			logger.info("***** UserOrderServiceImpl.toConfirmPay() method begin*****");
		}	
		
		HttpServletRequest  request=(HttpServletRequest)dto.get("request"); //得到请求
		String oid = dto.getAsString("oid");
		Map param = new HashMap();
		String hql = "";
		
		hql = "FROM LvOrder WHERE oid=:oid";
		param.put("oid", oid);
		LvOrder lvOrder = (LvOrder) lvlogicReadDao.findUnique(hql, param);
		
		String storeFlag = lvOrder.getStoreId(); //避免有些链接没有传递店铺标识
		dto.put("storeFlag", storeFlag);
		
		hql = "from LvOrderAddress where orderId=:oid";
		param.clear();
		param.put("oid", oid);
		LvOrderAddress lvOrderAdress = (LvOrderAddress) lvlogicReadDao.findUnique(hql, param);
		
//		Integer allCouponNum=0; //记录可使用优惠券的总台数
		BigDecimal allAmount=new   BigDecimal(Float.toString(0f));  //记录原总金额
		Float couponPrice=0f; //优惠券面值
		List<Object[]> objList=new ArrayList<Object[]>();
		//获取优惠码信息
		hql = "from LvOrderDetails where orderId=:oid  and isDelete=0 and storeId=:storeFlag";
		param.clear();
		param.put("oid", oid);
		param.put("storeFlag", storeFlag);
		List<LvOrderDetails> orderDetailList=(List<LvOrderDetails> )lvlogicReadDao.find(hql, param);
		
		if(orderDetailList!=null&&orderDetailList.size()>0){
			for(LvOrderDetails detail:orderDetailList){
				//获取该条订单详情所对应的产品(包括单个产品和套餐)
				dto.put("pcode", detail.getProductCode());
				LvProduct product=(LvProduct)productService.getProductByCode(dto);
				//将使用优惠券及运费前的金额累加
				BigDecimal   b1   =   allAmount;  
				BigDecimal   b2   =   new   BigDecimal(Float.toString(detail.getOprice()));  
				BigDecimal   b3   =   new   BigDecimal(Integer.toString(detail.getPnum()));
				allAmount = b2.multiply(b3).add(b1); 
				//判断是否使用了优惠券
				if(detail.getCouponCode()!=null&&detail.getCouponPrice()!=null){
					//获取优惠券面值
					couponPrice=detail.getCouponPrice(); 
				}
				//获取商品清单
				Object[] obj=new Object[3];
				obj[0]=product; 
				obj[1]=detail; 
				obj[2]=b2.multiply(b3).toString();
				objList.add(obj);
			}
		}
		
		hql = "from LvStore where storeFlag=:storeFlag";
		param.clear();
		param.put("storeFlag", storeFlag);
        LvStore lvStore=(LvStore)lvlogicReadDao.findUnique(hql, param);
        
        BigDecimal allCouponPrice= new   BigDecimal(Float.toString(0f));
		if(null!=lvOrder.getCouponNum() && lvOrder.getCouponNum()!=0){
			BigDecimal aa = new   BigDecimal(Float.toString(couponPrice));
			BigDecimal bb = new   BigDecimal(Integer.toString(lvOrder.getCouponNum()));
			allCouponPrice=aa.multiply(bb);
		}
		
		request.setAttribute("couponPrice", couponPrice);
		request.setAttribute("allCouponPrice", allCouponPrice.toString());
		request.setAttribute("allAmount", allAmount.toString());
		request.setAttribute("objList", objList);
		request.setAttribute("lvOrder", lvOrder);
		request.setAttribute("lvOrderAdress", lvOrderAdress);
		request.setAttribute("lvStore", lvStore);
		
//		String moneyMark="USD ";
//		if(null!=lvOrder.getPaymethod() && (lvOrder.getPaymethod()==3 || lvOrder.getPaymethod()==13||lvOrder.getPaymethod()==17||lvOrder.getPaymethod()==18)){
//			moneyMark="￥";
//		}
		request.setAttribute("moneyMark", lvOrder.getCurrency());
		
		if (logger.isInfoEnabled()){
			logger.info("***** UserOrderServiceImpl.toConfirmPay() method end*****");
		}	
		return dto;
	}
	
	/**
	 * 获取订单套餐详情表
	 * 根据订单详情表code来查询
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LvOrderPackageDetails> getPackageDetails(Dto dto)
			throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("***** UserOrderServiceImpl.getPackageDetails() method begin*****");
		}	
		
		String orderDetailsCode=dto.getAsString("orderDetailsCode");
		String hql = "from LvOrderPackageDetails where orderDetailsCode=:orderDetailsCode";
		HashMap param = new HashMap();
		param.put("orderDetailsCode", orderDetailsCode);
		List<LvOrderPackageDetails> packageDetails=(List<LvOrderPackageDetails>)lvlogicReadDao.find(hql, param);
		
		if (logger.isInfoEnabled()){
			logger.info("***** UserOrderServiceImpl.getPackageDetails() method end*****");
		}	
		return packageDetails;
	}
	
	/**
	 * 传递订单信息给支付页面
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Dto toPay(Dto dto) throws ServiceException{
		
		if (logger.isInfoEnabled()){
			logger.info("***** UserOrderServiceImpl.toPay() method begin*****");
		}	
		
		String oid=dto.getAsString("oid");
		LvOrder lvOrder=(LvOrder)lvlogicReadDao.findUniqueByProperty(LvOrder.class, "oid", oid);
		LvOrderAddress lvOrderAdress=(LvOrderAddress)lvlogicReadDao.findUniqueByProperty(LvOrderAddress.class, "orderId", oid);
		
		dto.put("lvOrder", lvOrder);
		dto.put("lvOrderAdress", lvOrderAdress);
		
		if (logger.isInfoEnabled()){
			logger.info("***** UserOrderServiceImpl.toPay() method end*****");
		}	
		return dto;
	}

	/**
	 * 确认收到货
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	public Dto confirmShouhuo(Dto dto) throws ServiceException{
		
		if (logger.isInfoEnabled()){
			logger.info("***** UserOrderServiceImpl.confirmShouhuo() method begin*****");
		}	
		
		String code=dto.getAsString("code");
//		LvOrder order=(LvOrder)lvlogicReadDao.findUniqueByProperty(LvOrder.class, "code", code);
//		order.setPayStatus((short)1);
//		order.setStatus(Constants.ORDER_STATUS_2);
		String hql="update LvOrder set payStatus=:payStatus, status=:status where code=:code";
		Map param=new HashMap();
		param.put("payStatus", (short)1);
		param.put("status", Constants.ORDER_STATUS_2);
		param.put("code", code);
		
		lvlogicWriteDao.update(hql,param);
		
		
		LvOrder order=(LvOrder)lvlogicReadDao.findUniqueByProperty(LvOrder.class, "code", code);		
		//调用启创同步销售订单接口
		wsThreeOrderService.updateOrderStatus(order.getOid(), Constants.ORDER_STATUS_2);
		
		if (logger.isInfoEnabled()){
			logger.info("***** UserOrderServiceImpl.confirmShouhuo() method end*****");
		}	
		return null;
	}
	
	/**
	 * 获取第三方物流信息
	 * @param dto
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Dto doLogisticsTrackingInfo(Dto dto) {
		
		if (logger.isInfoEnabled()){
			logger.info("***** UserOrderServiceImpl.doLogisticsTrackingInfo() method begin*****");
		}	
		
		HashMap param = new HashMap();
		param.put("oid", dto.getAsString("oid"));
		Object[]expressinfo=  (Object[]) lvlogicReadDao.findUnique("SELECT expressName,expressNum FROM LvOrder WHERE oid=:oid", param);
		if (expressinfo!=null&&expressinfo[0]!=null) {
            String key=expressinfo[0].toString().trim();
            String expressNum=expressinfo[1]!=null?expressinfo[1].toString():"";
            if (CacheMemory.LOGISTICSSERVICEID.containsKey(key)) {
				
            	LogisticsTrackingService bean = (LogisticsTrackingService) ServiceConstants.beanFactory.getBean(CacheMemory.LOGISTICSSERVICEID.get(key));
            	if (bean!=null) {
            		dto.put("expressNum", expressNum);
            		dto.put("key", key);
            		dto=bean.doLogisticsTracking(dto);
            	}
			}
			
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** UserOrderServiceImpl.doLogisticsTrackingInfo() method end*****");
		}	
		return dto;
	}

	/**
	 *  保存评论信息
	 * @param dto
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Dto saveComment(Dto dto) {

		if (logger.isInfoEnabled()){
			logger.info("***** UserOrderServiceImpl.saveComment() method begin*****");
		}	
		
		String storeFlag=dto.getAsString("storeFlag");
		//保存订单评论表
		LvOrderComment comment=(LvOrderComment)dto.getDefaultPo();
		
		//先判断该订单是否已被 评论
		int num = lvlogicReadDao.countByProperty(LvOrderComment.class, "orderId", comment.getOrderId());
		if(num>0){
			return null;
		}
		
		comment.setReplyId(0);
		comment.setIsCheck((short)0);
		comment.setIsDelete((short)0);
		comment.setCode(CodeUtils.getCode());
		comment.setCreateTime(new Date());
		comment.setStoreId(storeFlag);
		lvlogicWriteDao.save(comment);
		
		/**保存产品评论表**/
		//获取产品信息
		String hql = "from LvOrderDetails where orderId=:orderId and isDelete=0 and storeId=:storeFlag";
		HashMap param = new HashMap();
		param.put("orderId", comment.getOrderId());
		param.put("storeFlag", storeFlag);
		List<LvOrderDetails> details=(List<LvOrderDetails>)lvlogicReadDao.find(hql,param);
		
		if(details!=null&&details.size()>0){
			for(LvOrderDetails detail:details){
				LvProductComment pc=new LvProductComment();
				pc.setUid(comment.getUid());
				pc.setNickname(comment.getNickname());
				pc.setOid(comment.getOrderId());
				pc.setProductCode(detail.getProductCode());
				pc.setContent(comment.getContent());
				pc.setReplyId(comment.getReplyId());
				pc.setScore(comment.getScore());
				pc.setGrade(comment.getGrade());
				pc.setIsCheck((short)0);
				pc.setContryId(comment.getContryId());
				pc.setOprice(detail.getOprice());
				pc.setPnum(detail.getPnum());
				pc.setCurrency(detail.getCurrency());
				pc.setIsDelete((short)0);
				pc.setCode(CodeUtils.getCode());
				pc.setCreateTime(new Date());
				pc.setStoreId(storeFlag);
				pc.setCommentImages(comment.getCommentImages());
				lvlogicWriteDao.save(pc);
			}
		}
		
//		LvOrder order=(LvOrder)lvlogicReadDao.findUniqueByProperty(LvOrder.class, "oid", comment.getOrderId());
//		order.setStatus((short)4);
//		order.setPayStatus((short)1);
//		lvlogicWriteDao.update(order);
		hql="update LvOrder set payStatus=:payStatus,status=:status where oid=:oid";
		param.clear();
		param.put("payStatus", (short)1);
		param.put("status", (short)4);
		param.put("oid", comment.getOrderId());
		lvlogicWriteDao.update(hql,param);
		
		
		//调用启创同步销售订单接口
		wsThreeOrderService.updateOrderStatus(comment.getOrderId(), Constants.ORDER_STATUS_4);
		
		if (logger.isInfoEnabled()){
			logger.info("***** UserOrderServiceImpl.saveComment() method end*****");
		}	
		return null;
	}

	/**
	 * 提醒卖家发货
	 * @param dto
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Dto remindOrder(Dto dto) {
		
		if (logger.isInfoEnabled()){
			logger.info("***** UserOrderServiceImpl.remindOrder() method begin*****");
		}	
		
		String userCode=dto.getAsString("userCode");
		Integer uid=dto.getAsInteger("uid");
		String userName=dto.getAsString("userName");
		String oid=dto.getAsString("oid");
		String storeFlag=dto.getAsString("storeFlag");
		
		//获取该订单的提醒记录，如果之前提醒过，则在之前提醒的次数上加1；如果之前未提醒，则保存一条新的提醒记录
		LvOrderRemind re=(LvOrderRemind)lvlogicReadDao.findUniqueByProperty(LvOrderRemind.class, "orderId", oid);
		
		if(null!=re){ //表示之前提醒过
			//判断今天是否提醒，如果今天已经提醒，则提醒次数不累加
			
			String hql="update LvOrderRemind set remindNum=:remindNum, remindTime=:remindTime, modifyTime=:modifyTime, modifyUserId=:modifyUserId, modifyUserName=:modifyUserName where orderId=:oid";
			Map param=new HashMap();
			Integer remindNum=Integer.parseInt(re.getRemindNum())+1;
			param.put("remindNum", remindNum.toString());
			param.put("remindTime", new Date());
			param.put("modifyTime", new Date());
			param.put("modifyUserId", uid);
			param.put("modifyUserName", userName);
			param.put("oid", oid);
			lvlogicWriteDao.update(hql,param);
		}else{ //表示之前未提醒过，则保存一条新的提醒记录
			LvOrderRemind or=new LvOrderRemind();
			or.setUserCode(userCode);
			or.setOrderId(oid);
			or.setRemindNum("1");
			or.setCreateTime(new Date());
			or.setRemindTime(new Date());
			or.setStoreId(storeFlag);
			or.setCode(CodeUtils.getCode());
			lvlogicWriteDao.save(or);
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** UserOrderServiceImpl.remindOrder() method end*****");
		}	
		return null;
	}

	/**
	 * 获取订单信息及订单地址信息
	 * @param dto
	 * @return
	 */
	@Override
	public Dto getOrderInfo(Dto dto) {

		if (logger.isInfoEnabled()){
			logger.info("***** UserOrderServiceImpl.getOrderInfo() method begin*****");
		}	
		
		String oid=dto.getAsString("oid");
		LvOrder lvOrder=(LvOrder)lvlogicReadDao.findUniqueByProperty(LvOrder.class, "oid", oid);
		LvOrderAddress lvOrderAdress=(LvOrderAddress)lvlogicReadDao.findUniqueByProperty(LvOrderAddress.class, "orderId", oid);
		dto.put("lvOrder", lvOrder);
		dto.put("lvOrderAdress", lvOrderAdress);
		
		if (logger.isInfoEnabled()){
			logger.info("***** UserOrderServiceImpl.getOrderInfo() method end*****");
		}	
		return dto;
	}

	@Override
	public LvOrder getOrderByCode(Dto dto) {
		String oid = dto.getAsString("oid");
		return (LvOrder)lvlogicReadDao.findUniqueByProperty(LvOrder.class, "oid", oid);
	}
}
