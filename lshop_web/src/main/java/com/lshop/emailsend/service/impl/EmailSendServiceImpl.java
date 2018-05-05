package com.lshop.emailsend.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.impl.BasePo;
import com.gv.core.email.impl.CommonMailSender;
import com.gv.core.email.impl.DefaultMail;
import com.gv.core.util.BeanUtils;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.JobEmailBean;
import com.lshop.common.pojo.logic.LvMailConfig;
import com.lshop.common.thread.MailCreator;
import com.lshop.common.thread.impl.MailCreatorImpl;
import com.lshop.common.util.PropertiesHelper;
import com.lshop.emailsend.service.EmailSendService;
import com.lshop.emailsend.service.LvMailConfigService;


@Service("emailSendService")
public class EmailSendServiceImpl implements EmailSendService {
	
@Resource
private LvMailConfigService lvMailConfigService;

	/**
	 * 
	 * @Method: sendEmailNotice
	 * @Description: [多邮件发送公共方法]
	 * @Author: [liaoxj]
	 * @CreateDate: [2014-9-3 上午10:38:52]
	 * @UpdateUser: [liaoxj]
	 * @UpdateDate: [2014-9-3 上午10:38:52]
	 * @UpdateRemark: [说明本次修改内容]
	 * @throws
	 */
	@Override
	public Boolean sendEmailNotice(Dto dto) {
		Map map = this.getEmailProperties(dto);
		MailCreatorImpl mailsend = new MailCreatorImpl();
		mailsend.setSender(new CommonMailSender(String.valueOf(map.get("emailService")), 
				                                String.valueOf(map.get("email")), 
				                                String.valueOf(map.get("emailPwd"))));

		// TODO Auto-generated method stub
		DefaultMail mail = new DefaultMail();
		mail.setMailTo(dto.getAsString("userEmail"));// 获取收件人Email
		mail.setMailSubject(dto.getAsString("title"));
		mail.setMailForm(String.valueOf(map.get("email")));
		mail.setMailFormName(String.valueOf(map.get("mailfromName")));
		mail.setMailBody(dto.getAsString("content"));
		mailsend.create(mail);
		return true;
	}
	
	/**
	 * 
	 * @Method: getEmailProperties 
	 * @Description:  [多商城服务端邮件发送配置读取]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-10-24 上午11:47:17]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-10-24 上午11:47:17]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return 
	 * @return Map
	 */
	public Map getEmailProperties(Dto dto){
		Map map=new HashMap();
		String mallFlag=(String) dto.get("mallFlag");
		if(ObjectUtils.isNotEmpty(mallFlag)){
			LvMailConfig lvMailConfig=lvMailConfigService.findByMallFlag(dto);
			if(lvMailConfig!=null){
				String [] arrUsers=lvMailConfig.getSendUserName().split(",");
				List<String> emaiList=new ArrayList<String>();
				for (int i = 0; i < arrUsers.length; i++) {
					emaiList.add(arrUsers[i]);	
				}
				
				Random random =new Random();
				int num=random.nextInt(emaiList.size());
				String email=emaiList.get(num);
				
				map.put("emailService",lvMailConfig.getSendSmtpHost());
				map.put("email",email);
				map.put("emailPwd",lvMailConfig.getSendPassword());
				map.put("mailfromName", lvMailConfig.getMailFrom());
			}
		}
		return  map;
	}
}
