/**
 * 数据字典标签类
 */
package com.gv.base.tag.dictionary;

import java.io.IOException;
import java.util.Map.Entry;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.gv.base.util.Constants;

/**
 * 数据字典标签类
 * @author panjin
 *
 */
@SuppressWarnings("serial")
public class DictionaryTag extends BodyTagSupport {
	
	private static final Log logger	= LogFactory.getLog(DictionaryTag.class);

	//类型（只能为"select" or "text"）
	private String type;
	
	//字典编码
	private String code;
	
	//字典名称
	private String value;
	
	//select 标签名称
	private String name;
	
	//select 标签onchange事件
	private String change;
	
	//select 是否可用属性
	private String disabled;
	
	//select 控件id编号
	private String id;
	//select 控件id编号
	private String style;
	
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the change
	 */
	public String getChange() {
		return change;
	}


	/**
	 * @param change the change to set
	 */
	public void setChange(String change) {
		this.change = change;
	}
	
	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	@Override
	public int doEndTag() throws JspException {
		StringBuffer html = new StringBuffer();
		try {
			if (StringUtils.equals(type, "select")) { //select标签
				doOptionTag(html);
				html.append("</select>");
			} else if (StringUtils.equals(type, "text")) {
				if (null != Constants.DICTIONARY_MAP.get(code)) {
					html.append(Constants.DICTIONARY_MAP.get(code).get(value));
				}
			}
			pageContext.getOut().write(html.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	@Override
	public int doStartTag() throws JspException {
		checkAttribute(new String[]{type, code});
		StringBuffer html = new StringBuffer();
		try {
			if (StringUtils.equals(type, "select")) {
				html.append("<select style=\"width: 200px\"");
				doSelectStartTag(html);
				html.append(">");
				pageContext.getOut().write(html.toString());
			} else if (StringUtils.equals(type, "text")) {
				checkAttribute(new String[]{value});
			} else {
				throw new JspTagException("gv:dictionary标签中type属性值必须为select或text");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}

	/**
	 * select标签开始节点属性赋值
	 * @param html
	 */
	private void doSelectStartTag (StringBuffer html) {
		if (StringUtils.isNotBlank(name)) {
			html.append(" name=\"").append(name).append("\"");
		}
		if (StringUtils.isNotBlank(change)) {
			html.append(" onchange=\"").append(change).append("\"");
		}
		if (StringUtils.isNotBlank(disabled)){
			html.append(" disabled=\"").append(disabled).append("\"");
		}
		if (StringUtils.isNotBlank(id)){
			html.append(" id=\"").append(id).append("\"");
		}
		if(StringUtils.isNotBlank(style)){
			html.append(" class=\"").append(style).append("\"");
		}
	}
	
	/**
	 * 组装select标签里的option标签
	 * @param html
	 */
	private void doOptionTag(StringBuffer html) {
		html.append("<option value=\"\">--请选择--</option>");
		if (null != Constants.DICTIONARY_MAP.get(code)) {
			for (Entry<String, String> map : Constants.DICTIONARY_MAP.get(code).entrySet()) {
				html.append("<option value=\"");
				html.append(map.getKey() + "\"");
				if (StringUtils.isNotBlank(value) && StringUtils.equals(map.getKey(), value)) {
					html.append(" selected=\"selected\"");
				}
				html.append(">");
				html.append(map.getValue());
				html.append("</option>");
			}
		}
	}
	
	/**
	 * 校验必填属性是否为空
	 * @param array
	 * @throws JspException
	 */
	private void checkAttribute(String[] array) throws JspException {
		if (ArrayUtils.isEmpty(array)) {
			if (logger.isWarnEnabled()) {
				logger.warn("***** DictionaryTag.checkAttribute() method, parameter is empty*****");
			}
			return;
		}
		for (String str : array) {
			if (StringUtils.isBlank(str)) {
				throw new JspTagException("gv:dictionary标签中必填字段属性为空");
			}
		}
	}
}
