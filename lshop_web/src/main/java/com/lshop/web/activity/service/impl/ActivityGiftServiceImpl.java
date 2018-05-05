package com.lshop.web.activity.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gv.core.datastructure.page.Pagination;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.activity.constant.ActivityConstant;
import com.lshop.common.pojo.logic.LvActivityGift;
import com.lshop.common.pojo.logic.LvActivityProduct;
import com.lshop.web.activity.service.ActivityGiftService;

/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_web] 
 * @Package:      [com.lshop.web.activity.service.impl.ActivityGiftServiceImpl.java]  
 * @ClassName:    [ActivityGiftServiceImpl]   
 * @Description:  [活动赠品服务层]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2015-1-15 上午11:43:30]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2015-1-15 上午11:43:30]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0]
 */
@Service("ActivityGiftService")
public class ActivityGiftServiceImpl implements ActivityGiftService {
	@Resource
	private HibernateBaseDAO lvlogicReadDao;
	
	@Resource
	private HibernateBaseDAO lvlogicWriteDao;
	
	
	
	
	
	/**
	 * 
	 * @Method: findGiftByActivityCode 
	 * @Description:  [根据活动变查询活动赠品信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-2-5 上午11:13:07]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-2-5 上午11:13:07]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param activityCode 活动编号
	 * @return List<LvActivityGift>
	 */
	public List<LvActivityGift> findGiftByActivityCode(String activityCode){
		List<LvActivityGift> list=null;
		if(ObjectUtils.isNotEmpty(activityCode)){
		   String hql="from LvActivityGift ag" +
				   " where ag.activityCode=:activityCode " +
				   " and EXISTS(SELECT 1 FROM LvActivity ac where ac.code=ag.activityCode and ac.status=:status and ac.startTime<=:startTime and ac.endTime>=:endTime)" +
				   " and ag.giftSerplusNum>0 " ;	
			Map<String,Object> param=new HashMap<String, Object>(); 
			param.put("activityCode", activityCode);
			param.put("status", ActivityConstant.STATUS_USE);
			param.put("startTime", new Date());
			param.put("endTime", new Date());
			list=lvlogicReadDao.find(hql, param);
		}
		return list;
	}
	
	/**
	 * 
	 * @Method: findGiftByProductCode 
	 * @Description:  [根据活动变和产品编号查询活动赠品信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-15 上午11:43:50]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-15 上午11:43:50]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param activityCode 活动编号
	 * @param productCode 产品编号
	 * @return List<LvActivityGift>
	 */
	public List findGiftByActivityCode(String activityCode,String productCode){
		List list=null;
		if(ObjectUtils.isNotEmpty(activityCode)&&ObjectUtils.isNotEmpty(productCode)){
		   String hql="select ag.activityCode as activityCode, ag.giftEveryNum as giftEveryNum,ag.giftCode as giftCode," +
		   		   " pg.giftName as giftName,pg.giftNameEn as giftNameEn" +
		   		   " from LvActivityGift ag,LvPubGift pg " +
		   		   " where ag.giftCode=pg.code" +
				   " and ag.activityCode=:activityCode " +
				   " and EXISTS(SELECT 1 FROM LvActivity ac where ac.code=ag.activityCode and ac.status=:status and ac.startTime<=:startTime and ac.endTime>=:endTime)" +
				   " and ag.giftSerplusNum>0 " +
				   " and EXISTS(select 1 from LvProductGift pg where ag.giftCode=pg.giftCode and pg.productCode=:productCode )"; 	
			Map<String,Object> param=new HashMap<String, Object>(); 
			param.put("activityCode", activityCode);
			param.put("status", ActivityConstant.STATUS_USE);
			param.put("startTime", new Date());
			param.put("endTime", new Date());
			param.put("productCode", productCode);
			Pagination page=lvlogicReadDao.getMapListByHql(hql, param);
			list=page.getList();
		}
		return list;
	}
	
	/**
	 * 
	 * @Method: findGiftByProductCode 
	 * @Description:  [一句话描述该类的功能]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-15 下午5:28:38]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-15 下午5:28:38]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param productCode
	 * @return List<LvActivityGift>
	 */
	public LvActivityProduct findActivityProductByProductCode(String activityCode,String productCode){
		LvActivityProduct acProduct=null;
		if(ObjectUtils.isNotEmpty(productCode)){
		  Map<String,Object> param=new HashMap<String, Object>(); 
		  String hql=" From LvActivityProduct ap " +
		  		     " where EXISTS(SELECT 1 FROM LvActivity ac where ac.code=ap.activityCode and ac.status=:status and ac.startTime<=:startTime and ac.endTime>=:endTime)" +
		  		     " and ap.productCode=:productCode" +
		  		     " and ap.activityCode=:activityCode ";
		  param.put("status", ActivityConstant.STATUS_USE);
		  param.put("startTime", new Date());
		  param.put("endTime", new Date());
		  param.put("productCode", productCode);	
		  param.put("activityCode", activityCode);
		  List<LvActivityProduct> productList=lvlogicReadDao.find(hql, param);
		  if(ObjectUtils.isNotEmpty(productList)){
			  acProduct=productList.get(0);
		  }
		}
		return acProduct;
	}
	
