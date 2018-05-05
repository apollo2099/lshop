/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvActivity.service;

import java.util.List;

import com.lshop.common.pojo.logic.LvActivityProduct;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.service.BaseService;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */public interface LvActivityProductService extends BaseService{
	
	/**
	 * 获得所有
	 */
	public List findAll(Dto dto) throws ServiceException;

	public List<LvActivityProduct> findByActivityCode(String activityCode);
	/**
	 * 
	 * @Method: saveBatch 
	 * @Description:  [一句话描述该类的功能]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-9 下午2:20:10]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-9 下午2:20:10]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param productList 活动商品集合
	 * @param acitivityCode 活动编号
	 * @return Boolean
	 */
	public Boolean saveBatch(List<LvActivityProduct> productList,String acitivityCode)throws ServiceException;
	
	/**
	 * 
	 * @Method: isEffectiveActivity 
	 * @Description:  [根据商品编码查找是否存在启动的活动信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-22 下午5:39:55]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-22 下午5:39:55]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param productCode 商品编码
	 * @return Boolean
	 */
	public Boolean isEffectiveActivity(String productCode)throws ServiceException;
	
	/**
	 * 
	 * @Method: isEffectiveActivity 
	 * @Description:  [根据商品编码查找是否存在启动的活动信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-22 下午5:39:55]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-22 下午5:39:55]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param productCode 商品编码
	 * @return Boolean
	 */
	public Boolean isEffectiveActivity(List<LvActivityProduct> productList)throws ServiceException;
}
