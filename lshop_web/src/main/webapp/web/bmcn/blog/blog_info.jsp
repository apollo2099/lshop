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
		 <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		 <title>banana商城_<s:property value="%{#request.lvBlogContent.title.replaceAll(' ','_')}" /></title>
         <meta name="keywords" content="${lvBlogContent.keyword}"/>
         <meta name="description" content="<s:property value='%{#request.lvBlogContent.content.substring(0, 80)}'/>"/>
		<link href="${resDomain}/bmcn/res/css/blog.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<%@include file="/web/bmcn/common/top.jsp" %>
		<script type="text/javascript" src="${resDomain}/bmcn/res/js/blog.js"></script>
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
			
			<!--左边内容-->
		<div class="blog">
			<div class="blog_left_z">
				<div class="blog_left">
					<ul class="info">
						<li>
							<p><font class="kbqc"><s:date name="#request.lvBlogContent.createTime" format="yyyy-MM-dd HH:mm:ss"/></font></p>
							<p><font class="kbqc">标签：</font><a href="#">${lvBlogContent.keyword}</a></p>
						</li>
						<li class="center"><font class="red">${lvBlogContent.title }</font></li>
						<li class="center"><font class="kbqc">出处：banana TV</font> &nbsp; <font class="kbqc">作者：${lvBlogContent.userName}</font></li>		
						<li class="text">
						  <p>${lvBlogContent.content }</p>
						</li>
							
						<li>
							<p>上一篇：<font class="blue2"><a href="/blog/blogInfo${upContent.id}.html" class="biaoqian" title="<s:property value="#request.upContent.title"/>"><s:if test="%{null!=#request.upContent.title&&#request.upContent.title.length()>12}"><s:property value="%{#request.upContent.title.substring(0, 12)}" />……</s:if><s:else><s:property value="#request.upContent.title"/></s:else></a></font></p> 
							<p>下一篇：<font class="blue2"><a href="/blog/blogInfo${nextContent.id}.html" class="biaoqian"  title="<s:property value="#request.nextContent.title"/>"><s:if test="%{null!=#request.nextContent.title&&#request.nextContent.title.length()>12}"><s:property value="%{#request.nextContent.title.substring(0, 12)}" />……</s:if><s:else><s:property value="#request.nextContent.title"/></s:else></a></font></p>
						</li>
						<li>
							<p>发布：${lvBlogContent.userName}</p> 
							<p>分类：${blogType.type }</p>  
				 			<p>回复：<s:if test="#request.lvBlogContent.replyNum !=null">${lvBlogContent.replyNum  }</s:if>
				     				 <s:else>0</s:else></p>  
				 			<p>浏览：<s:if test="#request.lvBlogContent.clickNum !=null">${lvBlogContent.clickNum  }</s:if>
							         <s:else>0</s:else></p> 
						</li>	
						<li>
							<p><img src="${resDomain}/bmcn/res/images/share01.gif" /> <font class="redfont">分享到：</font></p>
							<p><img src="${resDomain}/bmcn/res/images/share02.gif" /> <a href="javascript:void(0)"  onClick="copyClipboard('txt');">复制网址</a></p>
							<p><img src="${resDomain}/bmcn/res/images/share03.gif" /> <a class="face" rel="nofollow" href="javascript:window.open('http://www.facebook.com/sharer.php?u='+encodeURIComponent(document.location.href)+'&amp;t='+encodeURIComponent(document.title));void(0)">Facebook</a></p>
							<p><img src="${resDomain}/bmcn/res/images/share04.gif" /><a href="#" class="jiathis_button_qzone">QQ空间</a></p>
						</li>	
						<script type="text/javascript" src="http://v2.jiathis.com/code/jia.js" charset="utf-8"></script>	
				   	</ul>  
				</div>
				 
			  	<div class="blog_comments">
			  		<span class="blog_comments_ban">所有评论</span>
			  		<c:if test="${empty page.list}">
				  		<ul class="pl">
							暂无评价，欢迎您第一个留言！
						</ul>
			  		</c:if>
			  		<s:iterator value="#request.page.list" status="stat" id="item">
					<ul class="pl">
						<li class="author">留言网友：${item.userName }   发表时间：<s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"/> </li>
						<li style="word-break:break-all">${item.content }</li>
						<s:iterator value="#request.manageLeaveList" id="manage">
			  				<s:if test="#item.id==#manage.whoId">
								<li class="reply"><font class="bluefont">管理员回复：</font>${manage.content }</li>
							</s:if>
						</s:iterator>
					</ul>
					</s:iterator>
					
					<c:if test="${page.totalPage>1}">
					<ul class="fenyea">
					  <u:newPage href="/web/blog/blogManage!view.action?lvBlogContent.id=${lvBlogContent.id}&page.pageNum=@" language="cn"></u:newPage>
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
						<span class="blog_comments_ban"><font class="ffont fontwz">发表评论</font></span>	
						 <ul class="pl">
							<li><textarea id="review" rows="" class="input1" name="lvBlogLeave.content" onfocus="changeText();">点击这里发表评论…</textarea></li>
							<li id="noLogin"><span class="redfont">抱歉,您还没有登录！</span><a href="javascript:toLo('${storeDomain }');" id="blog_login"> 登录</a> <a href="javascript:toRe('${storeDomain }');" id="blog_register">注册新用户</a></li>
							<li><input type="button" class="user_center_bt" id="regbtn"  onclick="onSub('${storeDomain }');" value="发表" /></li>
						</ul>
				  </form>	
				</div>									
			</div>
			
            <%@include file="/web/bmcn/blog/blog_right.jsp" %>
			<div class="cb"></div>
		</div>		
	
		<!-- 底部-->
		<%@include file="/web/bmcn/common/footer.jsp" %>
	
	</body>
</html> 