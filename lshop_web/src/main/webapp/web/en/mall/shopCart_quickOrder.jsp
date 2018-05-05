<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>TVpad Trade-in Program - Get latest TVpad4 with HD and smoother experience at $169</title>
<link href="${resDomain}/en/res/css/form_flowEn.css" rel="stylesheet" type="text/css" />
<link href="${resDomain}/en/res/css/Flow.css" type="text/css" rel="stylesheet"/>
<script src="${resDomain}/en/res/js/jquery-1.10.2.js" type="text/javascript"></script>

<%@include file="/web/en/common/top.jsp"%>
<script type="text/javascript" src="${resDomain}/www/res/js/searchSelect.js"></script>
<script type="text/javascript" src="${resDomain}/en/res/js/address.js"></script>
</head>
<body>
<!--bananer-->
<div class="mainpage1"></div>
<!--立即換新-->
<div class="mainpage2">
    <div class="mainpage2_top" id="md">
        <div class="liuchen_tit"></div>
        <div class="liuchen_tet">
            <p class="liuchen_p1">Enter your email,<br/>phone number and old<br/>TVpad’s MAC address</p>
            <p class="liuchen_p2">Enter your<br/>shipping address</p>
            <p class="liuchen_p3">Finish payment</p>
        </div>
    </div>
	<div class="mainpage2_buttom">
    	<!--以旧换新表单流程-->
        <!--確認換購-->
        <div class="flow" id="orderMacAdd" <c:if test="${not empty daccountMac}">style="display:none;"</c:if>>
            <form action="" class="" method="post" id="exForm" onsubmit="">
            <input name="loginstyle" type="hidden" id="loginstyle" value="0"/>
            <input name="productCode" type="hidden" value="${productCode }"/>
            <input name="sourceUrl" type="hidden" value="${sourceUrl }"/>
            <input name="shopFlag"  type="hidden"  value="${lvStore.storeFlag }" />
                <fieldset>
                    <div class="flow_mail">
                        <label>
                            <div class="tel_tit"><span>*</span>Email:</div>
                            <input type="text"  name="lvAccount.email" id="userEmail" onblur="subOrderEmail()"/>
                        </label>
                        <!--填写后的提示语段<p class="tel_red">請輸入賬號</p>-->
                        <p id="remind_email">The order status will be sent to your mailbox, so please enter a valid email.</p>
                    </div>
                   
                    <div class="flow_mima" style="display:none" id="divPwd">
	                 <label>
	                    <div class="tel_tit"><span>*</span>Password:</div>
	                    <input type="password" name="lvAccount.pwd" id="pwd"/>
	                    <p id="remind_pwd"><a class="yhxx_xg4" href="${storeDomain}/web/userCenterManage!toFindPassword.action" target="_blank">Forget password</a></p>
	                 </label>
                    </div>
                    
                    <div class="flow_tel">
                        <label>
                            <div class="tel_tit"><span>*</span>Tel:</div>
                            <input type="text"  class="input_txt1" id="phone" name="phone"  onblur="checkContact()" onkeypress="onlyNumber(event)" dvalue="Mobile No"/>
                        </label>
                        <%--
                        <label>
                            <input type="text"  class="input_txt2" id="tel" name="tel" onblur="checkContact()" dvalue="Phone No"/>
                        </label>
                         --%>
                        <p id="remind_phone">Please enter either of your contact number.</p>
                    </div>
                    <div class="flow_mac">
                        <label>
                            <div class="tel_tit"><span>*</span>MAC address:</div>
                            <input type="text"  maxlength="12" name="mac" id="mac" onblur="subOrderMac()">
                        </label>
                        <p id="remind_mac">12 characters, each MAC address can be used for only once.</p><p><em class="chak_em">Where to find the MAC address?<div class="chak"></div></em></p>
                    </div>
                </fieldset>
            
            <div class="flow_sub">
                 <input type="submit" class="sale" name="" value="Get $130 Off"/>
            </div>
            </form>
        </div>
        <!--確認換購--end-->
         <!--tv4产品价格-->
        <div class="product_tv4_2 clearfix" id="productInfo">
            <div class="product_img"><img src="${resDomain}/en/res/images/ermaien.jpg" alt="tvpad4"/></div>
            <div class="product_tet">
                <p>${product.productName }</p>
            </div>
            <div class="product_cost"><p><em>Price:</em><span>USD</span><strong>${product.price }</strong><br/>Get a USD${product.price-activityMac.amount } off by offering MAC address of your old Tvpad</p></div>
        </div>
        <!--tv4产品价格--end-->
        
        
        <!--提交订单-->
        <div class="indent" style="display:none">
            <!--用户信息-->
            <ul class="yhxx yhxx1" id="defaultOrderMac" <c:if test="${empty daccountMac}">style="display:none;"</c:if>>
                <li><span>Email:</span><p>${daccountMac.userEmail }</p></li>
                <li><span>Tel:</span><p> 
                <c:if test="${not empty daccountMac.contactPhone }">${daccountMac.contactPhone }</c:if>
	            <c:if test="${empty daccountMac.contactPhone and not empty daccountMac.contactTel }">${daccountMac.contactTel }</c:if>
	            </p>
	            </li>
                <li><span>MAC address:</span><p>${daccountMac.mac }<em id="changeOrderMac" class="yhxx_xg yhxx_xg1">[Edit]</em></p></li>
            </ul>
            <ul class="yhxx yhxx2"  id="defaultAddrUsed" style="display:none;">
                <li><span>Full Name:</span><p>${dAddress.relName}</p></li>
                <li><span>Address:</span><p><c:if test="${dAddress.contryId==100023}">
										${dAddress.contryName }&nbsp;${dAddress.provinceName}&nbsp;${dAddress.cityName }&nbsp;${dAddress.adress }
									</c:if>
									<c:if test="${dAddress.contryId!=100023}">
										${dAddress.adress }&nbsp;${dAddress.cityName }&nbsp;${dAddress.provinceName}&nbsp;${dAddress.contryName }
									</c:if></p></li>
                <li><span>Zip:</span><p>${dAddress.postCode}<em class="yhxx_xg yhxx_xg2" id="changeAddressUsed">Use this address</em></p></li>
            </ul>
            <ul class="yhxx yhxx4"  id="defaultAddr" <c:if test="${empty dAddress}">style="display:none;"</c:if> >
                <li><span>Full Name:</span><p>${dAddress.relName}</p></li>
                <li><span>Address:</span><p><c:if test="${dAddress.contryId==100023}">
										${dAddress.contryName }&nbsp;${dAddress.provinceName}&nbsp;${dAddress.cityName }&nbsp;${dAddress.adress }
									</c:if>
									<c:if test="${dAddress.contryId!=100023}">
										${dAddress.adress }&nbsp;${dAddress.cityName }&nbsp;${dAddress.provinceName}&nbsp;${dAddress.contryName }
									</c:if></p></li>
                <li><span>Zip:</span><p>${dAddress.postCode}<em class="yhxx_xg yhxx_xg4" id="changeAddress" addrCode="${dAddress.code }">[Edit]</em></p></li>
            </ul>
            <!--用户信息--end-->
            <!--收货地址-->
            <div class="address"  id="addressDiv" <c:if test="${not empty dAddress}">style="display:none;"</c:if>>
                    <form id="addressForm" action="/web/shopCart!addAddressQuick.action" method="post" >
                    <input type="hidden" name="productCode"  value="${productCode }"/>
                    <input type="hidden" name="sourceUrl" value="${sourceUrl }"/>
                    <input type="hidden" name="shopFlag" value="${lvStore.storeFlag }" />
                    <input type="hidden" name="lvAccountAddress.mobile" id="mobile" value="${daccountMac.contactPhone }"/>
		            <input type="hidden" name="lvAccountAddress.tel" id="tel" value="${daccountMac.contactTel }"/>
                    <fieldset>
                        <div class="address_receipt">
                            <label>
                                <div class="address_tit"><span>*</span>Full Name:</div>
                                <input type="text" name="lvAccountAddress.relName"/>
                            </label>
                            <p>English only</p>
                        </div>
                        <div class="address_site">
                            <label>
                                <div class="address_tit"><span>*</span>Address:</div>
                                <input type="text" class="input_txt3" name="lvAccountAddress.adress" id="adress" dvalue="Street address"/>
								
                            </label>
                            <div style="position:absolute; top:0px; left:215px;">
                                <span style="padding:0px 5px; float:left;">-</span>
                                <div id="citySelect" class="searchSelect" style="float:left" >
                                    <input class="searchSelectInput"></input><span class="searchSelectDown"></span>
                                </div>	
                                <input type="hidden" name="lvAccountAddress.cityId" id="cityId"/>
                                <input type="hidden" name="lvAccountAddress.cityName" id="cityName"/>	
                                <span style="padding:0px 5px; float:left;">-</span>
                                <div id="provinceSelect" class="searchSelect" style="float:left" >
                                    <input class="searchSelectInput"></input><span class="searchSelectDown"></span>
                                </div>
                                <input type="hidden" name="lvAccountAddress.provinceId" id="provinceId"/>
                                <input type="hidden" name="lvAccountAddress.provinceName" id="provinceName"/>								 
                                <span style="padding:0px 5px; float:left">-</span>
                                <div id="countrySelect" class="searchSelect" style="float:left">
                                    <input id="countrySelect-input" class="searchSelectInput"></input><span class="searchSelectDown"></span>
                                </div>									
                                <input type="hidden" name="lvAccountAddress.contryName"
                                    id="contryName" value="" />
                                <input type="hidden" name="lvAccountAddress.contryId"
                                    id="contryId" value="" />										
                                <p id="addressInfo">English only</p>
                                <!--填写后的提示语段<p class="tel_red">Choose your country!</p>-->
                           </div>
                        </div>
                        <div class="address_zip ">
                            <label>
                                <div class="address_tit"><span>*</span>Zip:</div>
                                <input type="text" id="mac_tet" name="lvAccountAddress.postCode" maxlength="12"/>
                            </label>
                            <!--填写后的提示语段<p class="tel_red">Please enter the Zip Code!</p>-->
                        </div>
                     </fieldset>

                <div class="address_sub">
                     <!--填写后的提示语段<p>Fields marked with "*" are required.</p>-->
                     <input id="addrBtn" type="submit" class="ship_add" name="" value="Save"/>
                </div>
                </form>
            </div>
            <!--收货地址--end-->
            
            
    
            <!--用户信息(支付方式单独放这里)-->
            <ul class="yhxx yhxx5" id="defaultPayMethod" <c:if test="${empty dPayment}">style="display:none;"</c:if>>
                <li><span>Payment:</span><p id="defaultPayMethodTxt">${dPayment.payName}</p><p><em class="yhxx_xg yhxx_xg5" id="changePayMethod">[Edit]</em></p></li>
            </ul>
            <!--用户信息--end-->
            <!--支付方式-->
            <div class="Pay clearfix" id="payMethodList" <c:if test="${not empty dPayment}">style="display:none"</c:if>>
                <span><em>*</em>Payment:</span>
                <form action="" class="">
                    <fieldset>
                    <ul>
                    <c:foreach items="${paymentList }" var="pay">
                        <li class="clearfix">
                            <input name="payCode" type="radio"  id="jcb" value="${pay.payValue }" <c:if test="${pay.payValue==dPayment.payValue }">checked="checked"</c:if>/>
                            <label for="jcb"><p>${pay.payName }</p></label>
                        </li>
                    </c:foreach>    
                    </ul>
                   </fieldset>
                </form>
                 <div class="Pay_sub">
                    <!--填写后的提示语段<p>Fields marked with "*" are required.</p>-->
                    <input type="submit" class="pay_mode" name="" value="Save" id="setPaymentBtn"/>
                 </div>
            </div>
            <!--支付方式--end-->
            
            
            <!--tv4产品价格-->
            <div class="product_tv4 clearfix">
                <div class="product_img"><img src="${resDomain}/en/res/images/ermaien.jpg" alt="tvpad4"></div>
                <div class="product_tet">
                    <p>${product.productName }</p>
                </div>
                <div class="product_cost"><hr class="hr_eng"/><p><em>Trade-in Program</em><span>USD${product.price }</span><br/><span class="usd_tet">USD</span><strong>${product.price-activityMac.amount }</strong><br/>Get ${activityMac.amount } off!</p></div>
            </div>
            <!--tv4产品价格--end-->
            <!--提交订单按钮-->
                <form action="/web/shopCart!saveOrder.action" method="post" id="cartInfoForm">
  		        <input type="hidden" id="shopFlag" name="shopFlag" value="${lvStore.storeFlag }" />
		        <input type="hidden" id="addressCode" name="addressCode" value="${dAddress.code }"/>
		        <input type="hidden" id="paymethod" name="lvOrder.paymethod" value="${dPayment.payValue}"/>
		        <input type="hidden" name="totalPrice" value="${totalAmount }"/>
		        <input type="hidden" id="couponCode" name="couponCode" value="" />
		        <input type="hidden" id="userCarriage" name="carriage" value="${carriage }" />
		        <input type="hidden" id="userProdAmount" name="prodAmount" value="" />
		        <input type="hidden" id="mac" name="mac" value="${daccountMac.mac  }"/>
		        <input type="hidden" id="bestDeliveryValue" name="bestDeliveryValue" value="1" />
            
            <div class="mit">
                <p>Please make sure that all the info provided above is correct!</p>
                <input type="submit" name="" value="Submit Order" id="subOrderBtn"/>
            </div>
            </form>
            <!--提交订单按钮--end-->
        </div>
        <!--提交订单--end-->
        <!--以旧换新表单流程--end-->
    </div>
