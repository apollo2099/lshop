<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>User Center_banana Mall</title>
<link href="${resDomain}/bmen/res/css/user_center.css" rel="stylesheet" type="text/css" />
<!-- 加载公共JS -->
<%@include file="/web/bmen/common/top.jsp"%>
<script type="text/javascript">
$(function(){
	var cardJson=lshop.getCookieToJSON("cardSuccess");

	$("#vbId").text(cardJson['amount']);
	$("#ordernumid").text(cardJson['orderno']);
	$("#accId").text(cardJson['otherAccount']);//帳號
	var userNoFlag=lshop.getCookie("userid");
	if(userNoFlag==""){
		$(".user_center_bt03").hide();
	}
});
</script>
</head>
<body>
<!-- 获取商城头部文件 -->
<%@include file="/web/bmen/common/header.jsp"%>
<div class="content_main">
  <!-- left_frame -->
  <%@include file="/web/bmen/user_center/leftFrame.jsp" %>
  <div class="right_frame">
    <h1><font class="bfont"><img src="${resDomain}/bmen/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">Home</a> --><a href="/web/userCenterManage!getAccount.action"> User Center</a> --></font> Account Balance</h1>
    <div class="usercenter_box">
      <div class="user_center_right">
        <ul>
          <li class="center"><img src="${resDomain}/bmen/res/images/suc.gif" /> <font class="suc">Succeeded!</font></li>
         
          <li>
            <p class="text1">Order No. ：</p>
            <p><font class="bluefont" id="ordernumid"></font> </p>
          </li>
           <li>
            <p class="text1">Account ：</p>
            <p><font class="bluefont" id="accId"></font> </p>
          </li>
          <li class="text1">
            <p class="text1">Recharge amount：</p>
            <p><font class="bluefont" id="vbId"></font> V</p>
          </li>
        </ul>
        <ul>
          <li class="center">
            <input class="user_center_bt" type="button" value="Continue" onclick="window.location.href='/web/recharge!list.action'" />
            <input class="user_center_bt03" type="button" value="Details" onclick="window.location.href='/web/recharge!tradeList.action?recordType=1'" />
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
<%@include file="/web/bmen/common/footer.jsp" %>
</body>
</html>
