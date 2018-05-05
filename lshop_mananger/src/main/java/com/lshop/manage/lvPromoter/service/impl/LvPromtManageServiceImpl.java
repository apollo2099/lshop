package com.lshop.manage.lvPromoter.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.call.StatusCode;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.datastructure.page.SimplePage;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.service.impl.BaseServiceImpl;
import com.gv.core.util.ObjectUtils;
import com.gv.core.util.StringUtil;
import com.lshop.common.pojo.logic.LvAdvertiseFund;
import com.lshop.common.pojo.logic.LvCouponAid;
import com.lshop.common.pojo.logic.LvExpenseFund;
import com.lshop.common.pojo.logic.LvMaterial;
import com.lshop.common.pojo.logic.LvOrder;
import com.lshop.common.pojo.logic.LvPromtContent;
import com.lshop.common.pojo.logic.LvRankfirstInfo;
import com.lshop.common.pojo.logic.LvSettlementLog;
import com.lshop.common.pojo.logic.LvUser;
import com.lshop.common.pojo.logic.LvUserCoupon;
import com.lshop.common.pojo.logic.LvUserPromoters;
import com.lshop.common.pojo.logic.LvVideo;
import com.lshop.manage.lvExtendBalance.service.LvExtendBalanceService;
import com.lshop.manage.lvPromoter.service.LvPromtManageService;

@SuppressWarnings("unchecked")
@Service("LvPromtManageService")
public class LvPromtManageServiceImpl extends BaseServiceImpl implements LvPromtManageService 
{
	@Resource
	private HibernateBaseDAO dao;
	@Resource
	private LvExtendBalanceService lvExtendBalanceService;
	public Pagination getPreparePayList(Dto dto) throws ServiceException
	{
		hql="from LvUserPromoters where settlement_status=1";   //查询结算状态为提交申请的
		SimplePage page=(SimplePage)dto.get("page");
		String keyword=dto.getAsString("keyword");
		if(!StringUtil.IsNullOrEmpty(keyword))
		{
			hql+=" and real_name like '%"+keyword+"%'";
		}
		Integer pageNo=page.getPageNum();
		Integer pageSize=page.getNumPerPage();
		Pagination pagination=(Pagination)dao.find(Finder.create(hql),pageNo,pageSize);
		return pagination;
	}
	
	public void preparePay(Dto dto) throws ServiceException,ParseException  //预结算
	{
		Integer id=dto.getAsInteger("id");
		LvUserPromoters promoters=(LvUserPromoters)dao.load(LvUserPromoters.class, id);
		promoters.setSettlementStatus(Short.parseShort("2"));  //结算处理中
		
	    LvUserCoupon userCoupon=(LvUserCoupon)dao.findUniqueByProperty(LvUserCoupon.class,"uid", promoters.getUid());
	    
	    Date date=new Date();
		  SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		  String str=format.format(date);
		  Date tempDate=format.parse(str);
		  long time=tempDate.getTime()-15*24*3600*1000;
		  Date dt=new Date(time);
		  SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  String strDate=df.format(dt);

//	    if(userCoupon!=null)
//		{
//			String couponCode=userCoupon.getCouponCode();
//			List<LvCouponAid> couponAidList=(List<LvCouponAid>)dao.find("from LvCouponAid where isSettlement=0 and ccode='"+couponCode+"'",null);
//			for(int i=0;i<couponAidList.size();i++)
//			{
//				List<LvOrder> orderList=(List<LvOrder>)dao.find("from LvOrder where payStatus=1 and oid='"+couponAidList.get(i).getOid()+"'",null);
//				if(orderList!=null&&orderList.size()>0)
//				{
//					LvOrder order=orderList.get(0);
//					String timeString=order.getOvertime().toString();
//					if(timeString!=null&&timeString.length()>0&&order.getOvertime().getTime()<df.parse(strDate).getTime())
//					{
//						dao.update("update LvCouponAid set isSettlement=1 where oid='"+order.getOid()+"'",null);
//					}
//				}
//			}
//		}
		
		dao.update(promoters);
	}
	
