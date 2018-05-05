	function doLoad(){
	    var users=lshop.getCookieToJSON('user');
	    var shopcart=lshop.getCookieToJSON('shopcart');
		if(users.email!=null&&users.email!=''){
		 $('#nicknameId').text(users.nickname)
		 	   $("#nameId").show();
		       $("#logoutId").show();
		       $("#loginId").hide();
		       $("#registerId").hide();
		       $('#lvnicknameId').text(users.nickname);
	       	   $('#lasttimeId').html(users.lastTime.replace('+',' '));   
		 }
		//获取购物车数量
		$.ajax({
		   type: "get",
		   url: "/web/index!getShopCartNum.action?num="+Math.round(Math.random()*10000),
		   async:false,
		   success: function(num){
		   		$("#shopCartNum").html(num);
		   	}
		})

	}

	$(function(){doLoad();});	
	
	//登录弹出效果
	 function onshow(tx_id,div_id){
	    var tx_b=document.getElementById(tx_id);
	    tx_b.style.left=(window.screen.width/2-200)+"px";
	    tx_b.style.top=(10+document.documentElement.scrollTop||document.body.scrollTop)+'px';
	    if ((navigator.userAgent.indexOf('MSIE') >= 0) && (navigator.userAgent.indexOf('Opera') < 0)){
	    	//你是使用IE
	    }else if (navigator.userAgent.indexOf('Firefox') >= 0){
	    	//你是使用Firefox
	    }else if (navigator.userAgent.indexOf('Opera') >= 0){
	    	//你是使用Opera
	    }else{
	    	//你是使用其他的浏览器浏览网页！
	    	tx_b.style.top=(10+document.body.scrollTop)+'px';
		}
	    $(tx_b).fadeIn("fast",function(){});
	   	document.getElementById("loginDiv").style.height=document.body.clientHeight+"px";
	    document.getElementById('loginDiv').style.width=document.body.clientWidth+"px";
		$('#div_id').show(); 
		document.getElementById("loginDiv").style.display="block";
	 }
	 
	 function onhide(tx_id,div_id){
		$('#'+tx_id).hide();
		var tx_b=document.getElementById(tx_id);
		$(tx_b).fadeOut("fast",function(){});
		$('#div_id').hide();
		document.getElementById("loginDiv").style.display="none";
		$("#msg").html("");
	 }
	
	var isuccB=false;
	function checkEmailOne(){
		var email=$('#email');
		var eInfo=$('#eInfo')
		 if($.trim(email.val())==''){
			//eInfo.html("<font color='red'>email不能为空！</font>");
		 }else{
			 if(checkEmail(email.val())==false){
				eInfo.html("<font color='red'>email格式不正确！</font>");
			 }else{
				 $.ajax({   
					 url: '/web/userCenterManage!isExistsUser.action',
					 data:"lvAccount.email="+$.trim(email.val()),   
					 type: 'POST',     
					 success: function(num){   
					  if(num==1){
						  isuccB=false;
						  eInfo.html("<font color='red'>该邮箱已存在！</font>");
					   }else if(num==0){
						    isuccB=true;
						    eInfo.html("");
						   }
					 }   
					 });
			}
		 }
	}
	
	var isuccA=false;
	function checkNickOne(){
		var nickname=$('#nickname');
		var nInfo=$('#nInfo');
		 if($.trim(nickname.val())==''){
			// nInfo.html("<font color='red'>昵称不能为空！<font>");
		 }else{
			 var pat=new RegExp("[^a-zA-Z0-9\_\u4e00-\u9fa5]","i"); 
			 if(pat.test($.trim(nickname.val()))==true) 
			 { 
				//  nInfo.html("<font color='red'>昵称不能使用非法字符！<font>");
			 }else{
			 	$.ajax({   
					 url: '/web/userCenterManage!isExistsUser.action',
					 data:"lvAccount.nickname="+$.trim(nickname.val()),   
					 type: 'POST',     
					 success: function(num){   
					  if(num==1){
						  isuccA=false;
						  nInfo.html("<font color='red'>该昵称已存在！</font>");
					   }else if(num==0){
						    isuccA=true;
						    nInfo.html("");
						
						   }
					 }   
			    });
			 }
		 }
	}
	
	function toCar(storeDomain){
			location.href=storeDomain+"/web/mall!getShopCartList.action";
	}
	
	function toReg(storeDomain){
		onhide('tx_b','loginDiv');
		var url=window.location;
		window.location.href=storeDomain+"/web/userCenterManage!toRegister.action?jumpurl="+url;
	}
	
	$().ready(function() {	
	//注册
		$("#regedit").validate({
			 rules: {
			 	'lvAccount.nickname':{
			 		required: true,
				 	isUnlawful:true,
				 	minlength:2,
				 	maxlength:32,
				 	chrnum:true
			 	},
			   'lvAccount.pwd': {
					required: true,
					minlength: 6,
	                maxlength: 16
				},
				truePwd: {
					required: true,
					equalTo: "#rpwd"
				},
				'lvAccount.email':{
			 		required: true,
				 	maxlength:32
			 	}
			},
			messages: {
				'lvAccount.nickname': {
					required: "<font color='red'>请输入昵称！</font>",
					isUnlawful: "<font color='red'>昵称不能含有非法字符！</font>",
					minlength: "<font color='red'>昵称不能少于2位字符！</font>",
					maxlength: "<font color='red'>昵称不能大于32位字符！</font>",
					chrnum: "<font color='red'>昵称不能为中文！</font>"
				},
				'lvAccount.pwd': {
					required: "<font color='red'>请输入密码！</font>",
					minlength: "<font color='red'>密码不能少于6位字符！</font>",
					maxlength: "<font color='red'>密码不能大于16位字符！</font>"
				},
				truePwd: {
					required: "<font color='red'>请输入确认密码！</font>",
					equalTo: "<font color='red'>两次输入密码不一致！</font>"
				},
				'lvAccount.email': {
					required: "<font color='red'>请输入Email！</font>",
					maxlength: "<font color='red'>Email不能大于32位字符！</font>"
				}
			},
			
			submitHandler:function(form){
				
				var email=$('#email');
				var nickname=$('#nickname');
				var eInfo=$('#eInfo');
				var rpwd=$('#rpwd').val();
				var truePwd=$('#truePwd').val();
				var code=$('#rcode');
				var codeInfo=$('#codeInfo');
				if($.trim(code.val())==''){
					codeInfo.html("<font color='red'>请输入验证码！</font>");
					 return ;
				 }else{
					 	if(isuccA==true&&isuccB==true){
								$.post("/web/regeditAccount.action",$("#regedit").serialize(),function(str){
								var data = eval('(' + str + ')');
								codeInfo.html("");
								if(data.mark==0){
									bi_webcoll.tvpad_register("http://www.itvpad.com/web/regeditAccount.action",0,data.jumpurl);
									$("#rmsg").html("<font color='red'>恭喜您注册成功，系统将自动跳转到登录页面!</font>");
									window.location.href=data.jumpurl;
								}else if(data.mark==1){
									$("#rmsg").html("<font color='red'>验证码不正确!</font>");
								}else if(data.mark==2){
									$("#rmsg").html("<font color='red'>注册失败!</font>");
									bi_webcoll.tvpad_register("http://www.itvpad.com/web/regeditAccount.action",2,data.jumpurl);
								}
							});
						}
					}
	
	    	}
	
		});
		
	//登录	
		$("#login").validate({
			 rules: {
			 	uname: {
				 	required: true,
				 	minlength: 2,
				 	maxlength: 32
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
					maxlength: "<font color='red'>账户不能大于32位字符！</font>"
				},
				pwd: {
					required: "<font color='red'>请输入密码！</font>",
					minlength: "<font color='red'>密码不能小于6位字符！</font>",
					maxlength: "<font color='red'>密码不能大于16位字符！</font>"
				}
			},
			submitHandler:function(form){
				$.post("/web/userCenterManage!login.action",$("#login").serialize(),function(str){
					var data = eval('(' + str + ')');
					if(data.flag==0){
						onhide('tx_b');
						top.window.location.reload(true); 
					}else if(data.flag==1){
						$("#msg").html("<font color='red'>该账号已删除!</font>");
					}else if(data.flag==2){
						$("#msg").html("<font color='red'>该账号已停用!</font>");
					}else if(data.flag==3){
						$("#msg").html("<font color='red'>用户名或密码不正确!</font>");
					}
				});
				
				setTimeout("$('#msg').html('')", 3000);
	   		 }
	
		});
	});
	
	
	function addBookmark(title,url) {
		try{
        	window.external.addfavorite(url, title);
	    }catch (e){
	        try{
	            window.sidebar.addpanel(title, url, "");
	        }catch (e){
	            alert("加入收藏失败，请使用ctrl+d进行添加");
	        }
	    }
	}
	
	function toRe(storeDomain){
		var url=window.location;
		window.location.href=storeDomain+"/web/userCenterManage!toRegister.action?jumpurl="+url;
	}
	
	
	//提交搜索框
	function subSearch(str){
		if(str=="product"){
			$("#searchForm").attr("action","/web/store!searchProducts.action");
		}else{
			$("#searchForm").attr("action","/web/store!searchStores.action");
		}
	}
	
	
	//显示用户中心下拉列表
	function showMenu1(){
		$("#aa").attr("class","menu1");
	}
	
	//隐藏用户中心下拉列表
	var a=0,b=0,c=0;
	$(function(){
		$("#aa").mouseover(function(){a=0;}).mouseout(function(){a=1;showMenu2();});
		$("#bb").mouseover(function(){b=0;}).mouseout(function(){b=1;showMenu2();});
		$(".cc").mouseover(function(){c=0;}).mouseout(function(){c=1;showMenu2();});
		
		function showMenu2(){
				window.setTimeout(function(){
					if(a==1&&b==1&&c==1){
						$("#aa").attr("class","menu2");
					}
				},1000);
		}
	});
	
	//兼容性
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