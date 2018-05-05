
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
			var f = data.mark;
			if(f==1){
				var gotourl =  data.jumpurl;
				if(gotourl=='') gotourl='/index.html';
				window.location.href=gotourl;
			}else if(f==-1){
				$("#msgg").html("账号不存在");
			}else if(f==-2){
				$("#msgg").html("账号已停用");
			}else if(f==-3){
				$("#msgg").html("账号未激活");
			}else if(f==-4){
				$("#msgg").html("密码错误");
			}else if(f==0){
				$("#msgg").html("系统错误");
			}
		});
	}
}

