package com.lshop.manage.lvStore.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvAd;
import com.lshop.common.pojo.logic.LvCarriageSet;
import com.lshop.common.pojo.logic.LvContent;
import com.lshop.common.pojo.logic.LvEmailTpl;
import com.lshop.common.pojo.logic.LvLinkUrl;
import com.lshop.common.pojo.logic.LvNavigation;
import com.lshop.common.pojo.logic.LvPaymentStyle;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.pojo.logic.LvProductImage;
import com.lshop.common.pojo.logic.LvProductPackage;
import com.lshop.common.pojo.logic.LvProductProperty;
import com.lshop.common.pojo.logic.LvProductType;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.util.CodeUtils;
import com.lshop.common.util.Constants;
import com.lshop.common.util.PropertiesHelper;
import com.lshop.manage.lvStore.service.InitStoreDataService;


/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvStore.service.impl.InitStoreDataServiceImpl.java]  
 * @ClassName:    [InitStoreDataServiceImpl]   
 * @Description:  [新增店铺初始化数据移植]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2013-1-30 下午03:23:49]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2013-1-30 下午03:23:49]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v3.0]
 */
@Service("InitStoreDataService")
public class InitStoreDataServiceImpl implements InitStoreDataService {
	
	@Resource private HibernateBaseDAO dao;
	private static final Log logger	= LogFactory.getLog(InitStoreDataService.class);
	
	
	/**
	 * 数据移植思想：从一个样本店铺作为原始数据负责一份基层数据信息到新的店铺中，以方便店铺的快速创建
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2013-1-29 下午05:51:51]   
	 */
	@Override
	public boolean initData(Dto dto) {
		if (logger.isInfoEnabled()){
			logger.info("***** InitStoreDataService.initData() method end*****");
		}
		String sampleFlag=null;
		LvStore lvStore=(LvStore) dto.get("lvStore");
		LvStore shop=(LvStore) dao.findUniqueByProperty(LvStore.class, "code", lvStore.getParentCode());
		if(shop!=null){
			if(ObjectUtils.isNotEmpty(shop.getStoreFlag())&&shop.getStoreFlag().equals("www")){
				sampleFlag =(String) PropertiesHelper.getConfiguration().getProperty("InitStoreDataService.sampleFlag.zh");
			}else if(ObjectUtils.isNotEmpty(shop.getStoreFlag())&&shop.getStoreFlag().equals("en")){
				sampleFlag =(String) PropertiesHelper.getConfiguration().getProperty("InitStoreDataService.sampleFlag.en");
			}
		}else{
			logger.info("当前店铺不属于任何商城，不能进行初始化操作 ！");
			return false;
		}
		
		//初始化数据目标店铺标志
		String targetFlag=lvStore.getStoreFlag();
	    //移植邮件模板数据
		insertEmailTpl(targetFlag, sampleFlag);
		//移植广告数据
		insertAd(targetFlag, sampleFlag);
		//移植菜单导航
		insertNavigation(targetFlag, sampleFlag);
		//移植静态化数据
		insertLinkUrl(targetFlag, sampleFlag);
		//移植运费设置
		insertCarriageSet(targetFlag, sampleFlag);
		//移植产品信息
		insertProduct(targetFlag, sampleFlag);
		//移植支付方式
		updatePaymentStyle(targetFlag, sampleFlag);
		//插入模板内容
		insertContent(targetFlag,sampleFlag);
		//更新产品套餐关系
		updateProductPackage(dto);
		
		if (logger.isInfoEnabled()){
			logger.info("***** InitStoreDataService.initData() method end*****");
		}
		return true;
	}
	
