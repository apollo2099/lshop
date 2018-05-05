
package com.lshop.ws.boss.buyorder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for rechargeRecordResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="rechargeRecordResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="incomings" type="{com.gv.webservice}synRechargeRecordDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rechargeRecordResponse", propOrder = {
    "incomings"
})
public class RechargeRecordResponse {

    protected SynRechargeRecordDto incomings;

    /**
     * Gets the value of the incomings property.
     * 
     * @return
     *     possible object is
     *     {@link SynRechargeRecordDto }
     *     
     */
    public SynRechargeRecordDto getIncomings() {
        return incomings;
    }

    /**
     * Sets the value of the incomings property.
     * 
     * @param value
     *     allowed object is
     *     {@link SynRechargeRecordDto }
     *     
     */
    public void setIncomings(SynRechargeRecordDto value) {
        this.incomings = value;
    }

}
