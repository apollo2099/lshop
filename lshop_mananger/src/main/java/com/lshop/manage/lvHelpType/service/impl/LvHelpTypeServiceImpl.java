/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvHelpType.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.datastructure.page.SimplePage;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.service.impl.BaseServiceImpl;
import com.gv.core.util.BeanUtils;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvHelpType;
import com.lshop.common.util.StringUtil;
import com.lshop.manage.lvHelpType.service.LvHelpTypeService;

/**
 * @author 好视网络-软件二部：唐迪
 * @version 1.0
 * @since 1.0
 */@Service("LvHelpTypeService")
public class LvHelpTypeServiceImpl extends BaseServiceImpl implements LvHelpTypeService {

	@Resource private HibernateBaseDAO dao;
	private LvHelpType lvHelpType;
	


	public Pagination findPage(Dto dto) throws ServiceException {
		String orderField = dto.getAsString("orderField");
		String orderDirection = dto.getAsString("orderDirection");
		SimplePage page = (SimplePage)dto.get("page");
		lvHelpType = (LvHelpType)dto.get("model");
		Map params=new HashMap();
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
        StringBuilder sql = new StringBuilder("select t from LvHelpType t where 1=1 ");
        if(lvHelpType!=null){
        	if(ObjectUtils.isNotEmpty(lvHelpType.getName())) {
	        	sql.append(" and t.name like :name");
	        }
        	if(ObjectUtils.isNotEmpty(lvHelpType.getStoreId())){
        		sql.append(" and t.storeId like :storeId");
        	}
        	params=BeanUtils.describeForHQL(lvHelpType);
        	//params.remove("storeId");
        }
		if (ObjectUtils.isNotEmpty(dto.getAsString("flag"))) {// 店铺标示
			String storeList = StringUtil.stringFormart(dto.getAsString("flag"), ",");
			sql.append(" and storeId in(" + storeList + ")");
		}
        if(!ObjectUtils.isNullOrEmptyString(orderField)&&!ObjectUtils.isNullOrEmptyString(orderDirection)){
        	sql.append(" order by "+ orderField+" "+orderDirection);
		}
		Finder finder = Finder.create(sql.toString());
		finder.setParams(params);
		Pagination pag = dao.find(finder, page.getPageNum(), page.getNumPerPage());
		return pag;
	}
	
	public List<LvHelpType> findAll(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		LvHelpType lvHelpType=(LvHelpType) dto.get("lvHelpType");
		String hql="from LvHelpType where 1=1";
		if(lvHelpType!=null){
			if(ObjectUtils.isNotEmpty(lvHelpType.getStoreId())){
				hql+=" and storeId='"+lvHelpType.getStoreId()+"'";
			}
		}
		if(ObjectUtils.isNotEmpty(dto.getAsString("flag"))){//店铺标示
			String storeList=StringUtil.stringFormart(dto.getAsString("flag"), ",");
    		hql+=" and storeId in("+storeList+")";
	    }
		hql+=" order by orderValue desc";
		return dao.find(hql, null);
	}
	
	public LvHelpType get(Dto dto) throws ServiceException {
		lvHelpType = (LvHelpType)dto.get("model");
		lvHelpType = (LvHelpType)dao.load(LvHelpType.class, lvHelpType.getId());
		return lvHelpType;
	}

	public void del(Dto dto) throws ServiceException {
		lvHelpType = get(dto);
		dao.delete(lvHelpType);
	}

	public void delList(Dto dto) throws ServiceException {
//		dao.delete(BasePoList)
	}

	public LvHelpType save(Dto dto) throws ServiceException {
		lvHelpType = (LvHelpType)dto.get("model");
		if (lvHelpType.getWebLanguage()==null) {
			lvHelpType.setWebLanguage("tw");
		}
		dao.save(lvHelpType);
		return lvHelpType;
	}

	public LvHelpType update(Dto dto) throws ServiceException {
		lvHelpType = (LvHelpType)dto.get("model");
		if (lvHelpType.getWebLanguage()==null) {
			lvHelpType.setWebLanguage("tw");
		}
		dao.update(lvHelpType);
		return lvHelpType;
	}

	@Override
	public LvHelpType getHelpType(Dto dto) throws ServiceException {
		Integer typeId = (Integer)dto.get("typeId");
		LvHelpType lvHelpType=(LvHelpType) dao.load(LvHelpType.class, typeId);
		return lvHelpType;
	}

}
