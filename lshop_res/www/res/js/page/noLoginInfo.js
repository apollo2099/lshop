$(function(){
	initLogin();
});

function initLogin() {
	$("#loginname").bind("focus", function(){
		$("#remind_loginname").html('請輸入您的Email或者暱稱！');
		$("#remind_loginname").attr("style","");
		$("#remind_loginname").attr("class","tips");
		$("#remind_loginname").show();
	});
	$("#loginpwd").bind("focus", function(){
		$("#remind_loginpwd").html('請輸入您註冊時設置的密碼！');
		$("#remind_loginpwd").attr("style","");
		$("#remind_loginpwd").attr("class","tips");
		$("#remind_loginpwd").show();
	});
	$("#logincode").bind("focus", function(){
		$("#remind_logincode").html('請輸入右邊圖片中的字符！');
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
	if (js.assert.isNull("loginname", '<p class="er">請輸入賬號！</p>', "remind_loginname")) {
		$("#remind_loginname").show();
		return false;
	}
	if (js.assert.isIllegalLength("loginname", "<2||>60", '<p class="er">賬號只能是2至60位字符！</p>', "remind_loginname")) {$("#remind_loginname").show();return false;}
	
	$("#remind_loginname").hide();
	return true;
}

function checkLoginPassword() {
	if (js.assert.isNull("loginpwd", '<p class="er">請輸入密碼！</p>', "remind_loginpwd")) {$("#remind_loginpwd").show();return false;}
	if (js.assert.isIllegalLength("loginpwd", "<6||>16", '<p class="er">密碼只能是6至16位字符！</p>', "remind_loginpwd")) {$("#remind_loginpwd").show();return false;}
	
	$("#remind_loginpwd").hide();
	return true;
}

function checkLoginVcode() {
	if (js.assert.isNull("logincode", '<p class="er">請輸入驗證碼！</p>', "remind_logincode")) {$("#remind_logincode").show();return false;}
	
	$("#remind_logincode").hide();
	return true;
}