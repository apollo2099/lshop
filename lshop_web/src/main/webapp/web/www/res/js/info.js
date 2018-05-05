
	//判断是否含有中文字符	
	function isChineseChar(str){   
	   var reg = /[\u4E00-\u9FA5\uF900-\uFA2D]/;
	   return reg.test(str);
	}
	
	//表单验证
	$().ready(function() {
		$("#infoForm").validate({
			rules: {
				'lvAccount.nickname':{required: true,isUnlawful:true,maxlength:32},
			    'lvAccountInfo.qq':{qq:true,maxlength:16},
			    'lvAccountInfo.msn':{msn:true,maxlength:32},
				'lvAccountInfo.name':{required: true,isUnlawful:true,maxlength:32},
				'lvAccountInfo.tel':{isChineseChar:true,maxlength:16},
			    'lvAccountInfo.mobile':{isChineseChar:true,maxlength:16},
				'lvAccountInfo.postCode':{required: true,isChineseChar:true,maxlength:16}
			},
			messages: {
				'lvAccount.nickname': {
					required: "<font color='red'>请输入昵称</font>",
					isUnlawful: "<font color='red'>昵称不能含有非法字符</font>",
				 },
				 'lvAccountInfo.qq': {
					 qq: "<font color='red'>qq號碼格式錯誤</font>",
					maxlength: "<font color='red'>qq號碼不能大于16位字符</font>"
				 },
				 'lvAccountInfo.msn': {
					 msn: "<font color='red'>msn格式錯誤</font>",
					maxlength: "<font color='red'>msn不能大于32位字符</font>"
				 },
				'lvAccountInfo.name': {
					required: "<font color='red'>请输入真實姓名</font>",
					isUnlawful: "<font color='red'>真實姓名不能含有非法字符</font>",
					maxlength: "<font color='red'>真實姓名不能大于32位字符</font>"
				 },
				 'lvAccountInfo.tel': {
					 isChineseChar: "<font color='red'>電話不能含有特殊字符</font>",
					 maxlength: "<font color='red'>電話不能大于16位字符</font>"
				 },
				 'lvAccountInfo.mobile': {
					isChineseChar: "<font color='red'>手機不能含有特殊字符</font>",
					maxlength: "<font color='red'>手機不能大于16位字符</font>"
				 },
				'lvAccountInfo.postCode': {
					required: "<font color='red'>请输入郵政區號</font>",
					isChineseChar: "<font color='red'>郵政區號不能含有特殊字符</font>",
					maxlength: "<font color='red'>郵政區號不能大于16位字符</font>"
				 }
			},
			submitHandler:function(form){
				var tel=$("#tel");
				var mobile=$("#mobile");
				if($.trim(tel.val())==''&&$.trim(mobile.val())==''){
				  $("#msgbox").html('電話和手機必須填寫其中一個');
				  mobile.focus();
				  return;
			    }
				clearDefaultValue();	// 清除 “洲/省”、“縣/市”这类默认值
			    form.submit();
			}
		});
	});