/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvStoreArea.service;

import java.util.List;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.service.BaseService;
import com.lshop.common.pojo.logic.LvStoreArea;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */public interface LvStoreAreaService extends BaseService{
	
	/**
	 * 获得所有
	 */
	public List<LvStoreArea> findAll(Dto dto) throws ServiceException;
	public List<LvStoreArea> findAllCity(Dto dto) throws ServiceException;
	public List<LvStoreArea> getAllCity(Dto dto) throws ServiceException;
	public List<LvStoreArea> getStoreArea(Dto dto)throws ServiceException;
	/**
	 * 国家分页查询
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Pagination findPage(Dto dto) throws ServiceException;
	/**
	 * 城市分页查询
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Pagination findAreaCity(Dto dto) throws ServiceException;

	/**
	 * 获得单独的实体信息
	 */
	public LvStoreArea get(Dto dto) throws ServiceException;

	/**
	 * 删除
	 * @param dto
	 * @throws ServiceException
	 */
	public void del(Dto dto) throws ServiceException;

	/**
	 * 保存
	 */
	public LvStoreArea save(Dto dto) throws ServiceException;
	
	/**
	 * 更新
	 */
	public LvStoreArea update(Dto dto)throws ServiceException;
	
	public Boolean isExistCountry(Dto dto)throws ServiceException;
	public Boolean isExistCity(Dto dto)throws ServiceException;
	public Boolean isExistCityEn(Dto dto) throws ServiceException;
	public Boolean isExistCityList(Dto dto)throws ServiceException;
	public Boolean isExistStoreList(Dto dto)throws ServiceException;
	
	
}
