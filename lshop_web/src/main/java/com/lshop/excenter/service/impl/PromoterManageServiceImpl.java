package com.lshop.excenter.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.datastructure.page.SimplePage;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.lshop.common.pojo.logic.LvAdvertiseFund;
import com.lshop.common.pojo.logic.LvCoupon;
import com.lshop.common.pojo.logic.LvExtendBalance;
import com.lshop.common.pojo.logic.LvMaterial;
import com.lshop.common.pojo.logic.LvPromtContent;
import com.lshop.common.pojo.logic.LvUserCoupon;
import com.lshop.common.pojo.logic.LvUserPromoters;
import com.lshop.common.pojo.logic.LvVideo;
import com.lshop.excenter.service.PromoterManageService;

@SuppressWarnings("unchecked")
@Service("PromoterManageService")
public class PromoterManageServiceImpl  implements PromoterManageService
{
	@Resource
	private HibernateBaseDAO lvlogicReadDao;
	
	@Resource
	private HibernateBaseDAO lvlogicWriteDao;
	
  public Pagination getOrderDetail(Dto dto) throws ServiceException,ParseException
  {
	 Integer uid=dto.getAsInteger("uid");
	 LvUserCoupon userCoupon=(LvUserCoupon)lvlogicReadDao.findUniqueByProperty(LvUserCoupon.class,"uid",uid);
	 
	 dto.put("couponCode", userCoupon.getCouponCode());
	 SimplePage page=(SimplePage)dto.get("page");
	 String couponCode=userCoupon.getCouponCode();
	 
	 Date date=new Date();
	 SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 SimpleDateFormat format1=new SimpleDateFormat("yyyy-MM-dd ");
	 String str=format1.format(date)+" 00:00:00";
	 
	 //获取买家姓名、成功购买时间、优惠台数、所在地区
	 String	 hql="select  oa.relName,o.overtime,o.couponNum ,oa.contryName,o.isBalance,o.status from LvOrder as o,LvOrderAddress as oa where oa.orderId=o.oid and o.oid in (select orderId from LvOrderDetails where couponCode='"+couponCode+"') and o.payStatus=1 and o.overtime<='"+str+"' and o.isdelete=0 order by o.overtime desc";// and o.overtime<'"+str+"'
	 Pagination pagination=lvlogicReadDao.find(Finder.create(hql),page.getPageNum(),page.getNumPerPage());
	 
	List<Object[]> objList=(List<Object[]>)pagination.getList();
	List<Object[]> myObjList=new ArrayList<Object[]>();
	if(objList!=null&&objList.size()>0)
	{
		for(int i=0;i<objList.size();i++)
		{
			Object[] obj=objList.get(i);
			Object[] myObj=new Object[7];
			myObj[0]=obj[0]; //买家姓名
			myObj[1]=couponCode; //推广码
			myObj[2]=(null==obj[1]?null:format.format(obj[1])); //成功使用时间
			myObj[3]=obj[2]; //优惠台数
			myObj[4]=obj[3]; //所在地区
			myObj[5]=obj[4]; //结算状态：0，未结算；1，已结算
			myObj[6]=obj[5]; //订单状态：3-已退货
			myObjList.add(myObj);
		}
	}
//	  pagination.setTotalCount(myObjList.size());
	  pagination.setList(myObjList);
	  return pagination;
  }
  
  
  public Dto getCodeList(Dto dto)throws ServiceException
  {
	  int mark=0;
	  int usedNum=0;
	  Object[] obj=new Object[6];
	  Integer uid=dto.getAsInteger("uid");
	  Date date=new Date();
	  SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
	  String str=format.format(date)+" 00:00:00";
	  
	  if(uid!=null)
	  {
		  LvUserCoupon userCoupon=(LvUserCoupon)lvlogicReadDao.findUniqueByProperty(LvUserCoupon.class,"uid", uid);
		  LvUserPromoters userPromoter=(LvUserPromoters)lvlogicReadDao.findUniqueByProperty(LvUserPromoters.class, "uid", uid);
		  
		  if(userCoupon!=null)
		  {
			mark=1;
			String couponCode=userCoupon.getCouponCode();
			dto.put("couponCode", couponCode);
			
			String hql1="select DISTINCT orderId from LvOrderDetails where couponCode='"+couponCode+"'" ;
			List<String[]> strList=(List<String[]>)lvlogicReadDao.find(hql1,null);
			if (strList!=null&&!strList.isEmpty()) {
				String string="(";
				
				for(int i=0;i<strList.size();i++)
				{
				  if(i==strList.size()-1)
				  string+="'"+strList.get(i)+"'";
				  else
				  {
					  string+="'"+strList.get(i)+"',";
				  }
				}
				string+=")";
				
				List<Object> objList=(List<Object>)lvlogicReadDao.find("select Count(*) from LvOrder where oid in "+string+" and overtime<='"+str+"' and payStatus=1 and status!=3 and isdelete=0",null);
				if(objList.size()>0)
				{
					String objString=objList.get(0).toString();
					usedNum=Integer.parseInt(objString);
				}
			}
			
	        LvCoupon coupon=(LvCoupon)lvlogicReadDao.findUniqueByProperty(LvCoupon.class, "couponCode", userCoupon.getCouponCode());
	    	obj[0]=coupon.getCouponCode();
	    	obj[1]=coupon.getValidityDate();
	    	obj[2]=coupon.getCreateTime();
	    	obj[3]=usedNum;
	    	obj[4]=Math.round(userPromoter.getSettlementedAmount()+userPromoter.getNonSettlementAmount()+userPromoter.getSettlementAmount()+0.005);
	    	dto.put("obj",obj);
		  }
		
		  if(userPromoter!=null)
		  {
		   dto.put("settlementedAmount", userPromoter.getSettlementedAmount());
		   dto.put("nonSettlementAmount", userPromoter.getNonSettlementAmount());
		   dto.put("settlementAmount", userPromoter.getSettlementAmount());
		   dto.put("settlementStatus", userPromoter.getSettlementStatus()); //存放申请状态，如果已提交申请，则将按钮屏蔽掉
		  }
	  }
	  dto.put("mark",mark);
	  
	  return dto;
  }
  
