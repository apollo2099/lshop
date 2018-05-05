$(function() {
	//弹出框登陆
	$("#divlogin").validate({
		submitHandler:function(form){
			var currentUrl = window.location.href;
			if(submitDivLoginValidate()){
				var gotourl = window.location.href;
				$.post("/web/userCenterManage!login.action",$("#divlogin").serialize(),function(str){
					var data = eval('(' + str + ')');
					var f = data.mark;
					if(f==1){
						var gotourl = data.jumpurl;
						gotourl = ((currentUrl.indexOf('?jumpurl')>-1)||(currentUrl.indexOf('@jumpurl')>-1))?gotourl:"";
						change2LoginStatu(gotourl);
						return;
					}else if(f==-1){
						$("#msg").html("Account does not exist");
					}else if(f==-2){
						$("#msg").html("Account has been disabled");
					}else if(f==-3){
						$("#msg").html("Account is not activated");
					}else if(f==-4){
						$("#msg").html("Incorrect password");
					}else if(f==0){
						$("#msg").html("System erro");
					}
					$("#erro_msg").css("display","block");
				});
			}
		}
	});
	
	//页面登陆
	$("#login_en").validate({
		submitHandler:function(form){
			if(submitLoginValidate()){
				$.post("/web/userCenterManage!login.action",$("#login_en").serialize(),function(str){
					var data = eval('(' + str + ')');
					var f = data.mark;
					if(f==1){
						var gotourl = data.jumpurl;
						change2LoginStatu(gotourl);
						return;
					}else if(f==-1){
						$("#msg1").html("Account does not exist");
					}else if(f==-2){
						$("#msg1").html("Account has been disabled");
					}else if(f==-3){
						$("#msg1").html("Account is not activated");
					}else if(f==-4){
						$("#msg1").html("Incorrect password");
					}else if(f==-5){
						$("#msg1").html("Verification code is incorrect");
					}else if(f==0){
						$("#msg1").html("System erro");
					}
					document.getElementById('rcId').src=document.getElementById('rcId').src+'?'+new Date();
				});
			}
		}
	});
	
	//页面注册
	$("#regedit_en").validate({
		submitHandler:function(form){
			if(submitRegistValidate()){
				$.post("/web/regeditAccount.action",$("#regedit_en").serialize(),function(str){
					var data = eval('(' + str + ')');
					if(data.mark==0){//注册成功
						var gotourl = data.jumpurl;
						change2LoginStatu(gotourl);
						return;
					}else if(data.mark==2){
						$("#msgg").html("User registration failed");
					}else if(data.mark==-2){
						$("#msgg").html("Verification code is incorrect");
					}
					$("#erro_msg0").css("display","block");
					$("input[name='code']").val("");
					$("#registerbut").removeClass("user_reg_bt_disable");
					document.getElementById('rcId').src=document.getElementById('rcId').src+'?'+new Date();
				});
			}
		}
	});
});
/**
 * 注册和登陆验证add by dingh
 */	
