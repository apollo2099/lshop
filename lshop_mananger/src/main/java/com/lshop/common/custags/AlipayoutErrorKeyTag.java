package com.lshop.common.custags;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.proxy.ServiceConstants;
import com.lshop.common.pojo.logic.LvAlipayoutDictionary;
import com.lshop.common.pojo.logic.LvNavigation;
import com.lshop.common.pojo.logic.LvPaymentStyle;
import com.lshop.common.util.Constants;
import com.lshop.common.util.StoreHelp;

/**
 * 根据支付宝外卡错误代码key与店铺标志获取 错误代码消息
 * @author tangd
 *
 */
public class AlipayoutErrorKeyTag extends TagSupport  {
public String errorKey;

@Override
public int doStartTag() throws JspException {
	// TODO Auto-generated method stub
        JspWriter out=pageContext.getOut(); 
        HttpServletRequest request=(HttpServletRequest) pageContext.getRequest();
        String serverName =  request.getServerName();;
        String flag = Constants.STORE_IDS.get(serverName.trim());//获取标志
        if(flag==null||errorKey==null||"".equals(errorKey)){
    		return SKIP_BODY;//不包含主体
        }
	   try{
	    
		   HibernateBaseDAO readDao =(HibernateBaseDAO) ServiceConstants.beanFactory.getBean("lvlogicReadDao");
		   Map param=new HashMap();
		   param.put("errorKey", errorKey);
		   param.put("storeFlag", flag);
		   LvAlipayoutDictionary outDic= (LvAlipayoutDictionary)readDao.findUnique("from LvAlipayoutDictionary where errorKey=:errorKey and storeFlag=:storeFlag", param);
		   if(outDic!=null){
		   out.write(outDic.getErrorMsg());
		   }
        }catch(IOException e) {   
            throw new JspException(e);  
        }  
	return SKIP_BODY;//不包含主体
}
public String getErrorKey() {
	return errorKey;
}

public void setErrorKey(String errorKey) {
	this.errorKey = errorKey;
}


}
