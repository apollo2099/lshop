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
<title>提交訂單信息_華揚商城</title>
<%@include file="/web/bscn/common/header.jsp" %>
<link href="/res/css/main.css" rel="stylesheet" type="text/css" />
<link href="/res/css/buy.css" rel="stylesheet" type="text/css" />

<!-- top -->
<%@include file="/web/bscn/common/top.jsp" %>
	
<script type="text/javascript">
//定义全局变量，存放从JS获取的国家省市信息
var areaData; //0，国家  1，省列表（01，省  02，市列表）
$(function(){
	$.ajax({
	   type: "get",
	   url: "http://www.bananatv.com/res/js/area_cn.js",
	   dataType:"JSON",
	   async:false,
	   success: function(jsonData){
	   		areaData=eval(jsonData);
	   	}
	})
});


function toFloat(value) { //保留一位小数点
    value = Math.round(parseFloat(value) * 100) / 100;
    return value;
}
//动态显示运费
function showCarriage(contryId,shopFlag,allAmount){

	$("input[name='addressCode']").parent().parent().removeClass("choose");
	$("input[name='addressCode']:checked").parent().parent().addClass("choose");
	
	$.ajax({
	   type: "POST",
	   url: "/web/shopCart!getCarriage.action",
	   data: "deliveryId="+contryId+"&shopFlag="+shopFlag,
	   success: function(data){
	   	 	$("#carriage").html(data);
	   	 	$("#allCarriage").html(data);
	   	 	$("#amount").html(toFloat(toFloat(${amount })+toFloat($("#allCarriage").html())));
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

//选择国家时
function contryChange(){
	//给国家名称赋值
	$("#contrynameId").val($("#contryId").find("option:selected").text());
	
	//获取对应的省
	if(areaData.length>=1){
		$.each(areaData,function(n,provinceObj) {
			if(provinceObj[0].id==$("#contryId").val()){
				if(provinceObj[1].length>=1){
					$("#provinceName").remove();
					$("#provinceNameId").remove();
					$("#cityName").remove();
					$("#cityNameId").remove();
					$("#test").after("<select name='lvAccountAddress.provinceId' id='provinceName' class='input4'  onchange='provinceChange()' >");
					$("#provinceName").append("<option value=''>--请选择州/省--</option>");
					$.each(provinceObj[1],function(n,cityObj) {
						$("<option></option>").val(cityObj[0].id).text(cityObj[0].nameen).appendTo($("#provinceName"));
					});
					$("#provinceName").after("<input type='hidden' name='lvAccountAddress.provinceName' id='provinceNameId'  value=''/>");
					$("#cityTest").after("<input type='text' name='lvAccountAddress.cityName' id='cityName' class='input04' value='县/市' onfocus='if(this.value==&quot;县/市&quot;) this.value=&quot;&quot;' onblur='if(this.value==&quot;&quot;)this.value=&quot;县/市&quot;'/>");
				}else{
					$("#provinceName").remove();
					$("#provinceNameId").remove();
					$("#cityName").remove();
					$("#cityNameId").remove();
					$("#test").after("<input type='text' name='lvAccountAddress.provinceName' id='provinceName' class='input04' value='州/省' onfocus='if(this.value==&quot;州/省&quot;) this.value=&quot;&quot;' onblur='if(this.value==&quot;&quot;)this.value=&quot;州/省&quot;'/>");
					$("#cityTest").after("<input type='text' name='lvAccountAddress.cityName' id='cityName' class='input04' value='县/市' onfocus='if(this.value==&quot;县/市&quot;) this.value=&quot;&quot;' onblur='if(this.value==&quot;&quot;)this.value=&quot;县/市&quot;'/>");
				}
			}
		});	
	}
}

//选择州省时
function provinceChange(){
	//给洲省名称赋值
	$("#provinceNameId").val($("#provinceName").find("option:selected").text());
	
	//获取对应的市
	if(areaData.length>=1){
		$.each(areaData,function(n,provinceObj) {
			if(provinceObj[0].id==$("#contryId").val()){
				if(provinceObj[1].length>=1){
					$.each(provinceObj[1],function(n,cityObj) {
						if(cityObj[0].id==$("#provinceName").val()){
							if(cityObj[1].length>=1){
								$("#cityName").remove();
								$("#cityNameId").remove();
								$("#cityTest").after("<select name='lvAccountAddress.cityId' id='cityName' class='input4'  onchange='cityChange()' >");
								$("#cityName").append("<option value=''>--请选择县/市--</option>");
								$.each(cityObj[1],function(n,city) {
									$("<option></option>").val(city.id).text(city.nameen).appendTo($("#cityName"));
								});
								$("#cityName").after("<input type='hidden' name='lvAccountAddress.cityName' id='cityNameId'  value=''/>");
							}else{
								$("#cityName").remove();
								$("#cityNameId").remove();
								$("#cityTest").after("<input type='text' name='lvAccountAddress.cityName' id='cityName' class='input04' value='县/市' onfocus='if(this.value==&quot;县/市&quot;) this.value=&quot;&quot;' onblur='if(this.value==&quot;&quot;)this.value=&quot;县/市&quot;'/>");
							}
						}
					});
				}
				
			}
		});	
	}
}

function onSub(){
	$("#adressForm").ajaxSubmit({
		type: "POST",
		url: "/web/shopCart!addAddress.action",
		dataType:"json",
		success:function(data){
			window.location.reload(true); 
		}
	
	
	});
}

function subMyForm(){
	$("#myAddress").val($("input[name='addressCode']:checked").val());
	$("#payValue").val($("input[name='payCode']:checked").val());
	$("#bestDeliveryValue").val($("input[name='bestDeliveryTime']:checked").val());
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
				isChineseChar: "<font color='red'>手機不能含有特殊字符</font>",
				maxlength: "<font color='red'>手機不能大于16位字符</font>"
			 },
			 'lvAccountAddress.tel': {
				 isChineseChar: "<font color='red'>電話不能含有特殊字符</font>",
				 maxlength: "<font color='red'>電話不能大于16位字符</font>"
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
		<div class="buy_top"><img src="/res/images/shopping01.gif" /></div>
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
													<input name="addressCode" type="radio" value="${address.code }" checked="checked" onclick="showCarriage('${address.contryId }','${lvStore.storeFlag}','${allAmount }');" />
													<c:if test="${address.contryId==100023}">
														${address.contryName }&nbsp;${address.provinceName}&nbsp;${address.cityName }&nbsp;${address.adress }
													</c:if>
													<c:if test="${address.contryId!=100023}">
														${address.adress }&nbsp;${address.cityName }&nbsp;${address.provinceName}&nbsp;${address.contryName }
													</c:if>
												</li>
												<li class="de">
													<a href="#" onclick="delAddress('${address.code }','${status.count }');">删除</a>
												</li>
											</ul>
										</c:if>
										<c:if test="${address.code!=addressCode}">
											<ul id="address${status.count }">
												<li class="add"  style="word-break:break-all">
													<input name="addressCode" type="radio" value="${address.code }" onclick="showCarriage('${address.contryId }','${lvStore.storeFlag}','${allAmount }');" />
													<c:if test="${address.contryId==100023}">
														${address.contryName }&nbsp;${address.provinceName}&nbsp;${address.cityName }&nbsp;${address.adress }
													</c:if>
													<c:if test="${address.contryId!=100023}">
														${address.adress }&nbsp;${address.cityName }&nbsp;${address.provinceName}&nbsp;${address.contryName }
													</c:if>
												</li>
												<li class="de">
													<a href="#"
														onclick="delAddress('${address.code }','${status.count }');">删除</a>
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
															<input name="addressCode" type="radio" value="${address.code }" checked="checked" onclick="showCarriage('${address.contryId }','${lvStore.storeFlag}','${allAmount }');" />
															<c:if test="${address.contryId==100023}">
																${address.contryName }&nbsp;${address.provinceName}&nbsp;${address.cityName }&nbsp;${address.adress }
															</c:if>
															<c:if test="${address.contryId!=100023}">
																${address.adress }&nbsp;${address.cityName }&nbsp;${address.provinceName}&nbsp;${address.contryName }
															</c:if>
														</li>
														<li class="de">
															<a href="#" onclick="delAddress('${address.code }','${sta.count }');">删除</a>
														</li>
													</ul>
												</c:if>
												<c:if test="${address.code!=dAddress.code}">
													<ul id="address${sta.count }">
														<li class="add">
															<input name="addressCode" type="radio" value="${address.code }" onclick="showCarriage('${address.contryId }','${lvStore.storeFlag}','${allAmount }');" />
															<c:if test="${address.contryId==100023}">
																${address.contryName }&nbsp;${address.provinceName}&nbsp;${address.cityName }&nbsp;${address.adress }
															</c:if>
															<c:if test="${address.contryId!=100023}">
																${address.adress }&nbsp;${address.cityName }&nbsp;${address.provinceName}&nbsp;${address.contryName }
															</c:if>
														</li>
														<li class="de">
															<a href="#"
																onclick="delAddress('${address.code }','${sta.count }');">删除</a>
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
															<input name="addressCode" type="radio" value="${address.code }" checked="checked" onclick="showCarriage('${address.contryId }','${lvStore.storeFlag}','${allAmount }');" />
															<c:if test="${address.contryId==100023}">
																${address.contryName }&nbsp;${address.provinceName}&nbsp;${address.cityName }&nbsp;${address.adress }
															</c:if>
															<c:if test="${address.contryId!=100023}">
																${address.adress }&nbsp;${address.cityName }&nbsp;${address.provinceName}&nbsp;${address.contryName }
															</c:if>
														</li>
														<li class="de">
															<a href="#" onclick="delAddress('${address.code }','${st.count}');">删除</a>
														</li>
													</ul>
												</c:if>
												<c:if test="${st.count!=1}">
													<ul id="address${st.count}">
														<li class="add">
															<input name="addressCode" type="radio" value="${address.code }" onclick="showCarriage('${address.contryId }','${lvStore.storeFlag}','${allAmount }');" />
															<c:if test="${address.contryId==100023}">
																${address.contryName }&nbsp;${address.provinceName}&nbsp;${address.cityName }&nbsp;${address.adress }
															</c:if>
															<c:if test="${address.contryId!=100023}">
																${address.adress }&nbsp;${address.cityName }&nbsp;${address.provinceName}&nbsp;${address.contryName }
															</c:if>
														</li>
														<li class="de">
															<a href="#"
																onclick="delAddress('${address.code }','${st.count}');">删除</a>
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
						<form id="addressForm" action="/web/group!addAddress.action" method="post">
							<input type="hidden" name="shopNum" value="${shopNum}"/>
							<input type="hidden" name="lvGroupBuy.code" value="${groupBuy.code}"/>
							<ul>
								<li class="wd1">
									<font class="redfont">*</font>收货人姓名：
								</li>
								<li class="wd2">
									<input name="lvAccountAddress.relName" class="input4"
										type="text" /><font>（非中国地区请填写英文）</font>
								</li>
							</ul>

							<ul>
								<li class="wd1">
									手机号码：
								</li>
								<li class="wd2">
									<input name="lvAccountAddress.mobile"  onkeypress="onlyNumber(event)" class="input4"
										type="text" id="mobile" />
									<span id="mobileInfo">电话和手机必须填写其中一个</span>
								</li>
							</ul>
							<ul>
								<li class="wd1">
									固定电话：
								</li>
								<li class="wd2">
									<input name="lvAccountAddress.tel" class="input4" type="text"
										id="tel" />
									<span id="telInfo"></span>
								</li>
							</ul>
							<ul>
								<li class="wd1">
									<font class="redfont">*</font>收货地址：
								</li>
								<li class="wd2">
									<select name="lvAccountAddress.contryId" id="contryId"
										onchange="contryChange()">
										<option value="">
											--请选择国家--
										</option>
										<option value="100023">中国</option>
										<c:foreach items="${contryList}" var="c">
											<c:if test="${c.id!=100023}">
												<option value="${c.id}">${c.nameen}</option>
											</c:if>
										</c:foreach>
									</select>
									<input type="hidden" name="lvAccountAddress.contryName"
										id="contrynameId" value="" />
									-
									<input type="hidden" id="test" />
									<input type="text" class="input04" name="lvAccountAddress.provinceName" id="provinceName"  value="州/省"  onfocus="if(this.value=='州/省')this.value=''" onblur="if(this.value=='')this.value='州/省'"/> 
									-
									<input type="hidden" id="cityTest" />
									<input name="lvAccountAddress.cityName" id="cityName"
										type="text" class="input04" value="县/市"
										onfocus="if(this.value=='县/市')this.value=''"
										onblur="if(this.value=='')this.value='县/市'" />
									-
									<input name="lvAccountAddress.adress" id="adress" type="text"
										class="input4" value="街道详细地址"
										onfocus="if(this.value=='街道详细地址')this.value=''"
										onblur="if(this.value=='')this.value='街道详细地址'" />
									<span id="addressInfo">请填写真实有效的地址，非中国地址请填写英文</span>
								</li>
							</ul>
							<ul>
								<li class="wd1">
									<font class="redfont">*</font>邮编区号：
								</li>
								<li class="wd2">
									<input name="lvAccountAddress.postCode" class="input4"
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
						 		<ul class="pay_t">支付方式</ul>
								<ul>
								<cus:paymethod></cus:paymethod>
									<li id="aa" style="display:none;"><font class="redfont">運費：${lvStore.currency} <span id="carriage">${carriage}</span></font></li>
									<li id="bb" style="display:none;"><font class="redfont">溫馨提示：</font>您的地區屬於偏遠地區，需要加收運費，詳情請查看：<a href="/help2.html#M_12"><font class="bluewz">免費配送範圍</font></a></li>
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
					<ul class="pay_t">商品清單</ul>
				</div>

				<!--订单列表-->
					<div class="buy_order" id="shopDiv">
						<ul>
							<li class="buy_order01">
								<p>&nbsp;</p>
								<p class="title">商品名称</p>
								<p>价格</p>
								<p>购买数量</p>
								<p>小计金额</p>
							</li>
							<li>
								<p><img src="${product.pimage }" width="70px" height="60px"/></p>
								<p class="title">${product.productName }</p>
								<p><font class="redfont fontwz">${lvStore.currency} ${groupBuy.presentPrice}</font></p>
						 	  	<p>${shopNum }</p>
						 	  	<p><font class="redfont fontwz">${lvStore.currency} <u:formatnumber value="${groupBuy.presentPrice *shopNum }"  type="number" groupingUsed="false" maxFractionDigits="2"/></font></p>
							</li>
						</ul>
						<ul class="sum"><span id="showData">商品总金额：${lvStore.currency} <u:formatnumber value="${amount }"  type="number" groupingUsed="false" maxFractionDigits="2"/>+ 运费:${lvStore.currency} <span id="allCarriage">${carriage}</span></span></ul>
						<ul class="sum01">应付金额：<font class="redfont">${lvStore.currency} <span id="amount"><u:formatnumber value="${amount+carriage}"  type="number" groupingUsed="false" maxFractionDigits="2"/></span></font></a></ul>					
					</div>	
					<div class="pay">
							<ul class="pay_t">訂單備註留言</ul>
							<ul>
							<form id="myForm" action="/web/group!toConfirmGroup.action" method="post" onsubmit="return onValidate()">
								<input type="hidden" name="groupCode" value="${groupBuy.code}"/>
								<input type="hidden" name="shopNum" value="${shopNum}"/>
								<input type="hidden" id="myAddress" name="myAddress" value=""/>
								<input type="hidden" id="payValue" name="payValue" value=""/>
								<input type="hidden" id="bestDeliveryValue" name="bestDeliveryValue" value="" />
								<!--<input type="hidden" id="postPrice" name="postPrice" value="${carriage}"/>  -->
								<li><textarea id="orderRemark" name="orderRemark"  class="input1" cols="" rows="">${orderRemark }</textarea></li>
								
								<li class=""><a href="javascript:subMyForm();"><img src="/res/images/sub.gif" border="0"/></a></li>
							</form>
							</ul>
					</div>		
								
		</div></div>
</div>		
		<!--End 购物车-->
	<!-- footer-->
	<%@include file="/web/bscn/common/footer.jsp" %>

</body>
</html> 

