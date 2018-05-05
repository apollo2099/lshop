
function doLoad(){
	    var users=lshop.getCookieToJSON('user');
	    var shopcart=lshop.getCookieToJSON('shopcart');
		if(users.email!=null&&users.email!=''){
		 $('#nicknameId').text(users.nickname)
		 	   $("#nameId").show();
		       $("#logoutId").show();
		       $("#loginId").hide();
		       $("#registerId").hide();
		       $('#c_uc_menu').show();
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
		 
/**		 //从cookie中获取已选择的店铺信息
		 var store = lshop.getCookieToJSON('store');
		 if(store.name!=null){
		 	$("#selectStore").html("前往&nbsp;<a href='http://"+store.domainName+"/index.html' target='_blank'><font color='#0093df'>"+store.name+"</font></a>");
		 }else{
		 	$("#selectStore").html("選擇您身邊的TVpad專賣店");
		 }
**/	
	}	
	
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
		var eInfo=$('#eInfo');
		 if($.trim(email.val())==''){
			eInfo.html("<font class='w_eInfo'>email不能為空！</font>");
		 }else{
			 if(checkEmail(email.val())==false){
				eInfo.html("<font class='w_eInfo'>email格式不正確！</font>");
			 }else{
				 $.ajax({   
					 url: '/web/userCenterManage!isExistsUser.action',
					 data:"lvAccount.email="+$.trim(email.val()),   
					 type: 'POST',     
					 success: function(num){   
					  if(num==1){
						  isuccB=false;
						  eInfo.html("<font color='red'>該郵箱已存在！</font>");
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
			// nInfo.html("<font color='red'>昵稱不能為空！<font>");
		 }else{
			 var pat=new RegExp("[^a-zA-Z0-9\_\u4e00-\u9fa5]","i"); 
			 if(pat.test($.trim(nickname.val()))==true) 
			 { 
				//  nInfo.html("<font color='red'>昵稱不能使用非法字符！<font>");
			 }else{
			 	$.ajax({   
					 url: '/web/userCenterManage!isExistsUser.action',
					 data:"lvAccount.nickname="+$.trim(nickname.val()),   
					 type: 'POST',     
					 success: function(num){   
					  if(num==1){
						  isuccA=false;
						  nInfo.html("<font color='red'>該昵稱已存在！</font>");
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
		var index = url.href.indexOf("jumpurl=");
		if(index==-1){
			window.location.href=storeDomain+"/web/userCenterManage!toLoginRegister.action?jumpFlag=2&jumpurl="+url+"";
		}else{
			var newUrl=url.href.substring(index,url.length);
			window.location.href=storeDomain+"/web/userCenterManage!toLoginRegister.action?jumpFlag=2&"+newUrl+"";
		}
	}
	
	function toLogin(storeDomain){
		onhide('tx_b','loginDiv');
		var url=window.location;
		var index = url.href.indexOf("jumpurl=");
		if(index==-1){
			window.location.href=storeDomain+"/web/userCenterManage!toLoginRegister.action?jumpFlag=1&jumpurl="+url+"";
		}else{
			var newUrl=url.href.substring(index,url.length);
			window.location.href=storeDomain+"/web/userCenterManage!toLoginRegister.action?jumpFlag=1&"+newUrl+"";
		}
	}
		
	// 顶部登录验证
	function subHeaderLoginForm() {
		$("#h_jumpurl").val(window.location.href);
		return checkHeaderEmail() && checkHeaderPassword();
	}
	
	function checkHeaderEmail() {
		if (js.assert.isNull("h_uname", '請輸入賬號！', "h_emsg")) {return false;}
		if (js.assert.isIllegalLength("h_uname", "<2||>60", '賬號只能是2至60位字符！', "h_emsg")) {return false;}
		return true;
	}
	
	function checkHeaderPassword() {
		if (js.assert.isNull("h_pwd", '請輸入密碼！', "h_emsg")) {return false;}
		if (js.assert.isIllegalLength("h_pwd", "<6||>16", '密碼只能是6至16位字符！', "h_emsg")) {return false;}
		return true;
	}
	
	
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
		var index = url.href.indexOf("jumpurl=");
		if(index==-1){
			window.location.href=storeDomain+"/web/userCenterManage!toLoginRegister.action?jumpFlag=2&jumpurl="+url+"";
		}else{
			var newUrl=url.href.substring(index,url.length);
			window.location.href=storeDomain+"/web/userCenterManage!toLoginRegister.action?jumpFlag=2&"+newUrl+"";
		}
	}
	
	
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
	
$(function(){
	//微信二维码引出框
	$('#c_top_weixin_btn').click(function(e){
		$('#c_wx_panel').show();
	});
	$('#c_wx_panel').find('a').click(function(e){
		$('#c_wx_panel').hide();
	});
	//显示,隐藏用户中心下拉列表
	$('#c_uc_menu').hover(function(e){
		$(this).removeClass('menu2').addClass('menu1').find('.list').show();
	}, function(e){
		$(this).addClass('menu2').removeClass('menu1').find('.list').hide();
	});
	//显示隐藏导航菜单
	$('#c_nav_menu').delegate('.more-menu', 'mouseenter', function(e){
		$(this).find('ul').show();
	}).delegate('.more-menu', 'mouseleave', function(e){
		$(this).find('ul').hide();
	});
	//搜索输入框点击事件
	$('#searchContent').one('click', function(e){
		$('#searchContent').css('color', '#636363').val('');
	});
	doLoad();
	//弹出框登陆
	$("#divlogin").validate({
		submitHandler:function(form){
			$("#h_emsg").html("");
			var currentUrl = window.location.href;
			if(subHeaderLoginForm()){
				$.post("/web/userCenterManage!login.action",$("#divlogin").serialize(),function(str){
					var data = eval('(' + str + ')');
					var f = data.mark;
					if(f==1){
						var gotourl = data.jumpurl;
						gotourl = ((currentUrl.indexOf('?jumpurl')>-1)||(currentUrl.indexOf('@jumpurl')>-1))?gotourl:"";
						change2LoginStatu(gotourl);
						$("#divlogin").trigger('userLoginSuccess');//用户登陆成功事件
						return;
					}else if(f==-1){
						$("#h_emsg").html("賬號不存在");
					}else if(f==-2){
						$("#h_emsg").html("賬號已停用");
					}else if(f==-3){
						$("#h_emsg").html("賬號未激活");
					}else if(f==-4){
						$("#h_emsg").html("密碼錯誤");
					}else if(f==0){
						$("#h_emsg").html("系統錯誤");
					}
				});
			}
		}
	});
	
	//页面登陆
	$("#login").validate({
		submitHandler:function(form){
			$("#msg1").html("");
			if(subLoginForm()){
				$.post("/web/userCenterManage!login.action",$("#login").serialize(),function(str){
					var data = eval('(' + str + ')');
					var f = data.mark;
					if(f==1){
						var gotourl = data.jumpurl;
						change2LoginStatu(gotourl);
						return;
					}else if(f==-1){
						$("#msg1").html("賬號不存在");
					}else if(f==-2){
						$("#msg1").html("賬號已停用");
					}else if(f==-3){
						$("#msg1").html("賬號未激活");
					}else if(f==-4){
						$("#msg1").html("密碼錯誤");
					}else if(f==-5){
						$("#msg1").html("驗證碼錯誤");
					}else if(f==0){
						$("#msg1").html("系統錯誤");
					}
					document.getElementById('rcloginId').src=document.getElementById('rcloginId').src+'?'+new Date();
				});
			}
		}
	});
	
	//页面注册
	$("#regedit").validate({
		submitHandler:function(form){
			if(registValidate()){
				$.post("/web/regeditAccount.action",$("#regedit").serialize(),function(str){
					var data = eval('(' + str + ')');
					if(data.mark==0){//注册成功
						var gotourl = data.jumpurl;
						change2LoginStatu(gotourl);
						return;
					}else if(data.mark==2){
						$("#msgg").html("註冊失敗");
					}else if(data.mark==-2){
						$("#msgg").html("驗證碼錯誤");
					}
					$("#erro_msg0").css("display","block");
					$("input[name='code']").val("");
					//$("#registerbut").removeClass("user_reg_bt_disable");
					document.getElementById('rcId').src=document.getElementById('rcId').src+'?'+new Date();
				});
			}
		}
	});
});
	
function change2LoginStatu(gotourl){
	if(gotourl==''){
		onhide('tx_b','loginDiv');
		var users=lshop.getCookieToJSON('user');
		var shopcart=lshop.getCookie('shopcartNum');
	    $('#nicknameId').text(users.nickname);
	    $("#loginId").hide();
        $("#registerId").hide();
 	    $("#nameId").show();
        $("#logoutId").show();
        $('#c_uc_menu').show();
        $("#shopCartNum").html(shopcart);
	}else{
		window.location.href=gotourl;
	}
}


function textFocus(id){
	var text = $("#" + id).val();
	var dvalue = $("#" + id).attr("dvalue");
	if(text==dvalue){
		$("#" + id).val('');
	}
	$("#" + id + "_tip2").css("display","none");
	$("#" + id + "_tip1").css("display","block");
}