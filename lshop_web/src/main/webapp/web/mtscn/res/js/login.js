
$(function (){
	//检验登陆表单
	checkLogin();
});


//验证登陆
function checkLogin(){
	//邮箱
	$("input[name='uname']").focus(function(){
		 if($(this).val()==$(this).attr("defalt")){
		   $(this).val("");	 
		 }
	}); 
	$("input[name='uname']").blur(function(){
		checkEmail($(this));
	});
	
	//密码
	$("input[name='pwd']").blur(function(){
	  	 checkPwd($(this));
	});

}

//提交登陆表单
function submitLogin(){
	var isEmail=checkEmail($("input[name='uname']"));//验证邮箱
	var isPwd=checkPwd($("input[name='pwd']")); //验证密码
	
	if(isEmail&&isPwd){
		$.post("/web/userCenterManage!login.action",$("#loginPage").serialize(),function(str){
			var data = eval('(' + str + ')');
			if(data.flag==0){
				window.location.href=data.jumpurl;
			}else if(data.flag==1){
				$("#msgg").html("该账号已删除!");
			}else if(data.flag==2){
				$("#msgg").html("该账号已停用!");
			}else if(data.flag==3){
				$("#msgg").html("用户名或密码不正确!");
			}
		});
	}
}

