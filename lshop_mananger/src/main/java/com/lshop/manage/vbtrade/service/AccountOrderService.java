/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.vbtrade.service;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.service.BaseService;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */
 public interface AccountOrderService extends BaseService{
	
	/**
	 * 分页查询
	 */
	public Pagination findPage(Dto dto) throws ServiceException;
	
	/**
	 * 充值交易记录
	 */
	public Pagination buyList(Dto dto) throws ServiceException;
	
	/**
	 * 消费交易记录
	 */
	public Pagination consumeList(Dto dto) throws ServiceException;
	
}
