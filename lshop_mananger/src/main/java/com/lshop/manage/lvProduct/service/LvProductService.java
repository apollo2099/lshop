package com.lshop.manage.lvProduct.service;

import java.util.List;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.logic.LvActivity;
import com.lshop.common.pojo.logic.LvProduct;

public interface LvProductService {
	public Pagination getList(Dto dto) throws ServiceException;
	public Pagination productList(Dto dto) throws ServiceException;

	public Pagination getShopProductList(Dto dto) throws ServiceException;
	public Pagination getProductImage(Dto dto) throws ServiceException;
	public Pagination getProductProperty(Dto dto) throws ServiceException;
	public Pagination getProductLadder(Dto dto) throws ServiceException;
	public Pagination findListByCurrency(Dto dto) throws ServiceException;
	public Pagination findListByCouponType(Dto dto)throws ServiceException;
	public Boolean save(Dto dto) throws ServiceException;
	public Boolean update(Dto dto) throws ServiceException;
	public Boolean updateProduct(Dto dto) throws ServiceException;
	public void delete (Dto dto) throws ServiceException;
	public LvProduct get(Dto dto) throws ServiceException ;
	public List<LvProduct> getAll(Dto dto)throws ServiceException ;
	public List<LvProduct> getAllProduct(Dto dto)throws ServiceException ;
	public List<LvProduct> getAllNoMealProduct(Dto dto)throws ServiceException ;
	public List<LvProduct> getAllMealProduct(Dto dto)throws ServiceException ;
	public List<LvProduct> getLadderProduct(Dto dto)throws ServiceException ;
	public List<LvProduct> getActivityProduct(Dto dto)throws ServiceException ;
	public List<LvProduct> changeProductList(Dto dto) throws ServiceException;
	public void updateSupport(Dto dto)throws ServiceException;
	public LvProduct getProduct(Dto dto)throws ServiceException;
	public String upload(Dto dto)throws ServiceException;
	public void updateType(Dto dto)throws ServiceException;
	public void updateUnSupport(Dto dto)throws ServiceException;
	public LvProduct getProductByCode(Dto dto) throws ServiceException;
	public LvProduct findByPubProductCode(Dto dto)throws ServiceException;
	public LvActivity getActivityByCode(Dto dto) throws ServiceException;
	public Boolean isExistPubProduct(Dto dto)throws ServiceException;
	public Boolean isExistPubPackage(Dto dto)throws ServiceException;
	
}
