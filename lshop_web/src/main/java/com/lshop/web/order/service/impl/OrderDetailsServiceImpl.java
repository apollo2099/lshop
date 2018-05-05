package com.lshop.web.order.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.ObjectUtils;
import com.lshop.web.order.service.OrderDetetailsService;

@Service("OrderDetetailsService")
public class OrderDetailsServiceImpl implements OrderDetetailsService {
	private static final Log logger	= LogFactory.getLog(OrderDetailsServiceImpl.class);
	@Resource
	private HibernateBaseDAO lvlogicReadDao;
	
	@Resource
	private HibernateBaseDAO lvlogicWriteDao;
	
	/**
	 * 
	 * @Method: findAllByOrderId 
	 * @Description:  [根据订单号查询订单详情]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-8 下午3:38:51]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-8 下午3:38:51]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param orderId 订单号
	 * @return List
	 */
	public List findAllByOrderId(String orderId){
		if (logger.isInfoEnabled()){
			logger.info("***** OrderDetailsServiceImpl.findAllByOrderId() method begin*****");
			logger.info("***** OrderDetailsServiceImpl.findAllByOrderId() 订单号OrderId="+orderId);
		}
		List list=null;
		if(ObjectUtils.isNotEmpty(orderId)){
			String hql=" select os.orderId as orderId,os.productCode as productCode,os.pnum as pnum,p.productName as productName "
					 + " from LvOrderDetails os ,LvProduct p where os.productCode=p.code and os.orderId=:orderId";
			Map<String,Object> param=new HashMap<String,Object>();
			param.put("orderId", orderId);
			list=lvlogicReadDao.getMapListByHql(hql, param).getList();
		}
		if (logger.isInfoEnabled()){
			logger.info("***** OrderDetailsServiceImpl.findAllByOrderId() method begin*****");
		}
		return list;
	}
	
	
}
