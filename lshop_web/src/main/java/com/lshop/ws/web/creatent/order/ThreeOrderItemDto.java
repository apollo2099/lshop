
package com.lshop.ws.web.creatent.order;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for threeOrderItemDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="threeOrderItemDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="oprice" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="pcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="punm" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "threeOrderItemDto", propOrder = {
    "oprice",
    "pcode",
    "punm"
})
public class ThreeOrderItemDto {

    protected Double oprice;
    protected String pcode;
    protected int punm;

    /**
     * Gets the value of the oprice property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getOprice() {
        return oprice;
    }

    /**
     * Sets the value of the oprice property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setOprice(Double value) {
        this.oprice = value;
    }

    /**
     * Gets the value of the pcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPcode() {
        return pcode;
    }

    /**
     * Sets the value of the pcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPcode(String value) {
        this.pcode = value;
    }

    /**
     * Gets the value of the punm property.
     * 
     */
    public int getPunm() {
        return punm;
    }

    /**
     * Sets the value of the punm property.
     * 
     */
    public void setPunm(int value) {
        this.punm = value;
    }

}
