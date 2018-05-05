/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.vbtrade.service.impl;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ServiceException;
import com.gv.core.service.impl.BaseServiceImpl;
import com.lshop.common.util.DateUtil;
import com.lshop.manage.vbtrade.service.AccountOrderService;
import com.lshop.ws.util.WSBossHelp;

/**
 * @author 好视网络-网站研发部：
 * @version 1.0
 * @since 1.0
 */
@Service("AccountOrderService")
public class AccountOrderServiceImpl extends BaseServiceImpl implements AccountOrderService {
	
	/**
	 * 分页查询
	 */
	public Pagination findPage(Dto dto) throws ServiceException {
		Pagination page = (Pagination) dto.get("page");
		String account = dto.getAsString("account");
		
		page = WSBossHelp.getInstance().accountList(account, null,
				null, null, page.getPageNum(),
				page.getNumPerPage());
		
		return page;
	}
	
	/**
	 * 充值交易记录
	 */
	public Pagination buyList(Dto dto) throws ServiceException {
		Pagination page = (Pagination) dto.get("page");
		String account = dto.getAsString("account");
		String buyStartTime = dto.getAsString("buyStartTime");
		String buyEndTime = dto.getAsString("buyEndTime");
		
		if (DateUtil.parseDate(buyStartTime) != null) {
			buyStartTime = DateUtil.getDayMinTime(buyStartTime).toString();
			buyStartTime = buyStartTime.substring(0, buyStartTime.length() - 2);
		}
		if (DateUtil.parseDate(buyEndTime) != null) {
			buyEndTime = DateUtil.getDayMaxTime(buyEndTime).toString();
			buyEndTime = buyEndTime.substring(0, buyEndTime.length() - 2);
		}
		
		page = WSBossHelp.getInstance().buyList(account, buyStartTime,
				buyEndTime, null, null, page.getPageNum(), page.getNumPerPage());

		return page;
	}
	
	/**
	 * 消费交易记录
	 */
	public Pagination consumeList(Dto dto) throws ServiceException {
		Pagination page = (Pagination) dto.get("page");
		String account = dto.getAsString("account");
		String mac = dto.getAsString("mac");
		String consumeStartTime = dto.getAsString("consumeStartTime");
		String consumeEndTime = dto.getAsString("consumeEndTime");
		
		if (DateUtil.parseDate(consumeStartTime) != null) {
			consumeStartTime = DateUtil.getDayMinTime(consumeStartTime).toString();
			consumeStartTime = consumeStartTime.substring(0, consumeStartTime.length() - 2);
		}
		if (DateUtil.parseDate(consumeEndTime) != null) {
			consumeEndTime = DateUtil.getDayMaxTime(consumeEndTime).toString();
			consumeEndTime = consumeEndTime.substring(0, consumeEndTime.length() - 2);
		}
		
		page = WSBossHelp.getInstance().consumeList(account, mac, consumeStartTime, 
				consumeEndTime, null, page.getPageNum(), page.getNumPerPage());
		
		try {
			List<Map> list = (List<Map>) page.getList();
			for (Map map : list) {
				List<Map> itemList = (List<Map>) map.get("items");
				for (Map item : itemList) {
					String remark = (String) item.get("remark");
					item.put("remark", JSONObject.fromObject(remark).get("zh_CN"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return page;
	}

}
