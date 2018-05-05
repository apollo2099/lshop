<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>註冊_華揚商城</title>
<link href="css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.validate.min.1.8.1.js"></script>
<script type="text/javascript" src="js/jquery-1.4.4.min.js" ></script>
<script type="text/javascript" src="js/FomrValidate.js" ></script>


<script type="text/javascript">
var isuccA=true;
var isuccB=true;
$().ready(function() {

	var users=lshop.getCookieToJSON('user');
	if(users.nickname!=null&&users.nickname!=''){
		location.href="/web/userCenterManage!registerAccount.action";
	}
	
	$("#regedit").validate({
		
		 rules: {
		   'lvAccount.pwd': {
				required: true,
				minlength: 6,
                maxlength: 16
			},
			truePwd: {
				required: true,
				equalTo: "#rpwd"
			},
			code:"required"
		},
		messages: {
			'lvAccount.pwd': {
				required: "<font color='red'>密码不能空！</font>",
				minlength: "<font color='red'>密码不能少于6位字符！</font>",
				maxlength: "<font color='red'>密码不能大于16位字符！</font>"
			},
			truePwd: {
				required: "<font color='red'>确认密码不能空！</font>",
				equalTo: "<font color='red'>确认密码与密码不一致！</font>"
			},
			code:"<font color='red'>不能为空</font>"
		},
		
		submitHandler:function(form){
		var email=$('#email');
		var nickname=$('#nickname');
		var eInfo=$('#eInfo')
		var rpwd=$('#rpwd').val();
		var truePwd=$('#truePwd').val();
		if($.trim(email.val())==''){
			 eInfo.html("<font color='red'>email不能为空！</font>");
			 email.focus();
			 return ;
		 }
		 if(checkEmail(email.val())==false){
			 eInfo.html("<font color='red'>email格式不正确！</font>");
			 email.focus();
			 return ;
		 }
		 if($.trim(nickname.val())==''){
			 nInfo.html("<font color='red'>昵称不能为空！<font>");
			 nickname.focus();
			 return ;
		 }else{
			 var pat=new RegExp("[^a-zA-Z0-9\_\u4e00-\u9fa5]","i"); 
			 if(pat.test($.trim(nickname.val()))==true) 
			 { 
				 nInfo.html("<font color='red'>昵称不能使用非法字符！<font>");
			      return; 
			 }
		 }
		if(isuccA==true&&isuccB==true){
			form.submit();
		}
    }

	});
});

function checkEmailOne(){
	var email=$('#email');
	var eInfo=$('#eInfo')
	 if($.trim(email.val())==''){
		 eInfo.html("<font color='red'>email不能为空！</font>");
	 }else{
		 if(checkEmail(email.val())==false){
			 eInfo.html("<font color='red'>email格式不正确！</font>");
		 }else{
			 $.ajax({   
				 url: '/web/userCenterManage!isExistsUser.action',
				 data:"lvUser.email="+$.trim(email.val()),   
				 type: 'POST',     
				 success: function(num){   
				  if(num==1){
					   isuccA=false;
					  eInfo.html("<font color='red'>该邮箱已存在！</font>");
					 
				   }else if(num==0){
					    isuccA=true;
					    eInfo.html("");
					   }
				 }   
				 });
		}
	 }
}
function checkNickOne(){
	var nickname=$('#nickname');
	var nInfo=$('#nInfo');
	 if($.trim(nickname.val())==''){
		 nInfo.html("<font color='red'>昵称不能为空！<font>");
	 }else{
		 var pat=new RegExp("[^a-zA-Z0-9\_\u4e00-\u9fa5]","i"); 
		 if(pat.test($.trim(nickname.val()))==true) 
		 { 
			 nInfo.html("<font color='red'>昵称不能使用非法字符！<font>");
		      return false; 
		 }
			 $.ajax({   
				 url: '/web/userCenterManage!isExistsUser.action',
				 data:"lvAccount.nickname="+$.trim(nickname.val()),   
				 type: 'POST',     
				 success: function(num){   
				  if(num==1){
					   isuccB=false;
					  nInfo.html("<font color='red'>该昵称已存在！</font>");
				   }else if(num==0){
					    isuccB=true;
					    nInfo.html("");
					
					   }
				 }   
		    });
	 }
}

function onSub(){
	document.myform.submit();
}
</script>
</head>
<body>
<div class="pop_up_box">
	<span class="box_l">用戶註冊<span class="box_r"><a href="#"><img src="${resDomain}/tvpadcn/res/images/close.gif" border="0" /></a></span></span>
	<font color="red">${requestScope.rmsg}</font>
	<form action="/web/regeditAccount.action" id="regedit" method="post">
		 <input type="hidden" name="jumpurl" value="${param.jumpurl}"/>
			<ul>
					<li><span class="wd1"><font class="redfont">*</font>用戶名：</span><input name="lvAccount.nickname" id="nickname" onblur="checkNickOne()" value="${lvUser.nickname}" class="input2" type="text" /><span id="nInfo"></span></li>
					<li><span class="wd1"><font class="redfont">*</font>密碼：</span><input name="lvAccount.pwd" class="input2" id="rpwd" type="password" /></li>	
					<li><span class="wd1"><font class="redfont">*</font>確認密碼：</span><input name="truePwd" class="input2" id="truePwd" type="password" /></li>	
					<li><span class="wd1"><font class="redfont">*</font>Email：</span><input name="lvAccount.email" id="email"  onblur="checkEmailOne()" value="${lvUser.email}" class="input2" type="text" /><span id="eInfo"></span></li>						
	  	 			<li>
	  	 				<span class="wd1"><font class="redfont">*</font>驗證碼：</span><input name="code" id="rcode" class="input2" type="text" />
  	      				<img src="imager.jsp" width="47" height="21" id="rcId" onclick="javascript:this.src=this.src+'?'+new Date()"/>
  	      				<a href="#" onclick="javascript:document.getElementById('rcId').src=document.getElementById('rcId').src+'?'+new Date()"> 換一張</a>
  	      			</li>
					<li><span class="wd2"><input type="image" src="${resDomain}/tvpadcn/res/images/admin_login01.gif"  border="0" onclick="onSub();"/></span></li>
  		  </ul>
</div>
</body>
</html>
