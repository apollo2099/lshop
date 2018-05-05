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
<title>Submit Order-HUA YANG MALL</title>
<%@include file="/web/bsen/common/header.jsp" %>
<link href="/res/css/main.css" rel="stylesheet" type="text/css" />
<link href="/res/css/buy.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
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
	ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
	ymPrompt.confirmInfo('<strong>Are you sure you want to delete it?</strong>',null,null,'Tips',function handler(tp){if(tp=='ok'){
		$.ajax({
		   type: "POST",
		   url: "/web/shopCart!delAddress.action",
		   data: "code="+addressCode,
		   success: function(flag){
		   	 	if(flag){
		   	 		$("#address"+status).remove();
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
	   			$("#provinceName").append("<option value=''>--State/Province--</option>");
	   			for(var i=0;i<data.length;i++){
	   				$("<option></option>").val(data[i].id).text(data[i].nameen).appendTo($("#provinceName"));
	   			}
	   			$("#provinceName").after("<input type='hidden' name='lvAccountAddress.provinceName' id='provinceNameId'  value=''/>");
	   		}else{
	   			$("#provinceName").remove();
	   			$("#provinceNameId").remove();
	   			$("#test").after("<input type='text' name='lvAccountAddress.provinceName' id='provinceName' class='input2' value='State/Province' onfocus='if(this.value==&quot;State/Province&quot;) this.value=&quot;&quot;' onblur='if(this.value==&quot;&quot;)this.value=&quot;State/Province&quot;'/>");

	   		}
	   	}
	});
}	

function provinceChange(){
	//给洲省名称赋值
	$("#provinceNameId").val($("#provinceName").find("option:selected").text());
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
       ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
  	   ymPrompt.alert({title:'Tips',height:230,message:'Please choose a shipping address. If your frequently used address is empty, please add new frequently used address information first!'}) ;
       return false;
    }
    if($("input[name='payCode']:checked").val()==null){
       ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
  	   ymPrompt.alert({title:'Tips',message:'Please choose a mode of payment for your order!'}) ;
       return false;
    }
    if($("input[name='bestDeliveryTime']:checked").val()==null){
       ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
  	   ymPrompt.alert({title:'Tips',message:'Please choose you best receiving time!'}) ;
       return false;
    }
    if($("#orderRemark").val().length>160){
    	ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
  	    ymPrompt.alert({title:'Tips',message:'Remark shall be no longer than 160 characters!'}) ;
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
				required: "<font color='red'>Please enter the consignee name!</font>",
				minlength: "<font color='red'>Consignee name shall be at lease 2 characters!</font>",
				maxlength: "<font color='red'>Consignee name shall not be longer than 32 characters!</font>",
				isNotChinese: "<font color='red'>Name shall not cover Chinese characters!</font>"
			 },
			 'lvAccountAddress.mobile': {
				isChineseChar: "<font color='red'>Mobile No. shall not cover special characters!</font>",
				maxlength: "<font color='red'>Mobile No. shall not be longer than 16 characters!</font>"
			 },
			 'lvAccountAddress.tel': {
				 isChineseChar: "<font color='red'>Phone No. shall not cover special characters!</font>",
				 maxlength: "<font color='red'>Phone No. shall not be longer than 16 characters!</font>"
			 },
			'lvAccountAddress.postCode': {
				required: "<font color='red'>Please enter the Zip Code!</font>",
				isChineseChar: "<font color='red'>Zip Code shall not cover special characters!</font>",
				maxlength: "<font color='red'>Zip Code shall not be longer than 16 characters!</font>"
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
			  $("#mobileInfo").html("<font color='red'>You must fill in a best contact number we can reach you at least.</font>");
			  $("#telInfo").html("<font color='red'>You must fill in a best contact number we can reach you at least.</font>");
			  mobile.focus();
			  return ;
		    }else if($.trim(adress.val())==''||$.trim(adress.val())=='Street'){
			  $("#addressInfo").html("<font color='red'>Detailed street address shall not be null!</font>");
			  $("#infoUl").show();
			  adress.focus();
			  return ;
		    }else if($.trim(cityName.val())==''||$.trim(cityName.val())=='County/City'){
		      $("#addressInfo").html("<font color='red'>County/City shall not be null!</font>");
		      $("#infoUl").show();
			  cityName.focus();
			  return ;
		    }else if($.trim(provinceName.val())==''||$.trim(provinceName.val())=='State/Province'){
		      $("#addressInfo").html("<font color='red'>State/Province shall not be null!</font>");
		      $("#infoUl").show();
			  provinceName.focus();
			  return ;
		    }else if($.trim($("#contryId").find("option:selected").val())==''){
		      $("#addressInfo").html("<font color='red'>Coutry shall not be null!</font>");
		      $("#infoUl").show();
			  contryId.focus();
			  return ;
		    }else if(isChinese.test(adress.val())) {
			  $("#addressInfo").html("<font color='red'>Detailed street address shall not be in Chinese！</font>");
			  $("#infoUl").show();
			  adress.focus();
			  return ;
		    }else if(isChinese.test(cityName.val())) {
		      $("#addressInfo").html("<font color='red'>County/City shall not be in Chinese!</font>");
		      $("#infoUl").show();
			  cityName.focus();
			  return ;
		    }else if(isChinese.test(provinceName.val())) {
		      $("#addressInfo").html("<font color='red'>State/Province shall not be in Chinese！</font>");
		      $("#infoUl").show();
			  provinceName.focus();
			  return ;
		    }else if(adress.val().length>128){
		      $("#addressInfo").html("<font color='red'>Detailed street address shall not be longer than 128 characters!</font>");
		      $("#infoUl").show();
			  adress.focus();
			  return ;
		    }else if(cityName.val().length>32){
		      $("#addressInfo").html("<font color='red'>County/City shall not be longer than 32 characters!</font>");
		      $("#infoUl").show();
			  cityName.focus();
			  return ;
		    }else if(provinceName.val().length>32){
		      $("#addressInfo").html("<font color='red'>State/Province shall not be longer than 32 characters!</font>");
		      $("#infoUl").show();
			  provinceName.focus();
			  return ;
		    }
		    
		    
		    if($("input[name='addressCode']").length>=5){
               ymPrompt.setDefaultCfg({closeTxt: 'Close',okTxt: ' OK ',cancelTxt: ' Cancel '}); 
  		 	   ymPrompt.alert({title:'Tips',height:230,message:'If you have set 5 frequently used addresses and want to add a new one, please remove an old one first!'}) ;
               return ;
            }
		    form.submit();
		    
		}
	});
});
</script>
</head>
<body>
	<!-- top -->
	<%@include file="/web/bsen/common/top.jsp" %>
	
	<div class="buy">	
		<div class="buy_top"><img src="/res/images/shopping01.gif" /></div>
				<div class="buy_lc02"></div>
			<div class="fill_orders">
				<h1>
					Fill in Order Info
				</h1>
				<div class="fill_orders01">
					<h2>
						Billing Info
					</h2>
					<div class="fill_orders_add" id="addressDiv">
						<h3>
							Frequently used address
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
													${address.adress }&nbsp;${address.cityName}&nbsp;${address.provinceName }&nbsp;${address.contryName }
												</li>
												<li class="de">
													<a href="#" onclick="delAddress('${address.code }','${status.count }');">Delete</a>
												</li>
											</ul>
										</c:if>
										<c:if test="${address.code!=addressCode}">
											<ul id="address${status.count }">
												<li class="add"  style="word-break:break-all">
													<input name="addressCode" type="radio" value="${address.code }" onclick="showCarriage('${address.contryId }','${lvStore.storeFlag}','${allAmount }');" />
													${address.adress }&nbsp;${address.cityName}&nbsp;${address.provinceName }&nbsp;${address.contryName }
												</li>
												<li class="de">
													<a href="#"
														onclick="delAddress('${address.code }','${status.count }');">Delete</a>
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
															${address.adress }&nbsp;${address.cityName}&nbsp;${address.provinceName }&nbsp;${address.contryName }
														</li>
														<li class="de">
															<a href="#" onclick="delAddress('${address.code }','${sta.count }');">Delete</a>
														</li>
													</ul>
												</c:if>
												<c:if test="${address.code!=dAddress.code}">
													<ul id="address${sta.count }">
														<li class="add">
															<input name="addressCode" type="radio" value="${address.code }" onclick="showCarriage('${address.contryId }','${lvStore.storeFlag}','${allAmount }');" />
															${address.adress }&nbsp;${address.cityName}&nbsp;${address.provinceName }&nbsp;${address.contryName }
														</li>
														<li class="de">
															<a href="#"
																onclick="delAddress('${address.code }','${sta.count }');">Delete</a>
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
															${address.adress }&nbsp;${address.cityName}&nbsp;${address.provinceName }&nbsp;${address.contryName }
														</li>
														<li class="de">
															<a href="#" onclick="delAddress('${address.code }','${st.count}');">Delete</a>
														</li>
													</ul>
												</c:if>
												<c:if test="${st.count!=1}">
													<ul id="address${st.count}">
														<li class="add">
															<input name="addressCode" type="radio" value="${address.code }" onclick="showCarriage('${address.contryId }','${lvStore.storeFlag}','${allAmount }');" />
															${address.adress }&nbsp;${address.cityName}&nbsp;${address.provinceName }&nbsp;${address.contryName }
														</li>
														<li class="de">
															<a href="#"
																onclick="delAddress('${address.code }','${st.count}');">Delete</a>
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
									<font class="redfont">*</font>Full Name：
								</li>
								<li class="wd2">
									<input name="lvAccountAddress.relName" class="input2"
										type="text" /><font class="redfont">(English Only)</font>
								</li>
							</ul>

							<ul>
								<li class="wd1">
									Mobile No.：
								</li>
								<li class="wd2">
									<input name="lvAccountAddress.mobile" class="input2"
										type="text" id="mobile" />
									<span id="mobileInfo"></span>
								</li>
							</ul>
							<ul>
								<li class="wd1">
									Tel.：
								</li>
								<li class="wd2">
									<input name="lvAccountAddress.tel" class="input2" type="text"
										id="tel" />
									<span id="telInfo"></span>
								</li>
							</ul>
							<ul>
								<li class="wd1">
									<font class="redfont">*</font>Shipping Address：
								</li>
								<li class="wd2">
									<input name="lvAccountAddress.adress" id="adress" type="text"
										class="input2" value="Street"
										onfocus="if(this.value=='Street')this.value=''"
										onblur="if(this.value=='')this.value='Street'" />
									-
									<input name="lvAccountAddress.cityName" id="cityName"
										type="text" class="input2" value="County/City"
										onfocus="if(this.value=='County/City')this.value=''"
										onblur="if(this.value=='')this.value='County/City'" />
									-
									<!--<input name="lvAccountAddress.provinceName" id="provinceName"
										type="text" class="input2" value="洲/省"
										onfocus="if(this.value=='洲/省')this.value=''"
										onblur="if(this.value=='')this.value='洲/省'" />  -->
									<input type="hidden" id="test" />
									<input type="text" name="lvAccountAddress.provinceName" id="provinceName" class="input2" value="State/Province"  onfocus="if(this.value=='State/Province')this.value=''" onblur="if(this.value=='')this.value='State/Province'"/> 
									-
									<select name="lvAccountAddress.contryId" id="contryId"
										onchange="contryChange()">
										<option value="">
											--Country--
										</option>
										<c:foreach items="${contryList}" var="c">
											<option value="${c.id}">${c.nameen}</option>
										</c:foreach>
									</select>
									<input type="hidden" name="lvAccountAddress.contryName"
										id="contrynameId" value="" />
									<span id="addressInfo">To ensure timely and correctly delivery our products, please fill in your real address(English Only). </span>
								</li>
							</ul>
							<ul>
								<li class="wd1">
									<font class="redfont">*</font>Zip Code：
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
						 		<ul class="pay_t">Payment Methods</ul>
								<ul>
								<cus:paymethod></cus:paymethod>
									<li id="aa" style="display:none;"><font class="redfont">Shipping Cost：${lvStore.currency} <span id="carriage">${carriage}</span></font></li>
									<li id="bb" style="display:none;"><font class="redfont">Tips：</font>The address you choose belongs to remote areas, and you shall pay extra fees：<a href="/help2.html#M_12"><font class="bluewz">View free shipping zones.</font></a></li>
								</ul>							  
					  </div>
					<div class="pay">
						<ul class="pay_t">
							Best receiving time
						</ul>
						<ul>
							<li>
								<input type='radio' name="bestDeliveryTime" value='1' <c:if test="${bestDeliveryValue==1 or empty bestDeliveryValue}">checked</c:if> />
								Anytime
							</li>
							<li>
								<input type='radio' name="bestDeliveryTime" value='2' <c:if test="${bestDeliveryValue==2}">checked</c:if>/>
								Monday to Friday
							</li>
							<li>
								<input type='radio' name="bestDeliveryTime" value='3' <c:if test="${bestDeliveryValue==3}">checked</c:if>/>
								Weekend
							</li>
							<li>
								<input type='radio' name="bestDeliveryTime" value='4' <c:if test="${bestDeliveryValue==4}">checked</c:if>/>
								Night Delivery
							</li>
						</ul>
					</div>
					<div class="pay">
						<ul class="pay_t">
							Order Summary
						</ul>
					</div>
											
					<!--订单列表-->
					<div class="buy_order" id="shopDiv">
						<ul>
							<li class="buy_order01">
								<p>&nbsp;</p>
								<p class="title">Items Name</p>
								<p>Price</p>
								<p>Quantity</p>
								<p>Subtotal</p>
							</li>
							<li>
								<p><img src="${product.pimage }" width="70px" height="60px"/></p>
								<p class="title">${product.productName }</p>
								<p><font class="redfont fontwz">${lvStore.currency} ${groupBuy.presentPrice}</font></p>
						 	  	<p>${shopNum }</p>
						 	  	<p><font class="redfont fontwz">${lvStore.currency} <u:formatnumber value="${groupBuy.presentPrice *shopNum }"  type="number" groupingUsed="false" maxFractionDigits="2"/></font></p>
							</li>
						</ul>
						<ul class="sum"><span id="showData">Total：${lvStore.currency} <u:formatnumber value="${amount }"  type="number" groupingUsed="false" maxFractionDigits="2"/>+ Shipping Cost:${lvStore.currency} <span id="allCarriage">${carriage}</span></span></ul>
						<ul class="sum01">Merchandise Subtotal：<font class="redfont">${lvStore.currency} <span id="amount"><u:formatnumber value="${amount+carriage}"  type="number" groupingUsed="false" maxFractionDigits="2"/></span></font></a></ul>					
					</div>	
					<div class="pay">
							<ul class="pay_t">Other Request</ul>
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
	<%@include file="/web/bsen/common/footer.jsp" %>

</body>
</html> 
