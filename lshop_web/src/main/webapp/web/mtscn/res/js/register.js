
$(function (){
	//检验注册表单
	checkRegister();
	//显示密码
	$("input[name=lvAccount.pwd]").change(function(e){
		$("input[name=lvAccount.pwd]").val($(this).val());
	});
	$("input[name=truePwd]").change(function(e){
		$("input[name=truePwd]").val($(this).val());
	});
	//点击显示密码
	$("#checkpwd").click(function(){
		 var tt=$(this).is(":checked");  
		 if(tt){
		     $("input[name=lvAccount.pwd]").toggle();
			 $("input[name=truePwd]").toggle();
		  }else{
			   $("input[name=lvAccount.pwd]").toggle();
			   $("input[name=truePwd]").toggle();
		 }
	});
});


//验证注册
function checkRegister(){
	//邮箱
	$("input[name='lvAccount.email']").focus(function(){
		 if($(this).val()==$(this).attr("defalt")){
		   $(this).val("");	 
		 }
	}); 
	$("input[name='lvAccount.email']").blur(function(){
		var isEmail=checkEmail($(this));
		if(isEmail){
			checkEmailOne($("input[name='lvAccount.email']"));
		}
	});
	
	//密码
	$("input[name='lvAccount.pwd']").blur(function(){
	  	 checkPwd($(this));
	});
	
	//确认密码
	$("input[name='truePwd']").blur(function(){
		checkConfirmPwd($(this),$("input[name='lvAccount.pwd']").val());
	});
}

//提交注册表单
function submitRegister(){
	var isEmail=checkEmail($("input[name='lvAccount.email']"));//验证邮箱
	var isPwd=checkPwd($("input[name='lvAccount.pwd']")); //验证密码
	var isConfirmpwd=checkConfirmPwd($("input[name='truePwd']"),$("input[name='lvAccount.pwd']").val());//验证确认密码
	var isEmailOne=false;
	if(isEmail){
		isEmailOne=checkEmailOne($("input[name='lvAccount.email']"));
	}
	
	if(isEmailOne&&isPwd&&isConfirmpwd){
		var dat = {'jumpurl': $('input[name="jumpurl"]').val(),
				'lvAccount.email': $('#email').val(),
				'lvAccount.pwd': $('input[name="lvAccount.pwd"]:visible').val(),
				'truePwd': $('input[name="truePwd"]:visible').val()};
		
		$.post("/web/regeditAccount.action",dat,function(str){
				var data = eval('(' + str + ')');
				if(data.mark==0){
					//$("#rmsg").html("恭喜您注册成功，系统将自动跳转到登录页面！");
					window.location.href='/';
				}else if(data.mark==2){
					$("#rmsg").html("注册失败！");
				}
			});
	}
}

