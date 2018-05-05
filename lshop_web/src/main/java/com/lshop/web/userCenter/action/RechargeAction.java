package com.lshop.web.userCenter.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.action.BaseAction;
import com.lshop.common.pojo.logic.LvArea;
import com.lshop.common.pojo.logic.LvPaymentStyle;
import com.lshop.common.pojo.logic.LvRecharge;
import com.lshop.common.pojo.logic.LvVbPlans;
import com.lshop.common.pojo.user.LvAccountAddress;
import com.lshop.common.util.Constants;
import com.lshop.common.util.I18nCache;
import com.lshop.common.util.StoreHelp;
import com.lshop.web.userCenter.UserConstant;
import com.lshop.web.userCenter.pojo.BossUser;

/**
 * 充值信息管理
 * 
 * @author tangd
 * 
 */
@Scope("prototype")
@Controller("RechargeAction")
public class RechargeAction extends BaseAction {
	private LvRecharge recharge = new LvRecharge();
	private String year;
	private String type;
	private String validCode;
	private Integer paymethod;
	private String rnum;
	private Integer recordType;
	private String email;
    private LvAccountAddress lvAccountAddress;
	/**
	 * 在线下订单充值
	 * 
	 * @return
	 * @throws ActionException
	 */
	public String doOrder() throws ActionException {// 用户下订单充值
		recharge.setCuserEmail(getUserLoginEmail());
		recharge.setStoreFlag(this.getFlag());
		dto.put("model", recharge);
		dto.put("type", type);
		//if ("1".equals(type)) {// 跟自己充值
			//.recharge.setAccounts(getUserLoginEmail());
		//}
		if(getUserLoginEmail()!=null){
		recharge.setCuserEmail(getUserLoginEmail());
		recharge.setOpAccount(getUserLoginEmail());
		}else{
		recharge.setCuserEmail(" ");
		recharge.setOpAccount(recharge.getAccounts());
		}
		String currency=Constants.STORE_TO_CURRENCY.get(this.getFlag());
		dto.put("currency", currency);
		recharge = (LvRecharge) this.doService("RechargeService", "saveOrder",
				dto);
		rnum = recharge.getRnum();
		return "doorder";
	}

