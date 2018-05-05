package com.lshop.ws.server.popularize.order.bean;

import java.util.ArrayList;
import java.util.List;

public class PCouponDtoPageResposne extends BasePojo {
	private List<PCouponMainPageDto> couponMainPageList = new ArrayList<PCouponMainPageDto>();

	private int totalCount;

	public List<PCouponMainPageDto> getCouponMainPageList() {
		return couponMainPageList;
	}

	public void setCouponMainPageList(List<PCouponMainPageDto> couponMainPageList) {
		this.couponMainPageList = couponMainPageList;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

}
