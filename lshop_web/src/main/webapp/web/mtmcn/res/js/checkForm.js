//验证账号邮箱
function checkEmail(email){
	var isEmail = false;
	var emailTrue=/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	if(email.val()=="" || email.val()==email.attr("defalt")){
		email.val(email.attr("defalt"));
		info="请输入email！"; 
		email.next(".tip").children(".errInfo").html(info);
		email.next(".tip").show();
	}else if(email.val().length>60){
		info = "email长度不得超过60位字符！";
		email.next(".tip").children(".errInfo").html(info);
		email.next(".tip").show();
    }else if(emailTrue.test(email.val())){
		email.next(".tip").hide();
		isEmail=true;
	}else{
		var info = "email格式不正确！";
		email.next(".tip").children(".errInfo").html(info);
		email.next(".tip").show();
	}
	return isEmail;
}

//检验邮箱唯一性
function checkEmailOne(email){
	var isOne = false;
	 $.ajax({   
		 url: '/web/userCenterManage!isExistsUser.action',
		 data:"lvAccount.email="+$.trim(email.val()),   
		 type: 'POST',   
		 async:false,  
		 success: function(num){  
		  if(num==1){
		  	  var info = "该email已注册！";
		  	  email.next(".tip").children(".errInfo").html(info);
			  email.next(".tip").show();
			  isOne=false;
		   }else if(num==0){
			   isOne=true;
		   }
		 }   
	});
	return isOne;
}

	//验证邮箱是否存在（和上一个验证唯一性一样，只是提示加的位置不一样，可合并为一个方法，提示提出来显示）
	function checkIsExistEmail(email){
		var isExist = false;
		 $.ajax({   
			 url: '/web/userCenterManage!isExistsUser.action',
			 data:"lvAccount.email="+$.trim(email.val()),   
			 type: 'POST',   
			 async:false,  
			 success: function(num){  
			  if(num==1){
				  isExist=true; //存在
			   }else if(num==0){
				   isExist=false;
				   var info = "该email未注册！";
			  	   email.next(".tip").children(".errInfo").html(info);
				   email.next(".tip").show();
			   }
			 }   
		});
		return isExist;
	}
	
//验证密码
	function checkPwd(pwd){
		var isPwd=false;
		var info = "";
		if(pwd.val()==""){
			info="请输入密码！"; 
			$("#p_errInfo").html(info);
			$("#p_tip").show();
		}else if(pwd.val().length>16){
				info = "密码长度不得超过16位字符！";
				$("#p_errInfo").html(info);
				$("#p_tip").show();
		}else if(pwd.val().length<6){
				info = "密码长度不得少于6位字符！";
				$("#p_errInfo").html(info);
				$("#p_tip").show();
		}else{
			isPwd = true;
		}
		return isPwd;
	}

//验证确认密码
function checkConfirmPwd(confirmPwd,pwd){
	var isConfirmpwd=false;
	var info = "两次输入密码不一致！";
	 if(confirmPwd.val()==""){
		info="请输入密码！"; 
		confirmPwd.next(".tip").children(".errInfo").html(info);
		confirmPwd.next(".tip").show();
	 }else if(pwd==confirmPwd.val()){
		confirmPwd.next(".tip").hide();
		isConfirmpwd=true;
	}else{
		confirmPwd.next(".tip").children(".errInfo").html(info);
		confirmPwd.next(".tip").show();
	}
	return isConfirmpwd;
}

//验证姓名
function checkName(name){
	  var isName=false;
	  var nameTrue=/^([a-zA-Z ]+|[\u4e00-\u9fa5]+){1,32}$/;
	  var info = "姓名格式不正确！";
	  if(name.val()==""){
		  info="请输入收货人姓名！"; 
		  name.next(".tip").children(".errInfo").html(info);
		  name.next(".tip").show();
	  }else if(nameTrue.test(name.val())){
		  name.next(".tip").hide();
		  isName=true;
	  }else{
	  		if(name.val().length>32){
				info = "姓名长度不得超过32位字符！";
			}
			if(name.val().length<2){
				info = "姓名长度不得少于2位字符！";
			}
			name.next(".tip").children(".errInfo").html(info);
		  	name.next(".tip").show();
	  }
	  return isName;
}

//验证手机
function checkMobile(mobile){
	  var ismobile=false;
	  var mobileTrue=/(((13[0-9]{1})|(15[0-9]{1}))+\d{1,8})/;
	  var info = "手机格式不正确！";
	  if(mobile.val()){
		  if(mobileTrue.test(mobile.val())){
			  mobile.next(".tip").hide();
			  ismobile=true;
		  }else{
				mobile.next(".tip").children(".errInfo").html(info);
			  	mobile.next(".tip").show();
		  }
	  }
	  ismobile=true;
	  return ismobile;
}