	/**
	 * 针对本号充值的状态检测
	 * 
	 * @return
	 */
	public String userStatus() {// 获取登录账号状态
		PrintWriter out = null;
		try {
			dto.put("email", getUserLoginEmail());
			BossUser userBean = (BossUser) this.doService("RechargeService", "baseinfo", dto);
			out = this.getResponse().getWriter();
			out.print(userBean.getUserstatus());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String isExistAccout() {// 检查账号的状态
		PrintWriter out = null;
		try {
			dto.put("email", email);
			BossUser userBean = (BossUser) this.doService("RechargeService",
					"baseinfo", dto);
			out = this.getResponse().getWriter();
			if (userBean.getUserstatus() != null
					&& userBean.getUserstatus() != -1) {// 账号存在
				if (userBean.getUserstatus() == 2) {
					out.print(3);// 未激活
				} else {
					if (userBean.getUserstatus() == 0) {
						out.print(0);// 账号已停用
					} else if (userBean.getUserstatus() == 1) {
						out.print(1);// 正常
					} else if (userBean.getUserstatus() == 3) {
						out.print(2);// 冻结
					}
				}
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * 在线下订单确认页面
	 */
	public String trueOrder() throws Exception {
		dto.put("rnum", rnum);
		recharge = (LvRecharge) this.doService("RechargeService", "getOrder", dto);
		
		dto.clear();
		dto.put("storeFlag", this.getFlag());
		List<LvPaymentStyle> paymentStyleList = (List<LvPaymentStyle>) this.doService("RechargeService", "getPaymentStyleList", dto);
		getRequest().setAttribute("paymentStyleList", paymentStyleList);	// 可支付的方式
		getRequest().setAttribute("rate", Constants.rateNum);	// 美元与人民币的汇率
		//获取国家列表
		List<LvArea>contryList=(List<LvArea>)this.doService("ShopCartService", "getContryList", dto);
		this.getRequest().setAttribute("contryList", contryList);

		String mallSystem = Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag()); //获取当前店铺所属系统（华扬orBanana）
		String userCode=this.getCookieValue(UserConstant.USER_ID, true, Constants.MALL_TO_DOMAIN_POSTFIX.get(mallSystem));
		dto.put("userCode", userCode);
		//dto.put("storeFlag", this.getFlag());
		//LvAccountAddress modeAccountAddress=(LvAccountAddress)this.doService("RechargeService","getUserDefAddress", dto);
		List<LvAccountAddress> addressList=(List)this.doService("AccountAddressService","getUserAddress", dto);
		if(addressList!=null&&addressList.size()>0){
		this.getRequest().setAttribute("addressList", addressList);
		}
		
		return "trueorder";
	}
	
	public static void main(String[] args) {
		float money = 5.3658f;
		money = ((float) Math.round(money * 100)) / 100;
		System.out.println(money);
	}

	public String toPay() throws Exception {// 前住支付
		dto.put("rnum", rnum);
		recharge = (LvRecharge) this.doService("RechargeService", "getOrder",
				dto);
		if (paymethod != null && paymethod > 0) {
			recharge.setRtype(paymethod);
			if (paymethod == Constants.PAY_METHOD_OLD_PAYPALOUT_JCB || paymethod == Constants.PAY_METHOD_ALIPAY) {
				if ("USD".equals(recharge.getCurrency())) {
					float money=recharge.getMoney()*Constants.rateNum;
					money = ((float) Math.round(money * 100)) / 100;
					recharge.setMoney(money);
					recharge.setCurrency("CNY");
				}
			} else {
				if ("CNY".equals(recharge.getCurrency())) {
					float money=recharge.getMoney()*Constants.rateNumCNY;
					money = ((float) Math.round(money * 100)) / 100;
					recharge.setMoney(money);
					recharge.setCurrency("USD");
				}
			}
		}
		dto.put("model", recharge);
		this.doService("RechargeService","updatePayMethod",dto);
		this.getRequest().setAttribute("recharge", recharge);
		this.getRequest().setAttribute("lvAccountAddress", lvAccountAddress);
		return SUCCESS;
	}

	/**
	 * 订单记录
	 * 
	 * @return
	 * @throws Exception 
	 */
	public String list() throws Exception {
		Calendar cal = Calendar.getInstance();
		if (ObjectUtils.isNullOrEmptyString(year)) {
			year = cal.get(Calendar.YEAR) + "";
		}
		this.getRequest().setAttribute("dateYear", cal.get(Calendar.YEAR));
		dto.put("year", year);
		dto.put("page", page);

		dto.put("email", getUserLoginEmail());
		dto.put("storeFlag", this.getFlag());
		page = (Pagination) this.doService("RechargeService", "findPage", dto);
		
		BossUser userBean = (BossUser) this.doService("RechargeService", "baseinfo", dto);
		
		this.getRequest().setAttribute("pagination", page);
		this.getRequest().setAttribute("userBean", userBean);
		this.getRequest().setAttribute("flag", "balance");
		this.isOrNoLoginFlag();
		return "list";
	}

	public String doCard() throws Exception {// 充值卡充值
		String code = (String) this.getSession().getAttribute("rcode");
		String i18n = this.getFlag();
		this.getRequest().setAttribute("email", recharge.getAccounts());
		if (!validCode.trim().equals(code)) {
			this.getRequest().setAttribute("resultMsg", I18nCache.getResourceValue("rechargeAction.java.code.wrong", i18n));// "验证码错误，请重新输入！
			return INPUT;
		}
		if(getUserLoginEmail()!=null){
			recharge.setCuserEmail(getUserLoginEmail());
			recharge.setOpAccount(getUserLoginEmail());
		}else{
		    recharge.setCuserEmail(recharge.getAccounts());
		    recharge.setOpAccount(recharge.getAccounts());
		}
	
		recharge.setStoreFlag(this.getFlag());
		dto.put("model", recharge);
		dto.put("cardPwd", this.getRequest().getParameter("cardPwd"));
		dto.put("rechargeResource", StoreHelp.getRechargeResource(this.getFlag()));
		Map map = (Map) this.doService("RechargeService", "cardCharge", dto);
		Integer result = (Integer) map.get("status");
		if (result == 1) {// 充值成功
			String cardSuccess = "type=" + type + "*amount="
					+ map.get("amount") + "*balnace=" + map.get("balnace")
					+ "*otherAccount=" + recharge.getAccounts() + "*orderno="
					+ map.get("orderno");
			this.addCookie("cardSuccess", cardSuccess, true, -1);
			return SUCCESS;
		}
		
		String msg = "";
		boolean cFlag=true;
		if (result == 3 || result == 5) {
			msg = I18nCache.getResourceValue("rechargeAction.java.card.wrong", i18n);// "卡号或密码不正确！
		} else if (result == 4) {
			msg = I18nCache.getResourceValue("rechargeAction.java.card.beuse", i18n);// "卡号已使用！";
		} else if (result == 6) {
			msg = I18nCache.getResourceValue("rechargeAction.java.card.overtime", i18n);// "卡号已过期！";
		} else if (result == 7) {
			msg = I18nCache.getResourceValue("rechargeAction.java.user.not.active", i18n);// "用户未激活！";
		} else if (result == 8) {
			msg = I18nCache.getResourceValue("rechargeAction.java.user.forbid", i18n);// "用户账号已停用！";
		} else if (result == 9) {
			msg = I18nCache.getResourceValue("rechargeAction.java.user.freeze", i18n);// "用户账号已冻结！";
		} else if (result == 10) {
			msg = I18nCache.getResourceValue("rechargeAction.java.for.user.not.exist", i18n);// "代充值账号不存在！";
		} else if (result == 11) {
			msg = I18nCache.getResourceValue("rechargeAction.java.for.user.not.active", i18n);// "代充值账号未激活！";
		} else if (result == 12) {
			msg = I18nCache.getResourceValue("rechargeAction.java.for.user.forbid", i18n);// "代充值账号已停用";
		} else if (result == 13) {
			msg = I18nCache.getResourceValue("rechargeAction.java.for.user.freeze", i18n);// "代充值账号已冻结！";
		} else {
			msg = I18nCache.getResourceValue("rechargeAction.java.fail", i18n);// "充值失败！";
			cFlag=false;
		}
		this.getRequest().setAttribute("resultMsg", msg);
		this.getRequest().setAttribute("type", type);
		if(true==cFlag){return INPUT;}
		getRequest().setAttribute("serviceTel", I18nCache.getResourceValue("common.service.tel", this.getFlag()));
		this.getRequest().setAttribute("orderno", map.get("orderno"));
		return "cardfail";
		
	}

	/**
	 * 交易记录
	 * 
	 * @return
	 */
	public String tradeList() {
		Calendar cal = Calendar.getInstance();
		if (ObjectUtils.isNullOrEmptyString(year)) {
			year = cal.get(Calendar.YEAR) + "";
		}
		dto.put("email", getUserLoginEmail());
		dto.put("year", year);
		dto.put("page", page);
		Dto dtoMap = null;
		if (recordType == 1) {// 充值记录
			dtoMap = (Dto) this.doService("RechargeService",
					"findChargeRecordList", dto);
		} else if (recordType == 2) {// 消费记录
			String i18n = (String) this.getSession().getAttribute("i18n");
			dto.put("i18n", i18n);
			dtoMap = (Dto) this.doService("RechargeService",
					"findConsumeRecordList", dto);
		}
		page = (Pagination) dtoMap.get("pag");
		this.getRequest().setAttribute("pagination", page);
		this.getRequest().setAttribute("totalamt", dtoMap.get("totalamt"));
		this.getRequest().setAttribute("dateYear", cal.get(Calendar.YEAR));
		this.getRequest().setAttribute("flag", "buylog");
		return "consumelist";
	}

    public String area() throws Exception{
        this.getResponse().setCharacterEncoding("UTF-8");
    	PrintWriter out=this.getResponse().getWriter();
    	String str=FileUtils.readFileToString(new File(this.getText("res.domain.path")+"/"+this.getFlag()+"/res/js/sysarea.js"),"UTF-8");
    	out.print(str);
    	return null;
    }
	public String online()throws Exception{
		dto.put("mallFlag", this.getFlag());
	    List<LvVbPlans> vbPlansList=(List<LvVbPlans>)this.doService("RechargeService", "getLvVbPlansList", dto);
	    String currency=Constants.STORE_TO_CURRENCY.get(this.getFlag());
	    this.getRequest().setAttribute("currency", currency);
	    
	    this.getRequest().setAttribute("vbPlansList", vbPlansList);
	    if(vbPlansList!=null){
	    for(LvVbPlans p:vbPlansList){
	    	if(p.getPtype()==true){//输入
	    		this.getRequest().setAttribute("vbPlans", p);
	    		break;
	    	}
	    }
	    }
	    DecimalFormat df = new DecimalFormat("#.###"); 
	    this.getRequest().setAttribute("rateVbNum",df.format(com.lshop.common.util.Constants.rateVbNum));
	    this.getRequest().setAttribute("rateVbNumCny",df.format(com.lshop.common.util.Constants.rateVbNumCny));
	    this.getRequest().setAttribute("email", getUserLoginEmail());
		return "online";
	}
	public String card()throws Exception{
		this.getRequest().setAttribute("email", getUserLoginEmail());
		return "card";
	}
	private void isOrNoLoginFlag() throws Exception{
		String mallSystem = Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag()); //获取当前店铺所属系统（华扬orBanana）
		String userCode=this.getCookieValue(UserConstant.USER_ID, true, Constants.MALL_TO_DOMAIN_POSTFIX.get(mallSystem));
		if(userCode==null||"".equals(userCode.trim())){
			this.getRequest().setAttribute("isOrNOLoginFlag", 0);//表示未登录
		}else{
			this.getRequest().setAttribute("isOrNOLoginFlag", 1);//表示已登录
		}
	}
	public LvRecharge getRecharge() {
		return recharge;
	}

	public void setRecharge(LvRecharge recharge) {
		this.recharge = recharge;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValidCode() {
		return validCode;
	}

	public void setValidCode(String validCode) {
		this.validCode = validCode;
	}

	public String getRnum() {
		return rnum;
	}

	public void setRnum(String rnum) {
		this.rnum = rnum;
	}

	public Integer getPaymethod() {
		return paymethod;
	}

	public void setPaymethod(Integer paymethod) {
		this.paymethod = paymethod;
	}

	public Integer getRecordType() {
		return recordType;
	}

	public void setRecordType(Integer recordType) {
		this.recordType = recordType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LvAccountAddress getLvAccountAddress() {
		return lvAccountAddress;
	}

	public void setLvAccountAddress(LvAccountAddress lvAccountAddress) {
		this.lvAccountAddress = lvAccountAddress;
	}

}
