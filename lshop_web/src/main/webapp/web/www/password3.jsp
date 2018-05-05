<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib prefix="app" uri="/WEB-INF/tld/gv-app.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>找回密碼_TVpad商城</title>
		<!-- 加载公共JS -->
		<%@include file="/web/www/common/top.jsp"%>
		<link href="${resDomain}/www/res/css/user_center.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${resDomain}/www/res/js/storeIndex.js"></script>
		
	</head>	
<%String flag = request.getParameter("flag");
String url = "";
if(null != flag && !flag.equals("")){url = "/web/" + flag + "/password.jsp";}
%>		
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/www/common/header.jsp"%>
		
		<div  class="xlhk_main">
			<h3>找回密碼</h3>
			<div class="password_bz4"></div>
			<ul>
			  <li>
			    <p class="wd4">
				  <span>密碼修改成功，請使用新密碼登錄</span>
				</p>
			  </li>
	          <li><p class="wd4">
	          	<input name="" type="button" 
	          	onclick="javascript:window.location.href='<%=url%>';" value="返 回"  class="user_center_bt02" /></p></li>
			
			<div class="cb"></div>
			</ul>
		</div>
		
		<!-- footer -->
		<%@include file="/web/www/common/footer.jsp" %>
	
	</body>
</html> 
