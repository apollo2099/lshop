package com.lshop.web.accountAddress.action;

import java.io.PrintWriter;

import net.sf.json.JSONObject;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lshop.common.action.BaseAction;
import com.lshop.common.pojo.user.LvAccountAddress;
import com.lshop.web.userCenter.UserConstant;

/**
 * 收货地址服务api
 * @author caicl
 *
 */
@Controller("AddressAction")
@Scope("prototype")
public class AddressAction extends BaseAction{

	private static final long serialVersionUID = -7641275185360884664L;
	private LvAccountAddress lvAccountAddress; //账户收货地址
	private String addressCode;//收货地址编码
	
	/**
	 * 增加收货地址,并设为默认地址
	 * @throws Exception
	 */
	public void addAddress() throws Exception {
		String userCode = this.getCookieValue(UserConstant.USER_ID, true);
		lvAccountAddress.setStoreId(this.getFlag());
		dto.put("userCode", userCode);
		dto.put("addr", lvAccountAddress);
		dto.put("setDefault", true);
		addressCode = this.doService("AccountAddressService", "addAddress", dto).toString();
		String json = "{\"addressCode\":\""+addressCode+"\"}";
		PrintWriter writer = this.getResponse().getWriter();
		this.getResponse().setContentType("application/json;charset=UTF-8");
		writer.print(json);
		writer.close();
	}
	/**
	 * 编辑收货地址,并设为默认地址
	 * @throws Exception
	 */
	public void editAddress() throws Exception {
		String userCode = this.getCookieValue(UserConstant.USER_ID, true);
		lvAccountAddress.setStoreId(this.getFlag());
		dto.put("userCode", userCode);
		dto.put("addr", lvAccountAddress);
		this.doService("AccountAddressService", "editAddress", dto);
		dto.put("addressCode", lvAccountAddress.getCode());
		this.doService("AccountAddressService", "setDefAddress", dto);
		String json = "{\"addressCode\":\""+lvAccountAddress.getCode()+"\"}";
		PrintWriter writer = this.getResponse().getWriter();
		this.getResponse().setContentType("application/json;charset=UTF-8");
		writer.print(json);
		writer.close();
	}
	/**
	 * 设置用户默认收货地址
	 * @throws Exception
	 */
	public void setDefaultAddress() throws Exception {
		String userCode = this.getCookieValue(UserConstant.USER_ID, true);
		dto.put("userCode", userCode);
		dto.put("addressCode", addressCode);
		this.doService("AccountAddressService", "setDefAddress", dto);
		
	}
	/**
	 * 返回收货人信息Json数据
	 * @throws Exception
	 */
	public void getAddressJson() throws Exception {
		String userCode = this.getCookieValue(UserConstant.USER_ID, true);
		dto.put("userCode", userCode);
		dto.put("addressCode", addressCode);
		String djson = JSONObject.fromObject( this.doService("AccountAddressService", "getAddressByCode", dto)).toString();
		PrintWriter writer = this.getResponse().getWriter();
		this.getResponse().setContentType("application/json;charset=UTF-8");
		writer.print(djson);
		writer.close();
	}
	public LvAccountAddress getLvAccountAddress() {
		return lvAccountAddress;
	}
	public void setLvAccountAddress(LvAccountAddress lvAccountAddress) {
		this.lvAccountAddress = lvAccountAddress;
	}
	public String getAddressCode() {
		return addressCode;
	}
	public void setAddressCode(String addressCode) {
		this.addressCode = addressCode;
	}
	
}
