/**
 * 
 */
package com.lshop.web.lottery.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.lshop.common.action.BaseAction;
import com.lshop.common.coupon.vo.CouponDetail;
import com.lshop.common.pojo.logic.LvArea;
import com.lshop.common.pojo.user.LvAccountAddress;
import com.lshop.common.util.Constants;
import com.lshop.web.coupon.service.CouponService;
import com.lshop.web.lottery.service.LotteryPrizeService;
import com.lshop.web.userCenter.UserConstant;

/**
 * 用户奖品
 * @author caicl
 *
 */
@Controller("LotteryPrizeAction")
@Scope("prototype")
public class LotteryPrizeAction extends BaseAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5295634367124242710L;
	private int pageNo = 1;
	private int pageSize = 10;
	private String couponCode;//优惠券码
	private String prizeCode;//奖品码
	private LvAccountAddress lvAccountAddress; //账户收货地址
	private String addressCode; 
	
	@Resource
	private LotteryPrizeService lotteryPrizeService;
	@Resource
	private CouponService couponService;
	/**
	 * 奖品列表
	 * @return
	 * @throws Exception 
	 */
	public String prizeList() throws Exception {
		String userCode = this.getCookieValue(UserConstant.USER_ID, true);
		String mallFlag = Constants.STORE_TO_MALL_SYSTEM.get(this.getFlag()); 
		getRequest().setAttribute("pagination", lotteryPrizeService.findPrizePage(userCode, pageNo, pageSize, mallFlag));
		getRequest().setAttribute("flag", "prize");//标识菜单栏
		getRequest().setAttribute("nowDate", new Date());
		return "prizeList";
	}
	/**
	 * 返回优惠券详情
	 * @return
	 * @throws Exception 
	 */
	public String getCoupon() throws Exception {
		CouponDetail detail = couponService.getCouponDetail(couponCode);
		getRequest().setAttribute("coupon", detail);
		return "coupon";
	}
	/**
	 * 查看收货地址列表
	 * @return
	 * @throws Exception 
	 */
	public String toAddDeliver() throws Exception {
		String uid=this.getCookieValue(UserConstant.USER_ID, true);
		//获取该用户的默认收货地址
		dto.put("userCode", uid);
		dto.put("storeFlag", this.getFlag());
		LvAccountAddress dAddress=(LvAccountAddress)this.doService("AccountAddressService", "getUserDefAddress", dto);
		//获取该用户的非默认地址列表，显示在前台页面上注意不要与默认收货地址重复
		List<LvAccountAddress> addressList=(List<LvAccountAddress>)this.doService("AccountAddressService", "getUserAddress", dto);
		//如果没有默认收货地址，则默认为最后添加的一个
		if(dAddress==null){
			if(null!=addressList && addressList.size()>0){
				dAddress = addressList.get(0);
			}
		}
		this.getRequest().setAttribute("dAddress", dAddress);
		this.getRequest().setAttribute("addressList", addressList);
		//获取国家列表
		List<LvArea>contryList=(List<LvArea>)this.doService("ShopCartService", "getContryList", dto);
		this.getRequest().setAttribute("contryList", contryList);
		return "deliver";
	}
	/**
	 * 增加奖品寄送信息
	 * @throws Exception 
	 */
	public String addDeliver() throws Exception {
		String userCode = this.getCookieValue(UserConstant.USER_ID, true);
		lotteryPrizeService.addDeliver(addressCode, userCode, prizeCode);
		dto.put("addressCode", addressCode);
		dto.put("userCode", userCode);
		doService("AccountAddressService", "setDefAddress", dto);
		return "toPrizeList";
	}
	/**
	 * 查看寄送信息详情
	 * @return
	 */
	public String getDeliver() {
		getRequest().setAttribute("deliver", lotteryPrizeService.getMaterialDeliver(prizeCode));
		return "deliverDetail";
	}
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	public String getPrizeCode() {
		return prizeCode;
	}
	public void setPrizeCode(String prizeCode) {
		this.prizeCode = prizeCode;
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
