package com.lshop.common.apache.struts2.dispatcher.ng.filter;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.StrutsStatics;
import org.apache.struts2.dispatcher.Dispatcher;
import org.apache.struts2.dispatcher.mapper.ActionMapping;
import org.apache.struts2.dispatcher.ng.ExecuteOperations;
import org.apache.struts2.dispatcher.ng.InitOperations;
import org.apache.struts2.dispatcher.ng.PrepareOperations;
import org.apache.struts2.dispatcher.ng.filter.FilterHostConfig;

public class StrutsPrepareAndExecuteFilter implements StrutsStatics, Filter {
	protected PrepareOperations prepare;
	protected ExecuteOperations execute;
	protected List<Pattern> excludedPatterns;

	public StrutsPrepareAndExecuteFilter() {
		this.excludedPatterns = null;
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		InitOperations init = new InitOperations();
		try {
			FilterHostConfig config = new FilterHostConfig(filterConfig);
			init.initLogging(config);
			Dispatcher dispatcher = init.initDispatcher(config);
			init.initStaticContentLoader(config, dispatcher);

			this.prepare = new PrepareOperations(filterConfig
					.getServletContext(), dispatcher);
			this.execute = new ExecuteOperations(filterConfig
					.getServletContext(), dispatcher);
			this.excludedPatterns = init.buildExcludedPatternsList(dispatcher);

			postInit(dispatcher, filterConfig);
		} finally {
			init.cleanup();
		}
	}

	protected void postInit(Dispatcher dispatcher, FilterConfig filterConfig) {
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		try {
			if ("/ws/WSOrderService".endsWith(request.getRequestURI())) {//过滤web service接口请求
				chain.doFilter(req, res);
			} else {
				HttpServletResponse response = (HttpServletResponse) res;

				this.prepare.setEncodingAndLocale(request, response);

				this.prepare.createActionContext(request, response);
				this.prepare.assignDispatcherToThread();
				if ((this.excludedPatterns != null)
						&& (this.prepare.isUrlExcluded(request,
								this.excludedPatterns))) {
					chain.doFilter(request, response);
				} else {
					request = this.prepare.wrapRequest(request);
					ActionMapping mapping = this.prepare.findActionMapping(
							request, response, true);
					if (mapping == null) {
						boolean handled = this.execute
								.executeStaticResourceRequest(request, response);
						if (!(handled))
							chain.doFilter(request, response);
					} else {
						this.execute.executeAction(request, response, mapping);
					}
				}

			}
		} finally {
			this.prepare.cleanupRequest(request);
		}

	}

	public void destroy() {
		this.prepare.cleanupDispatcher();
	}
}