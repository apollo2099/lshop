package com.lshop.manage.lvOrder.quartz.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.lshop.common.pojo.logic.LvOrder;
import com.lshop.common.pojo.logic.LvOrderAddress;
import com.lshop.common.pojo.logic.LvOrderComment;
import com.lshop.common.pojo.logic.LvOrderDetails;
import com.lshop.common.pojo.logic.LvProductComment;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.lvOrder.quartz.service.OrderCommentQuartzService;

/**
 * 
 * 系统自动评价功能实现
 * Simple to Introduction  
 * @ProjectName:  [lshop_mananger] 
 * @Package:      [com.lshop.manage.lvOrder.quartz.service.impl.OrderCommentQuartzServiceImpl.java]  
 * @ClassName:    [OrderCommentQuartzServiceImpl]   
 * @Description:  [ *(2)已收货，待评价状态订单如果没有进行任何操作，48小时后订单变为已完成(默认好评)]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2014-7-22 下午3:34:48]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2014-7-22 下午3:34:48]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v2.0]
 */
@Service("OrderCommentQuartzService")
public class OrderCommentQuartzServiceImpl implements OrderCommentQuartzService {
	private static final Log logger	= LogFactory.getLog(OrderCommentQuartzServiceImpl.class);
	@Resource 
	private HibernateBaseDAO dao;

	@Override
	public void run() {
		new Thread(this).start();	
		
	}

	@Override
	public void init() throws ServiceException {
		// TODO Auto-generated method stub
		updateOrderIsReceipt();//自动评价规则
	}

	
	/**
	 * 已收货，待评价状态订单如果没有进行任何操作，48小时后订单变为已完成(默认好评)
	 */
	public void updateOrderIsReceipt(){
		String curTime=""; //当前时间
 		String curSetTime=""; //当前可操作时间，即当前时间-48小时
 		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Calendar cal= Calendar.getInstance();
 		curTime=format.format(cal.getTime());
 		
 		//当前可操作时间，即当前时间-48小时
 		cal.add(Calendar.HOUR, -48);
 		curSetTime=format.format(cal.getTime());
 		System.out.println(curSetTime);
 		String hql="from LvOrder o where o.status=2 and o.payStatus=1 and o.isdelete=0 and o.modifyTime<='"+curSetTime+"'";
 		List<LvOrder> list=dao.find(hql, null);
		for (LvOrder lvOrder : list) {
			hql="update LvOrder set status=4 where oid='"+lvOrder.getOid()+"'";
			dao.update(hql, null);
			
			//新增订单评论
			LvOrderComment loc=new LvOrderComment();
			if(lvOrder.getStoreId().equals("tvpadcn")||lvOrder.getStoreId().equals("bscn")){
				loc.setContent("好评 ！");
			}else if(lvOrder.getStoreId().equals("tvpaden")||lvOrder.getStoreId().equals("bsen")){
				loc.setContent("positive criticism");
			}
			
			loc.setScore(5);
			loc.setGrade(Short.parseShort("3"));
			loc.setOrderId(lvOrder.getOid());
			loc.setIsDelete(Short.parseShort("0"));
			loc.setIsCheck(Short.parseShort("1"));//
			loc.setCode(CodeUtils.getCode());
			loc.setCreateTime(lvOrder.getCreateTime());
			loc.setStoreId(lvOrder.getStoreId());
			dao.save(loc);
			
			//根据订单号查询订单详情(新增产品评论)
			hql="select o from LvOrderAddress o where o.orderId='"+lvOrder.getOid()+"' ";
			LvOrderAddress lvOrderAddress=(LvOrderAddress) dao.findUnique(hql, null);
			hql="select o from LvOrderDetails o where o.orderId='"+lvOrder.getOid()+"' ";
			List<LvOrderDetails> detailsList= dao.find(hql, null);
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
					if(lvOrder.getStoreId().equals("tvpadcn")||lvOrder.getStoreId().equals("bscn")){
						lvProductComment.setContent("好评 ！");
					}else if(lvOrder.getStoreId().equals("tvpaden")||lvOrder.getStoreId().equals("bsen")){
						lvProductComment.setContent("positive criticism");
					}
					lvProductComment.setScore(5);
					lvProductComment.setGrade(Short.parseShort("3"));
					lvProductComment.setIsDelete(Short.parseShort("0"));
					lvProductComment.setCode(CodeUtils.getCode());
					lvProductComment.setCreateTime(lvOrder.getCreateTime());
					lvProductComment.setStoreId(lvOrder.getStoreId());
					dao.save(lvProductComment);
				}
			}
		}
	}
}
