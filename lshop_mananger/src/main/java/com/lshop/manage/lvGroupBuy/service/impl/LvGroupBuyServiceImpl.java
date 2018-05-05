package com.lshop.manage.lvGroupBuy.service.impl;

import java.io.File;
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
import com.lshop.common.pojo.logic.LvGroupBuy;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.util.Constants;
import com.lshop.common.util.StoreHelpUtil;
import com.lshop.common.util.StringUtil;
import com.lshop.manage.lvGroupBuy.service.LvGroupBuyService;
import com.lshop.manage.lvProduct.service.LvProductService;

/**
 * 
 *  
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvGroupBuy.service.impl.LvGroupBuyServiceImpl.java]  
 * @ClassName:    [LvGroupBuyServiceImpl]   
 * @Description:  [团购信息管理-数据访问层]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-7-23 下午05:13:05]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-7-23 下午05:13:05]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v1.0] 
 *
 */
@Service("LvGroupBuyService")
public class LvGroupBuyServiceImpl implements LvGroupBuyService {
	private static final Log logger	= LogFactory.getLog(LvGroupBuyServiceImpl.class);
	@Resource 
	private HibernateBaseDAO dao;
	@Resource 
	private LvProductService lvProductService;
	  /**
     * 
     * @Method: getList 
     * @Description:  [分页查询 团购信息]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-7-23 下午04:52:16]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-7-23 下午04:52:16]   
     * @UpdateRemark: [说明本次修改内容]  
     * @throws
     */
	
	/**
	 * 
	 * @Method: getAll 
	 * @Description:  [查询所有团购信息列表]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-24 上午09:45:39]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-24 上午09:45:39]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public List<LvGroupBuy> getAll(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvGroupBuyServiceImpl.getAll() method begin*****");
		}
		LvGroupBuy lvGroupBuy=(LvGroupBuy) dto.get("lvGroupBuy");
		StringBuilder hql = new StringBuilder( "from LvGroupBuy t where 1=1");
		if(lvGroupBuy!=null){
			if(ObjectUtils.isNotEmpty(lvGroupBuy.getStoreId())){
				hql.append(" and storeId='"+lvGroupBuy.getStoreId()+"'");
			}
		}

		hql.append(StoreHelpUtil.getNoShopRelevanceString(dto.getAsString("flag"), "storeId", "t"));
		List<LvGroupBuy> list = dao.find(hql.toString(), null);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvGroupBuyServiceImpl.getAll() method end*****");
		}	
	    return list;
	}
	
	@Override
	public Pagination getList(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvGroupPropertyServiceImpl.getList() method begin*****");
		}
		String orderField = dto.getAsString("orderField");
		String orderDirection = dto.getAsString("orderDirection");
		SimplePage page = (SimplePage)dto.get("page");
		LvGroupBuy lvGroupBuy=(LvGroupBuy) dto.get("lvGroupBuy");
		Map<String ,Object> params=new HashMap<String ,Object>();
		StringBuilder hql = new StringBuilder("from LvGroupBuy t where 1=1 ");
		if(lvGroupBuy!=null){
			if(ObjectUtils.isNotEmpty(lvGroupBuy.getProductCode())){
				hql.append(" and productCode=:productCode");
				params.put("productCode", lvGroupBuy.getProductCode());
			}
			if(ObjectUtils.isNotEmpty(lvGroupBuy.getTitle())){
				hql.append(" and title like :title");
				params.put("title","%"+lvGroupBuy.getTitle()+"%");
			}
			if(ObjectUtils.isNotEmpty(lvGroupBuy.getStoreId())){
				hql.append(" and storeId=:storeId");
				params.put("storeId", lvGroupBuy.getStoreId());
			}
		}
		//店铺标识
//		hql.append(StoreHelpUtil.getStoreRelevanceString(dto.getAsString("flag"),"storeId","t"));
		hql.append(StoreHelpUtil.getNoShopRelevanceString(dto.getAsString("flag"), "storeId", "t"));
		if (ObjectUtils.isNotEmpty(orderField)&& ObjectUtils.isNotEmpty(orderDirection)) {
			hql.append(" order by " + orderField + " " + orderDirection);
		}
	
		Finder finder = Finder.create(hql.toString());
		finder.setParams(params);
		return dao.find(finder, page.getPageNum(), page.getNumPerPage());
	}
    /**
     * 
     * @Method: delete 
     * @Description:  [删除团购信息]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-7-23 下午04:53:38]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-7-23 下午04:53:38]   
     * @UpdateRemark: [说明本次修改内容]  
     * @throws
     */
	@Override
	public void delete(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvGroupBuyServiceImpl.delete() method begin*****");
		}
		LvGroupBuy lvGroupBuy=(LvGroupBuy) dto.get("lvGroupBuy");
		dao.delete(lvGroupBuy);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvGroupBuyServiceImpl.delete() method end*****");
		}
		
	}

    /**
     * 
     * @Method: save 
     * @Description:  [新增团购信息]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-7-23 下午04:53:58]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-7-23 下午04:53:58]   
     * @UpdateRemark: [说明本次修改内容]  
     * @throws
     */
	@Override
	public Boolean save(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvGroupBuyServiceImpl.save() method begin*****");
		}
		LvGroupBuy lvGroupBuy=(LvGroupBuy) dto.get("lvGroupBuy");
		LvProduct lvProduct=new LvProduct();
		lvProduct.setCode(lvGroupBuy.getProductCode());
		dto.put("lvProduct", lvProduct);
		lvProduct=lvProductService.getProduct(dto);
		lvGroupBuy.setStoreId(lvProduct.getStoreId());
		
		dao.save(lvGroupBuy);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvGroupBuyServiceImpl.save() method end*****");
		}
		return true;
	}

    /**
     * 
     * @Method: update 
     * @Description:  [更新团购信息]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-7-23 下午04:54:21]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-7-23 下午04:54:21]   
     * @UpdateRemark: [说明本次修改内容]  
     * @throws
     */
	@Override
	public Boolean update(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvGroupBuyServiceImpl.update() method begin*****");
		}
		LvGroupBuy lvGroupBuy=(LvGroupBuy) dto.get("lvGroupBuy");
