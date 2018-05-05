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
		<%@include file="/web/bmcn/common/top.jsp" %>
		<link href="${resDomain}/bmcn/res/css/user_center.css" rel="stylesheet" type="text/css" />
	</head>
	
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/bmcn/common/header.jsp"%>
		
		<div  class="xlhk_main">
			<ul class="looking_pw">
				<li><span class="wd4">您的密码已成功发送到邮箱${param.email}，请登录您的邮件查看并修改密码！</span></li>
		        <li><span class="qrtj_1"><input onclick="javascript:location.href='/index.html'" type="button" value="返 回"  class="user_center_bt" style="CURSOR: pointer; "/></span></li>
				<div class="cb"></div>
			</ul>
		</div>
		
		<!-- footer -->
		<%@include file="/web/bmcn/common/footer.jsp" %>

	</body>
</html> 
	