var emailF = false;
var pwdF = false;
var tpwdF = false;
var codeF = false;
var emailStatu = 0;
var codeStatu = 0;

	function validateEmail(){
		$("#email_tip1").css("display","none");
		$("#email_tip2").css("display","none");
		$("#email_load").css("display","block");
		var email=$('#email').val();
		email = $.trim(email);
		if(email=='your email address'){
			email='';
		}
		 if(email==''){
			 $("#email_load").css("display","none");
			 $("#email_tip2").text("Please enter your E-mail address");
			 $("#email_tip2").css("display","block");
			 $("#email").val('your email address');
			 return false;
		 }else{
			 if(checkEmail(email)==false){
				$("#email_load").css("display","none");
				$("#email_tip2").text("Invalid E-mail format");
				$("#email_tip2").css("display","block");
				return false;
			 }else{
				 emailIsRegist(email);
				 if(emailStatu==1){
					 return true;
				 }else if(emailStatu==-1){			
					 return false;
				 }
			}
		 }
	}
	
	function emailIsRegist(email){
		$.ajax({   
			 url: '/web/userCenterManage!isExistsUser.action',
			 data:"lvAccount.email="+email,   
			 type: 'POST', 
			 async:false,
			 success: function(num){   
			  if(num==1){
				  emailStatu = -1;
				  $("#email_load").css("display","none");
				  $("#email_tip2").text("This E-mail address is already existed");
				  $("#email_tip2").css("display","block");
			   }else if(num==-1){
				   emailStatu = -1;
				   $("#email_tip2").text("Invalid E-mail format");
				   $("#email_tip2").css("display","block");
			   }else if(num==0){
				   emailStatu = 1;
				   $("#email_load").css("display","none");
			   }
			 }   
		});
	}
	
	function validatePwd(){
		$("#pwd_tip1").css("display","none");
		$("#pwd_tip2").css("display","none");
		var pwd=$('#rpwd').val();
		if($.trim(pwd)==''){
			$("#pwd_tip2").text("Please enter your password");
			$("#pwd_tip2").css("display","block");
			return false;
		}else if(pwd.length<6 || pwd.length>16){
			$("#pwd_tip2").text("6-16 characters. Combination of letters, numbers or symbols is highly recommended");
			$("#pwd_tip2").css("display","block");
			return false;
		}else{
			return true;
		}	
	}
	
	function validateTruePwd(){
		$("#tpwd_tip1").css("display","none");
		$("#tpwd_tip2").css("display","none");
		var pwd=$('#rpwd').val();
		var tpwd=$('#truePwd').val();
		if($.trim(tpwd)==''){
			$("#tpwd_tip2").text("Please re-enter your password");
			$("#tpwd_tip2").css("display","block");
			return false;
		}else if(tpwd != pwd){
			$("#tpwd_tip2").text("The two passwords that you entered do not match");
			$("#tpwd_tip2").css("display","block");
			return false;
		}else{
			return true;
		}	
	}
	
	function validateCode(){
		$("#code_tip1").css("display","none");
		$("#code_tip2").css("display","none");
		$("#code_load").css("display","block");
		var code=$('#rcode').val();
		code = $.trim(code);
		if(code==''){
			$("#code_load").css("display","none");
			$("#code_tip2").text("Please enter the Captcha");
			$("#code_tip2").css("display","block");
			return false;
		}else{
			return true;
		}
	}
	
	function submitRegistValidate(){
		emailF = validateEmail();
		if (!emailF) {
			return false;
		}
		pwdF = validatePwd();
		if (!pwdF) {
			return false;
		}
		tpwdF = validateTruePwd();
		if (!tpwdF) {
			return false;
		}
		codeF = validateCode();
		if (!codeF) {
			return false;
		}
		if(emailF && pwdF && tpwdF && codeF){
			var disableclass="user_reg_bt_disable";
			var classstr=$("#registerbut").attr('class');
			if(classstr.indexOf(disableclass)<0){
				$("#registerbut").addClass(disableclass);
				return true;
			}else{
				return false;
			}
		}
		return false;
	}
	
	function submitLoginValidate(){
		$("#msg1").html("");
		if(validateLogAccount() && validateLogPwd() && validateLogCode()){
			return true;
		}
		return false;
	}
	
	function validateLogAccount(){
		$("#name_tip1").css("display","none");
		$("#name_tip2").css("display","none");
		var name = $("#name").val();
		name = $.trim(name);
		if(name=='your email address'){
			name='';
		}
		if(name==''){
			$("#name_tip2").text("Please enter your E-mail address");
			$("#name_tip2").css("display","block");
			$("#name").val('your email address');
			return false;
		}
		return true;
	}
	
	function validateLogPwd(){
		$("#pwd_tip1").css("display","none");
		$("#pwd_tip2").css("display","none");
		var pwd = $("#log_pwd").val();
		if($.trim(pwd)==''){
			$("#pwd_tip2").text("Please enter your password");
			$("#pwd_tip2").css("display","block");
			return false;
		}
		return true;
	}
	
	function validateLogCode(){
		$("#code_tip1").css("display","none");
		$("#code_tip2").css("display","none");
		var code = $("#rcode").val();
		if($.trim(code)==''){
			$("#code_tip2").text("Please enter the Captcha");
			$("#code_tip2").css("display","block");
			return false;
		}
		return true;
		
	}
	
	function submitDivLoginValidate(){
		var uname = $("#uname").val();
		var pwd = $("#pwd").val();
		uname = $.trim(uname);
		pwd = $.trim(pwd);
		if(uname == ""){
			$("#msg").text("Please enter your E-mail address");
			$("#erro_msg").css("display","block");
			return false;
		}
		if(pwd == ""){
			$("#msg").text("Please enter your password");
			$("#erro_msg").css("display","block");
			return false;
		}
		var url = window.location.href;
		$("#jumpurl").val(url);
		return true;
	}
	
	function submitFindPwdValidate(){
		var email = $("#emailId").val();
		var code = $("#rcode").val();
		email = $.trim(email);
		code = $.trim(code);
		if(email == ""){
			$("#pwd_tip").text("Please enter your E-mail address");
			return false;
		}
		if(checkEmail(email)==false){
			$("#pwd_tip").text("Invalid E-mail format");
			return false;
		}
		if(code == ""){
			$("#pwd_tip").text("Please enter the Captcha");
			return false;
		}
		return true;
	}
	
	function submitResetPwdValidate(){
		pwdF = validatePwd();
		if (!pwdF) {
			return false;
		}
		tpwdF = validateTruePwd();
		if (!tpwdF) {
			return false;
		}
		
		if(pwdF && tpwdF){
			return true;
		}
		
		return false;
	}
	
	function change2LoginStatu(gotourl){
		if(gotourl==''){
			onhide('tx_b','loginDiv');
			var users=lshop.getCookieToJSON('user');
			var shopcart=lshop.getCookie('shopcartNum');
		    $('#nicknameId').text(users.nickname);
		    $("#loginId").hide();
	        $("#registerId").hide();
	 	    $("#nameId").show();
	        $("#logoutId").show();
	        $("#shopCartNum").html(shopcart);
		}else{
			window.location.href=gotourl;
		}
	}