<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>TVpad官方博客—TVpad官方商城</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="description" content="TVpad官方博客擁有最熱門的劇集介紹，精彩的體育賽事以及最新的官方活動訊息，讓您即時掌握最新動態。" />
		<meta name="keywords" content="TVpad官方博客，最新电影，劇情簡介，官方活動，熱門音樂介紹，熱門歐美、日韓、港台電視劇" />
		<link href="${resDomain}/www/res/css/blog.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<%@include file="/web/www/common/top.jsp" %>
		<script type="text/javascript" src="${resDomain}/www/res/js/blog.js"></script>
	</head>
	
	<body>
		<!-- 获取商城头部文件 -->
		<% request.setAttribute("navFlag","www_blog"); %>
		<%@include file="/web/www/common/header.jsp" %>
		
		<input type="hidden" id="tempHref" value="http://fusion.google.com/add?feedurl=<s:text name="lshop.lshopcn.url"/>blog/rss/rss.xml"/>
		<!-- 存储用户登录信息 -->
		<input type="hidden" id="userId" />
		<input type="hidden" id="userName" />
		
		<div class="blog">
			<div class="blog_banner">
		    	<ul>
		    		<li class="anniu05"><a href="#" class="rss" id="rssId" onclick="rss()">Rss訂閱</a></li>
		    	</ul>
		   	</div>
		<h1><img src="${resDomain}/www/res/images/icon02.gif" /><font class="bfont">當前位置--><a href="http://www.mtvpad.com/index.html"> 首頁</a>--><a href="http://www.mtvpad.com/blog.html"> 官方博客</a></font> </h1>
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
						<li class="blog_left_title_1"><s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"/>  出處：<a href="#">TVpad</a> 作者：<a href="#">${item.userName }</a></li>
						<li>
							<s:if test="%{null!=#item.intor&&#item.intor.length()>100}">
			                    <s:property value="%{#item.intor.substring(0, 100)}" />……
			                </s:if>
			                <s:else><s:property value="#item.intor"/></s:else>
			            </li>
						<li class="blog_left_more"><a href="/blog/blogInfo${item.id }.html">閱讀全文>></a></a></li>
						<li>
							分類：<s:iterator value="#request.typeList" id="t">
								    <s:if test="#t.id==#item.type">${t.type}<s:set name="flag"
										value="false" />
								    </s:if>
								</s:iterator>  |  
							回覆：<s:if test="#item.replyNum!=null">${item.replyNum }</s:if>
			      				 <s:else>0</s:else>   |  
			      			瀏覽：<s:if test="#item.clickNum!=null">${item.clickNum }</s:if>
				  				 <s:else>0</s:else>  | 
				  			標籤：<a href="#">${item.keyword }</a> </li>
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
		
			
            <!-- 博客右边菜单导航 --> 
			<%@include file="/web/www/blog/blog_right.jsp" %>
			
		</div>		
	
		<!-- footer -->
		<%@include file="/web/www/common/footer.jsp" %>
	
	</body>
</html> 