
package test.com.lshop.ws.server.popularize.order.service;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for pOrderDtoResposne complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="pOrderDtoResposne">
 *   &lt;complexContent>
 *     &lt;extension base="{http://service.order.popularize.server.ws.lshop.com/}basePojo">
 *       &lt;sequence>
 *         &lt;element name="POrderList" type="{http://service.order.popularize.server.ws.lshop.com/}pOrderDto" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pOrderDtoResposne", propOrder = {
    "pOrderList"
})
public class POrderDtoResposne
    extends BasePojo
{

    @XmlElement(name = "POrderList", nillable = true)
    protected List<POrderDto> pOrderList;

    /**
     * Gets the value of the pOrderList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pOrderList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPOrderList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link POrderDto }
     * 
     * 
     */
    public List<POrderDto> getPOrderList() {
        if (pOrderList == null) {
            pOrderList = new ArrayList<POrderDto>();
        }
        return this.pOrderList;
    }

}
