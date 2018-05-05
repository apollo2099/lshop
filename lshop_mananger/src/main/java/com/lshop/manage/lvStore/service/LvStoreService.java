/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvStore.service;

import java.util.List;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.service.BaseService;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.pojo.logic.LvTplModel;

/**
 * @author 好视网络-网站研发部：唐迪
 * @version 1.0
 * @since 1.0
 */public interface LvStoreService extends BaseService{
	
	/**
	 * 获得所有
	 */
	public List<LvStore> findAllStore(Dto dto) throws ServiceException;
	/**
	 * 获得所有(无店铺标识，包含商城)
	 */
	public List<LvStore> getAll(Dto dto) throws ServiceException;
	/**
	 * 获得所有(无店铺标识，不包含商城)
	 */
	public List<LvStore> getAllNoShop(Dto dto) throws ServiceException;
	/**
	 * 获得所有商城信息
	 */
	public List<LvStore> getShopList(Dto dto)throws ServiceException;
	/**
	 * 分页查询
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Pagination findPage(Dto dto) throws ServiceException;
	public Pagination getList(Dto dto) throws ServiceException;
	public List<LvStore> findStoreByParent(Dto dto)throws ServiceException;
	/**
	 * 获取店铺id 或者店铺code 来获取店铺信息
	 */
	public LvStore get(Dto dto) throws ServiceException;

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
	 * 新建店铺，设置模板，并添加支付方式,并返回店铺模板对象
	 */
	public LvTplModel save(Dto dto) throws ServiceException;
	
	/**
	 * 更新
	 */
	public LvStore update(Dto dto)throws ServiceException;
	/**
	 * 检查商铺标志是否重复
	 */
	public Boolean checkStoreFlag(Dto dto)throws ServiceException;
	/**
	 * 检查商铺域名是否重复
	 */
	public Boolean checkDomain(Dto dto)throws ServiceException;
	/**
	 * 检查商铺的商铺编号是否重复
	 */
	public Boolean checkStoreNumber(Dto dto)throws ServiceException;
	/**
	 * 检查店铺名称是否重复
	 */
	public Boolean checkStoreName(Dto dto)throws ServiceException;
	/**
	 * 根据店铺标志获取店铺
	 */
	public LvStore findStore(Dto dto)throws ServiceException;
	public LvStore findStoreByCode(Dto dto) throws ServiceException ;
	/**
	 * 更新所有店铺域名
	 */
	public Integer updateStoreIds(Dto dto) throws ServiceException;
	/**
	 * 设置店铺区域
	 */
	public Boolean updateStoreArea(Dto dto)throws ServiceException;
	/**
	 * 设置热门店铺
	 */
	public Boolean updateStoreHot(Dto dto)throws ServiceException;
	/**
	 * 更新店铺状态
	 */
	public Boolean updateStoreStatus(Dto dto)throws ServiceException;
	/**
	 * 修改排序值
	 */
	public Boolean updateOrderValue(Dto dto)throws ServiceException;
	/**
	 * 设置推荐店铺
	 */
	public Boolean updateStoreCommend(Dto dto)throws ServiceException;
	/**
	 * 刷新店铺编号
	 */
	public Boolean updateStoreNumber(Dto dto)throws ServiceException;
	public void updateParentCurrency(String storeId,String currency);
	/**
	 * 根据店铺标志获取父类信息
	 */
	public LvStore getParentStore(Dto dto)throws ServiceException;
	
}
