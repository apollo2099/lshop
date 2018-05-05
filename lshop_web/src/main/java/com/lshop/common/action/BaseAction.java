/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */
package com.lshop.common.action;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.call.CallResponse;
import com.gv.core.datastructure.call.DwzJsonResponse;
import com.gv.core.datastructure.call.StatusCode;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;
import com.gv.core.exception.GeneralFailureException;
import com.gv.core.proxy.ServiceProxy;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.util.CookieUtil;
import com.lshop.web.userCenter.UserConstant;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author dengby
 * 
 */
@SuppressWarnings("serial")
public class BaseAction extends ActionSupport implements Action {

	public static final Log logger = LogFactory.getLog(BaseAction.class);
	@Resource
	public ServiceProxy serviceProxyLocal;
	public Pagination page = new Pagination();
	public Dto dto = new BaseDto();
	public String serviceId;
	public String methodNames[];
	ServletContext servletContext = null;
	private String flag = "www";
	private String  storeDomain= "http://www.tvpadsc.com";
	private String resDomain = "http://res.mtvpad.com"; 

	public DwzJsonResponse json = new DwzJsonResponse();

	private String exdev = "false";

	public String getExdev() {
		return exdev;
	}

	public void setExdev(String exdev) {
		this.exdev = exdev;
	}
	
	public String getFlag() {

		return flag;
	}

	public void setFlag(String flag) {
		this.flag=flag;
	}

	protected Object doService(String serviceId, String methodName, Dto dto)throws GeneralFailureException {
		if(dto==null){
			dto= new BaseDto();
		}
		dto.put("flag", flag);
		CallResponse<?> callEvent = serviceProxyLocal.doService(serviceId,methodName, dto);
		checkError(callEvent);

		return callEvent.getData();
	}

	@SuppressWarnings("unchecked")
	protected void checkError(CallResponse responseEvent)
			throws ActionException {

		try {
			Throwable ex = responseEvent.getException();
			if (ex != null) {
				logger.error("get Exception from CallResponse Evente:"
						+ ex.getMessage());
				logger.error(ex);
				json.reSetStatusCode(StatusCode.S_SystemError, "绯荤粺閿欒");
				throw new ActionException(ex.getMessage(), ex.getCause());
			} else {
				Object obj = responseEvent.getData();
				if (null != obj && obj instanceof Dto) {
					Dto dto = (Dto) obj;
					if (ObjectUtils.isNotEmpty(dto.getAppCode())) {
						json.reSetStatusCode(dto.getAppCode(), dto.getAppMsg());
					} else {
						json.reSetStatusCode(StatusCode.M_Success);
					}
				} else {
					json.reSetStatusCode(StatusCode.M_Success);
				}
			}
		} catch (NullPointerException e) {
			json.reSetStatusCode(StatusCode.S_SystemError, "绯荤粺閿欒");
			throw new ActionException(e.getMessage(), e.getCause());
		}
	}

	protected HttpServletResponse addCookie(String name, String value,
			boolean isEncode, int maxAge) throws Exception {
		CookieUtil cookieUtil = new CookieUtil();
		return cookieUtil.createCookie(this.getResponse(), name, value,
				isEncode, maxAge);
	}

	protected HttpServletResponse addCookie(String name, String value,
			boolean isEncode, int maxAge, String domain) throws Exception {
		CookieUtil cookieUtil = new CookieUtil();
		return cookieUtil.createCookie(this.getResponse(), name, value,
				isEncode, maxAge, domain);
	}

	protected void removeByCookie(String cookieName) throws Exception {
		CookieUtil cookieUtil = new CookieUtil();
		cookieUtil.removeByCookie(this.getRequest(), this.getResponse(),
				cookieName);
	}

	protected void removeByCookie(String cookieName, String domain)
			throws Exception {
		CookieUtil cookieUtil = new CookieUtil();
		cookieUtil.removeByCookie(this.getRequest(), this.getResponse(),
				cookieName, domain);
	}

	/**
	 * *em=196@126.com*ud=454*rn=wqh*nn=null*se=F
	 * 
	 * @param name
	 * @param isDecode
	 * @return
	 * @throws Exception
	 */
	protected String getCookieValue(String name, boolean isDecode)
			throws Exception {
		String value = CookieUtil.getValue(this.getRequest(), name, isDecode);
		if (value == null || value.length() == 0) {
			return null;
		} else {
			return value;
		}
	}

