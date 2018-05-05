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
		<script type="text/javascript">
			$().ready(function() {
				var users=lshop.getCookieToJSON('user');
				$("#accountEmail").text(users.email);
			});
		</script>
		<style type="text/css">
		.right_frame .message{ width:767px; height:auto; overflow:hidden; margin-top:20px; border:solid 1px #e5e5e5;}
.right_frame .message ul.title{ width:747px; height:30px; line-height:30px;background:#eee; font-size:14px; font-weight:bold; padding:0 0 0 20px;}
.right_frame .message ul{clear:both; padding:20px; border-bottom:1px #e5e5e5 dashed; height:auto; overflow:hidden;}
.right_frame .message ul.btn{ text-align:center;}
.right_frame .message li.pp{ width:150px; float:left; text-align:right;}
.right_frame .message li.wz{ float:left;  width:557px; line-height:22px; text-align:left; padding-left:20px;}
		</style>
	</head>
	
	<body>	
		<!-- 获取商城头部文件 -->
		<%@include file="/web/bmen/common/header.jsp"%>
		
		<div class="content_main">
		
			<!-- left_frame -->
			<%@include file="/web/bmen/user_center/leftFrame.jsp" %>
		
		<div class="right_frame">
			 	<h1><font class="bfont"><img src="${resDomain}/bmen/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">Home</a> --><a href="/web/userCenterManage!getAccount.action"> User Center</a> --></font> Submit Western Union Info</span></h1> 
				<div class="message">
					<ul class="title">Submit order successfully</ul>
					<ul>
						<li class="pp"><img src="${resDomain}/bmen/res/images/suc.gif" /></li>
						 <li class="wz">Your Western Union information has been uploaded successfully.<br />
							Order No.：<a href="/web/userOrder!viewOrderInfo.action?oid=${param.oid }">${param.oid }</a> <br />
							Thank you for your order! We will confirm your payment in 1-2 working days and arrange delivery in 24 hours. Please check your order status at：<font id="accountEmail"></font>
						</li>
					</ul>	
		            <ul class="btn">
		              	<input type="button" class="btn05" onclick="javascript:location.href='/web/userOrder!getOrderlist.action';" value="Back" style="CURSOR: pointer; "/>
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
		<%@include file="/web/bmen/common/footer.jsp" %>
	
	</body>
</html> 
