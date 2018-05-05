package com.lshop.promotermanager.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.core.datastructure.page.Pagination;
import com.gv.core.util.StringUtil;
import com.gv.core.util.cryption.MD5;
import com.lshop.common.action.BaseAction;
import com.lshop.common.pojo.logic.LvEmailTpl;
import com.lshop.common.pojo.logic.LvRankfirstInfo;
import com.lshop.common.pojo.logic.LvRankfirstLogin;
import com.lshop.common.util.CookieUtil;
import com.lshop.excenter.ExcenterConstant;

@SuppressWarnings({"serial","unchecked"})
@Controller("RankPromoterManageAction")
@Scope("prototype")
public class RankPromoterManageAction extends BaseAction
{
  private String uname;
  private String pwd;
  private LvRankfirstInfo userp;
  private String newPwd;
  private LvRankfirstLogin rankFirstLogin;
  private String couponCode;
  
  public LvRankfirstLogin getRankFirstLogin() {
	return rankFirstLogin;
}
  
  public void setRankFirstLogin(LvRankfirstLogin rankFirstLogin) {
	this.rankFirstLogin = rankFirstLogin;
}
  
  /*private LvUser lvUser;
  
	public LvUser getLvUser() {
		return lvUser;
	}

	public void setLvUser(LvUser lvUser) {
		this.lvUser = lvUser;
	}*/
	
	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
  
	public LvRankfirstInfo getUserp() {
		return userp;
	}
	
	public void setUserp(LvRankfirstInfo userp) {
		this.userp = userp;
	}
  
  private String getUid() throws Exception{
		return  CookieUtil.getValue(this.getRequest(), ExcenterConstant.RANK_ID, true,getText("domain.base.name"));
  }
  
  public String login() throws Exception
  {
    dto.put("uname", uname.trim());
	dto.put("pwd", pwd);
	dto.put("ip", this.getRemoteAddress());
	LvRankfirstLogin rankFirstLogin = (LvRankfirstLogin) super.doService("RankPromoterManageService", "login", dto);
	if (rankFirstLogin != null)
	{
	  this.addCookie(ExcenterConstant.RANK_ID, rankFirstLogin.getId().toString(), true, -1,getText("domain.base.name"));
	  SimpleDateFormat  format = new SimpleDateFormat("yyyy年-MM月-dd日 HH:mm");
//	  String lastTime =format.format(rankFirstLogin.getLastTime());
	  String lastTime = format.format(new Date());
	  String str="uid="+rankFirstLogin.getId().toString()+"*email="+rankFirstLogin.getEmail()+"*nickname="+rankFirstLogin.getNickname()+"*lastTime="+lastTime;
	  this.addCookie(ExcenterConstant.RANK_PROMOTER,str, true, -1,getText("domain.base.name"));
	  return this.LOGIN;
	}
	else
	{
	  this.getRequest().setAttribute("msg", "用户名或密码不正确！");
	  return this.INPUT;
	}
  }
  
	public String logout() throws Exception
	{
		this.removeByCookie(ExcenterConstant.RANK_ID,getText("domain.base.name"));
		this.removeByCookie(ExcenterConstant.RANK_PROMOTER,getText("domain.base.name"));
		return "logout";
	}
  
	/**
	 * @deprecated 在一级推广商登陆情况下，显示一级推广商信息及其所对应的所有二级推广商信息
	 * @return JSP页面
	 * @throws Exception
	 * @author zhengxue  updateTime:2012/03/28
	 */
	@SuppressWarnings("unchecked")
    public String getPromtCodeList() throws Exception
    {
	  page.setNumPerPage(10);
	  dto.put("page", page);
	  dto.put("uid",this.getUid());
	  dto.put("couponCode", this.getCouponCode());
	  
	  super.doService("RankPromoterManageService", "getPromtCodeList", dto);
	  
	  Pagination p1=(Pagination)dto.get("p1");
	  this.getRequest().setAttribute("pagination",p1);
	  List<Object[]> objList=(List<Object[]>)p1.getList(); //该一级推广商旗下所有的推广码、截止有效期、领取时间、成交订单数、推广台数、佣金累积、二级推广商ID
	  
	  this.getRequest().setAttribute("objList", objList);
	  this.getRequest().setAttribute("couponCode", this.getCouponCode());
	  this.getRequest().setAttribute("couponNum", dto.getAsInteger("couponNum"));
	  this.getRequest().setAttribute("couponCodeNum",dto.getAsLong("couponCodeNum"));
	  this.getRequest().setAttribute("settlementStatus", Short.parseShort(dto.get("settlementStatus").toString())); //存放申请状态，如果已提交申请，则将按钮屏蔽掉
	  
	  Pagination p2=(Pagination)dto.get("p2");
	  List<Object[]> objList2=(List<Object[]>)p2.getList(); //该一级推广商的总推广台数、可结算佣金、未结算佣金、已结算佣金
	  Object[] ob=new Object[4];
	  if(objList2.size()>0)
	  {
		  ob=objList2.get(0);
	  }
	  else
	  {
		  ob[0]=0;
		  ob[1]=0.0;
		  ob[2]=0.0;
		  ob[3]=0.0;
	  }
//	  this.getRequest().setAttribute("totaNum", ob[0]);
	  this.getRequest().setAttribute("totaNum", dto.get("allPnum"));
	  this.getRequest().setAttribute("settlementAmount", ob[1]);
	  this.getRequest().setAttribute("nonSettlementAmount", ob[2]);
	  this.getRequest().setAttribute("settlementedAmount", ob[3]);
	  
	  return "promtCodeList";
   }
  
