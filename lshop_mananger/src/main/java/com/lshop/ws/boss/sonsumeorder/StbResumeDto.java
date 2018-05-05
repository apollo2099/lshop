
package com.lshop.ws.boss.sonsumeorder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for stbResumeDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="stbResumeDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="result" type="{com.gv.webservice}responseDto" minOccurs="0"/>
 *         &lt;element name="pageinfo" type="{com.gv.webservice}pageInfoDto" minOccurs="0"/>
 *         &lt;element name="totalamt" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="records" type="{com.gv.webservice}synStbResumeRecordsDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "stbResumeDto", propOrder = {
    "result",
    "pageinfo",
    "totalamt",
    "records"
})
public class StbResumeDto {

    protected ResponseDto result;
    protected PageInfoDto pageinfo;
    protected Long totalamt;
    protected SynStbResumeRecordsDto records;

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
     * Gets the value of the records property.
     * 
     * @return
     *     possible object is
     *     {@link SynStbResumeRecordsDto }
     *     
     */
    public SynStbResumeRecordsDto getRecords() {
        return records;
    }

    /**
     * Sets the value of the records property.
     * 
     * @param value
     *     allowed object is
     *     {@link SynStbResumeRecordsDto }
     *     
     */
    public void setRecords(SynStbResumeRecordsDto value) {
        this.records = value;
    }

}
