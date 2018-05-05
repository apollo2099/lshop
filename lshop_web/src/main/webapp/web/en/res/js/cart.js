//保留一位小数
function toFloat(value) { //保留一位小数点
    value = Math.round(parseFloat(value) * 100) / 100;

    return value;
 }

//改变数量
function changeNum(obj,outStatus,status,price,pid,code,ladderStatus){
	var nowNum=0;
	var num=$("#_"+outStatus+"num"+status).val();
	var amount=$("#allAmount"+outStatus).html();
	var oldXiaoPrice=$("#_"+outStatus+"price"+status).html();
	var oPrice;
	var allNum = parseInt($('#allNum'+outStatus).text());

	if(obj=="add"){
		if(num<9999){
			nowNum=Number(num)+1;
			allNum++;
		}else{
			nowNum=Number(num);
			ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
		    ymPrompt.alert({title:'Tips',message:'Qty. shall be at most 9999.'}) ;
		}
	}else if(obj=="del"&&num>1){
		nowNum=Number(num)-1;
		allNum--;
	}else{
	    nowNum=Number(num);
		ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
	    ymPrompt.alert({title:'Tips',message:'Qty. shall be at least 1.'}) ;
	}
	$("#_"+outStatus+"num"+status).val(nowNum);
	
	//判断是否使用阶梯价
	if(ladderStatus==1){
		$.ajax({
		   type: "POST",
		   url: "/web/shopCart!getPriceForLadder.action",
		   data: "pid="+pid+"&shopNum="+nowNum,
		   success: function(data){
		   		//改变产品金额
		   		oPrice=toFloat(data);
		   		changCar(code,nowNum,oPrice);
		   		$("#_"+outStatus+"oPrice"+status).html(oPrice);
		   		//改变小计金额
		   		var xiaoPrice=toFloat(oPrice)*nowNum;
		   		$("#_"+outStatus+"price"+status).html(toFloat(xiaoPrice));
		   		//改变总金额
	   			if(obj=="add"&&num<9999){
					amount=toFloat(toFloat(amount)-toFloat(oldXiaoPrice)+toFloat(xiaoPrice));
					$("#allAmount"+outStatus).html(toFloat(amount));
				}else if(obj=="del"&&num>1){
					amount=toFloat(toFloat(amount)-toFloat(oldXiaoPrice)+toFloat(xiaoPrice));
					$("#allAmount"+outStatus).html(toFloat(amount));
				}else{
				    amount=toFloat(amount);
				    $("#allAmount"+outStatus).html(toFloat(amount));
				}
		   }
		});
	}else{
			changCar(code,nowNum,price);
			//改变小计金额
	   		$("#_"+outStatus+"price"+status).html(toFloat(toFloat(price)*nowNum));
	   		//改变总金额
   			if(obj=="add"&&num<9999){
				amount=toFloat(toFloat(amount)+toFloat(price));
				$("#allAmount"+outStatus).html(toFloat(amount));
			}else if(obj=="del"&&num>1){
				amount=toFloat(toFloat(amount)-toFloat(price));
				$("#allAmount"+outStatus).html(toFloat(amount));
			}else{
			    amount=toFloat(amount);
			    $("#allAmount"+outStatus).html(toFloat(amount));
			}
	}
	$('#allNum'+outStatus).text(allNum);
	//修改购物车事件
 	var $a = $("#allAmount"+outStatus);
 	$a.parents('.c_shopcart').trigger('payChang', {orderPay: $a.html()});
}

//修改数据库中购物车数量
function changCar(code,nowNum,price){
		
	//用户未登录情况 
	var users=lshop.getCookieToJSON('user');
	if(null==users.uid||""==users.uid){
		$.ajax({
		   type: "POST",
		   url: "/web/index!updateCartCookie.action",
		   data: "code="+code+"&shopNum="+nowNum,
		   success: function(){}
		});
	}else{ //用户登录情况 
		$.ajax({
		   type: "POST",
		   url: "/web/shopCart!changCartNum.action",
		   data: "code="+code+"&shopNum="+nowNum+"&shopPrice="+price,
		   success: function(){}
		});
	}
}

