
package test.ws.server.bz.order.service;

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
@WebServiceClient(name = "WSOrderServiceService", targetNamespace = "http://service.order.bz.server.ws.lshop.com/", wsdlLocation = "http://10.0.2.86:8080/ws/WSOrderService?wsdl")
public class WSOrderServiceService
    extends Service
{

    private final static URL WSORDERSERVICESERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(test.ws.server.bz.order.service.WSOrderServiceService.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = test.ws.server.bz.order.service.WSOrderServiceService.class.getResource(".");
            url = new URL(baseUrl, "http://10.0.2.86:8089/ws/WSOrderService?wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'http://10.0.2.86:8080/ws/WSOrderService?wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        WSORDERSERVICESERVICE_WSDL_LOCATION = url;
    }

    public WSOrderServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WSOrderServiceService() {
        super(WSORDERSERVICESERVICE_WSDL_LOCATION, new QName("http://service.order.bz.server.ws.lshop.com/", "WSOrderServiceService"));
    }

    /**
     * 
     * @return
     *     returns WSOrderService
     */
    @WebEndpoint(name = "WSOrderServicePort")
    public WSOrderService getWSOrderServicePort() {
        return super.getPort(new QName("http://service.order.bz.server.ws.lshop.com/", "WSOrderServicePort"), WSOrderService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WSOrderService
     */
    @WebEndpoint(name = "WSOrderServicePort")
    public WSOrderService getWSOrderServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://service.order.bz.server.ws.lshop.com/", "WSOrderServicePort"), WSOrderService.class, features);
    }

}