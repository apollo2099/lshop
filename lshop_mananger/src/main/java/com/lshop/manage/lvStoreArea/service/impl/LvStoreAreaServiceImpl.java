/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvStoreArea.service.impl;

import java.util.ArrayList;
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
import com.lshop.common.pojo.logic.LvStoreArea;
import com.lshop.common.util.StoreHelpUtil;
import com.lshop.manage.lvStoreArea.service.LvStoreAreaService;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */@Service("LvStoreAreaService")
public class LvStoreAreaServiceImpl extends BaseServiceImpl implements LvStoreAreaService {
	/**
	 * 获得所有
	 */
	@Resource private HibernateBaseDAO dao;
	public List<LvStoreArea> findAll(Dto dto) throws ServiceException {
		LvStoreArea lvStoreArea=(LvStoreArea) dto.get("lvStoreArea");
		Map<String ,Object> params=new HashMap<String ,Object>();
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
        StringBuilder hql = new StringBuilder("from LvStoreArea t where (parentCode is null or parentCode='')");
		if(lvStoreArea!=null){
			if(ObjectUtils.isNotEmpty(lvStoreArea.getStoreId())){
				 hql.append(" and storeId=:storeId");
				 params.put("storeId", lvStoreArea.getStoreId());
			}
		}
		hql.append(StoreHelpUtil.getStoreRelevanceString(dto.getAsString("flag"),"storeId","t"));   
		List<LvStoreArea> list= dao.find(hql.toString(), params);
		return list;
	}
	
	/**
	 * 获得所有城市信息
	 */
	public List<LvStoreArea> findAllCity(Dto dto) throws ServiceException{
		Integer countryId=(Integer) dto.get("countryId");
		LvStoreArea lvStoreArea= (LvStoreArea) dao.findUniqueByProperty(LvStoreArea.class, "areaId", countryId);
		String hql=" from LvStoreArea where parentCode='"+lvStoreArea.getCode()+"'";
		List<LvStoreArea> list=(List<LvStoreArea>)dao.find(hql, null);
		return list;
	}
	
	/**
	 * 获得所有洲国家城市信息
	 */
	public List<LvStoreArea> getAllCity(Dto dto) throws ServiceException{
		LvStoreArea storeArea=(LvStoreArea) dto.get("lvStoreArea");
		String hql="from LvStoreArea where (parentCode is null or parentCode='')";
		if(storeArea!=null){
			if(ObjectUtils.isNotEmpty(storeArea.getStoreId())){
				hql+=" and storeId='"+storeArea.getStoreId()+"'";
			}
		}
		
		List<LvStoreArea> list= dao.find(hql, null);
		List<LvStoreArea> temp=new ArrayList<LvStoreArea>();
		for (LvStoreArea lvStoreArea : list) {
			hql=" from LvStoreArea where parentCode='"+lvStoreArea.getCode()+"'";
			List<LvStoreArea> listTmp=(List<LvStoreArea>)dao.find(hql, null);
			
			
			List<LvStoreArea> temp2=new ArrayList<LvStoreArea>();
			for(LvStoreArea sa : listTmp){
				hql=" from LvStoreArea where parentCode='"+sa.getCode()+"'";
				List<LvStoreArea> listTmp2=(List<LvStoreArea>)dao.find(hql, null);
				sa.setCityList(listTmp2);
				temp2.add(sa);
			}
			
			lvStoreArea.setCityList(temp2);
			temp.add(lvStoreArea);
		}
		return temp;
	}
	/**
	 * 分页查询
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Pagination findPage(Dto dto) throws ServiceException {
		SimplePage page = (SimplePage)dto.get("page");
		 LvStoreArea lvStoreArea = (LvStoreArea)dto.get("model");
		 Map<String ,Object> params=new HashMap<String ,Object>();
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
        StringBuilder sql = new StringBuilder("select t from LvStoreArea t where 1=1 and areaId<>'' ");
        if(lvStoreArea!=null){
        	if(ObjectUtils.isNotEmpty(lvStoreArea.getAreaName())) {
	        	sql.append(" and  t.areaName like :areaName");
	        	params.put("areaName", "%"+lvStoreArea.getAreaName().trim()+"%");
	        }
        	if(ObjectUtils.isNotEmpty(lvStoreArea.getCode())){
        		sql.append(" and t.parentCode=:code");
        		params.put("code",lvStoreArea.getCode() );
        	}
        }
        sql.append(StoreHelpUtil.getStoreRelevanceString(dto.getAsString("flag"),"storeId","t"));    	
        sql.append(" order by t.orderValue desc,t.createTime desc ");
		Finder finder = Finder.create(sql.toString());
		finder.setParams(params);
		Pagination pag = dao.find(finder, page.getPageNum(), page.getNumPerPage());
		return pag;
	}
	
	public Pagination findAreaCity(Dto dto) throws ServiceException{
		SimplePage page = (SimplePage)dto.get("page");
		 LvStoreArea lvStoreArea = (LvStoreArea)dto.get("model");
		 Map<String ,Object> params=new HashMap<String ,Object>();
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
       StringBuilder sql = new StringBuilder("select t from LvStoreArea t where 1=1 ");
	        	if(ObjectUtils.isNotEmpty(lvStoreArea.getAreaName())) {
		        	sql.append(" and  t.areaName like :areaName ");
		        	params.put("areaName","%"+lvStoreArea.getAreaName()+"%" );
		        }
	        	if(ObjectUtils.isNotEmpty(lvStoreArea.getParentCode())){
	        		sql.append(" and  t.parentCode=:parentCode");
	        		params.put("parentCode",lvStoreArea.getParentCode() );
	        	}
//	    sql.append(StoreHelpUtil.getStoreRelevanceString(dto.getAsString("flag")));
        sql.append(" order by t.orderValue desc,t.createTime desc ");
		Finder finder = Finder.create(sql.toString());
		finder.setParams(params);
		Pagination pag = dao.find(finder, page.getPageNum(), page.getNumPerPage());
		return pag;
	}
	
	/**
	 * 获得单独的实体信息
	 */
	public LvStoreArea get(Dto dto) throws ServiceException {
		 LvStoreArea lvStoreArea = (LvStoreArea)dto.get("model");
		 lvStoreArea = (LvStoreArea)dao.load(LvStoreArea.class, lvStoreArea.getId());
		return  lvStoreArea;
	}
	/**
	 * 删除
	 * @param dto
	 * @throws ServiceException
	 */
	public void del(Dto dto) throws ServiceException {
		 LvStoreArea lvStoreArea = (LvStoreArea)dto.get("model");
		 dao.delete(lvStoreArea);
	}


