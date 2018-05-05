/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvPatternCountry.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.lshop.manage.lvPatternCountry.service.LvPatternCountryService;
import com.lshop.common.pojo.logic.LvPatternCountry;
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
 */@Service("LvPatternCountryService")
public class LvPatternCountryServiceImpl extends BaseServiceImpl implements LvPatternCountryService {
	/**
	 * 获得所有
	 */
	@Resource private HibernateBaseDAO dao;
	public List<LvPatternCountry> findAll(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 分页查询
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Pagination findPage(Dto dto) throws ServiceException {
		SimplePage page = (SimplePage)dto.get("page");
		 LvPatternCountry lvPatternCountry = (LvPatternCountry)dto.get("lvPatternCountry");
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
        StringBuilder sql = new StringBuilder("select t from LvPatternCountry t where status<>-1 ");
		        if(ObjectUtils.isNotEmpty(lvPatternCountry.getPatternCode())) {
		        	sql.append(" and  t.patternCode = :patternCode ");
		        }	
	        	if(ObjectUtils.isNotEmpty(lvPatternCountry.getCountryName())) {
		        	sql.append(" and  t.countryName like :countryName ");
		        }
	        	if(ObjectUtils.isNotEmpty(lvPatternCountry.getCountryCode())) {
		        	sql.append(" and  t.countryCode like :countryCode ");
		        }
		        if(ObjectUtils.isNotEmpty(lvPatternCountry.getStatus())) {
		        	sql.append(" and  t.status = :status ");
		        }	
        Map parms = BeanUtils.describeForHQL(lvPatternCountry);
        if(ObjectUtils.isNotEmpty(dto.getAsString("orderField"))) {
            sql.append(" order by t." + dto.getAsString("orderField") + " " + dto.getAsString("orderDirection"));
        } 
		Finder finder = Finder.create(sql.toString());
		finder.setParams(parms);
		Pagination pag = dao.find(finder, page.getPageNum(), page.getNumPerPage());
		return pag;
	}
	/**
	 * 获得单独的实体信息
	 */
	public LvPatternCountry get(Dto dto) throws ServiceException {
		 LvPatternCountry lvPatternCountry = (LvPatternCountry)dto.get("lvPatternCountry");
		 lvPatternCountry = (LvPatternCountry)dao.load(LvPatternCountry.class, lvPatternCountry.getId());
		return  lvPatternCountry;
	}
	
	public LvPatternCountry findByCountryId(Dto dto) throws ServiceException {
		Integer countryId=dto.getAsInteger("countryId");
		return this.findByCountryId(countryId);
	}
	/**
	 * 获得单独的实体信息
	 */
	public LvPatternCountry findByCountryId(Integer countryId) throws ServiceException {
		LvPatternCountry lvPatternCountry=null;
		if(ObjectUtils.isNotEmpty(countryId)){
			String hql="from LvPatternCountry t where status<>-1 and countryId=:countryId";
			Map<String,Object> param=new HashMap<String, Object>();
			param.put("countryId", countryId);
			List<LvPatternCountry> list= dao.find(hql, param);
			if(ObjectUtils.isNotEmpty(list)){
				lvPatternCountry=list.get(0);
			}
		} 
		return  lvPatternCountry;
	}
	
	/**
	 * 删除
	 * @param dto
	 * @throws ServiceException
	 */
	public void del(Dto dto) throws ServiceException {
		 LvPatternCountry lvPatternCountry = get(dto);
		 String hql="update LvPatternCountry set status=-1 where id=:id";
		 Map<String,Object> param=new HashMap<String, Object>();
		 param.put("id", lvPatternCountry.getId());
		 dao.update(hql, param);
	}


	/**
	 * 保存
	 */
	public LvPatternCountry save(Dto dto) throws ServiceException {
		 LvPatternCountry lvPatternCountry = (LvPatternCountry)dto.get("lvPatternCountry");
		dao.save( lvPatternCountry);
		return   lvPatternCountry;
	}
	/**
	 * 更新
	 */
	public LvPatternCountry update(Dto dto) throws ServiceException {
		 LvPatternCountry lvPatternCountry = (LvPatternCountry)dto.get("lvPatternCountry");
		dao.update(lvPatternCountry);
		return  lvPatternCountry;
	}

}
