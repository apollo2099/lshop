
/**************************找回密码*******************************************/
	//验证找回密码表单
	$(function(){
		if($("#findPassForm").size()>0){
			$("#findPassForm").validate({
				 rules: {
					'lvAccount.email': {
						required: true,
				   		email: true
				   	}
				},
				messages: {
					'lvAccount.email': {
						required: "<font color='red'>请输入email！</font>",
						email: "<font color='red'>email格式不正确！</font>"
					}
				},
				submitHandler:function(form){
					if(pwdFlag==true){
						form.submit();
			   		 }
		   		 }
		
			});
		}
	});
	
	//验证找回密码中邮箱是否存在
	var pwdFlag=false;
	function checkIsExistEmail(){
		var email=$('#emailId');
		var eInfo=$('#emailInfo');
		if($.trim(email.val())!='' && checkEmail(email.val())==true){
		 	$.ajax({   
			 url: '/web/userCenterManage!isExistsUser.action',
			 data:"lvAccount.email="+$.trim(email.val()),   
			 type: 'POST',
			 async: false,  
			 success: function(num){
			  if(num==1){
				   pwdFlag=true;
				   $("#emailInfo").html("");
			   }else if(num==0){
				    pwdFlag=false;
				    $("#emailInfo").html("<font color='red'>该email不存在！</font>");
			  }
			 }   
			});
		}else{
			$("#emailInfo").html("");
		}
	}


/**************************店铺导航*******************************************/	
	//获取所有的洲
	function getAllContinents(){
		if(data.length>=1){
			var str = "<ul>";
			$.each(data[0],function(n,continent) {
				str += "<li id='continent"+continent.id+"'><a href='javascript:getContinent("+continent.id+");'>"+continent.areaName+"</a></li>";
			});
			str += "</ul>";
			$("#continent").append(str);
		}	
	}
	
	function showUL(obj){
		$(obj).addClass("sekuai");
	}
	function hideUL(obj){
		$(obj).removeClass("sekuai");
	}


/**************************登录页面*******************************************/		
		//验证登录表单		
		$(function(){		
			if($("#loginPage").size()>0){
				$("#loginPage").validate({
					 rules: {
					 	uname: {
						 	required: true,
						 	minlength: 2,
						 	maxlength: 60
					 	},
			
					   pwd: {
							required: true,
							minlength: 6,
			                maxlength: 16
						}
					},
					messages: {
						uname: {
							required: "<font color='red'>请输入登录账户！</font>",
							minlength: "<font color='red'>账户不能小于2位字符！</font>",
							maxlength: "<font color='red'>账户不能大于60位字符！</font>"
						},
						pwd: {
							required: "<font color='red'>请输入密码！</font>",
							minlength: "<font color='red'>密码不能小于6位字符！</font>",
							maxlength: "<font color='red'>密码不能大于16位字符！</font>"
						}
					},
					submitHandler:function(form){
						var code=$('#rcode');
						var codeInfo=$('#codeInfo');
						if($.trim(code.val())==''){
							codeInfo.html("<font color='red'>请输入验证码！</font>");
							 return ;
						}else{
							$.post("/web/userCenterManage!login.action",$("#loginPage").serialize(),function(str){
								var data = eval('(' + str + ')');
								if(data.flag==0){
									window.location.href=data.jumpurl;
								}else if(data.flag==1){
									$("#msgg").html("<font color='red'>该账号已删除!</font>");
								}else if(data.flag==2){
									$("#msgg").html("<font color='red'>该账号已停用!</font>");
								}else if(data.flag==3){
									$("#msgg").html("<font color='red'>用户名或密码不正确!</font>");
								}else if(data.flag==4){
									$("#msgg").html("<font color='red'>验证码不正确!</font>");
								}
							});
						}
			   		 }
				});		
			}

		});
		



/**************************商城首页广告图*******************************************/
	$(document).ready(function(){
		//保证导航栏背景与图片轮播背景一起显示
		$("#mainbody").removeClass();
		$("#mainbody").addClass("index_bg01");
	
		//滚动Banner图片的显示
		$('#slides').slides({
			preload: false,
			preloadImage: '/images/loading.gif',
			effect: 'fade',
			slideSpeed: 400,
			fadeSpeed: 100,
			play: 3000,
			pause: 100,
			hoverPause: true
		});
        
		/*图片显示背景开始*/
		$(".new_product a").hover(function(){
			$(this).children("span").fadeIn();
		},function(){
			$(this).children("span").fadeOut();
		})
		//$('#js-news').ticker();
    });