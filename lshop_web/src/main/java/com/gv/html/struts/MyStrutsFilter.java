package com.gv.html.struts;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.dispatcher.mapper.ActionMapping;
import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;


public class MyStrutsFilter extends StrutsPrepareAndExecuteFilter{
	public static final Log logger = LogFactory.getLog(MyStrutsFilter.class);
	public void init(FilterConfig filterConfig) throws ServletException {
		super.init(filterConfig);
		//application holder
		ApplicationHolder.prepare = prepare;
		ApplicationHolder.execute = execute;
		ApplicationHolder.servletContext = filterConfig.getServletContext();
	}
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        try {
            prepare.setEncodingAndLocale(request, response);
            prepare.createActionContext(request, response);
            prepare.assignDispatcherToThread();
            if (excludedPatterns != null && prepare.isUrlExcluded(request, excludedPatterns)) {
                chain.doFilter(request, response);
            } else {
                request = prepare.wrapRequest(request);
                ActionMapping mapping = prepare.findActionMapping(request, response, true);
                if (mapping == null) {
                    boolean handled = execute.executeStaticResourceRequest(request, response);
                    if (!handled) {
                        chain.doFilter(request, response);
                    }
                } else {
                	try {
                		execute.executeAction(request, response, mapping);
					} catch (ServletException e) {
						logger.error(e.getMessage());
						String exdev = request.getParameter("exdev");
						if (StringUtils.isNotBlank(exdev) && "true".equals(exdev)) {
							throw e;
						} else {
							logger.error(e.getMessage());
						}
					}
                }
            }
        } finally {
            prepare.cleanupRequest(request);
        }
    }
}
