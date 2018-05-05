package com.lshop.web.mac.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvMac;
import com.lshop.web.mac.service.MacService;

@Service("MacService")
public class MacServiceImpl implements MacService{
	private static final Log logger	= LogFactory.getLog(MacServiceImpl.class);
	@Resource
	private HibernateBaseDAO lvlogicReadDao;
	@Resource
	private HibernateBaseDAO lvlogicWriteDao;
	
	public Boolean saveMac(String mac){
		if(ObjectUtils.isNotEmpty(mac)){
			//查询mac库存信息
			LvMac m=this.findByMac(mac);
			if(ObjectUtils.isEmpty(m)){
				LvMac lvMac=new LvMac();
				lvMac.setMac(mac);
				lvMac.setCreateTime(new Date());
				lvMac.setStatus((short)1);
				lvlogicWriteDao.save(lvMac);
				return true;
			}
		}

		return false;
	}
	
	public LvMac findByMac(String mac){
		String hql="from LvMac where status<>-1 and mac=:mac";
		Map<String,Object> param=new HashMap<String, Object>();
		param.put("mac", mac);
		return (LvMac) lvlogicReadDao.findUnique(hql, param);
	}


}
