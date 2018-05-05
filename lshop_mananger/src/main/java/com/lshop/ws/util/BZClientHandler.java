package com.lshop.ws.util;

import java.io.IOException;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.soap.SOAPFaultException;



public class BZClientHandler implements SOAPHandler<SOAPMessageContext> {
	
	private String accountNum;
	private String password;
	
	 public String getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	   public boolean handleMessage(SOAPMessageContext context) {
	 
		System.out.println("Server : handleMessage()......");
	 
	 
		try{
		    SOAPMessage soapMsg = context.getMessage();
		    SOAPEnvelope soapEnv = soapMsg.getSOAPPart().getEnvelope();
	        SOAPHeader soapHeader = soapEnv.getHeader();
	 
	            //if no header, add one
		    if (soapHeader == null){
		            soapHeader = soapEnv.addHeader();
		     }
	 
		     
		    SOAPElement accountNumNode = soapHeader.addChildElement(new QName("http://service.webservice.datainterface.bz.gv.com/","accountNum"));
		    accountNumNode.setNodeValue("accountNum");
		    accountNumNode.setValue(accountNum);
		    
		    SOAPElement passwordNode = soapHeader.addChildElement(new QName("http://service.webservice.datainterface.bz.gv.com/","password"));
		    passwordNode.setNodeValue("password");
		    passwordNode.setValue(password);
		 
		    soapMsg.writeTo(System.out);
		    return true;
	 
			}catch(SOAPException e){
				System.err.println(e);
			}catch(IOException e){
				System.err.println(e);
			}
	 
	 
		  //continue other handler chain
		  return false;
		}
	 
		@Override
		public boolean handleFault(SOAPMessageContext context) {
	 
			System.out.println("Server : handleFault()......");
	 
			return true;
		}
	 
		@Override
		public void close(MessageContext context) {
			System.out.println("Server : close()......");
		}
	 
		@Override
		public Set<QName> getHeaders() {
			System.out.println("Server : getHeaders()......");
			return null;
		}
	 
	     private void generateSOAPErrMessage(SOAPMessage msg, String reason) {
	       try {
	          SOAPBody soapBody = msg.getSOAPPart().getEnvelope().getBody();
	          SOAPFault soapFault = soapBody.addFault();
	          soapFault.setFaultString(reason);
	          throw new SOAPFaultException(soapFault); 
	       }
	       catch(SOAPException e) { }
	    }
}
