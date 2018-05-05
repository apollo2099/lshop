$(function (){
	$("input[name='recharge.accounts']").focus(function(){
		 if($(this).val()==$(this).attr("defalt")){
		   $(this).val("");
		 }
	}); 
	$("input[name='recharge.accounts']").blur(function(){
		checkEmail($(this));
	});
	
	$("input[name='recharge.rcardNum']").focus(function(){
		 if($(this).val()==$(this).attr("defalt")){
		   $(this).val("");	 
		 }
	}); 
	$("input[name='recharge.rcardNum']").blur(function(){
		checkCartNum($(this));
	});
	
	$("input[name='cardPwd']").blur(function(){
		checkCartPwd($(this));
	});
	
	$("input[name='validCode']").blur(function(){
		checkCode($(this));
	});

});

//充值卡号
function checkCartNum(obj){
	if(obj.val()=="" || obj.val()==obj.attr("defalt")){
		obj.val(obj.attr("defalt"));
		info="请输入充值卡卡号"; 
		obj.next(".tip").children(".errInfo").html(info);
		obj.next(".tip").show();
		return false;
	}
	return true;
}
//充值卡密码
function checkCartPwd(obj){
	if(obj.val()==""){
		info="请输入充值卡密码"; 
		obj.next(".tip").children(".errInfo").html(info);
		obj.next(".tip").show();
		return false;
	}
	return true;
}
//验证码
function checkCode(obj){
	if(obj.val()==""){
		info="请输入验证码"; 
		obj.next(".tip").children(".errInfo").html(info);
		obj.next(".tip").show();
		return false;
	}
	return true;
}

// 选择充值方式
function initSwitch() {
	$("input[name='type']").each(function(){
		var radio = $(this);
		radio.click(function(){
			var type = radio.val();
			if (type == 1) {
				$("#item_account").hide();
			} else {
				$("#item_account").show();
			}
		});
	});
	
	var type = "${type}";
	$("input[name='type']").each(function(){
		if (this.value == type) {
			this.checked = true;
		}
	});
	
	// 页面加载时判断是否显示代充账号
	var type = $("input[name='type']:checked").val();
	if (type == 1) {
		$("#item_account").hide();
	} else {
		$("#item_account").show();
	}
}

function showInfo(obj,info){
	obj.next(".tip").children(".errInfo").html(info);
	obj.next(".tip").show();
}

// 表单提交--充值卡充值
function subFormCart() {
	var type = $("input[name='type']:checked").val();
	if (type == 2) {	// 帮人充值
		if(!checkEmail($("input[name='recharge.accounts']"))){
			return false;
		}
	}
	if (!checkCartNum($("input[name='recharge.rcardNum']"))) {return false;}
	if (!checkCartPwd($("input[name='cardPwd']"))) {return false;}
	if (!checkCode($("input[name='validCode']"))) {return false;}
	if (type == 1) {
		var result;
		$.ajax({
			url:"/web/recharge!userStatus.action",
			type: 'post',
			dataType:"text",
			cache:false,
			async:false,
			success:function(data){
				result = data;
			}
		});
		if (result == 2) {
			showInfo($("input[name='recharge.rcardNum']"),'您的账号已冻结');
			return false;
		} else if (result == 0) {
			showInfo($("input[name='recharge.rcardNum']"),'您的账号已停用');
			return false;
		}
		$("#docartform").submit();
		return true;
		
	} else if (type == 2) {
		var result;
		$.ajax({
			url:"/web/recharge!userStatus.action",
			type: 'post',
			dataType:"text",
			cache:false,
			async:false,
			success:function(data){
				result = data;
			}
		});
		
		if (result == 2) {
			showInfo($("input[name='recharge.rcardNum']"),'您的账号已冻结');
			return false;
		} else if (result == 0) {
			showInfo($("input[name='recharge.rcardNum']"),'您的账号已停用');
			return false;
		}
		
		var email = $("#accounts").val().trim();
		$.ajax({
			url:"/web/recharge!isExistAccout.action",
			type: 'post',
			dataType:"text",
			data:{email:email},
			cache:false,
			async:false,
			success:function(data){
				result = data;
			}
		});
		
		if (result == 3) {
			showInfo($("input[name='recharge.accounts']"),'代充账号未激活');
			return false;
		} else if (result == 2) {
			showInfo($("input[name='recharge.accounts']"),'代充账号已冻结');
			return false;
		} else if (result == "") {
			showInfo($("input[name='recharge.accounts']"),'代充账号不存在');
			return false;
		} else if (result == 0) {
			showInfo($("input[name='recharge.accounts']"),'代充账号已停用');
			return false;
		}
	}
	$("#docartform").submit();
	return true;
}

