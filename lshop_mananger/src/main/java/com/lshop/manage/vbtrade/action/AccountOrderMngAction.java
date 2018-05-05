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
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */
 @Controller("AccountOrderMngAction")
@Scope("prototype")
@SuppressWarnings("all")
public class AccountOrderMngAction extends BaseAction{
	private static final Log logger	= LogFactory.getLog(AccountOrderMngAction.class);

	/**
	 * 列表
	 */
	public String list() throws ActionException {
		String account = request.getParameter("account");
		
		dto.put("page", page);
		dto.put("account", account);
		
		page = (Pagination) this.doService("AccountOrderService", "findPage", dto);
		
		request.setAttribute("account", account);
		return LIST;
	}
	
	/**
	 * 充值明细列表
	 */
	public String buyList() throws ActionException {
		String account = request.getParameter("account");
		String buyStartTime = request.getParameter("buyStartTime");
		String buyEndTime = request.getParameter("buyEndTime");
		
		dto.put("page", page);
		dto.put("account", account);
		dto.put("buyStartTime", buyStartTime);
		dto.put("buyEndTime", buyEndTime);
		
		page = (Pagination) this.doService("AccountOrderService", "buyList", dto);
		
		request.setAttribute("account", account);
		request.setAttribute("buyStartTime", buyStartTime);
		request.setAttribute("buyEndTime", buyEndTime);
		return "buyList";
	}
	
	/**
	 * 消费明细列表
	 */
	public String consumeList() throws ActionException {
		String account = request.getParameter("account");
		String mac = request.getParameter("mac");
		String consumeStartTime = request.getParameter("consumeStartTime");
		String consumeEndTime = request.getParameter("consumeEndTime");
		
		dto.put("page", page);
		dto.put("account", account);
		dto.put("mac", mac);
		dto.put("consumeStartTime", consumeStartTime);
		dto.put("consumeEndTime", consumeEndTime);
		
		page = (Pagination) this.doService("AccountOrderService", "consumeList", dto);
		
		request.setAttribute("account", account);
		request.setAttribute("mac", mac);
		request.setAttribute("consumeStartTime", consumeStartTime);
		request.setAttribute("consumeEndTime", consumeEndTime);
		return "consumeList";
	}
	
	
	
}