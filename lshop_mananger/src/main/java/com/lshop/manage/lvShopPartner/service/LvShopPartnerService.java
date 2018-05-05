/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvShopPartner.service;

import java.util.List;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.service.BaseService;
import com.lshop.common.pojo.logic.LvShopPartner;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */public interface LvShopPartnerService extends BaseService{
	
	/**
	 * 获得所有
	 */
	public List<LvShopPartner> findAll(Dto dto) throws ServiceException;

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
	public LvShopPartner get(Dto dto) throws ServiceException;

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
	public LvShopPartner save(Dto dto) throws ServiceException;
	
	/**
	 * 更新
	 */
	public LvShopPartner update(Dto dto)throws ServiceException;
	/**
	 * 
	 * @Method: upload 
	 * @Description:  [图片上传]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-12-18 下午04:15:04]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-12-18 下午04:15:04]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @return 
	 * @return String
	 */
	public String upload(Dto dto);
}
