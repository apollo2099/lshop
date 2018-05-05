package com.lshop.manage.state.lvStateUser.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.DAOException;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvOrder;
import com.lshop.common.pojo.logic.LvStateUser;
import com.lshop.common.util.CodeUtils;
import com.lshop.common.util.Constants;
import com.lshop.common.util.PropertiesHelper;
import com.lshop.manage.lvOrder.service.LvOrderService;
import com.lshop.manage.state.lvStateUser.service.LvStateUserQuartzService;

@Service("LvStateUserQuartzService")
public class LvStateUserQuartzServiceImpl implements LvStateUserQuartzService {
	@Resource 
	private HibernateBaseDAO dao;
	@Resource
	private LvOrderService lvOrderService;
	private Dto dto;

	@Override
	public void init() throws ServiceException {
		// TODO Auto-generated method stub
		String userStateFlag=(String) PropertiesHelper.getConfiguration().getProperty("com.lshop.manage.state.lvStateUser.stateUserFlag");
		if(!(ObjectUtils.isNotEmpty(userStateFlag)&&userStateFlag.equals("1"))){
			new Thread(this).start();
		}
	}

	@Override
	public void start(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		this.dto=dto;
		new Thread(this).start();
	}

	@Override
	public void run() {
		PropertiesHelper.getConfiguration().setProperty("com.lshop.manage.state.lvStateUser.stateUserFlag", "1");
		if(dto!=null&&ObjectUtils.isNotEmpty(dto.getAsString("flag"))){
			// 清空临时表的数据
//			String hql = "delete from LvStateUser where storeId='" + dto.getAsString("flag") + "'";
//			dao.delete(hql, null);
			// 清空临时表的数据
			String hql = "delete from LvStateUser ";
			dao.delete(hql, null);
		}else{
			// 清空临时表的数据
			String hql = "delete from LvStateUser ";
			dao.delete(hql, null);
		}
		
		try {
		    String hql = " select DISTINCT o.userEmail as userEmail,la.nickname as nickname, la.createTime as createTime "
					+ " from LvOrder o,LvAccount la "
					+ " where o.userEmail=la.email "
					+ " and la.status<>-1 "
					+ " and o.isdelete=0 " 
					+ " and o.status!=3 ";
			if(dto==null){
				dto=new BaseDto();
			}
			Pagination pageTmp = dao.getMapListByHql(hql, null);
			List listPage = pageTmp.getList();
			if (listPage != null && listPage.size() > 0) {
				for (int i = 0; i < listPage.size(); i++) {
					LvOrder lvOrder = new LvOrder();
					Map map = (Map) listPage.get(i);
					Date firstdDate = null;
					Date secondDate = null;
					Date loginTime = (Date) map.get("createTime");

					lvOrder.setUserEmail(map.get("userEmail").toString());
					dto.put("lvOrder", lvOrder);
					// 统计支付订单数目
					Integer countPay = lvOrderService.countPay(dto);
					// 统计未支付订单数目
					Integer countUnPay = lvOrderService.countUnPay(dto);
					// 统计总订单数
					Integer countAll = lvOrderService.countAll(dto);
					// 销售额
					Float totalPriceUsd = lvOrderService.totalPriceUsd(dto);// 美元销售累计
					Float totalPriceRmb = lvOrderService.totalPriceRmb(dto);// 人民币销售累计
					Float totalPrice = totalPriceUsd+ (totalPriceRmb * Constants.rateNumCNY);

					// 处理第二次购买与第一次购买时间差
					hql = "select overtime from LvOrder where userEmail='"
							+ map.get("userEmail")
							+ "' and payStatus=1 and isdelete=0 order by overtime asc";
					List pay_orders = dao.find(hql, null);
					for (int j = 0; j < pay_orders.size(); j++) {
						// 注册时间到第一次支付时间，第二次购买时间于第一次购买时间差
						Date overtime = (Date) pay_orders.get(j);
						if (firstdDate == null) {
							firstdDate = overtime;
						} else {
							if (overtime != null) {
								if (overtime.before(firstdDate)) {
									secondDate = firstdDate;
									firstdDate = overtime;
								} else {
									secondDate = (secondDate == null || overtime.before(secondDate)) ? overtime: secondDate;
								}
							}
						}
					}

					long firstdDay = 0;
					if (firstdDate != null && loginTime != null&& firstdDate.getTime() > loginTime.getTime()) {
						firstdDay = (firstdDate.getTime() - loginTime.getTime())/ (24 * 60 * 60 * 1000);
					}
					long secondDay = 0;
					if (firstdDate != null && secondDate != null&& secondDate.getTime() > firstdDate.getTime()) {
						secondDay = (secondDate.getTime() - firstdDate.getTime())/ (24 * 60 * 60 * 1000);
					}

					
					
					
					
					LvStateUser lvStateUser = new LvStateUser();
					lvStateUser.setUserEmail(map.get("userEmail").toString());
					lvStateUser.setRegisterTime((Date) map.get("createTime"));
					lvStateUser.setNickName(String.valueOf(map.get("nickname")));
					lvStateUser.setCountPay(countPay);
					lvStateUser.setCountUnPay(countUnPay);
					lvStateUser.setCountAll(countAll);
					lvStateUser.setTotalPrice(totalPrice);
					lvStateUser.setFirstdDay(firstdDay);
					lvStateUser.setSecondDay(secondDay);
					lvStateUser.setCode(CodeUtils.getCode());
					lvStateUser.setCreateTime(new Date());
					dao.save(lvStateUser);

				}
			}
			
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PropertiesHelper.getConfiguration().setProperty("com.lshop.manage.state.lvStateUser.stateUserFlag", "0");
	}

}
