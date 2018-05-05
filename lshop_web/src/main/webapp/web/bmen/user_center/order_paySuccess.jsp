<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>User Center_banana Mall</title>
		<link href="${resDomain}/bmen/res/css/user_center.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<%@include file="/web/bmen/common/top.jsp"%>
	</head>
	
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/bmen/common/header.jsp"%>
			
		<div class="content_main">
		
			<!-- left_frame -->
			<%@include file="/web/bmen/user_center/leftFrame.jsp" %>
			 
			 <div class="right_frame">
			 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/bmen/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">Home</a> --><a href="/web/userCenterManage!getAccount.action"> User Center</a> --></font> Pay Success</span></h1> 
				<div class="usercenter_box">
					<div class="buy_order01">
						<ul class="title">The order is successfully submitted</ul>	
							<li class="pp"><img src="${resDomain}/bmen/res/images/suc.gif" /></li>
							<p>Your order has been successfully submitted, we'll arrange a delivery as soon<br /> 
						    as possible, please keep your phone open</p>
							<p>Order No.：<a href="/web/userOrder!viewOrderInfo.action?oid=${oid }">${oid }</a> </p>
							<p> After finishing payment, you can check the order status in <a href="/web/userCenterManage!getAccount.action"><font class="bluewz">User Center </font></a>--<a href="/web/userOrder!getOrderlist.action"><font class="bluewz"> My Order</font></a>  Check Order Status</p>
					</div>	
					<a href="/web/userOrder!getOrderlist.action"><img src="${resDomain}/bmen/res/images/user_center_btn04.gif" width="101" height="34" border="0" /></a>												
				</div>
			</div>	
		</div>	
		
		<!-- 底部广告位-->
		<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
		
		<!-- footer-->
		<%@include file="/web/bmen/common/footer.jsp" %>
	
	</body>
</html> 
