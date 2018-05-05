/**
 * 
 */
package com.lshop.web.product.vo;

import java.io.Serializable;
import java.util.List;

import com.gv.core.util.BeanUtils;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.pojo.logic.LvProductLadder;

/**
 * 商品redis缓存
 * 
 * @author caicl
 * 
 */
public class ProdCache implements Serializable {

	private static final long serialVersionUID = -2248615982804321767L;
	//LvProduct的属性
	private Integer id;
	private String productName;
	private String pcode;
	private String pmodel;
	private String ptype;
	private String shopProductType;
	private String pimage;
	private String pimageAd;
	private Integer isActivity;
	private Integer isSupport;
	private Integer isLadder;
	private Integer isPreferences;
	private Integer isSetMeal;
	private Integer isCommend;
	private Integer status;
	private String pubProductCode;
	private String pubPackageName;
	private Float price;
	private String storeId;
	private String code;
	/**
	 * 商品活动类型(1表示限时,2表示限量,3表示阶梯价)
	 */
	private int activityType;
	/**
	 * 店铺对应币种
	 */
	private String currency;
	/**
	 * 关联活动号
	 */
	private String activityNo;
	/**
	 * 活动名称
	 */
	private String activityName;
	/**
	 * 商品活动价格
	 */
	private float activityPrice;
	/**
	 * 限时活动开始时间戳
	 */
	private long beginTime;
	/**
	 * 限时活动结束时间戳
	 */
	private long endTime;
	/**
	 * 阶梯价列表
	 */
	private List<LvProductLadder> ladderList;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getPcode() {
		return pcode;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	public String getPmodel() {
		return pmodel;
	}
	public void setPmodel(String pmodel) {
		this.pmodel = pmodel;
	}
	public String getPtype() {
		return ptype;
	}
	public void setPtype(String ptype) {
		this.ptype = ptype;
	}
	public String getShopProductType() {
		return shopProductType;
	}
	public void setShopProductType(String shopProductType) {
		this.shopProductType = shopProductType;
	}
	public String getPimage() {
		return pimage;
	}
	public void setPimage(String pimage) {
		this.pimage = pimage;
	}
	public String getPimageAd() {
		return pimageAd;
	}
	public void setPimageAd(String pimageAd) {
		this.pimageAd = pimageAd;
	}
	public Integer getIsActivity() {
		return isActivity;
	}
	public void setIsActivity(Integer isActivity) {
		this.isActivity = isActivity;
	}
	public Integer getIsSupport() {
		return isSupport;
	}
	public void setIsSupport(Integer isSupport) {
		this.isSupport = isSupport;
	}
	public Integer getIsLadder() {
		return isLadder;
	}
	public void setIsLadder(Integer isLadder) {
		this.isLadder = isLadder;
	}
	public Integer getIsPreferences() {
		return isPreferences;
	}
	public void setIsPreferences(Integer isPreferences) {
		this.isPreferences = isPreferences;
	}
	public Integer getIsSetMeal() {
		return isSetMeal;
	}
	public void setIsSetMeal(Integer isSetMeal) {
		this.isSetMeal = isSetMeal;
	}
	public Integer getIsCommend() {
		return isCommend;
	}
	public void setIsCommend(Integer isCommend) {
		this.isCommend = isCommend;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getPubProductCode() {
		return pubProductCode;
	}
	public void setPubProductCode(String pubProductCode) {
		this.pubProductCode = pubProductCode;
	}
	public String getPubPackageName() {
		return pubPackageName;
	}
	public void setPubPackageName(String pubPackageName) {
		this.pubPackageName = pubPackageName;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getActivityType() {
		return activityType;
	}
	public void setActivityType(int activityType) {
		this.activityType = activityType;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getActivityNo() {
		return activityNo;
	}
	public void setActivityNo(String activityNo) {
		this.activityNo = activityNo;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public float getActivityPrice() {
		return activityPrice;
	}
	public void setActivityPrice(float activityPrice) {
		this.activityPrice = activityPrice;
	}
	public long getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(long beginTime) {
		this.beginTime = beginTime;
	}
	public long getEndTime() {
		return endTime;
	}
	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}
	public List<LvProductLadder> getLadderList() {
		return ladderList;
	}
	public void setLadderList(List<LvProductLadder> ladderList) {
		this.ladderList = ladderList;
	}
	/**
	 * 返回LvProduct对象
	 * @return
	 */
	public LvProduct getLvProduct() {
		LvProduct product = new LvProduct();
		product.setId(id);
		product.setProductName(productName);
		product.setPcode(pcode);
		product.setPtype(ptype);
		product.setShopProductType(shopProductType);
		product.setPimage(pimage);
		product.setPimageAd(pimageAd);
		product.setIsActivity(isActivity);
		product.setIsSupport(isSupport);
		product.setIsLadder(isLadder);
		product.setIsPreferences(isPreferences);
		product.setIsSetMeal(isSetMeal);
		product.setIsCommend(isCommend);
		product.setStatus(status);
		product.setPubProductCode(pubProductCode);
		product.setPubPackageName(pubPackageName);
		product.setPrice(price);
		product.setStoreId(storeId);
		product.setCode(code);
		return product;
	}
	/**
	 * 设置LvProduct对象
	 * @param product
	 */
	public void setLvProduct(LvProduct product) {
		id = product.getId();
		productName = product.getProductName();
		pcode = product.getPcode();
		ptype = product.getPtype();
		shopProductType = product.getShopProductType();
		pimage = product.getPimage();
		pimageAd = product.getPimageAd();
		isActivity = product.getIsActivity();
		isSupport = product.getIsSupport();
		isLadder = product.getIsLadder();
		isPreferences = product.getIsPreferences();
		isSetMeal = product.getIsSetMeal();
		isCommend = product.getIsCommend();
		status = product.getStatus();
		pubProductCode = product.getPubProductCode();
		pubPackageName = product.getPubPackageName();
		price = product.getPrice();
		storeId = product.getStoreId();
		code = product.getCode();
	}
}
