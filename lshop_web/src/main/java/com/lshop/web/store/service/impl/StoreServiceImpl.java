package com.lshop.web.store.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvActivityProduct;
import com.lshop.common.pojo.logic.LvShopActivity;
import com.lshop.common.pojo.logic.LvShopPartner;
import com.lshop.common.pojo.logic.LvShopProduct;
import com.lshop.common.pojo.logic.LvShopSubject;
import com.lshop.common.pojo.logic.LvSpecialtyStore;
import com.lshop.common.pojo.logic.LvSpecialtyStoreType;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.pojo.logic.LvStoreAddress;
import com.lshop.common.pojo.logic.LvStoreArea;
import com.lshop.common.util.Constants;
import com.lshop.common.util.SearchUtil;
import com.lshop.web.activity.service.ActivityGiftService;
import com.lshop.web.product.service.ProductService;
import com.lshop.web.store.service.StoreService;

/**
 * 商城模块
 * @author zhengxue
 * @since 1.0 2012-12-19
 *
 */
@SuppressWarnings("unchecked")
@Service("StoreService")
public class StoreServiceImpl implements StoreService {
	
	@Resource
	private HibernateBaseDAO lvlogicReadDao;
	
	@Resource
	private ProductService productService;
	
	@Resource 
	private ActivityGiftService activityGiftService; 
	
	private static final Log logger	= LogFactory.getLog(StoreServiceImpl.class);
	
