
package test.com.lshop.ws.server.bz.order.service;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for synAuditOrderToBZResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="synAuditOrderToBZResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://service.order.bz.server.ws.lshop.com/}lvOrderDtoResposne" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "synAuditOrderToBZResponse", propOrder = {
    "_return"
})
public class SynAuditOrderToBZResponse {

    @XmlElement(name = "return")
    protected LvOrderDtoResposne _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link LvOrderDtoResposne }
     *     
     */
    public LvOrderDtoResposne getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link LvOrderDtoResposne }
     *     
     */
    public void setReturn(LvOrderDtoResposne value) {
        this._return = value;
    }

}
