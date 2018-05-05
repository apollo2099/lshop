package com.lshop.manage.lvOrder.service.impl;


import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;

import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.datastructure.page.SimplePage;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.proxy.ServiceConstants;
import com.gv.core.util.ObjectUtils;
import com.gv.core.util.xml.XmlHelper;
import com.lshop.common.cache.LvAreaCache;
import com.lshop.common.payCenter.PayHelper;
import com.lshop.common.payutil.alipay.config.AlipayConfig;
import com.lshop.common.payutil.alipay.services.AlipayService;
import com.lshop.common.payutil.qh.service.QHPayUtils;
import com.lshop.common.payutil.qh.vo.TranckInfoVo;
import com.lshop.common.pojo.logic.LvArea;
import com.lshop.common.pojo.logic.LvCarriageSet;
import com.lshop.common.pojo.logic.LvCoupon;
import com.lshop.common.pojo.logic.LvCouponType;
import com.lshop.common.pojo.logic.LvEmailTpl;
import com.lshop.common.pojo.logic.LvManagerPayLog;
import com.lshop.common.pojo.logic.LvOrder;
import com.lshop.common.pojo.logic.LvOrderActivity;
import com.lshop.common.pojo.logic.LvOrderAddress;
import com.lshop.common.pojo.logic.LvOrderComment;
import com.lshop.common.pojo.logic.LvOrderCoupon;
import com.lshop.common.pojo.logic.LvOrderDetails;
import com.lshop.common.pojo.logic.LvOrderLogs;
import com.lshop.common.pojo.logic.LvOrderMac;
import com.lshop.common.pojo.logic.LvOrderPackageDetails;
import com.lshop.common.pojo.logic.LvPatternCountry;
import com.lshop.common.pojo.logic.LvPayLogs;
import com.lshop.common.pojo.logic.LvPaymentStyle;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.pojo.logic.LvProductComment;
import com.lshop.common.pojo.logic.LvProductPackage;
import com.lshop.common.pojo.logic.LvPubProduct;
import com.lshop.common.pojo.logic.LvShopProductType;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.pojo.logic.LvSwitchOrderLogs;
import com.lshop.common.pojo.user.LvAccount;
import com.lshop.common.util.CodeUtils;
import com.lshop.common.util.Constants;
import com.lshop.common.util.DateUtils;
import com.lshop.common.util.OrderHelp;
import com.lshop.common.util.StoreHelpUtil;
import com.lshop.common.util.StringUtil;
import com.lshop.emailsend.service.EmailSendService;
import com.lshop.manage.lvAccount.service.LvAccountService;
import com.lshop.manage.lvCarriageSet.service.LvCarriageSetService;
import com.lshop.manage.lvOrder.service.LvEmailService;
import com.lshop.manage.lvOrder.service.LvOrderActivityService;
import com.lshop.manage.lvOrder.service.LvOrderAddressService;
import com.lshop.manage.lvOrder.service.LvOrderCouponService;
import com.lshop.manage.lvOrder.service.LvOrderDetailsService;
import com.lshop.manage.lvOrder.service.LvOrderPackageDetailsService;
import com.lshop.manage.lvOrder.service.LvOrderService;
import com.lshop.manage.lvOrder.service.OrderToCreatentService;
import com.lshop.manage.lvOrder.service.OrderToWebService;
import com.lshop.manage.lvOrderMac.service.LvOrderMacService;
import com.lshop.manage.lvPatternCountry.service.LvPatternCountryService;
import com.lshop.manage.lvPaymentStyle.service.LvPaymentStyleService;
import com.lshop.manage.lvProduct.service.LvProductService;
import com.lshop.manage.lvProductPackage.service.LvProductPackageService;
import com.lshop.manage.lvPubProduct.service.LvPubProductService;
import com.lshop.manage.lvStore.service.LvStoreService;
import com.lshop.manage.message.service.OrderMsgService;
import com.lshop.ws.web.bz.order.BzExpressService;
import com.lshop.ws.web.bz.order.ExpressInfo;
import com.lshop.ws.web.bz.order.ExpressResponse;
import com.lshop.ws.web.creatent.order.WSThreeOrderService;

/**
 * 
 *  
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvOrder.service.impl.LvOrderServiceImpl.java]  
 * @ClassName:    [LvOrderServiceImpl]   
 * @Description:  [订单信息管理-数据访问层]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-7-13 上午11:54:03]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-7-13 上午11:54:03]   
 * @UpdateRemark: [说明本次修改内容]    
 * @Version:      [v1.0] 	
 *
 */
@Service("LvOrderService")
public class LvOrderServiceImpl implements LvOrderService {
	private static final Log logger	= LogFactory.getLog(LvOrderServiceImpl.class);
	@Resource 
	private HibernateBaseDAO dao;
	@Resource(name="OrderMsgService")
	private OrderMsgService orderMsgService;//全业务平台接口
	@Resource 
	private LvOrderDetailsService lvOrderDetailsService;
	@Resource
	private LvOrderAddressService lvOrderAddressService;
	@Resource
	private LvProductService lvProductService;
	@Resource
	private LvPubProductService lvPubProductService;
	@Resource
	private LvCarriageSetService lvCarriageSetService;
	@Resource
	private LvOrderPackageDetailsService lvOrderPackageDetailsService;
	@Resource
	private LvProductPackageService lvProductPackageService;
	@Resource
	private LvEmailService lvEmailService;
	@Resource
	private EmailSendService emailSendService;
	@Resource
	private LvStoreService lvStoreService;
	@Resource
	private LvAccountService lvAccountService;
	@Resource 
	private LvPaymentStyleService lvPaymentStyleService;
	@Resource
	private LvOrderCouponService lvOrderCouponService;
	@Resource 
	private LvOrderActivityService lvOrderActivityService;
	@Resource
	private LvOrderCouponService LvOrderCouponService;
	@Resource
	private LvPatternCountryService lvPatternCountryService;//规格和地区关联服务
	@Resource
	private OrderToWebService orderToWebService;//后台发送前台服务
	@Resource
	private OrderToCreatentService orderToCreatentService;//订单发送启创服务
	@Resource
	private WSThreeOrderService WSThreeOrderService;//启创业务平台接口
	@Resource
	private LvOrderMacService lvOrderMacService;//mac订单关联服务
	
	


	/**
     * 
     * @Method: getList 
     * @Description:  [分页查询订单信息列表]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-7-13 上午11:49:32]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-7-13 上午11:49:32]   
     * @UpdateRemark: [说明本次修改内容]  
     * @throws
     */
	@Override
	public Pagination getList(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.getList() method begin*****");
		}
		Integer orderType=(Integer) dto.get("orderType");
		String relName=(String) dto.get("relName");
		String couponCode=(String) dto.get("couponCode");
		String orderField = dto.getAsString("orderField");
		String orderDirection = dto.getAsString("orderDirection");
		SimplePage page = (SimplePage)dto.get("page");
		LvOrder lvOrder=(LvOrder) dto.get("lvOrder");
		LvOrderAddress lvOrderAddress=(LvOrderAddress) dto.get("lvOrderAddress");
		String mac=(String) dto.get("mac");
		Map<String ,Object> params=new HashMap<String ,Object>();
		StringBuilder hql = new StringBuilder(" select o.id as id,o.oid as oid,o.userEmail as userEmail,o.totalPrice as totalPrice," +
				   " o.currency as currency,o.flag as flag,o.paymethod as paymethod,o.status as status,o.payStatus as payStatus," +
				   " o.isServiceAudit as isServiceAudit,o.isFinanceAudit as isFinanceAudit,o.storeId as storeId," +
				   " o.createTime as createTime,o.overtime as overtime,o.sendRemark as sendRemark,a.contryId as contryId," +
				   " o.breakRemark as breakRemark,o.isdelete as isdelete,o.modifyTime as modifyTime,a.relName as relName,o.isSyncSas as isSyncSas " +
				   " from LvOrder o,LvOrderAddress a where a.orderId=o.oid ");

		if(lvOrder!=null){
			if(ObjectUtils.isNotEmpty(lvOrder.getOid())){//订单编号
				hql.append(" and o.oid like :oid");
				params.put("oid", "%"+lvOrder.getOid().trim()+"%");
			}
			if(ObjectUtils.isNotEmpty(lvOrder.getThirdOrderNum())){
				hql.append(" and o.thirdOrderNum like :thirdOrderNum");
				params.put("thirdOrderNum", "%"+lvOrder.getThirdOrderNum().trim()+"%");
			}
			if(ObjectUtils.isNotEmpty(lvOrder.getUserEmail())){//用户Email
				hql.append(" and o.userEmail like :userEmail ");
				params.put("userEmail", "%"+lvOrder.getUserEmail().trim()+"%");
			}
			if(ObjectUtils.isNotEmpty(lvOrder.getStatus())){//订单状态
				hql.append(" and o.status=:status ");
				params.put("status", lvOrder.getStatus());
			}
			if(ObjectUtils.isNotEmpty(lvOrder.getPayStatus())){//支付状态
				hql.append(" and o.payStatus=:payStatus ");
				params.put("payStatus", lvOrder.getPayStatus());
			}
            if(ObjectUtils.isNotEmpty(lvOrder.getFlag())){//订单类型(商家自主下单，用户下单)-菜单配置
            	hql.append(" and o.flag=:flag");
            	params.put("flag", lvOrder.getFlag());
			}
            if(ObjectUtils.isNotEmpty(lvOrder.getStartTime())&&ObjectUtils.isNotEmpty(lvOrder.getEndTime())){//下单时间
            	hql.append(" and o.createTime>:startTime" +
            		 " and o.createTime<:endTime");
            	params.put("startTime", DateUtils.convertToDateTime(lvOrder.getStartTime()+" 00:00:00"));
            	params.put("endTime", DateUtils.convertToDateTime(lvOrder.getEndTime()+" 23:59:59"));
            }
            if(ObjectUtils.isNotEmpty(lvOrder.getStartOverTime())&&ObjectUtils.isNotEmpty(lvOrder.getEndOverTime())){//交付时间
            	hql.append(" and o.overtime>:startOverTime" +
            		 " and o.overtime<:endOverTime");
            	params.put("startOverTime", DateUtils.convertToDateTime(lvOrder.getStartOverTime()+" 00:00:00"));
            	params.put("endOverTime", DateUtils.convertToDateTime(lvOrder.getEndOverTime()+" 23:59:59"));
            }
        
            if(ObjectUtils.isNotEmpty(lvOrder.getIsdelete())){//软删除标志
            	hql.append(" and o.isdelete=:isdelete ");
            	params.put("isdelete", lvOrder.getIsdelete());
            }else{
            	hql.append(" and o.isdelete=0 ");
            }
            if(ObjectUtils.isNotEmpty(lvOrder.getAuditFlag())){//客服和财务审核标志
            	if(lvOrder.getAuditFlag()==0){
            		hql.append(" and ((o.isServiceAudit=0 or o.isServiceAudit is null) or (o.isFinanceAudit=0 or o.isFinanceAudit is null))" +
            			 " and o.isServiceAudit!=-1 and o.isFinanceAudit!=-1 " +
            			 " and (o.status is null or o.status='')");//待审核
            	}else if(lvOrder.getAuditFlag()==1){
            		hql.append(" and (o.isServiceAudit=-1  or o.isFinanceAudit=-1 ))");//未通过审核
            	}
            	else if(lvOrder.getAuditFlag()==2){
            		hql.append(" and o.isServiceAudit=1  and o.isFinanceAudit=1 ");//通过审核
            	}
            }
            if(ObjectUtils.isNotEmpty(lvOrder.getPaymethod())){//支付方式(cod =7)
            	hql.append(" and o.paymethod=:paymethod ");
            	params.put("paymethod", lvOrder.getPaymethod());
            }
            if(ObjectUtils.isNotEmpty(lvOrder.getOrderFlag())){//已发货订单
            	hql.append(" and o.status in(1,2)");
            }
            if(ObjectUtils.isNotEmpty(lvOrder.getIsServiceAudit())){//客服审核状态
            	if (lvOrder.getIsServiceAudit()==0) {
            		hql.append(" and (o.isServiceAudit=:isServiceAudit or o.isServiceAudit is null) and o.isFinanceAudit!=-1 and (o.status is null or o.status='')");
				}else{
					hql.append(" and o.isServiceAudit=:isServiceAudit ");
				}
            	params.put("isServiceAudit", lvOrder.getIsServiceAudit());
            }
            if(ObjectUtils.isNotEmpty(lvOrder.getIsFinanceAudit())){//财务审核状态
            	if (lvOrder.getIsFinanceAudit()==0) {
            		hql.append(" and (o.isFinanceAudit=:isFinanceAudit or o.isFinanceAudit is null) and o.isServiceAudit!=-1 and (o.status is null or o.status='')");
				}else{
					hql.append(" and o.isFinanceAudit=:isFinanceAudit ");
				}
            	params.put("isFinanceAudit", lvOrder.getIsFinanceAudit());
            }
            if(ObjectUtils.isNotEmpty(lvOrder.getIsSyncSas())){
            	hql.append(" and o.isSyncSas=:isSyncSas");
            	params.put("isSyncSas", lvOrder.getIsSyncSas());
            }
            
            if(ObjectUtils.isNotEmpty(lvOrder.getStoreId())){
            	hql.append(" and o.storeId=:storeId ");
            	params.put("storeId", lvOrder.getStoreId());
            }
		}else{
			hql.append(" and o.isdelete=0 ");
		}
	    if (ObjectUtils.isNotEmpty(relName)){//收货人姓名
	    	hql.append(" and a.relName like :relName ");
	    	params.put("relName", "%"+relName.trim()+"%");
        }
	    if (ObjectUtils.isNotEmpty(orderType)) {//订单类型(商家自主下单，用户下单)-查询条件
	    	hql.append(" and o.flag=:orderType ");
	    	params.put("orderType", orderType);
		}
	    if (ObjectUtils.isNotEmpty(dto.get("countryId"))){
	    	hql.append(" and a.contryId=:contryId ");
	    	params.put("contryId", dto.get("countryId"));
	    }
	    if(ObjectUtils.isNotEmpty(couponCode)){//根据优惠码查询订单信息
	    	//根据优惠码查询订单编号
	    	dto.put("couponCode", couponCode);
	    	String tmpOrderId="";
			List<LvOrderCoupon> detailsList= lvOrderCouponService.findDetailsByCouponCode(dto);
			
			for(int i=0;i<detailsList.size();i++){
				LvOrderCoupon orderCoupon=detailsList.get(i);
				if(i==detailsList.size()-1){
					tmpOrderId+="'"+orderCoupon.getOrderId()+"'";
				}else{
					tmpOrderId+="'"+orderCoupon.getOrderId()+"',";
				}
			}
			if(ObjectUtils.isNotEmpty(tmpOrderId)){
				hql.append(" and o.oid in("+tmpOrderId+")");
			}else{//优惠码错误，查询的结果集为空
				Pagination pageTmp=new Pagination();
				pageTmp.setList(new ArrayList());
				return pageTmp;
			}
	    	
	    }
	    if(lvOrderAddress!=null){
	    	if(ObjectUtils.isNotEmpty(lvOrderAddress.getProvinceName())){
	    		hql.append(" and a.provinceName like :provinceName");
	    		params.put("provinceName", "%"+lvOrderAddress.getProvinceName()+"%");
	    	}
	    	if(ObjectUtils.isNotEmpty(dto.get("countryId"))&&ObjectUtils.isNotEmpty(lvOrderAddress.getProvinceId())){
	    		hql.append(" and a.provinceId=:provinceId");
	    		params.put("provinceId", lvOrderAddress.getProvinceId());
	    	}
	    }
	    
	    if(ObjectUtils.isNotEmpty(mac)){
	    	hql.append(" and EXISTS(select 1 from LvOrderMac om where om.orderId=o.oid and om.status<>-1 and om.mac=:mac)");
	    	params.put("mac", mac);
	    }
	    
		//判断当前是商城入口，还是商家入口
		hql.append(StoreHelpUtil.getNoShopRelevanceString(dto.getAsString("flag"), "storeId", "o"));
		//排序
		if(!ObjectUtils.isNullOrEmptyString(orderField)&&!ObjectUtils.isNullOrEmptyString(orderDirection)){
			if (orderField.equals("id")) {
				orderField="createTime";
			}
			hql.append(" order by o."+ orderField+" "+orderDirection);
		}
	
