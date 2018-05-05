$(function(){
	init();
});

function init() {
	$("#loginname").bind("focus", function(){
		$("#remind_loginname").html('<p class="pt">请输入您注册填写的电子邮件地址！</p>');
	});
	$("#loginpwd").bind("focus", function(){
		$("#remind_loginpwd").html('<p class="pt">请输入您注册时设置的密码！</p>');
	});
	$("#logincode").bind("focus", function(){
		$("#remind_logincode").html('<p class="pt">请输入右边图片中的字符！</p>');
	});
}

// 登录验证
function subLoginForm() {
	return checkEmail() && checkPassword() && checkVcode();
}

function checkEmail() {
	if (js.assert.isNull("loginname", '<p class="er">請輸入賬號！</p>', "remind_loginname")) {return false;}
	if (js.assert.isNotEmail("loginname", '<p class="er">賬號格式錯誤！</p>', "remind_loginname")) {return false;}
	if (js.assert.isIllegalLength("loginname", "<2||>60", '<p class="er">賬號只能是2至60位字符！</p>', "remind_loginname")) {return false;}
	return true;
}

function checkPassword() {
	if (js.assert.isNull("loginpwd", '<p class="er">請輸入密碼！</p>', "remind_loginpwd")) {return false;}
	if (js.assert.isIllegalLength("loginpwd", "<6||>16", '<p class="er">密碼只能是6至16位字符！</p>', "remind_loginpwd")) {return false;}
	return true;
}

function checkVcode() {
	if (js.assert.isNull("logincode", '<p class="er">請輸入驗證碼！</p>', "remind_logincode")) {return false;}
	
	var code = $("#logincode").val();
	var isPass = true;
	
	$.ajax({
		 url: '/web/validatecode.action',
		 data: "code=" + code,
		 type: 'POST', 
		 async: false,
		 success: function(num) {
			  if (num == 0) {
				  $("#remind_logincode").html('<p class="er">請輸入驗證碼！</p>');
				  isPass = false;
			   } else if(num == -1) {
				   $("#remind_logincode").html('<p class="er">驗證碼錯誤！</p>');
				   isPass = false;
			   } else if(num == 1) {
				   $("#remind_logincode").html('');
			   }
		 }
	});
	
	return isPass;
}