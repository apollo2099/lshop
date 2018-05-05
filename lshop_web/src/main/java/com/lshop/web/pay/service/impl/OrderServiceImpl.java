package com.lshop.web.pay.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.ObjectUtils;
import com.gv.core.util.StringUtil;
import com.lshop.common.datastructure.ResultMsg;
import com.lshop.common.pojo.logic.LvEmailTpl;
import com.lshop.common.pojo.logic.LvGroupBuy;
import com.lshop.common.pojo.logic.LvOrder;
import com.lshop.common.pojo.logic.LvOrderAddress;
import com.lshop.common.pojo.logic.LvOrderDetails;
import com.lshop.common.pojo.user.LvAccount;
import com.lshop.common.util.Constants;
import com.lshop.emailsend.service.EmailSendService;
import com.lshop.web.activity.service.ActivityService;
import com.lshop.web.group.service.GroupService;
import com.lshop.web.pay.service.OrderService;

@Service("OrderService")
public class OrderServiceImpl implements OrderService {
	private static final Log logger	= LogFactory.getLog(OrderServiceImpl.class);	
	@Resource
	private HibernateBaseDAO lvlogicReadDao;
	
	@Resource
	private HibernateBaseDAO lvlogicWriteDao;
	@Resource
	private HibernateBaseDAO lvuserReadDao;
	@Resource
	private EmailSendService emailSendService;
	@Resource
	private ActivityService activityService;
	@Resource
	private GroupService groupService;
	/**
	 * 修改订单状态
	 */
	@SuppressWarnings( { "unchecked", "unchecked" })
	public Boolean updateOrderStatus(Dto dto) throws ServiceException{
		String oid = dto.getAsString("oid");
		Short payStatus = (Short) dto.get("payStatus");// 获取支付状态
		Short status = (Short) dto.get("status");// 订单状态
		String thirdpartyorder = dto.getAsString("thirdpartyorder");// 第三方订单
        Float totalPrice=(Float) dto.get("totalPrice");
		Map param = new HashMap();
		String hql = "";
		if (status == Constants.ORDER_STATUS_0) {//待付款 
			hql = "FROM LvOrder WHERE oid=:oid AND payStatus=0 AND status=0";//查询是否是已付款
		}
		if (!"".equals(hql)) {
			param.put("oid", oid);
			LvOrder order = (LvOrder) lvlogicReadDao.findUnique(hql, param);
			if (order != null&&totalPrice!=null&&totalPrice>=order.getTotalPrice()) {
				String updateString = "UPDATE LvOrder SET payStatus=:payStatus,status=:status,thirdPartyOrder=:thirdpartyorder,overtime=:overtime WHERE id=:id";
				param.clear();
				Date overtime = null;
				if (order.getOvertime() == null) {
					overtime = new Date();//设置支付成功时间
				}
				param.put("id", order.getId());
				param.put("payStatus", payStatus);
				param.put("status", status);
				param.put("overtime", overtime);
				param.put("thirdpartyorder", thirdpartyorder);
				lvlogicWriteDao.update(updateString, param);
				/**
				 * 发送支付成功提醒邮件
				 */
				if (payStatus == Constants.PAY_STATUS_OK) {
					param.clear();
					hql = "FROM LvOrderAddress WHERE orderId=:orderId";
					param.put("orderId", oid);
					LvOrderAddress lvOrderAdress = (LvOrderAddress) lvlogicReadDao.findUnique(hql, param);
					Map map = new HashMap();
					map.put("storeId", order.getStoreId());
				    hql="FROM LvEmailTpl WHERE tplKey='PAY_OK_EMAIL_TEMP' and storeId=:storeId";
					LvEmailTpl emailTpl=(LvEmailTpl) lvlogicWriteDao.findUnique(hql, map);
					if(null!=emailTpl){
						String mailtemp=emailTpl.getEn()+emailTpl.getZh();
						mailtemp=mailtemp.replace("{oid}",order.getOid());
						mailtemp=mailtemp.replace("{paymethod}", Constants.PAY_METHODS.get(order.getPaymethod()).toString());
						mailtemp=mailtemp.replace("{totalPrice}", order.getTotalPrice().toString());
						mailtemp=mailtemp.replace("{relname}", lvOrderAdress.getRelName());
						mailtemp=mailtemp.replace("{currency}", order.getCurrency());
						mailtemp=mailtemp.replace("{sendtime}",StringUtil.dateFormat(new Date()));
						dto.put("userEmail", order.getUserEmail());
						dto.put("title", emailTpl.getEnTitle().replace("{oid}",order.getOid()));
						dto.put("content", mailtemp);
						dto.put("mallFlag", Constants.STORE_TO_MALL_SYSTEM.get(order.getStoreId())); //获取当前店铺所属系统（华扬orBanana）
						Boolean emailFlag=(boolean)emailSendService.sendEmailNotice(dto);//发邮件
					}
					
				}
              return true;
			}
		}
		return false;

	}
	@Override
	public LvOrder getOrder(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		String oid = dto.getAsString("oid");
		Map paramMap=new HashMap();
		paramMap.put("oid", oid);
		return (LvOrder)lvlogicReadDao.findUnique("FROM LvOrder WHERE oid=:oid",paramMap);
	
	}
	@Override
	public void orderPayCallBack(Dto dto) throws Exception {
		String oid = dto.getAsString("oid");
		String userCode = dto.getAsString("uid");
		//获取订单详情，以便找到相应的产品信息
		LvOrder order=(LvOrder)lvlogicReadDao.findUniqueByProperty(LvOrder.class, "oid", oid);
		
		if (ObjectUtils.isNotEmpty(order)) {
			if (null!=order.getIsGroup() && order.getIsGroup()==1){ //说明是团购订单
				groupService.finishOrderGroup(order);
			} else {
				String hql;
				Map<String, Object> param = new HashMap<String, Object>();
				if (StringUtils.isBlank(userCode)) {
					//查找用户号
					hql = "SELECT memid from LvOrder WHERE oid=:oid";
					param.put("oid", oid);
					Integer userid = (Integer) lvlogicReadDao.findUnique(hql, param);
					hql = "SELECT code FROM LvAccount WHERE id=:userid";
					param = new HashMap<String, Object>();
					param.put("userid", userid);
					userCode = lvuserReadDao.findUnique(hql, param).toString();
				}
				//查看订单中商品列表
				hql = "from LvOrderDetails where orderId=:oid";
				param = new HashMap<String, Object>();
				param.put("oid", oid);
				Map<String, Integer> prodNumMap = new HashMap<String, Integer>();
				List<LvOrderDetails> orderDetails = lvlogicReadDao.find(hql, param);
				for (Iterator<LvOrderDetails> iterator = orderDetails.iterator(); iterator.hasNext();) {
					LvOrderDetails lvOrderDetails = iterator.next();
					prodNumMap.put(lvOrderDetails.getProductCode(), lvOrderDetails.getPnum());
				}
				ResultMsg msg = activityService.finishOrderActivity(oid, userCode, prodNumMap);
				if (!msg.isSuccess()) {
					logger.error("订单支付成功回调活动失败:"+oid);
				}
			}
		}
		
	}


}
