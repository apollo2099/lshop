/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvNavigation.service;

import java.util.List;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.service.BaseService;
import com.lshop.common.pojo.logic.LvNavigation;

/**
 * @author 好视网络-网站研发部：唐迪
 * @version 1.0
 * @since 1.0
 */public interface LvNavigationService extends BaseService{
	
	/**
	 * 获得所有
	 */
	public List<LvNavigation> findAll(Dto dto) throws ServiceException;

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
	public LvNavigation get(Dto dto) throws ServiceException;

	/**
	 * 删除该导航及其子导航菜单
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
	public LvNavigation save(Dto dto) throws ServiceException;
	
	/**
	 * 更新
	 */
	public LvNavigation update(Dto dto)throws ServiceException;
	
	/**
	 * 上传图片
	 */
	public String upload(Dto dto)throws ServiceException;
	/**
	 * 根据商城标识查找父级导航菜单
	 * @param storeId
	 * @return
	 * @throws ServiceException
	 */
	public List<LvNavigation> findPrimNaviByStoreId(String storeId) throws ServiceException;
	/**
	 * 根据id返回对象
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	public LvNavigation getById(Integer id) throws ServiceException;
}
