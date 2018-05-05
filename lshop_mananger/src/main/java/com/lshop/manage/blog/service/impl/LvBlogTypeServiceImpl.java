package com.lshop.manage.blog.service.impl;

import java.util.HashMap;
import java.util.List;
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
import com.gv.core.service.impl.BaseServiceImpl;
import com.gv.core.util.BeanUtils;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvBlogType;
import com.lshop.common.util.StoreHelpUtil;
import com.lshop.common.util.StringUtil;
import com.lshop.manage.blog.service.LvBlogTypeService;
/**
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.blog.service.impl.LvBlogTypeServiceImpl.java]  
 * @ClassName:    [LvBlogTypeServiceImpl]   
 * @Description:  [博客类型管理-数据访问层]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-6-29 下午02:53:16]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-6-29 下午02:53:16]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v1.0] 
 *
 */
@Service("LvBlogTypeService")
public class LvBlogTypeServiceImpl extends BaseServiceImpl implements LvBlogTypeService{
	private static final Log logger	= LogFactory.getLog(LvBlogTypeServiceImpl.class);
	@Resource 
	private HibernateBaseDAO dao;
	private LvBlogType lvBlogType;
	
	
	@Override
	public Dto add(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 
	 * @Method: list 
	 * @Description:  [分页查询博客类型列表]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-6-29 下午02:43:47]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-6-29 下午02:43:47]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	@Override
	public Pagination list(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogTypeServiceImpl.list() method begin*****");
		}
		SimplePage page = (SimplePage)dto.get("page");
		lvBlogType= (LvBlogType)dto.get("model");
		Map<String ,Object> params=new HashMap<String ,Object>();
		StringBuilder hql = new StringBuilder("from LvBlogType t where 1=1 ");
		if(lvBlogType!=null){
			//序号
			if(ObjectUtils.isNotEmpty(lvBlogType.getId())){
				hql.append(" and t.id=:id");
				params.put("id", lvBlogType.getId());
			}
			//类型名称
			if(ObjectUtils.isNotEmpty(lvBlogType.getType())){
				hql.append(" and t.type like :type");
				params.put("type", "%"+lvBlogType.getType().trim()+"%");
			}
			if(ObjectUtils.isNotEmpty(lvBlogType.getStoreId())){
				hql.append(" and t.storeId=:storeId");
				params.put("storeId", lvBlogType.getStoreId());
			}
		}
		hql.append(StoreHelpUtil.getStoreRelevanceString(dto.getAsString("flag"), "storeId","t"));
		hql.append("order by storeId desc, orderId desc");
		Finder finder = Finder.create(hql.toString());
		finder.setParams(params);
		//返回执行的结果对象
		return dao.find(finder,page.getPageNum(), page.getNumPerPage());
		
	}

	/**
	 * 
	 * @Method: delete 
	 * @Description:  [删除博客类型]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-6-29 下午02:57:28]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-6-29 下午02:57:28]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public void delete(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogTypeServiceImpl.delete() method begin*****");
		}	
		lvBlogType=(LvBlogType)dto.get("model");
		lvBlogType = (LvBlogType)dao.load(LvBlogType.class, lvBlogType.getId());
		dao.delete(lvBlogType);
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogTypeServiceImpl.delete() method end*****");
		}
		
	}
	

	/**
	 * 
	 * @Method: update 
	 * @Description:  [修改博客类型]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-6-29 下午02:58:07]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-6-29 下午02:58:07]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public LvBlogType update(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogTypeServiceImpl.update() method begin*****");
		}
		lvBlogType=(LvBlogType)dto.get("model");
		if(lvBlogType!=null){
			dao.update(lvBlogType);
		}
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogTypeServiceImpl.update() method end*****");
		}
		return lvBlogType;
	}
	
	 /**
	  * 
	  * @Method: typelist 
	  * @Description:  [一句话描述该类的功能]  
	  * @Author:       [liaoxiongjian]     
	  * @CreateDate:   [2012-6-29 下午02:59:27]   
	  * @UpdateUser:   [liaoxiongjian]     
	  * @UpdateDate:   [2012-6-29 下午02:59:27]   
	  * @UpdateRemark: [说明本次修改内容]  
	  * @throws
	  */
	@Override
	public List typelist(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogTypeServiceImpl.typelist() method begin*****");
		}
		LvBlogType lvBlogType=(LvBlogType) dto.get("lvBlogType");
		String hql="from LvBlogType where 1=1";
		if(lvBlogType!=null){
			if(ObjectUtils.isNotEmpty(lvBlogType.getStoreId())){
				hql+=" and storeId='"+lvBlogType.getStoreId()+"'";
			}
		}
		if(ObjectUtils.isNotEmpty(dto.getAsString("flag"))){//店铺标示
			String storeList=StringUtil.stringFormart(dto.getAsString("flag"), ",");
			hql+=" and storeId in ("+storeList+")";
		}
		return dao.find(hql, null);
		
	}

	/**
	 * 
	 * @Method: save 
	 * @Description:  [新增博客类型]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-6-29 下午02:59:36]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-6-29 下午02:59:36]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Dto save(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogTypeServiceImpl.save() method begin*****");
		}
		lvBlogType=(LvBlogType)dto.get("model");
		LvBlogType lt=(LvBlogType)this.dao.findUniqueByProperty(LvBlogType.class, "type", lvBlogType.getType().trim());
		if(lt==null){
		   dao.save(lvBlogType);
		   result.put("flag", true);
		   result.put("lvBlogType", lvBlogType);
		   return result;
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogTypeServiceImpl.save() method end*****");
		}
		result.put("flag", false);
		return result;
	}
	
	/**
	 * 
	 * @Method: get 
	 * @Description:  [根据博客类型序号查询博客类型详细信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-6-29 下午03:00:05]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-6-29 下午03:00:05]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public LvBlogType get(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogTypeServiceImpl.get() method begin*****");
		}
		lvBlogType = (LvBlogType)dto.get("model");
		lvBlogType = (LvBlogType)dao.load(LvBlogType.class, lvBlogType.getId());
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogTypeServiceImpl.get() method end*****");
		}
		return lvBlogType;
	}
	






}