</div>
<!--我要諮詢-->
<div class="mainpage7">
	<div class="lianxi clearfix">
        <h1>If you still have questions, please feel free to contact us</h1>
        <div class="dianhua">
        	<img src="${resDomain}/en/res/images/dianhua.png"/><p>Tel:<br/><span>(00852)21349910<br/>(00852)21349920<br/>(00852)21349901</span></p>
        </div>
        <div class="qq">
        	<img src="${resDomain}/en/res/images/QQ.png" /><p>QQ:<br/><span>2389075307<br/>2276814617</span></p>
        </div>
        <div class="youjian">	
            <img src="${resDomain}/en/res/images/youjian.png" class="youjian_img"/><p>Email:<br/><span>service@mtvpad.com</span></p>
        </div>
    </div>
    <div class="TV_weixin"></div>
    <p class="jieshiq">*TVpad reserves the right to interpret the program.</p>
</div>
</body>
</html>



<script type="text/javascript">
var resDomainArea = '${resDomain}/www/res/area_en/';
$(function(){
	init();
	initArea();
	/***/
	var userid=lshop.getCookie("userid");
	if(userid==null||userid.length<=0){
		$(".flow").show();
	}else{
		var daMac="${daccountMac.userEmail}";
		if(daMac==""&&daMac.length<=0){
			$(".flow").show();
			$("#productInfo").show();
			$(".indent").hide();
		}else{
			$(".flow").hide();
			$(".indent").show();
			$("#productInfo").hide();			
		}
	}
	
});

