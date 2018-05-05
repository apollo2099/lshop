package com.gv.base.menu.common.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.taglibs.authz.AuthorizeTag;

import com.gv.core.datastructure.Key;
import com.gv.core.datastructure.impl.BasePo;
import com.gv.core.util.ObjectUtils;
/**
 * 
 * 项目名称：base_demo   
 * 类名称：MenuNode   
 * 类描述：   
 * 创建人：  yangzheng
 * 创建时间：2012-3-1 下午03:09:50   
 * 修改人：yangzheng   
 * 修改时间：2012-3-1 下午03:09:50   
 * 修改备注：   
 * @version
 */
public class MenuNode extends BasePo{


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
	private List<MenuNode> list = new ArrayList<MenuNode>();
	//url
	private String href;
	//打开方式
	private String target;
	
	/**
	 * 获得该节点的HTML,包括子节点
	 * @return
	 * @throws JspException 
	 */
	public String getNodeHTML(AuthorizeTag authorizeTag) throws JspException{
		StringBuffer htm = new StringBuffer();

		for (int i = 0; i < list.size(); i++) {
			MenuNode node = list.get(i);
		
			authorizeTag.setUrl(node.getValue());
			if ( authorizeTag.doStartTag()==1) {
				htm.append("<div class=\"accordionHeader\">");
				htm.append("<h2><span>Folder</span>"+node.getLabel()+"</h2>");
				htm.append("</div>");
				htm.append("<div class=\"accordionContent\" layoutH=\"38\">");
				htm.append("<ul class=\"tree treeFolder\">");
				htm.append(getChildHTML(node.getList(),2,authorizeTag));
				htm.append("</ul>");
				htm.append("</div>");
			}


		}
		return htm.toString();
	}
	
	private String getChildHTML(List<MenuNode> childList,Integer level,AuthorizeTag authorizeTag) throws JspException{
		StringBuffer htm = new StringBuffer();
		MenuNode node;
		if(ObjectUtils.isNotEmpty(childList))
		{
			//层次标记
			Integer levelmarker =level;
			
			for(int i =0; i < childList.size(); i ++){
				node = childList.get(i);
				String href=node.getHref();
				if(href!=null&&!"".equals(href.trim())){
					String rescode="";
					Integer num= href.indexOf("?");
        		    if(num>0){
        			    rescode =href.substring(0, num);
        		    }
			    	authorizeTag.setUrl(rescode+"?json.navTabId="+node.getValue());
			    }else{
			    	authorizeTag.setUrl(node.getValue());
			    }
				//authorizeTag.setUrl(node.getValue());
				if ( authorizeTag.doStartTag()==1) {
					List<MenuNode> _childList=node.getList();
					++levelmarker;
					if(ObjectUtils.isNotEmpty(node.getHref())){
						htm.append("<li><a href=\""+node.getHref()+node.getRel()+"\" target=\"navTab\" closeOthers=\"true\" rel=\""+node.getRel()+"\"  level=\""+level+"\" >"+node.getLabel()+"</a></li>");
					}else{
						htm.append("<li><a>"+node.getLabel()+"</a></li>");
					}
					if(ObjectUtils.isNotEmpty(_childList)){
						htm.append("<ul>").append(getChildHTML(_childList,levelmarker,authorizeTag)).append("</ul>");
					}
					levelmarker =level;
					htm.append("</li>");
				}
			}

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
	public List<MenuNode> getList() {
		return list;
	}
	public void setList(List<MenuNode> list) {
		this.list = list;
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
