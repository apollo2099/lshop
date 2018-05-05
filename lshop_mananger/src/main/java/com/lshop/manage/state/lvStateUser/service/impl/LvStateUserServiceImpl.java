package com.lshop.manage.state.lvStateUser.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionServlet;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.datastructure.page.SimplePage;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.BeanUtils;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvStateUser;
import com.lshop.common.util.DateUtils;
import com.lshop.manage.lvOrder.service.LvOrderService;
import com.lshop.manage.lvOrder.service.impl.LvOrderServiceImpl;
import com.lshop.manage.state.lvStateUser.service.LvStateUserQuartzService;
import com.lshop.manage.state.lvStateUser.service.LvStateUserService;

@Service("LvStateUserService")
public class LvStateUserServiceImpl implements LvStateUserService {
	private static final Log logger	= LogFactory.getLog(LvOrderServiceImpl.class);
	@Resource 
	private HibernateBaseDAO dao;
	@Resource
	private LvOrderService lvOrderService;
	@Resource
	private LvStateUserQuartzService lvStateUserQuartzService;
	private Dto dto;
	
	/**
	 * 
	 * @Method: getList 
	 * @Description:  [查询用户订单统计信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-12-3 上午11:34:57]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-12-3 上午11:34:57]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Pagination getList(Dto dto) throws ServiceException {
		String orderField = dto.getAsString("orderField");
		String orderDirection = dto.getAsString("orderDirection");
		SimplePage page = (SimplePage)dto.get("page");
		LvStateUser lvStateUser=(LvStateUser) dto.get("lvStateUser");
		Map<String ,Object> params=new HashMap<String ,Object>();
		StringBuilder sql = new StringBuilder("from LvStateUser ls where 1=1 ");
		if(lvStateUser!=null){
			if(ObjectUtils.isNotEmpty(lvStateUser.getStartTime())&&ObjectUtils.isNotEmpty(lvStateUser.getEndTime())){
				sql.append(" and ls.registerTime>=:startTime" +
   		             " and ls.registerTime<:endTime");
				params.put("startTime", DateUtils.convertToDateTime(lvStateUser.getStartTime()+" 00:00:00"));
				params.put("endTime", DateUtils.convertToDateTime(lvStateUser.getEndTime()+" 23:59:59"));
			}
			if(ObjectUtils.isNotEmpty(lvStateUser.getUserEmail())){
				sql.append(" and userEmail like :userEmail ");
				params.put("userEmail", "%"+lvStateUser.getUserEmail()+"%");
			}
			if(ObjectUtils.isNotEmpty(lvStateUser.getNickName())){
				sql.append(" and nickName like :nickName ");
				params.put("nickName", "%"+lvStateUser.getNickName()+"%");
			}			
		}

		if(ObjectUtils.isNotEmpty(orderField)&&ObjectUtils.isNotEmpty(orderDirection)){
			sql.append(" order by "+ orderField+" "+orderDirection);
		}
		Finder finder = Finder.create(sql.toString());
		finder.setParams(params);
		return dao.find(finder, page.getPageNum(), page.getNumPerPage());
	}

	/**
	 * 
	 * @Method: synchronousData 
	 * @Description:  [数字同步：先删除临时表，在根据统计数据将数据添加到临时表中]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-12-3 上午11:26:45]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-12-3 上午11:26:45]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public Boolean synchronousData(Dto dto) {
		
		lvStateUserQuartzService.start(dto);
		return true;
	}
	
	/**
	 * 
	 * @Method: exportUserOrder 
	 * @Description:  [导出用户统计订单信息]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2012-12-3 上午11:34:35]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2012-12-3 上午11:34:35]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @throws
	 */
	@Override
	public List exportUserOrder(Dto dto) throws Exception {
		String ids = (String) dto.get("ids");
		List list = new ArrayList();
		LvStateUser lvStateUser = (LvStateUser) dto.get("lvStateUser");
		if (ObjectUtils.isNotEmpty(ids)) {
			String hql = "from LvStateUser ls where id in(" + ids + ")";
			if (lvStateUser != null) {
				if (ObjectUtils.isNotEmpty(lvStateUser.getStartTime())
						&& ObjectUtils.isNotEmpty(lvStateUser.getEndTime())) {
					hql += " and ls.registerTime>='" + lvStateUser.getStartTime()+ " 00:00:00'\n" + 
					       " and ls.registerTime<'"+ lvStateUser.getEndTime() + " 23:59:59'\n";
				}
				if(ObjectUtils.isNotEmpty(lvStateUser.getUserEmail())){
					hql+=" and userEmail = '"+lvStateUser.getUserEmail()+"'";
				}
				if(ObjectUtils.isNotEmpty(lvStateUser.getNickName())){
					hql+=" and nickName like '%"+lvStateUser.getNickName()+"%'";
				}
			}
//			if (ObjectUtils.isNotEmpty(dto.getAsString("flag"))) {// 店铺标示
//				hql += " and ls.storeId='" + dto.getAsString("flag") + "'";
//			}
			List<LvStateUser> listPage = dao.find(hql, null);

			if (listPage != null) {
				String[] tempArray = null;
				// 增加表头
				String[] title = new String[9];
				title[0] = "注册日期";
				title[1] = "Email";
				title[2] = "下单数量";
				title[3] = "已经支付订单数";
				title[4] = "销售额";
				title[5] = "重复购买次数";
				title[6] = "注册时间到第一次支付时间";
				title[7] = "第二次购买时间于第一次购买时间差";
				list.add(title);
				for (int i = 0; i < listPage.size(); i++) {
					LvStateUser user = listPage.get(i);
					tempArray = new String[9];
					// 填充表格数据内容
					tempArray[0] = user.getRegisterTime().toString();
					tempArray[1] = user.getUserEmail();
					tempArray[2] = String.valueOf(user.getCountAll());
					tempArray[3] = String.valueOf(user.getCountPay());
					tempArray[4] = String.valueOf(user.getTotalPrice());
					tempArray[5] = String.valueOf(user.getCountAll() - 1);
					tempArray[6] = String.valueOf(user.getFirstdDay());
					tempArray[7] = String.valueOf(user.getSecondDay());
					list.add(tempArray);

				}

			}

		}

		return list;
	}


}
