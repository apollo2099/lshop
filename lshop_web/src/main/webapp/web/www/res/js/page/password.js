
// 提交验证
function subForm() {
	return checkEmail() && checkVcode();
}

function checkEmail() {
	if (js.assert.isNull("emailId", '請輸入賬號！', "remindbox")) {return false;}
	if (js.assert.isNotEmail("emailId", '賬號格式錯誤！', "remindbox")) {return false;}
	return true;
}

function checkVcode() {
	if (js.assert.isNull("rcode", '请输入验证码！', "remindbox")) {return false;}
	return true;
}
