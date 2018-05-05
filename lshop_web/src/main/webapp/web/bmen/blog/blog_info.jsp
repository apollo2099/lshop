<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>

<%
    response.setHeader("Pragma","No-cache");
    response.setHeader("Cache-Control","no-cache");
    response.setDateHeader("Expires", 0);
    response.flushBuffer();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title><s:property value="%{#request.lvBlogContent.title.replaceAll(' ','_')}" /></title>
         <meta name="keywords" content="${lvBlogContent.keyword}"/>
         <meta name="description" content="<s:property value='%{#request.lvBlogContent.content.substring(0, 200)}'/>"/>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="${resDomain}/bmen/res/css/blog.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<%@include file="/web/bmen/common/top.jsp" %>
		<script type="text/javascript" src="${resDomain}/bmen/res/js/blog.js"></script>
		<script type="text/javascript">
			$().ready(function() {
				var users=lshop.getCookieToJSON('user');
				if(users.uid!=null){
					$("#noLogin").hide();
				}else{
					$("#noLogin").show();
				}
			});
		</script>
	</head>
	
	<body>
		<!-- 获取商城头部文件 -->
		<% request.setAttribute("navFlag","bmen_blog"); %>
		<%@include file="/web/bmen/common/header.jsp" %>
		
		<input type="hidden" id="tempHref" value="http://fusion.google.com/add?feedurl=<s:text name="lshop.lshopcn.url"/>blog/rss/rss.xml"/>
		<!-- 存储用户登录信息 -->
		<input type="hidden" id="userId" />
		<input type="hidden" id="userName" />
		
		
		<div class="blog_banner">
	    	<ul>
			    <li><input  type="button" class="user_center_bt" id="rssId" onclick="rss()" value="RSS Feeds" /></li>
	    	</ul>
	   	</div>
			
			<!--左边内容-->
		<div class="blog">
			<div class="blog_left_z">
				<div class="blog_left">
					<ul class="info">
						<li>
							<p><font class="kbqc"><s:date name="#request.lvBlogContent.createTime" format="yyyy-MM-dd HH:mm:ss"/></font></p>
							<p><font class="kbqc">Tags：</font><a href="#">${lvBlogContent.keyword}</a></p>
						</li>
						<li class="center"><font class="red">${lvBlogContent.title }</font></li>
						<li class="center"><font class="kbqc">From：banana TV</font> &nbsp; <font class="kbqc">By：${lvBlogContent.userName}</font></li>		
						<li class="text">
						  <p>${lvBlogContent.content }</p>
						</li>
							
						<li>
							<p>Previous Article：<font class="blue2"><a href="/blog/blogInfo${upContent.id}.html" class="biaoqian" title="<s:property value="#request.upContent.title"/>"><s:if test="%{null!=#request.upContent.title&&#request.upContent.title.length()>12}"><s:property value="%{#request.upContent.title.substring(0, 20)}" />……</s:if><s:else><s:property value="#request.upContent.title"/></s:else></a></font></p> 
							<p>Next Article：<font class="blue2"><a href="/blog/blogInfo${nextContent.id}.html" class="biaoqian"  title="<s:property value="#request.nextContent.title"/>"><s:if test="%{null!=#request.nextContent.title&&#request.nextContent.title.length()>12}"><s:property value="%{#request.nextContent.title.substring(0, 20)}" />……</s:if><s:else><s:property value="#request.nextContent.title"/></s:else></a></font></p>
						</li>
						<li>
							<p>Posted by：${lvBlogContent.userName}</p> 
							<p>Category：<s:iterator value="#request.typeList" id="t">
					 					<s:if test="#t.id==lvBlogContent.type">${t.type}</s:if>
									 </s:iterator></p>  
				 			<p>Reply：<s:if test="#request.lvBlogContent.replyNum !=null">${lvBlogContent.replyNum  }</s:if>
				     				 <s:else>0</s:else></p>  
				 			<p><s:if test="#request.lvBlogContent.clickNum !=null">${lvBlogContent.clickNum  }</s:if>
							         <s:else>0</s:else> Views</p> 
						</li>	
						<li>
							<p><img src="${resDomain}/bmen/res/images/share01.gif" /> <font class="redfont">Share on：</font></p>
							<p><img src="${resDomain}/bmen/res/images/share02.gif" /> <a href="javascript:void(0)"  onClick="copyClipboard('txt');">Copy URL</a></p>
							<p><img src="${resDomain}/bmen/res/images/share03.gif" /> <a class="face" rel="nofollow" href="javascript:window.open('http://www.facebook.com/sharer.php?u='+encodeURIComponent(document.location.href)+'&amp;t='+encodeURIComponent(document.title));void(0)">Facebook</a></p>
							<p><img src="${resDomain}/bmen/res/images/share04.gif" /><a href="#" class="jiathis_button_qzone">QQ-zone</a></p>
						</li>	
						<script type="text/javascript" src="http://v2.jiathis.com/code/jia.js" charset="utf-8"></script>	
				   	</ul>  
				</div>
				 
			  	<div class="blog_comments">
			  		<span class="blog_comments_ban">All Comments</span>
			  		<c:if test="${empty page.list}">
				  		<ul class="pl">
							No comment, welcome to make the first comment!
						</ul>
			  		</c:if>
			  		<s:iterator value="#request.page.list" status="stat" id="item">
					<ul class="pl">
						<li class="author">By：${item.userName } &nbsp; Posted：<s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"/> </li>
						<li style="word-break:break-all">${item.content }</li>
						<s:iterator value="#request.manageLeaveList" id="manage">
			  				<s:if test="#item.id==#manage.whoId">
								<li class="reply"><font class="bluefont">Reply from moderators：</font>${manage.content }</li>
							</s:if>
						</s:iterator>
					</ul>
					</s:iterator>
					
					<c:if test="${page.totalPage>1}">
					<ul class="fenyea">
					  <u:newPage href="/web/blog/blogManage!view.action?lvBlogContent.id=${lvBlogContent.id}&page.pageNum=@" language="en"></u:newPage>
					  <script type="text/javascript">
						function toPage(){
							var pageNum = $("#pageValue").val();
						   	window.location.href="/web/blog/blogManage!view.action?lvBlogContent.id=${lvBlogContent.id}&page.pageNum="+pageNum;
						
						}
					 </script>
					 </ul>
				 	</c:if>
			
					<form action="/web/blog/blogManage!review.action" method="post" id="blogfrm">
						<input type="hidden" name="lvBlogContent.id" value="${lvBlogContent.id }"/>
						<input type="hidden" name="lvBlogLeave.lvBlogContent.id" value="${lvBlogContent.id}"/>
						<span class="blog_comments_ban"><font class="ffont fontwz">Leave comments</font></span>	
						 <ul class="pl">
							<li><textarea id="review" rows="" class="input1" name="lvBlogLeave.content" onfocus="changeText();">Click here to enter your comment…</textarea></li>
							<li id="noLogin"><span class="redfont">Sorry, you need to be logged in to post a comment.</span>&nbsp; <font class="blue2"><a href="javascript:toLo('${storeDomain }');" id="blog_login"> Login</a></font> &nbsp;  <font class="redfont"><a href="javascript:toRe('${storeDomain }');" id="blog_register">Register</a></font></li>
							<li><input type="button" class="user_center_bt" id="regbtn"  onclick="onSub('${storeDomain }');" value="Publish" /></li>
						</ul>
				  </form>	
				</div>									
			</div>
			
            <!-- 博客右边菜单导航 --> 
			<%@include file="/web/bmen/blog/blog_right.jsp" %>
			<div class="cb"></div>
		</div>		
	
		<!-- 底部-->
		<%@include file="/web/bmen/common/footer.jsp" %>
	
	</body>
</html> 