//删除购物车
function delCart(cartCode,outStatus,status,price){
	
	var amount=$("#allAmount"+outStatus).html();
	var nowNum=$("#_"+outStatus+"num"+status).val();
	var users=lshop.getCookieToJSON('user');
	if(null==users.uid||""==users.uid){
		ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
		ymPrompt.confirmInfo('<strong>Are you sure you want to delete it?</strong>',null,null,'Tips',function handler(tp){if(tp=='ok'){
			$.ajax({
			   type: "POST",
			   url: "/web/index!delCartCookie.action",
			   data: "code="+cartCode+"&shopCartNum="+$("#shopCartNum").html(),
			   async: false,
			   success: function(num){
		   			$("#_"+outStatus+"cart"+status).remove();
		   	 		var chaAmount=toFloat(amount)-toFloat(price)*nowNum;
		   	 		$("#allAmount"+outStatus).html(toFloat(chaAmount));
		   	 		$("#shopCartNum").html(num);
			   	 	var chaNum = parseInt($('#allNum'+outStatus).text()) - parseInt(nowNum);
		   	 		$('#allNum'+outStatus).text(chaNum);
			   	 	//修改购物车事件
			   	 	var $a = $("#allAmount"+outStatus);
			   	 	$a.parents('.c_shopcart').trigger('payChang', {orderPay: $a.html()});
			   }
			});
		  }
		if(tp=='cancel'){
			ymPrompt.close();
		  }} );
	}else{
		ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
		ymPrompt.confirmInfo('<strong>Are you sure you want to delete it?</strong>',null,null,'Tips',function handler(tp){if(tp=='ok'){
			$.ajax({
			   type: "POST",
			   url: "/web/shopCart!delCart.action",
			   data: "code="+cartCode,
			   async: false,
			   success: function(num){
			   	 	if(num!=-1){
			   	 		$("#_"+outStatus+"cart"+status).remove();
			   	 		$("#shopCartNum").html(num);
			   	 		var chaAmount=toFloat(amount)-toFloat(price)*nowNum;
			   	 		$("#allAmount"+outStatus).html(toFloat(chaAmount));
				   	 	var chaNum = parseInt($('#allNum'+outStatus).text()) - parseInt(nowNum);
			   	 		$('#allNum'+outStatus).text(chaNum);
				   	 	//修改购物车事件
				   	 	var $a = $("#allAmount"+outStatus);
				   	 	$a.parents('.c_shopcart').trigger('payChang', {orderPay: $a.html()});
			   	 	}else{
			   	 		alert("Operation failure!");
			   	 	}
			   	 }
			});
		  }
		if(tp=='cancel'){
			ymPrompt.close();
		  }} );
	}
}

//点击去结算
function showInfo(shopFlag,outStatus){
	var users=lshop.getCookieToJSON('user');
	if(null==users.uid||""==users.uid){
		ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
		ymPrompt.confirmInfo('<strong>Please login first. If you don\'t have an account yet, please register!</strong>',null,null,'Tips',function handler(tp){if(tp=='ok'){
			  onshow('tx_b','loginDiv');
		  }
		if(tp=='cancel'){
			ymPrompt.close();
		  }} );
	}else{
		var allAmount=$("#allAmount"+outStatus).html();
	    if(toFloat(allAmount)==0){
	        ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
	    	ymPrompt.alert({title:'Tips',message:'You have not yet added any items to your Shopping Cart, please choose an item first!'}) ;
	    }else{
	      location.href="/web/shopCart!toOrderInfo.action?shopFlag="+shopFlag;
	    }
	}
}

//点击店铺上面的全选
function checkBox(outStatus,obj){//商城循环顺序、点击复选框本身
	if($(obj).attr("checked")==true){
		$("input[name=inBox"+outStatus+"]").each(function(){
			$(this).attr("checked",true);
		});
	}else{
		$("input[name=inBox"+outStatus+"]").each(function(){
			$(this).attr("checked",false);
		});
		$("input[name=allBox]").attr("checked",false);
	}
}

//点击商城上面的总选
function checkAll(obj){
	if($(obj).attr("checked")==true){
		$("input[type='checkbox']").each(function(){
			$(this).attr("checked",true);
		});
	}else{
		$("input[type='checkbox']").each(function(){
			$(this).attr("checked",false);
		});
	}
	
}

//点击某个复选框
function checkInBox(outStatus,obj){
	if($(obj).attr("checked")==false){
		$("input[name=box"+outStatus+"]").attr("checked",false);
		$("input[name=allBox]").attr("checked",false);
	}
}

//点击店铺上方的批量删除
function deleteShopData(outStatus,url){
	ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
	ymPrompt.confirmInfo('<strong>Are you sure you want to delete it?</strong>',null,null,'Tips',function handler(tp){if(tp=='ok'){
		var str="";
		$("input[name=inBox"+outStatus+"]").each(function(){
			if($(this).attr("checked")==true){
				str += $(this).val() +";";
			}
		});
	
		if(str==""){
			ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
		    ymPrompt.alert({title:'Tips',message:'Please select the data you want to delete!'}) ;
		}else{
			str = str.substring(0, str.length - 1);
//						location.href="/web/shopCart!delManyCart.action?str="+str;
			url = url + "?str="+str;
			location.href=url;
		}
  }
	if(tp=='cancel'){
		ymPrompt.close();
  	}} );
}

//点击商城上面的总批量删除
function deleteAllCheckData(url){
	ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
	ymPrompt.confirmInfo('<strong>Are you sure you want to delete it?</strong>',null,null,'Tips',function handler(tp){if(tp=='ok'){
		var str="";
		$("input[type='checkbox']").each(function(){
			if($(this).attr("checked")==true){
				if($(this).val()!=""){
					str += $(this).val() +";";
				}
			}
		});

		if(str==""){
			ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
	    	ymPrompt.alert({title:'Tips',message:'Please select the data you want to delete!'}) ;
		}else{
			str = str.substring(0, str.length - 1);
//			            location.href="/web/shopCart!delManyCart.action?str="+str;
			url = url + "?str="+str;
			location.href=url;
		}
  }
	if(tp=='cancel'){
		ymPrompt.close();
  	}} );
}