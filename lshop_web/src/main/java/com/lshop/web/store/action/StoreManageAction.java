package com.lshop.web.store.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.core.datastructure.page.Pagination;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.action.BaseAction;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.pojo.logic.LvShopProduct;
import com.lshop.common.pojo.logic.LvShopSubject;
import com.lshop.common.pojo.logic.LvSpecialtyStore;
import com.lshop.common.pojo.logic.LvSpecialtyStoreType;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.pojo.logic.LvStoreArea;
import com.lshop.common.util.Constants;
import com.lshop.common.util.StoreHelp;
import com.lshop.web.activity.service.ActivityGiftService;
import com.lshop.web.product.service.ProductService;
import com.lshop.web.userCenter.UserConstant;

/**
 * 商城模块
 * @author zhengxue
 * @version 1.0 2012-12-19
 *
 */
@SuppressWarnings("serial")
@Controller("StoreManageAction")
@Scope("prototype")
public class StoreManageAction extends BaseAction {
	
	
	@Resource
	private ProductService productService;
	
	@Resource 
	private ActivityGiftService activityGiftService; 
	
	
	/**
	 * 获取商城所有商品信息和栏目信息
	 */
	public void getShopProducts(){
		
		dto.put("storeFlag", this.getFlag());
		List<LvShopSubject> subjectList=(List<LvShopSubject>)this.doService("StoreService", "getShopSubjectList", dto);
		
		List<Object[]> allObjList = new ArrayList<Object[]>();
		if(null!=subjectList && subjectList.size()>0){
			for(LvShopSubject subject:subjectList){
				
				//获取该栏目所对就的商品信息
				dto.put("storeFlag", this.getFlag());
				dto.put("subjectType", subject.getCode());
				List<LvShopProduct> productList=(List<LvShopProduct>)this.doService("StoreService", "getShopProductList", dto);
				
				List<Object[]> objList = new ArrayList<Object[]>(); //存放商品加店铺信息
				for(LvShopProduct product:productList){
					//获取该商品所属店铺信息
					dto.put("storeFlag", product.getStoreId());
					LvStore store = (LvStore)this.doService("StoreService", "getStoreByFlag", dto);
					
					Object obj[] = new Object[2];
					obj[0] = product;
					obj[1] = store;
					objList.add(obj);
				}
				
				Object allObj[] = new Object[2];
				allObj[0] = subject;
				allObj[1] = objList;
				allObjList.add(allObj);
			}
		}
		
		JSONArray jsonArray = JSONArray.fromObject(allObjList);
		
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
	 * 商城中查看更多商品信息
	 * 商城首页商品信息列表，点击“更多”操作
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String showMoreProducts(){
		
		//根据分类code获取分类名称
		dto.put("subjectCode", this.getRequest().getParameter("subjectType"));
		dto.put("storeFlag", this.getFlag());
		LvShopSubject subject = (LvShopSubject)this.doService("StoreService", "getShopSubjectByCode", dto);
		
		//根据分类获取该分类下面所有的商品信息
		dto.put("subjectType", this.getRequest().getParameter("subjectType"));
		dto.put("storeFlag", this.getFlag());
		dto.setDefaultPage(page);
		page.setNumPerPage(16);
		//www默认显示8个
		if ("www".equals(this.getFlag())) {
			page.setNumPerPage(8);
		}
		String numPage = this.getRequest().getParameter("numPage");
		if (StringUtils.isNotBlank(numPage)) {
			page.setNumPerPage(Integer.valueOf(numPage).intValue());
		}
		this.page=(Pagination)this.doService("StoreService", "getShopProductListForPage", dto);

		//获取价格要转换的list
		List<LvShopProduct> productList=(List<LvShopProduct>) this.page.getList();
		if(null!=productList && productList.size()>0){
			for(LvShopProduct product : productList){
				//获取商家商品价格
				dto.put("pcode", product.getProductCode());
				dto.put("storeFlag", product.getStoreId()); //商家标识
				float price = productService.getProductPrice(dto);
				float orignalPrice = productService.getProductOrignalPrice(dto);
				product.setProductPrice(price);
				product.setOrignalPrice(orignalPrice);
				
				//判断当前商品是否存在赠品
				List acProductList=activityGiftService.findGiftByProductCode(product.getProductCode());
				if(ObjectUtils.isNotEmpty(acProductList)){
					product.setIsGift((short)1);
				}else{
					product.setIsGift((short)0);
				}
				
			}
		}
		page.setList(productList);
		
		
		
		this.getRequest().setAttribute("pagination", this.page);
		this.getRequest().setAttribute("productList", page.getList());
		this.getRequest().setAttribute("exhibitType", this.getRequest().getParameter("exhibitType")); //1,广告图 2，商品信息
		this.getRequest().setAttribute("subjectType", this.getRequest().getParameter("subjectType"));
		this.getRequest().setAttribute("subject", subject);
		
		//将国家城市封装进去
		List<Object[]> objList = new ArrayList<Object[]>();
		for(LvShopProduct product:(List<LvShopProduct>) page.getList()){
			//获取店铺信息
			dto.put("storeFlag", product.getStoreId());
			LvStore store = (LvStore)this.doService("StoreService", "getStoreByFlag", dto);
			
			Object obj[] = new Object[2];
			obj[0] = product;
			obj[1] = store;
			objList.add(obj);
		}
		
		this.getRequest().setAttribute("objList", objList);
		return "moreProductList";
	}
	/**
	 * 分页显示更多商品
	 * @return
	 */
	public String pageShowMoreProducts(){
		
		//根据分类code获取分类名称
		dto.put("subjectCode", this.getRequest().getParameter("subjectType"));
		dto.put("storeFlag", this.getFlag());
		LvShopSubject subject = (LvShopSubject)this.doService("StoreService", "getShopSubjectByCode", dto);
		
		//根据分类获取该分类下面所有的商品信息
		dto.put("subjectType", this.getRequest().getParameter("subjectType"));
		dto.put("storeFlag", this.getFlag());
		dto.setDefaultPage(page);
		page.setNumPerPage(16);
		//www默认显示8个
		if ("www".equals(this.getFlag())) {
			page.setNumPerPage(8);
		}
		String numPage = this.getRequest().getParameter("numPage");
		if (StringUtils.isNotBlank(numPage)) {
			page.setNumPerPage(Integer.valueOf(numPage).intValue());
		}
		this.page=(Pagination)this.doService("StoreService", "getShopProductListForPage", dto);
		
		this.getRequest().setAttribute("pagination", this.page);
		this.getRequest().setAttribute("productList", page.getList());
		this.getRequest().setAttribute("exhibitType", this.getRequest().getParameter("exhibitType")); //1,广告图 2，商品信息
		this.getRequest().setAttribute("subject", subject);
		
		//将国家城市封装进去
		List<Object[]> objList = new ArrayList<Object[]>();
		for(LvShopProduct product:(List<LvShopProduct>) page.getList()){
			//获取店铺信息
			dto.put("storeFlag", product.getStoreId());
			LvStore store = (LvStore)this.doService("StoreService", "getStoreByFlag", dto);
			
			Object obj[] = new Object[2];
			obj[0] = product;
			obj[1] = store;
			objList.add(obj);
		}
		
		this.getRequest().setAttribute("objList", objList);
		return "pageMoreProductList";
	}
	/**
	 * 搜索商城商品信息操作
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String searchProducts(){
		
		//获取当前商城标识
		String mallFlag=StoreHelp.getParentFlagByFlag(this.getFlag());
		mallFlag=mallFlag==null?this.getFlag():mallFlag;
		
		//根据关键字搜索商品信息
		dto.put("str", replaceBlank(this.getRequest().getParameter("str")));
		dto.put("storeFlag", this.getFlag());
		dto.put("mallFlag", mallFlag);
		dto.setDefaultPage(page);
		page.setNumPerPage(40);
		//www默认显示8个
		if ("www".equals(this.getFlag())) {
			page.setNumPerPage(8);
		}
		this.page=(Pagination)this.doService("StoreService", "getShopProductListForSearch", dto);
		
		//封装最新产品价格
		List<LvProduct> productList = new ArrayList();
		if(null!=page.getList() && page.getList().size()>0){
			for(LvProduct product:(List<LvProduct>)page.getList()){
				//获取商家商品价格
				dto.put("pcode", product.getCode());
				dto.put("storeFlag", product.getStoreId()); //商家标识
				float price = (Float)this.doService("ProductService", "getProductPrice", dto);
				product.setPrice(price);
				productList.add(product);
			
			}
		}
		page.setList(productList);
		
		this.getRequest().setAttribute("pagination", this.page);
		this.getRequest().setAttribute("productList", page.getList());
		this.getRequest().setAttribute("searchContent", this.getRequest().getParameter("str"));
		
		//将国家城市封装进去
		List<Object[]> objList = new ArrayList<Object[]>();
		if(null!=page.getList() && page.getList().size()>0){
			for(LvProduct product:(List<LvProduct>) page.getList()){
				//获取店铺信息
				dto.put("storeFlag", product.getStoreId());
				LvStore store = (LvStore)this.doService("StoreService", "getStoreByFlag", dto);
				
				Object obj[] = new Object[2];
				obj[0] = product;
				obj[1] = store;
				objList.add(obj);
			}
		}

		this.getRequest().setAttribute("objList", objList);
		
		return "searchResultList";
	}
	
	/**
	 * 搜索店铺信息操作
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String searchStores(){
		
//		getStoreAreas(this.getFlag());
		
		//获取当前商城标识
		String mallFlag=StoreHelp.getParentFlagByFlag(this.getFlag());
		mallFlag=mallFlag==null?this.getFlag():mallFlag;
		
		//根据关键字搜索商品信息
		dto.put("str", replaceBlank(this.getRequest().getParameter("str")));
		dto.put("storeFlag", this.getFlag());
		dto.put("mallFlag", mallFlag);
		dto.setDefaultPage(page);
		page.setNumPerPage(16);
		this.page=(Pagination)this.doService("StoreService", "getStoreListForSearch", dto);
		
		this.getRequest().setAttribute("pagination", this.page);
		this.getRequest().setAttribute("storeList", page.getList());
		this.getRequest().setAttribute("searchContent", this.getRequest().getParameter("str"));
		
		//获取洲
		List<LvStoreArea> continentList = (List<LvStoreArea>)this.doService("StoreService", "getContinentArea", dto);
		this.getRequest().setAttribute("continentList", continentList);
		
		return "shopList";
	}

	/**
	 * 跳转至专卖店页面
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String toSpecialStores(){
		
		//查询所有的专卖店分类
		dto.put("storeFlag", this.getFlag());
		List<LvSpecialtyStoreType> typeList = (List<LvSpecialtyStoreType>)this.doService("StoreService", "getSpecialStoreTypes", dto);
		
		List list=new ArrayList(); //存放专卖店分类及其对应的专卖店列表
		
		if(null!=typeList && typeList.size()>0){
			//获取每个分类所对应的专卖店列表
			for(LvSpecialtyStoreType storeType : typeList){
				dto.put("storeFlag", this.getFlag());
				dto.put("storeTypeCode", storeType.getCode());
				List<LvSpecialtyStore> storeList=(List<LvSpecialtyStore>)this.doService("StoreService", "getSpecialStoresByType", dto);
				//将分类对象及专卖店列表放在map里面
				HashMap map=new HashMap();
				map.put("storeType", storeType);
				map.put("storeList", storeList);
				list.add(map);
			}
		}
		
		this.getRequest().setAttribute("list", list);
		return "specialStoreList";
		
	}
	
	/**
	 * 跳转至店铺导航页面
	 * @return
	 */
	public String toShopList(){
		
		//获取当前商城标识
		String mallFlag=StoreHelp.getParentFlagByFlag(this.getFlag());
		mallFlag=mallFlag==null?this.getFlag():mallFlag;
		
		//点击国家或城市
		String continentCode = this.getRequest().getParameter("continentCode");
		String countryCode = this.getRequest().getParameter("countryCode");
		String cityCode = this.getRequest().getParameter("cityCode");	
		dto.put("continentCode", continentCode);
		dto.put("countryCode", countryCode);
		dto.put("cityCode", cityCode);

		dto.setDefaultPage(page);
		page.setNumPerPage(16);
		dto.put("mallFlag", mallFlag);
		this.page=(Pagination)this.doService("StoreService", "getStoreListForPage", dto);
		
		this.getRequest().setAttribute("pagination", this.page);
		this.getRequest().setAttribute("storeList", page.getList());
		
		//获取洲
		List<LvStoreArea> continentList = (List<LvStoreArea>)this.doService("StoreService", "getContinentArea", dto);
		this.getRequest().setAttribute("continentList", continentList);
		
		this.getRequest().setAttribute("continentCode", continentCode);
		this.getRequest().setAttribute("countryCode", countryCode);
		this.getRequest().setAttribute("cityCode", cityCode);
		
		this.getRequest().setAttribute("mark", "store");//mark有值：一般页面的分页  mark为空：搜店铺的分页
		
		return "shopList";
	}
	
	/**
	 * 获取店铺所有的区域信息，以及其所对应的店铺信息
	 */
	public void getStoreAreas(){
		
		//获取当前商城标识
		String storeFlag = this.getRequest().getParameter("storeFlag");
		String mallFlag=StoreHelp.getParentFlagByFlag(storeFlag);
		mallFlag=mallFlag==null?storeFlag:mallFlag;
		
		//获取封装好的店铺区域信息
		dto.put("mallFlag", mallFlag);
		this.doService("StoreService", "getStoreAreas", dto);
		Object[] obj = (Object[])dto.get("obj");
		
		JSONArray jsonArray = JSONArray.fromObject(obj);
		
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
	 * 保存已选择的店铺信息至cookie中
	 * @return
	 */
	public String saveCookieForStore() throws Exception{
		
		//获取数据
		String code = this.getRequest().getParameter("code");
		String name = this.getRequest().getParameter("name");
		String domainName = this.getRequest().getParameter("domainName");
		
		String str = "code="+code+"*name="+name+"*domainName="+domainName;
		String mallSystem = Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag()); //获取当前店铺所属系统（华扬orBanana）
		this.addCookie(UserConstant.STORE,str, true, 100000000,Constants.MALL_TO_DOMAIN_POSTFIX.get(mallSystem));

		return null;
		
	}
	
	/**
	 * 根据IP获取商城产品展示
	 * @return
	 */
	public String getProductsForIp(){  
		
		//获取IP地址
		HttpServletRequest request = this.getRequest();
	    String ip = request.getHeader("x-forwarded-for");      
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
	         ip = request.getHeader("Proxy-Client-IP");      
	    }      
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
	         ip = request.getHeader("WL-Proxy-Client-IP");      
	    }      
	    if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {      
	    	ip = request.getRemoteAddr();      
	    }  
	    