  public String basic() throws Exception{
		dto.put("uid",this.getUid());
		this.userp=(LvRankfirstInfo)this.doService("RankPromoterManageService", "getUserInfo", dto);
		return "basic";
	}
  
  public String  edit() throws Exception{
      if(null==userp||"".equals(userp.getAccountNumber().trim())||null==userp.getAccountTypes()){
      	 this.getSession().setAttribute("msg", "false");
		}else{
		dto.setDefaultPo(userp);
		String uid=this.getCookieValue(ExcenterConstant.RANK_ID, true);
		dto.put("uid", uid);
		this.doService("RankPromoterManageService", "edit", dto);
		this.getSession().setAttribute("msg", "true");
		}
		return "edit";
	}
  
	public String editpwd() throws Exception{
		this.getRequest().setAttribute("flag", "pwd");
		String uid=this.getCookieValue(ExcenterConstant.RANK_ID, true);
	
		return "editpwd";
	}
	
	public String doEditpwd() throws Exception{
		String uid=this.getCookieValue(ExcenterConstant.RANK_ID, true);
		dto.put("uid", uid);
		pwd=MD5.convert32(this.pwd.trim());
		dto.put("origPwd",pwd);
		if(newPwd.length()<6){
			this.getRequest().setAttribute("msg", "密码不能小于6位！");
			return "editpwd";
		}
		dto.put("newPwd",  MD5.convert32(this.newPwd));
		this.rankFirstLogin=(LvRankfirstLogin)this.doService("RankPromoterManageService", "getUser", dto);
		if(!pwd.equals(rankFirstLogin.getPwd())){
			this.getRequest().setAttribute("msg", "原密码不正确！");
			return "editpwd";
		}
		this.doService("RankPromoterManageService", "editPwd", dto);
		this.getSession().setAttribute("msg", "密码修改成功，下次登录请用新密码！");
		return "doEditpwd";
	}
	
	
  public void sendRequest() throws Exception
  {
	  dto.put("uid",this.getUid());
	  dto.put("num", this.getRequest().getParameter("num"));
	  super.doService("RankPromoterManageService", "sendRequest", dto);
  }
  
	/**
	 * @deprecated 显示推广码的详情页面
	 * @return JSP页面
	 * @throws Exception
	 * @author zhengxue  updateTime:2012/03/29
	 */
  @SuppressWarnings("unchecked")
  public String getInfo() throws Exception
  {
	  dto.put("page", page);
	  dto.put("couponCode",this.getRequest().getParameter("code"));
	  super.doService("RankPromoterManageService","getInfo", dto);

	  Pagination p1=(Pagination)dto.get("p1");
	  List<Object[]> list1=(List<Object[]>)p1.getList();
	  Object[] ob=list1.get(0);
	  this.getRequest().setAttribute("realName", ob[0]);
	  this.getRequest().setAttribute("email", ob[1]);
	  this.getRequest().setAttribute("contryname", ob[2]);
	  this.getRequest().setAttribute("couponCode", dto.getAsString("couponCode"));
	  
	  Pagination p2=(Pagination)dto.get("p2");
	  
	  this.getRequest().setAttribute("code", this.getRequest().getParameter("code"));
	  this.getRequest().setAttribute("objList", p2.getList());	
	  this.getRequest().setAttribute("pagination", p2);
	 
	  return "info";
  }
  
