package com.lshop.manage.lvProductPackage.service.impl;

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
import com.lshop.common.pojo.logic.LvOrderDetails;
import com.lshop.common.pojo.logic.LvProductPackage;
import com.lshop.common.util.StoreHelpUtil;
import com.lshop.manage.lvProductPackage.service.LvProductPackageService;

/**
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvProductPackage.service.impl.LvProductPackageServiceImpl.java]  
 * @ClassName:    [LvProductPackageServiceImpl]   
 * @Description:  [产品套餐关联关系管理-数据访问层]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-7-4 上午11:25:08]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-7-4 上午11:25:08]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v1.0] 
 *
 */
@Service("LvProductPackageService")
public class LvProductPackageServiceImpl implements LvProductPackageService{
	private static final Log logger	= LogFactory.getLog(LvProductPackageServiceImpl.class);
	@Resource 
	private HibernateBaseDAO dao;
	

	/**
	 * 
	 * @Method: delete 
	 * @Description:  [删除产品套餐关联信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-4 上午11:27:14]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-4 上午11:27:14]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public synchronized void delete(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductPropertyServiceImpl.delete() method begin*****");
		}
		LvProductPackage lvProductPackage=(LvProductPackage)dto.get("lvProductPackage");
		String hql="delete from LvProductPackage where packageNum='"+lvProductPackage.getPackageNum()+"'";;
		dao.delete(hql,null);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductPropertyServiceImpl.delete() method end*****");
		}
	}

	/**
	 * 
	 * @Method: get 
	 * @Description:  [根据id查询套餐关联详情]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-4 上午11:27:21]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-4 上午11:27:21]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public LvProductPackage get(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductPropertyServiceImpl.get() method begin*****");
		}
		LvProductPackage lvProductPackage=(LvProductPackage)dto.get("lvProductPackage");
		lvProductPackage=(LvProductPackage) dao.load(LvProductPackage.class, lvProductPackage.getId());
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductPropertyServiceImpl.get() method end*****");
		}
		return lvProductPackage;
	}


	/**
	 * 
	 * @Method: save 
	 * @Description:  [新增产品套餐信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-4 上午11:27:26]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-4 上午11:27:26]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public synchronized Dto save(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductPropertyServiceImpl.save() method begin*****");
		}
		LvProductPackage lvProductPackage=(LvProductPackage)dto.get("lvProductPackage");
		dao.save(lvProductPackage);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductPropertyServiceImpl.save() method end*****");
		}
		return null;
	}

	/**
	 * 
	 * @Method: getProductPackage 
	 * @Description:  [根据套餐code查询套餐详情]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-31 下午03:03:24]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-31 下午03:03:24]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public List<LvProductPackage> getProductPackage(Dto dto)throws ServiceException {
		String productCode=(String) dto.get("productCode");
		List<LvProductPackage> list=null;
		if(ObjectUtils.isNotEmpty(productCode)){
			StringBuilder hql = new StringBuilder("select o from LvProductPackage o where packageNum='"+productCode+"' ");
			hql.append(StoreHelpUtil.getNoShopRelevanceString(dto.getAsString("flag"), "storeId", "o"));
			list=  dao.find(hql.toString(), null);
		}
		return list;
	}


}
