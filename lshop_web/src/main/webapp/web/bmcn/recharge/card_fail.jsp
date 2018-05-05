<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户中心_banana商城</title>
<link href="${resDomain}/bmcn/res/css/user_center.css" rel="stylesheet" type="text/css" />
<!-- 加载公共JS -->
<%@include file="/web/bmcn/common/top.jsp"%>
</head>
<body>
<!-- 获取商城头部文件 -->
<%@include file="/web/bmcn/common/header.jsp"%>
<div class="content_main">
  <!-- left_frame -->
  <%@include file="/web/bmcn/user_center/leftFrame.jsp" %>
  <div class="right_frame">
	 	<h1><font class="bfont"><img src="${resDomain}/bmcn/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">首页</a> --><a href="/web/userCenterManage!getAccount.action"> 用户中心</a> --><a href="/web/recharge!list.action"> 账户余额</a> --></font> 充值失败</h1> 
		<div class="usercenter_box">
		  <div class="user_center_right">
            <ul>
	          <li class="center"><img src="${resDomain}/bmcn/res/images/fail.gif" /> <font class="suc">充值失败！</font></li>
			  <li><p class="text1">订单号：</p> <p><font class="bluefont">${orderno}</font> </p></li>
			  <li>
	            <p class="text1">账号：</p>
	            <p><font class="bluefont" id="accId">${recharge.getAccounts}</font> </p>
	          </li>
	          <li class="center1"><p class="text1">客服热线：</p><p><font class="bluefont">${serviceTel}</font></p></li>
			</ul>
			<ul>
			  <li class="center">
			    <input id="regbtn" class="user_center_bt03" type="button" value="返 回"  onclick="javascript:location.href='/web/recharge!list.action'">
		      </li>
			</ul>
      	</div>
		<div class="cb"></div>		
	</div>
  </div>
</div>
<!--End content-->
<!-- 底部广告位-->
<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
<!-- footer-->
<%@include file="/web/bmcn/common/footer.jsp" %>
</body>
</html>