	/**
	 * 
	 * @Method: findGiftByProductCode 
	 * @Description:  [一句话描述该类的功能]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-15 下午5:28:38]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-15 下午5:28:38]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param productCode
	 * @return List<LvActivityGift>
	 */
	public List findGiftByProductCode(String productCode){
		List list=new ArrayList();
		if(ObjectUtils.isNotEmpty(productCode)){
		  Map<String,Object> param=new HashMap<String, Object>(); 
		  String hql=" From LvActivityProduct ap " +
		  		     " where EXISTS(SELECT 1 FROM LvActivity ac where ac.code=ap.activityCode and ac.status=:status and ac.startTime<=:startTime and ac.endTime>=:endTime)" +
		  		     " and ap.productCode=:productCode";
		  param.put("status", ActivityConstant.STATUS_USE);
		  param.put("startTime", new Date());
		  param.put("endTime", new Date());
		  param.put("productCode", productCode);	
		  List<LvActivityProduct> productList=lvlogicReadDao.find(hql, param);	
		  
		  if(ObjectUtils.isNotEmpty(productList)){
			  for (LvActivityProduct lvActivityProduct : productList) {
				  List  giftList=  findGiftByActivityCode(lvActivityProduct.getActivityCode(), productCode);
				  if(ObjectUtils.isNotEmpty(giftList)){
					  list.addAll(giftList);
				  }				  
			  }
		  }
		}
		return list;
	}
	
	
	
	/**
	 * 
	 * @Method: findGiftByProductCodeStr 
	 * @Description:  [根据商品code数据查询礼品信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-16 上午10:44:45]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-16 上午10:44:45]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public List findGiftByProductCodeStr(String activityCode,String [] productArr){
		List list=new ArrayList();
		if(ObjectUtils.isNotEmpty(productArr)){
			for (int i = 0; i < productArr.length; i++) {
				 String productCode=productArr[i];
				 //查询符合条件的笔数
				 LvActivityProduct acProduct=findActivityProductByProductCode(activityCode, productCode);
				 if(ObjectUtils.isNotEmpty(acProduct)){
					 List giftList= this.findGiftByActivityCode(activityCode,productCode);
					 list.addAll(giftList);
				 }
			}
		}
		
		//赠品叠加去重复
		List dedupList= giftDeduplication(list);
		return dedupList;
	}
	
	
	/**
	 * 
	 * @Method: giftDeduplication 
	 * @Description:  [赠品信息去重，赠送数量叠加]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-16 上午10:45:12]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-16 上午10:45:12]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param giftList
	 * @return List<LvActivityGift>
	 */
	public List giftDeduplication(List giftList){
		List resultList=new ArrayList();
		Map<String,Map> map = new HashMap<String,Map>(); 
		for (int i = 0; i < giftList.size(); i++) {
			Map giftMap=(Map) giftList.get(i);
			if(ObjectUtils.isNotEmpty(giftMap.get("giftCode"))){
				String giftCode=String.valueOf(giftMap.get("giftCode"));
				if (map.containsKey(giftCode)) { 
					Map giftMapTmp=map.get(giftCode);
					Integer giftNum=0;
					Integer giftNumTmp=0;
					if(ObjectUtils.isNotEmpty(giftMap.get("giftEveryNum"))){
						giftNum=Integer.parseInt(giftMap.get("giftEveryNum").toString());
					}
					if(ObjectUtils.isNotEmpty(giftMapTmp.get("giftEveryNum"))){
						giftNumTmp=Integer.parseInt(giftMapTmp.get("giftEveryNum").toString());
					}
					giftMapTmp.put("giftEveryNum", giftNum+giftNumTmp);
					map.put(giftCode, giftMapTmp); 
				} else { 
					map.put(giftCode, giftMap); 
				} 
			}
		}
		return new ArrayList( map.values());	
	}
	
	
	/**
	 * 
	 * @Method: updateGiftHaveNum 
	 * @Description:  [更新活动赠品领取数目]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-16 下午1:45:52]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-16 下午1:45:52]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param activityCode 活动编码
	 * @param giftCode 赠品编码
	 * @param giftNum 赠送数量
	 * @return void
	 */
	public void updateGiftHaveNum(String activityCode,String giftCode,Integer giftNum){
		String hql="update LvActivityGift set giftHaveNum=giftHaveNum+:giftHaveNum," +
				" giftSerplusNum=giftSerplusNum-:giftSerplusNum " +
				" where activityCode=:activityCode " +
				" and giftCode=:giftCode";
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("giftHaveNum", giftNum);
		param.put("giftSerplusNum", giftNum);
		param.put("activityCode", activityCode);
		param.put("giftCode", giftCode);
	    lvlogicWriteDao.update(hql, param);
	}
	
	
   }




