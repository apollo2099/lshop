<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Official Activities, Hot TV Plays, Exciting Sports and Other Latest Information-HUA YANG MALL</title>
<meta name="description" content="TVpad Blog on HUA YANG INTERNATIONAL provides hot TV drama intro, exciting sports, lastest and real time official activities, which enables users to know well the lastest trends." />
<meta name="keywords" content="TVpad Official Blog covers the exciting sports, hot TV drama intro, TVpad Blog, TVpad official activities, hot music intro, popular western TV plays intro and hot Japanese & Korean TV drama intro." />
<% request.setAttribute("navFlag","blog"); %>
<%@include file="/web/tvpaden/common/header.jsp" %>
<link href="${resDomain}/tvpaden/res/css/main.css" rel="stylesheet" type="text/css" />
<link href="${resDomain}/tvpaden/res/css/blog.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function rss(){
	//先判断用户是否已经登录
	var users=lshop.getCookieToJSON('user');
	if(null==users.uid||""==users.uid){
		ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
		ymPrompt.confirmInfo('Please login first. If you don\'t have an account yet, please register!',null,null,'Tips',function handler(tp){if(tp=='ok'){
			  onshow('tx_b','loginDiv');
		  }
		if(tp=='cancel'){
			ymPrompt.close();
		 }} );
	}else{
		var tempHref=$("#tempHref").val();
		$("#rssId").attr("href",tempHref);
	}
}
</script>
</head>
<body>
<input type="hidden" id="tempHref" value="http://fusion.google.com/add?feedurl=<s:text name="lshop.lshopcn.url"/>blog/rss/rss.xml"/>
<!-- top -->
<%@include file="/web/tvpaden/common/top.jsp" %>
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
		<img src="${resDomain}/tvpaden/res/images/icon02.gif" /><font class="bfont">You are here:--><a href="/index.html"> Home</a>--></font><a href="/web/blog/blogManage!list.action?lvBlogContent.type=&lvBlogContent.keyword=">Blogs</a>
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
			<li class="blog_left_title_1"><s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"/>  From:<a href="#">TVpad</a> By:<a href="#">${item.userName }</a></li>
			<li>
				<s:if test="%{null!=#item.intor&&#item.intor.length()>100}">
                    <s:property value="%{#item.intor.substring(0, 100)}" />……
                </s:if>
                <s:else><s:property value="#item.intor"/></s:else>
            </li>
			<li class="blog_left_more"><a href="/web/blog/blogManage!view.action?lvBlogContent.id=${item.id }">Read more>></a></a></li>
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
	  			Tags：<a href="#">${item.keyword }</a> </li>
		</ul>
		</s:iterator>

		   <!--分页-->
		  <div class="blog_fenye">
			<table width="100%">
				<tr>
				<td width="79%" align="center" style="text-align: center">
				<u:page href="/web/blog/blogManage!list.action?page.pageNum=@" language="en"></u:page>
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
			<span class="blog_sort_ban">Blog Category</span>
	      	<ul><li><a href="/web/blog/blogManage!list.action?lvBlogContent.type=&lvBlogContent.keyword=">All articles</a> [${contentSum}] </li></ul>
		  	<c:foreach items="${typeList}" var="t" varStatus="index" >
			   <c:foreach items="${typeCountList}" var="sum">
			    <c:if test="${t.id==sum.type}">
			       <ul><li><a href="/web/blog/blogManage!list.action?lvBlogContent.type=${t.id }&lvBlogContent.keyword=">${t.type }</a> [${sum.num}] </li></ul>
			    </c:if>
			   </c:foreach>
		  	</c:foreach>
		</div>
		
		<div class="blog_label">
			<span class="blog_label_ban">Hot Tags</span>
				<ul>
					<li>
				      	<c:foreach items="${tagsList}" var="tags">
				      		<a href="/web/blog/blogManage!list.action?lvBlogContent.keyword=${tags.tagName }&lvBlogContent.type=">${tags.tagName }</a>
				      	</c:foreach>
				    </li>
				</ul>
				
		</div>	
		
		<div class="blog_hot">
			<span class="blog_hot_ban">Hot blogs</span>
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
<%@include file="/web/tvpaden/common/footer.jsp" %>

</body>
</html> 