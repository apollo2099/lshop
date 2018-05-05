package com.lshop.common.message.jms.activemq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.call.CallRequest;
import com.gv.core.datastructure.call.CallResponse;
import com.gv.core.message.jms.activemq.ActivemqMessageQueueReceiver;
import com.gv.core.proxy.impl.FacadeHandle;
import com.gv.core.util.json.JsonHelper;

public class ActiveMessageQueueReceiverImpl extends ActivemqMessageQueueReceiver {

	private static final Log logger = LogFactory.getLog(ActiveMessageQueueReceiverImpl.class);
	private String serviceNameID;
	private String methodName;
	@Override
	public void processMessage() {
		// TODO Auto-generated method stub
		Message message = getMessage();
		if (message instanceof ObjectMessage) {
			ObjectMessage objMsg = (ObjectMessage) message;
			try{
				logger.info(objMsg.getObject().toString());
			Dto dataDto=JsonHelper.parseSingleJson2Dto(objMsg.getObject().toString());

			doService(serviceNameID, methodName, dataDto);
			} catch (JMSException e) {
				logger.error(e.getMessage());
			}
		}

	}

	private CallResponse<?> doService(String serviceNameID, String methodName, Dto dto) {
		if (logger.isInfoEnabled()) {
			logger.info("***** doService method begin*****");
		}

		CallRequest call = new CallRequest();
		call.setBeanKey(serviceNameID);
		call.setMethodName(methodName);
		call.setDto(dto);
		CallResponse response = FacadeHandle.doService(call);

		if (logger.isInfoEnabled()) {
			logger.info("***** doService method end*****");
		}
		return response;
	}

	public String getServiceNameID() {
		return serviceNameID;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setServiceNameID(String serviceNameID) {
		this.serviceNameID = serviceNameID;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

}
