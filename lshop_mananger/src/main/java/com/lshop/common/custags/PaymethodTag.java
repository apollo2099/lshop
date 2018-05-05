package com.lshop.common.custags;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts2.ServletActionContext;

import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.proxy.ServiceConstants;
import com.lshop.common.pojo.logic.LvPaymentStyle;
import com.lshop.common.util.Constants;
import com.opensymphony.xwork2.ActionContext;
/**
 * 获取商铺所配置的支付方式
 * @author tangd
 *
 */
public class PaymethodTag  extends TagSupport  {
    private String storeflag;
	@SuppressWarnings("unchecked")
	@Override
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
		    String serverName =  pageContext.getRequest().getServerName();;
		    String storeFlag = Constants.STORE_IDS.get(serverName.trim());//获取店铺标志
	        JspWriter out=pageContext.getOut();  
	    
	        HibernateBaseDAO readDao =(HibernateBaseDAO) ServiceConstants.beanFactory.getBean("lvlogicReadDao");
	        Map map=new HashMap();
	        //判断是否传递店铺标志，如果传递则用传递的，没有就用当前系统的
	        if(null!=storeflag && !("").equals(storeflag)){
	        	 map.put("storeFlag", storeflag);
	        }else{
	        	map.put("storeFlag", storeFlag);
	        }
	        
	        List <LvPaymentStyle> list=readDao.find("from LvPaymentStyle where  isActivity=1 and storeFlag=:storeFlag order by orderValue asc", map);
	        
	        HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
	        
	        try  
	        {  StringBuffer str=new StringBuffer();
	           if(list!=null){
	        	   int i=0;
	        	   for(LvPaymentStyle  payment:list){
	        		   i+=1;
	        		   str.append("<li>");
		       	       if(null!=request.getAttribute("payValue")  && payment.getPayValue()==Integer.parseInt(request.getAttribute("payValue").toString())){
		       	        	str.append("<input type='radio' name='payCode' value='"+payment.getPayValue()+"' checked/>");
		    	       }else if(i==1){
	        			   str.append("<input type='radio' name='payCode' value='"+payment.getPayValue()+"' checked/>");
	        		   }else{
	        			   str.append("<input type='radio' name='payCode' value='"+payment.getPayValue()+"' />");
	        		   }
	        		   str.append(payment.getPayName());
	        		   str.append("</li>");
	        	   }
	        	   out.write(str.toString()); //页面中显示的内容  
	           }
	        }catch(IOException e)  
	        {   
	            throw new JspException(e);  
	        }  
		return SKIP_BODY;//不包含主体
	}
	public String getStoreflag() {
		return storeflag;
	}
	public void setStoreflag(String storeflag) {
		this.storeflag = storeflag;
	}

}
