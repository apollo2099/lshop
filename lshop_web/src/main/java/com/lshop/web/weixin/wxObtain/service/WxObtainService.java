/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.web.weixin.wxObtain.service;

import java.util.List;

import com.lshop.web.weixin.common.pojo.WxObtain;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.service.BaseService;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */public interface WxObtainService extends BaseService{
	
	/**
	 * 获得所有
	 */
	public List<WxObtain> findAll(Dto dto) throws ServiceException;

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
	public WxObtain get(Dto dto) throws ServiceException;
	/**
	 * 是否能够领取
	 * @param wxhConfigId
	 * @param openid
	 * @return
	 * @throws ServiceException
	 */
	public boolean canObtain(int wxhConfigId, String openid, int obtainType, String ipAddress, String friendOpenid) throws ServiceException;

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
	public WxObtain save(Dto dto) throws ServiceException;
	
	/**
	 * 更新
	 */
	public WxObtain update(Dto dto)throws ServiceException;
}
