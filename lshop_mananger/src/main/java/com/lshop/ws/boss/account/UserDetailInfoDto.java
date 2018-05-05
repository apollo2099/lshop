
package com.lshop.ws.boss.account;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for userDetailInfoDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="userDetailInfoDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="accountno" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="registerdate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userstatus" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="balance" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="receiptsum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="paymentsum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "userDetailInfoDto", propOrder = {
    "accountno",
    "registerdate",
    "userstatus",
    "balance",
    "receiptsum",
    "paymentsum"
})
public class UserDetailInfoDto {

    protected String accountno;
    protected String registerdate;
    protected Integer userstatus;
    protected Integer balance;
    protected Integer receiptsum;
    protected Integer paymentsum;

    /**
     * Gets the value of the accountno property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountno() {
        return accountno;
    }

    /**
     * Sets the value of the accountno property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountno(String value) {
        this.accountno = value;
    }

    /**
     * Gets the value of the registerdate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegisterdate() {
        return registerdate;
    }

    /**
     * Sets the value of the registerdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegisterdate(String value) {
        this.registerdate = value;
    }

    /**
     * Gets the value of the userstatus property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getUserstatus() {
        return userstatus;
    }

    /**
     * Sets the value of the userstatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setUserstatus(Integer value) {
        this.userstatus = value;
    }

    /**
     * Gets the value of the balance property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBalance() {
        return balance;
    }

    /**
     * Sets the value of the balance property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBalance(Integer value) {
        this.balance = value;
    }

    /**
     * Gets the value of the receiptsum property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getReceiptsum() {
        return receiptsum;
    }

    /**
     * Sets the value of the receiptsum property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setReceiptsum(Integer value) {
        this.receiptsum = value;
    }

    /**
     * Gets the value of the paymentsum property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPaymentsum() {
        return paymentsum;
    }

    /**
     * Sets the value of the paymentsum property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPaymentsum(Integer value) {
        this.paymentsum = value;
    }

}
