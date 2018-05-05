package com.gv.base.menu.web.tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.springframework.security.taglibs.authz.AuthorizeTag;

import com.gv.base.menu.common.pojo.BaseMenu;
import com.gv.base.menu.common.pojo.MenuNode;
import com.gv.base.menu.service.BaseMenuService;
import com.gv.base.model.spring.ApplicationContextHolder;
import com.gv.core.util.BeanUtils;
import com.gv.core.util.ObjectUtils;
/**
 * 
 * 项目名称：base_demo   
 * 类名称：MenuTag   
 * 类描述：   
 * 创建人：  yangzheng
 * 创建时间：2012-3-1 下午03:09:06   
 * 修改人：yangzheng   
 * 修改时间：2012-3-1 下午03:09:06   
 * 修改备注：   
 * @version
 */
public class MenuTag extends AuthorizeTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3482316164503303258L;
	/**
	 * code标识
	 */
	private String code;
	/**
	 * 名字标识
	 */
	private String name;
	/**
	 * 值标识
	 */
	private String value;
	/**
	 * 显示标识
	 */
	private String labelFlag;
	/**
	 * 父编号标识
	 */ 
	private String parentflag;
	/**
	 * 是否为子节点标识
	 */
	private String leaf;
	/**
	 * 当前排序号
	 */
	private String levelno;
	/**
	 * 是否展开
	 */
	private String isopen;
	/**
	 * root节点
	 */
	private String rootnode;
	private String treeclass;
	private String rel;
	private String href;
	private String target;
	//<a>标签标识，打开页面，打开页面方式，打开页面大小
	
	@Override
	public int doEndTag() throws JspException {
		try {

			StringBuffer html = new StringBuffer();
			//1、不能多选的树，2、可以多选

			BaseMenuService menuService = (BaseMenuService) ApplicationContextHolder.getBean("BaseMenuService");
			List<BaseMenu> baseMenus = menuService.getAllMenu(null);
			
			List<MenuNode> list = createTreeNode(baseMenus, new MenuNode());
			html.append("<div class=\"toggleCollapse\"><h2>主菜单</h2><div>收缩</div></div>");
			html.append("<div class=\"accordion\" fillSpace=\"sidebar\">");
			for(MenuNode node : list){
				html.append(node.getNodeHTML((AuthorizeTag)this));
			}
			html.append("</div>");
			pageContext.getOut().print(html.toString());
		} catch (IOException e) {
		}
		return EVAL_PAGE;
	}
	
	/**
	 * 组装成树对象
	 * @param list
	 * @param node
	 * @return
	 */
	private List<MenuNode> createTreeNode(List list, MenuNode node){
		if(ObjectUtils.isEmpty(list)) return null;
		List<MenuNode> tree = new ArrayList<MenuNode>();
		try{
			for(int i = 0; i < list.size(); i ++){
				Object obj = list.get(i);
				String pid = ObjectUtils.isEmpty(BeanUtils.getFieldValue(obj, parentflag))?"":BeanUtils.getFieldValue(obj, parentflag).toString();
				if(pid.equals(rootnode)){//跟节点
					node = new MenuNode();
					if ( ObjectUtils.isNotEmpty(code) ){
						Object codeValue = BeanUtils.getFieldValue(obj, code);
						node.setId( ObjectUtils.isEmpty(codeValue)?"":codeValue.toString() );
					}
					if ( ObjectUtils.isNotEmpty(value) ){
						Object valueValue = BeanUtils.getFieldValue(obj, value);
						node.setValue( ObjectUtils.isEmpty(valueValue)?"":valueValue.toString() );
					}
					if ( ObjectUtils.isNotEmpty(labelFlag) ){
						Object labelValue = BeanUtils.getFieldValue(obj, labelFlag);
						node.setLabel( ObjectUtils.isEmpty(labelValue) ? "" : labelValue.toString());
					}
					if ( ObjectUtils.isNotEmpty(name) ){
						Object nameValue = BeanUtils.getFieldValue(obj, name);
						node.setName( ObjectUtils.isEmpty(nameValue) ? "" : nameValue.toString() );
					}
					if ( ObjectUtils.isNotEmpty(levelno) ){
						Object levelnoValue = BeanUtils.getFieldValue(obj, levelno);
						node.setLevelno( ObjectUtils.isEmpty(levelnoValue) ? 0 : Integer.parseInt(levelnoValue.toString()) );
					}
					if ( ObjectUtils.isNotEmpty(treeclass) ){
						node.setTreeclass(treeclass);
					}
					if ( ObjectUtils.isNotEmpty(rel) ){
						node.setRel(rel);
					}

					if ( ObjectUtils.isNotEmpty(target) ){
						node.setTarget(target);
					}
					node.getData().put("data", JSONObject.fromObject(obj).toString());
					createChildTreeNode(node, list);
					tree.add(node);
				}
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return tree;
	}
	
	/**
	 * 获得所有子节点
	 * @param node
	 * @param list
	 */
	private void createChildTreeNode(MenuNode node, List list){
		try{
			for(int i = 0; i < list.size(); i ++){
				Object obj = list.get(i);
				String pid = ObjectUtils.isEmpty(BeanUtils.getFieldValue(obj, parentflag))?"":BeanUtils.getFieldValue(obj, parentflag).toString();
				if(pid.equals(rootnode))continue;
				Object parentflagValue = BeanUtils.getFieldValue(obj, parentflag);//获得父编码
				if(ObjectUtils.isEmpty(parentflagValue)) continue;//为空直接跳出循环
				if(ObjectUtils.isEmpty(node)) continue;//为空直接跳出循环
				if(!node.getId().equals(parentflagValue.toString()))continue;//为空直接跳出循环
				MenuNode cNode = new MenuNode();
				if ( ObjectUtils.isNotEmpty(code) ){
					Object codeValue = BeanUtils.getFieldValue(obj, code);
					cNode.setId( ObjectUtils.isEmpty(codeValue)?"":codeValue.toString() );
				}
				if ( ObjectUtils.isNotEmpty(value) ){
					Object valueValue = BeanUtils.getFieldValue(obj, value);
					cNode.setValue( ObjectUtils.isEmpty(valueValue)?"":valueValue.toString() );
				}
				if ( ObjectUtils.isNotEmpty(labelFlag) ){
					Object labelValue = BeanUtils.getFieldValue(obj, labelFlag);
					cNode.setLabel( ObjectUtils.isEmpty(labelValue) ? "" : labelValue.toString());
				}
				if ( ObjectUtils.isNotEmpty(name) ){
					Object nameValue = BeanUtils.getFieldValue(obj, name);
					cNode.setName( ObjectUtils.isEmpty(nameValue) ? "" : nameValue.toString() );
				}
				if ( ObjectUtils.isNotEmpty(levelno) ){
					Object levelnoValue = BeanUtils.getFieldValue(obj, levelno);
					cNode.setLevelno( ObjectUtils.isEmpty(levelnoValue) ? 0 : Integer.parseInt(levelnoValue.toString()) );
				}
				if ( ObjectUtils.isNotEmpty(treeclass) ){
					cNode.setTreeclass(treeclass);
				}
				if ( ObjectUtils.isNotEmpty(rel) ){
					Object relValue = BeanUtils.getFieldValue(obj, rel);
					cNode.setRel( ObjectUtils.isEmpty(relValue) ? "" : relValue.toString());
				}
				if ( ObjectUtils.isNotEmpty(href) ){
					Object hrefValue = BeanUtils.getFieldValue(obj, href);
					cNode.setHref( ObjectUtils.isEmpty(hrefValue) ? "" : hrefValue.toString());
					
				}
				cNode.getData().put("data", JSONObject.fromObject(obj, getJsonConfig()).toString());
				createChildTreeNode(cNode, list);
				node.getList().add(cNode);
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public JsonConfig getJsonConfig(){
		JsonConfig config = new JsonConfig();
		config.setJsonPropertyFilter(new PropertyFilter() {
			public boolean apply(Object arg0, String arg1, Object arg2) {
				if(arg2 instanceof Number){
					return false;
				}
				if(arg2 instanceof String){
					return false;
				}
				return true;
			}
		});
		return config;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getLabelFlag() {
		return labelFlag;
	}
	public void setLabelFlag(String labelFlag) {
		this.labelFlag = labelFlag;
	}
	public String getParentflag() {
		return parentflag;
	}
	public void setParentflag(String parentflag) {
		this.parentflag = parentflag;
	}
	public String getLeaf() {
		return leaf;
	}
	public void setLeaf(String leaf) {
		this.leaf = leaf;
	}
	public String getLevelno() {
		return levelno;
	}
	public void setLevelno(String levelno) {
		this.levelno = levelno;
	}
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIsopen() {
		return isopen;
	}

	public void setIsopen(String isopen) {
		this.isopen = isopen;
	}

	public String getRootnode() {
		return rootnode;
	}

	public void setRootnode(String rootnode) {
		this.rootnode = rootnode;
	}

	public String getTreeclass() {
		return treeclass;
	}

	public void setTreeclass(String treeclass) {
		this.treeclass = treeclass;
	}

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	
	
}