	public Pagination getPayList(Dto dto) throws ServiceException
	{
		hql="from LvUserPromoters where settlement_status=1";
		SimplePage page=(SimplePage)dto.get("page");
		String keyword=dto.getAsString("keyword");
		if(!StringUtil.IsNullOrEmpty(keyword))
		{
			hql+=" and real_name like '%"+keyword+"%'";
		}
		Integer pageNo=page.getPageNum();
		Integer pageSize=page.getNumPerPage();
		Pagination pagination=(Pagination)dao.find(Finder.create(hql),pageNo,pageSize);
		List list=pagination.getList();
		List listTmp=new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			LvUserPromoters lvUserPromoters=(LvUserPromoters) list.get(i);
			//获取所有的二级推广码
			List<String> codeList=(List<String>)dao.find("select couponCode from LvUserCoupon where uid in ("+lvUserPromoters.getUid()+")",null);
			if(codeList.size()>0){
				String codes=codeList.toString().replaceAll("\\[|\\]", "'").replaceAll("\\, ", "','");
				//获取所有的订单
				List<String> oidList=(List<String>)dao.find("select orderId from LvOrderDetails where couponCode in ("+codes+")",null);
				if(oidList.size()>0){
					String oids=oidList.toString().replaceAll("\\[|\\]", "'").replaceAll("\\, ", "','");
					//获取推广台数
					String hql="select sum(couponNum) from LvOrder where oid in ("+oids+") and status<>3 and payStatus =1 and isBalance=1";
					Long pnum=(Long)dao.findUnique(hql, null);
					if(ObjectUtils.isNotEmpty(pnum)&&pnum>0){
						lvUserPromoters.setPnumEd(Integer.parseInt(pnum.toString()));	
					}
				}
			}
			//判断当前用户是否存在未结算的清单
			dto.put("uid",lvUserPromoters.getUid());
			Boolean isFlag=lvExtendBalanceService.isExistBalance(dto);
			lvUserPromoters.setIsFlag(isFlag);
			listTmp.add(lvUserPromoters);
		}
		
