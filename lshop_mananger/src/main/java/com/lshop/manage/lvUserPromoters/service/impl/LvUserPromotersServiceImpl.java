/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */
package com.lshop.manage.lvUserPromoters.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.datastructure.page.SimplePage;
import com.gv.core.exception.ActionException;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.service.impl.BaseServiceImpl;
import com.gv.core.util.ObjectUtils;
import com.gv.core.util.cryption.MD5;
import com.lshop.common.pojo.logic.LvAdvertiseFund;
import com.lshop.common.pojo.logic.LvCoupon;
import com.lshop.common.pojo.logic.LvEmailTpl;
import com.lshop.common.pojo.logic.LvExtendBalance;
import com.lshop.common.pojo.logic.LvExtendBalanceDetails;
import com.lshop.common.pojo.logic.LvOrder;
import com.lshop.common.pojo.logic.LvOrderDetails;
import com.lshop.common.pojo.logic.LvPromoterRelate;
import com.lshop.common.pojo.logic.LvRankfirstInfo;
import com.lshop.common.pojo.logic.LvRankfirstLogin;
import com.lshop.common.pojo.logic.LvSettlementLog;
import com.lshop.common.pojo.logic.LvUser;
import com.lshop.common.pojo.logic.LvUserCoupon;
import com.lshop.common.pojo.logic.LvUserPromoters;
import com.lshop.common.util.CodeUtils;
import com.lshop.common.util.DateUtils;
import com.lshop.emailsend.service.EmailSendService;
import com.lshop.manage.coupon.server.CouponManageServer;
import com.lshop.manage.lvExtendBalance.service.LvExtendBalanceDetalsService;
import com.lshop.manage.lvExtendBalance.service.LvExtendBalanceService;
import com.lshop.manage.lvUserPromoters.service.LvUserPromotersService;

/**
 * @author 好视网络-软件二部：唐迪
 * @version 1.0
 * @since 1.0
 */
@SuppressWarnings({ "unused", "unchecked" })
@Service("LvUserPromotersService")
public class LvUserPromotersServiceImpl extends BaseServiceImpl implements LvUserPromotersService {  