	/**
	 * 保存
	 */
	public LvStoreArea save(Dto dto) throws ServiceException {
		 LvStoreArea lvStoreArea = (LvStoreArea)dto.get("model");
		dao.save( lvStoreArea);
		return   lvStoreArea;
	}
	/**
	 * 更新
	 */
	public LvStoreArea update(Dto dto) throws ServiceException {
		 LvStoreArea lvStoreArea = (LvStoreArea)dto.get("model");
		dao.update(lvStoreArea);
		return  lvStoreArea;
	}
	public List<LvStoreArea> getStoreArea(Dto dto)throws ServiceException{
		Integer countryId=dto.getAsInteger("countryId");
		String hql="from LvStoreArea where parentCode in(select code from LvStoreArea where areaId="+countryId+")";
		List<LvStoreArea> list=dao.find(hql, null);
		return list;
	}
	
	/**
	 * 
	 * @Method: isExistCity 
	 * @Description:  [判断城市名称是否重复]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2013-2-5 上午11:48:46]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2013-2-5 上午11:48:46]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Boolean isExistCity(Dto dto) throws ServiceException {
		LvStoreArea lvStoreArea = (LvStoreArea)dto.get("model");
		String hql="from LvStoreArea where areaName=:areaName and parentCode=:parentCode)";
		Map param=new HashMap();
		param.put("areaName",lvStoreArea.getAreaName());
		param.put("parentCode",lvStoreArea.getParentCode());
		List list= dao.find(hql, param);
		if(list!=null&&list.size()>0){
			return true;
		}
		return false;
	}
	
	public Boolean isExistCityEn(Dto dto) throws ServiceException {
		LvStoreArea lvStoreArea = (LvStoreArea)dto.get("model");
		String hql="from LvStoreArea where areaNameEn=:areaNameEn and parentCode=:parentCode)";
		Map param=new HashMap();
		param.put("areaNameEn",lvStoreArea.getAreaNameEn());
		param.put("parentCode",lvStoreArea.getParentCode());
		List list= dao.find(hql, param);
		if(list!=null&&list.size()>0){
			return true;
		}
		return false;
	}
	/**
	 * 
	 * @Method: isExistCountry 
	 * @Description:  [判断国家名称是否重复]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2013-2-5 上午11:49:09]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2013-2-5 上午11:49:09]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Boolean isExistCountry(Dto dto) throws ServiceException {
		LvStoreArea lvStoreArea = (LvStoreArea)dto.get("model");
		String hql="from LvStoreArea where areaName=:areaName and areaId=:areaId and storeId=:storeId";
		Map param=new HashMap();
		param.put("areaName",lvStoreArea.getAreaName());
		param.put("areaId",lvStoreArea.getAreaId());
		param.put("storeId",lvStoreArea.getStoreId());
		List list= dao.find(hql, param);
		if(list!=null&&list.size()>0){
			return true;
		}
		return false;
	}

    /**
     * 
     * @Method: isExistCityList 
     * @Description:  [判断是否存在城市信息]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2013-2-21 下午05:11:49]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2013-2-21 下午05:11:49]   
     * @UpdateRemark: [说明本次修改内容]  
     * @throws
     */
	@Override
	public Boolean isExistCityList(Dto dto) throws ServiceException {
		LvStoreArea lvStoreArea=(LvStoreArea) dto.get("lvStoreArea");
		lvStoreArea=get(dto);
		if(ObjectUtils.isNotEmpty(lvStoreArea.getCode())){
			String hql=" from LvStoreArea where parentCode='"+lvStoreArea.getCode()+"'";
			List<LvStoreArea> list=(List<LvStoreArea>)dao.find(hql, null);
			if(list!=null&&list.size()>0){
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @Method: isExistStoreList 
	 * @Description:  [判断城市下是否存在店铺信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2013-2-21 下午05:12:15]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2013-2-21 下午05:12:15]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Boolean isExistStoreList(Dto dto) throws ServiceException {
		LvStoreArea lvStoreArea=(LvStoreArea) dto.get("lvStoreArea");
		lvStoreArea=get(dto);
		if(ObjectUtils.isNotEmpty(lvStoreArea.getAreaName())){
			String hql="from LvStore where cityCode=:cityCode ";
			Map param=new HashMap();
			param.put("cityCode",lvStoreArea.getCode());
			List list= dao.find(hql, param);
			if(list!=null&&list.size()>0){
				return true;
			}
		}
		return false;
	}

}
