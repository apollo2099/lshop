
$(function(){
	init();
});

function init() {
	$("#email").bind("focus", function(){
		$("#remind_email").html('該地址將成為登錄賬戶及系統郵件接收地址，請確認可以使用！');
		$("#remind_email").attr("style","");
		$("#remind_email").attr("class","tips");
		$("#remind_email").show();
	});
	$("#pwd").bind("focus", function(){
		$("#remind_pwd").html('6-16位字符，可使用字母、數字或符號的組合，不建議使用純字母、純數字、純符號！');
		$("#remind_pwd").attr("style","");
		$("#remind_pwd").attr("class","tips");
		$("#remind_pwd").show();
	});
	$("#truePwd").bind("focus", function(){
		$("#remind_truePwd").html('請再次輸入密碼！');
		$("#remind_truePwd").attr("style","");
		$("#remind_truePwd").attr("class","tips");
		$("#remind_truePwd").show();
	});
	$("#vcode").bind("focus", function(){
		$("#remind_vcode").html('請輸入右邊圖片中的字符！');
		$("#remind_vcode").attr("style","");
		$("#remind_vcode").attr("class","tips");
		$("#remind_vcode").show();
	});
	
}

// 注册验证
function registValidate() {
	var isvalid= checkEmail() && checkPassword() && checkTruePassword() && checkVcode();
	/*
	if(isvalid){
		var disableclass="user_reg_bt_disable";
		var classstr=$("#registerbut").attr('class');
		if(classstr.indexOf(disableclass)<0){
			$("#registerbut").addClass(disableclass);
		}else{
			isvalid=false;
		}
	}*/
	return isvalid;
}

function checkEmail() {
	if (js.assert.isNull("email", '請輸入賬號！', "remind_email")) {$("#remind_email").show();return false;}
	if (js.assert.isNotEmail("email", '<p class="er">賬號格式錯誤！</p>', "remind_email")) {$("#remind_email").show();return false;}
	if (js.assert.isIllegalLength("email", "<2||>32", '<p class="er">賬號只能是2至32位字符！</p>', "remind_email")) {$("#remind_email").show();return false;}
	
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
			  $("#remind_email").show();
			  isPass = false;
		   }else if(num==-1){
			   $("#remind_email").html('<p class="er">Email格式不正确！</p>');
			   $("#remind_email").show();
			   isPass = false;
		   } else if(num == 0) {
			   $("#remind_email").hide();
			   $("#remind_email").html('');
		   }
		 }   
	});
	return isPass;
}

function checkPassword() {
	if (js.assert.isNull("pwd", '<p class="er">請輸入密碼！</p>', "remind_pwd")) {$("#remind_pwd").show();return false;}
	if (js.assert.isIllegalLength("pwd", "<6||>16", '<p class="er">密碼只能是6至16位字符！</p>', "remind_pwd")) {$("#remind_pwd").show();return false;}
	
	$("#remind_pwd").hide();
	return true;
}

function checkTruePassword() {
	if (js.assert.isNull("truePwd", '<p class="er">請輸入确认密碼！</p>', "remind_truePwd")) {$("#remind_truePwd").show();return false;}
	if (js.assert.isNotEquals("pwd", "truePwd", '<p class="er">兩次輸入密碼不一致！</p>', "remind_truePwd")) {$("#remind_truePwd").show();return false;}
	
	$("#remind_truePwd").hide();
	return true;
}

function checkVcode() {
	if (js.assert.isNull("vcode", '<p class="er">請輸入驗證碼！</p>', "remind_vcode")) {$("#remind_vcode").show();return false;}
	
	$("#remind_vcode").hide();
	return true;
}