	@Resource private HibernateBaseDAO dao;
	@Resource private EmailSendService emailSendServiceImpl;
	@Resource private CouponManageServer couponManageServerImpl;
	@Resource 
	private LvExtendBalanceService lvExtendBalanceService;
	@Resource 
	private LvExtendBalanceDetalsService lvExtendBalanceDetalsService;
	public List<LvUserPromoters> findAll(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 已审核的推广商信息列表：增加新一列“所属父级”
	 * @return Pagination
	 * @throws ServiceException
	 * @author zhengxue  updateTime:2012/03/31
	 */
	public Pagination findPage(Dto dto) throws ServiceException {
		SimplePage page = (SimplePage)dto.get("page");
		 LvUserPromoters lvUserPromoters = (LvUserPromoters)dto.get("model");
		 String couponTmp=(String) dto.get("couponCode");
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
        String sql = "from LvUserPromoters where 1=1";
        String realName=lvUserPromoters.getRealName();
        String email=lvUserPromoters.getEmail();
        Short approvalStatus=lvUserPromoters.getApprovalStatus();
        Integer userType=lvUserPromoters.getUserType();
        String parentName=dto.getAsString("parentName");
        
        if(approvalStatus!=null)
        {
        	if(approvalStatus==1)
        	{
        		sql+=" and uid in (select id from LvUser)";
        	}
        	sql+=" and approvalStatus='"+approvalStatus+"'";
        }
        
        if(realName!=null&&realName.length()>0)
        {
        	sql+=" and realName='"+realName+"'";
        }
        if(email!=null&&email.length()>0)
        {
        	sql+=" and email='"+email+"'";
        }
        if(userType!=null){
        	sql+=" and userType='"+userType+"'";
        }
        if(!("").equals(parentName)&& approvalStatus==1)
        {
        	sql+=" and uid in (select pr.uidSecond from LvRankfirstInfo rf,LvPromoterRelate pr where rf.uid=pr.uidFirst and rf.realName like '%"+parentName+"%')";
        }
        if (ObjectUtils.isNotEmpty(couponTmp)) {
			sql+=" and uid in(select uid from LvUserCoupon where couponCode='"+couponTmp+"')";
		}
        sql+=" order by id desc";
		Finder finder = Finder.create(sql.toString());
		Pagination pag = dao.getMapListByHql(sql.toString(), page.getPageNum(), page.getNumPerPage(),null);
		
		List<HashMap> list=(List<HashMap>)pag.getList();
		if(list!=null&&list.size()>0)
		{
			StringBuffer str=new StringBuffer();
			for(HashMap map :list)
			{
			  Integer uid=(Integer)map.get("uid");
			  if(uid!=null)
			  {
				  str.append(uid).append(',');
				  LvUserCoupon userCoupon=(LvUserCoupon)dao.findUniqueByProperty(LvUserCoupon.class, "uid", uid);
				  
				  if(userCoupon!=null)
				    map.put("couponCode",userCoupon.getCouponCode());
				  else
				  {
					  map.put("couponCode","");
				  }
				  
				  List myList=dao.find("select rf.realName from LvRankfirstInfo rf,LvPromoterRelate pr where pr.uidSecond='"+uid+"' and rf.uid=pr.uidFirst", null);
				  if(myList.size()>0)
				  {
					  map.put("parentName", myList.get(0));
				  }
				  else
				  {
					  map.put("parentName", "");
				  }
			  }
			  else 
			  {
				  map.put("couponCode","");
			  }
			}
			if(str.toString().length()>0)
			{
				str.deleteCharAt(str.length()-1);
				List uList=dao.find("from LvUser where id in("+str.toString()+")", null);
				HttpServletRequest request=(HttpServletRequest)dto.get("request");
				request.setAttribute("uList", uList);
			}
		}
		
		return pag;
	}

	public LvUserPromoters get(Dto dto) throws ServiceException {
		 LvUserPromoters lvUserPromoters = (LvUserPromoters)dto.get("model");
		 lvUserPromoters = (LvUserPromoters)dao.load(LvUserPromoters.class, lvUserPromoters.getId());
		return  lvUserPromoters;
	}

	/**
	 * @description 未审核注册信息与审核注册信息模块 删除操作
	 * @version 2.0
	 * @author zhengxue
	 * @updateDate 2012/04/25
	 */
	public void del(Dto dto) throws ServiceException {
	
		//用来判断是未审核注册信息模块（0），还是已审核注册信息模块（1）
		Integer approvalStatus=dto.getAsInteger("approvalStatus");

		LvUserPromoters lvUserPromoters = get(dto);
		if(approvalStatus==0)
		{
			dao.delete(lvUserPromoters);
		}
		
		if(approvalStatus==1)
		{
			LvUser user=(LvUser)dao.findUniqueByProperty(LvUser.class, "id", lvUserPromoters.getUid());
			if(null!=user)
			{
				dao.delete(user);
			}
		}
	}

	public void delList(Dto dto) throws ServiceException {
//		dao.delete(BasePoList)
	}

	public LvUserPromoters save(Dto dto) throws ServiceException {
		 LvUserPromoters lvUserPromoters = (LvUserPromoters)dto.get("model");
//		 LvUserPromoters lvUserPromoters.setCode(SEQUtil.getSEQ(" LvUserPromoters lvUserPromoters".toUpperCase()));
//		 LvUserPromoters lvUserPromoters.setStatus(1);
		dao.save( lvUserPromoters);
		return   lvUserPromoters;
	}

	public LvUserPromoters update(Dto dto) throws ServiceException {
		 LvUserPromoters lvUserPromoters = (LvUserPromoters)dto.get("model");
		dao.update(lvUserPromoters);
		return  lvUserPromoters;
	}

	@Override
	public Integer isCheckedYes(Dto dto) throws ServiceException {//审核通过
		// TODO Auto-generated method stub
		 String hql="";
		 LvUserPromoters lvUserPromoters = (LvUserPromoters)dto.getDefaultPo();
		 //lvUserPromoters=(LvUserPromoters)dao.load(LvUserPromoters.class, lvUserPromoters.getId());
		 LvUser lvUser=(LvUser) dao.findUnique("from LvUser where email='"+lvUserPromoters.getEmail().trim()+"'", null);
		 String newPwd=dto.getAsString("newPwd");
		 if(lvUser==null){
			 lvUser=new LvUser();
			 lvUser.setCreateTime(new Date());
			 lvUser.setEmail(lvUserPromoters.getEmail());
			 lvUser.setLastTime(new Date());
			 lvUser.setNickname(lvUserPromoters.getRealName());
			 lvUser.setUserType((short)1);//推广用户
			//生成六位随机密码
			 lvUser.setPwd(MD5.convert32(newPwd));
			 Integer result=(Integer)dao.save(lvUser);{
				 if(result>0){
				  hql=" update LvUserPromoters set approvalStatus=1 ," +
			          " uid="+result+"," +
			          " userType="+lvUserPromoters.getUserType()+" ," +
			          " specialAmount="+lvUserPromoters.getSpecialAmount()+" " +
			          " where id="+lvUserPromoters.getId();	 
			          dao.update(hql, null);
				  return result;//返回用户id
				 }
			 }
		 }
		 return -1;
	}

	@Override
	public Integer isCheckedNo(Dto dto) throws ServiceException {//审核不通过
		// TODO Auto-generated method stub
		 LvUserPromoters lvUserPromoters = (LvUserPromoters)dto.getDefaultPo();
		 String hql="update LvUserPromoters set approvalStatus=-1, " +
		        " userType="+lvUserPromoters.getUserType()+"," +
	            " specialAmount="+lvUserPromoters.getSpecialAmount()+" " +
		 		" where id="+lvUserPromoters.getId();
		 dao.update(hql, null);
		return null;
	}

	@Override
	public List getEmailTplList(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		String hql="";
		  if(ObjectUtils.isNotEmpty(dto.getAsString("flag"))){//店铺标示
				hql+=" and storeId='"+dto.getAsString("flag")+"'";
			}
		return this.dao.find("FROM LvEmailTpl where tplKey like 'EXTEND%'", null);
		
	}

	public LvUserPromoters getUserPromoter(Dto dto) throws ActionException
	{
		int id=dto.getAsInteger("id");
		return (LvUserPromoters)dao.load(LvUserPromoters.class, id);
	}
	
	public void saveEdit(Dto dto) throws ActionException
	{
		LvUserPromoters userPromoters=(LvUserPromoters)dto.get("userPromoters");
		dao.update(userPromoters);
	}

	/**
	 * 搜索一级推广商信息列表
	 * @return Dto
	 * @throws ServiceException
	 * @author zhengxue  updateTime:2012/03/31
	 */
	public Dto getRankPromoter(Dto dto) throws ServiceException
	{
		SimplePage page=(SimplePage)dto.get("page");
		String nickname=dto.getAsString("nickname");
		String email=dto.getAsString("email");
		
		//搜索一级推广商 邮箱、昵称、已申请数量、申请数量、下级推广台数、累积佣金、已结算佣金、未结算佣金、创建时间、最后登陆时间、备注、登陆用户ID
		String hql="select rf.email,rf.nickname,rf.couponedNum,rf.couponNum,\n" +
				" (ra.settlementedNum+ra.settlementNum+ra.nonSettlementNum),\n" +
				" (ra.settlementedAmount+ra.settlementAmount+ra.nonSettlementAmount),\n" +
				" ra.settlementedAmount,(ra.settlementAmount+ra.nonSettlementAmount),\n" +
				" rf.createTime,rf.lastTime, ra.description,rf.id \n" +
				" from LvRankfirstLogin rf,LvRankfirstInfo ra where rf.email=ra.email\n";
		
		//用昵称检索
		if(null!=nickname && !("").equals(nickname))
		{
			hql+=" and rf.nickname like '%"+nickname+"%'";
		}
		//用邮箱检索
		if(null!=email && !("").equals(email))
		{
			hql+=" and rf.email like '%"+email+"%'";
		}
		Pagination p1=dao.find(Finder.create(hql),page.getPageNum(),page.getNumPerPage());
		
		// 搜索一级推广商个数、总推广台数、佣金累积
		hql=" select count(*),sum(ra.settlementedNum+ra.settlementNum+ra.nonSettlementNum),\n" +
		" sum(ra.settlementedAmount+ra.settlementAmount+ra.nonSettlementAmount)\n" +
		" from LvRankfirstInfo ra where 1=1";
		Pagination p2=dao.find(Finder.create(hql));
		
		
		//搜索二级推广商个数
		hql="select count(*) from LvPromoterRelate where uidFirst in (select uid from LvRankfirstInfo)";
		Long num=(Long)dao.findUnique(hql, null); 

		dto.put("promoterUserNum", num);
		dto.put("p1", p1);
		dto.put("p2", p2);
		return dto;
	}
	
	/**
	 * 一级推广商添加操作
	 * @return Dto
	 * @throws ServiceException
	 * @author zhengxue  updateTime:2012/03/31
	 */
	public Dto add(Dto dto) throws ServiceException
	{
		LvRankfirstLogin rankFirstLogin=(LvRankfirstLogin)dto.get("rankFirstLogin");
		String emailString=rankFirstLogin.getEmail();
		String hql="from LvRankfirstLogin where email='"+emailString+"'";
		List<LvRankfirstLogin> rankFirstLoginList=dao.find(hql, null);
		if(rankFirstLoginList!=null&&rankFirstLoginList.size()>0)
		{
			result.setAppCode("300");
			result.setAppMsg("邮箱已存在,请重新输入!");
			return result;
		}
		
		String passWord=rankFirstLogin.getPwd();
		rankFirstLogin.setPwd(MD5.convert32(passWord));
		rankFirstLogin.setCouponNum(0);
		rankFirstLogin.setCouponedNum(0);
		rankFirstLogin.setCreateTime(new Date());
		dao.save(rankFirstLogin);
		
		
		LvRankfirstInfo rankFirstInfo=(LvRankfirstInfo)dto.get("rankFirstInfo");
		rankFirstInfo.setRealName(rankFirstLogin.getNickname());
		rankFirstInfo.setEmail(rankFirstLogin.getEmail());
		rankFirstInfo.setUserRating("VIP6");
		rankFirstInfo.setUid(rankFirstLogin.getId());
		dao.save(rankFirstInfo);
		
		return dto;
	}
	
	public LvRankfirstLogin getLogin(Dto dto) throws ServiceException
	{
		Integer id=dto.getAsInteger("id");
		LvRankfirstLogin rankFirstLogin=(LvRankfirstLogin)dao.load(LvRankfirstLogin.class, id);
		LvRankfirstInfo rankFirstInfo=(LvRankfirstInfo)dao.findUniqueByProperty(LvRankfirstInfo.class,"uid",rankFirstLogin.getId());
		dto.put("rankFirstInfo", rankFirstInfo);
		return rankFirstLogin;
	}
	
	/**
	 * 一级推广商编辑操作
	 * @return Dto
	 * @throws ServiceException
	 * @author zhengxue  updateTime:2012/03/31
	 */
	public Dto editRankPromoter(Dto dto) throws ServiceException
	{
	 LvRankfirstLogin rankFirstLogin=(LvRankfirstLogin)dto.get("rankFirstLogin");
	 LvRankfirstInfo rankFirstInfo=(LvRankfirstInfo)dao.findUniqueByProperty(LvRankfirstInfo.class,"uid",rankFirstLogin.getId());
	 int id=rankFirstLogin.getId();
	 String emailString=rankFirstLogin.getEmail();
	 
	 //判断当前一级推广商是否已经存在。
	 String hql="from LvRankfirstLogin where id!="+id+" and email='"+emailString+"'";
     List<LvRankfirstLogin> rankFirstLoginList=(List<LvRankfirstLogin>)dao.find(hql,null);
     if(rankFirstLoginList!=null&&rankFirstLoginList.size()>0)
     {
    	 result.setAppCode("300");
		 result.setAppMsg("邮箱已存在,请重新输入!");
		 return result;
     }
     //保存一级推广商登录信息
     dao.update(rankFirstLogin);
     
     //保存一级推广商账户信息
     LvRankfirstInfo rankFirstInfo2=(LvRankfirstInfo)dto.get("rankFirstInfo");
     rankFirstInfo.setEmail(emailString);
     rankFirstInfo.setRealName(rankFirstLogin.getNickname());
     rankFirstInfo.setTel(rankFirstInfo2.getTel());
     rankFirstInfo.setAdress(rankFirstInfo2.getAdress());
     rankFirstInfo.setSpecialAmount(rankFirstInfo2.getSpecialAmount());
     rankFirstInfo.setDescription(rankFirstInfo2.getDescription());
     dao.update(rankFirstInfo);
     return dto;
	}
	
	/**
	 * 一级推广商查看操作
	 * @return AJAX
	 * @throws ActionException
	 * @author zhengxue  
	 * @since 1.0 2012/03/31
	 */
	public LvRankfirstInfo view(Dto dto) throws ServiceException
	{
		Integer id=dto.getAsInteger("id");
		LvRankfirstInfo info=(LvRankfirstInfo)dao.findUniqueByProperty(LvRankfirstInfo.class,"uid",id);
		return info;
	}
	
	/**
	 * 一级推广商审核模块，跳转到审核页面
	 * @return List<LvEmailTpl>
	 * @throws ServiceException
	 * @author zhengxue  updateTime:2012/04/01
	 */	
	public List<LvEmailTpl> getEmailModel(Dto dto) throws ServiceException
	{
		Integer id=dto.getAsInteger("id");
		LvRankfirstLogin rankFirstLogin=(LvRankfirstLogin)dao.load(LvRankfirstLogin.class, id);
		LvRankfirstInfo  rankFirstInfo =(LvRankfirstInfo) dao.findUniqueByProperty(LvRankfirstInfo.class, "uid", rankFirstLogin.getId());
		dto.put("rankFirstLogin", rankFirstLogin);
		dto.put("rankFirstInfo", rankFirstInfo);
		
		String tplKey="COUPON_RANK";
		return (List<LvEmailTpl>)dao.find("from LvEmailTpl where tplKey='"+tplKey+"'", null);
	}
	
	/**
	 *  一级推广商审核模块，保存审核页面数据
	 * @return Dto
	 * @throws ServiceException
	 * @author zhengxue  updateTime:2012/04/01
	 */	
	public Dto sendRankEmail(Dto dto) throws ServiceException
	{
		Integer rankFirstLoginId=dto.getAsInteger("rankFirstLogin.id");
		LvRankfirstLogin rankFirstLogin=(LvRankfirstLogin)dao.load(LvRankfirstLogin.class, rankFirstLoginId);
		dto.put("count",dto.getAsInteger("num"));//dto.put("count", rankFirstLogin.getCouponNum());
		LvUserPromoters up=(LvUserPromoters)dto.get("userPromoters");
		Double specialAmount=0.00;
		if(null!=dto.get("specialAmount") && !("").equals(dto.get("specialAmount")))
		{
			specialAmount=Double.parseDouble(dto.getAsString("specialAmount"));
		}
		
		Map map=couponManageServerImpl.createRankCoupon(dto);
		List<LvCoupon> numList=(List<LvCoupon>)map.get("numList");
		String tableInfo="<table border=\"1\"><tr><td>推广码</td><td>二级推广者账户（同推广码）</td><td>账户初始密码</td></tr>";
		for(int i=0;i<numList.size();i++)
		{
			LvCoupon coupon=numList.get(i);
			tableInfo+="<tr>";
			tableInfo+="<td>";
			tableInfo+=coupon.getCouponCode();
			tableInfo+="</td>";
			tableInfo+="<td>";
			tableInfo+=coupon.getCouponCode();
			tableInfo+="</td>";
			tableInfo+="<td>";
			tableInfo+="888888";
			tableInfo+="</td>";
			tableInfo+="</tr>";
			
			LvUser user=new LvUser();
			user.setEmail(coupon.getCouponCode());
			user.setNickname(coupon.getCouponCode());
			user.setPwd(MD5.convert32("888888"));
			user.setCreateTime(new Date());
			user.setUserType(Short.parseShort("1"));
			dao.save(user);
			
	        LvUserCoupon userCoupon=new LvUserCoupon();		
	        userCoupon.setUid(user.getId());
	        userCoupon.setCouponCode(coupon.getCouponCode());
	        dao.save(userCoupon);
	        
	        LvUserPromoters userPromoters=new LvUserPromoters();
//	        userPromoters.setRealName(coupon.getCouponCode());
	        userPromoters.setRealName("");
	        userPromoters.setUid(user.getId());
	        userPromoters.setEmail("");
	        userPromoters.setApprovalStatus(Short.parseShort("1"));
	        userPromoters.setUserType(up.getUserType());
	        userPromoters.setSpecialAmount(specialAmount);
			dao.save(userPromoters);
			
			LvPromoterRelate promoterRelate=new LvPromoterRelate();
			promoterRelate.setUidFirst(rankFirstLogin.getId());
			promoterRelate.setUidSecond(user.getId());
			dao.save(promoterRelate);
			dao.save(coupon);
		}
		
		tableInfo+="</table>";
		String tplKey="COUPON_RANK";
		LvEmailTpl emailTpl=null;
		String storeFlag="tvpadcn";//推广商无店铺标志，在一个用户多个店铺是无法判断店铺标志
		String hql="FROM LvEmailTpl WHERE tplKey=:key and storeId=:storeId";
		Map param=new HashMap();
		param.put("key", tplKey);
		param.put("storeId", storeFlag);
		List<LvEmailTpl> list=dao.find(hql, param);
		if(list!=null&&!list.isEmpty()){
			emailTpl=list.get(0);
			
		}
		
		dto.put("title", emailTpl.getEnTitle());
		dto.put("userEmail",rankFirstLogin.getEmail());
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		String content=(emailTpl.getEn()+emailTpl.getZh()).replace("{realName}", rankFirstLogin.getNickname()).replace("{sendtime}", format.format(new Date())).replace("{tableInfo}",tableInfo);
		dto.put("content", content);
		emailSendServiceImpl.sendRankEmailNotice(dto);
		
		rankFirstLogin.setCouponedNum(rankFirstLogin.getCouponedNum()+dto.getAsInteger("num"));  //已申请数量累加
		rankFirstLogin.setCouponNum(0); //申请数量清0
		dao.update(rankFirstLogin);
		return dto;
	}
  /**
   * 启用或停用优惠券
   */
	@Override
	public void startOrStop(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		Integer stopFlag=dto.getAsInteger("stopFlag");
		Integer couponId=dto.getAsInteger("couponId");
		LvCoupon lvCoupon=(LvCoupon)dao.load(LvCoupon.class, couponId);
		LvUserCoupon lvUserCoupon=(LvUserCoupon)dao.findUniqueByProperty(LvUserCoupon.class, "couponCode", lvCoupon.getCouponCode());
		LvUser user=(LvUser)dao.load(LvUser.class, lvUserCoupon.getUid());
		
		if(user!=null){
			if(stopFlag==0){//停用
					user.setIsDelete((short)1);
					//停用优惠码
					String hql="update LvCoupon set isActivate=0 where id="+couponId+" and isDel=0";
					dao.update(hql,null);
				}else if(stopFlag==1){//启用
					user.setIsDelete((short)0);
					//启用优惠码
					String hql="update LvCoupon set isActivate=1 where id="+couponId+" and isDel=0";
					dao.update(hql,null);
				}
		}
	}
	  /**
	   * 启用或停用账号
	   */
	@Override
	public void startOrStopAccount(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		Integer flag=dto.getAsInteger("flag");
		Integer id=dto.getAsInteger("uid");
		LvUser user=(LvUser)dao.load(LvUser.class, id);
		if(user!=null){
			if(flag==0){//停用
					user.setIsDelete((short)1);
				}else if(flag==1){//启用
					user.setIsDelete((short)0);
				}
		}
	}

	/**
	 * 推广成绩列表  
	 * @return Pagination
	 * @throws ServiceException
	 * @author zhengxue  updateTime:2012/04/01
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Dto gradePage(Dto dto) throws ServiceException {
		
		 String orderField = dto.getAsString("orderField");
		 String orderDirection = dto.getAsString("orderDirection");
		 SimplePage page = (SimplePage)dto.get("page");
		 LvUserPromoters lvUserPromoters = (LvUserPromoters)dto.get("model");
		 String couponCode=dto.getAsString("couponCode");
		 
		 //赋予初始值
		 dto.put("totalNum", 0);
		 dto.put("totalAmout", 0.00);
		 dto.put("totalOrder",0);
		 
	     StringBuilder sql=new StringBuilder("select t from LvUserPromoters t where  t.approvalStatus=1 ");
	     
	     /****************查询部分（按推广码，真实姓名，邮箱地址，用户类型）start******************/
	     //推广码查询
	     if(null!=couponCode && !("").equals(couponCode))
	     {
	    	 sql=new StringBuilder("select t from LvUserPromoters t,LvUserCoupon uc where  t.approvalStatus=1 and t.uid=uc.uid and uc.couponCode='"+couponCode+"'");
	     }
        
        //按真实姓名查询
    	if(ObjectUtils.isNotEmpty(lvUserPromoters.getRealName())) {
        	sql.append(" and  t.realName like  '%"+lvUserPromoters.getRealName().trim()+"%'");
        }
    	
    	//按邮箱地址查询
    	if(ObjectUtils.isNotEmpty(lvUserPromoters.getEmail())) {
        	sql.append(" and  t.email like  '%"+lvUserPromoters.getEmail().trim()+"%'");
        }
    	
    	//按用户类型查询
    	if(ObjectUtils.isNotEmpty(lvUserPromoters.getUserType())) {
        	sql.append(" and  t.userType="+lvUserPromoters.getUserType());
        	dto.put("userType", lvUserPromoters.getUserType());
        }

        if(ObjectUtils.isNotEmpty(orderField)) {
            sql.append(" order by t." + orderField + " " + orderDirection);
        }
        
  
        /****************查询部分（按推广码，真实姓名，邮箱地址，用户类型）end******************/
        
        
        /****************************业绩列表 start******************************/
		Pagination pag = dao.getMapListByHql(sql.toString(), page.getPageNum(), page.getNumPerPage(),null);
		List<HashMap> list=(List<HashMap>)pag.getList();
		
		if(list!=null&&list.size()>0)
		{
			for(HashMap map :list)
			{
			  Integer uid=(Integer)map.get("uid");
			  /**
			   * 查询具体的推广码
			   */
			  List<LvCoupon> lvCouponList = dao.find("from LvCoupon where couponCode=(select couponCode from LvUserCoupon where  uid ="+uid+")",null);
			  if(lvCouponList!=null&&lvCouponList.size()>0)
			  {
				  LvCoupon coupon=lvCouponList.get(0);
				 if(coupon!=null)
				 {
			       map.put("couponCode", coupon.getCouponCode());
			       map.put("sendTime",coupon.getCreateTime());
			       map.put("validitydate", coupon.getValidityDate());
			       map.put("couponId", coupon.getId());//优惠券的id
			       
			       short couponIsdel=-1;
			       LvUser lvUser=(LvUser)dao.findUniqueByProperty(LvUser.class, "id", uid);
			       if(lvUser!=null){
			    	   couponIsdel=lvUser.getIsDelete();
			       }
			       map.put("couponIsdel",couponIsdel);//优惠券状态
			       
			      // Object[] orderList=( Object[])dao.findUnique("select count(oid),min(overtime) from LvOrder where payStatus=1 and oid in(select oid from LvCouponAid where ccode='"+coupon.getCouponCode()+"')", null);
			       Object[] orderList=( Object[])dao.findUnique("select count(oid),min(overtime) from LvOrder " +
				       		" where payStatus=1 and isdelete=0 and oid in(" +
				       		" select orderId from LvOrderDetails where couponCode='"+coupon.getCouponCode()+"'" +
				       		" )", null);
			       map.put("orderNum", orderList[0]);
			       map.put("overtime", orderList[1]);
				 }
				 else
				 {
					 map.put("couponCode", "");
				     map.put("sendTime","");
				     map.put("validitydate","");
				     map.put("couponId", "");//优惠券的id
				     map.put("couponIsdel", -1);//优惠券状态
				     map.put("orderNum", "");
				     map.put("overtime", "");	
				 }
			  }
			}
		}
		dto.put("pagination", pag);
		 /****************************业绩列表 end******************************/
		
		
		/*********************************获取总推广台数、总收益、总订单数 start**************************************/
		//获取二级推广商所有的uid
		String myHql="select uid from LvUserPromoters where approvalStatus=1";
		List<Integer> uidList=(List<Integer>)dao.find(myHql,null);
		if(uidList.size()<=0)
		 {
			 return dto;
		 }
		String uids=uidList.toString().replaceAll("\\[|\\]", "");
		
		//获取推广台数、推广收益
		String hql="select sum(ra.settlementedNum+ra.settlementNum+ra.nonSettlementNum),sum(ra.settlementedAmount+ra.settlementAmount+ra.nonSettlementAmount) from LvUserPromoters ra where ra.uid in ("+uids+") ";
		Object[] myObj=(Object[])dao.findUnique(hql, null);

		dto.put("totalNum", myObj[0]);
		dto.put("totalAmout", myObj[1]);

		
		/********获取订单数 start******/
		Long totalOrder=0L;
		
		//获取所有的二级推广码
		List<String> codeList=(List<String>)dao.find("select couponCode from LvUserCoupon where uid in ("+uids+")",null);
		if(codeList.size()<=0)
		{
			return dto;
		}
		String codes=codeList.toString().replaceAll("\\[|\\]", "'").replaceAll("\\, ", "','");
		
		//获取所有的订单
		List<String> oidList=(List<String>)dao.find("select orderId from LvOrderDetails where couponCode in ("+codes+")",null);
		if(oidList.size()<=0)
		{
			return dto;
		}
		String oids=oidList.toString().replaceAll("\\[|\\]", "'").replaceAll("\\, ", "','");
		
		totalOrder=(Long)dao.findUnique("select count(oid) from LvOrder where oid in ("+oids+") and payStatus =1", null);

		dto.put("totalOrder",totalOrder);
		/******获取订单数 end*****/
		/*********************************获取总推广台数、总收益、总订单数 end**************************************/
		
		return dto;
	}
	
