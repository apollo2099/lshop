<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>User Center_TVpad Mall</title>
<link href="${resDomain}/en/res/css/user_center.css" rel="stylesheet" type="text/css" />
<!-- 加载公共JS -->
<%@include file="/web/en/common/top.jsp"%>
</head>
<body>
<!-- 获取商城头部文件 -->
<%@include file="/web/en/common/header.jsp"%>
<div class="content_main">
  <!-- left_frame -->
  <%@include file="/web/en/user_center/leftFrame.jsp" %>
  <div class="right_frame">
	 	<h1><font class="bfont"><img src="${resDomain}/en/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">Home</a> --><a href="/web/userCenterManage!getAccount.action"> User Center</a> --></font> Account Balance</h1> 
		<div class="usercenter_box">
		  <div class="user_center_right">
            <ul>
	          <li class="center"><img src="${resDomain}/en/res/images/suc.gif" /> <font style="font-size: 14px; font-weight: bold;">You have to cancel the order ${charge.rnum} payment transactions!</font></li>
			</ul>
			<ul>
			  <li class="center">
			  	<input class="user_center_bt03" type="button" value="Re-pay" onclick="javascript:location.href='/web/recharge!toPay.action?rnum=${charge.rnum}&paymethod=${charge.rtype}'" />
			    <input class="user_center_bt03" type="button" value="Back" onclick="javascript:location.href='/web/recharge!list.action'" />
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
<%@include file="/web/en/common/footer.jsp" %>
</body>
</html>