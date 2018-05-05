package com.lshop.web.pay.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.lshop.common.pojo.logic.LvWesternInfo;
import com.lshop.common.util.Constants;
import com.lshop.web.pay.service.WesternPayService;

@Service("WesternPayService")
public class WesternPayServiceImpl implements WesternPayService {

	@Resource
	private HibernateBaseDAO lvlogicReadDao;
	@Resource
	private HibernateBaseDAO lvlogicWriteDao;
	@Override
	public Integer saveWesternInfo(Dto dto) {
		Integer status=0;
		try {
			LvWesternInfo lInfo=(LvWesternInfo) dto.getDefaultPo();
			String hql="SELECT id FROM LvWesternInfo WHERE mtcn=:mtcn";
			Map param=new HashMap();
			param.put("mtcn", lInfo.getMtcn());
		    List list=lvlogicWriteDao.find(hql,param);
		    if (list==null||list.isEmpty()) {
		    	hql="SELECT payStatus,paymethod FROM LvOrder WHERE oid=:oid";
		    	List<Object[]> orderinfo=(List<Object[]>) lvlogicReadDao.find(Finder.create(hql).setParam("oid", lInfo.getOid())).getList();
		    	if (orderinfo!=null&&!orderinfo.isEmpty()&&orderinfo.get(0)[0].toString().equals(String.valueOf(Constants.PAY_STATUS_NO))&&orderinfo.get(0)[1].toString().equals(String.valueOf(Constants.PAY_METHOD_WESTERNUNION))) {
		    		lInfo.setStatus((short) 0);//默认未确认
			    	lvlogicWriteDao.save(lInfo);
			    	hql="UPDATE LvOrder SET payStatus=:payStatus WHERE oid=:oid";
			    	param.clear();
			    	param.put("payStatus", Constants.PAY_STATUS_OK_UNRECOGNIZED);
			    	param.put("oid", lInfo.getOid());
			    	lvlogicWriteDao.update(hql, param);
			    	status=1;
				}else {
					status=-1;
				}
			}else {
				status=-1;
			}
		} catch (Exception e) {
			status=-1;
		}
		return 	status;
	}
	
	public boolean isExistsMTCN(Dto dto) {
		String mtcn = dto.getAsString("mtch");
		String hql = "SELECT COUNT(*) FROM LvWesternInfo where mtcn = :mtcn ";
		Map<String, String> params = new HashMap<String, String>(1);
		params.put("mtcn", mtcn);
		Long rs = (Long) lvlogicReadDao.findUnique(hql, params);
		if (rs.intValue() > 0) {
			return true;
		}
		return false;
	}

}