	/**
	 * @description 一级推广商管理模块，查看业绩操作 
	 * @version 2.0
	 * @autho zhengxuew
	 * @updateDate 2012/04/25
	 */
	public Dto showGrade(Dto dto) throws ServiceException
	{
		 String orderField = dto.getAsString("orderField");
		 String orderDirection = dto.getAsString("orderDirection");
		 SimplePage page = (SimplePage)dto.get("page");
		 LvUserPromoters lvUserPromoters = (LvUserPromoters)dto.get("model");
		 String couponCode=dto.getAsString("couponCode");
		 Integer myUid=dto.getAsInteger("myId");
		 
		 //赋予初始值
		 dto.put("totalNum", 0);
		 dto.put("totalAmout", 0.00);
		 dto.put("totalOrder",0);
	
		 List<Integer> uidList=(List<Integer>)dao.find("select uidSecond from LvPromoterRelate where uidFirst='"+myUid+"'",null);
		 if(uidList.size()<=0)
		 {
			 return dto;
		 }
		 
		 LvRankfirstInfo rankFirstInfo=(LvRankfirstInfo)dao.findUniqueByProperty(LvRankfirstInfo.class, "uid", myUid);
		 
		 String uids=uidList.toString().replaceAll("\\[|\\]", "");
	     StringBuilder sql=new StringBuilder("select t from LvUserPromoters t where uid in ("+uids+") and approvalStatus=1 ");
	    
	    /****************查询部分（按推广码，真实姓名，邮箱地址，用户类型）start******************/
	    //推广码查询
	    if(!("").equals(couponCode))
	    {
	   	 sql=new StringBuilder("select t from LvUserPromoters t  where uid=(select uid from LvUserCoupon where couponCode='"+couponCode+"')");
	    }
	   
	   //按真实姓名查询
		if(ObjectUtils.isNotEmpty(lvUserPromoters.getRealName())) 
		{
			sql.append(" and  realName like  '%"+lvUserPromoters.getRealName().trim()+"%'");
	    }
		
		//按邮箱地址查询
		if(ObjectUtils.isNotEmpty(lvUserPromoters.getEmail())) 
		{
	   	  	sql.append(" and  email like  '%"+lvUserPromoters.getEmail().trim()+"%'");
	    }
		
		//按用户类型查询
		if(ObjectUtils.isNotEmpty(lvUserPromoters.getUserType())) 
		{
			sql.append(" and userType="+lvUserPromoters.getUserType());
			dto.put("userType", lvUserPromoters.getUserType());
	    }
	
	   if(ObjectUtils.isNotEmpty(orderField)) 
	   {
		   sql.append(" order by t." + orderField + " " + orderDirection);
	   } 
	   /****************查询部分（按推广码，真实姓名，邮箱地址，用户类型）end******************/
   
	   
	   /****************************业绩列表 start******************************/
		Pagination pag = dao.getMapListByHql(sql.toString(), page.getPageNum(), page.getNumPerPage(),null);
		List<HashMap> list=(List<HashMap>)pag.getList();
		
		if(list!=null&&list.size()>0)
		{
			for(HashMap map :list)
			{
			  Integer uid=(Integer)map.get("uid");
			  /**
			   * 查询具体的推广码
			   */
			  List<LvCoupon> lvCouponList = dao.find("from LvCoupon where couponCode=(select couponCode from LvUserCoupon where  uid ="+uid+")",null);
			  if(lvCouponList!=null&&lvCouponList.size()>0)
			  {
				  LvCoupon coupon=lvCouponList.get(0);
				 if(coupon!=null)
				 {
			       map.put("couponCode", coupon.getCouponCode());
			       map.put("sendTime",coupon.getCreateTime());
			       map.put("validitydate", coupon.getValidityDate());
			       map.put("couponId", coupon.getId());//优惠券的id

			       short couponIsdel=-1;
			       LvUser lvUser=(LvUser)dao.findUniqueByProperty(LvUser.class, "id", uid);
			       if(lvUser!=null){
			    	   couponIsdel=lvUser.getIsDelete();
			       }
			       map.put("couponIsdel",couponIsdel);//优惠券状态
			       
			       Object[] orderList=( Object[])dao.findUnique("select count(oid),min(overtime) from LvOrder " +
			       		" where payStatus=1 and oid in(" +
			       		" select orderId from LvOrderDetails where couponCode='"+coupon.getCouponCode()+"'" +
			       		" )", null);
			       map.put("orderNum", orderList[0]);
			       map.put("overtime", orderList[1]);
			       Integer totalNum=(Integer)map.get("settlementedNum")+(Integer)map.get("settlementNum")+(Integer)map.get("nonSettlementNum");
			       map.put("totalAmount", (totalNum)*rankFirstInfo.getSpecialAmount());
				 }
				 else
				 {
					 map.put("couponCode", "");
				     map.put("sendTime","");
				     map.put("validitydate","");
				     map.put("couponId", "");//优惠券的id
				     map.put("couponIsdel", -1);//优惠券状态
				     map.put("orderNum", "");
				     map.put("overtime", "");	
				 }
			  }
			}
		}
		dto.put("pagination", pag);
		 /****************************业绩列表 end******************************/
	
		
		/*********************************获取总推广台数、总收益、总订单数 start**************************************/
		String hql="select sum(ra.settlementedNum+ra.settlementNum+ra.nonSettlementNum)," +
				" sum(ra.settlementedAmount+ra.settlementAmount+ra.nonSettlementAmount) " +
				" from LvRankfirstInfo ra where ra.uid='"+myUid+"'";
		Object[] myObj=(Object[])dao.findUnique(hql, null);
	
		dto.put("totalNum", myObj[0]);
		dto.put("totalAmout", myObj[1]);
		
		/********获取订单数 start******/
		Long totalOrder=0L;
	
		//获取所有的二级推广码
		List<String> codeList=(List<String>)dao.find("select couponCode from LvUserCoupon where uid in ("+uids+")",null);
		if(codeList.size()<=0)
		{
			return dto;
		}
		String codes=codeList.toString().replaceAll("\\[|\\]", "'").replaceAll("\\, ", "','");
		
		//获取所有的订单
		List<String> oidList=(List<String>)dao.find("select orderId from LvOrderDetails where couponCode in ("+codes+")",null);
		if(oidList.size()<=0)
		{
			return dto;
		}
		String oids=oidList.toString().replaceAll("\\[|\\]", "'").replaceAll("\\, ", "','");
		
		totalOrder=(Long)dao.findUnique("select count(oid) from LvOrder where oid in ("+oids+") and payStatus =1", null);
	
		dto.put("totalOrder",totalOrder);
		/******获取订单数 end*****/
		/*********************************获取总推广台数、总收益、总订单数 end**************************************/
		
		return dto;
	}
	
	
	
	
	public List export(Dto dto) throws ServiceException
	{
		Integer approvalStatus=dto.getAsInteger("approvalStatus");
		String ids=(String) dto.get("ids");
		List list = new ArrayList();

		if (ids!=null&&!"".equals(ids.trim()))
		{
			if(approvalStatus==1)
			{
			    String[] title = new String[]{"真实姓名","邮箱地址","推广码","所属父级","收款帐户类型","收款帐户","联系电话","地址"};
				list.add(title);
				
		        String sql="select t.id,t.realName,t.email,t.accountTypes,t.accountNumber,t.tel,t.adress,t.approvalStatus,t.uid,t.description,uc.couponCode from LvUserPromoters t,LvUserCoupon uc  where 1=1 and uc.uid=t.uid and t.id in ("+ids+") order by t.id desc";
				List<Object[]> userPromoters=(List<Object[]>)dao.find(sql,null);
				for(int i=0;i<userPromoters.size();i++)
				{
					Object[] object=userPromoters.get(i);
					Object[] objects=new Object[8];

					objects[0]=object[1];
					objects[1]=object[2];
					objects[2]=object[10];
					
					List myList=dao.find("select rf.realName from LvRankfirstInfo rf,LvPromoterRelate pr where pr.uidSecond='"+object[8]+"' and rf.uid=pr.uidFirst", null);
					if(myList.size()>0)
					{
						objects[3]=myList.get(0);
					}
						
					if(object[3]!=null)
					{
						if(object[3].toString().equals("1"))
						{
							objects[4]="PayPal";
						}  
						if(object[3].toString().equals("2"))
						{
							objects[4]="支付宝";
						}	
					}

					objects[5]=object[4];
					objects[6]=object[5];
					objects[7]=object[6];
					list.add(objects);
				}
			}
			else if(approvalStatus==0)
			{
				String[] title = new String[]{"真实姓名","邮箱地址","收款帐户类型","收款帐户","联系电话","地址"};
				list.add(title);
				
				 String sql="select t.id,t.realName,t.email,t.accountTypes,t.accountNumber,t.tel,t.adress,t.approvalStatus,t.uid,t.description from LvUserPromoters t  where 1=1 and t.id in ("+ids+") order by t.id desc";
					List<Object[]> userPromoters=(List<Object[]>)dao.find(sql,null);
					for(int i=0;i<userPromoters.size();i++)
					{
						Object[] object=userPromoters.get(i);
						Object[] objects=new Object[6];
						
						objects[0]=object[1];
						objects[1]=object[2];

						if(object[3]!=null)
						{
							if(object[3].toString().equals("1"))
							{
								objects[2]="PayPal";
							} 
							if(object[3].toString().equals("2"))
							{
								objects[2]="支付宝";
							}
						}
						
						objects[3]=object[4];
						objects[4]=object[5];
						objects[5]=object[6];
						list.add(objects);
					}
			}
		}
		return list;
	}
	
