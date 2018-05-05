$(function(){
	checkIsBind();
	
	  checkLogin();
	  checkRegister();
	  
	  $(".micor_commentbt").find("span").click(function(){
		  $(this).attr("id","curspan1");
		  $(this).siblings().removeAttr("id");
		  var ind=$(this).index();
		  if(ind==0){
			  $(".myconmmentsubdivbox").show();
		      $(".replayconmmentsubdivbox").hide();
			  }else{
			  $(".myconmmentsubdivbox").hide();
		      $(".replayconmmentsubdivbox").show();
				  }
		  });
  });

//验证登陆
function checkLogin(){
	//邮箱
	$("#l_uname").focus(function(){
		 if($(this).val()==$(this).attr("defalt")){
		   $(this).val("");	 
		 }
	}); 
	$("#l_uname").blur(function(){
		checkEmail($(this));
	});
	
	//密码
	$("#l_pwd").blur(function(){
	  	 checkPwd($(this));
	});
	$("#l_code").blur(function(){
		checkCode($(this));
	});
}
//登陆验证提交
function authloginformSubmit(){
	var isEmail=checkEmail($("#l_uname"));//验证邮箱
	var isPwd=checkPwd($("#l_pwd")); //验证密码
	var isCode=checkCode($("#l_code")); //验证密码
	if( isEmail && isPwd && isCode ){
		$("#loginbut").attr("disabled","disabled");
		$("#loginbut").addClass("user_reg_bt_disable");
		$("#login_msgg").html("");
		$.post(storeDomain+"/web/wxAuth!authLogin.action",$("#authloginform").serialize(),function(str){
			var data = eval('(' + str + ')');
			var f = data.mark;
			if(f==1){
				location.href=data.jumpurl;
				return;
			}else if(f==-1){
				$("#login_msgg").html("账号不存在");
			}else if(f==-2){
				$("#login_msgg").html("账号已停用");
			}else if(f==-3){
				$("#login_msgg").html("账号未激活");
			}else if(f==-4){
				$("#login_msgg").html("密码错误");
			}else if(f==-5){
				$("#login_msgg").html("验证码错误");
			}else if(f==-6){
				$("#login_msgg").html("该账号已经被绑定");
			}else if(f==0 || f==-9){
				$("#login_msgg").html("系统错误");
			}
			$("#l_code").val("");
			$("#loginbut").removeClass("user_reg_bt_disable");
			$("#loginbut").removeAttr("disabled");
			document.getElementById('l_rcId').src=document.getElementById('l_rcId').src+'?'+new Date();
		});
	}else{
		$("#loginbut").removeClass("user_reg_bt_disable");
		$("#loginbut").removeAttr("disabled");
	}
}
//验证注册
function checkRegister(){
	//邮箱
	$("#r_uname").focus(function(){
		 if($(this).val()==$(this).attr("defalt")){
		   $(this).val("");	 
		 }
	}); 
	$("#r_uname").blur(function(){
		var isEmail=checkEmail($(this));
		if(isEmail){
			checkEmailOne($("#r_uname"));
		}
	});
	//密码
	$("#r_pwd").blur(function(){
		checkPwdR($(this));
	});
	//确认密码
	$("#r_truePwd").blur(function(){
		checkConfirmPwd($(this),$("#r_pwd").val());
	});
	//验证码
	$("#r_code").blur(function(){
		checkCode($(this));
	});
}
//注册验证提交
function authregistformSubmit(){
	$("#registerbut").attr("disabled","disabled");
	$("#registerbut").addClass("user_reg_bt_disable");
	if(checkRegisterFormR()){
		$.post(storeDomain+"/web/wxAuth!authRegist.action",$("#authregistform").serialize(),function(str){
			var data = eval('(' + str + ')');
			if(data.mark==0){//注册成功
				location.href=data.jumpurl;
				return;
			}else if(data.mark==2){
				$("#reg_msgg").html("注册失败");
			}else if(data.mark==-2){
				$("#reg_msgg").html("验证码错误");
			}else if(data.mark==-9){
				$("#reg_msgg").html("系统错误");
			}
			$("#r_code").val("");
			$("#registerbut").removeClass("user_reg_bt_disable");
			$("#registerbut").removeAttr("disabled");
			document.getElementById('r_rcId').src=document.getElementById('r_rcId').src+'?'+new Date();
		});
	}else{
		$("#registerbut").removeClass("user_reg_bt_disable");
		$("#registerbut").removeAttr("disabled");
	}
}
function checkRegisterFormR(){
	var isEmail=checkEmail($("#r_uname"));//验证邮箱
	var isPwd=checkPwdR($("#r_pwd")); //验证密码
	var isConfirmpwd=checkConfirmPwd($("#r_truePwd"),$("#r_pwd").val());//验证确认密码
	var isCode=checkCode($("#r_code"));
	var isEmailOne=false;
	if(isEmail){
		isEmailOne=checkEmailOne($("#r_uname"));
	}
	if(isEmailOne&&isPwd&&isConfirmpwd&&isCode){
		return true;
	}
	return false;
}
//验证密码
function checkPwdR(pwd){
	var isPwd=false;
	var info = "";
	if(pwd.val()==""){
		info="请输入密码！"; 
		pwd.next(".tip").children(".errInfo").html(info);
		pwd.next(".tip").show();
	}else if(pwd.val().length>16){
			info = "密码长度不得超过16位字符！";
			pwd.next(".tip").children(".errInfo").html(info);
			pwd.next(".tip").show();
	}else if(pwd.val().length<6){
			info = "密码长度不得少于6位字符！";
			pwd.next(".tip").children(".errInfo").html(info);
			pwd.next(".tip").show();
	}else{
		pwd.next(".tip").hide();
		isPwd = true;
	}
	return isPwd;
}

//注册验证提交
function checkIsBind(){
	$.post(storeDomain+"/web/wxAuth!checkIsBind.action",'token='+$('#token').val(),function(str){
		var data = eval('(' + str + ')');
		if(data.mark==-6){
			location.href="/index.html";
		}
	});
}



