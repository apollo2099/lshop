package com.lshop.web.shopCart.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.core.util.ObjectUtils;
import com.gv.core.util.StringUtil;
import com.lshop.common.action.BaseAction;
import com.lshop.common.activity.constant.ActivityConstant;
import com.lshop.common.activity.vo.ActivityLimitOrder;
import com.lshop.common.activity.vo.ActivityMac;
import com.lshop.common.constant.AppDataConstant;
import com.lshop.common.datastructure.ResultMsg;
import com.lshop.common.pojo.logic.LvAccountMac;
import com.lshop.common.pojo.logic.LvActivityGift;
import com.lshop.common.pojo.logic.LvArea;
import com.lshop.common.pojo.logic.LvEmailTpl;
import com.lshop.common.pojo.logic.LvOrder;
import com.lshop.common.pojo.logic.LvOrderAddress;
import com.lshop.common.pojo.logic.LvPaymentStyle;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.pojo.logic.LvProductLadder;
import com.lshop.common.pojo.logic.LvShopCart;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.pojo.logic.LvWesternInfo;
import com.lshop.common.pojo.user.LvAccountAddress;
import com.lshop.common.util.CodeUtils;
import com.lshop.common.util.Constants;
import com.lshop.common.util.DateJsonValueProcessor;
import com.lshop.common.util.FloatJsonValueProcessor;
import com.lshop.common.util.StoreHelp;
import com.lshop.web.activity.action.ActivityManageAction;
import com.lshop.web.activity.service.ActivityAppointProductService;
import com.lshop.web.activity.service.ActivityGiftService;
import com.lshop.web.activity.service.ActivityMacService;
import com.lshop.common.util.FloatUtils;
import com.lshop.web.activity.service.ActivityService;
import com.lshop.web.coupon.service.CouponService;
import com.lshop.web.mac.service.AccountMacService;
import com.lshop.web.onlineMall.service.OnlineMallService;
import com.lshop.web.product.vo.ProductActivityVo;
import com.lshop.web.shopCart.service.ShopCartService;
import com.lshop.web.shopCart.vo.OrderInfoVo;
import com.lshop.web.shopCart.vo.ShopCartItem;
import com.lshop.web.shopCart.vo.ShopCartVo;
import com.lshop.web.userCenter.UserConstant;

/**
 * 购物车模块
 * @author zhengxue
 * @since 2.0 2012-07-30
 *
 */
@SuppressWarnings("serial")
@Controller("ShopCartManageAction")
@Scope("prototype")
public class ShopCartManageAction extends BaseAction {
	private static final Log logger = LogFactory.getLog(ShopCartManageAction.class);
	private LvAccountAddress lvAccountAddress; //账户收货地址
	private LvOrder lvOrder; //订单表
	private LvOrderAddress lvOrderAddress; //收货地址
	private LvWesternInfo lvWesternInfo; //西联汇款信息表
	String myAddress;
	String myCoupon;
	/**
	 * 订单备注
	 */
	String orderRemark;
	Short payValue;
	Integer bestDeliveryValue;
	String shopFlag;
	private String oid;
	private String errorMsg;//错误信息
	/**
	 * 应付金额和金额币种
	 */
	String payCurrent;
	float orderPay;
	private String productCodeList;
	private String productCode;
	private String sourceUrl;
	
	@Resource
	private ActivityService activityService;
	@Resource
	private ActivityGiftService activityGiftService;
	@Resource 
	private ActivityAppointProductService activityAppointProductService;
	@Resource
	private AccountMacService accountMacService;
	@Resource
	private ActivityMacService activityMacService;
	@Resource
	private CouponService couponService;
	@Resource
	private ShopCartService shopCartService;
	@Resource
	private OnlineMallService onlineMallService;
	
	
	/**
	 * 删除购物车某条信息
	 * @return
	 */
	public String delCart() throws Exception{
		
		String uid=this.getCookieValue(UserConstant.USER_ID, true);
		String mallSystem = Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag()); //获取当前店铺所属系统（华扬orBanana）
		dto.put("userCode", uid);
		String code=this.getRequest().getParameter("code");
		dto.put("code", code);
		dto.put("mallSystem", mallSystem);
		Boolean flag=(Boolean)this.doService("OnlineMallService", "delCartByCode", dto);

		//将购物车内产品数量放在session中，便于在顶部的购物车信息旁边显示
		String userCode=this.getCookieValue(UserConstant.USER_ID, true); //获取登陆用户code
		dto.put("userCode", userCode);
		dto.put("storeFlag", this.getFlag());
		Integer shopCartNum=(Integer)this.doService("OnlineMallService", "getShopCartNum", dto);
		this.addCookie(UserConstant.SHOPCART_NUM, shopCartNum.toString(), true, -1,Constants.MALL_TO_DOMAIN_POSTFIX.get(mallSystem));
	
		PrintWriter out=this.getResponse().getWriter();
		if(flag){
			out.println(shopCartNum);
		}else{
			out.println(-1);
		}
		
