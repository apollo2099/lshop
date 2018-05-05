package com.lshop.manage.settlement.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.lshop.common.pojo.logic.LvAdvertiseFund;
import com.lshop.common.pojo.logic.LvRankfirstInfo;
import com.lshop.common.pojo.logic.LvUserCoupon;
import com.lshop.common.pojo.logic.LvUserPromoters;
import com.lshop.manage.settlement.service.AutomaticClearingService;

public class AutomaticClearingServiceImpl implements AutomaticClearingService {
//	private static final Log logger = LogFactory.getLog(AutomaticClearingServiceImpl.class);
	@Resource
	private HibernateBaseDAO lvlogicReadDao;
	@Resource
	private HibernateBaseDAO lvlogicWriteDao;

	@SuppressWarnings("unchecked")
	@Override 
	public void run() {
		
 		String curTime="2012-09-23 23:23:23"; //当前时间
 		String curSetTime=""; //当前可结算时间，即当前时间-15
 		
 		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 		
 		//当前时间
 		Calendar cal= Calendar.getInstance();
 		curTime=format.format(cal.getTime());
 		
 		//当前可结算时间，即当前时间-15
 		cal.add(Calendar.DATE, -15);
 		curSetTime=format.format(cal.getTime());
 		
		/**
		 * 一级推广商结算
		 */
		List<LvRankfirstInfo> rankFirstInfoList = (List<LvRankfirstInfo>)lvlogicReadDao.find("from LvRankfirstInfo where settlementStatus!=2", null);
		
		if (rankFirstInfoList!=null&&!rankFirstInfoList.isEmpty()) {
        	  for (LvRankfirstInfo  rankFirstInfo: rankFirstInfoList) {
    			 
				  Map timeMap=getTime(rankFirstInfo);
				  String clearSetTime=(String)timeMap.get("clearSetTime"); //上次不可结算时间，即（上次结算时间-15）
				  
				  Map setNumMap=getNum(rankFirstInfo,clearSetTime,curSetTime);
				  double setAmount = (Double)setNumMap.get("totalRevenue");
				  Long setNum = (Long)setNumMap.get("pnum");
				  
				  Map nonSetNumMap=getNum(rankFirstInfo,curSetTime,curTime);
				  double nonSetAmount = (Double)nonSetNumMap.get("totalRevenue");
				  Long nonSetNum = (Long)nonSetNumMap.get("pnum");
			         
        		  String rankHql="update LvRankfirstInfo set settlementAmount=:settlementAmount,\n" +
        		  		" settlementNum=:settlementNum,\n" +
        		  		" nonSettlementAmount=:nonSettlementAmount,\n" +
        		  		" nonSettlementNum=:nonSettlementNum \n" +
        		  		" where uid=:uid \n";
        		  Map rankMap = new HashMap();
        		  rankMap.put("uid", rankFirstInfo.getUid());
    			  rankMap.put("settlementAmount", setAmount);
    			  rankMap.put("settlementNum", setNum.intValue());
    			  rankMap.put("nonSettlementAmount", nonSetAmount);
    			  rankMap.put("nonSettlementNum", nonSetNum.intValue());

				  lvlogicWriteDao.update(rankHql, rankMap);
        	  }
		}
		
		
		
		/**
		 * 二级推广商结算
		 */
         List<LvUserPromoters> list=loadPromoters();
         if (list!=null&&!list.isEmpty()) {
        	  for (LvUserPromoters lvUserPromoters : list) {
        		  Map settlement_now=countIncome(lvUserPromoters,curTime);//当前时间全部收益
        		  Map settlement_15=countIncome(lvUserPromoters,curSetTime);//当前时间可结算收益
        		  
        			double settlementAmount=(Double) settlement_15.get("totalRevenue");
        			Long settlementNum=(Long) settlement_15.get("pnum");
        			double settlementAmountAll=(Double) settlement_now.get("totalRevenue");
        			Long settlementNumAll=(Long) settlement_now.get("pnum");
        			
        			String updatehql="UPDATE LvUserPromoters SET settlementAmount=:settlementAmount,\n" +
        					" settlementNum=:settlementNum,\n" +
        					" nonSettlementAmount=:nonSettlementAmount,\n" +
        					" nonSettlementNum=:nonSettlementNum \n" +
        					" WHERE uid=:uid \n";
        			Map param = new HashMap();
        			param.put("uid", lvUserPromoters.getUid());
        			param.put("settlementAmount", settlementAmount);
        			param.put("settlementNum", settlementNum.intValue());
    				param.put("nonSettlementAmount", settlementAmountAll-settlementAmount);
    				param.put("nonSettlementNum", settlementNumAll.intValue()-settlementNum.intValue());
    				lvlogicWriteDao.update(updatehql, param);
    				
    				/**
    				 * 广告基金计算
    				 */
    				LvAdvertiseFund advertiseFund=(LvAdvertiseFund)lvlogicReadDao.findUniqueByProperty(LvAdvertiseFund.class, "uid", lvUserPromoters.getUid());
    				if(advertiseFund!=null)
    				{
    					advertiseFund.setTotalnum(settlementNumAll.intValue()+lvUserPromoters.getSettlementedNum());
    					advertiseFund.setTotalfund((settlementNumAll.intValue()+lvUserPromoters.getSettlementedNum())*10.00);
    					//查询 已报销的总金额
    					String hql="SELECT SUM(fund) FROM LvExpenseFund WHERE uid="+lvUserPromoters.getUid();
    				    Double fundAll=(Double) lvlogicReadDao.findUnique(hql, null);
    				    if (fundAll==null||fundAll<0) {
    				    	fundAll=0.0;
						}
    					advertiseFund.setEnablefund((settlementNumAll.intValue()+lvUserPromoters.getSettlementedNum())*10.00-fundAll);//计算可报销的广告基金
    					lvlogicWriteDao.update(advertiseFund);
    				}
    				else 
    				{
						advertiseFund=new LvAdvertiseFund();
						advertiseFund.setUid(lvUserPromoters.getUid());
						LvUserCoupon userCoupon=(LvUserCoupon)lvlogicReadDao.findUniqueByProperty(LvUserCoupon.class, "uid", lvUserPromoters.getUid());
						if(userCoupon!=null)
						{
							advertiseFund.setCouponCode(userCoupon.getCouponCode());
						}
						advertiseFund.setTotalnum(settlementNumAll.intValue()+lvUserPromoters.getSettlementedNum());
						advertiseFund.setTotalfund((settlementNumAll.intValue()+lvUserPromoters.getSettlementedNum())*10.00);
						advertiseFund.setEnablefund((settlementNumAll.intValue()+lvUserPromoters.getSettlementedNum())*10.00);
						lvlogicWriteDao.save(advertiseFund);
					}
    				
        	  }
         }
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LvUserPromoters> loadPromoters() {
		String hql = "FROM LvUserPromoters WHERE approvalStatus=1 AND settlementStatus!=2";
		List<LvUserPromoters> objs = lvlogicReadDao.find(hql, null);
		return objs;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map countIncome(LvUserPromoters userPromoters, String date) {
		
		    Map re=new HashMap();
			StringBuilder hql = new StringBuilder("SELECT  sum(LvOrderDetails.pnum) from LvOrder as lvOrder ,LvOrderDetails AS LvOrderDetails,LvUserCoupon AS lvUserCoupon")
			        .append(" WHERE lvUserCoupon.uid=:uid AND LvOrderDetails.couponCode=lvUserCoupon.couponCode  AND  lvOrder.oid=LvOrderDetails.orderId AND  lvOrder.overtime<='")
					.append(date)
					.append("'  AND lvOrder.payStatus =1 ");
			Map param = new HashMap();
			param.put("uid", userPromoters.getUid());
			Long pnum=(Long)lvlogicReadDao.findUnique(hql.toString(), param);
			
			double totalRevenue=0;//收益
			if (pnum!=null&&pnum>0) {
				
				if (userPromoters!=null&&userPromoters.getUserType()!=null&&userPromoters.getUserType()==1) {
					totalRevenue=pnum*userPromoters.getSpecialAmount();
				}else {
				Integer settlementedNum=userPromoters.getSettlementedNum();
				long totalpnum=pnum+settlementedNum;
				if (totalpnum>=1&&totalpnum<=10) {
					totalRevenue=pnum*15;
					
				}else if (totalpnum>=11&&totalpnum<=50) {
					totalRevenue+=10*15+(totalpnum-10)*20;
					if (settlementedNum>=0&&settlementedNum<=9) {
						totalRevenue=totalRevenue-settlementedNum*15;
					}else if (settlementedNum>=10&&settlementedNum<=49) {
						totalRevenue=totalRevenue-10*15-(settlementedNum-10)*20;
					}
					
				}else if (totalpnum>=51&&totalpnum<=200) {
					totalRevenue+=10*15+40*20+(totalpnum-50)*25;
					if (settlementedNum>=0&&settlementedNum<=9) {
						totalRevenue=totalRevenue-settlementedNum*15;
					}else if (settlementedNum>=10&&settlementedNum<=49) {
						totalRevenue=totalRevenue-10*15-(settlementedNum-10)*20;
					}else if (settlementedNum>=50&&settlementedNum<=199) {
						totalRevenue=totalRevenue-10*15-40*20-(settlementedNum-50)*25;
					}
				
				}else if (totalpnum>=201&&totalpnum<=1000) {
					totalRevenue+=10*15+40*20+150*25+(totalpnum-200)*30;
					if (settlementedNum>=0&&settlementedNum<=9) {
						totalRevenue=totalRevenue-settlementedNum*15;
					}else if (settlementedNum>=10&&settlementedNum<=49) {
						totalRevenue=totalRevenue-10*15-(settlementedNum-10)*20;
					}else if (settlementedNum>=50&&settlementedNum<=199) {
						totalRevenue=totalRevenue-10*15-40*20-(settlementedNum-50)*25;
					}else if (settlementedNum>=200&&settlementedNum<=999) {
						totalRevenue=totalRevenue-10*15-40*20-150*25-(settlementedNum-200)*30;
					}
					
				}else if (totalpnum>=1001) {
					totalRevenue+=10*15+40*20+150*25+800*30+(totalpnum-1000)*35;
					if (settlementedNum>=0&&settlementedNum<=9) {
						totalRevenue=totalRevenue-settlementedNum*15;
					}else if (settlementedNum>=10&&settlementedNum<=49) {
						totalRevenue=totalRevenue-10*15-(settlementedNum-10)*20;
					}else if (settlementedNum>=50&&settlementedNum<=199) {
						totalRevenue=totalRevenue-10*15-40*20-(settlementedNum-50)*25;
					}else if (settlementedNum>=200&&settlementedNum<=999) {
						totalRevenue=totalRevenue-10*15-40*20-150*25-(settlementedNum-200)*30;
					}else if (settlementedNum>=1000) {
						totalRevenue=totalRevenue-10*15-40*20-150*25-800*30-(settlementedNum-1000)*35;
					}
				}
				
				}
				
			}else {
				pnum=0l;
			}
			re.put("pnum", pnum);//未结算的数量
			re.put("totalRevenue", totalRevenue);//未结算的金额
		return re;
	}
	
	/**
	 * @description 一级推广商获得可申请、不可申请结算的时间
	 * 可申请的时间：（当前时间-15）至 （上次结算时间-15）
	 * 不可申请的（即未满足15天的）：（当前时间-15）至 当前时间
	 * @version 1.0
	 * @author zhengzue
	 * @createDate 2012/04/20
	 */
	@SuppressWarnings("unchecked")
	public Map getTime(LvRankfirstInfo rankFirstInfo)
	{
		Map re=new HashMap();
		
		String clearTime=""; //上次结算时间
		String clearSetTime=""; //上次不可结算时间，即（上次结算时间-15）
		
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal= Calendar.getInstance();
		
		//上次结算时间
		Map param = new HashMap();
		param.put("uid", rankFirstInfo.getUid());
		Timestamp lastDate = (Timestamp)lvlogicReadDao.findUnique("select max(clearingTime) from LvSettlementLog where uid=:uid and userType=1", param);
		
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
	
		re.put("clearTime", clearTime);
		re.put("clearSetTime", clearSetTime);
	
		return re;
	}

	/**
	 * @description 一级推广商获得可申请、不可申请结算的台数及金额
	 * @version 1.0
	 * @author zhengzue
	 * @createDate 2012/04/20
	 */
	@SuppressWarnings("unchecked")
	public Map getNum(LvRankfirstInfo rankFirstInfo,String startTime,String endTime)
	{
		Map re=new HashMap();
		Long pnum=0L; //推广台数
		double totalRevenue=0; //收益
		
		//赋予初始值
		re.put("pnum", 0L);//台数（可结算or不可结算)
		re.put("totalRevenue", 0.00);//金额(可结算or不可结算）
		
		//获取该一级推广商所对应的所有二级推广商uid
		List<Integer> uidList=(List<Integer>)lvlogicReadDao.find("select uidSecond from LvPromoterRelate where uidFirst='"+rankFirstInfo.getUid()+"'",null);
		 if(uidList.size()<=0)
		 {
			 return re;
		 }
		String uids=uidList.toString().replaceAll("\\[|\\]", "");

		//获取所有的二级推广码
		List<String> codeList=(List<String>)lvlogicReadDao.find("select couponCode from LvUserCoupon where uid in ("+uids+")",null);
		if(codeList.size()<=0)
		{
			return re;
		}
		String codes=codeList.toString().replaceAll("\\[|\\]", "'").replaceAll("\\, ", "','");
		
		//获取所有的订单
		List<String> oidList=(List<String>)lvlogicReadDao.find("select orderId from LvOrderDetails where couponCode in ("+codes+")",null);
		if(oidList.size()<=0)
		{
			return re;
		}
		String oids=oidList.toString().replaceAll("\\[|\\]", "'").replaceAll("\\, ", "','");
		
		//获取推广台数
		String hql="select sum(pnum) from LvOrder where oid in ("+oids+") and overtime<='"+endTime+"' and payStatus =1";
		if(!("").equals(startTime))
		{
			hql+=" and overtime>'"+startTime+"'";
		}
		pnum=(Long)lvlogicReadDao.findUnique(hql, null);

		
		//获取收益
		if (pnum!=null&&pnum>0)
		{
			totalRevenue=pnum*rankFirstInfo.getSpecialAmount();
		}else {
			pnum=0L;
		}
		
		re.put("pnum", pnum);//台数（可结算or不可结算)
		re.put("totalRevenue", totalRevenue);//金额(可结算or不可结算）
		
		return re;
	}
	
	@Override
	public void init() {
		new Thread(this).start();
		
	}

}
