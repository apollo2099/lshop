/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvTplModel.service;

import java.io.IOException;
import java.util.List;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.service.BaseService;
import com.lshop.common.pojo.logic.LvTplModel;

import freemarker.template.TemplateException;

/**
 * @author 好视网络-网站研发部：唐迪
 * @version 1.0
 * @since 1.0
 */public interface LvTplModelService extends BaseService{
	
	/**
	 * 获得所有
	 */
	public List<LvTplModel> findAll(Dto dto) throws ServiceException;

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
	public LvTplModel get(Dto dto) throws ServiceException;

	/**
	 * 删除
	 * @param dto
	 * @throws ServiceException
	 */
	public Integer del(Dto dto) throws ServiceException;

	/**
	 * 批量删除
	 * @param dto
	 * @throws ServiceException
	 */
	public void delList(Dto dto) throws ServiceException;

	/**
	 * 保存
	 */
	public LvTplModel save(Dto dto) throws ServiceException;
	
	/**
	 * 更新
	 */
	public LvTplModel update(Dto dto)throws ServiceException;
    /**
     * 获取商铺标识来获取相应的默认模板块
     */
	public LvTplModel getDefaultTplModel(Dto dto)throws ServiceException;
	/**
	 * 设置商铺默认模板
	 */
	public void updateDefaultSet(Dto dto)throws ServiceException;
	/**
	 * 检查模板块名称是否存在 true表示存在，false表示不存在
	 */
	public boolean findCheckName(Dto dto)throws ServiceException;
	/**
	 * 写入模板与内容
	 */
	public void saveUpdateImportExcel(Dto dto)throws ServiceException, IOException, TemplateException;
}