	/**
	 * 
	 * @Method: insertEmailTpl 
	 * @Description:  [移植邮件模板数据]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2013-1-29 下午05:51:51]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2013-1-29 下午05:51:51]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param lvStore
	 * @return boolean
	 */
	public boolean insertEmailTpl(String targetFlag,String sampleFlag){
		//判断是否存在历史数据，如果存在先删除
		String hql="from LvEmailTpl where  storeId=:storeId";
		Map param=new HashMap();
		param.put("storeId",targetFlag);
		List<LvEmailTpl> listTmp=dao.find(hql, param);
		if(listTmp!=null){
			hql="delete from LvEmailTpl where  storeId=:storeId";
			dao.delete(hql, param);
		}
		//数据移植
		param.put("storeId",sampleFlag);
		hql="from LvEmailTpl where  storeId=:storeId";
		List<LvEmailTpl> list=dao.find(hql, param);
		for(LvEmailTpl lvEmailTpl:list){
			LvEmailTpl emailTpl=new LvEmailTpl();
			emailTpl.setZh(lvEmailTpl.getZh());
			emailTpl.setEn(lvEmailTpl.getEn());
			emailTpl.setTplKey(lvEmailTpl.getTplKey());
			emailTpl.setEnTitle(lvEmailTpl.getEnTitle());
			emailTpl.setZhTitle(lvEmailTpl.getZhTitle());
			emailTpl.setTplDescribe(lvEmailTpl.getTplDescribe());
			emailTpl.setStoreId(targetFlag);
			emailTpl.setCode(CodeUtils.getCode());
			emailTpl.setCreateTime(new Date());
			dao.save(emailTpl);
		}
		return true;
	}
	
	/**
	 * 
	 * @Method: insertAd 
	 * @Description:  [移植广告数据]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2013-1-30 上午10:16:00]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2013-1-30 上午10:16:00]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param lvStore
	 * @param sampleFlag
	 * @return boolean
	 */
	public boolean insertAd(String targetFlag,String sampleFlag){
		//判断是否存在历史数据，如果存在先删除
		String hql="from LvAd where  storeId=:storeId";
		Map param=new HashMap();
		param.put("storeId",targetFlag);
		List<LvEmailTpl> listTmp=dao.find(hql, param);
		if(listTmp!=null){
			hql="delete from LvAd where  storeId=:storeId";
			dao.delete(hql, param);
		}
		//数据移植
		param.put("storeId",sampleFlag);
		hql="from LvAd where  storeId=:storeId";
		List<LvAd> list=dao.find(hql, param);
		for(LvAd lvAd:list){
			LvAd ad=new LvAd();
			ad.setAdKey(lvAd.getAdKey());
			ad.setAdTitle(lvAd.getAdTitle());
			ad.setAdContent(lvAd.getAdContent());
			ad.setUrl(lvAd.getUrl());
			ad.setSortId(lvAd.getSortId());
			ad.setStoreId(targetFlag);
			ad.setCode(CodeUtils.getCode());
			ad.setCreateTime(new Date());
			dao.save(ad);
		}
		return true;
	}
	
	/**
	 * 
	 * @Method: insertNavigation 
	 * @Description:  [移植菜单导航]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2013-1-30 上午10:42:46]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2013-1-30 上午10:42:46]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param lvStore
	 * @param sampleFlag
	 * @return boolean
	 */
	public boolean insertNavigation(String targetFlag,String sampleFlag){
		//判断是否存在历史数据，如果存在先删除
		String hql="from LvNavigation where  storeId=:storeId";
		Map param=new HashMap();
		param.put("storeId",targetFlag);
		List<LvEmailTpl> listTmp=dao.find(hql, param);
		if(listTmp!=null){
			hql="delete from LvNavigation where  storeId=:storeId";
			dao.delete(hql, param);
		}
		//数据移植
		param.put("storeId",sampleFlag);
		hql="from LvNavigation where  storeId=:storeId";
		List<LvNavigation> list=dao.find(hql, param);
		for(LvNavigation lvNavigation:list){
			LvNavigation nvg=new LvNavigation();
			nvg.setNavName(lvNavigation.getNavName());
			nvg.setNavUrl(lvNavigation.getNavUrl());
			nvg.setNavParentId(lvNavigation.getNavParentId());
			nvg.setOrderValue(lvNavigation.getOrderValue());
			nvg.setOpenTarget(lvNavigation.getOpenTarget());
			nvg.setStoreId(targetFlag);
			nvg.setCode(CodeUtils.getCode());
			nvg.setCreateTime(new Date());
			dao.save(nvg);
		}
		return true;
	}
	
