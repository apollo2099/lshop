/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvActivity.service;

import java.util.List;

import com.lshop.common.pojo.logic.LvActivity;
import com.lshop.common.pojo.logic.LvActivityAppointProduct;
import com.lshop.common.pojo.logic.LvActivityLimitOrder;
import com.lshop.common.pojo.logic.LvActivityLimitTime;
import com.lshop.common.pojo.logic.LvActivityLimited;
import com.lshop.common.pojo.logic.LvActivityLink;
import com.lshop.common.pojo.logic.LvActivityLottery;
import com.lshop.common.pojo.logic.LvActivityMac;
import com.lshop.common.pojo.logic.LvActivityRegister;
import com.lshop.common.pojo.logic.LvActivityShare;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.service.BaseService;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */public interface LvActivityService extends BaseService{
	
	/**
	 * 获得所有
	 */
	 public List<LvActivity> findAllActivity(Dto dto)throws ServiceException;
	 public List<LvActivity> findAllActivityLimitOrder(Dto dto) throws ServiceException;
	 public List<LvActivity> findAllActivityLink(Dto dto) throws ServiceException;
	 public List<LvActivity> findAllActivityRegister(Dto dto) throws ServiceException;
	 public List<LvActivity> findAllActivityShare(Dto dto) throws ServiceException;
	 public List<LvActivity> findAllActivityLottery(Dto dto) throws ServiceException;
	 public List<LvActivity> findAllActivityPointProduct(Dto dto) throws ServiceException;

	/**
	 * 分页查询
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Pagination findPage(Dto dto) throws ServiceException;

	/**
	 * 获得单独的实体信息
	 */
	public LvActivity get(Dto dto) throws ServiceException;
	/**
	 * 根据code获取活动信息
	 */
	public LvActivity findByCode(Dto dto)throws ServiceException;
	/**
	 * 删除
	 * @param dto
	 * @throws ServiceException
	 */
	public void del(Dto dto) throws ServiceException;

	/**
	 * 保存
	 */
	public LvActivity save(Dto dto) throws ServiceException;
	
	/**
	 * 更新
	 */
	public LvActivity update(Dto dto)throws ServiceException;
	public LvActivity updateStatus(Dto dto)throws ServiceException;
	public void updateActvityGivenName(String tableName,Short givenTypeNum,String givenProductCode,String givenTypeName) throws ServiceException;	
	/**
	 * 
	 * @Method: createBatchCoupon 
	 * @Description:  [活动生产优惠券入口程序]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-9 下午4:29:36]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-9 下午4:29:36]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param activityCode 活动编码
	 * @param createNum 生产数量
	 * @param givenTypeNum 活动赠送类型（1=选择优惠券，2=赠送抽奖机会，3=赠送礼品）
	 * @param couponTypeCode 优惠券编码
	 * @param couponType 优惠券类型(1=线上，2=线下)
	 * @param operator 操作人
	 * @return void
	 */
	public void createBatchCoupon(String activityCode,Integer createNum,Short givenTypeNum,String couponTypeCode,Short couponType,String operator,String prizeCode);
	
	public LvActivityLimitTime findLimtTimeByCode(Dto dto)throws ServiceException;
	public LvActivityLimited findLimtNumByCode(Dto dto)throws ServiceException;
	public LvActivityLimitOrder findLimtOrderByCode(Dto dto)throws ServiceException;
	public LvActivityLink findLinkByCode(Dto dto)throws ServiceException;
	public LvActivityRegister findACRegisterByCode(Dto dto)throws ServiceException;
	public LvActivityShare findACShareByCode(Dto dto)throws ServiceException;
	public LvActivityLottery findACLotteryByCode(Dto dto)throws ServiceException;
	/**
	 * 
	 * @Method: findACPointProductByCode 
	 * @Description:  [根据活动code查询购买指定商品就送活动详情]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-7 下午5:43:04]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-7 下午5:43:04]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return LvActivityAppointProduct
	 */
	public LvActivityAppointProduct findACPointProductByCode(Dto dto)throws ServiceException;
	/**
	 * 
	 * @Method: findACMacByCode 
	 * @Description:  [根据活动code查询输入mac就送活动详情]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2015年7月10日 下午5:48:35]   
	 * @UpdateUser:   [liaoxj]     
	 * @UpdateDate:   [2015年7月10日 下午5:48:35]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return LvActivityMac
	 */
	public LvActivityMac findACMacByCode(Dto dto)throws ServiceException;
	public Boolean isExistActivityLimitTime(Dto dto)throws ServiceException;
	public Boolean isExistActivityLimitNum(Dto dto)throws ServiceException;
	
	
	
	public Boolean isEffectActivityRegister(Dto dto)throws ServiceException;
	public Boolean isEffectActivityShare(Dto dto)throws ServiceException;
	public Boolean isEffectActivityLimitOrder(Dto dto)throws ServiceException;
	public Boolean isEffectActivityLink(Dto dto)throws ServiceException;
	public Boolean isEffectActivityLimitTime(Dto dto)throws ServiceException;
	public Boolean isEffectActivityLimited(Dto dto)throws ServiceException;
	public Boolean isEffectLotteryForLimitOrder(Dto dto)throws ServiceException;
	public Boolean isEffectActivityAppointProduct(Dto dto)throws ServiceException;
	
}