function changeCodeImg(){
	$("#codeId").attr("src",$("#codeId").attr("src")+"?t="+new Date());
}

//在线充值
function getEnterAmount(){
	if($("input[name='vtype']:checked").val() == 0){
		var other = $.trim($("#otherId").val());
	    if(other==''){
	    	other = 0;
	    }
	    $("#rechargeAmount").html("");
	    $("#vnumId").val(other);
		$("#rechargeAmount").html(other/rateVbNum);
	}
}
//在线充值
function subFormOnline() {
	var type = $("input[name='type']:checked").val();
	if (type == 1) {
		var result;
		$.ajax({
			url:"/web/recharge!userStatus.action",
			type: 'post',
			dataType:"text",
			cache:false,
			async:false,
			success:function(data){
				result = data;
			}
		});		
		if(result == 2){
			showInfo($("input[name='recharge.accounts']"),'登陆账号已冻结');
			return false;
		}else if(result==0){
			showInfo($("input[name='recharge.accounts']"),'登陆账号已停用');
			return false;
		}
		
	} else if (type == 2) {// 帮人充值
		if(!(checkEmail($("input[name='recharge.accounts']")))) return false;
		var result;
		$.ajax({
			url:"/web/recharge!userStatus.action",
			type: 'post',
			dataType:"text",
			cache:false,
			async:false,
			success:function(data){
				result = data;
			}
		});
		if(result == 2){
			showInfo($("input[name='recharge.accounts']"),'登陆账号已冻结');
			return false;
		}else if(result==0){
			showInfo($("input[name='recharge.accounts']"),'登陆账号已停用');
			return false;
		}
		
		var email = $.trim($("#accouts").val());
		$.ajax({
			url:"/web/recharge!isExistAccout.action",
			type: 'post',
			data:{email:email},
			cache:false,
			async:false,
			dataType:"text",
			success:function(data){
				result = data;
			}
		});
		if(result==3){
			showInfo($("input[name='recharge.accounts']"),'代充账号未激活');
			return false;
		}else if(result==2){
			showInfo($("input[name='recharge.accounts']"),'代充账号已冻结');
			return false;
		}else if(result==""){
			showInfo($("input[name='recharge.accounts']"),'代充账号不存在');
			return false;
	    }else if(result==0){
	    	showInfo($("input[name='recharge.accounts']"),'代充账号已停用');
			return false;
		}
	}
	
	var num = $.trim($("#vnumId").val());
    if(num==''||num==0) {
    	showInfo($("input[name='otherId']"),'请输入需要购买的V币数量');
    	return false;
    }else if(!/^\d+$/.test(num)|| num<10){
    	showInfo($("input[name='otherId']"),'购买金额不能低于1美元');
		return false;
	}
    $("#doorderform").submit();
	return true;
}
//在线充值
function initNum(){
	var vtype = $("input[name='vtype']:checked").val();
	if(vtype == 0){
		var other = $.trim($("#otherId").val());
	    if(other==''){
	    	other = 0;
	    }
	    $("#rechargeAmount").html("");
	    $("#vnumId").val(other);
		$("#rechargeAmount").html(other/rateVbNum);
	}else{
		$("#rechargeAmount").html("");
	    $("#vnumId").val(vtype);
		$("#rechargeAmount").html(vtype/rateVbNum);
	}
}