function init() {
	$("#userEmail").bind("focus", function(){
		$("#remind_email").html('The address will be the system login account and receive e-mail address, make sure you can use!');
		$("#remind_email").attr("style","");
		$("#remind_email").attr("class","");
		$("#remind_email").show();
	});
	$("#pwd").bind("focus", function(){
		$("#remind_pwd").html('6-16 characters, you can use a combination of letters, numbers or symbols');
		$("#remind_pwd").attr("style","");
		$("#remind_pwd").attr("class","");
		$("#remind_pwd").show();
	});
	$("#mac").bind("focus", function(){
		$("#remind_mac").html('12 characters, each MAC address can be used for only once.');
		$("#remind_mac").attr("style","");
		$("#remind_mac").attr("class","");
		$("#remind_mac").show();
	});
	$("#phone").bind("focus", function(){
		$("#remind_phone").html('Please enter either of your contact number');
		$("#remind_phone").attr("style","");
		$("#remind_phone").attr("class","");
		$("#remind_phone").show();
	});
	$("#tel").bind("focus", function(){
		$("#remind_phone").html('Please enter either of your contact number');
		$("#remind_phone").attr("style","");
		$("#remind_phone").attr("class","");
		$("#remind_phone").show();
	});
	
}

function checkContact(){
	if (js.assert.isNull("phone", 'Please enter your contact phone', "remind_phone")) {
		$("#remind_phone").html('Please enter your contact phone');
		$("#remind_phone").show();
		return false;
	}
	return true;
}

