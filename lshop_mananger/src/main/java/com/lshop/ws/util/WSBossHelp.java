package com.lshop.ws.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.gv.base.model.spring.ApplicationContextHolder;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.datastructure.page.Pagination;
import com.lshop.ws.boss.account.UserDetailInfoDto;
import com.lshop.ws.boss.account.UserDetailsDto;
import com.lshop.ws.boss.account.WSUserListService;
import com.lshop.ws.boss.buyorder.RechargeRecordDto;
import com.lshop.ws.boss.buyorder.SynRechargeRecordDto;
import com.lshop.ws.boss.buyorder.WSSynRechargeRecordService;
import com.lshop.ws.boss.recharge.OnlineRechargeDto;
import com.lshop.ws.boss.recharge.SOAPException_Exception;
import com.lshop.ws.boss.recharge.WSOnlineRechargeService;
import com.lshop.ws.boss.sonsumeorder.StbResumeDto;
import com.lshop.ws.boss.sonsumeorder.SynStbResumeItemDetailDto;
import com.lshop.ws.boss.sonsumeorder.SynStbResumeRecordDto;
import com.lshop.ws.boss.sonsumeorder.WSStbResumeRecordService;

@SuppressWarnings("unchecked")
@Service("WSBossHelp")
public class WSBossHelp {
	
	private ArrayList emptyList = new ArrayList();
	
	private WSBossHelp() {} 
	
	public static WSBossHelp getInstance() {
		WSBossHelp service = (WSBossHelp) ApplicationContextHolder.getBean("WSBossHelp");
		return service;
	}
	
	/**
	 * 账户列表
	 * @param account	账户（支持模糊匹配，可以为空）
	 * @param status	状态（0=停用；1=正常，可以为空）
	 * @param startDate	注册日期（查询起始范围，可以为空）
	 * @param endDate	注册日期（查询结束范围，可以为空）
	 * @param pageNo	查询页码
	 * @param pageSize	每页查询条数
	 * 
	 */
	public Pagination accountList(String account, Integer status,
			String startDate, String endDate, int pageNo, int pageSize) {
		
		Pagination page = new Pagination();
		WSUserListService service = (WSUserListService) ApplicationContextHolder.getBean("WSUserListService");
		UserDetailsDto userDetailsDto = service.queryUserList(account,
				startDate, endDate, status, pageNo, pageSize);

		if (userDetailsDto == null || userDetailsDto.getUsers() == null
				|| userDetailsDto.getUsers().size() < 0
				|| userDetailsDto.getPage() == null) {
			page.setList(emptyList);
			return page;
		}
		
		List<UserDetailInfoDto> infoDtoList = userDetailsDto.getUsers().get(0).getUser();
		List list = new ArrayList();
		Map map = null;
		
		for (UserDetailInfoDto dto : infoDtoList) {
			map = new HashMap();
			map.put("accountno", dto.getAccountno());
			map.put("balance", dto.getBalance());
			map.put("paymentsum", dto.getPaymentsum());
			map.put("receiptsum", dto.getReceiptsum());
			map.put("registerdate", dto.getRegisterdate());
			map.put("userstatus", dto.getUserstatus());
			list.add(map);
		}
		com.lshop.ws.boss.account.PageInfoDto pageInfoDto = userDetailsDto.getPage();
		
		int pageNo2 = pageInfoDto.getTotal() / pageInfoDto.getPagesize();
		if (pageInfoDto.getTotal() % pageInfoDto.getPagesize() > 0) {
			pageNo2++;
		}
		if (pageNo > pageNo2) {
			pageNo = pageNo2;
		}
		
		page.setList(list);
		page.setPageNum(pageNo);
		page.setNumPerPage(pageInfoDto.getPagesize());
		page.setTotalCount(pageInfoDto.getTotal());
		
		return page;
	}
	
	/**
	 * 账户充值交易记录列表
	 * @param account		账户
	 * @param buyStartTime	交易时间（查询起始范围，可以为空）
	 * @param buyEndTime	交易时间（查询结束范围，可以为空）
	 * @param year			充值年份
	 * @param status		交易状态（1=成功；0=失败；2=全部）
	 * @param pageNo		查询页码
	 * @param pageSize		每页查询条数
	 * @return
	 */
	public Pagination buyList(String account, String buyStartTime, 
			String buyEndTime, String year, String status, int pageNo, int pageSize) {
		
		Pagination page = new Pagination();
		WSSynRechargeRecordService service = (WSSynRechargeRecordService) ApplicationContextHolder
				.getBean("WSSynRechargeRecordService");
		
		SynRechargeRecordDto synRechargeRecordDto = service.rechargeRecord(account,
				buyStartTime, buyEndTime, year, status, pageNo, pageSize);

		if (synRechargeRecordDto == null || synRechargeRecordDto.getRecord() == null
				|| synRechargeRecordDto.getPageinfo() == null) {
			page.setList(emptyList);
			return page;
		}
		
		List<RechargeRecordDto> recordDtoList = synRechargeRecordDto.getRecord();
		List list = new ArrayList();
		Map map = null;
		
		for (RechargeRecordDto dto : recordDtoList) {
			map = new HashMap();
			map.put("account", dto.getAccount());
			map.put("amt", dto.getAmt());
			map.put("cardno", dto.getCardno());
			map.put("client", dto.getClient());
			map.put("date", dto.getDate());
			map.put("status", dto.getStatus());
			map.put("tradeno", dto.getTradeno());
			list.add(map);
		}
		com.lshop.ws.boss.buyorder.PageInfoDto pageInfoDto = synRechargeRecordDto.getPageinfo();
		
		int pageNo2 = pageInfoDto.getTotal() / pageInfoDto.getPagesize();
		if (pageInfoDto.getTotal() % pageInfoDto.getPagesize() > 0) {
			pageNo2++;
		}
		if (pageNo > pageNo2) {
			pageNo = pageNo2;
		}
		
		page.setList(list);
		page.setPageNum(pageNo);
		page.setNumPerPage(pageInfoDto.getPagesize());
		page.setTotalCount(pageInfoDto.getTotal());
		return page;
	}
	
