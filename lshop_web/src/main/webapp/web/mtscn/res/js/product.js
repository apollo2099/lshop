

	//限时
	var t=0;
	function setTime(isTrue,activityType){
		if(isTrue){
			if(activityType=='1'){
				var tmpDay,tmpHour,tmpMinute,tmpSecond; 
			    tmpDay=parseInt(tmpInt/(24*60*60));
			    tmpHour=parseInt((tmpInt%(24*60*60))/(60*60)); 
			    tmpMinute=parseInt((tmpInt%(24*60*60)%(60*60))/60); 
			    tmpSecond=parseInt((tmpInt%(24*60*60)%(60*60))%60);  
			    if(tmpInt>0&&tmpDay>=0&&tmpHour>=0&&tmpMinute>=0&&tmpSecond>=0){
			    	t=1;
					$("#curTime").html(tmpDay+"天"+tmpHour+"小时"+tmpMinute+"分"+tmpSecond+"秒"); 
					tmpInt=tmpInt-1;
					setTimeout('setTime(true,1)',1000);
			    }else{
			    	if(t!=0){
			    		window.location.reload(true); 
			    	}
				}
			}
		}
	}

	//限量
	var c=0;
	function setCount(isTrue,activityType,productCode){
		if(isTrue){
			if(activityType=='2'){
				$.ajax({
				   type: "get",
				   url: "/web/mall!getLimitCount.action",
				   data: "productCode="+productCode,
				   success: function(data){
				   	 	if(data>0){
				   	 		c=1;
				   	 		$("#curCount").html(data);
				   	 		setTimeout('setCount()', 10000);
				   	 	}else{
				   	 		if(c!=0){
				   	 			window.location.reload(true); 
				   	 		}
				   	 	}
				   	 }
				});
			}
		}
	}
	
	//加入购物车	
	function toPut(productCode,storeId){
		$("input[name='lvShopCart.shopNum']").val($("#num").html()); 
		var users=lshop.getCookieToJSON('user');
		if(users.uid!=null){
			$.ajax({
			   type: "POST",
			   url: "/web/mall!putShopCart.action",
			    data: "lvShopCart.productCode="+productCode+"&lvShopCart.shopNum="+$("#num").html(),
			   success: function(num){
			   		if(num==-1){
			   			window.location.reload(true); 
			   		}else if(num==-2){
			   			window.location.reload(true); 
			   		}else{
			   			alert("加入购物车操作成功");
			   	 		$("#shopCartNum").html(num);
			   		}
			   	 }
			});
		}else{
			$.ajax({
			   type: "POST",
			   url: "/web/index!putCartCookie.action",
			   data: "storeId="+storeId+"&productCode="+productCode+"&num="+$("#num").html()+"&shopCartNum="+$("#shopCartNum").html(),
			   async: false,
			   success: function(num){
					alert("加入购物车操作成功，详情请进入购物车中查看");
					$("#shopCartNum").html(num);
			   	 }
			});
		}
	
	}

	//立即购买
	function toSub(storeId,productCode,storeDomain){
		$("input[name='lvShopCart.shopNum']").val($("#num").html()); 
		var users=lshop.getCookieToJSON('user');
		if(users.uid!=null){
			$("#buyForm").attr("action",storeDomain+"/web/mall!saveShopCart.action?shopFlag="+storeId);
			$("#buyForm").submit();
		}else{
			$.ajax({
			   type: "POST",
			   url: "/web/index!putCartCookie.action",
			   data: "storeId="+storeId+"&productCode="+productCode+"&num="+$("#num").html()+"&shopCartNum="+$("#shopCartNum").html(),
			   async: false,
			   success: function(){
					$("#buyForm").attr("action",storeDomain+"/web/mall!getShopCartList.action");
					$("#buyForm").submit();
			   	 }
			});
		}
	}

	$(function(){
		//阶梯价
		$(".discount_span").click(function(){
		  	$(".discount_table").slideToggle(); 
		});
		
		//评论
	  $(".commentbt1").find("ul").find("li").find("span").click(function(){
		  $(this).attr("id","curspan");
		  $(this).parent("li").siblings().find("span").removeAttr("id");
		   var ind=$(this).parent("li").index();
		   var str="productinfo_";
		   var strs="."+str+ind;
		   $(strs).show();
		   $(strs).siblings().hide();
		  
		  
		});
		
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

	});
 	