package com.gv.appstore.mng.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gv.appstore.mng.service.EmailMngService;
import com.gv.appstore.pojo.LvDeveloperTemplet;
import com.gv.appstore.util.CheckData;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.datastructure.page.SimplePage;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.ObjectUtils;
import com.gv.core.util.StringUtil;


@Service("EmailMngService")
public class EmailMngServiceImpl implements EmailMngService{
	@Resource 
	private HibernateBaseDAO dao;
	
	@Override
	public Pagination findPage(Dto dto) throws ServiceException {
		SimplePage page = (SimplePage)dto.get("page");
		LvDeveloperTemplet templ = (LvDeveloperTemplet)dto.get("templ");
		Pagination pag = new Pagination();
		StringBuilder hql = new StringBuilder();
		hql.append("from LvDeveloperTemplet where status<>-1");
		if(ObjectUtils.isNotEmpty(templ)){
			if(null != templ.getName()){
				if(CheckData.matcherDate(templ.getName())) return pag;
				hql.append(" and name like '%").
				append(CheckData.checkData(templ.getName().trim())).append("%'");
			}
			if(null != templ.getTitle()){
				if(CheckData.matcherDate(templ.getTitle())) return pag;
				hql.append(" and title like '%").
				append(CheckData.checkData(templ.getTitle().trim())).append("%'");
			}
			if(!StringUtil.IsNullOrEmpty(templ.getMallSign())){
				hql.append(" and mallSign = '").append(templ.getMallSign().trim()).append("'");
			}
			if(null != templ.getTtype() && templ.getTtype() != -1){
				hql.append(" and ttype = ").append(templ.getTtype());
			}
		}
		hql.append(" order by createTime desc");
		pag = dao.getMapListByHql(hql.toString(), page.getPageNum(), page.getNumPerPage(), null);
		return pag;
	}

	@Override
	public LvDeveloperTemplet get(Dto dto) throws ServiceException {
		int id = dto.getAsInteger("id");
		return (LvDeveloperTemplet)dao.load(LvDeveloperTemplet.class, id);
	}


	@Override
	public LvDeveloperTemplet add(Dto dto) throws ServiceException {
		LvDeveloperTemplet templ = (LvDeveloperTemplet)dto.get("templ");
		dao.save(templ);
		return null;
	}


	@Override
	public LvDeveloperTemplet edit(Dto dto) throws ServiceException {
		LvDeveloperTemplet templ = (LvDeveloperTemplet)dto.get("templ");
		dao.saveOrUpdate(templ);
		return null;
	}
	
	@Override
	public BaseDto doESBServiceWithHttpInvoke(String serviceId,
			String methodName, BaseDto dto) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void del(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delList(Dto dto) throws ServiceException {
		String ids = dto.getAsString("ids");
		if(null != ids && ids.length() > 0){
			StringBuilder hql = new StringBuilder();
			hql.append("update LvDeveloperTemplet set status=-1 where id in(").
				append(ids).append(")");
			this.dao.update(hql.toString(), null);
		}
	}

	@Override
	public Boolean isExist(Dto dto) throws ServiceException {
		LvDeveloperTemplet templ = (LvDeveloperTemplet)dto.get("templ");
		if(null != templ){
			StringBuilder hql = new StringBuilder();
			hql.append("from LvDeveloperTemplet where mallSign='").
				append(templ.getMallSign()).append("' and ttype=").append(templ.getTtype());
			
			if(null != templ.getId()){
				hql.append(" and id<>").append(templ.getId());
			}
			hql.append(" and status<>-1");
			Pagination pag = dao.getMapListByHql(hql.toString(), 1, 20, null);
			if((null != pag.getList()) && (pag.getList().size() > 0)){
				return true;
			}
		}
		return false;
	}

	@Override
	public LvDeveloperTemplet getBy(Dto dto) throws ServiceException {
		LvDeveloperTemplet templ = null;
		String mallSign = dto.getAsString("mallSign");
		int ttype = dto.getAsInteger("ttype");
		StringBuilder hql = new StringBuilder();
		hql.append("from LvDeveloperTemplet where mallSign='").
			append(mallSign).append("' and ttype=").append(ttype).append(" and status<>-1");
		List<LvDeveloperTemplet> list = dao.find(hql.toString(), null);
		if(null != list && list.size() > 0){
			templ = list.get(0);
		}
		return templ;
	}


}
