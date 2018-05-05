
package com.lshop.ws.web.bz.order;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for multiExpressResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="multiExpressResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="expressInfos" type="{http://service.webservice.datainterface.bz.gv.com/}expressInfos" minOccurs="0"/>
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
@XmlType(name = "multiExpressResponse", propOrder = {
    "expressInfos",
    "result"
})
public class MultiExpressResponse {

    protected ExpressInfos expressInfos;
    protected Result result;

    /**
     * Gets the value of the expressInfos property.
     * 
     * @return
     *     possible object is
     *     {@link ExpressInfos }
     *     
     */
    public ExpressInfos getExpressInfos() {
        return expressInfos;
    }

    /**
     * Sets the value of the expressInfos property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExpressInfos }
     *     
     */
    public void setExpressInfos(ExpressInfos value) {
        this.expressInfos = value;
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
