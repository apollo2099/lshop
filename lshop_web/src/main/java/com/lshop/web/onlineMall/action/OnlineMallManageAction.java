package com.lshop.web.onlineMall.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.core.util.ObjectUtils;
import com.lshop.common.action.BaseAction;
import com.lshop.common.pojo.ShoppingCartBeanForCookie;
import com.lshop.common.pojo.logic.LvActivity;
import com.lshop.common.pojo.logic.LvApp;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.pojo.logic.LvProductLadder;
import com.lshop.common.pojo.logic.LvProductType;
import com.lshop.common.pojo.logic.LvShopCart;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.util.AppCookieUtil;
import com.lshop.common.util.Constants;
import com.lshop.common.util.StoreHelp;
import com.lshop.web.product.vo.ProductActivityVo;
import com.lshop.web.userCenter.UserConstant;

/**
 * 网上商城模块
 * @author zhengxue
 * @since 2.0 2012-07-23
 *
 */
@SuppressWarnings("serial")
@Controller("OnlineMallManageAction")
@Scope("prototype")
public class OnlineMallManageAction extends BaseAction {
	
	private LvShopCart lvShopCart; //购物车表
	private String shopFlag; //商品所属店铺标志
	
	/**
	 * 跳转至网上商城主页面
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String toMall(){
		
		dto.put("storeFlag", this.getFlag());
		
		//获取所有的产品类型
		List<LvProductType> typeList=(List<LvProductType>)this.doService("ProductService", "getTypes", dto);
		this.getRequest().setAttribute("typeList", typeList);
		
		if(typeList!=null){
			List list=new ArrayList(); //存放产品列表
			List aList=new ArrayList(); //存放应用列表
			for(LvProductType pType:typeList){
				if(pType.getTypeFlag()==1){
					dto.put("ptype", pType.getCode());
					List<LvProduct> pList=(List<LvProduct>)(List<LvProduct>)this.doService("ProductService", "getProductByType", dto);
					Map map=new HashMap();
					map.put("list", pList);
					map.put("title", pType.getTypeName());
					list.add(map);
				}else if(pType.getTypeFlag()==2){
					dto.put("ptype", pType.getCode());
					List<LvApp> appList=(List<LvApp>)this.doService("ApplistService", "getApps", dto);
//					this.getRequest().setAttribute("appList", appList);
					Map map=new HashMap();
					map.put("list", appList);
					map.put("title", pType.getTypeName());
					aList.add(map);
				}
			}
			this.getRequest().setAttribute("pList", list);
			this.getRequest().setAttribute("aList", aList);
		}
		
		//获取当前店铺信息
		dto.put("storeFlag", this.getFlag());
		LvStore store = (LvStore)this.doService("StoreService", "getStoreByFlag", dto);
		this.getRequest().setAttribute("lvStore", store);
		
		return "mall";
	}

	/**
	 * 加入购物车——点击“立即购买”
	 * 之后需要调转到购物车列表页面
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String saveShopCart() throws Exception{
		
		//传递商品所属店铺标识，非商城标识
		dto.put("storeFlag",shopFlag);
		
		//保存该购物车信息
		String userCode=this.getCookieValue(UserConstant.USER_ID, true);
		
		//获取商城标识
		String mallFlag=StoreHelp.getParentFlagByFlag(this.getFlag());
		mallFlag=mallFlag==null?this.getFlag():mallFlag;
		
		//判断产品是否下架或删除
		dto.put("pcode", lvShopCart.getProductCode());
		
		//获取产品信息
		LvProduct product=(LvProduct)this.doService("ProductService", "getProductByCode", dto);
		
		if(product==null){
			this.getRequest().setAttribute("flag", 1); //"很抱歉，该产品不存在！""Sorry, this product no longer exists!"
			return "error";
		}
		if(null!=product){ 
			if(null!=product.getStatus() && product.getStatus()!=1){
				this.getRequest().setAttribute("flag", 1); //"很抱歉，该产品不存在！""Sorry, this product no longer exists!"
				return "error";
			}else if(null!=product.getIsSupport() && product.getIsSupport()!=1){
				this.getRequest().setAttribute("flag", 2); //"很抱歉，该产品已下架！" "Sorry, this product has been pulled off shelves!"
				return "error";
			}
		}
		
		dto.put("userCode", userCode);
		dto.put("mallFlag", mallFlag);
		lvShopCart.setUserCode(userCode);
		lvShopCart.setMallFlag(mallFlag);
//		lvShopCart.setStoreId(this.getFlag());从页面传值
		dto.setDefaultPo(lvShopCart);
		dto.put("product", product);
		this.doService("OnlineMallService", "cacheShopCart", dto);
		
		//将购物车内产品数量放在session中，便于在顶部的购物车信息旁边显示
		dto.put("storeFlag", shopFlag);
		Integer shopCartNum=(Integer)this.doService("OnlineMallService", "getShopCartNum", dto);
		String mallSystem = Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag()); //获取当前店铺所属系统（华扬orBanana）
		this.addCookie(UserConstant.SHOPCART_NUM, shopCartNum.toString(), true, -1,Constants.MALL_TO_DOMAIN_POSTFIX.get(mallSystem));
//		this.getRequest().getSession().setAttribute("shopCartNum",shopCartNum);
		
		return "getShopCartList";
	}

	/**
	 * 加入购物车——点击“立即购买”
	 * 之后需要调转到购物车列表页面
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public void getShopCartForCookie() throws Exception{
		
		String shopCartList = ""; //存放购物车列表 商家名称+产品+数量
		
		
		//获取已有的cookie中是否已有购物车信息
		Map<String, String> shopCart = getCookieValueToMap(UserConstant.SHOPCART,true);
		
		if(null!=shopCart && shopCart.containsKey("list") && shopCart.containsKey("num")){
			shopCartList = shopCart.get("list");
			List<ShoppingCartBeanForCookie> list=AppCookieUtil.parseString(shopCartList,"@","#",":");
			
			dto.put("list", list);
			List objList = (List)this.doService("OnlineMallService", "getAllShopCartInfoForCookie", dto);
			this.getRequest().setAttribute("objList",objList);
		}

		this.getRequest().setAttribute("mark","2");  //mark:1,登陆之后的购物车列表展示   2,未登陆之前的购物车列表展示
	}
	
	/**
	 * 获取购物车列表
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String getShopCartList() throws Exception{
		
		String userCode=this.getCookieValue(UserConstant.USER_ID, true);
		
		//判断用户有没有登陆
		if(userCode==null || userCode.equals("")){
			//获取已有的cookie中是否已有购物车信息
			Map<String, String> shopCart = getCookieValueToMap(UserConstant.SHOPCART,true);
			if(null!=shopCart && shopCart.containsKey("list") && shopCart.containsKey("num")){
				this.getShopCartForCookie();
			}
			return "shopCartMain";
		}
		
		//获取商城标识
		String mallFlag=StoreHelp.getParentFlagByFlag(this.getFlag());
		mallFlag=mallFlag==null?this.getFlag():mallFlag;
		
		dto.put("userCode", userCode);
		String mallSystem = Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag()); //获取当前店铺所属系统（华扬orBanana）
		dto.put("mallSystem", mallSystem);
		List list = (List)this.doService("OnlineMallService", "getAllShopCartInfo", dto);
		
		this.getRequest().setAttribute("objList",list);
		this.getRequest().setAttribute("mark","1"); //mark:1,登陆之后的购物车列表展示   2,未登陆之前的购物车列表展示
		return "shopCartMain";
	}
	
	/**
	 * 放入购物车——点击“加入购物车”
	 * 无需跳转页面，提示即可，用AJAX提交数据
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String putShopCart() throws Exception{
		
		PrintWriter out=this.getResponse().getWriter();
		
		dto.put("storeFlag", this.getFlag());
		String mallSystem = Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag()); //获取当前店铺所属系统（华扬orBanana）
		
		//保存该购物车信息
		String userCode=this.getCookieValue(UserConstant.USER_ID, true);

		//获取商城标识
		String mallFlag=StoreHelp.getParentFlagByFlag(this.getFlag());
		mallFlag=mallFlag==null?this.getFlag():mallFlag;
		
		//判断产品是否下架或删除
		dto.put("pcode", lvShopCart.getProductCode());
		
		//获取产品信息
		LvProduct product=(LvProduct)this.doService("ProductService", "getProductByCode", dto);
		
		if(product==null){
			out.println(-1);
		}else if(null!=product){ 
			if(null!=product.getStatus() && product.getStatus()!=1){
				out.println(-1);
			}else if(null!=product.getIsSupport() && product.getIsSupport()!=1){
				out.println(-2);
			}else{
				//产品正常的情况下再保存至购物车
				lvShopCart.setUserCode(userCode);
				lvShopCart.setStoreId(this.getFlag());
				lvShopCart.setMallFlag(mallFlag);
				dto.setDefaultPo(lvShopCart);
				dto.put("product", product);
				this.doService("OnlineMallService", "cacheShopCart", dto);
				
				//将购物车内产品数量放在session中，便于在顶部的购物车信息旁边显示
				dto.put("userCode", userCode);
				dto.put("storeFlag", this.getFlag());
				Integer shopCartNum=(Integer)this.doService("OnlineMallService", "getShopCartNum", dto);
				this.addCookie(UserConstant.SHOPCART_NUM, shopCartNum.toString(), true, -1,Constants.MALL_TO_DOMAIN_POSTFIX.get(mallSystem));
//				this.getRequest().getSession().setAttribute("shopCartNum",shopCartNum);
				
				out.print(shopCartNum);
			}
		}
		return null;
	}
	
	/**
	 * 组合购买，加入购物车
	 * @return
	 * @throws Exception
	 */
	public String saveMatchShop() throws Exception{
		
		dto.put("storeFlag", shopFlag);
		
		String userCode=this.getCookieValue(UserConstant.USER_ID, true); //获取登陆用户code
		//获取商城标识
		String mallFlag=StoreHelp.getParentFlagByFlag(this.getFlag());
		mallFlag=mallFlag==null?this.getFlag():mallFlag;
		dto.put("mallFlag", mallFlag);
		
		//获取选中的组合产品code
		String[] codes=this.getRequest().getParameterValues("commendProduct");
		//由产品code获取各个产品的信息
		if(codes!=null){
			for(int i=0;i<codes.length;i++){
				//初始化购物车信息表
				LvShopCart cart=new LvShopCart(); 
				//获取该产品信息
				dto.put("storeFlag", shopFlag);
				dto.put("pcode", codes[i]);
				LvProduct product=(LvProduct)this.doService("ProductService", "getProductByCode", dto);
				//产品不存在
				if(null==product){
					continue;
				}
				//产品删除或停用
				if(null!=product.getStatus() && product.getStatus()!=1){
					continue;
				}
				//产品下架
				if(null!=product.getIsSupport() && product.getIsSupport()!=1){
					continue;
				}
				
				//将用户信息存放到购物车表中
				if(product!=null){
					cart.setUserCode(userCode);
					cart.setProductCode(product.getCode());
					cart.setShopNum(1);
					cart.setShopPrice(product.getPrice());
					//判断该产品是否参加活动，如果参加活动，则获取其产品价格进行计算
					if(product.getIsActivity()!=null&&product.getIsActivity()==1){
						ProductActivityVo activity=(ProductActivityVo)this.doService("ProductService", "getActivityByCode", dto);
						if(null!=activity){
							cart.setShopPrice(activity.getActivityPrice());
						}
					}
					cart.setStoreId(shopFlag);
					cart.setMallFlag(mallFlag);
					dto.setDefaultPo(cart);
					dto.put("product", product);
					this.doService("OnlineMallService", "cacheShopCart", dto);
				}
			}
			
			//将购物车内产品数量放在session中，便于在顶部的购物车信息旁边显示
			dto.put("userCode", userCode);
			dto.put("storeFlag", shopFlag);
			Integer shopCartNum=(Integer)this.doService("OnlineMallService", "getShopCartNum", dto);
			String mallSystem = Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag()); //获取当前店铺所属系统（华扬orBanana）
			this.addCookie(UserConstant.SHOPCART_NUM, shopCartNum.toString(), true, -1,Constants.MALL_TO_DOMAIN_POSTFIX.get(mallSystem));
//			this.getRequest().getSession().setAttribute("shopCartNum",shopCartNum);
		}
		return "getShopCartList";
	}
	
	/**
	 * 及时更新限量产品中的剩余产品数
	 * @return
	 * @throws Exception
	 */
	public String getLimitCount() throws Exception{
		
		dto.put("storeFlag", this.getFlag());
		
		String productCode=this.getRequest().getParameter("productCode");
		dto.put("pcode", productCode);
		ProductActivityVo activity=(ProductActivityVo)this.doService("ProductService", "getActivityByCode", dto);
		PrintWriter out=this.getResponse().getWriter();
		if (ObjectUtils.isEmpty(activity)) {
			//活动被禁用或者结束
			out.println(0);
		} else {
			out.println(activity.getCounts());
		}
		return null;
	}
	
	/**
	 * 购物组合产品中，通过产品code找到相应的产品id
	 * 由于cookie购物车中是根据产品id来保存的，在此必须要获取产品id
	 * @return
	 * @throws Exception
	 */
	public String getProductIdByCode() throws Exception{
		
		dto.put("storeFlag", this.getFlag());
		
		String productCode=this.getRequest().getParameter("productCode");
		dto.put("pcode", productCode);
		LvProduct product=(LvProduct)this.doService("ProductService", "getProductByCode", dto);
		
		PrintWriter out=this.getResponse().getWriter();
		out.print(product.getId());
		return null;
	}
	
	/**
	 * 获取阶梯价格
	 * @return
	 * @throws Exception
	 */
	public String getLadderPrice() throws Exception{
		
		dto.put("storeFlag", this.getFlag());
		
		String productCode=this.getRequest().getParameter("productCode");
		dto.put("productCode", productCode);
		List<LvProductLadder> ladderList=(List<LvProductLadder>)this.doService("ProductService", "getLadderPrice", dto);
		
		this.getRequest().setAttribute("ladderList", ladderList);
		return "ladderPrice";
	}
	
	public LvShopCart getLvShopCart() {
		return lvShopCart;
	}

	public void setLvShopCart(LvShopCart lvShopCart) {
		this.lvShopCart = lvShopCart;
	}

	public String getShopFlag() {
		return shopFlag;
	}

	public void setShopFlag(String shopFlag) {
		this.shopFlag = shopFlag;
	}

}
