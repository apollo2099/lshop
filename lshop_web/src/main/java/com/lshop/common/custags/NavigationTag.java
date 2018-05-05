package com.lshop.common.custags;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.gv.core.hibernate3.HibernateBaseDAO;
import com.gv.core.proxy.ServiceConstants;
import com.gv.core.util.ObjectUtils;
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
	
	private String style="2"; //导航展示样式:1-表示商城导航样式,2-表示店铺导航样式(默认) 3-表示banana商城导航样式  4-表示banana店铺样式,5-表示Banana移动商城样式

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
		    if("1".equals(style) || "3".equals(style)|| "5".equals(style)){//获取商城标识
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
	        List<LvNavigation> list=getNaviList();
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
		        	  if ("en".equalsIgnoreCase(storeKey)) {//区分中英文生成html
			        	  for(LvNavigation nav:list){
			        		  transfer(str, nav, navFlag);
			        	  }
					} else {
						
						str.append("<ul>");
			        	  for(int i = 0; i<list.size(); i++){
			        		  LvNavigation nav = list.get(i);
			        		  if (0 == i) {
			        			  str.append("<li id='in_dex'");
								} else {
									str.append("<li");
								}
			        		  if (ObjectUtils.isNotEmpty(nav.getChildren())) {
			        			  str.append(" class='more-menu'");
			        		  }
			        		  str.append(">");
			        		  transfer(str, nav, navFlag);
				        	  //子结点
				        	  if (ObjectUtils.isNotEmpty(nav.getChildren())) {
									str.append("<ul style='display:none;'><li class='none'><img src='http://res.mtvpad.com/www/res/images/submenu_jt.gif' width='30' height='20' /></li>");
									for (LvNavigation cnav : nav.getChildren()) {
										str.append("<li>");
										transfer(str, cnav, navFlag);
										str.append("</li>");
									}
									str.append("</ul>");
								}
				        	  str.append("</li>");
			        	  }
			        	  str.append("<div class='clear'></div></ul>");
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

			if ("5".equals(style)) {
				
		         /*
		          * 移动端导航模板DEMO
		         <div class="navicon">   
		            <ul>
		               <li id="helpicon">
		                  <a href="help/help.html"><img src="images/tvpad4.png" width="58%"></a>
		                  <a href="#">TVpad 4</a>
		               </li>
		               <li id="forum">
		                  <a href="#"><img src="images/usricon.png" width="58%"></a>
		                   <a href="#">我的账户</a>
		               </li>
		               <li id="businessicon">
		                 <a href="#"><img src="images/nav_help.png" width="58%"></a>
		                 <a href="#">帮助中心</a>
		               </li>
		               <li id="computicon">
		                  <a href="#"><img src="images/nav_lt.png" width="58%"></a> 
		                  <a href="#">论坛</a></li>
		               <div class="clear"></div>
		            </ul>
		         </div>
		          <div class="clear"></div>
		          */
				
				str.append("<div class='navicon'>");
				if (list != null && list.size() > 0) {
					str.append(" <ul>");
					for (int i = 0; i < list.size(); i++) {
						LvNavigation nav = list.get(i);
						if (i == 3 ||(i<3&&i==list.size()-1)) {
							str.append("<li>");
							str.append("<a ").append("href='").append(nav.getNavUrl()).append("'");
							if (nav.getOpenTarget() == 1) {// 是否打开新窗口
								str.append(" target='_blank'");
							}
							str.append(">").append("<img src='"+nav.getNavImage()+"' width='90%'></a>");
							str.append("<a ").append("href='").append(nav.getNavUrl()).append("'");
							if (nav.getOpenTarget() == 1) {// 是否打开新窗口
								str.append(" target='_blank'");
							}
							str.append(">");
							str.append(""+nav.getNavName()+"</a>");
							str.append("</li><div class='clear'></div></ul><ul>");
						} else if(i == 7||(i<7&&i==list.size()-1)){
							str.append("<li>");
							str.append("<a ").append("href='").append(nav.getNavUrl()).append("'");
							if (nav.getOpenTarget() == 1) {// 是否打开新窗口
								str.append(" target='_blank'");
							}
							str.append(">").append("<img src='"+nav.getNavImage()+"' width='90%'></a></li>");
							str.append("<a ").append("href='").append(nav.getNavUrl()).append("'");
							if (nav.getOpenTarget() == 1) {// 是否打开新窗口
								str.append(" target='_blank'");
							}
							str.append(">");
							str.append(""+nav.getNavName()+"</a>");
							str.append("<div class='clear'></div></ul>");
						} else {
							str.append("<li>");
							str.append("<a ").append("href='").append(nav.getNavUrl()).append("'");
							if (nav.getOpenTarget() == 1) {// 是否打开新窗口
								str.append(" target='_blank'");
							}
							str.append(">").append("<img src='"+nav.getNavImage()+"' width='90%'></a>");
							str.append("<a ").append("href='").append(nav.getNavUrl()).append("'");
							if (nav.getOpenTarget() == 1) {// 是否打开新窗口
								str.append(" target='_blank'");
							}
							str.append(">");
							str.append(""+nav.getNavName()+"</a>");
							str.append("</li>");
						}
					}
				}
				str.append("</div>");
			}
	            out.write(str.toString());
	        }catch(IOException e) {   
	            throw new JspException(e);  
	        }  
		return SKIP_BODY;//不包含主体
	}
	
	/**
	 * 返回指定标志的导航菜单,并带有子菜单
	 * @param storeId
	 * @return
	 */
	private List<LvNavigation> getNaviList() {
		 HibernateBaseDAO readDao =(HibernateBaseDAO) ServiceConstants.beanFactory.getBean("lvlogicReadDao");
		    Map map =new HashMap();
		    map.put("storeId", storeKey);
	        List<LvNavigation> list=readDao.find("from LvNavigation where storeId=:storeId order by orderValue desc", map);
	        //找出顶级菜单
	        List<LvNavigation> par = new ArrayList<LvNavigation>();
	        for (int i = list.size()-1; i >= 0; i--) {
				if (0 == list.get(i).getNavParentId().intValue()) {
					par.add(list.remove(i));
				}
			}
	        Collections.reverse(par);
	        //把子结点放到父结点中
	        for (int i = 0; i < list.size(); i++) {
				for (int j = 0; j < par.size(); j++) {
					if (list.get(i).getNavParentId().equals(par.get(j).getId())) {
						LvNavigation tem = par.get(j);
						if (null == tem.getChildren()) {
							tem.setChildren(new ArrayList<LvNavigation>());
						}
						tem.getChildren().add(list.get(i));
					}
				}
			}
	        return par;
	}
	/**
	 * 生成导航链接
	 * @param str
	 * @param nav
	 * @param navFlag
	 */
	public void transfer(StringBuilder str, LvNavigation nav, String navFlag) {
		str.append("<a href='").append(nav.getNavUrl()).append("'");
        if(nav.getOpenTarget()==1){//是否打开新窗口 
      	str.append(" target='_blank'");
        }
        if(nav.getNavFlag()!=null&&!"".equals(nav.getNavFlag().trim())&&navFlag.matches(nav.getNavFlag().trim())){//标志与菜单匹配
      	  str.append(" class='dangqian' ");
        }
  	  str.append(">").append(nav.getNavName());
  	  str.append("</a>");
	}
	public void setStyle(String style) {
		this.style = style;
	}

	public void setStoreKey(String storeKey) {
		this.storeKey = storeKey;
	}
}
