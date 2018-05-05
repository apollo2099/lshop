<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib prefix="app" uri="/WEB-INF/tld/gv-app.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>找回密碼_華揚商城</title>
<%@include file="/web/tvpadcn/common/header.jsp" %>
<link href="${resDomain}/tvpadcn/res/css/main.css" rel="stylesheet" type="text/css" />
<link href="${resDomain}/tvpadcn/res/css/layout.css" rel="stylesheet" type="text/css" />
</head>
<body>
<!-- top -->
<%@include file="/web/tvpadcn/common/top.jsp" %>

<div  class="xlhk_main">
		<ul>
		<li><span class="wd4">您的密碼已成功發送到郵箱${param.email},請登錄您的郵件查看並修改密碼</span></li>
        <li><span class="qrtj_1"><input onclick="javascript:location.href='${storeDomain}/web/www/index!toIndex.action'" type="button" value="返 回"  class="qrtj" style="CURSOR: pointer; "/></span></li>
		<div class="cb"></div>
		</ul>
</div>

<!-- footer-->
<%@include file="/web/tvpadcn/common/footer.jsp" %>

</body>
</html> 
	