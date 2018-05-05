package com.lshop.manage.weixin.pojo;

/**
 * 复杂按钮 也就是父按钮
 * @author liudm
 *
 */
	public class ComplexButton extends Button {  
	    private Button[] sub_button;  
	  
	    public Button[] getSub_button() {  
	        return sub_button;  
	    }  
	  
	    public void setSub_button(Button[] sub_button) {  
	        this.sub_button = sub_button;  
	    }  
	}  

