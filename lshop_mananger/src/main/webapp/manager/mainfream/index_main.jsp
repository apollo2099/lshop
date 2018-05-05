<%@ page language="java" pageEncoding="utf-8"%>
<%@page import="java.util.Date"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<%
	Date date = new Date();
	request.setAttribute("nowday", date);
%>
<script type="text/javascript">
$(document).ready(function(){

$.msgWin.runMsg('<div></br></br><p><h1>上次登陆时间 :${sessionScope.lastTime}</h1></p></br> <p><h1>上次登录IP :${sessionScope.lastIP}</h1></p><div>',true); 
		
});

jQuery.msgWin = {
	runMsg:function(msgHtml,isHideMsg){
		this.initMsg();
		this.showMsg(msgHtml);
		if(isHideMsg==true){
			setTimeout(function(){$("#msg").animate({right:5,bottom:-250},1000);}, 3500);
			setTimeout(function(){$("#msg").hide();}, 4500);
		}
	}, 
	
	initMsg:function(){
		$("#container").append('<div class="panel" defH="150" id="msg" style="display: none;"></div>');
		$("#msg").css("position","absolute");
		$("#msg").css("display","none");
		$("#msg").css("width","250px");
		$("#msg").css("right","5px");
		$("#msg").html('<h1 onclick="$.msgWin.hideMsg();">消息提醒</h1><div id="msgInfo" ></div>');
	},
	          
	showMsg:function(param) {  
		$("#msg").show();
		$("#msgInfo").html(param);      
		$("#msg").css("bottom","-200").show(); 
		$("#msg").animate({right:5,bottom:5},1000);      
	},           
	hideMsg:function() {           
		$("#msg").animate({right:5,bottom:-250},1000).hide(1000);  
		setTimeout(function(){$("#msg").hide();}, 1000);
	}          
};  

</script>

	<div id="navTab" class="tabsPage" >
		<div class="tabsPageHeader">
			<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
				<ul class="navTab-tab" >
					<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">我的主页</span></span></a></li>
				</ul>
			</div>
			<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
			<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
			<div class="tabsMore">more</div>
		</div>
		<ul class="tabsMoreList">
			<li><a href="javascript:;">我的主页</a></li>
		</ul>
		<div class="navTab-panel tabsPageContent layoutBox">
			<div class="page unitBox">
				<div class="accountInfo">
					<div class="alertInfo">
						<h2><fmt:formatDate value="${nowday}" pattern="yyyy年MM月dd日  E"/></h2>
					</div>
					<div class="right">
					</div>
					<p>
						<span><a class="add" href="index_ver_info.jsp" target="dialog" mask="true" rel="${navTabId}" width="900" height="470"  title="版本介绍">平台版本信息</a></span>
					</p>
					<p>
					</p>
				</div>
				<div class="pageFormContent" layoutH="80">

<!-- 
					<div class="sortDrag" style="width:48%;margin:5px;float:left;">
						<div style="border:1px solid #B8D0D6;padding:5px;margin:5px;height: 80px;">消息提醒A1</div>
						<div style="border:1px solid #B8D0D6;padding:5px;margin:5px;height: 80px;">消息提醒A2</div>
						<div style="border:1px solid #B8D0D6;padding:5px;margin:5px;height: 80px;">消息提醒A3</div>
						<div style="border:1px solid #B8D0D6;padding:5px;margin:5px;height: 80px;">消息提醒A4</div>
						<div style="border:1px solid #B8D0D6;padding:5px;margin:5px;height: 80px;">消息提醒A5</div>
					</div>
					
					<div class="sortDrag" style="width:48%;margin:5px;float:left;">
						<div style="border:1px solid #B8D0D6;padding:5px;margin:5px;height: 80px;">消息提醒B1</div>
						<div style="border:1px solid #B8D0D6;padding:5px;margin:5px;height: 80px;">消息提醒B2</div>
						<div style="border:1px solid #B8D0D6;padding:5px;margin:5px;height: 80px;">消息提醒B3</div>
						<div style="border:1px solid #B8D0D6;padding:5px;margin:5px;height: 80px;">消息提醒B4</div>
						<div style="border:1px solid #B8D0D6;padding:5px;margin:5px;height: 80px;">消息提醒B5</div>
					</div>
 -->
				</div>
			</div>
		</div>
	</div>