	    //将IP地址转换成数字类型
	    long ipData = ipToLong(ip);
	    
	    //获取lv_store_area中当前IP国家Code
	    dto.put("ipData", ipData);
	    dto.put("storeFlag", this.getFlag());
	    String countryCode = (String)this.doService("StoreService", "getCountryCodeByIp", dto);
	    
	    /******************************获取所有的商城商品信息（IP国家优先）******************************/
		//dto.put("storeFlag", this.getFlag());
		List<LvShopSubject> subjectList=(List<LvShopSubject>)this.doService("StoreService", "getShopSubjectList", dto);
		
		List<Object[]> allObjList = new ArrayList<Object[]>();
		if(null!=subjectList && subjectList.size()>0){
			for(LvShopSubject subject:subjectList){
				
				//获取该栏目所对就的商品信息(IP国家优先)
				dto.put("storeFlag", this.getFlag());
				dto.put("subjectType", subject.getCode());
				dto.put("countryCode", countryCode);
				this.doService("StoreService", "getShopProductsByCountry", dto);
				List<Object[]> objList = (List<Object[]>)dto.get("objList");
				
				//栏目下面有商品的时候才显示
				if(null!=objList && objList.size()>0){
					Object allObj[] = new Object[2];
					allObj[0] = subject;
					allObj[1] = objList;
					allObjList.add(allObj);
				}
			}
		}

