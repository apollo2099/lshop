
package com.lshop.ws.boss.account;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for pageInfoDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="pageInfoDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pageno" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="pagesize" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="totalpage" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="total" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pageInfoDto", propOrder = {
    "pageno",
    "pagesize",
    "totalpage",
    "total"
})
public class PageInfoDto {

    protected int pageno;
    protected int pagesize;
    protected int totalpage;
    protected int total;

    /**
     * Gets the value of the pageno property.
     * 
     */
    public int getPageno() {
        return pageno;
    }

    /**
     * Sets the value of the pageno property.
     * 
     */
    public void setPageno(int value) {
        this.pageno = value;
    }

    /**
     * Gets the value of the pagesize property.
     * 
     */
    public int getPagesize() {
        return pagesize;
    }

    /**
     * Sets the value of the pagesize property.
     * 
     */
    public void setPagesize(int value) {
        this.pagesize = value;
    }

    /**
     * Gets the value of the totalpage property.
     * 
     */
    public int getTotalpage() {
        return totalpage;
    }

    /**
     * Sets the value of the totalpage property.
     * 
     */
    public void setTotalpage(int value) {
        this.totalpage = value;
    }

    /**
     * Gets the value of the total property.
     * 
     */
    public int getTotal() {
        return total;
    }

    /**
     * Sets the value of the total property.
     * 
     */
    public void setTotal(int value) {
        this.total = value;
    }

}
