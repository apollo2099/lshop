
package test.com.lshop.ws.server.bz.order.service;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.lshop.ws.web.bz.order package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.lshop.ws.web.bz.order
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link LvOrderDto }
     * 
     */
    public LvOrderDto createLvOrderDto() {
        return new LvOrderDto();
    }

    /**
     * Create an instance of {@link Result }
     * 
     */
    public Result createResult() {
        return new Result();
    }

    /**
     * Create an instance of {@link BasePojo }
     * 
     */
    public BasePojo createBasePojo() {
        return new BasePojo();
    }

    /**
     * Create an instance of {@link LvOrderDtoResposne }
     * 
     */
    public LvOrderDtoResposne createLvOrderDtoResposne() {
        return new LvOrderDtoResposne();
    }

    /**
     * Create an instance of {@link LvProductDto }
     * 
     */
    public LvProductDto createLvProductDto() {
        return new LvProductDto();
    }

}
