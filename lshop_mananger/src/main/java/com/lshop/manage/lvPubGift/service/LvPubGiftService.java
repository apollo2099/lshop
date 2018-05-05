/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvPubGift.service;

import java.util.List;

import com.lshop.common.pojo.logic.LvPubGift;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.service.BaseService;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */public interface LvPubGiftService extends BaseService{
	
	/**
	 * 获得所有
	 */
	public List<LvPubGift> findAll(Dto dto) throws ServiceException;

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
	public LvPubGift get(Dto dto) throws ServiceException;

	/**
	 * 保存
	 */
	public LvPubGift save(Dto dto) throws ServiceException;
	
	/**
	 * 更新
	 */
	public LvPubGift update(Dto dto)throws ServiceException;
	/**
	 * 
	 * @Method: updateStatus 
	 * @Description:  [修改赠品状态服务]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-6 下午6:26:27]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-6 下午6:26:27]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return LvPubGift
	 */
	public LvPubGift updateStatus(Dto dto)throws ServiceException;
}
