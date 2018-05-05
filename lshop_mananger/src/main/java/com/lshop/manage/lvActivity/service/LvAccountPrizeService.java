/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvActivity.service;

import com.lshop.common.pojo.logic.LvAccountPhysicalTicket;
import com.lshop.common.pojo.logic.LvAccountPrize;
import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.gv.core.service.BaseService;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */
public interface LvAccountPrizeService extends BaseService{

	/**
	 * 获得单独的实体信息
	 */
	public LvAccountPrize get(Dto dto) throws ServiceException;
	/**
	 * 根据活动code和用户code获取用户奖品信息
	 */
	public LvAccountPrize findByCode(Dto dto) throws ServiceException;
	public LvAccountPhysicalTicket findByAcountPrizeCode(Dto dto) throws ServiceException;
	public void updateAccountPrizeStatus(Dto dto)throws ServiceException;
}
