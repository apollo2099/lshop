package com.lshop.manage.lvUser.service;

/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */


import java.util.List;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.lshop.common.pojo.logic.LvEmailTpl;
import com.lshop.common.pojo.logic.LvUser;

/**
 * @author 好视网络-软件二部：唐迪
 * @version 1.0
 * @since 1.0
 */public interface LvUserService {
	
	/**
	 * 获得所有
	 */
	public List findAll(Dto dto) throws ServiceException;

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
	public LvUser get(Dto dto) throws ServiceException;

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
	public LvUser save(Dto dto) throws ServiceException;
	
	/**
	 * 更新
	 */
	public LvUser update(Dto dto)throws ServiceException;
	
	/**
	 * 导出
	 * @param dto
	 * @return
	 */
	public List<String[]> onExportByIds(Dto dto);
	/**
	 * 获取邮件模板
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public List<LvEmailTpl> getEmailTplList(Dto dto) throws ServiceException;
	/**
	 * 添加优惠券与用户关系
	 * @param dto
	 * @return
	 */
	public Integer addUserCoupon(Dto dto);
	/**
	 * 
	 */
	public Pagination findUserCoupon(Dto dto);
}
