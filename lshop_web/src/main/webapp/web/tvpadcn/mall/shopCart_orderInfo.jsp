<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="cus" uri="/WEB-INF/tld/cus.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<%    
  response.setHeader("Pragma","No-cache");    
  response.setHeader("Cache-Control","no-cache");    
  response.setDateHeader("Expires",   0);    
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>填寫訂單信息_華揚商城</title>
		<%@include file="/web/tvpadcn/common/header.jsp"%>
				<!-- top -->
		<%
			request.setAttribute("navFlag", "mall");
		%>
		<%@include file="/web/tvpadcn/common/top.jsp"%>
		<link href="/res/css/main.css" rel="stylesheet" type="text/css" />
		<link href="/res/css/buy.css" rel="stylesheet" type="text/css" />
		
		<script type="text/javascript">
function toFloat(value) { //保留一位小数点
    value = Math.round(parseFloat(value) * 100) / 100;
    return value;
   }
   
function showCarriage(contryId){
	$("input[name='addressCode']").parent().parent().removeClass("choose");
	$("input[name='addressCode']:checked").parent().parent().addClass("choose");
	
	$.ajax({
	   type: "POST",
	   url: "/web/shopCart!getCarriage.action",
	   data: "deliveryId="+contryId,
	   success: function(data){
	   	 	$("#carriage").html(data);
	   	 	$("#allCarriage").html(data);
	   	 	$("#amount").html(toFloat(toFloat(${allAmount })-toFloat($("#allCoupon").html())+toFloat($("#allCarriage").html())));
	   	 	if(data!=null&&data>0){
	   	 		$("#aa").show();
	   	 		$("#bb").show();
	   	 	}else{
	   	 		$("#aa").hide();
	   	 		$("#bb").hide();
	   	 	}
	   	 }
	});
}

function delAddress(addressCode,status){
	if(confirm("确定要删除吗？")){
		$.ajax({
		   type: "POST",
		   url: "/web/shopCart!delAddress.action",
		   data: "code="+addressCode,
		   async: false, 
		   success: function(flag){
		   	 	if(flag){
		   	 		$("#address"+status).remove();
		   	 	}else{
		   	 		alert("删除失败");
		   	 	}
		   	 }
		});
	}
}

function contryChange(){
	//给国家名称赋值
	$("#contrynameId").val($("#contryId").find("option:selected").text());
	
	//获取对应的省市
	var provinceId=$("#provinceId");
	$.ajax({
	   type: "POST",
	   url: "/web/userCenterManage!getProvinces.action",
	   data: "parentid="+$("#contryId").val(),
	   dataType:"JSON",
	   success: function(jsonData){
	   		var data=eval(jsonData);
	   		if(data.length>=1){
	   			$("#provinceName").remove();
	   			$("#provinceNameId").remove();
	   			$("#test").after("<select name='lvAccountAddress.provinceId' id='provinceName' class='input2'  onchange='provinceChange()' >");
	   			$("#provinceName").append("<option value=''>--請選擇洲/省--</option>");
	   			for(var i=0;i<data.length;i++){
	   				$("<option></option>").val(data[i].id).text(data[i].nameen).appendTo($("#provinceName"));
	   			}
	   			$("#provinceName").after("<input type='hidden' name='lvAccountAddress.provinceName' id='provinceNameId'  value=''/>");
	   		}else{
	   			$("#provinceName").remove();
	   			$("#provinceNameId").remove();
	   			$("#test").after("<input type='text' name='lvAccountAddress.provinceName' id='provinceName' class='input2' value='洲/省' onfocus='if(this.value==&quot;洲/省&quot;) this.value=&quot;&quot;' onblur='if(this.value==&quot;&quot;)this.value=&quot;洲/省&quot;'/>");

	   		}
	   	}
	});
}	

function provinceChange(){
	//给洲省名称赋值
	$("#provinceNameId").val($("#provinceName").find("option:selected").text());
}

function onSub(){
    if($("input[name='addressCode']").length>=5){
         alert("常用地址大于5项，请先删除再添加！");
         return false;
    }
	$("#adressForm").ajaxSubmit({
		type: "POST",
		url: "/web/shopCart!addAddress.action",
		dataType:"json",
		success:function(data){
			$("#addressDiv").add();
		}
	});
}

