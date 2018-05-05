package com.lshop.ws.server.popularize.order.bean;

public class PCouponDtoResposne extends BasePojo {
	PCouponMainDto couponMain = new PCouponMainDto();

	public PCouponMainDto getCouponMain() {
		return couponMain;
	}

	public void setCouponMain(PCouponMainDto couponMain) {
		this.couponMain = couponMain;
	}

}
