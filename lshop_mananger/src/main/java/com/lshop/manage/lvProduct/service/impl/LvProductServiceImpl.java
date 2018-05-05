package com.lshop.manage.lvProduct.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.datastructure.page.SimplePage;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.FileUpload;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvActivity;
import com.lshop.common.pojo.logic.LvActivityInfo;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.pojo.logic.LvProductImage;
import com.lshop.common.pojo.logic.LvProductLadder;
import com.lshop.common.pojo.logic.LvProductPackage;
import com.lshop.common.pojo.logic.LvProductProperty;
import com.lshop.common.pojo.logic.LvPubPackage;
import com.lshop.common.pojo.logic.LvPubPackageDetails;
import com.lshop.common.pojo.logic.LvShopProduct;
import com.lshop.common.util.CodeUtils;
import com.lshop.common.util.Constants;
import com.lshop.common.util.StoreHelpUtil;
import com.lshop.common.util.StringUtil;
import com.lshop.html.service.InitLinkDataService;
import com.lshop.manage.lvProduct.service.LvProductService;
import com.lshop.manage.lvProductPackage.service.LvProductPackageService;

/**
 * 
 *  
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvProduct.service.impl.LvProductServiceImpl.java]  
 * @ClassName:    [LvProductServiceImpl]   
 * @Description:  [产品信息管理-数据访问层]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-7-3 上午11:15:17]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-7-3 上午11:15:17]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v1.0] 
 *
 */
