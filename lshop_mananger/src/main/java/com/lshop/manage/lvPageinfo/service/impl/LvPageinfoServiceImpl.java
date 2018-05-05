package com.lshop.manage.lvPageinfo.service.impl;

import java.util.List;

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
import com.lshop.common.pojo.logic.LvPageinfo;
import com.lshop.manage.lvPageinfo.service.LvPageinfoService;
import com.lshop.manage.lvProductType.service.impl.LvProductTypeServiceImpl;
/**
 * 
 *  
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvPageinfo.service.impl.LvPageinfoServiceImpl.java]  
 * @ClassName:    [LvPageinfoServiceImpl]   
 * @Description:  [网站首页导航页面管理信息-数据库访问层]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-7-26 上午10:35:10]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-7-26 上午10:35:10]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v1.0] 
 *
 */
@Service("LvPageinfoService")
public class LvPageinfoServiceImpl implements LvPageinfoService{
	private static final Log logger	= LogFactory.getLog(LvPageinfoServiceImpl.class);
	@Resource 
	private HibernateBaseDAO dao;
	
	/**
	 * 
	 * @Method: getList 
	 * @Description:  [分页查询网站首页导航页面管理信息列表]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-26 上午10:24:09]   
	 * @UpdateUser:   [liaoxiongjian]   
	 * @UpdateDate:   [2012-7-26 上午10:24:09]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Pagination getList(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductTypeServiceImpl.getList() method end*****");
		}
		String orderField = dto.getAsString("orderField");
		String orderDirection = dto.getAsString("orderDirection");
		SimplePage page = (SimplePage)dto.get("page");
		LvPageinfo lvPageinfo=(LvPageinfo)dto.get("lvPageinfo");
		String hql="from LvPageinfo where 1=1";
		if(lvPageinfo!=null){
			if(ObjectUtils.isNotEmpty(lvPageinfo.getId())){
				hql+=" and id="+lvPageinfo.getId()+"";
			}
			if(ObjectUtils.isNotEmpty(lvPageinfo.getTitle())){
				hql+=" and title like '%"+lvPageinfo.getTitle().trim()+"%'";
			}
		}


		if(!StringUtil.IsNullOrEmpty(orderField)&&!StringUtil.IsNullOrEmpty(orderDirection)){
			hql+=" order by "+ orderField+" "+orderDirection;
		}
	
		return dao.find(Finder.create(hql), page.getPageNum(), page.getNumPerPage());
	}
	/**
	 * 
	 * @Method: delete 
	 * @Description:  [删除网站首页导航页面管理信息信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-26 上午10:24:55]   
	 * @UpdateUser:   [liaoxiongjian]   
	 * @UpdateDate:   [2012-7-26 上午10:24:55]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public void delete(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvPageinfoServiceImpl.delete() method begin*****");
		}
		LvPageinfo lvPageinfo=(LvPageinfo)dto.get("lvPageinfo");
		dao.delete(lvPageinfo);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvPageinfoServiceImpl.delete() method end*****");
		}
	}
	/**
	 * 
	 * @Method: get 
	 * @Description:  [根据id查询网站首页导航页面管理信息详情]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-26 上午10:25:09]   
	 * @UpdateUser:   [liaoxiongjian]   
	 * @UpdateDate:   [2012-7-26 上午10:25:09]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public LvPageinfo get(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvPageinfoServiceImpl.get() method begin*****");
		}
		LvPageinfo lvPageinfo=(LvPageinfo)dto.get("lvPageinfo");
		lvPageinfo=(LvPageinfo) dao.load(LvPageinfo.class, lvPageinfo.getId());
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvPageinfoServiceImpl.get() method end*****");
		}
		return lvPageinfo;
	}
	/**
	 * 
	 * @Method: save 
	 * @Description:  [新增网站首页导航页面管理信息信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-26 上午10:25:19]   
	 * @UpdateUser:   [liaoxiongjian]   
	 * @UpdateDate:   [2012-7-26 上午10:25:19]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Dto save(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvPageinfoServiceImpl.save() method begin*****");
		}
		LvPageinfo lvPageinfo=(LvPageinfo)dto.get("lvPageinfo");
		dao.save(lvPageinfo);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvPageinfoServiceImpl.save() method end*****");
		}
		return null;
	}
	/**
	 * 
	 * @Method: update 
	 * @Description:  [修改网站首页导航页面管理信息信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-26 上午10:25:29]   
	 * @UpdateUser:   [liaoxiongjian]   
	 * @UpdateDate:   [2012-7-26 上午10:25:29]   
	 * @UpdateRemark: [说明本次修改内容]   
	 * @throws
	 */
	@Override
	public void update(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvPageinfoServiceImpl.update() method begin*****");
		}
		LvPageinfo lvPageinfo=(LvPageinfo)dto.get("lvPageinfo");
		dao.update(lvPageinfo);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvPageinfoServiceImpl.update() method end*****");
		}
		
	}
	
}
