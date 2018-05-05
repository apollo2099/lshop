package com.gv.appstore.mng.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gv.appstore.mng.service.DeveloperMngService;
import com.gv.appstore.mng.service.EmailMngService;
import com.gv.appstore.pojo.LvDeveloper;
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


@Service("DeveloperMngService")
public class DeveloperMngServiceImpl implements DeveloperMngService{
	private static final Log logger = LogFactory.getLog(DeveloperMngServiceImpl.class);
	@Resource 
	private HibernateBaseDAO dao;
	@Resource 
	private EmailSendService emailSend;
	@Resource 
	private EmailMngService emailTempl;
	
	@Override
	public Pagination findPage(Dto dto) throws ServiceException {
		SimplePage page = (SimplePage)dto.get("page");
		LvDeveloper developer = (LvDeveloper)dto.get("developer");
		String createDateStart = dto.getAsString("createDateStart");
		String createDateEnd = dto.getAsString("createDateEnd");
		
		Pagination pag = new Pagination();
		StringBuilder hql = new StringBuilder("from LvDeveloper where 1=1");
		if(null != developer){
			if(!StringUtil.IsNullOrEmpty(developer.getEmail())){
				if(CheckData.matcherDate(developer.getEmail())) return pag;
				hql.append(" and email like '%").append(CheckData.checkData(developer.getEmail().trim())).append("%'");
			}
			if(!StringUtil.IsNullOrEmpty(developer.getContactName())){
				if(CheckData.matcherDate(developer.getContactName())) return pag;
				hql.append(" and contactName like '%").append(CheckData.checkData(developer.getContactName().trim())).
				append("%'");
			}
			if(!StringUtil.IsNullOrEmpty(developer.getCompany())){
				if(CheckData.matcherDate(developer.getCompany())) return pag;
				hql.append(" and company like '%").append(CheckData.checkData(developer.getCompany().trim())).
				append("%'");
			}
			if(null != developer.getDtype() && developer.getDtype() != -1){
				hql.append(" and dtype =").append(developer.getDtype());
			}
			if(null != developer.getDstatus() && developer.getDstatus() != -1){
				hql.append(" and dstatus =").append(developer.getDstatus());
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
		hql.append(" order by dstatus,createTime desc");
		
		pag = this.dao.getMapListByHql(hql.toString(), page.getPageNum(), page.getNumPerPage(), null);
		return pag;
	}
	
	@Override
	public LvDeveloper get(Dto dto) throws ServiceException {
		int id = dto.getAsInteger("id");
		return (LvDeveloper)dao.load(LvDeveloper.class, id);
	}
	
	@Override
	public LvDeveloper add(Dto dto) throws ServiceException {
		LvDeveloper d = (LvDeveloper)dto.get("developer");
		dao.save(d);
		return null;
	}
	
	@Override
	public BaseDto doESBServiceWithHttpInvoke(String serviceId,
			String methodName, BaseDto dto) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LvDeveloper verify(Dto dto) throws ServiceException {
		logger.info("开始审核。。。");
		LvDeveloper developer = (LvDeveloper)dto.get("developer");
		
		if(null != developer.getReason()){
			developer.setReason(developer.getReason().replace("\r\n", ""));
		}
		
		StringBuilder hql = new StringBuilder("update LvDeveloper set dstatus=");
		hql.append(developer.getDstatus()).append(",reason='").append(developer.getReason()).append("'");
		hql.append(",reviewTime=now()");
		hql.append(" where id=").append(developer.getId());
		this.dao.update(hql.toString(), null);
		
		//发送审核结果邮件
		try{
			dto.put("id", developer.getId());
			developer = this.get(dto);
			
			dto.put("mallSign", DevelopConstant.getSuitFlag(developer.getStoreFlag()));//店铺标识
			if(developer.getDstatus()==1){//审核通过
				dto.put("ttype", DevelopConstant.emailDevReviewYes);
			}
			if(developer.getDstatus()==2){//审核不通过
				dto.put("ttype", DevelopConstant.emailDevReviewNo);
			}
			
			LvDeveloperTemplet templ = this.emailTempl.getBy(dto);
			if(null != templ){
				dto.put("userEmail", developer.getEmail());
				dto.put("title", templ.getTitle());
				
				String content = templ.getContent();
				content = content.replace("{email}", developer.getEmail()).
						replace("{toapp}", "").replace("{reason}", developer.getReason()).
						replace("{senddate}", DateUtil.parseDateTime(new Date()));
				dto.put("content", content);
				dto.put("mallFlag",  Constants.STORE_TO_MALL_SYSTEM.get(developer.getStoreFlag()));
				this.emailSend.sendEmailNoticePub(dto);
			}else{
				logger.info("没有相应的邮件模版！----"+developer.getEmail());
			}
		}catch(Exception e){
			logger.info("邮件发送失败！");
		}
		return null;
	}
}
