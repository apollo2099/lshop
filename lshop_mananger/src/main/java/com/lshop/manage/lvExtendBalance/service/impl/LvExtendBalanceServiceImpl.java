package com.lshop.manage.lvExtendBalance.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.datastructure.page.SimplePage;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.ObjectUtils;
import com.gv.core.util.StringUtil;
import com.lshop.common.pojo.logic.LvAdvertiseFund;
import com.lshop.common.pojo.logic.LvExtendBalance;
import com.lshop.common.pojo.logic.LvExtendBalanceDetails;
import com.lshop.common.pojo.logic.LvOrder;
import com.lshop.common.pojo.logic.LvOrderAddress;
import com.lshop.common.pojo.logic.LvOrderDetails;
import com.lshop.common.pojo.logic.LvRankfirstInfo;
import com.lshop.common.pojo.logic.LvSettlementLog;
import com.lshop.common.pojo.logic.LvUserCoupon;
import com.lshop.common.pojo.logic.LvUserPromoters;
import com.lshop.common.util.CodeUtils;
import com.lshop.common.util.DateUtils;
import com.lshop.manage.lvExtendBalance.service.LvExtendBalanceService;
/**
 * 
 *  
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvExtendBalance.service.impl.LvExtendBalanceServiceImpl.java]  
 * @ClassName:    [LvExtendBalanceServiceImpl]   
 * @Description:  [推广商结算信息管理-数据访问层]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-8-2 下午05:13:08]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-8-2 下午05:13:08]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v1.0] 
 *
 */
@Service("LvExtendBalanceService")
public class LvExtendBalanceServiceImpl implements LvExtendBalanceService {
	private static final Log logger	= LogFactory.getLog(LvExtendBalanceServiceImpl.class);
	@Resource 
	private HibernateBaseDAO dao;
	
