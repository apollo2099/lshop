package com.lshop.manage.lvOrder.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import net.sf.json.JSONObject;

import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.message.jms.JmsMessageQueueSender;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvOrderDetails;
import com.lshop.common.pojo.user.LvAccount;
import com.lshop.common.util.Constants;
import com.lshop.manage.lvAccount.service.LvAccountService;
import com.lshop.manage.lvOrder.service.LvOrderDetailsService;
import com.lshop.manage.lvOrder.service.OrderToWebService;


@Service("OrderToWebService")
public class OrderToWebServiceImpl implements OrderToWebService{
	private static final Log logger	= LogFactory.getLog(LvOrderServiceImpl.class);
	@Resource
	private LvAccountService lvAccountService;
	@Resource
	private LvOrderDetailsService lvOrderDetailsService;
	@Resource
	private JmsMessageQueueSender messageQueueSenderLshopManagerPTP;

	/**
	 * 
	 * @Method: sendMessageForWebActivity 
	 * @Description:  [发送活动结束改变限量数目和订单满就送活动赠送优惠券信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-7-4 上午11:48:15]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-7-4 上午11:48:15]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param orderId 订单号
	 * @param userEmail 用户Email
	 * @param storeId  店铺标示
	 * @return void
	 */
	public void sendMessageForWebActivity(String orderId, String userEmail,String storeId) {
		if (logger.isInfoEnabled()){
			logger.info("***** OrderToWebServiceImpl.sendMessageForWebActivity() method begin*****");
		}
		Map<String,Object> resutlMap =new HashMap<String, Object>();
		BaseDto baseDto=new BaseDto();
		baseDto.put("userEmail", userEmail);
		LvAccount lvAccount=lvAccountService.getAccountByEmail(baseDto);
		if(ObjectUtils.isNotEmpty(lvAccount)){//用户存在时才处理请求
			resutlMap.put("orderId", orderId);
			resutlMap.put("userCode", lvAccount.getCode());
			List productList=new ArrayList<Object>();
			List<LvOrderDetails> detailList=lvOrderDetailsService.getOrderDetails(orderId);
			for(LvOrderDetails detail:detailList){
				Map<String,Object> map =new HashMap<String, Object>();
			    map.put("productCode", detail.getProductCode());
			    map.put("pnum", detail.getPnum());
			    productList.add(map);
			}
			resutlMap.put("productList", productList);
			logger.info("***** OrderToWebServiceImpl.sendMessageForWebActivity() method sendMessge=>*****"+JSONObject.fromObject(resutlMap).toString());
			String systemFlag=Constants.STORE_TO_MALL_SYSTEM.get(storeId);//根据店铺标志查询商城体系
			messageQueueSenderLshopManagerPTP.sendObject(JSONObject.fromObject(resutlMap).toString());//向tvpad前端项目发送消息
		}
		if (logger.isInfoEnabled()){
			logger.info("***** OrderToWebServiceImpl.sendMessageForWebActivity() method end*****");
		}
	}

}