	public List exportExcel(Dto dto) throws ServiceException
	{
		SimplePage page = (SimplePage)dto.get("page");
		String ids=(String) dto.get("ids");
		
		List list = new ArrayList();
		String[] title = new String[]{"推广者","邮箱地址","推广码","发送时间","截止时间","第一次使用时间","产生的订单数","推广台数","已结算的台数","未结算的台数","累计收益"};
		list.add(title);
		if (ids!=null&&!"".equals(ids.trim()))
		{
			    StringBuilder sql = new StringBuilder("");//查询帐户列表信息	
			    sql.append("select t from LvUserPromoters t where  t.approvalStatus=1 and t.id in ("+ids+")");

				Pagination pag = dao.getMapListByHql(sql.toString(),null);
				List<HashMap> listMap=(List<HashMap>)pag.getList();
				if(list!=null&&list.size()>0){
					for(HashMap map :listMap){
					  Integer uid=(Integer)map.get("uid");
					  Object[] objects=new Object[11];
					  LvUserPromoters userPromoters=(LvUserPromoters)dao.findUniqueByProperty(LvUserPromoters.class, "uid", uid);
					  objects[0]=userPromoters.getRealName();
					  objects[1]=userPromoters.getEmail();
					  objects[7]=userPromoters.getSettlementedNum()+userPromoters.getNonSettlementNum()+userPromoters.getSettlementNum();
					  objects[8]=userPromoters.getSettlementedNum();
					  objects[9]=userPromoters.getSettlementNum()+userPromoters.getNonSettlementNum();
					  objects[10]=userPromoters.getSettlementedAmount()+userPromoters.getNonSettlementAmount()+userPromoters.getSettlementAmount();
					  /**
					   * 查询具体的推广码
					   */
					  List<LvCoupon> lvCouponList = dao.find("from LvCoupon where  couponType=1 and couponCode=(select couponCode from LvUserCoupon where  uid ="+uid+")",null);
					  if(lvCouponList!=null&&lvCouponList.size()>0){
						  LvCoupon coupon=lvCouponList.get(0);
						 if(coupon!=null){
					       objects[2]=coupon.getCouponCode();
					       objects[3]=coupon.getCreateTime();
					       objects[4]=coupon.getValidityDate();
					      // Object[] orderList=( Object[])dao.findUnique("select min(overtime),count(oid) from LvOrder where payStatus=1 and oid in(select oid from LvCouponAid where ccode='"+coupon.getCouponCode()+"')", null);
					       Object[] orderList=( Object[])dao.findUnique("select count(oid),min(overtime) from LvOrder " +
						       		" where payStatus=1 and oid in(" +
						       		" select orderId from LvOrderDetails where couponCode='"+coupon.getCouponCode()+"'" +
						       		" )", null);
					       if(null!=orderList[1] && !("").equals(orderList[1]))
					       {
					    	   objects[5]=DateUtils.formatDate((Date)orderList[1], "yyyy-MM-dd HH:mm");
					       }
					       else
					       {
					    	   objects[5]="";
					       }
					       objects[6]=orderList[0].toString();
					      
						 }else {
						       objects[2]="";
						       objects[3]="";
						       objects[4]="";
						       objects[5]="";
						       objects[6]="";
						}
					  }
					  list.add(objects);
					}
				}
		}
		return list;
	}
	
