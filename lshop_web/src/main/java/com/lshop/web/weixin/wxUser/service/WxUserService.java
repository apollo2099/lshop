/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.web.weixin.wxUser.service;

import java.util.List;
import java.util.Map;

import com.lshop.web.weixin.common.pojo.WxUser;
import com.lshop.web.weixin.message.BaseMessage;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.service.BaseService;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */public interface WxUserService extends BaseService{
	
	/**
	 * 获得所有
	 */
	public List<WxUser> findAll(Dto dto) throws ServiceException;

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
	public WxUser get(Dto dto) throws ServiceException;
	public WxUser getByOpenid(int wxhConfigId, String openid) throws ServiceException;
	/**
	 * 获取排行榜名次
	 * @param wxhConfigId
	 * @param openid
	 * @return
	 * @throws ServiceException
	 */
	public int getRank(int wxhConfigId, String openid) throws ServiceException;
	public String getNicknameFromWx(int wxhConfigId, String openid) throws ServiceException;
	/**
	 * 获取排行榜
	 * @param wxhConfigId
	 * @return
	 * @throws ServiceException
	 */
	public List<Map<String, Object>> getRankList(int wxhConfigId, int lastNum) throws ServiceException;
	
	/**
	 * 查询领取活动详情
	 * @param wxhConfigId
	 * @param openid
	 * @param textMessage
	 * @return
	 * @throws ServiceException
	 */
	public String getTextMessageXml(int wxhConfigId, BaseMessage baseMessage) throws ServiceException;

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
	public WxUser save(Dto dto) throws ServiceException;
	
	/**
	 * 更新
	 */
	public WxUser update(Dto dto)throws ServiceException;
}
