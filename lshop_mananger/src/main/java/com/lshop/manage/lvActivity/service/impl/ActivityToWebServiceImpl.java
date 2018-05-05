package com.lshop.manage.lvActivity.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import net.sf.json.JSONObject;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.message.jms.JmsMessageQueueSender;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.util.DateUtils;
import com.lshop.manage.lvActivity.service.ActivityToWebService;

@Service("ActivityToWebService")
public class ActivityToWebServiceImpl implements ActivityToWebService{
	private static final Log logger = LogFactory.getLog(ActivityToWebServiceImpl.class);
	@Resource
	private HibernateBaseDAO dao;
	@Resource
	private JmsMessageQueueSender messageQueueSenderLshopManager;

   
	public void sendJSONToWeb(Dto dto)throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** ActivityToWebServiceImpl.sendJSONToWeb() method begin*****");
		}
		String activityCode=(String) dto.get("activityCode");
		Map param = new HashMap();
		initActivityLinkInfo(param,activityCode);
		messageQueueSenderLshopManager.sendObject(JSONObject.fromObject(param).toString());//向tvpad前端项目发送消息
		
		if (logger.isInfoEnabled()){
			logger.info("***** ActivityToWebServiceImpl.sendMessage*****"+JSONObject.fromObject(param).toString());
			logger.info("***** ActivityToWebServiceImpl.sendJSONToWeb() method end*****");
		}
	}
	
	
	/**
	 * 
	 * @Method: initActivityInfo 
	 * @Description:  [初始化活动信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-6-30 上午10:03:37]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-6-30 上午10:03:37]   
	 * @UpdateRemark: [说明本次修改内容]   
	 * @return void
	 */
	private void initActivityLinkInfo(Map param,String activityCode){
		if (logger.isInfoEnabled()){
			logger.info("***** ActivityToWebServiceImpl.initActivityInfo() method begin*****");
		}
		//点击链接就送活动
		String hql="SELECT t.id as id,t.activityCode as activityCode,t.limitNum as limitNum,t.activityHtml as activityHtml," +
				" t.givenTypeName as givenTypeName,t.givenProductCode as givenProductCode,t.grantTotal as grantTotal," +
				" t.uncollectedTotal as uncollectedTotal,t.givenTypeNum as givenTypeNum,t.code as code ,ac.startTime as startTime ," +
				" ac.endTime as endTime,ac.activityTitle as activityTitle,ac.status as status,t.strategyType as strategyType " +
				" FROM LvActivityLink t, LvActivity ac " +
				" WHERE t.activityCode=ac.code " +
				" and ac.typeKey=4 " +
				"  " +
				" and t.activityCode=:activityCode";
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("activityCode", activityCode);
		List list= dao.getMapListByHql(hql, map).getList();
		List<Map<String,Object>>  objList =new ArrayList<Map<String,Object>>();
		for (int i = 0; i < list.size(); i++) {
			Map<String,Object> resutlMap =(Map<String, Object>) list.get(i);
			if(ObjectUtils.isNullOrEmptyString(resutlMap.get("id"))){
				resutlMap.put("id", "");
			}
			if(ObjectUtils.isNullOrEmptyString(resutlMap.get("activityCode"))){
				resutlMap.put("activityCode", "");
			}
			if(ObjectUtils.isNullOrEmptyString(resutlMap.get("limitNum"))){
				resutlMap.put("limitNum", "");
			}
			if(ObjectUtils.isNullOrEmptyString(resutlMap.get("activityHtml"))){
				resutlMap.put("activityHtml", "");
			}
			if(ObjectUtils.isNullOrEmptyString(resutlMap.get("givenTypeName"))){
				resutlMap.put("givenTypeName", "");
			}
			if(ObjectUtils.isNullOrEmptyString(resutlMap.get("givenProductCode"))){
				resutlMap.put("givenProductCode", "");
			}
			if(ObjectUtils.isNullOrEmptyString(resutlMap.get("grantTotal"))){
				resutlMap.put("grantTotal", "");
			}
			if(ObjectUtils.isNullOrEmptyString(resutlMap.get("uncollectedTotal"))){
				resutlMap.put("uncollectedTotal", "");
			}
			if(ObjectUtils.isNullOrEmptyString(resutlMap.get("givenTypeNum"))){
				resutlMap.put("givenTypeNum", "");
			}
			if(ObjectUtils.isNullOrEmptyString(resutlMap.get("code"))){
				resutlMap.put("code", "");
			}
			Date startTime=(Date)resutlMap.get("startTime");
			if(ObjectUtils.isNullOrEmptyString(startTime)){
				resutlMap.put("startTime", "");
			}else{
				resutlMap.put("startTime", DateUtils.formatDate(startTime, "yyyy-MM-dd HH:mm:ss"));
			}
			Date endTime=(Date)resutlMap.get("endTime");
			if(ObjectUtils.isNullOrEmptyString(endTime)){
				resutlMap.put("endTime", "");
			}else{
				resutlMap.put("endTime", DateUtils.formatDate(endTime, "yyyy-MM-dd HH:mm:ss"));
			}
			if(ObjectUtils.isNullOrEmptyString(resutlMap.get("activityTitle"))){
				resutlMap.put("activityTitle", "");
			}
			if(ObjectUtils.isNullOrEmptyString(resutlMap.get("status"))){
				resutlMap.put("status", "");
			}
			if(ObjectUtils.isNullOrEmptyString(resutlMap.get("strategyType"))){
				resutlMap.put("strategyType", "");
			}
			
			objList.add(resutlMap);
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvActivityServiceImpl.initActivityInfo() method end*****");
		}
	    param.put("ACTIVITYLINKS", objList);	
	}

}
