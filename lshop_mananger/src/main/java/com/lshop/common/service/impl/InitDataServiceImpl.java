package com.lshop.common.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.message.jms.JmsMessageQueueSender;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.cache.LvAreaCache;
import com.lshop.common.pojo.logic.LvExchangeRate;
import com.lshop.common.pojo.logic.LvMallSystem;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.service.InitDataService;
import com.lshop.common.util.Constants;
import com.lshop.common.util.DateUtils;
import com.lshop.manage.lvArea.service.AreaRedisServer;
import com.lshop.manage.lvCoupon.service.CouponRedisServer;

@Lazy(false)
@Service("InitDataService")
public class InitDataServiceImpl implements InitDataService {
	
	@Resource 
	private HibernateBaseDAO dao;
	@Resource
	private JmsMessageQueueSender messageQueueSenderLshopManager;
	@Resource
	private CouponRedisServer couponRedisServer;
	@Resource
	private AreaRedisServer areaRedisServer;
	

	@PostConstruct
	@Override
	public void init() throws Exception {
		initStoreIds();//初始化店铺关联关系
		initRateNumMap();//初始化汇率对应关系
		initMallSystem();//初始化商城体系关系
		initCountryData();//初始化国家区域关系
		sendJSONToWeb();//后台向前端发送MQ消息服务
		couponRedisServer.initCouponCache();//初始化优惠码数据
		couponRedisServer.initActivityCouponCache();//初始话活动和优惠券的关系
		//areaRedisServer.initArea();//初始化区域数据
	}


    /**
     * 
     * @Method: initStoreIds 
     * @Description:  [商城域名和商城与商家的关系初始化数据加载]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2013-1-14 上午10:36:32]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2013-1-14 上午10:36:32]   
     * @UpdateRemark: [说明本次修改内容]   
     * @return void
     */
	@Override
	public Integer initStoreIds() {
		Constants.STORE_IDS.clear();
		Constants.STORE_LIST.clear();
		Constants.STORE_LIST_ALL.clear();
		Constants.STORE_TO_MALL_SYSTEM.clear();
		Constants.STORE_FLAG_TO_IDS.clear();
		Constants.STORE_TO_CURRENCY.clear();
		//局部变量来缓存所有store实例
		String hql = "FROM LvStore WHERE isdel=0";
		List<LvStore> stores = dao.find(hql, null);
		if (ObjectUtils.isEmpty(stores)) {
			return 0;
		}
		for (Iterator<LvStore> iterator = stores.iterator(); iterator.hasNext();) {
			LvStore store = iterator.next();
			//只处理状态为启用的店铺
			if (store.getStatus().intValue() != 1) {
				continue;
			}
			Constants.STORE_IDS.put(store.getDomainName().trim(), store.getStoreFlag().trim());
			if(ObjectUtils.isNotEmpty(store.getMallSystem())){
				Constants.STORE_TO_MALL_SYSTEM.put(store.getStoreFlag().trim(), store.getMallSystem().trim());
			}
			Constants.STORE_FLAG_TO_IDS.put(store.getStoreFlag().trim(), store.getId());

			if(ObjectUtils.isNotEmpty(store.getCurrency())){
				Constants.STORE_TO_CURRENCY.put(store.getStoreFlag().trim(), store.getCurrency().trim());
			}
			if (store.getParentCode().equals("0")) {
				//根据店铺父级关系查询商城下面的店铺信息(只包含启动状态的店铺关联关系)
				List<LvStore> subStores = new ArrayList<LvStore>();
				//根据店铺父级关系查询商城下面的店铺信息
				List<LvStore> subStoresAll = new ArrayList<LvStore>();
				for (Iterator<LvStore> iterator2 = stores.iterator(); iterator2.hasNext();) {
					LvStore ls = iterator2.next();
					if (ls.getStatus().intValue() == 1){
						subStoresAll.add(ls);
						if (ls.getParentCode().equals(store.getCode())) {
							subStores.add(ls);
						}
					}
				}
				List<String> subStoreList = new ArrayList<String>();
				if (ObjectUtils.isNotEmpty(subStores)) {
					for (Iterator<LvStore> iterator2 = subStores.iterator(); iterator2
							.hasNext();) {
						subStoreList.add("'"+iterator2.next().getStoreFlag()+"'");
					}
					Constants.STORE_LIST.put(store.getStoreFlag().trim(), StringUtils.join(subStoreList, ","));
				}
				subStoreList = new ArrayList<String>();
				if (ObjectUtils.isNotEmpty(subStoresAll)) {
					for (Iterator<LvStore> iterator2 = subStoresAll.iterator(); iterator2
							.hasNext();) {
						subStoreList.add("'"+iterator2.next().getStoreFlag()+"'");
					}
					Constants.STORE_LIST_ALL.put(store.getStoreFlag().trim(), StringUtils.join(subStoreList, ","));
				}
			}
		}
		return 1;
	}


