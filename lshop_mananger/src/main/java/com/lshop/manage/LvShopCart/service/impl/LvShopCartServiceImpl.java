package com.lshop.manage.LvShopCart.service.impl;

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
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.message.jms.JmsMessageQueueSender;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvActivityLimited;
import com.lshop.common.pojo.logic.LvGroupBuy;
import com.lshop.common.pojo.logic.LvOrder;
import com.lshop.common.pojo.logic.LvOrderActivity;
import com.lshop.common.pojo.logic.LvOrderDetails;
import com.lshop.common.pojo.logic.LvOrderPackageDetails;
import com.lshop.common.pojo.logic.LvPubProduct;
import com.lshop.common.pojo.user.LvAccount;
import com.lshop.common.util.Constants;
import com.lshop.manage.LvShopCart.service.LvShopCartService;
import com.lshop.manage.lvAccount.service.LvAccountService;
import com.lshop.manage.lvActivity.service.LvActivityService;
import com.lshop.manage.lvOrder.service.LvOrderActivityService;
import com.lshop.manage.lvOrder.service.LvOrderDetailsService;
import com.lshop.manage.lvOrder.service.OrderToWebService;
import com.lshop.manage.lvProduct.service.LvProductService;

/**
 * 购物车模块
 * @author zhengxue
 * @since 2.0 2012-07-30
 *
 */
@Service("LvShopCartService")
public class LvShopCartServiceImpl implements LvShopCartService {
	
	@Resource
	private HibernateBaseDAO dao;
	@Resource
	private LvProductService lvProductService;
	@Resource 
	private LvActivityService lvActivityService;
	@Resource
	private OrderToWebService orderToWebService;

	
	private static final Log logger	= LogFactory.getLog(LvShopCartServiceImpl.class);

	/**
	 * 支付时更改剩余量或已购买量
	 * 判断产品类型，如果是团购产品则更改已购买人数；如果是限量产品，则更改剩余产品量
	 * 传入参数订单号oid
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Dto changeCount(Dto dto) throws ServiceException {

		if (logger.isInfoEnabled()){
			logger.info("***** ShopCartServiceImpl.changeCount() method begin*****");
		}	
		
		String oid=dto.getAsString("oid"); //获取订单号
		String hql = "";
		Map<String,Object> param = new HashMap<String,Object>();
		//获取订单详情，以便找到相应的产品信息
		LvOrder order=(LvOrder)dao.findUniqueByProperty(LvOrder.class, "oid", oid);	
		if(null!=order.getIsGroup() && order.getIsGroup()==1){ //说明是团购产品
			//判断是否是团购产品，以订单号首字母来判断
			changeGroupNum(order.getGroupCode(),order.getOvertime(),order.getStoreId());
		}else{ //不是团购产品，就判断是否是限量产品
			orderToWebService.sendMessageForWebActivity(oid, order.getUserEmail(), order.getStoreId());
		}		
		if (logger.isInfoEnabled()){
			logger.info("***** ShopCartServiceImpl.changeCount() method end*****");
		}	
		
		return null;
	}



	

	/**
	 * 
	 * @Method: changeGroupNum 
	 * @Description:  [改变团购剩余库存]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-7-3 下午3:17:01]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-7-3 下午3:17:01]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param groupCode 团购code
	 * @param endTime 结束时间
	 * @param storeId 店铺标示
	 * @return void
	 */
	private void changeGroupNum(String groupCode,Date endTime,String storeId) {
		Map<String, Object> param=new HashMap<String, Object>();
		String hql= "from LvGroupBuy where code=:code and status=1 and endTime>=:endTime and storeId=:storeId order by endTime desc";
		param.clear();
		param.put("code", groupCode);
		param.put("endTime", endTime);
		param.put("storeId", storeId);
		List<LvGroupBuy> groupList=(List<LvGroupBuy>)dao.find(hql, param);
		
		if(groupList!=null&&groupList.size()>0){
			LvGroupBuy groupBuy=groupList.get(0);
			hql="update LvGroupBuy set purchasedNum=:purchasedNum where code=:code";
			param.clear();
			param.put("purchasedNum", groupBuy.getPurchasedNum()+1); //统计已购买人数，一个用户只能下一个订单，但订单中的产品数不限制
			param.put("code", groupBuy.getCode());
			dao.update(hql, param);
		}
	}

	/**
	 * 
	 * @Method: changeLimitNum 
	 * @Description:  [改变限量活动剩余库存减少]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-7-3 下午6:11:43]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-7-3 下午6:11:43]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param activityCode
	 * @param pnum
	 * @param orderProductCode 
	 * @return void
	 */
	private void changeLimitNum(String activityCode,Integer pnum,String orderProductCode) {
		Map<String, Object> param=new HashMap<String, Object>();
		BaseDto dto=new BaseDto();
		dto.put("activityCode", activityCode);
		LvActivityLimited lvActivityLimited= lvActivityService.findLimtNumByCode(dto);
		if(lvActivityLimited.getProductCode().equals(orderProductCode)){
			String hql=" update LvActivityLimited set limitTotal=:limitTotal" +
				       " where activityCode=:activityCode";
				param.clear();
				param.put("pnum",lvActivityLimited.getLimitTotal()-pnum);
				param.put("activityCode",activityCode);
				dao.update(hql, param);
		}
	}
		
}