  public Dto befPay(Dto dto) throws ServiceException
  {
	  Integer uid=dto.getAsInteger("uid");
	  LvUserPromoters userPromoters=(LvUserPromoters)lvlogicReadDao.findUniqueByProperty(LvUserPromoters.class, "uid", uid);
	  
	  	long validityPnum=0L; //有效的已结算台数
		//获取所有的二级推广码
		List codeList=lvlogicReadDao.find("select couponCode from LvUserCoupon where uid='"+userPromoters.getUid()+"'",null);
		if(null!=codeList&&codeList.size()>0)
		{
			String code=codeList.get(0).toString();
			//获取所有的订单
			List<String> oidList=(List<String>)lvlogicReadDao.find("select DISTINCT orderId from LvOrderDetails where couponCode='"+code+"'",null);
			if(null!=oidList && oidList.size()>0)
			{
				String oids=oidList.toString().replaceAll("\\[|\\]", "'").replaceAll("\\, ", "','");
				//获取有效的已结算台数
				String vHql="select sum(couponNum) from LvOrder where oid in ("+oids+") and payStatus =1 and status!=3 and isBalance=1 and isdelete=0";
				Long pnum=(Long)lvlogicReadDao.findUnique(vHql, null);
				if(null!=pnum&&pnum>0){
					validityPnum=pnum;
				}
			}
			
		}

//	  int presentNum=userPromoters.getSettlementedNum();
	  long totalNum=validityPnum+userPromoters.getSettlementNum()+userPromoters.getNonSettlementNum();
	  int presentRate=1;
	  double totalAmount=0.00;
	  long rateNum=0;  //升级台数
	  
      if(0<=validityPnum&&validityPnum<=10) 
      {
    	rateNum=11-validityPnum;
    	presentRate=1;
        totalAmount=(totalNum-validityPnum)*15;
      }
      else if(11<=validityPnum&&validityPnum<=50)
      {
    	rateNum=51-validityPnum;
    	presentRate=2;
        totalAmount=10*15+(totalNum-10)*20-userPromoters.getSettlementedAmount();
	  }
      else if(51<=validityPnum&&validityPnum<=200)
      {
    	rateNum=201-validityPnum;
    	presentRate=3;
        totalAmount=10*15+40*20+(totalNum-50)*35-userPromoters.getSettlementedAmount();
	  }
      else if(201<=validityPnum&&validityPnum<=1000)
      {
    	rateNum=1001-validityPnum;
    	presentRate=4;
        totalAmount=10*15+40*20+150*25+(totalNum-200)*30-userPromoters.getSettlementedAmount();
	  }
      else
      {
    	rateNum=0;
    	presentRate=5;
        totalAmount=10*15+40*20+150*25+800*30+(totalAmount-800)*35-userPromoters.getSettlementedAmount();
	  }
      
      LvUserCoupon userCoupon=(LvUserCoupon)lvlogicReadDao.findUniqueByProperty(LvUserCoupon.class, "uid", uid);
      LvCoupon coupon=(LvCoupon)lvlogicReadDao.findUniqueByProperty(LvCoupon.class, "couponCode", userCoupon.getCouponCode());
      dto.put("coupon",coupon);
      dto.put("userType", userPromoters.getUserType());
      if(userPromoters.getUserType()!=null&&userPromoters.getUserType()==1)
      {
    	  dto.put("specialAmount", userPromoters.getSpecialAmount());
      }
      dto.put("presentNum", validityPnum);
      dto.put("presentRate", presentRate);
      dto.put("totalNum", totalNum);
      dto.put("rateNum", rateNum);
      dto.put("id", userPromoters.getId());
      dto.put("totalAmount", totalAmount);
	  return dto;
  }
  
