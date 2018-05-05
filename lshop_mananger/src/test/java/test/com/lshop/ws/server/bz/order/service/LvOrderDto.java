
package test.com.lshop.ws.server.bz.order.service;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for lvOrderDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="lvOrderDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="adress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cityname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contryId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contryname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createtime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="currency" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mobile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oremark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="overtime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="paymethod" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="paynumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="postage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="postcode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="products" type="{http://service.order.bz.server.ws.lshop.com/}lvProductDto" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="provincename" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="relname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sendremark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sendtime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="source" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="totalPrice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "lvOrderDto", propOrder = {
    "adress",
    "cityname",
    "contryId",
    "contryname",
    "createtime",
    "currency",
    "description",
    "email",
    "mobile",
    "oid",
    "oremark",
    "overtime",
    "paymethod",
    "paynumber",
    "postage",
    "postcode",
    "products",
    "provincename",
    "relname",
    "sendremark",
    "sendtime",
    "source",
    "tel",
    "totalPrice"
})
public class LvOrderDto {

    protected String adress;
    protected String cityname;
    protected String contryId;
    protected String contryname;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createtime;
    protected String currency;
    protected String description;
    protected String email;
    protected String mobile;
    protected String oid;
    protected String oremark;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar overtime;
    protected Integer paymethod;
    protected String paynumber;
    protected String postage;
    protected String postcode;
    @XmlElement(nillable = true)
    protected List<LvProductDto> products;
    protected String provincename;
    protected String relname;
    protected String sendremark;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar sendtime;
    protected String source;
    protected String tel;
    protected float totalPrice;

    /**
     * Gets the value of the adress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdress() {
        return adress;
    }

    /**
     * Sets the value of the adress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdress(String value) {
        this.adress = value;
    }

    /**
     * Gets the value of the cityname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCityname() {
        return cityname;
    }

    /**
     * Sets the value of the cityname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCityname(String value) {
        this.cityname = value;
    }

    /**
     * Gets the value of the contryId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContryId() {
        return contryId;
    }

    /**
     * Sets the value of the contryId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContryId(String value) {
        this.contryId = value;
    }

    /**
     * Gets the value of the contryname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContryname() {
        return contryname;
    }

    /**
     * Sets the value of the contryname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContryname(String value) {
        this.contryname = value;
    }

    /**
     * Gets the value of the createtime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreatetime() {
        return createtime;
    }

    /**
     * Sets the value of the createtime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreatetime(XMLGregorianCalendar value) {
        this.createtime = value;
    }

    /**
     * Gets the value of the currency property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * Sets the value of the currency property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrency(String value) {
        this.currency = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the mobile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * Sets the value of the mobile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMobile(String value) {
        this.mobile = value;
    }

    /**
     * Gets the value of the oid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOid() {
        return oid;
    }

    /**
     * Sets the value of the oid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOid(String value) {
        this.oid = value;
    }

    /**
     * Gets the value of the oremark property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOremark() {
        return oremark;
    }

    /**
     * Sets the value of the oremark property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOremark(String value) {
        this.oremark = value;
    }

    /**
     * Gets the value of the overtime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOvertime() {
        return overtime;
    }

    /**
     * Sets the value of the overtime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOvertime(XMLGregorianCalendar value) {
        this.overtime = value;
    }

    /**
     * Gets the value of the paymethod property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPaymethod() {
        return paymethod;
    }

    /**
     * Sets the value of the paymethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPaymethod(Integer value) {
        this.paymethod = value;
    }

    /**
     * Gets the value of the paynumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaynumber() {
        return paynumber;
    }

    /**
     * Sets the value of the paynumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaynumber(String value) {
        this.paynumber = value;
    }

    /**
     * Gets the value of the postage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostage() {
        return postage;
    }

    /**
     * Sets the value of the postage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostage(String value) {
        this.postage = value;
    }

    /**
     * Gets the value of the postcode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * Sets the value of the postcode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostcode(String value) {
        this.postcode = value;
    }

    /**
     * Gets the value of the products property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the products property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProducts().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LvProductDto }
     * 
     * 
     */
    public List<LvProductDto> getProducts() {
        if (products == null) {
            products = new ArrayList<LvProductDto>();
        }
        return this.products;
    }

    /**
     * Gets the value of the provincename property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvincename() {
        return provincename;
    }

    /**
     * Sets the value of the provincename property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvincename(String value) {
        this.provincename = value;
    }

    /**
     * Gets the value of the relname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRelname() {
        return relname;
    }

    /**
     * Sets the value of the relname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRelname(String value) {
        this.relname = value;
    }

    /**
     * Gets the value of the sendremark property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSendremark() {
        return sendremark;
    }

    /**
     * Sets the value of the sendremark property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSendremark(String value) {
        this.sendremark = value;
    }

    /**
     * Gets the value of the sendtime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSendtime() {
        return sendtime;
    }

    /**
     * Sets the value of the sendtime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSendtime(XMLGregorianCalendar value) {
        this.sendtime = value;
    }

    /**
     * Gets the value of the source property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSource() {
        return source;
    }

    /**
     * Sets the value of the source property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSource(String value) {
        this.source = value;
    }

    /**
     * Gets the value of the tel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTel() {
        return tel;
    }

    /**
     * Sets the value of the tel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTel(String value) {
        this.tel = value;
    }

    /**
     * Gets the value of the totalPrice property.
     * 
     */
    public float getTotalPrice() {
        return totalPrice;
    }

    /**
     * Sets the value of the totalPrice property.
     * 
     */
    public void setTotalPrice(float value) {
        this.totalPrice = value;
    }

}
