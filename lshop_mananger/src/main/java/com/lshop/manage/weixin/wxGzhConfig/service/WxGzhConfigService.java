/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.weixin.wxGzhConfig.service;

import java.util.List;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.service.BaseService;
import com.lshop.manage.weixin.pojo.WxGzhConfig;

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
	
	
	/**
	 * 获取有效的token
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public WxGzhConfig getYxToken(Dto dto)throws ServiceException;
	/***
	 * 根据最新的配置id获取最新的token,并且要求传输当前登录的用户信息
	 * @param configId
	 * @return
	 * @throws ServiceException
	 */
	public String getLatestToken(Dto dto)throws ServiceException;
	
	/**
	 * 查询所有的公众号
	 * @return
	 * @throws ServiceException
	 */
	public List<WxGzhConfig> getAllWxgzh(Dto dto)throws ServiceException;
	
}
