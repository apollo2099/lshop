/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvPaymentStyle.service;

import java.util.List;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.service.BaseService;
import com.lshop.common.pojo.logic.LvPaymentData;
import com.lshop.common.pojo.logic.LvPaymentStyle;

/**
 * @author 好视网络-网站研发部：唐迪
 * @version 1.0
 * @since 1.0
 */public interface LvPaymentStyleService extends BaseService{
	
	/**
	 * 获得所有
	 */
	public List<LvPaymentStyle> findAll(Dto dto) throws ServiceException;
	public List<LvPaymentStyle> findAllByStoreFlag(Dto dto) throws ServiceException;

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
	public LvPaymentStyle get(Dto dto) throws ServiceException;

	/**
	 * 删除
	 * @param dto
	 * @throws ServiceException
	 */
	public void del(Dto dto) throws ServiceException;
	/**
	 * 
	 */
    List <LvPaymentData> findPaymentDataAll(Dto dto)throws ServiceException;
	/**
	 * 批量删除
	 * @param dto
	 * @throws ServiceException
	 */
	public void delList(Dto dto) throws ServiceException;

	/**
	 * 保存
	 */
	public Integer save(Dto dto) throws ServiceException;
	
	/**
	 * 更新
	 */
	public LvPaymentStyle update(Dto dto)throws ServiceException;
	public LvPaymentStyle findPaymentStyle(Dto dto)throws ServiceException;
}
