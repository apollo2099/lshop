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
<title>${lvBlogContent.title }- HUA YANG MALL</title>
<% request.setAttribute("navFlag","blog"); %>
<%@include file="/web/tvpaden/common/header.jsp" %>
<link href="${resDomain}/tvpaden/res/css/main.css" rel="stylesheet" type="text/css" />
<link href="${resDomain}/tvpaden/res/css/blog.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">

$().ready(function() {
	var users=lshop.getCookieToJSON('user');
	if(null==users.uid||""==users.uid){
		$("#noLogin").show();
	}else{
		$("#noLogin").hide();
	}
});
//验证用户是否已经登录，如果未登录阻止用户信息提交并弹出友好提示
function onSub(){
	var users=lshop.getCookieToJSON('user');
	if(null==users.uid||""==users.uid){
		ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
		ymPrompt.confirmInfo('<strong>Please login first. If you don\'t have an account yet, please register!</strong>',null,null,'Tips',function handler(tp){if(tp=='ok'){
			  onshow('tx_b','loginDiv');
		  }
		if(tp=='cancel'){
			ymPrompt.close();
		  }} );
	}else if($("#review").val()=="Click here to enter your comment…"||$("#review").val()==""){
		ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
	    ymPrompt.alert({title:'Tips',message:'Please enter your comments!'}) ;
	}else{
		$("#blogfrm").submit();
	}
}

//复制网址
function fCopyToClicp(id){
  var tempHref=document.location.href;
  var a = document.getElementById(id);
  window.clipboardData.setData('text',tempHref);
  ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
  ymPrompt.alert({title:'Tips',height:230,message:'The content has been copied to the clipboard, you may paste it on QQ/MSN and share it to your friends!'}) ;
}

 function copyClipboard(txt){
 	txt=document.location.href;
  	if(window.clipboardData){
		window.clipboardData.clearData();		
		window.clipboardData.setData("Text",txt);
 		ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
  		ymPrompt.alert({title:'Tips',height:230,message:'The content has been copied to the clipboard, you may paste it on QQ/MSN and share it to your friends!'}) ;
	}else if(navigator.userAgent.indexOf("Opera")!=-1){
		window.location=txt;
	}else if(window.netscape){
		try
		{
			netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
		}catch(e)
		{
		 	var a=	prompt("The current browser does not support shortcut key, please copy the code to clipboard by using 'Ctrl+C'",txt);
		  	if(a!=null){
 				mPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
  				ymPrompt.alert({title:'Tips',height:230,message:'The content has been copied to the clipboard, you may paste it on QQ/MSN and share it to your friends!'}) ;
		  	}
		}
		var clip=Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard);		
		if(!clip)return ;		
		var trans=Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable);		
		if(!trans)return ;	
		trans.addDataFlavor('text/unicode');	
		var str=new Object();	
		var len=new Object();	
		var str=Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString);	
		var copytext=txt;		
		str.data=copytext;		
		trans.setTransferData("text/unicode",str,copytext.length*2);	
		var clipid=Components.interfaces.nsIClipboard;	
		if(!clip)return false;	
		clip.setData(trans,null,clipid.kGlobalClipboard);	
		ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
  		ymPrompt.alert({title:'Tips',height:230,message:'The content has been copied to the clipboard, you may paste it on QQ/MSN and share it to your friends!'}) ;
	}
  }
//当用户焦点放在评论时，清除自带的文字内容
function changeText(){
	var review=$("#review").val();
		if(review=="Click here to enter your comment…"){
		$("#review").val("")
	}
}

function toLo(){
	var url=window.location;
	window.location.href="${storeDomain}/web/www/noLoginInfo.jsp?jumpurl="+url;
}
</script>

</head>

<%
Integer  contentType=(Integer)request.getAttribute("contentType");
System.out.println("jsp println=>"+contentType);
 %>

<body>
<%@include file="/web/tvpaden/common/top.jsp" %>

