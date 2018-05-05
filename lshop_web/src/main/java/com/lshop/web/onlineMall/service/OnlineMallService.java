package com.lshop.web.onlineMall.service;

import java.util.List;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.ShoppingCartBeanForCookie;
import com.lshop.common.pojo.logic.LvGroupBuy;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.pojo.logic.LvProductLadder;
import com.lshop.common.pojo.logic.LvShopCart;
import com.lshop.web.shopCart.vo.ShopCartItem;
import com.lshop.web.shopCart.vo.ShopCartVo;

public interface OnlineMallService {
	
	/**
	 * 保存购物车信息,合并购物车
	 * @param dto
	 * @throws ServiceException
	 */
	public void cacheShopCart(Dto dto) throws Exception;
	
	/**
	 * 获取该用户的购物车信息
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public List<LvShopCart> getShopCartList(Dto dto) throws ServiceException;

	/**
	 * 
	 * @Method: findShopCartByCode
	 * @Description: [根据产品编码和用户编码查询用户购物车信息]
	 * @Author: [liaoxj]
	 * @param productCode
	 *            产品编码
	 * @param userCode
	 *            用户编码
	 * @param storeFlag
	 *            店铺编码
	 * @return LvShopCart
	 */
	public LvShopCart findShopCartByCode(String productCode,String userCode,String storeFlag);
	
	/**
	 * 获取购物车中的数量
	 * @param dto
	 * @throws ServiceException
	 */
	public Integer getShopCartNum(Dto dto) throws ServiceException;
	/**
	 * 删除某条购物车信息
	 * 根据购物车表code
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Boolean delCartByCode(Dto dto);
	/**
	 * 更改购物车产品数量
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Dto changCartNum(Dto dto) throws ServiceException;
	/**
	 * 获取所有的购物车列表，分店铺展示
	 * @param dto（用户code）
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	public List getAllShopCartInfo(Dto dto) throws ServiceException;
	
	/**
	 * 获取cookie中所有的购物车信息，分店铺展示
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	public List getAllShopCartInfoForCookie(Dto dto) throws ServiceException;
	/**
	 * 返回用户店铺购物车信息及购物车统计
	 * @param userCode
	 * @param storeId
	 * @return
	 * @throws Exception
	 */
	public ShopCartVo getShopCartByStore(String userCode, String storeId) throws ServiceException;
	/**
	 * 用户登陆合并购物车
	 * @param cookies
	 * @return
	 * @throws ServiceException
	 */
	public boolean loginMergeShopCart(List<ShoppingCartBeanForCookie> cookies, String userCode) throws ServiceException;
}
