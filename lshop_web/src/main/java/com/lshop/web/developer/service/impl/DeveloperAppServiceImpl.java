package com.lshop.web.developer.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.datastructure.page.SimplePage;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvDeveloperApp;
import com.lshop.common.util.CodeUtils;
import com.lshop.web.developer.service.DeveloperAppService;

/**
 * 优惠券管理模块
 * 
 * @author zhengxue
 * @version 1.0 2014-06-19
 * 
 */
@Service("DeveloperAppService")
public  class DeveloperAppServiceImpl implements DeveloperAppService {
	private static final Log logger = LogFactory.getLog(DeveloperAppServiceImpl.class);


	@Resource
	private HibernateBaseDAO lvlogicReadDao;
	@Resource
	private HibernateBaseDAO lvlogicWriteDao;


	@Override
	public Pagination list(Dto dto) throws ServiceException {
		SimplePage page = (SimplePage)dto.get("page");
		String email =dto.getAsString("email");
		String bizline =dto.getAsString("bizline");
		String listhql="FROM LvDeveloperApp WHERE email=:email AND bizline=:bizline ORDER BY id DESC ";
		Map param=new HashMap();
		param.put("email", email);
		param.put("bizline", bizline);
		Finder finder=Finder.create(listhql);
		finder.setParams(param);
		Pagination apps=(Pagination)lvlogicReadDao.find(finder, page.getPageNum(), 20);
		return apps;
	}
	
	@Override
	public Integer save(Dto dto) throws ServiceException {
		LvDeveloperApp app=(LvDeveloperApp)dto.get("model");
		//如果数据都正常
		if(ObjectUtils.isEmpty(app.getName())||ObjectUtils.isEmpty(app.getUrl())){
			return 2;//信息不完整
		}
		app.setCode(CodeUtils.getCode());
		app.setStatus(3);//待完善
		app.setCreateTime(new Date());
		lvlogicWriteDao.save(app);
		return 1;
	}

	@Override
	public Integer saveinfo(Dto dto) throws ServiceException {
		LvDeveloperApp app=(LvDeveloperApp)dto.get("model");
		//如果数据都正常
		if(ObjectUtils.isEmpty(app.getCode())||ObjectUtils.isEmpty(app.getAppVersion())
			||ObjectUtils.isEmpty(app.getLang())||ObjectUtils.isEmpty(app.getAppType())
			||ObjectUtils.isEmpty(app.getKeyWord())||ObjectUtils.isEmpty(app.getAppDesc())
			||ObjectUtils.isEmpty(app.getDemoImgsUrl())||ObjectUtils.isEmpty(app.getIsCharge())
			||ObjectUtils.isEmpty(app.getIsAd())
				){
			return 2;//信息不完整
		}
		Map param=new HashMap();
		param.put("code", app.getCode());
		param.put("email", app.getEmail());
		String hql=" FROM LvDeveloperApp WHERE code=:code AND email=:email";
		LvDeveloperApp oldapp=(LvDeveloperApp)lvlogicReadDao.findUnique(hql, param);
		if(oldapp.getStatus()==3){
			oldapp.setStatus(0);//待审核
			oldapp.setUpdateTime(new Date());
			oldapp.setAppDesc(app.getAppDesc());
			oldapp.setAppType(app.getAppType());
			oldapp.setAppVersion(app.getAppVersion());
			oldapp.setIsAd(app.getIsAd());
			oldapp.setIsCharge(app.getIsCharge());
			oldapp.setKeyWord(app.getKeyWord());
			oldapp.setLang(app.getLang());
			oldapp.setDemoImgsUrl(app.getDemoImgsUrl());
			lvlogicWriteDao.update(oldapp);
		}
		return 1;
	}
	
	
	public LvDeveloperApp info(Dto dto) throws ServiceException {
		String code=dto.getAsString("code");
		String email=dto.getAsString("email");
		String infohql="FROM LvDeveloperApp WHERE code=:code AND email=:email";
		Map param=new HashMap();
		param.put("code", code);
		param.put("email", email);
		LvDeveloperApp app=(LvDeveloperApp)lvlogicReadDao.findUnique(infohql, param);
		return app;
	}
	

	public HibernateBaseDAO getLvlogicWriteDao() {
		return lvlogicWriteDao;
	}


	public void setLvlogicWriteDao(HibernateBaseDAO lvlogicWriteDao) {
		this.lvlogicWriteDao = lvlogicWriteDao;
	}

	
	public HibernateBaseDAO getLvlogicReadDao() {
		return lvlogicReadDao;
	}


	public void setLvlogicReadDao(HibernateBaseDAO lvlogicReadDao) {
		this.lvlogicReadDao = lvlogicReadDao;
	}



}
