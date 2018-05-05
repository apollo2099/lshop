package com.lshop.web.guarantee.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.core.proxy.ServiceConstants;
import com.lshop.common.action.BaseAction;
import com.lshop.ws.his.tvbox.GuaranteeInfoDto;
import com.lshop.ws.his.tvbox.WSStbGuaranteeInfoService;

/**
 * 
 * 查询保修期  
 *
 */
@Controller("GuaranteeAction")
@Scope("prototype")
public class GuaranteeAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	private static final Log logger = LogFactory.getLog(GuaranteeAction.class);

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String query() throws Exception{
		HttpServletRequest request = this.getRequest();
		HttpServletResponse response = this.getResponse();
		String mac = request.getParameter("mac");
		dto.put("mac", mac);
		WSStbGuaranteeInfoService wsStbGuaranteeInfoService = (WSStbGuaranteeInfoService)ServiceConstants.beanFactory.getBean("WSStbGuaranteeInfoService");
		GuaranteeInfoDto guaranteeInfoDto = wsStbGuaranteeInfoService.getGuaranteeInfoByMac(mac);
		
		String status = guaranteeInfoDto.getStatus();	// status （0 = 未过期；1 = 已过期；2 = 未激活；4=未激活保修期）
		String msg = ""; 
		if ("0".equals(status)) {
			String date = guaranteeInfoDto.getExpiredGuaranteedDate();
			msg = date.substring(0, 10);
		}
		
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("mac", mac);
		result.put("status", status);
		result.put("msg", msg);
		JSONObject json = JSONObject.fromObject(result);
		try {
			response.setCharacterEncoding("UTF-8");
			response.getWriter().print(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return null;
	}	
}