	/**
	 * 
	 * @Method: insertLinkUrl 
	 * @Description:  [移植静态化数据]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2013-1-30 上午10:43:23]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2013-1-30 上午10:43:23]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param lvStore
	 * @param sampleFlag
	 * @return boolean
	 */
	public boolean insertLinkUrl(String targetFlag,String sampleFlag){
		//判断是否存在历史数据，如果存在先删除
		String hql="from LvLinkUrl where  storeFlag=:storeFlag";
		Map param=new HashMap();
		param.put("storeFlag",targetFlag);
		List<LvEmailTpl> listTmp=dao.find(hql, param);
		if(listTmp!=null){
			hql="delete from LvLinkUrl where  storeFlag=:storeFlag";
			dao.delete(hql, param);
		}
		//数据移植
		param.put("storeFlag",sampleFlag);
		hql="from LvLinkUrl where  storeFlag=:storeFlag";
		List<LvLinkUrl> list=dao.find(hql, param);
		for(LvLinkUrl lvLinkUrl:list){
			LvLinkUrl linkUrl=new LvLinkUrl();
			linkUrl.setName(lvLinkUrl.getName());
			linkUrl.setDynamicurl(lvLinkUrl.getDynamicurl().trim());
			linkUrl.setStaticurl(lvLinkUrl.getStaticurl().trim());
			linkUrl.setHql(lvLinkUrl.getHql());
			linkUrl.setNote(lvLinkUrl.getNote());
			linkUrl.setStoreFlag(targetFlag);
			dao.save(linkUrl);
		}
		return true;
	}
	

	
	/**
	 * 
	 * @Method: insertCarriageSet 
	 * @Description:  [移植运费设置数据]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2013-1-30 上午10:43:34]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2013-1-30 上午10:43:34]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param lvStore
	 * @param sampleFlag
	 * @return boolean
	 */
	public boolean insertCarriageSet(String targetFlag,String sampleFlag){
		//判断是否存在历史数据，如果存在先删除
		String hql="from LvCarriageSet where  storeId=:storeId";
		Map param=new HashMap();
		param.put("storeId",targetFlag);
		List<LvEmailTpl> listTmp=dao.find(hql, param);
		if(listTmp!=null){
			hql="delete from LvCarriageSet where  storeId=:storeId";
			dao.delete(hql, param);
		}
		//数据移植
		param.put("storeId",sampleFlag);
		hql="from LvCarriageSet where  storeId=:storeId";
		List<LvCarriageSet> list=dao.find(hql, param);
		for(LvCarriageSet lvCarriageSet:list){
			LvCarriageSet carriageSet=new LvCarriageSet();
			carriageSet.setDeliveryId(lvCarriageSet.getDeliveryId());
			carriageSet.setCarriage(lvCarriageSet.getCarriage());
			carriageSet.setCurrency(lvCarriageSet.getCurrency());
			carriageSet.setRemark(lvCarriageSet.getRemark());
			carriageSet.setStoreId(targetFlag);
			carriageSet.setCode(CodeUtils.getCode());
			carriageSet.setCreateTime(new Date());
			dao.save(carriageSet);
		}
		return true;
	}
	
