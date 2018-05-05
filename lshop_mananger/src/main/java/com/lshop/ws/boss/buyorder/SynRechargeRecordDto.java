
package com.lshop.ws.boss.buyorder;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for synRechargeRecordDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="synRechargeRecordDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="result" type="{com.gv.webservice}responseDto" minOccurs="0"/>
 *         &lt;element name="pageinfo" type="{com.gv.webservice}pageInfoDto" minOccurs="0"/>
 *         &lt;element name="totalamt" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="record" type="{com.gv.webservice}rechargeRecordDto" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "synRechargeRecordDto", propOrder = {
    "result",
    "pageinfo",
    "totalamt",
    "record"
})
public class SynRechargeRecordDto {

    protected ResponseDto result;
    protected PageInfoDto pageinfo;
    protected Long totalamt;
    @XmlElement(nillable = true)
    protected List<RechargeRecordDto> record;

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link ResponseDto }
     *     
     */
    public ResponseDto getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseDto }
     *     
     */
    public void setResult(ResponseDto value) {
        this.result = value;
    }

    /**
     * Gets the value of the pageinfo property.
     * 
     * @return
     *     possible object is
     *     {@link PageInfoDto }
     *     
     */
    public PageInfoDto getPageinfo() {
        return pageinfo;
    }

    /**
     * Sets the value of the pageinfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link PageInfoDto }
     *     
     */
    public void setPageinfo(PageInfoDto value) {
        this.pageinfo = value;
    }

    /**
     * Gets the value of the totalamt property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTotalamt() {
        return totalamt;
    }

    /**
     * Sets the value of the totalamt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTotalamt(Long value) {
        this.totalamt = value;
    }

    /**
     * Gets the value of the record property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the record property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRecord().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RechargeRecordDto }
     * 
     * 
     */
    public List<RechargeRecordDto> getRecord() {
        if (record == null) {
            record = new ArrayList<RechargeRecordDto>();
        }
        return this.record;
    }

}
