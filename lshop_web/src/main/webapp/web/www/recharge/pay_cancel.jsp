<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用戶中心_TVpad商城</title>
<link href="${resDomain}/www/res/css/user_center.css" rel="stylesheet" type="text/css" />
<!-- 加载公共JS -->
<%@include file="/web/www/common/top.jsp"%>
</head>
<body>
<!-- 获取商城头部文件 -->
<%@include file="/web/www/common/header.jsp"%>
<div class="content_main">
  <!-- left_frame -->
  <%@include file="/web/www/user_center/leftFrame.jsp" %>
  <div class="right_frame">
	 	<h1><font class="bfont"><img src="${resDomain}/www/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">首頁</a> --><a href="/web/userCenterManage!getAccount.action"> 用戶中心</a> --><a href="/web/recharge!list.action"> 賬戶餘額</a> --><a>充值失敗</a></font> </h1> 
		<div class="usercenter_box">
		  <div class="user_center_right">
            <ul>
	          <li class="center"><img src="${resDomain}/www/res/images/suc.gif" /> <font style="font-size: 14px; font-weight: bold;">您已取消订单 ${charge.rnum} 的支付交易！</font></li>
			</ul>
			<ul>
			  <li class="center">
			  	<input class="user_center_bt03" type="button" value="重新支付" onclick="javascript:location.href='/web/recharge!toPay.action?rnum=${charge.rnum}&paymethod=${charge.rtype}'" />
			    <input class="user_center_bt03" type="button" value="返 回" onclick="javascript:location.href='/web/recharge!list.action'" />
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
<%@include file="/web/www/common/footer.jsp" %>
</body>
</html>