//验证电话
function checkTel(tel){
	  var istel=false;
	  var telTrue=/[0-9\-]{3,16}/;
	  var info = "电话格式不正确！";
	  if(tel.val()){
		  if(telTrue.test(tel.val())){
			  tel.next(".tip").hide();
			  istel=true;
		  }else{
				tel.next(".tip").children(".errInfo").html(info);
			  	tel.next(".tip").show();
		  }
	  }
	  istel=true;
	  return istel;
}

//验证国家
function checkCountry(coun){
	if(coun.val()){
		$('#contryId').parent().siblings('.inputd').children('.tip').hide();
		return true;
	} else {
		$('#contryId').parent().siblings('.inputd').children('.tip').show();
		return false;
	}
}

//验证洲/省
function checkProvince(province){
	  var isprovince=false;
	  var isChinese = /[\u4E00-\u9FA5]/i; 
	  var info = "";
	  var $t = null;
	  if(province.attr("type") == "text"){
		  $t = province.parent().next(".inputd").children('.tip');
	  } else {
		  $t = province.parent().parent().siblings('.inputd').children('.tip');
	  }
	  if(province.val()==""){
		  info="请输入洲/省！"; 
		  $t.children(".errInfo").html(info);
		  $t.show();
	  }else if(province.val().length>32){
	  	  info="洲/省不能超过32位字符！"; 
		  $t.children(".errInfo").html(info);
		  $t.show();
	  }else if(isChinese.test(province.val())) {
		  info="洲/省不能输入中文！"; 
		  $t.children(".errInfo").html(info);
		  $t.show();
	  }else{
		  isprovince = true;
		  	$t.hide();
	  }
	  return isprovince;
}


//验证县/市
function checkCity(city){
	  var isCity=false;
	  var isChinese = /[\u4E00-\u9FA5]/i; 
	  var info = "";
	  if(city.val()==""){
		  info="请输入县/市！"; 
		  city.next(".tip").children(".errInfo").html(info);
		  city.next(".tip").show();
	  }else if(city.val().length>32){
	  	  info="县/市不能超过32位字符！"; 
		  city.next(".tip").children(".errInfo").html(info);
		  city.next(".tip").show();
	  }else if(isChinese.test(city.val())) {
		  info="县/市不能输入中文！"; 
		  city.next(".tip").children(".errInfo").html(info);
		  city.next(".tip").show();
	  }else{
		  	city.next(".tip").hide();
		  	isCity = true;
	  }
	  return isCity;
}

//验证街道地址
function checkAddress(address){
	  var isaddress=false;
	  var isChinese = /[\u4E00-\u9FA5]/i; 
	  var info = "";
	  if(address.val()==""){
		  info="请输入街道地址！"; 
		  address.next(".tip").children(".errInfo").html(info);
		  address.next(".tip").show();
	  }else if(address.val().length>128){
	  	  info="街道地址不能超过128位字符！"; 
		  address.next(".tip").children(".errInfo").html(info);
		  address.next(".tip").show();
	  }else if(isChinese.test(address.val())) {
		  info="街道地址不能输入中文！"; 
		  address.next(".tip").children(".errInfo").html(info);
		  address.next(".tip").show();
	  }else{
		  	address.next(".tip").hide();
		  	isaddress = true;
	  }
	  return isaddress;
}

//验证邮编
function checkZip(zip){
	  var iszip=false;
	  var zipTrue=/[1-9]{1}(\d+){5,16}/;
	  var info = "邮编格式不正确！";
	  if(zip.val()==""){
		  info="请输入邮编！"; 
		  zip.next(".tip").children(".errInfo").html(info);
		  zip.next(".tip").show();
	  }else if(zipTrue.test(zip.val())){
		  zip.next(".tip").hide();
		  iszip=true;
	  }else{
	  		if(zip.val().length>16){
				info = "邮编长度不得超过16位字符！";
			}
			zip.next(".tip").children(".errInfo").html(info);
		  	zip.next(".tip").show();
	  }
	  return iszip;
}
/**
 * 表单输入域提示错误信息
 * @param field
 * @param text
 */
function errorTip(field, text){
	var tx = "";
	var $f = $(field);
	if(text){
		tx = text;
	}
	$t = $f.next();
	$t.find('span').text(tx);
	$t.show();
}
/**
 * 域校验成功操作
 * @param field
 */
function successTip(field){
	var $f = $(field);
	$f.next().hide();
}