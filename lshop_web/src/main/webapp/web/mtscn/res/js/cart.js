//判断是否登陆
$(function(){
	var users=lshop.getCookieToJSON('user');
	if(users.email!=null&&users.email!=''){
       $("#loginCartId").hide();
	 }else{
	 	$("#loginCartId").show();
	 }
})

//改变数量  
function changeNum(obj,outStatus,status,price,pid,code,ladderStatus){
	var nowNum=0;
	var num=$("#_"+outStatus+"num"+status).html(); //每个店铺里面每条商品对应的数量
	var oldXiaoPrice=$("#_"+outStatus+"price"+status).html();
	var amount=$("#allAmount"+outStatus).html(); //每个店铺对应的总金额
	var oPrice=price; //产品价格

	if(obj=="add"){
		if(num<9999){
			nowNum=Number(num)+1;
		}else{
			alert("数量不能大于9999");
			nowNum=Number(num);
		}
	}else if(obj=="del"&&num>1){
		nowNum=Number(num)-1;
	}else{
	    nowNum=Number(num);
	}
	$("#_"+outStatus+"num"+status).html(nowNum);
	
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
		   		$("#_"+outStatus+"oPrice"+status).html(toFloat2(oPrice));
		   		//改变小计金额
		   		var xiaoPrice=toFloat(oPrice)*nowNum;
		   		$("#_"+outStatus+"price"+status).html(toFloat2(toFloat(xiaoPrice)));
		   		//改变总金额
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
	   		//改变总金额
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

//点击店铺上面的全选
function checkBox(outStatus,obj){//商城循环顺序、点击复选框本身
	if($(obj)[0].checked==true){　　　
		$("input[name=inBox"+outStatus+"]").each(function(){
			$(this).attr("checked",true);
		});
	}else{
		$("input[name=inBox"+outStatus+"]").each(function(){
			$(this).attr("checked",false);
		});
	}
}

//点击某个复选框
function checkInBox(outStatus,obj){
	if($(obj)[0].checked==false){
		$("input[name=box"+outStatus+"]").attr("checked",false);
	}
}

//点击商城上面的总批量删除
function deleteAllCheckData(url){
	var str="";
	$("input[type='checkbox']").each(function(){
		if($(this)[0].checked==true){
			if($(this).val()!=""){
				str += $(this).val() +";";
			}
		}
	});
	if(str==""){
		alert("请选择要删除的数据！");
		return false;
	}
	if(confirm("确定要删除吗？")){
		str = str.substring(0, str.length - 1);
		url = url + "?str="+str;
		location.href=url;
	}
}

//点击去结算
function showInfo(shopFlag,outStatus){
	var users=lshop.getCookieToJSON('user');
	if(null==users.uid||""==users.uid){
		alert("您还未登录，请先登录！如果您没有登录账户，请先注册！");
	}else{
		location.href="/web/shopCart!toOrderInfo.action?shopFlag="+shopFlag;
	}
}