
$(function(){
	init();
});

function init() {
	$("#email").bind("focus", function(){
		$("#remind_email").html('<p class="pt">該地址將成為登錄賬戶及系統郵件接收地址，請確認可以使用！</p>');
	});
	$("#pwd").bind("focus", function(){
		$("#remind_pwd").html('<p class="pt">6-16位字符，可使用字母、數字或符號的組合，不建議使用純字母、純數字、純符號！</p>');
	});
	$("#truePwd").bind("focus", function(){
		$("#remind_truePwd").html('<p class="pt">請再次輸入密碼！</p>');
	});
	$("#vcode").bind("focus", function(){
		$("#remind_vcode").html('<p class="pt">请输入右边图片中的字符！</p>');
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
	if (js.assert.isNull("email", '<p class="er">請輸入賬號！</p>', "remind_email")) {return false;}
	if (js.assert.isNotEmail("email", '<p class="er">賬號格式錯誤！</p>', "remind_email")) {return false;}
	if (js.assert.isIllegalLength("email", "<2||>32", '<p class="er">賬號只能是2至32位字符！</p>', "remind_email")) {return false;}
	
	var email = $("#email").val();
	var isPass = true;
	
	$.ajax({   
		 url: '/web/userCenterManage!isExistsUser.action',
		 data: "lvAccount.email=" + email,   
		 type: 'POST', 
		 async: false,
		 success: function(num){   
		  if(num == 1) {
			  $("#remind_email").html('<p class="er">该邮箱已存在！</p>');
			  isPass = false;
		   } else if(num == 0) {
			   $("#remind_email").html('');
		   }
		 }   
	});
	return isPass;
}

function checkPassword() {
	if (js.assert.isNull("pwd", '<p class="er">請輸入密碼！</p>', "remind_pwd")) {return false;}
	if (js.assert.isIllegalLength("pwd", "<6||>16", '<p class="er">密碼只能是6至16位字符！</p>', "remind_pwd")) {return false;}
	return true;
}

function checkTruePassword() {
	if (js.assert.isNull("truePwd", '<p class="er">請輸入确认密碼！</p>', "remind_truePwd")) {return false;}
	if (js.assert.isNotEquals("pwd", "truePwd", '<p class="er">兩次輸入密碼不一致！</p>', "remind_truePwd")) {return false;}
	return true;
}

function checkVcode() {
	if (js.assert.isNull("vcode", '<p class="er">請輸入驗證碼！</p>', "remind_vcode")) {return false;}
	
	var code = $("#vcode").val();
	var isPass = true;
	
	$.ajax({
		 url: '/web/validatecode.action',
		 data: "code=" + code,
		 type: 'POST', 
		 async: false,
		 success: function(num) {
			  if (num == 0) {
				  $("#remind_vcode").html('<p class="er">請輸入驗證碼！</p>');
				  isPass = false;
			   } else if(num == -1) {
				   $("#remind_vcode").html('<p class="er">驗證碼錯誤！</p>');
				   isPass = false;
			   } else if(num == 1) {
				   $("#remind_vcode").html('');
			   }
		 }
	});
	return isPass;
}