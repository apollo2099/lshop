package com.lshop.web.product.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品限时,限量活动视图对象
 * @author caicl
 *
 */
public class ProductActivityVo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String productCode;
	private String activityTitle;
	/**
	 * 活动价格
	 */
	private Float activityPrice;
	/**
	 * 活动类型 (1限时，2限量)
	 */
	private Integer activityType;
	private String code;
	private Date createTime;
	private Date modifyTime;
	/**
	 * 限量活动库存
	 */
	private Integer counts;
	/**
	 * 活动开始时间
	 */
	private Date startTime;
	private Date endTime;
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getActivityTitle() {
		return activityTitle;
	}
	public void setActivityTitle(String activityTitle) {
		this.activityTitle = activityTitle;
	}
	public Float getActivityPrice() {
		return activityPrice;
	}
	public void setActivityPrice(Float activityPrice) {
		this.activityPrice = activityPrice;
	}
	public Integer getActivityType() {
		return activityType;
	}
	public void setActivityType(Integer activityType) {
		this.activityType = activityType;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public Integer getCounts() {
		return counts;
	}
	public void setCounts(Integer counts) {
		this.counts = counts;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
}
