
$(function(){
	init();
});

function init() {
	$("#loginname").bind("focus", function(){
		$("#remind_loginname").html('<p class="pt">Please enter your registration filled in Email Address!</p>');
	});
	$("#loginpwd").bind("focus", function(){
		$("#remind_loginpwd").html('<p class="pt">Please enter the password you set when you register!</p>');
	});
	$("#logincode").bind("focus", function(){
		$("#remind_logincode").html('<p class="pt">Please enter the characters in the picture on the right!</p>');
	});
}

// 登录验证
function subLoginForm() {
	return checkEmail() && checkPassword() && checkVcode();
}

function checkEmail() {
	if (js.assert.isNull("loginname", '<p class="er">Please enter your login account!</p>', "remind_loginname")) {return false;}
	if (js.assert.isNotEmail("loginname", '<p class="er">Account format error!</p>', "remind_loginname")) {return false;}
	if (js.assert.isIllegalLength("loginname", "<2||>60", '<p class="er">Account can only be 2-60 characters!</p>', "remind_loginname")) {return false;}
	return true;
}

function checkPassword() {
	if (js.assert.isNull("loginpwd", '<p class="er">Please enter your password!</p>', "remind_loginpwd")) {return false;}
	if (js.assert.isIllegalLength("loginpwd", "<6||>16", '<p class="er">Password can only be 6-16 characters!</p>', "remind_loginpwd")) {return false;}
	return true;
}

function checkVcode() {
	if (js.assert.isNull("logincode", '<p class="er">Please enter the verification code!</p>', "remind_logincode")) {return false;}
	
	var code = $("#logincode").val();
	var isPass = true;
	
	$.ajax({
		 url: '/web/validatecode.action',
		 data: "code=" + code,
		 type: 'POST', 
		 async: false,
		 success: function(num) {
			  if (num == 0) {
				  $("#remind_logincode").html('<p class="er">Please enter the verification code!</p>');
				  isPass = false;
			   } else if(num == -1) {
				   $("#remind_logincode").html('<p class="er">Verification code error!</p>');
				   isPass = false;
			   } else if(num == 1) {
				   $("#remind_logincode").html('');
			   }
		 }
	});
	
	return isPass;
}