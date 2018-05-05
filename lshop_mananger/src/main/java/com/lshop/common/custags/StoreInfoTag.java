package com.lshop.common.custags;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.proxy.ServiceConstants;
import com.lshop.common.pojo.logic.LvStore;
import com.lshop.common.util.Constants;
import com.lshop.common.util.StoreHelp;

@SuppressWarnings("serial")
public class StoreInfoTag extends TagSupport{
	   private String param;
	   
	   private String shopFlag;
	   
		@SuppressWarnings("unchecked")
		@Override
		public int doStartTag() throws JspException {
			// TODO Auto-generated method stub
	        HttpServletRequest request=(HttpServletRequest) pageContext.getRequest();
	        String serverName =  request.getServerName();;
	        String flag = Constants.STORE_IDS.get(serverName.trim());//获取标志
		    
	        String storeFlag = "";
	        //获取店铺的信息
	        if(null!=shopFlag && !("").equals(shopFlag)){
	        	storeFlag = shopFlag;
	        //获取商城的信息
	        }else{
			    //获取当前商城标识
				storeFlag=StoreHelp.getParentFlagByFlag(flag);
				storeFlag=storeFlag==null?flag:storeFlag;
	        }

		    
	        JspWriter out=pageContext.getOut();  
	    
	        HibernateBaseDAO readDao =(HibernateBaseDAO) ServiceConstants.beanFactory.getBean("dao");
	        Map map=new HashMap();
	        map.put("storeFlag", storeFlag);
		       
	        LvStore lvStore=(LvStore)readDao.findUnique("from LvStore where  isdel=0 and storeFlag=:storeFlag", map);
	        try  
	        {
	        	if(null!=lvStore && null!=param && !("").equals(param)){
	        		if("statcode".equals(param)){
			        	out.print(lvStore.getStatCode());
		            }else if("footerinfo".equals(param)){
		            	out.print(lvStore.getFooterInfo());	
		            }else if("storeLogo".equals(param)){
		            	out.print(lvStore.getLogo());
		            }else if("storeName".equals(param)){
		            	out.print(lvStore.getName());
		            }
	        	}
	        	
	        }catch(IOException e)  
	        {   
	            throw new JspException(e);  
	        }  
			return SKIP_BODY;//不包含主体
		}
		public String getParam() {
			return param;
		}
		public void setParam(String param) {
			this.param = param;
		}
		public String getShopFlag() {
			return shopFlag;
		}
		public void setShopFlag(String shopFlag) {
			this.shopFlag = shopFlag;
		}
		

}
