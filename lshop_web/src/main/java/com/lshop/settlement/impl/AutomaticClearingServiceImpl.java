package com.lshop.settlement.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import com.lshop.settlement.AutomaticClearingService;

@Service("AutomaticClearingService")
@Lazy(false)
public class AutomaticClearingServiceImpl implements AutomaticClearingService {
//	private static final Log logger = LogFactory.getLog(AutomaticClearingServiceImpl.class);
	@Resource
	private HibernateBaseDAO lvlogicReadDao;
	@Resource
	private HibernateBaseDAO lvlogicWriteDao;

	@SuppressWarnings("unchecked")
	@Override 
	public void run() {
		
 		String curTime=""; //当前时间
 		String curSetTime=""; //当前可结算时间，即当前时间-15
 		
 		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
 		
 		//当前时间
 		Calendar cal= Calendar.getInstance();
 		curTime=format.format(cal.getTime())+" 00:00:00";
 		
 		//当前可结算时间，即当前时间-15
 		cal.add(Calendar.DATE, -15);
 		curSetTime=format.format(cal.getTime())+" 00:00:00";
 		
		/**
		 * 一级推广商结算
		 */
		List<LvRankfirstInfo> rankFirstInfoList = (List<LvRankfirstInfo>)lvlogicReadDao.find("from LvRankfirstInfo", null);
		
		if (rankFirstInfoList!=null&&!rankFirstInfoList.isEmpty()) {
        	  for (LvRankfirstInfo  rankFirstInfo: rankFirstInfoList) {
    			 
//					  Map timeMap=getTime(rankFirstInfo);
//					  String clearSetTime=(String)timeMap.get("clearSetTime"); //上次不可结算时间，即（上次结算时间-15）
				  
				  Map setNumMap=getNum(rankFirstInfo,"",curSetTime);  //可结算台数：支付时间 <=可结算时间
				  double setAmount = (Double)setNumMap.get("totalRevenue");
				  Long setNum = (Long)setNumMap.get("pnum");
				  
				  Map nonSetNumMap=getNum(rankFirstInfo,curSetTime,curTime); //不可结算台数：可结算时间< 支付时间 <=当前时间
				  double nonSetAmount = (Double)nonSetNumMap.get("totalRevenue");
				  Long nonSetNum = (Long)nonSetNumMap.get("pnum");
			         
        		  String rankHql="update LvRankfirstInfo set settlementAmount=:settlementAmount,settlementNum=:settlementNum,nonSettlementAmount=:nonSettlementAmount,nonSettlementNum=:nonSettlementNum where uid=:uid";
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

        			
        			String updatehql="UPDATE LvUserPromoters SET settlementAmount=:settlementAmount,settlementNum=:settlementNum,nonSettlementAmount=:nonSettlementAmount,nonSettlementNum=:nonSettlementNum WHERE uid=:uid";
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
		String hql = "FROM LvUserPromoters WHERE approvalStatus=1";
		List<LvUserPromoters> objs = lvlogicReadDao.find(hql, null);
		return objs;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map countIncome(LvUserPromoters userPromoters, String date) {
		
		Map re=new HashMap();
		Long pnum=0L; //推广台数
		Long validityPnum=0L; //有效的已结算台数
		double totalRevenue=0; //收益
		
		//赋予初始值
		re.put("pnum", 0L);//台数（可结算or不可结算)
		re.put("totalRevenue", 0.00);//金额(可结算or不可结算）

		//获取所有的二级推广码
		List codeList=lvlogicReadDao.find("select couponCode from LvUserCoupon where uid='"+userPromoters.getUid()+"'",null);
		if(null==codeList||codeList.size()<=0)
		{
			return re;
		}
		String code=codeList.get(0).toString();
		
		//获取所有的订单
		List<String> oidList=(List<String>)lvlogicReadDao.find("select DISTINCT orderId from LvOrderDetails where couponCode='"+code+"'",null);
		if(oidList.size()<=0)
		{
			return re;
		}
		String oids=oidList.toString().replaceAll("\\[|\\]", "'").replaceAll("\\, ", "','");
		
		//获取未结算的推广台数
		String hql="select sum(couponNum) from LvOrder where oid in ("+oids+") and overtime<='"+date+"' and payStatus =1 and status!=3 and isBalance!=1 and isdelete=0";
		pnum=(Long)lvlogicReadDao.findUnique(hql, null);
		
		//获取有效的已结算台数
		String vHql="select sum(couponNum) from LvOrder where oid in ("+oids+") and payStatus =1 and status!=3 and isBalance=1 and isdelete=0";
		validityPnum=(Long)lvlogicReadDao.findUnique(vHql, null);
			
		if (null!=pnum && pnum>0) {
			//特殊二级推广商佣金计算规则
			if (userPromoters!=null&&userPromoters.getUserType()!=null&&userPromoters.getUserType()==1) {
				totalRevenue=pnum*userPromoters.getSpecialAmount();
			}else {
//				Integer settlementedNum=userPromoters.getSettlementedNum();
				long totalPnum=pnum;
				if(null!=validityPnum && validityPnum>0){
					totalPnum+=validityPnum;
				}else{
					validityPnum=0L;
				}
				//普通二级推广商佣金计算规则
				if (totalPnum>=1&&totalPnum<=10) {
					totalRevenue=pnum*15;
				}else if (totalPnum>=11&&totalPnum<=50) {
					totalRevenue+=10*15+(totalPnum-10)*20;
					if (validityPnum>=0&&validityPnum<=9) {
						totalRevenue=totalRevenue-validityPnum*15;
					}else if (validityPnum>=10&&validityPnum<=49) {
						totalRevenue=totalRevenue-10*15-(validityPnum-10)*20;
					}
					
				}else if (totalPnum>=51&&totalPnum<=200) {
					totalRevenue+=10*15+40*20+(totalPnum-50)*25;
					if (validityPnum>=0&&validityPnum<=9) {
						totalRevenue=totalRevenue-validityPnum*15;
					}else if (validityPnum>=10&&validityPnum<=49) {
						totalRevenue=totalRevenue-10*15-(validityPnum-10)*20;
					}else if (validityPnum>=50&&validityPnum<=199) {
						totalRevenue=totalRevenue-10*15-40*20-(validityPnum-50)*25;
					}
				
				}else if (totalPnum>=201&&totalPnum<=1000) {
					totalRevenue+=10*15+40*20+150*25+(totalPnum-200)*30;
					if (validityPnum>=0&&validityPnum<=9) {
						totalRevenue=totalRevenue-validityPnum*15;
					}else if (validityPnum>=10&&validityPnum<=49) {
						totalRevenue=totalRevenue-10*15-(validityPnum-10)*20;
					}else if (validityPnum>=50&&validityPnum<=199) {
						totalRevenue=totalRevenue-10*15-40*20-(validityPnum-50)*25;
					}else if (validityPnum>=200&&validityPnum<=999) {
						totalRevenue=totalRevenue-10*15-40*20-150*25-(validityPnum-200)*30;
					}
					
				}else if (totalPnum>=1001) {
					totalRevenue+=10*15+40*20+150*25+800*30+(totalPnum-1000)*35;
					if (validityPnum>=0&&validityPnum<=9) {
						totalRevenue=totalRevenue-validityPnum*15;
					}else if (validityPnum>=10&&validityPnum<=49) {
						totalRevenue=totalRevenue-10*15-(validityPnum-10)*20;
					}else if (validityPnum>=50&&validityPnum<=199) {
						totalRevenue=totalRevenue-10*15-40*20-(validityPnum-50)*25;
					}else if (validityPnum>=200&&validityPnum<=999) {
						totalRevenue=totalRevenue-10*15-40*20-150*25-(validityPnum-200)*30;
					}else if (validityPnum>=1000) {
						totalRevenue=totalRevenue-10*15-40*20-150*25-800*30-(validityPnum-1000)*35;
					}
				}
			
			}
			
		}else{
			pnum=0L;
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
/**		Map re=new HashMap();
		
		String clearTime=""; //上次结算时间
		String clearSetTime=""; //上次不可结算时间，即（上次结算时间-15）
		
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal= Calendar.getInstance();
		
		//上次结算时间
		Map param = new HashMap();
		param.put("uid", rankFirstInfo.getUid());
		Timestamp lastDate = (Timestamp)lvlogicReadDao.findUnique("select max(balanceTime) from LvExtendBalance where uid=:uid and userType=1 and balanceStatus=1", param);
		
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
*/	
		return null;
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
		List<String> oidList=(List<String>)lvlogicReadDao.find("select DISTINCT orderId from LvOrderDetails where couponCode in ("+codes+")",null);
		if(oidList.size()<=0)
		{
			return re;
		}
		String oids=oidList.toString().replaceAll("\\[|\\]", "'").replaceAll("\\, ", "','");
		
		//获取推广台数
		String hql="select sum(couponNum) from LvOrder where oid in ("+oids+") and overtime<='"+endTime+"' and payStatus =1 and status!=3 and isBalanceFirst!=1 and isdelete=0";
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