		return  dao.getMapListByHql(hql.toString(), page.getPageNum(), page.getNumPerPage(), params);
	}
	
	
	/**
	 * 
	 * @Method: findUnfilledOrder 
	 * @Description:  [查询所有未发货订单]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2015-2-3 上午10:29:25]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2015-2-3 上午10:29:25]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws ServiceException 
	 * @return List<LvOrder>
	 */
	public List<LvOrder> findUnfilledOrder()throws ServiceException{
		String hql="from LvOrder where status=0 AND payStatus=1 AND isFinanceAudit=1 AND isServiceAudit=1 AND isdelete=0 ";
		List<LvOrder> orderList=dao.find(hql, null);
		return orderList;
	}
	
	/**
	 * 
	 * @Method: get 
	 * @Description:  [根据id查询订单信息详情]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-13 上午11:49:58]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-13 上午11:49:58]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public LvOrder get(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.get() method begin*****");
		}
		LvOrder lvOrder=(LvOrder) dto.get("lvOrder");
		lvOrder=(LvOrder) dao.load(LvOrder.class, lvOrder.getId());
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.get() method end*****");
		}
		return lvOrder;
	}

	/**
	 * 
	 * @Method: getOrder 
	 * @Description:  [根据订单号查询订单详情]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-3 上午10:40:05]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-3 上午10:40:05]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public LvOrder getOrder(Dto dto) throws Exception {
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.get() method begin*****");
		}
		LvOrder lvOrder=(LvOrder) dto.get("lvOrder");
		lvOrder=(LvOrder) dao.findUniqueByProperty(LvOrder.class, "oid", lvOrder.getOid());

		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.get() method end*****");
		}
		return lvOrder;
	}
	
	/**
	 * 
	 * @Method: getOrder 
	 * @Description:  [根据订单号查询订单信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2013-3-27 下午03:01:43]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2013-3-27 下午03:01:43]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public LvOrder getOrder(String oid) throws Exception {
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.get() method begin*****");
		}
		LvOrder lvOrder= (LvOrder) dao.findUniqueByProperty(LvOrder.class, "oid", oid);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.get() method end*****");
		}
		return lvOrder;
	}
	
	/**
	 * 
	 * @Method: getOrderNoDelete 
	 * @Description:  [根据订单号获取订单信息(不包含删除)]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-12-6 上午10:07:52]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-12-6 上午10:07:52]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public LvOrder getOrderNoDelete(Dto dto) throws Exception{
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.get() method begin*****");
		}
		LvOrder lvOrder=(LvOrder) dto.get("lvOrder");
		String hql="from LvOrder where isdelete<>-1 and oid='"+lvOrder.getOid()+"'";
		lvOrder=(LvOrder) dao.findUnique(hql, null);

		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.get() method end*****");
		}
		return lvOrder;
	}

	/**
	 * 
	 * @Method: getOrderByThridNum 
	 * @Description:  [根据第三方订单号查询订单信息]  
	 * @Author:       [liaoxj]     
	 * @CreateDate:   [2015-3-24 下午2:55:36]   
	 * @UpdateUser:   [liaoxj]     
	 * @UpdateDate:   [2015-3-24 下午2:55:36]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public LvOrder getOrderByThridNum(String thirdOrderNum) throws ServiceException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.getOrderByThridNum() method begin*****");
		}
		LvOrder lvOrder=null;
		if(ObjectUtils.isNotEmpty(thirdOrderNum)){
			String hql="from LvOrder where isdelete<>-1 and thirdOrderNum =:thirdOrderNum ";
			Map<String ,Object> param=new HashMap<String,Object>();
			param.put("thirdOrderNum", thirdOrderNum);
			lvOrder=(LvOrder) dao.findUnique(hql, param);	
		}
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.getOrderByThridNum() method end*****");
		}
		return lvOrder;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @Method: delete 
	 * @Description:  [软删除订单信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-16 上午11:20:12]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-16 上午11:20:12]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public void delete(Dto dto) throws Exception {
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.delete() method begin*****");
		}
		//
		LvOrder lvOrder=(LvOrder) dto.get("lvOrder");
		String ids=dto.getAsString("ids");
		BaseUsers users =(BaseUsers)dto.get("users");
		
		//单个删除订单信息
		if(lvOrder!=null){
			if(ObjectUtils.isNotEmpty(lvOrder.getOid())){
				String hql="update LvOrder set isdelete=-1, " +
						" modifyUserId="+users.getId()+"," +
						" modifyUserName='"+users.getUserName()+"'," +
						" modifyTime='"+DateUtils.formatDate(new Date(),null)+"'" +
						" where oid ='"+lvOrder.getOid()+"'";
				dao.update(hql, null);
				
				//修改订单详情状态
				lvOrderDetailsService.updateStatus(lvOrder.getOid(), -1, users.getId(), users.getUserName());

				//后台订单部分添加订单日志信息：
			    this.saveOrderLogs(users.getUserName(), lvOrder.getOid(), "删除订单信息", lvOrder.getStoreId());
			    
			    //删除订单活动关联，恢复订单被暂用的数目(针对订单满就送活动实现)
			    updateActivityLimitOrder(dto, lvOrder.getOid());
			}
		}
		//批量删除订单信息
		if(ObjectUtils.isNotEmpty(ids)){
			String hql="update LvOrder set isdelete=-1, " +
						" modifyUserId="+users.getId()+"," +
						" modifyUserName='"+users.getUserName()+"'," +
						" modifyTime='"+DateUtils.formatDate(new Date(),null)+"'" +
					    " where oid in("+ids+")";
			dao.update(hql, null);
			
			String [] arrTmp=ids.split(",");
			for (int i = 0; i < arrTmp.length; i++) {
				LvOrder order=this.getOrder(arrTmp[i].trim().substring(1, arrTmp[i].trim().length()-1));
				if(ObjectUtils.isNotEmpty(order)){
					//修改订单详情状态
					lvOrderDetailsService.updateStatus(order.getOid(), -1, users.getId(), users.getUserName());
					
					//删除订单活动关联，恢复订单被暂用的数目(针对订单满就送活动实现)
					updateActivityLimitOrder(dto, order.getOid());
					
					//后台订单部分添加订单日志信息：
					this.saveOrderLogs(users.getUserName(), order.getOid(), "删除订单信息", order.getStoreId());
				}
			}
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.delete() method end*****");
		}
	}

	/*
	 * 删除订单活动关联，恢复订单被暂用的数目(针对订单满就送活动实现)
	 */
	private void updateActivityLimitOrder(Dto dto, String orderId) {
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.updateActivityLimitOrder() method begin*****");
		}
		dto.put("orderId",orderId);
		List<LvOrderActivity> list= lvOrderActivityService.findOrderActivityByOrderId(dto);
		for (LvOrderActivity lvOrderActivity : list) {
			//删除订单活动信息
			String hql="delete from LvOrderActivity where id=:id";
			Map<String,Object> param=new HashMap<String, Object>();
			param.put("id", lvOrderActivity.getId());
		    dao.delete(hql, param);
		    //修改活动暂用数目
		    hql=" update from LvActivityLimitOrder set occupiedTotal=occupiedTotal-1,uncollectedTotal=uncollectedTotal+1" +
		    	" where activityCode=:activityCode";
			param.clear();
			param.put("activityCode", lvOrderActivity.getActivityCode());
			dao.update(hql,param);
		}
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.updateActivityLimitOrder() method end*****");
		}
	}

    /**
     * 
     * @Method: receiving 
     * @Description:  [恢复软删除订单信息]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-7-17 下午06:02:32]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-7-17 下午06:02:32]   
     * @UpdateRemark: [说明本次修改内容]  
     * @throws
     */
	public Boolean receiving(Dto dto)throws Exception{
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.delete() method begin*****");
		}
		//判断最近一次修改是否是有效的版本控制，即是数据否失效。
		Date versionTime=(Date) dto.get("versionTime");
		LvOrder lvOrderTmp=this.getOrder(dto);
		if(ObjectUtils.isEmpty(versionTime)&&ObjectUtils.isNotEmpty(lvOrderTmp.getModifyTime())){
			return false;
		}else if(ObjectUtils.isNotEmpty(versionTime)&&ObjectUtils.isNotEmpty(lvOrderTmp.getModifyTime())){
			if(lvOrderTmp.getModifyTime().getTime()>versionTime.getTime()){
				return false;
			}
		}


		//
		LvOrder lvOrder=(LvOrder) dto.get("lvOrder");
		if(lvOrder!=null){
			if(ObjectUtils.isNotEmpty(lvOrder.getOid())){
			  String hql="update LvOrder set isdelete=0, " +
						   " modifyUserId="+lvOrder.getModifyUserId()+"," +
						   " modifyUserName='"+lvOrder.getModifyUserName()+"'," +
						   " modifyTime='"+DateUtils.formatDate(new Date(),null)+"'" +
						   " where oid ='"+lvOrder.getOid()+"'";
     		   dao.update(hql, null);

				//修改订单详情状态
				lvOrderDetailsService.updateStatus(lvOrder.getOid(), 0, lvOrder.getModifyUserId(), lvOrder.getModifyUserName());
			   
			   //后台订单部分添加订单日志信息：
			   this.saveOrderLogs(lvOrder.getModifyUserName(), lvOrder.getOid(), "恢复删除订单信息", lvOrder.getStoreId());
			}
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.delete() method end*****");
		}
		return true;
	}

	/**
	 * 
	 * @Method: update 
	 * @Description:  [修改订单状态和订单总金额]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-16 上午11:20:32]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-16 上午11:20:32]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public void update(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.update() method begin*****");
		}
		
		LvOrder lvOrder=(LvOrder) dto.get("lvOrder");
//		String hql="update LvOrder set status='"+lvOrder.getStatus()+"'," +
//				   " totalPrice="+lvOrder.getTotalPrice()+"," +
//				   " modifyUserId="+lvOrder.getModifyUserId()+"," +
//				   " modifyUserName='"+lvOrder.getModifyUserName()+"'," +
//				   " modifyTime='"+DateUtils.formatDate(lvOrder.getModifyTime(), null) +"'" +
//				   " where oid='"+lvOrder.getOid()+"'";
//		dao.update(hql, null);
//		dao.update(lvOrder);
		
		String hql = "UPDATE LvOrder SET expressName=:expressName," +
		" expressNum=:expressNum," +
		" expressCompany=:expressCompany," +
		" sendRemark=:sendRemark," +
		" status=:status " +
		" WHERE id=:id";
		Map param = new HashMap();
		param.put("expressName", "ss");
		param.put("expressNum", "tt");
		param.put("expressCompany", "ttt");
		param.put("sendRemark", "sss");
		param.put("status", Constants.ORDER_STATUS_1);
		param.put("id", lvOrder.getId());
		dao.update(hql, param);//
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.update() method end*****");
		}
	}
	
	/**
	 * 
	 * 
	 * @throws Exception 
	 * @Method: updateAudit 
	 * @Description:  [订单信息审核(客服审核和财务审核 ==>程序入口]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-17 上午10:57:01]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-17 上午10:57:01]   
	 * @UpdateRemark: [说明本次修改内容]  
	 */
	@Override
	public Integer updateAudit(Dto dto) throws Exception {
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.updateAudit() method begin*****");
		}
		//判断最近一次修改是否是有效的版本控制，即是数据否失效。
		Date versionTime=(Date) dto.get("versionTime");
		LvOrder lvOrderTmp=this.getOrderNoDelete(dto);
		if(lvOrderTmp==null){
			return -1;
		}
		if(ObjectUtils.isEmpty(versionTime)&&ObjectUtils.isNotEmpty(lvOrderTmp.getModifyTime())){
			return 1;
		}else if(ObjectUtils.isNotEmpty(versionTime)&&ObjectUtils.isNotEmpty(lvOrderTmp.getModifyTime())){
			if(lvOrderTmp.getModifyTime().getTime()>versionTime.getTime()){
				return 1;
			}
		}
		
		
		//验证当前订单是否存在需要匹配规格的商品
		Boolean isFlag=false;
		List<LvOrderDetails> detailsList=lvOrderDetailsService.getOrderDetails(lvOrderTmp.getOid());
		if(ObjectUtils.isNotEmpty(detailsList)){
			for (LvOrderDetails lvOrderDetails : detailsList) {
				LvPubProduct pubProduct=lvPubProductService.findByProductCode(lvOrderDetails.getProductCode());
				if(ObjectUtils.isNotEmpty(pubProduct)){
					if(pubProduct.getPcode().contains("@")){
						isFlag=true;
						break;
					}
				}
			}
		}
		
		if(isFlag){
			//根据订单号查询订单收货地址信息
			LvOrderAddress orderAddr=lvOrderAddressService.getOrderAddress(lvOrderTmp.getOid());
			if(ObjectUtils.isNotEmpty(orderAddr)&&ObjectUtils.isNotEmpty(orderAddr.getContryId())){
				//验证当前订单产品是否存在相应的规格信息
				LvPatternCountry lvPatternCountry=lvPatternCountryService.findByCountryId(orderAddr.getContryId());
				if(ObjectUtils.isEmpty(lvPatternCountry)){
					return 2;
				}
			}
		}

		
		
		//修改审核信息
		String serviceAudit="";
		String financeAudit="";
		LvOrder lvOrder=(LvOrder) dto.get("lvOrder");
		//客户审核
		if (ObjectUtils.isNotEmpty(lvOrder.getIsServiceAudit())) {
			String hql="update LvOrder set isServiceAudit='"+lvOrder.getIsServiceAudit()+"'," +
			   " serviceAuditContent='"+lvOrder.getServiceAuditContent()+"'," +
			   " modifyUserId="+lvOrder.getModifyUserId()+"," +
			   " modifyUserName='"+lvOrder.getModifyUserName()+"'," +
			   " modifyTime='"+DateUtils.formatDate(lvOrder.getModifyTime(), null) +"'" +
			   " where oid='"+lvOrder.getOid()+"'";
	           dao.update(hql, null);
	           
	           if(lvOrder.getIsServiceAudit()==Constants.ORDER_AUDIT_STATUS_OK){
	        	   serviceAudit="审核通过";
	           }else if(lvOrder.getIsServiceAudit()==Constants.ORDER_AUDIT_STATUS_NO){
	        	   serviceAudit="审核未通过";
	           }else if(lvOrder.getIsServiceAudit()==Constants.ORDER_AUDIT_STATUS_UN){
	        	   serviceAudit="待审核";
	           }
	           
		}
		
		//财务审核
		if (ObjectUtils.isNotEmpty(lvOrder.getIsFinanceAudit())) {
			String hql="update LvOrder set isFinanceAudit="+lvOrder.getIsFinanceAudit()+"," +
			   " financeAuditContent='"+lvOrder.getFinanceAuditContent()+"'," +
			   " modifyUserId="+lvOrder.getModifyUserId()+"," +
			   " modifyUserName='"+lvOrder.getModifyUserName()+"'," +
			   " modifyTime='"+DateUtils.formatDate(lvOrder.getModifyTime(), null) +"'" +
			   " where oid='"+lvOrder.getOid()+"'";
	           dao.update(hql, null);
	           
	           if(lvOrder.getIsFinanceAudit()==Constants.ORDER_AUDIT_STATUS_OK){
	        	   financeAudit="审核通过";
	           }else if(lvOrder.getIsFinanceAudit()==Constants.ORDER_AUDIT_STATUS_NO){
	        	   financeAudit="审核未通过";
	           }else if(lvOrder.getIsFinanceAudit()==Constants.ORDER_AUDIT_STATUS_UN){
	        	   financeAudit="待审核";
	           }
		}
		
		//修改订单同步到启创默认状态
		this.updateSyncSasFlag(lvOrder.getOid(), (short)0);

		//后台订单部分添加订单日志信息：
		String orderRemark="审核订单;";
		if(ObjectUtils.isNotEmpty(serviceAudit)){
			orderRemark+="客服审核状态："+serviceAudit;
		}
		if(ObjectUtils.isNotEmpty(financeAudit)){
			orderRemark+=",财务审核状态："+financeAudit;
		}
		
		this.saveOrderLogs(lvOrder.getModifyUserName(), lvOrder.getOid(),orderRemark, lvOrder.getStoreId());

		if (logger.isInfoEnabled()) {
			logger.info("***** LvOrderServiceImpl.updateAudit() method end*****");
		}
		return 0;

	}
	
	/**
	 * 
	 * @Method: sendOrderMSGToWMS 
	 * @Description:  [订单发送服务入口实现（华扬->启创）]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-12-17 下午4:06:35]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-12-17 下午4:06:35]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @return
	 * @throws Exception 
	 * @return Boolean
	 */
	public Boolean sendOrderMSGToWMS(Dto dto) throws Exception{
		String storeFlag="";
		String source="tvpadcn";
		if(ObjectUtils.isNotEmpty(dto.getAsString("flag"))){
			storeFlag=dto.getAsString("flag");
			source=dto.getAsString("flag");
		}
		//根据订单编号查询订单详情信息
		LvOrder lvOrder = this.getOrder(dto);
		/**
		 * 1.财务审核和客服审核通过修改订单状态为待发货状态
		 * 2.审核通过发送订单信息到商务平台 
		 */
		if (ObjectUtils.isNotEmpty(lvOrder.getIsServiceAudit())&& ObjectUtils.isNotEmpty(lvOrder.getIsFinanceAudit())) {
			if (lvOrder.getIsServiceAudit() == 1&& lvOrder.getIsFinanceAudit() == 1) {
				// 根据店铺标识查询店铺信息
				dto.put("storeFlag", lvOrder.getStoreId());
				LvStore lvStore = lvStoreService.findStore(dto);
				if (lvStore != null) {
					if (ObjectUtils.isNotEmpty(lvStore.getThirdPartyShippingMark())&& lvStore.getThirdPartyShippingMark() == 1) {
						// 发送订单到商务平台
						if (ObjectUtils.isNotEmpty(lvOrder.getPaymethod())) {
							if (lvOrder.getPaymethod() == Constants.PAY_METHOD_INSTALL) {// 支付方式是否是上门安装，体验后付款
                                //orderMsgCodService.sendOrderMSGToWMS(lvOrder.getOid(), source);// 上门安装
							} else {
								// 不是上门安装
								//orderMsgService.sendOrderMSGToWMS(lvOrder.getOid(), source);// 支付成功發送訂單信息到全業務平臺
							
								Boolean resutlFlag=orderToCreatentService.sendOrderMSGToCreatent(lvOrder.getOid());//支付成功發送訂單信息到启创代理商后台
								//调用支付成功發送訂單信息到启创代理商后台(线程入口程序)
								//this.orderToCreatentTask(lvOrder.getOid());
						        return resutlFlag;
							}
						}
					}
				}
			}
		}
		return true;
	}
	/**
	 * 
	 * @Method: OrderToCreatentTask 
	 * @Description:  [支付成功發送訂單信息到启创代理商后台(线程入口程序)]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-12-25 下午6:11:45]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-12-25 下午6:11:45]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @Version:      [v3.10.2]
	 * @throws
	 */
	@Override
	public void orderToCreatentTask(String orderNum) throws ServiceException {
		//线程包装
		OrderToCreatentThread t = new OrderToCreatentThread(orderNum);
		//创建优惠券线程
		Calendar cal = Calendar.getInstance();
		t.setName("thread-OrderToCreatentTask-"+cal.getTimeInMillis());
		//设置守护线程
		t.setDaemon(true);
		t.start();
	}
	
	/**
	 * 
	 * Simple to Introduction  
	 * @ProjectName:  [lshop_mananger] 
	 * @Package:      [com.lshop.manage.lvOrder.service.impl.LvOrderServiceImpl.java]  
	 * @ClassName:    [OrderToCreatentThread]   
	 * @Description:  [定义内部线程类(支付成功發送訂單信息到启创代理商后台)]   
	 * @Author:       [liaoxiongjian]   
	 * @CreateDate:   [2014-12-25 下午6:24:00]   
	 * @UpdateUser:   [liaoxiongjian]   
	 * @UpdateDate:   [2014-12-25 下午6:24:00]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @Version:      [v3.10.2]
	 */
	class OrderToCreatentThread extends Thread {
		private String orderNum;
		public OrderToCreatentThread(String orderNum) {
			super();
			this.orderNum = orderNum;
		}
		@Override
		public void run() {
			synchronized(this){
				orderToCreatentService.sendOrderMSGToCreatent(orderNum);//支付成功發送訂單信息到启创代理商后台
			}
		}
	}
    /**
     * @Method: updateBreakRemark 
     * @Description:  [退货备注]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-7-17 上午11:00:05]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-7-17 上午11:00:05]   
     * @UpdateRemark: [说明本次修改内容]  
     * @throws
     */
	@Override
	public Integer updateBreakRemark(Dto dto) throws Exception {
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.updateBreakRemark() method begin*****");
		}
		//判断最近一次修改是否是有效的版本控制，即是数据否失效。
		Date versionTime=(Date) dto.get("versionTime");
		LvOrder lvOrderTmp=this.getOrderNoDelete(dto);
		if(lvOrderTmp==null){
			return -1;
		}
		if(ObjectUtils.isEmpty(versionTime)&&ObjectUtils.isNotEmpty(lvOrderTmp.getModifyTime())){
			return 1;
		}else if(ObjectUtils.isNotEmpty(versionTime)&&ObjectUtils.isNotEmpty(lvOrderTmp.getModifyTime())){
			if(lvOrderTmp.getModifyTime().getTime()>versionTime.getTime()){
				return 1;
			}
		}
		
		//修改订单退货备注信息
		LvOrder lvOrder=(LvOrder) dto.get("lvOrder");
		String hql="update LvOrder set breakRemark='"+lvOrder.getBreakRemark()+"'," +
				   " status="+lvOrder.getStatus()+" ," +
				   " modifyUserId="+lvOrder.getModifyUserId()+"," +
				   " modifyUserName='"+lvOrder.getModifyUserName()+"'," +
				   " modifyTime='"+DateUtils.formatDate(lvOrder.getModifyTime(), null) +"'" +
				   " where oid='"+lvOrder.getOid()+"'";
		dao.update(hql, null);
		
		//后台订单部分添加订单日志信息：
		this.saveOrderLogs(lvOrder.getModifyUserName(), lvOrder.getOid(), "退货订单", lvOrder.getStoreId());
		
		//调用启创同步销售订单接口
		WSThreeOrderService.updateOrderStatus(lvOrder.getOid(), Integer.parseInt(lvOrder.getStatus().toString()));
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.updateBreakRemark() method end*****");
		}
		return 0;
		
	}

	/**
	 * 
	 * @Method: updateSendRemark 
	 * @Description:  [发货备注]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-17 上午11:00:12]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-17 上午11:00:12]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Integer updateSendRemark(Dto dto) throws Exception {
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.updateSendRemark() method begin*****");
		}
		//判断最近一次修改是否是有效的版本控制，即是数据否失效。
		Date versionTime=(Date) dto.get("versionTime");
		LvOrder lvOrderTmp=this.getOrderNoDelete(dto);
		if(lvOrderTmp==null){
			return -1;
		}
		if(ObjectUtils.isEmpty(versionTime)&&ObjectUtils.isNotEmpty(lvOrderTmp.getModifyTime())){
			return 1;
		}else if(ObjectUtils.isNotEmpty(versionTime)&&ObjectUtils.isNotEmpty(lvOrderTmp.getModifyTime())){
			if(lvOrderTmp.getModifyTime().getTime()>versionTime.getTime()){
				return 1;
			}
		}
		//修改订单发货备注信息
		LvOrder lvOrder=(LvOrder) dto.get("lvOrder");
		String hql="update LvOrder set sendRemark='"+lvOrder.getSendRemark()+"'," +
				   " modifyUserId="+lvOrder.getModifyUserId()+"," +
				   " modifyUserName='"+lvOrder.getModifyUserName()+"'," +
				   " modifyTime='"+DateUtils.formatDate(lvOrder.getModifyTime(), null) +"'" +
				   " where oid='"+lvOrder.getOid()+"'";
		dao.update(hql, null);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.updateSendRemark() method end*****");
		}
		return 0;
		
	}
	/**
	 * @throws Exception 
	 * 
	 * @Method: updateStatus 
	 * @Description:  [修改订单状态]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-17 下午04:36:00]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-17 下午04:36:00]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Integer updateStatus(Dto dto) throws Exception {
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.updateStatus() method begin*****");
		}
		//判断最近一次修改是否是有效的版本控制，即是数据否失效。
		Date versionTime=(Date) dto.get("versionTime");
		LvOrder lvOrderTmp=this.getOrderNoDelete(dto);
		if(lvOrderTmp==null){
			return -1;
		}
		if(ObjectUtils.isEmpty(versionTime)&&ObjectUtils.isNotEmpty(lvOrderTmp.getModifyTime())){
			return 1;
		}else if(ObjectUtils.isNotEmpty(versionTime)&&ObjectUtils.isNotEmpty(lvOrderTmp.getModifyTime())){
			if(lvOrderTmp.getModifyTime().getTime()>versionTime.getTime()){
				return 1;
			}
		}
		
		
		
		Short oldStatus=(Short) dto.get("oldStatus");
		Short oldPayStatus=(Short) dto.get("oldPayStatus");
		LvOrder lvOrder=(LvOrder) dto.get("lvOrder");
		if (ObjectUtils.isNotEmpty(lvOrder.getPayStatus())) {//后台修改发货状态和支付状态入库
			String hql="";
			
			if(ObjectUtils.isNotEmpty(lvOrder.getPayStatus())&&lvOrder.getPayStatus()==1&&lvOrder.getPayStatus()!=oldPayStatus){
			   hql="update LvOrder set  status="+lvOrder.getStatus()+"," + //订单发货状态
				   " payStatus="+lvOrder.getPayStatus()+"," +              //订单支付状态
				   " overtime='"+DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss")+"'," +//支付时间              
				   " modifyUserId="+lvOrder.getModifyUserId()+"," +
				   " modifyUserName='"+lvOrder.getModifyUserName()+"'," +
				   " modifyTime='"+DateUtils.formatDate(lvOrder.getModifyTime(), null) +"'" +
				   " where oid='"+lvOrder.getOid()+"'";
			   dao.update(hql, null);
			   //修改状态未已经支付，改变限量活动数目和订单满就送活动送券
			   orderToWebService.sendMessageForWebActivity(lvOrderTmp.getOid(), lvOrderTmp.getUserEmail(), lvOrderTmp.getStoreId());
			}else{
			   hql="update LvOrder set  status="+lvOrder.getStatus()+"," + //订单发货状态
				   " payStatus="+lvOrder.getPayStatus()+"," +              //订单支付状态
				   " modifyUserId="+lvOrder.getModifyUserId()+"," +
				   " modifyUserName='"+lvOrder.getModifyUserName()+"'," +
				   " modifyTime='"+DateUtils.formatDate(lvOrder.getModifyTime(), null) +"'" +
				   " where oid='"+lvOrder.getOid()+"'";
			    dao.update(hql, null);
			   
				//调用启创同步销售订单接口
				WSThreeOrderService.updateOrderStatus(lvOrder.getOid(), Integer.parseInt(lvOrder.getStatus().toString()));
			}
			
			
			//后台订单部分添加订单日志信息->发货状态描述：
			String tmpStatus="发货状态为:";
			if (ObjectUtils.isNotEmpty(oldStatus)&&!oldStatus.equals(lvOrder.getStatus())) {
				if(ObjectUtils.isNotEmpty(lvOrder.getStatus())){
					if(lvOrder.getStatus()==Constants.ORDER_STATUS_0){
						tmpStatus+="待发货";
					}
					if(lvOrder.getStatus()==Constants.ORDER_STATUS_1){
						tmpStatus+="已发货,未确认收货";			
					}
					if(lvOrder.getStatus()==Constants.ORDER_STATUS_2){
						tmpStatus+="已收货 ,待评价";
					}
					if(lvOrder.getStatus()==Constants.ORDER_STATUS_3){
						tmpStatus+="已退货";
					}
					if(lvOrder.getStatus()==Constants.ORDER_STATUS_4){
						tmpStatus+="已完成";
					}
				}
			}else{
				tmpStatus="";
			}
			
			//支付状态描述
			String tmpPayStatus="支付状态为:";
			if (ObjectUtils.isNotEmpty(oldPayStatus)&&!oldPayStatus.equals(lvOrder.getPayStatus())) {
				if (ObjectUtils.isNotEmpty(lvOrder.getPayStatus())) {
					if(lvOrder.getPayStatus()==Constants.PAY_STATUS_NO){
						tmpPayStatus+="未支付";
					}else if(lvOrder.getPayStatus()==Constants.PAY_STATUS_OK){
						tmpPayStatus+="已支付,已确认";
					}else if(lvOrder.getPayStatus()==Constants.PAY_STATUS_OK_UNRECOGNIZED){
						tmpPayStatus+="已支付,未确认";
					}
				}
			}else{
				tmpPayStatus="";
			}


			//后台订单部分添加订单日志信息：
			this.saveOrderLogs(lvOrder.getModifyUserName(), lvOrder.getOid(), "修改订单"+tmpStatus+""+tmpPayStatus, lvOrder.getStoreId());
			
	    }else{//已经发货订单确认收货操作等相关订单发货状态修改
			String hql="update LvOrder set  status="+lvOrder.getStatus()+"," + //订单发货状态
			   " modifyUserId="+lvOrder.getModifyUserId()+"," +
			   " modifyUserName='"+lvOrder.getModifyUserName()+"'," +
			   " modifyTime='"+DateUtils.formatDate(lvOrder.getModifyTime(), null) +"'" +
			   " where oid='"+lvOrder.getOid()+"'";
	        dao.update(hql, null);
	        
			//后台订单部分添加订单日志信息：
			this.saveOrderLogs(lvOrder.getModifyUserName(), lvOrder.getOid(), "后台确认收货成功!", lvOrder.getStoreId());
			
			//调用启创同步销售订单接口
			WSThreeOrderService.updateOrderStatus(lvOrder.getOid(), Integer.parseInt(lvOrder.getStatus().toString()));
		}
	
		
	
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.updateStatus() method end*****");
		}
		return 0;
		
	}
	/**
	 * @throws Exception 
	 * 
	 * @Method: updatePrice 
	 * @Description:  [修改订单总金额]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-15 上午11:02:15]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-15 上午11:02:15]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Integer updatePrice(Dto dto) throws Exception {
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.updatePrice() method begin*****");
		}
		//判断最近一次修改是否是有效的版本控制，即是数据否失效。
		Date versionTime=(Date) dto.get("versionTime");
		LvOrder lvOrderTmp=this.getOrderNoDelete(dto);
		if(lvOrderTmp==null){
			return -1;
		}
		if(ObjectUtils.isEmpty(versionTime)&&ObjectUtils.isNotEmpty(lvOrderTmp.getModifyTime())){
			return 1;
		}else if(ObjectUtils.isNotEmpty(versionTime)&&ObjectUtils.isNotEmpty(lvOrderTmp.getModifyTime())){
			if(lvOrderTmp.getModifyTime().getTime()>versionTime.getTime()){
				return 1;
			}
		}
		
		LvOrder lvOrder=(LvOrder) dto.get("lvOrder");
		LvOrder _obj=this.getOrder(dto);
		dto.put("orderId", lvOrder.getOid());
		LvOrderAddress lvOrderAddress = (LvOrderAddress)lvOrderAddressService.getOrderAddress(dto);
		
		String hql="update LvOrder set  totalPrice="+lvOrder.getTotalPrice()+"," +
				   " modifyUserId="+lvOrder.getModifyUserId()+"," +
				   " modifyUserName='"+lvOrder.getModifyUserName()+"'," +
				   " modifyTime='"+DateUtils.formatDate(lvOrder.getModifyTime(), null) +"'" +
				   " where oid='"+lvOrder.getOid()+"'";
		dao.update(hql, null);
		
		//后台订单部分添加订单日志信息：
		this.saveOrderLogs(lvOrder.getModifyUserName(), lvOrder.getOid(), "修改订单金额为："+lvOrder.getTotalPrice(), lvOrder.getStoreId());
		
		
		// 发送修改邮件
		dto.put("tplKey", "ORDER_TOTALPRICE_UPDATE_TEMP");
		dto.put("flag", lvOrderTmp.getStoreId());
		LvEmailTpl emailTpl = lvEmailService.get(dto);
		String content = emailTpl.getEn() + emailTpl.getZh();

		content = content.replace("{oid}", lvOrder.getOid());
		content = content.replace("{relname}", lvOrderAddress.getRelName());
		content = content.replace("{totalPrice}", _obj.getCurrency() + lvOrder.getTotalPrice());
		content = content.replace("{nowdate}", DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
		content = content.replace("{createtime}", DateUtils.formatDate(lvOrder.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
		content = content.replace("{sendtime}", DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
		dto.put("mallFlag", Constants.STORE_TO_MALL_SYSTEM.get(lvOrder.getStoreId()));
		dto.put("content", content);
		dto.put("userEmail", _obj.getUserEmail());
		String enTitle=emailTpl.getEnTitle();
		dto.put("title", enTitle.replace("{oid}", lvOrder.getOid()));
		emailSendService.sendEmailNotice(dto);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.updatePrice() method end*****");
		}
		return 0;
		
	}
	
	public Integer updateSendOrder(Dto dto)throws Exception{
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.updateSendOrder() method begin*****");
		}
		//判断最近一次修改是否是有效的版本控制，即是数据否失效。
		Date versionTime=(Date) dto.get("versionTime");
		LvOrder lvOrderTmp=this.getOrderNoDelete(dto);
		if(lvOrderTmp==null){
			return -1;
		}
		if(ObjectUtils.isEmpty(versionTime)&&ObjectUtils.isNotEmpty(lvOrderTmp.getModifyTime())){
			return 1;
		}else if(ObjectUtils.isNotEmpty(versionTime)&&ObjectUtils.isNotEmpty(lvOrderTmp.getModifyTime())){
			if(lvOrderTmp.getModifyTime().getTime()>versionTime.getTime()){
				return 1;
			}
		}
		
	    LvOrder lvOrder=(LvOrder) dto.get("lvOrder");	
	    String hql="update LvOrder set expressCompany=:expressCompany,expressName=:expressName,expressNum=:expressNum,status=:status, " +
	       " modifyUserId="+lvOrder.getModifyUserId()+"," +
		   " modifyUserName='"+lvOrder.getModifyUserName()+"'," +
		   " modifyTime='"+DateUtils.formatDate(lvOrder.getModifyTime(), null) +"'" +
	       "where oid=:oid";
	    Map param=new HashMap();
	    param.put("expressCompany", lvOrder.getExpressCompany());
	    param.put("expressName", lvOrder.getExpressName());
	    param.put("expressNum", lvOrder.getExpressNum());
	    param.put("status", Constants.ORDER_STATUS_1);
	    param.put("oid", lvOrder.getOid());
	    dao.update(hql, param);
	    
	  //调用启创同步销售订单接口
	  WSThreeOrderService.updateOrderStatus(lvOrder.getOid(), Constants.ORDER_STATUS_1);
	    if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.updateSendOrder() method end*****");
		}
	    return 0;
	}
	
	/**
	 * 
	 * @Method: updateSyncSasFlag 
	 * @Description:  [修改订单同步启创状态标志]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-12-17 下午4:10:34]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-12-17 下午4:10:34]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param orderId  订单号
	 * @param isSyncFlag 是否同步启创表在(1是，0否)
	 * @return void
	 */
	public void updateSyncSasFlag(String orderId,Short isSyncFlag){
		String hql = " UPDATE LvOrder SET isSyncSas=:isSyncSas"+
				     " WHERE oid=:oid";
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("isSyncSas", isSyncFlag);
		param.put("oid", orderId);
		dao.update(hql, param);//
	}
	
	/**
	 * 
	 * @Method: updateSyncSasFlag 
	 * @Description:  [修改订单同步启创状态标志(action调用)]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-12-17 下午4:10:34]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-12-17 下午4:10:34]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param orderId  订单号
	 * @param isSyncFlag 是否同步启创表在(1是，0否)
	 * @return void
	 */
	public void updateSyncSasFlag(Dto dto){
		String orderId=(String) dto.get("orderId");
		Short isSyncSas=(Short) dto.get("isSyncSas");
		if(ObjectUtils.isNotEmpty(orderId)&&ObjectUtils.isNotEmpty(isSyncSas)){
			this.updateSyncSasFlag(orderId, isSyncSas);
		}
	}
	
	/**
	 * @throws ParseException 
	 * 
	 * @Method: save 
	 * @Description:  [保存订单信息(商家自主下单)]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-18 上午11:01:36]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-18 上午11:01:36]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public void save(Dto dto) throws ServiceException, ParseException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.save() method begin*****");
		}
		BaseUsers users =(BaseUsers)dto.get("users");
		LvOrderAddress lvOrderAddress=(LvOrderAddress) dto.get("lvOrderAddress");//订单地址信息
		LvOrder lvOrder=(LvOrder) dto.get("lvOrder");//订单信息
		String oid=(String) dto.get("oid");//订单编号
		String overtime=(String) dto.get("overtime");//支付时间
		String couponCode=(String) dto.get("couponCode");//优惠码
		String storeFlag="tvpadcn";
		LvStore lvStore=null;
		if(ObjectUtils.isNotEmpty(lvOrder.getStoreId())){//设置店铺标示
			storeFlag=lvOrder.getStoreId();
			//根据商家标志查询商家信息
			dto.put("storeFlag", lvOrder.getStoreId());
			lvStore=lvStoreService.findStore(dto);
		}else{
			lvOrder.setStoreId("tvpadcn");
		}
		//获取优惠码面值，由优惠码确定
		Float couponPrice=0f;
		if(null!=couponCode&&!"".equals(couponCode)){
			dto.put("couponCode", couponCode);
			dto=this.vaildCoupon(dto);
			if(ObjectUtils.isEmpty(dto.getAsInteger("couponFlag"))){
				 LvCoupon coupon=(LvCoupon)dto.get("lvCoupon");
				 LvCouponType couponType=(LvCouponType)dto.get("lvCouponType");
				 couponPrice=couponType.getAmount();
				 
				 //保存订单优惠券管理详情
				 LvOrderCoupon orderCoupon=new LvOrderCoupon();
				 orderCoupon.setOrderId(oid);
				 orderCoupon.setCouponTypeCode(couponType.getCode());
				 orderCoupon.setCouponName(couponType.getName());
				 orderCoupon.setCouponPrice(couponPrice);
				 orderCoupon.setCouponCode(coupon.getCouponCode());
				 orderCoupon.setCode(CodeUtils.getCode());
				 orderCoupon.setCreateTime(new Date());
				 dao.save(orderCoupon);
				 //更新优惠码信息
				 String hql="update LvCouponType set usedNumber=:usedNumber," +
				 		" grantNumber=:grantNumber" +
				 		" where id=:id";
				 Map<String,Object> param=new HashMap<String, Object>();
				 param.put("usedNumber",couponType.getUsedNumber()+1 );
				 param.put("grantNumber", couponType.getGrantNumber()-1);
				 param.put("id", couponType.getId());
				 dao.update(hql,param);
				 
				 //更新优惠券信息
				 hql=" update LvCoupon set couponStatus=:couponStatus," +
				 	 " orderId=:orderId," +
				 	 " apply=:apply," +
				 	 " applyTime=:applyTime" +
				 	 " where couponCode=:couponCode";
				 param.clear();
				 param.put("couponStatus",Short.valueOf("2"));
				 param.put("orderId", oid);
				 param.put("apply", users.getUserName());
				 param.put("applyTime", new Date());
				 param.put("couponCode", coupon.getCouponCode());
				 dao.update(hql,param);
			}
		}
		
		
		//保存订单地址信息
		lvOrderAddress.setCode(CodeUtils.getCode());
		lvOrderAddress.setOrderId(oid);
		lvOrderAddress.setCreateTime(new Date());
		lvOrderAddress.setStoreId(lvOrder.getStoreId());
		dto.put("lvOrderAddress", lvOrderAddress);
		lvOrderAddressService.save(dto);
		
		
		
    	//保存订单详情
		Float tmpPrice=0.0f;
		Float tmpCouponPrice=0.0f;
		Integer tmpCouponNum=0;
		if(ObjectUtils.isNotEmpty(lvOrder.getList())){
			//String [] detailTmp=lvOrder.getDetailsList().split(",");//数据组装规则：[产品code, 购买数量]
			List<LvOrderDetails> list=lvOrder.getList();
			for (int i = 0; i < list.size(); i++) {
				Integer isPackage=0;
				LvOrderDetails lvOrderDetails=list.get(i);
				if(lvOrderDetails==null){//判断数据是否存在空值
					continue;
				}
				LvOrderDetails lod=new LvOrderDetails();
				//根据产品code查询产品信息
				LvProduct lvProduct=new LvProduct();
				lvProduct.setCode(lvOrderDetails.getProductCode());
				dto.put("lvProduct", lvProduct);
				lvProduct=(LvProduct) lvProductService.getProduct(dto);
				if(lvProduct!=null&&lvStore!=null){//根据店铺币种和支付方式币种区分取产品单价
					if(lvStore.getCurrency().equals("USD")&&lvOrder.getCurrency().equals("CNY")){//店铺币种为美元，支付方式为人民币，
						lod.setOprice((float)(Math.round(lvProduct.getPrice()*Constants.rateNum * 100)/ 100));
					}else if(lvStore.getCurrency().equals("CNY")&&lvOrder.getCurrency().equals("USD")){//店铺币种为人民币，支付方式为美元
						lod.setOprice((float)(Math.round(lvProduct.getPrice()*Constants.rateNumCNY * 100)/ 100));
					}else{
						lod.setOprice(lvProduct.getPrice());
					}
					lod.setPcode(lvProduct.getPcode());
					if(ObjectUtils.isNotEmpty(lvProduct.getIsSetMeal())){
						isPackage=lvProduct.getIsSetMeal();
					}
				}
				if(ObjectUtils.isEmpty(lod.getOprice())){
					lod.setOprice(0.0f);
				}
				if(ObjectUtils.isEmpty(lod.getCouponPrice())){
					lod.setCouponPrice(0.0f);
				}
				if(ObjectUtils.isEmpty(lod.getPnum())){
					lod.setPnum(0);
				}
				lod.setIsPackage(isPackage);
				lod.setOremark(lvOrder.getOrderRemark());
				lod.setCurrency(lvOrder.getCurrency());//??
				lod.setIsDelete(0);
				lod.setOrderId(oid);
				lod.setProductCode(lvOrderDetails.getProductCode());
				lod.setPnum(lvOrderDetails.getPnum());
				lod.setCode(CodeUtils.getCode());
				lod.setCreateTime(new Date());
				lod.setStoreId(lvOrder.getStoreId());
				//优惠码信息
				if(lvStore!=null){
					if(ObjectUtils.isNotEmpty(lvStore.getIsPreferences())&&lvStore.getIsPreferences()==1
						&&ObjectUtils.isNotEmpty(lvProduct.getIsPreferences())&&lvProduct.getIsPreferences()==1){
						lod.setCouponCode(couponCode);	
						if(lvOrder.getCurrency().equals("USD")){//根据币种区分取产品单价
						   lod.setCouponPrice(couponPrice);			
						}else{
						   lod.setCouponPrice((float)(Math.round(couponPrice*Constants.rateNum* 100)/ 100));
						}
					}else{
						lod.setCouponCode("");
						lod.setCouponPrice(0f);
					}
					if(ObjectUtils.isNotEmpty(lvStore.getIsPreferences())&&lvStore.getIsPreferences()==1
					   &&ObjectUtils.isNotEmpty(lvProduct.getIsPreferences())&&lvProduct.getIsPreferences()==1&&isPackage!=1){
					   tmpCouponNum+=lvOrderDetails.getPnum();
					}
				}

				lod.setStoreId(storeFlag);
				//条件产品或者套餐订单明细
				
				dto.put("lvOrderDetails", lod);
				lvOrderDetailsService.save(dto);

				//添加套餐订单明细关联信息
				if(isPackage!=null&&isPackage==1){
					//根据套餐产品编号查询套餐产品详情
					dto.put("productCode",lod.getProductCode());
					List<LvProductPackage> pgList= lvProductPackageService.getProductPackage(dto);
					for(int num=0;num<pgList.size();num++){
						LvProductPackage lvProductPackage=pgList.get(num);
						LvOrderPackageDetails lvOrderPackageDetails=new LvOrderPackageDetails();
						lvOrderPackageDetails.setProductCode(lvProductPackage.getProductCode());
						lvOrderPackageDetails.setPcode(lvProductPackage.getPcode());
						lvOrderPackageDetails.setPnum(lvProductPackage.getPnum()*lod.getPnum());
						lvOrderPackageDetails.setOrderDetailsCode(lod.getCode());
						lvOrderPackageDetails.setCurrency(lod.getCurrency());
						lvOrderPackageDetails.setCode(CodeUtils.getCode());
						lvOrderPackageDetails.setCreateTime(new Date());				
						if(lvStore.getCurrency().equals("USD")&&lvOrder.getCurrency().equals("CNY")){//店铺币种为美元，支付方式为人民币，
							lvOrderPackageDetails.setOprice((float)(Math.round(lvProductPackage.getPrice()*Constants.rateNum * 100)/ 100));
						}else if(lvStore.getCurrency().equals("CNY")&&lvOrder.getCurrency().equals("USD")){//店铺币种为人民币，支付方式为美元
							lvOrderPackageDetails.setOprice((float)(Math.round(lvProductPackage.getPrice()*Constants.rateNumCNY * 100)/ 100));
						}else{
							lvOrderPackageDetails.setOprice(lvProductPackage.getPrice());
						}
						
						
						if(ObjectUtils.isEmpty(lvOrderPackageDetails.getOprice())){
							lvOrderPackageDetails.setOprice(0.0f);
						}
						if(ObjectUtils.isEmpty(lvOrderPackageDetails.getCouponPrice())){
							lvOrderPackageDetails.setCouponPrice(0.0f);
						}
						if(ObjectUtils.isEmpty(lvOrderPackageDetails.getPnum())){
							lvOrderPackageDetails.setPnum(0);
						}
						
						//根据产品code判断当前产品是否使用优惠券
						LvProduct lvProductTmp=new LvProduct();
						lvProductTmp.setCode(lvProductPackage.getProductCode());
						dto.put("lvProduct", lvProductTmp);
						lvProductTmp=(LvProduct) lvProductService.getProduct(dto);
						if(ObjectUtils.isNotEmpty(lvStore.getIsPreferences())&&lvStore.getIsPreferences()==1&&lvProduct.getIsPreferences()==1
						   &&ObjectUtils.isNotEmpty(lvProductTmp.getIsPreferences())&&lvProductTmp.getIsPreferences()==1){
							 lvOrderPackageDetails.setCouponCode(couponCode);
						     if(lvOrder.getCurrency().equals("USD")){//根据币种区分取产品单价
							     lvOrderPackageDetails.setCouponPrice(couponPrice);
						     }else{
						    	 lvOrderPackageDetails.setCouponPrice((float)(Math.round(couponPrice*Constants.rateNum* 100)/ 100));
						     }				     
							 tmpCouponNum+=lvProductPackage.getPnum()*lod.getPnum();//累计可优惠台数
						}else{
							 lvOrderPackageDetails.setCouponCode("");
							 lvOrderPackageDetails.setCouponPrice(0f);
						}
						
                        //保存订单套餐详情
						dto.put("lvOrderPackageDetails", lvOrderPackageDetails);
						lvOrderPackageDetailsService.save(dto);
						tmpCouponPrice+=lvOrderPackageDetails.getCouponPrice()*lvOrderPackageDetails.getPnum();
					}
					//订单总金额=套餐产品单价*套餐数量+运费-优惠券单价*数量（订单套餐详情）--套餐
					tmpPrice=tmpPrice+lod.getOprice()*lod.getPnum()-tmpCouponPrice;
				}else{
					//订单总金额=产品单价*数量+运费-优惠券单价*数量（订单详情）--非套餐
					
					tmpPrice=tmpPrice+(lod.getOprice()*lod.getPnum()-lod.getCouponPrice()*lod.getPnum());
				}
			}
		}

    	//保存订单信息    	
		lvOrder.setOid(oid);
		lvOrder.setCreateTime(new Date());
		lvOrder.setIsdelete((short)0);
		lvOrder.setIsServiceAudit((short)0);
		lvOrder.setIsFinanceAudit(0);
		lvOrder.setCode(CodeUtils.getCode());
		if (overtime != null && !"".equals(overtime)) {
			lvOrder.setOvertime(DateUtils.convertToDateTime(overtime));
		}
		lvOrder.setCouponNum(tmpCouponNum);//设置可优惠台数
		lvOrder.setStatus((short) 0);
		lvOrder.setFlag(1);// 商家自主下单
		
		//根据区域id查询运费信息,没有运费设置就取默认运费
		dto.put("storeId", lvOrder.getStoreId());
		dto.put("deliveryId", lvOrderAddress.getContryId());
		LvCarriageSet lvCarriageSet=(LvCarriageSet)lvCarriageSetService.getCarriageSet(dto);
		if(lvCarriageSet!=null){
			if(lvStore.getCurrency().equals("USD")&&lvOrder.getCurrency().equals("CNY")){//店铺币种为美元，支付方式为人民币，
				DecimalFormat df = new DecimalFormat("0.00");
				lvOrder.setPostprice((float)(Math.round(lvCarriageSet.getCarriage()*Constants.rateNum* 100)/ 100));
				tmpPrice=tmpPrice+(float)(Math.round(lvCarriageSet.getCarriage()*Constants.rateNum* 100)/ 100);
			}else if(lvStore.getCurrency().equals("CNY")&&lvOrder.getCurrency().equals("USD")){//店铺币种为人民币，支付方式为美元
				DecimalFormat df = new DecimalFormat("0.00");
				lvOrder.setPostprice((float)(Math.round(lvCarriageSet.getCarriage()*Constants.rateNumCNY* 100)/ 100));
				tmpPrice=tmpPrice+(float)(Math.round(lvCarriageSet.getCarriage()*Constants.rateNumCNY* 100)/ 100);
			}else{
				lvOrder.setPostprice(lvCarriageSet.getCarriage());
				tmpPrice=tmpPrice+lvCarriageSet.getCarriage();
			}
		}else{//默认运费设置
			dto.put("deliveryId", 100000);
			LvCarriageSet cset=(LvCarriageSet)lvCarriageSetService.getCarriageSet(dto);
			if(cset!=null){
				if(lvStore.getCurrency().equals("USD")&&lvOrder.getCurrency().equals("CNY")){//店铺币种为美元，支付方式为人民币，
					DecimalFormat df = new DecimalFormat("0.00");
					lvOrder.setPostprice((float)(Math.round(cset.getCarriage()*Constants.rateNum* 100)/ 100));
					tmpPrice=tmpPrice+(float)(Math.round(cset.getCarriage()*Constants.rateNum* 100)/ 100);
				}else if(lvStore.getCurrency().equals("CNY")&&lvOrder.getCurrency().equals("USD")){//店铺币种为人民币，支付方式为美元
					DecimalFormat df = new DecimalFormat("0.00");
					lvOrder.setPostprice((float)(Math.round(cset.getCarriage()*Constants.rateNumCNY* 100)/ 100));
					tmpPrice=tmpPrice+(float)(Math.round(cset.getCarriage()*Constants.rateNumCNY* 100)/ 100);
				}else{
					lvOrder.setPostprice(cset.getCarriage());
					tmpPrice=tmpPrice+cset.getCarriage();
				}
			}
		}
		
		//处理可以填写订单总金额，如果未填写就用产品计算的金额
		if(ObjectUtils.isNotEmpty(lvOrder.getTotalPrice())&&lvOrder.getTotalPrice()>0){
			if(lvStore.getCurrency().equals("USD")&&lvOrder.getCurrency().equals("CNY")){//店铺币种为美元，支付方式为人民币，
				lvOrder.setTotalPrice((float)(Math.round(lvOrder.getTotalPrice()*Constants.rateNum* 100)/ 100));
			}else if(lvStore.getCurrency().equals("CNY")&&lvOrder.getCurrency().equals("USD")){//店铺币种为人民币，支付方式为美元
				lvOrder.setTotalPrice((float)(Math.round(lvOrder.getTotalPrice()*Constants.rateNumCNY* 100)/ 100));
			}else{
				lvOrder.setTotalPrice(lvOrder.getTotalPrice());
			}
		}else{
			lvOrder.setTotalPrice(tmpPrice);
		}
		
		//根据用户Email查询是否存在用户信息
		dto.put("userEmail", lvOrder.getUserEmail());
		LvAccount lvAccount=lvAccountService.getAccountByEmail(dto);
		if(lvAccount!=null){
			if(ObjectUtils.isNotEmpty(lvAccount.getId())){
				lvOrder.setMemid(lvAccount.getId());
			}
		}
		
		//保存订单信息
        dao.save(lvOrder);
		
        //后台订单部分添加订单日志信息：
		this.saveOrderLogs(users.getUserName(), lvOrder.getOid(), "商家自主下单", storeFlag);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.save() method end*****");
		}
		
	}
	

	
	/**
	 * 
	 * @Method: saveSysComment 
	 * @Description:  [添加系统自定义评论(包括订单评论，产品评论和修改订单状态为已完成)]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-18 下午05:23:17]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-10-15 下午01:03:17]   
	 * @UpdateRemark: [修改订单系统评论：v1改为只要产品评论信息,v2恢复添加订单评论,v3修改系统自评为已审核]  
	 * @throws
	 */
	@Override
	public void saveSysComment(Dto dto) throws Exception {
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.saveSysComment() method begin*****");
		}
		LvOrder lvOrder=(LvOrder) dto.get("lvOrder");
		LvOrderComment lvOrderComment=(LvOrderComment) dto.get("lvOrderComment");
		String ids=dto.getAsString("ids");
		String [] idsTmp=ids.split(",");
		for(int i=0;i<idsTmp.length;i++){
			LvOrder order=new LvOrder();
			order.setOid(idsTmp[i]);
			dto.put("lvOrder", order);
			order=this.getOrderNoDelete(dto);
			
			//新增订单评论
			LvOrderComment loc=new LvOrderComment();
			loc.setContent(lvOrderComment.getContent());
			loc.setScore(lvOrderComment.getScore());
			loc.setGrade(lvOrderComment.getGrade());
			loc.setOrderId(idsTmp[i]);
			loc.setIsDelete(Short.parseShort("0"));
			loc.setIsCheck(Short.parseShort("1"));//
			loc.setCode(CodeUtils.getCode());
			loc.setCreateTime(new Date());
			loc.setStoreId(order.getStoreId());
			dao.save(loc);
			
			//根据订单号查询订单详情(新增产品评论)
			dto.put("orderId", idsTmp[i]);
			LvOrderAddress lvOrderAddress= lvOrderAddressService.getOrderAddress(dto);
			List<LvOrderDetails> detailsList= lvOrderDetailsService.getOrderDetails(dto);
			for(int num=0;num<detailsList.size();num++){
				LvOrderDetails lvOrderDetails=detailsList.get(num);
				if(lvOrderDetails!=null){
					LvProductComment lvProductComment=new LvProductComment();
					lvProductComment.setOid(lvOrderDetails.getOrderId());
					lvProductComment.setProductCode(lvOrderDetails.getProductCode());
					lvProductComment.setContryId(lvOrderAddress.getContryId());
					lvProductComment.setOprice(lvOrderDetails.getOprice());
					lvProductComment.setPnum(lvOrderDetails.getPnum());
					lvProductComment.setCurrency(lvOrderDetails.getCurrency());
					lvProductComment.setIsCheck(Short.parseShort("1"));
					lvProductComment.setContent(lvOrderComment.getContent());
					lvProductComment.setScore(lvOrderComment.getScore());
					lvProductComment.setGrade(lvOrderComment.getGrade());
					lvProductComment.setIsDelete(Short.parseShort("0"));
					lvProductComment.setCode(CodeUtils.getCode());
					lvProductComment.setCreateTime(new Date());
					lvProductComment.setStoreId(order.getStoreId());
					dao.save(lvProductComment);
				}
			}
			
			//修改订单状态为已完成
			lvOrder.setOid(idsTmp[i]);
			lvOrder.setStatus((short)4);
			this.updateStatus(dto);
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.saveSysComment() method end*****");
		}
	}
	
	/**
	 * 
	 * @Method: doOrder 
	 * @Description:  [订单发货处理]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-31 下午03:20:14]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-31 下午03:20:14]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public Integer doOrder(Dto dto) throws Exception {		
		LvOrder lvOrder = (LvOrder) dto.get("lvOrder");//物流信息参数
		LvOrder temp = (LvOrder) dao.findUnique("FROM LvOrder WHERE oid='" + lvOrder.getOid() + "'", null);//订单信息
		
		//查找支付信息
		dto.put("payValue", temp.getPaymethod());
		dto.put("storeFlag", temp.getStoreId());
		LvPaymentStyle lvPaymentStyle=lvPaymentStyleService.findPaymentStyle(dto);
		
		
		if (temp != null && temp.getPayStatus() == Constants.PAY_STATUS_OK && temp.getStatus() == Constants.ORDER_STATUS_0) {
			if (temp.getPaymethod() == 3) {
				if(lvPaymentStyle.getPayChannel()==0){//内置接口分支
		            String hql="from LvPayLogs where oid='" + lvOrder.getOid() + "' and remark='WAIT_SELLER_SEND_GOODS'";
					List<LvPayLogs> list=dao.find(hql, null);
					if(list!=null&&list.size()>0){
						LvPayLogs lvPaylog=list.get(0);
						// 支付宝支付
						// 必填参数//
						// 支付宝交易号
						String trade_no = temp.getThirdPartyOrder();
						if (lvPaylog!=null&&trade_no != null && !"".equals(trade_no)) {
							// 物流公司名称
							String logistics_name = lvOrder.getExpressCompany();
							// 发货时的运输类型
							String transport_type = lvOrder.getExpressName();
							// 选填参数
							// 物流发货单号
							String invoice_no = lvOrder.getExpressNum();
							// ////////////////////////////////////////////////////////////////////////////////
							// 把请求参数打包成数组
							Map<String, String> sParaTemp = new HashMap<String, String>();
							sParaTemp.put("trade_no", trade_no);
							sParaTemp.put("logistics_name", logistics_name);
							sParaTemp.put("invoice_no", invoice_no);
							sParaTemp.put("transport_type", "EXPRESS");
							Map parms=new HashMap();
							parms.put("storeFlag", temp.getStoreId());
							parms.put("payValue", temp.getPaymethod());
							
							LvPaymentStyle pstyle=(LvPaymentStyle)dao.findUnique("from LvPaymentStyle t where storeFlag=:storeFlag and payValue=:payValue ", parms);
							JSONObject object=JSONObject.fromObject(pstyle.getParams());
							
							AlipayConfig alipayConfig=new AlipayConfig();
							alipayConfig.partner=(String)object.get("partner");
							alipayConfig.seller_email=(String)object.getString("seller_email");
							alipayConfig.key= (String)object.get("key");
							alipayConfig.gateway=pstyle.getUrl();
							
							// 构造函数，生成请求URL
							String sHtmlText = AlipayService.send_goods_confirm_by_platform(sParaTemp,alipayConfig);
							if (ObjectUtils.isNotEmpty(sHtmlText)) {
								Dto xmlDto = XmlHelper.parseXml2DtoBasedNode(sHtmlText);
								
								LvManagerPayLog paylog = new LvManagerPayLog();
								paylog.setOid(temp.getOid());
								paylog.setUname(dto.getAsString("uName"));// 操作人
								paylog.setCreateTime(new Date());
								if ("T".equals(xmlDto.getAsString("is_success"))){
									paylog.setRemark("确认发货成功");
									dao.save(paylog);
								} else {
									paylog.setRemark("确认发货失败:"+xmlDto.getAsString("error"));
									dao.save(paylog);
									return -1;
								}
							}else{
								LvManagerPayLog paylog = new LvManagerPayLog();
								paylog.setOid(temp.getOid());
								paylog.setUname(dto.getAsString("uName"));// 操作人
								paylog.setCreateTime(new Date());
								paylog.setRemark("确认发货失败");
								dao.save(paylog);
								return -1;
							}
						}else{//支付宝即时到帐交易方式  后台发货日志记录 
							LvManagerPayLog paylog = new LvManagerPayLog();
							paylog.setOid(temp.getOid());
							paylog.setUname(dto.getAsString("uName"));// 操作人
							paylog.setCreateTime(new Date());
							paylog.setRemark("支付宝即时到帐  后台发货");
							dao.save(paylog);
						}
						
						//更新物流信息和新增订单日志
						updateOrderExpressInfo(dto, lvOrder, temp);
					}
		                
				}else if(lvPaymentStyle.getPayChannel()==1){//支付系统接口实现
					String url="";//支付中心地址
					String mer_code="";//商户号
					String mer_key="";//商户密钥
					JSONObject payData=null;
					if(ObjectUtils.isNotEmpty(lvPaymentStyle.getPaymentSystemParams())){
						payData=JSONObject.fromObject(lvPaymentStyle.getPaymentSystemParams());
						url=(String) payData.get("paySysUrl");
						mer_code=(String) payData.get("paySysMerno");
						mer_key=(String) payData.get("paySysKey");
					}
					// 必填参数//
					//支付宝交易号
					String trade_no=temp.getThirdPartyOrder();
					// 物流公司名称
					String logistics_name = lvOrder.getExpressCompany();
					// 发货时的运输类型
					String transport_type = lvOrder.getExpressName();
					// 选填参数
					// 物流发货单号
					String invoice_no = lvOrder.getExpressNum();
					
					//拼接签名内容
					String str=mer_code+trade_no+mer_key;
					String sign = PayHelper.sign(str);// 生成签名
					//拼装参数
					String params="mer_code="+mer_code+"&trade_no="+trade_no+"&logistics_name="+logistics_name+"&invoice_no="+invoice_no+"&transport_type=EXPRESS&sign="+sign+"";
					url=url+"/pay/alipayreturn!faihuo.action";//拼接发送物流请求地址
					String  result=PayHelper.postUrl(url,params);
					if("T".equals(result)){
					    updateOrderExpressInfo(dto, lvOrder, temp);
						return 1;
					}else{
					    //发货失败！
						//记录订单日志
						String userName=dto.getAsString("uName");
						this.saveOrderLogs(userName, lvOrder.getOid(),"发货失败", lvOrder.getStoreId());
						
						return -1;
					}
				}
			}else if(temp.getPaymethod() == Constants.PAY_METHOD_QH){//钱海支付
				JSONObject payData = null;
				if (lvPaymentStyle.getParams() != null&& !"".equals(lvPaymentStyle.getParams().trim())) {
					payData = JSONObject.fromObject(lvPaymentStyle.getParams());
				}
				String account= (String) payData.get("account");        //账户
				String terminal= (String) payData.get("terminal");      //终端号
				String secureCode = (String) payData.get("secureCode"); //密钥
                String payment_id=temp.getThirdPartyOrder();            //支付单号
                String tracking_number=lvOrder.getExpressNum();         //物流单号
                String tracking_site=lvOrder.getExpressName();          //物流网址
                String trancking_handler=dto.getAsString("uName");      //物流添加人

                //调用上传钱海支付物流公共方法
				TranckInfoVo tranckInfoVo= QHPayUtils.uploadTrackNo(account, terminal, secureCode, payment_id, tracking_number, tracking_site, trancking_handler);
				if(tranckInfoVo.getTracking_results().equals("01")){
					//更新物流信息和新增订单日志
					updateOrderExpressInfo(dto, lvOrder, temp);
					return 1;
				}else{
					//记录订单日志
					String userName=dto.getAsString("uName");
					this.saveOrderLogs(userName, lvOrder.getOid(),"发货失败,失败原因："+tranckInfoVo.getTracking_results(), lvOrder.getStoreId());
					return -1;
				}
			}else{//非支付宝的其他支付方式，同步订单物流信息
				//更新物流信息和新增订单日志
				updateOrderExpressInfo(dto, lvOrder, temp);
				return 1;
			}
		}
		return -1;
	}

	/**
	 * 
	 * @Method: saveOrderLogs 
	 * @Description:  [保存订单日志公共方法]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-4-21 上午11:31:13]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-4-21 上午11:31:13]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param userName 操作者
	 * @param orderId 订单号
	 * @param operate 操作内容
	 * @param storeId 店铺标志
	 * @return void
	 */	
	public void saveOrderLogs(String userName,String orderId,String operate,String storeId){
		LvOrderLogs orderLog = new LvOrderLogs();
		orderLog.setUname(userName);
		orderLog.setOrd(orderId);
		orderLog.setCreateTime(new Date());
		orderLog.setCode(CodeUtils.getCode());
		if(ObjectUtils.isNotEmpty(operate)){
			orderLog.setOperate(operate);
		}
		orderLog.setStoreId(storeId);
		dao.save(orderLog);
	}
	
	

	/**
	 * @Method: updateOrderExpressInfo 
	 * @Description:  [更新物流信息和新增订单日志]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2014-4-21 上午10:47:45]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2014-4-21 上午10:47:45]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @param dto
	 * @param lvOrder 接口传入的订单参数
	 * @param temp   查询当前系统的订单详情
	 * @return void
	 */
	private void updateOrderExpressInfo(Dto dto, LvOrder lvOrder, LvOrder temp) {
		String hql = "UPDATE LvOrder SET expressName=:expressName," +
				" expressNum=:expressNum," +
				" expressCompany=:expressCompany," +
				" status=:status, " +
				" shipTime=:shipTime" +
				" WHERE id=:id";
		Map param = new HashMap();
		param.put("expressName", lvOrder.getExpressName());
		param.put("expressNum", lvOrder.getExpressNum());
		param.put("expressCompany", lvOrder.getExpressCompany());
		param.put("status", lvOrder.getStatus());
		param.put("shipTime", lvOrder.getShipTime());
		param.put("id", temp.getId());
		dao.update(hql, param);// 发货下订单
		
		//记录订单日志
		String operate="";
		if(ObjectUtils.isNotEmpty(dto.getAsString("operate"))){
			operate=dto.getAsString("operate");
		}else{
			operate="发货";
		}
		this.saveOrderLogs(dto.getAsString("uName"),
				           lvOrder.getOid(), 
				           operate, 
				           lvOrder.getStoreId());
		
	}
	/**
	 * 
	 * @Method: doShopNotice 
	 * @Description:  [商城批量卖家发货提醒]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-12-27 上午10:09:16]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-12-27 上午10:09:16]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public void doShopNotice(Dto dto)throws ServiceException{
		String ids=(String) dto.get("ids");
		LvOrder lvOrder=(LvOrder) dto.get("lvOrder");
		if(ObjectUtils.isNotEmpty(ids)){
			//导出信息中有那些店铺
			String hql="select storeId as storeId from LvOrder where oid in("+ids+") and status=0 group by storeId";
			List list= dao.getMapListByHql(hql, null).getList();
			if(list!=null){
				for(int i=0;i<list.size();i++){
					Map map=(Map) list.get(i);
					//查询每个店铺下对应各自的订单
					hql="from LvOrder where oid in("+ids+") and status=0 and storeId='"+String.valueOf(map.get("storeId"))+"'";
					List<LvOrder> listTmp= dao.find(hql, null);
					String orderString="";
					for(int k=0;k<listTmp.size();k++){
						LvOrder order=listTmp.get(k);
						if(k==listTmp.size()-1){
							orderString+=order.getOid();
						}else{
							orderString+=order.getOid()+",";
						}
					}
					if(ObjectUtils.isEmpty(orderString)){
						continue ;
					}
					
					
					dto.put("storeFlag", map.get("storeId"));
					LvStore lvStore=lvStoreService.findStore(dto);
					if(lvStore!=null){
						if(ObjectUtils.isNotEmpty(lvStore.getEmail())){
							// 发送修改邮件
							dto.put("flag", lvStore.getStoreFlag());
							dto.put("tplKey", "NOTICE_FAHUO");
							LvEmailTpl emailTpl = lvEmailService.get(dto);
							String content = emailTpl.getEn() + emailTpl.getZh();
							content = content.replace("${relName}",lvStore.getName());
							content = content.replace("${orderList}",orderString);
							content = content.replace("${sendtime}", DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
							//填充发送邮件关键元素
							dto.put("mallFlag", Constants.STORE_TO_MALL_SYSTEM.get(lvStore.getStoreFlag()));
							dto.put("content", content);
							dto.put("userEmail", lvStore.getEmail());
							dto.put("title", emailTpl.getEnTitle());
							emailSendService.sendEmailNoticePub(dto);
						}
					}
				}
			}
		}else if(lvOrder!=null&&ObjectUtils.isNotEmpty(lvOrder.getOid())){
			String hql="from LvOrder where oid='"+lvOrder.getOid()+"' and status=0 ";
			LvOrder order=(LvOrder) dao.findUnique(hql, null);
			if(order!=null){
				dto.put("storeFlag", order.getStoreId());
				LvStore lvStore=lvStoreService.findStore(dto);
				if(lvStore!=null){
					if(ObjectUtils.isNotEmpty(lvStore.getEmail())){
						// 发送修改邮件
						dto.put("flag", lvStore.getStoreFlag());
						dto.put("tplKey", "NOTICE_FAHUO");
						LvEmailTpl emailTpl = lvEmailService.get(dto);
						String content = emailTpl.getEn() + emailTpl.getZh();
						content = content.replace("${relName}",lvStore.getName());
						content = content.replace("${orderList}",order.getOid());
						content = content.replace("${sendtime}", DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
						//填充发送邮件关键元素
						dto.put("mallFlag", Constants.STORE_TO_MALL_SYSTEM.get(lvStore.getStoreFlag()));
						dto.put("content", content);
						dto.put("userEmail", lvStore.getEmail());
						dto.put("title", emailTpl.getEnTitle());
						emailSendService.sendEmailNoticePub(dto);
					}
				}	
			}
		}
	}
	
	
	public void changeOrder(Dto dto)throws ServiceException{
		String ids=(String) dto.get("ids");
		String targetFlag=(String) dto.get("targetFlag");
		LvOrder lvOrder=(LvOrder) dto.get("lvOrder");
		BaseUsers users=(BaseUsers) dto.get("users");
		
		
		dto.put("storeFlag", lvOrder.getStoreId());
		LvStore lvStore= lvStoreService.findStore(dto);//原店铺信息
		dto.put("storeFlag", targetFlag);
		LvStore targetStore= lvStoreService.findStore(dto);//目标店铺信息
		//更新订单目标店铺信息
		String hql=" update LvOrder set storeId=:storeId" +
				   " where oid=:oid ";
		Map param = new HashMap();
		param.put("storeId",targetStore.getStoreFlag());
		param.put("oid", lvOrder.getOid());
		dao.update(hql, param);// 
		
		List<LvOrderDetails> detailsList= lvOrder.getList();
	    for (LvOrderDetails lvOrderDetails : detailsList) {
	    	hql=" update LvOrderDetails set storeId=:storeId," +
	    		" productCode=:productCode" +
			    " where orderId=:orderId " +
			    " and id=:id";
			Map map = new HashMap();
			map.put("storeId",targetStore.getStoreFlag());
			map.put("orderId", lvOrder.getOid());
			map.put("productCode", lvOrderDetails.getTargetProductCode());
			map.put("id", lvOrderDetails.getId());
			dao.update(hql, map);// 	
		}
		
		
		//插入转单日志信息
		LvSwitchOrderLogs lvSwitchOrderLogs=new LvSwitchOrderLogs();
		lvSwitchOrderLogs.setOrderId(lvOrder.getOid());
		lvSwitchOrderLogs.setOldStoreCode(lvStore.getCode());
		lvSwitchOrderLogs.setTargetStoreCode(targetStore.getCode());
		lvSwitchOrderLogs.setCode(CodeUtils.getCode());
		lvSwitchOrderLogs.setCreateTime(new Date());
		lvSwitchOrderLogs.setCreateUserId(users.getId());
		lvSwitchOrderLogs.setCreateUserName(users.getUserName());
		dao.save(lvSwitchOrderLogs);
		
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @Method: synSendOrder 
	 * @Description:  [同步商务发货订单及物流信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2013-5-17 上午10:20:52]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2013-5-17 上午10:20:52]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public ExpressResponse synSendOrder(Dto dto)throws Exception{
		LvOrder lvOrder=(LvOrder) dto.get("lvOrder");		
		BaseUsers users=(BaseUsers) dto.get("users");
		BzExpressService bzExpressService=(BzExpressService)ServiceConstants.beanFactory.getBean("bzExpressService");	
		ExpressResponse expressResponse=bzExpressService.findLogisticsByCode(lvOrder.getOid());
		//ExpressResponse expressResponse=null;
		lvOrder=this.getOrder(lvOrder.getOid());
		if(expressResponse!=null){
		   if(ObjectUtils.isNotEmpty(expressResponse.getResult().getResultCode())
				   &&expressResponse.getResult().getResultCode().equals("1")){
			   ExpressInfo expressInfo=expressResponse.getExpressInfo();
			   if(expressInfo!=null&&expressInfo.getStatus().equals("1")&&lvOrder.getStatus()==Constants.ORDER_STATUS_0){
					

					LvOrder tempOrder=new LvOrder();
					tempOrder.setOid(expressInfo.getOid());
					tempOrder.setExpressCompany(expressInfo.getExpressCompany());
					tempOrder.setExpressName(expressInfo.getExpressName());
					tempOrder.setExpressNum(expressInfo.getExpressNum());
					tempOrder.setShipTime(DateUtils.convertToDateTime(expressInfo.getShipTime()));
					tempOrder.setStatus(Short.parseShort(expressInfo.getStatus()));
					dto.remove("lvOrder");
					dto.put("lvOrder", tempOrder);
					dto.put("uName", users.getUserName());
					dto.put("operate", "手动同步商务物流发货！");
					//调用同步物流实现
					this.doOrder(dto);	
			   }
		   }	
		}
		
		return expressResponse;
	}
	
	/**************************************************************************************
	 * 订单统计
	 * ************************************************************************************
	 * 
	 * @Method: countAll 
	 * @Description:  [订单统计-总下单数量]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-30 上午10:07:31]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-30 上午10:07:31]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */

	@Override
	public Integer countAll(Dto dto) throws Exception {
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.countAll() method begin*****");
		}
		LvOrder lvOrder=(LvOrder) dto.get("lvOrder");
		String hql="select count(id) from LvOrder o where o.isdelete=0";
		
		if(lvOrder!=null){
            if(ObjectUtils.isNotEmpty(lvOrder.getStartTime())&&ObjectUtils.isNotEmpty(lvOrder.getEndTime())){//下单时间
            	hql+=" and o.createTime>'"+lvOrder.getStartTime()+" 00:00:00'" +
            		 " and o.createTime<'"+lvOrder.getEndTime()+" 23:59:59'";
            }
            if(ObjectUtils.isNotEmpty(lvOrder.getUserEmail())){
            	hql+=" and o.userEmail='"+lvOrder.getUserEmail()+"'";
            }
            if(ObjectUtils.isNotEmpty(lvOrder.getMemid())){
            	hql+=" and o.memid="+lvOrder.getMemid()+"";
            }
		}
		if(ObjectUtils.isNotEmpty(dto.get("storeFlag"))){
			hql+=" and o.storeId='"+dto.get("storeFlag")+"'";
		}
		int sumCount = dao.countQueryResult(Finder.create(hql), null);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.countAll() method end*****");
		}
		return sumCount;
	}

    /**
     * 
     * @Method: countBack 
     * @Description:  [订单统计-退货订单数]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-7-30 上午10:10:39]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-7-30 上午10:10:39]   
     * @UpdateRemark: [说明本次修改内容]  
     * @throws
     */
	@Override
	public Integer countBack(Dto dto) throws Exception {
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.countBack() method begin*****");
		}
		LvOrder lvOrder=(LvOrder) dto.get("lvOrder");
		String hql="select count(id) from LvOrder o where o.isdelete=0 and o.status=3 ";
		if(lvOrder!=null){
            if(ObjectUtils.isNotEmpty(lvOrder.getStartTime())&&ObjectUtils.isNotEmpty(lvOrder.getEndTime())){//下单时间
            	hql+=" and o.createTime>'"+lvOrder.getStartTime()+" 00:00:00'" +
            		 " and o.createTime<'"+lvOrder.getEndTime()+" 23:59:59'";
            }
		}
		if(ObjectUtils.isNotEmpty(dto.get("storeFlag"))){
			hql+=" and o.storeId='"+dto.get("storeFlag")+"'";
		}
		int sumCount = dao.countQueryResult(Finder.create(hql), null);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.countBack() method end*****");
		}
		return sumCount;
	}
	
    /**
     * 
     * @Method: countDelete 
     * @Description:  [订单统计-删除订单数]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-7-30 上午10:11:05]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-7-30 上午10:11:05]   
     * @UpdateRemark: [说明本次修改内容]  
     * @throws
     */
	@Override
	public Integer countDelete(Dto dto) throws Exception {
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.countDelete() method begin*****");
		}
		LvOrder lvOrder=(LvOrder) dto.get("lvOrder");
		String hql="select count(id) from LvOrder o where o.isdelete=-1 ";
		if(lvOrder!=null){
            if(ObjectUtils.isNotEmpty(lvOrder.getStartTime())&&ObjectUtils.isNotEmpty(lvOrder.getEndTime())){//下单时间
            	hql+=" and o.createTime>'"+lvOrder.getStartTime()+" 00:00:00'" +
            		 " and o.createTime<'"+lvOrder.getEndTime()+" 23:59:59'";
            }
		}
		if(ObjectUtils.isNotEmpty(dto.get("storeFlag"))){
			hql+=" and o.storeId='"+dto.get("storeFlag")+"'";
		}
		int sumCount = dao.countQueryResult(Finder.create(hql), null);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.countDelete() method end*****");
		}
		return sumCount;
	}

	/**
	 * 
	 * @Method: countFinish 
	 * @Description:  [订单统计-完成订单数]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-30 上午10:12:27]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-30 上午10:12:27]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Integer countFinish(Dto dto) throws Exception {
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.countFinish() method begin*****");
		}
		LvOrder lvOrder=(LvOrder) dto.get("lvOrder");
		String hql="select count(id) from LvOrder o where o.isdelete=0 and o.status=4 ";
		if(lvOrder!=null){
            if(ObjectUtils.isNotEmpty(lvOrder.getStartTime())&&ObjectUtils.isNotEmpty(lvOrder.getEndTime())){//下单时间
            	hql+=" and o.createTime>'"+lvOrder.getStartTime()+" 00:00:00'" +
            		 " and o.createTime<'"+lvOrder.getEndTime()+" 23:59:59'";
            }
		}
		if(ObjectUtils.isNotEmpty(dto.get("storeFlag"))){
			hql+=" and o.storeId='"+dto.get("storeFlag")+"'";
		}
		int sumCount = dao.countQueryResult(Finder.create(hql), null);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.countFinish() method end*****");
		}
		return sumCount;
	}
    /**
     * 
     * @Method: countPay 
     * @Description:  [订单统计-已付款订单数]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-7-30 上午10:12:50]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-7-30 上午10:12:50]   
     * @UpdateRemark: [说明本次修改内容]  
     * @throws
     */
	@Override
	public Integer countPay(Dto dto) throws Exception {
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.countPay() method begin*****");
		}
		LvOrder lvOrder=(LvOrder) dto.get("lvOrder");
		String hql="select count(id) from LvOrder o where o.isdelete=0 and o.payStatus in(1,2) ";
		if(lvOrder!=null){
            if(ObjectUtils.isNotEmpty(lvOrder.getStartTime())&&ObjectUtils.isNotEmpty(lvOrder.getEndTime())){//下单时间
            	hql+=" and o.createTime>'"+lvOrder.getStartTime()+" 00:00:00'" +
            		 " and o.createTime<'"+lvOrder.getEndTime()+" 23:59:59'";
            }
            if(ObjectUtils.isNotEmpty(lvOrder.getUserEmail())){
            	hql+=" and o.userEmail='"+lvOrder.getUserEmail()+"'";
            }
            if(ObjectUtils.isNotEmpty(lvOrder.getMemid())){
            	hql+=" and o.memid="+lvOrder.getMemid()+"";
            }
		}
		if(ObjectUtils.isNotEmpty(dto.get("storeFlag"))){
			hql+=" and o.storeId='"+dto.get("storeFlag")+"'";
		}
		int sumCount = dao.countQueryResult(Finder.create(hql), null);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.countPay() method end*****");
		}
		return sumCount;
	}

    /**
     * 
     * @Method: countUnPay 
     * @Description:  [订单统计-未付款订单数]  
     * @Author:       [liaoxiongjian]     
     * @CreateDate:   [2012-7-30 上午10:13:14]   
     * @UpdateUser:   [liaoxiongjian]     
     * @UpdateDate:   [2012-7-30 上午10:13:14]   
     * @UpdateRemark: [说明本次修改内容]  
     * @throws
     */
	@Override
	public Integer countUnPay(Dto dto) throws Exception {
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.countUnPay() method begin*****");
		}
		LvOrder lvOrder=(LvOrder) dto.get("lvOrder");
		String hql="select count(id) from LvOrder o where o.isdelete=0 and o.payStatus =0";
		if(lvOrder!=null){
            if(ObjectUtils.isNotEmpty(lvOrder.getStartTime())&&ObjectUtils.isNotEmpty(lvOrder.getEndTime())){//下单时间
            	hql+=" and o.createTime>'"+lvOrder.getStartTime()+" 00:00:00'" +
            		 " and o.createTime<'"+lvOrder.getEndTime()+" 23:59:59'";
            }
            if(ObjectUtils.isNotEmpty(lvOrder.getUserEmail())){
            	hql+=" and o.userEmail='"+lvOrder.getUserEmail()+"'";
            }
            if(ObjectUtils.isNotEmpty(lvOrder.getMemid())){
            	hql+=" and o.memid="+lvOrder.getMemid()+"";
            }
		}
		if(ObjectUtils.isNotEmpty(dto.get("storeFlag"))){
			hql+=" and o.storeId='"+dto.get("storeFlag")+"'";
		}
		int sumCount = dao.countQueryResult(Finder.create(hql), null);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.countUnPay() method end*****");
		}
		return sumCount;
	}
	
	/**
	 * 
	 * @Method: totalPrice 
	 * @Description:  [订单统计-销售额(已经支付且未退货的订单才统计)]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-30 上午11:54:37]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-30 上午11:54:37]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public Float totalPrice(Dto dto)throws Exception{
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.totalPrice() method begin*****");
		}
		LvOrder lvOrder=(LvOrder) dto.get("lvOrder");
		String hql="select sum(totalPrice) as totalPrice from LvOrder o where o.isdelete=0 and o.payStatus=1 and o.status!=3 ";
		if(lvOrder!=null){
            if(ObjectUtils.isNotEmpty(lvOrder.getStartTime())&&ObjectUtils.isNotEmpty(lvOrder.getEndTime())){//下单时间
            	hql+=" and o.createTime>'"+lvOrder.getStartTime()+" 00:00:00'" +
            		 " and o.createTime<'"+lvOrder.getEndTime()+" 23:59:59'";
            }
		}
		if(ObjectUtils.isNotEmpty(dto.get("storeFlag"))){
			hql+=" and o.storeId='"+dto.get("storeFlag")+"'";
		}
		Float num=0.0f;
		List<Double> list = dao.find(hql, null);
		if(list!=null&&list.size()>0){
			Double obj= list.get(0);
			if(obj!=null){
				num=Float.parseFloat(obj.toString());
			}
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.totalPrice() method end*****");
		}
		return num;
	}
	
	/**
	 * 
	 * @Method: totalPriceRmb 
	 * @Description:  [订单统计-销售额(已经支付且未退货的订单才统计)币种为RMB] 
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-10-11 下午01:56:54]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-10-11 下午01:56:54]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Float totalPriceRmb(Dto dto) throws Exception {
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.totalPrice() method begin*****");
		}
		LvOrder lvOrder=(LvOrder) dto.get("lvOrder");
		String hql="select sum(totalPrice) as totalPrice from LvOrder o where o.isdelete=0 and o.payStatus=1 and o.status!=3 and currency in('RMB','CNY')";
		if(lvOrder!=null){
            if(ObjectUtils.isNotEmpty(lvOrder.getStartTime())&&ObjectUtils.isNotEmpty(lvOrder.getEndTime())){//下单时间
            	hql+=" and o.createTime>'"+lvOrder.getStartTime()+" 00:00:00'" +
            		 " and o.createTime<'"+lvOrder.getEndTime()+" 23:59:59'";
            }
            if(ObjectUtils.isNotEmpty(lvOrder.getUserEmail())) {
            	hql+=" and o.userEmail='"+lvOrder.getUserEmail()+"'";
			}
            if(ObjectUtils.isNotEmpty(lvOrder.getMemid())){
            	hql+=" and o.memid="+lvOrder.getMemid()+"";
            }
		}
		if(ObjectUtils.isNotEmpty(dto.get("storeFlag"))){
			hql+=" and o.storeId='"+dto.get("storeFlag")+"'";
		}
		Float num=0.0f;
		List<Double> list = dao.find(hql, null);
		if(list!=null&&list.size()>0){
			Double obj= list.get(0);
			if(obj!=null){
				num=Float.parseFloat(obj.toString());
			}
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.totalPrice() method end*****");
		}
		return num;
	}

	/**
	 * 
	 * @Method: totalPriceUsd 
	 * @Description:   [订单统计-销售额(已经支付且未退货的订单才统计)币种为USD] 
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-10-11 下午01:57:32]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-10-11 下午01:57:32]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Float totalPriceUsd(Dto dto) throws Exception {
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.totalPrice() method begin*****");
		}
		LvOrder lvOrder=(LvOrder) dto.get("lvOrder");
		String hql="select sum(totalPrice) as totalPrice from LvOrder o where o.isdelete=0 and o.payStatus=1 and o.status!=3 and currency='USD'";
		if(lvOrder!=null){
            if(ObjectUtils.isNotEmpty(lvOrder.getStartTime())&&ObjectUtils.isNotEmpty(lvOrder.getEndTime())){//下单时间
            	hql+=" and o.createTime>'"+lvOrder.getStartTime()+" 00:00:00'" +
            		 " and o.createTime<'"+lvOrder.getEndTime()+" 23:59:59'";
            }
            if(ObjectUtils.isNotEmpty(lvOrder.getUserEmail())) {
            	hql+=" and o.userEmail='"+lvOrder.getUserEmail()+"'";
			}
            if(ObjectUtils.isNotEmpty(lvOrder.getMemid())){
            	hql+=" and o.memid="+lvOrder.getMemid()+"";
            }
		}
		if(ObjectUtils.isNotEmpty(dto.get("storeFlag"))){
			hql+=" and o.storeId='"+dto.get("storeFlag")+"'";
		}
		Float num=0.0f;
		List<Double> list = dao.find(hql, null);
		if(list!=null&&list.size()>0){
			Double obj= list.get(0);
			if(obj!=null){
				num=Float.parseFloat(obj.toString());
			}
		}
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.totalPrice() method end*****");
		}
		return num;
	}

	/**
	 * 
	 * @Method: shopTypeState 
	 * @Description:  [商城分类统计]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-12-27 上午10:10:13]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-12-27 上午10:10:13]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public Pagination shopTypeState(Dto dto)throws Exception{
		String orderField = dto.getAsString("orderField");
		String orderDirection = dto.getAsString("orderDirection");
		SimplePage page = (SimplePage)dto.get("page");
		LvOrder lvOrder=(LvOrder) dto.get("lvOrder");
		LvShopProductType shopProductType=(LvShopProductType) dto.get("lvShopProductType");
		String hql="from LvShopProductType where 1=1 ";
		if(shopProductType!=null){
			if(ObjectUtils.isNotEmpty(shopProductType.getCode())){
				hql+=" and code='"+shopProductType.getCode()+"'";
			}
		}
		Finder finder = Finder.create(hql.toString());
		Pagination pageTmp=dao.find(finder, page.getPageNum(), page.getNumPerPage());
		List list=pageTmp.getList();
		List listTmp=new ArrayList();
		if(list!=null&&list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				Map map=new HashMap();
				LvShopProductType lvShopProductType=(LvShopProductType) list.get(i);
				if(lvShopProductType!=null){
				  hql="from LvProduct p where p.shopProductType='"+lvShopProductType.getCode()+"' and p.status<>-1 and p.isSupport=1";
				  List listProduct=dao.find(hql, null); 
				  Float totalPrice=0f;
				  Integer pnumTmp=0;
				  Float opriceTmp=0f;
				  for(int k=0;k<listProduct.size();k++){
					  Float usdTmp=0f;
					  Float rmbTmp=0f;
					  LvProduct lvProduct=(LvProduct) listProduct.get(k);
					  //USD
					  hql=" select sum(s.pnum) as pnum, ROUND(sum(s.oprice*s.pnum),2) as sales" +
						" from LvOrder o ,LvOrderDetails s ,LvProduct p,LvStore ls " +
						" where o.oid=s.orderId " +
						" and s.productCode=p.code " +
						" and o.storeId=ls.storeFlag " +
						" and p.status<>-1 " +
						" and p.isSupport=1 " +
						" and o.isdelete=0" +
						" and o.payStatus=1 and o.status!=3"+
					    " and s.productCode='"+lvProduct.getCode()+"'" +
					    " and o.currency='USD'";
					  if(lvOrder!=null){
					    if(ObjectUtils.isNotEmpty(lvOrder.getStartTime())&&ObjectUtils.isNotEmpty(lvOrder.getEndTime())){//下单时间
					      hql+=" and o.createTime>'"+lvOrder.getStartTime()+" 00:00:00'" +
					           " and o.createTime<'"+lvOrder.getEndTime()+" 23:59:59'";
					     }
					  }
					  List listUsd= dao.getMapListByHql(hql, null).getList();
					  if(listUsd!=null&&listUsd.size()>0){
						  Map param=(Map) listUsd.get(0);
						  if(ObjectUtils.isNotEmpty(param.get("pnum"))){
							  pnumTmp+=Integer.parseInt(String.valueOf(param.get("pnum")));
						  }
						  if(ObjectUtils.isNotEmpty(param.get("sales"))){
							  usdTmp=Float.parseFloat(String.valueOf(param.get("sales")));
						  }
					  }
					  
					  //RMB
					  hql=" select sum(s.pnum) as pnum, ROUND(sum(s.oprice*s.pnum),2) as sales" +
						" from LvOrder o ,LvOrderDetails s ,LvProduct p,LvStore ls " +
						" where o.oid=s.orderId " +
						" and s.productCode=p.code " +
						" and o.storeId=ls.storeFlag " +
						" and p.status<>-1 " +
						" and p.isSupport=1 " +
						" and o.isdelete=0" +
						" and o.payStatus=1 and o.status!=3"+
					    " and s.productCode='"+lvProduct.getCode()+"'" +
					    " and (o.currency='RMB' or o.currency='CNY')";
					  if(lvOrder!=null){
						 if(ObjectUtils.isNotEmpty(lvOrder.getStartTime())&&ObjectUtils.isNotEmpty(lvOrder.getEndTime())){//下单时间
						   hql+=" and o.createTime>'"+lvOrder.getStartTime()+" 00:00:00'" +
						        " and o.createTime<'"+lvOrder.getEndTime()+" 23:59:59'";
						 }
					  }
					  List listRmb= dao.getMapListByHql(hql, null).getList();
					  if(listRmb!=null&&listRmb.size()>0){
						  Map param=(Map) listRmb.get(0);
						  if(ObjectUtils.isNotEmpty(param.get("pnum"))){
							  pnumTmp+=Integer.parseInt(String.valueOf(param.get("pnum")));
						  }
						  if(ObjectUtils.isNotEmpty(param.get("sales"))){
							  rmbTmp=Float.parseFloat(String.valueOf(param.get("sales")));
						  }
					  }
					  totalPrice+=usdTmp+(rmbTmp*Constants.rateNumCNY);
				  }
				  if(ObjectUtils.isNotEmpty(pnumTmp)&&pnumTmp>0){
					     opriceTmp=totalPrice/pnumTmp;
				  }
				  //商城商品分类下无产品不显示统计数据
				  if(listProduct!=null&&listProduct.size()>0){
					  map.put("code", lvShopProductType.getCode());
					  map.put("shopTypeName", lvShopProductType.getTypeName());
					  map.put("shopTypeCount", listProduct.size());
					  map.put("sales", totalPrice);
					  map.put("oprice", opriceTmp);
					  map.put("pnum", pnumTmp);
					  listTmp.add(map);
				  }
				}
			}
		}
		pageTmp.setList(listTmp);
		pageTmp.setTotalCount(listTmp.size());
		return pageTmp;
	}
	
	
	
	/**
	 * 
	 * @Method: stateProduct 
	 * @Description:  [产品销售统计-产品销售统计数据(产品名称，店铺名称，销售量，销售额，均价)]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-30 下午04:20:19]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-30 下午04:20:19]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Pagination stateProduct(Dto dto) throws Exception {
		String orderField = dto.getAsString("orderField");
		String orderDirection = dto.getAsString("orderDirection");
		SimplePage page = (SimplePage)dto.get("page");
		LvOrder lvOrder=(LvOrder) dto.get("lvOrder");
		LvProduct product=(LvProduct) dto.get("lvProduct");
		Map<String ,Object> params=new HashMap<String ,Object>();
		
		StringBuilder hql = new StringBuilder("select DISTINCT s.product_code as productCode " +
				" from lv_order_details s " +
				" left join lv_order o on o.oid=s.order_id" +
	            " left join lv_product p on s.product_code=p.code" +
		        " where p.status<>-1 " +
		        " and p.is_support=1 " +
		        " and o.isdelete=0 " +
		        " and o.pay_status=1 and o.status!=3");
		if(lvOrder!=null){
			if(ObjectUtils.isNotEmpty(lvOrder.getStoreId())){
				hql.append(" and p.store_id='"+lvOrder.getStoreId()+"'");
			}
			if(ObjectUtils.isNotEmpty(lvOrder.getStartTime())&&ObjectUtils.isNotEmpty(lvOrder.getEndTime())){//下单时间
				hql.append(" and o.create_time>'"+lvOrder.getStartTime()+" 00:00:00'"+
            		 " and o.create_time<'"+lvOrder.getEndTime()+" 23:59:59' ");
            }
		}
		if(product!=null){
			if(ObjectUtils.isNotEmpty(product.getProductName())){
				hql.append(" and p.product_name like '%"+product.getProductName().trim()+"%'");
			}
		}
		//判断当前是商城入口，还是商家入口
		hql.append(StoreHelpUtil.getNoShopRelevanceString(dto.getAsString("flag"), "store_id", "p"));
		
		Session session = dao.getSessionFactory().openSession();
		SQLQuery query = session.createSQLQuery(hql.toString()).addScalar("productCode", Hibernate.STRING);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP); //将结果集设置成map的格式
		List listPage = query.list();
		session.close();
		
//		Pagination pageTmp=dao.getMapListByHql(hql.toString(), page.getPageNum(), page.getNumPerPage(), params);
		//根据币种区分销售额和均价重新计算，考虑到币种不同问题
		Pagination pageTmp=new Pagination();
		pageTmp.setTotalCount(listPage.size());
		pageTmp.setPageNum(page.getPageNum());
		pageTmp.setNumPerPage(page.getNumPerPage());
		Integer startNum=(page.getPageNum()-1)*page.getNumPerPage();
		Integer endNum;
		if(listPage.size()>=page.getPageNum()*page.getNumPerPage()){
			endNum=page.getPageNum()*page.getNumPerPage();
		}else{
			endNum=listPage.size();
		}
		
		List listTmp=new ArrayList();
		for (int i = startNum; i < endNum; i++) {
			Map map=(Map) listPage.get(i);
//			Long pnum=(Long) map.get("pnum");
			Float totalPrice=0f;
			Float usdTmp=0f;
			Float rmbTmp=0f;
			Float opriceTmp=0f;
			Integer pnumTmp=0;
			//根据产品code查询产品信息
			LvProduct lvProduct=new LvProduct();
			lvProduct.setCode(String.valueOf(map.get("productCode")));
			dto.put("lvProduct",lvProduct);
			lvProduct=lvProductService.getProduct(dto);
		
			
			Map<String, Object> paramsUSD = new HashMap<String, Object>();
			StringBuilder hql_usd = new StringBuilder(
					" select s.pnum as pnum,s.oprice as oprice,s.currency as currency "
							+ " from LvOrderDetails s,LvOrder o,LvProduct p "
							+ " where o.oid=s.orderId " 
							+ " and p.code=s.productCode" 
							+ " and o.isdelete=0"
							+ " and o.payStatus=1 and o.status!=3");
			if (lvOrder != null) {
				if (ObjectUtils.isNotEmpty(lvOrder.getStoreId())) {
					hql_usd.append(" and p.storeId=:storeId ");
					paramsUSD.put("storeId", lvOrder.getStoreId());
				}
				if (ObjectUtils.isNotEmpty(lvOrder.getStartTime())
						&& ObjectUtils.isNotEmpty(lvOrder.getEndTime())) {// 下单时间
					hql_usd.append(" and o.createTime>:startTime "
						+ " and o.createTime<:endTime ");
					paramsUSD.put("startTime",DateUtils.convertToDateTime(lvOrder.getStartTime()+ " 00:00:00"));
					paramsUSD.put("endTime",DateUtils.convertToDateTime(lvOrder.getEndTime()+ " 23:59:59"));
				}
			}
			if (product != null) {
				if (ObjectUtils.isNotEmpty(product.getProductName())) {
					hql_usd.append(" and p.productName like :productName ");
					paramsUSD.put("productName", "%"+ product.getProductName().trim() + "%");
				}
			}
			// 判断当前是商城入口，还是商家入口
			hql_usd.append(StoreHelpUtil.getNoShopRelevanceString(dto.getAsString("flag"), "storeId", "o"));
			hql_usd.append(" and s.productCode=:productCode");
			paramsUSD.put("productCode", String.valueOf(map.get("productCode")));
			List listUsd = dao.getMapListByHql(hql_usd.toString(), paramsUSD).getList();
			for (int j = 0; j < listUsd.size(); j++) {
				Map param=(Map)listUsd.get(j);
                Integer pnum=(Integer) param.get("pnum");
                Float oprice=(Float) param.get("oprice");
				String currency=(String) param.get("currency");
				if(currency.equals("RMB")||currency.equals("CNY")){
					totalPrice+=(oprice*Constants.rateNumCNY)*pnum;
					opriceTmp+=oprice*Constants.rateNumCNY;
				}else{
					totalPrice+=(oprice*pnum);
					opriceTmp+=oprice;
				}
				pnumTmp+=pnum;
			}
		
			//拼装数据：店铺标识,产品名称,销售金额,均价和销售量
		    map.put("storeId", lvProduct.getStoreId());
		    map.put("productName", lvProduct.getProductName());
			map.put("sales", String.format("%.2f",totalPrice));
			map.put("oprice",String.format("%.2f",opriceTmp/pnumTmp));
			map.put("pnum", pnumTmp);
			listTmp.add(map);
		} 
		pageTmp.setList(listTmp);
		return pageTmp; 
	}

	/**
	 * 
	 * ************************************************************************************
	 * 用户订单统计(用户email，昵称，未支付订单，已经支付订单，重复购买次数，订单总台数，总订单数，销售额，创建时间)
	 * ************************************************************************************
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-31 上午10:12:05]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-31 上午10:12:05]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Pagination stateUser(Dto dto) throws ServiceException {
		String flag=dto.getAsString("flag");//店铺标识
		String orderField = dto.getAsString("orderField");
		String orderDirection = dto.getAsString("orderDirection");
		SimplePage page = (SimplePage)dto.get("page");
		LvAccount lvAccount=(LvAccount) dto.get("lvAccount");
		String hql=" select DISTINCT o.userEmail as userEmail,la.nickname as nickname, la.createTime as createTime " +
			" from LvOrder o,LvAccount la " +
			" where o.userEmail=la.email " +
			" and la.status<>-1 " +
			" and o.isdelete=0 " +
			" and o.status!=3 ";
		if(lvAccount!=null){
			if(ObjectUtils.isNotEmpty(lvAccount.getStartTime())&&ObjectUtils.isNotEmpty(lvAccount.getEndTime())){
				hql+=" and la.createTime>='"+lvAccount.getStartTime()+" 00:00:00'" +
   		             " and la.createTime<'"+lvAccount.getEndTime()+" 23:59:59'";
			}
	    }
 		if(ObjectUtils.isNotEmpty(flag)){//店铺标识
			hql+=" and o.storeId='"+flag+"'";
		}
		Pagination pageTmp=dao.getMapListByHql(hql.toString(), page.getPageNum(), page.getNumPerPage(), null);
		//重新统计分页总条数，解决gv_core中统计的bug
		Integer totalCount=0;
		List list= dao.find(hql, null);
		if (list!=null&&list.size()>0) {
			totalCount=list.size();	
		}
		pageTmp.setTotalCount(totalCount);
		return pageTmp; 
	}
	
	/**
	 * 
	 * @Method: exportOrder 
	 * @Description:  [导出订单详情信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-17 上午09:38:55]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-17 上午09:38:55]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public List exportOrder(Dto dto)throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.exportOrder() method begin*****");
		}
		// TODO Auto-generated method stub
		List list = new ArrayList();
		String hql="FROM LvOrder o,LvOrderAddress a where o.oid=a.orderId and o.oid in (" + dto.getAsString("ids") + ")";
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
			
		List listPage = dao.find(hql, null);
		if (listPage != null) {

			String[] tempArray = null;
			LvOrder lvOrder = null;
			LvOrderAddress lvOrderAddress = null;
			// 增加表头
			String[] title = new String[28];
			title[0] = "下单时间";
			title[1] = "所属店铺";
			title[2] = "商城订单号";
			title[3] = "第三方订单号";
			title[4] = "收件人姓名";
			title[5] = "国家";
			title[6] = "洲/省";
			title[7] = "城市(县)";
			title[8] = "详细地址";
			title[9] = "邮编";
			title[10] = "收件人联系方式";
			title[11] = "产品信息";
			title[12] = "订单总金额";
			title[13] = "支付方式";
			title[14] = "第三方支付订单号";
			title[15] = "成功支付时间";
			title[16] = "订单备注";
			title[17] = "物流公司";
			title[18] = "物流单号";
			title[19] = "发货备注";
			title[20] = "退货备注";
			title[21] = "优惠券名称";
			title[22] = "优惠券金额";
			title[23] = "优惠码";
			title[24] = "Mac";
			title[25] = "Mac优惠金额";
			title[26] = "客服审核备注";
			title[27] = "财务审核备注";
			list.add(title);
			for (int i = 0; i < listPage.size(); i++) {
				tempArray = new String[28];
				Object[] obj = (Object[]) listPage.get(i);
				lvOrder = (LvOrder) obj[0];
				lvOrderAddress = (LvOrderAddress) obj[1];
				tempArray[0] = DateUtils.formatDate(lvOrder.getCreateTime(), "yyyy-MM-dd HH:mm:ss");
				
				//根据店铺编码获取店铺信息
				dto.put("storeFlag", lvOrder.getStoreId());
				LvStore store=lvStoreService.findStore(dto);
				if(ObjectUtils.isNotEmpty(store)){
					tempArray[1] =store.getName() ;
				}else{
					tempArray[1] ="" ;
				}
				tempArray[2] = lvOrder.getOid();
				if(ObjectUtils.isNotEmpty(lvOrder.getThirdOrderNum())){
					tempArray[3] = lvOrder.getThirdOrderNum();
				}else{
					tempArray[3] = "";
				}
				tempArray[4] = lvOrderAddress.getRelName();
				tempArray[5] = lvOrderAddress.getContryName();
				tempArray[6] = lvOrderAddress.getProvinceName();
				tempArray[7] = lvOrderAddress.getCityName();
				tempArray[8] = lvOrderAddress.getAdress();
				tempArray[9] = lvOrderAddress.getPostCode();
				if(lvOrderAddress.getMobile()!=null&&! "".equals(lvOrderAddress.getMobile().trim())){
				  tempArray[10] = lvOrderAddress.getMobile() ;
				}else{
				  tempArray[10] =lvOrderAddress.getTel();
				}
				
				//根据订单号查询订单详情中的优惠码信息
				String tmpProduct="";
				//根据订单号查询订单详情
				dto.put("orderId", lvOrder.getOid());
				List plist=this.lvOrderDetailsService.getDetailsByOid(dto);
				for(int amount=0;amount<plist.size();amount++){
					//根据订单详情，查询订单详情对于的产品，将产品信息拼接。	
					LvProduct lvProduct=new LvProduct();
					Map tmpMap=(Map) plist.get(amount);
					if(ObjectUtils.isNotEmpty(tmpMap.get("productCode"))){
						lvProduct.setCode(tmpMap.get("productCode").toString());
						dto.put("lvProduct", lvProduct);
						lvProduct=lvProductService.getProduct(dto);
						//拼接当前订单的产品信息
						if (lvProduct!=null) {
							if(plist.size()-1==amount){
								if(ObjectUtils.isNotEmpty(lvProduct.getProductName())){
									tmpProduct+=lvProduct.getProductName()+"("+tmpMap.get("pnum" )+")";
								}	
							}else{
								if(ObjectUtils.isNotEmpty(lvProduct.getProductName())){
									tmpProduct+=lvProduct.getProductName()+"("+tmpMap.get("pnum")+")"+",";
								}
							}
						}
					}
				}
				
				tempArray[11] = tmpProduct;//产品信息
				tempArray[12] = String.valueOf(lvOrder.getCurrency())+" "+String.valueOf(lvOrder.getTotalPrice());
				if (lvOrder.getPaymethod() ==Constants.PAY_METHOD_PAYPAL) {
					tempArray[13] = "Paypal支付";
				} else if (lvOrder.getPaymethod() == Constants.PAY_METHOD_ICC) {
					tempArray[13] = "国际信用卡";
				} else if (lvOrder.getPaymethod() == Constants.PAY_METHOD_ALIPAY) {
					tempArray[13] = "支付宝";
				} else if (lvOrder.getPaymethod() == Constants.PAY_METHOD_WESTERNUNION) {
					tempArray[13] = "西联汇款";
				} else if (lvOrder.getPaymethod() == Constants.PAY_METHOD_YOURSPAY) {
					tempArray[13] = "优仕支付";
				} else if (lvOrder.getPaymethod() == Constants.PAY_METHOD_KQ) {
					tempArray[13] = "快钱支付";
				} else if (lvOrder.getPaymethod() == Constants.PAY_METHOD_95EPAY) {
					tempArray[13] = "双乾支付";
				} else if (lvOrder.getPaymethod() == Constants.PAY_METHOD_GB) {
					tempArray[13] = "钱包支付";
				} else if (lvOrder.getPaymethod() == Constants.PAY_METHOD_PAYPALOUT_VISA) {
					tempArray[13] = "支付宝外卡visa支付";
				} else if (lvOrder.getPaymethod() == Constants.PAY_METHOD_PAYPALOUT_MASTER) {
					tempArray[13] = "支付宝外卡master支付";
				} else if (lvOrder.getPaymethod() == Constants.PAY_METHOD_PAYPALOUT_JCB) {
					tempArray[13] = "支付宝外卡jcb支付";
				}else if (lvOrder.getPaymethod() == Constants.PAY_METHOD_INSTALL) {
					tempArray[13] = "上门安装";
				}else if (lvOrder.getPaymethod() == Constants.PAY_METHOD_PAYDOLLAR){
					tempArray[13] = "PAYDOLLAR支付";
				}
				
				if (lvOrder.getThirdPartyOrder() != null) {
					tempArray[14] = lvOrder.getThirdPartyOrder();
				} else {
					tempArray[14] = "";
				}
				
				if (lvOrder.getOvertime() != null) {
					tempArray[15] = DateUtils.formatDate(lvOrder.getOvertime(),"yyyy-MM-dd HH:mm:ss");
				} else {
					tempArray[15] = "";
				}

				tempArray[16] = lvOrder.getOrderRemark();
				tempArray[17] = lvOrder.getExpressCompany();
				tempArray[18] = lvOrder.getExpressNum();
	
				tempArray[19] = lvOrder.getSendRemark();
				tempArray[20] = lvOrder.getBreakRemark();
				
				
				//根据订单号查询优惠券信息
				dto.put("orderId", lvOrder.getOid());
				List<LvOrderCoupon> couponList=lvOrderCouponService.findDetailsByOrderId(dto);
				if(ObjectUtils.isNotEmpty(couponList)){
					LvOrderCoupon orderCoupon=couponList.get(0);
					tempArray[21] = orderCoupon.getCouponName();//优惠券名称
					tempArray[22] = String.valueOf(lvOrder.getCurrency())+" "+String.valueOf(orderCoupon.getCouponPrice());//优惠券金额
					tempArray[23] = orderCoupon.getCouponCode();//优惠码
				}else{
					tempArray[21] = "";//优惠券名称
					tempArray[22] = "";//优惠券金额
					tempArray[23] = "";//优惠码
				}
				
				//根据订单号查询mac订单关联关系
				LvOrderMac lvOrderMac=lvOrderMacService.findByOrderId(lvOrder.getOid());
				if(ObjectUtils.isNotEmpty(lvOrderMac)){
					tempArray[24] = lvOrderMac.getMac();
					tempArray[25] =  String.valueOf(lvOrder.getCurrency())+" "+String.valueOf(lvOrderMac.getDiscountAmount());
				}else{
					tempArray[24] = "";
					tempArray[25] = "";
				}
				
				
				tempArray[26] = lvOrder.getServiceAuditContent();
				tempArray[27] = lvOrder.getFinanceAuditContent();
				list.add(tempArray);
			}

		}
		if (logger.isInfoEnabled()){
			logger.info("***** LvOrderServiceImpl.exportOrder() method end*****");
		}
		return list;
	}
	
	/**
	 * @throws Exception 
	 * 
	 * @Method: exportStateOrder 
	 * @Description:  [订单统计-导出订单统计数据]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-30 上午10:13:33]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-30 上午10:13:33]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public List exportStateOrder(Dto dto) throws Exception {
		    LvOrder lvOrder=(LvOrder) dto.get("lvOrder");
		    LvStore lvStore= (LvStore) dto.get("lvStore");
		    String ids=(String) dto.get("ids");
		    List list = new ArrayList();
			
			// 增加表头
			String[] title = new String[17];
			title[0] = "下单起始时间";
			title[1] = "下单结束时间";
			title[2] = "店铺名称";
			title[3] = "总下单数量";
			title[4] = "已付款订单数";
			title[5] = "未付款订单数";
			title[6] = "销售额";
			title[7] = "已完成订单数";
			title[8] = "已删除订单数";
			title[9] = "已退货订单数";
		    list.add(title);
		    if(ObjectUtils.isNotEmpty(ids)){
		    	 String hql="from LvStore t where isdel=0 and storeFlag in("+ids+")";
				 List tmpList=dao.find(hql, null);
				 for (int i = 0; i < tmpList.size(); i++) {
					String [] tempArray = new String[17];
					LvStore store=(LvStore) tmpList.get(i);
					dto.put("storeFlag", store.getStoreFlag());
					
					String countAll=this.countAll(dto).toString();
					String countPay=this.countPay(dto).toString();
					String countUnPay=this.countUnPay(dto).toString();
					String countFinish=this.countFinish(dto).toString();
					String countDelete=this.countDelete(dto).toString();
					String countBack=this.countBack(dto).toString();
					Float totalPriceUsd=this.totalPriceUsd(dto);
					Float totalPriceRmb=this.totalPriceRmb(dto);
					Float totalPrice=totalPriceUsd+(totalPriceRmb*Constants.rateNumCNY);
					
				    if(lvOrder!=null){
				       tempArray[0] = lvOrder.getStartTime();
					   tempArray[1] = lvOrder.getEndTime();
				    }
					tempArray[2] = store.getName();
					tempArray[3] = countAll;
					tempArray[4] = countPay;
					tempArray[5] = countUnPay;
					tempArray[6] = String.valueOf(totalPrice);
					tempArray[7] = countFinish;
					tempArray[8] = countDelete;
					tempArray[9] = countBack;
					list.add(tempArray);
				}
		    }
			return list;
	}

	/**
	 * 
	 * ************************************************************************************
	 * 产品销售统计
	 * ************************************************************************************
	 * 
	 * 
	 * @Method: exportStateProduct 
	 * @Description:  [产品销售统计-导出产品销售统计数据(产品名称,店铺名称,销售量,销售额,均价)]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-30 上午10:06:09]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-30 上午10:06:09]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws Exception 
	 */
	@Override
	public List exportStateProduct(Dto dto) throws Exception {
		SimplePage page = (SimplePage)dto.get("page");
		LvOrder lvOrder=(LvOrder) dto.get("lvOrder");
		String ids=(String) dto.get("ids");
		Map<String ,Object> params=new HashMap<String ,Object>();
		StringBuilder hql = new StringBuilder(" select ls.name as name,s.productCode as productCode,p.productName as productName," +
				" ROUND(sum(s.oprice*s.pnum),2) as sales,sum(s.pnum) as pnum,ROUND(avg(s.oprice),2) as oprice" +
				" from LvOrder o ,LvOrderDetails s ,LvProduct p,LvStore ls " +
				" where o.oid=s.orderId " +
				" and s.productCode=p.code  " +
				" and o.storeId=ls.storeFlag " +
				" and o.isdelete=0 "+
				" and p.status<>-1 " +
		        " and p.isSupport=1 " +
		        " and o.isdelete=0" +
		        " and o.payStatus=1 and o.status!=3");
		if(lvOrder!=null){
			if(ObjectUtils.isNotEmpty(lvOrder.getStoreId())){
				hql.append(" and p.storeId=:storeId");
				params.put("storeId",lvOrder.getStoreId());
			}
			if(ObjectUtils.isNotEmpty(lvOrder.getStartTime())&&ObjectUtils.isNotEmpty(lvOrder.getEndTime())){//下单时间
				hql.append(" and o.createTime>:startTime " +
	            	" and o.createTime<:endTime");
				params.put("startTime", DateUtils.convertToDateTime(lvOrder.getStartTime()+" 00:00:00"));
            	params.put("endTime", DateUtils.convertToDateTime(lvOrder.getEndTime()+" 23:59:59"));
	        }
		}
		if(ObjectUtils.isNotEmpty(ids)){
			hql.append("and s.productCode in("+ids+")");
		}
		//判断当前是商城入口，还是商家入口
		hql.append(StoreHelpUtil.getNoShopRelevanceString(dto.getAsString("flag"), "storeId", "o"));
		hql.append(" group by s.productCode");
		Pagination pageTmp=dao.getMapListByHql(hql.toString(),params);
		List listPage=pageTmp.getList();
		
		List list=new ArrayList();
		if (listPage != null) {
			String[] tempArray = null;
			// 增加表头
			String[] title = new String[17];
			title[0] = "产品名称";
			title[1] = "店铺名称";
			title[2] = "销售量";
			title[3] = "销售额";
			title[4] = "均价";
			list.add(title);
			for (int i = 0; i < listPage.size(); i++) {
				tempArray = new String[17];
				Map map=  (Map) listPage.get(i);
				
				Long pnum=(Long) map.get("pnum");
				Float totalPrice=0f;
				Float usdTmp=0f;
				Float rmbTmp=0f;
				Float opriceTmp=0f;
				//USD
				hql = new StringBuilder(" select ROUND(sum(s.oprice*s.pnum),2) as sales" +
				" from LvOrder o ,LvOrderDetails s ,LvProduct p,LvStore ls " +
				" where o.oid=s.orderId " +
				" and s.productCode=p.code " +
				" and o.storeId=ls.storeFlag " +
				" and p.status<>-1 " +
				" and p.isSupport=1 " +
				" and o.isdelete=0" +
				" and o.payStatus=1 and o.status!=3");
				if(lvOrder!=null){
				  if(ObjectUtils.isNotEmpty(lvOrder.getStoreId())){
					  hql.append(" and p.storeId='"+lvOrder.getStoreId()+"'");
				  }
				  if(ObjectUtils.isNotEmpty(lvOrder.getStartTime())&&ObjectUtils.isNotEmpty(lvOrder.getEndTime())){//下单时间
					  hql.append(" and o.createTime>'"+lvOrder.getStartTime()+" 00:00:00'" +
			               " and o.createTime<'"+lvOrder.getEndTime()+" 23:59:59'");
			         }
				}

				//判断当前是商城入口，还是商家入口
				hql.append(StoreHelpUtil.getNoShopRelevanceString(dto.getAsString("flag"), "storeId", "o"));
				hql.append(" and s.productCode='"+String.valueOf(map.get("productCode"))+"'" +
					 " and o.currency='USD'");
				List listUsd= dao.find(hql.toString(), null);
				if (ObjectUtils.isNotEmpty(listUsd)&&listUsd.size()>0&&listUsd.get(0)!=null) {
					usdTmp=Float.parseFloat(listUsd.get(0).toString());
				}
				
				
				//RMB
				hql = new StringBuilder(" select ROUND(sum(s.oprice*s.pnum),2) as sales" +
				" from LvOrder o ,LvOrderDetails s ,LvProduct p,LvStore ls " +
				" where o.oid=s.orderId " +
				" and s.productCode=p.code " +
				" and o.storeId=ls.storeFlag " +
				" and p.status<>-1 " +
				" and p.isSupport=1 " +
				" and o.isdelete=0" +
				" and o.payStatus=1 and o.status!=3");
				if(lvOrder!=null){
					if (ObjectUtils.isNotEmpty(lvOrder.getStoreId())) {
						hql.append(" and p.storeId='" + lvOrder.getStoreId() + "'");
					}
					if (ObjectUtils.isNotEmpty(lvOrder.getStartTime())&& ObjectUtils.isNotEmpty(lvOrder.getEndTime())) {// 下单时间
						hql.append( " and o.createTime>'" + lvOrder.getStartTime()+ " 00:00:00'" + 
						       " and o.createTime<'"+ lvOrder.getEndTime() + " 23:59:59'");
					}
				}
				
				//判断当前是商城入口，还是商家入口
				hql.append(StoreHelpUtil.getNoShopRelevanceString(dto.getAsString("flag"), "storeId", "o"));
				hql.append(" and s.productCode='"+String.valueOf(map.get("productCode"))+"'" +
					 " and (o.currency='RMB' or o.currency='CNY')");
				List listRmb= dao.find(hql.toString(), null);
				if (ObjectUtils.isNotEmpty(listRmb)&&listRmb.size()>0&&listRmb.get(0)!=null) {
					rmbTmp=Float.parseFloat(listRmb.get(0).toString());
				}
			    totalPrice=usdTmp+(rmbTmp*Constants.rateNumCNY);
			    if(ObjectUtils.isNotEmpty(pnum)&&pnum>0){
			    	opriceTmp=totalPrice/pnum;
			    }

				
				tempArray[0] = String.valueOf(map.get("productName"));
				tempArray[1] = String.valueOf(map.get("name"));
				tempArray[2] = String.valueOf(map.get("pnum"));
				tempArray[3] = String.valueOf(totalPrice);
				tempArray[4] = String.valueOf(opriceTmp);
				list.add(tempArray);
			}

		}
		
		return list;
	}

	
	/**
	 * 
	 * 
	 * @Method: exportUserOrder 
	 * @Description:  [导出用户订单统计]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-7-31 上午10:12:33]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-7-31 上午10:12:33]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws Exception 
	 */
	@Override
	public List exportUserOrder(Dto dto) throws Exception {
		SimplePage page = (SimplePage)dto.get("page");
		LvAccount lvAccount=(LvAccount) dto.get("lvAccount");
		LvOrder lvOrder=null;
		String ids=(String) dto.get("ids");
		String hql="select o.userEmail as userEmail,la.nickname as nickname,count(o.id)-1 as repeatNum," +
					" count(o.id) as totalNum,ROUND(sum(o.totalPrice),2) as totalPrice,min(la.createTime) as createTime,min(o.overtime) as overtime" +
					" from LvOrder o,LvAccount la" +
					" where o.userEmail=la.email " +
					" and la.status<>-1" +
					" and o.isdelete=0" +
					" and o.status!=3 ";
		if(lvAccount!=null){
			if(ObjectUtils.isNotEmpty(lvAccount.getStartTime())&&ObjectUtils.isNotEmpty(lvAccount.getEndTime())){
				hql+=" and la.createTime>='"+lvAccount.getStartTime()+" 00:00:00'" +
				     " and la.createTime<'"+lvAccount.getEndTime()+" 23:59:59'";
			    lvOrder=new LvOrder();
				lvOrder.setStartTime(lvAccount.getStartTime());
				lvOrder.setEndOverTime(lvAccount.getEndTime());
			}
		}
		if(ObjectUtils.isNotEmpty(ids)){
			hql+="and o.userEmail in("+ids+")";
		}
		if(ObjectUtils.isNotEmpty(dto.getAsString("flag"))){//店铺标示
			String storeList=StringUtil.stringFormart(dto.getAsString("flag"), ",");
			hql+=" and o.storeId in("+storeList+")";
		}
		hql+=" group by o.userEmail";
		
		Pagination pageTmp=dao.getMapListByHql(hql,null);
		List listPage=pageTmp.getList();
		
		//
		List list=new ArrayList();
		if (listPage != null) {
			String[] tempArray = null;
			// 增加表头
			String[] title = new String[9];
			title[0] = "注册日期";
			title[1] = "Email";
			title[2] = "下单数量";
			title[3] = "已经支付订单数";
			//title[4] = "订购台数";
			title[4] = "销售额";
			title[5] = "重复购买次数";
			title[6] = "注册时间到第一次支付时间";
			title[7] = "第二次购买时间于第一次购买时间差";
			list.add(title);			
			for (int i = 0; i < listPage.size(); i++) {
				tempArray = new String[9];
				Map map=  (Map) listPage.get(i);
				Date loginTime=(Date) map.get("createTime");
				Date firstdDate=null;
				Date secondDate=null;
				//未支付订单统计
				if(lvOrder==null){
				   lvOrder=new LvOrder();
				}
				lvOrder.setUserEmail(String.valueOf(map.get("userEmail")));
				dto.put("lvOrder", lvOrder);
				Integer countPay= (Integer)this.countPay(dto);
				lvOrder.setUserEmail(map.get("userEmail").toString());
	    		//统计总订单数
	    		Integer countAll=(Integer) this.countAll(dto);
	    		//销售额
	    		Float totalPriceUsd=(Float)this.totalPriceUsd(dto);//美元销售累计
	    		Float totalPriceRmb=(Float) this.totalPriceRmb(dto);//人民币销售累计
	    		Float totalPrice=totalPriceUsd+(totalPriceRmb*Constants.rateNumCNY);
				
				//处理第二次购买与第一次购买时间差
				hql="select overtime from LvOrder where userEmail='"+map.get("userEmail")+"' and payStatus=1 and isdelete=0 order by overtime asc";
				List pay_orders=dao.find(hql, null);
				for(int j=0;j<pay_orders.size();j++){
					//注册时间到第一次支付时间，第二次购买时间于第一次购买时间差
					Date overtime=(Date) pay_orders.get(j);
					if (firstdDate==null) {
						firstdDate=overtime;
					}else {
						if (overtime!=null) {
							if (overtime.before(firstdDate)) {
								secondDate=firstdDate;
								firstdDate=overtime;
							}else {
								secondDate=(secondDate==null||overtime.before(secondDate))?overtime:secondDate;
							}
						}
					}
				}
				
				
				long firstdDay = 0;
				if (firstdDate != null && loginTime != null &&firstdDate.getTime() >loginTime.getTime()) {
					firstdDay = (firstdDate.getTime() - loginTime.getTime()) / (24 * 60 * 60 * 1000);
				}
				long secondDay = 0;
				if (firstdDate != null&&secondDate != null && secondDate.getTime()>firstdDate.getTime()) {
					secondDay = (secondDate.getTime() - firstdDate.getTime()) / (24 * 60 * 60 * 1000);
				}
				//填充表格数据内容
				tempArray[0] = String.valueOf(map.get("createTime"));
				tempArray[1] = String.valueOf(map.get("userEmail"));
				tempArray[2] = String.valueOf(countAll);
				tempArray[3] = String.valueOf(countPay);
				//tempArray[4] = String.valueOf("");
				tempArray[4] = String.valueOf(totalPrice);
				tempArray[5] = String.valueOf(countAll-1);
				tempArray[6] = String.valueOf(firstdDay);
				tempArray[7] = String.valueOf(secondDay);
				list.add(tempArray);
			}

		}
			
	return list;
	}

	/**
	 * 
	 * @Method: exportShopTypeState 
	 * @Description:  [导出商品分类统计数据]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-12-27 下午01:52:18]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-12-27 下午01:52:18]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	public List exportShopTypeState(Dto dto)throws Exception {
		LvOrder lvOrder=(LvOrder) dto.get("lvOrder");
		LvShopProductType shopProductType=(LvShopProductType) dto.get("lvShopProductType");
		String ids=(String) dto.get("ids");
		String hql="from LvShopProductType where 1=1 ";
		if(shopProductType!=null){
			if(ObjectUtils.isNotEmpty(shopProductType.getCode())){
				hql+=" and code='"+shopProductType.getCode()+"'";
			}
		}
		if(ObjectUtils.isNotEmpty(ids)){
			hql+=" and code in("+ids+")";
		}
		Finder finder = Finder.create(hql.toString());
		Pagination pageTmp=dao.find(finder);
		List listPage=pageTmp.getList();
		List list=new ArrayList();
		if(listPage!=null&&listPage.size()>0){
			String[] tempArray = null;
			// 增加表头
			String[] title = new String[9];
			title[0] = "商品分类";
			title[1] = "商品数量";
			title[2] = "销售量";
			title[3] = "销售额(USD)";
			title[4] = "均价(USD)";
			list.add(title);
			
			for (int i = 0; i < listPage.size(); i++) {
				Map map=new HashMap();
				LvShopProductType lvShopProductType=(LvShopProductType) listPage.get(i);
				if(lvShopProductType!=null){
				  hql="from LvProduct where shopProductType='"+lvShopProductType.getCode()+"'";
				  List listProduct=dao.find(hql, null); 
				  Float totalPrice=0f;
				  Integer pnumTmp=0;
				  Float opriceTmp=0f;
				  for(int k=0;k<listProduct.size();k++){
					  Float usdTmp=0f;
					  Float rmbTmp=0f;
					  LvProduct lvProduct=(LvProduct) listProduct.get(k);
					  //USD
					  hql=" select sum(s.pnum) as pnum, ROUND(sum(s.oprice*s.pnum),2) as sales" +
						" from LvOrder o ,LvOrderDetails s ,LvProduct p,LvStore ls " +
						" where o.oid=s.orderId " +
						" and s.productCode=p.code " +
						" and o.storeId=ls.storeFlag " +
						" and p.status<>-1 " +
						" and p.isSupport=1 " +
						" and o.isdelete=0" +
						" and o.payStatus=1 and o.status!=3"+
					    " and s.productCode='"+lvProduct.getCode()+"'" +
					    " and o.currency='USD'";
					  if(lvOrder!=null){
					    if(ObjectUtils.isNotEmpty(lvOrder.getStartTime())&&ObjectUtils.isNotEmpty(lvOrder.getEndTime())){//下单时间
					      hql+=" and o.createTime>'"+lvOrder.getStartTime()+" 00:00:00'" +
					           " and o.createTime<'"+lvOrder.getEndTime()+" 23:59:59'";
					     }
					  }
					  List listUsd= dao.getMapListByHql(hql, null).getList();
					  if(listUsd!=null&&listUsd.size()>0){
						  Map param=(Map) listUsd.get(0);
						  if(ObjectUtils.isNotEmpty(param.get("pnum"))){
							  pnumTmp+=Integer.parseInt(String.valueOf(param.get("pnum")));
						  }
						  if(ObjectUtils.isNotEmpty(param.get("sales"))){
							  usdTmp=Float.parseFloat(String.valueOf(param.get("sales")));
						  }
					  }
					  
					  //RMB
					  hql=" select sum(s.pnum) as pnum, ROUND(sum(s.oprice*s.pnum),2) as sales" +
						" from LvOrder o ,LvOrderDetails s ,LvProduct p,LvStore ls " +
						" where o.oid=s.orderId " +
						" and s.productCode=p.code " +
						" and o.storeId=ls.storeFlag " +
						" and p.status<>-1 " +
						" and p.isSupport=1 " +
						" and o.isdelete=0" +
						" and o.payStatus=1 and o.status!=3"+
					    " and s.productCode='"+lvProduct.getCode()+"'" +
					    " and (o.currency='RMB' or o.currency='CNY')";
					  if(lvOrder!=null){
						 if(ObjectUtils.isNotEmpty(lvOrder.getStartTime())&&ObjectUtils.isNotEmpty(lvOrder.getEndTime())){//下单时间
						   hql+=" and o.createTime>'"+lvOrder.getStartTime()+" 00:00:00'" +
						        " and o.createTime<'"+lvOrder.getEndTime()+" 23:59:59'";
						 }
					  }
					  List listRmb= dao.getMapListByHql(hql, null).getList();
					  if(listRmb!=null&&listRmb.size()>0){
						  Map param=(Map) listRmb.get(0);
						  if(ObjectUtils.isNotEmpty(param.get("pnum"))){
							  pnumTmp+=Integer.parseInt(String.valueOf(param.get("pnum")));
						  }
						  if(ObjectUtils.isNotEmpty(param.get("sales"))){
							  rmbTmp=Float.parseFloat(String.valueOf(param.get("sales")));
						  }
					  }
					  totalPrice+=usdTmp+(rmbTmp*Constants.rateNumCNY);
				  }
				  if(ObjectUtils.isNotEmpty(pnumTmp)&&pnumTmp>0){
					     opriceTmp=totalPrice/pnumTmp;
				  }
				  
				  
				  if(listProduct!=null&&listProduct.size()>0){
					  tempArray = new String[9];
					  //填充表格数据内容
					  
					  tempArray[0] = lvShopProductType.getTypeName();
					  tempArray[1] = String.valueOf(listProduct.size());
					  tempArray[2] = String.valueOf(pnumTmp);
					  tempArray[3] = String.valueOf(totalPrice);
					  tempArray[4] = String.valueOf(opriceTmp);
					  list.add(tempArray);
				  }
				}
			}
		}
		return list;
	}
	/**
	 * 
	 * @Method: vaildCoupon 
	 * @Description:  [一句话描述该类的功能]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-9-21 下午02:58:36]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-9-21 下午02:58:36]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Dto vaildCoupon(Dto dto) throws ParseException {
		Date toDay = new Date();
		LvCoupon lvCoupon=null;
		LvCouponType lvCouponType=null;
		String couponCode=dto.getAsString("couponCode");
		//获取优惠码信息
		String hql = "from LvCoupon where couponCode=:couponCode";
		HashMap param = new HashMap();
		param.put("couponCode", couponCode);
		List<LvCoupon> lvCouponList = (List) dao.find(hql, param);
		if (lvCouponList != null && lvCouponList.size() > 0) {
			lvCoupon = lvCouponList.get(0);
			//根据优惠码获取优惠券信息
			if(ObjectUtils.isNotEmpty(lvCoupon)){
				hql="from LvCouponType where code=:code ";
				param.clear();
				param.put("code", lvCoupon.getCouponTypeCode());
				lvCouponType= (LvCouponType) dao.findUnique(hql, param);
				if(ObjectUtils.isEmpty(lvCouponType)){//优惠券不存在
					dto.put("couponFlag", 2);
				}else if(lvCouponType.getStatus()==0){//优惠券停用
					dto.put("couponFlag", 3);
				}else if(toDay.getTime()>lvCouponType.getEndTime().getTime()){//优惠券已经过期
					dto.put("couponFlag", 4);
				}else if(lvCoupon.getCouponStatus()==2){//优惠码已使用
					dto.put("couponFlag", 5);
				}
			}else{//优惠码不存在
				dto.put("couponFlag", 1);
			}
		}
		dto.put("lvCoupon", lvCoupon);
		dto.put("lvCouponType", lvCouponType);
		return dto;
	}
	
	
    /**
     * 
     * @Method: createThirdOrderNum 
     * @Description:  [创建第三方订单号规则]  
     * @Author:       [liaoxj]     
     * @CreateDate:   [2015-3-20 下午5:24:38]   
     * @UpdateUser:   [liaoxj]     
     * @UpdateDate:   [2015-3-20 下午5:24:38]   
     * @UpdateRemark: [说明本次修改内容]  
     * @param storeId 店铺标志
     * @return String 订单号
     * @throws Exception 
     */
	public String createOrderNum(String storeId,String markFlag) throws Exception {
		//订单编号规则变更：B+店铺id（2位）+时间（14位）+随机码（5位）
    	Integer id=Constants.STORE_FLAG_TO_IDS.get(storeId);
    	String mark=null;
    	if(ObjectUtils.isNotEmpty(id)){
    		if(id<10){
        		mark=markFlag+"0"+id.toString();
        	}else{
        		mark=markFlag+id.toString();
        	}
    	}else{
    		mark=markFlag;
    	}
    	String oid = OrderHelp.createOrderId(mark);
    	//根据订单号查询订单是否存在，如果存在重新生成
    	Boolean isFlag=false;
    	LvOrder order= this.getOrder(oid);
    	if(ObjectUtils.isNotEmpty(order)){
    		isFlag=true;
    	}
    	
    	while(isFlag){
    		this.createOrderNum(storeId, markFlag);
    	}
		return oid;
	}
		
}
