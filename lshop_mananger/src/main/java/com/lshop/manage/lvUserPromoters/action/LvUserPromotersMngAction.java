/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvUserPromoters.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.criterion.Order;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.email.impl.CommonMailSender;
import com.gv.core.exception.ActionException;
import com.gv.core.exception.DAOException;
import com.gv.core.exception.ServiceException;
import com.gv.core.util.ObjectUtils;
import com.gv.core.web.action.BaseAction;
import com.lshop.common.pojo.logic.LvCoupon;
import com.lshop.common.pojo.logic.LvEmailTpl;
import com.lshop.common.pojo.logic.LvRankfirstInfo;
import com.lshop.common.pojo.logic.LvRankfirstLogin;
import com.lshop.common.pojo.logic.LvUser;
import com.lshop.common.pojo.logic.LvUserCoupon;
import com.lshop.common.pojo.logic.LvUserPromoters;
import com.lshop.common.util.DateUtils;
import com.lshop.common.util.ExcelFileHelp;
import com.lshop.manage.common.action.BaseManagerAction;

/**
 * @author 好视网络-软件二部：唐迪
 * @version 1.0
 * @since 1.0
 */@Controller("LvUserPromotersMngAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvUserPromotersMngAction extends BaseManagerAction{
	private static final Log logger	= LogFactory.getLog(LvUserPromotersMngAction.class);
	private LvUserPromoters lvUserPromoters=new LvUserPromoters();
    public Integer check;
    private List lvEmailTplList; 
	private LvCoupon vo;
	private String validitydate;
	private LvRankfirstLogin rankFirstLogin;
	private LvRankfirstInfo rankFirstInfo;
	private Integer couponid;
	private int filesize;
	private BufferedInputStream excelStream;
	private String couponCode;
	private Date versionTime;

	




	/**
	 * 进入添加页面
	 * @return
	 * @throws ActionException
	 */
	public String befSave()throws ActionException{
		return "befSave";
	}
	
	/**
	 * 添加
	 * @return
	 * @throws ActionException
	 */
	public String save()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvUserPromotersMngAction.save() method begin*****");
		}
		//根据实际情况添加
//		lvUserPromoters.setCreatedBy("1");
//		lvUserPromoters.setCreatedDate(new Date());
//		lvUserPromoters.setLastUpdatedBy("1");
//		lvUserPromoters.setLastUpdatedDate(new Date());
		dto.put("model", lvUserPromoters);
		this.doService("LvUserPromotersService", "save", dto);
		
		json.setStatusCode(200);
//		json.setMessage("成功！");
//		json.setNavTabId("lvUserPromoters");
		if (logger.isInfoEnabled()){
			logger.info("***** LvUserPromotersMngAction.save() method end*****");
		}
		return AJAX;
	}
	
	/**
	 * 列表
	 * @return
	 * @throws ActionException
	 */
	public String list()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvUserPromotersMngAction.list() method begin*****");
		}
		//设置显示页数
		if(ObjectUtils.isNotEmpty(page.getNumPerPage())){
//			page.setPageNumShown(page.getNumPerPage());
		}
		request.setAttribute("approvalStatus", request.getParameter("lvUserPromoters.approvalStatus"));
		
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("page",page);
		dto.put("model", lvUserPromoters);
		dto.put("request", this.getRequest());
		dto.put("parentName", request.getParameter("parentName"));
		dto.put("couponCode", couponCode);
		
		request.setAttribute("parentName", request.getParameter("parentName"));
		page = (Pagination)this.doService("LvUserPromotersService", "findPage", dto);
    	if (logger.isInfoEnabled()){
			logger.info("***** LvUserPromotersMngAction.list() method end*****");
		}
		return LIST;
	}
	/**
	 * 详情
	 * @return
	 * @throws ActionException
	 */
	public String view()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvUserPromotersMngAction.view() method begin*****");
		}
		dto.put("model", lvUserPromoters);
		lvUserPromoters = (LvUserPromoters)this.doService("LvUserPromotersService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvUserPromotersMngAction.view() method end*****");
		}
		return "view";
	}
	
	/**
	 * 跳入编辑页面
	 * @return
	 * @throws ActionException
	 */
	public String befEdit()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvUserPromotersMngAction.befEdit() method begin*****");
		}
		dto.put("model", lvUserPromoters);
		lvUserPromoters = (LvUserPromoters)this.doService("LvUserPromotersService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvUserPromotersMngAction.befEdit() method end*****");
		}
		return "edit";
	}
	/**
	 * 编辑
	 * @return
	 * @throws ActionException
	 */
	public String edit()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvUserPromotersMngAction.edit() method begin*****");
		}
		dto.put("model", lvUserPromoters);
		this.doService("LvUserPromotersService", "update", dto);
		json.setStatusCode(200);
