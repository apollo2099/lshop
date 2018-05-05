
$(function(){
	init();
});

function init() {
	$("#email").bind("focus", function(){
		$("#remind_email").html('<p class="pt">The address will be the system login account and receive e-mail address, make sure you can use!</p>');
	});
	$("#pwd").bind("focus", function(){
		$("#remind_pwd").html('<p class="pt">6-16 characters, you can use a combination of letters, numbers or symbols, it is not recommended to use pure letters, pure digital, pure symbol!</p>');
	});
	$("#truePwd").bind("focus", function(){
		$("#remind_truePwd").html('<p class="pt">Please enter the password again!</p>');
	});
	$("#vcode").bind("focus", function(){
		$("#remind_vcode").html('<p class="pt">Please enter the characters in the picture on the right!</p>');
	});
}

// 注册验证
function registValidate() {
	var isvalid= checkEmail() && checkPassword() && checkTruePassword() && checkVcode();
	if(isvalid){
		var disableclass="user_reg_bt_disable";
		var classstr=$("#registerbut").attr('class');
		if(classstr.indexOf(disableclass)<0){
			$("#registerbut").addClass(disableclass);
		}else{
			isvalid=false;
		}
	}
	return isvalid;
}

function checkEmail() {
	if (js.assert.isNull("email", '<p class="er">Please enter account!</p>', "remind_email")) {return false;}
	if (js.assert.isNotEmail("email", '<p class="er">Account format error!</p>', "remind_email")) {return false;}
	if (js.assert.isIllegalLength("email", "<2||>32", '<p class="er">Account can only be 2-32 characters!</p>', "remind_email")) {return false;}
	
	var email = $("#email").val();
	var isPass = true;
	
	$.ajax({   
		 url: '/web/userCenterManage!isExistsUser.action',
		 data: "lvAccount.email=" + email,   
		 type: 'POST', 
		 async: false,
		 success: function(num){   
		  if(num == 1) {
			  $("#remind_email").html('<p class="er">The mailbox already exists!</p>');
			  isPass = false;
		   } else if(num == 0) {
			   $("#remind_email").html('');
		   }
		 }   
	});
	return isPass;
}

function checkPassword() {
	if (js.assert.isNull("pwd", '<p class="er">Please enter password!</p>', "remind_pwd")) {return false;}
	if (js.assert.isIllegalLength("pwd", "<6||>16", '<p class="er">Password can only be 6-16 characters!</p>', "remind_pwd")) {return false;}
	return true;
}

function checkTruePassword() {
	if (js.assert.isNull("truePwd", '<p class="er">Please enter confirm password!</p>', "remind_truePwd")) {return false;}
	if (js.assert.isNotEquals("pwd", "truePwd", '<p class="er">Enter the password twice inconsistent!</p>', "remind_truePwd")) {return false;}
	return true;
}

function checkVcode() {
	if (js.assert.isNull("vcode", '<p class="er">Please enter captcha !</p>', "remind_vcode")) {return false;}
	
	var code = $("#vcode").val();
	var isPass = true;
	
	$.ajax({
		 url: '/web/validatecode.action',
		 data: "code=" + code,
		 type: 'POST', 
		 async: false,
		 success: function(num) {
			  if (num == 0) {
				  $("#remind_vcode").html('<p class="er">Please enter the verification code!</p>');
				  isPass = false;
			   } else if(num == -1) {
				   $("#remind_vcode").html('<p class="er">Verification code error!</p>');
				   isPass = false;
			   } else if(num == 1) {
				   $("#remind_vcode").html('');
			   }
		 }
	});
	return isPass;
}