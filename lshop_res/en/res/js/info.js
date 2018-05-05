		
		//判断是否含有中文字符	
		function isChineseChar(str){   
		   var reg = /[\u4E00-\u9FA5\uF900-\uFA2D]/;
		   return reg.test(str);
		}
		
		//表单验证
		$().ready(function() {
			$("#myform").validate({
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
						required: "<font color='red'>Please enter your nickname!</font>",
						isUnlawful: "<font color='red'>Invalid characters in nickname!</font>",
					 },
					 'lvAccountInfo.qq': {
						 qq: "<font color='red'>QQ number format incorrect!</font>",
						maxlength: "<font color='red'>QQ number shall not be longer than 16 characters!</font>"
					 },
					 'lvAccountInfo.msn': {
						 msn: "<font color='red'>MSN format incorrect!</font>",
						maxlength: "<font color='red'>MSN shall not be longer than 32 characters!</font>"
					 },
					'lvAccountInfo.name': {
						required: "<font color='red'>Please enter your real name!</font>",
						isUnlawful: "<font color='red'>Invalid characters in real name!</font>",
						maxlength: "<font color='red'>Real Name shall not be longer than 32 characters!</font>"
					 },
					 'lvAccountInfo.tel': {
						 isChineseChar: "<font color='red'>Phone No. shall not cover special characters!</font>",
						 maxlength: "<font color='red'>Phone No. shall not be longer than 16 characters!</font>"
					 },
					 'lvAccountInfo.mobile': {
						isChineseChar: "<font color='red'>Mobile No. shall not cover special characters!</font>",
						maxlength: "<font color='red'>Mobile No. shall not be longer than 16 characters!</font>"
					 },
					'lvAccountInfo.postCode': {
						required: "<font color='red'>Please enter the Zip Code!</font>",
						isChineseChar: "<font color='red'>Zip Code shall not cover special characters!</font>",
						maxlength: "<font color='red'>Zip Code shall not be longer than 16 characters!</font>"
					 }
				},
				submitHandler:function(form){
					var tel=$("#tel");
					var mobile=$("#mobile");
					if($.trim(tel.val())==''&&$.trim(mobile.val())==''){
					  $("#msgbox").html("You must fill in a best contact number we can reach you at least.");
					  mobile.focus();
					  return ;
				    }
					clearDefaultValue();	// 清除 “洲/省”、“縣/市”这类默认值
				    form.submit();
				}
			});
		});
		
		
		
		
		