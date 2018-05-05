<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>TVpad Official Blog - TVpad Mall</title>
		<meta name="description" content="Find introductions to latest TV dramas, sports events, and official promotion here." />
		<meta name="keywords" content="TVpad official site, latest movies, plot summary, official promotion, pop music, hit American dramas, popular Japanese TV dramas, hot Korean TV shows, popular HK/Taiwan TV plays" />
		<link href="${resDomain}/en/res/css/blog.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<%@include file="/web/en/common/top.jsp" %>
		<script type="text/javascript" src="${resDomain}/en/res/js/blog.js"></script>
	</head>
	
	<body>
		<!-- 获取商城头部文件 -->
		<% request.setAttribute("navFlag","en_blog"); %>
		<%@include file="/web/en/common/header.jsp" %>
		
		<input type="hidden" id="tempHref" value="http://fusion.google.com/add?feedurl=<s:text name="lshop.lshopcn.url"/>blog/rss/rss.xml"/>
		<!-- 存储用户登录信息 -->
		<input type="hidden" id="userId" />
		<input type="hidden" id="userName" />
			
		<div class="blog">
			<div class="blog_banner">
		    	<ul>
		    		<li class="anniu05"><a href="#" class="rss" id="rssId" onclick="rss()">RSS Feeds</a></li>
		    	</ul>
		   	</div>
			
			<h1>
				<img src="${resDomain}/en/res/images/icon02.gif" /><font class="bfont">You are here:--><a href="/index.html"> Home</a>--></font><a href="/blog.html">Blogs</a>
				<c:if test="${not empty lvBlogContent.type}">
					<c:foreach items="${typeList}" var="t">
						<c:if test="${t.id==lvBlogContent.type}">--><a href="/blog/blogType${t.id }.html">${t.type}</a></c:if>
					</c:foreach>
				</c:if>
				<c:if test="${not empty lvBlogContent.keyword}">--><a href="/web/blog/blogManage!list.action?lvBlogContent.keyword=${lvBlogContent.keyword }">${lvBlogContent.keyword}</a></c:if>	
			</h1>
		
		<!--内容部分-->
			<div class="blog_left_z">
			 <div class="blog_left">
				<s:iterator value="#request.page.list" status="stat" id="item">
				<ul>
					<li class="blog_left_title">
					    <s:if test="%{null!=#item.title&&#item.title.length()>30}">
		                        <a href="/blog/blogInfo${item.id }.html"><s:property value="%{#item.title.substring(0, 30)}" />……</a>
		                 </s:if>
		                 <s:else>
		                 	<a href="/blog/blogInfo${item.id }.html"><s:property value="#item.title"/></a>
		                 </s:else>
		            </li>
					<li class="blog_left_title_1"><s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"/>  From:<a href="#">TVpad</a> By:<a href="#">${item.userName }</a></li>
					<li>
						<s:if test="%{null!=#item.intor&&#item.intor.length()>100}">
		                    <s:property value="%{#item.intor.substring(0, 100)}" />……
		                </s:if>
		                <s:else><s:property value="#item.intor"/></s:else>
		            </li>
					<li class="blog_left_more"><a href="/blog/blogInfo${item.id }.html">Read more>></a></a></li>
					<li>
						Category：<s:iterator value="#request.typeList" id="t">
							    <s:if test="#t.id==#item.type">${t.type}<s:set name="flag"
									value="false" />
							    </s:if>
							</s:iterator>  |  
						Reply：<s:if test="#item.replyNum!=null">${item.replyNum }</s:if>
		      				 <s:else>0</s:else>   |  
		      			Views：<s:if test="#item.clickNum!=null">${item.clickNum }</s:if>
			  				 <s:else>0</s:else>  | 
			  			Tags：<c:foreach items="${item.tagList }" var="tag">
				  			   <a href="/web/blog/blogManage!list.action?lvBlogContent.keyword=${tag }&lvBlogContent.type=">${tag }</a>
				  			</c:foreach></p> </li>
				</ul>
				</s:iterator>
			</div>
				 
				 <!--分页-->
			<div class="blog_left_1">
			<c:if test="${page.totalPage>1}">
				  <u:newPage href="/web/blog/blogManage!list.action?page.pageNum=@" language="en"></u:newPage>
				  <script type="text/javascript">
					function toPage(){
						var pageNum = $("#pageValue").val();
					   	window.location.href="/web/blog/blogManage!list.action?page.pageNum="+pageNum;
					
					}
				 </script>
			 </c:if>
				</div>
			</div>
			
			<!-- 博客右边菜单导航 --> 
			<%@include file="/web/en/blog/blog_right.jsp" %>
		</div>		
		
		<!-- footer -->
		<%@include file="/web/en/common/footer.jsp" %>
	
	</body>
</html> 