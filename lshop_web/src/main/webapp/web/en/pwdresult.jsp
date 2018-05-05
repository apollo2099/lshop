<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib prefix="app" uri="/WEB-INF/tld/gv-app.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Forget Password-TVpad MALL</title>
		<!-- 加载公共JS -->
		<%@include file="/web/en/common/top.jsp" %>
		<link href="${resDomain}/en/res/css/user_center.css" rel="stylesheet" type="text/css" />
	</head>
	
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/en/common/header.jsp" %>
		<div  class="xlhk_main">
				<ul class="looking_pw">
				<li><span class="wd4">Your password has been sent to <a href="mailto:${param.email}" class="ljh">${param.email}</a>, please check your email and change your password.</span></li>
		        <li><span class="qrtj_1"><input onclick="javascript:location.href='/index.html'" type="button"  value="Back"  class="user_center_bt" style="CURSOR: pointer; "/></span></li>
				<div class="cb"></div>
				</ul>
		</div>
		
		<!-- footer -->
		<%@include file="/web/en/common/footer.jsp" %>

	</body>
</html> 
	