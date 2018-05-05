
// 提交验证
function subForm() {
	return checkEmail() && checkVcode();
}

function checkEmail() {
	if (js.assert.isNull("emailId", 'Please enter account!', "remindbox")) {return false;}
	if (js.assert.isNotEmail("emailId", 'Account format error!', "remindbox")) {return false;}
	return true;
}

function checkVcode() {
	if (js.assert.isNull("rcode", 'Please enter the verification code!', "remindbox")) {return false;}
	return true;
}
