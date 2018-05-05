package com.lshop.web.group.action;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;
import com.gv.core.util.StringUtil;
import com.lshop.common.action.BaseAction;
import com.lshop.common.pojo.logic.LvArea;
import com.lshop.common.pojo.logic.LvCarriageSet;
import com.lshop.common.pojo.logic.LvEmailTpl;
import com.lshop.common.pojo.logic.LvGroupBuy;
import com.lshop.common.pojo.logic.LvGroupProperty;
import com.lshop.common.pojo.logic.LvOrder;
import com.lshop.common.pojo.logic.LvOrderAddress;
import com.lshop.common.pojo.logic.LvPaymentStyle;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.pojo.user.LvAccountAddress;
import com.lshop.common.util.Constants;
import com.lshop.common.util.StoreHelp;
import com.lshop.web.shopCart.service.ShopCartService;
import com.lshop.web.userCenter.UserConstant;

/**
 * 团购模块
 * @author zhengxue
 * @version 2.0 2012-08-08
 *
 */
@SuppressWarnings("serial")
@Controller("GroupManageAction")
@Scope("prototype")
public class GroupManageAction extends BaseAction {
	@Resource
	private ShopCartService shopCartService;
	
	private LvGroupBuy lvGroupBuy; //团购信息表
	private LvOrder lvOrder; //订单表
	private String groupCode; //团购号
	private Integer shopNum; //团购数量
	private LvAccountAddress lvAccountAddress; //收货地址
	private String oid;	

