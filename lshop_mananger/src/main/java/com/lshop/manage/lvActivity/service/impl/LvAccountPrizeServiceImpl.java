/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvActivity.service.impl;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.lshop.common.activity.constant.ActivityConstant;
import com.lshop.common.pojo.logic.LvAccountPhysicalTicket;
import com.lshop.common.pojo.logic.LvAccountPrize;
import com.lshop.manage.lvActivity.service.LvAccountPrizeService;
import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.service.impl.BaseServiceImpl;
import com.gv.core.util.ObjectUtils;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */
@Service("LvAccountPrizeService")
public class LvAccountPrizeServiceImpl extends BaseServiceImpl implements LvAccountPrizeService {
	private static final Log logger	= LogFactory.getLog(LvAccountPrizeServiceImpl.class);
	@Resource 
	private HibernateBaseDAO dao;
	/**
	 * 获得单独的实体信息
	 */
	public LvAccountPrize get(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvAccountPrizeServiceImpl.get() method begin*****");
		}
		LvAccountPrize lvAccountPrize = (LvAccountPrize)dto.get("model");
		lvAccountPrize = (LvAccountPrize)dao.load(LvAccountPrize.class, lvAccountPrize.getId());
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvAccountPrizeServiceImpl.get() method end*****");
		}
		return  lvAccountPrize;
	}
	/**
	 * 获得单独的实体信息
	 */
	public LvAccountPrize findByCode(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()) {
			logger.info("***** LvAccountPrizeServiceImpl.findByCode() method begin*****");
		}
		String accountPrizeCode = (String) dto.get("accountPrizeCode");
		LvAccountPrize lvAccountPrize = null;
		if (ObjectUtils.isNotEmpty(accountPrizeCode)) {
			lvAccountPrize = (LvAccountPrize) dao.findUniqueByProperty(LvAccountPrize.class, "code", accountPrizeCode);
		}
		if (logger.isInfoEnabled()) {
			logger.info("***** LvAccountPrizeServiceImpl.findByCode() method end*****");
		}
		 return  lvAccountPrize;
	}

	/**
	 * 
	 * @Method: findByAcountPrizeCode 
	 * @Description:  [根据用户奖品code查询实物兑奖地址信息]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2014-8-19 上午10:45:24]   
	 * @UpdateUser:   [liaoxj]     
	 * @UpdateDate:   [2014-8-19 上午10:45:24]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public LvAccountPhysicalTicket findByAcountPrizeCode(Dto dto)throws ServiceException {
		if (logger.isInfoEnabled()) {
			logger.info("***** LvAccountPrizeServiceImpl.findByAcountPrizeCode() method begin*****");
		}
		String accountPrizeCode=(String) dto.get("accountPrizeCode");
		LvAccountPhysicalTicket apt=null;
		if(ObjectUtils.isNotEmpty(accountPrizeCode)){
			 apt= (LvAccountPhysicalTicket) dao.findUniqueByProperty(LvAccountPhysicalTicket.class, "accountPrizeCode", accountPrizeCode);
		}
		
		if (logger.isInfoEnabled()) {
			logger.info("***** LvAccountPrizeServiceImpl.findByAcountPrizeCode() method end*****");
		}
		return apt;
	}
	
	/**
	 * 
	 * @Method: updateAccountPrizeStatus 
	 * @Description:  [更新用户发货状态和实物兑奖收货地址信息]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2014-8-19 上午10:46:00]   
	 * @UpdateUser:   [liaoxj]     
	 * @UpdateDate:   [2014-8-19 上午10:46:00]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public void updateAccountPrizeStatus(Dto dto)throws ServiceException{
		if (logger.isInfoEnabled()) {
			logger.info("***** LvAccountPrizeServiceImpl.updateAccountPrizeStatus() method begin*****");
		}
		LvAccountPhysicalTicket lvAccountPhysicalTicket=(LvAccountPhysicalTicket) dto.get("lvAccountPhysicalTicket");
		LvAccountPrize lvAccountPrize=(LvAccountPrize) dto.get("lvAccountPrize");
		//更新发货地址信息
		dao.update(lvAccountPhysicalTicket);
		
		//更新奖品发货状态
		String hql="update LvAccountPrize set status=:status where code=:code";
		Map<String,Object> param=new HashMap<String, Object>();
		param.put("status", ActivityConstant.ACCOUNT_PRIZE_STATUS_LOTTERY_SEND);
		param.put("code", lvAccountPrize.getCode());
		dao.update(hql, param);
		
		if (logger.isInfoEnabled()) {
			logger.info("***** LvAccountPrizeServiceImpl.updateAccountPrizeStatus() method end*****");
		}
	}
}
