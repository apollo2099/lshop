package com.lshop.manage.message.service.impl;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.gv.core.message.jms.JmsMessageQueueSender;
import com.lshop.manage.message.service.ProductMsgService;

@Service("productMsgService")
public class ProductMsgServiceImpl implements ProductMsgService {
	private static final Log logger = LogFactory
			.getLog(ProductMsgServiceImpl.class);

	@Resource(name = "productMessageSender")
	private JmsMessageQueueSender productMessageSender;

	@Override
	public void sendProductMsg(String code) {
		if (code != null && !code.equals("")) {
			productMessageSender.sendObject(code);
			logger.info("********product :" + code + " change information sended!");
		}
	}

	public JmsMessageQueueSender getProductMessageSender() {
		return productMessageSender;
	}

	public void setProductMessageSender(
			JmsMessageQueueSender productMessageSender) {
		this.productMessageSender = productMessageSender;
	}

}
