package com.lshop.manage.lvProductCommend.service.impl;

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
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.pojo.logic.LvProductCommend;
import com.lshop.manage.lvProductCommend.service.LvProductCommendService;
/**
 * 
 *  
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvProductCommend.service.impl.LvProductCommendServiceImpl.java]  
 * @ClassName:    [LvProductCommendServiceImpl]   
 * @Description:  [产品推荐组合-数据访问层]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-7-4 上午11:31:47]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-7-4 上午11:31:47]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v1.0] 
 *
 */
@Service("LvProductCommendService")
public class LvProductCommendServiceImpl implements LvProductCommendService{
	private static final Log logger	= LogFactory.getLog(LvProductCommendServiceImpl.class);
	@Resource 
	private HibernateBaseDAO dao;

	/**
	 * 
	 * @Method: delete 
	 * @Description:  [删除产品推荐组合]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-4 上午11:36:02]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-4 上午11:36:02]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public void delete(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductCommendServiceImpl.delete() method begin*****");
		}
		LvProductCommend lvProductCommend=(LvProductCommend)dto.get("lvProductCommend");
		dao.delete(lvProductCommend);
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductCommendServiceImpl.delete() method end*****");
		}
	}
	
	@Override
	public void deleteCommend(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductCommendServiceImpl.delete() method begin*****");
		}
		LvProductCommend lvProductCommend=(LvProductCommend)dto.get("lvProductCommend");
		String hql="delete from LvProductCommend where 1=1" ;
		
		if(ObjectUtils.isNotEmpty(lvProductCommend.getProductCode())){			
		    hql+=" and productCode='"+lvProductCommend.getProductCode()+"' " ;
		}
		if(ObjectUtils.isNotEmpty(lvProductCommend.getCommendCode())){
			hql+=" and commendCode='"+lvProductCommend.getCommendCode()+"'";
		}
		dao.delete(hql, null);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductCommendServiceImpl.delete() method end*****");
		}
	}
	/**
	 * 
	 * @Method: get 
	 * @Description:  [根据id查询产品推荐组合详情]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-4 上午11:35:38]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-4 上午11:35:38]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public LvProductCommend get(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductCommendServiceImpl.get() method begin*****");
		}
		LvProductCommend lvProductCommend=(LvProductCommend)dto.get("lvProductCommend");
		lvProductCommend=(LvProductCommend) dao.load(LvProductCommend.class, lvProductCommend.getId());
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductCommendServiceImpl.get() method end*****");
		}
		return lvProductCommend;
	}
    /**
     * 
     * @Method: save 
     * @Description:  [新增产品推荐组合]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-7-4 上午11:34:51]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-7-4 上午11:34:51]   
     * @UpdateRemark: [说明本次修改内容]  
     * @throws
     */
	@Override
	public Dto save(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductCommendServiceImpl.save() method begin*****");
		}
		LvProductCommend lvProductCommend=(LvProductCommend)dto.get("lvProductCommend");
		dao.save(lvProductCommend);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductCommendServiceImpl.save() method end*****");
		}
		return null;
	}

	/**
	 * 
	 * @Method: getProductCommend 
	 * @Description:  [查询所有的推荐产品组合]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-26 下午05:57:39]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-26 下午05:57:39]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public List<LvProductCommend> getProductCommend(Dto dto)
			throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductCommendServiceImpl.getProductCommend() method begin*****");
		}	
		String productCode=(String) dto.get("productCode");
		if(productCode==null){
			return null;
		}
		String hql = "from LvProductCommend where productCode='"+productCode+"'";
		List<LvProductCommend> list = dao.find(hql, null);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductCommendServiceImpl.getProductCommend() method end*****");
		}	
		return list;
	}

}
