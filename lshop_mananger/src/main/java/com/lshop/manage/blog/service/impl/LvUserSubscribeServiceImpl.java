package com.lshop.manage.blog.service.impl;

import java.util.List;
import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.SimplePage;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.service.impl.BaseServiceImpl;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvUserSubscribe;
import com.lshop.manage.blog.service.LvUserSubscribeService;

/**
 * 
 *  
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.blog.service.impl.LvUserSubscribeServiceImpl.java]  
 * @ClassName:    [LvUserSubscribeServiceImpl]   
 * @Description:  [用户博客文章邮件订阅管理关联关系-数据访问层]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-6-29 下午05:00:01]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-6-29 下午05:00:01]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v1.0] 
 *
 */
@Service("LvUserSubscribeService")
public class LvUserSubscribeServiceImpl extends BaseServiceImpl implements LvUserSubscribeService{

	private static final Log logger	= LogFactory.getLog(LvUserSubscribeServiceImpl.class);
	@Resource 
	private HibernateBaseDAO dao;
	private LvUserSubscribe lvUserSubscribe;

	/**
	 * 
	 * @Method: list 
	 * @Description:  [查询博客订阅的用户列表]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-6-29 下午05:01:19]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-6-29 下午05:01:19]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public List list(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvUserSubscribeServiceImpl.list() method begin*****");
		}
		SimplePage page = (SimplePage)dto.get("page");
		String hql="from LvUserSubscribe where 1=1 " ;
		if(ObjectUtils.isNotEmpty(dto.getAsString("flag"))){//店铺标示
			hql+=" and storeId='"+dto.getAsString("flag")+"'";
		}
		hql+="order by id desc";
		if (logger.isInfoEnabled()){
			logger.info("***** LvUserSubscribeServiceImpl.list() method end*****");
		}
		return dao.find(hql,null);
	}
	/**
	 * 
	 * @Method: save 
	 * @Description:  [新增博客订阅的用户]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-6-29 下午05:01:25]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-6-29 下午05:01:25]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public LvUserSubscribe save(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvUserSubscribeServiceImpl.save() method begin*****");
		}
		lvUserSubscribe=(LvUserSubscribe) dto.get("model");
		if(ObjectUtils.isNotEmpty(dto.getAsString("flag"))){//设置店铺标示
			lvUserSubscribe.setStoreId(dto.getAsString("flag"));
		}else{
			lvUserSubscribe.setStoreId("tvpadcn");
		}
		dao.save(lvUserSubscribe);
		if (logger.isInfoEnabled()){
			logger.info("***** LvUserSubscribeServiceImpl.save() method end*****");
		}
		return lvUserSubscribe;
	}
	
	/**
	 * 
	 * @Method: get 
	 * @Description:  [根据用户id查询博客订阅的用户列表详细信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-6-29 下午05:01:30]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-6-29 下午05:01:30]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public LvUserSubscribe get(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvUserSubscribeServiceImpl.get() method begin*****");
		}
		lvUserSubscribe=(LvUserSubscribe) dto.get("model");
		String hql="from LvUserSubscribe where uid="+lvUserSubscribe.getUid()+"";
		List list=dao.find(hql, null);
		if(list!=null&&list.size()>0){
			lvUserSubscribe=(LvUserSubscribe) list.get(0);
		}
		if (logger.isInfoEnabled()){
			logger.info("***** LvUserSubscribeServiceImpl.get() method end*****");
		}
		return lvUserSubscribe;
	}
	
	/**
	 * 
	 * @Method: getUserSubscribe 
	 * @Description:  [根据序号查询博客订阅的用户列表详细信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-6-29 下午05:01:34]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-6-29 下午05:01:34]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public LvUserSubscribe getUserSubscribe(Dto dto)throws  ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvUserSubscribeServiceImpl.getUserSubscribe() method begin*****");
		}
		lvUserSubscribe=(LvUserSubscribe) dto.get("model");
		lvUserSubscribe=(LvUserSubscribe) dao.load(LvUserSubscribe.class, lvUserSubscribe.getId());
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvUserSubscribeServiceImpl.getUserSubscribe() method end*****");
		}
		return lvUserSubscribe;
	}
	
	/**
	 * 
	 * @Method: delete 
	 * @Description:  [删除博客订阅的用户信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-6-29 下午05:01:39]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-6-29 下午05:01:39]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public LvUserSubscribe delete(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvUserSubscribeServiceImpl.delete() method begin*****");
		}
		lvUserSubscribe=(LvUserSubscribe) dto.get("model");
		if(lvUserSubscribe==null){
			return lvUserSubscribe;
		}
		lvUserSubscribe=getUserSubscribe(dto);
		dao.delete(lvUserSubscribe);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvUserSubscribeServiceImpl.delete() method begin*****");
		}
		return lvUserSubscribe;
		
	}


	public LvUserSubscribe getLvUserSubscribe() {
		return lvUserSubscribe;
	}
	public void setLvUserSubscribe(LvUserSubscribe lvUserSubscribe) {
		this.lvUserSubscribe = lvUserSubscribe;
	}


	
}
