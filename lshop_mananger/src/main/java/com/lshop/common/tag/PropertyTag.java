package com.lshop.common.tag;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.components.Property;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;

public class PropertyTag extends ComponentTagSupport {

		private String defaultValue;
	    private String value;
	    private boolean escape = true;
	    private String lang;

	  
		public Component getBean(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
			return new Property(stack);
	    }

	    protected void populateParams() {
	        super.populateParams();

//	        Property tag = (Property) component;
//	        
//	        tag.setDefault(defaultValue);
//	        if (lang==null) {
//	        	  if (LangUtil.WEB_LANG==null) {
//	        		  LangUtil.WEB_LANG=Locale.getDefault();
//	        	  }
//	        	  if (value.indexOf('.')==-1) {
//	        		  if (LangUtil.WEB_LANG==Locale.CHINA) {
//		        		  tag.setValue(value+"cn");
//		        	  }else if (LangUtil.WEB_LANG==Locale.US) {
//		        		  tag.setValue(value+"en");
//		        	  }else if (LangUtil.WEB_LANG==Locale.TAIWAN) {
//		        		  tag.setValue(value+"tr");
//		        	  }
//				}else {
//					  if (LangUtil.WEB_LANG==Locale.CHINA) {
//		        		  tag.setValue(value.replaceFirst("\\.", "cn."));
//		        	  }else if (LangUtil.WEB_LANG==Locale.US) {
//		        		  tag.setValue(value.replaceFirst("\\.", "en."));
//		        	  }else if (LangUtil.WEB_LANG==Locale.TAIWAN) {
//		        		  tag.setValue(value.replaceFirst("\\.", "tr."));
//		        	  }
//				}
//	        	
//	        	
//			}else {
//				  tag.setValue(value+lang);
//			}
//	      
//	        tag.setEscape(escape);
	    }

	    public void setDefault(String defaultValue) {
	        this.defaultValue = defaultValue;
	    }

	    public void setEscape(boolean escape) {
	        this.escape = escape;
	    }

	    public void setValue(String value) {
	        this.value = value;
	    }
	    public String getLang() {
			return lang;
		}

		public void setLang(String lang) {
			this.lang = lang;
		}


}
