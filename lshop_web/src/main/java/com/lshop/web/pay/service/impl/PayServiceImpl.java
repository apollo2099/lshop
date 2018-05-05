/**
 * <p>Title: OrderServiceImpl.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */
package com.lshop.web.pay.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.lshop.common.pojo.logic.LvOrder;
import com.lshop.common.pojo.logic.LvPayLogs;
import com.lshop.common.pojo.logic.LvPaymentStyle;
import com.lshop.common.util.Constants;
import com.lshop.web.pay.service.PayService;

@Service("PayService")
public class PayServiceImpl implements PayService {
	
	private static final Log logger	= LogFactory.getLog(PayServiceImpl.class);	

	@Resource
	private HibernateBaseDAO lvlogicReadDao;
	@Resource
	private HibernateBaseDAO lvlogicWriteDao;
	@Override
	public void addPayLog(Dto dto) {
		LvPayLogs lvPayLog=(LvPayLogs) dto.getDefaultPo();
		String hql="SELECT id FROM LvPayLogs WHERE oid=:oid AND remark=:remark";
		Map parm=new HashMap();
		parm.put("oid", lvPayLog.getOid());
		parm.put("remark", lvPayLog.getRemark());
		List list=(List) lvlogicReadDao.find(hql, parm);
		if (list==null||list.isEmpty()) {
			LvOrder order=(LvOrder)lvlogicReadDao.findUniqueByProperty(LvOrder.class, "oid", lvPayLog.getOid());
			if(order!=null){
			lvPayLog.setStoreId(order.getStoreId());
			}
			lvlogicWriteDao.save(lvPayLog);
		}
	}
	

	public LvPaymentStyle getLvPaymentStyle(Dto dto)throws ServiceException{//获取支付方式数据
		String payValue=dto.getAsString("payValue");
		String storeFlag=dto.getAsString("storeFlag");//店铺标志
		Map params=new HashMap();
		params.put("payValue", Short.parseShort(payValue));
		params.put("storeFlag", storeFlag);
		return (LvPaymentStyle)lvlogicReadDao.findUnique("from LvPaymentStyle where payValue=:payValue and storeFlag=:storeFlag and payType="+ Constants.PAYTYPE_LINEPAY, params);
	}

	
	

}