	public Pagination getDetailList(Dto dto) throws ServiceException
	{
		String keyword=dto.getAsString("keyword");
		Pagination pagination=null;
		SimplePage page = (SimplePage)dto.get("page");
		Integer id=dto.getAsInteger("id");
	 	String sql ="select t from LvUserPromoters t where  t.id="+id;//查询帐户列表信息	
	 	List<LvUserPromoters> userPromoters =(List<LvUserPromoters>)dao.find(sql, null);
		if(userPromoters!=null&&userPromoters.size()>0)
		{
			for(LvUserPromoters userPromoter :userPromoters)
			{
			  Integer uid=userPromoter.getUid();
			  List<LvCoupon> lvCouponList = dao.find("from LvCoupon where  couponType=1 and couponCode=(select couponCode from LvUserCoupon where  uid ="+uid+")",null);
			  if(lvCouponList!=null&&lvCouponList.size()>0)
			  {
				  LvCoupon coupon=lvCouponList.get(0);
				 if(coupon!=null){
				   String hql=" select o.oid as oid,o.createTime as createTime,o.overtime as overtime,ca.pnum as pnum," +
				   		      " o.postprice as postprice,o.totalPrice as totalPrice,o.id as id,ca.isPackage as isPackage," +
				   		      " ca.code as orderDetailsCode " +
	          		          " from LvOrderDetails ca,LvOrder o " +
	          		          " where o.payStatus=1 " +
	          		          " and o.oid=ca.orderId " +
	          		          " and ca.couponCode='"+coupon.getCouponCode()+"'" +
	          		          " and o.isdelete=0"	; 
				   if(keyword!=null&&keyword.length()>0){
				      hql+=" and o.oid='"+keyword+"'";
				   }
				
				    //针对未套餐的统计优惠订单台数
				    pagination= dao.getMapListByHql(hql.toString(), page.getPageNum(), page.getNumPerPage(), null);
					List list=pagination.getList();
					List listTmp=new ArrayList();
					for (int i = 0; i < list.size(); i++) {
						Map map=(Map) list.get(i);
						Integer isPackage=(Integer) map.get("isPackage");
						String orderId=(String) map.get("oid");
						String orderDetailsCode=(String) map.get("orderDetailsCode");
						if(ObjectUtils.isNotEmpty(isPackage)&&isPackage!=null&&isPackage==1){
							//套餐优惠码统计笔数
							hql="select sum(ld.pnum) as num" +
								" from LvOrderPackageDetails ld where ld.couponCode ='"+coupon.getCouponCode()+"'" +
								" and ld.orderDetailsCode ='"+orderDetailsCode+"'";
							Long tmpNum=(Long) dao.findUnique(hql, null);
							map.put("MealPnum", tmpNum);
						}else{
							map.put("MealPnum", 0);
						}
						listTmp.add(map);
					}
					pagination.setList(listTmp);
				   
				   
				 }
			  }
			}
		}
		return pagination;
	}
	
