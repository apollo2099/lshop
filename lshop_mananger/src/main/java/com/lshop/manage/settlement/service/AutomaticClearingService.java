package com.lshop.manage.settlement.service;

import java.util.List;
import java.util.Map;
import com.lshop.common.pojo.logic.LvRankfirstInfo;
import com.lshop.common.pojo.logic.LvUserPromoters;

/**
 * 自动汇总每天统计的结算数据
 * @author xusl
 *
 */
public interface AutomaticClearingService extends Runnable{

	/**
	 * 	获取推广者信息
	 * @return
	 */
	public 	List<LvUserPromoters> loadPromoters();
	/**
	 * 计算推广者的收益
	 * @param                 
	 * @return             
	 */
	public Map countIncome(LvUserPromoters userPromoters,String date);
	
	/**
	 * @description 一级推广商获得可申请、不可申请结算的时间
	 * 可申请的时间：（当前时间-15）至 （上次结算时间-15）
	 * 不可申请的（即未满足15天的）：（当前时间-15）至 当前时间
	 * @version 1.0
	 * @author zhengzue
	 * @createDate 2012/04/20
	 */
	public Map getTime(LvRankfirstInfo rankFirstInfo);
	
	/**
	 * @description 一级推广商获得可申请、不可申请结算的台数及金额
	 * @version 1.0
	 * @author zhengzue
	 * @createDate 2012/04/20
	 */
	public Map getNum(LvRankfirstInfo rankFirstInfo,String startTime,String endTime);
	
	public void init();
	
}