	/**
	 * 
	 * @Method: initMallDomainPostfix 
	 * @Description:  [初始化商城体系和商城域名后缀的对应关系]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2013-11-19 下午1:27:09]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2013-11-19 下午1:27:09]   
	 * @UpdateRemark: [说明本次修改内容]   
	 * @return void
	 */
	private void initMallSystem() {
		Constants.MALL_TO_DOMAIN_POSTFIX.clear();
		Constants.MALL_SYSTEM_TO_STORE.clear();
		String hql="from LvMallSystem";
		List<LvMallSystem> listMall= dao.find(hql, null);
		//局部变量来缓存所有store实例
		hql = "FROM LvStore WHERE isdel=0";
		List<LvStore> stores = dao.find(hql, null);
		if(listMall!=null&&listMall.size()>0){
			for (LvMallSystem lvMallSystem : listMall) {
				Constants.MALL_TO_DOMAIN_POSTFIX.put(lvMallSystem.getMallSystemFlag(), lvMallSystem.getDomainPostfix());
				//存储商城体系标志对应商城店铺标志
				List<String> storeFlagList = new ArrayList<String>();
				for (Iterator<LvStore> iterator = stores.iterator(); iterator.hasNext();) {
					LvStore lvStore = iterator.next();
					if (lvMallSystem.getMallSystemFlag().equals(lvStore.getMallSystem())) {
						storeFlagList.add(lvStore.getStoreFlag());
					}
				}
				if (ObjectUtils.isNotEmpty(storeFlagList)) {
					Constants.MALL_SYSTEM_TO_STORE.put(lvMallSystem.getMallSystemFlag(), storeFlagList);
				}
			}
		}
	}
    /**
     * 
     * @Method: initRateNumMap 
     * @Description:  [币种兑换率初始化数据加载]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2013-1-14 上午10:36:32]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2013-1-14 上午10:36:32]   
     * @UpdateRemark: [说明本次修改内容]   
     * @return void
     */
	public void initRateNumMap(){
		String hql="select t from LvExchangeRate t where 1=1";
		List<LvExchangeRate> list=dao.find(hql, null);
		for (LvExchangeRate lvExchangeRate : list) {
			//美元兑人民币汇率处理
			if(lvExchangeRate.getCurrency().equals("CNY")&&lvExchangeRate.getMainCurrency().equals("USD")){
				Constants.rateNum=lvExchangeRate.getExchangeRate();
			}
			//人民币兑美元汇率处理
			if(lvExchangeRate.getCurrency().equals("USD")&&lvExchangeRate.getMainCurrency().equals("CNY")){
				Constants.rateNumCNY=lvExchangeRate.getExchangeRate();
			}
		}
	}
	

	
	/**
	 * 
	 * @Method: initCountryData 
	 * @Description:  [初始化区域信息]    
	 * @return void
	 */
	public void initCountryData() {
		String hql = "FROM LvArea";
		List list = dao.find(hql, null);
		LvAreaCache.setList(list);
	}
	
	
	/**
	 * 
	 * @Method: initActivityInfo 
	 * @Description:  [初始化活动信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-6-30 上午10:03:37]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-6-30 上午10:03:37]   
	 * @UpdateRemark: [说明本次修改内容]   
	 * @return void
	 */
	public void initActivityInfo(Map param){
		//限时活动
		//限量活动
		
		//订单满就送活动
		//点击链接就送活动
		String hql="SELECT t.activityCode as activityCode,t.limitNum as limitNum,t.activityHtml as activityHtml," +
				" t.givenTypeName as givenTypeName,t.givenProductCode as givenProductCode,t.grantTotal as grantTotal," +
				" t.uncollectedTotal as uncollectedTotal,t.givenTypeNum as givenTypeNum,t.code as code ,ac.startTime as startTime ," +
				" ac.endTime as endTime,ac.activityTitle as activityTitle,ac.status as status,t.strategyType as strategyType " +
				" FROM LvActivityLink t, LvActivity ac " +
				" WHERE t.activityCode=ac.code " +
				" and ac.typeKey=4 " +
				" and ac.status=1";
		List list= dao.getMapListByHql(hql, null).getList();
		List<Map<String,Object>>  objList =new ArrayList<Map<String,Object>>();
		for (int i = 0; i < list.size(); i++) {
			Map<String,Object> resutlMap =(Map<String, Object>) list.get(i);
			if(ObjectUtils.isNullOrEmptyString(resutlMap.get("activityCode"))){
				resutlMap.put("activityCode", "");
			}
			if(ObjectUtils.isNullOrEmptyString(resutlMap.get("limitNum"))){
				resutlMap.put("limitNum", "");
			}
			if(ObjectUtils.isNullOrEmptyString(resutlMap.get("activityHtml"))){
				resutlMap.put("activityHtml", "");
			}
			if(ObjectUtils.isNullOrEmptyString(resutlMap.get("givenTypeName"))){
				resutlMap.put("givenTypeName", "");
			}
			if(ObjectUtils.isNullOrEmptyString(resutlMap.get("givenProductCode"))){
				resutlMap.put("givenProductCode", "");
			}
			if(ObjectUtils.isNullOrEmptyString(resutlMap.get("grantTotal"))){
				resutlMap.put("grantTotal", "");
			}
			if(ObjectUtils.isNullOrEmptyString(resutlMap.get("uncollectedTotal"))){
				resutlMap.put("uncollectedTotal", "");
			}
			if(ObjectUtils.isNullOrEmptyString(resutlMap.get("givenTypeNum"))){
				resutlMap.put("givenTypeNum", "");
			}
			if(ObjectUtils.isNullOrEmptyString(resutlMap.get("code"))){
				resutlMap.put("code", "");
			}
			Date startTime=(Date)resutlMap.get("startTime");
			if(ObjectUtils.isNullOrEmptyString(startTime)){
				resutlMap.put("startTime", "");
			}else{
				resutlMap.put("startTime", DateUtils.formatDate(startTime, "yyyy-MM-dd HH:mm:ss"));
			}
			Date endTime=(Date)resutlMap.get("endTime");
			if(ObjectUtils.isNullOrEmptyString(endTime)){
				resutlMap.put("endTime", "");
			}else{
				resutlMap.put("endTime", DateUtils.formatDate(endTime, "yyyy-MM-dd HH:mm:ss"));
			}
			if(ObjectUtils.isNullOrEmptyString(resutlMap.get("activityTitle"))){
				resutlMap.put("activityTitle", "");
			}
			if(ObjectUtils.isNullOrEmptyString(resutlMap.get("status"))){
				resutlMap.put("status", "");
			}
			if(ObjectUtils.isNullOrEmptyString(resutlMap.get("strategyType"))){
				resutlMap.put("strategyType", "");
			}
			objList.add(resutlMap);
		}
	    param.put("ACTIVITYLINKS", objList);	
	}
	
	
	/**
	 * 
	 * @Method: sendJSONToWeb 
	 * @Description:  [后台向前端发送MQ消息内容]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-6-30 上午9:51:26]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-6-30 上午9:51:26]   
	 * @UpdateRemark: [说明本次修改内容]   
	 * @return void
	 */
	protected void sendJSONToWeb() {
		Map param = new HashMap();
		param.put("rateVbNum", Constants.rateVbNum);//汇率信息
		param.put("rateVbNumCny", Constants.rateVbNumCny);//汇率信息
		param.put("rateNum", Constants.rateNum);//汇率信息
		param.put("rateNumCNY", Constants.rateNumCNY);//汇率信息
		param.put("STORE_IDS", Constants.STORE_IDS);
		param.put("STORE_LIST", Constants.STORE_LIST);
		param.put("STORE_LIST_ALL", Constants.STORE_LIST_ALL);
		param.put("STORE_TO_MALL_SYSTEM", Constants.STORE_TO_MALL_SYSTEM);
		param.put("STORE_FLAG_TO_IDS", Constants.STORE_FLAG_TO_IDS);
		param.put("MALL_TO_DOMAIN_POSTFIX", Constants.MALL_TO_DOMAIN_POSTFIX);
		param.put("MALL_SYSTEM_TO_STORE", Constants.MALL_SYSTEM_TO_STORE);
		param.put("STORE_TO_CURRENCY", Constants.STORE_TO_CURRENCY);
		initActivityInfo(param);
		messageQueueSenderLshopManager.sendObject(JSONObject.fromObject(param).toString());//向tvpad前端项目发送消息
	}
}
