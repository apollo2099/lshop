<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib prefix="app" uri="/WEB-INF/tld/gv-app.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Retrieve Password_banana Mall</title>
		<!-- 加载公共JS -->
		<%@include file="/web/bmen/common/top.jsp"%>
		<link href="${resDomain}/bmen/res/css/user_center.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${resDomain}/bmen/res/js/storeIndex.js"></script>
		
	</head>	
<%String flag = request.getParameter("flag");
String url = "";
if(null != flag && !flag.equals("")){url = "/web/" + flag + "/password.jsp";}
%>		
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/bmen/common/header.jsp"%>
		
		<div  class="xlhk_main">
			<h3>Retrieve Password</h3>
			<div class="password_bz2"></div>
				<ul>
				  <li>
				    <p class="wd4">
					  <span><font class="fontwz redfont">Your application has been submitted; please log in your email to change your password within 24 hours!</font></span>
					  <span>(The mail may sometimes be considered as spam. If you do not receive the mail, please click “Back” to submit your application again)</span>
					</p>
				  </li>
		          <li><p class="wd4"><input name="" 
		          type="submit" onclick="javascript:window.location.href='<%=url%>';" value="Back"  class="user_center_bt02" /></p></li>
				</ul>
		
		
		</div>
		
		<!-- footer -->
		<%@include file="/web/bmen/common/footer.jsp" %>
	
	</body>
</html> 