	/**
	 * 获取商城栏目信息列表
	 * @param dto(storeFlag)
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<LvShopSubject> getShopSubjectList(Dto dto)
			throws ServiceException {

		if (logger.isInfoEnabled()){
			logger.info("***** StoreServiceImpl.getShopSubjectList() method begin*****");
		}	
		
		String hql="from LvShopSubject where storeId=:storeFlag order by orderValue desc";
		HashMap param=new HashMap();
		param.put("storeFlag", dto.getAsString("storeFlag"));
		List<LvShopSubject> subjectList=(List<LvShopSubject>)lvlogicReadDao.find(hql, param);
		
		if (logger.isInfoEnabled()){
			logger.info("***** StoreServiceImpl.getShopSubjectList() method end*****");
		}	
		return subjectList;
	}
	
	/**
	 * 获取栏目所对应的商品信息
	 * @param dto(subjectType,storeFlag)
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<LvShopProduct> getShopProductList(Dto dto)
			throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("***** StoreServiceImpl.getShopProductList() method begin*****");
		}	
		
		String hql="from LvShopProduct where subjectType=:subjectType and storeFlag=:storeFlag order by orderValue desc";
		HashMap param=new HashMap();
		param.put("subjectType", dto.getAsString("subjectType"));
		param.put("storeFlag", dto.getAsString("storeFlag")); //商城标识
		List<LvShopProduct> productList=(List<LvShopProduct>)lvlogicReadDao.find(hql, param);

		//将商品价格封装进去，方便页面取值
		dto.put("productList", productList);
		List<LvShopProduct> newProductList=this.changeProductList(dto);
		
		if (logger.isInfoEnabled()){
			logger.info("***** StoreServiceImpl.getShopProductList() method end*****");
		}	
		
		return newProductList;
	}
	
	
	/**
	 * 获取栏目所对应的商品信息
	 * @param dto(subjectType,storeFlag)
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public Pagination getShopProductListForPage(Dto dto)
			throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("***** StoreServiceImpl.getShopProductListForPage() method begin*****");
		}	
		
		Pagination page = dto.getDefaultPage();
		
		String hql="from LvShopProduct where subjectType=:subjectType and storeFlag=:storeFlag order by orderValue desc";
		HashMap param=new HashMap();
		param.put("subjectType", dto.getAsString("subjectType"));
		param.put("storeFlag", dto.getAsString("storeFlag")); //商城标识
		page=lvlogicReadDao.find(Finder.create(hql).setParams(param), page.getPageNum(), page.getNumPerPage());
//		List<LvShopProduct> productList=(List<LvShopProduct>)lvlogicReadDao.find(hql, param);

		//将商品价格封装进去，方便页面取值
//		dto.put("productList", page.getList());
//		List<LvShopProduct> newProductList=this.changeProductList(dto);
//		
//		page.setList(newProductList);
		
		if (logger.isInfoEnabled()){
			logger.info("***** StoreServiceImpl.getShopProductListForPage() method end*****");
		}	
		return page;
	}
	
	/**
	 * 获取商城活动信息列表
	 * @param dto(storeFlag)
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<LvShopActivity> getShopActivityList(Dto dto)
			throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("***** StoreServiceImpl.getShopActivityList() method begin*****");
		}	
		
		String hql="from LvShopActivity where storeId=:storeFlag order by orderValue desc";
		HashMap param=new HashMap();
		param.put("storeFlag", dto.getAsString("storeFlag"));
		List<LvShopActivity> activityList=(List<LvShopActivity>)lvlogicReadDao.find(hql, param);
		
		if (logger.isInfoEnabled()){
			logger.info("***** StoreServiceImpl.getShopActivityList() method end*****");
		}	
		return activityList;
	}

	/**
	 * 获取合作商家信息
	 * @param dto(storeFlag)
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<LvShopPartner> getShopPartnerList(Dto dto)
			throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("***** StoreServiceImpl.getShopPartnerList() method begin*****");
		}	
		
		String hql="from LvShopPartner where storeId=:storeFlag order by orderValue desc";
		HashMap param=new HashMap();
		param.put("storeFlag", dto.getAsString("storeFlag"));
		List<LvShopPartner> partnerList=(List<LvShopPartner>)lvlogicReadDao.find(hql, param);
		
		if (logger.isInfoEnabled()){
			logger.info("***** StoreServiceImpl.getShopPartnerList() method end*****");
		}	
		return partnerList;
	}

	/**
	 * 根据关键字搜索商品信息
	 * @param dto(str,storeFlag)
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public Pagination getShopProductListForSearch(Dto dto)
			throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("***** StoreServiceImpl.getShopProductListForSearch() method begin*****");
		}	
		
		Pagination page = dto.getDefaultPage();
		String str = dto.getAsString("str");
		String mallFlag = dto.getAsString("mallFlag");
		String storeFlags= Constants.STORE_LIST.get(mallFlag);
		if(storeFlags==null || storeFlags.equals("")){
			return page;
		}
		String hql = "";
		
		//用搜索引擎进行搜索
		String ids = "";
		dto.put("keyword", str);
		try {
//			//初始化搜索数据
//			SearchUtil.initProductInfo(lvlogicReadDao);
			dto.put("mark", "product");
			dto.put("storeFlags", storeFlags);
			//20141105最小粒度分词搜索
			//ids=SearchUtil.search(dto);
			ids=SearchUtil.searchProd(str);
			//判断是否有符合条件的数据
			if(null!=ids && !("").equals(ids)){
				hql="from LvProduct where id in ("+ids+") and storeId in ("+storeFlags+") order by find_in_set(id,'"+ids+"')";
			}else{
				return page;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		page=lvlogicReadDao.find(Finder.create(hql), page.getPageNum(), page.getNumPerPage());
		page.getTotalPage();
		
		if (logger.isInfoEnabled()){
			logger.info("***** StoreServiceImpl.getShopProductListForSearch() method end*****");
		}	
		return page;
	}

	/**
	 * 根据关键字搜索店铺信息
	 * @param dto(str,storeFlag)
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public Pagination getStoreListForSearch(Dto dto)
			throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("***** StoreServiceImpl.getStoreListForSearch() method begin*****");
		}	
		
		Pagination page = dto.getDefaultPage();
		String str = dto.getAsString("str");
		String mallFlag = dto.getAsString("mallFlag");
		String storeFlags= Constants.STORE_LIST.get(mallFlag);
		if(storeFlags==null || storeFlags.equals("")){
			return page;
		}
		String hql = "";
		
		//用搜索引擎进行搜索
		String ids = "";
		dto.put("keyword", str);
		try {
//			//初始化搜索数据
//			SearchUtil.initStoreInfo(lvlogicReadDao);
			dto.put("mark", "store");
			dto.put("storeFlags", storeFlags);
			ids=SearchUtil.search(dto);
			//判断是否有符合条件的数据
			if(null!=ids && !("").equals(ids)){
				hql="from LvStore where id in ("+ids+") and storeFlag in ("+storeFlags+") order by find_in_set(id,'"+ids+"')";
			}else{
				return page;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		page=lvlogicReadDao.find(Finder.create(hql), page.getPageNum(), page.getNumPerPage());
		page.getTotalPage();
		
		if (logger.isInfoEnabled()){
			logger.info("***** StoreServiceImpl.getStoreListForSearch() method end*****");
		}	
		return page;
	}
	
	/**
	 * 获取所有专卖店分类
	 * @param dto(storeFlag)
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<LvSpecialtyStoreType> getSpecialStoreTypes(Dto dto)
			throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("***** StoreServiceImpl.getSpecialStoreTypes() method begin*****");
		}	
		
		String hql="from LvSpecialtyStoreType where storeId=:storeFlag order by orderValue desc";
		HashMap param = new HashMap();
		param.put("storeFlag", dto.getAsString("storeFlag"));
		List<LvSpecialtyStoreType> typeList = (List<LvSpecialtyStoreType>)lvlogicReadDao.find(hql,param);
		
		if (logger.isInfoEnabled()){
			logger.info("***** StoreServiceImpl.getSpecialStoreTypes() method end*****");
		}	
		return typeList;
	}

	/**
	 * 根据专卖店类别获取其对应的专卖店
	 * @param dto(storeTypeCode,storeFlag)
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<LvSpecialtyStore> getSpecialStoresByType(Dto dto)
			throws ServiceException {

		if (logger.isInfoEnabled()){
			logger.info("***** StoreServiceImpl.getSpecialStoresByType() method begin*****");
		}	
		
		String hql="from LvSpecialtyStore where storeTypeCode=:storeTypeCode and storeId=:storeFlag order by orderValue desc";
		HashMap param = new HashMap();
		param.put("storeTypeCode", dto.getAsString("storeTypeCode"));
		param.put("storeFlag", dto.getAsString("storeFlag"));
		List<LvSpecialtyStore> storeList=(List<LvSpecialtyStore>)lvlogicReadDao.find(hql,param);
		
		if (logger.isInfoEnabled()){
			logger.info("***** StoreServiceImpl.getSpecialStoresByType() method end*****");
		}	
		return storeList;
	}

	/**
	 * 给商城商品设置价格，并返回包含价格的商品列表
	 * @param productList
	 * @return
	 */
	private List<LvShopProduct> changeProductList(Dto dto){
		
		if (logger.isInfoEnabled()){
			logger.info("***** StoreServiceImpl.changeProductList() method begin*****");
		}	
		
		//获取要转换的list
		List<LvShopProduct> productList = (List<LvShopProduct>) dto.get("productList");
		if(null!=productList && productList.size()>0){
			for(LvShopProduct product : productList){
				//获取商家商品价格
				dto.put("pcode", product.getProductCode());
				dto.put("storeFlag", product.getStoreId()); //商家标识
				float price = productService.getProductPrice(dto);
				float orignalPrice = productService.getProductOrignalPrice(dto);
				product.setProductPrice(price);
				product.setOrignalPrice(orignalPrice);
				
				//判断当前商品是否存在赠品
				List acProductList=activityGiftService.findGiftByProductCode(product.getProductCode());
				if(ObjectUtils.isNotEmpty(acProductList)){
					product.setIsGift((short)1);
				}else{
					product.setIsGift((short)0);
				}
				
			}
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** StoreServiceImpl.changeProductList() method end*****");
		}	
		return productList;
	}

