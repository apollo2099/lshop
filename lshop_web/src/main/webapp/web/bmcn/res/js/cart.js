
//保留一位小数
function toFloat(value) { //保留一位小数点
    value = Math.round(parseFloat(value) * 100) / 100;
    return value;
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
   
//改变数量  
function changeNum(obj,outStatus,status,price,pid,code,ladderStatus){
	var nowNum=0;
	var num=$("#_"+outStatus+"num"+status).val();
	var amount=$("#allAmount"+outStatus).html();
	var oldXiaoPrice=$("#_"+outStatus+"price"+status).html();
	var oPrice=price;
	var allNum = parseInt($('#allNum'+outStatus).text());

	if(obj=="add"){
		if(num<9999){
			nowNum=Number(num)+1;
			allNum++;
		}else{
			alert("数量不能大于9999");
			nowNum=Number(num);
		}
	}else if(obj=="del"&&num>1){
		allNum--;
		nowNum=Number(num)-1;
	}else{
	    nowNum=Number(num);
		alert("数量不能小于1");
	}
	$("#_"+outStatus+"num"+status).val(nowNum);
	
	//判断是否使用阶梯价
	if(ladderStatus==1){
		$.ajax({
		   type: "POST",
		   url: "/web/shopCart!getPriceForLadder.action",
		   data: "pid="+pid+"&shopNum="+nowNum,
		   async: false,
		   success: function(data){
		   		//改变产品金额
		   		oPrice=toFloat(data);
		   		changCar(code,nowNum,oPrice);
		   		$("#_"+outStatus+"oPrice"+status).html(toFloat2(oPrice));
		   		//改变小计金额
		   		var xiaoPrice=toFloat(oPrice)*nowNum;
		   		$("#_"+outStatus+"price"+status).html(toFloat2(toFloat(xiaoPrice)));
		   		//改变总金额,商品总计
	   			if(obj=="add"&&num<9999){
					amount=toFloat(toFloat(amount)-toFloat(oldXiaoPrice)+toFloat(xiaoPrice));
					$("#allAmount"+outStatus).html(toFloat2(amount));
				}else if(obj=="del"&&num>1){
					amount=toFloat(toFloat(amount)-toFloat(oldXiaoPrice)+toFloat(xiaoPrice));
					$("#allAmount"+outStatus).html(toFloat2(amount));
				}else{
				    amount=toFloat(amount);
				    $("#allAmount"+outStatus).html(toFloat2(amount));
				}
		   }
		});
	}else{
			changCar(code,nowNum,price);
			//改变小计金额
	   		$("#_"+outStatus+"price"+status).html(toFloat2(toFloat(toFloat(price)*nowNum)));
	   		//改变总金额,商品总计
   			if(obj=="add"&&num<9999){
				amount=toFloat(toFloat(amount)+toFloat(price));
				$("#allAmount"+outStatus).html(toFloat2(amount));
			}else if(obj=="del"&&num>1){
				amount=toFloat(toFloat(amount)-toFloat(price));
				$("#allAmount"+outStatus).html(toFloat2(amount));
			}else{
			    amount=toFloat(amount);
			    $("#allAmount"+outStatus).html(toFloat2(amount));
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
		if(confirm("确定要删除吗？")){
			$.ajax({
			   type: "POST",
			   url: "/web/index!delCartCookie.action",
			   data: "code="+cartCode+"&shopCartNum="+$("#shopCartNum").html(),
			   async: false,
			   success: function(num){
		   			$("#_"+outStatus+"cart"+status).remove();
		   	 		var chaAmount=toFloat(amount)-toFloat(price)*nowNum;
		   	 		$("#allAmount"+outStatus).html(toFloat2(toFloat(chaAmount)));
		   	 		$("#shopCartNum").html(num);
		   	 		var chaNum = parseInt($('#allNum'+outStatus).text()) - parseInt(nowNum);
		   	 		$('#allNum'+outStatus).text(chaNum);
			   	 	//修改购物车事件
			   	 	var $a = $("#allAmount"+outStatus);
			   	 	$a.parents('.c_shopcart').trigger('payChang', {orderPay: $a.html()});
			   }
			});
		}
	}else{
		if(confirm("确定要删除吗？")){
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
			   	 		$("#allAmount"+outStatus).html(toFloat2(toFloat(chaAmount)));
				   	 	var chaNum = parseInt($('#allNum'+outStatus).text()) - parseInt(nowNum);
			   	 		$('#allNum'+outStatus).text(chaNum);
				   	 	//修改购物车事件
				   	 	var $a = $("#allAmount"+outStatus);
				   	 	$a.parents('.c_shopcart').trigger('payChang', {orderPay: $a.html()});
			   	 	}else{
			   	 		alert("删除失败");
			   	 	}
			   	 }
			});
		}
	}
}

//点击去结算
function showInfo(shopFlag,outStatus){
	var users=lshop.getCookieToJSON('user');
	if(null==users.uid||""==users.uid){
		alert("您還未登录，请先登录！如果您没有登录账户，请先注册！");
		onshow('tx_b','loginDiv');
	}else{
		var allAmount=$("#allAmount"+outStatus).html();
	    if(toFloat(allAmount)==0){
	        alert("该店鋪購物车为空，请先选择購買产品！");
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
	if(confirm("确定要删除嗎？")){
		var str="";
		$("input[name=inBox"+outStatus+"]").each(function(){
			if($(this).attr("checked")==true){
				str += $(this).val() +";";
			}
		});

		if(str==""){
			alert("请选择要删除的數據！");
		}else{
			str = str.substring(0, str.length - 1);
//			location.href="/web/shopCart!delManyCart.action?str="+str;
			url = url + "?str="+str;
			location.href=url;
		}
	}
}

//点击商城上面的总批量删除
function deleteAllCheckData(url){
	if(confirm("确定要删除嗎？")){
		var str="";
		$("input[type='checkbox']").each(function(){
			if($(this).attr("checked")==true){
				if($(this).val()!=""){
					str += $(this).val() +";";
				}
			}
		});

		if(str==""){
			alert("请选择要删除的數據！");
		}else{
			str = str.substring(0, str.length - 1);
//			location.href="/web/shopCart!delManyCart.action?str="+str;
			url = url + "?str="+str;
			location.href=url;
		}
	}
}


