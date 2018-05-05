package com.lshop.manage.blog.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.core.datastructure.page.Pagination;
import com.gv.core.util.FileUpload;
import com.gv.core.util.ObjectUtils;
import com.gv.core.util.StringUtil;
import com.gv.core.web.action.BaseAction;
import com.lshop.common.pojo.logic.LvBlogContent;
import com.lshop.common.pojo.logic.LvBlogSubscribe;
import com.lshop.common.pojo.logic.LvBlogTags;
import com.lshop.common.pojo.logic.LvBlogType;
import com.lshop.common.pojo.logic.LvEmailTpl;
import com.lshop.common.pojo.logic.LvUserSubscribe;
import com.lshop.common.util.Constants;
import com.lshop.common.util.HtmlRegexpUtil;
import com.lshop.manage.common.action.BaseManagerAction;

import org.apache.struts2.ServletActionContext;

/**
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.blog.action.LvBlogSubscribeAction.java]  
 * @ClassName:    [LvBlogSubscribeAction]   
 * @Description:  [邮件订阅管理-action业务层]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-6-29 下午04:04:44]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-6-29 下午04:04:44]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v1.0] 
 *
 */
@Controller("LvBlogSubscribeAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvBlogSubscribeAction extends BaseManagerAction{
private static final Log logger	= LogFactory.getLog(LvBlogSubscribeAction.class);
	

	private LvBlogSubscribe lvBlogSubscribe;
	private LvBlogType lvBlogType;
	private LvBlogContent lvBlogContent;

	private File img;            //接收这个上传的文件
	private String imgFileName;  //Struts2提供的格式，在文件名后+FileName就是上传文件的名字
	

	/**
	 * 
	 * @Method: list 
	 * @Description:  [分页查询所有邮件订阅信息列表]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-6-29 下午04:06:55]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-6-29 下午04:06:55]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return String
	 * @throws
	 */
	public String list(){
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogSubscribeAction.list() method begin*****");
		}
		dto.put("page", page);
		dto.put("model", lvBlogSubscribe);
		page = (Pagination)this.doService("LvBlogSubscribeService", "list", dto);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogSubscribeAction.list() method end*****");
		}
		return LIST;
	}
	
    /**
     * 
     * @Method: befEdit 
     * @Description:  [	跳转到编辑订阅设置页面]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-6-29 下午04:08:15]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-6-29 下午04:08:15]   
     * @UpdateRemark: [说明本次修改内容]  
     * @return String
     * @throws
     */
	public String befEdit(){
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogSubscribeAction.befEdit() method begin*****");
		}
		dto.put("model", lvBlogSubscribe);
		lvBlogSubscribe = (LvBlogSubscribe)this.doService("LvBlogSubscribeService", "get", dto);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogSubscribeAction.befEdit() method end*****");
		}
		return "edit";
	}
	
   /**
    * 
    * @Method: edit 
    * @Description:  [设置邮件订阅信息-后台]  
    * @Author:       [liaoxiongjian]     
    * @CreateDate:   [2012-6-29 下午04:09:30]   
    * @UpdateUser:   [liaoxiongjian]     
    * @UpdateDate:   [2012-6-29 下午04:09:30]   
    * @UpdateRemark: [说明本次修改内容]  
    * @return String
    * @throws
    */
	public String edit(){
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogSubscribeAction.edit() method begin*****");
		}
		
		//获取资源管理域名
		String systemFlag=Constants.STORE_TO_MALL_SYSTEM.get(lvBlogSubscribe.getStoreId());//根据店铺标志查询商城体系
	    String domainPostfix=Constants.MALL_TO_DOMAIN_POSTFIX.get(systemFlag);//更加商城体系查找体系对于后缀域名
	    String resDomain="http://res"+domainPostfix;  
		String resPath = this.getText("res.domain.path"); //获取资源文件路径
		String filepath=resPath+"/upload/"+lvBlogSubscribe.getStoreId()+"/res/images";//文件上传资源路径
				
		//上传广告图片
//		String root = request.getRealPath("/");
//		String lshopcn = this.getText("lshop.lshopcn.url");
//		String absolutePath = "/res/images/mailbanner/";
		if (img != null && img.isFile()) {
			String fileName = new SimpleDateFormat("yyyyMMddHHmmssS")
					.format(new Date())
					+ imgFileName.substring(imgFileName.lastIndexOf("."));
			FileUpload.upload(img, filepath, fileName);
			lvBlogSubscribe.setAdBanner(resDomain+"/upload/"+lvBlogSubscribe.getStoreId()+"/res/images/"+fileName);
		}
		
		//设置默认的广告图片
