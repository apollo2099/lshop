<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>購物車_華揚商城</title>
<%@include file="/web/tvpadcn/common/header.jsp" %>
<!-- top -->
<% request.setAttribute("navFlag","mall"); %>
<%@include file="/web/tvpadcn/common/top.jsp" %>
<link href="/res/css/main.css" rel="stylesheet" type="text/css" />
<link href="/res/css/buy.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/res/js/cookie.js"></script>
<script type="text/javascript">
function changeNum(obj,status,price,pid){
	var nowNum=0;
	var num=$("#num"+status).val();
	var amount=$("#allAmount").html();

	if(obj=="add"){
		nowNum=Number(num)+1;
		amount=Number(amount)+Number(price);
	}else if(obj=="del"&&num>1){
		nowNum=Number(num)-1;
		amount=Number(amount)-Number(price);
	}else{
	    nowNum=Number(num);
	    amount=Number(amount);
		alert("数量不能小于1");
	}
	
	updateCookieById(pid,nowNum+"");
	$("#num"+status).val(nowNum);
	$("#allAmount").html(amount);
	$("#price"+status).html(Number(price)*nowNum );
	
}

function delCart(pid,status){
	if(confirm("确定要删除吗？")){
		delCookieById(pid);
		$("#cart"+status).remove();
	}
	var num=getCookieNum();
	$("#shopCartNum").html(num);
}

function showInfo(){
	alert("您还未登录，请先登录！如果您没有登录账户，请先注册！");
	onshow('tx_b','loginDiv');
}
</script>

</head>
<body>

	<div class="buy">	
		<div class="buy_top"><img src="/res/images/shopping01.gif" /></div>
				<div class="buy_lc"></div>																			
					<!--订单列表-->
					<div class="buy_order">
						<ul>
							<li class="buy_order01">
								<p>&nbsp;</p>
								<p class="title">商品名称</p>
								<p>价格</p>
								<p>购买数量</p>
								<p>小计金额</p>
								<p>操作</p>
							</li>
							<font color="red">${msg }</font>
							<c:foreach items="${objList}" var="obj" varStatus="status">
							<li id="cart${status.count }">
								<p><img src="${obj[0].pimage }" width="70px" height="60px"/></p>
								<p class="title">${obj[0].productName }</p>
								<p><font class="redfont fontwz">USD ${obj[0].price  }</font></p>
						 	  	<p class="num"><a href="javascript:changeNum('del','${status.count }','${obj[0].price  }','${obj[0].id}');"><img src="/res/images/jian.gif" border="0"/></a>
						 	    <input type="text" class="input0" id="num${status.count }" name="num" value="${obj[1] }"/>
					 	      	<a href="javascript:changeNum('add','${status.count }','${obj[0].price  }','${obj[0].id}');"><img src="/res/images/jia.gif" border="0"/></a></p>
								<p><font class="redfont fontwz">USD <span id="price${status.count }">${obj[0].price  * obj[1] }</span></font></p>
								<p><a href="#" onclick="delCart('${obj[0].id}','${status.count}')">删 除</a></p>
							</li>
							</c:foreach>
						</ul>
						<ul class="sum">商品總金額：<font class="redfont">USD <span id="allAmount">${allAmount }</span></font></a></ul>
						<ul class="btn"><a href="/web/mall!toMall.action"><img src="/res/images/btn01.gif" /></a><a href="javascript:showInfo();"><img src="/res/images/btn02.gif" border="0" readonly="true"/></a>
						</ul>
					</div>					
		</div>
		<!--End 购物车-->
	<!-- footer-->
	<%@include file="/web/tvpadcn/common/footer.jsp" %>

</body>
</html> 