	/**
	 * 
	 * @Method: getList 
	 * @Description:  [一句话描述该类的功能]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-2 下午05:13:12]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-2 下午05:13:12]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Pagination getList(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvExtendBalanceServiceImpl.getList() method begin*****");
		}
		
		String orderField = dto.getAsString("orderField");
		String orderDirection = dto.getAsString("orderDirection");
		SimplePage page = (SimplePage)dto.get("page");
		LvExtendBalance lvExtendBalance=(LvExtendBalance) dto.get("lvExtendBalance");
		Map<String ,Object> params=new HashMap<String ,Object>();
		StringBuilder hql = new StringBuilder("from LvExtendBalance where 1=1 ");
		if(lvExtendBalance!=null){
			if(ObjectUtils.isNotEmpty(lvExtendBalance.getBalanceId())){
				hql.append(" and balanceId=:balanceId");
				params.put("balanceId", lvExtendBalance.getBalanceId());
			}
			if(ObjectUtils.isNotEmpty(lvExtendBalance.getStartTime())&&ObjectUtils.isNotEmpty(lvExtendBalance.getEndTime())){
				hql.append(" and balanceTime>:startTime" +
       		         " and balanceTime<:endTime");
            	params.put("startTime", DateUtils.convertToDateTime(lvExtendBalance.getStartTime()+" 00:00:00"));
            	params.put("endTime", DateUtils.convertToDateTime(lvExtendBalance.getEndTime()+" 23:59:59"));
			}
			if(ObjectUtils.isNotEmpty(lvExtendBalance.getUid())){
				hql.append(" and uid=:uid");
				params.put("uid", lvExtendBalance.getUid());
			}
		}		
		hql.append(" order by balanceStatus asc ,balanceTime desc");
		Finder finder = Finder.create(hql.toString());
		finder.setParams(params);
		return dao.find(finder, page.getPageNum(), page.getNumPerPage());
	}
	
	
	/**
	 * 
	 * @Method: save 
	 * @Description:  [一句话描述该类的功能]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-2 下午05:13:17]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-2 下午05:13:17]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public void save(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		if (logger.isInfoEnabled()){
			logger.info("***** LvExtendBalanceServiceImpl.save() method begin*****");
		}
		LvExtendBalance lvExtendBalance=(LvExtendBalance) dto.get("lvExtendBalance");
		dao.save(lvExtendBalance);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvExtendBalanceServiceImpl.save() method end*****");
		}
	}

	
	@Override
	public Boolean isExistBalance(Dto dto) throws ServiceException {
		Integer uid=(Integer) dto.get("uid");
		if (ObjectUtils.isNotEmpty(uid)) {
			String hql="from LvExtendBalance where balanceStatus=0 and uid="+uid+"";
			List list=dao.find(hql, null);
			if (list!=null&&list.size()>0) {
				return true;
			}
		}
		return false;
	}
   
	
	/**
	 * 
	 * @Method: getOrderInfo 
	 * @Description:  [一句话描述该类的功能]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-2 下午05:13:21]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-2 下午05:13:21]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public void getOrderInfo(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 
	 * @Method: getBalanceDetails 
	 * @Description:  [查看结算清单详情信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-20 下午12:00:03]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-20 下午12:00:03]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Pagination getBalanceDetails(Dto dto) throws ServiceException {
		String orderField = dto.getAsString("orderField");
		String orderDirection = dto.getAsString("orderDirection");
		SimplePage page = (SimplePage)dto.get("page");
		LvExtendBalance lvExtendBalance=(LvExtendBalance) dto.get("lvExtendBalance");
		
		String hql="select bd.balanceId as balanceId ,bd.orderId as orderId,o.createTime as createTime,o.overtime as overtime,o.postprice as postprice," +
				" o.totalPrice as totalPrice,ds.productCode as productCode,lp.productName as productName,ds.code as orderDetailsCode," +
				" ds.pnum as pnum,ds.isPackage as isPackage,bd.couponCode as couponCode \n" +
				" from LvExtendBalanceDetails bd ,LvOrderDetails ds,LvOrder o,LvProduct lp \n" +
				" where bd.orderDetailsCode=ds.code \n" +
				" and ds.orderId=o.oid " +
				" and o.payStatus=1" +
				" and ds.productCode=lp.code" +
				" and bd.balanceId='"+lvExtendBalance.getBalanceId()+"'\n";

		if(!StringUtil.IsNullOrEmpty(orderField)&&!StringUtil.IsNullOrEmpty(orderDirection)){
			hql+=" order by bd."+ orderField+" "+orderDirection;
		}
		//针对未套餐的统计优惠订单台数
		Pagination pageTmp= dao.getMapListByHql(hql.toString(), page.getPageNum(), page.getNumPerPage(), null);
		List list=pageTmp.getList();
		List listTmp=new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			Map map=(Map) list.get(i);
			Integer isPackage=(Integer) map.get("isPackage");
			String couponCode=(String) map.get("couponCode");
			String orderId=(String) map.get("orderId");
			String orderDetailsCode=(String) map.get("orderDetailsCode");
			if(ObjectUtils.isNotEmpty(isPackage)&&isPackage!=null&&isPackage==1){
				//套餐优惠码统计笔数
				hql=" select sum(ld.pnum) as num" +
					" from LvOrderPackageDetails ld where ld.couponCode ='"+couponCode+"'" +
					" and ld.orderDetailsCode='"+orderDetailsCode+"'";
				Long tmpNum=(Long) dao.findUnique(hql, null);
				map.put("MealPnum", tmpNum);
			}else{
				map.put("MealPnum", 0);
			}
			listTmp.add(map);
		}
		pageTmp.setList(listTmp);
		return  pageTmp;
	}

	/**
	 * 
	 * @Method: updateBalance 
	 * @Description:  [财务确认结算(同步订单信息中的结算状态,推广商信息表中的结算数目和结算金额刷新)]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-20 下午12:00:06]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-20 下午12:00:06]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public void updateBalance(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvExtendBalanceServiceImpl.save() method begin*****");
		}
		
		//财务结算确认
		LvExtendBalance lvExtendBalance=(LvExtendBalance) dto.get("lvExtendBalance");
	    String hql="update LvExtendBalance set balanceStatus=1," +
  		        " modifyTime='"+DateUtils.formatDate(lvExtendBalance.getModifyTime(), null) +"'," +
	    		" modifyUserId="+lvExtendBalance.getModifyUserId()+"," +
	    		" modifyUserName='"+lvExtendBalance.getModifyUserName()+"'" +
	    		" where id="+lvExtendBalance.getId()+"";
		dao.update(hql, null);
		
		//根据id查询结算信息
		lvExtendBalance=this.get(dto);
		//修改订单信息表的结算状态
		if(ObjectUtils.isNotEmpty(lvExtendBalance.getBalanceId())){
			if(lvExtendBalance.getUserType()==1){//一级推广商
				hql=" update LvOrder set isBalanceFirst=1 " +
				    " where oid in(select orderId from LvExtendBalanceDetails where balanceId ='"+lvExtendBalance.getBalanceId()+"')";
				dao.update(hql, null);
			}else if(lvExtendBalance.getUserType()==2){//二级推广商
				hql="update LvOrder set isBalance=1 " +
				    " where oid in(select orderId from LvExtendBalanceDetails where balanceId ='"+lvExtendBalance.getBalanceId()+"')";
				dao.update(hql, null);	
			}
		}
		
		//根据结算日志修改一级二级推广商的已清算和可清算数据
		if(lvExtendBalance.getUserType()==1){//一级推广商
			//修改一级推广商的已清算和可清算数据
			LvRankfirstInfo lvRankfirstInfo = (LvRankfirstInfo)dao.findUnique("select o from LvRankfirstInfo o where  uid="+lvExtendBalance.getUid()+"", null);
			//累计可结算台数(统计可结算台数-本次结算单台数)
			Integer settlementNumTmp=0;
			if (ObjectUtils.isNotEmpty(lvRankfirstInfo.getSettlementNum())) {
				if (lvRankfirstInfo.getSettlementNum()>=lvExtendBalance.getBalanceNum()) {
					settlementNumTmp=lvRankfirstInfo.getSettlementNum()-lvExtendBalance.getBalanceNum();
				}
			}
			//累计可结算金额(统计可结算金额-本次结算单金额)
			Double settlementAmountTmp=0.0;
			if (ObjectUtils.isNotEmpty(lvRankfirstInfo.getSettlementAmount())) {
				if (lvRankfirstInfo.getSettlementAmount()>=lvExtendBalance.getBalancePrice()) {
					settlementAmountTmp=lvRankfirstInfo.getSettlementAmount()-lvExtendBalance.getBalancePrice();
				}
			}
			hql=" update LvRankfirstInfo set settlementNum="+settlementNumTmp+"," + //累计可结算台数
			" settlementAmount="+settlementAmountTmp+"," +                          //累计可结算金额
			" settlementedNum="+(lvExtendBalance.getBalanceNum()+lvRankfirstInfo.getSettlementedNum())+"," +         //将已结算台数累加
			" settlementedAmount="+(lvExtendBalance.getBalancePrice()+lvRankfirstInfo.getSettlementedAmount())+"," + //将已结算金额累加
			" settlementStatus=0," +
			" modifyTime='"+DateUtils.formatDate(lvExtendBalance.getModifyTime(), null) +"'," +
	    	" modifyUserId="+lvExtendBalance.getModifyUserId()+"," +
	    	" modifyUserName='"+lvExtendBalance.getModifyUserName()+"'" +
			" where uid="+lvRankfirstInfo.getUid()+"";
		    dao.update(hql,null);
		}else if(lvExtendBalance.getUserType()==2){//二级推广商
			//修改二级推广商的已清算和可清算数据
			LvUserPromoters promoters=(LvUserPromoters)dao.findUnique("select o from LvUserPromoters o where  uid="+lvExtendBalance.getUid()+"", null);
			//累计可结算台数(统计可结算台数-本次结算单台数)
			Integer settlementNumTmp=0;
			if (ObjectUtils.isNotEmpty(promoters.getSettlementNum())) {
				if (promoters.getSettlementNum()>=lvExtendBalance.getBalanceNum()) {
					settlementNumTmp=promoters.getSettlementNum()-lvExtendBalance.getBalanceNum();
				}
			}
			//累计可结算金额(统计可结算金额-本次结算单金额)
			Double settlementAmountTmp=0.0;
			if (ObjectUtils.isNotEmpty(promoters.getSettlementAmount())) {
				if (promoters.getSettlementAmount()>=lvExtendBalance.getBalancePrice()) {
					settlementAmountTmp=promoters.getSettlementAmount()-lvExtendBalance.getBalancePrice();
				}
			}

			hql=" update LvUserPromoters set settlementNum="+settlementNumTmp+"," +   //累计可结算台数
				" settlementAmount="+settlementAmountTmp+"," +                        //累计可结算金额
				" settlementedNum="+(lvExtendBalance.getBalanceNum()+promoters.getSettlementedNum())+"," +        //将已结算台数累加
				" settlementedAmount="+(lvExtendBalance.getBalancePrice()+promoters.getSettlementedAmount())+"," + //将已结算金额累加
				" settlementStatus=0," +
				" modifyTime='"+DateUtils.formatDate(lvExtendBalance.getModifyTime(), null) +"'," +
		    	" modifyUserId="+lvExtendBalance.getModifyUserId()+"," +
		    	" modifyUserName='"+lvExtendBalance.getModifyUserName()+"'" +  
				" where uid="+lvExtendBalance.getUid()+"";
			dao.update(hql,null);
		}
		
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvExtendBalanceServiceImpl.save() method end*****");
		}
	}
	
	/**
	 * 
	 * @Method: get 
	 * @Description:  [根据id查询结算信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-20 下午03:17:08]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-20 下午03:17:08]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public LvExtendBalance get(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvExtendBalanceServiceImpl.get() method begin*****");
		}
		LvExtendBalance lvExtendBalance=(LvExtendBalance) dto.get("lvExtendBalance");
		lvExtendBalance=(LvExtendBalance) dao.load(LvExtendBalance.class, lvExtendBalance.getId());
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvExtendBalanceServiceImpl.get() method begin*****");
		}
		return lvExtendBalance;
	}
	
	/**
	 * 
	 * @Method: getBalance 
	 * @Description:  [根据结算单编号查询结算单详情]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-11-28 上午09:13:52]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-11-28 上午09:13:52]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public LvExtendBalance getBalance(Dto dto) throws ServiceException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvExtendBalanceServiceImpl.get() method begin*****");
		}
		LvExtendBalance lvExtendBalance=(LvExtendBalance) dto.get("lvExtendBalance");
		String hql="from LvExtendBalance where balanceId=:balanceId";
		Map param=new HashMap();
		param.put("balanceId", lvExtendBalance.getBalanceId());
		lvExtendBalance=(LvExtendBalance)dao.findUnique(hql, param);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvExtendBalanceServiceImpl.get() method begin*****");
		}
		return lvExtendBalance;
	}
	

	/**
	 * 
	 * @Method: saveStatement 
	 * @Description:  [后台推广商生成结算清单]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-20 下午04:07:06]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-20 下午04:07:06]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Boolean saveStatement(Dto dto) throws ServiceException {
		BaseUsers user =(BaseUsers)dto.get("USER");
		Integer uid=dto.getAsInteger("uid"); //用户id
		Integer spreaderType=dto.getAsInteger("spreaderType");//推广商类型：1一级推广商，2二级推广商
		String curTime=""; //当前时间
 		String curSetTime=""; //当前可结算时间，即当前时间-15
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		
 		//当前时间
 		Calendar cal= Calendar.getInstance();
 		curTime=format.format(cal.getTime())+" 00:00:01";
 		
 		//当前可结算时间，即当前时间-15
 		cal.add(Calendar.DATE, -15);
 		curSetTime=format.format(cal.getTime())+" 00:00:01";
 		
 		if(ObjectUtils.isNotEmpty(spreaderType)){
 			if(spreaderType==1){
 				//一级推广商结算
 				return this.saveFirstBalance(uid,spreaderType,curSetTime,user);
 			}else if(spreaderType==2){
 				// 二级推广商结算
 				return this.savesecondBalance(uid,spreaderType,curSetTime,user);
 			}
 		}
 		return false;
	}
	
	/**
	 * 一级推广商结算累计算法描述：
	 * (1)结算金额=结算台数*一级推广商返现金额
	 *  并且生成结算清单信息和清单详情信息
	 * @Method: saveFirstBalance  
	 * @return void
	 */
	public Boolean saveFirstBalance(Integer uid,Integer spreaderType,String curSetTime,BaseUsers user){		
     	   //查询一级推广商信息
		   LvRankfirstInfo lvRankfirstInfo = (LvRankfirstInfo)dao.findUnique("select o from LvRankfirstInfo o where settlementStatus!=0 and uid="+uid+"", null);
		   //获取该一级推广商所对应的所有二级推广商uid
		   List<Integer> uidList=(List<Integer>)dao.find("select uidSecond from LvPromoterRelate where uidFirst='"+uid+"'",null);
		   if(uidList.size()>0){
			  String uids=uidList.toString().replaceAll("\\[|\\]", ""); 
			  //获取所有的二级推广码
			  List<String> codeList=(List<String>)dao.find("select couponCode from LvUserCoupon where uid in ("+uids+")",null);
			  Integer pnum=0;
			  
			  Integer hisPnum=0;
			  if (codeList!=null&&  codeList.size()>0) {
		      //for (int i = 0; i < codeList.size(); i++) {
		    	 //String couponCode=codeList.get(i);
				 String codes=codeList.toString().replaceAll("\\[|\\]", "'").replaceAll("\\, ", "','");
		    	 if(ObjectUtils.isNotEmpty(codes)){
		    		//根据优惠码统计推广的总台数
		    		 List<String> oidList=(List<String>)dao.find("select DISTINCT orderId from LvOrderDetails where couponCode in("+codes+")",null);
		    		 String oids=oidList.toString().replaceAll("\\[|\\]", "'").replaceAll("\\, ", "','");
		    		 String hql="select sum(couponNum) from LvOrder where oid in ("+oids+") and overtime<='"+curSetTime+"' and payStatus =1 and status!=3 and  isdelete=0 and isBalanceFirst!=1 ";
		    		 Long tmpNum=(Long)dao.findUnique(hql, null);
		    		 //历史已经结算台数
		    		 hql="select sum(couponNum) from LvOrder where oid in ("+oids+") and overtime<='"+curSetTime+"' and payStatus =1 and status!=3 and isdelete=0 and isBalanceFirst=1";
		    		 Long hisNum=(Long)dao.findUnique(hql, null);
		    		

						Integer tmpPnum=0;
						if(ObjectUtils.isNotEmpty(tmpNum)){
							tmpPnum=tmpNum.intValue();
		    	        }
						
						//累加历史结算台数
						Integer hisPnumTmp=0;
						if(ObjectUtils.isNotEmpty(hisNum)){
							hisPnumTmp=hisNum.intValue();
						}
						//已经结算的总台数累加
						pnum+=(tmpPnum+hisPnumTmp);
						hisPnum+=hisPnumTmp;
		    	 }
				    
				 }//end for
		         //
		        
		         List<String> orderList=new ArrayList<String>();
		         String codes=codeList.toString().replaceAll("\\[|\\]", "'").replaceAll("\\, ", "','");
				 if(ObjectUtils.isNotEmpty(pnum)&&pnum>0&&pnum>hisPnum){
						 Double totalRevenue=0.0;
						 Double hisRevenue=0.0;
						//结算金额=结算台数*一级推广商返现金额
						if(ObjectUtils.isNotEmpty(lvRankfirstInfo.getSpecialAmount())){
						   totalRevenue=lvRankfirstInfo.getSpecialAmount()*pnum;
						   hisRevenue=lvRankfirstInfo.getSpecialAmount()*hisPnum;
						   
						 //(1)保存推广商结算信息
							LvExtendBalance lvExtendBalance=new LvExtendBalance();
							String balanceId = "JS" + System.currentTimeMillis();
							lvExtendBalance.setBalanceId(balanceId);   //结算单号
							lvExtendBalance.setBalanceStatus(0);       //结算状态
							lvExtendBalance.setBalanceTime(new Date());//结算时间
							lvExtendBalance.setUid(uid);
							lvExtendBalance.setUserEmail(lvRankfirstInfo.getEmail());
							lvExtendBalance.setUserType(spreaderType);//推广商用户类型
							lvExtendBalance.setCode(CodeUtils.getCode());
							lvExtendBalance.setCreateTime(new Date());
							
							lvExtendBalance.setBalanceNum(pnum-hisPnum);       //结算数量
							lvExtendBalance.setBalancePrice(totalRevenue-hisRevenue);//结算金额
							//lvExtendBalance.setCouponCode(codes);
							dao.save(lvExtendBalance);
							
							//(2)根据优惠码查询当前的订单详情，保存推广商结算明细信息(不区分套餐和非套餐)payStatus =1 and status!=3 and  isdelete=0
							String hql=" select ld from LvOrderDetails ld where ld.couponCode in("+codes+")  " +
								" and not EXISTS(select lsd from LvExtendBalanceDetails lsd where lsd.orderDetailsCode=ld.code and EXISTS  (select lb.balanceId from LvExtendBalance lb where lb.balanceId=lsd.balanceId and lb.userType="+spreaderType+"))" +
								" and exists(select o from LvOrder o where o.oid=ld.orderId and o.status<>3 and overtime<='"+curSetTime+"' and o.isdelete=0 and o.payStatus =1 and o.isBalanceFirst!=1)";
							List<LvOrderDetails> detailsList=dao.find(hql, null);
							for(LvOrderDetails lvOrderDetails: detailsList){
								LvExtendBalanceDetails lvExtendBalanceDetails=new LvExtendBalanceDetails();
								lvExtendBalanceDetails.setBalanceId(balanceId);                      //结算单号
								lvExtendBalanceDetails.setOrderId(lvOrderDetails.getOrderId());      //订单编号
								lvExtendBalanceDetails.setOrderDetailsCode(lvOrderDetails.getCode()); //订单详情code
								lvExtendBalanceDetails.setCouponCode(lvOrderDetails.getCouponCode());//优惠码
								//新增结算详情信息
								dao.save(lvExtendBalanceDetails);
								
								//同步订单信息结算状态
								if(ObjectUtils.isNotEmpty(lvOrderDetails.getOrderId())){
									hql="update LvOrder set isBalanceFirst=0 where oid='"+lvOrderDetails.getOrderId()+"'";
									dao.update(hql, null);
								}
								//添加订单集合
								orderList.add(lvOrderDetails.getOrderId());
							} 
							
							//累计已经结算台数
							Integer settlementedNumTmp=0;
							if (ObjectUtils.isNotEmpty(lvRankfirstInfo.getSettlementedNum())) {
								settlementedNumTmp=pnum+lvRankfirstInfo.getSettlementedNum();
							}else{
								settlementedNumTmp=pnum;
							}
							//累计已经结算金额
							Double settlementedAmountTmp=0.0;
							if(ObjectUtils.isNotEmpty(lvRankfirstInfo.getSettlementedAmount())) {
								settlementedAmountTmp=totalRevenue+lvRankfirstInfo.getSettlementedAmount();
							}else{
								settlementedAmountTmp=totalRevenue;
							}
							//累计可结算台数
							Integer settlementNumTmp=0;
							if (ObjectUtils.isNotEmpty(lvRankfirstInfo.getSettlementNum())) {
								if (lvRankfirstInfo.getSettlementNum()>=(pnum-hisPnum)) {
									settlementNumTmp=lvRankfirstInfo.getSettlementNum()-(pnum-hisPnum);
								}
							}
							//累计可结算金额
							Double settlementAmountTmp=0.0;
							if (ObjectUtils.isNotEmpty(lvRankfirstInfo.getSettlementAmount())) {
								if (lvRankfirstInfo.getSettlementAmount()>=(totalRevenue-hisRevenue)) {
									settlementAmountTmp=lvRankfirstInfo.getSettlementAmount()-(totalRevenue-hisRevenue);
								}
							}
							
		
						   //去处list中订单编号的重复值
						   Iterator it1 = orderList.iterator();
						   Hashtable ht = new Hashtable();
						   while(it1.hasNext()){
							  Object obj = it1.next();
							  ht.put(obj, obj);
					       }
							  
						   Iterator it2 = ht.keySet().iterator();
						   List listTmp = new ArrayList();
						   while(it2.hasNext()){
								listTmp.add(it2.next());
						   }
						   String tmpAids=listTmp.toString().replaceAll("\\[|\\]", "'").replaceAll("\\, ", "','");
							
							//(4)将结算记录保存到数据库中
							LvSettlementLog settlementLog=new LvSettlementLog();
							settlementLog.setAids(tmpAids);
							settlementLog.setClearingTime(new Timestamp(System.currentTimeMillis()));		
							settlementLog.setSettlementPeople(user.getUserName());
							settlementLog.setSettlementedAmount(totalRevenue-hisRevenue);
							settlementLog.setSettlementedNum(pnum-hisPnum);
							settlementLog.setUid(uid);
							settlementLog.setUserType(Short.parseShort(spreaderType.toString()));
							dao.save(settlementLog);
						   
						}
						
				 }//end if
				 else{
					 return false;
				 }

		 }
		 //更新一级级推广商修改记录
		 String hql=" update LvRankfirstInfo set modifyTime=:modifyTime,modifyUserId=:modifyUserId," +
				    " modifyUserName=:modifyUserName where uid=:uid";
				Map mapTmp=new HashMap();
			    mapTmp.put("modifyTime", new Date());
			    mapTmp.put("modifyUserId", user.getId());
			    mapTmp.put("modifyUserName", user.getUserName());
			    mapTmp.put("uid", uid);
		 dao.update(hql,mapTmp);  
		 return true;
	}
	
	
	/**
	 * 二级推广商结算累计算法描述：(主要体现在推广等级累计)
	 * (1)结算台数=本次结算台数+历史结算台数(未退货订单)
	 * (2)结算金额=结算台数累计等级算出结算金额-历史结束台数(为退货订单)等级算出的结算金额
	 *  并且生成结算清单信息和清单详情信息
	 * @Method: saveFirstBalance  
	 * @return void
	 */
	public Boolean savesecondBalance(Integer uidTmp,Integer spreaderType,String curSetTime,BaseUsers user){

		
		String hql=" select lc.uid as uid,lp.email as email,lp.userType as userType," +
		   " lp.specialAmount as specialAmount,lc.couponCode as couponCode \n" +
		   " from LvUserPromoters lp,LvUserCoupon lc \n" +
		   " where lp.uid=lc.uid " +
		   " and lc.uid ="+uidTmp+"" ;

		List list= dao.getMapListByHql(hql, null).getList();
		for(int num=0;num<list.size();num++){
			Map map=(Map) list.get(num);
			LvExtendBalance lvExtendBalance=new LvExtendBalance();
			Integer userType=0;
			double specialAmount=0.0;
			int pnum=0;
			String email="";
			String couponCode="";
			Integer uid=0;
			//推广商用户类型
			if(ObjectUtils.isNotEmpty(map.get("userType"))){
				userType=Integer.parseInt(map.get("userType").toString());
			}
			//返现固定金额
			if(ObjectUtils.isNotEmpty(map.get("specialAmount"))){
				specialAmount=Double.parseDouble(map.get("specialAmount").toString());			
			}
			//推广商id
			if(ObjectUtils.isNotEmpty(uid)){
				uid=Integer.parseInt(map.get("uid").toString());
			}
			//推广商Email
			if(ObjectUtils.isNotEmpty(map.get("email"))){
				email=map.get("email").toString();
			}
			//保存推广商结算信息
			String balanceId = "JS" + System.currentTimeMillis();
			lvExtendBalance.setBalanceId(balanceId);   //结算单号
			lvExtendBalance.setBalanceStatus(0);       //结算状态
			lvExtendBalance.setBalanceTime(new Date());//结算时间
			lvExtendBalance.setUid(uid);
			lvExtendBalance.setUserEmail(email);
			lvExtendBalance.setUserType(spreaderType);//推广商用户类型
			lvExtendBalance.setCode(CodeUtils.getCode());
			lvExtendBalance.setCreateTime(new Date());
			
		   /**
			* 二级推广商结算累计算法描述：(主要体现在推广等级累计)
			* (1)结算台数=本次结算台数+历史结算台数(未退货订单)
			* (2)结算金额=结算台数累计等级算出结算金额-历史结束台数(为退货订单)等级算出的结算金额
			*/
			//优惠码
			if(ObjectUtils.isNotEmpty(map.get("couponCode"))){
				 couponCode=map.get("couponCode").toString();//优惠码
				 List<String> oidList=(List<String>)dao.find("select DISTINCT orderId from LvOrderDetails where couponCode ='"+couponCode+"'",null);
	    		 String oids=oidList.toString().replaceAll("\\[|\\]", "'").replaceAll("\\, ", "','");
	    		 hql="select sum(couponNum) from LvOrder where oid in ("+oids+") and overtime<='"+curSetTime+"' and payStatus =1 and status!=3 and  isdelete=0 and isBalance!=1 ";
	    		 Long tmpNum=(Long)dao.findUnique(hql, null);
	    		 //历史已经结算台数
	    		 hql="select sum(couponNum) from LvOrder where oid in ("+oids+") and overtime<='"+curSetTime+"' and payStatus =1 and status!=3 and isdelete=0 and isBalance=1";
	    		 Long hisNum=(Long)dao.findUnique(hql, null);
				
				
				if(ObjectUtils.isNotEmpty(tmpNum)){
					Integer tmpPnum=0;
					Integer hisPnum=0;
					//累加本次应结算台数
					tmpPnum=tmpNum.intValue();
					//累加历史结算台数
					if(ObjectUtils.isNotEmpty(hisNum)){
						hisPnum=hisNum.intValue();
					}
					
					//计算返现总金额
					Double totalRevenue= this.countIncome(userType,specialAmount,tmpPnum+hisPnum);
					//计算历史结算金额
					Double hisRevenue= this.countIncome(userType,specialAmount,hisPnum);
					
					//新增结算单信息
					lvExtendBalance.setBalanceNum(tmpPnum);                  //结算数量
					lvExtendBalance.setBalancePrice(totalRevenue-hisRevenue);//结算金额
					lvExtendBalance.setCouponCode(couponCode);
					dao.save(lvExtendBalance);
					
					//根据优惠码查询当前的订单详情，保存推广商结算明细信息(不区分套餐和非套餐)
					List<String> orderList=new ArrayList<String>();
					hql=" select ld from LvOrderDetails ld where ld.couponCode='"+couponCode+"'" +
						" and not EXISTS(select lsd from LvExtendBalanceDetails lsd where lsd.orderDetailsCode=ld.code and EXISTS  (select lb.balanceId from LvExtendBalance lb where lb.balanceId=lsd.balanceId and lb.userType="+spreaderType+"))" +
						" and exists(select o from LvOrder o where o.oid=ld.orderId and o.status<>3 and o.overtime<='"+curSetTime+"' and o.isdelete=0 and o.isBalance!=1 and o.payStatus =1)";
					List<LvOrderDetails> detailsList=dao.find(hql, null);
					for(LvOrderDetails lvOrderDetails: detailsList){
						LvExtendBalanceDetails lvExtendBalanceDetails=new LvExtendBalanceDetails();
						lvExtendBalanceDetails.setBalanceId(balanceId);                      //结算单号
						lvExtendBalanceDetails.setOrderId(lvOrderDetails.getOrderId());      //订单编号
						lvExtendBalanceDetails.setOrderDetailsCode(lvOrderDetails.getCode()); //订单详情code
						lvExtendBalanceDetails.setCouponCode(lvOrderDetails.getCouponCode());//优惠码
						//新增结算详情信息
						dao.save(lvExtendBalanceDetails);
						
						//同步订单信息结算状态
						if(ObjectUtils.isNotEmpty(lvOrderDetails.getOrderId())){
							hql="update LvOrder set isBalance=0 where oid='"+lvOrderDetails.getOrderId()+"'";
							dao.update(hql, null);
						}
						//添加订单集合
						orderList.add(lvOrderDetails.getOrderId());
					}

					
					//去处list中订单编号的重复值(用于统计结算的订单数目)
					Iterator it1 = orderList.iterator();
					Hashtable ht = new Hashtable();
					while(it1.hasNext()){
					   Object obj = it1.next();
					   ht.put(obj, obj);
					} 
					Iterator it2 = ht.keySet().iterator();
					List listTmp = new ArrayList();
					while(it2.hasNext()){
						  listTmp.add(it2.next());
					}
					String tmpAids=listTmp.toString().replaceAll("\\[|\\]", "'").replaceAll("\\, ", "','");
					
					
					//更新二级推广商结算统计信息
					int level=0;
					LvUserPromoters promoters=(LvUserPromoters)dao.findUnique("select o from LvUserPromoters o where settlementStatus=1 and uid="+uidTmp+"", null);
					//累计已经结算台数
					Integer settlementedNumTmp=0;
					if (ObjectUtils.isNotEmpty(promoters.getSettlementedNum())) {
						settlementedNumTmp=tmpPnum+promoters.getSettlementedNum();
					}else{
						settlementedNumTmp=tmpPnum;
					}
					//累计已经结算金额
					Double settlementedAmountTmp=0.0;
					if(ObjectUtils.isNotEmpty(promoters.getSettlementedAmount())) {
						settlementedAmountTmp=(totalRevenue-hisRevenue)+promoters.getSettlementedAmount();
					}else{
						settlementedAmountTmp=(totalRevenue-hisRevenue);
					} 
					//累计可结算台数
					Integer settlementNumTmp=0;
					if (ObjectUtils.isNotEmpty(promoters.getSettlementNum())) {
						if (promoters.getSettlementNum()>=tmpPnum) {
							settlementNumTmp=promoters.getSettlementNum()-tmpPnum;
						}
					}
					//累计可结算金额
					Double settlementAmountTmp=0.0;
					if (ObjectUtils.isNotEmpty(promoters.getSettlementAmount())) {
						if (promoters.getSettlementAmount()>=(totalRevenue-hisRevenue)) {
							settlementAmountTmp=promoters.getSettlementAmount()-(totalRevenue-hisRevenue);
						}
					}
					
				    //判断当前用户的VIP等级		
					level=settlementedNumTmp;
					String tmpUserRate="";
					if(promoters.getUserType()!=null&&promoters.getUserType()==1){
						tmpUserRate="VIP6";
					}else{
						 if(level<=10){
						    tmpUserRate="VIP1";
						 }else if(11<=level&&level<=50){
						    tmpUserRate="VIP2";
					     }else if(51<=level&&level<=200){
						    tmpUserRate="VIP3";
						 }else if(201<=level&&level<=1000){
						    tmpUserRate="VIP4";
						 }else{
						    tmpUserRate="VIP5";	
						 }
					}
					   

					//更新二级推广商修改记录
					hql="update LvUserPromoters set " +
					" userRating='"+tmpUserRate+"'," +
					" modifyTime='"+DateUtils.formatDate(new Date(), null) +"'," +
			    	" modifyUserId="+user.getId()+"," +
			    	" modifyUserName='"+user.getUserName()+"'" +
                    " where uid="+uid+"";
			        dao.update(hql,null);
			        
					//添加结算日志
					LvSettlementLog settlementLog=new LvSettlementLog();
					settlementLog.setAids(tmpAids);
					settlementLog.setClearingTime(new Timestamp(System.currentTimeMillis()));		
					settlementLog.setSettlementPeople(user.getUserName());
					settlementLog.setSettlementedAmount(totalRevenue-hisRevenue);
					settlementLog.setSettlementedNum(tmpPnum);
					settlementLog.setUid(uidTmp);
					settlementLog.setUserType(Short.parseShort("2"));
					dao.save(settlementLog);
					
					/**
					 * 广告基金计算
					 */
					LvAdvertiseFund advertiseFund=(LvAdvertiseFund)dao.findUniqueByProperty(LvAdvertiseFund.class, "uid", uid);
					if(advertiseFund!=null)
					{
						advertiseFund.setTotalnum(advertiseFund.getTotalnum()+lvExtendBalance.getBalanceNum());
						advertiseFund.setTotalfund(advertiseFund.getTotalfund()+lvExtendBalance.getBalanceNum()*10.00);
						//查询 已报销的总金额
						String sql="SELECT SUM(fund) FROM LvExpenseFund WHERE uid="+uid;
					    Double fundAll=(Double) dao.findUnique(sql, null);
					    if (fundAll==null||fundAll<0) {
					    	fundAll=0.0;
						}
						advertiseFund.setEnablefund(advertiseFund.getEnablefund()+lvExtendBalance.getBalanceNum()*10.00-fundAll);//计算可报销的广告基金累计
						dao.update(advertiseFund);
					}else{
						advertiseFund=new LvAdvertiseFund();
						advertiseFund.setUid(uid);
						LvUserCoupon userCoupon=(LvUserCoupon)dao.findUniqueByProperty(LvUserCoupon.class, "uid", uid);
						if(userCoupon!=null)
						{
							advertiseFund.setCouponCode(userCoupon.getCouponCode());
						}
						advertiseFund.setTotalnum(lvExtendBalance.getBalanceNum());
						advertiseFund.setTotalfund(pnum*10.00);
						advertiseFund.setEnablefund(pnum*10.00);
						dao.save(advertiseFund);
					}
					
					
				}else {
					return false;
				}
			}
		}
		return true;
	}
	
	
	/**
	 * @Method: exportBalance 
	 * @Description:  [导出结算清单]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-20 下午12:00:11]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-20 下午12:00:11]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public List exportBalance(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.exportOrder() method begin*****");
		}
		// TODO Auto-generated method stub
		List list = new ArrayList();
		List listPage = dao.find("from LvExtendBalance where  id in (" + dto.getAsString("ids") + ")", null);
		if (listPage != null) {
			String[] tempArray = null;
			LvOrder lvOrder = null;
			LvOrderAddress lvOrderAddress = null;
			// 增加表头
			String[] title = new String[7];
			title[0] = "结算单号";
			title[1] = "结算条数";
			title[2] = "结算金额";
			title[3] = "结算时间";
			title[4] = "结算状态";
			title[5] = "优惠码";
			title[6] = "Email";
			list.add(title);
			for (int i = 0; i < listPage.size(); i++) {
				tempArray = new String[7];
				LvExtendBalance lvExtendBalance=(LvExtendBalance) listPage.get(i);
				tempArray[0] = lvExtendBalance.getBalanceId();
				tempArray[1] = lvExtendBalance.getBalanceNum().toString();
				tempArray[2] = lvExtendBalance.getBalancePrice().toString();
				tempArray[3] = lvExtendBalance.getBalanceTime().toString();
				if(lvExtendBalance.getBalanceStatus()==1){
					tempArray[4] = "已结算";
				}else{
					tempArray[4] = "未结算";
				}
				tempArray[5] = lvExtendBalance.getCouponCode();
				tempArray[6] = lvExtendBalance.getUserEmail();
				list.add(tempArray);
			}

		}
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.exportOrder() method end*****");
		}
		return list;
	}
    /**
     * 
     * @Method: exportDetailsBalance 
     * @Description:  [导出结算情况详情(用于财务对账核对)]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-9-27 下午12:03:23]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-9-27 下午12:03:23]   
     * @UpdateRemark: [说明本次修改内容]  
     * @throws
     */
	@Override
	public List exportDetailsBalance(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.exportDetailsBalance() method begin*****");
		}
		List list = new ArrayList();
		String ids=(String) dto.get("ids");
		if (ObjectUtils.isNotEmpty(ids)) {
			String hql="select bd.balanceId as balanceId ,bd.orderId as orderId,o.createTime as createTime,o.overtime as overtime,o.postprice as postprice," +
			" o.totalPrice as totalPrice,ds.productCode as productCode,lp.productName as productName," +
			" ds.pnum as pnum,ds.isPackage as isPackage,bd.couponCode as couponCode,ds.code as orderDetailsCode \n" +
			" from LvExtendBalanceDetails bd ,LvOrderDetails ds,LvOrder o,LvProduct lp \n" +
			" where bd.orderDetailsCode=ds.code \n" +
			" and ds.orderId=o.oid " +
			" and o.payStatus=1" +
			" and ds.productCode=lp.code" +
			" and bd.balanceId in("+ids+") \n";
			
			Pagination pageTmp=dao.getMapListByHql(hql, null);
			List listPage=pageTmp.getList();
			
			if (listPage != null) {
				String[] tempArray = null;
				// 增加表头
				String[] title = new String[9];
				title[0] = "结算单号";
				title[1] = "订单编号";
				title[2] = "下单时间";
				title[3] = "支付时间";
				title[4] = "套餐数";
				title[5] = "订单台数";
				title[6] = "邮件费用";
				title[7] = "订单总金额";
				title[8] = "优惠码";
				list.add(title);
				for (int i = 0; i < listPage.size(); i++) {
					Map map=(Map) listPage.get(i);
					tempArray = new String[9];
					Integer isPackage=(Integer) map.get("isPackage");
					String couponCode=(String) map.get("couponCode");
					String orderId=(String) map.get("orderId");
					String orderDetailsCode=(String) map.get("orderDetailsCode");
					Long tmpNum=0l;
					if(isPackage==1){
						//套餐优惠码统计笔数
						hql="select sum(ld.pnum) as num" +
							" from LvOrderPackageDetails ld where ld.couponCode ='"+couponCode+"'" +
							" and ld.orderDetailsCode='"+orderDetailsCode+"'";
						tmpNum=(Long) dao.findUnique(hql, null);
					}
					
					tempArray[0] = String.valueOf(map.get("balanceId"));//结算单号
					tempArray[1] = String.valueOf(map.get("orderId"));  //订单编号
					if (ObjectUtils.isNotEmpty(map.get("createTime"))) {//下单时间
						tempArray[2] = DateUtils.formatDate(DateUtils.convertToDateTime(map.get("createTime").toString()), "yyyy-MM-dd HH:mm:ss");
					}else{
						tempArray[2]="";	
					}
					if (ObjectUtils.isNotEmpty(map.get("overtime"))) {  //支付时间
						tempArray[3] = DateUtils.formatDate(DateUtils.convertToDateTime(map.get("overtime").toString()), "yyyy-MM-dd HH:mm:ss");
					}else{
						tempArray[3] ="";
					}
					if(isPackage==1){//套餐数，订单台数
						tempArray[4] = String.valueOf(map.get("pnum"));
						tempArray[5] = String.valueOf(tmpNum);
					}else if(isPackage!=1){
						tempArray[4] = "";
						tempArray[5] = String.valueOf(map.get("pnum"));
					}
					tempArray[6] = String.valueOf(map.get("postprice")); //邮件费用
					tempArray[7] = String.valueOf(map.get("totalPrice"));//订单总金额
					tempArray[8] = String.valueOf(map.get("couponCode"));//优惠码
					list.add(tempArray);
				}
			}
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.exportDetailsBalance() method end*****");
		}
		return list;
	}
	/**
	 * 
	 * @Method: countIncome 
	 * @Description:  [计算推广返现的金额]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-2 下午03:42:30]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-2 下午03:42:30]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return double
	 * @throws
	 */
	public double countIncome(Integer userType,Double specialAmount,Integer pnum) {
		double totalRevenue=0;//收益
		if (pnum!=null&&pnum>0) {
			if (userType==1) {//特殊用户
				totalRevenue=pnum*specialAmount;
			}else {//普通用户按照vip登记返现
			long totalpnum=pnum;
			/**
			 * 等级	区间	     返现金额（等级佣金）
			 * VIP1	推广销量1-10台	    15美金
			 * VIP2	推广销量11-50台	    20美金
			 * VIP3	推广销量51-200台	25美金
			 * VIP4	推广销量201-1000台	30美金
			 * VIP5	推广销量1001台以上	35美金
			 * VIP6	黄金合作伙伴	待定，根据具体情况  
			 * 
			 * VIP 1-5设定用户仅能看到自己当前级别和下一级别的返现金额；（避免用户知道高级用户返现的金额，泄漏价格冲击终端消费者）
			 * VIP6 不显示当前级别，也不显示下一级别返现金额；
			 * 
			 * 返现金额计算方式：
			 * 返现金额= 推广台数*所在等级的佣金
			 */
			if (totalpnum>=1&&totalpnum<=10) {
				totalRevenue=pnum*15;
			}else if (totalpnum>=11&&totalpnum<=50) {
				totalRevenue+=10*15+(totalpnum-10)*20;
			}else if (totalpnum>=51&&totalpnum<=200) {
				totalRevenue+=10*15+40*20+(totalpnum-50)*25;
			}else if (totalpnum>=201&&totalpnum<=1000) {
				totalRevenue+=10*15+40*20+150*25+(totalpnum-200)*30;
			}else if (totalpnum>=1001) {
				totalRevenue+=10*15+40*20+150*25+800*30+(totalpnum-1000)*35;
			}	
			}
		}
		return totalRevenue;
    }

	
	/**
	 * ******************************************************************************************
	 * 更新结算清单入口：在未进行财务结算前，更新结算清单(已经存在订单关系且订单关系发生变更的情况)。
	 * ******************************************************************************************
	 */
	@Override
	public Boolean updateStatement(Dto dto) throws ServiceException {
		LvExtendBalance lvExtendBalance=(LvExtendBalance) dto.get("lvExtendBalance");
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		BaseUsers user =(BaseUsers)dto.get("USER");
		String curTime=""; //当前时间
 		String curSetTime=""; //当前可结算时间，即当前时间-15
		
 		//更新当前结算清单
 		String hql="update LvExtendBalance set modifyTime='"+DateUtils.formatDate(new Date(), null) +"'," +
	 		" modifyUserId="+user.getId()+"," +
	 		" modifyUserName='"+user.getUserName()+"'" +
	 		" where id="+lvExtendBalance.getId()+"";
		dao.update(hql, null);
		
 		//当前时间
 		Calendar cal= Calendar.getInstance();
 		curTime=format.format(cal.getTime())+" 00:00:01";
 		//当前可结算时间，即当前时间-15
 		cal.add(Calendar.DATE, -15);
 		curSetTime=format.format(cal.getTime())+" 00:00:01";
 		
 		List<String> orderList=null;
 		if(lvExtendBalance!=null){
 			if(ObjectUtils.isNotEmpty(lvExtendBalance.getUserType())){
 	 			if(lvExtendBalance.getUserType()==1){
 	 				//一级推广商结算
 	 				return this.updateFirstBalance(lvExtendBalance.getUid(),lvExtendBalance.getUserType(),curSetTime,user,lvExtendBalance.getBalanceId());
 	 			}else if(lvExtendBalance.getUserType()==2){
 	 				// 二级推广商结算
 	 				return this.updateSecondBalance(lvExtendBalance.getUid(),lvExtendBalance.getUserType(),curSetTime,user,lvExtendBalance.getBalanceId());
 	 			}
 	 		}
 		}
 		return false;
	}
	
	/**
	 * 
	 * @Method: updateFirstBalance 
	 * @Description:  [更新一级推广商结算清单信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-12-21 上午10:58:51]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-12-21 上午10:58:51]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param uid
	 * @param spreaderType
	 * @param curSetTime
	 * @param user
	 * @param balanceId
	 * @return boolean
	 */
	public boolean updateFirstBalance(Integer uid,Integer spreaderType,String curSetTime,BaseUsers user,String balanceId){
		 //查询一级推广商信息
		 String hql="select o from LvRankfirstInfo o where settlementStatus!=0 and uid="+uid+"";
		 LvRankfirstInfo lvRankfirstInfo = (LvRankfirstInfo)dao.findUnique(hql, null);
		 
		//获取已经优惠码关联的所有订单信息   
		//获取该一级推广商所对应的所有二级推广商uid
		String oids_his="";
		List<Integer> uidList = (List<Integer>) dao.find("select uidSecond from LvPromoterRelate where uidFirst='" + uid+ "'", null);
		if (uidList.size() > 0) {
			String uids = uidList.toString().replaceAll("\\[|\\]", "");
			// 获取所有的二级推广码
			List<String> codeList = (List<String>) dao.find("select couponCode from LvUserCoupon where uid in (" + uids+ ")", null);
			if (codeList != null && codeList.size() > 0) {
				String codes = codeList.toString().replaceAll("\\[|\\]", "'").replaceAll("\\, ", "','");
				if (ObjectUtils.isNotEmpty(codes)) {
					// 根据优惠码统计推广的总台数
					List<String> oidListHis = (List<String>) dao.find("select DISTINCT orderId from LvOrderDetails where couponCode in("+ codes + ")", null);
					oids_his=oidListHis.toString().replaceAll("\\[|\\]", "'").replaceAll("\\, ", "','");
				}
			}
		 }
		 
		 //根据结算单查询订单列表信息
		 hql="select DISTINCT orderId from LvExtendBalanceDetails where balanceId='"+balanceId+"'  ";
		 List<String> oidList=dao.find(hql, null);
		 
		 String oids=oidList.toString().replaceAll("\\[|\\]", "'").replaceAll("\\, ", "','");
		 hql="select sum(couponNum) from LvOrder where oid in ("+oids+") and overtime<='"+curSetTime+"' and payStatus =1 and status!=3 and  isdelete=0 and isBalanceFirst!=1 ";
		 Long tmpNum=(Long)dao.findUnique(hql, null);
		 Integer pnum=0;
		 Integer hisPnum=0;
		 //历史已经结算台数
		 hql="select sum(couponNum) from LvOrder where oid in ("+oids_his+") and overtime<='"+curSetTime+"' and payStatus =1 and status!=3 and isdelete=0 and isBalanceFirst=1";
		 Long hisNum=(Long)dao.findUnique(hql, null);
		 Integer tmpPnum=0;
		 if(ObjectUtils.isNotEmpty(tmpNum)){
			tmpPnum=tmpNum.intValue();
	     }
			
		 //累加历史结算台数
		 Integer hisPnumTmp=0;
		 if(ObjectUtils.isNotEmpty(hisNum)){
			hisPnumTmp=hisNum.intValue();
		 }
		 //已经结算的总台数累加
		 pnum+=(tmpPnum+hisPnumTmp);
		 hisPnum+=hisPnumTmp;
		 
		 if(ObjectUtils.isNotEmpty(pnum)&&pnum>0&&pnum>hisPnum){
			 Double totalRevenue=0.0;
			 Double hisRevenue=0.0;
			//结算金额=结算台数*一级推广商返现金额
			if(ObjectUtils.isNotEmpty(lvRankfirstInfo.getSpecialAmount())){
			   totalRevenue=lvRankfirstInfo.getSpecialAmount()*pnum;
			   hisRevenue=lvRankfirstInfo.getSpecialAmount()*hisPnum;
			   
			   //(1)保存推广商结算信息
			   hql="update LvExtendBalance set balanceNum=:balanceNum,balancePrice=:balancePrice where balanceId=:balanceId";
			   Map param=new HashMap();
			   param.put("balanceNum", pnum-hisPnum);
			   param.put("balancePrice",totalRevenue-hisRevenue );
			   param.put("balanceId", balanceId);
			   dao.update(hql, param);
			 }
			
			//(4)将结算记录保存到数据库中
			LvSettlementLog settlementLog=new LvSettlementLog();
			settlementLog.setAids(oids);
			settlementLog.setClearingTime(new Timestamp(System.currentTimeMillis()));		
			settlementLog.setSettlementPeople(user.getUserName());
			settlementLog.setSettlementedAmount(totalRevenue-hisRevenue);
			settlementLog.setSettlementedNum(pnum-hisPnum);
			settlementLog.setUid(uid);
			settlementLog.setUserType(Short.parseShort(spreaderType.toString()));
			dao.save(settlementLog);
		  }else{
			 return false;
		  }
			
		//更新一级级推广商修改记录
		hql=" update LvRankfirstInfo set modifyTime=:modifyTime,modifyUserId=:modifyUserId," +
			" modifyUserName=:modifyUserName where uid=:uid";
			Map mapTmp=new HashMap();
		    mapTmp.put("modifyTime", new Date());
		    mapTmp.put("modifyUserId", user.getId());
		    mapTmp.put("modifyUserName", user.getUserName());
		    mapTmp.put("uid", uid);
		dao.update(hql,mapTmp);
		return true;
    }
	
	/**
	 * 
	 * @Method: updateSecondBalance 
	 * @Description:  [更新二级推广商结算清单信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-12-21 上午10:59:33]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-12-21 上午10:59:33]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param uid
	 * @param spreaderType
	 * @param curSetTime
	 * @param user
	 * @param balanceId
	 * @return boolean
	 */
	public boolean updateSecondBalance(Integer uid,Integer spreaderType,String curSetTime,BaseUsers user,String balanceId){
		//统计历史该优惠码已经结算的订单台数
		String hql=" select lc.uid as uid,lp.email as email,lp.userType as userType," +
		   " lp.specialAmount as specialAmount,lc.couponCode as couponCode \n" +
		   " from LvUserPromoters lp,LvUserCoupon lc \n" +
		   " where lp.uid=lc.uid " +
		   " and lc.uid ="+uid+"" ;
		List list= dao.getMapListByHql(hql, null).getList();
		Map map=(Map) list.get(0);
		String oids_his="";
		if(ObjectUtils.isNotEmpty(map.get("couponCode"))){
			 String couponCode=map.get("couponCode").toString();//优惠码
			 List<String> oidListHis=(List<String>)dao.find("select DISTINCT orderId from LvOrderDetails where couponCode ='"+couponCode+"'",null);
			 oids_his=oidListHis.toString().replaceAll("\\[|\\]", "'").replaceAll("\\, ", "','");
		}
		
		//更新二级推广商结算统计信息
		int level=0;
		hql="select o from LvUserPromoters o where settlementStatus=1 and uid="+uid+"";
		LvUserPromoters promoters=(LvUserPromoters)dao.findUnique(hql, null);
		
		hql="select DISTINCT orderId from LvExtendBalanceDetails where balanceId='"+balanceId+"'  ";
		List<String> oidList=dao.find(hql, null);
		String oids=oidList.toString().replaceAll("\\[|\\]", "'").replaceAll("\\, ", "','");
		//本次结算台数
		hql="select sum(couponNum) from LvOrder where oid in ("+oids+") and overtime<='"+curSetTime+"' and payStatus =1 and status!=3 and  isdelete=0 and isBalance!=1 ";
		Long tmpNum=(Long)dao.findUnique(hql, null);
		//历史已经结算台数
		hql="select sum(couponNum) from LvOrder where oid in ("+oids_his+") and overtime<='"+curSetTime+"' and payStatus =1 and status!=3 and isdelete=0 and isBalance=1";
		Long hisNum=(Long)dao.findUnique(hql, null);
		 
		if(ObjectUtils.isNotEmpty(tmpNum)){
				Integer tmpPnum=0;
				Integer hisPnum=0;
				//累加本次应结算台数
				tmpPnum=tmpNum.intValue();
				//累加历史结算台数
				if(ObjectUtils.isNotEmpty(hisNum)){
					hisPnum=hisNum.intValue();
				}
				
				//计算返现总金额
				Double totalRevenue= this.countIncome(promoters.getUserType(),promoters.getSpecialAmount(),tmpPnum+hisPnum);
				//计算历史结算金额
				Double hisRevenue= this.countIncome(promoters.getUserType(),promoters.getSpecialAmount(),hisPnum);
				
				//新增结算单信息
				hql="update LvExtendBalance set balanceNum=:balanceNum,balancePrice=:balancePrice where balanceId=:balanceId";
				Map param=new HashMap();
				param.put("balanceNum", tmpPnum);
				param.put("balancePrice",totalRevenue-hisRevenue );
				param.put("balanceId", balanceId);
				dao.update(hql, param);	
				
				//累计已经结算台数
				Integer settlementedNumTmp=0;
				if (ObjectUtils.isNotEmpty(promoters.getSettlementedNum())) {
					settlementedNumTmp=tmpPnum+promoters.getSettlementedNum();
				}else{
					settlementedNumTmp=tmpPnum;
				}
			    //判断当前用户的VIP等级		
				level=settlementedNumTmp;
				String tmpUserRate="";
				if(promoters.getUserType()!=null&&promoters.getUserType()==1){
					tmpUserRate="VIP6";
				}else{
					 if(level<=10){
					    tmpUserRate="VIP1";
					 }else if(11<=level&&level<=50){
					    tmpUserRate="VIP2";
				     }else if(51<=level&&level<=200){
					    tmpUserRate="VIP3";
					 }else if(201<=level&&level<=1000){
					    tmpUserRate="VIP4";
					 }else{
					    tmpUserRate="VIP5";	
					 }
				}
				//更新二级推广商修改记录
				hql=" update LvUserPromoters set userRating=:userRating,modifyTime=:modifyTime,modifyUserId=:modifyUserId," +
					" modifyUserName=:modifyUserName where uid=:uid";
				Map mapTmp=new HashMap();
			    mapTmp.put("userRating", tmpUserRate);
			    mapTmp.put("modifyTime", new Date());
			    mapTmp.put("modifyUserId", user.getId());
			    mapTmp.put("modifyUserName", user.getUserName());
			    mapTmp.put("uid", uid);
				dao.update(hql,mapTmp);

		       
				
				//添加结算日志
				LvSettlementLog settlementLog=new LvSettlementLog();
				settlementLog.setAids(oids);
				settlementLog.setClearingTime(new Timestamp(System.currentTimeMillis()));		
				settlementLog.setSettlementPeople(user.getUserName());
				settlementLog.setSettlementedAmount(totalRevenue-hisRevenue);
				settlementLog.setSettlementedNum(tmpPnum);
				settlementLog.setUid(uid);
				settlementLog.setUserType(Short.parseShort("2"));
				dao.save(settlementLog);	
		 }else{
				return false;
		 }
		return true;
	}
	
	/**
	 * 
	 * @Method: isVersionFailure 
	 * @Description:  [判断最近一次修改是否是有效的版本控制，即是数据否失效]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2013-1-12 下午03:34:25]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2013-1-12 下午03:34:25]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public Boolean isVersionFailure(Dto dto) throws ServiceException {
		//判断最近一次修改是否是有效的版本控制，即是数据否失效。
		Date versionTime=(Date) dto.get("versionTime");
		LvExtendBalance lvExtendBalance=this.get(dto);
		if(ObjectUtils.isEmpty(versionTime)&&ObjectUtils.isNotEmpty(lvExtendBalance.getModifyTime())){
			return false;
		}else if(ObjectUtils.isNotEmpty(versionTime)&&ObjectUtils.isNotEmpty(lvExtendBalance.getModifyTime())){
			if(lvExtendBalance.getModifyTime().getTime()>versionTime.getTime()){
				return false;
			}
		}
		return true;
	}
	
}
