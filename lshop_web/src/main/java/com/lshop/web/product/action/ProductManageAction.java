package com.lshop.web.product.action;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.core.datastructure.page.Pagination;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.action.BaseAction;
import com.lshop.common.activity.constant.ActivityConstant;
import com.lshop.common.activity.vo.Activity;
import com.lshop.common.activity.vo.ActivityLimitTime;
import com.lshop.common.activity.vo.ActivityLimited;
import com.lshop.common.activity.vo.GeneralActivity;
import com.lshop.common.pojo.logic.LvActivity;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.pojo.logic.LvProductImage;
import com.lshop.common.pojo.logic.LvProductLadder;
import com.lshop.common.pojo.logic.LvProductProperty;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.price.vo.ProductPrice;
import com.lshop.common.util.Constants;
import com.lshop.common.util.StoreHelp;
import com.lshop.web.activity.service.ActivityService;
import com.lshop.web.price.service.PriceService;
import com.lshop.web.product.service.ProductService;
import com.lshop.web.product.vo.ProdDetailActivityVo;
import com.lshop.web.product.vo.ProductActivityVo;
import com.lshop.web.userCenter.UserConstant;

/**
 * 产品模块
 * @author zhengxue
 * @since 2.0 2012-07-23
 *
 */
@SuppressWarnings("serial")
@Controller("ProductManageAction")
@Scope("prototype")
public class ProductManageAction extends BaseAction {
	private String shopFlag;
	/**
	 * 产品号,多个以逗号隔开
	 */
	private String prodCode;
	/**
	 * 商品活动
	 */
	private ProductActivityVo activity;
	@Resource
	private ProductService productService;
	/**
	 * 跳转至某个产品商城页面
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public String toProduct() throws Exception{
		
		//如果传递有店铺标志，就用新传的店铺标志；如果没有，则取当前的店铺标志 
		String storeFlag = this.getFlag();
		String shopFlag = this.getRequest().getParameter("shopFlag");
		if(null!=shopFlag && !("").equals(shopFlag)){
			if(!shopFlag.equals(storeFlag)){
				storeFlag = shopFlag;
			}
		}
		dto.put("storeFlag", storeFlag);
		
		String pcode=this.getRequest().getParameter("pcode");
		dto.put("pcode", pcode);
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
		
		this.getRequest().setAttribute("oPrice", product.getPrice());
		this.getRequest().setAttribute("product", product); //产品列表
		
		//获取产品图片信息
		List<LvProductImage> pImages=(List<LvProductImage>)this.doService("ProductService", "getProductImageByCode", dto);
		this.getRequest().setAttribute("pImages", pImages);
		
		//获取产品搭配信息
		List<LvProduct> mProducts=(List<LvProduct>)this.doService("ProductService", "getMatchProduct", dto);
		this.getRequest().setAttribute("mProducts", mProducts); //组合产品列表，里面已经将活动价格进行封装，即凡是参加活动的显示活动价，没参加活动的显示原价
		this.getRequest().setAttribute("mProductsLen", mProducts.size());
		
		//获取推荐产品
		List<LvProduct> rProducts=(List<LvProduct>)this.doService("ProductService", "getReCommendProduct", dto);
		this.getRequest().setAttribute("rProducts", rProducts);//推荐产品列表，里面已经将活动价格进行封装，即凡是参加活动的显示活动价，没参加活动的显示原价
		
		//获取产品的属性（如：产品介绍、规格参数等）
		dto.put("pcode", pcode);
		List<LvProductProperty> propertys=(List<LvProductProperty>)this.doService("ProductService", "getPropertyByCode", dto);
		this.getRequest().setAttribute("propertys", propertys);
		this.getRequest().setAttribute("propertyNum", propertys.size());
		
		//获取该产品的评论信息
		page.setNumPerPage(10);
		dto.setDefaultPage(page);
		List<Object[]> objList=(List<Object[]>)this.doService("ProductService", "getCommentsByCode", dto);
		page=(Pagination)dto.get("page");
		this.getRequest().setAttribute("objList", objList);
		this.getRequest().setAttribute("commentNum", page.getTotalCount());
		this.getRequest().setAttribute("pagination", page);
		
		this.getRequest().setAttribute("flag",product.getCode());//用来指定左边的选项卡选中哪一个
		this.getRequest().setAttribute("len",rProducts.size());//用来指定左边的选项卡选中哪一个
		
		//用户评价默认选中标识
		String pageMark=this.getRequest().getParameter("pageMark");
		if(null!=pageMark && !("").equals(pageMark)){
			this.getRequest().setAttribute("pageMark", 1);
		}else{
			this.getRequest().setAttribute("pageMark", 0);
		}
		
		//获取当前店铺信息
		dto.put("storeFlag", storeFlag);
		LvStore store = (LvStore)this.doService("StoreService", "getStoreByFlag", dto);
		this.getRequest().setAttribute("lvStore", store);
		
		String str = "code="+store.getCode()+"*name="+store.getName()+"*domainName="+store.getDomainName();
		String mallSystem = Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag()); //获取当前店铺所属系统（华扬orBanana）
		this.addCookie(UserConstant.STORE,str, true, 100000000,Constants.MALL_TO_DOMAIN_POSTFIX.get(mallSystem));
		
		return "product";
	}
	
	
	/**
	 * 改变活动状态
	 */
	public String changActivityStatus(){
		String pcode=this.getRequest().getParameter("productCode");
		dto.put("pcode", pcode);
		this.doService("ProductService", "updateActivityStatus", dto);
		return null;
	}

