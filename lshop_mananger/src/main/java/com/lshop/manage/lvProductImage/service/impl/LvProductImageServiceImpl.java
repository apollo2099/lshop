package com.lshop.manage.lvProductImage.service.impl;

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
import com.lshop.common.pojo.logic.LvProductImage;
import com.lshop.common.util.StoreHelpUtil;
import com.lshop.common.util.StringUtil;
import com.lshop.manage.lvProduct.service.LvProductService;
import com.lshop.manage.lvProductImage.service.LvProductImageService;


@Service("LvProductImageService")
public class LvProductImageServiceImpl implements LvProductImageService{
	private static final Log logger	= LogFactory.getLog(LvProductImageServiceImpl.class);
	@Resource 
	private HibernateBaseDAO dao;
	@Resource 
	private LvProductService lvProductService;
	/**
	 * 
	 * @Method: getList 
	 * @Description:  [分页查询产品效果图信息列表]  
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
			logger.info("***** LvProductImageServiceImpl.getList() method begin*****");
		}
		
		String orderField = dto.getAsString("orderField");
		String orderDirection = dto.getAsString("orderDirection");
		SimplePage page = (SimplePage)dto.get("page");
		LvProductImage lvProductImage=(LvProductImage) dto.get("lvProductImage");
		Map<String ,Object> params=new HashMap<String ,Object>();
		StringBuilder hql = new StringBuilder("select p.id as id,p.productImage as productImage,p.productCode as productCode," +
				" p.sortId as sortId,s.domainName as domainName,p.storeId as storeId" +
		        " from LvProductImage p,LvStore s where p.storeId=s.storeFlag ");

        if(lvProductImage!=null){
        	if(ObjectUtils.isNotEmpty(lvProductImage.getProductCode())){
        		 hql.append(" and p.productCode=:productCode ");
        		 params.put("productCode", lvProductImage.getProductCode());
        	}
        }
        hql.append(StoreHelpUtil.getNoShopRelevanceString(dto.getAsString("flag"), "storeId", "p"));
//      hql.append(StoreHelpUtil.getStoreRelevanceString(dto.getAsString("flag"),"storeId","p"));
		if(ObjectUtils.isNotEmpty(orderField)&&ObjectUtils.isNotEmpty(orderDirection)){
			 hql.append(" order by p."+ orderField+" "+orderDirection);
		}
	
		return dao.getMapListByHql(hql.toString(), page.getPageNum(), page.getNumPerPage(), params);
	}
    /**
     * 
     * @Method: delete 
     * @Description:  [删除产品效果图信息]  
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
			logger.info("***** LvProductImageServiceImpl.delete() method begin*****");
		}
		LvProductImage lvProductImage=(LvProductImage) dto.get("lvProductImage");
		dao.delete(lvProductImage);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductImageServiceImpl.delete() method end*****");
		}
		
	}

	/**
	 * 
	 * @Method: save 
	 * @Description:  [新增产品效果图信息]  
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
			logger.info("***** LvProductImageServiceImpl.save() method begin*****");
		}
		LvProductImage lvProductImage=(LvProductImage) dto.get("lvProductImage");
		LvProduct lvProduct=(LvProduct) dto.get("lvProduct");
		lvProductImage.setStoreId(lvProduct.getStoreId());
		dao.save(lvProductImage);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductImageServiceImpl.save() method end*****");
		}
		return null;
	}

	/**
	 * 
	 * @Method: update 
	 * @Description:  [保存产品效果图信息]  
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
			logger.info("***** LvProductImageServiceImpl.update() method begin*****");
		}
		LvProductImage lvProductImage=(LvProductImage) dto.get("lvProductImage");
		dao.update(lvProductImage);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductImageServiceImpl.update() method end*****");
		}
	}
	
	/**
	 * 
	 * @Method: get 
	 * @Description:  [根据id查询产品效果图详情]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-4 上午11:16:06]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-4 上午11:16:06]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public LvProductImage get(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductImageServiceImpl.get() method begin*****");
		}
		LvProductImage lvProductImage=(LvProductImage) dto.get("lvProductImage");
		lvProductImage=(LvProductImage) dao.load(LvProductImage.class, lvProductImage.getId());
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductImageServiceImpl.get() method end*****");
		}
		return lvProductImage;
	}


}
