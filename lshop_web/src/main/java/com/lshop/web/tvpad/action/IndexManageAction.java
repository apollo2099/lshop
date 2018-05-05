package com.lshop.web.tvpad.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.core.util.ObjectUtils;
import com.gv.html.core.HtmlEngine;
import com.lshop.common.action.BaseAction;
import com.lshop.common.pojo.logic.LvArea;
import com.lshop.common.pojo.logic.LvBlogContent;
import com.lshop.common.pojo.logic.LvBlogType;
import com.lshop.common.pojo.logic.LvShopProduct;
import com.lshop.common.pojo.logic.LvShopSubject;
import com.lshop.common.util.Constants;
import com.lshop.common.util.StoreHelp;
import com.lshop.web.blog.service.BlogService;
import com.lshop.web.store.service.StoreService;
import com.lshop.web.userCenter.UserConstant;

/**
 * 首页
 * @author zhengxue
 * @version 2.0 2012-08-27
 *
 */
@SuppressWarnings("serial")
@Controller("IndexManageAction")
@Scope("prototype")
public class IndexManageAction extends BaseAction {
	
	@Resource BlogService blogService;
	@Resource StoreService storeService;
	/**
	 * 首页跳转
	 * @return
	 * @throws Exception
	 */
	public String toIndex() throws Exception{
		String storeFlag = getFlag();
		if ("www".equals(storeFlag)) {
			//博客列表数据
			loadBlog(storeFlag);
			//栏目数据
			loadShopSuject(storeFlag);
		} else if ("en".equals(storeFlag)) {
			//栏目数据
			loadShopSuject(storeFlag);
		}
		return "toIndex";
	}

	/**
	 * 加载首页推荐新闻
	 * @param storeFlag
	 */
	private void loadBlog(String storeFlag) {
		//获取最新推荐博客文件信息
		List<LvBlogContent> blogs = blogService.getContentsByRecommend(storeFlag);
		getRequest().setAttribute("blogs", blogs);
	}

	/**
	 * 加载首页栏目数据
	 * @param storeFlag
	 */
	private void loadShopSuject(String storeFlag) {
		//栏目商品列表数据
		dto.put("storeFlag", storeFlag);
		List<LvShopSubject> subjectList=storeService.getShopSubjectList(dto);
		if (ObjectUtils.isNotEmpty(subjectList)) {
			for(LvShopSubject subject:subjectList){
				//获取该栏目所对就的商品信息
				dto.put("storeFlag", storeFlag);
				dto.put("subjectType", subject.getCode());
				List<LvShopProduct> productList=(List<LvShopProduct>)storeService.getShopProductList(dto);
				subject.setProducts(productList);
			}
			getRequest().setAttribute("subjectList", subjectList);
		}
	}
	
	/**
	 * cookie——添加
	 * @return
	 * @throws Exception
	 */
	public String putCartCookie() throws Exception{
//		this.removeByCookie(UserConstant.SHOPCART, Constants.MALL_TO_DOMAIN_POSTFIX.get(mallSystem));
		String shopCartList = ""; //存放购物车列表 商家名称+产品+数量
		Integer shopCartNum = 0; //存放购物车数量 多少条产品信息
		
		//获取现有的购物车信息
		String storeId = this.getRequest().getParameter("storeId");
		String productCode = this.getRequest().getParameter("productCode");
		String num = this.getRequest().getParameter("num");
		
		//获取已有的cookie中是否已有购物车信息
		Map<String, String> shopCart = getCookieValueToMap(UserConstant.SHOPCART,true);
		if(null!=shopCart && shopCart.containsKey("list") && shopCart.containsKey("num")){
			shopCartList = shopCart.get("list");
			shopCartNum = Integer.parseInt(shopCart.get("num"));
			//判断这条产品信息是否存在(根据店铺标志和产品code)
			if(shopCartList.indexOf("#"+productCode)!=-1){
				int startNum=shopCartList.indexOf(":", shopCartList.indexOf("#"+productCode)); //数量之前索引
				int endNum = shopCartList.indexOf("@",shopCartList.indexOf("#"+productCode)); //数量之后索引
				String oldNum = ""; //获取之前该产品的数量
				if(endNum!=-1){
					oldNum = shopCartList.substring(startNum+1,endNum); 
				}else{
					oldNum = shopCartList.substring(startNum+1); 
				}
				String oldList = "@"+storeId+"#"+productCode+":"+oldNum; //获取之前该产品的信息
				int newNum = 0;
				if(Integer.parseInt(num)+Integer.parseInt(oldNum)>9999){
					newNum = 9999;
				}else{
					newNum = Integer.parseInt(num)+Integer.parseInt(oldNum);
				}
				String newList = "@"+storeId+"#"+productCode+":"+newNum; //当前产品信息
				shopCartList = shopCartList.replace(oldList, newList); //将此条产品信息替换掉
			}else{
				shopCartList +="@"+storeId+"#"+productCode+":"+num;
				shopCartNum += 1;
			}
		}else{
			shopCartList = "@"+storeId+"#"+productCode+":"+num;
			shopCartNum = 1;
		}

		String str="list="+shopCartList.toString()+"*num="+shopCartNum;
		String mallSystem = Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag()); //获取当前店铺所属系统（华扬orBanana）
		this.addCookie(UserConstant.SHOPCART, str, true, -1,Constants.MALL_TO_DOMAIN_POSTFIX.get(mallSystem));

