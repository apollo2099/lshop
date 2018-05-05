package com.lshop.manage.lvOrder.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.datastructure.page.SimplePage;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvOrder;
import com.lshop.common.pojo.logic.LvOrderDetails;
import com.lshop.common.util.Constants;
import com.lshop.common.util.DateUtils;
import com.lshop.common.util.StoreHelpUtil;
import com.lshop.common.util.StringUtil;
import com.lshop.manage.lvOrder.service.LvOrderDetailsService;
/**
 * 
 *  
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvOrder.service.impl.LvOrderDetailsServiceImpl.java]  
 * @ClassName:    [LvOrderDetailsServiceImpl]   
 * @Description:  [订单详情管理-数据访问层]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-7-16 下午02:01:34]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-7-16 下午02:01:34]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v1.0] 
 *
 */
@Service("LvOrderDetailsService")
public class LvOrderDetailsServiceImpl implements LvOrderDetailsService{
	private static final Log logger	= LogFactory.getLog(LvOrderDetailsServiceImpl.class);
	@Resource 
	private HibernateBaseDAO dao;
	
	
	@Override
	public List<LvOrderDetails> getOrderDetails(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderDetailsServiceImpl.getOrderDetails() method begin*****");
		}
		String orderId=dto.getAsString("orderId");
		List<LvOrderDetails> list=null;
		if(ObjectUtils.isNotEmpty(orderId)){
			String hql="select o from LvOrderDetails o where o.orderId='"+orderId+"' ";
			list=  (List<LvOrderDetails>) dao.find(hql, null);
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderDetailsServiceImpl.getOrderDetails() method begin*****");
		}
		return list;
	}
	
	
	public List<LvOrderDetails> getOrderDetails(String orderId) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderDetailsServiceImpl.getOrderDetails() method begin*****");
		}
		List<LvOrderDetails> list=null;
		if(ObjectUtils.isNotEmpty(orderId)){
			String hql="select o from LvOrderDetails o where o.orderId='"+orderId+"' ";
			list=  (List<LvOrderDetails>) dao.find(hql, null);
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderDetailsServiceImpl.getOrderDetails() method begin*****");
		}
		return list;
	}
	
	public Pagination getChangeOrderDetails(Dto dto) throws ServiceException{
		String orderId=dto.getAsString("orderId");
		String orderField = dto.getAsString("orderField");
		String orderDirection = dto.getAsString("orderDirection");
		SimplePage page = (SimplePage)dto.get("page");
		if(ObjectUtils.isNotEmpty(orderId)){
			String hql="select o.id as id,o.pnum as pnum ,o.productCode as productCode,p.productName as productName" +
					" from LvOrderDetails o,LvProduct p " +
					" where o.orderId='"+orderId+"' " +
					" and o.productCode=p.code ";
			return dao.getMapListByHql(hql.toString(), page.getPageNum(), page.getNumPerPage(), null);
		}
		return null;
	}
	/**
	 * 
	 * @Method: getOrderDetails 
	 * @Description:  [查询订单详情]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-16 下午02:00:53]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-16 下午02:00:53]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public List getCouponCode(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderDetailsServiceImpl.getOrderDetails() method begin*****");
		}
		String orderId=dto.getAsString("orderId");
		List list=null;
		if(ObjectUtils.isNotEmpty(orderId)){
			String hql="select DISTINCT o.couponCode as  couponCode from LvOrderDetails o where o.orderId='"+orderId+"' ";
			//判断当前是商城入口，还是商家入口
			String storeListString=""; 
			if(ObjectUtils.isNotEmpty(dto.getAsString("flag"))){
				String [] arr=dto.getAsString("flag").split(",");
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
				if(ObjectUtils.isNotEmpty(storeListString)){
					hql+=" and o.storeId in ("+storeListString+")";
				}else{
					String storeList=StringUtil.stringFormart(dto.getAsString("flag"), ",");
					hql+=" and o.storeId in("+storeList+")";
				}
			}
			list=  dao.getMapListByHql(hql, null).getList();
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderDetailsServiceImpl.getOrderDetails() method begin*****");
		}
		return list;
	}
	
	@Override
	public List getDetailsByOid(Dto dto)
			throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderDetailsServiceImpl.getOrderDetails() method begin*****");
		}
		String orderId=dto.getAsString("orderId");
		List list=null;
		if(ObjectUtils.isNotEmpty(orderId)){
			String hql="select DISTINCT o.productCode as productCode ,o.pnum as pnum from LvOrderDetails o where orderId='"+orderId+"' ";
			//判断当前是商城入口，还是商家入口
			String storeListString=""; 
			if(ObjectUtils.isNotEmpty(dto.getAsString("flag"))){
				String [] arr=dto.getAsString("flag").split(",");
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
				if(ObjectUtils.isNotEmpty(storeListString)){
					hql+=" and o.storeId in ("+storeListString+")";
				}else{
					String storeList=StringUtil.stringFormart(dto.getAsString("flag"), ",");
					hql+=" and o.storeId in("+storeList+")";
				}
			}
			list=  dao.getMapListByHql(hql, null).getList();
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderDetailsServiceImpl.getOrderDetails() method begin*****");
		}
		return list;
	}
	/**
	 * 
	 * @Method: getDetailsByCouponCode 
	 * @Description:  [一句话描述该类的功能]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-19 上午11:31:35]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-19 上午11:31:35]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public List<LvOrderDetails> getDetailsByCouponCode(Dto dto)
			throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderDetailsServiceImpl.getOrderDetails() method begin*****");
		}
		String couponCode=dto.getAsString("couponCode");
		List<LvOrderDetails> list=null;
		if(ObjectUtils.isNotEmpty(couponCode)){
			Map<String ,Object> params=new HashMap<String ,Object>();
			StringBuilder hql = new StringBuilder("select o from LvOrderDetails o where couponCode=:couponCode");
			params.put("couponCode", couponCode);
			//判断当前是商城入口，还是商家入口
			hql.append(StoreHelpUtil.getNoShopRelevanceString(dto.getAsString("flag"), "storeId", "o"));
			list=  dao.find(hql.toString(), params);
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderDetailsServiceImpl.getOrderDetails() method begin*****");
		}
		return list;
	}
	
	/**
	 * 
	 * @Method: isExistsByProductCode 
	 * @Description:  [根据商品code和第三方订单号查询商品详情是否存在]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2015-3-25 下午3:15:47]   
	 * @UpdateUser:   [liaoxj]     
	 * @UpdateDate:   [2015-3-25 下午3:15:47]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param productCode 商品编码
	 * @param thirdOrderNum 第三方订单号
	 * @return boolean 
	 */
	public boolean isExistsByProductCode(String productCode,String thirdOrderNum)throws ServiceException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderDetailsServiceImpl.isExistsByProductCode() method begin*****");
		}
		String hql=" from LvOrderDetails os where os.productCode=:productCode " +
				   " and EXISTS(select o from LvOrder o where o.oid=os.orderId and o.isdelete<>-1 and o.thirdOrderNum=:thirdOrderNum)" +
				   " and os.isDelete<>-1 ";
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("productCode", productCode);
		param.put("thirdOrderNum", thirdOrderNum);
		List<LvOrderDetails> list= dao.find(hql, param);
		if(list!=null&&list.size()>0){
			return true;
		}
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderDetailsServiceImpl.isExistsByProductCode() method end*****");
		}
		return false;
	}
	
	/**
	 * 
	 * @Method: save 
	 * @Description:  [新增订单详情]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-18 上午11:03:46]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-18 上午11:03:46]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public void save(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderDetailsServiceImpl.save() method begin*****");
		}
		LvOrderDetails lvOrderDetails=(LvOrderDetails) dto.get("lvOrderDetails");
        dao.save(lvOrderDetails);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderDetailsServiceImpl.save() method end*****");
		}
		
	}
	
	/**
	 * 
	 * @Method: updateStatus 
	 * @Description:  [修改订单详情状态]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2015-3-31 下午5:02:44]   
	 * @UpdateUser:   [liaoxj]     
	 * @UpdateDate:   [2015-3-31 下午5:02:44]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public void updateStatus(String orderId,Integer status,Integer userId,String userName) throws ServiceException {
		String hql="update LvOrderDetails set isDelete=:status, " +
				" modifyUserId=:modifyUserId," +
				" modifyUserName=:modifyUserName," +
				" modifyTime=:modifyTime " +
				" where orderId =:orderId";
		Map<String,Object> param=new HashMap<String, Object>();
		param.put("status", status);
		param.put("modifyUserId", userId);
		param.put("modifyUserName", userName);
		param.put("modifyTime", new Date());
		param.put("orderId", orderId);
		dao.update(hql, param);
	}
	




}