	public List exportOrder(Dto dto) throws ServiceException
	{
		String ids=(String) dto.get("ids");
		List list = new ArrayList();
		String[] title = new String[]{"订单号","下单时间","支付成功时间","购买产品数量","邮寄费用","订单总金额"};
		list.add(title);
		Integer id=dto.getAsInteger("id");
	 	String sql ="select t from LvUserPromoters t where  t.id="+id;//查询帐户列表信息	
	 	List<LvUserPromoters> userPromoters =(List<LvUserPromoters>)dao.find(sql, null);
		if(userPromoters!=null&&userPromoters.size()>0)
		{
			for(LvUserPromoters userPromoter :userPromoters)
			{
			  Integer uid=userPromoter.getUid();
			  List<LvCoupon> lvCouponList = dao.find("from LvCoupon where  couponType=1 and couponCode=(select couponCode from LvUserCoupon where  uid ="+uid+")",null);
			  if(lvCouponList!=null&&lvCouponList.size()>0)
			  {
				  LvCoupon coupon=lvCouponList.get(0);
				  List<Object[]> objList=dao.find("select o.oid,o.createtime,o.overtime,o.pnum,o.postprice,o.totalPrice from LvOrder o,LvCouponAid ca where o.payStatus=1 and o.oid=ca.oid and o.id in ("+ids+")",null);
				  for(int i=0;i<objList.size();i++)
				  {
				    	Object[] obj=objList.get(i);
						Object[] objects=new Object[6];
						objects[0]=obj[0];
						objects[1]=obj[1];
						objects[2]=obj[2];
						objects[3]=obj[3];
						objects[4]=obj[4];
						objects[5]=obj[5];
						list.add(objects);
				  }
			  }
			}
		}
		return list;
	}
	
   /**
    * 一级推广商管理模块导出
    * @return JSP页面
    * @throws ServiceException
    * @author zhengxue
    * @since 1.0  2012/03/31
    */
	public List exportInfos(Dto dto) throws ServiceException
	{
		String ids=(String) dto.get("ids");
		List list = new ArrayList();
		String[] title = new String[]{"邮箱","昵称","已申请数量","申请数量","下级推广台数","佣金累积","已结算佣金","未结算佣金","创建时间","最后登陆时间","备注"};
		list.add(title);

		if (ids!=null&&!"".equals(ids.trim()))
		{
			String hql="select rf.email,rf.nickname,rf.couponedNum,rf.couponNum,(ra.settlementedNum+ra.settlementNum),(ra.settlementedAmount+ra.settlementAmount+ra.nonSettlementAmount),ra.settlementedAmount,(ra.settlementAmount+ra.nonSettlementAmount),rf.createTime,rf.lastTime, ra.description from LvRankfirstLogin rf,LvRankfirstInfo ra where rf.email=ra.email and rf.id in ("+ids+")";
			List<Object[]> myList=(List<Object[]>)dao.find(hql,null);
			for(int i=0;i<myList.size();i++)
			{
				Object[] obj=myList.get(i);
				list.add(obj);
			}
		}
		return list;
	}
	
	   /**
	    * 一级推广商列表
	    * @return Pagination
	    * @throws ServiceException
	    * @author zhengxue
	    * @since 1.0  2012/04/06
	    */
		public Pagination getLvRankFirstInfoList(Dto dto) throws ServiceException
		{
			SimplePage page = (SimplePage)dto.get("page");
			String realName=dto.getAsString("realName");
			String email=dto.getAsString("email");
			
			StringBuffer hql=new StringBuffer("from LvRankfirstInfo where settlementStatus !=0");
			if(null!=realName && !("").equals(realName))
			{
				hql.append(" and realName like '%"+realName+"%'");
			}
			if(null!=email && !("").equals(email))
			{
				hql.append(" and email like '%"+email+"%'");
			}
			Pagination pagination=dao.find(Finder.create(hql.toString()),page.getPageNum(),page.getNumPerPage());
			List list=pagination.getList();
			List listTmp=new ArrayList();
			for (int i = 0; i < list.size(); i++) {
				LvRankfirstInfo lvRankfirstInfo=(LvRankfirstInfo) list.get(i);
				//判断当前用户是否存在未结算的清单
				dto.put("uid",lvRankfirstInfo.getUid());
				Boolean isFlag=lvExtendBalanceService.isExistBalance(dto);
				lvRankfirstInfo.setIsFlag(isFlag);
				listTmp.add(lvRankfirstInfo);
			}
			pagination.setList(listTmp);
			return pagination;
		}
		
