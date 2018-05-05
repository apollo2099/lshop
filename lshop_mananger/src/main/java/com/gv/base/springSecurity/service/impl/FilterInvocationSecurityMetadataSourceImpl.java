package com.gv.base.springSecurity.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntUrlPathMatcher;
import org.springframework.security.web.util.UrlMatcher;
import org.springframework.stereotype.Service;

import com.gv.base.springSecurity.common.pojo.BaseAuthorities;
import com.gv.base.springSecurity.common.pojo.BaseResources;
import com.gv.core.hibernate3.HibernateBaseDAO;


/**
 * 
 * 项目名称：base_demo   
 * 类名称：FilterInvocationSecurityMetadataSourceImpl   
 * 类描述： 最核心的地方，就是提供某个资源对应的权限定义，即getAttributes方法返回的结果.
 * 		        注意，我例子中使用的是AntUrlPathMatcher这个path matcher来检查URL是否与资源定义匹配，
 *         事实上你还要用正则的方式来匹配，或者自己实现一个matcher。
 * 
 *         此类在初始化时，应该取到所有资源及其对应角色的定义
 * 
 *         说明：对于方法的spring注入，只能在方法和成员变量里注入，
 *         如果一个类要进行实例化的时候，不能注入对象和操作对象，
 *         所以在构造函数里不能进行操作注入的数据。  
 * 创建人：  yangzheng
 * 创建时间：2012-3-1 下午03:01:57   
 * 修改人：yangzheng   
 * 修改时间：2012-3-1 下午03:01:57   
 * 修改备注：   
 * @version
 */

@Service("FilterInvocationSecurityMetadataSource")
public class FilterInvocationSecurityMetadataSourceImpl implements FilterInvocationSecurityMetadataSource {
	private HibernateBaseDAO dao;

	private UrlMatcher urlMatcher = new AntUrlPathMatcher();
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	public FilterInvocationSecurityMetadataSourceImpl(HibernateBaseDAO dao) {
		this.dao=dao;
		loadResourceDefine();
	}

	private void loadResourceDefine() {
		
		List<BaseAuthorities> baseAuthoritieList=(List<BaseAuthorities>) dao.find("from BaseAuthorities ",null);
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		

		for (BaseAuthorities baseAuthorities : baseAuthoritieList) {
			ConfigAttribute ca = new SecurityConfig(baseAuthorities.getAuthoritiesCode());// "ROLE_ADMIN"
			
			//获取资源
			List<BaseResources> baseResourceList = (List<BaseResources>) dao.find("from BaseResources where resourceCode in(select resourceCode from BaseAuthoritiesResources where authorityCode='"+baseAuthorities.getAuthoritiesCode()+"')",null);
			for (BaseResources baseResource : baseResourceList) {
				String url = baseResource.getResourceString();
				// 判断资源和权限的对应关系，如果已经存在，要进行增加
				if (resourceMap.containsKey(url)) {
					Collection<ConfigAttribute> value = resourceMap.get(url);
					value.add(ca);
					resourceMap.put(url, value);
				} else {
					Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
					atts.add(ca);
					resourceMap.put(url, atts);
				}
			}
		
		}
	}

	// According to a URL, Find out permission configuration of this URL.
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		// guess object is a URL.
		String url = ((FilterInvocation) object).getRequestUrl();
		Iterator<String> ite = resourceMap.keySet().iterator();
		while (ite.hasNext()) {
			String resURL = ite.next();
			if (urlMatcher.pathMatchesUrl(url, resURL)) {
				return resourceMap.get(resURL);
			}
		}
		return null;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}
	
	public  void refreshResource() {
		resourceMap.clear();
		loadResourceDefine();
	}

}

