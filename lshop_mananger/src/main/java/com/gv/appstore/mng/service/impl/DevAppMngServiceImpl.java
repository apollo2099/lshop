package com.gv.appstore.mng.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gv.appstore.mng.service.DevAppMngService;
import com.gv.appstore.mng.service.EmailMngService;
import com.gv.appstore.pojo.LvDeveloperApp;
import com.gv.appstore.pojo.LvDeveloperTemplet;
import com.gv.appstore.util.CheckData;
import com.gv.appstore.util.DevelopConstant;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.datastructure.page.SimplePage;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.StringUtil;
import com.lshop.common.util.Constants;
import com.lshop.common.util.DateUtil;
import com.lshop.emailsend.service.EmailSendService;


@Service("DevAppMngService")
public class DevAppMngServiceImpl implements DevAppMngService{
	private static final Log logger = LogFactory.getLog(DevAppMngServiceImpl.class);
	@Resource 
	private HibernateBaseDAO dao;
	@Resource 
	private EmailSendService emailSend;
	@Resource 
	private EmailMngService emailTempl;
	
	@Override
	public Pagination findPage(Dto dto) throws ServiceException {
		SimplePage page = (SimplePage)dto.get("page");
		LvDeveloperApp app = (LvDeveloperApp)dto.get("app");
		String createDateStart = dto.getAsString("createDateStart");
		String createDateEnd = dto.getAsString("createDateEnd");
		
		Pagination pag = new Pagination();
		StringBuilder hql = new StringBuilder("from LvDeveloperApp where 1=1");
		if(null != app){
			if(!StringUtil.IsNullOrEmpty(app.getEmail())){
				if(CheckData.matcherDate(app.getEmail())) return pag;
				hql.append(" and email like '%").append(CheckData.checkData(app.getEmail().trim())).append("%'");
			}
			if(!StringUtil.IsNullOrEmpty(app.getName())){
				if(CheckData.matcherDate(app.getName())) return pag;
				hql.append(" and name like '%").append(CheckData.checkData(app.getName().trim())).append("%'");
			}
			if(!StringUtil.IsNullOrEmpty(app.getAppType())){
				if(CheckData.matcherDate(app.getAppType())) return pag;
				hql.append(" and appType like '%").append(CheckData.checkData(app.getAppType().trim())).append("%'");
			}
			if(null != app.getReviewStatus() && app.getReviewStatus() != -1){
				hql.append(" and reviewStatus =").append(app.getReviewStatus());
			}
			if(null != app.getIsCharge() && app.getIsCharge() != -1){
				hql.append(" and isCharge =").append(app.getIsCharge());
			}
			if(null != app.getIsAd() && app.getIsAd() != -1){
				hql.append(" and isAd =").append(app.getIsAd());
			}
		}
		
		if(!StringUtil.IsNullOrEmpty(createDateStart)){
			createDateStart = createDateStart + " 00:00:00";
			hql.append(" and createTime >= '").append(createDateStart).append("'");
		}
		if(!StringUtil.IsNullOrEmpty(createDateEnd)){
			createDateEnd = createDateEnd + " 23:59:59";
			hql.append(" and createTime <= '").append(createDateEnd).append("'");
		}
		hql.append(" order by reviewStatus,createTime desc");
		
		pag = this.dao.getMapListByHql(hql.toString(), page.getPageNum(), page.getNumPerPage(), null);
		return pag;
	}
	
	@Override
	public LvDeveloperApp get(Dto dto) throws ServiceException {
		int id = dto.getAsInteger("id");
		return (LvDeveloperApp)dao.load(LvDeveloperApp.class, id);
	}
	
	@Override
	public LvDeveloperApp add(Dto dto) throws ServiceException {
		System.out.println("1111");
		return null;
	}
	
	@Override
	public BaseDto doESBServiceWithHttpInvoke(String serviceId,
			String methodName, BaseDto dto) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void verify(Dto dto) throws ServiceException {
		logger.info("开始审核。。。");
		LvDeveloperApp app = (LvDeveloperApp)dto.get("app");
		
		if(null != app.getReason()){
			app.setReason(app.getReason().replace("\r\n", ""));
		}
		
		StringBuilder hql = new StringBuilder("update LvDeveloperApp set reviewStatus=");
		hql.append(app.getReviewStatus()).append(",reason='").append(app.getReason()).append("'");
		hql.append(",reviewTime=now()");
		hql.append(" where id=").append(app.getId());
		this.dao.update(hql.toString(), null);
		//发送审核结果邮件
		try{
			dto.put("id", app.getId());
			app = this.get(dto);
			
			dto.put("mallSign", DevelopConstant.getSuitFlag(app.getStoreFlag()));//店铺标识
			if(app.getReviewStatus()==1){//审核通过
				dto.put("ttype", DevelopConstant.emailAppReviewYes);
			}
			if(app.getReviewStatus()==2){//审核不通过
				dto.put("ttype", DevelopConstant.emailAppReviewNo);
			}
			
			LvDeveloperTemplet templ = this.emailTempl.getBy(dto);
			if(null != templ){
				dto.put("userEmail", app.getEmail());
				dto.put("title", templ.getTitle());
				
				String content = templ.getContent();
				content = content.replace("{email}", app.getEmail()).
						replace("{appname}", app.getName()).replace("{reason}", app.getReason()).
						replace("{senddate}", DateUtil.parseDateTime(new Date()));
				dto.put("content", content);
				dto.put("mallFlag", Constants.STORE_TO_MALL_SYSTEM.get(app.getStoreFlag()));
				this.emailSend.sendEmailNoticePub(dto);
			}else{
				logger.info("没有相应的邮件模版！----"+app.getEmail());
			}
		}catch(Exception e){
			logger.info("邮件发送失败！");
		}
	}
}