function showDiv(){
	if($("#couponDiv").hide()){
		$("#couponDiv").show();
	}
}

function hideDiv(){
	if($("#couponDiv").show()){
		$("#couponDiv").hide();
	}
}

function checkCouponCode(){
	var couponCode=$("#couponCode").val();
	if(couponCode==""){
		$("#couponMsg").html("<font color='red'>請輸入優惠碼！</font>");
	}else{
		$.ajax({
		   type: "POST",
		   url: "/web/shopCart!checkCouponCode.action",
		   data: "couponCode="+couponCode,
		   success: function(str){
		   	 	var o = eval('(' + str + ')');
		   	 	if(o.flag==1){
		   	 		$("#couponMsg").html("<font color='red'>優惠券不存在！</font>");
		   	 		$("#allCoupon").html(0);
		   	 	}else if(o.flag==2){
		   	 		$("#couponMsg").html("<font color='red'>優惠券已刪除！</font>");
		   	 		$("#allCoupon").html(0);
		   	 	}else if(o.flag==3){
		   	 		$("#couponMsg").html("<font color='red'>優惠券未激活！</font>");
		   	 		$("#allCoupon").html(0);
		   	 	}else if(o.flag==4){
		   	 		$("#couponMsg").html("<font color='red'>優惠券已過期！</font>");
		   	 		$("#allCoupon").html(0);
		   	 	}else{
		   	 		$("#couponMsg").html("優惠券驗證通過！金額为：<font class='redfont'>USD <span id='price'>"+o.money+"</span></font>，有效日期為：<font class='redfont'>"+o.validatydate+"</font>");
		   	 		$("#allCoupon").html(toFloat(o.money*${allNum }));
		   	 	}
		   	 	$("#amount").html(toFloat(toFloat(${allAmount })-toFloat($("#allCoupon").html())+toFloat($("#allCarriage").html())));
		   	 }
		});
	}
}

function subMyForm(){
	$("#myAddress").val($("input[name='addressCode']:checked").val());
	$("#payValue").val($("input[name='payCode']:checked").val());
	$("#bestDeliveryValue").val($("input[name='bestDeliveryTime']:checked").val());
	$("#myCoupon").val($("#couponCode").val());
	$("#myForm").submit();
}

//提交订单信息关键项目校验：订单收货地址,支付方式
function onValidate(){
    if($("input[name='addressCode']:checked").val()==null){
       alert("請選擇收貨地址，如果常用地址中無地址信息，請先添加常用地址信息！");
       return false;
    }
    if($("input[name='payCode']:checked").val()==null){
       alert("請選擇訂單支付方式！");
       return false;
    }
    if($("input[name='bestDeliveryTime']:checked").val()==null){
       alert("請選擇最佳收貨時間！");
       return false;
    }
    if($("#orderRemark").val().length>160){
    	alert("備註不得超過160位字符！");
    	return false;
    }
    $("#myAddress").val($("input[name='addressCode']:checked").val());
	$("#payValue").val($("input[name='payCode']:checked").val());
	$("#bestDeliveryValue").val($("input[name='bestDeliveryTime']:checked").val());
	$("#myCoupon").val($("#couponCode").val());
}

