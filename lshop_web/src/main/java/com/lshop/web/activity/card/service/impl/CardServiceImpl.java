package com.lshop.web.activity.card.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.lshop.common.pojo.logic.LvMappCardsTmp;
import com.lshop.common.pojo.logic.LvMappCardsUser;
import com.lshop.web.activity.card.service.CardService;

@Service("CardService")
public class CardServiceImpl implements CardService{

	@Resource
	private HibernateBaseDAO lvlogicReadDao;
	@Resource
	private HibernateBaseDAO lvlogicWriteDao;
	
	@Override
	public LvMappCardsTmp getCardByName(Dto dto) throws ServiceException {
		String code = dto.getAsString("code");
		List list = lvlogicReadDao.find("from LvMappCardsTmp where status=1 and code='"+code+"'", null);
		if(null == list || list.size() < 1){
			return null;
		}
		return (LvMappCardsTmp)list.get(0);
	}
	

	@Override
	public void save(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		LvMappCardsUser model=(LvMappCardsUser)dto.get("model");
		lvlogicWriteDao.save(model);
	}

	@Override
	public LvMappCardsUser getMappCardsUser(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		String code = dto.getAsString("code");
		Map param=new HashMap();
		return (LvMappCardsUser)lvlogicReadDao.findUnique("from LvMappCardsUser where code=:code", param);
	}


	public Dto getUserCard(Dto dto) throws ServiceException {
		String code = dto.getAsString("code");
		List list = lvlogicReadDao.find("from LvMappCardsTmp where status=1", null);

		Pagination page = lvlogicReadDao.getMapListByHql("select tmp.title as title,tmp.cssName as cssName,u.recipient as recipient,"
				+ "u.cdesc as cdesc,u.sender as sender,u.imgUrl as imgUrl,u.createTime as createTime,u.imgUrl as imgUrl,u.tempCode as tempCode "
				+ "from LvMappCardsTmp tmp,LvMappCardsUser u where tmp.status=1 and u.code='"+code+"' AND u.tempCode=tmp.code", null);
		List<Map<String,Object>> result=(List<Map<String, Object>>) page.getList();
         if(result!=null&&result.size()>0){
        	 dto.put("model", result.get(0));
         }else{
        	 dto.put("model", null);
         }
		 return dto;
	}


	@Override
	public List<LvMappCardsTmp> getList(Dto dto) throws ServiceException {
		String hql = "from LvMappCardsTmp where status=1";
		List<LvMappCardsTmp> list = (List<LvMappCardsTmp>)lvlogicReadDao.find(hql, null);
		return list;
	}

}
