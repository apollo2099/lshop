package com.lshop.common.pojo.logic;

import com.gv.core.datastructure.Key;
import com.gv.core.datastructure.impl.BasePo;

public class LvPromtContent  extends BasePo implements java.io.Serializable 
{
  private Integer id;
  private Integer model;
  private String formName;
  private String textContent;
  private String htmlContent;
  private Integer status;

  public LvPromtContent() {}

	public LvPromtContent(Integer model, String formName, String textContent,
		String htmlContent,Integer status) {
	super();
	this.model = model;
	this.formName = formName;
	this.textContent = textContent;
	this.htmlContent = htmlContent;
	this.status=status;
}

	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getModel() {
		return model;
	}

	public void setModel(Integer model) {
		this.model = model;
	}

	public String getFormName() {
		return formName;
	}

	public void setFormName(String formName) {
		this.formName = formName;
	}

	public String getTextContent() {
		return textContent;
	}

	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}

	public String getHtmlContent() {
		return htmlContent;
	}

	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}
	
	public Integer getStatus() {
		return status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public Key getPk() {
		// TODO Auto-generated method stub
		return null;
	}
}
