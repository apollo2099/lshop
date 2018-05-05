package com.lshop.manage.lvOrderRemind.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.datastructure.page.SimplePage;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvOrderLogs;
import com.lshop.common.pojo.logic.LvOrderRemind;
import com.lshop.common.util.Constants;
import com.lshop.common.util.DateUtils;
import com.lshop.common.util.StoreHelpUtil;
import com.lshop.common.util.StringUtil;
import com.lshop.manage.lvOrder.service.impl.LvOrderServiceImpl;
import com.lshop.manage.lvOrderRemind.service.LvOrderRemindService;
/**
 * 
 *  
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvOrderRemind.service.impl.LvOrderRemindServiceImpl.java]  
 * @ClassName:    [LvOrderRemindServiceImpl]   
 * @Description:  [订单提醒管理-数据访问层]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-7-20 下午05:26:02]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-7-20 下午05:26:02]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v1.0] 
 *
 */
@Service("LvOrderRemindService")
public class LvOrderRemindServiceImpl implements LvOrderRemindService {
	private static final Log logger	= LogFactory.getLog(LvOrderRemindServiceImpl.class);
	@Resource 
	private HibernateBaseDAO dao;
	
	/**
	 * 
	 * @Method: getList 
	 * @Description:  [分页查询订单提醒信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-23 上午09:27:43]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-23 上午09:27:43]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Pagination getList(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderRemindServiceImpl.getList() method begin*****");
		}

		String orderField = dto.getAsString("orderField");
		String orderDirection = dto.getAsString("orderDirection");
		SimplePage page = (SimplePage)dto.get("page");
		LvOrderRemind lvOrderRemind=(LvOrderRemind) dto.get("lvOrderRemind");
		Map<String ,Object> params=new HashMap<String ,Object>();
		StringBuilder hql = new StringBuilder(" select o.id as id,o.orderId as orderId,o.remindNum as remindNum," +
				   " o.remindTime as remindTime,la.nickname as nickname,la.email as email" +
				   " from LvOrderRemind o,LvAccount la " +
				   " where la.code=o.userCode " +
				   " and exists (select  lo from LvOrder lo where o.orderId=lo.oid and lo.status=0 and lo.payStatus=1 )");
		if(lvOrderRemind!=null){
			if(ObjectUtils.isNotEmpty(lvOrderRemind.getOrderId())){
				hql.append(" and o.orderId =:orderId");
				params.put("orderId", lvOrderRemind.getOrderId());
			}
			if(ObjectUtils.isNotEmpty(lvOrderRemind.getStartTime())&&ObjectUtils.isNotEmpty(lvOrderRemind.getEndTime())){
				hql.append(" and o.remindTime>:startTime" +
			    	 " and o.remindTime<:endTime");
				params.put("startTime", DateUtils.convertToDateTime(lvOrderRemind.getStartTime()+" 00:00:00"));
            	params.put("endTime", DateUtils.convertToDateTime(lvOrderRemind.getEndTime()+" 23:59:59"));
			}
		}
		//判断当前是商城入口，还是商家入口
		hql.append(StoreHelpUtil.getNoShopRelevanceString(dto.getAsString("flag"), "storeId", "o"));
	
		hql.append(" order by o.orderId desc ,o.remindNum desc");
		return  dao.getMapListByHql(hql.toString(), page.getPageNum(), page.getNumPerPage(), params);
	}
	/**
	 * 
	 * @Method: get 
	 * @Description:  [根据订单提醒id查询详情]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-23 上午09:27:03]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-23 上午09:27:03]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public LvOrderRemind get(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderRemindServiceImpl.get() method begin*****");
		}
		LvOrderRemind lvOrderRemind=(LvOrderRemind) dto.get("lvOrderRemind");
		lvOrderRemind=(LvOrderRemind) dao.load(LvOrderRemind.class, lvOrderRemind.getId());
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderRemindServiceImpl.get() method end*****");
		}
		return lvOrderRemind;
	}
    /**
     * 
     * @Method: delete 
     * @Description:  [删除订单提醒信息]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-7-23 上午09:26:24]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-7-23 上午09:26:24]   
     * @UpdateRemark: [说明本次修改内容]  
     * @throws
     */
	@Override
	public void delete(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderRemindServiceImpl.delete() method begin*****");
		}
		String ids=(String) dto.get("ids");
		String hql="delete from LvOrderRemind where id in("+ids+")";
		dao.delete(hql,null);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderRemindServiceImpl.delete() method end*****");
		}
	}


	
	

}
