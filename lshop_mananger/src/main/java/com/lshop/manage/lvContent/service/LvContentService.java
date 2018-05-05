/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvContent.service;

import java.io.IOException;
import java.util.List;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.service.BaseService;
import com.lshop.common.pojo.logic.LvContent;

import freemarker.template.TemplateException;

/**
 * @author 好视网络-网站研发部：唐迪
 * @version 1.0
 * @since 1.0
 */public interface LvContentService extends BaseService{
	
	/**
	 * 获得所有
	 */
	public List<LvContent> findAll(Dto dto) throws ServiceException;

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
	public LvContent get(Dto dto) throws ServiceException;

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
	public LvContent save(Dto dto) throws ServiceException;
	
	/**
	 * 更新
	 */
	public LvContent update(Dto dto)throws ServiceException;
	/**
	 * 获取默认模板的明细
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public List findDefulatTplDetail(Dto dto )throws ServiceException;
	
	/**
	 * 根据内容与模板生成页面
	 */
	public void  doBuild(Dto dto)throws ServiceException,IOException,TemplateException;
}
