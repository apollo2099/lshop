/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.vbtrade.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.service.impl.BaseServiceImpl;
import com.lshop.common.pojo.logic.LvRecharge;
import com.lshop.common.util.CommonUtil;
import com.lshop.common.util.DateUtil;
import com.lshop.ws.util.WSBossHelp;
import com.lshop.manage.vbtrade.service.TuserOrderService;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */
@Service("TuserOrderService")
public class TuserOrderServiceImpl extends BaseServiceImpl implements TuserOrderService {
	
	@Resource
	private HibernateBaseDAO dao;

	/**
	 * 分页查询
	 * 
	 * @param dto
	 * @return
	 * @throws ServiceException
	 */
	public Pagination findPage(Dto dto) throws ServiceException {
		Pagination page = (Pagination) dto.get("page");
		
		String orderNo = dto.getAsString("orderNo");
		String account = dto.getAsString("account");
		Integer payStatus = dto.getAsInteger("payStatus");
		Integer orderStatus = dto.getAsInteger("orderStatus");
		String startTime = dto.getAsString("startTime");
		String endTime = dto.getAsString("endTime");
		
		Map<String, Object> param = new HashMap<String, Object>();
		/*StringBuilder hql = new StringBuilder(
				"SELECT rnum AS rnum, accounts AS accounts, createDate AS createDate," +
				" money AS money, currency AS currency, vbNum AS vbNum, payStatus AS payStatus," +
				" status AS status" +
				" FROM LvRecharge WHERE 1=1");*/
		
		StringBuilder hql = new StringBuilder("FROM LvRecharge WHERE 1=1");
		
		if (payStatus != null) {
			param.put("payStatus", payStatus);
			hql.append(" AND payStatus=:payStatus");
		}
		if (orderStatus != null) {
			param.put("status", orderStatus);
			hql.append(" AND status=:status");
		}
		if (DateUtil.parseDate(startTime) != null) {
			Timestamp time = DateUtil.getDayMinTime(startTime);
			param.put("startTime", time);
			hql.append(" AND createDate >= :startTime");
		}
		if (DateUtil.parseDate(endTime) != null) {
			Timestamp time = DateUtil.getDayMaxTime(endTime);
			param.put("endTime", time);
			hql.append(" AND createDate <= :endTime");
		}
		if (StringUtils.isNotBlank(orderNo)) {
			String keyword = CommonUtil.filterLike(orderNo.trim());
			param.put("rnum", "%"+ keyword +"%");
			hql.append(" AND rnum LIKE :rnum");
		}
		if (StringUtils.isNotBlank(account)) {
			String keyword = CommonUtil.filterLike(account.trim());
			param.put("accounts", "%"+ keyword +"%");
			hql.append(" AND accounts LIKE :accounts");
		}
		hql.append(" ORDER BY createDate DESC");
		page = dao.getMapListByHql(hql.toString(), page.getPageNum(), page.getNumPerPage(), param);
		return page;
	}
	
	/**
	 * 充值V币
	 */
	public Dto charge(Dto dto) throws ServiceException {
		String orderNo = dto.getAsString("orderNo");	// 订单号
		
		Map params = new HashMap();
		params.put("rnum", orderNo);
		String hql = "FROM LvRecharge WHERE rnum=:rnum";
		LvRecharge charge = (LvRecharge) dao.findUnique(hql, params);
		
		if (charge.getPayStatus() == 1 && charge.getStatus() == 0) {
			String paydate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(charge.getPayDate());
			if (charge.getIsForOther() == 0) {	// 本号充值
				dto = WSBossHelp.getInstance().recharge(charge.getAccounts(),
						charge.getRnum(), charge.getVbNum() + "", 0, null, 1, 1, paydate);
			} else if (charge.getIsForOther() == 1) {// 给他人充值
				dto = WSBossHelp.getInstance().recharge(charge.getOpAccount(),
						charge.getRnum(), charge.getVbNum() + "", 1, charge.getAccounts(), 1, 1, paydate);
			}
			Integer status = dto.getAsInteger("status");
			if (status != null && status == 1) {	// 充值成功
				dao.update("update LvRecharge set status=1 where rnum=:rnum", params);// 发货成功
			}
		}
		return dto;
	}
	
}
