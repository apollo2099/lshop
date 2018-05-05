package com.lshop.manage.blog.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Service;
import com.lshop.common.thread.MailCreator;
import com.lshop.common.thread.impl.MailCreatorImpl;
import com.lshop.common.util.HtmlRegexpUtil;
import com.lshop.common.util.PropertiesHelper;

import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvBlogContent;
import com.lshop.common.pojo.logic.LvBlogSubscribe;
import com.lshop.common.pojo.logic.LvBlogType;
import com.lshop.common.pojo.logic.LvEmailTpl;
import com.lshop.common.pojo.logic.LvUserSubscribe;
import com.gv.core.email.impl.DefaultMail;
import com.gv.core.exception.ServiceException;
import com.gv.core.email.impl.CommonMailSender;

/**
 * 
 * @ClassName EmailQuartzJob
 * @Description TODO
 * @author shenyanghong ahong2011@gmail.com
 * @date 2012-6-29
 * 
 */
@Service("EmailQuartzJob")
public class EmailQuartzJob implements Job{
	@Resource
	private HibernateBaseDAO lvlogicReadDao;
	@Resource
	private HibernateBaseDAO lvlogicWriteDao;
	
	private Logger logger = Logger.getLogger(this.getClass());
	private LvBlogSubscribe lvBlogSubscribe;
	private LvBlogContent lvBlogContent;
	private LvEmailTpl lvEmailTpl;
	
	private MailCreator mailCreator;// 邮件发送服务
	private String mailfrom;//发件人地址
	private String mailrecipient;//收件人地址
	private String mailtemp;//邮件模板
	private String mailsubject;//邮件主题
	private String mailfromName;//发件人
	public EmailQuartzJob(){
		
	}

