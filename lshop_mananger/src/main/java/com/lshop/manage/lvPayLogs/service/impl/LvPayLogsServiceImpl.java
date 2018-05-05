package com.lshop.manage.lvPayLogs.service.impl;

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
import com.lshop.common.pojo.logic.LvPayLogs;
import com.lshop.common.util.Constants;
import com.lshop.common.util.DateUtils;
import com.lshop.common.util.StoreHelpUtil;
import com.lshop.common.util.StringUtil;
import com.lshop.manage.lvPayLogs.service.LvPayLogsService;
@Service("LvPayLogsService")
public class LvPayLogsServiceImpl implements LvPayLogsService{
	private static final Log logger	= LogFactory.getLog(LvPayLogsServiceImpl.class);
	@Resource 
	private HibernateBaseDAO dao;
	
	/**
	 * 
	 * @Method: getList 
	 * @Description:  [分页查询支付日志信息列表]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-11上午11:10:35]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-11上午11:10:35]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Pagination getList(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductImageServiceImpl.getList() method begin*****");
		}
		
		String orderField = dto.getAsString("orderField");
		String orderDirection = dto.getAsString("orderDirection");
		SimplePage page = (SimplePage)dto.get("page");
		LvPayLogs lvPayLogs=(LvPayLogs) dto.get("lvPayLogs");
		Map<String ,Object> params=new HashMap<String ,Object>();
		StringBuilder hql = new StringBuilder("from LvPayLogs o where 1=1 ");
		if(lvPayLogs!=null){
			if(ObjectUtils.isNotEmpty(lvPayLogs.getStartTime())&&ObjectUtils.isNotEmpty(lvPayLogs.getEndTime())){//下单时间
				hql.append(" and paydate>:startTime and paydate<:endTime");
				params.put("startTime", DateUtils.convertToDateTime(lvPayLogs.getStartTime()+" 00:00:00"));
            	params.put("endTime", DateUtils.convertToDateTime(lvPayLogs.getEndTime()+" 23:59:59"));
            }
			if(ObjectUtils.isNotEmpty(lvPayLogs.getOid())){//订单编号
				hql.append(" and oid = :oid ");
				params.put("oid", lvPayLogs.getOid());
			}
		}
		//判断当前是商城入口，还是商家入口
		hql.append(StoreHelpUtil.getNoShopRelevanceString(dto.getAsString("flag"), "storeId", "o"));
		if(ObjectUtils.isNotEmpty(orderField)&&ObjectUtils.isNotEmpty(orderDirection)){
			hql.append(" order by "+ orderField+" "+orderDirection);
		}
		Finder finder = Finder.create(hql.toString());
		finder.setParams(params);
	
		return dao.find(finder, page.getPageNum(), page.getNumPerPage());
	}
    /**
     * 
     * @Method: delete 
     * @Description:  [删除支付日志信息]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-7-11 上午11:11:28]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-7-11 上午11:11:28]   
     * @UpdateRemark: [说明本次修改内容]  
     * @throws
     */
	@Override
	public void delete(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvPayLogsServiceImpl.delete() method begin*****");
		}
		LvPayLogs lvPayLogs=(LvPayLogs) dto.get("lvPayLogs");
		dao.delete(lvPayLogs);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvPayLogsServiceImpl.delete() method end*****");
		}
		
	}

	/**
	 * 
	 * @Method: save 
	 * @Description:  [新增支付日志信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-11 上午11:11:58]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-11 上午11:11:58]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Dto save(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvPayLogsServiceImpl.save() method begin*****");
		}
		LvPayLogs lvPayLogs=(LvPayLogs) dto.get("lvPayLogs");
		if(ObjectUtils.isNotEmpty(dto.getAsString("flag"))){//设置店铺标示
			lvPayLogs.setStoreId(dto.getAsString("flag"));
		}else{
			lvPayLogs.setStoreId("tvpadcn");
		}
		dao.save(lvPayLogs);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvPayLogsServiceImpl.save() method end*****");
		}
		return null;
	}
	
	/**
	 * 
	 * @Method: get 
	 * @Description:  [根据id查询支付日志详情]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-11 上午10:29:47]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-11 上午10:29:47]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public LvPayLogs get(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvPayLogsServiceImpl.get() method begin*****");
		}
		LvPayLogs lvPayLogs=(LvPayLogs) dto.get("lvPayLogs");
		lvPayLogs=(LvPayLogs) dao.load(LvPayLogs.class, lvPayLogs.getId());
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvPayLogsServiceImpl.get() method end*****");
		}
		return lvPayLogs;
	}


}
