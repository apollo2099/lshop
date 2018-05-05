package com.lshop.html.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.gv.core.exception.ActionException;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.manage.common.action.BaseManagerAction;

/**
 * 
 * @author xusl
 * Date 2010-04-19
 * lshop 初始化静态页面
 */
@Controller("InitHtmlAction")
@Scope("prototype")
public class InitHtmlAction extends BaseManagerAction{


	private static final long serialVersionUID = 1L;
	
	private static final Log	logger	= LogFactory.getLog(InitHtmlAction.class);
	private String staticurl;
	private String message;
	private String note; 


	/**
	 *  初始化生成静态页面
	 *  批量生成
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public synchronized String inithtml() throws ActionException{
		PrintWriter out = null;
		this.setFlag();
		if(!"".equals(this.getFlag())){
			if (logger.isInfoEnabled()){
				logger.info("***** InitHtmlAction.init() method begin*****");
			}
			try{
				List<LvStore> storeFlagList=new ArrayList<LvStore>();
				List<LvStore> storeList=(List<LvStore>)this.doService("LvStoreService", "findAllStore", dto);
				if(ObjectUtils.isNotEmpty(storeList)){
					for (LvStore lvStore : storeList) {
						if(lvStore.getParentCode().equals("0")){
							dto.put("parentCode", lvStore.getCode());
							List<LvStore> st=(List<LvStore>)this.doService("LvStoreService", "findStoreByParent", dto);
							if(ObjectUtils.isNotEmpty(st)){
								for (LvStore s : st) {
									storeFlagList.add(s);
								}							
							}
						}
						storeFlagList.add(lvStore);
					}
				}
				dto.put("storeList", storeFlagList);
				super.doService( "InitLinkDataService", "sendStoreToWeb", dto);
//				List list=new ArrayList();
//				for (LvStore lvStore : storeList) {
//					String domainname=request.getScheme()+"://"+lvStore.getDomainName();
//					String htmlpath=request.getRealPath("/")+"web/"+lvStore.getStoreFlag();
//					HtmlStaticDto htmlDto=new HtmlStaticDto();
//					htmlDto.setDomainname(domainname);
//					htmlDto.setHtmlpath(htmlpath);
//					htmlDto.setStoreFlag(lvStore.getStoreFlag());
//					list.add(htmlDto);
//					dto.put("note", note);
//				}
//				dto.put("list", list);
//				super.doService( "InitLinkDataService", "init", dto);
				
				//
				Map<String,Object>  mapTmp=new HashMap<String,Object>();
				response.setContentType("text/html;charset=UTF-8");   
				response.setCharacterEncoding("UTF-8");
			    out=this.getResponse().getWriter();
			    String jsonTmp = JSONObject.fromObject(mapTmp).toString();
			    logger.info("json_message"+jsonTmp);
			    out.print(jsonTmp);
				out.close();
				
				json.setMessage("缓存更新成功！");
				json.setCallbackType(null);
			}
			catch(Exception ex){
				json.setStatusCode(json.STATUS_CODE_ERROR);
				json.setMessage("更新异常请重试！");
				json.setCallbackType(null);
			}
			
			if (logger.isInfoEnabled()){
				logger.info("***** InitHtmlAction.init() method End*****");
			}
			
		}else{
			json.setStatusCode(json.STATUS_CODE_ERROR);
			json.setMessage("该后台 用户不属于任何店铺，不能更新缓存！");
			json.setCallbackType(null);
		}
		return null;
	}
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStaticurl() {
		return staticurl;
	}

	public void setStaticurl(String staticurl) {
		this.staticurl = staticurl;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

	
	

}
