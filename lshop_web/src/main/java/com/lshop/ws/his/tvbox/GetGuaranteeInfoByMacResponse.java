
package com.lshop.ws.his.tvbox;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getGuaranteeInfoByMacResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getGuaranteeInfoByMacResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="result" type="{http://webservice.stb.gv.com/}guaranteeInfoDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getGuaranteeInfoByMacResponse", propOrder = {
    "result"
})
public class GetGuaranteeInfoByMacResponse {

    protected GuaranteeInfoDto result;

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link GuaranteeInfoDto }
     *     
     */
    public GuaranteeInfoDto getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link GuaranteeInfoDto }
     *     
     */
    public void setResult(GuaranteeInfoDto value) {
        this.result = value;
    }

}