  public Dto payRequest(Dto dto) throws ServiceException
  {
	   int id=dto.getAsInteger("id");
	   LvUserPromoters userPromoters=(LvUserPromoters)lvlogicReadDao.findUniqueByProperty(LvUserPromoters.class, "uid", id);//LvUserPromoters userPromoters=(LvUserPromoters)dao.load(LvUserPromoters.class, id);
	   if(userPromoters.getSettlementStatus()==0)
	   {
	    //userPromoters.setSettlementStatus(Short.parseShort("1"));
	    lvlogicWriteDao.update("update LvUserPromoters set settlementStatus=1 where uid="+id,null);
	   }
	   return dto;
  }
  
  public Pagination getSettledList(Dto dto) throws ServiceException //结算查询
  {
	  //获取用户推广码
	  Integer uid=dto.getAsInteger("uid");
	  LvUserCoupon userCoupon=(LvUserCoupon)lvlogicReadDao.findUniqueByProperty(LvUserCoupon.class,"uid", uid);
	  
	  //获取结算时间、结算台数、结算金额
	  dto.put("couponCode", userCoupon.getCouponCode());
	  SimplePage page=(SimplePage)dto.get("page");
	  String hql="select balanceTime,balanceNum,balancePrice,balanceId from LvExtendBalance where uid="+uid+" and userType=2 and balanceStatus=1 order by balanceTime desc";
	  Pagination pagination=lvlogicReadDao.find(Finder.create(hql),page.getPageNum(),page.getNumPerPage());
	  
	  //获取结算订单号
	    SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Object[]> objList=(List<Object[]>)pagination.getList();
		List<Object[]> myObjList=new ArrayList<Object[]>();
		if(objList!=null&&objList.size()>0)
		{
			for(int i=0;i<objList.size();i++)
			{
				Object[] obj=objList.get(i);
				Object[] myObj=new Object[5];
				myObj[0]=(null==obj[0]?null:format.format(obj[0])); //结算时间
				myObj[1]=obj[1]; //结算台数
				myObj[2]=obj[2]; //结算金额
				
				//查询结算订单号
				String oids="";
				List<String[]> oidList=(List<String[]>)lvlogicReadDao.find("select orderId from LvExtendBalanceDetails where balanceId='"+obj[3]+"'", null);
				if(oidList!=null){
					for(int j=0;j<oidList.size();j++){
						if(j==oidList.size()-1){
							oids+="'"+oidList.get(j)+"'";
						}else{
							oids+="'"+oidList.get(j)+"',";
						}
					}
				}
				myObj[3]=oids; //结算订单号
				myObj[4]=obj[3]; //结算单号
				myObjList.add(myObj);
			}
		}
	  pagination.setList(myObjList);
	  return pagination;
  }
  
  public  Pagination getDetailList(Dto dto) throws ServiceException //结算明细
  {
		//查询结算订单号
	    String balanceId=dto.getAsString("balanceId");
		String ids="";
		List<String[]> oidList=(List<String[]>)lvlogicReadDao.find("select orderId from LvExtendBalanceDetails where balanceId='"+balanceId+"'", null);
		if(oidList!=null){
			for(int j=0;j<oidList.size();j++){
				if(j==oidList.size()-1){
					ids+="'"+oidList.get(j)+"'";
				}else{
					ids+="'"+oidList.get(j)+"',";
				}
			}
		}
		
//	  String ids=dto.getAsString("ids");
	  SimplePage page=(SimplePage)dto.get("page");
	  String hql="select DISTINCT oa.relName,o.oid,o.overtime,o.couponNum ,oa.contryName,o.isBalance from LvOrder as o,LvOrderAddress as oa where  oa.orderId=o.oid and o.oid in  ("+ids+") and o.isdelete=0 order by o.overtime desc";
	  Pagination pagination=lvlogicReadDao.find(Finder.create(hql),page.getPageNum(),page.getNumPerPage());
      return pagination;
  }
  
