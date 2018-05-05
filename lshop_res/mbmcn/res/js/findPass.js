
$(function (){
	//检验找回密码表单
	checkFindPass();
	
	$("input[name='code']").blur(function(){
		checkCode($(this));
	});
});


//验证找回密码
function checkFindPass(){
	//邮箱
	$("input[name='lvAccount.email']").focus(function(){
		 if($(this).val()==$(this).attr("defalt")){
		   $(this).val("");	 
		 }
	}); 
	$("input[name='lvAccount.email']").blur(function(){
		var isEmail = checkEmail($(this));
		if(isEmail){
			checkIsExistEmail($("input[name='lvAccount.email']"));
		}
	});

}

//验证码
function checkCode(obj){
	if(obj.val()==""){
		info="请输入验证码"; 
		obj.next(".tip").children(".errInfo").html(info);
		obj.next(".tip").show();
		return false;
	}
	return true;
}

//提交找回密码表单
function submitFindPass(){
	var isEmail=checkEmail($("input[name='lvAccount.email']"));//验证邮箱
	var fcode=checkCode($("input[name='code']"));
	if(!fcode){
		return false;
	}
	var isEmailExist=false;
	if(isEmail){
		isEmailExist=checkIsExistEmail($("input[name='lvAccount.email']"));
	}
	
	if(isEmailExist){
		return true;
	}else{
		return false;
	}
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

function validatePwd(){
	return checkPwd($("input[name='lvAccount.pwd']"));
}

function validateTruePwd(){
	var pwd = $("#rpwd").val();
	return checkConfirmPwd($("input[name='newPwd']"),pwd);
}