<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>官方活動、熱門劇集、精彩體育等最新信息_華揚商城</title>
<meta name="description" content="TVpad華揚官方博客，最熱門的劇集介紹，精彩的體育賽事和最新的官方活動實時更新，讓用戶掌握最新動態。" />
<meta name="keywords" content="TVpad官方博客，精彩體育賽事，熱門劇集介紹，TVpad blog，TVpad官方活動，熱門音樂介紹，熱門歐美電視劇介紹，熱門日韓電視劇介紹" />
<%@include file="/web/tvpadcn/common/header.jsp" %>
<link href="${resDomain}/tvpadcn/res/css/main.css" rel="stylesheet" type="text/css" />
<link href="${resDomain}/tvpadcn/res/css/blog.css" rel="stylesheet" type="text/css" />

<% request.setAttribute("navFlag","blog"); %>
<%@include file="/web/tvpadcn/common/top.jsp" %>
<script type="text/javascript">
function rss(){
	//先判断用户是否已经登录
	var users=lshop.getCookieToJSON('user');
	if(null==users.uid||""==users.uid){
		alert("請先登陸，如果沒有帳戶請先註冊帳戶!");
		onshow('tx_b','loginDiv');
	}else{
		var tempHref=$("#tempHref").val();
		$("#rssId").attr("href",tempHref);
	}
}
</script>
</head>
<body>
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
	
	<h1>
		<img src="${resDomain}/tvpadcn/res/images/icon02.gif" /><font class="bfont">當前位置--><a href="/index.html"> 首頁</a>--></font><a href="/web/blog/blogManage!list.action?lvBlogContent.type=&lvBlogContent.keyword=">官方博客</a>
		<c:if test="${not empty lvBlogContent.type}">
			<c:foreach items="${typeList}" var="t">
				<c:if test="${t.id==lvBlogContent.type}">--><a href="/web/blog/blogManage!list.action?lvBlogContent.type=${t.id }">${t.type}</a></c:if>
			</c:foreach>
		</c:if>
		<c:if test="${not empty lvBlogContent.keyword}">--><a href="/web/blog/blogManage!list.action?lvBlogContent.keyword=${lvBlogContent.keyword }">${lvBlogContent.keyword}</a></c:if>	
	</h1>

<!--内容部分-->
	<span class="blog_left">
		<s:iterator value="#request.page.list" status="stat" id="item">
		<ul>
			<li class="blog_left_title">
			    <s:if test="%{null!=#item.title&&#item.title.length()>30}">
                        <s:property value="%{#item.title.substring(0, 30)}" />……
                 </s:if>
                 <s:else><s:property value="#item.title"/></s:else>
            </li>
			<li class="blog_left_title_1"><s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"/>  出處：<a href="#">TVpad</a> 作者：<a href="#">${item.userName }</a></li>
			<li>
				<s:if test="%{null!=#item.intor&&#item.intor.length()>100}">
                    <s:property value="%{#item.intor.substring(0, 100)}" />……
                </s:if>
                <s:else><s:property value="#item.intor"/></s:else>
            </li>
			<li class="blog_left_more"><a href="/web/blog/blogManage!view.action?lvBlogContent.id=${item.id }">閱讀全文>></a></a></li>
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

		   <!--分页-->
		  <div class="blog_fenye">
			<table width="100%">
				<tr>
				<td width="79%" align="center" style="text-align: center">
				<u:page href="/web/blog/blogManage!list.action?page.pageNum=@" language="cn"></u:page>
		           <script type="text/javascript">
						function gotoPage(num){
						   window.location.href="/web/blog/blogManage!list.action?page.pageNum="+num;
						}
				</script>
				</td>
				</tr>
			</table>
		</div>	
	</span>
	
	<span class="blog_right">
		<div class="blog_sort">
			<span class="blog_sort_ban">Blog 分类</span>
	      	<ul><li><a href="/web/blog/blogManage!list.action?lvBlogContent.type=&lvBlogContent.keyword=">所有文章</a> [${contentSum}] </li></ul>
		  	<c:foreach items="${typeList}" var="t" varStatus="index" >
			   <c:foreach items="${typeCountList}" var="sum">
			    <c:if test="${t.id==sum.type}">
			       <ul><li><a href="/web/blog/blogManage!list.action?lvBlogContent.type=${t.id }&lvBlogContent.keyword=">${t.type }</a> [${sum.num}] </li></ul>
			    </c:if>
			   </c:foreach>
		  	</c:foreach>
		</div>
		
		<div class="blog_label">
			<span class="blog_label_ban">熱門標籤</span>
				<ul>
					<li>
				      	<c:foreach items="${tagsList}" var="tags">
				      		<a href="/web/blog/blogManage!list.action?lvBlogContent.keyword=${tags.tagName }&lvBlogContent.type=">${tags.tagName }</a>
				      	</c:foreach>
				    </li>
				</ul>
				
		</div>	
		
		<div class="blog_hot">
			<span class="blog_hot_ban">熱點博文</span>
	      	<ul>
	     		<s:iterator value="#request.contentList" status="sta" id="co">
	      			<li><a href="/web/blog/blogManage!view.action?lvBlogContent.id=${co.id }">
		      		 <s:if test="%{null!=#co.title&&#co.title.length()>16}">
	                        <s:property value="%{#co.title.substring(0, 16)}" />……
	                 </s:if>
	                 <s:else><s:property value="#co.title"/></s:else>
	      		</a></li>
	      		</s:iterator>
	      	</ul>
		</div>	
	</span>
</div>		

<!-- footer-->
<%@include file="/web/tvpadcn/common/footer.jsp" %>

</body>
</html> 