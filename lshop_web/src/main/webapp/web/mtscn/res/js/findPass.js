
$(function (){
	//检验找回密码表单
	checkFindPass();
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

//提交找回密码表单
function submitFindPass(){
	var isEmail=checkEmail($("input[name='lvAccount.email']"));//验证邮箱
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

