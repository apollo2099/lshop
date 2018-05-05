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
		<%@include file="/web/www/common/top.jsp" %>
		<link href="${resDomain}/www/res/css/user_center.css" rel="stylesheet" type="text/css" />
	</head>
	
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/www/common/header.jsp"%>
		
		<div  class="xlhk_main">
			<ul class="looking_pw">
				<li><span class="wd4">您的密碼已成功發送到郵箱${param.email},請登錄您的郵件查看並修改密碼</span></li>
		        <li><span class="qrtj_1"><input onclick="javascript:location.href='/index.html'" type="button" value="返 回"  class="user_center_bt" style="CURSOR: pointer; "/></span></li>
				<div class="cb"></div>
			</ul>
		</div>
		
		<!-- footer -->
		<%@include file="/web/www/common/footer.jsp" %>

	</body>
</html> 
	