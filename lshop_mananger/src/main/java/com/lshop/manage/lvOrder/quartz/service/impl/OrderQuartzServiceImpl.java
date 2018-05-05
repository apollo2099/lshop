package com.lshop.manage.lvOrder.quartz.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.exception.DAOException;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvOrder;
import com.lshop.common.pojo.logic.LvOrderAddress;
import com.lshop.common.pojo.logic.LvOrderComment;
import com.lshop.common.pojo.logic.LvOrderDetails;
import com.lshop.common.pojo.logic.LvProductComment;
import com.lshop.common.util.CodeUtils;
import com.lshop.common.util.Constants;
import com.lshop.common.util.DateUtils;
import com.lshop.manage.lvOrder.quartz.service.OrderQuartzService;
import com.lshop.manage.lvOrder.service.LvOrderService;
import com.lshop.ws.web.bz.order.BzExpressService;
import com.lshop.ws.web.bz.order.ExpressInfo;
import com.lshop.ws.web.bz.order.ExpressInfos;
import com.lshop.ws.web.bz.order.MultiExpressResponse;
import com.lshop.ws.web.creatent.order.ThreeOrderSendDto;
import com.lshop.ws.web.creatent.order.ThreeOrderSendDtoResposne;
import com.lshop.ws.web.creatent.order.WSThreeOrderService;
/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.manage.lvOrder.service.impl.OrderQuartzServiceImpl.java]  
 * @ClassName:    [LvOrderQuartzServiceImpl]   
 * @Description:  [订单状态需要增加两个时间戳——(1)已发货，未确认收货状态订单如果没有进行任何操作，第15个自然日订单状态自动变为已完成；
 *                 (3)定时同步商务未发货的物流信息]
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2013-2-26 下午02:27:02]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2013-2-26 下午02:27:02]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v3.0]
 */
@Service("OrderQuartzService")
public class OrderQuartzServiceImpl implements OrderQuartzService {
	private static final Log logger	= LogFactory.getLog(OrderQuartzServiceImpl.class);
	@Resource 
	private HibernateBaseDAO dao;
	@Resource
	private LvOrderService lvOrderService;
	@Resource
	private BzExpressService bzExpressService;
	@Resource
    private WSThreeOrderService wsThreeOrderService;
	@PostConstruct
	public void init() throws ServiceException {
		new Thread(this).start();	
	}

	@Override
	public void start(Dto dto) throws ServiceException {
		new Thread(this).start();
	}

	@Override
	public void run() {
		updateOrderShipped();//自动完成规则
		synOrderLogisticsFromBZ();//从商务系统同步物流信息
	}
	

	
	/**
	 * 已发货，未确认收货状态订单如果没有进行任何操作，第15个自然日订单状态自动变为已完成
	 */
	public void updateOrderShipped(){
 		String curTime=""; //当前时间
 		String curSetTime=""; //当前可操作时间，即当前时间-15
 		
 		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
 		
 		//当前时间
 		Calendar cal= Calendar.getInstance();
 		curTime=format.format(cal.getTime());
 		
 		//当前可操作时间，即当前时间-15
 		cal.add(Calendar.DATE, -15);
 		curSetTime=format.format(cal.getTime());
 		String hql="from LvOrder o where o.status=1 and o.payStatus=1 and o.isdelete=0 and o.modifyTime<='"+curSetTime+"'";
 		List<LvOrder> list=dao.find(hql, null);
		for (LvOrder lvOrder : list) {
			hql="update LvOrder set status=4 where oid='"+lvOrder.getOid()+"'";
			dao.update(hql, null);
		}
	}
	
	/**
	 * 同步商务已经发货订单物流信息到华扬系统中
	 */
	public void synOrderLogisticsFromBZ(){
 		try {
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			//当前时间
			Calendar cal= Calendar.getInstance();
			String curTime=format.format(cal.getTime());
			//当前可操作时间，即当前时间-1
			cal.add(Calendar.DATE, -1);
			String startDate=format.format(cal.getTime());
			MultiExpressResponse multiExpressResponse=bzExpressService.findLogisticsByDate(startDate+" 00:00:00", startDate+" 23:59:59");
			if(multiExpressResponse!=null){
				if(ObjectUtils.isNotEmpty(multiExpressResponse.getResult().getResultCode())&&multiExpressResponse.getResult().getResultCode().equals("1")){
					multiExpressResponse.getResult().getResultCode();
					ExpressInfos exInfos=multiExpressResponse.getExpressInfos();
					if(exInfos!=null){
						List<ExpressInfo> list=exInfos.getExpressInfo();
						for (ExpressInfo expressInfo : list) {
							if(expressInfo.getStatus().equals("1")){
								LvOrder lvOrder=lvOrderService.getOrder(expressInfo.getOid());
								if(lvOrder!=null&&lvOrder.getStatus()==Constants.ORDER_STATUS_0){
									
								BaseDto dto=new BaseDto();
								LvOrder tempOrder=new LvOrder();
								tempOrder.setOid(expressInfo.getOid());
								tempOrder.setExpressCompany(expressInfo.getExpressCompany());
								tempOrder.setExpressName(expressInfo.getExpressName());
								tempOrder.setExpressNum(expressInfo.getExpressNum());
								tempOrder.setShipTime(DateUtils.convertToDateTime(expressInfo.getShipTime()));
								tempOrder.setStatus(Short.parseShort(expressInfo.getStatus()));
								dto.put("lvOrder", tempOrder);
								dto.put("uName", "BZSystem");
								dto.put("operate", "BZSYSTEM_SEND_LOGISTICS");
								//调用同步物流实现
								lvOrderService.doOrder(dto);	
							}
							}
						}
					}
				}
			}
		} catch (DAOException e) {
			logger.error(e.getMessage(),e);
		} catch (IllegalArgumentException e) {
			logger.error(e.getMessage(),e);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	

}
