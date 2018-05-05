<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<%
    response.setHeader("Pragma","No-cache");
    response.setHeader("Cache-Control","no-cache");
    response.setDateHeader("Expires", 0);
    response.flushBuffer();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Shopping Cart-HUA YANG MALL</title>
<% request.setAttribute("navFlag","mall"); %>
<%@include file="/web/tvpaden/common/header.jsp" %>
<link href="/res/css/main.css" rel="stylesheet" type="text/css" />
<link href="/res/css/buy.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">

function toFloat(value) { //保留一位小数点
    value = Math.round(parseFloat(value) * 100) / 100;

    return value;
   }
function changeNum(obj,status,price,pid,code,ladderStatus){
	var nowNum=0;
	var num=$("#num"+status).val();
	var amount=$("#allAmount").html();
	var oldXiaoPrice=$("#price"+status).html();
	var oPrice;

	if(obj=="add"){
		nowNum=Number(num)+1;
	}else if(obj=="del"&&num>1){
		nowNum=Number(num)-1;
	}else{
	    nowNum=Number(num);
		ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
	    ymPrompt.alert({title:'Tips',message:'Qty. shall be at least 1.'}) ;
	}
	$("#num"+status).val(nowNum);
	
	//用户未登录情况 
	var users=lshop.getCookieToJSON('user');
	if(null==users.uid||""==users.uid){
		updateCookieById(pid,nowNum+"");
	}
	
	//将改变后的数量保存在购物车中
	if(null!=code&&""!=code){
		$.ajax({
		   type: "POST",
		   url: "/web/shopCart!changCartNum.action",
		   data: "code="+code+"&shopNum="+nowNum,
		   success: function(){

		   }
		});
	}
	
	//判断是否使用阶梯价
	if(ladderStatus==1){
		$.ajax({
		   type: "POST",
		   url: "/web/shopCart!getPriceForLadder.action",
		   data: "pid="+pid+"&shopNum="+nowNum,
		   success: function(data){
		   		//改变产品金额
		   		oPrice=toFloat(data);
		   		$("#oPrice"+status).html(oPrice);
		   		//改变小计金额
		   		var xiaoPrice=toFloat(oPrice)*nowNum;
		   		$("#price"+status).html(toFloat(xiaoPrice));
		   		//改变总金额
	   			if(obj=="add"){
					amount=toFloat(toFloat(amount)-toFloat($("#price"+status).html())+toFloat(xiaoPrice));
					$("#allAmount").html(toFloat(amount));
				}else if(obj=="del"&&num>1){
					amount=toFloat(toFloat(amount)-toFloat($("#price"+status).html())+toFloat(xiaoPrice));
					$("#allAmount").html(toFloat(amount));
				}else{
				    amount=toFloat(amount);
				    $("#allAmount").html(toFloat(amount));
				}
		   }
		});
	}else{
	   		$("#price"+status).html(toFloat(toFloat(price)*nowNum));
			$("#allAmount").html(toFloat(toFloat(amount)+toFloat(price)));
	}
}

function delCart(cartCode,status,price){
	
	var amount=$("#allAmount").html();
	var nowNum=$("#num"+status).val();
	var users=lshop.getCookieToJSON('user');
	if(null==users.uid||""==users.uid){
		ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
		ymPrompt.confirmInfo('<strong>Are you sure you want to delete it?</strong>',null,null,'Tips',function handler(tp){if(tp=='ok'){
			var chaAmount=toFloat(amount)-toFloat(price)*nowNum;
			delCookieById(cartCode);
			$("#cart"+status).remove();
			$("#allAmount").html(toFloat(chaAmount));
		  }
		if(tp=='cancel'){
			ymPrompt.close();
		  }} );
		  
		var num=getCookieNum();
		$("#shopCartNum").html(num);
	}else{
		ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
		ymPrompt.confirmInfo('<strong>Are you sure you want to delete it?</strong>',null,null,'Tips',function handler(tp){if(tp=='ok'){
			$.ajax({
			   type: "POST",
			   url: "/web/shopCart!delCart.action",
			   data: "code="+cartCode,
			   success: function(num){
			   	 	if(num!=-1){
			   	 		$("#cart"+status).remove();
			   	 		$("#shopCartNum").html(num);
			   	 		var chaAmount=toFloat(amount)-toFloat(price)*nowNum;
			   	 		$("#allAmount").html(toFloat(chaAmount));
			   	 	}else{
			   	 		ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
	    				ymPrompt.alert({title:'Tips',message:'Delete failed!'}) ;
			   	 	}
			   	 }
			});
		  }
		if(tp=='cancel'){
			ymPrompt.close();
		  }} );
	}
}