		pagination.setList(listTmp);
		return pagination;
	}
	

	
	public Boolean pay(Dto dto) throws ServiceException  //结算
	{
		BaseUsers user=(BaseUsers)dto.get("USER");
		Integer uid=dto.getAsInteger("uid");
		Integer id=dto.getAsInteger("id");
		int level=0;
		LvUserPromoters promoters=(LvUserPromoters)dao.load(LvUserPromoters.class, id);
	    LvUserCoupon userCoupon=(LvUserCoupon)dao.findUniqueByProperty(LvUserCoupon.class,"uid", uid);
	    if(userCoupon!=null)
		{
			String couponCode=userCoupon.getCouponCode();
			/**
			List<Integer> couponAidList=(List<Integer>)dao.find("SELECT id from LvCouponAid where ccode='"+couponCode+"' and isSettlement=1", null);
			String updatehql="UPDATE LvCouponAid SET isSettlement=2 WHERE ccode='"+couponCode+"' AND isSettlement=1";
			String adis=couponAidList.toString().replaceAll("\\[|\\]", "");
		    dao.update(updatehql, null);
		    */
			//
		   dto.put("spreaderType",2);	
		   return lvExtendBalanceService.saveStatement(dto);

		}
	    /**
	    level=promoters.getSettlementedNum()+promoters.getSettlementNum();
		promoters.setSettlementedNum(level);
		double temp=promoters.getSettlementedAmount()+promoters.getSettlementAmount()+0.005;
		temp=Math.round(temp);
		promoters.setSettlementedAmount(temp);
		promoters.setSettlementNum(Integer.parseInt("0"));
		promoters.setSettlementAmount(0.00);
		promoters.setSettlementStatus(Short.parseShort("0"));  //未提交申请
		if(promoters.getUserType()!=null&&promoters.getUserType()==1)
		{
			promoters.setUserRating("VIP6");
		}
		else
		{
		    if(level<=10)
		    {
		    	promoters.setUserRating("VIP1");
		    }
		    else if(11<=level&&level<=50)
		    {
		    	promoters.setUserRating("VIP2");
			}
		    else if(51<=level&&level<=200)
		    {
		    	promoters.setUserRating("VIP3");
			}
		    else if(201<=level&&level<=1000)
		    {
		    	promoters.setUserRating("VIP4");
			}
		    else
		    {
		    	promoters.setUserRating("VIP5");	
			}
		}
		dao.update(promoters);
		*/
		return false;

	}
	
	public Pagination getOrderList(Dto dto) throws ServiceException
	{
		String keyword=dto.getAsString("keyword");
		Integer uid=dto.getAsInteger("uid");
		SimplePage page=(SimplePage)dto.get("page");
		if(uid==null)
			return this.page;
		if(ObjectUtils.isNotEmpty(uid))
		{
			LvUserCoupon userCoupon=(LvUserCoupon)dao.findUniqueByProperty(LvUserCoupon.class,"uid", uid);
			if(userCoupon!=null)
			{
				String couponCode=userCoupon.getCouponCode();
				hql="select o.id as id,o.oid as oid,o.createTime as createTime,o.overtime as overtime," +
						" ld.pnum as pnum, o.postprice as postprice,o.totalPrice  as totalPrice,"+couponCode+" as couponCode " +
						" from LvOrder o,LvOrderDetails ld " +
						" where o.oid=ld.orderId and ld.couponCode='"+couponCode+"'" +
						" and o.payStatus=1 ";
                //hql="select o.id,o.oid,o.createtime,o.overtime,o.pnum,o.postprice,o.totalPrice,ca.ccode from LvOrder o,LvCouponAid ca where ca.ccode='"+couponCode+"' and isSettlement=1 and o.oid=ca.oid";
				if(keyword!=null&&keyword.length()>0)
				{
					hql+=" and o.oid='"+keyword+"'";
				}
				return dao.getMapListByHql(hql.toString(), page.getPageNum(), page.getNumPerPage(), null);
				/*
				hql="from LvCouponAid where ccode='"+couponCode+"' and isSettlement=1" ;
				Pagination pagination=(Pagination)dao.find(Finder.create(hql),page.getPageNum(),page.getNumPerPage());
				List<LvCouponAid> couponAidList=(List<LvCouponAid>)pagination.getList();
				if(keyword!=null&&keyword.length()>0)
				{
					lvOrder=(LvOrder)dao.findUniqueByProperty(LvOrder.class, "oid",keyword);
					if(lvOrder!=null)
						orderList.add(lvOrder);
				}
				else
				{
					for(int i=0;i<couponAidList.size();i++)
					{
						lvOrder=(LvOrder)dao.findUniqueByProperty(LvOrder.class,"oid",couponAidList.get(i).getOid());
						orderList.add(lvOrder);	
					}
				}
				*/
				
			}
		}
		return null;

	}
	
	public List export(Dto dto) throws ServiceException
	{
		String ids=(String) dto.get("ids");
		List list = new ArrayList();
		String[] title = new String[]{"真实姓名","邮箱","联系号码","账号类型","账号","可结算金额","可结算产品数","已结算金额","已结算产品数","已结算未退货产品数"};
		list.add(title);
		if (ids!=null&&!"".equals(ids.trim()))
		{
			String hql="from LvUserPromoters where id in ("+ids+")";
			List<LvUserPromoters> userPromoters=(List<LvUserPromoters>)dao.find(hql,null);
			for(int i=0;i<userPromoters.size();i++)
			{
				Object[] objects=new Object[10];
				LvUserPromoters lvUserPromoters=userPromoters.get(i);
				if(lvUserPromoters!=null){
					objects[0]=lvUserPromoters.getRealName();
					objects[1]=lvUserPromoters.getEmail();
					objects[2]=lvUserPromoters.getTel();
					if(lvUserPromoters.getAccountTypes()!=null)
					{
					if(lvUserPromoters.getAccountTypes()==1)
				        objects[3]="PayPal";
					if(lvUserPromoters.getAccountTypes()==2)
						objects[3]="支付宝";
					}
					objects[4]=lvUserPromoters.getAccountNumber();
					objects[5]=lvUserPromoters.getSettlementAmount();
					objects[6]=lvUserPromoters.getSettlementNum();
					objects[7]=lvUserPromoters.getSettlementedAmount();
					objects[8]=lvUserPromoters.getSettlementedNum();
					
					List<String> codeList=(List<String>)dao.find("select couponCode from LvUserCoupon where uid in ("+lvUserPromoters.getUid()+")",null);
					if(codeList.size()>0){
						String codes=codeList.toString().replaceAll("\\[|\\]", "'").replaceAll("\\, ", "','");
						//获取所有的订单
						List<String> oidList=(List<String>)dao.find("select orderId from LvOrderDetails where couponCode in ("+codes+")",null);
						if(oidList.size()>0){
							String oids=oidList.toString().replaceAll("\\[|\\]", "'").replaceAll("\\, ", "','");
							//获取推广台数
							hql="select sum(couponNum) from LvOrder where oid in ("+oids+") and status<>3 and payStatus =1 and isBalance=1";
							Long pnum=(Long)dao.findUnique(hql, null);
							if(ObjectUtils.isNotEmpty(pnum)&&pnum>0){
								objects[9]=pnum;
							}else{
								objects[9]=0;
							}
						}
					}				
					list.add(objects);
				}
			}
		}
		return list;
	}
	
	public List exportExl(Dto dto) throws ServiceException
	{
		String ids=(String) dto.get("ids");
		List list = new ArrayList();
		String[] title = new String[]{"真实姓名","邮箱","联系号码","账号类型","账号","可结算金额","可结算产品数","已结算金额","已结算产品数"};
		list.add(title);
		if (ids!=null&&!"".equals(ids.trim()))
		{
			String hql="from LvUserPromoters where settlement_status=1 and id in ("+ids+")";
			List<LvUserPromoters> userPromoters=(List<LvUserPromoters>)dao.find(hql,null);
			for(int i=0;i<userPromoters.size();i++)
			{
				Object[] objects=new Object[9];
				objects[0]=userPromoters.get(i).getRealName();
				objects[1]=userPromoters.get(i).getEmail();
				objects[2]=userPromoters.get(i).getTel();
				if(userPromoters.get(i).getAccountTypes()!=null)
				{
					if(userPromoters.get(i).getAccountTypes()==1)
				        objects[3]="PayPal";
					if(userPromoters.get(i).getAccountTypes()==2)
						objects[3]="支付宝";
				}
				objects[4]=userPromoters.get(i).getAccountNumber();
				objects[5]=userPromoters.get(i).getSettlementAmount();
				objects[6]=userPromoters.get(i).getSettlementNum();
				objects[7]=userPromoters.get(i).getSettlementedAmount();
				objects[8]=userPromoters.get(i).getSettlementedNum();
				list.add(objects);
			}
		}
		return list;
	}
	
	public List exportOrder(Dto dto) throws ServiceException
	{
		Integer uid=dto.getAsInteger("uid");
		String ids=(String) dto.get("ids");
		List list = new ArrayList();
		String[] title = new String[]{"订单号","下单时间","支付成功时间","购买产品数量","邮寄费用","订单总金额","推广码"};
		list.add(title);
		if(ObjectUtils.isNotEmpty(ids)){
			if(ObjectUtils.isNotEmpty(uid))
			{
				LvUserCoupon userCoupon=(LvUserCoupon)dao.findUniqueByProperty(LvUserCoupon.class,"uid", uid);
				if(userCoupon!=null)
				{
					String couponCode=userCoupon.getCouponCode();
					hql="select o.oid as oid,o.createTime as createTime,o.overtime as overtime," +
					" ld.pnum as pnum, o.postprice as postprice,o.totalPrice  as totalPrice,"+couponCode+" as couponCode " +
					" from LvOrder o,LvOrderDetails ld where o.oid=ld.orderId and ld.couponCode='"+couponCode+"'" +
					" and o.oid in ("+ids+")";
					//hql="select o.oid,o.createtime,o.overtime,o.pnum,o.postprice,o.totalPrice,ca.ccode from LvOrder o,LvCouponAid ca where ca.ccode='"+couponCode+"' and isSettlement=1 and o.oid=ca.oid and o.oid in ("+ids+")";
				    List<Object[]> objList=(List<Object[]>)dao.find(hql,null);
				    for(int i=0;i<objList.size();i++)
					{
				    	Object[] obj=objList.get(i);
						Object[] objects=new Object[7];
						objects[0]=obj[0];
						objects[1]=obj[1];
						objects[2]=obj[2];
						objects[3]=obj[3];
						objects[4]=obj[4];
						objects[5]=obj[5];
						objects[6]=obj[6];
						list.add(objects);
					}
				}
			}
		}
		
		
		return list;
		
	}
	
	
	public Pagination getFundList(Dto dto) throws ServiceException
	{
		String promtCode=dto.getAsString("promtCode");
		SimplePage page=(SimplePage)dto.get("page");
		Integer pageNo=page.getPageNum();
		Integer pageSize=page.getNumPerPage();
		String hql="from LvAdvertiseFund";
		if(promtCode!=null&&promtCode.length()>0)
			hql+=" where promtCode='"+promtCode+"'";
		Pagination pagination=dao.getMapListByHql(hql, pageNo, pageSize, null);
		List<HashMap> list=(List<HashMap>)pagination.getList();
		if(list!=null&&list.size()>0)
		{
			for(HashMap map :list)
			{
				 Integer uid=(Integer)map.get("uid");
				 LvUserPromoters userPromoters=(LvUserPromoters)dao.findUniqueByProperty(LvUserPromoters.class, "uid", uid);
				 LvUserCoupon userCoupon=(LvUserCoupon)dao.findUniqueByProperty(LvUserCoupon.class, "uid", uid);
				 if(userPromoters!=null)
				 {
					 map.put("realname", userPromoters.getRealName());
					 map.put("email", userPromoters.getEmail());
				 }
				 if(userCoupon!=null)
				 {
					 map.put("couponCode", userCoupon.getCouponCode());
				 }
				 map.put("disablefund", Double.parseDouble(map.get("totalfund").toString())-Double.parseDouble(map.get("enablefund").toString()));
			}
		}
		return pagination;
	}
	
	public Dto getExpenseInfo(Dto dto) throws ServiceException
	{
		Integer uid=dto.getAsInteger("uid");
		LvAdvertiseFund advertiseFund=(LvAdvertiseFund)dao.findUniqueByProperty(LvAdvertiseFund.class, "uid", uid);
		LvUser user=(LvUser)dao.load(LvUser.class, uid);
		if(advertiseFund!=null)
		{
		 Object obj=advertiseFund.getEnablefund();
		 if(obj!=null)
		   dto.put("enablefund", advertiseFund.getEnablefund());
		}
		if(user!=null)
		{
		if(user.getNickname()!=null)
		 dto.put("realname", user.getNickname());
		}
		return dto;
	}
	
	public Dto expenseFund(Dto dto) throws ServiceException
	{
		Integer uid=dto.getAsInteger("uid");
		LvExpenseFund expenseFund=(LvExpenseFund)dto.get("expenseFund");
		
		LvAdvertiseFund advertiseFund=(LvAdvertiseFund)dao.findUniqueByProperty(LvAdvertiseFund.class, "uid", uid);
		if(advertiseFund.getEnablefund()-expenseFund.getFund()<0)
		{
			result.setAppCode(StatusCode.S_ParameterError);
			result.setAppMsg("报销金额有误");
			return result;
		}
		advertiseFund.setEnablefund(advertiseFund.getEnablefund()-expenseFund.getFund());
		dao.update(advertiseFund);
		
		expenseFund.setUid(uid);
		dao.save(expenseFund);
		return dto;
	}
	
	public Pagination getFundDetail(Dto dto) throws ServiceException
	{
		Integer uid=dto.getAsInteger("uid");
		LvUser user=(LvUser)dao.load(LvUser.class,uid);
		LvAdvertiseFund advertiseFund=(LvAdvertiseFund)dao.findUniqueByProperty(LvAdvertiseFund.class, "uid",uid);
		if(user!=null)
		{
			if(user.getNickname()!=null)
			  dto.put("realname", user.getNickname());
		}
		if(advertiseFund!=null)
		{
			dto.put("totalnum",advertiseFund.getTotalnum());
			dto.put("totalfund",advertiseFund.getTotalfund());
			dto.put("disablefund",advertiseFund.getTotalfund()-advertiseFund.getEnablefund());
			dto.put("enablefund",advertiseFund.getEnablefund());
		}
		SimplePage page=(SimplePage)dto.get("page");
		Integer pageNo=page.getPageNum();
		Integer pageSize=page.getNumPerPage();
		Pagination pagination=(Pagination)dao.find(Finder.create("from LvExpenseFund where uid="+uid), pageNo, pageSize);
		return pagination;
	}
	
	public Pagination getContentList(Dto dto) throws ServiceException
	{
		SimplePage page=(SimplePage)dto.get("page");
		Integer pageNo=page.getPageNum();
		Integer pageSize=page.getNumPerPage();
		Pagination pagination=dao.find(Finder.create("from LvPromtContent where status=0"),pageNo,pageSize);
		return pagination;
	}
	
	public Dto add(Dto dto) throws ServiceException
	{
		Integer model=dto.getAsInteger("model");
		LvPromtContent promtContent=(LvPromtContent)dto.get("promtContent");
		if(model==2)
		{
			promtContent.setModel(2);
		    promtContent.setStatus(0);
		}
		dao.save(promtContent);
		return dto;
	}
	
	public Dto delete(Dto dto) throws ServiceException
	{
		String ids=dto.getAsString("ids");
		hql ="update LvPromtContent set status=1 where id in ("+ids+")";
		dao.update(hql,null);
		return dto;
	}
	
	public Dto befEdit(Dto dto) throws ServiceException
	{
		Integer id=dto.getAsInteger("id");
		LvPromtContent promtContent=(LvPromtContent)dao.load(LvPromtContent.class, id);
		dto.put("promtContent", promtContent);
		return dto;
	}
	
	
	
	public Dto edit(Dto dto) throws ServiceException
	{
		LvPromtContent promtContent=(LvPromtContent)dto.get("promtContent");
		dao.update(promtContent);
		return dto;
	}
	
	public Pagination getMaterialList(Dto dto) throws ServiceException
	{
		SimplePage page=(SimplePage)dto.get("page");
		Integer pageNo=page.getPageNum();
		Integer pageSize=page.getNumPerPage();
		Pagination pagination=(Pagination)dao.find(Finder.create("from LvMaterial where status=0"),pageNo, pageSize);
		return pagination;
	}
	
	public Dto deleteMaterial(Dto dto) throws ServiceException
	{
		String ids=dto.getAsString("ids");
		hql="update LvMaterial set status=1 where id in ("+ids+")";
		dao.update(hql,null);
		return dto;
	}
	
	public Dto addMaterial(Dto dto) throws ServiceException
	{
		 String smallimgName=dto.getAsString("smallimgName");
		 String bigimgName=dto.getAsString("bigimgName");
		 String compressName=dto.getAsString("compressName");
		 LvMaterial material=new LvMaterial();
		 material.setMaterialName(dto.getAsString("name"));
		 material.setSmallPath(smallimgName);
		 material.setBigPath(bigimgName);
		 material.setCompressPath(compressName);
		 material.setStatus(0);
		 dao.save(material);
		 return dto;
	}
	
	public Dto befEditMaterial(Dto dto) throws ServiceException
	{
		Integer id=dto.getAsInteger("id");
		LvMaterial material=(LvMaterial)dao.load(LvMaterial.class, id);
		dto.put("material",material);
		return dto;
	}
	
	public Dto editMaterial(Dto dto) throws ServiceException
	{
		 Integer id=dto.getAsInteger("id");
		 LvMaterial material=(LvMaterial)dao.load(LvMaterial.class, id);
		 String smallimgName=dto.getAsString("smallimgName");
		 String bigimgName=dto.getAsString("bigimgName");
		 String compressName=dto.getAsString("compressName");
		
		 material.setMaterialName(dto.getAsString("name"));
		 if(smallimgName!=null&&smallimgName.length()>0)
		     material.setSmallPath(smallimgName);
		 if(bigimgName!=null&&bigimgName.length()>0)
		     material.setBigPath(bigimgName);
		 if(compressName!=null&&compressName.length()>0)
		     material.setCompressPath(compressName);
		 material.setStatus(0);
		 dao.update(material);
		 return dto;
	}
	
	public List exportFund(Dto dto) throws ServiceException
	{
		String ids=(String) dto.get("ids");
		List list = new ArrayList();
		String[] title = new String[]{"姓名","邮件","推广码","总台数","总基金","已报销基金","可用广告基金"};
		list.add(title);
		List<LvAdvertiseFund> advertiseFundList=(List<LvAdvertiseFund>)dao.find("from LvAdvertiseFund where id in ("+ids+")", null);
		for(int i=0;i<advertiseFundList.size();i++)
		{
			LvAdvertiseFund advertiseFund=advertiseFundList.get(i);
			LvUserPromoters userPromoters=(LvUserPromoters)dao.findUniqueByProperty(LvUserPromoters.class, "uid", advertiseFund.getUid());
			LvUserCoupon userCoupon=(LvUserCoupon)dao.findUniqueByProperty(LvUserCoupon.class, "uid", advertiseFund.getUid());
			Object[] objects=new Object[7];
			if(userPromoters!=null)
			{
		      if(userPromoters.getRealName()!=null)
			     objects[0]=userPromoters.getRealName();
		      if(userPromoters.getEmail()!=null)
			     objects[1]=userPromoters.getEmail();
			}
			if(userCoupon!=null)
			  objects[2]=userCoupon.getCouponCode();
			objects[3]=advertiseFund.getTotalnum();
			objects[4]=advertiseFund.getTotalfund();
			objects[5]=advertiseFund.getTotalfund()-advertiseFund.getEnablefund();
			objects[6]=advertiseFund.getEnablefund();
			list.add(objects);
		}
		return list;
	}
	
	public Pagination getVideoList(Dto dto) throws ServiceException
	{
		String hql="from LvVideo";
		SimplePage page=(SimplePage)dto.get("page");
		Integer pageNo=page.getPageNum();
		Integer pageSize=page.getNumPerPage();
		return dao.find(Finder.create(hql),pageNo,pageSize);
		
	}
	
	public Dto addVideo(Dto dto) throws ServiceException
	{
		LvVideo video=(LvVideo)dto.get("video");
		dao.save(video);
		return dto;
	}
	
	public LvVideo befEditVideo(Dto dto) throws ServiceException
	{
		Integer id=dto.getAsInteger("id");
		return (LvVideo)dao.load(LvVideo.class, id);
	}
	
	public Dto editVideo(Dto dto) throws ServiceException
	{
		LvVideo video=(LvVideo)dto.get("video");
		dao.update(video);
		return dto;
	}
	
	public Dto deleteVideo(Dto dto) throws ServiceException
	{
		String ids=dto.getAsString("ids");
		hql ="delete from LvVideo where id in ("+ids+")";
		dao.delete(hql, null);
		return dto;
	}
	
	public Boolean isVersionFailure(Dto dto) throws ServiceException {
		//判断最近一次修改是否是有效的版本控制，即是数据否失效。
		Date versionTime=(Date) dto.get("versionTime");
		Integer uid=dto.getAsInteger("uid");
		String hql="from LvUserPromoters where uid=:uid";
		Map param=new HashMap();
		param.put("uid", uid);
		LvUserPromoters lvUserPromoters=(LvUserPromoters) dao.findUnique(hql, param);
		if(lvUserPromoters==null){//已经删除
			return false;
		}
		if(ObjectUtils.isEmpty(versionTime)&&ObjectUtils.isNotEmpty(lvUserPromoters.getModifyTime())){
			return false;
		}else if(ObjectUtils.isNotEmpty(versionTime)&&ObjectUtils.isNotEmpty(lvUserPromoters.getModifyTime())){
			if(lvUserPromoters.getModifyTime().getTime()>versionTime.getTime()){
				return false;
			}
		}
		return true;
	}
	
}