	   /**
	    * 一级推广商结算操作
	    * @throws ServiceException
	    * @author zhengxue
	    * @since 1.0  2012/04/10
	    */
		@SuppressWarnings("unchecked")
		public Boolean pay(Dto dto) throws ServiceException  //结算
		{
			BaseUsers user =(BaseUsers)dto.get("USER");
			Integer uid=dto.getAsInteger("uid");
			//
			dto.put("USER", user);
			dto.put("uid", uid);
            dto.put("spreaderType",1);	
			return lvExtendBalanceService.saveStatement(dto);
			
			/*
			Integer num=rankFirstInfo.getSettlementNum();
			Double amount=rankFirstInfo.getSettlementAmount();
			
			
			//修改一级推广商的已清算和可清算数据，以及清算状态
			int settNum=0; 
			settNum=rankFirstInfo.getSettlementedNum()+rankFirstInfo.getSettlementNum(); 
			rankFirstInfo.setSettlementedNum(settNum); //将已结算台数累加
			rankFirstInfo.setSettlementNum(0); //将可结算台数清零

			double settAmount=0.00;
			settAmount=Math.round(rankFirstInfo.getSettlementedAmount()+rankFirstInfo.getSettlementAmount()+0.005);
			rankFirstInfo.setSettlementedAmount(settAmount); //将已结算金额累加
			rankFirstInfo.setSettlementAmount(0.00); //将可结算金额清零
			
			rankFirstInfo.setSettlementStatus(Short.parseShort("0")); //将结算状态改为初始状态（未提交申请）
			
			dao.update(rankFirstInfo);
			
			String curTime=""; //当前时间
			String curSetTime=""; //当前可结算时间，即当前时间-15
			String clearTime=""; //上次结算时间
			String clearSetTime=""; //上次不可结算时间，即（上次结算时间-15）
			
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			
			
			//当前时间
			Calendar cal= Calendar.getInstance();
			curTime=format.format(cal.getTime());
			
			//当前可结算时间，即当前时间-15
			cal.add(Calendar.DATE, -15);
			curSetTime=format.format(cal.getTime());
			
			//上次结算时间
			Map param = new HashMap();
			param.put("uid", rankFirstInfo.getUid());
			Timestamp lastDate = (Timestamp)dao.findUnique("select max(clearingTime) from LvSettlementLog where uid=:uid and userType=1", param);
			
			if(lastDate!=null)
			{
				clearTime = format.format(lastDate);
				
				//上次不可结算时间，即（上次结算时间-15）
				Date d=new Date();
				try {
					d = format.parse(clearTime);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				
				cal.setTime(d);
				cal.add(Calendar.DATE, -15);
				clearSetTime=format.format(cal.getTime());
			}
		
			String adis="";
			//获取该一级推广商所对应的所有二级推广商uid
			List<Integer> uidList=(List<Integer>)dao.find("select uidSecond from LvPromoterRelate where uidFirst='"+rankFirstInfo.getUid()+"'",null);
			 if(uidList.size()>0)
			 {
				 String uids=uidList.toString().replaceAll("\\[|\\]", "");
				 
				 //获取所有的二级推广码
				 List<String> codeList=(List<String>)dao.find("select couponCode from LvUserCoupon where uid in ("+uids+")",null);
				 if(codeList.size()>0)
				 {
					 String codes=codeList.toString().replaceAll("\\[|\\]", "'").replaceAll("\\, ", "','");
					 
					 //获取所有的订单
					 List<String> oidList=(List<String>)dao.find("select orderId from LvOrderDetails where couponCode in ("+codes+")",null);
					 if(oidList.size()>0)
					 {
						  String oids=oidList.toString().replaceAll("\\[|\\]", "'").replaceAll("\\, ", "','");
						  
						  //获取符合条件的订单
						  String hql="select oid from LvOrder where oid in ("+oids+") and overtime<='"+curSetTime+"' and payStatus =1";
						  if(!("").equals(clearSetTime))
						  {
							  hql+=" and overtime>'"+clearSetTime+"'";
						  }
						  List<String> myOidList=(List<String>)dao.find(hql,null);
						  if(myOidList.size()>0)
						  {
							  String myOids=myOidList.toString().replaceAll("\\[|\\]", "'").replaceAll("\\, ", "','");
							  
							 //获取符合条件的LvCouponAid表的id
							  List<Integer> couponAidList=(List<Integer>)dao.find("select id from LvOrderDetails where orderId in ("+myOids+")", null);
							  adis=couponAidList.toString().replaceAll("\\[|\\]", "");
						  }
					 }
				 }
				
			 }
			 
			
			
			//将结算记录保存到数据库中
			LvSettlementLog settlementLog=new LvSettlementLog();
			settlementLog.setAids(adis);
			settlementLog.setClearingTime(new Timestamp(System.currentTimeMillis()));		
			settlementLog.setSettlementPeople(user.getUserName());
			settlementLog.setSettlementedAmount(amount);
			settlementLog.setSettlementedNum(num);
			settlementLog.setUid(uid);
			Short userType =1;
			settlementLog.setUserType(userType);
			dao.save(settlementLog);
			*/
	
		}
		
		
		/**
		 * 获取清算记录列表
		 * @param dto
		 * @return Pagination
		 * @throws ServiceException
		 * @author zhengxue
	     * @since 1.0  2012/04/10
		 */
		public Pagination getLogList(Dto dto) throws ServiceException
		{
			  String realName=dto.getAsString("realName");
			  String email=dto.getAsString("email");
			  SimplePage page=(SimplePage)dto.get("page");
			  
			  //查询结算处理时间，真实姓名，邮箱，联系电话，账户类型，账户，结算订单（取数量），结算产品数，结算金额，被结算人uid，操作id，结算订单
			  StringBuffer hql= new StringBuffer("" +
			  		" select sl.clearingTime,up.realName,up.email,up.tel,up.accountTypes,up.accountNumber,sl.aids," +
			  		" sl.settlementedNum,sl.settlementedAmount,sl.uid,sl.id,sl.aids " +
			  		" from LvSettlementLog sl,LvRankfirstInfo up where up.uid=sl.uid and sl.userType=1");
			  if(null!=realName && !("").equals(realName))
			  {
				  hql.append(" and up.realName like '%"+realName+"%'");
			  }
			  if(null!=email && !("").equals(email))
			  {
				  hql.append("  and up.email like '%"+email+"%'");
			  }
			  hql.append(" order by sl.clearingTime desc");
			  
			Pagination pagination=(Pagination)dao.find(Finder.create(hql.toString()), page.getPageNum(), page.getNumPerPage());
			return pagination;
		 }
		
		/**
		 * 一级推广商管理结算记录导出
		 * @param dto
		 * @return List
		 * @throws ServiceException
		 * @author zhengxue
	     * @since 1.0  2012/04/10
		 */
		public List exportRecords(Dto dto) throws ServiceException
		{
			String ids=(String) dto.get("ids");
			List list = new ArrayList();
			String[] title = new String[]{"结算处理时间","真实姓名","邮箱","联系电话","账户类型","账户","结算订单数","结算产品数","结算金额"};
			list.add(title);

			if (ids!=null&&!"".equals(ids.trim()))
			{
				String hql="select sl.clearingTime,up.realName,up.email,up.tel,up.accountTypes," +
						" up.accountNumber,sl.aids,sl.settlementedNum,sl.settlementedAmount " +
						" from LvSettlementLog sl,LvRankfirstInfo up " +
						" where up.uid=sl.uid and sl.userType=1 and sl.id in ("+ids+")";
				List<Object[]> myList=(List<Object[]>)dao.find(hql,null);
				SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
				for(int i=0;i<myList.size();i++)
				{
					Object[] obj=myList.get(i);
					obj[0]=dateFormat.format(obj[0]);
					
					String aids=obj[6].toString();
					int size=0;
				    if(!("").equals(aids)){
					    String[] aid=aids.split(",");
					    size=aid.length;
				    }
				    obj[6]=size;
					list.add(obj);
				}
			}
			return list;
		}
		
		
		/**
		 * 一级推广商结算记录中查看订单详情
		 * @param dto
		 * @return Pagination
		 * @throws ServiceException
		 * @author zhengxue
	     * @since 1.0  2012/04/11
		 */
		public Pagination getOrderList(Dto dto) throws ServiceException
		{
			SimplePage page=(SimplePage)dto.get("page");
			String aids=dto.getAsString("aids");
			String oid=dto.getAsString("oid");
			String ccode=dto.getAsString("ccode");
			
			//如果没有订单，则直接返回
			if(null==aids || ("").equals(aids))
			{
				return this.page;
			}
			
			//查询订单号，下单时间，支付成功时间，购买产品数量，邮寄费用，订单总额，优惠码，订单ID
			String hql="select o.oid as oid,o.createTime as createTime,o.overtime as overtime," +
					" ca.pnum as pnum,o.postprice as postprice,o.totalPrice as totalPrice," +
					" ca.couponCode as couponCode,o.id as id,ca.isPackage as isPackage " +
					" from LvOrder o,LvOrderDetails ca " +
					" where o.oid=ca.orderId " +
					" and ca.orderId in ("+aids+")" +
					" and (ca.couponCode is not null or ca.couponCode!='')" +
					" and o.payStatus=1" +
					" and ca.couponCode<>''";
			
			if(null!=oid && !("").equals(oid))
			{
				hql+=" and o.oid like '%"+oid+"%'";
			}
			if(null!=ccode && !("").equals(ccode))
			{
				hql+=" and ca.ccode like '%"+ccode+"%'";
			}
			
			//Pagination pagination=(Pagination)dao.find(Finder.create(hql),page.getPageNum(),page.getNumPerPage());
			//针对未套餐的统计优惠订单台数
			Pagination pagination= dao.getMapListByHql(hql.toString(), page.getPageNum(), page.getNumPerPage(), null);
			List list=pagination.getList();
			List listTmp=new ArrayList();
			for (int i = 0; i < list.size(); i++) {
				Map map=(Map) list.get(i);
				Integer isPackage=(Integer) map.get("isPackage");
				String couponCode=(String) map.get("couponCode");
				String orderId=(String) map.get("oid");
				if(ObjectUtils.isNotEmpty(isPackage)&&isPackage!=null&&isPackage==1){
					//套餐优惠码统计笔数
					hql="select sum(ld.pnum) as num" +
						" from LvOrderPackageDetails ld where ld.couponCode ='"+couponCode+"'" +
						" and ld.orderDetailsCode in( select lod.code from LvOrderDetails lod where lod.isPackage=1  " +
						" and lod.orderId='"+orderId+"')";
					Long tmpNum=(Long) dao.findUnique(hql, null);
					map.put("MealPnum", tmpNum);
				}else{
					map.put("MealPnum", 0);
				}
				listTmp.add(map);
			}
			pagination.setList(listTmp);
			
			return pagination;
		}
		
