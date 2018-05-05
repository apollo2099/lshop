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
import com.lshop.common.pojo.logic.LvNavigation;
import com.lshop.common.util.Constants;
import com.lshop.common.util.StoreHelp;
/**
 *获取对应的商铺产品菜单
 * @author tangd
 *
 */
@SuppressWarnings("serial")
public class NavigationTag extends TagSupport  {
	
	private String style="2"; //导航展示样式:1-表示商城导航样式,2-表示店铺导航样式(默认) 3-表示banana商城导航样式  4-表示banana店铺样式

	private String storeKey; //区分是商城导航还是店铺导航，同时为了兼容之前的页面，在此需要进行判断。

	@SuppressWarnings("unchecked")
	@Override
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
	        JspWriter out=pageContext.getOut(); 
	        HttpServletRequest request=(HttpServletRequest) pageContext.getRequest();
	        String serverName =  request.getServerName();;
	        String flag = Constants.STORE_IDS.get(serverName.trim());//获取标志
	        if(flag==null){
	    		return SKIP_BODY;//不包含主体
	        }
		    if("1".equals(style) || "3".equals(style)){//获取商城标识
		    	   if(storeKey==null){
			    		String storeFlag=StoreHelp.getParentFlagByFlag(flag);
			    		storeFlag=storeFlag==null?flag:storeFlag;
			    		storeKey=storeFlag;
				    }
		    	
		    }else{
		    	   if(storeKey==null){
				    	storeKey=flag;
				    }
		    	
		    }
		    HibernateBaseDAO readDao =(HibernateBaseDAO) ServiceConstants.beanFactory.getBean("lvlogicReadDao");
		    Map map =new HashMap();
		    map.put("storeId", storeKey);
		   
	        List<LvNavigation> list=readDao.find("from LvNavigation where navParentId=0 and storeId=:storeId order by orderValue desc", map);
	        String navFlag=(String)pageContext.getRequest().getAttribute("navFlag");//获取着色标志
	        if(navFlag==null||"".equals(navFlag.trim())){
	        	navFlag=request.getRequestURL().toString();//获取请求url
	        }
	        
	        try  
	        {  
	         StringBuilder str=new StringBuilder();
	         //如果是商城，则换另一种样式用来显示商城的导航
	         if("1".equals(style)){
		          if(list!=null&&list.size()>0){
		        	  for(LvNavigation nav:list){
			        	  str.append("<a ")
			        	  .append("href='").append(nav.getNavUrl()).append("'");
		                  if(nav.getOpenTarget()==1){//是否打开新窗口 
			            	str.append(" target='_blank'");
			              }
		                  if(nav.getNavFlag()!=null&&!"".equals(nav.getNavFlag().trim())&&navFlag.matches(nav.getNavFlag().trim())){//标志与菜单匹配
		                	  str.append(" class='dangqian' ");
		                  }
			        	  str.append(">").append(nav.getNavName()).append("</a>");
		        	  }
		          }
		     //如果是其他店铺，则统一用一种样式
	         }
	         if("2".equals(style)){
		          str.append("<div  class='menu'>");
		          if(list!=null&&list.size()>0){
		        	  for(LvNavigation nav:list){
			        	  str.append("<a ").append("href='").append(nav.getNavUrl()).append("'");
		                  if(nav.getOpenTarget()==1){//是否打开新窗口 
			            	str.append(" target='_blank'");
			              }
		                  if(nav.getNavFlag()!=null&&!"".equals(nav.getNavFlag().trim())&&navFlag.matches(nav.getNavFlag().trim())){//标志与菜单匹配
		                	  str.append(" class='choose' ");
		                  }
			        	  str.append(">").append(nav.getNavName()).append("</a>");
		        	  }
		          }
		          str.append("</div>");
	         }
	         if("3".equals(style)){
		          if(list!=null&&list.size()>0){
		        	  for(LvNavigation nav:list){
		        	  	  str.append("<li");
		        	  	  if(nav.getNavFlag()!=null&&!"".equals(nav.getNavFlag().trim())&&navFlag.matches(nav.getNavFlag().trim())){//标志与菜单匹配
		                	  str.append(" class='dq' ");
		                  }
		        	  	  str.append(">");
			        	  str.append("<a ").append("href='").append(nav.getNavUrl()).append("'");
		                  if(nav.getOpenTarget()==1){//是否打开新窗口 
			            	str.append(" target='_blank'");
			              }
			        	  str.append(">").append(nav.getNavName()).append("</a>");
		        	  }
		          }
	         }

	          out.write(str.toString());
	        }catch(IOException e) {   
	            throw new JspException(e);  
	        }  
		return SKIP_BODY;//不包含主体
	}
	public void setStyle(String style) {
		this.style = style;
	}


	public void setStoreKey(String storeKey) {
		this.storeKey = storeKey;
	}
}
