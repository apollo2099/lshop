package com.lshop.manage.llinkurl.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.gv.core.datastructure.page.Pagination;
import com.lshop.common.pojo.logic.LvLinkUrl;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.manage.common.action.BaseManagerAction;

/**
 * 动态资源
 * 
 * @author tangd
 * 
 */
@SuppressWarnings("serial")
@Controller("LinkUrlManageAction")
@Scope("prototype")
public class LinkUrlManageAction extends BaseManagerAction {
	public List<?> list;
	private String ids;
	private LvLinkUrl vo;


	/**
	 * 获取链接列表
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String list() {
		dto.setDefaultPo(vo);
		dto.setDefaultPage(page);
		page = (Pagination) doService("LinkUrlManageService", "list", dto);
		
		//查询所有店铺信息
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","getAll", dto);
		this.getRequest().setAttribute("storeList", storeList);
		return "list";
	}
   public String bufAdd(){
		//查询所有店铺信息
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","getAll", dto);
		this.getRequest().setAttribute("storeList", storeList);
	    return "bufAdd";
   }
	/**
	 * 添加资源
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String add() {
		this.setFlag();
//		vo.setStoreFlag(this.getFlag());
		dto.setDefaultPo(vo);
		Integer result = (Integer) doService("LinkUrlManageService","addres", dto);
		json.setStatusCode(200);
		json.setMessage("添加成功!");
		return AJAX;
	}

	/**
	 * 修改资源
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String update() {
		dto.setDefaultPo(vo);
		Integer result = (Integer) doService("LinkUrlManageService","updateres", dto);
		json.setStatusCode(result);
		json.setMessage("修改成功!");
		return AJAX;
	}

	/**
	 * 删除资源
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String del() {
		dto.put("ids", ids);
		Integer result = (Integer) doService("LinkUrlManageService","delres", dto);
		json.setStatusCode(result);
		json.setMessage("删除成功!");
		json.doNavTabTodo();
		return AJAX;
	}

	/**
	 * 查看资源
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String view() {
		dto.put("id", id);
		vo = (LvLinkUrl) doService("LinkUrlManageService", "viewres", dto);
		
		//查询所有店铺信息
		List<LvStore> storeList= (List<LvStore>) this.doService("LvStoreService","getAll", dto);
		this.getRequest().setAttribute("storeList", storeList);
		return "info";
	}

	public String htmlStatic(){
		dto.put("id", id);
		doService("LinkUrlManageService", "htmlStatic", dto);
		json.setMessage("静态化指令已经发送成功，请稍后查看静态化页面!");
		json.doNavTabTodo();
		return AJAX;
		
	}
	
	
	public String htmlStaticBatch(){
		dto.put("ids", ids);
		doService("LinkUrlManageService", "htmlStaticBatch", dto);
		json.setMessage("静态化指令已经发送成功，请稍后查看静态化页面!");
		json.doNavTabTodo();
		return AJAX;
	}
	
	public List<?> getList() {
		return list;
	}

	public String getIds() {
		return ids;
	}

	public LvLinkUrl getVo() {
		return vo;
	}


	public void setList(List<?> list) {
		this.list = list;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public void setVo(LvLinkUrl vo) {
		this.vo = vo;
	}

	

}
