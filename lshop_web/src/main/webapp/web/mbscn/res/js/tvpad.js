// JavaScript Document
$(function(){
	 //加减
	var cut=$(".spanblock").find(".cut");	
	var add=$(".spanblock").find(".add");
	$.each(cut,function(index,obj) {
       $(obj).click(function(){
		var cont=$(this).next(".zhi").text();
		var conts=parseInt(cont);
			if(conts==1){
			 $(this).next(".zhi").text(1);
			 return false;
			 }else{
				conts--;
			   $(this).next(".zhi").text(conts);
			  	 
			}
		
		});
    });
	
	$.each(add,function(index,obj) {
      $(obj).click(function(){
		var cont=$(this).prev().text();
		var conts=parseInt(cont);
			if(conts<=9999){
				conts++;
				$(this).prev().text(conts);
				}else{
					return false;
					}
		
		}); 
    });

    checksubmint();		
	
	$(".fastchannel").click(function(){
		center($(".maketips"));
		$(this).attr("id","fastchannel");
	   // $("body").css("overflow","hidden");
	});
	$("#dele,.bttip").click(function(){
	   center($(".mark_tip"));
	  
	}); 
	$(".bt_marktip").find("a").eq(1).click(function(){
			 $(".mark_tip").hide();
			 $(".mark1").hide();
			 $(window).unbind("scroll");
			  $(window).unbind("resize");
			  
			  
	});
	
	
//当前浏览器窗口的 宽高度
 function center(obj){
	var screenWidth = $(window).width(), screenHeight = $(window).height();
	var objLeft = (screenWidth - obj.width())/2 ;
    var objTop = (screenHeight - obj.height())/2;
    var srollTop=$(document).scrollTop();
    obj.css({left:objLeft+"px",top:objTop+srollTop+"px","display":"block"});
	 $(".mark1").show();
	
$(window).resize(function(){
	var screenWidth = $(window).width(), screenHeight = $(window).height();
	var objLeft = (screenWidth - obj.width())/2 ;
    var objTop = (screenHeight - obj.height())/2;
	var srollTop=$(document).scrollTop();
   obj.css({left:objLeft+"px",top:objTop+srollTop+"px","display":"block"});
	});
 $(window).scroll(function(){
	var screenWidth = $(window).width(), screenHeight = $(window).height();
	var objLeft = (screenWidth - obj.width())/2 ;
    var objTop = (screenHeight - obj.height())/2;
	var srollTop=$(document).scrollTop();
    obj.css({left:objLeft+"px",top:objTop+srollTop+"px","display":"block"});
	});
	
	}

})

//全选
  function chkall(){
  var chekboxs=document.getElementsByName("chk_list");
  var chk_all=document.getElementById("chk_all");
  if(chekboxs){
	  for(var i=0;i<chekboxs.length;i++){
		  chekboxs[i].checked=chk_all.checked;
		  }
	  }
  }     
