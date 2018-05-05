package com.lshop.web.userCenter.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvEmailTpl;
import com.lshop.web.userCenter.service.LvEmailManageService;


@Service("lvEmailManageService")
public class LvEmailManageServiceImpl implements LvEmailManageService {
	@Resource
	private HibernateBaseDAO lvlogicReadDao;

	@SuppressWarnings("unchecked")
	@Override
	public LvEmailTpl get(Dto dto) throws ServiceException {
		LvEmailTpl tpl=null;
		String tplKey=dto.getAsString("tplKey");
		String storeId=dto.getAsString("storeId");
		String hql="FROM LvEmailTpl WHERE  tplKey=:tplKey ";
		Map param=new HashMap();
		param.put("tplKey", tplKey);
		if (ObjectUtils.isNotEmpty(storeId)) {
			hql+=" and storeId=:storeId";
			param.put("storeId", storeId);
		}
		List<LvEmailTpl> list=lvlogicReadDao.find(hql, param);
		if(list!=null&&!list.isEmpty()){
			tpl=list.get(0);
		}
		return tpl;
	}


}

