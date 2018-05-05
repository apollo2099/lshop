
package com.lshop.ws.his.tvbox;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.lshop.ws.his.tvbox package. 
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

    private final static QName _GetGuaranteeInfoByMacResponse_QNAME = new QName("http://webservice.stb.gv.com/", "getGuaranteeInfoByMacResponse");
    private final static QName _GuaranteeInfoDto_QNAME = new QName("http://webservice.stb.gv.com/", "GuaranteeInfoDto");
    private final static QName _GetGuaranteeInfoByMac_QNAME = new QName("http://webservice.stb.gv.com/", "getGuaranteeInfoByMac");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.lshop.ws.his.tvbox
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetGuaranteeInfoByMacResponse }
     * 
     */
    public GetGuaranteeInfoByMacResponse createGetGuaranteeInfoByMacResponse() {
        return new GetGuaranteeInfoByMacResponse();
    }

    /**
     * Create an instance of {@link GetGuaranteeInfoByMac }
     * 
     */
    public GetGuaranteeInfoByMac createGetGuaranteeInfoByMac() {
        return new GetGuaranteeInfoByMac();
    }

    /**
     * Create an instance of {@link GuaranteeInfoDto }
     * 
     */
    public GuaranteeInfoDto createGuaranteeInfoDto() {
        return new GuaranteeInfoDto();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetGuaranteeInfoByMacResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.stb.gv.com/", name = "getGuaranteeInfoByMacResponse")
    public JAXBElement<GetGuaranteeInfoByMacResponse> createGetGuaranteeInfoByMacResponse(GetGuaranteeInfoByMacResponse value) {
        return new JAXBElement<GetGuaranteeInfoByMacResponse>(_GetGuaranteeInfoByMacResponse_QNAME, GetGuaranteeInfoByMacResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GuaranteeInfoDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.stb.gv.com/", name = "GuaranteeInfoDto")
    public JAXBElement<GuaranteeInfoDto> createGuaranteeInfoDto(GuaranteeInfoDto value) {
        return new JAXBElement<GuaranteeInfoDto>(_GuaranteeInfoDto_QNAME, GuaranteeInfoDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetGuaranteeInfoByMac }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.stb.gv.com/", name = "getGuaranteeInfoByMac")
    public JAXBElement<GetGuaranteeInfoByMac> createGetGuaranteeInfoByMac(GetGuaranteeInfoByMac value) {
        return new JAXBElement<GetGuaranteeInfoByMac>(_GetGuaranteeInfoByMac_QNAME, GetGuaranteeInfoByMac.class, null, value);
    }

}
