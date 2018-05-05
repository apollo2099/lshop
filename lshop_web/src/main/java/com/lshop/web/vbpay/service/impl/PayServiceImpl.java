/**
 * <p>Title: OrderServiceImpl.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */
package com.lshop.web.vbpay.service.impl;

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
import com.lshop.common.pojo.logic.LvPaymentStyle;
import com.lshop.common.pojo.logic.LvRechargePaylog;
import com.lshop.common.util.Constants;
import com.lshop.web.vbpay.service.PayService;

@Service("VBPayService")
public class PayServiceImpl implements PayService {
	
	private static final Log logger	= LogFactory.getLog(PayServiceImpl.class);	

	@Resource
	private HibernateBaseDAO lvlogicReadDao;
	@Resource
	private HibernateBaseDAO lvlogicWriteDao;
	@Override
	public void addPayLog(Dto dto)throws ServiceException {
	 LvRechargePaylog  log=(LvRechargePaylog)dto.getDefaultPo();
	 lvlogicWriteDao.save(log);
	}
	
	/**
	 * 获取支付方式
	 */
	public LvPaymentStyle getLvPaymentStyle(Dto dto) throws ServiceException {
		String storeFlag = dto.getAsString("storeFlag");
		Short payValue = Short.parseShort(dto.getAsString("payValue"));
		Map param = new HashMap();
		param.put("storeFlag", storeFlag);
		param.put("payType", Constants.PAYTYPE_LINEPAY_VB);
		param.put("payValue", payValue);
		String hql = "FROM LvPaymentStyle WHERE storeFlag=:storeFlag AND payType=:payType AND payValue=:payValue AND isActivity=1";
		List<LvPaymentStyle> list = lvlogicReadDao.find(hql, param);
		return list.isEmpty() ? null : list.get(0);
	}
}
