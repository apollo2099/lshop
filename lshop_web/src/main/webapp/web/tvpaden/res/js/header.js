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
	 
	 //从cookie中获取已选择的店铺信息
/**	 var store = lshop.getCookieToJSON('store');
	 if(store.name!=null){
	 	$("#selectStore").html("Go to&nbsp;<a href='http://"+store.domainName+"/index.html' target='_blank'><font color='#0093df'>"+store.name+"</font></a>");
	 }else{
	 	$("#selectStore").html("Find a TVpad Retail Store");
	 }
**/
}

$(function(){doLoad();});

/**
 * //定义全局变量，存放从JS获取的店铺区域信息
var data; //0，所有的洲列表 1，所有的店铺列表 2，洲组合列表
$(function(){
	$.ajax({
	   type: "get",
	   url: "${resDomain}/tvpaden/res/js/storeArea.js",
	   dataType:"JSON",
	   async:false,
	   success: function(jsonData){
	   		data=eval(jsonData);
	   		$('.xuanze').mouseover(showContinent);
	   	}
	})
});
**/


//展示洲
function showContinent(){
	if(data.length>=1){
		var str = "";
		$.each(data[0],function(n,continent) { 
			str += "<div class='dp_menu1' id='cont"+continent.id+"' >";
			str += "<h2 class='zhou' onmouseover='showCountry("+continent.id+");'><a href='javascript:showCountry("+continent.id+");'>"+continent.areaName+"</a></h2>";
			str += "<ul id='coun"+continent.id+"' class='con' style='display:none;'>";
			$.each(data[2],function(n,obj) {
				if(obj[0].id==continent.id){
					$.each(obj[1],function(n,country) {
						str += "<li onmouseover='showCity("+country.id+",event);'><a href='javascript:showCity("+country.id+",event);'>"+country.areaName+"</a></li>";
					});
				}
			});
			str += "</ul>";
			str += "</div>";
		});

		$("#menu_country").html(str);
		$("#menu_country").show(function(){
			  $(".zhou").click(function(){
				  var obj=$(this).next("ul");
				  $(".dp_menu>div>ul:visible").hide();
				  obj.slideDown();
				  
			  });
		});
	}
}


//焦点在洲
function showCountry(id){
	$("#menu_store").hide();
	$("#coun"+id).show();
	var prevDiv = $("#cont"+id).prevAll().find("ul");
	var nextDiv = $("#cont"+id).nextAll().find("ul");
	if(prevDiv!=null){
		prevDiv.hide();
	}
	if(nextDiv!=null){
		nextDiv.hide();
	}

}

//焦点在国家
function showCity(id,event){
	var str = "";
	$.each(data[3],function(n,obj) {
		if(obj[0].id==id){
			$.each(obj[1],function(n,city) {
				str += "<dl>";
				str += "<dt>"+city.areaName+"</dt>";
				$.each(data[4],function(n,ob) {
					if(ob[0].id==city.id){
						str += "<dd>";
						$.each(ob[1],function(a,store) {
							var tmp = "javascript:saveCookieForStore('"+store.code+"','"+store.name+"','"+store.domainName+"');";
							var oldName = store.name;
							var newName = store.name;
							if(oldName.length>5){
								newName = oldName.substring(0, 5)+"…";
							}
							if(store.isHot==1){
								str += "<a href="+tmp+" title='"+oldName+"'><font color='red'>"+newName+"</font></a>";
							}else{
								str += "<a href="+tmp+" title='"+oldName+"'>"+newName+"</a>";
							}
							if(a<ob[1].length-1){
								str += " |";
							}
						});
						str += "</dd>";
					}
				});
				str += "</dl>";
			});
		}
	});
	
	$("#menu_store").html(str);
	$("#menu_store").show(function(){
		 var num=$(".dp_menu2>dl").length;
		  if(num<3){
			  $(".dp_menu2").css("width",(802-(3-num)*263));
		  }else{
			  $(".dp_menu2").css("width",802);
			  
		  }
	});
	var e = event || window.event;	
	var top = e.pageY || e.y+120;
	$("#menu_store").css("top",top-180);
}

