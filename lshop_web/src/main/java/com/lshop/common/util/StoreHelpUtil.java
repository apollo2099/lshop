package com.lshop.common.util;

import java.util.List;
import java.util.Map;

import com.gv.core.util.ObjectUtils;

/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.common.util.StoreHelpUtil.java]  
 * @ClassName:    [StoreHelpUtil]   
 * @Description:  [一句话描述该类的功能]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2013-5-13 上午09:31:28]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2013-5-13 上午09:31:28]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v3.0]
 */
public class StoreHelpUtil {
	/**
	 * 
	 * @Method: getShopRelevanceString 
	 * @Description:  [根据配置查询商城下的店铺关联关系并且拼接查询脚本(包含商城本身)]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2013-5-10 下午05:23:32]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2013-5-10 下午05:23:32]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param storeFlag 店铺标识
	 * @param fieldName 查询条件列名
	 * @param aliasFlag 别名
	 * @return StringBuilder 返回拼装好的hql
	 */
	public static StringBuilder getShopRelevanceString(String storeFlag,String fieldName,String aliasFlag){
		StringBuilder hql=new StringBuilder();
		//判断当前是商城入口，还是商家入口
		String storeListString=""; 
		if(ObjectUtils.isNotEmpty(storeFlag)){
			String [] arr=storeFlag.split(",");
			String temp="";
			for (int i = 0; i < arr.length; i++) {
				if (ObjectUtils.isNotEmpty(arr[i])) {
					for(Map.Entry<String, String> entry:Constants.STORE_LIST.entrySet()){   
						if(arr[i].trim().equals(entry.getKey())){
						   if(ObjectUtils.isNotEmpty(storeListString)){
							   storeListString+=","+entry.getValue();
						   }else{
							   storeListString+=entry.getValue();
						   }
						}
					}
				}
			}
			String storeList=StringUtil.stringFormart(storeFlag, ",");
			if(ObjectUtils.isNotEmpty(storeListString)){
				hql.append(" and "+aliasFlag.trim()+"."+fieldName.trim()+" in ("+storeListString+","+storeList+")");
			}else{
				hql.append(" and "+aliasFlag.trim()+"."+fieldName.trim()+" in("+storeList+")");
			}
		}
		return hql;
	}
	
	
	/**
	 * 
	 * @Method: getNoShopRelevanceString 
	 * @Description:  [根据配置查询商城下的店铺关联关系并且拼接查询脚本(不包含商城本身)]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2013-5-10 下午05:23:32]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2013-5-10 下午05:23:32]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param storeFlag 店铺标识
	 * @param fieldName 查询条件列名
	 * @param aliasFlag 别名
	 * @return StringBuilder 返回拼装好的hql
	 */
	public static StringBuilder getNoShopRelevanceString(String storeFlag,String fieldName,String aliasFlag){
		StringBuilder hql=new StringBuilder();
		//判断当前是商城入口，还是商家入口
		String storeListString=""; 
		if(ObjectUtils.isNotEmpty(storeFlag)){
			String [] arr=storeFlag.split(",");
			String temp="";
			for (int i = 0; i < arr.length; i++) {
				if (ObjectUtils.isNotEmpty(arr[i])) {
					for(Map.Entry<String, String> entry:Constants.STORE_LIST.entrySet()){   
						if(arr[i].trim().equals(entry.getKey())){
						   if(ObjectUtils.isNotEmpty(storeListString)){
							   storeListString+=","+entry.getValue();
						   }else{
							   storeListString+=entry.getValue();
						   }
						}
					}
				}
			}
			String storeList=StringUtil.stringFormart(storeFlag, ",");
			if(ObjectUtils.isNotEmpty(storeListString)){
				hql.append(" and "+aliasFlag+"."+fieldName.trim()+" in ("+storeListString+")");
			}else{
				hql.append(" and "+aliasFlag+"."+fieldName.trim()+" in("+storeList+")");
			}
		}
		return hql;
	}
	
	
	/**
	 * 
	 * @Method: getStoreRelevanceString 
	 * @Description:  [根据配置查询多个店铺关联并且拼接查询脚本]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2013-5-10 下午05:23:32]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2013-5-10 下午05:23:32]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param storeFlag 店铺标识
	 * @param fieldName 查询字段
	 * @param aliasFlag 别名
	 * @return StringBuilder 返回拼装好的hql
	 */
	public static StringBuilder getStoreRelevanceString(String storeFlag,String fieldName,String aliasFlag){
		StringBuilder hql=new StringBuilder();
		if(ObjectUtils.isNotEmpty(storeFlag)){//店铺标示
			String storeList=StringUtil.stringFormart(storeFlag, ",");
			hql.append(" and "+aliasFlag+"."+fieldName.trim()+" in ("+storeList+")");
		}
		return hql;
	}
	
	
	/**
	 * 
	 * @Method: getMallSystemRelevanceStoreString 
	 * @Description:  [根据商城体系标志查询体系下的商城店铺标志组装hql]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2013-11-19 下午5:01:32]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2013-11-19 下午5:01:32]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param storeFlag 店铺标示
	 * @param fieldName 查询列名
	 * @param aliasFlag 别名
	 * @return StringBuilder
	 */
	public static StringBuilder getMallSystemRelevanceStoreString(String storeFlag,String fieldName,String aliasFlag){
		StringBuilder hql=new StringBuilder();
		if(ObjectUtils.isNotEmpty(storeFlag)){
			//
			for (Map.Entry<String, String> entry : Constants.STORE_TO_MALL_SYSTEM.entrySet()) {
				if (storeFlag.trim().equals(entry.getKey())) {
					//
					for (Map.Entry<String, List<String>> item : Constants.MALL_SYSTEM_TO_STORE.entrySet()) {
						if(entry.getValue().trim().equals(item.getKey())){
							List<String> list=item.getValue();
						    for (int i = 0; i < list.size(); i++) {
								 String temp =list.get(i);
								 if(i==0){
									 hql.append(" and "+aliasFlag+"."+fieldName.trim()+" = '"+temp+"'");
								 }else{
									 hql.append(" or "+aliasFlag+"."+fieldName.trim()+" = '"+temp+"'");
								 }
							}
						}
					}	
				}
			}
		}

		return hql;
	}
	
	
}
