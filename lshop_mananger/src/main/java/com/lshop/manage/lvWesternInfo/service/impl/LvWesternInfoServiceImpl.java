package com.lshop.manage.lvWesternInfo.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.datastructure.page.SimplePage;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.BeanUtils;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvEmailTpl;
import com.lshop.common.pojo.logic.LvOrder;
import com.lshop.common.pojo.logic.LvOrderAddress;
import com.lshop.common.pojo.logic.LvWesternInfo;
import com.lshop.common.util.Constants;
import com.lshop.common.util.DateUtils;
import com.lshop.common.util.StoreHelpUtil;
import com.lshop.emailsend.service.EmailSendService;
import com.lshop.manage.lvWesternInfo.service.LvWesternInfoService;
/**
 * 
 *  
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvWesternInfo.service.impl.LvWesternInfoServiceImpl.java]  
 * @ClassName:    [LvWesternInfoServiceImpl]   
 * @Description:  [西联汇款管理-数据库访问层]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2012-8-3 上午11:23:47]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2012-8-3 上午11:23:47]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v1.0] 
 *
 */
@Service("LvWesternInfoService")
public class LvWesternInfoServiceImpl implements LvWesternInfoService {
	private static final Log logger	= LogFactory.getLog(LvWesternInfoServiceImpl.class);
	@Resource 
	private HibernateBaseDAO dao;
	@Resource
	private EmailSendService emailSendService;
	/**
	 * 
	 * @Method: getList 
	 * @Description:  [查询西联汇款分页列表信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-3 上午11:24:15]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-3 上午11:24:15]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Pagination getList(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvWesternInfoServiceImpl.getList() method end*****");
		}
		String orderField = dto.getAsString("orderField");
		String orderDirection = dto.getAsString("orderDirection");
		SimplePage page = (SimplePage)dto.get("page");
		LvWesternInfo lvWesternInfo=(LvWesternInfo)dto.get("lvWesternInfo");
		Map<String ,Object> params=new HashMap<String ,Object>();
		//组装SQL,时间类型的没组装，如果有需要自己添加SQL
		//字符串类型为模糊查询，不区分大小写
        StringBuilder hql = new StringBuilder("from LvWesternInfo t where 1=1");
		if(lvWesternInfo!=null){
			if(ObjectUtils.isNotEmpty(lvWesternInfo.getOid())){//订单编号
				hql.append(" and oid like :oid");
			}
			if(ObjectUtils.isNotEmpty(lvWesternInfo.getStatus())){//汇款状态
				hql.append(" and status=:status");		
			}
			if(ObjectUtils.isNotEmpty(lvWesternInfo.getTransferTime())){//汇款时间
				hql.append(" and transferTime like :transferTime");
			}
			if(ObjectUtils.isNotEmpty(lvWesternInfo.getMtcn())){//MTCN
				hql.append(" and mtcn like :mtcn");	
			}
			params=BeanUtils.describeForHQL(lvWesternInfo);
		}
		//判断当前是商城入口，还是商家入口
		hql.append(StoreHelpUtil.getNoShopRelevanceString(dto.getAsString("flag"), "storeId", "t"));
		
		if(ObjectUtils.isNotEmpty(orderField)&&ObjectUtils.isNotEmpty(orderDirection)){
			hql.append(" order by "+ orderField+" "+orderDirection);
		}
		Finder finder = Finder.create(hql.toString());
		finder.setParams(params);
		return dao.find(finder, page.getPageNum(), page.getNumPerPage());
	}

	/**
	 * 
	 * @Method: truePay 
	 * @Description:  [西联汇款确认付款操作]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-8-3 上午11:24:39]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-8-3 上午11:24:39]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public void truePay(Dto dto) throws ServiceException {
		if (logger.isInfoEnabled()){
			logger.info("***** LvWesternInfoServiceImpl.truePay() method begin*****");
		}
		String storeFlag="tvpadcn";
		if(ObjectUtils.isNotEmpty(dto.getAsString("flag"))){//店铺标示
			storeFlag=dto.getAsString("flag");
		}
		//修改西联汇款信息为确认已经付款状态
		LvWesternInfo lvWesternInfo = (LvWesternInfo)dto.get("lvWesternInfo");
		lvWesternInfo.setStatus((short)1);//确认付款
		dao.update("update LvWesternInfo set status="+lvWesternInfo.getStatus()+" where id="+lvWesternInfo.getId(),null);
		
		
		Map param=new HashMap();
		param.put("oid", lvWesternInfo.getOid());
		param.put("overtime", new Date());
		//修改订单支付状态
		dao.update("update LvOrder set payStatus=1,overtime=:overtime where oid=:oid",param);
		
		//发送支付成功提醒邮件
		param.clear();
		String hql="FROM LvOrder WHERE oid=:orderId AND payStatus=1 AND status=0";
		param.put("orderId", lvWesternInfo.getOid());
		LvOrder order = (LvOrder) dao.findUnique(hql, param);
		
		hql = "FROM LvOrderAddress WHERE orderId=:orderId";
		LvOrderAddress lvOrderAdress = (LvOrderAddress) dao.findUnique(hql, param);
		
		Map map = new HashMap();
		map.put("storeId", order.getStoreId());
	    hql="FROM LvEmailTpl WHERE tplKey='PAY_OK_EMAIL_TEMP' and storeId=:storeId";
		LvEmailTpl emailTpl=(LvEmailTpl) dao.findUnique(hql, map);
		String mailtemp=emailTpl.getEn()+emailTpl.getZh();
		mailtemp=mailtemp.replace("{oid}",order.getOid());
		mailtemp=mailtemp.replace("{paymethod}", Constants.PAY_METHODS.get(order.getPaymethod()).toString());
		mailtemp=mailtemp.replace("{totalPrice}", order.getTotalPrice().toString());
		mailtemp=mailtemp.replace("{relname}", lvOrderAdress.getRelName());
		mailtemp=mailtemp.replace("{currency}", order.getCurrency());
		mailtemp=mailtemp.replace("{sendtime}",DateUtils.formatDate(new Date(),null));
		
		dto.put("mallFlag", Constants.STORE_TO_MALL_SYSTEM.get(order.getStoreId()));
		dto.put("userEmail", order.getUserEmail());
		dto.put("title", emailTpl.getEnTitle().replace("{oid}",order.getOid()));
		dto.put("content", mailtemp);
		Boolean emailFlag=(boolean)emailSendService.sendEmailNotice(dto);//发邮件

		if (logger.isInfoEnabled()){
			logger.info("***** LvWesternInfoServiceImpl.truePay() method end*****");
		}
	}

}
