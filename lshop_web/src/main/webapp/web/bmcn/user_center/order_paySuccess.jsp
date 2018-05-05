<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
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
			 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/bmcn/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">首页</a> --><a href="/web/userCenterManage!getAccount.action"> 用户中心</a> --></font> 提交西联汇款成功</span></h1> 
				<div class="usercenter_box">
					<div class="buy_order01">
						<ul class="title">订单提交成功</ul>	
							<li class="pp"><img src="${resDomain}/bmcn/res/images/suc.gif" /></li>
							<p>您的订单已提交成功，我们会尽快安排发货，请保持电话通畅</p>
							<p>订单编号：<a href="/web/userOrder!viewOrderInfo.action?oid=${oid }">${oid }</a> </p>
							<p>完成支付后，可以通过 <a href="/web/userCenterManage!getAccount.action"><font class="bluewz">用户中心</font></a>--<a href="/web/userOrder!getOrderlist.action"><font class="bluewz">我的订单</font></a> 查看订单状态</p>
					</div>	
					<a href="/web/userOrder!getOrderlist.action"><img src="${resDomain}/bmcn/res/images/user_center_btn04.gif" width="101" height="34" border="0" /></a>												
				</div>
			</div>	
		</div>	
		
		<!-- 底部广告位-->
		<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
		
		<!-- footer-->
		<%@include file="/web/bmcn/common/footer.jsp" %>
	
	</body>
</html> 
