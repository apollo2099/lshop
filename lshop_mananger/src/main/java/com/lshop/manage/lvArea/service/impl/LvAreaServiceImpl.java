package com.lshop.manage.lvArea.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvArea;
import com.lshop.common.pojo.logic.LvCarriageSet;
import com.lshop.manage.lvArea.service.LvAreaService;


@Service("LvAreaService")
public class LvAreaServiceImpl implements LvAreaService {
	private static final Log logger	= LogFactory.getLog(LvAreaServiceImpl.class);
	@Resource 
	private HibernateBaseDAO dao;
	@Override
	public List<LvArea> getAll(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvAreaServiceImpl.getAll() method begin*****");
		}
		LvArea lvArea=(LvArea) dto.get("lvArea");
		Map<String ,Object> params=new HashMap<String ,Object>();
		StringBuilder hql = new StringBuilder("from LvArea where (parentid is null or parentid='') ");
		if(lvArea!=null){
			if(ObjectUtils.isNotEmpty(lvArea.getStoreId())){
				 hql.append(" and storeId=:storeId");
				 params.put("storeId", lvArea.getStoreId());
			}
			if(ObjectUtils.isNotEmpty(lvArea.getId())){
				 hql.append(" and id=:id");
				 params.put("id", lvArea.getId());
			}
		}
		List<LvArea> list = dao.find(hql.toString(), params);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvAreaServiceImpl.getAll() method end*****");
		}	
	    return list;
	}
	
	public List<LvArea> getAllArea(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvAreaServiceImpl.getAllArea() method begin*****");
		}
		Map<String ,Object> params=new HashMap<String ,Object>();
		StringBuilder hql = new StringBuilder("from LvArea where 1=1");
		List<LvArea> list = dao.find(hql.toString(), params);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvAreaServiceImpl.getAllArea() method end*****");
		}	
	    return list;
	}
	
	/**
	 * 
	 * @Method: findCountryByCode 
	 * @Description:  [根据编码code查询国家信息]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2015-4-1 下午2:31:28]   
	 * @UpdateUser:   [liaoxj]     
	 * @UpdateDate:   [2015-4-1 下午2:31:28]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param countryCode 国家code
	 * @return LvArea
	 */
	public LvArea findCountryByCode(String countryCode){
		if (logger.isInfoEnabled()){
			logger.info("***** LvAreaServiceImpl.findCountryByCode() method begin*****");
		}
		LvArea lvArea=null;
		if(ObjectUtils.isNotEmpty(countryCode)){
			StringBuilder hql = new StringBuilder("from LvArea where (parentid is null or parentid='') and code=:code ");
			Map<String,Object> params=new HashMap<String, Object>();
			params.put("code", countryCode.trim());
			List<LvArea> list = dao.find(hql.toString(), params);
			if(list!=null&&list.size()>0){
				lvArea=list.get(0);
			}
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvAreaServiceImpl.findCountryByCode() method end*****");
		}
		return lvArea;
	}
	
	/**
	 * 
	 * @Method: findProvinceByCode 
	 * @Description:  [根据国家id和省份code查询省份信息]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2015-4-1 下午2:32:00]   
	 * @UpdateUser:   [liaoxj]     
	 * @UpdateDate:   [2015-4-1 下午2:32:00]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param parentid 国家id
	 * @param priviceCode 省份code
	 * @return LvArea
	 */
	public LvArea findProvinceByCode(Integer parentid,String priviceCode){
		if (logger.isInfoEnabled()){
			logger.info("***** LvAreaServiceImpl.findProvinceByCode() method begin*****");
		}
		LvArea lvArea=null;
		if(ObjectUtils.isNotEmpty(parentid)){
			StringBuilder hql = new StringBuilder("from LvArea where parentid=:parentid and code=:code ");
			Map<String,Object> params=new HashMap<String, Object>();
			params.put("parentid", parentid);
			params.put("code", priviceCode.trim());
			List<LvArea> list = dao.find(hql.toString(), params);
			if(list!=null&&list.size()>0){
				lvArea=list.get(0);
			}
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvAreaServiceImpl.findProvinceByCode() method end*****");
		}
		return lvArea;
	}
	
	/**
	 * 
	 * @Method: findProvinceByName 
	 * @Description:  [根据国家id和省份名称查询省份信息]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2015-4-1 下午2:32:04]   
	 * @UpdateUser:   [liaoxj]     
	 * @UpdateDate:   [2015-4-1 下午2:32:04]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param parentid 国家id
	 * @param priviceName 省份名称
	 * @return LvArea
	 */
	public LvArea findProvinceByName(Integer parentid,String priviceName){
		if (logger.isInfoEnabled()){
			logger.info("***** LvAreaServiceImpl.findProvinceByName() method begin*****");
		}
		LvArea lvArea=null;
		if(ObjectUtils.isNotEmpty(parentid)){
			StringBuilder hql = new StringBuilder("from LvArea where parentid=:parentid and nameen=:nameen ");
			Map<String,Object> params=new HashMap<String, Object>();
			params.put("parentid", parentid);
			params.put("nameen",priviceName);
			List<LvArea> list = dao.find(hql.toString(), params);
			if(list!=null&&list.size()>0){
				lvArea=list.get(0);
			}
		}
		if (logger.isInfoEnabled()){
			logger.info("***** LvAreaServiceImpl.findProvinceByName() method end*****");
		}
		return lvArea;
	}
	
	/**
	 * 
	 * @Method: getProvince 
	 * @Description:  [根据国家id查找省份/洲]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2013-5-27 上午10:46:07]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2013-5-27 上午10:46:07]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public List<LvArea> getProvince(Dto dto ) throws ServiceException {
		Integer countryId=(Integer) dto.get("countryId");
		Map<String ,Object> params=new HashMap<String ,Object>();
		String hql = "from LvArea where 1=1";
		if(ObjectUtils.isNotEmpty(countryId)){
			hql+=" and parentid=:parentid";
			params.put("parentid", countryId);
		}
		List<LvArea> list = dao.find(hql, params);
		return list;
	}
	
	/**
	 * 
	 * @Method: get 
	 * @Description:  [根据id获取区域信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2013-3-28 下午02:14:40]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2013-3-28 下午02:14:40]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public LvArea get(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvAreaServiceImpl.get() method begin*****");
		}
		LvArea lvArea=(LvArea) dto.get("lvArea");
		lvArea=(LvArea) dao.load(LvArea.class, lvArea.getId());
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvAreaServiceImpl.get() method end*****");
		}
		return lvArea;
	}
	
	

}
