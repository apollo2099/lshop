
package test.ws.server.popularize.order.service;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the test.com.lshop.ws.server.popularize.order.service package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: test.com.lshop.ws.server.popularize.order.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PProductDto }
     * 
     */
    public PProductDto createPProductDto() {
        return new PProductDto();
    }

    /**
     * Create an instance of {@link Result }
     * 
     */
    public Result createResult() {
        return new Result();
    }

    /**
     * Create an instance of {@link POrderDto }
     * 
     */
    public POrderDto createPOrderDto() {
        return new POrderDto();
    }

    /**
     * Create an instance of {@link POrderDtoResposne }
     * 
     */
    public POrderDtoResposne createPOrderDtoResposne() {
        return new POrderDtoResposne();
    }

    /**
     * Create an instance of {@link BasePojo }
     * 
     */
    public BasePojo createBasePojo() {
        return new BasePojo();
    }

}
