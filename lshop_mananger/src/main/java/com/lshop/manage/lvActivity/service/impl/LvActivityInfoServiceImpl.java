package com.lshop.manage.lvActivity.service.impl;

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
import com.gv.core.util.StringUtil;
import com.lshop.common.pojo.logic.LvActivity;
import com.lshop.common.pojo.logic.LvActivityInfo;
import com.lshop.common.util.DateUtils;
import com.lshop.manage.lvActivity.service.LvActivityInfoService;
@Service("LvActivityInfoService")
public class LvActivityInfoServiceImpl implements LvActivityInfoService{
	private static final Log logger	= LogFactory.getLog(LvActivityInfoServiceImpl.class);
	@Resource 
	private HibernateBaseDAO dao;


	@Override
	public Pagination getList(Dto dto) throws ServiceException {
		return null;
	}
    /**
     * 
     * @Method: delete 
     * @Description:  [删除产品活动扩展属性信息]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-7-4 上午11:11:28]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-7-4 上午11:11:28]   
     * @UpdateRemark: [说明本次修改内容]  
     * @throws
     */
	@Override
	public void delete(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** lvActivityServiceImpl.delete() method begin*****");
		}
		if(ObjectUtils.isNotEmpty(dto.get("activityCode"))){
			String hql="delete from LvActivityInfo where activityCode='"+dto.get("activityCode")+"'";
			dao.delete(hql,null);
		}

		if (logger.isInfoEnabled()){
			logger.info("***** lvActivityServiceImpl.delete() method end*****");
		}
		
	}

	/**
	 * 
	 * @Method: save 
	 * @Description:  [新增产品活动扩展属性信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-4 上午11:11:58]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-4 上午11:11:58]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Dto save(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** lvActivityServiceImpl.save() method begin*****");
		}
		LvActivityInfo lvActivityInfo=(LvActivityInfo) dto.get("lvActivityInfo");
		dao.save(lvActivityInfo);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvActivityInfoServiceImpl.save() method end*****");
		}
		return null;
	}

	
	
	/**
	 * 
	 * @Method: get 
	 * @Description:  [根据id查询产品活动扩展属性信息详情]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-4 上午11:16:06]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-4 上午11:16:06]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public LvActivityInfo get(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvActivityInfoServiceImpl.get() method begin*****");
		}
		LvActivityInfo lvActivityInfo=(LvActivityInfo) dto.get("lvActivityInfo");
		lvActivityInfo=(LvActivityInfo) dao.load(LvActivityInfo.class, lvActivityInfo.getId());
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvActivityInfoServiceImpl.get() method end*****");
		}
		return lvActivityInfo;
	}
	

	/**
	 * 
	 * @Method: update 
	 * @Description:  [修改产品活动扩展属性信息(限时/限量)]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-9 下午02:27:20]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-9 下午02:27:20]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public void update(Dto dto) throws ServiceException {
		LvActivityInfo lvActivityInfo=(LvActivityInfo) dto.get("lvActivityInfo");
		if(ObjectUtils.isNotEmpty(lvActivityInfo.getPropertyValue())&&ObjectUtils.isNotEmpty(lvActivityInfo.getActivityCode())){
			if(ObjectUtils.isNotEmpty(lvActivityInfo.getPropertyKey())){
				if(lvActivityInfo.getPropertyKey().equals("counts")){
					String hql="update LvActivityInfo set propertyValue='"+lvActivityInfo.getPropertyValue()+"', " +
							" modifyTime='"+DateUtils.formatDate(lvActivityInfo.getModifyTime(), "")+"'," +
							" modifyUserId="+lvActivityInfo.getModifyUserId()+"," +
							" modifyUserName='"+lvActivityInfo.getModifyUserName()+"'" +
					" where activityCode='"+lvActivityInfo.getActivityCode()+"' and propertyKey='counts'";
			        dao.update(hql,null);
				}else if(lvActivityInfo.getPropertyKey().equals("startTime")){
					String hql="update LvActivityInfo set propertyValue='"+lvActivityInfo.getPropertyValue()+"', " +
					" modifyTime='"+DateUtils.formatDate(lvActivityInfo.getModifyTime(), "")+"'," +
					" modifyUserId="+lvActivityInfo.getModifyUserId()+"," +
					" modifyUserName='"+lvActivityInfo.getModifyUserName()+"'" +
					" where activityCode='"+lvActivityInfo.getActivityCode()+"' and propertyKey='startTime'";
			        dao.update(hql,null);
				}else if(lvActivityInfo.getPropertyKey().equals("endTime")){
					String hql="update LvActivityInfo set propertyValue='"+lvActivityInfo.getPropertyValue()+"' ," +
					" modifyTime='"+DateUtils.formatDate(lvActivityInfo.getModifyTime(), "")+"'," +
					" modifyUserId="+lvActivityInfo.getModifyUserId()+"," +
					" modifyUserName='"+lvActivityInfo.getModifyUserName()+"'" +
					" where activityCode='"+lvActivityInfo.getActivityCode()+"' and propertyKey='endTime'";
			        dao.update(hql,null);
				}
			}
		}	
	}
}
