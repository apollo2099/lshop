/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.web.weixin.wxTextMaterial.service;

import java.util.List;

import com.lshop.web.weixin.common.pojo.WxTextMaterial;
import com.lshop.web.weixin.message.BaseMessage;
import com.lshop.web.weixin.message.send.TextMessage;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.service.BaseService;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */public interface WxTextMaterialService extends BaseService{
	
	/**
	 * 获得所有
	 */
	public List<WxTextMaterial> findAll(Dto dto) throws ServiceException;

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
	public WxTextMaterial get(Dto dto) throws ServiceException;
	public WxTextMaterial getById(int wxhConfigId, int id) throws ServiceException;
	/**
	 * 获取文本消息xml
	 * @param wxhConfigId
	 * @param id
	 * @param textMessage
	 * @return
	 * @throws ServiceException
	 */
	public String getTextMessageXml(int wxhConfigId, int id, BaseMessage baseMessage) throws ServiceException;

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
	public WxTextMaterial save(Dto dto) throws ServiceException;
	
	/**
	 * 更新
	 */
	public WxTextMaterial update(Dto dto)throws ServiceException;
}
