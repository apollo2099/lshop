
package test.com.lshop.ws.server.bz.order.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for lvProductDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="lvProductDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="coupons" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oprice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="pcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="punm" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="specialprice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "lvProductDto", propOrder = {
    "coupons",
    "oprice",
    "pcode",
    "punm",
    "specialprice"
})
public class LvProductDto {

    protected String coupons;
    protected float oprice;
    protected String pcode;
    protected int punm;
    protected float specialprice;

    /**
     * Gets the value of the coupons property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCoupons() {
        return coupons;
    }

    /**
     * Sets the value of the coupons property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCoupons(String value) {
        this.coupons = value;
    }

    /**
     * Gets the value of the oprice property.
     * 
     */
    public float getOprice() {
        return oprice;
    }

    /**
     * Sets the value of the oprice property.
     * 
     */
    public void setOprice(float value) {
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

    /**
     * Gets the value of the specialprice property.
     * 
     */
    public float getSpecialprice() {
        return specialprice;
    }

    /**
     * Sets the value of the specialprice property.
     * 
     */
    public void setSpecialprice(float value) {
        this.specialprice = value;
    }

}
