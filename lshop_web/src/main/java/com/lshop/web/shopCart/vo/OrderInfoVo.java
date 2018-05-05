package com.lshop.web.shopCart.vo;

import java.io.Serializable;
import java.util.List;

import com.lshop.common.coupon.vo.CustomerCoupon;
import com.lshop.common.pojo.logic.LvArea;
import com.lshop.common.pojo.logic.LvPaymentStyle;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.pojo.user.LvAccountAddress;

/**
 * 订单提交页视图
 * @author caicl
 *
 */
public class OrderInfoVo implements Serializable{
	private static final long serialVersionUID = -7621645765536529110L;
	private ShopCartVo shopCartVo;
	private List<LvAccountAddress> addressList;
	private LvAccountAddress dAddress;
	private List<LvPaymentStyle> paymentList;
	private LvPaymentStyle dPayment;
	private List<LvArea> contryList;
	private LvStore store;
	private String carriage;
	private String orderAmount;
	private List<CustomerCoupon> couponList;
	public OrderInfoVo() {
		super();
	}
	public OrderInfoVo(ShopCartVo shopCartVo,
			List<LvAccountAddress> addressList, LvAccountAddress dAddress,
			List<LvPaymentStyle> paymentList, LvPaymentStyle dPayment,
			List<LvArea> contryList, LvStore store, String carriage,
			String orderAmount, List<CustomerCoupon> couponList) {
		super();
		this.shopCartVo = shopCartVo;
		this.addressList = addressList;
		this.dAddress = dAddress;
		this.paymentList = paymentList;
		this.dPayment = dPayment;
		this.contryList = contryList;
		this.store = store;
		this.carriage = carriage;
		this.orderAmount = orderAmount;
		this.couponList = couponList;
	}
	public ShopCartVo getShopCartVo() {
		return shopCartVo;
	}
	public void setShopCartVo(ShopCartVo shopCartVo) {
		this.shopCartVo = shopCartVo;
	}
	public List<LvAccountAddress> getAddressList() {
		return addressList;
	}
	public void setAddressList(List<LvAccountAddress> addressList) {
		this.addressList = addressList;
	}
	public LvAccountAddress getdAddress() {
		return dAddress;
	}
	public void setdAddress(LvAccountAddress dAddress) {
		this.dAddress = dAddress;
	}
	public List<LvPaymentStyle> getPaymentList() {
		return paymentList;
	}
	public void setPaymentList(List<LvPaymentStyle> paymentList) {
		this.paymentList = paymentList;
	}
	public LvPaymentStyle getdPayment() {
		return dPayment;
	}
	public void setdPayment(LvPaymentStyle dPayment) {
		this.dPayment = dPayment;
	}
	public List<LvArea> getContryList() {
		return contryList;
	}
	public void setContryList(List<LvArea> contryList) {
		this.contryList = contryList;
	}
	public LvStore getStore() {
		return store;
	}
	public void setStore(LvStore store) {
		this.store = store;
	}
	public String getCarriage() {
		return carriage;
	}
	public void setCarriage(String carriage) {
		this.carriage = carriage;
	}
	public String getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}
	public List<CustomerCoupon> getCouponList() {
		return couponList;
	}
	public void setCouponList(List<CustomerCoupon> couponList) {
		this.couponList = couponList;
	}
}