  /**
   * @description 显示可申请（以及未满15天结算条件的）结算佣金的订单详情页面
   * @version 1.0 
   * @author zhengxue 
   * @createDate 2012/04/18
   */
  public String getSettleInfo() throws Exception
  {
	  dto.put("page", page);
	  dto.put("uid",this.getUid());
	  dto.put("remark", this.getRequest().getParameter("remark"));
	  super.doService("RankPromoterManageService","getSettleInfo", dto);
	  
	  Pagination pagination=(Pagination)dto.get("pagination");
	  List<Object[]> objList=(List<Object[]>)pagination.getList();
	  
	  SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	  if(objList!=null&&objList.size()>0)
	  {
		 for(int i=0;i<objList.size();i++)
		 {
			Object[] obj=objList.get(i);
			if(null!=obj[2] && !("").equals(obj[2]))
			{
				obj[2]=dateFormat.format(obj[2]);
			}
			else
			{
				obj[2]="";
			}
		}
	 }
	  
	  this.getRequest().setAttribute("remark", this.getRequest().getParameter("remark"));
	  this.getRequest().setAttribute("objList", objList);
	  this.getRequest().setAttribute("pagination", pagination);
	  return "info";
  }

  public String password()
  {
		if(rankFirstLogin!=null)
		{
			String email=rankFirstLogin.getEmail().trim();
			if(!"".equals(email))
			{
				dto.put("email", email);
				Integer newPwd=100000+new Random().nextInt(899999);
				dto.put("newPwd", MD5.convert32(newPwd.toString())); 
				LvRankfirstLogin rankFirstLogin =(LvRankfirstLogin)this.doService("RankPromoterManageService", "getRankUser", dto);
				if(rankFirstLogin!=null)
				{
					dto.put("tplKey", "FIND_USER_PASSWORD");
					dto.put("storeId", "tvpadcn");
					LvEmailTpl	lvEmailTpl = (LvEmailTpl) this.doService("lvEmailManageService","get", dto);
					dto.put("title", lvEmailTpl.getEnTitle());
					dto.put("userEmail", rankFirstLogin.getEmail());
					String body=(lvEmailTpl.getEn()+lvEmailTpl.getZh()).replace("{nickname}", rankFirstLogin.getNickname()).replace("{pwd}",newPwd.toString()).replace("{email}",rankFirstLogin.getEmail());
					body=body.replace("{sendtime}", StringUtil.dateFormat(new Date(), "yyyy-MM-dd HH:mm:ss"));
					dto.put("content", body);
					
					//获取配置的发件人
					dto.put("emailService",this.getText("lshop.email.send.many.smtpHost"));
					dto.put("sendEmail", this.getText("lshop.email.send.many.userName"));
					dto.put("emailPwd", this.getText("lshop.email.send.many.password"));
					dto.put("mailfromName", this.getText("lshop.email.mailfromname"));
				    doService("emailSendService", "sendEmailCode", dto);
				}
				else
				{
					return this.ERROR;
				}
				return "pwdresult";
			}
		}
		return this.ERROR;
	}
  
	/**
	 * @deprecated 一级推广商结算查询模块
	 * @return JSP页面
	 * @throws Exception
	 * @author zhengxue 
	 * @since 1.0  2012/03/29
	 */
	@SuppressWarnings("unchecked")
	public String getSettledList() throws Exception 
	{
		dto.put("uid",this.getUid());//dto.put("uid",100);  dto.put("uid",request.getParameter("uid"));
		dto.put("page",page);
		Pagination pagination=(Pagination)super.doService("RankPromoterManageService", "getSettledList", dto);

		this.getRequest().setAttribute("size", pagination.getList().size());
		this.getRequest().setAttribute("pagination", pagination);
		
		List<Object[]> objList=(List<Object[]>)pagination.getList();
		if(objList!=null&&objList.size()>0)
		{
			for(int i=0;i<objList.size();i++)
			{
				Object[] obj=objList.get(i);
				if(obj[0]==null||obj[0].equals(""))
				{
					obj[0]="null";
				}
				else
				{
					obj[0]=obj[0].toString().substring(0, obj[0].toString().length()-2);
				}
			}
		}
		this.getRequest().setAttribute("objList",objList);
//		this.getRequest().setAttribute("couponCode", dto.getAsString("couponCode"));
		
		return "settleLog";
	}
	
	/**
	 * @deprecated 一级推广商提交结算申请
	 * @return JSP页面
	 * @throws Exception
	 * @author zhengxue 
	 * @since 1.0  2012/04/29
	 */
	@SuppressWarnings("unchecked")
	public String payRequest() throws Exception 
	{
		dto.put("id",this.getUid()); 
		super.doService("RankPromoterManageService", "payRequest", dto);
		return "payRequest";
	}
  
  public void setUname(String uname) 
  {
	this.uname = uname;
  }
 
  public String getUname()
  {
	return uname;
  }
 
  public String getPwd()
  {
	return pwd;
  }
 
  public void setPwd(String pwd) 
  {
	this.pwd = pwd;
  }

public String getCouponCode() {
	return couponCode;
}

public void setCouponCode(String couponCode) {
	this.couponCode = couponCode;
}
}
