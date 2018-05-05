$(function(){
$("#loginForm").validate({
		submitHandler:function(form){
			var currentUrl = window.location.href;
			if(submitLoginValidate()){
				$.post("/web/threeauth!buildAccount.action",$("#loginForm").serialize(),function(str){
					var data = eval('(' + str + ')');
					var f = data.mark;
					if(f==1){
						location.href=data.jumpurl;
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
					document.getElementById('rcIdA').src=document.getElementById('rcIdA').src+'?'+new Date();
				});
			}
		}
	});

$("#regeditForm").validate({
	submitHandler:function(form){
		var currentUrl = window.location.href;
		if(submitRegistValidate()){
			$.post("/web/threeauth!registerBuild.action",$("#regeditForm").serialize(),function(str){
				var data = eval('(' + str + ')');
				if(data.mark==0){//注册成功
					location.href=data.jumpurl;
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
	codeF = validateCode2();
	
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
function validateCode2(){
	$("#code_tip1").css("display","none");
	$("#code_tip2").css("display","none");
	$("#code_load").css("display","block");
	var code=$('#rcode2').val();
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

function boxLoginChoose(id){
	if(id==1){
	$("#box_login1").show();
	$("#box_login2").hide();
	$("#index1").addClass("choose");
	$("#index2").removeClass("choose");
	}
	if(id==2){
		$("#box_login2").show();
		$("#box_login1").hide();
		$("#index2").addClass("choose");
		$("#index1").removeClass("choose");
	}
}