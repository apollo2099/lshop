package com.lshop.manage.blog.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.scheduling.quartz.CronTriggerBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.call.StatusCode;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.datastructure.page.SimplePage;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.service.impl.BaseServiceImpl;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvBlogSubscribe;
import com.lshop.common.util.StringUtil;
import com.lshop.manage.blog.service.LvBlogSubscribeService;
/**
 * 
 *  
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.blog.service.impl.LvBlogSubscribeServieImpl.java]  
 * @ClassName:    [LvBlogSubscribeServieImpl]   
 * @Description:  [邮件订阅管理-数据访问层]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-6-29 下午04:47:37]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-6-29 下午04:47:37]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v1.0] 
 *
 */
@Service("LvBlogSubscribeService")
public class LvBlogSubscribeServieImpl extends BaseServiceImpl implements LvBlogSubscribeService{
	private static final Log logger	= LogFactory.getLog(LvBlogSubscribeServieImpl.class);
	@Resource 
	private HibernateBaseDAO dao;

	private LvBlogSubscribe lvBlogSubscribe;
	
	/**
	 * 
	 * @Method: list 
	 * @Description:  [分页查询邮件订阅列表信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-6-29 下午04:48:18]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-6-29 下午04:48:18]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Pagination list(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogSubscribeServieImpl.list() method begin*****");
		}
		SimplePage page = (SimplePage)dto.get("page");
		lvBlogSubscribe=(LvBlogSubscribe)dto.get("model");
		
		String hql="from LvBlogSubscribe where 1=1 ";
		if(lvBlogSubscribe!=null){
			if(lvBlogSubscribe.getId()!=null){
				hql+=" and id="+lvBlogSubscribe.getId()+"";
			}
			if(lvBlogSubscribe.getTitle()!=null){
				hql+=" and title like '%"+lvBlogSubscribe.getTitle()+"%'";
			}
		}
		if(ObjectUtils.isNotEmpty(dto.getAsString("flag"))){//店铺标示
			String storeList=StringUtil.stringFormart(dto.getAsString("flag"), ",");
			hql+=" and storeId in ("+storeList+")";
		}
		hql+="order by id desc";
		return dao.find(Finder.create(hql),
				page.getPageNum(), page.getNumPerPage());
	}
	@Override
	public Dto add(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @Method: delete 
	 * @Description:  [删除邮件订阅信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-6-29 下午04:49:05]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-6-29 下午04:49:05]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Dto delete(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogSubscribeServieImpl.delete() method begin*****");
		}
		lvBlogSubscribe=(LvBlogSubscribe)dto.get("model");
		lvBlogSubscribe=(LvBlogSubscribe)dao.load(LvBlogSubscribe.class,lvBlogSubscribe.getId());
		dao.delete(lvBlogSubscribe);
		result.setAppCode(StatusCode.M_Success);
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogSubscribeServieImpl.delete() method end*****");
		}
		return result;
	}

	/**
	 * 
	 * @Method: get 
	 * @Description:  [根据序号查询邮件订阅详情]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-6-29 下午04:49:26]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-6-29 下午04:49:26]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public LvBlogSubscribe get(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogSubscribeServieImpl.get() method begin*****");
		}
		lvBlogSubscribe=(LvBlogSubscribe)dto.get("model");
		lvBlogSubscribe=(LvBlogSubscribe)dao.load(LvBlogSubscribe.class, lvBlogSubscribe.getId());
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogSubscribeServieImpl.get() method end*****");
		}
		return lvBlogSubscribe;
	}

	
    /**
     * 
     * @Method: save 
     * @Description:  [保存邮件订阅信息]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-6-29 下午04:49:58]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-6-29 下午04:49:58]   
     * @UpdateRemark: [说明本次修改内容]  
     * @throws
     */
	@Override
	public Boolean save(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogSubscribeServieImpl.save() method begin*****");
		}
		lvBlogSubscribe=(LvBlogSubscribe)dto.get("model");
		String hql="from LvBlogSubscribe where 1=1";
		if(ObjectUtils.isNotEmpty(dto.getAsString("flag"))){//设置店铺标示
		   hql+=" and storeId='"+dto.getAsString("flag")+"'";
		   lvBlogSubscribe.setStoreId(dto.getAsString("flag"));
		}else{
		   hql+=" and storeId='tvpadcn'";
		   lvBlogSubscribe.setStoreId("tvpadcn");
		}
		List list=dao.find(hql, null);
		if(list.size()>0){
			return false;
		}
		
		dao.save(lvBlogSubscribe);
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogSubscribeServieImpl.save() method end*****");
		}
		return true;
	}
    
	/**
	 * 
	 * @Method: update 
	 * @Description:  [修改邮件订阅信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-6-29 下午04:50:20]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-6-29 下午04:50:20]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public LvBlogSubscribe update(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogSubscribeServieImpl.update() method begin*****");
		}
		lvBlogSubscribe=(LvBlogSubscribe)dto.get("model");
		dao.update(lvBlogSubscribe);
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogSubscribeServieImpl.update() method end*****");
		}
		return lvBlogSubscribe;
	}
	

	
	
	public LvBlogSubscribe getLvBlogSubscribe() {
		return lvBlogSubscribe;
	}

	public void setLvBlogSubscribe(LvBlogSubscribe lvBlogSubscribe) {
		this.lvBlogSubscribe = lvBlogSubscribe;
	}


}
