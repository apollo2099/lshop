
package com.lshop.ws.web.bz.order;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for expressResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="expressResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="expressInfo" type="{http://service.webservice.datainterface.bz.gv.com/}expressInfo" minOccurs="0"/>
 *         &lt;element name="result" type="{http://service.webservice.datainterface.bz.gv.com/}result" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "expressResponse", propOrder = {
    "expressInfo",
    "result"
})
public class ExpressResponse implements java.io.Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected ExpressInfo expressInfo;
    protected Result result;

    /**
     * Gets the value of the expressInfo property.
     * 
     * @return
     *     possible object is
     *     {@link ExpressInfo }
     *     
     */
    public ExpressInfo getExpressInfo() {
        return expressInfo;
    }

    /**
     * Sets the value of the expressInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExpressInfo }
     *     
     */
    public void setExpressInfo(ExpressInfo value) {
        this.expressInfo = value;
    }

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link Result }
     *     
     */
    public Result getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link Result }
     *     
     */
    public void setResult(Result value) {
        this.result = value;
    }

}
