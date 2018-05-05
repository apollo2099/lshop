package com.lshop.common.custags;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.proxy.ServiceConstants;
import com.lshop.common.pojo.logic.LvPaymentStyle;
import com.lshop.common.util.Constants;
/**
 * 获取商铺所配置的支付方式
 * @author tangd
 *
 */
@SuppressWarnings("serial")
public class getPaymethodNameTag  extends TagSupport  {
    private String storeflag;
    private Short payValue;
    private Integer showLength;
    
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
	        map.put("payValue", payValue);
	        List <LvPaymentStyle> list=readDao.find("from LvPaymentStyle where  payValue=:payValue and storeFlag=:storeFlag and payType="+ Constants.PAYTYPE_LINEPAY, map);
	        
	        LvPaymentStyle lvPaymentStyle=null;
	        String payName="";
	        try  
	        {  
	           if(null!=list && list.size()>0){
	        	   lvPaymentStyle=list.get(0);
	        	   payName=lvPaymentStyle.getPayName();
	        	   int indexNum=payName.toString().indexOf("（"); //中文括号
	        	   if(indexNum>=0){
	        		   payName=payName.substring(0, indexNum);
	        	   }else{
	        		   int num=payName.toString().indexOf("("); //英文括号
	        		   if(num>=0){
	        			   payName=payName.substring(0,num);
	        		   }
	        	   }
	        	   //指定显示多长字符
	        	   if(null!=showLength && showLength>0 && payName.toString().length()>showLength){
	        		   payName=payName.subSequence(0, showLength).toString()+"...";
	        	   }
	        	   out.write(payName); //页面中显示的内容  
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
	public Short getPayValue() {
		return payValue;
	}
	public void setPayValue(Short payValue) {
		this.payValue = payValue;
	}
	public Integer getShowLength() {
		return showLength;
	}
	public void setShowLength(Integer showLength) {
		this.showLength = showLength;
	}

}
