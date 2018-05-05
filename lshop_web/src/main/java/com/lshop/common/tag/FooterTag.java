package com.lshop.common.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.impl.BaseDto;
import com.gv.core.proxy.ServiceConstants;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.util.Constants;
import com.lshop.web.tvpad.service.IndexService;

/**
 * 广告模块标签
 * @author zhengxue
 * @version 1.0 2012-07-21
 *
 */
@SuppressWarnings("serial")
public class FooterTag extends BodyTagSupport {
	
	public Dto dto = new BaseDto();
	
	public int doEndTag() throws JspException {
		
		JspWriter out = pageContext.getOut();
		StringBuilder html=new StringBuilder();
		
		//得到底部版权信息标签所涉及到的service
		IndexService indexService=(IndexService)ServiceConstants.beanFactory.getBean("IndexService");		
		
		//获取店铺标志
	    String serverName = pageContext.getRequest().getServerName();
	    String storeFlag = Constants.STORE_IDS.get(serverName.trim());
	    
		//获取店铺信息
		dto.put("storeFlag", storeFlag);
		LvStore lvStore=indexService.getStore(dto);
		
		if(null!=lvStore){
			html.append(lvStore.getFooterInfo());
			try {
				out.write(html.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return super.doEndTag();

	}
}