function checkOrderEmail() {
	if (js.assert.isNull("userEmail", 'Please enter your email', "remind_email")) {
		$("#remind_email").html('Please enter your email');
		$("#remind_email").attr("class","er");
		$("#remind_email").show();
		return false;
	}
	if (js.assert.isNotEmail("userEmail", 'Account format error', "remind_email")) {
		$("#remind_email").html('Account format error!');
		$("#remind_email").attr("class","er");
		$("#remind_email").show();
		return false;
	}
	if (js.assert.isIllegalLength("userEmail", "<2||>32", 'Account can only be 2-32 characters', "remind_email")) {
		$("#remind_email").html('Account can only be 2-32 characters');
		$("#remind_email").attr("class","er");
		$("#remind_email").show();
		return false;
	}
	return true;
}

function subOrderEmail(){
	var email = $("#userEmail").val();
	var isPass= true;
	if(checkOrderEmail()){
		$.ajax({   
			 url: '/web/userCenterManage!isExistsUser.action',
			 data: "lvAccount.email=" + email,   
			 type: 'POST', 
			 async: false,
			 success: function(num){   
			  if(num == 1) {
				  $("#remind_email").attr("class","");
				  $("#remind_email").html('We will send order status and logistics information to your mailbox, please fill in E-mail');
				  $("#remind_email").show();
				  $("#remind_email_es").show();
				  $("#divPwd").show();
				  isPass = false;
				  $("#loginstyle").val(1);
			   }else if(num==-1){
				   $("#remind_email").html('<p class="er">Invalid E-mail format</p>');
				   $("#remind_email").show();
				   isPass = false;
			   } else if(num == 0) {				   
				   $("#remind_email_es").hide();
				   $("#divPwd").hide();
				   $("#remind_email").attr("class","");
				   $("#remind_email").html('We will send order status and logistics information to your mailbox, please fill in E-mail');
			       $("#remind_email").show();
			       $("#loginstyle").val(0);
			   }
			 }   
		});
	}
	return isPass;
}