	/**
	 * 根据code获取栏目分类
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public LvShopSubject getShopSubjectByCode(Dto dto) throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("***** StoreServiceImpl.getShopSubjectByCode() method begin*****");
		}	
		
		LvShopSubject subject = (LvShopSubject)lvlogicReadDao.findUniqueByProperty(LvShopSubject.class, "code", dto.getAsString("subjectCode"));
		
		if (logger.isInfoEnabled()){
			logger.info("***** StoreServiceImpl.getShopSubjectByCode() method end*****");
		}	
		return subject;
	}

	/**
	 * 根据店铺标志获取店铺信息
	 * @param dto(storeFlag店铺标志)
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public LvStore getStoreByFlag(Dto dto) throws ServiceException {
		
		
		LvStore store = null;
			
		String hql = "from LvStore where storeFlag = :storeFlag and isdel = 0";
		HashMap param = new HashMap();
		param.put("storeFlag", dto.getAsString("storeFlag"));
		List list = lvlogicReadDao.find(hql, param);
		if(null!=list && list.size()>0){
			store = (LvStore)list.get(0);
		}
		
		return store;
	}

	/**
	 * 根据店铺code获取店铺地址信息
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public LvStoreAddress getStoreAddressByCode(Dto dto)
			throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("***** StoreServiceImpl.getStoreAddressByCode() method begin*****");
		}	
		
		//获取店铺code
		String storeCode = dto.getAsString("storeCode");
		LvStoreAddress address= null;
		
		if(null!=storeCode && !("").equals(storeCode)){
			address = (LvStoreAddress)lvlogicReadDao.findUniqueByProperty(LvStoreAddress.class, "storeCode", storeCode);
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** StoreServiceImpl.getStoreAddressByCode() method end*****");
		}	
		return address;
	}

	/**
	 * 获取所有的店铺信息
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<LvStore> getStoreList(Dto dto) throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("***** StoreServiceImpl.getStoreList() method begin*****");
		}	
		
		//获取当前商城code
		LvStore pStore = (LvStore)lvlogicReadDao.findUniqueByProperty(LvStore.class, "storeFlag", dto.getAsString("mallFlag"));
		if(pStore==null){
			return null;
		}
		
		String hql = "from LvStore where isdel=0 and status=1 and parentCode=:parentCode order by orderValue desc,createTime desc";
		HashMap param = new HashMap();
		param.put("parentCode", pStore.getCode());
		List<LvStore> storeList = (List<LvStore>)lvlogicReadDao.find(hql, param);
		
		if (logger.isInfoEnabled()){
			logger.info("***** StoreServiceImpl.getStoreList() method begin*****");
		}	
		return storeList;
	}

	/**
	 * 获取所有的店铺区域信息
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public Dto getStoreAreas(Dto dto) throws ServiceException {

		if (logger.isInfoEnabled()){
			logger.info("***** StoreServiceImpl.getStoreAreas() method begin*****");
		}	
		
		//获取当前商城code
		LvStore pStore = (LvStore)lvlogicReadDao.findUniqueByProperty(LvStore.class, "storeFlag", dto.getAsString("mallFlag"));
		if(pStore==null){
			return null;
		}
		
		HashMap param = new HashMap();
		String hql = "";
		
		Object[] obj = new Object[5]; //0，所有洲列表 1，所有店铺列表  2,objList1  3,objList2  4,objList3
		List<Object[]> objList1 = new ArrayList<Object[]>();  //一个洲对应多个国家和店铺 0，洲 1，国家 2，店铺
		List<Object[]> objList2 = new ArrayList<Object[]>();  //一个国家对应多个城市和店铺 0,国家 1，城市 2，店铺
		List<Object[]> objList3 = new ArrayList<Object[]>();  //一个城市对应多个店铺 0，城市 1，店铺
		
		/*******************获取所有洲列表及店铺列表************************/
		//获取所有的店铺
		List<LvStore> allStores = this.getStoreList(dto);
		String codes_continent = "";
		String codes_country = "";
		String codes_city = "";
		if(null!=allStores && allStores.size()>0){
			for(LvStore store : allStores){
				codes_continent += "'"+store.getContinentCode()+"',";
				codes_country += "'"+store.getCountryCode()+"',";
				codes_city += "'"+store.getCityCode()+"',";
			}
			codes_continent = codes_continent.substring(0, codes_continent.length()-1);
			codes_country = codes_country.substring(0, codes_country.length()-1);
			codes_city = codes_city.substring(0, codes_city.length()-1);
		}
		
