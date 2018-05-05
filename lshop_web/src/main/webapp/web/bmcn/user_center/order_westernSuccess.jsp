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
		<script type="text/javascript">
			$().ready(function() {
				var users=lshop.getCookieToJSON('user');
				$("#accountEmail").text(users.email);
			});
		</script>
	</head>
	
	<body>	
		<!-- 获取商城头部文件 -->
		<%@include file="/web/bmcn/common/header.jsp"%>
		
		<div class="content_main">
		
			<!-- left_frame -->
			<%@include file="/web/bmcn/user_center/leftFrame.jsp" %>
		
			<div class="right_frame">
			 	<h1><font class="bfont"><img src="${resDomain}/bmcn/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">首页</a> --><a href="/web/userCenterManage!getAccount.action"> 用户中心</a> --></font> 提交西联汇款成功</h1>
				<div class="message">
					<ul class="title">订单提交成功</ul>	
					<ul>
						<li class="pp"><img src="${resDomain}/bmcn/res/images/suc.gif" /></li>
						 <li class="wz">
						 	您已成功上传西联信息<br />
							订单编号：<a href="/web/userOrder!viewOrderInfo.action?oid=${param.oid }">${param.oid }</a> <br />
							感谢您的订购，我们会在1-2个工作日内进行款项确认，到账之后24小时之内为您发货。<br />
							通过邮件通知您的订单状态，请您及时关注您下单时提供的邮箱：<font class="blue2"><font id="accountEmail"></font></font>
						</li>
					</ul>	
		            <ul class="btn">
		              	<input type="button" class="btn05" onclick="javascript:location.href='/web/userOrder!getOrderlist.action';" value="返  回" style="CURSOR: pointer; "/>
		            </ul>
		  		</div> 
		 	</div>
		  	<!--End right_Frame-->
		  	<div class="cb"></div>
		</div>
		<!--End content-->

		<!-- 底部广告位-->
		<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
		
		<!-- footer-->
		<%@include file="/web/bmcn/common/footer.jsp" %>
	
	</body>
</html> 
