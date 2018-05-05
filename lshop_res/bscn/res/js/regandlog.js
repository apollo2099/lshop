$(function() {
	//弹出框登陆
	$("#divlogin").validate({
		submitHandler:function(form){
			var currentUrl = window.location.href;
			if(submitDivLoginValidate()){
				$.post("/web/userCenterManage!login.action",$("#divlogin").serialize(),function(str){
					var data = eval('(' + str + ')');
					var f = data.mark;
					if(f==1){
						var gotourl = data.jumpurl;
						gotourl = ((currentUrl.indexOf('?jumpurl')>-1)||(currentUrl.indexOf('@jumpurl')>-1))?gotourl:"";
						var synurl = data.synurl;
						loadjscssfile(synurl,change2LoginStatu,gotourl);
						$("#divlogin").trigger('userLoginSuccess');//用户登陆成功事件
						return;
					}else if(f==-1){
						$("#msg").html("账号不存在");
					}else if(f==-2){
						$("#msg").html("账号已停用");
					}else if(f==-3){
						$("#msg").html("账号未激活");
					}else if(f==-4){
						$("#msg").html("密码错误");
					}else if(f==0){
						$("#msg").html("系统错误");
					}
					$("#erro_msg").css("display","block");
				});
			}
		}
	});
	
	//页面登陆
	$("#login").validate({
		submitHandler:function(form){
			var currentUrl = window.location.href;
			if(submitLoginValidate()){
				$.post("/web/userCenterManage!login.action",$("#login").serialize(),function(str){
					var data = eval('(' + str + ')');
					var f = data.mark;
					if(f==1){
						var gotourl = data.jumpurl;
						gotourl = ((currentUrl.indexOf('?jumpurl')>-1)||(currentUrl.indexOf('@jumpurl')>-1))?gotourl:"";
						var synurl = data.synurl;
						loadjscssfile(synurl,change2LoginStatu,gotourl);
						return;
					}else if(f==-1){
						$("#msg1").html("账号不存在");
					}else if(f==-2){
						$("#msg1").html("账号已停用");
					}else if(f==-3){
						$("#msg1").html("账号未激活");
					}else if(f==-4){
						$("#msg1").html("密码错误");
					}else if(f==-5){
						$("#msg1").html("验证码错误");
					}else if(f==0){
						$("#msg1").html("系统错误");
					}
					document.getElementById('rcId').src=document.getElementById('rcId').src+'?'+new Date();
				});
			}
		}
	});
	
	//页面注册
	$("#regedit").validate({
		submitHandler:function(form){
			var currentUrl = window.location.href;
			if(submitRegistValidate()){
				$.post("/web/regeditAccount.action",$("#regedit").serialize(),function(str){
					var data = eval('(' + str + ')');
					if(data.mark==0){//注册成功
						var gotourl = data.jumpurl;
						gotourl = ((currentUrl.indexOf('?jumpurl')>-1)||(currentUrl.indexOf('@jumpurl')>-1))?gotourl:"";
						var synurl = data.synurl;
						loadjscssfile(synurl,change2LoginStatu,gotourl);
						return;
					}else if(data.mark==2){
						$("#msgg").html("注册失败");
					}else if(data.mark==-2){
						$("#msgg").html("验证码错误");
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
		if(email=='请输入Email地址'){
			email='';
		}
		 if(email==''){
			 $("#email_load").css("display","none");
			 $("#email_tip2").text("请输入Email地址！");
			 $("#email_tip2").css("display","block");
			 $("#email").val('请输入Email地址');
			 return false;
		 }else{
			 if(checkEmail(email)==false){
				$("#email_load").css("display","none");
				$("#email_tip2").text("Email格式不正确！");
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
				  $("#email_tip2").text("该邮箱已存在！");
				  $("#email_tip2").css("display","block");
			   }else if(num==-1){
				   emailStatu = -1;
				   $("#email_tip2").text("Email格式不正确！");
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
			$("#pwd_tip2").text("请输入密码！");
			$("#pwd_tip2").css("display","block");
			return false;
		}else if(pwd.length<6 || pwd.length>16){
			$("#pwd_tip2").text("6-16位字符，可使用字母、数字或符号的组合，不建议使用纯字母、纯数字、纯符号！");
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
			$("#tpwd_tip2").text("请再次输入密码！");
			$("#tpwd_tip2").css("display","block");
			return false;
		}else if(tpwd != pwd){
			$("#tpwd_tip2").text("两次密码输入不一致！");
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
			$("#code_tip2").text("请输入验证码！");
			$("#code_tip2").css("display","block");
			return false;
		}else{
			return true;
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
					  $("#code_tip2").text("请输入验证码！");
					  $("#code_tip2").css("display","block");
					  return false;
				   }else if(num==-1){
					   codeStatu = -1;
					   $("#code_load").css("display","none");
					   $("#code_tip2").text("验证码错误！");
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
		$("#erro_msg0").css("display","none");
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
		if(name=='请输入Email地址'){
			name='';
		}
		if(name==''){
			$("#name_tip2").text("请输入账号！");
			$("#name_tip2").css("display","block");
			$("#name").val('请输入Email地址');
			return false;
		}
		return true;
	}
	
	function validateLogPwd(){
		$("#pwd_tip1").css("display","none");
		$("#pwd_tip2").css("display","none");
		var pwd = $("#log_pwd").val();
		if($.trim(pwd)==''){
			$("#pwd_tip2").text("请输入密码！");
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
			$("#code_tip2").text("请输入验证码！");
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
			$("#msg").text("请输入账号！");
			$("#erro_msg").css("display","block");
			return false;
		}
		if(pwd == ""){
			$("#msg").text("请输入密码！");
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
			$("#pwd_tip").text("请输入您的Email地址！");
			return false;
		}
		if(checkEmail(email)==false){
			$("#pwd_tip").text("Email格式不正确！");
			return false;
		}
		if(code == ""){
			$("#pwd_tip").text("请输入验证码！");
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
		if(!gotourl){
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
	
	function loadjscssfile(filename, onload, data) {
		$.getScript(filename,function(){
			onload(data);
		});
	}
	
	function userlogout(){
		$.ajax({   
			 url: '/web/userCenterManage!logout.action',
			 data:'',   
			 type: 'POST', 
			 async:false,
			 success: function(str){   
				 var data = eval('(' + str + ')');
				 var synurl = data.synurl;
				 if(synurl==''){
					 refreshPage();
				 }else{
					 loadjscssfile(synurl,refreshPage);
				 }
				 return;
			 }   
		});
	}
	
	function refreshPage(){
		window.location.href="http://www.bananatv.com/index.html";
	}