@Service("LvProductService")
public class LvProductServiceImpl implements LvProductService {
	private static final Log logger	= LogFactory.getLog(LvProductServiceImpl.class);
	@Resource 
	private HibernateBaseDAO dao;
	@Resource 
	private LvProductPackageService lvProductPackageService;
	@Resource
	private InitLinkDataService initLinkDataService;
	/**
	 * 
	 * @Method: getList 
	 * @Description:  [分页查询产品信息列表]  
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
			logger.info("***** LvProductServiceImpl.getList() method end*****");
		}
		String orderField = dto.getAsString("orderField");
		String orderDirection = dto.getAsString("orderDirection");
		SimplePage page = (SimplePage)dto.get("page");
		LvProduct lvProduct=(LvProduct)dto.get("lvProduct");
		Map<String ,Object> params=new HashMap<String ,Object>();
		StringBuilder hql = new StringBuilder("select p.id as id,p.shopProductType as shopProductType,p.productName as productName,p.code as code,p.storeId as storeId,p.isSupport as isSupport," +
		" p.price as price,p.pcode as pcode,p.pmodel as pmodel,p.pimage as pimage,p.pimageAd as pimageAd,s.domainName as domainName,s.name as storeName,s.currency as currency," +
		" p.createTime as createTime,p.unsupportRemark as unsupportRemark,p.unsupportTime as unsupportTime,p.isSetMeal as isSetMeal,p.orderId as orderId " +
		" from LvProduct p,LvStore s where p.status<>-1 and p.storeId=s.storeFlag and s.isdel=0 " );
		if(lvProduct!=null){
			if(ObjectUtils.isNotEmpty(lvProduct.getId())){//产品id
				hql.append(" and p.id=:id");
				params.put("id",lvProduct.getId());
			}
			if(ObjectUtils.isNotEmpty(lvProduct.getProductName())){//产品名称
				hql.append(" and p.productName like :productName");
				params.put("productName", "%"+lvProduct.getProductName().trim()+"%");
			}
			if(ObjectUtils.isNotEmpty(lvProduct.getPmodel())){//产品模型
				hql.append(" and p.pmodel like :pmodel");
				params.put("pmodel", "%"+lvProduct.getPmodel().trim()+"%");
			}
			if(ObjectUtils.isNotEmpty(lvProduct.getIsSupport())){
				hql.append(" and p.isSupport=:isSupport");
				params.put("isSupport", lvProduct.getIsSupport());
			}else{
				hql.append(" and (p.isSupport<>0 or p.isSupport is null)");
			}
			if(ObjectUtils.isNotEmpty(lvProduct.getStoreId())){//店铺标示
				hql.append(" and p.storeId=:storeId");
				params.put("storeId", lvProduct.getStoreId());
			}
			if(ObjectUtils.isNotEmpty(lvProduct.getShopProductType())){//商城商品分类
				hql.append(" and p.shopProductType=:shopProductType");
				params.put("shopProductType",lvProduct.getShopProductType());
			}
			if(ObjectUtils.isNotEmpty(lvProduct.getPtype())){//商品类别
				hql.append(" and p.ptype=:ptype");
				params.put("ptype",lvProduct.getPtype());
			}
			if(ObjectUtils.isNotEmpty(lvProduct.getIsSetMeal())){//是否套餐
				hql.append(" and p.isSetMeal=:isSetMeal");
				params.put("isSetMeal",lvProduct.getIsSetMeal());
			}
			if(ObjectUtils.isNotEmpty(lvProduct.getPcode())){//商品code
				hql.append(" and pp.pcode like :pcode");
				params.put("pcode", "%"+lvProduct.getPcode().trim()+"%");
			}
			if(ObjectUtils.isNotEmpty(lvProduct.getIsActivity())){
				hql.append(" and p.isActivity=:isActivity");
				params.put("isActivity",lvProduct.getIsActivity());
			}
		}else{
			hql.append(" and (p.isSupport<>0 or p.isSupport is null)");
		}
		//店铺标识
		//hql.append(StoreHelpUtil.getStoreRelevanceString(dto.getAsString("flag"),"storeId","p"));
		hql.append(StoreHelpUtil.getNoShopRelevanceString(dto.getAsString("flag"), "storeId", "p"));
		hql.append(" order by p.storeId desc,p.orderId desc");
	
		return dao.getMapListByHql(hql.toString(),  page.getPageNum(), page.getNumPerPage(), params);
	}
	
	/**
	 * 
	 * @Method: productList 
	 * @Description:  [分页查询非套餐类的产品信息列表]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-4 下午05:28:45]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-4 下午05:28:45]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Pagination productList(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductServiceImpl.getList() method end*****");
		}
		String orderField = dto.getAsString("orderField");
		String orderDirection = dto.getAsString("orderDirection");
		SimplePage page = (SimplePage)dto.get("page");
		LvProduct lvProduct=(LvProduct)dto.get("lvProduct");
		StringBuilder hql = new StringBuilder("select p.id as id,p.productName as productName,p.pcode as pcode,p.pmodel as pmodel,p.price as price," +
				" p.createTime as createTime ,p.pimage as pimage,p.code as code,s.domainName as domainName" +
				" from LvProduct p, LvStore s where p.storeId=s.storeFlag  " +
				" and p.status<>-1 and (p.isSetMeal<>1 or p.isSetMeal is null)");
		if(lvProduct!=null){
			if(ObjectUtils.isNotEmpty(lvProduct.getId())){//产品id
				hql.append(" and p.id="+lvProduct.getId()+"");
			}
			if(ObjectUtils.isNotEmpty(lvProduct.getProductName())){//产品名称
				hql.append(" and p.productName like '%"+lvProduct.getProductName()+"%'");
			}
			if(ObjectUtils.isNotEmpty(lvProduct.getPmodel())){//产品模型
				hql.append(" and p.pmodel like '%"+lvProduct.getPmodel()+"%'");
			}
			if(ObjectUtils.isNotEmpty(lvProduct.getCode())){
				hql.append(" and p.code <>'"+lvProduct.getCode()+"'");
			}
			if(ObjectUtils.isNotEmpty(lvProduct.getIsSupport())){
				hql.append(" and p.isSupport="+lvProduct.getIsSupport()+"");
			}else{
				hql.append(" and (p.isSupport<>0 or p.isSupport is null)");
			}
			if(ObjectUtils.isNotEmpty(lvProduct.getStoreId())){
				hql.append(" and p.storeId='"+lvProduct.getStoreId()+"'");
			}
		}else{
			hql.append(" and (p.isSupport<>0 or p.isSupport is null)");
		}
		hql.append(StoreHelpUtil.getNoShopRelevanceString(dto.getAsString("flag"), "storeId", "p"));
		if(!ObjectUtils.isNullOrEmptyString(orderField)&&!ObjectUtils.isNullOrEmptyString(orderDirection)){
			hql.append(" order by p."+ orderField+" "+orderDirection);
		}
		return dao.getMapListByHql(hql.toString(),  page.getPageNum(), page.getNumPerPage(), null);
	}
	
	
	@Override
	public Pagination getShopProductList(Dto dto) throws ServiceException {
		String orderField = dto.getAsString("orderField");
		String orderDirection = dto.getAsString("orderDirection");
		LvProduct lvProduct=(LvProduct) dto.get("lvProduct");
		LvShopProduct lvShopProduct=(LvShopProduct) dto.get("lvShopProduct");
		SimplePage page = (SimplePage)dto.get("page");
		Map<String ,Object> params=new HashMap<String ,Object>();
		StringBuilder sql = new StringBuilder("select p.id as id,p.shopProductType as shopProductType,p.productName as productName,p.code as productCode,p.storeId as storeId," +
				" p.price as price,p.pmodel as pmodel,p.pimage as pimage,p.pimageAd as pimageAd,s.domainName as domainName,s.name as storeName " +
				" from LvProduct p,LvStore s" +
				" where p.storeId=s.storeFlag" +
				" and p.status<>-1 and p.isSupport=1  and s.isdel=0 ") ;
		if(lvShopProduct!=null){
			if(ObjectUtils.isNotEmpty(lvShopProduct.getSubjectType())){
				sql.append(" and not EXISTS(select 1 from LvShopProduct sp where sp.productCode=p.code and sp.subjectType='"+lvShopProduct.getSubjectType()+"')");
			}
			if(ObjectUtils.isNotEmpty(lvShopProduct.getStoreFlag())){
				//判断当前是商城入口，还是商家入口
				sql.append(StoreHelpUtil.getNoShopRelevanceString(lvShopProduct.getStoreFlag(), "storeId", "p"));
			}
		}	
		if(lvProduct!=null){
			if(ObjectUtils.isNotEmpty(lvProduct.getShopProductType())){
				sql.append(" and p.shopProductType=:shopProductType");
				params.put("shopProductType",lvProduct.getShopProductType() );
			}
            if(ObjectUtils.isNotEmpty(lvProduct.getProductName())){
            	sql.append(" and p.productName like :productName");
            	params.put("productName", "%"+lvProduct.getProductName().trim()+"%");
			}
            if(ObjectUtils.isNotEmpty(lvProduct.getStoreId())){
            	sql.append(" and p.storeId=:storeId");
            	params.put("storeId", lvProduct.getStoreId());
			}
		}
		//判断当前是商城入口，还是商家入口
		sql.append(StoreHelpUtil.getNoShopRelevanceString(dto.getAsString("flag"), "storeId", "p"));
		sql.append(" order by p.createTime desc ");
		return dao.getMapListByHql(sql.toString(), page.getPageNum(), page.getNumPerPage(), params);
	}
	/**
	 * 
	 * @Method: getProductImage 
	 * @Description:  [根据产品code查询产品的效果图分页列表信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-12-22 上午09:49:29]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-12-22 上午09:49:29]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public Pagination getProductImage(Dto dto) throws ServiceException{
		String orderField = dto.getAsString("orderField");
		String orderDirection = dto.getAsString("orderDirection");
		SimplePage page = (SimplePage)dto.get("page");
		LvProduct lvProduct=(LvProduct)dto.get("lvProduct");
		LvProductImage lvProductImage=(LvProductImage) dto.get("lvProductImage");
		Pagination pag=null;
		if(lvProduct!=null){
			if(ObjectUtils.isNotEmpty(lvProduct.getCode())){
				String hql="select p.id as id,p.productImage as productImage,p.sortId as sortId,s.domainName as domainName" +
						" from LvProductImage p,LvStore s where p.storeId=s.storeFlag and p.productCode='"+lvProduct.getCode()+"' ";
				if(lvProductImage!=null){
					if(ObjectUtils.isNotEmpty(lvProductImage.getId())){
						hql+=" and p.id ="+lvProductImage.getId()+"";
					}
				}
				hql+=" order by p.sortId desc ";
				pag=dao.getMapListByHql(hql, page.getPageNum(), page.getNumPerPage(), null);
			}
		}
		return pag;
	}
	/**
	 * 
	 * @Method: getProductProperty 
	 * @Description:  [根据产品code查询产品的扩展属性分页列表信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-12-22 上午09:49:52]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-12-22 上午09:49:52]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public Pagination getProductProperty(Dto dto) throws ServiceException{
		String orderField = dto.getAsString("orderField");
		String orderDirection = dto.getAsString("orderDirection");
		SimplePage page = (SimplePage)dto.get("page");
		LvProduct lvProduct=(LvProduct)dto.get("lvProduct");
		LvProductProperty lvProductProperty=(LvProductProperty) dto.get("lvProductProperty");
		dto.put("lvProductProperty", lvProductProperty);
		Pagination pag=null;
		if(lvProduct!=null){
			if(ObjectUtils.isNotEmpty(lvProduct.getCode())){
				String hql="from LvProductProperty where productCode='"+lvProduct.getCode()+"' ";
				if(lvProductProperty!=null){
					if(ObjectUtils.isNotEmpty(lvProductProperty.getTitle())){
						hql+=" and title like '%"+lvProductProperty.getTitle().trim()+"%'";
					}
				}
				hql+=" order by sortId desc ";
				pag=dao.find(Finder.create(hql), page.getPageNum(), page.getNumPerPage());
			}
		}
		return pag;
	}
	/**
	 * 
	 * @Method: getProductLadder 
	 * @Description:  [根据产品code查询产品的阶梯价分页列表信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-3-12 下午4:19:58]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-3-12 下午4:19:58]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @return
	 * @throws ServiceException 
	 * @return Pagination
	 */
	public Pagination getProductLadder(Dto dto) throws ServiceException{
		String orderField = dto.getAsString("orderField");
		String orderDirection = dto.getAsString("orderDirection");
		SimplePage page = (SimplePage)dto.get("page");
		LvProduct lvProduct=(LvProduct)dto.get("lvProduct");
		LvProductLadder lvProductLadder=(LvProductLadder) dto.get("lvProductLadder");
		dto.put("lvProductLadder", lvProductLadder);
		Pagination pag=null;
		if(lvProduct!=null){
			if(ObjectUtils.isNotEmpty(lvProduct.getCode())){
				String hql="from LvProductLadder p where productCode='"+lvProduct.getCode()+"' ";

				if(!ObjectUtils.isNullOrEmptyString(orderField)&&!ObjectUtils.isNullOrEmptyString(orderDirection)){
					hql+=" order by p."+ orderField+" "+orderDirection;
				}
				pag=dao.find(Finder.create(hql), page.getPageNum(), page.getNumPerPage());
			}
		}
		return pag;
	}
	
	
	/**
	 * 
	 * @Method: findListByCurrency 
	 * @Description:  [根据币种查询对于商品信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-6-18 下午5:29:33]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-6-18 下午5:29:33]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public Pagination findListByCurrency(Dto dto) throws ServiceException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductTypeServiceImpl.getList() method end*****");
		}
		String orderField = dto.getAsString("orderField");
		String orderDirection = dto.getAsString("orderDirection");
		SimplePage page = (SimplePage)dto.get("page");
		LvProduct lvProduct=(LvProduct)dto.get("lvProduct");
		String currency=(String) dto.get("currency");
		Map<String ,Object> params=new HashMap<String ,Object>();
		StringBuilder hql = new StringBuilder("select t.id as id,t.productName as productName,t.price as price," +
				" t.createTime as createTime,t.code as code,t.storeId as storeId,s.name as name  " +
				" from LvProduct t,LvStore s " +
				" where t.storeId in(select storeFlag from LvStore where currency=:currency)" +
				" and t.storeId=s.storeFlag " +
				" and t.status<>-1 and t.isSupport=1  and s.isdel=0 ");
		params.put("currency", currency);
		if(lvProduct!=null){
            if(ObjectUtils.isNotEmpty(lvProduct.getProductName())){
            	hql.append(" and t.productName like :productName");
            	params.put("productName", "%"+lvProduct.getProductName().trim()+"%");
			}
            if(ObjectUtils.isNotEmpty(lvProduct.getStoreId())){
            	hql.append(" and t.storeId=:storeId");
            	params.put("storeId", lvProduct.getStoreId());
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
		StringBuilder hql = new StringBuilder("select t.id as id,t.productName as productName,t.price as price," +
				" t.createTime as createTime,t.code as code,s.name as name " +
				" from LvProduct t, LvCouponProduct cp,LvStore s " +
				" where t.storeId=s.storeFlag " +
				" and t.code=cp.relationCode" +
				" and cp.couponTypeCode=:couponTypeCode" +
				" and t.status<>-1 and t.isSupport=1  and s.isdel=0");
		param.put("couponTypeCode", couponTypeCode);
		return dao.getMapListByHql(hql.toString(), param);
	}
	
	/**
	 * 
	 * @Method: changeProductList 
	 * @Description:  [获取目标店铺产品分页列表]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2013-2-27 下午03:42:12]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2013-2-27 下午03:42:12]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public List<LvProduct> changeProductList(Dto dto) throws ServiceException{
		String targetFlag=(String) dto.get("targetFlag");
		Pagination pag=null;
		String hql="from LvProduct where status<>-1 ";
		if(ObjectUtils.isNotEmpty(targetFlag)){
			hql+=" and storeId='"+targetFlag+"'";
		}
		List<LvProduct> list=dao.find(hql, null);
		return list;
	}
	/**
	 * 
	 * @Method: delete 
	 * @Description:  [删除产品信息]  
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
			logger.info("***** LvProductServiceImpl.delete() method begin*****");
		}
		String ids = dto.getAsString("ids");
		String hql="update LvProduct set status=-1 where id in ("+ids+")";
		dao.update(hql,null);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductServiceImpl.delete() method end*****");
		}
	}
	/**
	 * 
	 * @Method: get 
	 * @Description:  [根据id查询产品信息详情]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-3 上午11:05:15]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-3 上午11:05:15]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public LvProduct get(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductServiceImpl.get() method begin*****");
		}
		LvProduct lvProduct=(LvProduct)dto.get("lvProduct");
		lvProduct=(LvProduct) dao.load(LvProduct.class, lvProduct.getId());
		
		//判断是否为套餐
		if(lvProduct!=null){
			if(ObjectUtils.isNotEmpty(lvProduct.getIsSetMeal())){
				if(lvProduct.getIsSetMeal()==1){
					dto.put("productCode", lvProduct.getCode());
					List<LvProductPackage> list=(List<LvProductPackage>) lvProductPackageService.getProductPackage(dto);
					if(list!=null){
						lvProduct.setPackageList(list);
					}
				}
			}
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductServiceImpl.get() method end*****");
		}
		return lvProduct;
	}
	/**
	 * 
	 * @Method: save 
	 * @Description:  [新增产品信息(发布新产品)]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-3 上午11:05:19]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-3 上午11:05:19]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public synchronized Boolean save(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductServiceImpl.save() method begin*****");
		}
		//新增保存产品信息
		LvProduct lvProduct=(LvProduct)dto.get("lvProduct");
		LvPubPackage lvPubPackage=(LvPubPackage) dto.get("lvPubPackage");
		//判断该同一类型的产品名称是否已经存在
		String hql="from LvProduct where productName=:productName and ptype=:ptype and storeId=:storeId and status<>-1 ";
		Map param=new HashMap();
		param.put("productName", lvProduct.getProductName());
		param.put("ptype", lvProduct.getPtype());
		param.put("storeId", lvProduct.getStoreId());
		LvProduct product=(LvProduct) dao.findUnique(hql, param);
		if(product!=null){
			return false;
		}
		
		
		if(ObjectUtils.isNotEmpty(lvProduct.getIsSetMeal())&&lvProduct.getIsSetMeal()==1){
			//显示判断当前套餐是否存在套餐关系表信息，如果存在先删除，再添加新的关系
			int num=dao.countByProperty(LvProductPackage.class, "packageNum", lvProduct.getCode());
			if(num>0){
				LvProductPackage lp=new LvProductPackage();
				lp.setPackageNum(lvProduct.getCode());
				dto.put("lvProductPackage", lp);
				lvProductPackageService.delete(dto);
			}
			//设置商品和公共套餐的关系
			lvProduct.setPubProductCode(lvPubPackage.getCode());
			lvProduct.setPubPackageName(lvPubPackage.getPackageName());
			
			List<LvPubPackageDetails> packageDetailsList=lvPubPackage.getDetailsList();
			if(ObjectUtils.isNotEmpty(packageDetailsList)){
				for (int i = 0; i < packageDetailsList.size(); i++) {
					LvPubPackageDetails pubPackageDetails=packageDetailsList.get(i);
					if(ObjectUtils.isNotEmpty(pubPackageDetails)){
						LvProductPackage lvProductPackage=new LvProductPackage();
						lvProductPackage.setPackageNum(lvProduct.getCode());
						lvProductPackage.setPnum(pubPackageDetails.getProductNum());
						lvProductPackage.setPubProductCode(pubPackageDetails.getPubProductCode());
						lvProductPackage.setPubPackageDetailsCode(pubPackageDetails.getCode());
						//根据公共商品code和店铺标志查询店铺商品信息
						dto.put("pubProductCode",pubPackageDetails.getPubProductCode());
						dto.put("storeId", lvProduct.getStoreId());
						LvProduct pt=this.findByPubProductCode(dto);
						if(pt!=null){
							lvProductPackage.setProductCode(pt.getCode());//店铺商品code
							lvProductPackage.setPcode(pt.getPcode());
							lvProductPackage.setPriceUsd(pt.getPriceUsd());
							lvProductPackage.setPriceRmb(pt.getPriceRmb());
							lvProductPackage.setPrice(pt.getPrice());
						}
						lvProductPackage.setCreateTime(new Date());
						lvProductPackage.setCode(CodeUtils.getCode());
						lvProductPackage.setStoreId(lvProduct.getStoreId());
						dto.put("lvProductPackage", lvProductPackage);
						lvProductPackageService.save(dto);
					}
				}
			}
		}
		
		//保存商品信息
		dao.save(lvProduct);

		//执行单个页面静态化任务
		String htmlPath="/products/"+lvProduct.getCode()+".html";
		initLinkDataService.sendHtmlToWeb(htmlPath, lvProduct.getStoreId());
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductServiceImpl.save() method end*****");
		}
		return true;
	}
	/**
	 * 
	 * @Method: update 
	 * @Description:  [修改产品信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-3 上午11:05:24]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-3 上午11:05:24]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public synchronized Boolean update(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductServiceImpl.update() method begin*****");
		}
		//修改产品信息
		LvProduct lvProduct=(LvProduct)dto.get("lvProduct");
		LvPubPackage lvPubPackage=(LvPubPackage) dto.get("lvPubPackage");
		if(!lvProduct.getOldProductName().equals(lvProduct.getProductName())||!lvProduct.getOldPType().equals(lvProduct.getPtype())){
			//判断该同一类型的产品名称是否已经存在
			String hql="from LvProduct where productName=:productName and ptype=:ptype and storeId=:storeId and status<>-1";
			Map param=new HashMap();
			param.put("productName", lvProduct.getProductName());
			param.put("ptype", lvProduct.getPtype());
			param.put("storeId", lvProduct.getStoreId());
			LvProduct product=(LvProduct) dao.findUnique(hql, param);
			if(product!=null){
				return false;
			}
		}
		
		if(ObjectUtils.isNotEmpty(lvProduct.getIsSetMeal())&&lvProduct.getIsSetMeal()==1){
			List<LvPubPackageDetails> packageDetailsList=lvPubPackage.getDetailsList();
			if(ObjectUtils.isNotEmpty(packageDetailsList)){
				//显示判断当前套餐是否存在套餐关系表信息，如果存在先删除，再添加新的关系
				int num=dao.countByProperty(LvProductPackage.class, "packageNum", lvProduct.getCode());
				if(num>0){
					LvProductPackage lp=new LvProductPackage();
					lp.setPackageNum(lvProduct.getCode());
					dto.put("lvProductPackage", lp);
					lvProductPackageService.delete(dto);
				}
				//设置商品和公共套餐的关系
				lvProduct.setPubProductCode(lvPubPackage.getCode());
				lvProduct.setPubPackageName(lvPubPackage.getPackageName());
				
				//循环遍历套餐详情信息
				for (int i = 0; i < packageDetailsList.size(); i++) {
					LvPubPackageDetails pubPackageDetails=packageDetailsList.get(i);
					if(ObjectUtils.isNotEmpty(pubPackageDetails)){
						LvProductPackage lvProductPackage=new LvProductPackage();
						lvProductPackage.setPackageNum(lvProduct.getCode());
						lvProductPackage.setPnum(pubPackageDetails.getProductNum());
						lvProductPackage.setPubProductCode(pubPackageDetails.getPubProductCode());
						lvProductPackage.setPubPackageDetailsCode(pubPackageDetails.getCode());
						//根据公共商品code和店铺标志查询店铺商品信息
						dto.put("pubProductCode",pubPackageDetails.getPubProductCode());
						dto.put("storeId", lvProduct.getStoreId());
						LvProduct pt=this.findByPubProductCode(dto);
						if(pt!=null){
							lvProductPackage.setProductCode(pt.getCode());//店铺商品code
							lvProductPackage.setPcode(pt.getPcode());
							lvProductPackage.setPriceUsd(pt.getPriceUsd());
							lvProductPackage.setPriceRmb(pt.getPriceRmb());
							lvProductPackage.setPrice(pt.getPrice());
						}
						lvProductPackage.setCreateTime(new Date());
						lvProductPackage.setCode(CodeUtils.getCode());
						lvProductPackage.setStoreId(lvProduct.getStoreId());
						dto.put("lvProductPackage", lvProductPackage);
						lvProductPackageService.save(dto);
					}
				}
			}
		}
		
		//更新商品信息
		dao.update(lvProduct);
		

		if (logger.isInfoEnabled()){
			logger.info("***** LvProductServiceImpl.update() method end*****");
		}
		return true;
		
	}
	
	public Boolean updateProduct(Dto dto) throws ServiceException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductServiceImpl.update() method begin*****");
		}
		//修改产品信息
		LvProduct lvProduct=(LvProduct)dto.get("lvProduct");
		if(!lvProduct.getOldProductName().equals(lvProduct.getProductName())||!lvProduct.getOldPType().equals(lvProduct.getPtype())){
			//判断该同一类型的产品名称是否已经存在
			String hql="from LvProduct where productName=:productName and ptype=:ptype and storeId=:storeId and status<>-1";
			Map param=new HashMap();
			param.put("productName", lvProduct.getProductName());
			param.put("ptype", lvProduct.getPtype());
			param.put("storeId", lvProduct.getStoreId());
			LvProduct product=(LvProduct) dao.findUnique(hql, param);
			if(product!=null){
				return false;
			}
		}
		
		dao.update(lvProduct);
		return true;
	}
	
	/**
	 * 
	 * @Method: updateType 
	 * @Description:  [批量修改商城商品分类类型]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-12-21 下午03:23:01]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-12-21 下午03:23:01]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public void updateType(Dto dto)throws ServiceException{
		String ids=(String) dto.get("ids");
		LvProduct lvProduct=(LvProduct) dto.get("lvProduct");
		if(ObjectUtils.isNotEmpty(ids)){
			String hql="update LvProduct set shopProductType=:shopProductType ," +
			" modifyUserId=:modifyUserId,modifyUserName=:modifyUserName,modifyTime=:modifyTime" +
			" where id in("+ids+")";
			Map param=new HashMap();
			param.put("shopProductType", lvProduct.getShopProductType());
			param.put("modifyUserId", lvProduct.getModifyUserId());
			param.put("modifyUserName", lvProduct.getModifyUserName());
			param.put("modifyTime", lvProduct.getModifyTime());
		    dao.update(hql, param);
		}
	}
	/**
	 * 
	 * @Method: updateUnSupport 
	 * @Description:  [商家商品下架，填写下架说明]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-12-21 下午04:01:50]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-12-21 下午04:01:50]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public void updateUnSupport(Dto dto)throws ServiceException{
		String ids=(String) dto.get("ids");
		LvProduct lvProduct=(LvProduct) dto.get("lvProduct");
		if(ObjectUtils.isNotEmpty(ids)){
			String hql="update LvProduct set isSupport=:isSupport ," +
					" unsupportRemark=:unsupportRemark," +
					" unsupportTime=:unsupportTime," +
			        " modifyUserId=:modifyUserId," +
			        " modifyUserName=:modifyUserName," +
			        " modifyTime=:modifyTime" +
			        " where id in("+ids+")";
			Map param=new HashMap();
			param.put("isSupport", 0);
			param.put("unsupportRemark", lvProduct.getUnsupportRemark());
			param.put("unsupportTime", lvProduct.getModifyTime());
			param.put("modifyUserId", lvProduct.getModifyUserId());
			param.put("modifyUserName", lvProduct.getModifyUserName());
			param.put("modifyTime", lvProduct.getModifyTime());
		    dao.update(hql, param);
		    
		    //根据id查询产品信息，在根据产品信息删除商城产品(即商城产品下架)
		    hql="from LvProduct where id in("+ids+")";
		    List<LvProduct> productList=dao.find(hql, null);
		    for (LvProduct product : productList) {
				hql="delete from LvShopProduct where productCode='"+product.getCode()+"'";
				dao.delete(hql, null);
				
				//删除存在购物车中已经下架的商品信息
				hql="delete from LvShopCart where productCode='"+product.getCode()+"'";
				dao.delete(hql, null);
			}
		}
	}
	/**
	 * 
	 * @Method: getAll 
	 * @Description:  [查询所有的产品信息（包括下架，删除的）]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-12-9 下午02:30:49]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-12-9 下午02:30:49]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public List<LvProduct> getAll(Dto dto) throws ServiceException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductServiceImpl.getAll() method begin*****");
		}		
		String hql = "from LvProduct p where 1=1";
		//判断当前是商城入口，还是商家入口
		String storeListString=""; 
		if(ObjectUtils.isNotEmpty(dto.getAsString("flag"))){
			String [] arr=dto.getAsString("flag").split(",");
			String temp="";
			for (int i = 0; i < arr.length; i++) {
				if (ObjectUtils.isNotEmpty(arr[i])) {
					for(Map.Entry<String, String> entry:Constants.STORE_LIST.entrySet()){   
						if(arr[i].trim().equals(entry.getKey())){
						   if(ObjectUtils.isNotEmpty(storeListString)){
							   storeListString+=","+entry.getValue();
						   }else{
							   storeListString+=entry.getValue();
						   }
						}
					}
				}
			}
			if(ObjectUtils.isNotEmpty(storeListString)){
				hql+=" and p.storeId in ("+storeListString+")";
			}else{
				String storeList=StringUtil.stringFormart(dto.getAsString("flag"), ",");
				hql+=" and p.storeId in("+storeList+")";
			}
		}
		List<LvProduct> list = dao.find(hql, null);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductServiceImpl.getAll() method end*****");
		}	
	    return list;
	}
	/**
	 * 
	 * @Method: getAllProduct
	 * @Description:  [查询所有上架未删除的产品信息列表（包括套餐）]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-16 下午04:50:48]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-16 下午04:50:48]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public List<LvProduct> getAllProduct(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductServiceImpl.getAll() method begin*****");
		}
		LvProduct lvProduct=(LvProduct) dto.get("lvProduct");
		StringBuilder hql = new StringBuilder("from LvProduct p where status<>-1 and isSupport=1");
		if(lvProduct!=null){
			if(ObjectUtils.isNotEmpty(lvProduct.getStoreId())){
				hql.append(" and storeId ='"+lvProduct.getStoreId()+"'");
			}
		}
//		if(ObjectUtils.isNotEmpty(dto.getAsString("flag"))){//店铺标示
//			String storeList=StringUtil.stringFormart(dto.getAsString("flag"), ",");
//			hql+=" and storeId in("+storeList+")";
//		}
		hql.append(StoreHelpUtil.getNoShopRelevanceString(dto.getAsString("flag"), "storeId", "p"));
		List<LvProduct> list = dao.find(hql.toString(), null);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductServiceImpl.getAll() method end*****");
		}	
	    return list;
	}
	/**
	 * 
	 * @Method: getAllProduct 
	 * @Description:  [查询未删除的产品列表信息(非套餐,不考虑上下架问题)]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-4 上午10:23:00]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-4 上午10:23:00]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public List<LvProduct> getAllNoMealProduct(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductServiceImpl.getAllNoMealProduct() method begin*****");
		}	
		LvProduct lvProduct=(LvProduct) dto.get("lvProduct");
		String hql = "from LvProduct p where status<>-1  and isSetMeal<>1";
		if(lvProduct!=null){
			if(ObjectUtils.isNotEmpty(lvProduct.getStoreId())){
				hql+=" and storeId='"+lvProduct.getStoreId()+"'";
			}
		}
		
//		if(ObjectUtils.isNotEmpty(dto.getAsString("flag"))){//店铺标示
//			String storeList=StringUtil.stringFormart(dto.getAsString("flag"), ",");
//			hql+=" and storeId in("+storeList+")";
//		}
		hql+=StoreHelpUtil.getNoShopRelevanceString(dto.getAsString("flag"), "storeId", "p").toString();
		List<LvProduct> list = dao.find(hql, null);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductServiceImpl.getAllNoMealProduct() method end*****");
		}	
	    return list;
	}
	/**
	 * 
	 * @Method: getAllMealProduct 
	 * @Description:  [获取所有的为套餐的产品信息列表]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-4 下午03:59:06]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-4 下午03:59:06]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public List<LvProduct> getAllMealProduct(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductServiceImpl.getAllMealProduct() method begin*****");
		}	
		String hql = "from LvProduct where status<>-1 and isSupport=1 and isSetMeal=1";
		if(ObjectUtils.isNotEmpty(dto.getAsString("flag"))){//店铺标示
			String storeList=StringUtil.stringFormart(dto.getAsString("flag"), ",");
			hql+=" and storeId in("+storeList+")";
		}
		List<LvProduct> list = dao.find(hql, null);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductServiceImpl.getAllMealProduct() method end*****");
		}	
		return list;
	}
	
	/**
	 * 
	 * @Method: getLadderProduct 
	 * @Description:  [获取所有设置阶梯价的产品信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-9-20 下午05:05:16]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-9-20 下午05:05:16]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public List<LvProduct> getLadderProduct(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductServiceImpl.getLadderProduct() method begin*****");
		}
		LvProduct lvProduct=(LvProduct) dto.get("lvProduct");
		String hql = "from LvProduct p where status<>-1 and isSupport=1 and isLadder=1";
		if(lvProduct!=null){
			if(ObjectUtils.isNotEmpty(lvProduct.getStoreId())){
				hql+=" and storeId='"+lvProduct.getStoreId()+"'";
			}
		}
//		if(ObjectUtils.isNotEmpty(dto.getAsString("flag"))){//店铺标示
//			String storeList=StringUtil.stringFormart(dto.getAsString("flag"), ",");
//			hql+=" and storeId in("+storeList+")";
//		}
		hql+=StoreHelpUtil.getNoShopRelevanceString(dto.getAsString("flag"), "storeId", "p").toString();
		List<LvProduct> list = dao.find(hql, null);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductServiceImpl.getLadderProduct() method end*****");
		}	
		return list;
	}

	/**
	 * 
	 * @Method: getActivityProduct 
	 * @Description:  [获取所有开启活动的产品信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-9-20 下午05:38:31]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-9-20 下午05:38:31]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public List<LvProduct> getActivityProduct(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductServiceImpl.getActivityProduct() method begin*****");
		}	
		LvProduct lvProduct=(LvProduct) dto.get("lvProduct");
		String hql = "from LvProduct p where status<>-1 and isSupport=1 and isActivity=1";
		if(lvProduct!=null){
			if(ObjectUtils.isNotEmpty(lvProduct.getStoreId())){
				hql+=" and storeId='"+lvProduct.getStoreId()+"'";
			}
		}
		
//		if(ObjectUtils.isNotEmpty(dto.getAsString("flag"))){//店铺标示
//			String storeList=StringUtil.stringFormart(dto.getAsString("flag"), ",");
//			hql+=" and storeId in("+storeList+")";
//		}
		hql+=StoreHelpUtil.getNoShopRelevanceString(dto.getAsString("flag"), "storeId", "p").toString();
		List<LvProduct> list = dao.find(hql, null);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductServiceImpl.getActivityProduct() method end*****");
		}	
		return list;
	}


	/**
	 * 
	 * @Method: undercarriage 
	 * @Description:  [一句话描述该类的功能]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-9 下午03:30:46]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-9 下午03:30:46]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public void updateSupport(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductServiceImpl.undercarriage() method begin*****");
		}
		LvProduct lvProduct=(LvProduct)dto.get("lvProduct");
		if(ObjectUtils.isNotEmpty(lvProduct.getCode())&&ObjectUtils.isNotEmpty(lvProduct.getIsSupport())){
			String hql="update LvProduct set isSupport="+lvProduct.getIsSupport()+" where code='"+lvProduct.getCode()+"'";
			dao.update(hql,null);
			
			//在根据产品信息删除商城产品(即商城产品下架)
			if(lvProduct.getIsSupport()==0){
				hql="delete from LvShopProduct where productCode='"+lvProduct.getCode()+"'";
				dao.delete(hql, null);
				
				//删除存在购物车中已经下架的商品信息
				hql="delete from LvShopCart where productCode='"+lvProduct.getCode()+"'";
				dao.delete(hql, null);
			}
		}
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductServiceImpl.undercarriage() method end*****");
		}
		
	}

	/**
	 * 
	 * @Method: getProduct 
	 * @Description:  [通过code查询产品详细信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-10 下午02:48:03]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-10 下午02:48:03]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public LvProduct getProduct(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductServiceImpl.getProduct() method begin*****");
		}
		LvProduct lvProduct=(LvProduct) dto.get("lvProduct");
		if(ObjectUtils.isNotEmpty(lvProduct.getCode())){
			lvProduct=(LvProduct) dao.findUniqueByProperty(LvProduct.class, "code", lvProduct.getCode());
		}
		
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvProductServiceImpl.getProduct() method end*****");
		}
		return lvProduct;
	}
	
	
	/**
	 * 根据code查找对应的产品
	 * 需要传递参数code
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public LvProduct getProductByCode(Dto dto) throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("***** ProductServiceImpl.getProductByCode() method begin*****");
		}	
		String hql="from LvProduct where code=:code and storeId=:storeId";
		HashMap map=new HashMap();
		map.put("code", dto.getAsString("pcode"));
		map.put("storeId", dto.getAsString("storeFlag"));
		LvProduct product=(LvProduct)dao.findUnique(hql,map);
		
		if (logger.isInfoEnabled()){
			logger.info("***** ProductServiceImpl.getProductByCode() method end*****");
		}	
		
		return product;
	}

	/**
	 * 根据公共商品code和店铺标示查找对应的产品
	 * 需要传递参数code
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public LvProduct findByPubProductCode(Dto dto)throws ServiceException{
		if (logger.isInfoEnabled()){
			logger.info("***** ProductServiceImpl.findByPubProductCode() method begin*****");
		}
		LvProduct product=null;
		String hql="from LvProduct where pubProductCode=:pubProductCode and storeId=:storeId and status<>-1";
		HashMap map=new HashMap();
		map.put("pubProductCode", dto.getAsString("pubProductCode"));
		map.put("storeId", dto.getAsString("storeId"));
		List list=dao.find(hql, map);
		if(list!=null&&list.size()>0){
			product=(LvProduct) list.get(0);
		}		
		if (logger.isInfoEnabled()){
			logger.info("***** ProductServiceImpl.findByPubProductCode() method end*****");
		}	
		
		return product;
	}
	
	/**
	 * 
	 * @Method: isExistPubProduct 
	 * @Description:  [判断当前店铺商品是否已经添加了公共商品信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-3-18 上午10:47:24]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-3-18 上午10:47:24]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public Boolean isExistPubProduct(Dto dto)throws ServiceException{
		if (logger.isInfoEnabled()){
			logger.info("***** ProductServiceImpl.isExistPubProduct() method begin*****");
		}	
		String hql="from LvProduct p where p.status<>-1  ";
		HashMap<String,Object> param=new HashMap<String,Object>();
		if(ObjectUtils.isNotEmpty(dto.getAsString("pubProductCode"))){
			hql+=" and pubProductCode=:pubProductCode";
			param.put("pubProductCode", dto.getAsString("pubProductCode"));
		}
		if(ObjectUtils.isNotEmpty(dto.getAsString("storeId"))){
			hql+=" and storeId=:storeId";
			param.put("storeId", dto.getAsString("storeId"));
		}
		List list= dao.find(hql, param);
		if(list!=null&&list.size()>0){
			return true;
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** ProductServiceImpl.isExistPubProduct() method end*****");
		}
        return false;
	}
	
	/**
	 * 
	 * @Method: isExistPubPackage 
	 * @Description:  [判断当前套餐产品是否已经在店铺中存在]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-3-18 上午10:51:20]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-3-18 上午10:51:20]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public Boolean isExistPubPackage(Dto dto)throws ServiceException{
		if (logger.isInfoEnabled()){
			logger.info("***** ProductServiceImpl.isExistPubPackage() method begin*****");
		}	
		String hql="from LvProduct p where p.status<>-1 ";
		HashMap<String,Object> param=new HashMap<String,Object>();
		if (ObjectUtils.isNotEmpty(dto.getAsString("pubPackageCode"))) {
			hql+=" and pubPackageCode=:pubPackageCode";
			param.put("pubPackageCode", dto.getAsString("pubPackageCode"));
		}
		if (ObjectUtils.isNotEmpty(dto.getAsString("storeId"))) {
			hql+=" and storeId=:storeId";
			param.put("storeId", dto.getAsString("storeId"));
		}
		List list= dao.find(hql, param);
		if(list!=null&&list.size()>0){
			return true;
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** ProductServiceImpl.isExistPubPackage() method end*****");
		}
		
		return false;
	}
	
	/**
	 * 获取该产品的活动信息
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public LvActivity getActivityByCode(Dto dto) throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("***** ProductServiceImpl.getActivityByCode() method begin*****");
		}	
		
		String pcode=dto.getAsString("pcode");
		String storeFlag=dto.getAsString("storeFlag");
		LvActivity activity=null;
		SimpleDateFormat  format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		//获取最新活动
		String hql = "from LvActivity where productCode=:pcode and storeId=:storeFlag and status=1 order by modifyTime desc";
		HashMap param = new HashMap();
		param.put("storeFlag", storeFlag);
		param.put("pcode", pcode);
		List<LvActivity> aList=(List<LvActivity>)dao.find(hql, param);
		
		if(aList!=null&&aList.size()>0){
			activity=aList.get(0);
			//获取活动属性表
			hql = "from LvActivityInfo where activityCode=:activityCode and storeId=:storeFlag";
			param.clear();
			param.put("activityCode", activity.getCode());
			param.put("storeFlag", storeFlag);
			List<LvActivityInfo> infoList=(List<LvActivityInfo>)dao.find(hql, param);
//			if(infoList!=null){
//				//如果是1限时，则将活动开始时间和结束时间封装进去，便于前台取值
//				if(activity.getActivityType()==1){
//					for(LvActivityInfo info:infoList){
//						try {
//							if(info.getPropertyKey().equals("startTime")){
//								activity.setStartTime(format.parse(info.getPropertyValue()));
//							}
//							if(info.getPropertyKey().equals("endTime")){
//								activity.setEndTime(format.parse(info.getPropertyValue()));
//							}
//						} catch (ParseException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//					}
//				}
//				
//				//如果是2限量，则将活动产品数量封装进去
//				if(activity.getActivityType()==2){
//					for(LvActivityInfo info:infoList){
//						if(info.getPropertyKey().equals("counts")){
//							activity.setCounts(Integer.parseInt(info.getPropertyValue()));
//						}
//					}
//				}
//			}
		}

		if (logger.isInfoEnabled()){
			logger.info("***** ProductServiceImpl.getActivityByCode() method end*****");
		}	
		
		return activity;
	}
	/**
	 * 
	 * @Method: upload 
	 * @Description:  [一句话描述该类的功能]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-3 下午06:25:25]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-3 下午06:25:25]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String upload(Dto dto){
		String imgFileName=(String) dto.get("imgFileName");
		File img=(File) dto.get("img");
		HttpServletRequest request=(HttpServletRequest) dto.get("request");
		LvProduct lvProduct=(LvProduct) dto.get("lvProduct");
		String storeId="";
		if(lvProduct!=null){
			if(ObjectUtils.isNotEmpty(lvProduct.getStoreId())){
				storeId=lvProduct.getStoreId();
			}
		}else if(ObjectUtils.isNotEmpty(dto.getAsString("flag"))){//店铺标示
			storeId=dto.getAsString("flag");
		}

		//获取资源管理域名
		String systemFlag=Constants.STORE_TO_MALL_SYSTEM.get(storeId);//根据店铺标志查询商城体系
	    String domainPostfix=Constants.MALL_TO_DOMAIN_POSTFIX.get(systemFlag);//更加商城体系查找体系对于后缀域名
	    String resDomain="http://res"+domainPostfix;
	    String resPath = dto.getAsString("resPath"); //获取资源文件路径 
	    
//		String basepath=request.getRealPath("/web/"+storeId+"/res/images");//文件上传资源路径
	    String basepath=resPath+"/upload/"+storeId+"/res/images";//文件上传资源路径
		String imgName="product_"+System.currentTimeMillis()+imgFileName.substring(imgFileName.indexOf("."));
		FileUpload.upload(img,basepath, imgName);
		return resDomain+"/upload/"+storeId+"/res/images/"+imgName;
	}




	


}
