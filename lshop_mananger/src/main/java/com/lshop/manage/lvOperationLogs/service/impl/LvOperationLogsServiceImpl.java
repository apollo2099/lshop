package com.lshop.manage.lvOperationLogs.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.datastructure.page.SimplePage;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvCoupon;
import com.lshop.common.pojo.logic.LvCouponType;
import com.lshop.common.pojo.logic.LvOperationLogs;
import com.lshop.common.pojo.logic.LvOrder;
import com.lshop.common.pojo.logic.LvOrderLogs;
import com.lshop.common.util.DateUtils;
import com.lshop.common.util.StoreHelpUtil;
import com.lshop.manage.lvOperationLogs.service.LvOperationLogsService;

/**
 * 操作日志模块
 * @author zhengxue 2014-06-24
 *
 */
@Service("LvOperationLogsService")
public class LvOperationLogsServiceImpl implements LvOperationLogsService {
	
	private static final Log logger	= LogFactory.getLog(LvOperationLogsServiceImpl.class);
	@Resource 
	private HibernateBaseDAO dao;
	
	/**
	 * 查询列表
	 */
	@Override
	public Pagination getList(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvOperationLogsServiceImpl.getList() method begin*****");
		}
		
		//获取参数
		SimplePage page = (SimplePage)dto.get("page");
		LvOperationLogs lvOperationLogs=(LvOperationLogs) dto.get("lvOperationLogs");
		
		Map<String ,Object> params=new HashMap<String ,Object>();
		StringBuilder hql = new StringBuilder("from LvOperationLogs o where 1=1 ");
		if(lvOperationLogs!=null){
			if(ObjectUtils.isNotEmpty(lvOperationLogs.getOperationModule())){//模块名称
				hql.append(" and o.operationModule like :operationModule");
				params.put("operationModule", "%"+lvOperationLogs.getOperationModule().trim()+"%");
			}
			if(ObjectUtils.isNotEmpty(lvOperationLogs.getOperator())){//操作人
				hql.append(" and o.operator like :operator ");
				params.put("operator", "%"+lvOperationLogs.getOperator().trim()+"%");
			}
			if(ObjectUtils.isNotEmpty(lvOperationLogs.getOperationKey())){//操作关键字
				hql.append(" and o.operationKey like :operationKey ");
				params.put("operationKey", "%"+lvOperationLogs.getOperationKey().trim()+"%");
			}
			if(ObjectUtils.isNotEmpty(lvOperationLogs.getStartTime())&&ObjectUtils.isNotEmpty(lvOperationLogs.getEndTime())){//下单时间
            	hql.append(" and o.createtime>:startTime" +
            		 " and o.createtime<:endTime");
            	params.put("startTime", DateUtils.convertToDateTime(lvOperationLogs.getStartTime()+" 00:00:00"));
            	params.put("endTime", DateUtils.convertToDateTime(lvOperationLogs.getEndTime()+" 23:59:59"));
	        }
		}
		
		hql.append(" order by o.createtime desc");
	
		return  dao.getMapListByHql(hql.toString(), page.getPageNum(), page.getNumPerPage(), params);
	}

	/**
	 * 查看详情
	 */
	@Override
	public LvOperationLogs view(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvOperationLogsServiceImpl.view() method begin*****");
		}
		LvOperationLogs lvOperationLogs=(LvOperationLogs) dto.get("lvOperationLogs");
		lvOperationLogs=(LvOperationLogs) dao.load(LvOperationLogs.class, lvOperationLogs.getId());
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvOperationLogsServiceImpl.view() method end*****");
		}
		return lvOperationLogs;
	}

	/**
	 * 单个删除
	 */
	@Override
	public void delete(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvOperationLogsServiceImpl.delete() method begin*****");
		}
		LvOperationLogs lvOperationLogs=(LvOperationLogs) dto.get("lvOperationLogs");
		dao.delete(lvOperationLogs);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvOperationLogsServiceImpl.delete() method end*****");
		}
	}

	/**
	 * 导出excel
	 */
	@Override
	public List exportOperationLogs(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvOperationLogsServiceImpl.exportOperationLogs() method begin*****");
		}
		
		List list = new ArrayList(); //存放结果数据
		
		//获取导出数据
		String hql="from LvOperationLogs where id in (" + dto.getAsString("ids") + ") order by createtime desc";
		List<LvOperationLogs> logList = (List<LvOperationLogs>)dao.find(hql, null);
		
		if(null!=logList && logList.size()>0){
			//增加表头
			String[] title = new String[5];
			title[0] = "操作人";
			title[1] = "操作模块";
			title[2] = "操作关键字";
			title[3] = "操作详情";
			title[4] = "操作时间";
			list.add(title);
			
			//增加数据
			for(LvOperationLogs log : logList){
				String[] data = new String[5];
				data[0] = log.getOperator();
				data[1] = log.getOperationModule();
				data[2] = log.getOperationKey();
				data[3] = log.getOperationDetails();
				data[4] = DateUtils.formatDate(log.getCreatetime(), "yyyy-MM-dd HH:mm:ss");
				list.add(data);
			}
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvOperationLogsServiceImpl.exportOperationLogs() method end*****");
		}
		return list;
	}

	/**
	 * 新增
	 */
	@Override
	public Dto save(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
