
package com.lshop.ws.boss.recharge;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.lshop.ws.web.online package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Recharge_QNAME = new QName("com.gv.webservice", "recharge");
    private final static QName _RechargeResponse_QNAME = new QName("com.gv.webservice", "rechargeResponse");
    private final static QName _SOAPException_QNAME = new QName("com.gv.webservice", "SOAPException");
    private final static QName _OnlineRechargeDto_QNAME = new QName("com.gv.webservice", "OnlineRechargeDto");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.lshop.ws.web.online
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SOAPException }
     * 
     */
    public SOAPException createSOAPException() {
        return new SOAPException();
    }

    /**
     * Create an instance of {@link OnlineRechargeDto }
     * 
     */
    public OnlineRechargeDto createOnlineRechargeDto() {
        return new OnlineRechargeDto();
    }

    /**
     * Create an instance of {@link Recharge }
     * 
     */
    public Recharge createRecharge() {
        return new Recharge();
    }

    /**
     * Create an instance of {@link RechargeResponse }
     * 
     */
    public RechargeResponse createRechargeResponse() {
        return new RechargeResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Recharge }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "com.gv.webservice", name = "recharge")
    public JAXBElement<Recharge> createRecharge(Recharge value) {
        return new JAXBElement<Recharge>(_Recharge_QNAME, Recharge.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RechargeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "com.gv.webservice", name = "rechargeResponse")
    public JAXBElement<RechargeResponse> createRechargeResponse(RechargeResponse value) {
        return new JAXBElement<RechargeResponse>(_RechargeResponse_QNAME, RechargeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SOAPException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "com.gv.webservice", name = "SOAPException")
    public JAXBElement<SOAPException> createSOAPException(SOAPException value) {
        return new JAXBElement<SOAPException>(_SOAPException_QNAME, SOAPException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OnlineRechargeDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "com.gv.webservice", name = "OnlineRechargeDto")
    public JAXBElement<OnlineRechargeDto> createOnlineRechargeDto(OnlineRechargeDto value) {
        return new JAXBElement<OnlineRechargeDto>(_OnlineRechargeDto_QNAME, OnlineRechargeDto.class, null, value);
    }

}