		//获取所有有店铺的洲
//		hql = "from LvStoreArea where parentCode is null order by orderValue desc";
		hql = "from LvStoreArea where code in ("+codes_continent+") order by orderValue desc, createTime desc";
		List<LvStoreArea> continentList = (List<LvStoreArea>)lvlogicReadDao.find(hql, null);
		
		/*******************一个洲对应多个国家和店铺************************/
		//获取该洲下面的国家和店铺
//		String continentCodes = "";
		if(null!=continentList && continentList.size()>0){
			for(LvStoreArea conArea: continentList){
				
				//获取店铺
				param.clear();
				param.put("continentCode", conArea.getCode());
				param.put("parentCode", pStore.getCode());
				hql = "from LvStore where continentCode = :continentCode and isdel=0 and status=1 and parentCode=:parentCode order by orderValue desc, createTime desc";
				List<LvStore> continentStores = (List<LvStore>)lvlogicReadDao.find(hql,param);	
				
				//获取有店铺的国家
				param.clear();
				param.put("parentCode", conArea.getCode());
				hql = "from LvStoreArea where parentCode = :parentCode and code in ("+codes_country+") order by orderValue desc, createTime desc";
				List<LvStoreArea> countyList = (List<LvStoreArea>)lvlogicReadDao.find(hql, param);
				
				Object[] obj1 = new Object[3];//0，洲 1，国家 2，店铺
				obj1[0] = conArea;
				obj1[1] = countyList;
				obj1[2] = continentStores;
				objList1.add(obj1);
				
//				continentCodes += "'"+conArea.getCode()+"',";
			}
//			continentCodes = continentCodes.substring(0, continentCodes.length()-1);
		}
		
