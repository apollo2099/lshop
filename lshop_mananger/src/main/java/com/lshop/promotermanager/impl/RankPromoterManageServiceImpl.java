package com.lshop.promotermanager.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.datastructure.page.SimplePage;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.StringUtil;
import com.gv.core.util.cryption.MD5;
import com.lshop.common.pojo.logic.LvRankfirstInfo;
import com.lshop.common.pojo.logic.LvRankfirstLogin;
import com.lshop.common.pojo.logic.LvUserLog;
import com.lshop.common.pojo.logic.LvUserPromoters;
import com.lshop.common.util.Constants;
import com.lshop.common.util.DateUtils;
import com.lshop.promotermanager.RankPromoterManageService;

@SuppressWarnings("unchecked")
@Service("RankPromoterManageService")
public class RankPromoterManageServiceImpl implements RankPromoterManageService
{
	@Resource
	private HibernateBaseDAO lvlogicReadDao;
	
	@Resource
	private HibernateBaseDAO lvlogicWriteDao;
	
	private Logger logger=Logger.getLogger(this.getClass());
	
	public LvRankfirstLogin login(Dto dto) throws ServiceException
	{
		String hql = "from LvRankfirstLogin as u where u.email = :email  and u.pwd = :pwd";
		Map param = new HashMap();
		param.put("email", dto.getAsString("uname"));
		param.put("pwd", MD5.convert32(dto.getAsString("pwd").trim()));
		LvRankfirstLogin rankFirstLogin= (LvRankfirstLogin) lvlogicReadDao.findUnique(hql, param);
		if(rankFirstLogin!=null)
		{
			 SimpleDateFormat  format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			 
			 hql="update LvRankfirstLogin set lastTime='"+format.format(new Date())+"' where id="+rankFirstLogin.getId();
			 lvlogicWriteDao.update(hql,null);
			 //保存日志记录
			 LvUserLog userLog = new LvUserLog();
			 userLog.setIp(dto.getAsString("ip"));
			 userLog.setUid(rankFirstLogin.getId());
			 userLog.setCreateTime(new Date());
			 this.lvlogicWriteDao.save(userLog);
		}
		return rankFirstLogin;
	}

