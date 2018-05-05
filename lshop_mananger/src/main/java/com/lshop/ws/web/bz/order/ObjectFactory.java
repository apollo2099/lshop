
package com.lshop.ws.web.bz.order;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.lshop.ws.web.bz.order package. 
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

    private final static QName _Result_QNAME = new QName("http://service.webservice.datainterface.bz.gv.com/", "Result");
    private final static QName _ExpressInfo_QNAME = new QName("http://service.webservice.datainterface.bz.gv.com/", "ExpressInfo");
    private final static QName _ExpressInfos_QNAME = new QName("http://service.webservice.datainterface.bz.gv.com/", "ExpressInfos");
    private final static QName _ExpressResponse_QNAME = new QName("http://service.webservice.datainterface.bz.gv.com/", "ExpressResponse");
    private final static QName _MultiExpressResponse_QNAME = new QName("http://service.webservice.datainterface.bz.gv.com/", "MultiExpressResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.lshop.ws.web.bz.order
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ExpressResponse }
     * 
     */
    public ExpressResponse createExpressResponse() {
        return new ExpressResponse();
    }

    /**
     * Create an instance of {@link MultiExpressResponse }
     * 
     */
    public MultiExpressResponse createMultiExpressResponse() {
        return new MultiExpressResponse();
    }

    /**
     * Create an instance of {@link ExpressInfo }
     * 
     */
    public ExpressInfo createExpressInfo() {
        return new ExpressInfo();
    }

    /**
     * Create an instance of {@link ExpressInfos }
     * 
     */
    public ExpressInfos createExpressInfos() {
        return new ExpressInfos();
    }

    /**
     * Create an instance of {@link Result }
     * 
     */
    public Result createResult() {
        return new Result();
    }

    /**
     * Create an instance of {@link ArrayList }
     * 
     */
    public ArrayList createArrayList() {
        return new ArrayList();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Result }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.webservice.datainterface.bz.gv.com/", name = "Result")
    public JAXBElement<Result> createResult(Result value) {
        return new JAXBElement<Result>(_Result_QNAME, Result.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExpressInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.webservice.datainterface.bz.gv.com/", name = "ExpressInfo")
    public JAXBElement<ExpressInfo> createExpressInfo(ExpressInfo value) {
        return new JAXBElement<ExpressInfo>(_ExpressInfo_QNAME, ExpressInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExpressInfos }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.webservice.datainterface.bz.gv.com/", name = "ExpressInfos")
    public JAXBElement<ExpressInfos> createExpressInfos(ExpressInfos value) {
        return new JAXBElement<ExpressInfos>(_ExpressInfos_QNAME, ExpressInfos.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExpressResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.webservice.datainterface.bz.gv.com/", name = "ExpressResponse")
    public JAXBElement<ExpressResponse> createExpressResponse(ExpressResponse value) {
        return new JAXBElement<ExpressResponse>(_ExpressResponse_QNAME, ExpressResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiExpressResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.webservice.datainterface.bz.gv.com/", name = "MultiExpressResponse")
    public JAXBElement<MultiExpressResponse> createMultiExpressResponse(MultiExpressResponse value) {
        return new JAXBElement<MultiExpressResponse>(_MultiExpressResponse_QNAME, MultiExpressResponse.class, null, value);
    }

}