<div class="blog">
	<div class="blog_banner">
    	<ul>
    		<li class="anniu05"><a href="#" class="rss">RSS Feeds</a></li>
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
		<c:if test="${not empty lvBlogContent.id}">--><a class="lm" href="/web/blog/blogManage!view.action?lvBlogContent.id=${lvBlogContent.id }">${lvBlogContent.title}</a></c:if>	
	</h1>
	<!--左边内容-->
	<span class="blog_left">
		<ul>
			<li class="blog_left_info"><font class="blogfont"><s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"/>     Tags：${lvBlogContent.keyword}</font></li>
			<li class="blog_left_infotitle">${lvBlogContent.title }</li>	
			<li class="blog_left_author"><font class="blogfont">From:TVpad    &nbsp;&nbsp;By:${lvBlogContent.userName}</font></li>		
			<li>
			  <p>${lvBlogContent.content }</p>
			</li>
				
			<li class="blog_left_next"><font class="blogfont">Prev Article：<a href="/web/blog/blogManage!view.action?lvBlogContent.id=${upContent.id}&type=${contentType}&keyword=${keyword}" class="biaoqian">${upContent.title }</a> </font></li>
			<li class="blog_left_next"><font class="blogfont">Next Article：<a href="/web/blog/blogManage!view.action?lvBlogContent.id=${nextContent.id}&type=${contentType}&keyword=${keyword}" class="biaoqian">${nextContent.title }</a></font></li>
			<li>Posted by:bell  |  
				Category：<s:iterator value="#request.typeList" id="t">
		 				<s:if test="#t.id==lvBlogContent.type">${t.type}</s:if>
						 </s:iterator>  | 
	 				Reply：<s:if test="#request.lvBlogContent.replyNum !=null">${lvBlogContent.replyNum  }</s:if>
	     					 <s:else>0</s:else>  | 
	 				Views：<s:if test="#request.lvBlogContent.clickNum !=null">${lvBlogContent.clickNum  }</s:if>
				     <s:else>0</s:else>
			</li>	
			<li>
				<img src="${resDomain}/tvpaden/res/images/share01.gif" /> <font class="redfont">Share on:</font>
				<img src="${resDomain}/tvpaden/res/images/share02.gif" /> <a href="javascript:void(0)"  onClick="copyClipboard('txt');">Copy URL</a>
				<img src="${resDomain}/tvpaden/res/images/share03.gif" /> <a class="face" rel="nofollow" href="javascript:window.open('http://www.facebook.com/sharer.php?u='+encodeURIComponent(document.location.href)+'&amp;t='+encodeURIComponent(document.title));void(0)">Facebook</a>
				<img src="${resDomain}/tvpaden/res/images/share04.gif" /><a href="#" class="jiathis_button_qzone">QQ-zone</a>
			</li>	
			<script type="text/javascript" src="http://v2.jiathis.com/code/jia.js" charset="utf-8"></script>	
	   	</ul>  

		  <div class="blog_comments">
		  	<span class="blog_comments_ban">All comments</span>
		  		<s:iterator value="#request.page.list" status="stat" id="item">
				<ul>
					<li class="author">Netizen message：${item.userName }   Time：<s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"/> </li>
					<li>${item.content }</li>
					<s:iterator value="#request.manageLeaveList" id="manage">
		  				<s:if test="#item.id==#manage.whoId">
							<li class="reply"><font class="bluefont">Reply from moderators：</font>${manage.content }</li>
						</s:if>
					</s:iterator>
				</ul>
				</s:iterator>
				
		    <input type="hidden" value="${lvBlogContent.id }" id="contentId"/>  
		    <div class="blog_fenye">
			<table width="100%">
				<tr>
				<td width="79%" align="center" style="text-align: center">
				<u:page href="/web/blog/blogManage!view.action?lvBlogContent.id=${lvBlogContent.id}&page.pageNum=@" language="en"></u:page>
				 <script type="text/javascript">
						function gotoPage(num){
						   window.location.href="/web/blog/blogManage!view.action?lvBlogContent.id="+$("#contentId").val()+"&page.pageNum="+num;
						}
				</script>
				</td>
				</tr>
			</table>
	     	</div>
		
			<form action="/web/blog/blogManage!review.action" method="post" id="blogfrm">
				<input type="hidden" name="lvBlogContent.id" value="${lvBlogContent.id }"/>
				<input type="hidden" name="lvBlogLeave.lvBlogContent.id" value="${lvBlogContent.id}"/>
				<span class="blog_comments_ban"><font class="ffont fontwz">Leave comments</font></span>	
				<ul>
					<li><textarea id="review" rows="" class="input1" name="lvBlogLeave.content" onfocus="changeText();">Click here to enter your comment…</textarea></li>
				</ul>
				<div id="loginInfo">
					<ul>
						<li id="noLogin"><font class="redfont">Sorry, you are current logged out!<a href="javascript:toLo();" id="blog_login">  Login</a> <a href="javascript:toRe('${storeDomain }');" id="blog_register">Register</a></font></li>
						<li><input type="button" onclick="onSub();" class="btn05" value="Publish" style="CURSOR: pointer; "/></li>
					</ul>
				</div>
		  </form>	
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

<!-- 底部-->
<%@include file="/web/tvpaden/common/footer.jsp" %>

</body>
</html> 