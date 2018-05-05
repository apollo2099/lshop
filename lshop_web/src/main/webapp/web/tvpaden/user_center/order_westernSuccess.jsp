<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>User Center- HUA YANG MALL</title>
<%@include file="/web/tvpaden/common/header.jsp" %>
<link href="${resDomain}/tvpaden/res/css/main.css" rel="stylesheet" type="text/css" />
<link href="${resDomain}/tvpaden/res/css/user_center.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
$().ready(function() {
	var users=lshop.getCookieToJSON('user');
	$("#accountEmail").text(users.email);
});

</script>
</head>
<body>
<!-- top -->
<%@include file="/web/tvpaden/common/top.jsp" %>
	
<div class="content_main">

	<!-- left_frame -->
	<%@include file="/web/tvpaden/user_center/leftFrame.jsp" %>
	 
	 <div class="right_frame">
	 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/tvpaden/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">Home</a>--><a href="/web/userCenterManage!getAccount.action">User Center</a>--></font> 提交西联汇款成功</span></h1> 
		<div class="usercenter_box">
			<div class="buy_order01">
				<ul class="title">The order is successfully submitted</ul>	
					<img src="${resDomain}/tvpaden/res/images/suc.gif" />You have successfully uploaded Western Union information<br />
					<p>Order No.：<a href="/web/userOrder!viewOrderInfo.action?oid=${oid }">${oid }</a> </p>
					<p>Thank you，we'll confirm your remittance information within 1-2 business days and make</p>
					<p>a delivery within 24 hours after receiving your remittance. We will notice your order status</p>
					<p>by email, please timely check the email at<font id="accountEmail"></font> you provided while placing</p>
					<p>your order.</p>
			</div>	
			<a href="/web/userOrder!getOrderlist.action"><img src="${resDomain}/tvpaden/res/images/user_center_btn04.gif" width="101" height="34" border="0" /></a>												
		</div>
	</div>	
</div>	
<!-- footer-->
<%@include file="/web/tvpaden/common/footer.jsp" %>

</body>
</html> 
