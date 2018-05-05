package com.lshop.ws.util;

import java.util.Iterator;
import java.util.Set;

import javax.xml.soap.Node;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.soap.SOAPFaultException;

import org.w3c.dom.NodeList;

import com.gv.core.util.StringUtil;

public class BZServiceHandler implements SOAPHandler<SOAPMessageContext> {

	String accountNum;
	String password;

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set getHeaders() {
		// TODO Auto-generated method stub
		return null;
	}

	public void close(MessageContext context) {
		// TODO Auto-generated method stub

	}

	public boolean handleMessage(SOAPMessageContext context) {
		Boolean outbound = (Boolean) context
				.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

		if (!outbound.booleanValue()) {
			SOAPMessage soapMessage = context.getMessage();

			System.out.println(context.getMessage());
			SOAPEnvelope soapEnvelope;
			try {
				soapMessage.writeTo(System.out);
				soapEnvelope = soapMessage.getSOAPPart().getEnvelope();
				SOAPHeader soapHeader = soapEnvelope.getHeader();

				Iterator item = soapHeader.getChildElements();
				while (item.hasNext()) {
					Node a = (Node) item.next();
					NodeList nodeList = a.getChildNodes();
					for (int i = 0; i < nodeList.getLength(); i++) {
						Node item1 = (Node) nodeList.item(i);
						System.out.println(item1.getNodeName() + ":"
								+ item1.getValue());
					}

					System.out.println(a.getNodeName() + ":" + a.getValue());

				}

				NodeList nodeList0 = soapHeader.getElementsByTagName("accountNum");
				Node accountNumNode = (Node) nodeList0.item(0);
				
				NodeList nodeList1 = soapHeader.getElementsByTagName("password");
				Node passwordNode = (Node) nodeList1.item(0);
				
				String accountNum2 = accountNumNode.getValue();
				String password2 = passwordNode.getValue();

				if (!StringUtil.IsNullOrEmpty(accountNum) && !StringUtil.IsNullOrEmpty(password)) {
					if ((accountNum2.equals(this.accountNum) && password2.equals(this.password))) {
						return true;
					} else {
						generateSoapFault(context.getMessage(), "验证失败!");
						return false;
					}

				} else {
					generateSoapFault(context.getMessage(), "认证失败,帐户或密码为空!");
					return false;
				}

			} catch (Exception e) {
				generateSoapFault(context.getMessage(), "消息格式错误!");

				e.printStackTrace();
			}
		}

		return false;
	}

	public boolean handleFault(SOAPMessageContext context) {
		return false;
	}

	private void generateSoapFault(SOAPMessage soapMessage, String reasion) {
		try {
			SOAPBody soapBody = soapMessage.getSOAPBody();
			SOAPFault soapFault = soapBody.getFault();

			if (soapFault == null) {
				soapFault = soapBody.addFault();
			}

			soapFault.setFaultString(reasion);

			throw new SOAPFaultException(soapFault);

		} catch (SOAPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
