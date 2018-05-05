<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib prefix="app" uri="/WEB-INF/tld/gv-app.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Forget Password-HUA YANG MALL</title>
<%@include file="/web/tvpaden/common/header.jsp" %>
<link href="${resDomain}/tvpaden/res/css/main.css" rel="stylesheet" type="text/css" />
<link href="${resDomain}/tvpaden/res/css/layout.css" rel="stylesheet" type="text/css" />
</head>
<body>
<!-- top -->
<%@include file="/web/tvpaden/common/top.jsp" %>

<div  class="xlhk_main">
		<ul>
		<li><span class="wd4">Your passwork has been sent to you E-mail ${param.email}, please check your E-mail and modify your password.</span></li>
        <li><span class="qrtj_1"><input onclick="javascript:location.href='/web/tvpaden/index.jsp'" type="button" value="Back"  class="qrtj" style="CURSOR: pointer; "/></span></li>
		<div class="cb"></div>
		</ul>
</div>

<!-- footer-->
<%@include file="/web/tvpaden/common/footer.jsp" %>

</body>
</html> 
	