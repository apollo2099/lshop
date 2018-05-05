/**
 * 
 */
package com.gv.base.dictionary.web.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.base.resources.web.action.BaseResourcesAction;
import com.gv.base.springSecurity.common.pojo.BaseDictionarys;
import com.gv.core.datastructure.Dto;
import com.gv.core.datastructure.page.Pagination;
import com.gv.core.exception.ActionException;
import com.gv.core.web.action.BaseAction;

/**
 * @author panjin
 *
 */
@SuppressWarnings("serial")
@Controller("BaseDictionaryAction")
@Scope("prototype")
public class BaseDictionaryAction extends BaseAction {
	private static final Log logger	= LogFactory.getLog(BaseResourcesAction.class);
	
	private BaseDictionarys dictionarys ;
	
	public BaseDictionarys getDictionarys() {
		return dictionarys;
	}

	public void setDictionarys(BaseDictionarys dictionarys) {
		this.dictionarys = dictionarys;
	}
    
	/**
	 * 获取数据字典树结构对象集合
	 * @return
	 * @throws ActionException
	 */
	public String getDictionary() throws ActionException {
		if (logger.isInfoEnabled()){
			logger.info("***** BaseDictionaryAction.menuResources() method begin*****");
		}
		
		Dto dictionaryMap = (Dto)super.doService("BaseDictionaryService", "getDictionary", dto);
		
		request.setAttribute("rootNode", dictionaryMap.get("rootNode"));
		request.setAttribute("levelOneNode", dictionaryMap.get("levelOneNode"));
		if (logger.isInfoEnabled()){
			logger.info("***** BaseDictionaryAction.menuResources() method end*****");
		}
		return "dictionaryTree";
	}
	
	public String list() throws ActionException {
		if (logger.isInfoEnabled()){
			logger.info("***** BaseDictionaryAction.list() method begin*****");
		}

		dto.put("page", page);
		dto.put("orderField", orderField);
		dto.put("orderDirection", orderDirection);
		dto.put("parentCode", request.getParameter("dictionaryCode"));
		dto.put("keyword", keyword);
		
		page = (Pagination)this.doService("BaseDictionaryService", "getList", dto);
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseDictionaryAction.list() method end*****");
		}
		return LIST;
	}
	
	public String bfAdd() throws ActionException {
		if (logger.isInfoEnabled()){
			logger.info("***** BaseDictionaryAction.bfAdd() method begin*****");
		}
		if (logger.isInfoEnabled()){
			logger.info("***** BaseDictionaryAction.bfAdd() method end*****");
		}	
		return BFADD;
	}
	
	public String add() throws ActionException {
		if (logger.isInfoEnabled()){
			logger.info("***** BaseDictionaryAction.add() method begin*****");
		}		
		dto.put("baseDictionarys", dictionarys);
		dto = (Dto) super.doService("BaseDictionaryService", "save", dto);

		//层次为3，局部刷新
		if (dictionarys.getLevelNo() == 3) {
			json.setRel(json.getNavTabId());
			json.setNavTabId("");
		}
		if (logger.isInfoEnabled()){
			logger.info("***** BaseDictionaryAction.add() method end*****");
		}	
		return AJAX;
	}
	
	public String bfEdit() throws ActionException {
		if (logger.isInfoEnabled()){
			logger.info("***** BaseDictionaryAction.bfEdit() method begin*****");
		}		
		
		dto.put("dictionaryCode", request.getParameter("dictionaryCode"));
		dto.put("parentCode", request.getParameter("parentCode"));
		dictionarys = (BaseDictionarys) super.doService("BaseDictionaryService", "get", dto);
		
		if (logger.isInfoEnabled()){
			logger.info("***** BaseDictionaryAction.bfEdit() method end*****");
		}	
		return BFEDIT;
	}
	
	public String edit() throws ActionException {
		if (logger.isInfoEnabled()){
			logger.info("***** BaseDictionaryAction.edit() method begin*****");
		}		
		
		dto.put("baseDictionarys", dictionarys);
		super.doService("BaseDictionaryService", "update", dto);
		
		//层次为3，局部刷新
		if (dictionarys.getLevelNo() == 3) {
			json.setRel(json.getNavTabId());
			json.setNavTabId("");
		}
		if (logger.isInfoEnabled()){
			logger.info("***** BaseDictionaryAction.edit() method end*****");
		}	
		return AJAX;
	}
	
	public String delete() throws ActionException {
		if (logger.isInfoEnabled()){
			logger.info("***** BaseDictionaryAction.delete() method begin*****");
		}
		
		dto.put("ids",ids);
		super.doService("BaseDictionaryService", "delete", dto);
		
		json.doNavTabTodo();
		if (logger.isInfoEnabled()){
			logger.info("***** BaseDictionaryAction.delete() method end*****");
		}	
		return AJAX;
	}
	
	/**
	 * 删除该节点及该节点下的叶子节点
	 * @return
	 * @throws ActionException
	 */
	public String deleteNode() throws ActionException {
		if (logger.isInfoEnabled()){
			logger.info("***** BaseDictionaryAction.deleteNode() method begin*****");
		}
		
		dto.put("id",request.getParameter("id"));
		dto.put("parentCode", request.getParameter("dictionaryCode"));
		super.doService("BaseDictionaryService", "deleteNode", dto);
		
		json.doNavTabTodo();
		if (logger.isInfoEnabled()){
			logger.info("***** BaseDictionaryAction.deleteNode() method end*****");
		}	
		return AJAX;
	}
}
