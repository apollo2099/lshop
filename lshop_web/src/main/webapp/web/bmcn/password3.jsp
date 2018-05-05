<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib prefix="app" uri="/WEB-INF/tld/gv-app.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>找回密码_banana商城</title>
		<!-- 加载公共JS -->
		<%@include file="/web/bmcn/common/top.jsp"%>
		<link href="${resDomain}/bmcn/res/css/user_center.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${resDomain}/bmcn/res/js/storeIndex.js"></script>
		
	</head>	
<%String flag = request.getParameter("flag");
String url = "";
if(null != flag && !flag.equals("")){url = "/web/" + flag + "/password.jsp";}
%>		
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/bmcn/common/header.jsp"%>
		
		<div  class="xlhk_main">
			<h3>找回密码</h3>
			<div class="password_bz4"></div>
			<ul>
			  <li>
			    <p class="wd4">
				  <span>密码修改成功，请使用新密码登录</span>
				</p>
			  </li>
	          <li><p class="wd4">
	          	<input name="" type="button" 
	          	onclick="javascript:window.location.href='<%=url%>';" value="返 回"  class="user_center_bt02" /></p></li>
			
			<div class="cb"></div>
			</ul>
		</div>
		
		<!-- footer -->
		<%@include file="/web/bmcn/common/footer.jsp" %>
	
	</body>
</html> 
