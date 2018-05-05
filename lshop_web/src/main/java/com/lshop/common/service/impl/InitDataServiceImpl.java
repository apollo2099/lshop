package com.lshop.common.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvExchangeRate;
import com.lshop.common.pojo.logic.LvMallSystem;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.service.InitDataService;
import com.lshop.common.util.Constants;

@Lazy(false)
@Service("InitDataService")
public class InitDataServiceImpl implements InitDataService {
	private static final Log logger	= LogFactory.getLog(InitDataServiceImpl.class);
	@Resource 
	private HibernateBaseDAO lvlogicReadDao;

	@PostConstruct
	@Override
	public void init() {
		// TODO Auto-generated method stub
		initStoreIds();
		initRateNumMap();
		initMallSystem();
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
		Constants.DOMAIN_STORE.clear();
		Constants.STORE_LIST.clear();
		Constants.STORE_LIST_ALL.clear();
		Constants.STORE_TO_MALL_SYSTEM.clear();
		Constants.STORE_FLAG_TO_IDS.clear();
		Constants.STORE_TO_CURRENCY.clear();
		Constants.STORE_TO_NAME.clear();
		//局部变量来缓存所有store实例
		String hql = "FROM LvStore WHERE isdel=0";
		List<LvStore> stores = lvlogicReadDao.find(hql, null);
		if (ObjectUtils.isEmpty(stores)) {
			logger.info("没有启用的店铺数据存在");
			return 0;
		}
		for (Iterator<LvStore> iterator = stores.iterator(); iterator.hasNext();) {
			LvStore store = iterator.next();
			//只处理状态为启用的店铺
			if (store.getStatus().intValue() != 1) {
				continue;
			}
			Constants.STORE_IDS.put(store.getDomainName().trim(), store.getStoreFlag().trim());
			Constants.DOMAIN_STORE.put(store.getStoreFlag().trim(), store.getDomainName().trim());
			if(ObjectUtils.isNotEmpty(store.getMallSystem())){
				Constants.STORE_TO_MALL_SYSTEM.put(store.getStoreFlag().trim(), store.getMallSystem().trim());
			}
			Constants.STORE_FLAG_TO_IDS.put(store.getStoreFlag().trim(), store.getId());

			if(ObjectUtils.isNotEmpty(store.getCurrency())){
				Constants.STORE_TO_CURRENCY.put(store.getStoreFlag().trim(), store.getCurrency().trim());
			}
			if(ObjectUtils.isNotEmpty(store.getName())){
				Constants.STORE_TO_NAME.put(store.getStoreFlag().trim(), store.getName().trim());
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
			} else {
				//店铺
				Constants.STORE_IN_USE.add(store.getStoreFlag());
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
		List<LvMallSystem> listMall= lvlogicReadDao.find(hql, null);
		//局部变量来缓存所有store实例
		hql = "FROM LvStore WHERE isdel=0";
		List<LvStore> stores = lvlogicReadDao.find(hql, null);
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
		List<LvExchangeRate> list=lvlogicReadDao.find(hql, null);
		for (LvExchangeRate lvExchangeRate : list) {
			//目前只是处理人民币与美元的兑换
			if(lvExchangeRate.getCurrency().equals("CNY")&&lvExchangeRate.getMainCurrency().equals("USD")){
				Constants.rateNum=lvExchangeRate.getExchangeRate();
			}
			if(lvExchangeRate.getCurrency().equals("USD")&&lvExchangeRate.getMainCurrency().equals("CNY")){
				Constants.rateNumCNY=lvExchangeRate.getExchangeRate();
			}
			//处理V币与美元的兑换
			if(lvExchangeRate.getCurrency().equals("CNY")&&lvExchangeRate.getMainCurrency().equals("VB")){
				Constants.rateVbNumCny=lvExchangeRate.getExchangeRate();
			}
			//处理V币与人民币的兑换
			if(lvExchangeRate.getCurrency().equals("USD")&&lvExchangeRate.getMainCurrency().equals("VB")){
				Constants.rateVbNum=lvExchangeRate.getExchangeRate();
			}
		}
	}
}
