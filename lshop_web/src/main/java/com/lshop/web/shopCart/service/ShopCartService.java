package com.lshop.web.shopCart.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.mail.Flags.Flag;

import org.apache.commons.collections.map.Flat3Map;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.lshop.common.activity.vo.ActivityLimitOrder;
import com.lshop.common.coupon.vo.CustomerCoupon;
import com.lshop.common.datastructure.ResultMsg;
import com.lshop.common.pojo.logic.LvArea;
import com.lshop.common.pojo.logic.LvCarriageSet;
import com.lshop.common.pojo.logic.LvPaymentStyle;
import com.lshop.common.pojo.user.LvAccountAddress;
import com.lshop.web.shopCart.vo.OrderInfoVo;
import com.lshop.web.shopCart.vo.ShopCartVo;

/**
 * 购物车模块
 * @author zhengxue
 * @since 2.0 2012-07-30
 *
 */
public interface ShopCartService {
	/**
	 * 根据区域编号获取运费信息,若没有设置返回0
	 * @param storeFlag
	 * @param deliverId
	 * @return
	 * @throws ServiceException
	 */
	public float getDeliverCarrigage(String storeFlag, Integer deliverId) throws ServiceException;
	/**
	 * 重新加载运费缓存
	 * @param storeDeliver
	 * @return
	 */
	public float reloadCarriageCache(String storeFlag, Integer deliverId) throws ServiceException;
	/**
	 * 获取所有的支付方式
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public List<LvPaymentStyle> getPaymentList(Dto dto) throws ServiceException;
	/**
	 * 重新加载店铺支付方式
	 * @param storeFlag
	 * @return
	 * @throws ServiceException
	 */
	public List<LvPaymentStyle> reloadPaymentCache(String storeFlag) throws ServiceException;
	/**
	 * 重新加载店铺指定支付方式
	 * @param storeFlag
	 * @param paymentCode
	 * @return
	 * @throws ServiceException
	 */
	public boolean reloadPaymentCache(String storeFlag, String paymentCode) throws ServiceException;
	/**
	 * 获取国家列表
	 * @param dto
	 * @return
	 */
	public List<LvArea> getContryList(Dto dto);
	/**
	 * 载入国家收货地址列表
	 */
	public void loadCountryList();
	/**
	 * 保存订单信息
	 * @param dto
	 * @return
	 * @throws ParseException
	 */
	public ResultMsg saveOrder(Dto dto) throws Exception;
	/**
	 * 保存西联汇款
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Dto saveWesternInfo(Dto dto) throws ServiceException;
	/**
	 * 支付时更改剩余量或已购买量
	 * 判断产品类型，如果是团购产品则更改已购买人数；如果是限量产品，则更改剩余产品量
	 * 传入参数订单号oid
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Dto changeCount(Dto dto) throws ServiceException;
	
	/**
	 * 获取商品应付金额及商品条数
	 * 在保存订单前比较商品金额是否发生改变，以及判断是否所有产品下架或删除情况
	 * @param dto
	 * @return
	 * @throws ParseException
	 */
	public float getTotalPrice(Dto dto) throws Exception;
	/**
	 * 店铺购物车计算,不考虑币种,商品币种和店铺币种一样
	 * @param shopFlag 店铺标识
	 * @param usercode 用户
	 * @param currencyRate 汇率
	 * @return
	 * @throws Exception
	 */
	public float caculateShopcart(String userCode, String shopFlag, float currencyRate) throws Exception;
	/**
	 * 订单计算
	 * @param userCode
	 * @param shopFlag
	 * @param carrigate
	 * @param couponAmount
	 * @param currencyRate
	 * @return
	 * @throws Exception
	 */
	public float caculateOrder(String userCode, String shopFlag, float carrigate, float couponAmount, float currencyRate) throws Exception;
	/**
	 * 返回用户在指定店铺购物车中可使用的优惠券
	 * param userCode 用户
	 * param shopFlag 店铺标识
	 * @return
	 * @throws Exception
	 */
	public List<CustomerCoupon> getUserShopcartCoupons(Dto dto) throws Exception;
	/**
	 * 校验用户优惠券码
	 * @param dto
	 * @return
	 * @throws Exception
	 */
	ResultMsg checkUserCouponCode(Dto dto) throws Exception;
	/**
	 * 返回订单参加的订单活动信息
	 * @param orderNo
	 * @return
	 * @throws Exception
	 */
	public List<ActivityLimitOrder> getOrderActivities(String orderNo) throws Exception;
	/**
	 * 返回指定店铺币种和地址标识的运费值
	 * @param addresscode 收货地址号
	 * @param storeFlag 店铺标识
	 * @return
	 */
	float getCarrige(LvAccountAddress address, String storeFlag) ;
	/**
	 * 获得用户订单提交页相关信息
	 * @param userCode
	 * @param storeFlag
	 * @return
	 * @throws Exception
	 */
	OrderInfoVo getOrderInfoVo(String userCode, String storeFlag) throws Exception;
}
