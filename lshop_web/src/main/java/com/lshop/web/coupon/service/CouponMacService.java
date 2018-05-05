package com.lshop.web.coupon.service;

import com.lshop.common.pojo.logic.LvCouponMac;

public interface CouponMacService {
	/**
	 * 
	 * @Method: save
	 * @Description: [保存以旧换新记录]
	 * @Author: [liaoxj]
	 * @CreateDate: [2015年7月7日 下午4:42:55]
	 * @UpdateUser: [liaoxj]
	 * @UpdateDate: [2015年7月7日 下午4:42:55]
	 * @UpdateRemark: [说明本次修改内容]
	 * @param mac
	 *            mac编码
	 * @param couponCode
	 *            优惠码
	 * @param userEmail
	 *            用户邮箱
	 * @param contactWay
	 *            用户手机
	 * @param ip
	 *            ip
	 * @param sourceUrl
	 *            来源
	 * @return Boolean
	 */
	public Boolean save(String mac,String couponCode,String userEmail,String ip,String sourceUrl);
	
	/**
	 * 
	 * @Method: findByMac
	 * @Description: [根据MAC查找以旧换新记录]
	 * @Author: [liaoxj]
	 * @CreateDate: [2015年7月7日 下午4:43:43]
	 * @UpdateUser: [liaoxj]
	 * @UpdateDate: [2015年7月7日 下午4:43:43]
	 * @UpdateRemark: [说明本次修改内容]
	 * @param mac
	 *            mac编码
	 * @return LvCouponMac
	 */
	public LvCouponMac findByMac(String mac);
	
	/**
	 * 
	 * @Method: updateStatus 
	 * @Description:  [更新mac以旧换新状态]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2015年7月8日 下午3:45:58]   
	 * @UpdateUser:   [liaoxj]     
	 * @UpdateDate:   [2015年7月8日 下午3:45:58]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param couponCode 优惠码
	 * @return Boolean
	 */
	public Boolean updateStatus(String couponCode);

}