	protected String getCookieValue(String name, boolean isDecode, String domain)
			throws Exception {
		String value = CookieUtil.getValue(this.getRequest(), name, isDecode,
				domain);
		if (value == null || value.length() == 0) {
			return null;
		} else {
			return value;
		}

	}

	/**
	 * 将cookie中的值分解后放入MAP中。 *em=196@126.com*ud=454*rn=wqh*nn=null*se=F
	 * 
	 * @param name
	 * @param isDecode
	 * @return
	 * @throws Exception
	 */
	protected Map<String, String> getCookieValueToMap(String name,
			boolean isDecode) throws Exception {
		Map<String, String> resultMap = new HashMap<String, String>();
		String cookieValue = getCookieValue(name, isDecode);
		if (cookieValue == null || "".equals(cookieValue)
				|| cookieValue.indexOf("*") < 0) {
			return resultMap;
		}

		StringTokenizer token = new StringTokenizer(cookieValue, "*");
		while (token.hasMoreTokens()) {
			String value = token.nextToken();
			StringTokenizer bToken = new StringTokenizer(value, "=");
			String mapKey = bToken.nextToken();
			String mapValue = bToken.nextToken();
			resultMap.put(mapKey, mapValue);
		}

		return resultMap;
	}

	// 穿越网关，代理服务器
	public static String getRemoteAddress() {
		HttpServletRequest request = ServletActionContext.getRequest();
		if (request == null) {
			return "127.0.0.1";
		}
		String forwards = request.getHeader("x-forwarded-for");
		if (forwards == null || forwards.trim().length() == 0
				|| "unknown".equalsIgnoreCase(forwards)) {

			forwards = request.getHeader("Proxy-Client-IP");
		}
		if (forwards == null || forwards.trim().length() == 0
				|| "unknown".equalsIgnoreCase(forwards)) {

			forwards = request.getHeader("WL-Proxy-Client-IP");
		}
		if (forwards == null || forwards.trim().length() == 0
				|| "unknown".equalsIgnoreCase(forwards)) {

			forwards = request.getRemoteAddr();
		}
		if (forwards == null || forwards.trim().length() == 0
				|| "unknown".equalsIgnoreCase(forwards)) {
			forwards = request.getHeader("X-Real-IP");
		}
		if (forwards != null && forwards.trim().length() > 0) {
			String[] ips = forwards.split(",");
			String ip = "";
			if (ips.length > 0) {
				for (int i = 0; i < ips.length; i++) {
					if (ips[i] != null && ips[i].trim().length() > 0
							&& !"unknown".equalsIgnoreCase(ips[i])) {
						ip = ips[i];
						break;
					}
				}
			}
			return ip;
		}
		return forwards;
	}
	
	
	/**
	 * 获取用户登录email
	* @author:luohaidong 
	* @param  @return 
	* @return String
	* @throws
	 */
	protected  String getUserLoginEmail() {
		String email = null;
		try {
			Map<String, String> userinfo = this.getCookieValueToMap(
					UserConstant.USER_CENTER, true);
			email = userinfo.get("email");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return email;
	}


	protected static final String JSON = "json";
	protected static final String AJAX = "ajax";

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String[] getMethodNames() {
		return methodNames;
	}

	public void setMethodNames(String[] methodNames) {
		this.methodNames = methodNames;
	}

	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	public HttpSession getSession() {
		return getRequest().getSession();
	}

	public ServletContext getServletContext() {

		if (this.servletContext == null)
			return ServletActionContext.getServletContext();
		return this.servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public DwzJsonResponse getJson() {
		return json;
	}

	public void setJson(DwzJsonResponse json) {
		this.json = json;
	}

	public String getStoreDomain() {
		return storeDomain;
	}

	public void setStoreDomain(String storeDomain) {
		this.storeDomain = storeDomain;
	}
	
	public String getResDomain() {
		return resDomain;
	}

	public void setResDomain(String resDomain) {
		this.resDomain = resDomain;
	}

}
