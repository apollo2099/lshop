/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvSpecialtyStoreType.service;

import java.util.List;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.service.BaseService;
import com.lshop.common.pojo.logic.LvSpecialtyStoreType;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */public interface LvSpecialtyStoreTypeService extends BaseService{
	
	/**
	 * 获得所有
	 */
	public List<LvSpecialtyStoreType> findAll(Dto dto) throws ServiceException;
	public List<LvSpecialtyStoreType> findAllPareat(Dto dto) throws ServiceException;
	public List<LvSpecialtyStoreType> findAllByPareat(Dto dto) throws ServiceException;
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
	public LvSpecialtyStoreType get(Dto dto) throws ServiceException;

	/**
	 * 删除
	 * @param dto
	 * @throws ServiceException
	 */
	public Boolean del(Dto dto) throws ServiceException;

	/**
	 * 批量删除
	 * @param dto
	 * @throws ServiceException
	 */
	public void delList(Dto dto) throws ServiceException;

	/**
	 * 保存
	 */
	public LvSpecialtyStoreType save(Dto dto) throws ServiceException;
	
	/**
	 * 更新
	 */
	public LvSpecialtyStoreType update(Dto dto)throws ServiceException;
	public LvSpecialtyStoreType getSpecialtyStoreType(Dto dto )throws ServiceException;
	
	/**
	 * 根据专卖店分类名称判断是否重复
	 */
	public Boolean isExsitSpecialtyStoreTypeByName(Dto dto)throws ServiceException;
	
}
