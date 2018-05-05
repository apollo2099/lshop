package com.lshop.manage.lvProductType.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gv.base.department.common.pojo.BaseDepartment;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.datastructure.page.SimplePage;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.pojo.logic.LvProductType;
import com.lshop.common.util.Constants;
import com.lshop.common.util.StoreHelpUtil;
import com.lshop.common.util.StringUtil;
import com.lshop.manage.lvProductType.service.LvProductTypeService;
/**
 * 
 *  
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvProductType.service.impl.LvProductTypeServiceImpl.java]  
 * @ClassName:    [LvProductTypeServiceImpl]   
 * @Description:  [产品类型管理-数据访问层]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-7-3 上午11:04:46]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-7-3 上午11:04:46]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v1.0] 
 *
 */
@Service("LvProductTypeService")
public class LvProductTypeServiceImpl implements LvProductTypeService {
	private static final Log logger	= LogFactory.getLog(LvProductTypeServiceImpl.class);
	@Resource 
	private HibernateBaseDAO dao;
	
	/**
	 * 
	 * @Method: getList 
	 * @Description:  [分页查询产品类型列表]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-3 上午11:04:59]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-3 上午11:04:59]   
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
		LvProductType lvProductType=(LvProductType)dto.get("lvProductType");
		Map<String ,Object> params=new HashMap<String ,Object>();
		StringBuilder hql = new StringBuilder("from LvProductType t where 1=1");
		if(lvProductType!=null){
			if(ObjectUtils.isNotEmpty(lvProductType.getId())){
				hql.append(" and id=:id");
				params.put("id", lvProductType.getId());
			}
			if(ObjectUtils.isNotEmpty(lvProductType.getTypeFlag())){
				hql.append(" and typeFlag=:typeFlag");
				params.put("typeFlag", lvProductType.getTypeFlag());
			}
			if(ObjectUtils.isNotEmpty(lvProductType.getTypeName())){
				hql.append(" and typeName like :typeName");
				params.put("typeName", "%"+lvProductType.getTypeName()+"%");
			}
			if(ObjectUtils.isNotEmpty(lvProductType.getStoreId())){
				hql.append(" and storeId=:storeId");
				params.put("storeId", lvProductType.getStoreId());
			}
		}
	    //店铺标识
//		hql.append(StoreHelpUtil.getStoreRelevanceString(dto.getAsString("flag"),"storeId","t"));
		hql.append(StoreHelpUtil.getNoShopRelevanceString(dto.getAsString("flag"), "storeId", "t"));
		hql.append(" order by storeId desc,orderId desc");
	
		Finder finder = Finder.create(hql.toString());
		finder.setParams(params);
	
		return dao.find(finder, page.getPageNum(), page.getNumPerPage());
	}
	
	
	/**
	 * 
	 * @Method: findListByCurrency 
	 * @Description:  [根据币种查询对于币种店铺对于的商品类型信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-6-18 下午4:04:10]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-6-18 下午4:04:10]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return Pagination
	 */
	public Pagination findListByCurrency(Dto dto) throws ServiceException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductTypeServiceImpl.getList() method end*****");
		}
		String orderField = dto.getAsString("orderField");
		String orderDirection = dto.getAsString("orderDirection");
		SimplePage page = (SimplePage)dto.get("page");
		LvProductType lvProductType=(LvProductType)dto.get("lvProductType");
		String currency=(String) dto.get("currency");
		Map<String ,Object> params=new HashMap<String ,Object>();
		StringBuilder hql = new StringBuilder("select t.id as id,t.typeName as typeName,t.code as code,t.storeId as storeId,s.name as name  " +
				" from LvProductType t,LvStore s " +
				" where t.storeId=s.storeFlag " +
				" and s.currency=:currency ");
		params.put("currency", currency);
		if(ObjectUtils.isNotEmpty(lvProductType)){
			if(ObjectUtils.isNotEmpty(lvProductType.getStoreId())){
				hql.append(" and t.storeId=:storeId");
				params.put("storeId", lvProductType.getStoreId());
			}
			if(ObjectUtils.isNotEmpty(lvProductType.getTypeName())){
				hql.append(" and t.typeName like :typeName");
				params.put("typeName", "%"+lvProductType.getTypeName()+"%");
			}
		}
		hql.append(" order by t.storeId desc,t.orderId desc");
		return dao.getMapListByHql(hql.toString(), page.getPageNum(), page.getNumPerPage(), params);
	}
	
	/**
	 * 
	 * @Method: findByCouponType 
	 * @Description:  [根据优惠券类型查询商品类型信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-6-19 下午1:59:40]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-6-19 下午1:59:40]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws ServiceException 
	 * @return Map
	 */
	public Pagination findListByCouponType(Dto dto)throws ServiceException{
		String couponTypeCode=(String) dto.get("couponTypeCode");
		Map<String ,Object> param=new HashMap<String ,Object>();
		StringBuilder hql = new StringBuilder("select t.id as id,t.typeName as typeName,t.code as code,s.name as name  " +
				" from LvProductType t, LvCouponProduct cp,LvStore s " +
				" where t.storeId=s.storeFlag " +
				" and t.code=cp.relationCode" +
				" and cp.couponTypeCode=:couponTypeCode");
		param.put("couponTypeCode", couponTypeCode);
		return dao.getMapListByHql(hql.toString(), param);
	}
	/**
	 * 
	 * @Method: delete 
	 * @Description:  [删除产品类型信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-3 上午11:05:07]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-3 上午11:05:07]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public void delete(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductTypeServiceImpl.delete() method begin*****");
		}
		LvProductType lvProductType=(LvProductType)dto.get("lvProductType");
		dao.delete(lvProductType);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductTypeServiceImpl.delete() method end*****");
		}
	}
	/**
	 * 
	 * @Method: get 
	 * @Description:  [根据id查询产品类型详情]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-3 上午11:05:15]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-3 上午11:05:15]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public LvProductType get(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductTypeServiceImpl.get() method begin*****");
		}
		LvProductType lvProductType=(LvProductType)dto.get("lvProductType");
		lvProductType=(LvProductType) dao.load(LvProductType.class, lvProductType.getId());
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductTypeServiceImpl.get() method end*****");
		}
		return lvProductType;
	}
	/**
	 * 
	 * @Method: save 
	 * @Description:  [新增产品类型信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-3 上午11:05:19]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-3 上午11:05:19]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Boolean save(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductTypeServiceImpl.save() method begin*****");
		}
		LvProductType lvProductType=(LvProductType)dto.get("lvProductType");
		String hql="from LvProductType where typeName=:typeName and typeFlag=:typeFlag and storeId=:storeId";
		Map param=new HashMap();
		param.put("typeName", lvProductType.getTypeName());
		param.put("typeFlag", lvProductType.getTypeFlag());
		param.put("storeId", lvProductType.getStoreId());
		LvProductType type=(LvProductType) dao.findUnique(hql, param);
		if(type!=null){
			return false;
		}
		dao.save(lvProductType);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductTypeServiceImpl.save() method end*****");
		}
		return true;
	}
	/**
	 * 
	 * @Method: update 
	 * @Description:  [修改产品类型信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-3 上午11:05:24]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-3 上午11:05:24]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Boolean update(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductTypeServiceImpl.update() method begin*****");
		}
		LvProductType lvProductType=(LvProductType)dto.get("lvProductType");
		if (!lvProductType.getOldTypeName().equals(lvProductType.getTypeName())) {
			String hql="from LvProductType where typeName=:typeName and typeFlag=:typeFlag and storeId=:storeId";
			Map param=new HashMap();
			param.put("typeName", lvProductType.getTypeName());
			param.put("typeFlag", lvProductType.getTypeFlag());
			param.put("storeId", lvProductType.getStoreId());
			LvProductType type=(LvProductType) dao.findUnique(hql, param);
			if(type!=null){
				return false;
			}
		}
		
		
		dao.update(lvProductType);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductTypeServiceImpl.update() method end*****");
		}
		return true;
		
	}
	/**
	 * 
	 * @Method: getAll 
	 * @Description:  [获取产品类型列表]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-3 下午05:02:58]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-3 下午05:02:58]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public List<LvProductType> getAll(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductTypeServiceImpl.getAll() method begin*****");
		}		
		String hql = "from LvProductType p where p.typeFlag=1";
		LvProduct lvProduct=(LvProduct) dto.get("lvProduct");
		if(lvProduct!=null){
			if(ObjectUtils.isNotEmpty(lvProduct.getStoreId())){
				hql+=" and storeId='"+lvProduct.getStoreId()+"'";
			}
		}else{
			if(ObjectUtils.isNotEmpty(dto.getAsString("flag"))){//店铺标示
				String storeList=StringUtil.stringFormart(dto.getAsString("flag"), ",");
				hql+=" and storeId in("+storeList+")";
		    }
		}
		List<LvProductType> list = dao.find(hql, null);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductTypeServiceImpl.getAll() method end*****");
		}	
	    return list;
	}
	
	/**
	 * 
	 * @Method: getAppAll 
	 * @Description:  [获取应用的产品类型列表]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-3 下午05:02:58]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-3 下午05:02:58]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public List<LvProductType> getAppAll(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductTypeServiceImpl.getAll() method begin*****");
		}
		String typeStoreId=(String) dto.get("typeStoreId");
		String hql = "from LvProductType t where typeFlag=2";
		if(ObjectUtils.isNotEmpty(typeStoreId)){
			hql+=" and storeId='"+typeStoreId+"'";
		}
		if(ObjectUtils.isNotEmpty(dto.getAsString("flag"))){//店铺标示
			String storeList=StringUtil.stringFormart(dto.getAsString("flag"), ",");
			hql+=" and storeId in("+storeList+")";
		}
		hql+=StoreHelpUtil.getNoShopRelevanceString(dto.getAsString("flag"), "storeId", "t").toString();
		List<LvProductType> list = dao.find(hql, null);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductTypeServiceImpl.getAll() method end*****");
		}	
	    return list;
	}
	
	
}
