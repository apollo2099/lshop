package com.lshop.web.developer.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvDeveloper;
import com.lshop.common.util.CodeUtils;
import com.lshop.web.developer.service.DeveloperService;

/**
 * 优惠券管理模块
 * 
 * @author zhengxue
 * @version 1.0 2014-06-19
 * 
 */
@Service("DeveloperService")
public class DeveloperServiceImpl implements DeveloperService {
	private static final Log logger = LogFactory.getLog(DeveloperServiceImpl.class);
	@Resource
	private HibernateBaseDAO lvuserReadDao;
	@Resource
	private HibernateBaseDAO lvuserWriteDao;


	@Resource
	private HibernateBaseDAO lvlogicReadDao;
	@Resource
	private HibernateBaseDAO lvlogicWriteDao;


	public HibernateBaseDAO getLvlogicReadDao() {
		return lvlogicReadDao;
	}


	public void setLvlogicReadDao(HibernateBaseDAO lvlogicReadDao) {
		this.lvlogicReadDao = lvlogicReadDao;
	}


	@Override
	public LvDeveloper getInfo(Dto dto) throws ServiceException {
		String email=dto.getAsString("email");
		String bizline=dto.getAsString("bizline");
		Map param = new HashMap();
		param.put("email", email);
		param.put("bizline", bizline);
	    String info_sql = "FROM LvDeveloper WHERE email=:email AND bizline=:bizline";
	    LvDeveloper developer=(LvDeveloper) lvlogicReadDao.findUnique(info_sql, param);
		return developer;
	}

	
	@Override
	public Integer save(Dto dto) throws ServiceException {
		LvDeveloper developer=(LvDeveloper)dto.get("model");
		
		
		Integer type=developer.getDtype();
		//如果数据都正常
		if(!ObjectUtils.isNotEmpty(developer.getContactName().trim())
				&&!ObjectUtils.isNotEmpty(developer.getIdNum().trim())
				&&!ObjectUtils.isNotEmpty(developer.getIdUrl())
				&&!ObjectUtils.isNotEmpty(developer.getTel().trim())
				){
			return 2;//信息不完整
		}
		//企业开发者
		if(type==1){
			//如果数据都正常
			if(!ObjectUtils.isNotEmpty(developer.getAddress().trim())
					&&!ObjectUtils.isNotEmpty(developer.getCompany().trim())
					){
				return 2;//信息不完整
			}
		}
		developer.setIdNum(developer.getIdNum().trim());
		
		//认证号码不能重复
		String checkIdHql="FROM LvDeveloper WHERE dtype=:dtype AND (dstatus=1 OR dstatus=0) AND idNum=:idNum  AND bizline=:bizline  AND  email!=:email";
		Map param=new HashMap();
		param.put("dtype", type);
		param.put("email", developer.getEmail());
		param.put("idNum", developer.getIdNum());
		param.put("bizline", developer.getBizline());
		int checkIdHqlcount= lvlogicReadDao.countQueryResult(Finder.create(checkIdHql), param);
		if(checkIdHqlcount>0){
			return 3;//认证号码已存在
		}
		//避免重复提交
		String checkhql="FROM LvDeveloper WHERE email=:email AND bizline=:bizline ";
		 param=new HashMap();
		param.put("email", developer.getEmail());
		param.put("bizline", developer.getBizline());
		int count= lvlogicReadDao.countQueryResult(Finder.create(checkhql), param);
		//如果无重复 则添加
		if(count==0){
			developer.setCode(CodeUtils.getCode());
			developer.setDstatus(0);//待审核
			developer.setCreateTime(new Date());
			lvlogicWriteDao.save(developer);
		}
		return 1;
	}


	public HibernateBaseDAO getLvuserReadDao() {
		return lvuserReadDao;
	}


	public void setLvuserReadDao(HibernateBaseDAO lvuserReadDao) {
		this.lvuserReadDao = lvuserReadDao;
	}


	public HibernateBaseDAO getLvuserWriteDao() {
		return lvuserWriteDao;
	}


	public void setLvuserWriteDao(HibernateBaseDAO lvuserWriteDao) {
		this.lvuserWriteDao = lvuserWriteDao;
	}
	public HibernateBaseDAO getLvlogicWriteDao() {
		return lvlogicWriteDao;
	}


	public void setLvlogicWriteDao(HibernateBaseDAO lvlogicWriteDao) {
		this.lvlogicWriteDao = lvlogicWriteDao;
	}
	
	
	

}
