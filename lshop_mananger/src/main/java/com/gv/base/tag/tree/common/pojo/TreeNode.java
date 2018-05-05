package com.gv.base.tag.tree.common.pojo;

import java.util.ArrayList;
import java.util.List;

import com.gv.core.datastructure.Key;
import com.gv.core.datastructure.impl.BasePo;
import com.gv.core.util.ObjectUtils;
import com.gv.core.util.StringUtil;

public class TreeNode extends BasePo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3947853837249010671L;
	//显示的内容
	private String label;
	//值
	private String value;
	//名称
	private String name;
	//编码
	private String id;
	//排序号
	private int levelno;
	//class属性
	private String treeclass;
	//rel属性
	private String rel;
	//子节点
	private List<TreeNode> list = new ArrayList<TreeNode>();
	//扩展方法
	private String attr1;
	private String attr2;
	private String href;
	private String target;
	
	/**
	 * 获得该节点的HTML,包括子节点
	 * @return
	 */
	public String getNodeHTML(){
		StringBuffer htm = new StringBuffer();
		htm.append("<li><a onclick=\"treenodeclick(this)\" rel=\""+rel+"\" " );
		if (!StringUtil.IsNullOrEmpty(href)) {
			htm.append("href=\""+href+"?code="+value+"&json.navTabId="+rel+"\" ");
		}
		htm.append(" target=\""+target+"\" level=\"1\" id=\""+id+"\" class=\""+treeclass+"\" tname=\""+name+"\" tvalue=\""+value+"\" data='"+data.get("data")+"'>"+label+"</a>");
		htm.append(getChildHTML(list,2));
		htm.append("</li>");
		return htm.toString();
	}
	
	private String getChildHTML(List<TreeNode> childList,Integer level){
		StringBuffer htm = new StringBuffer();
		TreeNode node;
		if(ObjectUtils.isNotEmpty(childList))
		{
			//层次标记
			Integer levelmarker =level;
			htm.append("<ul>");
			for(int i =0; i < childList.size(); i ++){
				node = childList.get(i);
				htm.append("<li><a onclick=\"treenodeclick(this)\" rel=\""+rel+"\" ");
				if (!StringUtil.IsNullOrEmpty(href)) {
					htm.append("href=\""+href+"?code="+node.getValue()+"&json.navTabId="+rel+"\"" );
				}
				htm.append(" target=\""+target+"\"   level=\""+level+"\"  id=\""+node.getId()+"\" class=\""+treeclass+"\" tname=\""+node.getName()+"\" tvalue=\""+node.getValue()+"\" data='"+node.getData().get("data")+"'>"+node.getLabel()+"</a>");
				htm.append(getChildHTML(node.getList(),++levelmarker));
				levelmarker =level;
				htm.append("</li>");
			}
			htm.append("</ul>");
		}
		return htm.toString();
	}
	@Override
	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<TreeNode> getList() {
		return list;
	}
	public void setList(List<TreeNode> list) {
		this.list = list;
	}
	public String getAttr1() {
		return attr1;
	}
	public void setAttr1(String attr1) {
		this.attr1 = attr1;
	}
	public String getAttr2() {
		return attr2;
	}
	public void setAttr2(String attr2) {
		this.attr2 = attr2;
	}
	public int getLevelno() {
		return levelno;
	}
	public void setLevelno(int levelno) {
		this.levelno = levelno;
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