		return null;
	}
	
	/**
		 * 批量删除购物车信息
		 * @return
		 */
		public String delManyCart() throws Exception{
			
			String str = this.getRequest().getParameter("str");
			String codes[]=str.split(";");
	
			String uid=this.getCookieValue(UserConstant.USER_ID, true);
			String mallSystem = Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag()); //获取当前店铺所属系统（华扬orBanana）
			Integer shopCartNum = 0;
			String shopCartList = "";
		
			if(null!=codes){
				
				//未登陆情况下
				if(null==uid || "".equals(uid)){
					Map<String, String> shopCart = getCookieValueToMap(UserConstant.SHOPCART,true);
					if(null!=shopCart && shopCart.containsKey("list") && shopCart.containsKey("num")){
						shopCartNum=Integer.parseInt(shopCart.get("num"));
						shopCartList = shopCart.get("list");
					}
				}
				
				for(int i=0; i<codes.length; i++){
					String code=codes[i];
					//判断用户是否登陆
					if(null!=uid && !"".equals(uid)){
						dto.put("userCode", uid);
						dto.put("code", code);
						dto.put("mallSystem", mallSystem);
						this.doService("OnlineMallService", "delCartByCode", dto);
					}else{
						//获取已有的cookie中是否已有购物车信息
						if(null!=shopCartList && null!=shopCartNum && !"".equals(shopCartList) && 0!=shopCartNum){
							//判断这条产品信息是否存在(根据店铺标志和产品code)
							if(shopCartList.indexOf("#"+code)!=-1){
								String temStr= shopCartList.substring(0, shopCartList.indexOf("#"+code)); //找到这条信息@位置
								int startNum=temStr.lastIndexOf("@");
								int endNum = shopCartList.indexOf("@",shopCartList.indexOf("#"+code)); //数量之后索引
								String delStr=""; //获取要删除的字符串
								if(endNum!=-1){
									delStr = shopCartList.substring(startNum,endNum); 
								}else{
									delStr = shopCartList.substring(startNum); 
								}
								shopCartList = shopCartList.replace(delStr, ""); //将此条产品信息替换掉
								
								shopCartNum = shopCartNum-1;
							}
						}
					}
				}
				
				if(null!=uid && !"".equals(uid)){
					//将购物车内产品数量放在session中，便于在顶部的购物车信息旁边显示
					String userCode=this.getCookieValue(UserConstant.USER_ID, true); //获取登陆用户code
					dto.put("userCode", userCode);
					dto.put("storeFlag", this.getFlag());
					Integer cartNum=(Integer)this.doService("OnlineMallService", "getShopCartNum", dto);
					this.addCookie(UserConstant.SHOPCART_NUM, cartNum.toString(), true, -1,Constants.MALL_TO_DOMAIN_POSTFIX.get(mallSystem));
	//				this.getRequest().getSession().setAttribute("shopCartNum",cartNum);
				}else{
	//				String myStr="list="+shopCartList.toString()+"*num="+shopCartNum;
					String myStr="";
					if(0!=shopCartNum && !("").equals(shopCartList)){
						myStr="list="+shopCartList.toString()+"*num="+shopCartNum;
					}
					this.addCookie(UserConstant.SHOPCART, myStr, true, -1,Constants.MALL_TO_DOMAIN_POSTFIX.get(mallSystem));
				}
	
			}
			
			return "getShopCartList";
		}

	/**
	 * 修改购物车中产品数量
	 * @return
	 * @throws Exception 
	 */
	public String changCartNum() throws Exception{
		String code=this.getRequest().getParameter("code");
		String shopNum=this.getRequest().getParameter("shopNum");
		String shopPrice = this.getRequest().getParameter("shopPrice");
		String mallSystem = Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag()); //获取当前店铺所属系统（华扬orBanana）
		String uid=this.getCookieValue(UserConstant.USER_ID, true);
		dto.put("userCode", uid);
		dto.put("mallSystem", mallSystem);
		dto.put("code", code);
		dto.put("shopNum", shopNum);
		dto.put("shopPrice", shopPrice);
		this.doService("OnlineMallService", "changCartNum", dto);
		return null;
	}

	//查看商品清单
	public String toShopList() throws Exception{
		String uid=this.getCookieValue(UserConstant.USER_ID, true);
		ShopCartVo shopCartVo = onlineMallService.getShopCartByStore(uid, shopFlag);
		//将购物车表信息与对应的产品信息整合，便于在页面显示
		List<Object[]> objList=new ArrayList<Object[]>(); //存放总列表信息
		if (ObjectUtils.isEmpty(shopCartVo) || ObjectUtils.isEmpty(shopCartVo.getItems())) {
			this.getRequest().setAttribute("flag", 3); //"该产品已下架或不存在！""Sorry, this product no longer exists or has been pulled off shelves!"
			return "over";
		} else {
			for (Iterator<ShopCartItem> iterator = shopCartVo.getItems().iterator(); iterator.hasNext();) {
				ShopCartItem item = iterator.next();
				Object[] obj=new Object[3]; //存放两个对象：0，购物车信息; 1,产品信息.2产品小计
				obj[0]=item.getShopCart();
				obj[1]=item.getProduct();
				obj[2]=FloatUtils.formatDouble(item.getSum(), AppDataConstant.PRICE_SACAL);
				objList.add(obj);
			}
			this.getRequest().setAttribute("objList",objList);
		}
		return "shopList";
	}
	
	/**
	 * 跳转至购物车——填写订单信息
	 * @return
	 */
	public String toOrderInfo() throws Exception{
		String uid=this.getCookieValue(UserConstant.USER_ID, true);
		HttpServletRequest request = this.getRequest();
		//获取订单提交页信息
		OrderInfoVo orderInfoVo = shopCartService.getOrderInfoVo(uid, shopFlag);
		if (null == orderInfoVo) {
			request.setAttribute("flag", 3); //"该产品已下架或不存在！""Sorry, this product no longer exists or has been pulled off shelves!"
			return "getShopCartList";
		}
		//购物车信息
		ShopCartVo shopCartVo = orderInfoVo.getShopCartVo();
		//将购物车表信息与对应的产品信息整合，便于在页面显示
		List<Object[]> objList=new ArrayList<Object[]>(); //存放总列表信息
		for (Iterator<ShopCartItem> iterator = shopCartVo.getItems().iterator(); iterator.hasNext();) {
			ShopCartItem item = iterator.next();
			Object[] obj=new Object[3]; //存放两个对象：0，购物车信息; 1,产品信息.2产品小计
			obj[0]=item.getShopCart();
			obj[1]=item.getProduct();
			obj[2]=FloatUtils.formatDouble(item.getSum(), AppDataConstant.PRICE_SACAL);
			objList.add(obj);
		}
		request.setAttribute("shopcartNum", shopCartVo.getTotalNum());
		request.setAttribute("objList",objList);
		request.setAttribute("allAmount", FloatUtils.formatDouble(shopCartVo.getTotalAmount(), AppDataConstant.PRICE_SACAL));
		request.setAttribute("allCouponPrice",0); //设置默认优惠金额为0
		request.setAttribute("addressList", orderInfoVo.getAddressList());
		request.setAttribute("dAddress", orderInfoVo.getdAddress());
		request.setAttribute("paymentList", orderInfoVo.getPaymentList());
		request.setAttribute("dPayment", orderInfoVo.getdPayment());
		request.setAttribute("contryList", orderInfoVo.getContryList());
		request.setAttribute("lvStore", orderInfoVo.getStore());
		request.setAttribute("carriage", orderInfoVo.getCarriage());
		request.setAttribute("totalAmount", orderInfoVo.getOrderAmount());
		request.setAttribute("couponList", orderInfoVo.getCouponList());
		return "orderInfo";
	}
	/**
	 * 跳转快速下单页面
	 * @return
	 * @throws Exception 
	 */
	public String toQuickOrder() throws Exception{
		if(logger.isInfoEnabled()){
			  logger.info("***** ShopCartManageAction.toQuickOrder() method begin*****");
		}
		String uid=this.getCookieValue(UserConstant.USER_ID, true);
		String s= this.getRequest().getParameter("sourceUrl");
		//根据产品查询输入mac就送活动信息
		ActivityMac activityMac= activityMacService.findByProduct(productCode);
		if(ObjectUtils.isEmpty(activityMac)){
			return "quickOrderOver";
		}
		
		//获取商城标识
		String mallFlag=StoreHelp.getParentFlagByFlag(this.getFlag());
		mallFlag=mallFlag==null?this.getFlag():mallFlag;
		
		//获取该用户的默认收货地址
		dto.put("userCode", uid);
		dto.put("storeFlag", this.getFlag());
		LvAccountAddress dAddress=(LvAccountAddress)this.doService("AccountAddressService", "getUserDefAddress", dto);
		
		
		//获取该用户的非默认地址列表，显示在前台页面上注意不要与默认收货地址重复
		List<LvAccountAddress> addressList=(List<LvAccountAddress>)this.doService("AccountAddressService", "getUserAddress", dto);
		this.getRequest().setAttribute("addressList", addressList);
		
		if(dAddress==null){
			//如果没有默认收货地址，则默认为最后添加的一个
			if(null!=addressList && addressList.size()>0){
				dAddress = addressList.get(0);
			}
		}
		//获取运费信息，默认运费是由默认地址的区域编号确定
		Float carriage=0f;
		if(null!=dAddress){
			carriage = shopCartService.getCarrige(dAddress, shopFlag);
		}
		this.getRequest().setAttribute("carriage", new BigDecimal(Float.toString(carriage)).toString());
		
		//获取该店铺的支付方式
		dto.put("storeFlag", shopFlag);
		List<LvPaymentStyle> paymentList=(List<LvPaymentStyle>)this.doService("ShopCartService", "getPaymentList", dto);
		this.getRequest().setAttribute("paymentList", paymentList);
		//获得用户店铺默认支付方式
		dto.put("userCode", uid);
		this.getRequest().setAttribute("dPayment", this.doService("AccountPaymentService", "getUserPaystyle", dto));
		
		//获取当前店铺标志 
		dto.put("storeFlag", shopFlag);
		LvStore store = (LvStore)this.doService("StoreService", "getStoreByFlag", dto);
		

		
		//获取对应的产品信息
		dto.clear();
		dto.put("storeFlag", shopFlag);
		dto.put("pcode", productCode);
		LvProduct product=(LvProduct)this.doService("ProductService", "getProductByCode", dto);

		
		BigDecimal allAmount = new   BigDecimal(Float.toString(0f)); //存放总金额
		
		if(ObjectUtils.isNotEmpty(uid)&&ObjectUtils.isNotEmpty(product)){
			//查询用户兑换mac情况
			LvAccountMac  daccountMac = accountMacService.findByUserCode(uid);
			this.getRequest().setAttribute("daccountMac", daccountMac);
			
			//默认是原价
			LvShopCart cart=new LvShopCart();
			cart.setShopPrice(product.getPrice());

			
			Boolean flag=true;
			//判断是否启用活动价格，如果启用，则显示活动价格
			if(product.getIsActivity()!=null&&product.getIsActivity()==1){
				dto.put("pcode", product.getCode());
				ProductActivityVo ac=(ProductActivityVo)this.doService("ProductService", "getActivityByCode", dto);
				if (ObjectUtils.isNotEmpty(ac)) {
					cart.setShopPrice(ac.getActivityPrice());
					flag=false;
				}
			}
			
			//如果没有使用优惠价格，则判断是否启用阶梯价格
			if(flag){
				if(product.getIsLadder()!=null&&product.getIsLadder()==1){
					dto.put("productCode", cart.getProductCode());
					dto.put("pnum",cart.getShopNum());
					LvProductLadder ladder=(LvProductLadder)this.doService("OnlineMallService", "getLadderByPnum", dto);
					//如果启用阶梯价格，则产品价格显示为阶梯价
					if(null!=ladder&&null!=ladder.getPrice()){
						cart.setShopPrice(ladder.getPrice());
					}
				}
			}
			
			//获取商品总金额
			float price=cart.getShopPrice();
			Integer num=1;
		
			BigDecimal   b1   =   allAmount;   
	        BigDecimal   b2   =   new   BigDecimal(Float.toString(price));  
	        BigDecimal   b3   =   new   BigDecimal(Integer.toString(num));
	        allAmount = b2.multiply(b3).add(b1); 
			
	        
	        //验证购物车是否存在，防止刷新页面重复提交
	        LvShopCart shopCart= onlineMallService.findShopCartByCode(productCode, uid, shopFlag);
	        if(ObjectUtils.isEmpty(shopCart)){

	        	//拼装购物车信息
				cart.setProductCode(productCode);
				cart.setUserCode(uid);
				cart.setCode(CodeUtils.getCode());
				cart.setShopNum(1);
				cart.setStoreId(shopFlag);
				cart.setMallFlag(mallFlag);
				
		        //保存购物车信息
		        dto.put("storeFlag", shopFlag);
		        dto.put("product", product);
		    	dto.setDefaultPo(cart);
				this.doService("OnlineMallService", "saveShopCart", dto);
				
				//将购物车内产品数量放在session中，便于在顶部的购物车信息旁边显示
				dto.put("storeFlag", shopFlag);
				dto.put("userCode", uid);
				Integer shopCartNum=(Integer)this.doService("OnlineMallService", "getShopCartNum", dto);
				String mallSystem = Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag()); //获取当前店铺所属系统（华扬orBanana）
				this.addCookie(UserConstant.SHOPCART_NUM, shopCartNum.toString(), true, -1,Constants.MALL_TO_DOMAIN_POSTFIX.get(mallSystem));
	        }
		}
		

		BigDecimal b = new   BigDecimal(Float.toString(carriage));
		this.getRequest().setAttribute("totalAmount",allAmount.add(b).toString());
		this.getRequest().setAttribute("allCouponPrice",0); //设置默认优惠金额为0
		this.getRequest().setAttribute("lvStore",store);
		this.getRequest().setAttribute("dAddress", dAddress);
		this.getRequest().setAttribute("product",product);
		this.getRequest().setAttribute("activityMac",activityMac);
		this.getRequest().setAttribute("sourceUrl",sourceUrl);
		
		
		if(logger.isInfoEnabled()){
			  logger.info("***** ShopCartManageAction.toQuickOrder() method end*****");
		}
		return "quickOrder";
	}
	
	/**
	 * 获取运费
	 * 根据用户选择的送货地址，动态显示运费
	 * @return
	 * @throws Exception
	 */
	public String getCarriage(){
		Integer deliveryId=Integer.parseInt(this.getRequest().getParameter("deliveryId"));
		try {
			PrintWriter out=this.getResponse().getWriter();
			out.println(shopCartService.getDeliverCarrigage(shopFlag, deliveryId));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 删除某条常用地址
	 * @return
	 * @throws Exception 
	 */
	public String delAddress() throws Exception{
		
		String code=this.getRequest().getParameter("code");
		dto.put("addressCode", code);
		String userCode = this.getCookieValue(UserConstant.USER_ID, true);
		dto.put("userCode", userCode);
		Boolean flag=(Boolean)this.doService("AccountAddressService", "delAddress", dto);
		
		try {
			PrintWriter out=this.getResponse().getWriter();
			out.println(flag);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 添加常用地址
	 * @return
	 * @throws Exception
	 */
	public String addAddress() throws Exception{
		String userCode = this.getCookieValue(UserConstant.USER_ID, true);
		lvAccountAddress.setStoreId(this.getFlag());
		dto.put("userCode", userCode);
		dto.put("addr", lvAccountAddress);
		dto.put("setDefault", true);
		this.doService("AccountAddressService", "addAddress", dto);
		return "toOrderInfo";
	}
	
	public String addAddressQuick() throws Exception{
		String userCode = this.getCookieValue(UserConstant.USER_ID, true);
		lvAccountAddress.setStoreId(this.getFlag());
		dto.put("userCode", userCode);
		dto.put("addr", lvAccountAddress);
		dto.put("setDefault", true);
		this.doService("AccountAddressService", "addAddress", dto);
		this.getRequest().setAttribute("sourceUrl",sourceUrl);
		return "toQuickOrder";
	}
	
	
	/**
	 * 设置店铺默认收货地址
	 * @throws Exception
	 */
	public void setDefaultAddress() throws Exception {
		String userCode = this.getCookieValue(UserConstant.USER_ID, true);
		dto.put("userCode", userCode);
		dto.put("addressCode", myAddress);
		this.doService("AccountAddressService", "setDefAddress", dto);
		
	}
	
	/**
	 * 异步添加地址，并且设置店铺默认收货地址
	 * @throws Exception
	 */
	public String asynAddAddress() throws Exception{
		String userCode = this.getCookieValue(UserConstant.USER_ID, true);
		lvAccountAddress.setStoreId(this.getFlag());
		dto.put("userCode", userCode);
		dto.put("addr", lvAccountAddress);
		dto.put("setDefault", true);
		String addressCode=(String) this.doService("AccountAddressService", "addAddress", dto);
		
		PrintWriter out = null;
		try {
			this.getResponse().setContentType("text/html;charset=UTF-8");   
			this.getResponse().setCharacterEncoding("UTF-8");
		    out=this.getResponse().getWriter();
		    String jsonTmp = JSONObject.fromObject(addressCode).toString();
		    logger.info("json_message"+jsonTmp);
			out.print(jsonTmp );
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	/**
	 * 返回收货人信息Json数据
	 * @throws Exception
	 */
	public void getAddressJson() throws Exception {
		String userCode = this.getCookieValue(UserConstant.USER_ID, true);
		dto.put("userCode", userCode);
		dto.put("addressCode", myAddress);
		String djson = JSONObject.fromObject( this.doService("AccountAddressService", "getAddressByCode", dto)).toString();
		PrintWriter writer = this.getResponse().getWriter();
		this.getResponse().setContentType("application/json;charset=UTF-8");
		writer.print(djson);
		writer.close();
	}
	/**
	 * 编辑收货人信息
	 * @return
	 * @throws Exception
	 */
	public String editAddress() throws Exception {
		String userCode = this.getCookieValue(UserConstant.USER_ID, true);
		lvAccountAddress.setStoreId(this.getFlag());
		dto.put("userCode", userCode);
		dto.put("addr", lvAccountAddress);
		this.doService("AccountAddressService", "editAddress", dto);
		this.doService("AccountAddressService", "setDefAddress", dto);
		return "toOrderInfo";
	}
	/**
	 * 设置用户店铺默认支付方式
	 * @throws Exception 
	 */
	public void setDefaultPayment() throws Exception {
		String userCode = this.getCookieValue(UserConstant.USER_ID, true);
		Integer payment = Integer.valueOf(payValue);
		dto.put("userCode", userCode);
		dto.put("payment", payment);
		dto.put("storeFlag", shopFlag);
		this.doService("AccountPaymentService", "MergePayment", dto);
		String djson = JSONObject.fromObject( this.doService("AccountPaymentService", "getUserPaystyle", dto)).toString();
		PrintWriter writer = this.getResponse().getWriter();
		this.getResponse().setContentType("application/json;charset=UTF-8");
		writer.print(djson);
		writer.close();
	}
	
	/**
	 * 验证优惠码
	 * @return
	 * @throws Exception 
	 */
	public String checkCouponCode() throws Exception{
		String userCode = this.getCookieValue(UserConstant.USER_ID, true);
		dto.put("userCode", userCode);
		dto.put("couponCode", myCoupon);
		dto.put("shopFlag", shopFlag);
		ResultMsg msg = (ResultMsg) this.doService("ShopCartService", "checkUserCouponCode", dto);
		
		PrintWriter out = null;
		try {
			out=this.getResponse().getWriter();
			this.getResponse().setContentType("application/json;charset=UTF-8");
			JsonConfig config = new JsonConfig();  
            config.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy年M月d日"));  
            config.registerJsonValueProcessor(Float.class, new FloatJsonValueProcessor());
			String json = JSONObject.fromObject(msg, config).toString();
			out.print(json);
		} catch (IOException e) {
		  e.getMessage(); 
		} finally{
			if(out!=null){
			   out.close();	
			}
		}
		return null;
	}
	/**
	 * 保存订单
	 * @return
	 * @throws Exception
	 */
	public String saveOrder() throws Exception{
		dto.put("storeFlagWeb", this.getFlag());
		//获取登陆账户信息
		String uid=this.getCookieValue(UserConstant.USER_ID, true);
		dto.put("uid", uid);
		//获取选中的地址信息
		dto.put("addressCode", this.getRequest().getParameter("addressCode"));
		//获取先中的最佳收货时间
		dto.put("bestDeliveryValue", this.getRequest().getParameter("bestDeliveryValue"));
		//获取支付方式
		dto.put("paymethod", Integer.valueOf(lvOrder.getPaymethod()));
		//获取优惠券码
		dto.put("couponCode", this.getRequest().getParameter("couponCode"));
		//mac码
		dto.put("mac", this.getRequest().getParameter("mac"));
		//mac优惠金额
		dto.put("macAmount", this.getRequest().getParameter("macAmount"));
		
		//比较订单金额是否发生变化
		dto.put("storeFlag", shopFlag);
		//订单留言
		dto.put("orderRemark", orderRemark);
		
		//保存订单
		dto.put("storeFlag", shopFlag);
		dto.put("userCode", uid);
		dto.put("prodAmount", this.getRequest().getParameter("prodAmount"));
		dto.put("carriage", this.getRequest().getParameter("carriage"));
		ResultMsg resultMsg = (ResultMsg) this.doService("ShopCartService", "saveOrder", dto);
		if (resultMsg.isSuccess()) {
			lvOrder = (LvOrder) resultMsg.getReObj();
		} else {
			errorMsg = URLEncoder.encode(resultMsg.getMsg(),"utf-8");
			return "toOrderInfo";
		}
		
		//更新购物车个数，显示在顶部
		dto.put("userCode", uid);
		dto.put("storeFlag", shopFlag);
		Integer shopCartNum=(Integer)this.doService("OnlineMallService", "getShopCartNum", dto);
		String mallSystem = Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag()); //获取当前店铺所属系统（华扬orBanana）
		this.addCookie(UserConstant.SHOPCART_NUM, shopCartNum.toString(), true, -1,Constants.MALL_TO_DOMAIN_POSTFIX.get(mallSystem));
		
		//给用户发送邮件，告知生成订单
		Map<String, String> user = getCookieValueToMap(UserConstant.USER_CENTER,true);
		dto.put("tplKey", "ORDER_CONFIRM_TEMP");
		dto.put("storeId", shopFlag);
		LvEmailTpl lvEmailTpl = (LvEmailTpl) this.doService("lvEmailManageService","get", dto);
		if(lvEmailTpl!=null){
			//2014-4-3 如果注册的用户呢称为空,则用邮箱代替
			if (StringUtils.isBlank(user.get("nickname"))) {
				user.put("nickname", user.get("email"));
			}
			dto.put("mallFlag", Constants.STORE_TO_MALL_SYSTEM.get(shopFlag)); //获取当前店铺所属系统（华扬orBanana）
			dto.put("userEmail", user.get("email"));
			String title=lvEmailTpl.getEnTitle().replace("{relname}",user.get("nickname"));
			String body=(lvEmailTpl.getEn() + lvEmailTpl.getZh()).replace("{relname}",user.get("nickname")).replace("{oid}", lvOrder.getOid());
			body=body.replace("{createtime}", StringUtil.dateFormat(new Date(), "yyyy-MM-dd HH:mm:ss"));
			body=body.replace("{sendtime}", StringUtil.dateFormat(new Date(), "yyyy-MM-dd HH:mm:ss"));
			dto.put("title", title);
			dto.put("content", body);
		    doService("emailSendService", "sendEmailNotice", dto);
		}
		
		oid = lvOrder.getOid();
		return "orderSuccess"; //shopCart_oidSuccess.jsp 订单生成成功页面
	
	}
	
	/**
	 * 保存西联汇款信息
	 * @return
	 */
	public String saveWesternInfo(){

		dto.setDefaultPo(lvWesternInfo);
		this.doService("ShopCartService", "saveWesternInfo", dto);
		
		Integer flag=dto.getAsInteger("flag");
		if(flag==1){ //表示重复提交
			this.getRequest().setAttribute("msg", "您已提交西聯匯款信息，不能重複提交！"); //根据msg是否为空，在JSP页面写提示消息，跟这里的提示消息无关
			return "westernInfo";
		}else{
			this.getRequest().setAttribute("oid",lvWesternInfo.getOid() );
			this.getRequest().setAttribute("shopFlag",lvWesternInfo.getStoreId() );
			oid = lvWesternInfo.getOid();
			return "westernSuccess";
		}
	}
	
	/**
	 * 测试支付时数量改变
	 * 用于支付团购时已购买数量增加，及支付限量时剩余数量减少
	 */
	public void test(){
		String oid=this.getRequest().getParameter("oid");
		dto.put("oid", oid);
		dto.put("storeFlag", this.getFlag());
		this.doService("ShopCartService", "changeCount", dto);
	}
	
	/**
	 * 针对活动结束情况，跳转入支付页面（包括西联汇款、上门安装及网上支付情况）
	 * @return
	 */
	public String toContinuePay(){
	
//		dto.put("storeFlag", this.getFlag());
		String oid=this.getRequest().getParameter("oid");
		dto.put("oid", oid);
		this.doService("UserOrderService", "toPay", dto);
		
		//传递订单和收货地址信息
		LvOrder order=(LvOrder)dto.get("lvOrder");
		this.getRequest().setAttribute("lvOrder",order );
		this.getRequest().setAttribute("lvOrderAdress", (LvOrderAddress)dto.get("lvOrderAdress"));
		
		if(order.getPaymethod()==4){ //西联汇款
			return "toWesternInfo";
		}else if(order.getPaymethod()==7){ //上门安装
			return "installSuccess";
		}else{ //网上支付
			this.getRequest().setAttribute("mark", 1); //mark:1,表示支付成功后跳转到购物车支付成功页面；2，表示支付成功后跳转到用户中心支付成功页面
			return "payMothodCode";
		}
	}
	
	/**
	 * 获取阶梯价，并在购物车列表中及时显示
	 * @param dto
	 * @return
	 */
	public String getPriceForLadder() throws Exception{
		
		//获取产品信息
		dto.put("id", this.getRequest().getParameter("pid"));
		LvProduct product=(LvProduct)this.doService("ProductService", "getProductById", dto);
		
		float price=product.getPrice();
		//判断是否启用阶梯价格，如果启用，则显示阶梯价格
		if(product.getIsLadder()!=null&&product.getIsLadder()==1){
			dto.put("productCode", product.getCode());
			dto.put("pnum",this.getRequest().getParameter("shopNum"));
			dto.put("storeFlag", product.getStoreId());
			LvProductLadder ladder=(LvProductLadder)this.doService("ProductService", "getLadderByPnum", dto);
			//如果启用阶梯价格，则产品价格显示为阶梯价
			if(null!=ladder&&null!=ladder.getPrice()){
				price=ladder.getPrice();
			}
		}
		
		PrintWriter out=this.getResponse().getWriter();
		out.print(price);
		return null;
	}
	
	/**
	 * 跳转至支付页面
	 * @return
	 */
	public String toPayOrder(){
	
		String oid=this.getRequest().getParameter("oid");
		dto.put("oid", oid);
		this.doService("UserOrderService", "toPay", dto);
		
		//传递订单和收货地址信息
		lvOrder=(LvOrder)dto.get("lvOrder");
		lvOrderAddress=(LvOrderAddress)dto.get("lvOrderAdress");
		this.getRequest().setAttribute("lvOrder", lvOrder);
		this.getRequest().setAttribute("lvOrderAdress", lvOrderAddress);
		this.getRequest().setAttribute("mark", 1); //mark:1,表示支付成功后跳转到购物车支付成功页面；2，表示支付成功后跳转到用户中心支付成功页面
		if(lvOrder.getPaymethod()==4){ //西联汇款
			return "toWesternInfo";
		}else if(lvOrder.getPaymethod()==7){ //上门安装
			return "installSuccess";
		}else{ //网上支付
			//根据订单信息查询支付方式详情
			dto.put("payValue", lvOrder.getPaymethod());
			dto.put("storeFlag", lvOrder.getStoreId());
			LvPaymentStyle lvPaymentStyle=(com.lshop.common.pojo.logic.LvPaymentStyle) this.doService("PayService", "getLvPaymentStyle", dto);
			this.getRequest().setAttribute("lvPaymentStyle", lvPaymentStyle);
			
			//判断是否新的支付方式(支付宝国际卡)
//			if(Constants.PAY_METHOD_ALIPAY_INITIAL==lvPaymentStyle.getPayValue()){
//				String checkoutId;
//				try {
//					checkoutId = alipayInitialService.prepareCheckout(oid);
//					if(ObjectUtils.isNotEmpty(checkoutId)){
//						this.getRequest().setAttribute("checkoutId",checkoutId); 
//						return "pay";
//					}else {
//						return "e404";
//					}
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
			
			
			
			this.getRequest().setAttribute("mark", 1); //mark:1,表示支付成功后跳转到购物车支付成功页面；2，表示支付成功后跳转到用户中心支付成功页面
			return "payMothodCode";
		}

	}
	
	/**
	 * 订单下单成功后提示应付金额及参加订单活动信息
	 * @throws Exception 
	 */
	public void orderSuccess() throws Exception {
		dto.put("oid", oid);
		LvOrder order = (LvOrder) this.doService("UserOrderService", "getOrderByCode", dto);
		String message; 
		if (ObjectUtils.isEmpty(order)) {
			message = "{ \"res\": \"fail\" }";
		} else {
			//获取订单活动信息
			List<ActivityLimitOrder> limitOrders = shopCartService.getOrderActivities(oid);
			if (ObjectUtils.isEmpty(limitOrders)) {
				message = "{ \"res\": \"success\", \"price\":\""+order.getCurrency()+" "+order.getTotalPrice()+"\" }";
			} else {
				message = "{ \"res\": \"success\", \"price\":\""+order.getCurrency()+" "+order.getTotalPrice()+"\"," +
						" \"activity\": "+JSONArray.fromObject(limitOrders).toString()+" }";
			}
		}
		this.getResponse().setContentType("application/json;charset=UTF-8");
		PrintWriter out = this.getResponse().getWriter();
		out.print(message);
		out.close();
	}
	
	/**
	 * 返回订单满就送优惠券活动和订单满就送抽奖机会活动提示
	 * @return
	 * @throws Exception 
	 */
	public String getOrderActivityTip() throws Exception {
		//订单满就送优惠券
		this.getRequest().setAttribute("activity", activityService.getActivityLimitOrder(shopFlag, orderPay));
		//订单满就送抽奖机会
		this.getRequest().setAttribute("activityLottery", activityService.getActivityLimitOrderLottery(shopFlag, orderPay));
		
		//指定购买商品就送赠品信息
		if(ObjectUtils.isNotEmpty(productCodeList)){
			String [] arrStr=productCodeList.split(",");
			//根据商品编码查询符合条件的购买指定商品就送的活动信息
			List list= activityAppointProductService.findActivityByProductCode(arrStr);
			List activityAppontList = findGiftByProductCode(arrStr, list);
			//赠品去重
			this.getRequest().setAttribute("activityAppontList",activityAppontList);
		}
		return "orderActivityTip";
	}

	/**
	 * 
	 * @Method: findGiftByProductCode 
	 * @Description:  [组装活动赠品信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-17 下午3:14:53]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-17 下午3:14:53]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param productStr 商品编码数组
	 * @param list 
	 * @return List
	 */
	private List findGiftByProductCode(String[] productStr, List list) {
		List activityList=new ArrayList();
		if(ObjectUtils.isNotEmpty(list)){
			for (int i = 0; i < list.size(); i++) {
			    Map activityMap=(Map) list.get(i);
			    if(ObjectUtils.isNotEmpty(activityMap.get("givenTypeNum"))){
			    	Short givenTypeNum=Short.parseShort(activityMap.get("givenTypeNum").toString());
			    	String activityCode=(String) activityMap.get("activityCode");
			    	if(givenTypeNum==ActivityConstant.GIVEN_TYPE_NUM_GIFT){
			    		//根据活动编码和商品编码查询赠品信息
			    		List<LvActivityGift> activityGift=activityGiftService.findGiftByProductCodeStr(activityCode,productStr);
			    		activityMap.put("activityGift",activityGift);
			    	}
			    	activityList.add(activityMap);
			    }
			}
		}
		return activityList;
	}
	
	public LvAccountAddress getLvAccountAddress() {
		return lvAccountAddress;
	}

	public void setLvAccountAddress(LvAccountAddress lvAccountAddress) {
		this.lvAccountAddress = lvAccountAddress;
	}

	public LvOrder getLvOrder() {
		return lvOrder;
	}

	public void setLvOrder(LvOrder lvOrder) {
		this.lvOrder = lvOrder;
	}

	public LvWesternInfo getLvWesternInfo() {
		return lvWesternInfo;
	}

	public void setLvWesternInfo(LvWesternInfo lvWesternInfo) {
		this.lvWesternInfo = lvWesternInfo;
	}

	public String getMyAddress() {
		return myAddress;
	}

	public void setMyAddress(String myAddress) {
		this.myAddress = myAddress;
	}

	public String getMyCoupon() {
		return myCoupon;
	}

	public void setMyCoupon(String myCoupon) {
		this.myCoupon = myCoupon;
	}

	public String getOrderRemark() {
		return orderRemark;
	}

	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}

	public Short getPayValue() {
		return payValue;
	}

	public void setPayValue(Short payValue) {
		this.payValue = payValue;
	}

	public Integer getBestDeliveryValue() {
		return bestDeliveryValue;
	}

	public void setBestDeliveryValue(Integer bestDeliveryValue) {
		this.bestDeliveryValue = bestDeliveryValue;
	}

	public LvOrderAddress getLvOrderAddress() {
		return lvOrderAddress;
	}

	public void setLvOrderAddress(LvOrderAddress lvOrderAddress) {
		this.lvOrderAddress = lvOrderAddress;
	}

	public String getShopFlag() {
		return shopFlag;
	}

	public void setShopFlag(String shopFlag) {
		this.shopFlag = shopFlag;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	public String getPayCurrent() {
		return payCurrent;
	}

	public void setPayCurrent(String payCurrent) {
		this.payCurrent = payCurrent;
	}

	public float getOrderPay() {
		return orderPay;
	}

	public void setOrderPay(float orderPay) {
		this.orderPay = orderPay;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getProductCodeList() {
		return productCodeList;
	}

	public void setProductCodeList(String productCodeList) {
		this.productCodeList = productCodeList;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getSourceUrl() {
		return sourceUrl;
	}

	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}
	

}
