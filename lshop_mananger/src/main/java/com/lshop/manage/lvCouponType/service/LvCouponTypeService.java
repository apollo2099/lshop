/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvCouponType.service;

import java.util.List;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.service.BaseService;
import com.lshop.common.pojo.logic.LvCoupon;
import com.lshop.common.pojo.logic.LvCouponType;
import com.lshop.ws.server.popularize.order.bean.PCouponDtoPageResposne;
import com.lshop.ws.server.popularize.order.bean.PCouponDtoResposne;
import com.lshop.ws.server.popularize.order.bean.PCouponMainDto;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */public interface LvCouponTypeService extends BaseService{
	
	/**
	 * 获得所有
	 */
	public List<LvCouponType> findAll(Dto dto) throws ServiceException;

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
	public LvCouponType get(Dto dto) throws ServiceException;
	
	/**
	 * 
	 * @Method: findByCode 
	 * @Description:  [根据优惠券code查询优惠券信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-6-23 上午10:35:31]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-6-23 上午10:35:31]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return LvCouponType
	 */
	public LvCouponType findByCode(Dto dto) throws ServiceException;
	public LvCouponType findByCode(String couponTypeCode) throws ServiceException;

	/**
	 * 删除
	 * @param dto
	 * @throws ServiceException
	 */
	public void del(Dto dto) throws ServiceException;

	/**
	 * 批量删除
	 * @param dto
	 * @throws ServiceException
	 */
	public void delList(Dto dto) throws ServiceException;

	/**
	 * 保存
	 */
	public LvCouponType save(Dto dto) throws ServiceException;
	
	/**
	 * 更新
	 */
	public LvCouponType update(Dto dto)throws ServiceException;
	public LvCouponType updateTotalNumber(Dto dto)throws ServiceException;
	public LvCouponType updateExtendDate(Dto dto)throws ServiceException;
	public LvCouponType updateDisable(Dto dto)throws ServiceException;
	public LvCouponType updateStatus(Dto dto)throws ServiceException;
	public List<LvCoupon> updateDownload(Dto dto)throws ServiceException;
	/**
	 * 
	 * @Method: updateNoGrantNumber 
	 * @Description:  [更新优惠券的未发放数目]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-12-23 下午7:57:56]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-12-23 下午7:57:56]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param noGentNumber 变更前未发放数目
	 * @param createNum    创建优惠码数目
	 * @param couponTypeId 优惠券id
	 * @return boolean
	 */
	public boolean updateNoGrantNumber(Integer noGentNumber,Integer createNum,Integer couponTypeId);
	public List exportCouponTypeList(Dto dto)throws ServiceException;
	
	public PCouponMainDto checkCouponCode(String couponCodes) throws ServiceException;
	
	public PCouponDtoPageResposne findPageForTg(String couponName,String pageNum, String numPerPage) throws ServiceException;
	
	public PCouponDtoResposne getCoupon(String couponTypeCode,int couponQuantity) throws ServiceException;
}