		/*******************一个国家对应多个城市和店铺************************/
		//获取所有有店铺的国家
//		hql = "from LvStoreArea where parentCode in ("+continentCodes+") order by orderValue desc";
		hql = "from LvStoreArea where code in ("+codes_country+") order by orderValue desc, createTime desc";
		List<LvStoreArea> countyList = (List<LvStoreArea>)lvlogicReadDao.find(hql, null);
		
		//获取该国家下面的城市和店铺
//		String contryCodes = "";
		if(null!=countyList && countyList.size()>0){
			for(LvStoreArea contryArea : countyList){
				
				//获取店铺
				param.clear();
				param.put("countryCode", contryArea.getCode());
				param.put("parentCode", pStore.getCode());
				hql = "from LvStore where countryCode = :countryCode and isdel=0 and status=1 and parentCode=:parentCode order by orderValue desc, createTime desc";
				List<LvStore> contryStores = (List<LvStore>)lvlogicReadDao.find(hql,param);
				
				//获取有店铺的城市
				param.clear();
				param.put("parentCode", contryArea.getCode());
				hql = "from LvStoreArea where parentCode = :parentCode and code in ("+codes_city+") order by orderValue desc, createTime desc";
				List<LvStoreArea> cityList = (List<LvStoreArea>)lvlogicReadDao.find(hql, param);
		
				Object[] obj2 = new Object[3];//0,国家 1，城市 2，店铺
				obj2[0] = contryArea;
				obj2[1] = cityList;
				obj2[2] = contryStores;
				objList2.add(obj2);
				
//				contryCodes += "'"+contryArea.getCode()+"',";
			}
//			contryCodes = contryCodes.substring(0, contryCodes.length()-1);
		}
		
		/*******************一个城市对应多个店铺************************/
		//获取所有有店铺的城市
//		hql = "from LvStoreArea where parentCode in ("+contryCodes+") order by orderValue desc";
		hql = "from LvStoreArea where code in ("+codes_city+") order by orderValue desc, createTime desc";
		List<LvStoreArea> cityList = (List<LvStoreArea>)lvlogicReadDao.find(hql, null);
		
		//获取该城市下面的店铺
		if(null!=cityList && cityList.size()>0){
			for(LvStoreArea cityArea : cityList){
				//获取店铺
				param.clear();
				param.put("cityCode", cityArea.getCode());
				param.put("parentCode", pStore.getCode());
				hql = "from LvStore where cityCode = :cityCode and isdel=0 and status=1 and parentCode=:parentCode order by orderValue desc, createTime desc";
				List<LvStore> cityStores = (List<LvStore>)lvlogicReadDao.find(hql,param);
				
				Object[] obj3 = new Object[2]; //0，城市 1，店铺
				obj3[0] = cityArea;
				obj3[1] = cityStores;
				objList3.add(obj3);
			}
		}
		
		obj[0] = continentList;
		obj[1] = allStores;
		obj[2] = objList1;
		obj[3] = objList2;
		obj[4] = objList3;
		
		dto.put("obj", obj);
		
