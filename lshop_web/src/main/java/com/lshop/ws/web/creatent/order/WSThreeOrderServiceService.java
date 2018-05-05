
package com.lshop.ws.web.creatent.order;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "WSThreeOrderServiceService", targetNamespace = "http://service.threeOrder.server.mall.ws.lshop.com/", wsdlLocation = "http://10.0.2.83:8081/ws/threeOrder?wsdl")
public class WSThreeOrderServiceService
    extends Service
{

    private final static URL WSTHREEORDERSERVICESERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(com.lshop.ws.web.creatent.order.WSThreeOrderServiceService.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = com.lshop.ws.web.creatent.order.WSThreeOrderServiceService.class.getResource(".");
            url = new URL(baseUrl, "http://10.0.2.83:8081/ws/threeOrder?wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'http://10.0.2.86:8080/ws/threeOrder?wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        WSTHREEORDERSERVICESERVICE_WSDL_LOCATION = url;
    }

    public WSThreeOrderServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WSThreeOrderServiceService() {
        super(WSTHREEORDERSERVICESERVICE_WSDL_LOCATION, new QName("http://service.threeOrder.server.mall.ws.lshop.com/", "WSThreeOrderServiceService"));
    }

    /**
     * 
     * @return
     *     returns WSThreeOrderService
     */
    @WebEndpoint(name = "WSThreeOrderServicePort")
    public WSThreeOrderService getWSThreeOrderServicePort() {
        return super.getPort(new QName("http://service.threeOrder.server.mall.ws.lshop.com/", "WSThreeOrderServicePort"), WSThreeOrderService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WSThreeOrderService
     */
    @WebEndpoint(name = "WSThreeOrderServicePort")
    public WSThreeOrderService getWSThreeOrderServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://service.threeOrder.server.mall.ws.lshop.com/", "WSThreeOrderServicePort"), WSThreeOrderService.class, features);
    }

}
