package com.lshop.manage.lvOrder.service.impl;

import java.util.Date;
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
import com.lshop.common.pojo.logic.LvOrderAddress;
import com.lshop.common.pojo.logic.LvOrderLogs;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.lvOrder.service.LvOrderAddressService;
/**
 * 
 *  
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvOrder.service.impl.LvOrderAddressServiceImpl.java]  
 * @ClassName:    [LvOrderAddressServiceImpl]   
 * @Description:  [订单地址信息管理-数据库访问层]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-7-18 上午11:07:50]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-7-18 上午11:07:50]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v1.0] 
 *
 */
@Service("LvOrderAddressService")
public class LvOrderAddressServiceImpl implements LvOrderAddressService {
	private static final Log logger	= LogFactory.getLog(LvOrderAddressServiceImpl.class);
	@Resource 
	private HibernateBaseDAO dao;
	/**
	 * 
	 * @Method: getOrderAddress 
	 * @Description:  [根据订单编号查询订单地址信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-18 上午11:07:55]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-18 上午11:07:55]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public LvOrderAddress getOrderAddress(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderAddressServiceImpl.getOrderAddress dto() method begin*****");
		}
		String orderId=dto.getAsString("orderId");
		LvOrderAddress lvOrderAddress=this.getOrderAddress(orderId);

		
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderAddressServiceImpl.getOrderAddress dto() method end*****");
		}
		return lvOrderAddress;
	}
	
	
	public LvOrderAddress getOrderAddress(String orderId) throws ServiceException {
		LvOrderAddress lvOrderAddress= (LvOrderAddress) dao.findUniqueByProperty(LvOrderAddress.class, "orderId", orderId);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderAddressServiceImpl.getOrderAddress() method end*****");
		}
		return lvOrderAddress;
	}
    /**
     * 
     * @Method: save 
     * @Description:  [新增订单地址信息]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-7-18 上午11:08:02]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-7-18 上午11:08:02]   
     * @UpdateRemark: [说明本次修改内容]  
     * @throws
     */
	@Override
	public void save(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderAddressServiceImpl.save() method begin*****");
		}
		LvOrderAddress lvOrderAddress=(LvOrderAddress) dto.get("lvOrderAddress");
        dao.save(lvOrderAddress);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderAddressServiceImpl.save() method end*****");
		}
	}
	/**
	 * 
	 * @Method: update 
	 * @Description:  [更新订单地址信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-15 上午10:14:33]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-15 上午10:14:33]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public void update(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderAddressServiceImpl.update() method begin*****");
		}
		LvOrderAddress lvOrderAddress=(LvOrderAddress) dto.get("lvOrderAddress");
        dao.update(lvOrderAddress);
		
		//后台订单部分添加订单日志信息：
	    LvOrderLogs orderLog = new LvOrderLogs();
	    orderLog.setUname(lvOrderAddress.getModifyUserName());
		orderLog.setOrd(lvOrderAddress.getOrderId());
		orderLog.setCreateTime(new Date());
		orderLog.setCode(CodeUtils.getCode());
		orderLog.setOperate("编辑订单地址信息");
		dao.save(orderLog);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderAddressServiceImpl.update() method end*****");
		}
		
	}
	
	/**
	 * 
	 * @Method: isExistsByThirdOrderNum 
	 * @Description:  [验证订单地址信息是否已经存在]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2015-3-25 下午3:24:19]   
	 * @UpdateUser:   [liaoxj]     
	 * @UpdateDate:   [2015-3-25 下午3:24:19]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param thirdOrderNum 第三方订单号
	 * @return boolean 
	 */
	public boolean isExistsByThirdOrderNum(String thirdOrderNum)throws ServiceException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderAddressServiceImpl.isExistsByThirdOrderNum() method begin*****");
		}
		String hql=" from LvOrderAddress os " +
				   " where EXISTS(select 1 from LvOrder o where o.oid=os.orderId and o.isdelete<>-1 and o.thirdOrderNum=:thirdOrderNum)";
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("thirdOrderNum", thirdOrderNum);
		List list= dao.find(hql, param);
		if(list!=null&&list.size()>0){
			return true;
		}
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderAddressServiceImpl.isExistsByThirdOrderNum() method end*****");
		}
		return false;
	}

}
