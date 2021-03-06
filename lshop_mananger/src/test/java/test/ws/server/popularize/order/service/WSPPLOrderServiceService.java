
package test.ws.server.popularize.order.service;

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
@WebServiceClient(name = "WSPPLOrderServiceService", targetNamespace = "http://service.order.popularize.server.ws.lshop.com/", wsdlLocation = "http://10.0.2.86:8089/ws/WSPPLOrderService?wsdl")
public class WSPPLOrderServiceService
    extends Service
{

    private final static URL WSPPLORDERSERVICESERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(test.ws.server.popularize.order.service.WSPPLOrderServiceService.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = test.ws.server.popularize.order.service.WSPPLOrderServiceService.class.getResource(".");
            url = new URL(baseUrl, "http://10.0.2.86:8089/ws/WSPPLOrderService?wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'http://10.0.2.86:8089/ws/WSPPLOrderService?wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        WSPPLORDERSERVICESERVICE_WSDL_LOCATION = url;
    }

    public WSPPLOrderServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WSPPLOrderServiceService() {
        super(WSPPLORDERSERVICESERVICE_WSDL_LOCATION, new QName("http://service.order.popularize.server.ws.lshop.com/", "WSPPLOrderServiceService"));
    }

    /**
     * 
     * @return
     *     returns WSPPLOrderService
     */
    @WebEndpoint(name = "WSPPLOrderServicePort")
    public WSPPLOrderService getWSPPLOrderServicePort() {
        return super.getPort(new QName("http://service.order.popularize.server.ws.lshop.com/", "WSPPLOrderServicePort"), WSPPLOrderService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WSPPLOrderService
     */
    @WebEndpoint(name = "WSPPLOrderServicePort")
    public WSPPLOrderService getWSPPLOrderServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://service.order.popularize.server.ws.lshop.com/", "WSPPLOrderServicePort"), WSPPLOrderService.class, features);
    }

}
