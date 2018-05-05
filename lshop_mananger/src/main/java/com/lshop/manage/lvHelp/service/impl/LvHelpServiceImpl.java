/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvHelp.service.impl;

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
import com.lshop.common.pojo.logic.LvHelp;
import com.lshop.common.util.StringUtil;
import com.lshop.manage.lvHelp.service.LvHelpService;

/**
 * @author 好视网络-软件二部：唐迪
 * @version 1.0
 * @since 1.0
 */@Service("LvHelpService")
public class LvHelpServiceImpl extends BaseServiceImpl implements LvHelpService {

	@Resource private HibernateBaseDAO dao;

	
	public List<LvHelp> findAll(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public Pagination findPage(Dto dto) throws ServiceException {
		String orderField = dto.getAsString("orderField");
		String orderDirection = dto.getAsString("orderDirection");
		SimplePage page = (SimplePage)dto.get("page");
		LvHelp  lvHelp = (LvHelp)dto.get("model");
		Map params=new HashMap();
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
        StringBuilder sql = new StringBuilder("select t from LvHelp t where 1=1  ");	
        if(lvHelp!=null){
	        if(ObjectUtils.isNotEmpty(lvHelp.getHelpId())) {
	        	sql.append(" and  t.helpId =:helpId ");
	        }	
        	if(ObjectUtils.isNotEmpty(lvHelp.getName())) {
	        	sql.append(" and  t.name like :name");
	        }
        	if(ObjectUtils.isNotEmpty(lvHelp.getStoreId())){
        		sql.append(" and t.storeId like :storeId");
        	}
        	params=BeanUtils.describeForHQL(lvHelp);
        }
	    if(ObjectUtils.isNotEmpty(dto.getAsString("flag"))){//店铺标示
	        String storeList=StringUtil.stringFormart(dto.getAsString("flag"), ",");
	        sql.append(" and storeId in("+storeList+")");
	    }
        if(!ObjectUtils.isNullOrEmptyString(orderField)&&!ObjectUtils.isNullOrEmptyString(orderDirection)){
        	sql.append(" order by "+ orderField+" "+orderDirection);
		}
		Finder finder = Finder.create(sql.toString());
		finder.setParams(params);
		Pagination pag = dao.find(finder, page.getPageNum(), page.getNumPerPage());
		return pag;
	}

	public LvHelp get(Dto dto) throws ServiceException {
		LvHelp 	lvHelp = (LvHelp)dto.get("model");
		lvHelp = (LvHelp)dao.load(LvHelp.class, lvHelp.getId());
		return lvHelp;
	}

	public void del(Dto dto) throws ServiceException {
		LvHelp  lvHelp = get(dto);
		dao.delete(lvHelp);
	}

	public void delList(Dto dto) throws ServiceException {
//		dao.delete(BasePoList)
	}

	public LvHelp save(Dto dto) throws ServiceException {
		LvHelp lvHelp = (LvHelp)dto.get("model");
		if (lvHelp.getWebLanguage()==null) {			
			lvHelp.setWebLanguage("tw");
		}
		dao.save(lvHelp);
		return lvHelp;
	}

	public LvHelp update(Dto dto) throws ServiceException {
		LvHelp lvHelp = (LvHelp)dto.get("model");
		if (lvHelp.getWebLanguage()==null) {
			
			lvHelp.setWebLanguage("tw");
		}
		dao.update(lvHelp);
		return lvHelp;
	}

}
