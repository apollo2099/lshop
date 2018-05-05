package com.lshop.manage.blog.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.call.StatusCode;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.datastructure.page.SimplePage;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.service.impl.BaseServiceImpl;
import com.gv.core.util.BeanUtils;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvBlogTags;
import com.lshop.common.util.StoreHelpUtil;
import com.lshop.common.util.StringUtil;
import com.lshop.manage.blog.service.LvBlogTagsService;
/**
 * 
 *  
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.blog.service.impl.LvBlogTagsServiceImpl.java]  
 * @ClassName:    [LvBlogTagsServiceImpl]   
 * @Description:  [Blog标签管理-数据访问层]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-6-29 下午04:53:42]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-6-29 下午04:53:42]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v1.0] 
 *
 */
@Service("LvBlogTagsService")
public class LvBlogTagsServiceImpl extends BaseServiceImpl implements LvBlogTagsService{


	private static final Log logger	= LogFactory.getLog(LvBlogTagsServiceImpl.class);
	@Resource 
	private HibernateBaseDAO dao;
	private LvBlogTags lvBlogTags;

	/**
	 * 
	 * @Method: list 
	 * @Description:  [分页查询Blog标签列表]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-6-29 下午04:55:15]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-6-29 下午04:55:15]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Pagination list(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogTagsServiceImpl.list() method begin*****");
		}
		SimplePage page = (SimplePage)dto.get("page");
		lvBlogTags=(LvBlogTags)dto.get("model");
		Map parms=new HashMap();
		StringBuilder hql = new StringBuilder("from LvBlogTags  t where isShow=1 ") ;
			if(lvBlogTags!=null){
				if(ObjectUtils.isNotEmpty(lvBlogTags.getId())){
					hql.append(" and id=:id ");
				}
				if(ObjectUtils.isNotEmpty(lvBlogTags.getTagName())){
					hql.append(" and tagName like :tagName ");
				}
				if(ObjectUtils.isNotEmpty(lvBlogTags.getStoreId())){
					hql.append(" and storeId like :storeId ");
				}
				parms=BeanUtils.describeForHQL(lvBlogTags);	
			}
	    hql.append(StoreHelpUtil.getStoreRelevanceString(dto.getAsString("flag"),"storeId","t"));
		hql.append(" order by storeId desc,orderId desc");
		Finder finder = Finder.create(hql.toString());
		finder.setParams(parms);
		return dao.find(finder,page.getPageNum(), page.getNumPerPage());
	}
	
	@Override
	public Dto add(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @Method: delete 
	 * @Description:  [删除Blog标签信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-6-29 下午04:55:24]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-6-29 下午04:55:24]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Dto delete(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogTagsServiceImpl.delete() method begin*****");
		}
		lvBlogTags=(LvBlogTags)dto.get("model");
		String hql="update LvBlogTags set isShow=0 where  id="+lvBlogTags.getId()+"";
		this.dao.update(hql,null);
		result.setAppCode(StatusCode.M_Success);
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogTagsServiceImpl.delete() method end*****");
		}
		return result;
	}

	/**
	 * 
	 * @Method: get 
	 * @Description:  [根据序号查询Blog标签详细信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-6-29 下午04:55:28]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-6-29 下午04:55:28]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public LvBlogTags get(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogTagsServiceImpl.get() method begin*****");
		}
		lvBlogTags=(LvBlogTags)dto.get("model");
		lvBlogTags=(LvBlogTags)this.dao.load(LvBlogTags.class, lvBlogTags.getId());
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogTagsServiceImpl.get() method end*****");
		}
		return lvBlogTags;
	}

    /**
     * 
     * @Method: save 
     * @Description:  [新增Blog标签信息]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-6-29 下午04:55:35]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-6-29 下午04:55:35]   
     * @UpdateRemark: [说明本次修改内容]  
     * @throws
     */
	@Override
	public Boolean save(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogTagsServiceImpl.save() method begin*****");
		}
		lvBlogTags=(LvBlogTags)dto.get("model");
		if(lvBlogTags!=null){
			if (ObjectUtils.isNotEmpty(lvBlogTags.getTagName())) {
				String hql="from LvBlogTags where tagName=:tagName and isShow=:isShow";
				Map param=new HashMap();
				param.put("tagName", lvBlogTags.getTagName());
				param.put("isShow", 1);
				List list=dao.find(hql, param);
				if(list==null||list.size()<=0){
					this.dao.save(lvBlogTags);
					return true;
				}
			}
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogTagsServiceImpl.save() method end*****");
		}
		return false;
	}

	/**
	 * 
	 * @Method: update 
	 * @Description:  [修改Blog标签信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-6-29 下午04:55:40]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-6-29 下午04:55:40]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public LvBlogTags update(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogTagsServiceImpl.update() method begin*****");
		}
		lvBlogTags=(LvBlogTags)dto.get("model");
		this.dao.update(lvBlogTags);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogTagsServiceImpl.update() method end*****");
		}
		return lvBlogTags;
	}
	
	
	

	public LvBlogTags getLvBlogTags() {
		return lvBlogTags;
	}

	public void setLvBlogTags(LvBlogTags lvBlogTags) {
		this.lvBlogTags = lvBlogTags;
	}

}