	/**
	 * 
	 * @Method: insertProduct 
	 * @Description:  [移植产品类型，产品信息，产品扩展属性，产品效果图信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2013-1-30 下午04:47:22]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2013-1-30 下午04:47:22]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param lvStore
	 * @param sampleFlag
	 * @return boolean
	 */
	public boolean insertProduct(String targetFlag,String sampleFlag){
		//删除历史数据
		Map param=new HashMap();
		param.put("storeId",targetFlag);
		String hql="delete from LvProductType where storeId=:storeId";
		dao.delete(hql, param);
		hql="delete from LvProduct where storeId=:storeId";
		dao.delete(hql, param);
		hql="delete from LvProductPackage where storeId=:storeId";
		dao.delete(hql, param);
		hql="delete from LvProductProperty where storeId=:storeId";
		dao.delete(hql, param);
		hql="delete from LvProductImage where storeId=:storeId";
		dao.delete(hql, param);
		
		
		//产品类型
		hql="from LvProductType where storeId=:storeId ";
		param.put("storeId",sampleFlag);
		List<LvProductType> typeList=dao.find(hql, param);
		for (LvProductType lvProductType : typeList) {
			String typeCode=CodeUtils.getCode();
			LvProductType productType =new LvProductType();
			productType.setTypeName(lvProductType.getTypeName());
			productType.setTypeFlag(lvProductType.getTypeFlag());
			productType.setOrderId(lvProductType.getOrderId());
			productType.setStoreId(targetFlag);
			productType.setCreateTime(new Date());
			productType.setCode(typeCode);
			dao.save(productType);
			
			//产品信息
			hql="from LvProduct where ptype=:ptype and storeId=:storeId";
			param.put("ptype",lvProductType.getCode());
			List<LvProduct> productList= dao.find(hql, param);
			for (LvProduct lvProduct : productList) {
				String productCode=CodeUtils.getCode();
				Constants.PRODUCT_CODES.put(lvProduct.getCode(), productCode);
				
				LvProduct product=new LvProduct();
				product.setProductName(lvProduct.getProductName());
				product.setPcode(lvProduct.getPcode());
				product.setPriceRmb(lvProduct.getPriceRmb());
				product.setPriceUsd(lvProduct.getPriceUsd());
				product.setPrice(lvProduct.getPrice());
				product.setDescription(lvProduct.getDescription());
				product.setPmodel(lvProduct.getPmodel());
				product.setPtype(typeCode);
				product.setPimage(lvProduct.getPimage());
				product.setPimageAd(lvProduct.getPimageAd());
				product.setIsActivity(lvProduct.getIsActivity());
				product.setIsSupport(lvProduct.getIsSupport());
				product.setIsLadder(lvProduct.getIsLadder());
				product.setIsPreferences(lvProduct.getIsPreferences());
				product.setIsSetMeal(lvProduct.getIsSetMeal());
				product.setIsCommend(lvProduct.getIsCommend());
				product.setStatus(lvProduct.getStatus());
				product.setOrderId(lvProduct.getOrderId());
				product.setShopProductType(lvProduct.getShopProductType());
				product.setCode(productCode);
				product.setCreateTime(new Date());
				product.setStoreId(targetFlag);
				dao.save(product);

				//判断是否为套餐
				if(ObjectUtils.isNotEmpty(lvProduct.getIsSetMeal())&&lvProduct.getIsSetMeal()==1){
					hql="from LvProductPackage where packageNum='"+lvProduct.getCode()+"'";
					List<LvProductPackage> packageList=dao.find(hql, null);
					for (LvProductPackage lvProductPackage : packageList) {
						LvProductPackage productPackage=new LvProductPackage();
						productPackage.setPackageNum(productCode);
						productPackage.setPackageTitle(lvProductPackage.getPackageTitle());
						productPackage.setProductCode(lvProductPackage.getProductCode());
						productPackage.setPnum(lvProductPackage.getPnum());
						productPackage.setPriceRmb(lvProductPackage.getPriceRmb());
						productPackage.setPriceUsd(lvProductPackage.getPriceUsd());
						productPackage.setPrice(lvProductPackage.getPrice());
						productPackage.setPcode(lvProductPackage.getPcode());
						productPackage.setCode(CodeUtils.getCode());
						productPackage.setStoreId(targetFlag);
						productPackage.setCreateTime(new Date());
						dao.save(productPackage);
					}
				}
				
				
				//产品扩展属性
				hql="from LvProductProperty where productCode='"+lvProduct.getCode()+"'";
				List<LvProductProperty> propertyList=dao.find(hql, null);
				for (LvProductProperty lvProductProperty : propertyList) {
					LvProductProperty pp=new LvProductProperty();
					pp.setTitle(lvProductProperty.getTitle());
					pp.setContent(lvProductProperty.getContent());
					pp.setProductCode(productCode);
					pp.setSortId(lvProductProperty.getSortId());
					pp.setStoreId(targetFlag);
					pp.setCode(CodeUtils.getCode());
					pp.setCreateTime(new Date());
					dao.save(pp);
				}
				
				//产品效果图
				hql="from LvProductImage where productCode='"+lvProduct.getCode()+"'";
				List<LvProductImage> pimageList=dao.find(hql, null);
				for (LvProductImage lvProductImage : pimageList) {
					LvProductImage pimage=new LvProductImage();
					pimage.setProductCode(productCode);
					pimage.setProductImage(lvProductImage.getProductImage());
					pimage.setSortId(lvProductImage.getSortId());
					pimage.setStoreId(targetFlag);
					pimage.setCode(CodeUtils.getCode());
					pimage.setCreateTime(new Date());
					dao.save(pimage);
				}
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @Method: updatePaymentStyle 
	 * @Description:  [更新支付信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2013-1-30 下午03:24:26]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2013-1-30 下午03:24:26]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public boolean updatePaymentStyle(String targetFlag,String sampleFlag){
		Map param=new HashMap();
		String hql="from LvPaymentStyle where storeFlag=:storeFlag";
		param.put("storeFlag",sampleFlag);
		List<LvPaymentStyle> sampleList=dao.find(hql, param);
		param.put("storeFlag",targetFlag);
		List<LvPaymentStyle> list=dao.find(hql, param);
		for (LvPaymentStyle lvPaymentStyle : sampleList) {
			for (LvPaymentStyle lps : list) {
				if(lvPaymentStyle.getPayValue().equals(lps.getPayValue())){
					hql="UPDATE LvPaymentStyle set payName=:payName,params=:params,url=:url where id=:id ";
					Map map=new HashMap();
					map.put("payName", lvPaymentStyle.getPayName());
					map.put("params", lvPaymentStyle.getParams());
					map.put("url", lvPaymentStyle.getUrl());
					map.put("id", lps.getId());
					dao.update(hql, map);
				}
			}
		}
		return true;
	}
	
	/**
	 * 
	 * @Method: insertContent 
	 * @Description:  [新增内容列表信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2013-1-30 下午03:24:44]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2013-1-30 下午03:24:44]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param lvStore
	 * @return boolean
	 */
	public boolean insertContent(String targetFlag,String sampleFlag){
		//判断是否存在历史数据，如果存在先删除
		String hql="from LvContent where isdel=0 and storeFlag=:storeFlag";
		Map param=new HashMap();
		param.put("storeFlag",targetFlag);
		List<LvContent> listTmp=dao.find(hql, param);
		if(listTmp!=null&&listTmp.size()>0){
			hql="update LvContent set isdel=1 where  storeFlag=:storeFlag";
			dao.delete(hql, param);
		}
		
		hql="from LvContent where  isdel=0 and storeFlag=:storeFlag";
		param.put("storeFlag",sampleFlag);
		List<LvContent> list=dao.find(hql, param);
		for(LvContent lvContent:list){
			LvContent content=new LvContent();
			content.setTemplatePath(lvContent.getTemplatePath());
			content.setPageName(lvContent.getPageName());
			content.setPageTitle(lvContent.getPageTitle());
			content.setIsHasContent(lvContent.getIsHasContent());
			content.setPageContent(lvContent.getPageContent());
			content.setPagePath(lvContent.getPagePath());
			content.setPageKeywords(lvContent.getPageKeywords());
			content.setPageDescription(lvContent.getPageDescription());
			content.setIsdel(lvContent.getIsdel());
			content.setStoreFlag(targetFlag);
			content.setCode(CodeUtils.getCode());
			content.setCreateTime(new Date());
			dao.save(content);
		}
		return true;
	}
	
	/**
	 * 
	 * @Method: updateProductPackage 
	 * @Description:  [更新产品套餐关系]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2013-1-31 下午02:13:28]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2013-1-31 下午02:13:28]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public void updateProductPackage(Dto dto) throws ServiceException{
		LvStore lvStore=(LvStore) dto.get("lvStore");
		String hql="from LvProductPackage where storeId=:storeId";
		Map param=new HashMap();
		param.put("storeId", lvStore.getStoreFlag());
		List<LvProductPackage> list=dao.find(hql, param);
		for (LvProductPackage lvProductPackage : list) {
			for(Map.Entry<String, String> entry:Constants.PRODUCT_CODES.entrySet()){   
				if(lvProductPackage.getProductCode().equals(entry.getKey())){
				   hql="update LvProductPackage set productCode='"+entry.getValue()+"' where productCode='"+entry.getKey()+"'";
				   dao.update(hql, null);
				}
			}
		}
	}
}
