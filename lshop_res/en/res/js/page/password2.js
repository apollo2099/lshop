
$(function(){
	init();
});

function init() {
	$("#rpwd").bind("focus", function(){
		$("#remind_rpwd").html('<span class="pt">6-16 characters, you can use a combination of letters, numbers or symbols, it is not recommended to use pure letters, pure digital, pure symbol!</span>');
	});
	$("#truePwd").bind("focus", function(){
		$("#remind_truePwd").html('<span class="pt">Please enter the new password again!</span>');
	});
}

// 提交验证
function subForm() {
	return checkPassword() && checkTruePassword();
}

function checkPassword() {
	if (js.assert.isNull("rpwd", '<span class="er">Please enter the new password!</span>', "remind_rpwd")) {return false;}
	if (js.assert.isIllegalLength("rpwd", "<6||>16", '<span class="er">Password can only be 6-16 characters!</span>', "remind_rpwd")) {return false;}
	return true;
}

function checkTruePassword() {
	if (js.assert.isNull("truePwd", '<span class="er">Please enter confirm new password!</span>', "remind_truePwd")) {return false;}
	if (js.assert.isNotEquals("rpwd", "truePwd", '<span class="er">Enter the password twice inconsistent!</span>', "remind_truePwd")) {return false;}
	return true;
}
