
package com.lshop.ws.web.creatent.order;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for threeOrderSendDtoResposne complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="threeOrderSendDtoResposne">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.threeOrder.server.mall.ws.lshop.com/}basePojo">
 *       &lt;sequence>
 *         &lt;element name="orderList" type="{http://service.threeOrder.server.mall.ws.lshop.com/}threeOrderSendDto" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "threeOrderSendDtoResposne", propOrder = {
    "orderList"
})
public class ThreeOrderSendDtoResposne
    extends BasePojo
{

    @XmlElement(nillable = true)
    protected List<ThreeOrderSendDto> orderList;

    /**
     * Gets the value of the orderList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the orderList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOrderList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ThreeOrderSendDto }
     * 
     * 
     */
    public List<ThreeOrderSendDto> getOrderList() {
        if (orderList == null) {
            orderList = new ArrayList<ThreeOrderSendDto>();
        }
        return this.orderList;
    }

}
