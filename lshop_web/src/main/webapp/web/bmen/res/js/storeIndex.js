
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
						required: "<font color='red'>Please enter your E-mail address!</font>",
							email: "<font color='red'>Invalid E-mail format!</font>"
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
				    $("#emailInfo").html("<font color='red'>Sorry, the email address doesn’t exist!</font>");
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
							required: "<font color='red'>Please enter your login account!</font>",
							minlength: "<font color='red'>The account shall be at least 2 characters!</font>",
							maxlength: "<font color='red'>The account shall not be longer than 60 characters!</span></font>"
						},
						pwd: {
							required: "<font color='red'>Please enter the password!</font>",
							minlength: "<font color='red'>Password shall be at least 6 characters!</font>",
							maxlength: "<font color='red'>Password shall not be longer than 16 characters!</span></font>"
						}
					},
					submitHandler:function(form){
						var code=$('#rcode');
						var codeInfo=$('#codeInfo');
						if($.trim(code.val())==''){
							codeInfo.html("<font color='red'>Please enter the Auth Code!</font>");
							 return ;
						}else{
							$.post("/web/userCenterManage!login.action",$("#loginPage").serialize(),function(str){
								var data = eval('(' + str + ')');
								if(data.flag==0){
									window.location.href=data.jumpurl;
								}else if(data.flag==1){
									$("#msgg").html("<font color='red'>This account has been removed!</font>");
								}else if(data.flag==2){
									$("#msgg").html("<font color='red'>This account is disabled!</font>");
								}else if(data.flag==3){
									$("#msgg").html("<font color='red'>Invalid username or password!</font>");
								}else if(data.flag==4){
									$("#msgg").html("<font color='red'>Invalid Auth Code!</font>");
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