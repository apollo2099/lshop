package com.lshop.web.coupon.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.core.datastructure.page.Pagination;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.action.BaseAction;
import com.lshop.common.coupon.vo.MyCouponViewVo;
import com.lshop.common.pojo.logic.LvCoupon;
import com.lshop.common.pojo.logic.LvCouponType;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.pojo.logic.LvProductType;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.util.Constants;
import com.lshop.web.activity.service.ActivityService;
import com.lshop.web.userCenter.UserConstant;

/**
 * 优惠券管理模块
 * @author zhengxue
 * @version 1.0 2014-06-19
 *
 */
@SuppressWarnings("serial")
@Controller("CouponManageAction")
@Scope("prototype")
public class CouponManageAction extends BaseAction {
	@Resource
	private ActivityService activityService;
	/**
	 * 获取我的优惠券（各种使用状况）
	 * @author zhengxue
	 * @version 1.0 2014-06-19
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public String getCouponList() throws Exception{
		
		String useStatus = this.getRequest().getParameter("useStatus");//使用状态 ：1,未使用，未过期  2，已使用 3，未使用，已过期（默认状态为1）
	
		//获取当前店铺所属系统（华扬orBanana）
		String mallSystem = Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag()); 
		
		//获取当前用户code
		String userCode = null;;
		try {
			userCode = this.getCookieValue(UserConstant.USER_ID, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//获取符合条件的优惠券列表
		page.setNumPerPage(10);
		dto.setDefaultPage(page);
		dto.put("obtain", userCode);
		dto.put("useStatus", useStatus);
		dto.put("mallSystem", mallSystem);
		page = (Pagination)this.doService("CouponService", "getCouponList", dto);
		
		//对优惠券列表进行数据封装
		if(null!=page.getList() && page.getList().size()>0){
			for(MyCouponViewVo lvCoupon : (List<MyCouponViewVo>)page.getList()){
				
				//根据优惠码获取其对应的优惠券类型
				dto.put("code", lvCoupon.getCouponTypeCode());
				LvCouponType couponType = (LvCouponType)this.doService("CouponService", "getCouponTypeByCoupon", dto);
				lvCoupon.setLvCouponType(couponType);
				
				//根据优惠券类型找可使用的商品或商品类型
				String limitProducts = "";
				dto.put("couponTypeCode", couponType.getCode());
				//当使用种类是商品类型
				if(couponType.getRelationType()==1){ 
					//根据优惠券类型获取限定商品类别
					List<LvProductType> productTypeList = (List<LvProductType>)this.doService("CouponService", "getProductTypesByCoupon", dto);
					for(LvProductType productType : productTypeList){
						//根据店铺标志获取其对应的店铺
						dto.put("storeFlag", productType.getStoreId());
						LvStore store = (LvStore)this.doService("StoreService", "getStoreByFlag", dto);
						limitProducts += productType.getTypeName()+"("+store.getName()+")"+"、";
						
					}
				//当使用种类是商品	
				}else{ 
					//根据优惠券类型获取限定商品
					List<LvProduct> productList = (List<LvProduct>)this.doService("CouponService", "getProductsByCoupon", dto);
					for(LvProduct product : productList){
						if(ObjectUtils.isNotEmpty(product)){
							//根据店铺标志获取其对应的店铺
							dto.put("storeFlag", product.getStoreId());
							LvStore store = (LvStore)this.doService("StoreService", "getStoreByFlag", dto);
							if(ObjectUtils.isNotEmpty(store)&&ObjectUtils.isNotEmpty(store.getName())){
								limitProducts += product.getProductName()+"("+store.getName()+")"+"、";
							}
						}
					}
				}
				
				if(StringUtils.isNotEmpty(limitProducts)){
					limitProducts = limitProducts.substring(0, limitProducts.length()-1);
					lvCoupon.setLimitProducts(limitProducts);
				}
				//获取活动信息
				if (StringUtils.isNotBlank(lvCoupon.getGrantWay())) {
					lvCoupon.setGrantActivity(activityService.getActivity(lvCoupon.getGrantWay()));
				}
			}
		}

		this.getRequest().setAttribute("pagination", page); 
		this.getRequest().setAttribute("useStatus", useStatus);//标识当前选中项
		this.getRequest().setAttribute("flag", "coupon");//标识菜单栏
		return "myCouponList";
	}
	
	/**
	 * 查看更多优惠券
	 * @return
	 */
	public String moreCouponList(){
		
		String useStatus = this.getRequest().getParameter("useStatus");//使用状态 ：1,未使用，未过期  2，已使用 3，未使用，已过期（默认状态为1）
	
		//获取当前店铺所属系统（华扬orBanana）
		String mallSystem = Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag()); 
		
		//获取当前用户code
		String userCode = null;;
		try {
			userCode = this.getCookieValue(UserConstant.USER_ID, true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//获取符合条件的优惠券列表
		page.setNumPerPage(10);
		dto.setDefaultPage(page);
		dto.put("obtain", userCode);
		dto.put("useStatus", useStatus);
		dto.put("mallSystem", mallSystem);
		page = (Pagination)this.doService("CouponService", "getCouponList", dto);
		
		//对优惠券列表进行数据封装
		if(null!=page.getList() && page.getList().size()>0){
			for(LvCoupon lvCoupon : (List<LvCoupon>)page.getList()){
				
				//根据优惠码获取其对应的优惠券类型
				dto.put("code", lvCoupon.getCouponTypeCode());
				LvCouponType couponType = (LvCouponType)this.doService("CouponService", "getCouponTypeByCoupon", dto);
				lvCoupon.setLvCouponType(couponType);
				
				//根据优惠券类型找可使用的商品或商品类型
				String limitProducts = "";
				dto.put("couponTypeCode", couponType.getCode());
				//当使用种类是商品类型
				if(couponType.getRelationType()==1){ 
					//根据优惠券类型获取限定商品类别
					List<LvProductType> productTypeList = (List<LvProductType>)this.doService("CouponService", "getProductTypesByCoupon", dto);
					for(LvProductType productType : productTypeList){
						//根据店铺标志获取其对应的店铺
						dto.put("storeFlag", productType.getStoreId());
						LvStore store = (LvStore)this.doService("StoreService", "getStoreByFlag", dto);
						limitProducts += productType.getTypeName()+"("+store.getName()+")"+"、";
						
					}
				//当使用种类是商品	
				}else{ 
					//根据优惠券类型获取限定商品
					List<LvProduct> productList = (List<LvProduct>)this.doService("CouponService", "getProductsByCoupon", dto);
					for(LvProduct product : productList){
						//根据店铺标志获取其对应的店铺
						dto.put("storeFlag", product.getStoreId());
						LvStore store = (LvStore)this.doService("StoreService", "getStoreByFlag", dto);
						limitProducts += product.getProductName()+"("+store.getName()+")"+"、";
					}
				}
				
				if(StringUtils.isNotEmpty(limitProducts)){
					limitProducts = limitProducts.substring(0, limitProducts.length()-1);
					lvCoupon.setLimitProducts(limitProducts);
				}
			}
		}

		this.getRequest().setAttribute("pagination", page); 
		this.getRequest().setAttribute("useStatus", useStatus);//标识当前选中项
		this.getRequest().setAttribute("flag", "coupon");//标识菜单栏
		return "moreCouponList";
	}
}
