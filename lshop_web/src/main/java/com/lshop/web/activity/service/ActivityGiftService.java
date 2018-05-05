package com.lshop.web.activity.service;

import java.util.List;

import com.lshop.common.pojo.logic.LvActivityGift;
import com.lshop.common.pojo.logic.LvActivityProduct;

public interface ActivityGiftService {
	
	/**
	 * 
	 * @Method: findGiftByActivityCode 
	 * @Description:  [根据活动变查询活动赠品信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-2-5 上午11:13:07]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-2-5 上午11:13:07]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param activityCode 活动编号
	 * @return List<LvActivityGift>
	 */
	public List<LvActivityGift> findGiftByActivityCode(String activityCode);
	/**
	 * 
	 * @Method: findGiftByProductCode 
	 * @Description:  [根据活动变和产品编号查询活动赠品信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-15 上午11:43:50]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-15 上午11:43:50]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param activityCode 活动编号
	 * @param productCode 产品编号
	 * @return List<LvActivityGift> 赠品集合
	 */
	public List findGiftByActivityCode(String activityCode,String productCode);

	/**
	 * 
	 * @Method: findActivityProductByProductCode 
	 * @Description:  [根据商品编号和活动编码查询活动商品信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-30 上午11:40:55]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-30 上午11:40:55]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param activityCode
	 * @param productCode
	 * @return LvActivityProduct
	 */
	public LvActivityProduct findActivityProductByProductCode(String activityCode,String productCode);
	
	
	/**
	 * 
	 * @Method: findGiftByProductCode 
	 * @Description:  [根据产品编号查询活动赠品信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-15 下午5:32:23]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-15 下午5:32:23]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param productArr 产品数组
	 * @return List<LvActivityGift> 赠品集合
	 */
	public List findGiftByProductCode(String productCode);
	
	/**
	 * 
	 * @Method: findGiftByProductCodeStr 
	 * @Description:  [一句话描述该类的功能]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-16 下午1:47:01]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-16 下午1:47:01]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param productArr 商品编码数组
	 * @return List<LvActivityGift> 赠品集合
	 */
	public List findGiftByProductCodeStr(String activityCode,String [] productArr);
	
	/**
	 * 
	 * @Method: updateGiftHaveNum 
	 * @Description:  [更新活动赠品领取数目]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-16 下午1:45:52]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-16 下午1:45:52]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param activityCode 活动编码
	 * @param giftCode 赠品编码
	 * @param giftNum 赠送数量
	 * @return void
	 */
	public void updateGiftHaveNum(String activityCode,String giftCode,Integer giftNum);
	
	public List giftDeduplication(List giftList);
}