	/**
	 * 跳转至团购页面
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String toGroupBuy() throws ActionException{
		
		//用户评价默认选中标识
		String pageMark=this.getRequest().getParameter("pageMark");
		if(null!=pageMark && !("").equals(pageMark)){
			this.getRequest().setAttribute("pageMark", 1);
		}else{
			this.getRequest().setAttribute("pageMark", 0);
		}
		
		String groupCode=this.getRequest().getParameter("code");
		dto.put("storeFlag", this.getFlag());
		
		//获取当前店铺信息
		dto.put("storeFlag", this.getFlag());
		LvStore store = (LvStore)this.doService("StoreService", "getStoreByFlag", dto);
		this.getRequest().setAttribute("lvStore", store);
		
		//获取团购信息lv_group_buy
		dto.put("groupCode", groupCode);
		LvGroupBuy groupBuy=(LvGroupBuy)this.doService("GroupService", "getGroupByCode", dto);
		
		if(groupBuy!=null && (null!=groupBuy.getStoreId()&&groupBuy.getStoreId().equals(this.getFlag()))){
			//获取团购属性信息lv_group_property
			List<LvGroupProperty> groupPropertyList=(List<LvGroupProperty>)this.doService("GroupService", "getGroupPropertyList", dto);
			
			//获取产品信息lv_product
			dto.put("pcode", groupBuy.getProductCode());
			LvProduct product=(LvProduct)this.doService("ProductService", "getProductByCode", dto);
			
			//获取该产品的评论信息
			page.setNumPerPage(10);
			dto.setDefaultPage(page);
			List<Object[]> objList=(List<Object[]>)this.doService("ProductService", "getCommentsByCode", dto);
			page=(Pagination)dto.get("page");
			
			//获取时间差，用于倒计时
			Date curDate=new Date(System.currentTimeMillis());
			Long curTime=curDate.getTime();
			Long startTime=groupBuy.getStartTime().getTime();
			Long endTime=groupBuy.getEndTime().getTime();
			
			if(groupBuy.getStartTime().after(new Date())){ //活动还未开始，开始剩余时间
				Long beforeTime=startTime-curTime;
				this.getRequest().setAttribute("beforeTime", beforeTime);
			}else{ //活动已开始但还未结束，结束剩余时间
				Long afterTime=endTime-curTime;
				this.getRequest().setAttribute("afterTime", afterTime);
			}
			
			this.getRequest().setAttribute("objList", objList);
			this.getRequest().setAttribute("commentNum", page.getTotalCount());
			this.getRequest().setAttribute("pagination", page);
			this.getRequest().setAttribute("groupBuy", groupBuy);
			this.getRequest().setAttribute("groupPropertyList", groupPropertyList);
			this.getRequest().setAttribute("propertyNum", groupPropertyList.size());
			this.getRequest().setAttribute("product", product);
			
			//判断团购是否结束
			if(groupBuy.getStatus()==1&&groupBuy.getEndTime().after(new Date())&&groupBuy.getStartTime().before(new Date())){
				this.getRequest().setAttribute("mark", 1); //团购已开始未结束
			}else if(groupBuy.getStatus()==1&&groupBuy.getStartTime().after(new Date())){
				this.getRequest().setAttribute("mark", 2); //团购还未开始
			}else{
				this.getRequest().setAttribute("mark", 3); //团购已结束
			}
			
			return "group_index"; //提示此团购信息不存在
		}else{
			return "error"; //提示此团购信息不存在
		}

			
	}
	
	/**
	 * 保存团购数据
	 * 团购直接进入订单信息页面，不进入购物车列表页面
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String saveGroup() throws Exception{
		
		//获取团购信息
		dto.put("code", lvGroupBuy.getCode());
		LvGroupBuy myGroup=(LvGroupBuy)this.doService("GroupService", "getGroupBuyByCode", dto);
		
		
		//判断团购是否结束
		Date curDate=new Date();
		if(myGroup.getStatus()==1&&myGroup.getEndTime().after(curDate)){
			
			String uid=this.getCookieValue(UserConstant.USER_ID, true);
			
			//获取该用户的默认收货地址
			dto.put("userCode", uid);
			dto.put("storeFlag", this.getFlag());
			LvAccountAddress dAddress=(LvAccountAddress)this.doService("AccountAddressService", "getUserDefAddress", dto);
			
			
			//获取该用户的非默认地址列表，显示在前台页面上注意不要与默认收货地址重复
			List<LvAccountAddress> addressList=(List<LvAccountAddress>)this.doService("AccountAddressService", "getUserAddress", dto);
			this.getRequest().setAttribute("addressList", addressList);
			
			//如果没有默认收货地址，则默认为最后添加的一个
			if(dAddress==null){
				if(null!=addressList && addressList.size()>0){
					dAddress = addressList.get(0);
				}
			}
			
			//获取该店铺的支付方式
			dto.put("storeFlag", myGroup.getStoreId());
			List<LvPaymentStyle> paymentList=(List<LvPaymentStyle>)this.doService("ShopCartService", "getPaymentList", dto);
			this.getRequest().setAttribute("paymentList", paymentList);
			//获得用户店铺默认支付方式
			dto.put("userCode", uid);
			this.getRequest().setAttribute("dPayment", this.doService("AccountPaymentService", "getUserPaystyle", dto));
			//获取运费信息，默认运费是由默认地址的区域编号确定
			Float carriage=0f;
			if(null!=dAddress){
				carriage = shopCartService.getCarrige(dAddress, myGroup.getStoreId());
			}
			this.getRequest().setAttribute("carriage", new BigDecimal(Float.toString(carriage)).toString());
			
			//获取国家列表
			List<LvArea>contryList=(List<LvArea>)this.doService("ShopCartService", "getContryList", dto);
			this.getRequest().setAttribute("contryList", contryList);
			
			//获取当前店铺标志 
			dto.put("storeFlag", myGroup.getStoreId());
			LvStore store = (LvStore)this.doService("StoreService", "getStoreByFlag", dto);
			
			//获取团购产品信息
			dto.put("pcode", myGroup.getProductCode());
			LvProduct product=(LvProduct)this.doService("ProductService", "getProductByCode", dto);
			
			this.getRequest().setAttribute("groupBuy",myGroup);
			this.getRequest().setAttribute("product",product);
			this.getRequest().setAttribute("shopNum", shopNum);
			this.getRequest().setAttribute("amount", myGroup.getPresentPrice()*shopNum);
			this.getRequest().setAttribute("lvStore",store);
			this.getRequest().setAttribute("dAddress", dAddress);

			return "group_orderInfo";
		}else{
			this.getRequest().setAttribute("lvGroupBuy",myGroup);
			return "over"; //跳转到团购结束的提示页面
		}
	}
	
	/**
	 * 保存订单信息——团购中
	 * @return
	 */
	public String saveOrderForGroup() throws Exception{
		
		//获取团购信息
		dto.put("code", lvGroupBuy.getCode());
		LvGroupBuy myGroup=(LvGroupBuy)this.doService("GroupService", "getGroupBuyByCode", dto);
		
		//判断团购是否结束
		Date curDate=new Date();
		if(myGroup.getStatus()==1&&myGroup.getEndTime().after(curDate)){
			
			//获取登陆账户信息
			String uid=this.getCookieValue(UserConstant.USER_ID, true);
			dto.put("uid", uid);
			//获取选中的地址信息
			dto.put("addressCode", this.getRequest().getParameter("addressCode"));
			//获取先中的最佳收货时间
			dto.put("bestDeliveryValue", this.getRequest().getParameter("bestDeliveryValue"));
			//获取参数值
			dto.put("lvOrder", lvOrder);
			dto.put("lvGroupBuy", myGroup);
			dto.put("shopNum", this.getRequest().getParameter("shopNum"));
			this.doService("GroupService", "saveOrderForGroup", dto);
			
			//传递订单和收货地址信息
			LvOrder order=(LvOrder)dto.get("lvOrder");
			this.getRequest().setAttribute("lvOrder",order );
			this.getRequest().setAttribute("lvOrderAdress", (LvOrderAddress)dto.get("lvOrderAdress"));
			
			//给用户发送邮件，告知生成订单
			Map<String, String> user = getCookieValueToMap(UserConstant.USER_CENTER,true);
			dto.put("tplKey", "ORDER_CONFIRM_TEMP");
			dto.put("storeId", myGroup.getStoreId());
			LvEmailTpl lvEmailTpl = (LvEmailTpl) this.doService("lvEmailManageService","get", dto);
			if(lvEmailTpl!=null){
				//2014-4-3 如果注册的用户呢称为空,则用邮箱代替
				if (StringUtils.isBlank(user.get("nickname"))) {
					user.put("nickname", user.get("email"));
				}
				dto.put("mallFlag", Constants.STORE_TO_MALL_SYSTEM.get(myGroup.getStoreId())); //获取当前店铺所属系统（华扬orBanana）
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
		}else{
			this.getRequest().setAttribute("lvGroupBuy",myGroup);
			return "over"; //跳转到团购结束的提示页面
		}
	}

	/**
	 * 检验该用户之前是否团购过此产品
	 * @return
	 */
	public String checkGroupOrder() throws Exception{
		
		Integer flag=0; //用来标志是否团购过此产品，1表示团购过，0表示未团购过
		//获取登陆账户信息
		String uid="";
		Map<String, String> user = getCookieValueToMap(UserConstant.USER_CENTER,true);
		if (user != null && user.containsKey("id")) {
			uid=user.get("id");
		}
		String groupCode=this.getRequest().getParameter("groupCode");
		
		dto.put("uid", uid);
		dto.put("groupCode", groupCode);
		dto.put("storeFlag", this.getFlag());
		LvOrder order=(LvOrder)this.doService("GroupService", "checkGroupOrder", dto);
		if(order!=null){
			flag=1;
		}
		
		PrintWriter out=this.getResponse().getWriter();
		out.println(flag);
		return null;
	}
	

	/**
	 * 团购——添加收货地址
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

		return "toGroupOrderInfo";
	}
	
	/**
	 * 团购——编辑收货人信息
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
		
		return "toGroupOrderInfo";
	}
	
	public LvGroupBuy getLvGroupBuy() {
		return lvGroupBuy;
	}

	public void setLvGroupBuy(LvGroupBuy lvGroupBuy) {
		this.lvGroupBuy = lvGroupBuy;
	}

	public LvOrder getLvOrder() {
		return lvOrder;
	}

	public void setLvOrder(LvOrder lvOrder) {
		this.lvOrder = lvOrder;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String code) {
		groupCode = code;
	}

	public Integer getShopNum() {
		return shopNum;
	}

	public void setShopNum(Integer shopNum) {
		this.shopNum = shopNum;
	}

	public LvAccountAddress getLvAccountAddress() {
		return lvAccountAddress;
	}

	public void setLvAccountAddress(LvAccountAddress lvAccountAddress) {
		this.lvAccountAddress = lvAccountAddress;
	}

	public String getOid() {
		return oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}
}