//添加常用地址表单验证
$().ready(function() {
	$("#addressForm").validate({
		rules: {
		    'lvAccountAddress.relName':{required: true,minlength:2,maxlength:32,isNotChinese:true},
			'lvAccountAddress.mobile':{isChineseChar:true,maxlength:16},
			'lvAccountAddress.tel':{isChineseChar:true,maxlength:16},
			'lvAccountAddress.postCode':{required: true,isChineseChar:true,maxlength:16}
		},
		messages: {
			'lvAccountAddress.relName': {
				required: "<font color='red'>请输入收貨人姓名</font>",
				minlength: "<font color='red'>姓名不能少于2位字符</font>",
				maxlength: "<font color='red'>姓名不能大于32位字符</font>",
				isNotChinese: "<font color='red'>姓名不能含有中文字符</font>"
			 },
			 'lvAccountAddress.mobile': {
				isChineseChar: "<font color='red'>手機不能含有特殊字符!</font>",
				maxlength: "<font color='red'>手機不能大于16位字符!</font>"
			 },
			 'lvAccountAddress.tel': {
				 isChineseChar: "<font color='red'>電話不能含有特殊字符!</font>",
				 maxlength: "<font color='red'>電話不能大于16位字符!</font>"
			 },
			'lvAccountAddress.postCode': {
				required: "<font color='red'>请输入郵政區號</font>",
				isChineseChar: "<font color='red'>郵政區號不能含有特殊字符</font>",
				maxlength: "<font color='red'>郵政區號不能大于16位字符</font>"
			 }
		},
		submitHandler:function(form){
			var isChinese = /[\u4E00-\u9FA5]/i; 
			var tel=$("#tel");//电话号码
			var mobile=$("#mobile");//手机号码
			var adress=$("#adress");//
			var cityName=$("#cityName");
			var provinceName=$("#provinceName");
			var contryId=$("#contryId");
			if($.trim(tel.val())==''&&$.trim(mobile.val())==''){
			  $("#mobileInfo").html("<font color='red'>電話和手機必須填寫其中一個</font>");
			  $("#telInfo").html("<font color='red'>電話和手機必須填寫其中一個</font>");
			  mobile.focus();
			  return ;
		    }else if($.trim(adress.val())==''||$.trim(adress.val())=='街道詳細地址'){
			  $("#addressInfo").html("<font color='red'>街道詳細地址不能為空！</font>");
			  $("#infoUl").show();
			  adress.focus();
			  return ;
		    }else if($.trim(cityName.val())==''||$.trim(cityName.val())=='縣/市'){
		      $("#addressInfo").html("<font color='red'>縣/市不能為空！</font>");
		      $("#infoUl").show();
			  cityName.focus();
			  return ;
		    }else if($.trim(provinceName.val())==''||$.trim(provinceName.val())=='洲/省'){
		      $("#addressInfo").html("<font color='red'>洲/省不能為空！</font>");
		      $("#infoUl").show();
			  provinceName.focus();
			  return ;
		    }else if($.trim($("#contryId").find("option:selected").val())==''){
		      $("#addressInfo").html("<font color='red'>國家不能為空！</font>");
		      $("#infoUl").show();
			  contryId.focus();
			  return ;
		    }else if(isChinese.test(adress.val())) {
			  $("#addressInfo").html("<font color='red'>街道詳細地址不能輸入中文！</font>");
			  $("#infoUl").show();
			  adress.focus();
			  return ;
		    }else if(isChinese.test(cityName.val())) {
		      $("#addressInfo").html("<font color='red'>縣/市不能輸入中文！</font>");
		      $("#infoUl").show();
			  cityName.focus();
			  return ;
		    }else if(isChinese.test(provinceName.val())) {
		      $("#addressInfo").html("<font color='red'>洲/省不能輸入中文！</font>");
		      $("#infoUl").show();
			  provinceName.focus();
			  return ;
		    }else if(adress.val().length>128){
		      $("#addressInfo").html("<font color='red'>街道詳細地址不能超過128位字符！</font>");
		      $("#infoUl").show();
			  adress.focus();
			  return ;
		    }else if(cityName.val().length>32){
		      $("#addressInfo").html("<font color='red'>縣/市不能超過32位字符！</font>");
		      $("#infoUl").show();
			  cityName.focus();
			  return ;
		    }else if(provinceName.val().length>32){
		      $("#addressInfo").html("<font color='red'>洲/省不能超過32位字符！</font>");
		      $("#infoUl").show();
			  provinceName.focus();
			  return ;
		    }
		    
		    
		    if($("input[name='addressCode']").length>=5){
               alert("常用地址大于5项，请先删除再添加！");
               return ;
            }
		    form.submit();
		    
		}
	});
});



