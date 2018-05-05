package com.lshop.manage.lvProductProperty.service.impl;

import java.util.HashMap;
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
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.pojo.logic.LvProductProperty;
import com.lshop.common.util.StoreHelpUtil;
import com.lshop.common.util.StringUtil;
import com.lshop.manage.lvProduct.service.LvProductService;
import com.lshop.manage.lvProductProperty.service.LvProductPropertyService;
/**
 * 
 *  
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvProductProperty.service.impl.LvProductPropertyServiceImpl.java]  
 * @ClassName:    [LvProductPropertyServiceImpl]   
 * @Description:  [产品扩展属性信息-数据访问层]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-7-4 上午11:10:05]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-7-4 上午11:10:05]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v1.0] 
 *
 */
@Service("LvProductPropertyService")
public class LvProductPropertyServiceImpl implements LvProductPropertyService {
	private static final Log logger	= LogFactory.getLog(LvProductPropertyServiceImpl.class);
	@Resource 
	private HibernateBaseDAO dao;
	@Resource 
	private LvProductService lvProductService;
	/**
	 * 
	 * @Method: getList 
	 * @Description:  [分页查询产品扩展属性信息列表]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-4 上午11:10:35]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-4 上午11:10:35]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Pagination getList(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductPropertyServiceImpl.getList() method begin*****");
		}
		String orderField = dto.getAsString("orderField");
		String orderDirection = dto.getAsString("orderDirection");
		SimplePage page = (SimplePage)dto.get("page");
		Map<String ,Object> params=new HashMap<String ,Object>();
		LvProductProperty lvProductProperty=(LvProductProperty) dto.get("lvProductProperty");
		StringBuilder hql = new StringBuilder("from LvProductProperty t where 1=1 ");
        if(lvProductProperty!=null){
        	if(ObjectUtils.isNotEmpty(lvProductProperty.getProductCode())){
        		hql.append(" and productCode=:productCode");
        		params.put("productCode", lvProductProperty.getProductCode());
        	}
        	if(ObjectUtils.isNotEmpty(lvProductProperty.getTitle())){
        		hql.append(" and title like :title ");
        		params.put("title","%"+lvProductProperty.getTitle().trim()+"%");
        	}
        }
        hql.append(StoreHelpUtil.getNoShopRelevanceString(dto.getAsString("flag"), "storeId", "t"));
//      hql.append(StoreHelpUtil.getStoreRelevanceString(dto.getAsString("flag"),"storeId","t"));
        hql.append(" order by storeId desc,productCode desc,sortId desc ");
        Finder finder = Finder.create(hql.toString());
		finder.setParams(params);
		return dao.find(finder, page.getPageNum(), page.getNumPerPage());
	}
    /**
     * 
     * @Method: delete 
     * @Description:  [删除产品扩展属性信息]  
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
			logger.info("***** LvProductPropertyServiceImpl.delete() method begin*****");
		}
		LvProductProperty lvProductProperty=(LvProductProperty) dto.get("lvProductProperty");
		dao.delete(lvProductProperty);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductPropertyServiceImpl.delete() method end*****");
		}
		
	}

	/**
	 * 
	 * @Method: save 
	 * @Description:  [新增产品扩展属性信息]  
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
			logger.info("***** LvProductPropertyServiceImpl.save() method begin*****");
		}
		LvProductProperty lvProductProperty=(LvProductProperty) dto.get("lvProductProperty");
		LvProduct lvProduct=new LvProduct();
		lvProduct.setCode(lvProductProperty.getProductCode());
		dto.put("lvProduct", lvProduct);
		lvProduct=lvProductService.getProduct(dto);
		lvProductProperty.setStoreId(lvProduct.getStoreId());
		dao.save(lvProductProperty);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductPropertyServiceImpl.save() method end*****");
		}
		return null;
	}

	/**
	 * 
	 * @Method: update 
	 * @Description:  [保存产品扩展属性信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-4 上午11:12:18]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-4 上午11:12:18]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public void update(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductPropertyServiceImpl.update() method begin*****");
		}
		LvProductProperty lvProductProperty=(LvProductProperty) dto.get("lvProductProperty");
		dao.update(lvProductProperty);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductPropertyServiceImpl.update() method end*****");
		}
	}
	
	/**
	 * 
	 * @Method: get 
	 * @Description:  [根据id查询产品扩展信息详情]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-4 上午11:16:06]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-4 上午11:16:06]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public LvProductProperty get(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductPropertyServiceImpl.get() method begin*****");
		}
		LvProductProperty lvProductProperty=(LvProductProperty) dto.get("lvProductProperty");
		lvProductProperty=(LvProductProperty) dao.load(LvProductProperty.class, lvProductProperty.getId());
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductPropertyServiceImpl.get() method end*****");
		}
		return lvProductProperty;
	}


}
