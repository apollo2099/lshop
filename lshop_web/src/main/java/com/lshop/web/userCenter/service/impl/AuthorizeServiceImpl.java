package com.lshop.web.userCenter.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.lshop.common.pojo.logic.LvUserTh;
import com.lshop.web.userCenter.service.AuthorizeService;

/**
 * 授权模块
 * @author dingh
 *
 */
@Service("AuthorizeService")
public class AuthorizeServiceImpl implements AuthorizeService {
	
	@Resource
	private HibernateBaseDAO lvlogicReadDao;
	@Resource
	private HibernateBaseDAO lvlogicWriteDao;

	/**
	 * 根据授权code（微信openId）查找是否有绑定的账号
	 */
	@Override
	public LvUserTh getLvUserTh(Dto dto) throws ServiceException {
		String thCode = dto.getAsString("openId");
		Integer thType=dto.getAsInteger("thType");
		HashMap map=new HashMap();
		map.put("thCode", thCode);
		map.put("thType", thType);
		String hql="from LvUserTh where thCode=:thCode and thType=:thType and status=1";
		List<LvUserTh> list=(List<LvUserTh>)lvlogicReadDao.find(hql, map);
		if(null != list && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

	/**
	 * 判断账号是否已经绑定
	 * 账号可对banana和tvpad分别进行绑定、
	 * 在banana或tvpad中只能绑定一次
	 * true表示已绑定
	 * false表示未绑定
	 */
	@Override
	public boolean isBind(Dto dto) throws ServiceException {
		String code = dto.getAsString("code");
		String mallFlag = dto.getAsString("mallFlag");
		Integer thType=dto.getAsInteger("thType");
		HashMap map=new HashMap();
		map.put("code", code);
		map.put("mallFlag", mallFlag);
		map.put("thType", thType);
		String hql="from LvUserTh where userCode=:code and thType=:thType and mallFlag=:mallFlag and status=1";
		List<LvUserTh> list=(List<LvUserTh>)lvlogicReadDao.find(hql, map);
		if(null != list && list.size() > 0){
			return true;
		}
		return false;
	}

	/**
	 * 新增绑定
	 */
	@Override
	public LvUserTh bind(Dto dto) throws ServiceException {
		LvUserTh lvUserTh = (LvUserTh)dto.get("lvUserTh");
		lvlogicWriteDao.save(lvUserTh);
		return null;
	}
	

}
