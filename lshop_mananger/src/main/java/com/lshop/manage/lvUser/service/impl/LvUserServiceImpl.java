/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvUser.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.datastructure.page.SimplePage;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.Finder;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvCoupon;
import com.lshop.common.pojo.logic.LvEmailTpl;
import com.lshop.common.pojo.logic.LvUser;
import com.lshop.common.pojo.logic.LvUserCoupon;
import com.lshop.common.util.DateUtils;
import com.lshop.manage.lvUser.service.LvUserService;
import com.lshop.manage.lvUser.service.bean.LvUserBean;
import com.lshop.manage.lvUser.service.bean.UserCouponBean;



/**
 * @author 好视网络-软件二部：唐迪
 * @version 1.0
 * @since 1.0
 */
@Service("LvUserService")
public class LvUserServiceImpl implements LvUserService {

	@Resource
	private HibernateBaseDAO dao;
	private LvUser lvUser;

	public List<LvUser> findAll(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<String[]> onExportByIds(Dto dto) {
		String ids = dto.getAsString("ids");
		if (ids == null || "".equals(ids.trim())) {
			return null;
		}
//		StringBuilder sql = new StringBuilder("select email,min(createTime) from LvUser t where 1=1 AND userType=0 GROUP BY email");
		StringBuilder sql = new StringBuilder("select email,min(createTime) from LvUser t where 1=1 AND userType=0  AND id  in(").append(ids).append("  ) GROUP BY email");
		List<String[]> list = new ArrayList<String[]>();
		list.add(new String[] { "序号", "注册日期", "E-mail", "下单数量", "已支付订单数量", "订购台数", "销售额($)", "重复购买次数", "注册时间到第一次支付时间差（以天为单位）", "第二次购买时间与第一次购买时间时间差（以天为单位）" });
		List<Object[]> objs = dao.find(sql.toString(), null);
		Map param = new HashMap(2);
		if (objs != null && !objs.isEmpty()) {
			String[] obj = null;
			int i = 0;

			for (Object[] u : objs) {
				param.clear();
				String email = u[0] + "";
				Date loginTime = (Date) u[1];
				obj = new String[10];
				obj[0] = ++i + "";
				obj[1] = DateUtils.formatDate(loginTime, "yyyy-MM-dd HH:mm:ss");
				obj[2] = email;
				
				param.put("userEmail", email);
				String hql = "select count(id) from LvOrder where userEmail=:userEmail and isdelete=0";
				Finder fin = Finder.create(hql);
				Integer notPlayNum = dao.countQueryResult(fin, param);
				obj[3] = notPlayNum + "";
				
				hql="select pnum,totalPrice,currency,overtime from LvOrder where userEmail=:userEmail and payStatus=1 and isdelete=0";
				List<Object[]> pay_orders=dao.find(hql, param);
				long payordercount=0;
				int pnumCount=0;
				Float totalPriceCount=0f;
				Date firstdDate=null;
				Date secondDate=null;
				if (pay_orders!=null&&!pay_orders.isEmpty()) {
					payordercount=pay_orders.size();
					for (Object[] objects : pay_orders) {
						int pnum=(Integer) objects[0];
						Float totalPrice=(Float) objects[1];
						String currency=(String) objects[2];
						Date overtime=(Date) objects[3];
						pnumCount+=pnum;
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
						if (currency!=null&&"RMB".equals(currency.trim())) {
							totalPrice=(totalPrice/6.4f);
							totalPrice= Math.round(totalPrice * 100) / 100f;
						}
						totalPriceCount+=totalPrice;
						
					}
				}
				long firstdDay = 0;
				if (firstdDate != null && loginTime != null) {
					firstdDay = (firstdDate.getTime() - loginTime.getTime()) / (24 * 60 * 60 * 1000);
				}
				long secondDay = 0;
				if (firstdDate != null&&secondDate != null) {
					secondDay = (secondDate.getTime() - firstdDate.getTime()) / (24 * 60 * 60 * 1000);
				}
			

				obj[4] = payordercount + "";
				obj[5] = pnumCount+ "";
				obj[6] = totalPriceCount + "";
				obj[7] =(payordercount>1?payordercount-1:0) + "";
				obj[8] = firstdDay + "";
				obj[9] = secondDay + "";
				
				list.add(obj);
			}

		}
		return list;
	}

	public Pagination findPage(Dto dto) throws ServiceException {
		String orderField = dto.getAsString("orderField");
		String orderDirection = dto.getAsString("orderDirection");
		SimplePage page = (SimplePage) dto.get("page");
		lvUser = (LvUser) dto.get("model");
		String startdate = (String) dto.get("startdate");
		String enddate = (String) dto.get("enddate");
		// 组装SQL,时间类型的没组装，如果有需要自己添加SQL
		// 字符串类型为模糊查询，不区分大小写
		StringBuilder sql = new StringBuilder("select t from LvUser t where userType=0 ");
		Map parms = new HashMap();
		if (ObjectUtils.isNotEmpty(lvUser.getId())) {
			sql.append(" and  t.id = :id ");
			parms.put("id", lvUser.getId());
		}
		if (ObjectUtils.isNotEmpty(lvUser.getEmail())) {
			sql.append(" and  t.email like :email ");
			parms.put("email", lvUser.getEmail());
		}
		if (ObjectUtils.isNotEmpty(lvUser.getNickname())) {
			sql.append(" and  t.nickname like :nickname ");
			parms.put("nickname", lvUser.getNickname());
		}

		if (ObjectUtils.isNotEmpty(startdate)) {
			sql.append(" and  t.createTime >='").append(startdate).append(" 00:00'");
		}
		if (ObjectUtils.isNotEmpty(enddate)) {
			sql.append(" and  t.createTime <='").append(enddate).append(" 23:59'");
		}
		if (ObjectUtils.isNotEmpty(orderField)) {
			sql.append(" order by t." + orderField + " " + orderDirection);
		}
		Finder finder = Finder.create(sql.toString());
		finder.setParams(parms);

		Pagination pag = dao.find(finder, page.getPageNum(), page.getNumPerPage());
		if (pag.getList() != null && pag.getList().size() > 0) {
			LvUserBean userBean = null;
			List list = new ArrayList();
			for (LvUser u : (List<LvUser>) pag.getList()) {
				userBean = new LvUserBean();
				userBean.setId(u.getId());
				userBean.setEmail(u.getEmail());
				userBean.setCreateTime(u.getCreateTime());
				userBean.setNickname(u.getNickname());
				userBean.setLasttime(u.getLastTime());
				Map myparam = new HashMap();
				myparam.put("userEmail", u.getEmail());
				String hql = "select count(id) from LvOrder where userEmail=:userEmail  and isdelete=0 and payStatus!=1";
				Finder fin = Finder.create(hql);
				Integer notPlayNum = dao.countQueryResult(fin, myparam);

				userBean.setNotPlayNum(notPlayNum);
				hql = "select count(id),sum(pnum) from LvOrder where userEmail=:userEmail and payStatus=1 and isdelete=0";
				Object[] onPlayNum = (Object[]) dao.findUnique(hql, myparam);
				userBean.setOnPlayNum((Long) onPlayNum[0]);
				userBean.setRplayNum(userBean.getOnPlayNum() > 0 ? userBean.getOnPlayNum() - 1 : 0);
				userBean.setPnums((Long) onPlayNum[1]);
				list.add(userBean);
			}
			pag.setList(list);
		}
		return pag;
	}

	public LvUser get(Dto dto) throws ServiceException {
		lvUser = (LvUser) dto.get("model");
		lvUser = (LvUser) dao.load(LvUser.class, lvUser.getId());
		return lvUser;
	}

	public void del(Dto dto) throws ServiceException {
		lvUser = get(dto);
		dao.delete(lvUser);
	}

	public void delList(Dto dto) throws ServiceException {
		String ids = dto.getAsString("ids");
		if (ids == null || "".equals(ids.trim())) {
			return;
		}
		String hql = "DELETE LvUser WHERE id IN (" + ids + ")";
		dao.delete(hql, null);
	}

	public LvUser save(Dto dto) throws ServiceException {
		lvUser = (LvUser) dto.get("model");
		// lvUser.setCode(SEQUtil.getSEQ("lvUser".toUpperCase()));
		// lvUser.setStatus(1);
		dao.save(lvUser);
		return lvUser;
	}

	public LvUser update(Dto dto) throws ServiceException {
		lvUser = (LvUser) dto.get("model");
		dao.update(lvUser);
		return lvUser;
	}

	public List<LvEmailTpl> getEmailTplList(Dto dto) throws ServiceException {
		// TODO Auto-generated method stub
		return this.dao.find("FROM LvEmailTpl where tplKey like 'COUPON%'", null);
	}

	public Integer addUserCoupon(Dto dto) {
		// TODO Auto-generated method stub
		LvUserCoupon lvUserCoupon=(LvUserCoupon) dto.getDefaultPo();
		if(ObjectUtils.isNotEmpty(dto.getAsString("flag"))){//店铺标示
			lvUserCoupon.setStoreId(dto.getAsString("flag"));
		}else{
			lvUserCoupon.setStoreId("tvpadcn");
		}
		return (Integer) this.dao.save(lvUserCoupon);
	}

	public Pagination findUserCoupon(Dto dto) {
		SimplePage page = (SimplePage) dto.get("page");
		// TODO Auto-generated method stub
		Finder finder = Finder.create("FROM LvUserCoupon where uid=" + dto.getAsInteger("uid"));
		Pagination pag = dao.find(finder, page.getPageNum(), page.getNumPerPage());
		if (pag != null) {
			List<LvUserCoupon> couponList = (List<LvUserCoupon>) pag.getList();
			List<UserCouponBean> beanList = new ArrayList();
			if (couponList != null && couponList.size() > 0) {
				UserCouponBean bean = null;
				for (LvUserCoupon userCoupon : couponList) {
					Finder f = Finder.create("FROM LvCouponAid where  ccode ='" + userCoupon.getCouponCode() + "'");
					Integer num = dao.countQueryResult(f, null);
					Finder b = Finder.create("FROM LvCoupon where  couponCode ='" + userCoupon.getCouponCode() + "'" + " and oid<>''");
					Integer pnum = dao.countQueryResult(b, null);
					LvCoupon lvCoupon = (LvCoupon) dao.findUnique("FROM LvCoupon where couponCode ='" + userCoupon.getCouponCode() + "'", null);
					bean = new UserCouponBean();
					bean.setCount(num + pnum);
					bean.setCouponCode(userCoupon.getCouponCode());
//					bean.setCouponType(lvCoupon.getCouponType() + "");
//					bean.setCouponName(lvCoupon.getCouponName());
					beanList.add(bean);
				}
				pag.setList(beanList);
			}
		}
		return pag;
	}

}