	/**
	 * 定义发送邮件的任务
	 */
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		JobDataMap data = context.getJobDetail().getJobDataMap();
		lvlogicReadDao=(HibernateBaseDAO)data.get("lvlogicReadDao");
		lvlogicWriteDao=(HibernateBaseDAO)data.get("lvlogicWriteDao");
		this.sendEmailSubscribe();
	}
	
	/**
	 * 查询配置的邮件订阅配置信息
	 */
	public LvBlogSubscribe getSubscribe()throws ServiceException {
		String hql="from LvBlogSubscribe";
		List list=this.lvlogicReadDao.find(Finder.create(hql),1, 1).getList();
		if(list!=null&&list.size()>0){
			lvBlogSubscribe=(LvBlogSubscribe) list.get(0);
		}
		return lvBlogSubscribe;
	}
	
	
	/**
	 * 查询邮件订阅内容
	 */
	public List<LvBlogContent> getContent(){
//		String hql="from LvBlogContent where isDelete=1 order by clickNum desc";
//		List<LvBlogContent> list=this.lvlogicReadDao.find(hql, null);
		String hql = "from LvBlogContent where isDelete=1 ";
		hql+=" order by createTime desc ";
		return (List<LvBlogContent>) lvlogicReadDao.find(Finder.create(hql),1, 10).getList();
	}

 
	/**
	 * 查询博客订阅邮件模块信息
	 */
	public LvEmailTpl getEmialTpl() throws ServiceException {
		LvEmailTpl tpl=null;
		String hql="from LvEmailTpl where tplKey=:key";
		Map param=new HashMap();
		param.put("key", "BLOG_SUBSCRIBE");
		List<LvEmailTpl> list=this.lvlogicReadDao.find(hql, param);
		if(list!=null&&!list.isEmpty()){
			tpl=list.get(0);
		}
		return tpl;
	}	
	/**
	 *查询订阅用户关联信息 
	 */
	public List<LvUserSubscribe> getUserSubscribe() throws ServiceException {
		String hql="from LvUserSubscribe where 1=1 " ;
		hql+="order by id desc";
		List<LvUserSubscribe> list=this.lvlogicReadDao.find(hql, null);
		return list;
	}
	
	
	public List<LvBlogType> getType()throws ServiceException {
		String hql="from LvBlogType";
		return this.lvlogicReadDao.find(hql, null);
	}

	/**
	 * 发送邮件
	 */
	public Boolean sendEmailSubscribe() throws ServiceException {
		//订阅发送邮件的变量
		String emailService= (String) PropertiesHelper.getConfiguration().getProperty("lshop.email.blog.send.smtpHost");//邮件发送服务名
		String sendEmail=(String) PropertiesHelper.getConfiguration().getProperty("lshop.email.blog.send.userName");    //发送人邮箱
		String emailPwd=(String) PropertiesHelper.getConfiguration().getProperty("lshop.email.blog.send.password");     //发送人密码
		String lshopcn=(String) PropertiesHelper.getConfiguration().getProperty("lshop.lshopcn.url");                   //网址域名配置
		String adBanner="";//广告图片链接地址
		String adUrl="";   //广告超链接地址
		String userId="";  //用户id
		
		//1.获取订阅邮件模板信息
		lvEmailTpl=this.getEmialTpl();
		//2.获取邮件订阅配置信息
		lvBlogSubscribe=this.getSubscribe();
        //3.获取配置的发件人
		
		//4.获取用户邮件订阅列表
		List<LvUserSubscribe> users=this.getUserSubscribe();
		for (LvUserSubscribe lvUserSubscribe:users) {
			List<LvBlogContent> contents=this.getContent();
			String content="";	
			for (LvBlogContent lvBlogContent:contents) {
				if(lvBlogContent!=null){
					String contentTmp=HtmlRegexpUtil.filterHtml(lvBlogContent.getContent());
					contentTmp=contentTmp.replace("&nbsp;","");
					if(contentTmp.length()>100){
						contentTmp=contentTmp.substring(0,100);
					}
					lvBlogContent.setContent(contentTmp);
					List<LvBlogType> typeList=this.getType();
    				for(LvBlogType lvBlogType:typeList){
    				if(lvBlogContent.getType().equals(lvBlogType.getId())){
    				content+=" <div class=\"main_z\">" +
					" <div class=\"main_n1\">" +
					" <h2  class=\"title2\">"+lvBlogContent.getTitle()+"</h2>" +
					" <div class=\"date\">"+lvBlogContent.getCreateTime()+"</div>"+
					" <div class=\"cb\">" +
					" </div>" +
					" </div>" +
					" <p class=\"text\">"+lvBlogContent.getContent()+"...." +
					" </p>" +
					" <div class=\"a7\">作者："+lvBlogContent.getUserName()+"分类："+lvBlogType.getType()+"</div>" +
					" <p class=\"xian\">" +
					" <img alt=\"\" src=\"http://www.lshopcn.com/res/images/xian.gif\" />" +
					" </p>" +
					" </div>";	
    				}
    				}
    			}
				
			}
			if(ObjectUtils.isNotEmpty(lvBlogSubscribe.getAdBanner())){
    			adBanner=lvBlogSubscribe.getAdBanner();
    		}
    		if(ObjectUtils.isNotEmpty(lvBlogSubscribe.getAdUrl())){
    			adUrl=lvBlogSubscribe.getAdUrl();
    		}
    		if(ObjectUtils.isNotEmpty(lvUserSubscribe.getId())){
    			userId=lvUserSubscribe.getId().toString();
    		}
    		
    		content=lvEmailTpl.getZh().replace("${content}", content)
    		                          .replace("${adBanner}",adBanner)
    		                          .replace("${adUrl}",adUrl)
    		                          .replace("${lshopcn}",lshopcn)
    		                          .replace("${userId}",userId);
    	
			//发送邮件信息
			DefaultMail mail = new DefaultMail();
			mail.setMailTo(lvUserSubscribe.getEmail());//获取收件人Email
			mail.setMailSubject(lvBlogSubscribe.getTitle());//邮件标题
			mail.setMailForm(sendEmail);//发件人
			mail.setMailFormName(mailfromName);
			mail.setMailBody(content);
			
			MailCreatorImpl mailsend=new MailCreatorImpl();
			mailsend.setSender(new CommonMailSender(emailService,sendEmail,emailPwd));
			mailsend.create(mail);
		}
		
		return true;
	}

	
	public HibernateBaseDAO getLvlogicReadDao() {
		return lvlogicReadDao;
	}

	public void setLvlogicReadDao(HibernateBaseDAO lvlogicReadDao) {
		this.lvlogicReadDao = lvlogicReadDao;
	}

	public HibernateBaseDAO getLvlogicWriteDao() {
		return lvlogicWriteDao;
	}

	public void setLvlogicWriteDao(HibernateBaseDAO lvlogicWriteDao) {
		this.lvlogicWriteDao = lvlogicWriteDao;
	}

}
