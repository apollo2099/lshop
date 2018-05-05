$(function (){
	$("input[name='recharge.accounts']").focus(function(){
		 if($(this).val()==$(this).attr("defalt")){
		   $(this).val("");
		 }
	}); 
	$("input[name='recharge.accounts']").blur(function(){
		checkEmail($(this));
	});
	
	$("input[name='recharge.rcardNum']").focus(function(){
		 if($(this).val()==$(this).attr("defalt")){
		   $(this).val("");	 
		 }
	}); 
	$("input[name='recharge.rcardNum']").blur(function(){
		checkCartNum($(this));
	});
	
	$("input[name='cardPwd']").blur(function(){
		checkCartPwd($(this));
	});
	
	$("input[name='validCode']").blur(function(){
		checkCode($(this));
	});

});

//充值卡号
function checkCartNum(obj){
	if(obj.val()=="" || obj.val()==obj.attr("defalt")){
		obj.val(obj.attr("defalt"));
		info="请输入充值卡卡号"; 
		obj.next(".tip").children(".errInfo").html(info);
		obj.next(".tip").show();
		return false;
	}
	return true;
}
//充值卡密码
function checkCartPwd(obj){
	if(obj.val()==""){
		info="请输入充值卡密码"; 
		obj.next(".tip").children(".errInfo").html(info);
		obj.next(".tip").show();
		return false;
	}
	return true;
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



function showInfo(obj,info){
	obj.next(".tip").children(".errInfo").html(info);
	obj.next(".tip").show();
}

// 表单提交--充值卡充值
function subFormCart() {

		if(!checkEmail($("input[name='recharge.accounts']"))){
			return false;
		}
	if (!checkCartNum($("input[name='recharge.rcardNum']"))) {return false;}
	if (!checkCartPwd($("input[name='cardPwd']"))) {return false;}
	if (!checkCode($("input[name='validCode']"))) {return false;}
		var email = $("#accounts").val().trim();
		$.ajax({
			url:"/web/recharge!isExistAccout.action",
			type: 'post',
			dataType:"text",
			data:{email:email},
			cache:false,
			async:false,
			success:function(data){
				result = data;
			}
		});
		
		if (result == 3) {
			showInfo($("input[name='recharge.accounts']"),'账号未激活');
			return false;
		} else if (result == 2) {
			showInfo($("input[name='recharge.accounts']"),'账号已冻结');
			return false;
		} else if (result == "") {
			showInfo($("input[name='recharge.accounts']"),'账号不存在');
			return false;
		} else if (result == 0) {
			showInfo($("input[name='recharge.accounts']"),'账号已停用');
			return false;
		}
	
	$("#docartform").submit();
	return true;
}

function changeCodeImg(){
	$("#codeId").attr("src",$("#codeId").attr("src")+"?t="+new Date());
}

