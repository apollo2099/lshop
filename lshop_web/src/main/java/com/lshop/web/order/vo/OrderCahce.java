package com.lshop.web.order.vo;

import java.io.Serializable;
import java.util.List;

import com.lshop.common.pojo.logic.LvOrder;
import com.lshop.common.pojo.logic.LvOrderAddress;
import com.lshop.common.pojo.logic.LvOrderDetails;
import com.lshop.common.pojo.logic.LvOrderPackageDetails;

/**
 * 订单缓存
 * @author caicl
 *
 */
public class OrderCahce implements Serializable{

	private static final long serialVersionUID = -5662081887122977808L;
	
	private LvOrder order;
	private List<LvOrderDetails> details;
	private LvOrderAddress address;
	private List<LvOrderPackageDetails> packageDetail;
	private float allAmount;//订单总金额(没有乘汇率)
	private boolean sync = false;//是否已经进行过同步;若为true则表示发生同步失败
	public OrderCahce(LvOrder order, List<LvOrderDetails> details,
			LvOrderAddress address, List<LvOrderPackageDetails> packageDetail,
			float allAmount) {
		super();
		this.order = order;
		this.details = details;
		this.address = address;
		this.packageDetail = packageDetail;
		this.allAmount = allAmount;
	}
	public OrderCahce() {
		super();
	}
	public LvOrder getOrder() {
		return order;
	}
	public void setOrder(LvOrder order) {
		this.order = order;
	}
	public List<LvOrderDetails> getDetails() {
		return details;
	}
	public void setDetails(List<LvOrderDetails> details) {
		this.details = details;
	}
	public LvOrderAddress getAddress() {
		return address;
	}
	public void setAddress(LvOrderAddress address) {
		this.address = address;
	}
	public List<LvOrderPackageDetails> getPackageDetail() {
		return packageDetail;
	}
	public void setPackageDetail(List<LvOrderPackageDetails> packageDetail) {
		this.packageDetail = packageDetail;
	}
	public float getAllAmount() {
		return allAmount;
	}
	public void setAllAmount(float allAmount) {
		this.allAmount = allAmount;
	}
	public boolean isSync() {
		return sync;
	}
	public void setSync(boolean sync) {
		this.sync = sync;
	}
	@Override
	public String toString() {
		return "OrderCahce [order=" + order + ", details=" + details
				+ ", address=" + address + ", packageDetail=" + packageDetail
				+ ", allAmount=" + allAmount + ", sync=" + sync + "]";
	}
	
}
