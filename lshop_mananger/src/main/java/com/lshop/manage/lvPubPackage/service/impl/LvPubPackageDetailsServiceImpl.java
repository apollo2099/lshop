/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvPubPackage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.lshop.common.pojo.logic.LvPubPackageDetails;
import com.lshop.manage.lvPubPackage.service.LvPubPackageDetailsService;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.SimplePage;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.service.impl.BaseServiceImpl;
import com.gv.core.util.BeanUtils;
import com.gv.core.util.ObjectUtils;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Service("LvPubPackageDetailsService")
public class LvPubPackageDetailsServiceImpl extends BaseServiceImpl implements LvPubPackageDetailsService {
	/**
	 * 获得所有
	 */
	@Resource private HibernateBaseDAO dao;
	public List<LvPubPackageDetails> findAll(Dto dto) throws ServiceException {
		LvPubPackageDetails lvPubPackageDetails = (LvPubPackageDetails)dto.get("lvPubPackageDetails");
		String pubPackageCode=(String) dto.get("pubPackageCode");
		String hql="from LvPubPackageDetails where 1=1";
		Map<String ,Object> param=new HashMap<String ,Object>();
		if(ObjectUtils.isNotEmpty(pubPackageCode)){
			hql+=" and pubPackageCode=:pubPackageCode";
			param.put("pubPackageCode", pubPackageCode);
		}
		List<LvPubPackageDetails> list= dao.find(hql, param);
		return list;		
	}
	
	
	public Pagination findByPackageCode(Dto dto) throws ServiceException {
		String pubPackageCode=(String) dto.get("pubPackageCode");
		String hql=" select pp.productName as productName,pp.productModel as productModel,pp.pcode as pcode," +
				   " pd.productNum as productNum,pd.pubPackageCode as pubPackageCode,pd.pubProductCode as pubProductCode,pd.code as code " +
				   " from LvPubPackageDetails pd,LvPubProduct pp where pp.code=pd.pubProductCode";
		Map<String ,Object> param=new HashMap<String ,Object>();
		if(ObjectUtils.isNotEmpty(pubPackageCode)){
			hql+=" and pd.pubPackageCode=:pubPackageCode";
			param.put("pubPackageCode", pubPackageCode);
		}		
		return dao.getMapListByHql(hql, param);		
	}
	
	/**
	 * 
	 * @Method: isExistPubPackageDetail 
	 * @Description:  [一句话描述该类的功能]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-4-22 下午3:29:19]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-4-22 下午3:29:19]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @throws ServiceException 
	 * @return Boolean
	 */
	public Boolean isExistPubPackageDetail(Dto dto)throws ServiceException{
		String pubProductCode=(String) dto.get("pubProductCode");
		Map<String,Object> param=new HashMap<String, Object>();
        StringBuilder hql = new StringBuilder("select t from LvPubPackageDetails t where pubProductCode=:pubProductCode ");
        param.put("pubProductCode", pubProductCode);
    	List list=dao.find(hql.toString(), param);
    	if(list!=null&&list.size()>0){
    		return true;
    	}
		return false;
	}

}
