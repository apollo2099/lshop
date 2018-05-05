<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>用戶中心_TVpad商城</title>
		<link href="${resDomain}/www/res/css/user_center.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<%@include file="/web/www/common/top.jsp"%>
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
		<%@include file="/web/www/common/header.jsp"%>
		
		<div class="content_main">
		
			<!-- left_frame -->
			<%@include file="/web/www/user_center/leftFrame.jsp" %>
			 
			 <div class="right_frame">
			 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/www/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">首頁</a>--><a href="/web/userCenterManage!getAccount.action">用戶中心</a>--><a>提交西联汇款成功</a></font> </span></h1> 
				<div class="usercenter_box">
			  		<div class="western_success">
			    		<ul>
				  			<li class="pp"><img src="${resDomain}/www/res/images/suc.gif" /></li>
						  	<li class="wz">
						  		您已成功上傳西聯信息<br />
								訂單編號：<a href="/web/userOrder!viewOrderInfo.action?oid=${param.oid }">${param.oid }</a> <br />
								感謝您的訂購，我們會在1-2個工作日內進行款項確認，到賬之後24小時之內為您發貨<br/>
								通過郵件通知您的訂單狀態，請您及時關注您下單是提供的郵箱：<font id="accountEmail"></font>
							</li>
						</ul>
						<ul class="btn"><input type="button" onclick="javascript:location.href='/web/userOrder!getOrderlist.action';" value="返回" class="user_center_bt" /></ul>
			  		</div>
		   		 </div>
			</div>	
		</div>	
		
		<!-- 底部广告位-->
		<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
		
		<!-- footer-->
		<%@include file="/web/www/common/footer.jsp" %>
	
	</body>
</html> 
