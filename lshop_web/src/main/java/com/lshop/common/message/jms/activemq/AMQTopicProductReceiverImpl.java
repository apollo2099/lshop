package com.lshop.common.message.jms.activemq;

import javax.annotation.Resource;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.scheduling.annotation.Async;

import com.lshop.web.product.service.ProductService;

/**
 * 订阅商品更新消息
 * @author caicl
 *
 */
public class AMQTopicProductReceiverImpl implements MessageListener{
	private static final Log logger = LogFactory.getLog(AMQTopicProductReceiverImpl.class);
	@Resource
	private ProductService productService;
	@Override
	@Async
	public void onMessage(Message message) {
		if (message instanceof Object) {
			ObjectMessage mes = (ObjectMessage) message;
			String[] pcodes = null;
			try {
				pcodes = mes.getObject().toString().split(",");
				for(String pcode : pcodes) {
						productService.reflashProdctCache(pcode);
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
	}

}
