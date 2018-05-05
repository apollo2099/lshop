
package com.lshop.ws.boss.sonsumeorder;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.lshop.ws.web.resumeRecord package. 
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

    private final static QName _SynResumeRecord_QNAME = new QName("com.gv.webservice", "synResumeRecord");
    private final static QName _StbResumeDto_QNAME = new QName("com.gv.webservice", "StbResumeDto");
    private final static QName _PageInfoDto_QNAME = new QName("com.gv.webservice", "PageInfoDto");
    private final static QName _SynStbResumeItemDetailDto_QNAME = new QName("com.gv.webservice", "SynStbResumeItemDetailDto");
    private final static QName _SynStbResumeRecordDto_QNAME = new QName("com.gv.webservice", "SynStbResumeRecordDto");
    private final static QName _SynResumeRecordResponse_QNAME = new QName("com.gv.webservice", "synResumeRecordResponse");
    private final static QName _ResponseDto_QNAME = new QName("com.gv.webservice", "ResponseDto");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.lshop.ws.web.resumeRecord
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ArrayList }
     * 
     */
    public ArrayList createArrayList() {
        return new ArrayList();
    }

    /**
     * Create an instance of {@link SynStbResumeItemDto }
     * 
     */
    public SynStbResumeItemDto createSynStbResumeItemDto() {
        return new SynStbResumeItemDto();
    }

    /**
     * Create an instance of {@link SynResumeRecordResponse }
     * 
     */
    public SynResumeRecordResponse createSynResumeRecordResponse() {
        return new SynResumeRecordResponse();
    }

    /**
     * Create an instance of {@link SynStbResumeRecordDto }
     * 
     */
    public SynStbResumeRecordDto createSynStbResumeRecordDto() {
        return new SynStbResumeRecordDto();
    }

    /**
     * Create an instance of {@link PageInfoDto }
     * 
     */
    public PageInfoDto createPageInfoDto() {
        return new PageInfoDto();
    }

    /**
     * Create an instance of {@link SynResumeRecord }
     * 
     */
    public SynResumeRecord createSynResumeRecord() {
        return new SynResumeRecord();
    }

    /**
     * Create an instance of {@link ResponseDto }
     * 
     */
    public ResponseDto createResponseDto() {
        return new ResponseDto();
    }

    /**
     * Create an instance of {@link SynStbResumeItemDetailDto }
     * 
     */
    public SynStbResumeItemDetailDto createSynStbResumeItemDetailDto() {
        return new SynStbResumeItemDetailDto();
    }

    /**
     * Create an instance of {@link StbResumeDto }
     * 
     */
    public StbResumeDto createStbResumeDto() {
        return new StbResumeDto();
    }

    /**
     * Create an instance of {@link SynStbResumeRecordsDto }
     * 
     */
    public SynStbResumeRecordsDto createSynStbResumeRecordsDto() {
        return new SynStbResumeRecordsDto();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SynResumeRecord }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "com.gv.webservice", name = "synResumeRecord")
    public JAXBElement<SynResumeRecord> createSynResumeRecord(SynResumeRecord value) {
        return new JAXBElement<SynResumeRecord>(_SynResumeRecord_QNAME, SynResumeRecord.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StbResumeDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "com.gv.webservice", name = "StbResumeDto")
    public JAXBElement<StbResumeDto> createStbResumeDto(StbResumeDto value) {
        return new JAXBElement<StbResumeDto>(_StbResumeDto_QNAME, StbResumeDto.class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link SynStbResumeItemDetailDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "com.gv.webservice", name = "SynStbResumeItemDetailDto")
    public JAXBElement<SynStbResumeItemDetailDto> createSynStbResumeItemDetailDto(SynStbResumeItemDetailDto value) {
        return new JAXBElement<SynStbResumeItemDetailDto>(_SynStbResumeItemDetailDto_QNAME, SynStbResumeItemDetailDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SynStbResumeRecordDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "com.gv.webservice", name = "SynStbResumeRecordDto")
    public JAXBElement<SynStbResumeRecordDto> createSynStbResumeRecordDto(SynStbResumeRecordDto value) {
        return new JAXBElement<SynStbResumeRecordDto>(_SynStbResumeRecordDto_QNAME, SynStbResumeRecordDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SynResumeRecordResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "com.gv.webservice", name = "synResumeRecordResponse")
    public JAXBElement<SynResumeRecordResponse> createSynResumeRecordResponse(SynResumeRecordResponse value) {
        return new JAXBElement<SynResumeRecordResponse>(_SynResumeRecordResponse_QNAME, SynResumeRecordResponse.class, null, value);
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
