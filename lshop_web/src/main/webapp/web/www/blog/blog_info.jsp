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
		<title>${lvBlogContent.title }_TVpad商城</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="${resDomain}/www/res/css/blog.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<%@include file="/web/www/common/top.jsp" %>
		<script type="text/javascript" src="${resDomain}/www/res/js/blog.js"></script>
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
			<h1><img src="${resDomain}/www/res/images/icon02.gif" /><font class="bfont">當前位置--><a href="http://www.mtvpad.com/index.html"> 首頁</a>--><a href="http://www.mtvpad.com/blog.html"> 官方博客</a>
			<c:if test="${not empty lvBlogContent.type}">
			-->
					<c:foreach items="${typeList}" var="t">
					<c:if test="${lvBlogContent.type== t.id}">
					<a href="http://www.mtvpad.com/blog/blogType${t.id }.html">${t.type}</a></font> </h1>
					</c:if>
			</c:foreach>
			</c:if>
			<!--左边内容-->
			<div class="blog_left_z">
				<div class="blog_left">
					<ul>
					    <li class="blog_left_info"><font class="blogfont"> 標籤：${lvBlogContent.keyword}</font></li>
		                <li class="blog_left_infotitle">${lvBlogContent.title }</li>	
		                <li class="blog_left_author"><font class="blogfont">出處：TVpad  作者：${lvBlogContent.userName}  發表時間：<s:date name="#request.lvBlogContent.createTime" format="yyyy-MM-dd HH:mm:ss"/></font></li>		
						<%--
						<li class="blog_left_info"><font class="blogfont"><s:date name="#request.lvBlogContent.createTime" format="yyyy-MM-dd HH:mm:ss"/>     標籤：${lvBlogContent.keyword}</font></li>
						<li class="blog_left_infotitle">${lvBlogContent.title }</li>	
						<li class="blog_left_author"><font class="blogfont">出處：TVpad    &nbsp;&nbsp;作者：${lvBlogContent.userName}</font></li>
						 --%>		
						<li>
						  <p>${lvBlogContent.content }</p>
						</li>
							
						<li class="blog_left_next"><font class="blogfont">上一篇：<a href="/blog/blogInfo${upContent.id}.html" class="biaoqian" title="<s:property value="#request.upContent.title"/>"><s:if test="%{null!=#request.upContent.title&&#request.upContent.title.length()>12}"><s:property value="%{#request.upContent.title.substring(0, 12)}" />……</s:if><s:else><s:property value="#request.upContent.title"/></s:else></a> </font></li>
						<li class="blog_left_next"><font class="blogfont">下一篇：<a href="/blog/blogInfo${nextContent.id}.html" class="biaoqian"  title="<s:property value="#request.nextContent.title"/>"><s:if test="%{null!=#request.nextContent.title&&#request.nextContent.title.length()>12}"><s:property value="%{#request.nextContent.title.substring(0, 12)}" />……</s:if><s:else><s:property value="#request.nextContent.title"/></s:else></a></font></li>
						<li>發佈：${lvBlogContent.userName}  |  
							分類：<s:iterator value="#request.typeList" id="t">
					 				<s:if test="#t.id==lvBlogContent.type">${t.type}</s:if>
									 </s:iterator>  | 
				 				回覆：<s:if test="#request.lvBlogContent.replyNum !=null">${lvBlogContent.replyNum  }</s:if>
				     					 <s:else>0</s:else>  | 
				 				瀏覽：<s:if test="#request.lvBlogContent.clickNum !=null">${lvBlogContent.clickNum  }</s:if>
							     <s:else>0</s:else>
						</li>	
						<li>
							<img src="${resDomain}/www/res/images/share01.gif" /> <font class="redfont">分享到：</font>
							<img src="${resDomain}/www/res/images/share02.gif" /> <a href="javascript:void(0)"  onClick="copyClipboard('txt');">複製網址</a>
							<img src="${resDomain}/www/res/images/share03.gif" /> <a class="face" rel="nofollow" href="javascript:window.open('http://www.facebook.com/sharer.php?u='+encodeURIComponent(document.location.href)+'&amp;t='+encodeURIComponent(document.title));void(0)">Facebook</a>
							<img src="${resDomain}/www/res/images/share04.gif" /><a href="#" class="jiathis_button_qzone">QQ空間</a>
						</li>	
						<script type="text/javascript" src="http://v2.jiathis.com/code/jia.js" charset="utf-8"></script>	
				   	</ul>  
				</div>
				 
			  	<div class="blog_comments">
			  		<span class="blog_comments_ban">所有評論</span>
			  		<s:iterator value="#request.page.list" status="stat" id="item">
					<ul class="pl">
						<li class="author">留言網友：${item.userName }   發表時間：<s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"/> </li>
						<li style="word-break:break-all">${item.content }</li>
						<s:iterator value="#request.manageLeaveList" id="manage">
			  				<s:if test="#item.id==#manage.whoId">
								<li class="reply"><font class="bluefont">管理員回覆：</font>${manage.content }</li>
							</s:if>
						</s:iterator>
					</ul>
					</s:iterator>
					
					<c:if test="${page.totalPage>1}">
					  <u:newPage href="/web/blog/blogManage!view.action?lvBlogContent.id=${lvBlogContent.id}&page.pageNum=@" language="cn"></u:newPage>
					  <script type="text/javascript">
						function toPage(){
							var pageNum = $("#pageValue").val();
						   	window.location.href="/web/blog/blogManage!view.action?lvBlogContent.id=${lvBlogContent.id}&page.pageNum="+pageNum;
						
						}
					 </script>
				 	</c:if>
			
					<form action="/web/blog/blogManage!review.action" method="post" id="blogfrm">
						<input type="hidden" name="lvBlogContent.id" value="${lvBlogContent.id }"/>
						<input type="hidden" name="lvBlogLeave.lvBlogContent.id" value="${lvBlogContent.id}"/>
						<span class="blog_comments_ban"><font class="ffont fontwz">發表評論</font></span>	
						<ul>
							<li><textarea id="review" rows="" class="input1" name="lvBlogLeave.content" onfocus="changeText();">點擊這裡發表評論…</textarea></li>
						</ul>
						<div id="loginInfo">
							<ul>
								<li id="noLogin"><font class="redfont">抱歉,您還沒有登錄！<a href="javascript:toLo('${storeDomain }');" id="blog_login"> 登錄</a> <a href="javascript:toRe('${storeDomain }');" id="blog_register">註冊新用戶</a></font></li>
								<li><a href="javascript:onSub('${storeDomain }');"><img src="${resDomain}/www/res/images/button.gif" border="0" /></a></li>
							</ul>
						</div>
				  </form>	
				</div>									
			</div>
			
			<!-- 博客右边菜单导航 --> 
			<%@include file="/web/www/blog/blog_right.jsp" %>
			
		</div>		
	
		<!-- 底部-->
		<%@include file="/web/www/common/footer.jsp" %>
	
	</body>
</html> 