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
<script type="text/javascript">
$(function(){
	$("#findPassForm").validate({
		 rules: {
			'lvAccount.email': {
				required: true,
		   		email: true
		   	}
		},
		messages: {
			'lvAccount.email': {
				required: "<font color='red'>Please enter your E-mail address!</font>",
				email: "<font color='red'>Invalid E-mail format!</font>"
			}
		},
		submitHandler:function(form){
			if(pwdFlag==true){
				form.submit();
	   		 }
   		 }

	});
});

var pwdFlag=false;
function checkIsExistEmail(){
	var email=$('#emailId');
	var eInfo=$('#emailInfo');
	if($.trim(email.val())!='' && checkEmail(email.val())==true){
	 	$.ajax({   
		 url: '/web/userCenterManage!isExistsUser.action',
		 data:"lvAccount.email="+$.trim(email.val()),   
		 type: 'POST',
		 async: false,  
		 success: function(num){
		  if(num==1){
			   pwdFlag=true;
			   $("#emailInfo").html("");
		   }else if(num==0){
			    pwdFlag=false;
			    $("#emailInfo").html("<font color='red'>该email不存在!</font>");
		  }
		 }   
		});
	}else{
		$("#emailInfo").html("");
	}
}

</script>
</head>
<body>
<!-- top -->
<%@include file="/web/tvpaden/common/top.jsp" %>

<div  class="xlhk_main">
		<ul>
		<form name="myForm" action="/web/userCenterManage!password.action"  method="post" id="findPassForm">
			<li><span class="wd3"><b class="bt">Reset Password </b></span></li>
			<font color="red">${findPassFormMsg}</font>
			<li><span class="wd3"><font class="redfont">*</font>Please enter your E-mail：</span><input name="lvAccount.email" id="emailId" type="text" class="input2"  onblur="checkIsExistEmail()"  onkeyup="checkIsExistEmail()"/><span id="emailInfo"></span></li>
	        <li><span class="qrtj_1"><input name="" type="submit" value="Submit"  class="qrtj" style="CURSOR: pointer; "/><input onclick="javascript:location.href='/index.html'" type="button"  value="Back"  class="qrtj" style="CURSOR: pointer; "/></span></li>
			<div class="cb"></div>
		</form>
		</ul>
</div>

<!-- footer-->
<%@include file="/web/tvpaden/common/footer.jsp" %>

</body>
</html> 
