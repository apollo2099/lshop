package com.lshop.manage.blog.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.gv.core.util.BeanUtils;
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
import com.gv.core.util.ObjectUtils;

import com.lshop.common.pojo.logic.LvBlogContent;
import com.lshop.common.pojo.logic.LvBlogType;
import com.lshop.common.util.StoreHelpUtil;
import com.lshop.common.util.StringUtil;
import com.lshop.manage.blog.service.LvBlogContentService;

/**
 * 
 *  
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.blog.service.impl.LvBlogContentServiceImpl.java]  
 * @ClassName:    [LvBlogContentServiceImpl]   
 * @Description:  [博客文章管理-数据访问层]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-6-29 下午03:41:22]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-6-29 下午03:41:22]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v1.0] 
 *
 */
@Service("LvBlogContentService")
public class LvBlogContentServiceImpl extends BaseServiceImpl implements LvBlogContentService{
	private static final Log logger	= LogFactory.getLog(LvBlogContentServiceImpl.class);
	@Resource 
	private HibernateBaseDAO dao;

	
    /**
     * 
     * @Method: list 
     * @Description:  [分页查询博客文章列表信息]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-6-29 下午03:44:40]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-6-29 下午03:44:40]   
     * @UpdateRemark: [说明本次修改内容]  
     * @throws
     */
	@Override
	public Pagination list(Dto dto) {
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogContentServiceImpl.list() method begin*****");
		}
		SimplePage page = (SimplePage)dto.get("page");
		LvBlogContent lvBlogContent=(LvBlogContent)dto.get("model");
		Map parms=new HashMap();
		StringBuilder hql = new StringBuilder("from LvBlogContent t where isDelete=1 ");
		if(ObjectUtils.isNotEmpty(lvBlogContent)){
			if(ObjectUtils.isNotEmpty(lvBlogContent.getType())){
				hql.append(" and type=:type");
			}
			if(ObjectUtils.isNotEmpty(lvBlogContent.getTitle())){
				hql.append(" and title like :title");
			}
			if(ObjectUtils.isNotEmpty(lvBlogContent.getStoreId())){
				hql.append(" and storeId like :storeId");
			}
			parms=BeanUtils.describeForHQL(lvBlogContent);
		}
		hql.append(StoreHelpUtil.getStoreRelevanceString(dto.getAsString("flag"),"storeId","t"));
		hql.append(" order by orderId desc , id desc");
		Finder finder = Finder.create(hql.toString());
		finder.setParams(parms);
		//返回执行的结果对象
		return dao.find(finder,page.getPageNum(), page.getNumPerPage());
	}
	
	/**
	 * 
	 * @Method: add 
	 * @Description:  [新增博客文章(发表博客)]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-6-29 下午03:42:11]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-6-29 下午03:42:11]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Dto add(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogContentServiceImpl.add() method begin*****");
		}
		LvBlogContent lvBlogContent=(LvBlogContent) dto.get("model");
		dao.save(lvBlogContent);
		result.setAppCode(StatusCode.M_Success);
		result.put("lvBlogContent", lvBlogContent);
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogContentServiceImpl.add() method end*****");
		}
		return result;
	}
   /**
    * 
    * @Method: delete 
    * @Description:  [删除博客文章信息]  
    * @Author:       [liaoxiongjian]     
    * @CreateDate:   [2012-6-29 下午03:43:40]   
    * @UpdateUser:   [liaoxiongjian]     
    * @UpdateDate:   [2012-6-29 下午03:43:40]   
    * @UpdateRemark: [说明本次修改内容]  
    * @throws
    */
	@Override
	public Dto delete(Dto dto) {
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogContentServiceImpl.delete() method begin*****");
		}
		LvBlogContent po=(LvBlogContent)dto.get("model");
		String hql="update LvBlogContent set isDelete=0 WHERE id="+po.getId()+"";
		this.dao.update(hql, null);
	    result.setAppCode(StatusCode.M_Success);
	    if (logger.isInfoEnabled()){
			logger.info("***** LvBlogContentServiceImpl.delete() end begin*****");
		}
		return result;
	}


	/**
	 * 
	 * @Method: update 
	 * @Description:  [修改博客文章信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-6-29 下午03:46:15]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-6-29 下午03:46:15]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Dto update(Dto dto) {
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogContentServiceImpl.update() method begin*****");
		}
		LvBlogContent po=(LvBlogContent)dto.get("model");
		dao.update(po);		
		result.setAppCode(StatusCode.M_Success);
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogContentServiceImpl.update() method end*****");
		}
		return result;	
		//return (BaseVo) this.dao.update((BasePo) dto);
	}
	
	/**
	 * 
	 * @Method: get 
	 * @Description:  [根据博客序号查询博客文章详细信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-6-29 下午03:47:23]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-6-29 下午03:47:23]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public LvBlogContent get(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogContentServiceImpl.get() method begin*****");
		}
		LvBlogContent lvBlogContent = (LvBlogContent)dto.get("model");
		lvBlogContent = (LvBlogContent)dao.load(LvBlogContent.class, lvBlogContent.getId());
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogContentServiceImpl.get() method begin*****");
		}
		return  lvBlogContent;
	}
	
	/**
	 * 
	 * @Method: listTop10 
	 * @Description:  [查询TOP10的热门文章信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-6-29 下午03:48:34]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-6-29 下午03:48:34]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Pagination listTop10(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogContentServiceImpl.listTop10() method begin*****");
		}
		SimplePage page = (SimplePage)dto.get("page");
		String hql = "from LvBlogContent where isDelete=1 ";
		if(ObjectUtils.isNotEmpty(dto.getAsString("flag"))){
			hql+=" and storeId='"+dto.getAsString("flag")+"'";
		}
		hql+=" order by createTime desc ";
		return dao.find(Finder.create(hql),page.getPageNum(), page.getNumPerPage());
	}
}