	public Dto getPromtCodeList(Dto dto) throws ServiceException
	{
		SimplePage page=(SimplePage)dto.get("page");
		Integer uid=dto.getAsInteger("uid");
		String couponCode=dto.getAsString("couponCode");
		Long allPnum=0l; //总推广台数
		
		//搜索该一级推广商旗下所有的推广码、截止有效期、领取时间、推广台数、佣金累积、二级推广商ID
//		String hql="select uc.couponCode,c.validityDate,c.createTime,(up.settlementedNum+up.settlementNum+up.nonSettlementNum) as totalPnum,((up.settlementedNum+up.settlementNum+up.nonSettlementNum)*rf.specialAmount),up.id  from LvRankfirstInfo rf,LvPromoterRelate pr,LvUserCoupon uc,LvUserPromoters up,LvCoupon c where rf.uid="+uid+" and pr.uidFirst=rf.uid and uc.uid=pr.uidSecond and c.couponCode=uc.couponCode and up.uid=pr.uidSecond";
		String hql="select (up.settlementedNum+up.settlementNum+up.nonSettlementNum) as totalPnum,rf.specialAmount,up.uid  from LvRankfirstInfo rf,LvPromoterRelate pr,LvUserPromoters up where rf.uid="+uid+" and pr.uidFirst=rf.uid and up.uid=pr.uidSecond order by (up.settlementedNum+up.settlementNum+up.nonSettlementNum) desc";
		Pagination p1=lvlogicReadDao.find(Finder.create(hql),page.getPageNum(),page.getNumPerPage());
		
//		//当用户用推广码进行搜索时，则加上推广码的判断条件
//		if(null!=couponCode && !("").equals(couponCode))
//		{
//			hql+=" and uc.couponCode like '%"+couponCode+"%'";
//		}
//		//按照推广台数降序排列
//		hql+=" order by (up.settlementedNum+up.settlementNum+up.nonSettlementNum) desc";
//		Pagination p1=lvlogicReadDao.find(Finder.create(hql),page.getPageNum(),page.getNumPerPage());
		
		//搜索成交订单数,与上面搜索的各个字段放在一起
		List<Object[]> objList=(List<Object[]>)p1.getList(); //上面搜索出的列表数据（不包含成交订单数）
		List<Object[]> myObjList=new ArrayList<Object[]>(); //自定义的数组列表，用来存放该一级推广商旗下所有的推广码、截止有效期、领取时间、成交订单数、推广台数、佣金累积、二级推广商ID
		
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		Date date=new Date();
		String str=format.format(date)+" 00:00:00";
		
		for(int i=0;i<objList.size();i++)
		{
			Object[] obj=objList.get(i);
			Object[] myObj=new Object[7];
			
			String ohql="select couponCode from LvUserCoupon  where uid="+obj[2]+"";
			//当用户用推广码进行搜索时，则加上推广码的判断条件
			if(null!=couponCode && !("").equals(couponCode))
			{
				ohql+=" and couponCode like '%"+couponCode+"%'";
			}
			myObj[0]=lvlogicReadDao.findUnique(ohql, null); //推广码

			myObj[1]=""; //截止有效期
			myObj[2]=""; //领取时间
			myObj[3]=0; //成交订单数
			myObj[4]=0; //推广台数
			myObj[5]=0; //佣金累积
			
			//获取截止有效期和领取时间
			String cHql="select validityDate,createTime from LvCoupon where couponCode='"+myObj[0]+"'";
			List<Object[]> list=(List<Object[]>)lvlogicReadDao.find(cHql, null);
			if(null!=list && list.size()>0){
				Object cObj[]=list.get(0);
				myObj[1]=dateFormat.format(cObj[0]); //截止有效期
				myObj[2]=dateFormat.format(cObj[1]); //领取时间
			}

			
			//获取所有的订单
			String oHql="select DISTINCT orderId from LvOrderDetails where couponCode='"+myObj[0]+"'";
			List<String> oidList=(List<String>)lvlogicReadDao.find(oHql,null);
			if(null!=oidList && oidList.size()>0)
			{	
				String oids=oidList.toString().replaceAll("\\[|\\]", "'").replaceAll("\\, ", "','");
				
				//获取每个二级推广商成交订单数和推广台数（去掉已退货的）
				String tHql="select Count(*),sum(couponNum) from LvOrder where oid in ("+oids+") and payStatus=1 and status!=3 and overtime<='"+str+"' and isdelete=0";
				List<Object[]> tList=(List<Object[]>)lvlogicReadDao.find(tHql, null);
				if(null!=tList && tList.size()>0){
					Object tObj[]=tList.get(0);
					myObj[3]=tObj[0];
					myObj[4]=tObj[1];
				}
				
				//获取二级推广商推广佣金（包括已结算已退货的佣金）
				Long secondNum=0L; //二级推广商推广台数，未退货的
				Long tNum=0L; //二级推广商推广台数，已结算已退货的
				float specialAmount=0f;
				String yHql="select sum(couponNum) from LvOrder where oid in ("+oids+") and payStatus=1 and status!=3 and overtime<='"+str+"' and isdelete=0";
				String sHql="select sum(couponNum) from LvOrder where oid in ("+oids+") and payStatus=1 and status=3 and isBalanceFirst=1 and overtime<='"+str+"' and isdelete=0";
				secondNum=(Long)lvlogicReadDao.findUnique(yHql, null);
				tNum=(Long)lvlogicReadDao.findUnique(sHql, null);
				if(null==secondNum){
					secondNum=0l;
				}
				if(null==tNum){
					tNum=0l;
				}
				if(null!=obj[1] && !("").equals(obj[1].toString())){
					specialAmount=Float.parseFloat(obj[1].toString());
				}
				myObj[5]=(secondNum+tNum)*specialAmount;
			}

			myObj[6]=obj[2]; //二级推广商
			myObjList.add(myObj);
			
			if(null!=myObj[4]){
				allPnum+=Long.parseLong(myObj[4].toString());
			}
		}
		p1.setList(myObjList);
		dto.put("p1", p1);
		
		LvRankfirstLogin rankFirstLogin=(LvRankfirstLogin)lvlogicReadDao.load(LvRankfirstLogin.class, uid);
		dto.put("couponNum", rankFirstLogin.getCouponNum());
		
		LvRankfirstInfo rankFirstInfo=(LvRankfirstInfo)lvlogicReadDao.findUniqueByProperty(LvRankfirstInfo.class, "uid", uid);
		dto.put("settlementStatus", rankFirstInfo.getSettlementStatus()); //存放申请状态，如果已提交申请，则将按钮屏蔽掉
		
		//搜索该一级推广商的总推广台数、可结算佣金、未结算佣金、已结算佣金
		Pagination p2=lvlogicReadDao.find(Finder.create("select (settlementedNum+settlementNum+nonSettlementNum),settlementAmount,nonSettlementAmount,settlementedAmount from LvRankfirstInfo where uid="+uid));
		dto.put("p2", p2);
		
		//搜索该一级推广商拥有的推广码个数
//		Long couponCodeNum=(Long)lvlogicReadDao.findUnique("select count(*) from LvPromoterRelate pr,LvUserCoupon uc,LvUserPromoters up,LvCoupon c where pr.uidFirst="+uid+" and uc.uid=pr.uidSecond and c.couponCode=uc.couponCode and up.uid=pr.uidSecond", null);
		dto.put("couponCodeNum", p1.getTotalCount());
		
		dto.put("allPnum", allPnum);

		return dto;
	}
	
