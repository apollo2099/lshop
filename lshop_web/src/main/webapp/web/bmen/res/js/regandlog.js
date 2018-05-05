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
			isCodeRight(code);
			if(codeStatu==1){
				 return true;
			 }else if(codeStatu==-1){			
				 return false;
			 }
		}
	}
	
	function isCodeRight(code){
		url = '/web/validatecode.action';
		$.ajax({   
			 url: url,
			 data:"code="+code,   
			 type: 'POST', 
			 async:false,
			 success: function(num){   
				  if(num==0){
					  codeStatu = -1;
					  $("#code_load").css("display","none");
					  $("#code_tip2").text("Please enter the Captcha");
					  $("#code_tip2").css("display","block");
					  return false;
				   }else if(num==-1){
					   codeStatu = -1;
					   $("#code_load").css("display","none");
					   $("#code_tip2").text("Verification code is incorrect");
					   $("#code_tip2").css("display","block");
					   $('#rcode').val("");
					   return false;
				   }else if(num==1){
					   codeStatu = 1;
					   $("#code_load").html("");
					   return true;
				   }
			 }   
		});
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
	