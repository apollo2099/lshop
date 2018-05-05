<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		 <title>banana TV——家文化倡导者官方博客 </title>
         <meta name="keywords" content="bananaTV官方博客； banana TV 幼教版；香蕉盒子；bananaTV blog；  家文化倡导者" />
         <meta name="description" content="bananaTV最新新闻动态，掌握行业资讯，提供产品最新活动，精彩的中文电视节目，热门话题，用户可随时轻松享受。"/>
		<link href="${resDomain}/bmcn/res/css/blog.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<%@include file="/web/bmcn/common/top.jsp" %>
		<script type="text/javascript" src="${resDomain}/bmcn/res/js/blog.js"></script>
	</head>
	
	<body>
		<!-- 获取商城头部文件 -->
		<% request.setAttribute("navFlag","bmcn_blog"); %>
		<%@include file="/web/bmcn/common/header.jsp" %>
		
		<input type="hidden" id="tempHref" value="http://fusion.google.com/add?feedurl=<s:text name="lshop.lshopcn.url"/>blog/rss/rss.xml"/>
		<!-- 存储用户登录信息 -->
		<input type="hidden" id="userId" />
		<input type="hidden" id="userName" />
		
		<div class="blog_banner">
	    	<ul>
			    <li><input  type="button" class="user_center_bt" id="rssId" onclick="rss()" value="RSS 订阅" /></li>
	    	</ul>
	   	</div>
		
			<!--内容部分-->
		<div class="blog">
			<div class="blog_left_z">
				<div class="blog_left">
					<s:iterator value="#request.page.list" status="stat" id="item">
					<ul>
						<li class="title">
						    <s:if test="%{null!=#item.title&&#item.title.length()>30}">
			                        <a href="/blog/blogInfo${item.id }.html"><s:property value="%{#item.title.substring(0, 30)}" />……</a>
			                 </s:if>
			                 <s:else>
			                 	<a href="/blog/blogInfo${item.id }.html"><s:property value="#item.title"/></a>
			                 </s:else>
			            </li>
						<li>
							<p><font class="kbqc"><s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"/></font></p>  
							<p><font class="kbqc">出处：</font><a href="#">banana TV</a></p> 
							<p><font class="kbqc">作者：</font><a href="#">${item.userName }</a></p>
						</li>
						<li class="text">
							<s:if test="%{null!=#item.intor&&#item.intor.length()>100}">
			                    <s:property value="%{#item.intor.substring(0, 100)}" />……
			                </s:if>
			                <s:else><s:property value="#item.intor"/></s:else>
			            </li>
						<li class="more"><font class="redfont"><a href="/blog/blogInfo${item.id }.html">阅读全文>></a></font></li>
						<li>
							<p>分类：<s:iterator value="#request.typeList" id="t">
								    <s:if test="#t.id==#item.type">${t.type}<s:set name="flag"
										value="false" />
								    </s:if>
								</s:iterator></p>  
							<p>回复：<s:if test="#item.replyNum!=null">${item.replyNum }</s:if>
			      				 <s:else>0</s:else></p>  
			      			<p>浏览：<s:if test="#item.clickNum!=null">${item.clickNum }</s:if>
				  				 <s:else>0</s:else></p> 
				  			<p>标签：
				  			<c:foreach items="${item.tagList }" var="tag">
				  			   <a href="/web/blog/blogManage!list.action?lvBlogContent.keyword=${tag }&lvBlogContent.type=">${tag }</a>
				  			</c:foreach>
				  			
				  			</p>
				  		</li>
					</ul>
					</s:iterator>
				</div>
			 
			  <div class="blog_left_1">
				<c:if test="${page.totalPage>1}">
					  <u:newPage href="/web/blog/blogManage!list.action?page.pageNum=@" language="cn"></u:newPage>
					  <script type="text/javascript">
						function toPage(){
							var pageNum = $("#pageValue").val();
						   	window.location.href="/web/blog/blogManage!list.action?page.pageNum="+pageNum;
						
						}
					 </script>
				 </c:if>
			  </div>
			 
		  </div>
		
			
            <%@include file="/web/bmcn/blog/blog_right.jsp" %>
			<div class="cb"></div>
		</div>		
	
		<!-- footer -->
		<%@include file="/web/bmcn/common/footer.jsp" %>
	
	</body>
</html> 