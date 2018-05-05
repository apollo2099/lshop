
package com.lshop.ws.boss.recharge;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for rechargeResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="rechargeResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="recharge" type="{com.gv.webservice}onlineRechargeDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rechargeResponse", propOrder = {
    "recharge"
})
public class RechargeResponse {

    protected OnlineRechargeDto recharge;

    /**
     * Gets the value of the recharge property.
     * 
     * @return
     *     possible object is
     *     {@link OnlineRechargeDto }
     *     
     */
    public OnlineRechargeDto getRecharge() {
        return recharge;
    }

    /**
     * Sets the value of the recharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link OnlineRechargeDto }
     *     
     */
    public void setRecharge(OnlineRechargeDto value) {
        this.recharge = value;
    }

}
