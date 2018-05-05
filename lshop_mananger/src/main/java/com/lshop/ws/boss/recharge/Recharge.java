
package com.lshop.ws.boss.recharge;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for recharge complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="recharge">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="account" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tradeno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="quantity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="forother" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="otheraccount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="synccounter" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="client" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="dealTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "recharge", propOrder = {
    "account",
    "tradeno",
    "quantity",
    "forother",
    "otheraccount",
    "synccounter",
    "client",
    "dealTime"
})
public class Recharge {

    protected String account;
    protected String tradeno;
    protected String quantity;
    protected Integer forother;
    protected String otheraccount;
    protected Integer synccounter;
    protected Integer client;
    protected String dealTime;

    /**
     * Gets the value of the account property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccount() {
        return account;
    }

    /**
     * Sets the value of the account property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccount(String value) {
        this.account = value;
    }

    /**
     * Gets the value of the tradeno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTradeno() {
        return tradeno;
    }

    /**
     * Sets the value of the tradeno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTradeno(String value) {
        this.tradeno = value;
    }

    /**
     * Gets the value of the quantity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuantity() {
        return quantity;
    }

    /**
     * Sets the value of the quantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuantity(String value) {
        this.quantity = value;
    }

    /**
     * Gets the value of the forother property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getForother() {
        return forother;
    }

    /**
     * Sets the value of the forother property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setForother(Integer value) {
        this.forother = value;
    }

    /**
     * Gets the value of the otheraccount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtheraccount() {
        return otheraccount;
    }

    /**
     * Sets the value of the otheraccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtheraccount(String value) {
        this.otheraccount = value;
    }

    /**
     * Gets the value of the synccounter property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSynccounter() {
        return synccounter;
    }

    /**
     * Sets the value of the synccounter property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSynccounter(Integer value) {
        this.synccounter = value;
    }

    /**
     * Gets the value of the client property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getClient() {
        return client;
    }

    /**
     * Sets the value of the client property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setClient(Integer value) {
        this.client = value;
    }

    /**
     * Gets the value of the dealTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDealTime() {
        return dealTime;
    }

    /**
     * Sets the value of the dealTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDealTime(String value) {
        this.dealTime = value;
    }

}
