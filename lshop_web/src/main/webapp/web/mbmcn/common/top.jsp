<%@ page language="java" pageEncoding="utf-8" import="java.util.Date"%>
<%long timeFlag = new Date().getTime(); %>
<link href="${resDomain}/mbmcn/res/css/style.css?time=<%=timeFlag %>" rel="stylesheet" type="text/css">
<link href="${resDomain}/mbmcn/res/css/development.css?time=<%=timeFlag %>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${resDomain}/mbmcn/res/js/jquery-1.4.4.min.js" ></script>
<script type="text/javascript" src="${resDomain}/mbmcn/res/js/header.js" ></script>
<script type="text/javascript" src="${resDomain}/mbmcn/res/js/hammer.js" ></script>
<script type="text/javascript" src="${resDomain}/mbmcn/res/js/FomrValidate.js" ></script>
<script type="text/javascript" src="https://rds.alipay.com/merchant/merchant.js"></script>
<script type="text/javascript">
$(function(){
	lshop.setCookie("jsReturnCookie",encodeURIComponent(window["alipay-merchant-result"]));
});
</script>