function showInfo(){
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
		var allAmount=$("#allAmount").html();
	    if(toFloat(allAmount)==0){
	    	ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
	    	ymPrompt.alert({title:'Tips',message:'You have not yet added any items to your Shopping Cart, please choose an item first!'}) ;
	    }else{
	      location.href="/web/shopCart!toOrderInfo.action";
	    }
	}
}
</script>

</head>
<body>
	<!-- top -->
	<%@include file="/web/tvpaden/common/top.jsp" %>

	<div class="buy">	
		<div class="buy_top"><img src="/res/images/shopping01.gif" /></div>
				<div class="buy_lc"></div>																			
					<!--订单列表-->
					<div class="buy_order">
						<ul>
							<li class="buy_order01">
								<p>&nbsp;</p>
								<p class="title">Items Name</p>
								<p>Price</p>
								<p>Quantity</p>
								<p>Subtotal</p>
								<p>Action</p>
							</li>
							<c:if test="${mark==1}">
								<c:foreach items="${objList}" var="obj" varStatus="status">
								<li id="cart${status.count }">
									<p><img src="${obj[1].pimage }" width="70px" height="60px"/></p>
									<p class="title">${obj[1].productName }</p>
									<p><font class="redfont fontwz">USD <span id="oPrice${status.count }">${obj[0].shopPrice }</span></font></p>
							 	  	<p class="num"><a href="javascript:changeNum('del','${status.count }','${obj[0].shopPrice }','${obj[1].id}','${obj[0].code }','${obj[1].isLadder}');"><img src="/res/images/jian.gif" border="0"/></a>
							 	    <input type="text" class="input0" id="num${status.count }" name="num" value="${obj[0].shopNum }" readonly/>
						 	      	<a href="javascript:changeNum('add','${status.count }','${obj[0].shopPrice }','${obj[1].id}','${obj[0].code }','${obj[1].isLadder}');"><img src="/res/images/jia.gif" border="0"/></a></p>
									<p><font class="redfont fontwz">USD <span id="price${status.count }"><u:formatnumber value="${obj[0].shopPrice * obj[0].shopNum }"  type="number" groupingUsed="false" maxFractionDigits="2"/></span></font></p>
									<p><a href="#" onclick="delCart('${obj[0].code}','${status.count }','${obj[0].shopPrice }')">Remove</a></p>
								</li>
								</c:foreach>
							</c:if>
							<c:if test="${mark==2}">
								<c:foreach items="${objList}" var="obj" varStatus="status">
								<li id="cart${status.count }">
									<p><img src="${obj[0].pimage }" width="70px" height="60px"/></p>
									<p class="title">${obj[0].productName }</p>
									<p><font class="redfont fontwz">USD ${obj[0].price  }</font></p>
							 	  	<p class="num"><a href="javascript:changeNum('del','${status.count }','${obj[0].price  }','${obj[0].id}',null);"><img src="/res/images/jian.gif" border="0"/></a>
							 	    <input type="text" class="input0" id="num${status.count }" name="num" value="${obj[1] }" readonly/>
						 	      	<a href="javascript:changeNum('add','${status.count }','${obj[0].price  }','${obj[0].id}',null);"><img src="/res/images/jia.gif" border="0"/></a></p>
									<p><font class="redfont fontwz">USD <span id="price${status.count }"><u:formatnumber value="${obj[0].price  * obj[1] }"  type="number" groupingUsed="false" maxFractionDigits="2"/></span></font></p>
									<p><a href="#" onclick="delCart('${obj[0].id}','${status.count}','${obj[0].price  }')">Remove</a></p>
								</li>
								</c:foreach>
							</c:if>

						</ul>
						<ul class="sum"></ul>
						<ul class="sum" style="display:none;">Total：<font class="redfont">USD <span id="allAmount"><u:formatnumber value="${allAmount}"  type="number" groupingUsed="false" maxFractionDigits="2"/></span></font></a></ul>
						<ul class="btn1">
							<p class="bt"><a href="/web/mall!toMall.action"><img src="/res/images/btn01.gif" /></a><p class="bt">
							<p class="bt"><a href="javascript:showInfo();"><img src="/res/images/btn02.gif" border="0" /></a><p class="bt">
						</ul>
					</div>					
		</div>
		<!--End 购物车-->
	<!-- footer-->
	<%@include file="/web/tvpaden/common/footer.jsp" %>

</body>
</html> 