//表单验证   
function checksubmint(){
	 var email=/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
	 var pwd=/^[\@A-Za-z0-9\!\#\$\%\^\&\*\.\~]{6,22}$/;
	 var renname=/^([a-zA-Z ]+|[\u4e00-\u9fa5]+){1,32}$/;
	 var phone=/13[0-9]{9}/;
	 var tel=/(\(\d{3,4}\)|\d{3,4}-|\s)?\d{8}/;
	 var zipcode=/[1-9]{1}(\d+){5,16}/;
	 var Payer=/\w{1,32}/; 
	 var Amount=/^\d+\.{0,1}\d{1,11}$/
	 var Country=/\w{1,32}/;
	 var City1=/w{1,48}/;
	 var date=/^\d{4}(\-|\/|\.)\d{1,2}\1\d{1,2}$/;
	 var mtcn=/w{1,10}/;
	 var username=/[0-9A-Za-z]{2,32}/;
	 var truusername=/[0-9A-Za-z]{1,32}/;
	 var teare=/^([\u4e00-\u9fa5]|[0-9]|[,]|[，]|[“]){5,200}$/;
	 var Confirmpwd;
	     
		 $("#teare").focus(function(){
			 $(this).html("");
			 });		 
		 $("#teare").blur(function(){
			 if(teare.test($(this).text())){
				 $(this).parent().next(".tip").hide();
				 return true;
				 }else{
					 $(this).parent().next(".tip").show();
					 return false;
					 }
			 });
		 
		 
		   $("input[name=truusername]").focus(function(){
		 if($(this).val()==$(this).attr("defalt")){
		   $(this).val("");	 
		 }
		 }); 
	  $("input[name=truusername]").blur(function(){
		  if($(this).val()==""){
			  $(this).val($(this).attr("defalt"));	 
			  return false;
			  }else if(truusername.test($(this).val())){
			 
			  $(this).next(".tip").hide();
			  return true;
			  }else{
				  $(this).next(".tip").show();
				  return false;
				  }
		  });	  
	 
	    
		  $("input[name=username]").focus(function(){
		 if($(this).val()==$(this).attr("defalt")){
		   $(this).val("");	 
		 }
		 }); 
	  $("input[name=username]").blur(function(){
		  if($(this).val()==""){
			  $(this).val($(this).attr("defalt"));	 
			  return false;
			  }else if(username.test($(this).val())){
			 
			  $(this).next(".tip").hide();
			  return true;
			  }else{
				  $(this).next(".tip").show();
				  return false;
				  }
		  });	  
		
		 $("input[name=mtcn]").blur(function(){
		  if(mtcn.test($(this).val())){
			  $(this).next(".tip").hide();
			  return true;
			  }else{
				  $(this).next(".tip").show();
				  return false;
				  }    
		  });	
		
		 $("input[name=date]").blur(function(){
		  if(date.test($(this).val())){
			  $(this).next(".tip").hide();
			  return true;
			  }else{
				  $(this).next(".tip").show();
				  return false;
				  }    
		  });	  
	 
	    $("input[name=City1]").blur(function(){
		  if(City1.test($(this).val())){
			  $(this).next(".tip").hide();
			  return true;
			  }else{
				  $(this).next(".tip").show();
				  return false;
				  }    
		  });	  
	    
		$("input[name=Country]").blur(function(){
		  if(Country.test($(this).val())){
			  $(this).next(".tip").hide();
			  return true;
			  }else{
				  $(this).next(".tip").show();
				  return false;
				  }    
		  });	  
		
		
		$("input[name=Amount]").blur(function(){
		  if(Amount.test($(this).val())){
			  $(this).next(".tip").hide();
			  return true;
			  }else{
				  $(this).next(".tip").show();
				  return false;
				  }    
		  });	 
		
		$("input[name=Payer]").blur(function(){
		  if(Payer.test($(this).val())){
			  $(this).next(".tip").hide();
			  return true;
			  }else{
				  $(this).next(".tip").show();
				  return false;
				  }  
		  
		  });	  
		
		
		$("input[name=street]").focus(function(){
		 if($(this).val()==$(this).attr("defalt")){
		   $(this).val("");	 
		 }
		 }); 
	  $("input[name=street]").blur(function(){
		  if($(this).val()==""){
			  $(this).val($(this).attr("defalt"));	
			  return false; 
			  }else{
				  return true;
				  }  
		  
		  });	  
		  
		
		  $("input[name=City]").focus(function(){
		 if($(this).val()==$(this).attr("defalt")){
		   $(this).val("");	 
		 }
		 }); 
	  $("input[name=City]").blur(function(){
		  if($(this).val()==""){
			  $(this).val($(this).attr("defalt"));	
			  return false; 
			  }else{
				  return true;
				  } 
		  
		  });	  
		
		 $("input[name=zipcode]").focus(function(){
		 if($(this).val()==$(this).attr("defalt")){
		   $(this).val("");	 
		 }
		 }); 
	  $("input[name=zipcode]").blur(function(){
		  if($(this).val()==""){
			  $(this).val($(this).attr("defalt"));	 
			  return false;
			  }else if(phone.test($(this).val())){
			 
			  $(this).next(".tip").hide();
			  return true;
			  }else{
				  $(this).next(".tip").show();
				  return false;
				  }
		  });	  
		  
		
		  $("input[name=phone]").focus(function(){
		 if($(this).val()==$(this).attr("defalt")){
		   $(this).val("");	 
		 }
		 }); 
	  $("input[name=phone]").blur(function(){
		  if($(this).val()==""){
			  $(this).val($(this).attr("defalt"));	 
			  return false;
			  }else if(phone.test($(this).val())){
			 
			  $(this).next(".tip").hide();
			  return true;
			  }else{
				  $(this).next(".tip").show();
				  return false;
				  }
		  });	  
	 
	 
	     $("input[name=tel]").focus(function(){
		 if($(this).val()==$(this).attr("defalt")){
		   $(this).val("");	 
		 }
		 }); 
	  $("input[name=tel]").blur(function(){
		  if($(this).val()==""){
			  $(this).val($(this).attr("defalt"));	 
			  return false;
			  }else if(tel.test($(this).val())){
			 
			  $(this).next(".tip").hide();
			  return true;
			  }else{
				  $(this).next(".tip").show();
				  return false;
				  }
		  });	  
	 
	   $("input[name=renname]").focus(function(){
		 if($(this).val()==$(this).attr("defalt")){
		   $(this).val("");	 
		 }
		 }); 
	  $("input[name=renname]").blur(function(){
		  if($(this).val()==""){
			  $(this).val($(this).attr("defalt"));
			  return false;	 
			  }else if(renname.test($(this).val())){
			 
			  $(this).next(".tip").hide();
			  return true;
			  }else{
				  $(this).next(".tip").show();
				  return false;
				  }
		  });	  
	 
	 
	 
	 
	  
	  $("input[name=Confirmpwd]").focus(function(){
		 if($(this).val()==$(this).attr("defalt")){
		   $(this).val("");	 
		 }
		 }); 
	  $("input[name=Confirmpwd]").blur(function(){
		  if($(this).val()==""){
			  $(this).val($(this).attr("defalt"));	 
			  }else if(Confirmpwd==$(this).val()){
			  $(this).next(".tip").hide();
			  return true;
			  }else{
				  $(this).next(".tip").show();
				  return false;
				  }
		  });	  
	  
	   $("input[name=pwd1]").focus(function(){
		 if($(this).val()==$(this).attr("defalt")){
		   $(this).val("");	 
		 }
		 }); 
	  $("input[name=pwd1]").blur(function(){
		  if($(this).val()==""){
			  $(this).val($(this).attr("defalt"));	 
			  }else if(pwd.test($(this).val())){
			  
			  $(this).next(".tip").hide();
			  return true;
			  }else{
				  $(this).next(".tip").show();
				  return false;
				  }
		  });	  
	  
	  
	   $("input[name=pwd]").focus(function(){
		 if($(this).val()==$(this).attr("defalt")){
		   $(this).val("");	 
		 }
		 }); 
	  $("input[name=pwd]").blur(function(){
		  if($(this).val()==""){
			  $(this).val($(this).attr("defalt"));	 
			  }else if(pwd.test($(this).val())){
			  Confirmpwd=$(this).val();
			  $(this).next(".tip").hide();
			  return true;
			  }else{
				  $(this).next(".tip").show();
				  return false;
				  }
		  });	  
	  
	  
	  $("input[name=email]").focus(function(){
		 if($(this).val()==$(this).attr("defalt")){
		   $(this).val("");	 
		 }
		 }); 
	  $("input[name=email]").blur(function(){
		  if($(this).val()==""){
			  $(this).val($(this).attr("defalt"));	 
			  }else if(email.test($(this).val())){
			  $(this).next(".tip").hide();
			  return true;
			  }else{
				  $(this).next(".tip").show();
				  return false;
				  }
		  });
		  
		  
 $("#checkpwd").click(function(){
		  var tt=$(this).is(':checked');  
		   if(tt){
		     $("input[name=pwd]").attr("type","text");
			 $("input[name=Confirmpwd]").attr("type","text");
			
		  }else{
			   $("input[name=pwd]").attr("type","password");
			   $("input[name=Confirmpwd]").attr("type","password");
			  }
		});		  
		  	  
}

function showhide(obj){
    $(obj).next(".country").slideToggle();
	$(".country").find("ul").find("li").click(function(){
		  $(this).parents(".country").prev().text($(this).text());
		  $(obj).next(".country").hide();
		});
		
	}