		/******************************获取所有的推荐店铺信息（IP国家优先）******************************/
	    dto.put("countryCode", countryCode);
	    dto.put("storeFlag", this.getFlag());
	    List<LvStore> storeList = (List<LvStore>)this.doService("StoreService", "getCommandStoresByCountry", dto);
	    
	    //将商城首页的商品信息及推荐店铺信息输出
	    Object obj[] = new Object[2];
	    obj[0] = allObjList;
	    obj[1] = storeList;
		JSONArray jsonArray = JSONArray.fromObject(obj);
		
		//输出数据
		PrintWriter out = null;
		try {
			this.getResponse().setCharacterEncoding("utf-8");
			out = this.getResponse().getWriter();
			out.print(jsonArray);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(out!=null){
				out.close();
			}
		}
		
		return null;
	}

	/**
	 * 保存店铺信息至cookie
	 * @throws Exception
	 */
	public void saveShopCookie() throws Exception{
	
		//获取店铺信息
		dto.put("storeFlag", this.getFlag());
		LvStore store = (LvStore)this.doService("StoreService", "getStoreByFlag", dto);
		
		//保存店铺信息至cookie
		String str = "code="+store.getCode()+"*name="+store.getName()+"*domainName="+store.getDomainName();
		String mallSystem = Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag()); //获取当前店铺所属系统（华扬orBanana）
		this.addCookie(UserConstant.STORE,str, true, 100000000,Constants.MALL_TO_DOMAIN_POSTFIX.get(mallSystem));

	}
	
	
    //将127.0.0.1形式的IP地址转换成十进制整数，这里没有进行任何错误处理
    public static long ipToLong(String strIp){
        long[] ip = new long[4];
        //先找到IP地址字符串中.的位置
        int position1 = strIp.indexOf(".");
        int position2 = strIp.indexOf(".", position1 + 1);
        int position3 = strIp.indexOf(".", position2 + 1);
        //将每个.之间的字符串转换成整型
        ip[0] = Long.parseLong(strIp.substring(0, position1));
        ip[1] = Long.parseLong(strIp.substring(position1+1, position2));
        ip[2] = Long.parseLong(strIp.substring(position2+1, position3));
        ip[3] = Long.parseLong(strIp.substring(position3+1));
        return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
  }
	
	//去除字符串中的空格、回车、换行符、制表符
	public String replaceBlank(String str){
		String newStr = str;
		if(null!=str && !("").equals(str)){
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			newStr = m.replaceAll("");
		}
		return newStr;
	}
	
	public static void main(String args[]){
		String ip = "127.0.0.255";
		long num = ipToLong(ip);
		System.out.println(num);
	}
	
}
