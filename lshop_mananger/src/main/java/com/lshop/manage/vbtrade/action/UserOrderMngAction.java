/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.vbtrade.action;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.core.web.action.BaseAction;
import com.gv.core.util.ObjectUtils;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */
 @Controller("UserOrderMngAction")
@Scope("prototype")
@SuppressWarnings("all")
public class UserOrderMngAction extends BaseAction{
	private static final Log logger	= LogFactory.getLog(UserOrderMngAction.class);

	/**
	 * 列表
	 */
	public String list() throws ActionException {
		String orderNo = request.getParameter("orderNo");
		String account = request.getParameter("account");
		String payStatus = request.getParameter("payStatus");
		String orderStatus = request.getParameter("orderStatus");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		
		dto.put("page", page);
		dto.put("orderNo", orderNo);
		dto.put("account", account);
		dto.put("payStatus", payStatus);
		dto.put("orderStatus", orderStatus);
		dto.put("startTime", startTime);
		dto.put("endTime", endTime);
		
		page = (Pagination) this.doService("TuserOrderService", "findPage", dto);
		
		request.setAttribute("orderNo", orderNo);
		request.setAttribute("account", account);
		request.setAttribute("payStatus", payStatus);
		request.setAttribute("orderStatus", orderStatus);
		request.setAttribute("startTime", startTime);
		request.setAttribute("endTime", endTime);
		return LIST;
	}
	
	/**
	 * V币充值
	 */
	public String charge() throws ActionException {
		String orderNo = request.getParameter("orderNo");
		dto.put("orderNo", orderNo);
		dto = (Dto) this.doService("TuserOrderService", "charge", dto);
		Integer  status = dto.getAsInteger("status");
		String message = dto.getAsString("message");
		
		if (status != null && status == 1) {
			json.setMessage("操作成功！");
			json.setCallbackType(null);
			json.setNavTabId("");
		} else {
			json.setStatusCode(json.STATUS_CODE_ERROR);
			json.setMessage("操作失败！"+ message);
		}
		return AJAX;
	}
	
}