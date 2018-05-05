package com.lshop.web.product.service;

import java.util.List;
import java.util.Map;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.logic.LvActivity;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.pojo.logic.LvProductImage;
import com.lshop.common.pojo.logic.LvProductLadder;
import com.lshop.common.pojo.logic.LvProductPackage;
import com.lshop.common.pojo.logic.LvProductProperty;
import com.lshop.common.pojo.logic.LvProductType;
import com.lshop.common.price.vo.ProductPrice;
import com.lshop.web.product.vo.ProdDetailActivityVo;
import com.lshop.web.product.vo.ProductActivityVo;

/**
 * 产品模块
 * @author zhengxue
 * @since 2.0 2012-07-20
 *
 */
public interface ProductService {
	
	/**
	 * 获取所有的产品类型（产品、配件、应用）
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public List<LvProductType> getTypes(Dto dto) throws ServiceException;
	
	/**
	 * 根据产品类型查找对应的产品列表
	 * 需传递参数ptype
	 * @param dto
	 * @return
	 * @throws ServiceExcetpion
	 */
	public List<LvProduct> getProductByType(Dto dto) throws ServiceException;
	
	/**
	 * 根据code查找对应的产品
	 * 需要传递参数code
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public LvProduct getProductByCode(Dto dto) throws ServiceException;
	public LvProduct getProductByPubCode(Dto dto) throws ServiceException;
	/**
	 * 根据id查找对应的产品
	 * 需要传递参数id
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public LvProduct getProductById(Dto dto) throws ServiceException;
	
	/**
	 * 获取该产品的正在进行中的限时,限量活动信息
	 * pcode 产品号
	 * @return
	 * @throws ServiceException
	 */
	public ProductActivityVo getActivityByCode(Dto dto) throws ServiceException;
	
	/**
	 * 获取该产品图片信息
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public List<LvProductImage> getProductImageByCode(Dto dto) throws ServiceException;
	
	/**
	 * 获取该产品搭配信息
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public  List<LvProduct> getMatchProduct(Dto dto) throws ServiceException;
	
	/**
	 * 获取推荐产品
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public List<LvProduct> getReCommendProduct(Dto dto) throws ServiceException;
	
	/**
	 * 获取该产品的属性
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public List<LvProductProperty> getPropertyByCode(Dto dto) throws ServiceException;
	
	/**
	 * 获取评论列表
	 * 之所以是这种格式，是因为里面还封装的有国旗图标
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public List<Object[]> getCommentsByCode(Dto dto) throws ServiceException;
	
	/**
	 * 获取套餐信息列表
	 * 根据套餐编号，即为套餐情况下的产品列表code查询
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public List<LvProductPackage> getPackageList(Dto dto) throws ServiceException;
	
	/**
	 * 改变活动状态
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Dto updateActivityStatus(Dto dto) throws ServiceException;
	
	/**
	 * 获取产品当前实时价格;若产品参加了限时,限量和阶梯价,则返回该价格;否则返回原价
	 * 产品有可以是下架的产品,仅供商品栏目,搜索结果列表使用
	 * param pnum 产品数量
	 * param pcode 产品号
	 * param storeFlag 店铺标识
	 * @return
	 * @throws ServiceException
	 */
	public float getProductPrice(Dto dto) throws ServiceException;
	/***
	 * 获取原价
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public float getProductOrignalPrice(Dto dto) throws ServiceException;
	
	/**
	 * 获得商品详情活动信息,阶梯价信息
	 * @param pcode
	 * @return
	 * @throws Exception
	 */
	ProdDetailActivityVo getProdDetailActivity(String pcode) throws Exception;
	/**
	 * 更新商品数据缓存
	 * @param prodNo
	 * @return
	 * @throws Exception
	 */
	boolean reflashProdctCache(String prodNo) throws Exception;
	/**
	 * 获取阶梯价格
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public  List<LvProductLadder> getLadderPrice(Dto dto) throws ServiceException;
	
	/**
	 * 根据购买台数获取当前的阶梯价
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public LvProductLadder getLadderByPnum(Dto dto) throws ServiceException;
	/**
	 * 若商品已下架,返回null;否则返回商品在售价格
	 * @param prodNo
	 * @param pnum
	 * @return
	 * @throws ServiceException
	 */
	public ProductPrice getSalePrice(String prodNo, int pnum) throws ServiceException;
	/**
	 * 批量获取商品在售价格
	 * @param storeId
	 * @param productNumMap
	 * @return
	 * @throws ServiceException
	 */
	public Map<String, ProductPrice> getBachProductPrice(Map<String, Integer> productNumMap) throws ServiceException;

	/**
	 * 批量获取商品在售价格
	 * @param shopFlag
	 * @param pcode
	 * @return
	 * @throws Exception
	 */
	String getPricesJson(String shopFlag, String[] pcode) throws Exception;
}