</script>
	</head>
	<body>

		<div class="buy">
			<div class="buy_top">
				<img src="/res/images/shopping01.gif" />
			</div>
			<div class="buy_lc02"></div>

			<div class="fill_orders">
				<h1>
					填寫訂單信息
				</h1>
				<div class="fill_orders01">
					<h2>
						收貨人信息
					</h2>
					<div class="fill_orders_add" id="addressDiv">
						<h3>
							常用地址
						</h3>
						<!-- 如果是返回修改，则会有选中的地址 -->
						<c:if test="${not empty addressList}">
							<c:choose>
								<c:when test="${not empty addressCode}">
									<c:foreach items="${addressList}" var="address" varStatus="status">
										<c:if test="${address.code==addressCode}">
											<ul class="choose" id="address${status.count }">
												<li class="add"  style="word-break:break-all">
													<input name="addressCode" type="radio" value="${address.code }" checked="checked" onclick="showCarriage('${address.contryId }');" />
													${address.adress }&nbsp;${address.cityName}&nbsp;${address.provinceName }&nbsp;${address.contryName }
												</li>
												<li class="de">
													<a href="#" onclick="delAddress('${address.code }','${status.count }');">刪除</a>
												</li>
											</ul>
										</c:if>
										<c:if test="${address.code!=addressCode}">
											<ul id="address${status.count }">
												<li class="add"  style="word-break:break-all">
													<input name="addressCode" type="radio" value="${address.code }" onclick="showCarriage('${address.contryId }');" />
													${address.adress }&nbsp;${address.cityName}&nbsp;${address.provinceName }&nbsp;${address.contryName }
												</li>
												<li class="de">
													<a href="#"
														onclick="delAddress('${address.code }','${status.count }');">刪除</a>
												</li>
											</ul>
										</c:if>
									</c:foreach>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${not empty dAddress}">
											<c:foreach items="${addressList}" var="address" varStatus="sta">
												<c:if test="${address.code==dAddress.code}">
													<ul class="choose" id="address${sta.count }">
														<li class="add">
															<input name="addressCode" type="radio" value="${address.code }" checked="checked" onclick="showCarriage('${address.contryId }');" />
															${address.adress }&nbsp;${address.cityName}&nbsp;${address.provinceName }&nbsp;${address.contryName }
														</li>
														<li class="de">
															<a href="#" onclick="delAddress('${address.code }','${sta.count }');">刪除</a>
														</li>
													</ul>
												</c:if>
												<c:if test="${address.code!=dAddress.code}">
													<ul id="address${sta.count }">
														<li class="add">
															<input name="addressCode" type="radio" value="${address.code }" onclick="showCarriage('${address.contryId }');" />
															${address.adress }&nbsp;${address.cityName}&nbsp;${address.provinceName }&nbsp;${address.contryName }
														</li>
														<li class="de">
															<a href="#"
																onclick="delAddress('${address.code }','${sta.count }');">刪除</a>
														</li>
													</ul>
												</c:if>
											</c:foreach>
										</c:when>
										<c:otherwise>
											<c:foreach items="${addressList}" var="address" varStatus="st">
												<c:if test="${st.count==1}">
													<ul class="choose" id="address${st.count}">
														<li class="add">
															<input name="addressCode" type="radio" value="${address.code }" checked="checked" onclick="showCarriage('${address.contryId }');" />
															${address.adress }&nbsp;${address.cityName}&nbsp;${address.provinceName }&nbsp;${address.contryName }
														</li>
														<li class="de">
															<a href="#" onclick="delAddress('${address.code }','${st.count}');">刪除</a>
														</li>
													</ul>
												</c:if>
												<c:if test="${st.count!=1}">
													<ul id="address${st.count}">
														<li class="add">
															<input name="addressCode" type="radio" value="${address.code }" onclick="showCarriage('${address.contryId }');" />
															${address.adress }&nbsp;${address.cityName}&nbsp;${address.provinceName }&nbsp;${address.contryName }
														</li>
														<li class="de">
															<a href="#"
																onclick="delAddress('${address.code }','${st.count}');">刪除</a>
														</li>
													</ul>
												</c:if>
											</c:foreach>
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose>
						</c:if>
					</div>

					<div class="receive_info">
						<form id="addressForm" action="/web/shopCart!addAddress.action"
							method="post">
							<ul>
								<li class="wd1">
									<font class="redfont">*</font>收貨人姓名：
								</li>
								<li class="wd2">
									<input name="lvAccountAddress.relName" class="input2"
										type="text" /><font class="redfont">(姓名必須為英文)</font>
								</li>
							</ul>

							<ul>
								<li class="wd1">
									手機號碼：
								</li>
								<li class="wd2">
									<input name="lvAccountAddress.mobile"  onkeypress="onlyNumber(event)" class="input2"
										type="text" id="mobile" />
									<span id="mobileInfo"></span>
								</li>
							</ul>
							<ul>
								<li class="wd1">
									固定電話：
								</li>
								<li class="wd2">
									<input name="lvAccountAddress.tel" class="input2" type="text"
										id="tel" />
									<span id="telInfo"></span>
								</li>
							</ul>
							<ul>
								<li class="wd1">
									<font class="redfont">*</font>收貨地址：
								</li>
								<li class="wd2">
									<input name="lvAccountAddress.adress" id="adress" type="text"
										class="input2" value="街道詳細地址"
										onfocus="if(this.value=='街道詳細地址')this.value=''"
										onblur="if(this.value=='')this.value='街道詳細地址'" />
									-
									<input name="lvAccountAddress.cityName" id="cityName"
										type="text" class="input2" value="縣/市"
										onfocus="if(this.value=='縣/市')this.value=''"
										onblur="if(this.value=='')this.value='縣/市'" />
									-
									<!--<input name="lvAccountAddress.provinceName" id="provinceName"
										type="text" class="input2" value="洲/省"
										onfocus="if(this.value=='洲/省')this.value=''"
										onblur="if(this.value=='')this.value='洲/省'" />  -->
									<input type="hidden" id="test" />
									<input type="text" name="lvAccountAddress.provinceName" id="provinceName" class="input2" value="洲/省"  onfocus="if(this.value=='洲/省')this.value=''" onblur="if(this.value=='')this.value='洲/省'"/> 
									-
									<select name="lvAccountAddress.contryId" id="contryId"
										onchange="contryChange()">
										<option value="">
											--請選擇國家--
										</option>
										<c:foreach items="${contryList}" var="c">
											<option value="${c.id}">${c.nameen}</option>
										</c:foreach>
									</select>
									<input type="hidden" name="lvAccountAddress.contryName"
										id="contrynameId" value="" />
									<span id="addressInfo">为确保及时收到我们的产品，请填写真实地址（英文）</span>
								</li>
							</ul>
							<ul>
								<li class="wd1">
									<font class="redfont">*</font>郵編區號：
								</li>
								<li class="wd2">
									<input name="lvAccountAddress.postCode" class="input2"
										type="text" />
								</li>
							</ul>
							<ul>
								<li class="wd1">
									&nbsp;
								</li>
								<li class="wd2">
									<input type="image" src="/res/images/add_address.gif"
										width="113" height="23" onclick="onSub();" />
								</li>
							</ul>
						</form>
					</div>

					<div class="pay">
						<ul class="pay_t">
							支付方式
						</ul>
						<ul>
							<cus:paymethod></cus:paymethod>
							<li id="aa" style="display: none;">
								<font class="redfont">運費：USD <span id="carriage">${carriage}</span>
								</font>
							</li>
							<li id="bb" style="display: none;">
								<font class="redfont">溫馨提示：</font>您的地區屬於偏遠地區，需要加收運費，詳情請查看：
								<a href="/help2.html#M_12"><font class="bluewz">免費配送範圍</font>
								</a>
							</li>
						</ul>
					</div>
					<div class="pay">
						<ul class="pay_t">
							最佳收货时间
						</ul>
						<ul>
							<li>
								<input type='radio' name="bestDeliveryTime" value='1' <c:if test="${bestDeliveryValue==1 or empty bestDeliveryValue}">checked</c:if> />
								任何时间均可以送货
							</li>
							<li>
								<input type='radio' name="bestDeliveryTime" value='2' <c:if test="${bestDeliveryValue==2}">checked</c:if>/>
								周一至周五送货（周末没人无法签收）
							</li>
							<li>
								<input type='radio' name="bestDeliveryTime" value='3' <c:if test="${bestDeliveryValue==3}">checked</c:if>/>
								周末送货（工作日没人无法签收）
							</li>
							<li>
								<input type='radio' name="bestDeliveryTime" value='4' <c:if test="${bestDeliveryValue==4}">checked</c:if>/>
								晚上送货（白天没人无法签收）
							</li>
						</ul>
					</div>
					<div class="pay">
						<ul class="pay_t">
							商品清單
						</ul>
					</div>

					<!--订单列表-->
					<div class="buy_order" id="shopDiv">
						<ul>
							<li class="buy_order01">
								<p>
									&nbsp;
								</p>
								<p class="title">
									商品名称
								</p>
								<p>
									价格
								</p>
								<p>
									购买数量
								</p>
								<p>
									小计金额
								</p>
							</li>
							<c:foreach items="${objList}" var="obj" varStatus="status">
								<li id="cart${status.count }">
									<p>
										<img src="${obj[1].pimage }" width="70px" height="60px" />
									</p>
									<p class="title">
										${obj[1].productName }
									</p>
									<p>
										<font class="redfont fontwz">USD ${obj[0].shopPrice }</font>
									</p>
									<p>
										${obj[0].shopNum }
									</p>
									<p>
										<font class="redfont fontwz">USD <u:formatnumber
												value="${obj[0].shopPrice * obj[0].shopNum }" type="number" groupingUsed="false" maxFractionDigits="2"/>
										</font>
									</p>
								</li>
							</c:foreach>
						</ul>
						<ul class="sum">
							<span id="showData">商品总金额：USD <u:formatnumber value="${allAmount }"  type="number" groupingUsed="false" maxFractionDigits="2"/>- 优惠券减免：USD <span
								id="allCoupon"><u:formatnumber value="${allCouponPrice }"  type="number" groupingUsed="false" maxFractionDigits="2"/></span> + 运费USD <span id="allCarriage"><u:formatnumber value="${carriage}"  type="number" groupingUsed="false" maxFractionDigits="2"/></span>
							</span>
						</ul>
						<ul class="sum01">
							应付金额：
							<font class="redfont">USD <span id="amount"><u:formatnumber
										value="${allAmount+carriage-allCouponPrice}" type="number" groupingUsed="false" maxFractionDigits="2"/>
							</span>
							</font>
							</a>
						</ul>
					</div>

					<c:if test="${allNum>0}">
						<div class="coupons">
							<p>
								<a href="javascript:showDiv();"><font class="redfont"><span
										id="couponTitle">(+)使用TVpad優惠券</span>
								</font>
								</a>
							</p>
							<div id="couponDiv" style="display: none">
								<ul>
									<li class="btn">
										<a href="javascript:hideDiv();" onclick="hideDiv();">【開關】</a>
									</li>
									<li>
										提示：優惠券只針對TVpad機頂盒，其他產品均不能使用！訂購多台使用優惠券每台均可享受同等優惠！
									</li>
									<li>
										請輸入您的優惠碼：
										<label>
											<input id="couponCode" name="couponCode" type="text"
												class="input2" value="${couponCode }" onblur="checkCouponCode();" />
										</label>
										<a href="javascript:checkCouponCode();"><img
												src="/res/images/buy_icon.gif" width="55" height="23"
												border="0" />
										</a><span id="couponMsg"></span>
									</li>
								</ul>
							</div>
						</div>
					</c:if>
					<div class="pay">
						<ul class="pay_t">
							訂單備註留言
						</ul>
						<ul>
							<form id="myForm" action="/web/shopCart!toConfirmOrder.action"
								method="post" onsubmit="return onValidate()">
								<input type="hidden" id="myAddress" name="myAddress" value="" />
								<input type="hidden" id="payValue" name="payValue" value="" />
								<input type="hidden" id="bestDeliveryValue" name="bestDeliveryValue" value="" />
								<input type="hidden" id="myCoupon" name="myCoupon" value="${couponCode }" />
								<!--<input type="hidden" id="postPrice" name="postPrice" value="${carriage}"/>  -->
								<!--<input type="hidden" id="couponPrice" name="couponPrice" value=""/>  -->
								<!--<input type="hidden" id="couponNum" name="couponNum" value="${allNum }"/>  -->
								<li>
									<textarea id="orderRemark" name="orderRemark" class="input1"
										cols="" rows="">${orderRemark }</textarea>
								</li>
								<li class="">
									<input type="image" src="/res/images/sub.gif" border="0"/>
								</li>
							</form>
						</ul>
					</div>

				</div>
			</div>
		</div>
		<!--End 购物车-->
		<!-- footer-->
		<%@include file="/web/tvpadcn/common/footer.jsp"%>

	</body>
</html>