		PrintWriter out=this.getResponse().getWriter();
		out.print(shopCartNum);
		
		return null;
	}
	
	/**
	 * 修改购物车中的cookie信息
	 * @return
	 * @throws Exception
	 */
	public String updateCartCookie() throws Exception{
		
		//获取产品code和数量
		String productCode = this.getRequest().getParameter("code");
		String num = this.getRequest().getParameter("shopNum");
		
		//获取已有的cookie中是否已有购物车信息
		Map<String, String> shopCart = getCookieValueToMap(UserConstant.SHOPCART,true);
		if(null!=shopCart && shopCart.containsKey("list") && shopCart.containsKey("num")){
			String shopCartList = shopCart.get("list");
			//判断这条产品信息是否存在(根据店铺标志和产品code)
			if(shopCartList.indexOf("#"+productCode)!=-1){
				int startNum=shopCartList.indexOf(":", shopCartList.indexOf("#"+productCode)); //数量之前索引
				int endNum = shopCartList.indexOf("@",shopCartList.indexOf("#"+productCode)); //数量之后索引
				String oldNum = ""; //获取之前该产品的数量
				if(endNum!=-1){
					oldNum = shopCartList.substring(startNum+1,endNum); 
				}else{
					oldNum = shopCartList.substring(startNum+1); 
				}
				String oldList = "#"+productCode+":"+oldNum; //获取之前该产品的信息
				String newList = "#"+productCode+":"+(Integer.parseInt(num)); //当前产品信息
				shopCartList = shopCartList.replace(oldList, newList); //将此条产品信息替换掉
				
				String str="list="+shopCartList.toString()+"*num="+Integer.parseInt(shopCart.get("num"));
				String mallSystem = Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag()); //获取当前店铺所属系统（华扬orBanana）
				this.addCookie(UserConstant.SHOPCART, str, true, -1,Constants.MALL_TO_DOMAIN_POSTFIX.get(mallSystem));
			}
		}
		return null;
	}
	
	/**
	 * 删除购物车cookie中某条产品信息
	 * @return
	 * @throws Exception
	 */
	public String delCartCookie() throws Exception{
		
		//获取要删除的产品code
		String productCode = this.getRequest().getParameter("code");
		
		//获取已有的cookie中是否已有购物车信息
		Map<String, String> shopCart = getCookieValueToMap(UserConstant.SHOPCART,true);
		if(null!=shopCart && shopCart.containsKey("list") && shopCart.containsKey("num")){
			String shopCartList = shopCart.get("list");
			//判断这条产品信息是否存在(根据店铺标志和产品code)
			if(shopCartList.indexOf("#"+productCode)!=-1){
				String temStr= shopCartList.substring(0, shopCartList.indexOf("#"+productCode)); //找到这条信息@位置
				int startNum=temStr.lastIndexOf("@");
				int endNum = shopCartList.indexOf("@",shopCartList.indexOf("#"+productCode)); //数量之后索引
				String delStr=""; //获取要删除的字符串
				if(endNum!=-1){
					delStr = shopCartList.substring(startNum,endNum); 
				}else{
					delStr = shopCartList.substring(startNum); 
				}
				shopCartList = shopCartList.replace(delStr, ""); //将此条产品信息替换掉
				
				Integer shopCartNum = Integer.parseInt(shopCart.get("num"))-1;
				String str="";
				if(0!=shopCartNum && !("").equals(shopCartList)){
					str="list="+shopCartList.toString()+"*num="+shopCartNum;
				}
				String mallSystem = Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag()); //获取当前店铺所属系统（华扬orBanana）
				this.addCookie(UserConstant.SHOPCART, str, true, -1,Constants.MALL_TO_DOMAIN_POSTFIX.get(mallSystem));
				
				PrintWriter out=this.getResponse().getWriter();
				out.print(shopCartNum);
			}
		}
		return null;
	}
	
	/**
	 * 获取购物车数量
	 * @return
	 * @throws Exception
	 */
	public String getShopCartNum() throws Exception{
		
		int shopCartNum=0;
		PrintWriter out=this.getResponse().getWriter();
		
		//判断用户是否登陆
		String uid;
		try {
			uid = this.getCookieValue(UserConstant.USER_ID, true);
		} catch (Exception e) {
			out.print(0);
			return null;
		}
		
		//未登陆
		if(uid==null||"".equals(uid.trim())){
			Map<String, String> shopCart = getCookieValueToMap(UserConstant.SHOPCART,true);
			if(null!=shopCart && shopCart.containsKey("list") && shopCart.containsKey("num")){
				shopCartNum = Integer.parseInt(shopCart.get("num").toString());	
			}
		//登陆
		}else{
			dto.put("storeFlag", this.getFlag());
			dto.put("userCode", uid);
			shopCartNum=(Integer)this.doService("OnlineMallService", "getShopCartNum", dto);	
		}
		
		
		out.print(shopCartNum);
		return null;
	}
	
	/**
	 * 获取国家及关联省列表
	 */
	@SuppressWarnings("unchecked")
	public void getAreas(){
		
		//获取封装好的国家及关联省列表
//		this.doService("IndexService", "getAreas", dto);
		List<Object[]> objList = (List<Object[]>)dto.get("objList");
		
		JSONArray jsonArray = JSONArray.fromObject(objList);
		
		//输出数据
		PrintWriter out = null;
		try {
			this.getResponse().setCharacterEncoding("utf-8");
			out = this.getResponse().getWriter();
			out.print(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(out!=null){
				out.close();	
			}
		}
		
	}
	
	/**
	 * 获取国家及关联省列表（如果是中国则显示中文，其他显示英文）
	 */
	@SuppressWarnings("unchecked")
	public void getCnAreas(){
		
		//获取国家
		List<LvArea> countryList=(List<LvArea>)this.doService("ShopCartService", "getContryList", dto);
		
		//为每个国家封装对应的省
		List<Object[]> country_provinceList = new ArrayList<Object[]>(); //一个国家对应多个省 0，国家   1，省列表
		if(null!=countryList && countryList.size()>0){
			for(LvArea country : countryList){
				
				//国家如果是中国时，则给英文字段赋予中文语言
				if(country.getId()==100023){
					//给国家赋予中文
					country.setNameen(country.getNamecn());
				}
				
				//获取国家对应的省
				dto.put("parentid",country.getId());
				List<LvArea> provinceList=(List<LvArea>)this.doService("UserCenterService", "getProvinces", dto);
				
				//为每个省封装对应的市
				List<Object[]> province_cityList = new ArrayList<Object[]>();//一个省对应多个市  0，省  1，市列表
				if(null!=provinceList && provinceList.size()>0){
					for(LvArea province : provinceList){
						
						//国家如果是中国时，则给英文字段赋予中文语言
						if(country.getId()==100023){
							province.setNameen(province.getNamecn());
						}
						
						//获取省对应的市
						dto.put("parentid",province.getId());
						List<LvArea> cityList=(List<LvArea>)this.doService("UserCenterService", "getProvinces", dto); //因为这个获取省的方法同样可以用于获取市，所以调用的是同一方法
						
						//国家如果是中国时，则给英文字段赋予中文语言
						if(country.getId()==100023){
							if(null!=cityList && cityList.size()>0){
								for(LvArea city : cityList){
									city.setNameen(city.getNamecn());
								}
							}
						}
						
						Object ob[] = new Object[2]; //0，省  1，市列表
						ob[0] = province;
						ob[1] = cityList;
						province_cityList.add(ob);
						
					}
				}
				
				Object obj[] = new Object[2]; //0，国家   1，省列表
				obj[0] = country;
				obj[1] = province_cityList;
				country_provinceList.add(obj);
			}
		}
		
		JSONArray jsonArray = JSONArray.fromObject(country_provinceList);
		
		//输出数据
		PrintWriter out = null;
		try {
			this.getResponse().setCharacterEncoding("utf-8");
			out = this.getResponse().getWriter();
			StringBuilder json = new StringBuilder("var areaData= ");
			json.append(jsonArray.toString());
			json.append(";");
			out.print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(out!=null){
				out.close();	
			}
		}
		
	}
}