	public LvRankfirstInfo getUserInfo(Dto dto) throws ServiceException
	{
		String hql="from LvRankfirstInfo where uid="+dto.getAsInteger("uid");
		return (LvRankfirstInfo) lvlogicReadDao.find(hql,null).get(0);
	}
	
	public LvRankfirstLogin getUser(Dto dto) throws ServiceException {
		// TODO Auto-generated method stu
		return (LvRankfirstLogin) this.lvlogicReadDao.load(LvRankfirstLogin.class, dto.getAsInteger("uid"));
	}
	
	public void editPwd(Dto dto) throws ServiceException{
		if(null==dto){
			logger.error("缺少必要参数 ！");
			throw new ServiceException("缺少参数,不能执行此操作!");
		}
		Integer uid = dto.getAsInteger("uid");
		String origPawd = dto.getAsString("origPwd");
		String newPawd = dto.getAsString("newPwd");
		if(null==uid || StringUtil.IsNullOrEmpty(origPawd) || StringUtil.IsNullOrEmpty(newPawd)){
			logger.error("缺少必要参数 ！userID["+uid+"] orgiPwd["+origPawd+"] newPwd[+"+newPawd+"]");
			throw new ServiceException("缺少参数,不能执行此操作!");
		}
		//修改新密码
		String hql = "update LvRankfirstLogin  set pwd =:pwd where id = :id";
		Map paraMap = new HashMap();
		paraMap.put("pwd", newPawd);
		paraMap.put("id", uid);
	    lvlogicWriteDao.update(hql, paraMap);
		logger.debug("修改密码成功！");
	}
	
	public void edit(Dto dto) throws ServiceException {

		LvRankfirstInfo userp = (LvRankfirstInfo) dto.getDefaultPo();
		String hql="from LvRankfirstInfo where uid="+dto.getAsInteger("uid");
		LvRankfirstInfo info=(LvRankfirstInfo) lvlogicReadDao.find(hql,null).get(0);
		
//		info.setAccountNumber(userp.getAccountNumber());
//		info.setAccountTypes(userp.getAccountTypes());
//		if(userp.getDescription()!=null&&!userp.getDescription().equals(""))
//			info.setDescription(userp.getDescription());
//		dao.update(info);
		
		String rHql = "update LvRankfirstInfo  set accountNumber =:accountNumber, accountTypes=:accountTypes";
		if(userp.getDescription()!=null&&!userp.getDescription().equals("")){
			rHql+=", description=:description";
		}
		rHql+="  where id = :id";
		
		Map paraMap = new HashMap();
		paraMap.put("accountNumber", userp.getAccountNumber());
		paraMap.put("accountTypes", userp.getAccountTypes());
		paraMap.put("id", info.getId());
		if(userp.getDescription()!=null&&!userp.getDescription().equals("")){
			paraMap.put("description", userp.getDescription());
		}
	    lvlogicWriteDao.update(rHql, paraMap);
	}
	
