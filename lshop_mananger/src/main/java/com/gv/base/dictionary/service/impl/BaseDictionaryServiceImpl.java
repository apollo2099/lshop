/**
 * 
 */
package com.gv.base.dictionary.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gv.base.dictionary.service.BaseDictionaryService;
import com.gv.base.dictionary.service.InitDictionaryService;
import com.gv.base.springSecurity.common.pojo.BaseDictionarys;
import com.gv.base.util.Constants;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.call.StatusCode;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.service.impl.BaseServiceImpl;
import com.gv.core.util.ObjectUtils;
import com.gv.core.util.StringUtil;

/**
 * @author panjin
 * 
 */
@Service("BaseDictionaryService")
public class BaseDictionaryServiceImpl extends BaseServiceImpl implements BaseDictionaryService {
	
	private static final Log logger	= LogFactory.getLog(BaseDictionaryServiceImpl.class);
	@Resource private HibernateBaseDAO dao;
	@Resource private InitDictionaryService dictionaryService;
	
	public void delete(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** BaseDictionaryServiceImpl.delete() method begin*****");
		}
		
		String ids = dto.getAsString("ids");
		String hql = "delete from BaseDictionarys where id in ("+ids+")";
		dao.delete(hql,null);
		dictionaryService.init();
		if (logger.isInfoEnabled()){
			logger.info("***** BaseDictionaryServiceImpl.delete() method end*****");
		}
	}
	/**
	 * 
	 * 功能描述：   其他基础表删除操作时同步删除相应的数据字段信息
	 * 创建人：yangzheng  
	 * 创建时间：2012-5-14 上午11:59:33   
	 * 修改人：yangzheng   
	 * 修改时间：2012-5-14 上午11:59:33   
	 * 修改备注：
	 * @param dto
	 * @throws ServiceException
	 */
	public void deleteByCode(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** BaseDictionaryServiceImpl.deleteByCode() method begin*****");
		}
		
		String getCodeHql = dto.getAsString("getCodeHql");
		String parentCode = dto.getAsString("parentCode");
		String hql = "delete from BaseDictionarys where dictionaryCode in ("+getCodeHql+") and parentCode='"+parentCode+"'";
		dao.delete(hql,null);
		dictionaryService.init();
		if (logger.isInfoEnabled()){
			logger.info("***** BaseDictionaryServiceImpl.deleteByCode() method end*****");
		}
	}
	
	@SuppressWarnings("unchecked")
	public void deleteNode(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** BaseDictionaryServiceImpl.deleteNode() method begin*****");
		}
		
		String ids = dto.getAsString("id");
		String parentCode = dto.getAsString("parentCode");
		
		String hql = "select t.id from BaseDictionarys t where t.parentCode = '" + parentCode + "'";
		List<Integer> subNodeIdList = (List<Integer>)dao.find(hql, null);
		if (ids != null){
			StringBuilder sb = new StringBuilder();
			for (int id : subNodeIdList) {
				sb.append("," + id);
			}
			ids = ids + sb.toString();
			String sql = "delete from BaseDictionarys where id in (" + ids + ")";
			dao.delete(sql, null);
		}
		
		dictionaryService.init();
		if (logger.isInfoEnabled()){
			logger.info("***** BaseDictionaryServiceImpl.deleteNode() method end*****");
		}
	}
	
	public BaseDictionarys get(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** BaseDictionaryServiceImpl.get() method begin*****");
		}
		
		String dictionaryCode = dto.getAsString("dictionaryCode");
		String parentCode = dto.getAsString("parentCode");
		String sql = "select t from BaseDictionarys t where t.dictionaryCode = '" + dictionaryCode + "' and t.parentCode = '"
		             + parentCode + "'";
		BaseDictionarys baseDictionarys = (BaseDictionarys)dao.findUnique(sql, null);
		if (logger.isInfoEnabled()){
			logger.info("***** BaseDictionaryServiceImpl.get() method end*****");
		}
		return baseDictionarys;
	}

	public Dto save(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** BaseDictionaryServiceImpl.save() method begin*****");
		}
	    BaseDictionarys baseDictionarys = (BaseDictionarys) dto.get("baseDictionarys");
		
		String sql = "select count(t) from BaseDictionarys t where 1=1 and t.dictionaryCode = '" + baseDictionarys.getDictionaryCode() + "' and t.parentCode = '"
		             + baseDictionarys.getParentCode() + "'";
		Finder finder = Finder.create(sql);
		int count = dao.countQueryResult(finder, null);
		if (count > 0) {
			dto.setAppCode(StatusCode.M_Failure);
			dto.setAppMsg("该数据字典已经存在!");
			return dto;
		}
		baseDictionarys.setStatus(1);
		dao.save(baseDictionarys);
		dto.setAppCode(StatusCode.M_Success);
		
		dictionaryService.init();
		if (logger.isInfoEnabled()){
			logger.info("***** BaseDictionaryServiceImpl.save() method end*****");
		}
		return dto;
	}

	public void update(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** BaseDictionaryServiceImpl.update() method begin*****");
		}
		
		BaseDictionarys baseDictionarys = (BaseDictionarys) dto.get("baseDictionarys");
		dao.update(baseDictionarys);
		
		dictionaryService.init();
		if (logger.isInfoEnabled()){
			logger.info("***** BaseDictionaryServiceImpl.update() method end*****");
		}

	}

	public Pagination getList(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** BaseDictionaryServiceImpl.getList() method begin*****");
		}
		
		String keyword = dto.getAsString("keyword");
		Pagination page = (Pagination)dto.get("page");
		String orderField = dto.getAsString("orderField");
		String orderDirection = dto.getAsString("orderDirection");
		String parentCode = dto.getAsString("parentCode");
		
        StringBuilder sql = new StringBuilder("select t from BaseDictionarys t where t.status <> -1 ");
        
        if (!StringUtil.IsNullOrEmpty(keyword)) {
			sql.append(" and t.codeName like '%" + keyword.trim()+"%'");
		}
        if(ObjectUtils.isNotEmpty(parentCode)) {
        	sql.append(" and t.parentCode = '" + parentCode +"'");
        }
        if(ObjectUtils.isNotEmpty(orderField)) {
            sql.append(" order by t." + orderField + " " + orderDirection);
        }
		Finder finder = Finder.create(sql.toString());
		Pagination pag = dao.find(finder, page.getPageNum(), page.getNumPerPage());
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseDictionaryServiceImpl.getList() method end*****");
		}
		return pag;
	}

	/**
	 * 
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	@SuppressWarnings("unchecked")
	public Dto getDictionary(Dto dto)
			throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** BaseDictionaryServiceImpl.getDictionary() method begin*****");
		}
		
		String hql = "from BaseDictionarys t where t.status <> -1 and t.dictionaryCode = '" +
		       Constants.ROOT_DICTIONARY_CODE + "' and t.parentCode = '" + Constants.ROOT_DICTIONARY_PARENT_CODE + "'";
		BaseDictionarys rootNode = (BaseDictionarys)dao.findUnique(hql, dto);
		if (null != rootNode) {
			String parentCode = rootNode.getDictionaryCode();
			hql = "from BaseDictionarys t where t.status <> -1 and t.parentCode = '" + parentCode + "'";
			List<BaseDictionarys> levelOneNode = (List<BaseDictionarys>)dao.find(hql, dto);
			dto.put("rootNode", rootNode);
			if (levelOneNode.size() == 0){
				dto.put("levelOneNode", null);
			}else {
				dto.put("levelOneNode", levelOneNode);
			}
			
		}
		if (logger.isInfoEnabled()){
			logger.info("***** BaseDictionaryServiceImpl.getDictionary() method end*****");
		}
		return dto;
	}

}
