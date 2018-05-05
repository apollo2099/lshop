package com.lshop.common.message.jms.activemq.service;

import javax.jms.JMSException;

import com.gv.core.datastructure.Dto;
import com.gv.core.exception.ServiceException;

public interface StoreMsgService{
   public void processStoreMSGToWEB(String msg) throws ServiceException,JMSException;;
}
