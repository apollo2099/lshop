//判断浏览器兼容性	
	var isIE = false;
	var isFF = false;
	var isSa = false;
	
	if ((navigator.userAgent.indexOf("MSIE")>0) && (parseInt(navigator.appVersion) >=4))isIE = true;
	if(navigator.userAgent.indexOf("Firefox")>0)isFF = true;
	if(navigator.userAgent.indexOf("Safari")>0)isSa = true; 
	
	function onlyNumber(e)
	{
	    var key;
	    iKeyCode = window.event?e.keyCode:e.which;
	    if( !(((iKeyCode >= 48) && (iKeyCode <= 57)) || (iKeyCode == 13) || (iKeyCode == 46) || (iKeyCode == 45) || (iKeyCode == 37) || (iKeyCode == 39) || (iKeyCode == 8)))
	    {    
	        if (isIE)
	        {
	            e.returnValue=false;
	        }
	        else
	        {
	            e.preventDefault();
	        }
	    }
	} 

	//判断是否含有中文字符	
	function isChineseChar(str){   
	   var reg = /[\u4E00-\u9FA5\uF900-\uFA2D]/;
	   return reg.test(str);
	}
	

	//验证用户名唯一性
	var editInfoFlag=true;
	function checkEditNick(){
		var nickname=$('#editNickName');
		var nInfo=$('#editNickInfo');
		 if($.trim(nickname.val())==''){
			 nInfo.html("");
		 }else{
			 var pat=new RegExp("[^a-zA-Z0-9\_\u4e00-\u9fa5]","i"); 
			 if(pat.test($.trim(nickname.val()))==true) 
			 { 
				 nInfo.html("");
			 }
				 $.ajax({   
					 url: '/web/userCenterManage!isExistsUser.action',
					 data:"lvAccount.nickname="+$.trim(nickname.val()),   
					 type: 'POST',     
					 success: function(num){   
					  if(num==1){
						   editInfoFlag=false;
						  nInfo.html("<font color='red'>该昵称已存在！</font>");
					   }else if(num==0){
						    editInfoFlag=true;
						    nInfo.html("");
						
						   }
					 }   
			    });
		 }
	}
	//提交表单信息
	function subForm(){
		//给国家名称赋值
		$("#contrynameId").val($("#contryId").find("option:selected").text());
		$("#infoForm").submit();
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
					 qq: "<font color='red'>qq号码格式错误</font>",
					maxlength: "<font color='red'>qq号码不能大于16位字符</font>"
				 },
				 'lvAccountInfo.msn': {
					 msn: "<font color='red'>msn格式错误</font>",
					maxlength: "<font color='red'>msn不能大于32位字符</font>"
				 },
				'lvAccountInfo.name': {
					required: "<font color='red'>请输入真实姓名</font>",
					isUnlawful: "<font color='red'>真实姓名不能含有非法字符</font>",
					maxlength: "<font color='red'>真实姓名不能大于32位字符</font>"
				 },
				 'lvAccountInfo.tel': {
					 isChineseChar: "<font color='red'>电话不能含有特殊字符</font>",
					 maxlength: "<font color='red'>电话不能大于16位字符</font>"
				 },
				 'lvAccountInfo.mobile': {
					isChineseChar: "<font color='red'>手机不能含有特殊字符</font>",
					maxlength: "<font color='red'>手机不能大于16位字符</font>"
				 },
				'lvAccountInfo.postCode': {
					required: "<font color='red'>请输入邮政区号</font>",
					isChineseChar: "<font color='red'>邮政区号不能含有特殊字符</font>",
					maxlength: "<font color='red'>邮政区号不能大于16位字符</font>"
				 }
			},
			submitHandler:function(form){
				$("#telInfo2").css("display","none");
				$("#mobileInfo2").css("display","none");
				
				var tel=$("#tel");
				var mobile=$("#mobile");
				if($.trim(tel.val())==''&&$.trim(mobile.val())==''){
				  $("#telInfo1").css("display","none");
				  $("#mobileInfo1").css("display","none");
				  $("#telInfo2").css("display","block");
				  $("#mobileInfo2").css("display","block");
				  tel.focus();
				  return ;
			    }
			    form.submit();
			}
		});
	});