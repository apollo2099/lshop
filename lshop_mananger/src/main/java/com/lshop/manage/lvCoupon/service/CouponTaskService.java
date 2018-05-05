package com.lshop.manage.lvCoupon.service;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.logic.LvCouponType;
public interface CouponTaskService{
	
	/**
	 * 
	 * @Method: createBatchCoupon 
	 * @Description:  [创建优惠码任务]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2014-8-21 上午9:52:56]   
	 * @UpdateUser:   [liaoxj]     
	 * @UpdateDate:   [2014-8-21 上午9:52:56]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param createNum 创建数目
	 * @param activityCode 活动code
	 * @param couponTypeCode 优惠券编码
	 * @param couponStatus 优惠吗状态
	 * @param couponType 优惠码类型（1线上，2线下）
	 * @param operator 操作人
	 * @throws ServiceException 
	 * @return void
	 */
	public void createBatchCoupon(Integer createNum, String activityCode,String couponTypeCode,Short couponStatus,Short couponType,String operator) throws ServiceException ;
	/**
	 * 
	 * @Method: createBatchCoupon 
	 * @Description:  [创建优惠码任务(二)]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2014-9-10 下午5:16:12]   
	 * @UpdateUser:   [liaoxj]     
	 * @UpdateDate:   [2014-9-10 下午5:16:12]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param createNum 创建数目
	 * @param activityCode 活动编码
	 * @param couponTypeCode 优惠券编码
	 * @param couponStatus 优惠吗状态
	 * @param couponType 优惠码类型
	 * @param operator 操作人
	 * @param prizeCode 奖项物品code
	 * @throws ServiceException 
	 * @return void
	 */
	public void createBatchCoupon(Integer createNum, String activityCode, String couponTypeCode,Short couponStatus,Short couponType,String operator,String prizeCode)throws ServiceException ;

}
