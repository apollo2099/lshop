
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
	$("input[name='code']").blur(function(){
		checkCode($(this));
	});
	$("input[name='code']").val("");
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
	var isCode=checkCode($("input[name='code']"));
	var isEmailOne=false;
	if(isEmail){
		isEmailOne=checkEmailOne($("input[name='lvAccount.email']"));
	}
	
	if(isEmailOne&&isPwd&&isConfirmpwd&&isCode){
		var disableclass="user_reg_bt_disable";
		var classstr=$("#registerbut").attr('class');
		//防止重复提交
		if(classstr.indexOf(disableclass)<0){
				$("#registerbut").addClass(disableclass);//样式改为灰色
				var dat = {'jumpurl': $('input[name="jumpurl"]').val(),
						'lvAccount.email': $('#email').val(),
						'lvAccount.pwd': $('input[name="lvAccount.pwd"]:visible').val(),
						'truePwd': $('input[name="truePwd"]:visible').val(),
						'code': $('input[name="code"]').val()};
			
			  $.post("/web/regeditAccount.action",dat,function(str){
					var data = eval('(' + str + ')');
					if(data.mark==0){
						//$("#rmsg").html("恭喜您注册成功，系统将自动跳转到登录页面！");
						window.location.href=data.jumpurl;
					}else if(data.mark==2){
						$("#registerbut").removeClass(disableclass);
						$("#rmsg").html("注册失败");
						$("input[name='code']").val("");
						document.getElementById('rcId').src=document.getElementById('rcId').src+'?'+new Date();
					}else if(data.mark==-2){
						$("#registerbut").removeClass(disableclass);
						$("#rmsg").html("验证码错误");
						$("input[name='code']").val("");
						document.getElementById('rcId').src=document.getElementById('rcId').src+'?'+new Date();
					}
				});
		}
	}
}

//验证码
var codeStatu=0;
function checkCode(obj){
	var code = $.trim(obj.val());
	if(code==""){
		info="请输入验证码"; 
		obj.next(".tip").children(".errInfo").html(info);
		obj.next(".tip").show();
		return false;
	}else{
		isCodeRight(code);
		if(codeStatu==1){
			 return true;
		 }else if(codeStatu==-1){	
			info="验证码错误"; 
			obj.next(".tip").children(".errInfo").html(info);
			obj.next(".tip").show();
			return false;
		 }else if(codeStatu==0){	
				info="请输入验证码"; 
				obj.next(".tip").children(".errInfo").html(info);
				obj.next(".tip").show();
				return false;
			 }
	}
	return true;
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
				  codeStatu = 0;
				  return false;
			   }else if(num==-1){
				   codeStatu = -1;
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