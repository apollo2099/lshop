/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvPubPackage.service;

import java.util.List;

import com.lshop.common.pojo.logic.LvPubPackageDetails;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.service.BaseService;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */public interface LvPubPackageDetailsService extends BaseService{
	
	/**
	 * 获得所有
	 */
	public List<LvPubPackageDetails> findAll(Dto dto) throws ServiceException;
	public Pagination findByPackageCode(Dto dto) throws ServiceException ;
	public Boolean isExistPubPackageDetail(Dto dto)throws ServiceException;

}
