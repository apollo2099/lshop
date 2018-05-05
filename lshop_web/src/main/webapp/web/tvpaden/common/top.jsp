<%@ page language="java" pageEncoding="UTF-8"%>

<link href="${resDomain}/tvpaden/res/css/main.css" rel="stylesheet" type="text/css" />
<link href="${resDomain}/tvpaden/res/css/layout.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${resDomain}/tvpaden/res/js/jquery-1.4.4.min.js" ></script>
<script type="text/javascript" src="${resDomain}/tvpaden/res/js/FomrValidate.js" ></script>
<script type="text/javascript" src="${resDomain}/tvpaden/res/js/jquery.validate.min.1.8.1.js" ></script>
<script type="text/javascript" src="${resDomain}/tvpaden/res/js/jquery_validate_extend.js" ></script>
<script type="text/javascript" src="${resDomain}/tvpaden/res/js/jquery.form.js" ></script>
<script type="text/javascript" src="${resDomain}/tvpaden/res/js/cookie.js"></script>
<link rel="stylesheet" type="text/css" href="${resDomain}/tvpaden/res/js/ymPrompt/skin/vista/ymPrompt.css" />
<script type="text/javascript" src="${resDomain}/tvpaden/res/js/ymPrompt/ymPrompt.js" ></script>
<script type="text/javascript" src="${resDomain}/en/res/js/page/noLoginInfo.js"></script>
<script type="text/javascript" src="${resDomain}/tvpaden/res/js/header.js" ></script>
<script type="text/javascript" src="${resDomain}/en/res/js/myjs.js"></script>
<script type="text/javascript" src="${resDomain}/www/res/js/bi.js"></script>
<script type="text/javascript">
	$(function(){
		 if(${empty searchContent}){
			$("#searchContent").val("Please enter your search here…");
		 }
	});
</script>