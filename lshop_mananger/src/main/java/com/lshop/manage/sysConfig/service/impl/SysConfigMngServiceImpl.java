package com.lshop.manage.sysConfig.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.datastructure.page.SimplePage;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.message.jms.JmsMessageQueueSender;
import com.gv.core.util.StringUtil;
import com.lshop.common.pojo.logic.LvSysConfig;
import com.lshop.manage.sysConfig.service.SysConfigMngService;

@Service("SysConfigMngService")
public class SysConfigMngServiceImpl implements SysConfigMngService{
//	private static final Log logger = LogFactory.getLog(SysConfigMngServiceImpl.class);
	@Resource 
	private HibernateBaseDAO dao;
	@Resource
	private JmsMessageQueueSender messageQueueSenderLshopManager;
	
	private static final String TVPAD_PRE = "gv_tvpad@";
	private static final String BANANATV_PRE = "gv_bananatv@";

	@Override
	public Pagination findPage(Dto dto) throws ServiceException {
		SimplePage page = (SimplePage)dto.get("page");
		LvSysConfig config = (LvSysConfig)dto.get("config");
		Pagination pag = new Pagination();
		StringBuilder hql = new StringBuilder("from LvSysConfig where status<>-1");
		
		if(null != config){
			if(!StringUtil.IsNullOrEmpty(config.getName())){
				hql.append(" and name like '%").append(config.getName()).append("%'");
			}
			if(!StringUtil.IsNullOrEmpty(config.getVal())){
				hql.append(" and val like '%").append(config.getVal()).append("%'");
			}
			if(null != config.getType()){
				hql.append(" and type =").append(config.getType());
			}
		}
		hql.append(" order by createTime desc");
		pag = this.dao.getMapListByHql(hql.toString(), page.getPageNum(), page.getNumPerPage(), null);
		return pag;
	}

	@Override
	public LvSysConfig getById(Dto dto) throws ServiceException {
		int id = dto.getAsInteger("id");
		return (LvSysConfig)dao.load(LvSysConfig.class, id);
	}

	@Override
	public LvSysConfig save(Dto dto) throws ServiceException {
		LvSysConfig config = (LvSysConfig)dto.get("config");
		dao.saveOrUpdate(config);
		return null;
	}
	
	/**
	 * 同一类型下不允许存在相同名称配置
	 * 存在返回true
	 * 不存在返回false
	 */
	@Override
	public Boolean isExist(Dto dto) throws ServiceException {
		LvSysConfig config = (LvSysConfig)dto.get("config");
		Pagination pag = new Pagination();
		StringBuilder hql = new StringBuilder("from LvSysConfig where status<>-1");
		hql.append(" and type=").append(config.getType()).
		append(" and name='").append(config.getName()).append("'");
		
		if(null != config.getId()){
			hql.append(" and id<>").append(config.getId());
		}
		
		pag = this.dao.getMapListByHql(hql.toString(), 1, 10, null);
		List list = pag.getList();
		if(null != list && list.size() > 0){
			return true;
		}
		return false;
	}

	@Override
	public Boolean delList(Dto dto) throws ServiceException {
		String ids = dto.getAsString("ids");
		if(null != ids && ids.length() > 0){
			StringBuilder hql = new StringBuilder();
			hql.append("update LvSysConfig set status=-1 where id in(").
				append(ids).append(")");
			this.dao.update(hql.toString(), null);
		}
		return null;
	}
	
	@Override
	public Boolean initData(Dto dto) throws ServiceException {
		Map<String,Object> configParam = new HashMap<String,Object>();
		Map<String,Object> param = new HashMap<String,Object>();
		List<LvSysConfig> list = dao.find("from LvSysConfig where status<>-1", null);
		String name = null;
		for(LvSysConfig c : list){
			if(c.getType() == 0) name = c.getName();
			if(c.getType() == 1) name = TVPAD_PRE+c.getName();
			if(c.getType() == 2) name = BANANATV_PRE+c.getName();
			param.put(name, c.getVal());
		}
		configParam.put("configParam", param);
		messageQueueSenderLshopManager.sendObject(JSONObject.fromObject(configParam).toString());
		return null;
	}
	
	@Override
	public BaseDto doESBServiceWithHttpInvoke(String serviceId,
			String methodName, BaseDto dto) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}
