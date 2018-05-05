package com.lshop.web.store.service;

import java.util.List;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.logic.LvShopActivity;
import com.lshop.common.pojo.logic.LvShopPartner;
import com.lshop.common.pojo.logic.LvShopProduct;
import com.lshop.common.pojo.logic.LvShopSubject;
import com.lshop.common.pojo.logic.LvSpecialtyStore;
import com.lshop.common.pojo.logic.LvSpecialtyStoreType;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.pojo.logic.LvStoreAddress;
import com.lshop.common.pojo.logic.LvStoreArea;

/**
 * 商城模块
 * @author zhengxue
 * @since 1.0 2012-12-19
 *
 */
public interface StoreService {
	
	/**
	 * 获取商城栏目信息列表
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public List<LvShopSubject> getShopSubjectList(Dto dto) throws ServiceException;
	
	/**
	 * 获取栏目所对应的商品信息
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public List<LvShopProduct> getShopProductList(Dto dto) throws ServiceException; 
	
	/**
	 * 获取栏目所对应的商品信息(带分页)
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Pagination getShopProductListForPage(Dto dto) throws ServiceException; 
	
	/**
	 * 获取商城活动信息列表
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public List<LvShopActivity> getShopActivityList(Dto dto) throws ServiceException;
	
	/**
	 * 获取合作商家信息
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public List<LvShopPartner> getShopPartnerList(Dto dto) throws ServiceException;
	
	/**
	 * 根据关键字搜索商品信息
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Pagination getShopProductListForSearch(Dto dto) throws ServiceException; 
	
	/**
	 * 根据关键字搜索店铺信息
	 * @param dto(str,storeFlag)
	 * @return
	 * @throws ServiceException
	 */
	public Pagination getStoreListForSearch(Dto dto) throws ServiceException;
	
	/**
	 * 获取所有专卖店分类
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public List<LvSpecialtyStoreType> getSpecialStoreTypes(Dto dto) throws ServiceException; 

	/**
	 * 根据专卖店类别获取其对应的专卖店
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public List<LvSpecialtyStore> getSpecialStoresByType(Dto dto) throws ServiceException;
	
	/**
	 * 根据code获取栏目分类
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public LvShopSubject getShopSubjectByCode(Dto dto) throws ServiceException;
	
	/**
	 * 根据店铺标志获取店铺信息
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public LvStore getStoreByFlag(Dto dto) throws ServiceException;
	
	/**
	 * 根据店铺code获取店铺地址信息
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public LvStoreAddress getStoreAddressByCode(Dto dto) throws ServiceException;
	
	/**
	 * 获取所有的店铺信息
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public List<LvStore> getStoreList(Dto dto) throws ServiceException;
	
	/**
	 * 获取所有的店铺区域信息
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Dto getStoreAreas(Dto dto) throws ServiceException;
	
	/**
	 * 获取所有的店铺信息（带分页）
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Pagination getStoreListForPage(Dto dto) throws ServiceException;
	
	/**
	 * 根据域名获取店铺信息，用于判断该店铺是否删除或停用
	 * @param domainName
	 * @return
	 */
	public LvStore getStoreByDomain(String domainName);
	
	/**
	 * 获取所有的推荐店铺
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public List<LvStore> getRecommendStoreList(Dto dto) throws ServiceException;
	
	/**
	 * 获取所有有店铺的洲
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public List<LvStoreArea> getContinentArea(Dto dto) throws ServiceException;
	
	/**
	 * 根据IP获取当前国家code
	 * @param dto
	 * @return
	 */
	public String getCountryCodeByIp(Dto dto);
	
	/**
	 * 获取所有的商城商品信息（IP国家优先）
	 * @param dto
	 * @return
	 */
	public Dto getShopProductsByCountry(Dto dto);
	
	/**
	 * 获取所有的推荐店铺信息（IP国家优先）
	 * @param dto
	 * @return
	 */
	public List<LvStore> getCommandStoresByCountry(Dto dto);
	
	/**
	 * 由店铺标志获取商城标志
	 * @param dto
	 * @return
	 */
	public String getMallFlag(Dto dto);
	
	/**
	 * 根据币种找店铺（启用状态的店铺）
	 * @param dto
	 * @return
	 * @throws ServiceException
	 * @author zhengxue
	 * @version 1.0 2014-06-19
	 */
	public List getStoresByCurrency(Dto dto) throws ServiceException;
	
}