//点击店铺时，保存店铺信息至cookie中
function saveCookieForStore(code,name,domainName){
	$.ajax({
	   type: "POST",
	   url: "/web/store!saveCookieForStore.action",
	   data: "code="+code+"&name="+name+"&domainName="+domainName,
	   async: false,
	   success: function(){
	   		var url = "http://"+domainName+"/index.html";
			window.open(url);
	   	 }
	});
}


/**
 * 隐藏店铺区域导航
 */
$(function(){
  var m1=0,m2=0,m3=0;
  if($('.sc_menu_A_r'))
  $('.sc_menu_A_r').mouseover(function(){
	  m1=1;
  }).mouseout(function(){
	  m1=0;
	  window.setTimeout(function(){onhideall();},3000);
  });
  if($('.dp_menu'))
  $('.dp_menu').mouseover(function(){
	  m2=1;
  }).mouseout(function(){
	  m2=0;
	  onhideall();
  });
  if($('.dp_menu2'))
  $('.dp_menu2').mouseover(function(){
	  m3=1;
	  
  }).mouseout(function(){
	  m3=0;
	  onhideall();
  });
  function onhideall(){
		if(m1==0&&m2==0&&m3==0){
			window.setTimeout(function(){
				if(m1==0&&m2==0&&m3==0){
					$('.dp_menu').hide();
					$('.dp_menu2').hide();
				}
			},1000);
		}
	}
});


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
		//eInfo.html("<font color='red'>email不能為空！</font>");
	 }else{
		 if(checkEmail(email.val())==false){
			eInfo.html("<font color='red'>Invalid E-mail format!</font>");
		 }else{
			 $.ajax({   
				 url: '/web/userCenterManage!isExistsUser.action',
				 data:"lvAccount.email="+$.trim(email.val()),   
				 type: 'POST',     
				 success: function(num){   
				  if(num==1){
					  isuccB=false;
					  eInfo.html("<font color='red'>This E-mail address is already existed!</font>");
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
					  nInfo.html("<font color='red'>This nickname is already existed!</font>");
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

//顶部登录验证
function subHeaderLoginForm() {
	$("#h_jumpurl").val(window.location.href);
	return checkHeaderEmail() && checkHeaderPassword();
}

function checkHeaderEmail() {
	if (js.assert.isNull("h_uname", 'Please enter account!', "h_emsg")) {return false;}
	if (js.assert.isNotEmail("h_uname", 'Account format error!', "h_emsg")) {return false;}
	if (js.assert.isIllegalLength("h_uname", "<2||>32", 'Account format error!', "h_emsg")) {return false;}
	return true;
}

function checkHeaderPassword() {
	if (js.assert.isNull("h_pwd", 'Please enter your password!', "h_emsg")) {return false;}
	if (js.assert.isIllegalLength("h_pwd", "<6||>16", 'Password can only be 6-16 characters!', "h_emsg")) {return false;}
	return true;
}


function addBookmark(title,url) {
		try{
        	window.external.addfavorite(url, title);
	    }catch (e){
	        try{
	            window.sidebar.addpanel(title, url, "");
	        }catch (e){
	           alert("Add to Favorites failed! Please press 'Ctrl+d' to add it.");
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
			if($.trim(searchContent.val())=='Please enter item name'){
			  searchContent.val("");
		    }
		    form.submit();
		}
		
	});
});

//提交搜索框
function subSearch(str,storeDomain){
	if(str=="product"){
		$("#searchForm").attr("action",storeDomain+"/web/store!searchProducts.action");
	}else{
		$("#searchForm").attr("action",storeDomain+"/web/store!searchStores.action");
	}
}
