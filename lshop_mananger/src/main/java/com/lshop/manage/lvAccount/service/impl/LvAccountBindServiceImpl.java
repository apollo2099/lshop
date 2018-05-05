package com.lshop.manage.lvAccount.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.datastructure.page.SimplePage;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.StringUtil;
import com.lshop.common.pojo.logic.LvUserTh;
import com.lshop.manage.lvAccount.service.LvAccountBindService;

@Service("LvAccountBindService")
public class LvAccountBindServiceImpl implements LvAccountBindService{

	@Resource 
	private HibernateBaseDAO dao;
	
	@Override
	public Pagination getPagination(Dto dto) throws ServiceException {
		SimplePage page = (SimplePage)dto.get("page");
		LvUserTh lvUserTh = (LvUserTh)dto.get("lvUserTh");
		String createDateStart = dto.getAsString("createDateStart");
		String createDateEnd = dto.getAsString("createDateEnd");
		String useremail = dto.getAsString("useremail");
		Pagination pag = new Pagination();
		StringBuilder hql = new StringBuilder("select t as lvUserTh,u.email as email from LvUserTh t,LvAccount u where 1=1");
		hql.append(" and t.userCode=u.code");
		
		if(null != lvUserTh){
			if(null != lvUserTh.getThType()){
				hql.append(" and t.thType=").append(lvUserTh.getThType());
			}
		}
		
		if(!StringUtil.IsNullOrEmpty(useremail)){
			hql.append(" and u.email like '%").append(useremail).append("%'");
		}
		if(!StringUtil.IsNullOrEmpty(createDateStart)){
			createDateStart = createDateStart + " 00:00:00";
			hql.append(" and t.createTime >= '").append(createDateStart).append("'");
		}
		if(!StringUtil.IsNullOrEmpty(createDateEnd)){
			createDateEnd = createDateEnd + " 23:59:59";
			hql.append(" and t.createTime <= '").append(createDateEnd).append("'");
		}
		hql.append(" order by t.createTime desc");
		
		pag = this.dao.getMapListByHql(hql.toString(), page.getPageNum(), page.getNumPerPage(), null);
		return pag;
	}

	@Override
	public void del(Dto dto) throws ServiceException {
		Integer id = dto.getAsInteger("id");
		String modifyuser = dto.getAsString("modifyuser");
		StringBuilder hql = new StringBuilder("update LvUserTh set status=0,updateTime=now(),modifyUserName='");
		hql.append(modifyuser).append("' where id=").append(id);
		this.dao.update(hql.toString(), null);
	}
	
	@Override
	public void delList(Dto dto) throws ServiceException {
		String ids = dto.getAsString("ids");
		String modifyuser = dto.getAsString("modifyuser");
		StringBuilder hql = new StringBuilder("update LvUserTh set status=0,updateTime=now(),modifyUserName='");
		hql.append(modifyuser).append("' where id in(");
		hql.append(ids).append(")");
		this.dao.update(hql.toString(), null);
	}

	@Override
	public LvUserTh get(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	

}
