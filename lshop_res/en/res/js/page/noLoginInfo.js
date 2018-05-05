
$(function(){
	initLogin();
});

function initLogin() {
	$("#loginname").bind("focus", function(){
		$("#remind_loginname").html('Please enter your Email Address or nickname!');
		$("#remind_loginname").attr("style","");
		$("#remind_loginname").attr("class","tips");
		$("#remind_loginname").show();
	});
	$("#loginpwd").bind("focus", function(){
		$("#remind_loginpwd").html('Please enter the password you set when you register!');
		$("#remind_loginpwd").attr("style","");
		$("#remind_loginpwd").attr("class","tips");
		$("#remind_loginpwd").show();
	});
	$("#logincode").bind("focus", function(){
		$("#remind_logincode").html('Please enter the characters in the picture on the right!');
		$("#remind_logincode").attr("style","");
		$("#remind_logincode").attr("class","tips");
		$("#remind_logincode").show();
	});
}

// 登录验证
function subLoginForm() {
	return checkLoginEmail() && checkLoginPassword() && checkLoginVcode();
}

function checkLoginEmail() {
	if (js.assert.isNull("loginname", '<p class="er">Please enter your email or nickname!</p>', "remind_loginname")) {
		$("#remind_loginname").show();
		return false;
	}
	if (js.assert.isIllegalLength("loginname", "<2||>60", '<p class="er">Account can only be 2-60 characters!</p>', "remind_loginname")) {
		$("#remind_loginname").show();
		return false;
	}
	
	$("#remind_loginname").hide();
	return true;
}

function checkLoginPassword() {
	if (js.assert.isNull("loginpwd", '<p class="er">Please enter your password!</p>', "remind_loginpwd")) {
		$("#remind_loginpwd").show();
		return false;
	}
	if (js.assert.isIllegalLength("loginpwd", "<6||>16", '<p class="er">Password can only be 6-16 characters!</p>', "remind_loginpwd")) {
		$("#remind_loginpwd").show();
		return false;
	}
	$("#remind_loginpwd").hide();
	return true;
}

function checkLoginVcode() {
	if (js.assert.isNull("logincode", '<p class="er">Please enter the verification code!</p>', "remind_logincode")) {
		$("#remind_logincode").show();
		return false;
	}else{
		$("#remind_logincode").hide();
	}
	
	return true;
}