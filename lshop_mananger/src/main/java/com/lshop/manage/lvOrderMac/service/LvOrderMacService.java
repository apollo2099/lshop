/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvOrderMac.service;

import java.util.List;

import com.lshop.common.pojo.logic.LvOrderMac;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.service.BaseService;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */public interface LvOrderMacService extends BaseService{
	
	/**
	 * 获得所有
	 */
	public List<LvOrderMac> findAll(Dto dto) throws ServiceException;

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
	public LvOrderMac get(Dto dto) throws ServiceException;
	/**
	 * 
	 * @Method: findByOrderId 
	 * @Description:  [根据订单号获得单独的实体信息]  
	 * @Author:       [liaoxj]       
	 * @param orderId
	 * @throws ServiceException 
	 * @return LvOrderMac
	 */
	public LvOrderMac findByOrderId(String orderId) throws ServiceException;
	/**
	 * 删除
	 * @param dto
	 * @throws ServiceException
	 */
	public void del(Dto dto) throws ServiceException;
	public List exportOrderMac(Dto dto)throws ServiceException;


}
