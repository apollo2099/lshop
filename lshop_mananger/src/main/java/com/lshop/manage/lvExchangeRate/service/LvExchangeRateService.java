/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvExchangeRate.service;

import java.util.List;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.service.BaseService;
import com.lshop.common.pojo.logic.LvExchangeRate;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */public interface LvExchangeRateService extends BaseService{
	
	/**
	 * 获得所有
	 */
	public List<LvExchangeRate> findAll(Dto dto) throws ServiceException;

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
	public LvExchangeRate get(Dto dto) throws ServiceException;

	/**
	 * 删除
	 * @param dto
	 * @throws ServiceException
	 */
	public void del(Dto dto) throws ServiceException;

	/**
	 * 保存
	 */
	public LvExchangeRate save(Dto dto) throws ServiceException;
	
	/**
	 * 更新
	 */
	public LvExchangeRate update(Dto dto)throws ServiceException;
    /**
     * 
     * @Method: isExistRate 
     * @Description:  [根据币种查询当前币种兑换率是否存在]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-12-18 上午10:25:21]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-12-18 上午10:25:21]   
     * @UpdateRemark: [说明本次修改内容]  
     * @param dto
     * @throws ServiceException 
     * @return Boolean
     */
	public Boolean isExistRate(Dto dto)throws ServiceException;
}
