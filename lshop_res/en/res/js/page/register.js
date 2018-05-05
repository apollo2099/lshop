
$(function(){
	init();
});

function init() {
	$("#email").bind("focus", function(){
		$("#remind_email").html('The address will be the system login account and receive e-mail address, make sure you can use!');
		$("#remind_email").attr("style","");
		$("#remind_email").attr("class","tips");
		$("#remind_email").show();
	});
	$("#pwd").bind("focus", function(){
		$("#remind_pwd").html('6-16 characters, you can use a combination of letters, numbers or symbols, it is not recommended to use pure letters, pure digital, pure symbol!');
		$("#remind_pwd").attr("style","");
		$("#remind_pwd").attr("class","tips");
		$("#remind_pwd").show();
	});
	$("#truePwd").bind("focus", function(){
		$("#remind_truePwd").html('Please enter the password again!');
		$("#remind_truePwd").attr("style","");
		$("#remind_truePwd").attr("class","tips");
		$("#remind_truePwd").show();
	});
	$("#vcode").bind("focus", function(){
		$("#remind_vcode").html('Please enter the characters in the picture on the right!');
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
	if (js.assert.isNull("email", 'Please enter account!', "remind_email")) {$("#remind_email").show();return false;}
	if (js.assert.isNotEmail("email", 'Account format error!', "remind_email")) {$("#remind_email").show();return false;}
	if (js.assert.isIllegalLength("email", "<2||>32", 'Account can only be 2-32 characters!', "remind_email")) {$("#remind_vcode").show();return false;}
	
	
	var email = $("#email").val();
	var isPass = true;
	
	$.ajax({   
		 url: '/web/userCenterManage!isExistsUser.action',
		 data: "lvAccount.email=" + email,   
		 type: 'POST', 
		 async: false,
		 success: function(num){   
		  if(num == 1) {
			  $("#remind_email").html('<p class="er">The mailbox already exists!</p>');
			  $("#remind_email").show();
			  isPass = false;
		   }else if(num==-1){
			   $("#remind_email").html('<p class="er">Invalid E-mail format!</p>');
			   $("#remind_email").show();
			   isPass = false;
		   } else if(num == 0) {
			   $("#remind_email").html('');
			   $("#remind_email").hide();
		   }
		 }   
	});
	return isPass;
}

function checkPassword() {
	if (js.assert.isNull("pwd", '<p class="er">Please enter password!</p>', "remind_pwd")) {$("#remind_pwd").show();return false;}
	if (js.assert.isIllegalLength("pwd", "<6||>16", '<p class="er">Password can only be 6-16 characters!</p>', "remind_pwd")) {$("#remind_pwd").show();return false;}
	
	$("#remind_pwd").hide();
	return true;
}

function checkTruePassword() {
	if (js.assert.isNull("truePwd", '<p class="er">Please enter confirm password!</p>', "remind_truePwd")) {$("#remind_truePwd").show();return false;}
	if (js.assert.isNotEquals("pwd", "truePwd", '<p class="er">Enter the password twice inconsistent!</p>', "remind_truePwd")) {$("#remind_truePwd").show();return false;}
	
	$("#remind_truePwd").hide();
	return true;
}

function checkVcode() {
	if (js.assert.isNull("vcode", '<p class="er">Please enter captcha !</p>', "remind_vcode")) {$("#remind_vcode").show();return false;}
	
	$("#remind_vcode").hide();
	return true;
}