/*验证mac格式和非空*/
function checkOrderMac(){
	if (js.assert.isNull("mac", 'Please enter the MAC', "remind_mac")) {
		$("#remind_mac").attr("class","tel_red");
		$("#remind_mac").html('Please enter the MAC');
		$("#remind_mac").show();
		return false;
	}
	var mac=$("#mac").val();
	//验证码格式是否正确
	if (!/^ac867e[a-fA-F0-9]{6}$/.test(mac.toLowerCase())) {
		$("#remind_mac").attr("class","tel_red");
		$("#remind_mac").html('Please enter the correct MAC code！');
		$("#remind_mac").show();
		return false;
	}
	return true;
};

/**
 * 验证输入的mac是否已经下过订单了
 */
function subOrderMac(){
	var mac=$("#mac").val();
	var isPass=true;
	if(checkOrderMac()){
		$.ajax({   
			 url: '/web/mac!findMac.action',
			 data: "mac=" + mac+'&productCode=${productCode}',   
			 type: 'POST', 
			 async: false,
			 success: function(num){   
			   if(num == 1) {
				    $("#remind_mac").attr("class","tel_red");
	                $("#remind_mac").html('The MAC has been used');
	     		    $("#remind_mac").show();
				    isPass = false;
			   }else if(num == 2){
				    $("#remind_mac").attr("class","tel_red");
		            $("#remind_mac").html('The MAC has been used');
		     		$("#remind_mac").show();
					isPass = false;
			   }else if(num == -1){
				    $("#remind_mac").attr("class","tel_red");
		            $("#remind_mac").html('MAC redemption activity has ended');
		     		$("#remind_mac").show();
					isPass = false;
			   }
			 }   
		});
	}
	return isPass;
}


//兑换mac表单验证
function checkExMacForm(){
	if(checkOrderEmail()&&checkContact()&&checkOrderMac()){
		var phone=$("#phone").val();
		if(phone=="Mobile No"){
			$("#phone").val("");
		}
		return true;
	}
    return false;
}

