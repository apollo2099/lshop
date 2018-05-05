package com.lshop.manage.lvOrderLogs.service.impl;

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
import com.lshop.common.pojo.logic.LvProductImage;
import com.lshop.common.util.Constants;
import com.lshop.common.util.DateUtils;
import com.lshop.common.util.StoreHelpUtil;
import com.lshop.common.util.StringUtil;
import com.lshop.manage.lvOrderLogs.service.LvOrderLogsService;
/**
 * 
 *  
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvOrderLogs.service.impl.LvOrderLogsServiceImpl.java]  
 * @ClassName:    [LvOrderLogsServiceImpl]   
 * @Description:  [订单日志管理-数据访问层]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-7-11 上午10:02:45]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-7-11 上午10:02:45]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v1.0] 
 *
 */
@Service("LvOrderLogsService")
public class LvOrderLogsServiceImpl implements LvOrderLogsService{
	private static final Log logger	= LogFactory.getLog(LvOrderLogsServiceImpl.class);
	@Resource 
	private HibernateBaseDAO dao;
	/**
	 * 
	 * @Method: getList 
	 * @Description:  [分页查询订单日志信息列表]  
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
		LvOrderLogs lvOrderLogs=(LvOrderLogs) dto.get("lvOrderLogs");
		Map<String ,Object> params=new HashMap<String ,Object>();
		StringBuilder hql = new StringBuilder("from LvOrderLogs o where 1=1 ");
		if(lvOrderLogs!=null){
			if(ObjectUtils.isNotEmpty(lvOrderLogs.getOrd())){//订单编号
				hql.append(" and ord=:ord");
				params.put("ord", lvOrderLogs.getOrd());
			}
			if(ObjectUtils.isNotEmpty(lvOrderLogs.getStartTime())&&ObjectUtils.isNotEmpty(lvOrderLogs.getEndTime())){
				hql.append(" and createTime>:startTime " +
					 " and createTime<:endTime ");
				params.put("startTime", DateUtils.convertToDateTime(lvOrderLogs.getStartTime()+" 00:00:00"));
            	params.put("endTime", DateUtils.convertToDateTime(lvOrderLogs.getEndTime()+" 23:59:59"));
			}
		}
		//判断当前是商城入口，还是商家入口
		hql.append(StoreHelpUtil.getNoShopRelevanceString(dto.getAsString("flag"), "storeId", "o"));
		if(!ObjectUtils.isNullOrEmptyString(orderField)&&!ObjectUtils.isNullOrEmptyString(orderDirection)){
			hql.append(" order by "+ orderField+" "+orderDirection);
		}
		Finder finder = Finder.create(hql.toString());
		finder.setParams(params);
		return dao.find(finder, page.getPageNum(), page.getNumPerPage());
	}
    /**
     * 
     * @Method: delete 
     * @Description:  [删除订单日志信息]  
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
			logger.info("***** LvOrderLogsServiceImpl.delete() method begin*****");
		}
		LvOrderLogs lvOrderLogs=(LvOrderLogs) dto.get("lvOrderLogs");
		dao.delete(lvOrderLogs);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderLogsServiceImpl.delete() method end*****");
		}
		
	}

	/**
	 * 
	 * @Method: save 
	 * @Description:  [新增订单日志信息]  
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
			logger.info("***** LvOrderLogsServiceImpl.save() method begin*****");
		}
		LvOrderLogs lvOrderLogs=(LvOrderLogs) dto.get("lvOrderLogs");
//		if(ObjectUtils.isNotEmpty(dto.getAsString("flag"))){//设置店铺标示
//			lvOrderLogs.setStoreId(dto.getAsString("flag"));
//		}else{
//			lvOrderLogs.setStoreId("tvpadcn");
//		}
		dao.save(lvOrderLogs);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderLogsServiceImpl.save() method end*****");
		}
		return null;
	}
	/**
	 * 
	 * @Method: get 
	 * @Description:  [根据id查询订单日志详情]    
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-11 上午10:31:04]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-11 上午10:31:04]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public LvOrderLogs get(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderLogsServiceImpl.get() method begin*****");
		}
		LvOrderLogs lvOrderLogs=(LvOrderLogs) dto.get("lvOrderLogs");
		lvOrderLogs=(LvOrderLogs) dao.load(LvOrderLogs.class, lvOrderLogs.getId());
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderLogsServiceImpl.get() method end*****");
		}
		return lvOrderLogs;
	}


}
