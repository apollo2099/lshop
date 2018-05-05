
package com.lshop.ws.boss.account;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.lshop.ws.web.userlist package. 
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

    private final static QName _QueryUserListResponse_QNAME = new QName("com.gv.webservice", "queryUserListResponse");
    private final static QName _PageInfoDto_QNAME = new QName("com.gv.webservice", "PageInfoDto");
    private final static QName _QueryUserList_QNAME = new QName("com.gv.webservice", "queryUserList");
    private final static QName _UserDetailInfoDto_QNAME = new QName("com.gv.webservice", "UserDetailInfoDto");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.lshop.ws.web.userlist
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link QueryUserList }
     * 
     */
    public QueryUserList createQueryUserList() {
        return new QueryUserList();
    }

    /**
     * Create an instance of {@link UserDetailDto }
     * 
     */
    public UserDetailDto createUserDetailDto() {
        return new UserDetailDto();
    }

    /**
     * Create an instance of {@link PageInfoDto }
     * 
     */
    public PageInfoDto createPageInfoDto() {
        return new PageInfoDto();
    }

    /**
     * Create an instance of {@link QueryUserListResponse }
     * 
     */
    public QueryUserListResponse createQueryUserListResponse() {
        return new QueryUserListResponse();
    }

    /**
     * Create an instance of {@link UserDetailsDto }
     * 
     */
    public UserDetailsDto createUserDetailsDto() {
        return new UserDetailsDto();
    }

    /**
     * Create an instance of {@link ArrayList }
     * 
     */
    public ArrayList createArrayList() {
        return new ArrayList();
    }

    /**
     * Create an instance of {@link UserDetailInfoDto }
     * 
     */
    public UserDetailInfoDto createUserDetailInfoDto() {
        return new UserDetailInfoDto();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryUserListResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "com.gv.webservice", name = "queryUserListResponse")
    public JAXBElement<QueryUserListResponse> createQueryUserListResponse(QueryUserListResponse value) {
        return new JAXBElement<QueryUserListResponse>(_QueryUserListResponse_QNAME, QueryUserListResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PageInfoDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "com.gv.webservice", name = "PageInfoDto")
    public JAXBElement<PageInfoDto> createPageInfoDto(PageInfoDto value) {
        return new JAXBElement<PageInfoDto>(_PageInfoDto_QNAME, PageInfoDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueryUserList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "com.gv.webservice", name = "queryUserList")
    public JAXBElement<QueryUserList> createQueryUserList(QueryUserList value) {
        return new JAXBElement<QueryUserList>(_QueryUserList_QNAME, QueryUserList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserDetailInfoDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "com.gv.webservice", name = "UserDetailInfoDto")
    public JAXBElement<UserDetailInfoDto> createUserDetailInfoDto(UserDetailInfoDto value) {
        return new JAXBElement<UserDetailInfoDto>(_UserDetailInfoDto_QNAME, UserDetailInfoDto.class, null, value);
    }

}
