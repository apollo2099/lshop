package com.lshop.common.spring;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.impl.BaseDto;
import com.lshop.common.util.Constants;
import com.lshop.web.accountAddress.service.AccountAddressService;
import com.lshop.web.accountPayment.service.AccountPaymentService;
@Component
public class LoginEventListener implements ApplicationListener<LoginEvent>{
	@Resource AccountAddressService accountAddressService;
	@Resource AccountPaymentService accountPaymentService;
	@Override
	@Async
	public void onApplicationEvent(LoginEvent event) {
		loginProcess(event.getUserCode(), event.getMallSystem());
	}
	/**
	 * 登陆后处理
	 * @param userCode
	 */
	public void loginProcess(String userCode, String mallFlag) {
		//预先加载用户收货地址
		Dto dto = new BaseDto();
		dto.put("userCode", userCode);
		accountAddressService.getUserAddress(dto);
		//预先加载用户默认支付方式
		List<String> storeList = Constants.MALL_SYSTEM_TO_STORE.get(mallFlag);
		for (Iterator<String> iterator = storeList.iterator(); iterator.hasNext();) {
			String store = iterator.next();
			if (Constants.STORE_IN_USE.contains(store)) {
				accountPaymentService.getUserPayment(userCode, store);
			}
		}
	}
}
