<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>最新博客邮件订阅预览</title>
<style type="text/css">
div,dl,dt,dd,ul,ol,li,h1,h2,h3,h4,h5,h6,pre,form,fieldset,input,textarea,p,blockquote,th,td,iframe {margin:0;padding:0;}
#mail {
	width: 800px;
	margin-right: auto;
	margin-left: auto;
}
body{ padding:0; margin:0; font-family:"宋体"; font-size:12px; color:#777;}
.mail_top{width:auto;height:78px;background-image: url(/res/images/mail_top_bg.gif);background-repeat: repeat;}
.mail_top_a{width:700px;height:78px;margin-right: auto;margin-left: auto;}
.mail_logo{ width:96px; height:48px; float:left; padding:17px 0 0 0;}
.zhuce{ width:157px; height:48px; float:right; padding:17px 0 0 0; display:block;}
.quxiao{ float:left;}
.shouye{ float:right;}
.banner{width:auto;height:299px;background-image: url(/res/images/mail_banner_bg.gif);background-repeat: repeat;}
.banner_a{ width:700px; height:299px; margin:0 auto 0 auto;}
.main{ width:auto; height:auto;background-image: url(/res/images/mail_main_bg.gif);background-repeat: repeat-x;}
.main_1{width:698px;height:auto;margin-right: auto;margin-left: auto;padding-top: 20px;}
.main_n{
	width:698px;
	height:auto;
	display: block;
	margin-right: auto;
	margin-left: auto;
	border: 1px solid #d1d1d1;
}
.title {
	font-size: 14px;
	color: #444;
	font-weight: bold;
	background-image: url(/res/images/title_bg.gif);
	background-repeat: repeat;
	height: 31px;
	width: 696px;
	line-height: 31px;
	text-indent: 18px;
}
.main_z {
	width: 663px;
	padding-top: 10px;
	margin-right: auto;
	margin-left: auto;
}
.main_n1 {
	height: auto;
	width: 663px;
	display: block;
}
.title2 {
	font-size: 14px;
	font-weight: bold;
	color: #777;
	display: block;
	float: left;
}
.date {
	float: right;
	display: block;
	color: #a7a7a7;
}
.text {
	line-height: 20px;
	padding-top: 10px;
}
.a7 {
	color: #a7a7a7;
	display: block;
	padding-top: 15px;
	padding-bottom: 10px;
}
.xian {
	height: 1px;
	width: 661px;
	margin-right: auto;
	margin-left: auto;
	display: block;
}
.anniu {
	height: 58px;
	width: 226px;
	display: block;
	float: right;
	padding-right: 18px;
	padding-top: 20px;
}
.cb {
	clear: both;
}
.copy {
	height: 73px;
	margin-top: 30px;
	background-image: url(/res/images/banquan_bg.gif);
	line-height: 73px;
	text-align: center;
}
</style>
</head>

<body>
<div id="mail">
<div class="mail_top">
  <div class="mail_top_a">
    <img src="/res/images/mail_logo.gif"  class="mail_logo"/>
	<span class="zhuce">
	<a href="<s:text name="lshop.lshopcn.url"/>/"><img src="/res/images/mail_quxiao.gif" border="0"  class="quxiao"/></a>
	<a href="<s:text name="lshop.lshopcn.url"/>"><img src="/res/images/mail_shouye.gif" border="0"  class="shouye"/></a>
	</span> 
  </div>
</div>

<div class="banner">
  <div class="banner_a"><img src="/res/images/mail_banner.gif" /></div>
</div>

<div class="main">
  <div class="main_1">
    <span class="main_n"><h1 class="title">
     <s:if test="%{null!=#request.lvBlogSubscribe.title&&#request.lvBlogSubscribe.title.length()>30}">
                               <s:property value="%{#request.lvBlogSubscribe.title.substring(0, 30)}" />
                           ……
                          </s:if>
                          <s:else><s:property value="#request.lvBlogSubscribe.title"/></s:else>
    </h1>
	
	<s:iterator value="#request.page.list" status="stat" id="item">
	<div class="main_z">
	  <span class="main_n1" style="word-wrap:break-word; width:615px;"><h2 class="title2">${item.title }</h2>  <span class="date">${item.createTime}</span> 
	   <div class="cb"></div>
	  </span>
      <p class="text">
      <span class="blog_text01" style="word-wrap:break-word; width:615px;">
<s:if test="%{null!=#item.content&&#item.content.length()>100}">
                           <s:property value="%{#item.content.substring(0, 100)}" />
                           ……
                          </s:if>
                          <s:else><s:property value="#item.content"/></s:else></span>	
      </p>
<span class="a7">作者：${item.userName } &nbsp; 分类：<s:iterator value="#request.typeList" id="t">
							    <s:if test="#t.id==#item.type">${t.type}<s:set name="flag"
									value="false" />
							    </s:if>
						        </s:iterator></span>
<p class="xian"><img src="/res/images/xian.gif" /></p>
    </div>
	   </s:iterator>
	   
	 
	<span class="anniu"><a href="<s:text name="lshop.lshopcn.url"/>"><img src="/res/images/mail_anniu.gif" border="0" /></a></span>  
<div class="cb"></div>
</span>
  
  </div>
</div>

<div  class="copy">Copyright(C) 2007-2015   華揚國際科技有限公司（HUA YANG INTERNATIONAL  TECHNOLOGY LIMITED）.All Rights Reserved</div>
</div>
</body>
</html>
