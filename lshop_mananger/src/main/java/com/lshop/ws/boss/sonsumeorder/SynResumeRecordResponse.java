
package com.lshop.ws.boss.sonsumeorder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for synResumeRecordResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="synResumeRecordResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="outgoings" type="{com.gv.webservice}stbResumeDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "synResumeRecordResponse", propOrder = {
    "outgoings"
})
public class SynResumeRecordResponse {

    protected StbResumeDto outgoings;

    /**
     * Gets the value of the outgoings property.
     * 
     * @return
     *     possible object is
     *     {@link StbResumeDto }
     *     
     */
    public StbResumeDto getOutgoings() {
        return outgoings;
    }

    /**
     * Sets the value of the outgoings property.
     * 
     * @param value
     *     allowed object is
     *     {@link StbResumeDto }
     *     
     */
    public void setOutgoings(StbResumeDto value) {
        this.outgoings = value;
    }

}
