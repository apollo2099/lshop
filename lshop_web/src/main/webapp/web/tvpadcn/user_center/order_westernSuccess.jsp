<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用戶中心_華揚商城</title>
<%@include file="/web/tvpadcn/common/header.jsp" %>
<!-- top -->
<%@include file="/web/tvpadcn/common/top.jsp" %>
<link href="${resDomain}/tvpadcn/res/css/main.css" rel="stylesheet" type="text/css" />
<link href="${resDomain}/tvpadcn/res/css/user_center.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
$().ready(function() {
	var users=lshop.getCookieToJSON('user');
	$("#accountEmail").text(users.email);
});

</script>
</head>
<body>
	
<div class="content_main">

	<!-- left_frame -->
	<%@include file="/web/tvpadcn/user_center/leftFrame.jsp" %>
	 
	 <div class="right_frame">
	 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/tvpadcn/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">首頁</a>--><a href="/web/userCenterManage!getAccount.action">用戶中心</a>--></font> 提交西联汇款成功</span></h1> 
		<div class="usercenter_box">
			<div class="buy_order01">
				<ul class="title">訂單提交成功</ul>	
					<img src="${resDomain}/tvpadcn/res/images/suc.gif" />您已成功上傳西聯信息
					<p>訂單編號：<a href="/web/userOrder!viewOrderInfo.action?oid=${oid }">${oid }</a> 
					<p>感謝您的訂購，我們會在1-2個工作日內進行款項確認，到賬之後24小時之內為您發貨</p>
					<p>通過郵件通知您的訂單狀態，請您及時關注您下單是提供的郵箱：<font id="accountEmail"></font></p>
			</div>	
			<a href="/web/userOrder!getOrderlist.action"><img src="${resDomain}/tvpadcn/res/images/user_center_btn04.gif" width="101" height="34" border="0" /></a>												
		</div>
	</div>	
</div>	
<!-- footer-->
<%@include file="/web/tvpadcn/common/footer.jsp" %>

</body>
</html> 