	public Dto sendRequest(Dto dto) throws ServiceException
	{
		Integer num=dto.getAsInteger("num");
		Integer uid=dto.getAsInteger("uid");
		LvRankfirstLogin rankFirstLogin=(LvRankfirstLogin)lvlogicReadDao.load(LvRankfirstLogin.class, uid);
//		rankFirstLogin.setCouponNum(num);
//		dao.update(rankFirstLogin);
		String hql=" update LvRankfirstLogin set couponNum=:couponNum where id=:id";
		Map paraMap = new HashMap();
		paraMap.put("couponNum", num);
		paraMap.put("id", rankFirstLogin.getId());
	    lvlogicWriteDao.update(hql, paraMap);
		return dto;
	}
	
	public Dto getInfo(Dto dto) throws ServiceException
	{
		SimplePage page=(SimplePage)dto.get("page");
		String couponCode=dto.getAsString("couponCode");
		dto.put("couponCode", couponCode);
		
		String hql1="select up.realName,up.email,up.adress from LvUserPromoters up,LvUserCoupon uc where uc.couponCode='"+couponCode+"' and up.uid=uc.uid";
		Pagination p1=lvlogicReadDao.find(Finder.create(hql1));
		dto.put("p1", p1);
		
		 Date date=new Date();
		 SimpleDateFormat format1=new SimpleDateFormat("yyyy-MM-dd");
		 SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 String str=format1.format(date)+" 00:00:00";
		 
		 //获取买家姓名、成功购买时间、优惠台数、所在地区
		 String	 hql="select oa.relName,o.overtime,o.couponNum ,oa.contryName,o.status from LvOrder as o,LvOrderAddress as oa where oa.orderId=o.oid and o.oid in (select orderId from LvOrderDetails where couponCode='"+couponCode+"') and o.payStatus=1 and o.overtime<='"+str+"' and o.isdelete=0 order by o.overtime desc";// and o.overtime<'"+str+"'
		 Pagination p2=lvlogicReadDao.find(Finder.create(hql),page.getPageNum(),page.getNumPerPage());
		 
		List<Object[]> objList=(List<Object[]>)p2.getList();
		List<Object[]> myObjList=new ArrayList<Object[]>();
		if(objList!=null&&objList.size()>0)
		{
			for(int i=0;i<objList.size();i++)
			{
				Object[] obj=objList.get(i);
				Object[] myObj=new Object[6];
				myObj[0]=obj[0]; //买家姓名
				myObj[1]=couponCode; //推广码
				myObj[2]=(null==obj[1]?null:format.format(obj[1])); //成功使用时间
				myObj[3]=obj[2]; //优惠台数
				myObj[4]=obj[3]; //所在地区
				myObj[5]=obj[4]; //订单状态：3-已退货

				myObjList.add(myObj);
			}
		}
		
		p2.setList(myObjList);
		dto.put("p2", p2);
		
		return dto;
	}
	