		/**
		 * 一级推广商结算记录中订单详情数据导出
		 * @param dto
		 * @return List
		 * @throws ServiceException
		 * @author zhengxue
	     * @since 1.0  2012/04/11
		 */
		public List exportOrders(Dto dto) throws ServiceException
		{
			String ids=(String) dto.get("ids");
			String couponCode=(String) dto.get("couponCode");
			List list = new ArrayList();
			String[] title = new String[]{"订单号","下单时间","支付成功时间","套餐数目","购买产品数量","邮寄费用","订单总额","优惠码"};
			list.add(title);

			//查询订单号，下单时间，支付成功时间，购买产品数量，邮寄费用，订单总额，优惠码，订单ID
			String hql="select o.oid as oid,o.createTime as createTime,o.overtime as overtime," +
					" ca.pnum as pnum,o.postprice as postprice,o.totalPrice as totalPrice," +
					" ca.couponCode as couponCode,o.id as id,ca.isPackage as isPackage " +
					" from LvOrder o,LvOrderDetails ca " +
					" where o.oid=ca.orderId " +
					" and ca.orderId in ("+ids+")" +
					" and (ca.couponCode is not null or ca.couponCode!='')" +
					" and o.payStatus=1" +
					" and ca.couponCode<>''";
			
			//Pagination pagination=(Pagination)dao.find(Finder.create(hql),page.getPageNum(),page.getNumPerPage());
			//针对未套餐的统计优惠订单台数
			Pagination pagination= dao.getMapListByHql(hql.toString(), null);
			List listTmp=pagination.getList();
			for (int i = 0; i < listTmp.size(); i++) {
				Map map=(Map) listTmp.get(i);
				Integer isPackage=(Integer) map.get("isPackage");
				String [] tempArray = new String[22];
				tempArray[0] = String.valueOf(map.get("oid"));
				tempArray[1] = DateUtils.formatDate((Date)map.get("createTime"), "yyyy-MM-dd HH:mm:ss");
				tempArray[2] = DateUtils.formatDate((Date)map.get("overtime"), "yyyy-MM-dd HH:mm:ss");
				if(ObjectUtils.isNotEmpty(isPackage)&&isPackage==1){
					//套餐优惠码统计笔数
					hql="select sum(ld.pnum) as num" +
						" from LvOrderPackageDetails ld where ld.couponCode ='"+map.get("couponCode")+"'" +
						" and ld.orderDetailsCode in( select lod.code from LvOrderDetails lod where lod.isPackage=1  " +
						" and lod.orderId='"+String.valueOf(map.get("oid"))+"')";
					Long tmpNum=(Long) dao.findUnique(hql, null);
					tempArray[3] =String.valueOf(map.get("pnum"));
					tempArray[4] =tmpNum.toString(); 
				}else{
					tempArray[3] ="";
					tempArray[4] =String.valueOf(map.get("pnum"));
				}
				tempArray[5] = String.valueOf(map.get("postprice"));
				tempArray[6] = String.valueOf(map.get("totalPrice"));
				tempArray[7] = String.valueOf(map.get("couponCode"));
				list.add(tempArray);
			}			
			return list;
		}

		@Override
		public List exportOrderGrade(Dto dto) throws ServiceException {
			String ids=(String) dto.get("ids");
			List list = new ArrayList();
			String[] title = new String[]{"订单号","下单时间","支付成功时间","购买套餐数量","购买产品数量","邮寄费用","订单总金额"};
			list.add(title);
			Integer id=dto.getAsInteger("id");
	            if(ObjectUtils.isNotEmpty(ids)){
	            	String hql=" select o.oid as oid,o.createTime as createTime,o.overtime as overtime,ca.pnum as pnum,ca.couponCode as couponCode," +
	 		          " o.postprice as postprice,o.totalPrice as totalPrice,o.id as id,ca.isPackage as isPackage,ca.code as orderDetailsCode " +
			          " from LvOrderDetails ca,LvOrder o " +
			          " where o.payStatus=1 " +
			          " and o.oid=ca.orderId " +
			          " and o.oid in("+ids+")";
				if(keyword!=null&&keyword.length()>0){
				      hql+=" and o.oid='"+keyword+"'";
				}
	
				//针对未套餐的统计优惠订单台数
				Pagination  pagination= dao.getMapListByHql(hql.toString(), null);
			    List listTmp=pagination.getList();
			    for (int i = 0; i < listTmp.size(); i++) {
					Map map=(Map) listTmp.get(i);
					Integer isPackage=(Integer) map.get("isPackage");
					String couponCode=(String) map.get("couponCode");
					String orderId=(String) map.get("orderId");
					String orderDetailsCode=(String) map.get("orderDetailsCode");
					Long tmpNum=0l;
					if(isPackage==1){
					 //套餐优惠码统计笔数
						
					 hql="select sum(ld.pnum) as num" +
						 " from LvOrderPackageDetails ld where ld.couponCode ='"+couponCode+"'" +
						 " and ld.orderDetailsCode ='"+orderDetailsCode+"'";
						 tmpNum=(Long) dao.findUnique(hql, null);
						
					 }
					
					Object[] objects=new Object[7];
					objects[0]=String.valueOf(map.get("oid"));
					objects[1]=String.valueOf(map.get("createTime"));
					objects[2]=String.valueOf(map.get("overtime"));
					if (isPackage==1) {
						objects[3]=String.valueOf(map.get("pnum"));
						objects[4]=String.valueOf(tmpNum);
					}else if(isPackage!=1){
						objects[3]=String.valueOf(tmpNum);
						objects[4]=String.valueOf(map.get("pnum"));
					}
					
					objects[5]=String.valueOf(map.get("postprice"));
					objects[6]=String.valueOf(map.get("totalPrice"));
					list.add(objects);
					
				}
			    
            }
			
			return list;
		}

		/**
		 * 
		 * @Method: isVersionFailure 
		 * @Description:  [判断最近一次修改是否是有效的版本控制，即是数据否失效]  
		 * @Author:       [liaoxiongjian]     
		 * @CreateDate:   [2012-12-13 下午02:32:20]   
		 * @UpdateUser:   [liaoxiongjian]     
		 * @UpdateDate:   [2012-12-13 下午02:32:20]   
		 * @UpdateRemark: [说明本次修改内容]  
		 * @throws
		 */
		@Override
		public Boolean isVersionFailure(Dto dto) throws ServiceException {
			//判断最近一次修改是否是有效的版本控制，即是数据否失效。
			Date versionTime=(Date) dto.get("versionTime");
			Integer uid=dto.getAsInteger("uid");
			String hql="from LvRankfirstInfo where uid=:uid";
			Map param=new HashMap();
			param.put("uid", uid);
			LvRankfirstInfo lvRankfirstInfo=(LvRankfirstInfo) dao.findUnique(hql, param);
			if(lvRankfirstInfo==null){//已经删除
				return false;
			}
			if(ObjectUtils.isEmpty(versionTime)&&ObjectUtils.isNotEmpty(lvRankfirstInfo.getModifyTime())){
				return false;
			}else if(ObjectUtils.isNotEmpty(versionTime)&&ObjectUtils.isNotEmpty(lvRankfirstInfo.getModifyTime())){
				if(lvRankfirstInfo.getModifyTime().getTime()>versionTime.getTime()){
					return false;
				}
			}
			return true;
		}

}
