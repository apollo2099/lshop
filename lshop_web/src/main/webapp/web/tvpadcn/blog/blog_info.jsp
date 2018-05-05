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
<title>${lvBlogContent.title }_華揚商城</title>
<link href="${resDomain}/tvpadcn/res/css/main.css" rel="stylesheet" type="text/css" />
<link href="${resDomain}/tvpadcn/res/css/blog.css" rel="stylesheet" type="text/css" />

<% request.setAttribute("navFlag","blog"); %>
<%@include file="/web/tvpadcn/common/top.jsp" %>

<script type="text/javascript">

$().ready(function() {
	var users=lshop.getCookieToJSON('user');
	if(users.uid!=null){
		$("#noLogin").hide();
	}else{
		$("#noLogin").show();
	}
});
//验证用户是否已经登录，如果未登录阻止用户信息提交并弹出友好提示
function onSub(){
	var users=lshop.getCookieToJSON('user');
	if(null==users.uid||""==users.uid){
		toLo();
	}else if($("#review").val()=="點擊這裡發表評論…"||$("#review").val()==""){
		alert("請填寫評論內容!");
	}else{
		$("#blogfrm").submit();
	}
}

//复制网址
function fCopyToClicp(id){
  var tempHref=document.location.href;
  var a = document.getElementById(id);
  window.clipboardData.setData('text',tempHref);
  alert("已複製到剪貼板，您可以粘貼到QQ/MSN上發送給您的好友！");
}

 function copyClipboard(txt){
 	txt=document.location.href;
  	if(window.clipboardData){
		window.clipboardData.clearData();		
		window.clipboardData.setData("Text",txt);
 		alert("已複製到剪貼板，您可以粘貼到QQ/MSN上發送給您的好友！");
	}else if(navigator.userAgent.indexOf("Opera")!=-1){
		window.location=txt;
	}else if(window.netscape){
		try
		{
			netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
		}catch(e)
		{
		 	var a=	prompt("您使用的瀏覽器不支持快捷鍵，請按下 Ctrl+C 複製代碼到剪貼板！",txt);
		  	if(a!=null){
 					alert("已複製到剪貼板，您可以粘貼到QQ/MSN上發送給您的好友！");
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
		alert("已複製到剪貼板，您可以粘貼到QQ/MSN上發送給您的好友！");
	}
  }
//当用户焦点放在评论时，清除自带的文字内容
function changeText(){
	var review=$("#review").val();
		if(review=="點擊這裡發表評論…"){
		$("#review").val("")
	}
}

function toLo(){
	var url=window.location;
	window.location.href="${storeDomain}/web/www/noLoginInfo.jsp?jumpurl="+url;
}
</script>

</head>



<body>

<div class="blog">
	<div class="blog_banner">
    	<ul>
    		<li class="anniu05"><a href="#" class="rss">Rss訂閱</a></li>
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
		<c:if test="${not empty lvBlogContent.id}">--><a class="lm" href="/web/blog/blogManage!view.action?lvBlogContent.id=${lvBlogContent.id }">${lvBlogContent.title}</a></c:if>	
	</h1>
	<!--左边内容-->
	<span class="blog_left">
		<ul>
			<li class="blog_left_info"><font class="blogfont"><s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"/>     標籤：${lvBlogContent.keyword}</font></li>
			<li class="blog_left_infotitle">${lvBlogContent.title }</li>	
			<li class="blog_left_author"><font class="blogfont">出處：TVpad    &nbsp;&nbsp;作者：${lvBlogContent.userName}</font></li>		
			<li>
			  <p>${lvBlogContent.content }</p>
			</li>
				
			<li class="blog_left_next"><font class="blogfont">上一篇：<a href="/web/blog/blogManage!view.action?lvBlogContent.id=${upContent.id}&type=${contentType}&keyword=${keyword}" class="biaoqian">${upContent.title }</a> </font></li>
			<li class="blog_left_next"><font class="blogfont">下一篇：<a href="/web/blog/blogManage!view.action?lvBlogContent.id=${nextContent.id}&type=${contentType}&keyword=${keyword}" class="biaoqian">${nextContent.title }</a></font></li>
			<li>發佈：bell  |  
				分類：<s:iterator value="#request.typeList" id="t">
		 				<s:if test="#t.id==lvBlogContent.type">${t.type}</s:if>
						 </s:iterator>  | 
	 				回覆：<s:if test="#request.lvBlogContent.replyNum !=null">${lvBlogContent.replyNum  }</s:if>
	     					 <s:else>0</s:else>  | 
	 				瀏覽：<s:if test="#request.lvBlogContent.clickNum !=null">${lvBlogContent.clickNum  }</s:if>
				     <s:else>0</s:else>
			</li>	
			<li>
				<img src="${resDomain}/tvpadcn/res/images/share01.gif" /> <font class="redfont">分享到：</font>
				<img src="${resDomain}/tvpadcn/res/images/share02.gif" /> <a href="javascript:void(0)"  onClick="copyClipboard('txt');">複製網址</a>
				<img src="${resDomain}/tvpadcn/res/images/share03.gif" /> <a class="face" rel="nofollow" href="javascript:window.open('http://www.facebook.com/sharer.php?u='+encodeURIComponent(document.location.href)+'&amp;t='+encodeURIComponent(document.title));void(0)">Facebook</a>
				<img src="${resDomain}/tvpadcn/res/images/share04.gif" /><a href="#" class="jiathis_button_qzone">QQ空间</a>
			</li>	
			<script type="text/javascript" src="http://v2.jiathis.com/code/jia.js" charset="utf-8"></script>	
	   	</ul>  

		  <div class="blog_comments">
		  	<span class="blog_comments_ban">所有評論</span>
		  		<s:iterator value="#request.page.list" status="stat" id="item">
				<ul>
					<li class="author">留言網友：${item.userName }   發表時間：<s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"/> </li>
					<li>${item.content }</li>
					<s:iterator value="#request.manageLeaveList" id="manage">
		  				<s:if test="#item.id==#manage.whoId">
							<li class="reply"><font class="bluefont">管理员回复：</font>${manage.content }</li>
						</s:if>
					</s:iterator>
				</ul>
				</s:iterator>
				
		    <input type="hidden" value="${lvBlogContent.id }" id="contentId"/>  
		    <div class="blog_fenye">
			<table width="100%">
				<tr>
				<td width="79%" align="center" style="text-align: center">
				<u:page href="/web/blog/blogManage!view.action?lvBlogContent.id=${lvBlogContent.id}&page.pageNum=@"></u:page>
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
				<span class="blog_comments_ban"><font class="ffont fontwz">發表評論</font></span>	
				<ul>
					<li><textarea id="review" rows="" class="input1" name="lvBlogLeave.content" onfocus="changeText();">點擊這裡發表評論…</textarea></li>
				</ul>
				<div id="loginInfo">
					<ul>
						<li id="noLogin"><font class="redfont">抱歉,您還沒有登錄！<a href="javascript:toLo();" id="blog_login"> 登錄</a> <a href="javascript:toRe('${storeDomain }');" id="blog_register">註冊新用戶</a></font></li>
						<li><input type="button" onclick="onSub();" class="btn05" value="发 表" style="CURSOR: pointer; "/></li>
					</ul>
				</div>
		  </form>	
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

<!-- 底部-->
<%@include file="/web/tvpadcn/common/footer.jsp" %>

</body>
</html> 