//		if(lvGroupBuy.getStatus()==1){//存在一个产品多个为开启状态的活动
//			//判断当前是否存在开启状态的活动
//			dto.put("productCode", lvGroupBuy.getProductCode());
//			Boolean flag=isExistGroupBy(dto);
//			if(!flag){
//				return false;
//			}	
//		}
		dao.update(lvGroupBuy);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvGroupBuyServiceImpl.update() method end*****");
		}
		return true;
	}
	
    /**
     * 
     * @Method: get 
     * @Description:  [查询团购信息详情]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-7-23 下午04:54:40]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-7-23 下午04:54:40]   
     * @UpdateRemark: [说明本次修改内容]  
     * @throws
     */
	@Override
	public LvGroupBuy get(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvGroupBuyServiceImpl.get() method begin*****");
		}
		LvGroupBuy lvGroupBuy=(LvGroupBuy) dto.get("lvGroupBuy");
		lvGroupBuy=(LvGroupBuy) dao.load(LvGroupBuy.class, lvGroupBuy.getId());
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvGroupBuyServiceImpl.get() method end*****");
		}
		return lvGroupBuy;
	}
	

	
	/**
	 * 
	 * @Method: upload 
	 * @Description:  [团购图片上传]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-10-24 下午02:18:28]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-10-24 下午02:18:28]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @return 
	 * @return String
	 */
	public String upload(Dto dto){
		
		String imgFileName=(String) dto.get("imgFileName");
		File img=(File) dto.get("img");
		HttpServletRequest request=(HttpServletRequest) dto.get("request");
		LvGroupBuy lvGroupBuy=(LvGroupBuy) dto.get("lvGroupBuy");
		
		//获取资源管理域名
		String systemFlag=Constants.STORE_TO_MALL_SYSTEM.get(lvGroupBuy.getStoreId());//根据店铺标志查询商城体系
	    String domainPostfix=Constants.MALL_TO_DOMAIN_POSTFIX.get(systemFlag);//更加商城体系查找体系对于后缀域名
	    String resDomain="http://res"+domainPostfix;
	    String resPath = dto.getAsString("resPath"); //获取资源文件路径 
	    
		if(lvGroupBuy!=null){
			if(ObjectUtils.isNotEmpty(lvGroupBuy.getStoreId())){
				String basepath=resPath+"/upload/"+lvGroupBuy.getStoreId()+"/res/images";//文件上传资源路径
				String imgName="group_"+System.currentTimeMillis()+imgFileName.substring(imgFileName.indexOf("."));
				FileUpload.upload(img,basepath, imgName);
				return resDomain+"/upload/"+lvGroupBuy.getStoreId()+"/res/images/"+imgName;
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @Method: isExistGroupBy 
	 * @Description:  [判断是一个产品否存在活动的团购]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-12-7 上午10:14:46]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-12-7 上午10:14:46]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Boolean isExistGroupBy(Dto dto) throws ServiceException {
		String productCode=(String) dto.get("productCode");
		if(ObjectUtils.isNotEmpty(productCode)){
			String hql="from LvGroupBuy where productCode='"+productCode+"' and status=1";
			if(ObjectUtils.isNotEmpty(dto.getAsString("flag"))){//店铺标示
				hql+=" and storeId='"+dto.getAsString("flag")+"'";
			}
			Integer num= dao.countQueryResult(Finder.create(hql), null);
			if (num>0) {
				return false;
			}
		}
	    return true;	
	}

}
