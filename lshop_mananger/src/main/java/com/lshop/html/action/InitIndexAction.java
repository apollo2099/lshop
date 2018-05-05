package com.lshop.html.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.gv.core.exception.ActionException;
import com.lshop.common.util.Constants;
import com.lshop.common.util.ShellUtil;
import com.lshop.manage.common.action.BaseManagerAction;
/**
 * 
 * Simple to Introduction  
 * @ProjectName:  [lshop_new] 
 * @Package:      [com.lshop.html.action.InitIndexAction.java]  
 * @ClassName:    [InitIndexAction]   
 * @Description:  [初始化搜索引擎服务]   
 * @Author:       [liaoxiongjian]   
 * @CreateDate:   [2013-2-26 上午10:45:51]   
 * @UpdateUser:   [liaoxiongjian]   
 * @UpdateDate:   [2013-2-26 上午10:45:51]   
 * @UpdateRemark: [说明本次修改内容]  
 * @Version:      [v3.0]
 */
@Controller("InitIndexAction")
@Scope("prototype")
public class InitIndexAction extends BaseManagerAction{
	private static final Log	logger	= LogFactory.getLog(InitIndexAction.class);
	private static final long serialVersionUID = 3773204016379301671L;
	
	public String initIndex() throws ActionException{
		try {

				PrintWriter out = null;
				
				this.doService("InitIndexDataService", "init", dto);
				json.setMessage("初始化搜索引擎成功！");
				json.setCallbackType(null);
				
				Map<String,Object>  mapTmp=new HashMap<String,Object>();
				response.setContentType("text/html;charset=UTF-8");   
				response.setCharacterEncoding("UTF-8");
				out=this.getResponse().getWriter();
				String jsonTmp = JSONObject.fromObject(mapTmp).toString();
				logger.info("json_message"+jsonTmp);
				out.print(jsonTmp);
				out.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String synResource()throws ActionException, IOException{
		//
		String basthPath=this.getText("lshop.web.syn.resource.path");
		ShellUtil.ExeShell(basthPath);
		return null;
		
	}
}
