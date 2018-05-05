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
		$("#erro_msg").css("display","none");
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
	
	function toCar(storeDomain){
			location.href=storeDomain+"/web/mall!getShopCartList.action";
	}
	
	function toReg(storeDomain){
		onhide('tx_b','loginDiv');
		var url=window.location;
		window.location.href=storeDomain+"/web/userCenterManage!toRegister.action?jumpurl="+url;
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
	
	function toFloat(x) {  
	    var f = parseFloat(x);  
	    if (isNaN(f)) {  
	        return false;  
	    }  
	    var f = Math.round(x*100)/100;  
	    return f;
	} 
	
	//制保留2位小数，如：2，会在2后面补上00.即2.00  
	function toFloat2(f){
		var s = f.toString();  
	    var rs = s.indexOf('.');  
	    if (rs < 0) {  
	        rs = s.length;  
	        s += '.';  
	    }  
	    while (s.length <= rs + 1) {  
	        s += '0';  
	    }  
	    return s;  
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
	
	
	
	