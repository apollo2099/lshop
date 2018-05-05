
package com.lshop.ws.boss.recharge;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebService(name = "WSOnlineRechargeService", targetNamespace = "com.gv.webservice")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface WSOnlineRechargeService {


    /**
     * 
     * @param dealTime
     * @param synccounter
     * @param client
     * @param account
     * @param otheraccount
     * @param quantity
     * @param tradeno
     * @param forother
     * @return
     *     returns com.lshop.ws.web.online.OnlineRechargeDto
     * @throws SOAPException_Exception
     */
    @WebMethod
    @WebResult(name = "recharge", targetNamespace = "")
    @RequestWrapper(localName = "recharge", targetNamespace = "com.gv.webservice", className = "com.lshop.ws.boss.recharge.Recharge")
    @ResponseWrapper(localName = "rechargeResponse", targetNamespace = "com.gv.webservice", className = "com.lshop.ws.boss.recharge.RechargeResponse")
    public OnlineRechargeDto recharge(
        @WebParam(name = "account", targetNamespace = "")
        String account,
        @WebParam(name = "tradeno", targetNamespace = "")
        String tradeno,
        @WebParam(name = "quantity", targetNamespace = "")
        String quantity,
        @WebParam(name = "forother", targetNamespace = "")
        Integer forother,
        @WebParam(name = "otheraccount", targetNamespace = "")
        String otheraccount,
        @WebParam(name = "synccounter", targetNamespace = "")
        Integer synccounter,
        @WebParam(name = "client", targetNamespace = "")
        Integer client,
        @WebParam(name = "dealTime", targetNamespace = "")
        String dealTime)
        throws SOAPException_Exception
    ;

}