	/**
	 * 账户消费交易记录列表
	 * @param account			账户
	 * @param mac				交易的mac
	 * @param consumeStartTime	交易时间（查询起始范围，可以为空）
	 * @param consumeEndTime	交易时间（查询结束范围，可以为空）
	 * @param year				消费年份
	 * @param pageNo			查询页码
	 * @param pageSize			每页查询条数
	 * @return
	 */
	public Pagination consumeList(String account, String mac, String consumeStartTime, 
			String consumeEndTime, String year, int pageNo, int pageSize) {
		
		Pagination page = new Pagination();
		
		WSStbResumeRecordService service = (WSStbResumeRecordService) ApplicationContextHolder
				.getBean("WSStbResumeRecordService");
		
		StbResumeDto stbResumeDto = service.synResumeRecord(account, mac,
				consumeStartTime, consumeEndTime, year, pageNo, pageSize);
		
		if (stbResumeDto == null || stbResumeDto.getRecords() == null
				|| stbResumeDto.getRecords().getRecord() == null
				|| stbResumeDto.getPageinfo() == null) {
			page.setList(emptyList);
			return page;
		}
		
		List<SynStbResumeRecordDto> recordDtoList = stbResumeDto.getRecords().getRecord();
		List list = new ArrayList();
		Map map = null;
		
		for (SynStbResumeRecordDto dto : recordDtoList) {
			map = new HashMap();
			map.put("account", dto.getAccountno());
			map.put("amt", dto.getAmt());
			map.put("date", dto.getDate());
			map.put("mac", dto.getMac());
			map.put("tradeno", dto.getTradeno());
			
			List itemList = new ArrayList();
			
			if (dto.getItems() != null && dto.getItems().get(0) != null
					&& dto.getItems().get(0).getItem() != null) {
				
				List<SynStbResumeItemDetailDto> detailDtoList = dto.getItems().get(0).getItem();
				Map itemMap = new HashMap();
				
				for (SynStbResumeItemDetailDto itemDto : detailDtoList) {
					itemMap = new HashMap<String, Object>();
					itemMap.put("name", itemDto.getName());
					itemMap.put("price", itemDto.getPrice());
					itemMap.put("remark", itemDto.getRemark());
				}
				itemList.add(itemMap);
			}
			map.put("items", itemList);
			list.add(map);
		}
		com.lshop.ws.boss.sonsumeorder.PageInfoDto pageInfoDto = stbResumeDto.getPageinfo();
		
		int pageNo2 = pageInfoDto.getTotal() / pageInfoDto.getPagesize();
		if (pageInfoDto.getTotal() % pageInfoDto.getPagesize() > 0) {
			pageNo2++;
		}
		if (pageNo > pageNo2) {
			pageNo = pageNo2;
		}
		
		page.setList(list);
		page.setPageNum(pageNo);
		page.setNumPerPage(pageInfoDto.getPagesize());
		page.setTotalCount(pageInfoDto.getTotal());
		return page;
	}
	
	/**
	 * 充值V币
	 * @param account	充值账户
	 * @param orderNo	订单号
	 * @param amount	充值金额（V币）
	 * @param type		充值方式（0=本号充值；1=帮人充值）
	 * @param otheraccount	帮充账户（type为1时不能为空）
	 * @param synccounter	同步计数器
	 * @param client		客户端（1=启创；2=机顶盒；3=手动）
	 * @param dealTime		订单交易时间
	 * @return
	 */
	public Dto recharge(String account, String orderNo, String amount, Integer type, String otheraccount, Integer synccounter, Integer client, String dealTime) {
		BaseDto dto = new BaseDto();
		WSOnlineRechargeService service = (WSOnlineRechargeService) ApplicationContextHolder
				.getBean("WSOnlineRechargeService");
		try {
			OnlineRechargeDto rechargeDto  = service.recharge(account, orderNo, amount, type, otheraccount, synccounter, client, dealTime);
			Integer status = rechargeDto.getStatus();
			String message = rechargeDto.getMsg();
			Integer balance = rechargeDto.getBalance();
			
			dto.put("status", status);
			dto.put("message", message);
			dto.put("balance", balance);
		} catch (SOAPException_Exception e) {
			e.printStackTrace();
		}
		return dto;
	}
	
}
