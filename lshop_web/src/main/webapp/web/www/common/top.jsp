<%@ page language="java" pageEncoding="utf-8"%>
<link type="text/css" href="${resDomain}/www/res/css/css.css" rel="stylesheet"  />
<%-- <link href="${resDomain}/www/res/css/user_center.css" rel="stylesheet" type="text/css" />用户中心该自己引入该样式 --%>

<link type="text/css" href="${resDomain}/www/res/css/development.css" rel="stylesheet"  /> 
<script type="text/javascript" src="${resDomain}/www/res/js/jquery-1.4.4.min.js" ></script>
<script type="text/javascript" src="${resDomain}/www/res/js/FomrValidate.js" ></script>
<script type="text/javascript" src="${resDomain}/www/res/js/jquery.validate.min.1.8.1.js" ></script>
<script type="text/javascript" src="${resDomain}/www/res/js/jquery_validate_extend.js" ></script>
<script type="text/javascript" src="${resDomain}/www/res/js/jquery.form.js" ></script>
<script type="text/javascript" src="${resDomain}/www/res/js/cookie.js"></script>
<script type="text/javascript" src="${resDomain}/www/res/js/slides.jquery.js"></script>
<script type="text/javascript" src="${resDomain}/www/res/js/page/noLoginInfo.js"></script>
<script type="text/javascript" src="${resDomain}/www/res/js/header.js"></script>
<script type="text/javascript" src="${resDomain}/www/res/js/myjs.js"></script>
<script type="text/javascript" src="${resDomain}/www/res/js/bi.js"></script>
<script type="text/javascript" src="https://rds.alipay.com/merchant/merchant.js"></script>
<script type="text/javascript">
$(function(){
	lshop.setCookie("jsReturnCookie",encodeURIComponent(window["alipay-merchant-result"]));
});
</script>