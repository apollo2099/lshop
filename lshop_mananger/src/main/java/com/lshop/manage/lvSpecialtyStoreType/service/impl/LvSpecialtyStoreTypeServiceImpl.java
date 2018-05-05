/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvSpecialtyStoreType.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.SimplePage;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.service.impl.BaseServiceImpl;
import com.gv.core.util.BeanUtils;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvShopSubject;
import com.lshop.common.pojo.logic.LvSpecialtyStore;
import com.lshop.common.pojo.logic.LvSpecialtyStoreType;
import com.lshop.manage.lvSpecialtyStore.service.LvSpecialtyStoreService;
import com.lshop.manage.lvSpecialtyStoreType.service.LvSpecialtyStoreTypeService;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Service("LvSpecialtyStoreTypeService")
public class LvSpecialtyStoreTypeServiceImpl extends BaseServiceImpl implements LvSpecialtyStoreTypeService {
	@Resource 
	private HibernateBaseDAO dao;
	@Resource 
	private LvSpecialtyStoreService lvSpecialtyStoreService;
	 /**
	 * 获得所有专卖店父级信息
	 */
	public List<LvSpecialtyStoreType> findAllPareat(Dto dto) throws ServiceException {
		String hql="from LvSpecialtyStoreType where parentCode='0'";
		List<LvSpecialtyStoreType> list=dao.find(hql, null);
		return list;
	}
	
	/**
	 * 
	 * @Method: findAll 
	 * @Description:  [查询所有非父级专卖店列表信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-12-26 下午01:24:02]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-12-26 下午01:24:02]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public List<LvSpecialtyStoreType> findAll(Dto dto) throws ServiceException {
		String hql="from LvSpecialtyStoreType where parentCode<>'0'";
		List<LvSpecialtyStoreType> list=dao.find(hql, null);
		return list;
	}
	/**
	 * 
	 * @Method: findAllByPareat 
	 * @Description:  [根据专卖店父级查询专卖店列表信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-12-26 下午01:24:13]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-12-26 下午01:24:13]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public List<LvSpecialtyStoreType> findAllByPareat(Dto dto) throws ServiceException{
		String parentCode=(String) dto.get("parentCode");
		List<LvSpecialtyStoreType> list=null;
		if(ObjectUtils.isNotEmpty(parentCode)){
			String hql="from LvSpecialtyStoreType where parentCode<>'0' and parentCode='"+parentCode+"'";
			list=dao.find(hql, null);
		}
		return list;
	}
	
	
	/**
	 * 分页查询
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Pagination findPage(Dto dto) throws ServiceException {
		SimplePage page = (SimplePage)dto.get("page");
		 LvSpecialtyStoreType lvSpecialtyStoreType = (LvSpecialtyStoreType)dto.get("model");
		 Map<String ,Object> params=new HashMap<String ,Object>();
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
        StringBuilder sql = new StringBuilder("select t from LvSpecialtyStoreType t where 1=1 ");
        if(lvSpecialtyStoreType!=null){
        	if(ObjectUtils.isNotEmpty(lvSpecialtyStoreType.getStoreTypeName())) {
	        	sql.append(" and  t.storeTypeName like :storeTypeName");
	        	params.put("storeTypeName", "%"+lvSpecialtyStoreType.getStoreTypeName()+"%");
	        }
        	if(ObjectUtils.isNotEmpty(lvSpecialtyStoreType.getParentCode())) {
	        	sql.append(" and  t.parentCode =:parentCode");
	        	params.put("parentCode", lvSpecialtyStoreType.getParentCode());
	        }
        }
        
        sql.append(" and t.parentCode<>'0'");
        sql.append(" order by t.createTime desc ");
		Finder finder = Finder.create(sql.toString());
		finder.setParams(params);
		Pagination pag = dao.find(finder, page.getPageNum(), page.getNumPerPage());
		return pag;
	}
	/**
	 * 获得单独的实体信息
	 */
	public LvSpecialtyStoreType get(Dto dto) throws ServiceException {
		 LvSpecialtyStoreType lvSpecialtyStoreType = (LvSpecialtyStoreType)dto.get("model");
		 lvSpecialtyStoreType = (LvSpecialtyStoreType)dao.load(LvSpecialtyStoreType.class, lvSpecialtyStoreType.getId());
		return  lvSpecialtyStoreType;
	}
	
	/**
	 * 
	 * @Method: getSpecialtyStoreType 
	 * @Description:  [根据类型code查询专卖店信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-12-26 下午01:51:19]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-12-26 下午01:51:19]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public LvSpecialtyStoreType getSpecialtyStoreType(Dto dto )throws ServiceException{
		String storeTypeCode=(String) dto.get("storeTypeCode");
		LvSpecialtyStoreType lvSpecialtyStoreType=null;
		if (ObjectUtils.isNotEmpty(storeTypeCode)) {
			String hql="from LvSpecialtyStoreType where code='"+storeTypeCode+"'";
			lvSpecialtyStoreType=(LvSpecialtyStoreType) dao.findUnique(hql, null);
		}
	    return lvSpecialtyStoreType;	
	}
	/**
	 * 删除
	 * @param dto
	 * @throws ServiceException
	 */
	public Boolean del(Dto dto) throws ServiceException {
		 LvSpecialtyStoreType lvSpecialtyStoreType = get(dto);
		 dto.put("storeTypeCode", lvSpecialtyStoreType.getCode());
		 List<LvSpecialtyStore> list= lvSpecialtyStoreService.findAllByType(dto);
		 if(list!=null&&list.size()>0){
			 return false;
		 }
		dao.delete(  lvSpecialtyStoreType);
		return true;
	}

	public void delList(Dto dto) throws ServiceException {

	}
	/**
	 * 保存
	 */
	public LvSpecialtyStoreType save(Dto dto) throws ServiceException {
		LvSpecialtyStoreType lvSpecialtyStoreType = (LvSpecialtyStoreType) dto.get("model");
		if (ObjectUtils.isNotEmpty(dto.getAsString("flag"))) {// 店铺标示
			lvSpecialtyStoreType.setStoreId(dto.getAsString("flag"));
		}
		dao.save(lvSpecialtyStoreType);
		return lvSpecialtyStoreType;
	}
	/**
	 * 更新
	 */
	public LvSpecialtyStoreType update(Dto dto) throws ServiceException {
		 LvSpecialtyStoreType lvSpecialtyStoreType = (LvSpecialtyStoreType)dto.get("model");
		dao.update(lvSpecialtyStoreType);
		return  lvSpecialtyStoreType;
	}
	
	/**
	 * 根据专卖店分类名称判断是否重复
	 */
	public Boolean isExsitSpecialtyStoreTypeByName(Dto dto)throws ServiceException{
		LvSpecialtyStoreType lvSpecialtyStoreType = (LvSpecialtyStoreType)dto.get("model");
		String hql="from LvSpecialtyStoreType where storeTypeName=:storeTypeName";
		Map param=new HashMap();
		param.put("storeTypeName",lvSpecialtyStoreType.getStoreTypeName().trim());
		List list= dao.find(hql, param);
		if(list!=null&&list.size()>0){
			return true;
		}
		return false;
	}


}
