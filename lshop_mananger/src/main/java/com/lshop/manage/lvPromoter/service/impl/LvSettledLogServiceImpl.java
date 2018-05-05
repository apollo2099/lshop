package com.lshop.manage.lvPromoter.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.datastructure.page.SimplePage;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.service.impl.BaseServiceImpl;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvOrder;
import com.lshop.common.pojo.logic.LvUserPromoters;
import com.lshop.common.util.DateUtils;
import com.lshop.manage.lvPromoter.service.LvSettledLogService;

@SuppressWarnings("unchecked")
@Service("LvSettledLogService")
public class LvSettledLogServiceImpl extends BaseServiceImpl implements LvSettledLogService
{
  @Resource HibernateBaseDAO dao;
  
  public Pagination getLogList(Dto dto) throws ServiceException
  {
	  String name=dto.getAsString("name");
	  String email=dto.getAsString("email");
	  String couponCode=dto.getAsString("couponCode");
	  String overtime=dto.getAsString("overtime");
	  String accountNumber=dto.getAsString("accountNumber");
	  
		SimplePage page=(SimplePage)dto.get("page");
		Integer pageNo=page.getPageNum();
		Integer pageSize=page.getNumPerPage();
		String hql="select sl.clearingTime,up.realName,up.email,up.tel,uc.couponCode,up.accountTypes," +
				" up.accountNumber,sl.aids,sl.settlementedNum,sl.settlementedAmount,sl.aids,sl.uid " +
				" from LvSettlementLog sl,LvUserPromoters up,LvUserCoupon uc where up.uid=sl.uid and uc.uid=sl.uid and sl.userType=2";
		if(name!=null&&name.length()>0)
			hql+=" and up.realName='"+name+"'";
		if(email!=null&&email.length()>0)
		{
			hql+=" and up.email='"+email+"'";
		}
		if(couponCode!=null&&couponCode.length()>0)
		{
			hql+=" and uc.couponCode='"+couponCode+"'";
		}
		if(accountNumber!=null&&accountNumber.length()>0)
		{
			hql+=" and up.accountNumber='"+accountNumber+"'";
		}
		if(overtime!=null&&overtime.length()>0)
		{
			String startTime=overtime+" 00:00:00";
			String endTime=overtime+" 23:59:59";
			hql+=" and sl.clearingTime>'"+startTime+"' and sl.clearingTime<'"+endTime+"'";
		}
		hql+=" order by sl.clearingTime desc";
		Pagination pagination=(Pagination)dao.find(Finder.create(hql), pageNo, pageSize);
		return pagination;
  }

  public LvUserPromoters getUserDetail(Dto dto) throws ServiceException
  {
	  Integer uid=dto.getAsInteger("uid");
	  LvUserPromoters userPromoters=(LvUserPromoters)dao.findUniqueByProperty(LvUserPromoters.class,"uid", uid);
	  return userPromoters;
  }

  public Pagination getOrderList(Dto dto) throws ServiceException
  {
	  String keyword=dto.getAsString("keyword");
	  SimplePage page=(SimplePage)dto.get("page");
	  String couponCode= (String) dto.get("couponCode");
	  String aids=(String) dto.get("aids");
	  Pagination pagination=null;
	  if (ObjectUtils.isNotEmpty(aids) ) {
		 String hql="select o.id as id,o.oid as oid,o.createTime as createTime,o.overtime as overtime," +
			" ld.pnum as pnum, o.postprice as postprice,o.totalPrice  as totalPrice,ld.couponCode as couponCode,ld.isPackage as isPackage " +
			" from LvOrderDetails ld, LvOrder o " +
			" where o.oid=ld.orderId " +
			//" and ld.couponCode='"+couponCode+"'" +
			" and o.oid in("+aids+")" +
			" and o.payStatus=1" +
			" and ld.couponCode<>''";
		    if (ObjectUtils.isNotEmpty(keyword)) {
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
				if(ObjectUtils.isNotEmpty(isPackage)&&isPackage==1){
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
	  }
	  
	  return pagination;
 }

  public List export(Dto dto) throws ServiceException
  {
	  Pagination pagination=null;
	  String ids=(String) dto.get("ids");
	  String couponCode= (String) dto.get("couponCode");
	  List list = new ArrayList();
	  String[] title = new String[]{"订单号","下单时间","支付成功时间","套餐数","购买产品数量","邮寄费用","订单总金额","优惠码"};
	  list.add(title);
      if (ObjectUtils.isNotEmpty(ids)) {
    	  String hql="select o.oid as oid,o.createTime as createTime,o.overtime as overtime," +
  		             " ld.pnum as pnum, o.postprice as postprice,o.totalPrice  as totalPrice,ld.couponCode as couponCode,ld.isPackage as isPackage" +
  		             " from LvOrder o,LvOrderDetails ld where o.oid=ld.orderId and o.oid in ("+ids+")" +
  		       	     " and o.payStatus=1" +
  				     " and ld.couponCode<>''";
       pagination= dao.getMapListByHql(hql, null);
       List listTmp=pagination.getList();
		for (int i = 0; i < listTmp.size(); i++) {
			Map map=(Map) listTmp.get(i);
			Integer isPackage=(Integer) map.get("isPackage");
			String orderId=(String) map.get("oid");
			
			String [] tempArray = new String[22];
			tempArray[0] = String.valueOf(map.get("oid"));
			tempArray[1] = DateUtils.formatDate((Date)map.get("createTime"), "yyyy-MM-dd HH:mm:ss");
			tempArray[2] = DateUtils.formatDate((Date)map.get("overtime"), "yyyy-MM-dd HH:mm:ss");
			if(ObjectUtils.isNotEmpty(isPackage)&&isPackage==1){
				//套餐优惠码统计笔数
				hql="select sum(ld.pnum) as num" +
					" from LvOrderPackageDetails ld where ld.couponCode ='"+couponCode+"'" +
					" and ld.orderDetailsCode in( select lod.code from LvOrderDetails lod where lod.isPackage=1  " +
					" and lod.orderId='"+orderId+"')";
				Long tmpNum=(Long) dao.findUnique(hql, null);
				tempArray[3] =String.valueOf(map.get("pnum"));
				tempArray[4] =tmpNum.toString(); 
			}else{
				tempArray[3] ="";
				tempArray[4] =String.valueOf(map.get("pnum"));
			}
			tempArray[5] = String.valueOf(map.get("postprice"));
			tempArray[6] = String.valueOf(map.get("totalPrice"));
			tempArray[7] = couponCode;

  			list.add(tempArray);
	   }
	  }
      
	  return list;
  }
}
