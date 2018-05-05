
package com.lshop.ws.boss.buyorder;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.lshop.ws.web.rechargeRecord package. 
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

    private final static QName _PageInfoDto_QNAME = new QName("com.gv.webservice", "PageInfoDto");
    private final static QName _RechargeRecord_QNAME = new QName("com.gv.webservice", "rechargeRecord");
    private final static QName _RechargeRecordResponse_QNAME = new QName("com.gv.webservice", "rechargeRecordResponse");
    private final static QName _RechargeRecordDto_QNAME = new QName("com.gv.webservice", "RechargeRecordDto");
    private final static QName _ResponseDto_QNAME = new QName("com.gv.webservice", "ResponseDto");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.lshop.ws.web.rechargeRecord
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RechargeRecordDto }
     * 
     */
    public RechargeRecordDto createRechargeRecordDto() {
        return new RechargeRecordDto();
    }

    /**
     * Create an instance of {@link RechargeRecordResponse }
     * 
     */
    public RechargeRecordResponse createRechargeRecordResponse() {
        return new RechargeRecordResponse();
    }

    /**
     * Create an instance of {@link ResponseDto }
     * 
     */
    public ResponseDto createResponseDto() {
        return new ResponseDto();
    }

    /**
     * Create an instance of {@link PageInfoDto }
     * 
     */
    public PageInfoDto createPageInfoDto() {
        return new PageInfoDto();
    }

    /**
     * Create an instance of {@link RechargeRecord }
     * 
     */
    public RechargeRecord createRechargeRecord() {
        return new RechargeRecord();
    }

    /**
     * Create an instance of {@link SynRechargeRecordDto }
     * 
     */
    public SynRechargeRecordDto createSynRechargeRecordDto() {
        return new SynRechargeRecordDto();
    }

    /**
     * Create an instance of {@link ArrayList }
     * 
     */
    public ArrayList createArrayList() {
        return new ArrayList();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PageInfoDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "com.gv.webservice", name = "PageInfoDto")
    public JAXBElement<PageInfoDto> createPageInfoDto(PageInfoDto value) {
        return new JAXBElement<PageInfoDto>(_PageInfoDto_QNAME, PageInfoDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RechargeRecord }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "com.gv.webservice", name = "rechargeRecord")
    public JAXBElement<RechargeRecord> createRechargeRecord(RechargeRecord value) {
        return new JAXBElement<RechargeRecord>(_RechargeRecord_QNAME, RechargeRecord.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RechargeRecordResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "com.gv.webservice", name = "rechargeRecordResponse")
    public JAXBElement<RechargeRecordResponse> createRechargeRecordResponse(RechargeRecordResponse value) {
        return new JAXBElement<RechargeRecordResponse>(_RechargeRecordResponse_QNAME, RechargeRecordResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RechargeRecordDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "com.gv.webservice", name = "RechargeRecordDto")
    public JAXBElement<RechargeRecordDto> createRechargeRecordDto(RechargeRecordDto value) {
        return new JAXBElement<RechargeRecordDto>(_RechargeRecordDto_QNAME, RechargeRecordDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResponseDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "com.gv.webservice", name = "ResponseDto")
    public JAXBElement<ResponseDto> createResponseDto(ResponseDto value) {
        return new JAXBElement<ResponseDto>(_ResponseDto_QNAME, ResponseDto.class, null, value);
    }

}