//		json.setMessage("修改成功！");
//		json.setNavTabId("lvUserPromoters");
		if (logger.isInfoEnabled()){
			logger.info("***** LvUserPromotersMngAction.edit() method end*****");
		}
		return AJAX;
	}
	
	public String bfEdit() throws ActionException
	{
		if (logger.isInfoEnabled()){
			logger.info("***** LvUserPromotersMngAction.bfEdit() method begin*****");
		}
		dto.put("id", request.getParameter("id"));
		LvUserPromoters userPromoters=(LvUserPromoters)this.doService("LvUserPromotersService", "getUserPromoter", dto);
		request.setAttribute("lvUserPromoters", userPromoters);
		if (logger.isInfoEnabled()){
			logger.info("***** LvUserPromotersMngAction.bfEdit() method end*****");
		}
		return "bfEdit";
	}
	
	public String saveEdit() throws ActionException
	{
		if (logger.isInfoEnabled()){
			logger.info("***** LvUserPromotersMngAction.saveEdit() method begin*****");
		}
		//lvUserPromoters.setSpecialAmount(Double.parseDouble(request.getParameter("lvUserPromoters.specialAmount")));
		if (lvUserPromoters.getUserType()==0) {//普通用户没有特殊金额
			lvUserPromoters.setSpecialAmount(0.0);
		}
		dto.put("userPromoters", lvUserPromoters);
		
		this.doService("LvUserPromotersService", "saveEdit", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvUserPromotersMngAction.saveEdit() method end*****");
		}
		json.setStatusCode(200);
		return AJAX;
	}
	
	/**
	 * 删除
	 * @return
	 * @throws ActionException
	 */
	public String del()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvUserPromotersMngAction.del() method begin*****");
		}
		dto.put("approvalStatus", request.getParameter("approvalStatus"));
		dto.put("model", lvUserPromoters);
		//删除model
		this.doService("LvUserPromotersService", "del", dto);

		json.setStatusCode(200);
		json.doNavTabTodo();
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvUserPromotersMngAction.del() method end*****");
		}
		return AJAX;
	}
	
	/**
	 * 批量删除
	 * @return
	 * @throws ActionException
	 */
	public String delList()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvUserPromotersMngAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				lvUserPromoters.setId(Integer.parseInt(temp_ids[i]));
				dto.put("model", lvUserPromoters);
				//删除model
				this.doService("LvUserPromotersService", "del", dto);
				}
			}
		}
		json.setStatusCode(200);
		json.doNavTabTodo();
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvUserPromotersMngAction.delList() method end*****");
		}
		return AJAX;
	}
	public String auditing(){
		this.lvEmailTplList=(List)this.doService("LvUserPromotersService", "getEmailTplList", dto);
		//根据id查询二级推广商信息
		dto.put("id", lvUserPromoters.getId());
		LvUserPromoters lvUserPromoters=(LvUserPromoters) this.doService("LvUserPromotersService", "getUserPromoter", dto);
		this.getRequest().setAttribute("lvUserPromoters",lvUserPromoters);
		return "auditing";
	}
	public String isChecked(){
		 Integer statusCode=200;
		 Integer newPwd=100000+new Random().nextInt(899999);//随机生成密码
		 dto.put("newPwd", newPwd) ;
		 dto.setDefaultPo(lvUserPromoters);
	//	 String url=request.getParameter("url");
	//	 String htmlContent=request.getParameter("htmlContent");
		 Integer upId=lvUserPromoters.getId();
		if(check==1){//单个用户审核 
			Integer userid=(Integer)  this.doService("LvUserPromotersService", "isCheckedYes", dto);//审核成功生成用户
			dto.clear();
			if(userid>0){
				lvUserPromoters.setUid(userid);
				dto.put("model", lvUserPromoters);
				lvUserPromoters = (LvUserPromoters)this.doService("LvUserPromotersService", "get", dto);
				dto.clear();
				//validitydate+=" 23:59:59";
				vo.setValidityDate(DateUtils.convertToDateTime(validitydate));
				dto.setDefaultPo(vo);
				dto.put("count", 1);
				dto.put("ids", userid);
				dto.put("type", this.getRequest().getParameter("type"));
				Map map=(Map) doService("CouponManageServer", "createSpreadCoupon", dto);
				List<LvCoupon> numList=(List<LvCoupon>)map.get("numList");//生成优惠券
				List<LvUser> userList=(List<LvUser>)map.get("userList");//获取用户数据
				dto.clear();
			    if(numList!=null){
			    	LvUser lvUser=userList.get(0);
			    	LvUserCoupon userCoupon=new LvUserCoupon();
					userCoupon.setUid(lvUser.getId());
					userCoupon.setCouponCode(numList.get(0).getCouponCode());
					dto.setDefaultPo(userCoupon);
					Integer issuccess=(Integer)this.doService("LvUserService", "addUserCoupon", dto);
				if(issuccess>0){//发邮件
				dto.clear();
			    dto.put("tplKey", request.getParameter("tplKey"));
				LvEmailTpl lvEmailTpl = (LvEmailTpl) this.doService("lvEmailManageService",
						"get", dto);
				/**发件邮件配置**/
				dto.put("title", lvEmailTpl.getEnTitle());// 邮件标题
				dto.put("userEmail", lvUserPromoters.getEmail().trim());// 用户email
				//获取配置的发件人
				dto.put("emailService",this.getText("lshop.email.send.smtpHost"));
				dto.put("sendEmail", this.getText("lshop.email.code.many.send.userName"));
				dto.put("emailPwd", this.getText("lshop.email.code.many.send.password"));
				SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
				String content=(lvEmailTpl.getEn()+lvEmailTpl.getZh()).replace("{couponCode}", numList.get(0).getCouponCode()).replace("{pwd}", newPwd.toString()).
				replace("{realName}", lvUserPromoters.getRealName()).replace("{email}", lvUserPromoters.getEmail()).
			    replace("{sendtime}", format.format(new Date()));//替换模板参数
				dto.put("content", content);// 邮件内容
				doService("emailSendService", "sendEmailCode", dto);//发送email
				}
			 }
			}else{
				statusCode=300;
				json.setMessage("操作失败！");
			}
		}else if(check==-1){//审核不通过
		      this.doService("LvUserPromotersService", "isCheckedNo", dto);
		}
		json.setStatusCode(statusCode);
		
//		json.setCallbackType(null);
		return AJAX;
	}

	/**
	 * 搜索一级推广商信息列表
	 * @return JSP页面
	 * @throws ActionException
	 * @author zhengxue  updateTime:2012/03/31
	 */
	public String rankPromoterList() throws ActionException
	{
		dto.put("page", page);
		dto.put("nickname", request.getParameter("nickname"));
		dto.put("email", request.getParameter("email"));
		
		super.doService("LvUserPromotersService", "getRankPromoter", dto);
		
		//存放一级推广商 邮箱、昵称、已申请数量、申请数量、下级推广台数、累积佣金、已结算佣金、未结算佣金、创建时间、最后登陆时间、登陆用户ID列表
		Pagination p1=(Pagination)dto.get("p1");
		request.setAttribute("page", p1);
		
		//存放一级推广商个数、二级推广商个数、总推广台数、佣金累积
		Pagination p2=(Pagination)dto.get("p2");
		List<Object[]> objList=(List<Object[]>)p2.getList();
		if (objList!=null&&objList.size()>0) {
			Object[] obj=objList.get(0);
			request.setAttribute("rankFirstUserNum", obj[0]);
			request.setAttribute("totalSettlementNum", obj[1]);
			request.setAttribute("totalSettlementAmount", obj[2]);
		}
		
		request.setAttribute("promoterUserNum", dto.getAsLong("promoterUserNum"));
		
		//搜索完成后，将搜索框中的内容默认显示
		request.setAttribute("nickname", request.getParameter("nickname"));
		request.setAttribute("email", request.getParameter("email"));
		
		return "rankPromoter";
	}

	public String bfadd() throws ActionException
	{
        return "bfadd";		
	}
	
	/**
	 * 一级推广商添加操作
	 * @return AJAX
	 * @throws ActionException
	 * @author zhengxue  updateTime:2012/03/31
	 */
	public String add() throws ActionException
	{
		dto.put("rankFirstLogin", rankFirstLogin);
		dto.put("rankFirstInfo", rankFirstInfo);
		super.doService("LvUserPromotersService", "add", dto);
		json.setCallbackType(null);
		return AJAX;
	}
	
	/**
	 * 一级推广商编辑操作
	 * @return AJAX
	 * @throws ActionException
	 * @author zhengxue  updateTime:2012/03/31
	 */
	public String bfEditRankPromoter() throws ActionException
	{
		dto.put("id", id);
		LvRankfirstLogin rankFirstLogin=(LvRankfirstLogin)super.doService("LvUserPromotersService", "getLogin", dto);
		request.setAttribute("rankFirstLogin", rankFirstLogin);
		request.setAttribute("rankFirstInfo",(LvRankfirstInfo)(dto.get("rankFirstInfo")));
		return "bfEditPromoter";
	}
	
	/**
	 * 一级推广商查看操作
	 * @return AJAX
	 * @throws ActionException
	 * @author zhengxue  
	 * @since 1.0 2012/03/31
	 */
	public String bfViewRankPromoter() throws ActionException
	{
		dto.put("id", id);
		LvRankfirstInfo info = (LvRankfirstInfo)this.doService("LvUserPromotersService", "view", dto);
		request.setAttribute("info", info);
		return "bfViewRankPromoter";
	}
	
	public String editRankPromoter() throws ActionException
	{
	  dto.put("rankFirstLogin", rankFirstLogin);
	  dto.put("rankFirstInfo",rankFirstInfo);
	  super.doService("LvUserPromotersService", "editRankPromoter", dto);

	  return AJAX;
	}

	/**
	 * 一级推广商审核模块，跳转到审核页面
	 * @return 审核页面
	 * @throws ActionException
	 * @author zhengxue  updateTime:2012/04/01
	 */	
	public String befCheck() throws ActionException
	{
		dto.put("id",id);
		lvEmailTplList=(List<LvEmailTpl>)super.doService("LvUserPromotersService", "getEmailModel", dto);
		LvRankfirstLogin rankFirstLogin=(LvRankfirstLogin)dto.get("rankFirstLogin");
		LvRankfirstInfo rankFirstInfo=(LvRankfirstInfo)dto.get("rankFirstInfo");
		request.setAttribute("rankFirstLogin", rankFirstLogin);
		request.setAttribute("rankFirstInfo", rankFirstInfo);
		return "befCheck";
	}
	
	/**
	 * 一级推广商审核模块，保存审核页面数据
	 * @return AJAX
	 * @throws ActionException
	 * @author zhengxue  updateTime:2012/04/01
	 */	
	public String checkRankPromoter() throws ActionException
	{
		dto.put("userPromoters", this.lvUserPromoters);
		dto.put("rankFirstLogin.id", request.getParameter("rankFirstLogin.id"));
		dto.put("type", this.getRequest().getParameter("type"));
		dto.put("num", request.getParameter("num"));
		dto.put("specialAmount", request.getParameter("specialAmount"));
		
//		validitydate+=" 23:59:59";
	    vo.setValidityDate(DateUtils.convertToDateTime(validitydate));
		dto.setDefaultPo(vo);
		
		//获取配置的发件人
		dto.put("emailService",this.getText("lshop.email.send.smtpHost"));
		dto.put("sendEmail", this.getText("lshop.email.code.many.send.userName"));
		dto.put("emailPwd", this.getText("lshop.email.code.many.send.password"));
		
		super.doService("LvUserPromotersService", "sendRankEmail", dto);
		json.setCallbackType(null);
		return AJAX;
	}
	 /**
	  * 启用推广码
	  * @return
	  * @throws ActionException
	  */
     public String start() throws ActionException{
     	 dto.put("stopFlag", 1);
     	 dto.put("couponId", couponid);
    	 doService("LvUserPromotersService","startOrStop",dto);
 		 json.doNavTabTodo();
 		
    	 return AJAX;
     }
     /**
      * 停用推广码
      * @return
      * @throws ActionException
      */
    public String stop() throws ActionException{
    	 dto.put("stopFlag", 0);
     	 dto.put("couponId", couponid);
    	 doService("LvUserPromotersService","startOrStop",dto);
 		 json.doNavTabTodo();
    	 return AJAX;
     }
    
    /**
     * 启用帐号
     * @return
     * @throws ActionException
     */
    public String startAccount() throws ActionException{
    	 dto.put("flag", 1);
    	 dto.put("uid", lvUserPromoters.getUid());
   	     doService("LvUserPromotersService","startOrStopAccount",dto);
		 json.setCallbackType(null);
		
   	 return AJAX;
    }
    /**
     * 停用帐号
     * @return
     * @throws ActionException
     */
   public String stopAccount() throws ActionException{
   	 dto.put("flag", 0);
   	 dto.put("uid", lvUserPromoters.getUid());
   	 doService("LvUserPromotersService","startOrStopAccount",dto);
		json.setCallbackType(null);
   	 return AJAX;
    }
    
	/**
	 * 推广成绩列表  
	 * @return 列表页面
	 * @throws ActionException
	 * @author zhengxue  updateTime:2012/04/01
	 */
   public String gradelist() throws ActionException{
		dto.put("couponCode", request.getParameter("couponCode"));
		
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("page", page);
		dto.put("model", lvUserPromoters);
		
		this.doService("LvUserPromotersService", "gradePage", dto);
		page = (Pagination)dto.get("pagination");
		
		request.setAttribute("page", page);
		request.setAttribute("totalNum", dto.get("totalNum"));
		request.setAttribute("totalAmout", dto.get("totalAmout"));
		request.setAttribute("totalOrder", dto.get("totalOrder"));
	    	
	   return "gradelist";
   }
   
   
	/**
	 * @description 一级推广商管理模块，查看业绩操作 
	 * @version 2.0
	 * @autho zhengxuew
	 * @updateDate 2012/04/25
	 */
  public String showGrade() throws ActionException{
		dto.put("couponCode", request.getParameter("couponCode"));
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("page", page);
		dto.put("model", lvUserPromoters);
		dto.put("myId", request.getParameter("myId"));
		
		this.doService("LvUserPromotersService", "showGrade", dto);
		page = (Pagination)dto.get("pagination");
		
		request.setAttribute("page", page);
		request.setAttribute("totalNum", dto.get("totalNum"));
		request.setAttribute("totalAmout", dto.get("totalAmout"));
		request.setAttribute("totalOrder", dto.get("totalOrder"));
		request.setAttribute("myId", request.getParameter("myId"));
		request.setAttribute("couponCode", request.getParameter("couponCode"));
	    	
	   return "showGrade";
  }
  
   public String export() throws ActionException, IOException, RowsExceededException, WriteException
   {
	   //删除文件上传临时文件夹下面所有临时文件
	   deleteTempFile(request);
	   
 	   dto.put("approvalStatus", request.getParameter("approvalStatus"));
 	   dto.put("ids", this.getIds());
 		List list = (List)this.doService("LvUserPromotersService","export",dto);
 		String path = request.getRealPath("/temp")+"/"+ System.currentTimeMillis() + ".tmp";
 		ExcelFileHelp.create(path, list);
 		File file = new File(path);
 		try 
 		{
 			filesize=(int) file.length();
 			excelStream = new BufferedInputStream(new FileInputStream(file));
 		}
 		catch (FileNotFoundException e)
 		{
 		}
 		file.delete();
 		return "excel";
   }
   
   public String exportExl() throws ActionException, IOException, RowsExceededException, WriteException
   {
	   //删除文件上传临时文件夹下面所有临时文件
	   deleteTempFile(request);
	   
	   dto.put("page", page);
 	   dto.put("ids", this.getIds());
 	   
 	   //判断id是否为空，为空，表示直接是推广业绩模块；不为空，表示查看的是一级推广商管理中的某个推广商所有业绩
 	   dto.put("myId", request.getParameter("myId"));
 	   
 		List list = (List)this.doService("LvUserPromotersService","exportExcel",dto);
 		String path = request.getRealPath("/temp")+"/"+ System.currentTimeMillis() + ".tmp";
 		ExcelFileHelp.create(path, list);
 		File file = new File(path);
 		try 
 		{
 			filesize=(int) file.length();
 			excelStream = new BufferedInputStream(new FileInputStream(file));
 		}
 		catch (FileNotFoundException e)
 		{
 		}
 		file.delete();
 		return "exportExcel";
   }
   
   public String getDetailList() throws ActionException
   {
	   dto.put("keyword", request.getParameter("keyword"));
	   dto.put("page", page);
	   dto.put("id", request.getParameter("id"));
	   page=(Pagination)super.doService("LvUserPromotersService", "getDetailList", dto);
//	   if(page!=null)
//	   {
//	      List<Object[]> orderList=(List<Object[]>)page.getList();
//	      request.setAttribute("orderList", orderList);
//	   }
	  
	   return "detailList";
   }
   
   public String  exportOrder() throws ActionException, IOException, RowsExceededException, WriteException
   {
 	  dto.put("id", request.getParameter("id"));
 	  dto.put("ids", this.getIds());
 		List list = (List)this.doService("LvUserPromotersService","exportOrder",dto);
 		String path = request.getRealPath("/temp")+"/"+ System.currentTimeMillis() + ".tmp";
 		ExcelFileHelp.create(path, list);
 		File file = new File(path);
 		try 
 		{
 			filesize=(int) file.length();
 			excelStream = new BufferedInputStream(new FileInputStream(file));
 		}
 		catch (FileNotFoundException e)
 		{
 		}
 		file.delete();
 	  return "orderdetail";
   }
   
   /**
    * 
    * @Method: exportOrderGrade 
    * @Description:  [导出推广业绩订单列表信息]  
    * @Author:       [liaoxiongjian]     
    * @CreateDate:   [2012-9-28 下午05:10:52]   
    * @UpdateUser:   [liaoxiongjian]     
    * @UpdateDate:   [2012-9-28 下午05:10:52]   
    * @UpdateRemark: [说明本次修改内容]  
    * @throws ActionException
    * @throws IOException
    * @throws RowsExceededException
    * @throws WriteException 
    * @return String
    */
   public String exportOrderGrade()throws ActionException, IOException, RowsExceededException, WriteException{
	        //删除文件上传临时文件夹下面所有临时文件
	        deleteTempFile(request);
	        
	        dto.put("ids", ids);
	 		List list = (List)this.doService("LvUserPromotersService","exportOrderGrade",dto);
	 		String path = request.getRealPath("/temp")+"/"+ System.currentTimeMillis() + ".tmp";
	 		ExcelFileHelp.create(path, list);
	 		File file = new File(path);
	 		try 
	 		{
	 			filesize=(int) file.length();
	 			excelStream = new BufferedInputStream(new FileInputStream(file));
	 		}
	 		catch (FileNotFoundException e)
	 		{
	 		}
	 		file.delete();
	 	  return "exportOrderGrade";
   }
 
   /**
    * 一级推广商管理模块导出
    * @return JSP页面
    * @throws ActionException,IOException,RowsExceededException,WriteException
    * @author zhengxue
    * @since 1.0  2012/03/31
    */
   public String  exportInfos() throws ActionException, IOException, RowsExceededException, WriteException
   {
	    //删除文件上传临时文件夹下面所有临时文件
	    deleteTempFile(request);
	   
 	    dto.put("ids", this.getIds());
 		List list = (List)this.doService("LvUserPromotersService","exportInfos",dto);
 		String path = request.getRealPath("/temp")+"/"+ System.currentTimeMillis() + ".tmp";
 		ExcelFileHelp.create(path, list);
 		File file = new File(path);
 		try 
 		{
 			filesize=(int) file.length();
 			excelStream = new BufferedInputStream(new FileInputStream(file));
 		}
 		catch (FileNotFoundException e)
 		{
 		}
 		file.delete();
 	  return "exportInfos";
   }
   
   /**
    * 一级推广商结算管理
    * @return JSP页面
    * @throws ActionException
    * @author zhengxue
    * @since 1.0  2012/04/06
    */
   public String  accountManage() throws ActionException
   {
	  dto.put("page", page);
	  dto.put("realName", request.getParameter("realName"));
	  dto.put("email", request.getParameter("email"));
	  
	  Pagination pagination=(Pagination)this.doService("LvUserPromotersService","getLvRankFirstInfoList",dto);

	  request.setAttribute("page", pagination);
	  request.setAttribute("email", request.getParameter("email"));
	  request.setAttribute("realName", request.getParameter("realName"));
	  
 	  return "accountManageList";
   }
   
   /**
    * 一级推广商结算操作
    * @return JSP页面
    * @throws ActionException
    * @author zhengxue
    * @since 1.0  2012/04/10
    */
   public String pay() throws ActionException
   {
	      BaseUsers user=(BaseUsers)getSession().getAttribute("USER");
		  dto.put("USER", user);
		  dto.put("uid", request.getParameter("uid"));
		  dto.put("versionTime", versionTime);
		  Boolean isFlagTmp=(Boolean) this.doService("LvUserPromotersService", "isVersionFailure", dto);
		  if(!isFlagTmp){
			  json.setMessage("版本失效,刷新当前数据列表!");
			  json.setCallbackType(null);
			  return AJAX;
		  }
		  Boolean isFlag=(Boolean) this.doService("LvUserPromotersService", "pay", dto);
		  if(!isFlag){
			  json.setStatusCode(300);
			  json.setMessage("当前无可生成的结算清单数据");
		  }else{
			  json.setStatusCode(200);
			  json.setMessage("生成结算单成功！");
		  }
		  json.setCallbackType(null);
		  return AJAX;
   }

   /**
    * 一级推广商结算记录(日志)
    * @return JSP页面
    * @throws ActionException
    * @author zhengxue
    * @since 1.0  2012/04/06
    */
   public String  accountRecord() throws ActionException
   {
	  dto.put("realName", request.getParameter("realName"));
	  dto.put("email", request.getParameter("email"));
	  dto.put("page",page);
	  
	  page=(Pagination)super.doService("LvUserPromotersService", "getLogList", dto);
	  List<Object[]> objList=(List<Object[]>)page.getList();
	  
	  SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  for(int i=0;i<objList.size();i++)
	  {
		  Object[] obj=objList.get(i);
		  obj[0]=dateFormat.format(obj[0]);
		  String aids=obj[6].toString();
		  int size=0;
		  if(!("").equals(aids)){
			  String[] aid=aids.split(",");
			  size=aid.length;
		  }
		  obj[6]=size;
	  }
	  
	  request.setAttribute("objList", objList); 
	  request.setAttribute("realName", request.getParameter("realName"));
	  request.setAttribute("email", request.getParameter("email"));
 	  return "accountRecordList";
   }
   
   /**
    * 一级推广商结算记录导出
    * @return JSP页面
    * @throws ActionException,IOException,RowsExceededException,WriteException
    * @author zhengxue
    * @since 1.0  2012/04/11
    */
   public String  exportRecords() throws ActionException, IOException, RowsExceededException, WriteException
   {
	    //删除文件上传临时文件夹下面所有临时文件
	    deleteTempFile(request);
	   
 	    dto.put("ids", this.getIds());
 		List list = (List)this.doService("LvUserPromotersService","exportRecords",dto);
 		String path = request.getRealPath("/temp")+"/"+ System.currentTimeMillis() + ".tmp";
 		ExcelFileHelp.create(path, list);
 		File file = new File(path);
 		try 
 		{
 			filesize=(int) file.length();
 			excelStream = new BufferedInputStream(new FileInputStream(file));
 		}
 		catch (FileNotFoundException e)
 		{
 		}
 		file.delete();
 	  return "exportInfos";
   }
   
   /**
    * 一级推广商结算记录中查看订单详情
    * @return JSP页面
    * @throws ActionException,IOException,RowsExceededException,WriteException
    * @author zhengxue
    * @since 1.0  2012/04/11
    */
	public String getOrderList() throws ActionException
	{
		String aids=request.getParameter("aids");
		String oid=request.getParameter("oid");
		String ccode=request.getParameter("ccode");
		
		dto.put("aids", aids);
		dto.put("oid", oid);
		dto.put("ccode", ccode);
		dto.put("page",page);
		
		page=(Pagination)super.doService("LvUserPromotersService", "getOrderList", dto);
		
		request.setAttribute("page", page);
		request.setAttribute("oid", oid);
		request.setAttribute("ccode", ccode);
		
		return "orderList";
	}
	
	   /**
	    * 一级推广商结算记录中的订单详情数据导出
	    * @return JSP页面
	    * @throws ActionException,IOException,RowsExceededException,WriteException
	    * @author zhengxue
	    * @since 1.0  2012/04/11
	    */
	   public String  exportOrders() throws ActionException, IOException, RowsExceededException, WriteException
	   {
		    //删除文件上传临时文件夹下面所有临时文件
		    deleteTempFile(request);
		    
	 	    dto.put("ids", this.getIds());
	 		List list = (List)this.doService("LvUserPromotersService","exportOrders",dto);
	 		String path = request.getRealPath("/temp")+"/"+ System.currentTimeMillis() + ".tmp";
	 		ExcelFileHelp.create(path, list);
	 		File file = new File(path);
	 		try 
	 		{
	 			filesize=(int) file.length();
	 			excelStream = new BufferedInputStream(new FileInputStream(file));
	 		}
	 		catch (FileNotFoundException e)
	 		{
	 		}
	 		file.delete();
	 	  return "exportOrders";
	   }

	   public void deleteTempFile(HttpServletRequest request){
			//删除文件上传临时文件夹下面所有临时文件
			String pathTmp = request.getRealPath("/temp");
			File fileTemp=new File(pathTmp);
			if(fileTemp.isDirectory()){
				 File[] f=fileTemp.listFiles();
				 for (File fs : f) {
					boolean isFlag=fs.delete();
					if (logger.isInfoEnabled()) {
						logger.info("删除文件是否成功:"+fs.getName()+"->"+isFlag);
					}
				}
			}
		}  
	   
		public Integer getCheck() {
			return check;
		}
	
		public void setCheck(Integer check) {
			this.check = check;
		}
	
		public List getLvEmailTplList() {
			return lvEmailTplList;
		}
	
		public void setLvEmailTplList(List lvEmailTplList) {
			this.lvEmailTplList = lvEmailTplList;
		}
	
		public LvCoupon getVo() {
			return vo;
		}
	
		public void setVo(LvCoupon vo) {
			this.vo = vo;
		}
	
		public String getValiditydate() {
			return validitydate;
		}
	
		public void setValiditydate(String validitydate) {
			this.validitydate = validitydate;
		}
	
		public Integer getCouponid() {
			return couponid;
		}
	
		public void setCouponid(Integer couponid) {
			this.couponid = couponid;
		}
		public LvRankfirstLogin getRankFirstLogin() {
			return rankFirstLogin;
		}
	
		public void setRankFirstLogin(LvRankfirstLogin rankFirstLogin) {
			this.rankFirstLogin = rankFirstLogin;
		}
	
		public LvRankfirstInfo getRankFirstInfo() {
			return rankFirstInfo;
		}
	
		public void setRankFirstInfo(LvRankfirstInfo rankFirstInfo) {
			this.rankFirstInfo = rankFirstInfo;
		}
	
		public void setFilesize(int filesize) {
			this.filesize = filesize;
		}
	
		public void setExcelStream(BufferedInputStream excelStream) {
			this.excelStream = excelStream;
		}
		
		public int getFilesize() {
			return filesize;
		}
	
		public BufferedInputStream getExcelStream() {
			return excelStream;
		}
		
		
		public String getCouponCode() {
			return couponCode;
		}
	
		public void setCouponCode(String couponCode) {
			this.couponCode = couponCode;
		}
		
		public LvUserPromoters getLvUserPromoters() {
			return lvUserPromoters;
		}
	
		public void setLvUserPromoters(LvUserPromoters lvUserPromoters) {
			this.lvUserPromoters = lvUserPromoters;
		}
		
		public Date getVersionTime() {
			return versionTime;
		}

		public void setVersionTime(Date versionTime) {
			this.versionTime = versionTime;
		}
}