	/**
	 * @description 显示可申请（以及未满15天结算条件的）结算佣金的订单详情页面
	 * @version 1.0 
	 * @author zhengxue 
	 * @createDate 2012/04/18
	 */
	public Dto getSettleInfo(Dto dto) throws ServiceException
	{
/**		SimplePage page=(SimplePage)dto.get("page");
		Integer uid=dto.getAsInteger("uid");
		String remark=dto.getAsString("remark"); //"1",可申请结算的佣金    "2",未满足15天条件的佣金
		
		Calendar date= Calendar.getInstance();
		String nowdatestr=DateUtils.formatDate(date.getTime(), "yyyy-MM-dd"); //当前时间
		date.add(Calendar.DATE, -15);
        String settlementDate=DateUtils.formatDate(date.getTime(), "yyyy-MM-dd"); //15天前时间
        
		//该一级推广商上一次的结算时间
		Timestamp lastDate = (Timestamp)lvlogicReadDao.findUnique("select max(clearingTime) from LvSettlementLog where uid='"+uid+"' and userType=1", null);
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String lastSetDate = ""; //上次清算时间
		if(lastDate!=null){
			lastSetDate = format.format(lastDate);
		}
		
		String hql="";
		if("1".equals(remark)){
	        hql="select oa.relname,ca.ccode,o.overtime,o.pnum,oa.contryname from LvOrderAdress oa,LvOrder o,LvCouponAid ca,LvUserCoupon uc,LvPromoterRelate pr where pr.uidFirst='"+uid+"' and uc.uid=pr.uidSecond and ca.ccode=uc.couponCode and o.oid=ca.oid and o.oid=oa.oid and o.payStatus=1 and o.status!=3 and o.overtime<'"+settlementDate+" 00:00:00' and o.overtime>'"+lastSetDate+"'";
		}
		if("2".equals(remark)){
	        hql="select oa.relname,ca.ccode,o.overtime,o.pnum,oa.contryname from LvOrderAdress oa,LvOrder o,LvCouponAid ca,LvUserCoupon uc,LvPromoterRelate pr where pr.uidFirst='"+uid+"' and uc.uid=pr.uidSecond and ca.ccode=uc.couponCode and o.oid=ca.oid and o.oid=oa.oid and o.payStatus=1 and o.status!=3 and o.overtime>'"+settlementDate+" 00:00:00' and o.overtime<'"+nowdatestr+" 00:00:00'  ";
		}

        Pagination pagination=lvlogicReadDao.find(Finder.create(hql),page.getPageNum(),page.getNumPerPage());
		dto.put("pagination", pagination);
*/		
		return dto;
	}
	
	public LvRankfirstLogin getRankUser(Dto dto) throws ServiceException
	{
		String email=dto.getAsString("email");
		String newPwd=dto.getAsString("newPwd");
		LvRankfirstLogin rankFirstLogin=(LvRankfirstLogin)lvlogicReadDao.findUniqueByProperty(LvRankfirstLogin.class, "email", email);
		if(rankFirstLogin!=null)
		{
			String hql="update LvRankfirstLogin set pwd=:pwd where id=:id";
			Map param=new HashMap();
			param.put("pwd", newPwd);
			param.put("id", rankFirstLogin.getId());
		    lvlogicWriteDao.update(hql, param);
		}
		return rankFirstLogin;
	}
	
	/**
	 * @deprecated 一级推广商结算查询模块
	 * @param dto
	 * @return Pagination
	 * @throws ServiceException
	 * @author zhengxue 
	 * @since 1.0  2012/03/29
	 */
	  public Pagination getSettledList(Dto dto) throws ServiceException //结算查询
	  {
		  Integer uid=dto.getAsInteger("uid");
//		  LvUserCoupon userCoupon=(LvUserCoupon)dao.findUniqueByProperty(LvUserCoupon.class,"uid", uid);
//		  dto.put("couponCode", userCoupon.getCouponCode());
		  SimplePage page=(SimplePage)dto.get("page");
		  String hql="select balanceTime,balanceNum,balancePrice from LvExtendBalance where uid="+uid+" and userType=1 and balanceStatus=1 order by balanceTime desc";
		  Pagination pagination=lvlogicReadDao.find(Finder.create(hql),page.getPageNum(),page.getNumPerPage());
		  return pagination;
	  }
	  
		/**
		 * @deprecated 一级推广商提交结算申请
		 * @param dto
		 * @return Dto
		 * @throws ServiceException
		 * @author zhengxue 
		 * @since 1.0  2012/04/09
		 */
	  public Dto payRequest(Dto dto) throws ServiceException
	  {
		   int id=dto.getAsInteger("id");
		   LvRankfirstInfo rankFirstInfo=(LvRankfirstInfo)lvlogicReadDao.findUniqueByProperty(LvRankfirstInfo.class, "uid", id);
		   if(rankFirstInfo.getSettlementStatus()==0)
		   {
		    //当一点推广商点击“提交结算申请”后，直接将结算状态改为“1提交结算申请”
		    lvlogicWriteDao.update("update LvRankfirstInfo set settlementStatus=1 where uid="+id,null);
		   }
		   return dto;
	  }
}
