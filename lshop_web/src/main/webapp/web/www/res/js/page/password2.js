
$(function(){
	init();
});

function init() {
	$("#rpwd").bind("focus", function(){
		$("#remind_rpwd").html('<span class="pt">6-16位字符，可使用字母、數字或符號的組合，不建議使用純字母、純數字、純符號！</span>');
	});
	$("#truePwd").bind("focus", function(){
		$("#remind_truePwd").html('<span class="pt">请再次输入新密码！</span>');
	});
}

// 提交验证
function subForm() {
	return checkPassword() && checkTruePassword();
}

function checkPassword() {
	if (js.assert.isNull("rpwd", '<span class="er">请输入新密码！</span>', "remind_rpwd")) {return false;}
	if (js.assert.isIllegalLength("rpwd", "<6||>16", '<span class="er">密碼只能是6至16位字符！</span>', "remind_rpwd")) {return false;}
	return true;
}

function checkTruePassword() {
	if (js.assert.isNull("truePwd", '<span class="er">请输入确认新密码！</span>', "remind_truePwd")) {return false;}
	if (js.assert.isNotEquals("rpwd", "truePwd", '<span class="er">兩次輸入密碼不一致！</span>', "remind_truePwd")) {return false;}
	return true;
}
