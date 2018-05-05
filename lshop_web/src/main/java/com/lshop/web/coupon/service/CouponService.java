package com.lshop.web.coupon.service;

import java.util.List;
import java.util.Map;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.lshop.common.coupon.vo.CouponDetail;
import com.lshop.common.coupon.vo.CustomerCoupon;
import com.lshop.common.coupon.vo.CustomerCouponEditCheck;
import com.lshop.common.coupon.vo.CustomerCouponQuery;
import com.lshop.common.coupon.vo.CustomerCouponSubmitCheck;
import com.lshop.common.datastructure.ResultMsg;
import com.lshop.common.pojo.logic.LvCouponType;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.pojo.logic.LvProductType;
import com.lshop.common.price.vo.ProductPrice;

/**
 * 优惠券管理模块
 * 
 * @author zhengxue
 * @version 1.0 2014-06-19
 * 
 */
public interface CouponService {

	/**
	 * 获取当前用户的优惠券列表
	 * 可传参：是否使用+是否过期状态
	 * @param dto
	 * @return
	 * @throws ServiceException
	 * @author zhengxue
	 * @version 1.0 2014-06-19
	 */
	public Pagination getCouponList(Dto dto) throws ServiceException;

	/**
	 * 根据优惠码获取其对应的优惠券类型
	 * 
	 * @param dto
	 * @return
	 * @throws ServiceException
	 * @author zhengxue
	 * @version 1.0 2014-06-19
	 */
	public LvCouponType getCouponTypeByCoupon(Dto dto) throws ServiceException;

	/**
	 * 根据优惠券类型获取指定商品或商品类别code
	 * 
	 * @param dto
	 * @return
	 * @throws ServiceException
	 * @author zhengxue
	 * @version 1.0 2014-06-19
	 */
	public List getCouponProductsByType(Dto dto) throws ServiceException;

	/**
	 * 根据优惠券类型获取指定的商品
	 * 
	 * @param dto
	 * @return
	 * @throws ServiceException
	 * @author zhengxue
	 * @version 1.0 2014-06-19
	 */
	public List<LvProduct> getProductsByCoupon(Dto dto) throws ServiceException;

	/**
	 * 根据优惠券类型获取指定的商品类别
	 * 
	 * @param dto
	 * @return
	 * @throws ServiceException
	 * @author zhengxue
	 * @version 1.0 2014-06-19
	 */
	public List<LvProductType> getProductTypesByCoupon(Dto dto) throws ServiceException;

	/**
	 * 
	 * @Description:获取客户可用优惠券
	 * @author CYJ
	 * @date 2014-6-25 下午2:31:21
	 * @param username
	 *            用户名
	 * @param amount
	 *            订单总金额
	 * @param storeId
	 *            店铺标识
	 * @return
	 * @throws Exception
	 */
	public List<CustomerCoupon> getCustomerCoupon(CustomerCouponQuery customerCouponQuery) throws Exception;
	/**
	 * 
	 * @Description:获取优惠券列表(线上)
	 * @author CYJ
	 * @date 2014-6-25 下午4:28:27
	 * @param storeId
	 * @param username
	 * @param orderAmount
	 * @param productNoList
	 * @return
	 * @throws Exception
	 */
	public List<CustomerCoupon> getCustomerCouponList(String storeId, String username, Map<String, ProductPrice> productPriceMap) throws Exception;
	/**
	 * 
	 * @Description:校验优惠券有效性
	 * @author CYJ
	 * @date 2014-6-25 下午4:11:30
	 * @param customerCouponEditCheck
	 * @return
	 * @throws Exception
	 */
	public ResultMsg checkCustomerCoupon(CustomerCouponEditCheck customerCouponEditCheck) throws Exception;

	/**
	 * 
	 * @Description:使用优惠券
	 * @author CYJ
	 * @date 2014-6-25 下午5:07:48
	 * @param orderNo
	 *            订单号
	 * @param customerCouponSubmitCheck
	 * @return
	 * @throws Exception
	 */
	public ResultMsg useCustomerCoupon(String orderNo, CustomerCouponSubmitCheck customerCouponSubmitCheck) throws Exception;

	/**
	 * 
	 * @Description:减优惠券可用库存
	 * @author CYJ
	 * @date 2014-6-27 上午11:31:55
	 * @param couponQuantityMap
	 *            (优惠券类型业务编码，数量)
	 * @return
	 * @throws Exception
	 */
	public Map<String, List<Object[]>> costCouponSqlMap(Map<String, Integer> couponQuantityMap) throws Exception;

	/**
	 * 
	 * @Description:绑定优惠券用户
	 * @author CYJ
	 * @date 2014-6-27 上午11:45:34
	 * @param couponCode
	 *            优惠券业务编码
	 * @param username
	 *            用户名
	 * @return
	 * @throws Exception
	 */
	public Map<String, List<Object[]>> updateUserCouponSqlMap(String couponCode, String username) throws Exception;
	
	

	/**
	 * 
	 * @Description:设置优惠码领券（无用户）
	 * @author CYJ
	 * @date 2014-6-27 上午11:45:34
	 * @param couponCode
	 *            优惠券业务编码
	 * @return
	 * @throws Exception
	 */
	public Map<String, List<Object[]>> updateUserCouponSqlMap(String couponCode) throws Exception;

	/**
	 * 根据优惠券业务码查询优惠券详情
	 * @param couponNo
	 * @return
	 * @throws Exception
	 */
	CouponDetail getCouponDetail(String couponNo) throws Exception;
	/**
	 * 通过抽奖领取优惠券
	 * @param couponCode
	 * @return
	 * @throws Exception
	 */
	boolean obtainCouponByLottery(String couponCode);
}
