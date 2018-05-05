/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvHelp.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;
import com.gv.core.util.ObjectUtils;
import com.gv.core.web.action.BaseAction;
import com.lshop.common.pojo.logic.LvArea;
import com.lshop.common.pojo.logic.LvHelp;
import com.lshop.common.pojo.logic.LvHelpType;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.util.CodeUtils;
import com.lshop.html.service.InitLinkDataService;
import com.lshop.manage.common.action.BaseManagerAction;

/**
 * @author 好视网络-软件二部：唐迪
 * @version 1.0
 * @since 1.0
 */@Controller("LvHelpMngAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvHelpMngAction extends BaseManagerAction{
	private static final Log logger	= LogFactory.getLog(LvHelpMngAction.class);
	private LvHelp lvHelp =new LvHelp() ;
	private LvHelpType lvHelpType=new LvHelpType();
	@Resource
	private InitLinkDataService initLinkDataService;
	/**
	 * 列表
	 * @return
	 * @throws ActionException
	 */
	public String list()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvHelpMngAction.list() method begin*****");
		}

		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("page",page);
		dto.put("model", lvHelp);
		
		page = (Pagination)this.doService("LvHelpService", "findPage", dto);
		this.request.setAttribute("helpTypeList", this.doService("LvHelpTypeService", "findAll", dto));//帮助类别列表
		
		//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","findAllStore", dto);
		this.getRequest().setAttribute("storeList", storeList);
    	if (logger.isInfoEnabled()){
			logger.info("***** LvHelpMngAction.list() method end*****");
		}
		return LIST;
	}
	
	/**
	 * 进入添加页面
	 * @return
	 * @throws ActionException
	 */
	public String befSave()throws ActionException{
		this.request.setAttribute("helpTypeList", this.doService("LvHelpTypeService", "findAll", dto));//帮助类别列表
		
		//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","findAllStore", dto);
		this.getRequest().setAttribute("storeList", storeList);
		return "befSave";
	}
	
	/**
	 * 添加
	 * @return
	 * @throws ActionException
	 */
	public String save()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvHelpMngAction.save() method begin*****");
		}
		//根据实际情况添加
		lvHelp.setCode(CodeUtils.getCode());//code设置
		lvHelp.setCreateTime(new Date());   //创建时间
		dto.put("model", lvHelp);
		this.doService("LvHelpService", "save", dto);

		//执行单个页面静态化任务
		String htmlPath = "/help"+lvHelp.getHelpId()+".html";
		initLinkDataService.sendHtmlToWeb(htmlPath, lvHelp.getStoreId());
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvHelpMngAction.save() method end*****");
		}
		return AJAX;
	}
	
	

	/**
	 * 详情
	 * @return
	 * @throws ActionException
	 */
	public String view()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvHelpMngAction.view() method begin*****");
		}
		dto.put("model", lvHelp);
		lvHelp = (LvHelp)this.doService("LvHelpService", "get", dto);
		
		dto.put("typeId", lvHelp.getHelpId());
		lvHelpType = (LvHelpType)this.doService("LvHelpTypeService", "getHelpType", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvHelpMngAction.view() method end*****");
		}
		return "view";
	}
	
	/**
	 * 跳入编辑页面
	 * @return
	 * @throws ActionException
	 */
	public String befEdit()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvHelpMngAction.befEdit() method begin*****");
		}
		dto.put("lvHelpType", lvHelpType);
		this.request.setAttribute("helpTypeList", this.doService("LvHelpTypeService", "findAll", dto));//帮助类别列表
		dto.put("model", lvHelp);
		lvHelp = (LvHelp)this.doService("LvHelpService", "get", dto);
		
		//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","findAllStore", dto);
		this.getRequest().setAttribute("storeList", storeList);
		if (logger.isInfoEnabled()){
			logger.info("***** LvHelpMngAction.befEdit() method end*****");
		}
		return "edit";
	}
	/**
	 * 编辑
	 * @return
	 * @throws ActionException
	 */
	public String edit()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvHelpMngAction.edit() method begin*****");
		}
		//获取登录用户信息
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");
		lvHelp.setModifyUserId(users.getId());
		lvHelp.setModifyUserName(users.getUserName());
		lvHelp.setModifyTime(new Date());
		dto.put("model", lvHelp);
		this.doService("LvHelpService", "update", dto);

		//执行单个页面静态化任务
		String htmlPath = "/help"+lvHelp.getHelpId()+".html";
		initLinkDataService.sendHtmlToWeb(htmlPath, lvHelp.getStoreId());
		if (logger.isInfoEnabled()){
			logger.info("***** LvHelpMngAction.edit() method end*****");
		}
		return AJAX;
	}
	
	/**
	 * 删除
	 * @return
	 * @throws ActionException
	 */
	public String del()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvHelpMngAction.del() method begin*****");
		}
		dto.put("model", lvHelp);
		//删除model
		this.doService("LvHelpService", "del", dto);
        json.doNavTabTodo();
        
		if (logger.isInfoEnabled()){
			logger.info("***** LvHelpMngAction.del() method end*****");
		}
		return AJAX;
	}
	
	/**
	 * 批量删除
	 * @return
	 * @throws ActionException
	 */
	public String delList()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvHelpMngAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				lvHelp.setId(Integer.parseInt(temp_ids[i].trim()));
				dto.put("model", lvHelp);
				//删除model
				this.doService("LvHelpService", "del", dto);
				}
			}
		}
		json.doNavTabTodo();
		if (logger.isInfoEnabled()){
			logger.info("***** LvHelpMngAction.delList() method end*****");
		}
		return AJAX;
	}

	/**
	 * 
	 * @Method: toHelpType 
	 * @Description:  [根据店铺标志查询对应店铺下的帮助类型]  
	 * @Author:       [liaoxiongjian]     
	 * @CreateDate:   [2013-3-23 下午03:35:57]   
	 * @UpdateUser:   [liaoxiongjian]     
	 * @UpdateDate:   [2013-3-23 下午03:35:57]   
	 * @UpdateRemark: [说明本次修改内容]  
	 * @return 
	 * @return String
	 */
	public String toHelpType(){
		PrintWriter out = null;
		dto.put("lvHelpType", lvHelpType);
		List<LvHelpType> list=(List<LvHelpType>) this.doService("LvHelpTypeService", "findAll", dto);
		List listTmp=new ArrayList();
		Map<String,Object>  mapTmp=new HashMap<String,Object>();
		if(list!=null&&list.size()>0){
			for (int i = 0; i < list.size(); i++) {
				LvHelpType lvHelpType=list.get(i);
				Map<String,Object>  map=new HashMap<String,Object>();
				map.put("helpId", lvHelpType.getId());
				map.put("helpName", lvHelpType.getName());
				listTmp.add(map);
			}
			mapTmp.put("listTmp", listTmp);
			try {
				response.setContentType("text/html;charset=UTF-8");   
				response.setCharacterEncoding("UTF-8");
			    out=this.getResponse().getWriter();
			    String jsonTmp = JSONObject.fromObject(mapTmp).toString();
			    logger.info("json_message"+jsonTmp);
				out.print(jsonTmp );
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	public LvHelpType getLvHelpType() {
		return lvHelpType;
	}

	public void setLvHelpType(LvHelpType lvHelpType) {
		this.lvHelpType = lvHelpType;
	}
	
	public LvHelp getLvHelp() {
		return lvHelp;
	}

	public void setLvHelp(LvHelp lvHelp) {
		this.lvHelp = lvHelp;
	}

}