	/**
	 * 获取产品评论列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Pagination getProductComments(){
		//获取该产品的评论信息
		page.setNumPerPage(10);
		dto.setDefaultPage(page);
		dto.put("pcode", getRequest().getParameter("pcode"));
		List<Object[]> objList=(List<Object[]>)this.doService("ProductService", "getCommentsByCode", dto);
		page=(Pagination)dto.get("page");
		this.getRequest().setAttribute("pagination", page);

		return page;
	}
	
	/**
	 * 产品评论分页
	 * @return
	 */
	public String toProductComments(){
		PrintWriter out = null;
		HttpServletResponse response  = ServletActionContext.getResponse();
		
		
		page.setNumPerPage(10);
		dto.setDefaultPage(page);
		dto.put("storeFlag",this.getFlag());
		dto.put("pcode", getRequest().getParameter("pcode"));
		List<Object[]> objList=(List<Object[]>)this.doService("ProductService", "getCommentsByCode", dto);
		page=(Pagination)dto.get("page");
		
		this.getRequest().setAttribute("objList", objList);
		this.getRequest().setAttribute("commentNum", page.getTotalCount());
		this.getRequest().setAttribute("pagination", page);
		
		
		return "pageProductComments";
	}
	/**
	 * 异步加载产品价格
	 * @throws Exception
	 */
	public void loadProdPrice() throws Exception {
		if (StringUtils.isBlank(shopFlag) || StringUtils.isBlank(prodCode)) {
			return;
		}
		String[] pcode = prodCode.split(",");
		String json = productService.getPricesJson(shopFlag, pcode);
		this.getResponse().setContentType("application/json;charset=UTF-8");
		PrintWriter out = this.getResponse().getWriter();
		out.print(json);
		out.close();
	}
	/**
	 * 异步更新商品详情商品价格
	 * @throws Exception
	 */
	public void getProdDetailPrice() throws Exception {
		ProdDetailActivityVo detailActivityVo = productService.getProdDetailActivity(prodCode);
		String json = JSONObject.fromObject(detailActivityVo).toString();
		this.getResponse().setContentType("application/json;charset=UTF-8");
		PrintWriter out = this.getResponse().getWriter();
		out.print(json);
		out.close();
	}
	public ProductActivityVo getActivity() {
		return activity;
	}
	public void setActivity(ProductActivityVo activity) {
		this.activity = activity;
	}

	public String getShopFlag() {
		return shopFlag;
	}

	public void setShopFlag(String shopFlag) {
		this.shopFlag = shopFlag;
	}

	public String getProdCode() {
		return prodCode;
	}

	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}
	
}

