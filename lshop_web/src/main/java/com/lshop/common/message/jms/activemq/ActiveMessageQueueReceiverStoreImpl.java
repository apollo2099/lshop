package com.lshop.common.message.jms.activemq;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gv.core.exception.ServiceException;
import com.lshop.common.message.jms.activemq.service.StoreMsgService;

public class ActiveMessageQueueReceiverStoreImpl implements MessageListener{
	private static final Log logger = LogFactory.getLog(ActiveMessageQueueReceiverStoreImpl.class);
	@Resource
	private StoreMsgService storeMsgService;	
	




	public void onMessage(Message message) {
		if (logger.isInfoEnabled()) {
			logger.info("*****ActiveMessageQueueReceiverStoreImpl: onMessage method begin*****");
		}

		try {
			
            if (message instanceof ObjectMessage) {
            	ObjectMessage txtMsg = (ObjectMessage)message;
                    String msg = txtMsg.getObject().toString();
                    storeMsgService.processStoreMSGToWEB(msg);
            }     

        } catch (JMSException e) {
            System.out.println("Caught: " + e);
            e.printStackTrace();
        } catch (ServiceException e) {
			e.printStackTrace();
		} 

		if (logger.isInfoEnabled()) {
			logger.info("*****ActiveMessageQueueReceiverStoreImpl: onMessage method end*****");
		}
	}


}