  public Pagination getSettleOrderList(Dto dto) throws ServiceException,ParseException//可申请结算的订单详细
  {
	 Integer uid=dto.getAsInteger("uid");
	 LvUserCoupon userCoupon=(LvUserCoupon)lvlogicReadDao.findUniqueByProperty(LvUserCoupon.class,"uid",uid);
	 
	 dto.put("couponCode", userCoupon.getCouponCode());
	 SimplePage page=(SimplePage)dto.get("page");
	 String couponCode=userCoupon.getCouponCode();
	 
	 SimpleDateFormat format1=new SimpleDateFormat("yyyy-MM-dd");
	 SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	 //当前时间
	 Calendar cal= Calendar.getInstance();
	 String curTime=format1.format(cal.getTime());
	 //当前可结算时间，即当前时间-15
	 cal.add(Calendar.DATE, -15);
	 String curSetTime=format1.format(cal.getTime())+" 00:00:00";
	 
	 //获取买家姓名、成功购买时间、优惠台数、所在地区
	 String	 hql="select oa.relName,o.overtime,o.couponNum ,oa.contryName,o.isBalance from LvOrder as o,LvOrderAddress as oa where oa.orderId=o.oid and o.oid in (select orderId from LvOrderDetails where couponCode='"+couponCode+"') and o.payStatus=1 and o.status!=3 and o.isBalance!=1 and o.overtime<='"+curSetTime+"' and o.isdelete=0 order by o.overtime desc";
	 Pagination pagination=lvlogicReadDao.find(Finder.create(hql),page.getPageNum(),page.getNumPerPage());
	 
	  //获取结算状态
		List<Object[]> objList=(List<Object[]>)pagination.getList();
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
				myObj[5]=obj[4]; //结算状态：0，未结算；1，已结算
				myObjList.add(myObj);
			}
		}
	  
//	  pagination.setTotalCount(myObjList.size());
	  pagination.setList(myObjList);
	  return pagination;
  }
  
  public Pagination getNsettleOrderList(Dto dto) throws ServiceException,ParseException //未满足15天条件的订单详细
  {
		 Integer uid=dto.getAsInteger("uid");
		 LvUserCoupon userCoupon=(LvUserCoupon)lvlogicReadDao.findUniqueByProperty(LvUserCoupon.class,"uid",uid);
		 
		 dto.put("couponCode", userCoupon.getCouponCode());
		 SimplePage page=(SimplePage)dto.get("page");
		 String couponCode=userCoupon.getCouponCode();
		 
		 SimpleDateFormat format1=new SimpleDateFormat("yyyy-MM-dd");
		 SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 //当前时间
		 Calendar cal= Calendar.getInstance();
		 String curTime=format1.format(cal.getTime())+" 00:00:00";
		 //当前可结算时间，即当前时间-15
		 cal.add(Calendar.DATE, -15);
		 String curSetTime=format1.format(cal.getTime())+" 00:00:00";
		 
		 //获取买家姓名、成功购买时间、优惠台数、所在地区
		 String	 hql="select oa.relName,o.overtime,o.couponNum ,oa.contryName,o.isBalance from LvOrder as o,LvOrderAddress as oa where oa.orderId=o.oid and o.oid in (select orderId from LvOrderDetails where couponCode='"+couponCode+"') and o.payStatus=1 and o.status!=3 and o.isBalance!=1 and o.overtime<='"+curTime+"' and o.overtime>'"+curSetTime+"' and o.isdelete=0 order by o.overtime desc";
		 Pagination pagination=lvlogicReadDao.find(Finder.create(hql),page.getPageNum(),page.getNumPerPage());
		 
		  //获取结算状态
			List<Object[]> objList=(List<Object[]>)pagination.getList();
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
					myObj[5]=obj[4]; //结算状态：0，未结算；1，已结算
					myObjList.add(myObj);
				}
			}
		
