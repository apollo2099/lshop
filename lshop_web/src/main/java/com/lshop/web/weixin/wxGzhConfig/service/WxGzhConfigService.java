/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.web.weixin.wxGzhConfig.service;

import java.util.List;

import com.lshop.web.weixin.common.pojo.WxGzhConfig;
import com.lshop.web.weixin.common.pojo.WxUser;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.service.BaseService;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */public interface WxGzhConfigService extends BaseService{
	
	/**
	 * 获得所有
	 */
	public List<WxGzhConfig> findAll(Dto dto) throws ServiceException;

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
	public WxGzhConfig get(Dto dto) throws ServiceException;
	public WxGzhConfig getByCode(String code) throws ServiceException;
	public WxGzhConfig getByStoreId(String storeId) throws ServiceException;
	public WxGzhConfig getById(int id) throws ServiceException;
	public String getAccessToken(WxGzhConfig wxGzhConfig) throws ServiceException;

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
	public WxGzhConfig save(Dto dto) throws ServiceException;
	
	/**
	 * 更新
	 */
	public WxGzhConfig update(Dto dto)throws ServiceException;
}
