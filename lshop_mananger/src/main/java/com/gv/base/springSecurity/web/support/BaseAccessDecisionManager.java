package com.gv.base.springSecurity.web.support;

import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
/**
 * 
 * 项目名称：base_demo   
 * 类名称：SysAccessDecisionManager   
 * 类描述：   
 * 创建人：  yangzheng
 * 创建时间：2012-3-1 下午03:04:32   
 * 修改人：yangzheng   
 * 修改时间：2012-3-1 下午03:04:32   
 * 修改备注：   
 * @version
 */
 
public class BaseAccessDecisionManager implements AccessDecisionManager {
	private static final Log logger = LogFactory.getLog(BaseAccessDecisionManager.class);
    //In this method, need to compare authentication with configAttributes.
    // 1, A object is a URL, a filter was find permission configuration by this URL, and pass to here.
    // 2, Check authentication has attribute in permission configuration (configAttributes)
    // 3, If not match corresponding authentication, throw a AccessDeniedException.
    public void decide(Authentication authentication, Object object,Collection<ConfigAttribute> configAttributes)throws AccessDeniedException, InsufficientAuthenticationException {
        if(configAttributes == null){
            return ;
        }
        if(logger.isInfoEnabled()){
			  logger.info("222222222222222222"+object.toString());  //object is a URL.
		}
        Iterator<ConfigAttribute> ite=configAttributes.iterator();
        while(ite.hasNext()){
            ConfigAttribute ca=ite.next();
            //需要的权限集合
            String needAuthority=((SecurityConfig)ca).getAttribute();
            for(GrantedAuthority ga:authentication.getAuthorities()){
                if(needAuthority.equals(ga.getAuthority())){  
                    return;
                }
            }
        }
        throw new AccessDeniedException("no right");
    }

    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    public boolean supports(Class<?> clazz) {
        return true;
    }


}
