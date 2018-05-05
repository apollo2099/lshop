<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>User Center _TVpad Mall</title>
		<link href="${resDomain}/en/res/css/user_center.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<%@include file="/web/en/common/top.jsp" %>
		<script type="text/javascript">
			$().ready(function() {
				var users=lshop.getCookieToJSON('user');
				var str = "<a href='mailto:"+users.email+"'>"+users.email+"</a>";
				$("#accountEmail").html(str);
			});
		</script>
	</head>
	
	<body>	
		<!-- 获取商城头部文件 -->
		<%@include file="/web/en/common/header.jsp" %>
		<div class="content_main">
		
			<!-- left_frame -->
			<%@include file="/web/en/user_center/leftFrame.jsp" %>
			 
			 <div class="right_frame">
			 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/en/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">Home</a>--><a href="/web/userCenterManage!getAccount.action">User Center </a>--></font> Submit Western Union Info</span></h1> 
		  	 	<div class="usercenter_box">
			  		<div class="western_success">
			    		<ul>
				  			<li class="pp"><img src="${resDomain}/en/res/images/suc.gif" /></li>
						  	<li class="wz">
						  		Your Western Union information has been uploaded successfully.<br />
								Order No.：<a href="/web/userOrder!viewOrderInfo.action?oid=${param.oid }">${param.oid }</a> <br />
								Thank you for your order! We will confirm your payment in 1-2 working days and arrange delivery in 24 hours. Please check your order status at：<font id="accountEmail"></font>
							</li>
						</ul>
						<ul class="btn"><input type="button" onclick="javascript:location.href='/web/userOrder!getOrderlist.action';" value="Back" class="user_center_bt" /></ul>
			  		</div>
		   		 </div>
		    
			</div>	
		</div>	
		
		<!-- 底部广告位-->
		<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
		
		<!-- footer-->
		<%@include file="/web/en/common/footer.jsp" %>
		
	</body>
</html> 
