

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
		 
/**		 //从cookie中获取已选择的店铺信息
		 var store = lshop.getCookieToJSON('store');
		 if(store.name!=null){
		 	$("#selectStore").html("前往&nbsp;<a href='http://"+store.domainName+"/index.html' target='_blank'><font color='#0093df'>"+store.name+"</font></a>");
		 }else{
		 	$("#selectStore").html("選擇您身邊的TVpad專賣店");
		 }
**/	
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
		window.location.href=storeDomain+"/web/userCenterManage!toRegister.action?jumpurl="+url;
	}
		
	// 顶部登录验证
	function subHeaderLoginForm() {
		$("#h_jumpurl").val(window.location.href);
		return checkHeaderEmail() && checkHeaderPassword();
	}
	
	function checkHeaderEmail() {
		if (js.assert.isNull("h_uname", '請輸入賬號！', "h_emsg")) {return false;}
		if (js.assert.isNotEmail("h_uname", '賬號格式錯誤！', "h_emsg")) {return false;}
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
		window.location.href=storeDomain+"/web/userCenterManage!toRegister.action?jumpurl="+url;
	}
	
	$().ready(function() {
		$("#searchForm").validate({
			submitHandler:function(form){
				var searchContent=$("#searchContent");
				if($.trim(searchContent.val())=='請輸入搜索商品'){
				  searchContent.val("");
			    }
			    form.submit();
			}
			
		});
	});
	
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
	
	
	
	
	
	
	
	
	