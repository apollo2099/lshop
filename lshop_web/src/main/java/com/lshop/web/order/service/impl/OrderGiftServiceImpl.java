package com.lshop.web.order.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import com.gv.core.datastructure.Dto;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvActivityGift;
import com.lshop.web.order.service.OrderGiftService;


/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_web] 
 * @Package:      [com.lshop.web.order.service.impl.OrderGiftServiceImpl.java]  
 * @ClassName:    [OrderGiftServiceImpl]   
 * @Description:  [订单赠品详情-数据库访问层实现]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2015-1-16 下午3:34:40]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2015-1-16 下午3:34:40]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0]
 */
@Service("OrderGiftService")
public class OrderGiftServiceImpl implements OrderGiftService {
	private static final Log logger	= LogFactory.getLog(OrderGiftServiceImpl.class);
	@Resource
	private HibernateBaseDAO lvlogicReadDao;
	
	@Resource
	private HibernateBaseDAO lvlogicWriteDao;
	
	
	/**
	 * 
	 * @Method: findAllByOrderId 
	 * @Description:  [根据订单号查询赠品详情]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-1-8 下午3:38:51]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-1-8 下午3:38:51]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param orderId 订单号
	 * @return List
	 */
	public List findAllByOrderId(String orderId){
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderGiftServiceImpl.findAllByOrderId() method begin*****");
			logger.info("***** LvOrderGiftServiceImpl.findAllByOrderId() 订单号OrderId="+orderId);
		}
		List list=null;
		if(ObjectUtils.isNotEmpty(orderId)){
			String hql="select og.id as id,og.orderId as orderId,og.giftNum as giftNum,pg.giftName as giftName,pg.giftNameEn as giftNameEn," +
					" og.giftCode as giftCode ,pg.pcode as pcode" +
					" from LvOrderGift og,LvPubGift pg where og.giftCode=pg.code" +
					" and og.orderId=:orderId";
			Map<String,Object> param=new HashMap<String,Object>();
			param.put("orderId", orderId);
			list=lvlogicReadDao.getMapListByHql(hql, param).getList();
			
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderGiftServiceImpl.findAllByOrderId() method begin*****");
		}
		return list;
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
		List<Map> resultList=new ArrayList<Map>();
		Map<String,Map> resultMap = new HashMap<String,Map>(); 
		for (int i = 0; i < giftList.size(); i++) {
			Map map=(Map) giftList.get(i);
			String giftCode=(String) map.get("giftCode");
			if(resultMap.containsKey(giftCode)){
				Integer totalNum=0;
				Integer giftNum=0;
				Integer oldGiftNum=0;
				if(ObjectUtils.isNotEmpty(map.get("giftNum"))){
					giftNum=Integer.parseInt(map.get("giftNum").toString());
				}
				if(ObjectUtils.isNotEmpty(resultMap.get(giftCode))){
					Map result=resultMap.get(giftCode);
					if(ObjectUtils.isNotEmpty(result.get("giftNum"))){
						oldGiftNum=Integer.parseInt(result.get("giftNum").toString());
					}
				}
				totalNum=giftNum+oldGiftNum;
				map.put("giftNum", totalNum);
				resultMap.put(giftCode, map); 
			}else{
				resultMap.put(giftCode, map); 
			}
		}
		return new ArrayList(resultMap.values());	
	}

}
