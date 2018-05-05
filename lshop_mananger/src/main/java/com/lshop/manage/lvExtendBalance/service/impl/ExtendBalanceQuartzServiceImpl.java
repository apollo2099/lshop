package com.lshop.manage.lvExtendBalance.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvAdvertiseFund;
import com.lshop.common.pojo.logic.LvExtendBalance;
import com.lshop.common.pojo.logic.LvExtendBalanceDetails;
import com.lshop.common.pojo.logic.LvOrderDetails;
import com.lshop.common.pojo.logic.LvUserCoupon;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.lvExtendBalance.service.ExtendBalanceQuartzService;
import com.lshop.manage.lvExtendBalance.service.LvExtendBalanceDetalsService;
import com.lshop.manage.lvExtendBalance.service.LvExtendBalanceService;

/**
 * 
 *  
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvExtendBalance.service.impl.ExtendBalanceQuartzServiceImpl.java]  
 * @ClassName:    [ExtendBalanceQuartzServiceImpl]   
 * @Description:  [推广商自动结算并且生成结算清单]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-8-2 下午04:40:59]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-8-2 下午04:40:59]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v1.0] 
 *
 */
@Service("ExtendBalanceQuartzService")
public class ExtendBalanceQuartzServiceImpl implements ExtendBalanceQuartzService {
	private static final Log logger	= LogFactory.getLog(ExtendBalanceQuartzServiceImpl.class);
	@Resource 
	private HibernateBaseDAO dao;

	
	
	@Override
	public void run() {
		//二级推广商自动结算(非套餐)
    	//this.statNotPackage();
		//二级推广商自动结算(套餐)
		//this.statIsPackage();
		String hql="select lc.uid as uid,lp.email as email,lp.userType as userType,lp.specialAmount as specialAmount,lc.couponCode as couponCode \n" +
		" from LvUserPromoters lp,LvUserCoupon lc \n" +
		" where lp.id=lc.uid " ;
		List list= dao.getMapListByHql(hql, null).getList();
		for(int num=0;num<list.size();num++){
			Map map=(Map) list.get(num);
			LvExtendBalance lvExtendBalance=new LvExtendBalance();
			Integer userType=0;
			double specialAmount=0.0;
			int pnum=0;
			Integer uid=0;
			String email="";
			String couponCode="";
			//推广商用户类型
			if(ObjectUtils.isNotEmpty(map.get("userType"))){
				userType=Integer.parseInt(map.get("userType").toString());
			}
			//返现固定金额
			if(ObjectUtils.isNotEmpty(map.get("specialAmount"))){
				specialAmount=Double.parseDouble(map.get("specialAmount").toString());			
			}
			//推广商id
			if(ObjectUtils.isNotEmpty(map.get("uid"))){
				uid=Integer.parseInt(map.get("uid").toString());
			}
			//推广商Email
			if(ObjectUtils.isNotEmpty(map.get("email"))){
				email=map.get("email").toString();
			}
			//保存推广商结算信息
			String balanceId = "JS" + System.currentTimeMillis();
			lvExtendBalance.setBalanceId(balanceId);   //结算单号
			lvExtendBalance.setBalanceStatus(1);       //结算状态
			lvExtendBalance.setBalanceTime(new Date());//结算时间
			lvExtendBalance.setUid(uid);
			lvExtendBalance.setUserEmail(email);
			lvExtendBalance.setUserType(2);
			lvExtendBalance.setCode(CodeUtils.getCode());
			lvExtendBalance.setCreateTime(new Date());
			
			//优惠码
			if(ObjectUtils.isNotEmpty(map.get("couponCode"))){
				couponCode=map.get("couponCode").toString();//优惠码
				//非套餐优惠码统计笔数
				hql=" select sum(ld.pnum) as pnum" +
						   " from LvOrderDetails ld where  ld.couponCode='"+couponCode+"'" +
						   " and (ld.isPackage=0 or ld.isPackage is null)" +
						   " and not EXISTS(select lsd from LvExtendBalanceDetails lsd where lsd.orderDetailsCode=ld.code)";
				Long tmpNum=(Long) dao.findUnique(hql, null);
				
				//套餐优惠码统计笔数
				hql="select sum(ld.pnum) as num" +
						" from LvOrderPackageDetails ld where ld.couponCode='"+couponCode+"'" +
						" and orderDetailsCode in( select code from LvOrderDetails where isPackage=1)"+
						" and not EXISTS(select lsd from LvExtendBalanceDetails lsd where lsd.orderDetailsCode=ld.orderDetailsCode)";
				Long tmpNum2=(Long) dao.findUnique(hql, null);
				if(ObjectUtils.isNotEmpty(tmpNum)||ObjectUtils.isNotEmpty(tmpNum2)){
					Integer tmpPnum=0;
					if(ObjectUtils.isEmpty(tmpNum)){
						tmpPnum=tmpNum2.intValue();
					}else if(ObjectUtils.isEmpty(tmpNum2)){
						tmpPnum=tmpNum.intValue();
					}else{
						tmpPnum=tmpNum.intValue()+tmpNum2.intValue();
					}
					//计算返现总金额
					Double totalRevenue= this.countIncome(userType,specialAmount,tmpPnum);
					
					//新增结算单信息
					lvExtendBalance.setBalanceNum(tmpPnum);//结算数量
					lvExtendBalance.setBalancePrice(totalRevenue);//结算金额
					lvExtendBalance.setCouponCode(couponCode);
					dao.save(lvExtendBalance);
					
					//根据优惠码查询当前的订单详情，保存推广商结算明细信息(非套餐)
					hql="select o from LvOrderDetails o where couponCode='"+couponCode+"'" +
							" and not EXISTS(select lsd from LvExtendBalanceDetails lsd where lsd.orderDetailsCode=o.code) ";
					List<LvOrderDetails> detailsList=dao.find(hql, null);
					for(LvOrderDetails lvOrderDetails: detailsList){
						LvExtendBalanceDetails lvExtendBalanceDetails=new LvExtendBalanceDetails();
						lvExtendBalanceDetails.setBalanceId(balanceId);                      //结算单号
						lvExtendBalanceDetails.setOrderId(lvOrderDetails.getOrderId());      //订单编号
						lvExtendBalanceDetails.setOrderDetailsCode(lvOrderDetails.getCode()); //订单详情code
						lvExtendBalanceDetails.setCouponCode(lvOrderDetails.getCouponCode());//优惠码
						dao.save(lvExtendBalanceDetails);
					}
					
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
					}
					else 
					{
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
				}
				
				
				
			}
		}
	}


	@Override
	public void init() {
		new Thread(this).start();	
	}

	public void start(Dto dto)throws ServiceException{
		new Thread(this).start();
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
	
}
