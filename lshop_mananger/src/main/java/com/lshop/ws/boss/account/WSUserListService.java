
package com.lshop.ws.boss.account;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebService(name = "WSUserListService", targetNamespace = "com.gv.webservice")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface WSUserListService {


    /**
     * 
     * @param startdate
     * @param page
     * @param status
     * @param enddate
     * @param key
     * @param size
     * @return
     *     returns com.lshop.ws.web.userlist.UserDetailsDto
     */
    @WebMethod
    @WebResult(name = "result", targetNamespace = "")
    @RequestWrapper(localName = "queryUserList", targetNamespace = "com.gv.webservice", className = "com.lshop.ws.boss.account.QueryUserList")
    @ResponseWrapper(localName = "queryUserListResponse", targetNamespace = "com.gv.webservice", className = "com.lshop.ws.boss.account.QueryUserListResponse")
    public UserDetailsDto queryUserList(
        @WebParam(name = "key", targetNamespace = "")
        String key,
        @WebParam(name = "startdate", targetNamespace = "")
        String startdate,
        @WebParam(name = "enddate", targetNamespace = "")
        String enddate,
        @WebParam(name = "status", targetNamespace = "")
        Integer status,
        @WebParam(name = "page", targetNamespace = "")
        int page,
        @WebParam(name = "size", targetNamespace = "")
        int size);

}