//确认兑换mac
$("#exForm").validate({
	submitHandler:function(form){
		if(checkExMacForm()){
			$.post("/web/userCenterManage!loginRegister.action",$("#exForm").serialize(),function(str){
				var data = eval('(' + str + ')');
				var f = data.mark;
				
				if(f == 11) {
				    $("#remind_mac").attr("class","tel_red");
	                $("#remind_mac").html('The MAC has been used');
	     		    $("#remind_mac").show();
				}else if(f == 12){
					    $("#remind_mac").attr("class","tel_red");
			            $("#remind_mac").html('The MAC has been used');
			     		$("#remind_mac").show();
						isPass = false;
				}else if(f == -11){
					    $("#remind_mac").attr("class","tel_red");
			            $("#remind_mac").html('MAC redemption activity has ended');
			     		$("#remind_mac").show();
				}
				
				
				var loginstyle=$("#loginstyle").val();			
				if(loginstyle==0){
					if(data.mark==0){//注册成功
						var gotourl = "/web/shopCart!toQuickOrder.action?shopFlag=${shopFlag}&productCode=${productCode}&sourceUrl=${sourceUrl}#defaultOrderMac";
						change2LoginStatu(gotourl);
						return;
					}else if(data.mark==2){
						$("#remind_email").html("Registration Failed");
						$("#userEmail").focus();
					}
				}else if(loginstyle==1){
					if(f==1){
						var gotourl = "/web/shopCart!toQuickOrder.action?shopFlag=${shopFlag}&productCode=${productCode}&sourceUrl=${sourceUrl}#defaultOrderMac";
						change2LoginStatu(gotourl);
						return;
					}else if(f==-1){
						$("#remind_email_es").html("Account does not exist");
						$("#userEmail").focus();
					}else if(f==-2){
						$("#remind_email_es").html("Account has been disabled");
						$("#userEmail").focus();
					}else if(f==-3){
						$("#remind_email_es").html("Account is not activated");
						$("#userEmail").focus();
					}else if(f==-4){
						$("#remind_email_es").html("Incorrect password");
						$("#pwd").val("");
						$("#pwd").focus();
					}else if(f==-5){
						$("#remind_email_es").html("Verification code is incorrect");
					}else if(f==0){
						$("#remind_email_es").html("System erro");
					}
				}
			});
		}
	}
});



//mac兑换
$('#changeOrderMac').click(function(e){
	$('#orderMacAdd').show();
	$('#defaultOrderMac').hide();
	$(".indent").hide();
});

//收货地址
$('#changeAddress').click(function(e){
	$('#addressDiv').show();
	$("#defaultAddrUsed").show();
	$('#defaultAddr').hide();
});

$('#changeAddressUsed').click(function(e){
	$('#addressDiv').hide();
	$("#defaultAddrUsed").hide();
	$('#defaultAddr').show();
});



//支付方式
$('#changePayMethod').click(function(e){
	$('#payMethodList').show();
	$('#defaultPayMethod').hide();
});



//设置用户支付方式
$('#setPaymentBtn').click(function(e){
	var payment = $('input[name="payCode"]:checked').val();
	var shopFlag = $('#shopFlag').val();
	if(payment==null||payment.length<=0){
		$('#payMethodList').show();
		$('#defaultPayMethod').hide();
		alert("You need to save the payment, and then save the order!");
	}else{
		$.post('/web/shopCart!setDefaultPayment.action', {payValue: payment, shopFlag: shopFlag}, function(data){
			$('#defaultPayMethodTxt').empty().append(data.payName);
			$('#paymethod').val(payment);
			$('#payMethodList').hide();
			$('#defaultPayMethod').show();
		});
	}	
});


//提交订单
$('#subOrderBtn').click(function(e){
	//check
	var isSubmit = true;
	var msg = [];
	if(!$('#defaultOrderMac').is(':visible')){
		msg.push('You need to save the MAC exchange information, and then save the order');
		isSubmit = false;
	}
	if(!$('#defaultAddr').is(':visible')){
		msg.push('You need to save the recipient information, and then save the order');
		isSubmit = false;
	}
	if(!$('#defaultPayMethod').is(':visible')){
		msg.push('You need to save the payment, and then save the order');
		isSubmit = false;
	}
	if(!isSubmit){
		alert(msg.join('\n'));
		return false;
	}
	//submit
	//传回运费和商品中价格,校验是否发生变化
	$('#userProdAmount').val($('#prodAmount').text());
	$('#userCarriage').val($('#allCarriage').text());
	return true;
});


//图片显示与隐藏
$(".chak_em").hover(
	function(){
		$(".chak").css("display","block")
		},function(){
		$(".chak").css("display","none")
});
</script>

