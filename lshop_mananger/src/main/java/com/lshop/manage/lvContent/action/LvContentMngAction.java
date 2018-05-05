/**
 * <p>Title: BaseAction.java </p>
 * <p>Description:  </p>
 * <p>Copyright: ShenZhen GreatVision Network Technology Co.,Ltd. </p>
 * <p>Company: 深圳好视网络科技有限公司
 */

package com.lshop.manage.lvContent.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.base.springSecurity.common.pojo.BaseUsers;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;
import com.gv.core.util.ObjectUtils;
import com.lshop.common.pojo.logic.LvContent;
import com.lshop.common.pojo.logic.LvProduct;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.pojo.logic.LvTplDetail;
import com.lshop.common.util.CodeUtils;
import com.lshop.manage.common.action.BaseManagerAction;

import freemarker.template.TemplateException;

/**
 * @author 好视网络-网站研发部：唐迪
 * @version 1.0
 * @since 1.0
 */@Controller("LvContentMngAction")
@Scope("prototype")
@SuppressWarnings("all")
public class LvContentMngAction extends BaseManagerAction{
	private static final Log logger	= LogFactory.getLog(LvContentMngAction.class);
	private LvContent lvContent = new LvContent();
    private List<LvTplDetail> detailList;
	public LvContent getLvContent() {
		return lvContent;
	}

	public void setLvContent(LvContent lvContent) {
		this.lvContent = lvContent;
	}
	
	/**
	 * 进入添加页面
	 * @return
	 * @throws ActionException
	 */
	public String befSave()throws ActionException{
//		this.setFlag();
//		dto.put("storeFlag", this.getFlag());
//		detailList=(List)this.doService("LvContentService", "findDefulatTplDetail", dto);  
		
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
			logger.info("***** LvContentMngAction.save() method begin*****");
		}
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");//后台登录信息
	    lvContent.setModifyUserId(users.getId());
	    lvContent.setModifyUserName(users.getUserName());
	    lvContent.setCode(CodeUtils.getCode());
		lvContent.setIsdel((short)0);
		lvContent.setCreateTime(new Date());
		dto.put("model", lvContent);
		this.doService("LvContentService", "save", dto);
		
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** LvContentMngAction.save() method end*****");
		}
		return AJAX;
	}
	
	/**
	 * 列表
	 * @return
	 * @throws ActionException
	 */
	public String list()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvContentMngAction.list() method begin*****");
		}
		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("model", lvContent);
		
		page = (Pagination)this.doService("LvContentService", "findPage", dto);

		//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","getAll", dto);
		this.getRequest().setAttribute("storeList", storeList);
		
    	if (logger.isInfoEnabled()){
			logger.info("***** LvContentMngAction.list() method end*****");
		}
		return LIST;
	}

	/**
	 * 详情
	 * @return
	 * @throws ActionException
	 */
	public String view()throws ActionException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvContentMngAction.view() method begin*****");
		}
		dto.put("model", lvContent);
		lvContent = (LvContent)this.doService("LvContentService", "get", dto);
		if (logger.isInfoEnabled()){
			logger.info("***** LvContentMngAction.view() method end*****");
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
			logger.info("***** LvContentMngAction.befEdit() method begin*****");
		}
		dto.put("model", lvContent);
		lvContent = (LvContent)this.doService("LvContentService", "get", dto);
//		this.setFlag();
		dto.put("storeFlag", lvContent.getStoreFlag());
		detailList=(List)this.doService("LvContentService", "findDefulatTplDetail", dto);  
		
		//根据当前用户查询对应用户的店铺信息（可支持多角色操作）
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","findAllStore", dto);
		this.getRequest().setAttribute("storeList", storeList);
		
		if (logger.isInfoEnabled()){
			logger.info("***** LvContentMngAction.befEdit() method end*****");
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
			logger.info("***** LvContentMngAction.edit() method begin*****");
		}
		BaseUsers users =(BaseUsers)getSession().getAttribute("USER");//后台登录信息
	    lvContent.setModifyUserId(users.getId());
	    lvContent.setModifyUserName(users.getUserName());
	    lvContent.setModifyTime(new Date());
		dto.put("model", lvContent);
		this.doService("LvContentService", "update", dto);
		json.setStatusCode(200);
		if (logger.isInfoEnabled()){
			logger.info("***** LvContentMngAction.edit() method end*****");
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
			logger.info("***** LvContentMngAction.del() method begin*****");
		}
		dto.put("model", lvContent);
		this.doService("LvContentService", "del", dto);

		json.setStatusCode(200);

		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvContentMngAction.del() method end*****");
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
			logger.info("***** LvContentMngAction.delList() method begin*****");
		}
		if (ids != null && ids.length()> 0 )
		{
			String[] temp_ids= ids.split(",");
			for( int i=0; i<temp_ids.length; i++)
			{
				if (temp_ids[i].length()>0)
				{
				lvContent.setId(Integer.parseInt(temp_ids[i].trim()));
				dto.put("model", lvContent);
				//删除model
				this.doService("LvContentService", "del", dto);
				}
			}
		}
		json.setStatusCode(200);
		json.setCallbackType(null);
		if (logger.isInfoEnabled()){
			logger.info("***** LvContentMngAction.delList() method end*****");
		}
		return AJAX;
	}
    
	/**
	 * 根据模板生成对应的页面
	 * @return
	 * @throws IOException 
	 * @throws TemplateException 
	 */
	public String doBuild()throws ActionException, IOException, TemplateException{
		if (logger.isInfoEnabled()){
			logger.info("***** LvContentMngAction.doBuild method begin*****");
		}
		dto.put("ids", ids);
		dto.put("rootPath", this.getText("lshop.web.path"));
		this.doService("LvContentService", "doBuild", dto);
		json.setStatusCode(200);
	    json.setCallbackType(null);
		if (logger.isInfoEnabled()){
				logger.info("***** LvContentMngAction.doBuild method end*****");
		}
		return AJAX;
	}
	
	public String toTplDetail(){
		PrintWriter out = null;
		dto.put("storeFlag", lvContent.getStoreFlag());
		if(lvContent!=null&&ObjectUtils.isNotEmpty(lvContent.getStoreFlag())){
			List<LvTplDetail> list=(List<LvTplDetail>) this.doService("LvContentService", "findDefulatTplDetail", dto);
			List listTmp=new ArrayList();
			Map<String,Object>  mapTmp=new HashMap<String,Object>();
			if(list!=null&&list.size()>0){
				for (int i = 0; i < list.size(); i++) {
					LvTplDetail lvTplDetail=list.get(i);
					Map<String,Object>  map=new HashMap<String,Object>();
					map.put("pagePath", lvTplDetail.getPagePath());
					map.put("name", lvTplDetail.getName());
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
		}
		
		return null;
	}
	
	public List<LvTplDetail> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<LvTplDetail> detailList) {
		this.detailList = detailList;
	}

}