		if (logger.isInfoEnabled()){
			logger.info("***** StoreServiceImpl.getStoreAreas() method end*****");
		}	
		return dto;
	}

	/**
	 * 获取所有的店铺信息（带分页）
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public Pagination getStoreListForPage(Dto dto) throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("***** StoreServiceImpl.getStoreListForPage() method begin*****");
		}	
		
		Pagination page = dto.getDefaultPage();
		String continentCode = dto.getAsString("continentCode");
		String countryCode = dto.getAsString("countryCode");
		String cityCode = dto.getAsString("cityCode");
		
		//获取当前商城code
		LvStore pStore = (LvStore)lvlogicReadDao.findUniqueByProperty(LvStore.class, "storeFlag", dto.getAsString("mallFlag"));
		if(pStore==null){
			return null;
		}
		
		StringBuffer hql = new StringBuffer();
		HashMap param = new HashMap();
		hql.append("from LvStore where isdel=0 and status=1 and parentCode=:parentCode");
		param.put("parentCode", pStore.getCode());
		if(null!=continentCode && !("").equals(continentCode)){
			
			hql.append(" and continentCode=:continentCode");
			param.put("continentCode", continentCode);
		}
		if(null!=countryCode && !("").equals(countryCode)){
			
			hql.append(" and countryCode=:countryCode");
			param.put("countryCode", countryCode);
		}
		if(null!=cityCode && !("").equals(cityCode)){
			
			hql.append(" and cityCode=:cityCode");
			param.put("cityCode", cityCode);
		}

		hql.append(" order by orderValue desc,createTime desc");
		
		page=lvlogicReadDao.find(Finder.create(hql.toString()).setParams(param), page.getPageNum(), page.getNumPerPage());
		page.getTotalPage();
		
		if (logger.isInfoEnabled()){
			logger.info("***** StoreServiceImpl.getStoreListForPage() method end*****");
		}	
		
		return page;
	}

	/**
	 * 根据域名获取店铺信息，用于判断该店铺是否删除或停用
	 * @param domainName
	 * @return
	 */
	@Override
	public LvStore getStoreByDomain(String domainName){
		
		if (logger.isInfoEnabled()){
			logger.info("***** StoreServiceImpl.getStoreByDomain() method begin*****");
		}	
		
		HashMap map = new HashMap();
		map.put("domainName", domainName);
		String hql = "from LvStore where domainName=:domainName and isdel=0 and status=1 and parentCode!='0'";
		LvStore store = (LvStore)lvlogicReadDao.findUnique(hql, map);
		
		if (logger.isInfoEnabled()){
			logger.info("***** StoreServiceImpl.getStoreByDomain() method end*****");
		}	
		return store;
	}

	/**
	 * 获取所有的推荐店铺
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<LvStore> getRecommendStoreList(Dto dto) throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("***** StoreServiceImpl.getRecommendStoreList() method begin*****");
		}	
		
		String hql = "from LvStore where isdel=0 and status=1 and parentCode=:parentCode and isCommend=1 order by orderValue desc,createTime desc";
		HashMap map = new HashMap();
		map.put("parentCode", dto.getAsString("parentCode"));
		List<LvStore> storeList = (List<LvStore>)lvlogicReadDao.find(hql, map);
		
		if (logger.isInfoEnabled()){
			logger.info("***** StoreServiceImpl.getRecommendStoreList() method end*****");
		}	
		return storeList;
	}

	/**
	 * 获取所有有店铺的洲
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public List<LvStoreArea> getContinentArea(Dto dto) throws ServiceException {
		
		if (logger.isInfoEnabled()){
			logger.info("***** StoreServiceImpl.getContinentArea() method begin*****");
		}	
		
		//获取当前商城code
		LvStore pStore = (LvStore)lvlogicReadDao.findUniqueByProperty(LvStore.class, "storeFlag", dto.getAsString("mallFlag"));
		if(pStore==null){
			return null;
		}
		
		//获取所有有店铺的洲的code
		String hql = "select DISTINCT continentCode from LvStore where isdel=0 and status=1 and parentCode=:parentCode";
		HashMap param = new HashMap();
		param.put("parentCode", pStore.getCode());
		List<String> codeList=(List<String>)lvlogicReadDao.find(hql,param);
		if(codeList.size()>0)
		{
			String codes=codeList.toString().replaceAll("\\[|\\]", "'").replaceAll("\\, ", "','");
			hql = "from LvStoreArea where code in ("+codes+") order by orderValue desc, createTime desc";
			List<LvStoreArea> continentList = (List<LvStoreArea>)lvlogicReadDao.find(hql, null);
			
			if (logger.isInfoEnabled()){
				logger.info("***** StoreServiceImpl.getShopSubjectList method end*****");
			}	
			
			return continentList;
		}else{
			if (logger.isInfoEnabled()){
				logger.info("***** StoreServiceImpl.getContinentArea() method end*****");
			}	
			return null;
		}
	}

	/**
	 * 根据IP获取当前国家code
	 * @param dto
	 * @return
	 */
	@Override
	public String getCountryCodeByIp(Dto dto) {
		
		if (logger.isInfoEnabled()){
			logger.info("***** StoreServiceImpl.getCountryCodeByIp() method begin*****");
		}	
		
		Long ipData = dto.getAsLong("ipData");
		if(null!=ipData && ipData>0){
			//从lv_ip_country表中获取countryid
			int countryid = 0; 
			String hql = "select countryid from LvIpCountry where longIpStart<=:ipData and longIpEnd>=:ipData";
			HashMap map = new HashMap();
			map.put("ipData", ipData);
			List list = lvlogicReadDao.find(hql, map);
			if(null!=list && list.size()>0){
				countryid = Integer.parseInt(list.get(0).toString());
				
				//从lv_store_area表中获取countrycode
				map.clear();
				map.put("areaId", countryid);
				map.put("storeFlag", dto.getAsString("storeFlag"));
				String hq = "select code from LvStoreArea where areaId=:areaId and storeId=:storeFlag";
				List listt = lvlogicReadDao.find(hq, map);
				if(null!=listt && listt.size()>0){
					if (logger.isInfoEnabled()){
						logger.info("***** StoreServiceImpl.getShopSubjectList method end*****");
					}	
					return listt.get(0).toString();
				}
			}
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** StoreServiceImpl.getCountryCodeByIp() method end*****");
		}	
		return null;
	}

	/**
	 * 获取所有的推荐店铺信息（IP国家优先）
	 * @param dto
	 * @return
	 */
	@Override
	public List<LvStore> getCommandStoresByCountry(Dto dto) {

		if (logger.isInfoEnabled()){
			logger.info("***** StoreServiceImpl.getCommandStoresByCountry() method begin*****");
		}	
		
		HashMap param=new HashMap();
		String countryCode = dto.getAsString("countryCode");
		String hql = "";
		List<LvStore> storeList = null; 
		
		//获取当前商城code
		LvStore pStore = (LvStore)lvlogicReadDao.findUniqueByProperty(LvStore.class, "storeFlag", dto.getAsString("storeFlag"));
		if(pStore==null){
			return null;
		}

		//优先获取IP国家的推荐店铺
		param.put("countryCode", countryCode);
		param.put("parentCode", pStore.getCode());
		hql = "from LvStore where isdel=0 and status=1 and parentCode=:parentCode and isCommend=1 and countryCode = :countryCode order by orderValue desc,createTime desc";
		storeList = (List<LvStore>)lvlogicReadDao.find(hql, param);
		
		if(null!=storeList && storeList.size()>0){
			//再获取非IP国家的推荐店铺
			//param.put("countryCode", countryCode);
			hql = "from LvStore where isdel=0 and status=1 and parentCode=:parentCode and isCommend=1 and (countryCode != :countryCode or countryCode is null) order by orderValue desc,createTime desc";
			List<LvStore> nostoreList = (List<LvStore>)lvlogicReadDao.find(hql, param);
			
			//将非IP国家的推荐店铺与IP国家的推荐店铺组合一起
			for(LvStore store : nostoreList){
				storeList.add(store);
			}
		//如果没有符合的，则获取所有的推荐店铺
		}else{
			dto.put("parentCode", pStore.getCode());
			storeList = this.getRecommendStoreList(dto);
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** StoreServiceImpl.getCommandStoresByCountry() method end*****");
		}	
		return storeList;
	}

	/**
	 * 获取所有的商城商品信息（IP国家优先）
	 * @param dto
	 * @return
	 */
	@Override
	public Dto getShopProductsByCountry(Dto dto) {
		
		if (logger.isInfoEnabled()){
			logger.info("***** StoreServiceImpl.getShopProductsByCountry() method begin*****");
		}	
		
		String countryCode = dto.getAsString("countryCode");
		HashMap param=new HashMap();
		String hql = "";
		List<Object[]> objList = new ArrayList<Object[]>(); //存放商品加店铺信息
		String storeFlag = dto.getAsString("storeFlag");
		
		//获取当前商城code
		LvStore pStore = (LvStore)lvlogicReadDao.findUniqueByProperty(LvStore.class, "storeFlag", storeFlag);
		if(pStore==null){
			return null;
		}
		
		//获取店铺
		param.put("countryCode", countryCode);
		param.put("parentCode", pStore.getCode());
		hql = "select storeFlag from LvStore where countryCode = :countryCode and isdel=0 and status=1 and parentCode=:parentCode order by orderValue desc, createTime desc";
		List<String> storeFlagList=(List<String>)lvlogicReadDao.find(hql,param);
		if(null!=storeFlagList && storeFlagList.size()>0){
			String storeFlags=storeFlagList.toString().replaceAll("\\[|\\]", "'").replaceAll("\\, ", "','");
			
			//获取该店铺下面的IP国家商品
			param.clear();
			param.put("subjectType", dto.getAsString("subjectType"));
			hql="from LvShopProduct where subjectType=:subjectType and storeId in ("+storeFlags+") order by orderValue desc";
			List<LvShopProduct> productList=(List<LvShopProduct>)lvlogicReadDao.find(hql, param);
			
			//获取该店铺下面的非IP国家商品
			param.clear();
			param.put("subjectType", dto.getAsString("subjectType"));
			hql="from LvShopProduct where subjectType=:subjectType and storeId not in ("+storeFlags+") order by orderValue desc";
			List<LvShopProduct> noproductList=(List<LvShopProduct>)lvlogicReadDao.find(hql, param);

			//将商品价格封装进去，方便页面取值
			dto.put("productList", productList);
			List<LvShopProduct> newProductList=this.changeProductList(dto);
			dto.put("productList", noproductList);
			List<LvShopProduct> nonewProductList=this.changeProductList(dto);
			
			//将商品信息和店铺信息封装至一起
			for(LvShopProduct product:newProductList){
				//获取该商品所属店铺信息
				dto.put("storeFlag", product.getStoreId());
				LvStore store = this.getStoreByFlag(dto);
				Object obj[] = new Object[2];
				obj[0] = product;
				obj[1] = store;
				objList.add(obj);
			}
			for(LvShopProduct pro:nonewProductList){
				//获取该商品所属店铺信息
				dto.put("storeFlag", pro.getStoreId());
				LvStore store = this.getStoreByFlag(dto);
				Object obj[] = new Object[2];
				obj[0] = pro;
				obj[1] = store;
				objList.add(obj);
			}
		}else{
			dto.put("storeFlag", storeFlag);
			List<LvShopProduct> allProductList = this.getShopProductList(dto);
			//将商品信息和店铺信息封装至一起
			for(LvShopProduct product:allProductList){
				//获取该商品所属店铺信息
				dto.put("storeFlag", product.getStoreId());
				LvStore store = this.getStoreByFlag(dto);
				Object obj[] = new Object[2];
				obj[0] = product;
				obj[1] = store;
				objList.add(obj);
			}
		}
		
		dto.put("objList", objList);
		
		if (logger.isInfoEnabled()){
			logger.info("***** StoreServiceImpl.getShopProductsByCountry() method end*****");
		}	
		return dto;
	}

	/**
	 * 由店铺标志获取商城标志
	 * @param dto
	 * @return
	 */
	@Override
	public String getMallFlag(Dto dto) {

		if (logger.isInfoEnabled()){
			logger.info("***** StoreServiceImpl.getMallFlag() method begin*****");
		}	
		
		String storeFlag = dto.getAsString("storeFlag");
		HashMap param = new HashMap();
		String hql = "";
		
		param.put("storeFlag", storeFlag);
		hql = "Select parentCode from LvStore where storeFlag=:storeFlag";
		List list = lvlogicReadDao.find(hql, param);
		if(null!=list && list.size()>0){
			String parentCode = list.get(0).toString();
			LvStore store = (LvStore)lvlogicReadDao.findUniqueByProperty(LvStore.class, "code", parentCode);
			if(null!=store){
				return store.getStoreFlag();
			}
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** StoreServiceImpl.getMallFlag() method end*****");
		}	
		return storeFlag;
	}

	/**
	 * 根据币种找店铺（启用状态的店铺）
	 * @param dto
	 * @return
	 * @throws ServiceException
	 * @author zhengxue
	 * @version 1.0 2014-06-19
	 */
	@Override
	public List getStoresByCurrency(Dto dto) throws ServiceException {

		String currency = dto.getAsString("currency"); //币种
		String mallSystem = dto.getAsString("mallSystem"); //所属商城体系
		
		String hql = "select name from LvStore where currency=:currency and mallSystem=:mallSystem and status=1";
		HashMap param = new HashMap();
		param.put("currency",currency);
		param.put("mallSystem",mallSystem);
		List storeNameList = lvlogicReadDao.find(hql, param);

		return storeNameList;
	}

}