//		  pagination.setTotalCount(myObjList.size());
		  pagination.setList(myObjList);
		  return pagination;
  }
  
  public Pagination getFundRelate(Dto dto) throws ServiceException
  {
	  Integer uid=dto.getAsInteger("uid");
	  SimplePage page=(SimplePage)dto.get("page");
	  LvUserCoupon userCoupon=(LvUserCoupon)lvlogicReadDao.findUniqueByProperty(LvUserCoupon.class, "uid", uid);
	  LvAdvertiseFund advertiseFund=(LvAdvertiseFund)lvlogicReadDao.findUniqueByProperty(LvAdvertiseFund.class, "uid", uid);
	  Pagination pagination=(Pagination)lvlogicReadDao.find(Finder.create("select expensetime,fund,detail from LvExpenseFund where uid="+uid),page.getPageNum(),page.getNumPerPage());
	  
	  int size=pagination.getList().size();
	  dto.put("size", size);
	  
	  String couponCode=userCoupon.getCouponCode();
	  if(couponCode!=null )
	     dto.put("couponCode",userCoupon.getCouponCode());
	  else 
	  {
		dto.put("couponCode", "");
	  }
	 
	  Integer num=0;
	  double enablefund=0d;
	  if(advertiseFund!=null)
	  {
		 num=advertiseFund.getTotalnum();
		 enablefund=advertiseFund.getEnablefund();
	  }
	 
	  dto.put("num",num);
	  dto.put("enablefund",enablefund);
	  return pagination;
  }
  
  public List<Object[]> getUserDetail(Dto dto) throws ServiceException
  {
	String  hql="select realName,settlementedAmount+settlementAmount+nonSettlementAmount from LvUserPromoters order by settlementedAmount+settlementAmount+nonSettlementAmount desc LIMIT 5";
	return lvlogicReadDao.find(hql, null); 
  }
  
  public Dto getToolDetail(Dto dto) throws ServiceException
  {
	  Integer uid=dto.getAsInteger("uid");
	  LvUserCoupon userCoupon=(LvUserCoupon)lvlogicReadDao.findUniqueByProperty(LvUserCoupon.class, "uid", uid);
//	  LvUserPromoters userPromoters=(LvUserPromoters)dao.findUniqueByProperty(LvUserPromoters.class, "uid", uid);
	  if(userCoupon!=null)
	  {
	   dto.put("couponCode", userCoupon.getCouponCode());
	  }
	
	  List<LvPromtContent> promtContentModel1=(List<LvPromtContent>)lvlogicReadDao.find("from LvPromtContent where status=0 and model=1", null);
	  List<LvPromtContent> promtContentModel2=(List<LvPromtContent>)lvlogicReadDao.find("from LvPromtContent where status=0 and model=2", null);
	  List<LvMaterial> materialList=(List<LvMaterial>)lvlogicReadDao.find("from LvMaterial where status=0",null);
	  List<LvVideo> videoList=(List<LvVideo>)lvlogicReadDao.find("from LvVideo",null);
	  dto.put("videoList", videoList);
	  dto.put("materialList", materialList);
	  dto.put("promtContentModel1", promtContentModel1);
	  dto.put("promtContentModel2", promtContentModel2);
	  return dto;
  }
  
  public Dto getContent(Dto dto) throws ServiceException
  {
	  Integer id=dto.getAsInteger("id");
	  LvPromtContent promtContent=(LvPromtContent)lvlogicReadDao.load(LvPromtContent.class, id);
	  dto.put("promtContent", promtContent);
	  return dto;
  }
  
  public Dto getHtmlContent(Dto dto) throws ServiceException
  {
	  Integer id=dto.getAsInteger("id");
	  LvPromtContent promtContent=(LvPromtContent)lvlogicReadDao.load(LvPromtContent.class, id);
	  dto.put("promtContent", promtContent);
	  return dto;
  }
  
  public Dto getPromtPage(Dto dto) throws ServiceException
  {
	  Integer uid=dto.getAsInteger("uid");
	  LvUserCoupon userCoupon=(LvUserCoupon)lvlogicReadDao.findUniqueByProperty(LvUserCoupon.class, "uid",uid);
	  if(userCoupon!=null)
		  dto.put("couponCode", userCoupon.getCouponCode());
	  return dto;
  }
  
  /**
   * 获取结算状态
   * @param oid
   * @return
   */
  /*  public Integer getBalanceStatus(String oid){
	    Integer flag=0; //未结算
	    
		List list=dao.find("select balanceId from LvExtendBalanceDetails where orderId='"+oid+"'", null);
		if(list!=null&&list.size()>0){
			String balanceId=list.get(0).toString(); //结算单号
			LvExtendBalance balance=(LvExtendBalance)dao.findUniqueByProperty(LvExtendBalance.class, "balanceId", balanceId);
			if(balance!=null){
				if(balance.getBalanceStatus()==2){
					flag=1;
				}
			}
		}
		return flag;
  }
 */ 
}
