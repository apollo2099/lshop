/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvActivity.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.lshop.common.activity.constant.ActivityConstant;
import com.lshop.common.pojo.logic.LvActivityProduct;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.lvActivity.service.LvActivityProductService;
import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.service.impl.BaseServiceImpl;
import com.gv.core.util.ObjectUtils;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */
@Service("LvActivityProductService")
public class LvActivityProductServiceImpl extends BaseServiceImpl implements LvActivityProductService {
	/**
	 * 获得所有
	 */
	@Resource private HibernateBaseDAO dao;
	public List findAll(Dto dto) throws ServiceException {
		List list=null;
		String activityCode=(String) dto.get("activityCode"); 
		if(ObjectUtils.isNotEmpty(activityCode)){
			String hql=" select t.id as id,t.productName as productName,t.currency as currency,t.price as price,t.storeId as storeId,s.name as storeName" +
					   " from LvActivityProduct t,LvStore s where t.storeId=s.storeFlag "+
					   " and t.activityCode =:activityCode ";
			Map<String,Object> param=new HashMap<String, Object>();
			param.put("activityCode", activityCode);
			list= dao.getMapListByHql(hql, param).getList();
		}
		return list;
	}

	
	/**
	 * 
	 * @Method: findByActivityCode 
	 * @Description:  [根据活动编码查询活动商品信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-22 下午6:28:53]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-22 下午6:28:53]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public List<LvActivityProduct> findByActivityCode(String activityCode){
		List<LvActivityProduct> list=null;
		if(ObjectUtils.isNotEmpty(activityCode)){
	           String hql="from LvActivityProduct where activityCode=:activityCode";
			   Map<String,Object> param=new HashMap<String, Object>();
			   param.put("activityCode", activityCode);
			   list=dao.find(hql, param);
		}
		return list;
	}
	
	/**
	 * 
	 * @Method: saveBatch 
	 * @Description:  [批量保存活动产品信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-9 下午2:20:10]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-9 下午2:20:10]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param productList 活动商品集合
	 * @param acitivityCode 活动编号
	 * @return Boolean
	 */
	public Boolean saveBatch(List<LvActivityProduct> productList,String acitivityCode){
		 if(ObjectUtils.isNotEmpty(productList)){
			 for (LvActivityProduct activityProduct : productList) {
				 activityProduct.setActivityCode(acitivityCode);
				 activityProduct.setCode(CodeUtils.getCode());
				 activityProduct.setCreateTime(new Date());
				 dao.save(activityProduct);
			}
		 }
		 return true;
	}
	
	
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
	public Boolean isEffectiveActivity(String productCode){		
		Date nowDate=new Date();
		String hql="SELECT o.id FROM LvActivity o,LvActivityProduct t " +
				   " WHERE o.code=t.activityCode " +
				   " AND o.status=:status " +
				   " AND o.startTime<=:startTime " +
				   " AND o.endTime>=:endTime " +
				   " and t.productCode=:productCode ";
		Map<String,Object> param=new HashMap<String, Object>();
		param.put("status",ActivityConstant.STATUS_USE);
		param.put("startTime",nowDate );
		param.put("endTime",nowDate );
		param.put("productCode", productCode);
		List list= dao.find(hql, param);
		if(list!=null&&list.size()>0){
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @Method: isEffectiveActivity 
	 * @Description:  [根据商品编码查找是否存在启动的活动信息(批量)]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-22 下午5:39:55]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-22 下午5:39:55]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param productList 商品编码集合
	 * @return Boolean
	 */
	public Boolean isEffectiveActivity(List<LvActivityProduct> productList){
		if(ObjectUtils.isNotEmpty(productList)){
			for (LvActivityProduct lvActivityProduct : productList) {
				if(ObjectUtils.isNotEmpty(lvActivityProduct)){
					Boolean isFlag=this.isEffectiveActivity(lvActivityProduct.getProductCode());
					if(isFlag){
						return true;
					}else{
						continue;
					}
				}
			}
		}
		return false;
	}
}