//		if(ObjectUtils.isEmpty(lvBlogSubscribe.getAdBanner())){
//			lvBlogSubscribe.setAdBanner(lshopcn + "/res/images/mail_banner.gif");
//		}
		//保存订阅邮件配置信息
		dto.put("model", lvBlogSubscribe);
		this.doService("LvBlogSubscribeService", "update", dto);
		
		//调用邮件定时发送线程任务
		this.doService("BlogEmailSubscribeService", "init2", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogSubscribeAction.edit() method end*****");
		}
		return AJAX;
	}
	
    /**
     * 
     * @Method: befSave 
     * @Description:  [跳转到新增邮件订阅界面-后台]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-6-29 下午04:10:29]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-6-29 下午04:10:29]   
     * @UpdateRemark: [说明本次修改内容]  
     * @return String
     * @throws
     */
	public String befSave(){
		return "befSave";
	}
	
    /**
     * 
     * @Method: save 
     * @Description:  [保存邮件订阅-后台]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-6-29 下午04:10:53]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-6-29 下午04:10:53]   
     * @UpdateRemark: [说明本次修改内容]  
     * @return String
     * @throws
     */
	public String save(){
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogSubscribeAction.save() method begin*****");
		}
		dto.put("model", lvBlogSubscribe);
		Boolean isFlag=(Boolean) this.doService("LvBlogSubscribeService", "save", dto);
		if(!isFlag){
			this.json.setStatusCode(300);
			this.json.setMessage("已经存在邮件订阅设置，请删除原有数据或者选择修改！");
			return AJAX;
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogSubscribeAction.save() method end*****");
		}
		return AJAX;
	}
	
    /**
     * 
     * @Method: del 
     * @Description:  [删除邮件订阅-后台]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-6-29 下午04:11:39]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-6-29 下午04:11:39]   
     * @UpdateRemark: [说明本次修改内容]  
     * @return String
     * @throws
     */
	public String del(){
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogSubscribeAction.del() method begin*****");
		}
		dto.put("model", lvBlogSubscribe);
		this.doService("LvBlogSubscribeService", "delete", dto);
		
		json.setStatusCode(200);
	    json.setCallbackType(null);
	    if (logger.isInfoEnabled()){
			logger.info("***** LvBlogSubscribeAction.del() method begin*****");
		}
		return AJAX;	
	}
	
	/**
	 * 
	 * @Method: delList 
	 * @Description:  [批量删除邮件订阅]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-9-26 上午10:43:09]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-9-26 上午10:43:09]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return 
	 * @return String
	 */
	public String delList(){
		
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				int id=Integer.parseInt(temp_ids[i].trim());
				lvBlogSubscribe=new LvBlogSubscribe();
				lvBlogSubscribe.setId(id);
				dto.put("model", lvBlogSubscribe);
				//删除model
				this.doService("LvBlogSubscribeService", "delete", dto);
				}
			}
		}
		json.setStatusCode(200);
		json.setCallbackType(null);
		return AJAX;
	}
	
	
    /**
     * 
     * @Method: sendEmailNow 
     * @Description:  [针对已经订阅博客文章的用户立即发送邮件，将博客推荐文章发送给用户阅读]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-6-29 下午04:13:05]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-6-29 下午04:13:05]   
     * @UpdateRemark: [说明本次修改内容]  
     * @return String
     * @throws
     */
	public String sendEmailNow(){
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogSubscribeAction.sendEmailNow() method begin*****");
		}
		PrintWriter out = null;
		String adBanner="";
		String adUrl="";
		String userId="";
		try {
			
		String lshopcn=this.getText("lshop.lshopcn.url");
		//1.获取订阅邮件模板信息
		dto.put("tplKey", "BLOG_SUBSCRIBE");
		LvEmailTpl	lvEmailTpl = (LvEmailTpl) this.doService("lvEmailManageService","get", dto);
		dto.clear();
		
		//2.获取邮件订阅配置信息
		dto.put("model", lvBlogSubscribe);
		lvBlogSubscribe=(LvBlogSubscribe)this.doService("LvBlogSubscribeService", "get", dto);
		
		//3.获取配置的发件人
		dto.put("emailService",this.getText("lshop.email.blog.send.smtpHost"));
		dto.put("sendEmail", this.getText("lshop.email.blog.send.userName"));
		dto.put("emailPwd", this.getText("lshop.email.blog.send.password"));
		
		//4.获取用户邮件订阅列表
		List userList=(List) doService("LvUserSubscribeService", "list", dto);
        for(int i=0;i<userList.size();i++){
        	LvUserSubscribe ls=(LvUserSubscribe)userList.get(i);
        	
        	dto.put("title", lvBlogSubscribe.getTitle());// 邮件标题
    		dto.put("userEmail", ls.getEmail());// 用户email
    		
    		//5.获取最新的订阅邮件的博文内容
    		page.setNumPerPage(10);
    		dto.setDefaultPage(page);
    		List contentList=((Pagination)this.doService("LvBlogContentService", "listTop10", dto)).getList();
    		String content="";
    		//手动拼装要发送的html邮件博文主题订阅内容
    		for(int num=0;num<contentList.size();num++){
    			lvBlogContent=(LvBlogContent)contentList.get(num);
    			if(lvBlogContent!=null){
    				String contentTmp=HtmlRegexpUtil.filterHtml(lvBlogContent.getContent());
    				contentTmp=contentTmp.replace("&nbsp;", "");
    				if(contentTmp.length()>100){
    					contentTmp=contentTmp.substring(0,100);
    				}
    				lvBlogContent.setContent(contentTmp);
    				List<LvBlogType> typeList=(List<LvBlogType>) this.doService("LvBlogTypeService", "typelist", dto);
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
    					" <img alt=\"\" src=\""+lshopcn+"/res/images/xian.gif\" />" +
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
    		if(ObjectUtils.isNotEmpty(ls.getId())){
    			userId=ls.getId().toString();
    		}
    		
    		content=lvEmailTpl.getZh().replace("${content}", content)
    		                          .replace("${adBanner}",adBanner)
    		                          .replace("${adUrl}",adUrl)
    		                          .replace("${lshopcn}",lshopcn)
    		                          .replace("${userId}",userId);
    		dto.put("content", content);//邮件内容
    		doService("emailSendService", "sendEmailNoticePub", dto);
        }
			out = this.getResponse().getWriter();
			out.print(true);
		} catch (Exception e) {
			if(out!=null){
				out.print(false);
			}
			e.printStackTrace();
		}
	
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogSubscribeAction.sendEmailNow() method end*****");
		}
		return null;
	}
	
    /**
     * 
     * @Method: preview 
     * @Description:  [订阅邮件发送内容预览]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-6-29 下午04:15:41]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-6-29 下午04:15:41]   
     * @UpdateRemark: [说明本次修改内容]  
     * @return String
     * @throws
     */
	public String preview(){
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogSubscribeAction.preview() method begin*****");
		}
		//最新10条的博文信息
		page.setNumPerPage(10);
		dto.put("page", page);
		page=(Pagination)this.doService("LvBlogContentService", "listTop10", dto);
		
		List list=page.getList();
		for(int i=0;i<list.size();i++){
			LvBlogContent lvBlogContent=(LvBlogContent)list.get(i);
			String content=HtmlRegexpUtil.filterHtml(lvBlogContent.getContent());
			content=content.replace("&nbsp;", "");
			lvBlogContent.setContent(content);
		}
		page.setList(list);
		
		//订阅邮件头部广告部分
		dto.put("model", lvBlogSubscribe);
		lvBlogSubscribe=(LvBlogSubscribe)this.doService("LvBlogSubscribeService", "get", dto);
		this.getRequest().setAttribute("lvBlogSubscribe", lvBlogSubscribe);
		
		//查询博客类别列表信息
		dto.put("model", lvBlogType);
		List typeList=((Pagination)doService("LvBlogTypeService", "list", dto)).getList();
		this.getRequest().setAttribute("typeList", typeList);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvBlogSubscribeAction.preview() method end*****");
		}
		return "preview";
	}
	

	

	
	public LvBlogSubscribe getLvBlogSubscribe() {
		return lvBlogSubscribe;
	}
	public void setLvBlogSubscribe(LvBlogSubscribe lvBlogSubscribe) {
		this.lvBlogSubscribe = lvBlogSubscribe;
	}

	public LvBlogType getLvBlogType() {
		return lvBlogType;
	}
	public void setLvBlogType(LvBlogType lvBlogType) {
		this.lvBlogType = lvBlogType;
	}

	public LvBlogContent getLvBlogContent() {
		return lvBlogContent;
	}

	public void setLvBlogContent(LvBlogContent lvBlogContent) {
		this.lvBlogContent = lvBlogContent;
	}

	public File getImg() {
		return img;
	}

	public void setImg(File img) {
		this.img = img;
	}

	public String getImgFileName() {
		return imgFileName;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}





}
