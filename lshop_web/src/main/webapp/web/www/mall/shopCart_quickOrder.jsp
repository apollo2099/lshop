<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>TVpad以舊換新——立減$130換購最新TVpad4獲得高清流暢體驗</title>
<link href="${resDomain}/www/res/css/Flow.css" type="text/css" rel="stylesheet"/>
<link href="${resDomain}/www/res/css/form_flow.css" rel="stylesheet" type="text/css" />
<%@include file="/web/www/common/top.jsp"%>
<script type="text/javascript" src="${resDomain}/www/res/js/searchSelect.js"></script>
<script type="text/javascript" src="${resDomain}/www/res/js/address.js"></script>
<script type="text/javascript" src="${resDomain}/www/res/js/quickOrder.js"></script>

</head>

<body>

<!--bananer-->
<div class="mainpage1"></div>

<!--立即換新-->
<div class="mainpage2">
        <div id="md" class="mainpage2_top">
        <div  class="liuchen_tit"></div></a>
        <div  class="liuchen_tet">
            <p class="liuchen_p1">輸入您的資料<br/>及舊機MAC碼</p><p>輸入<br/>收貨地址</p><p>付款<br/>完成換購</p>
        </div>
    </div>
	<div  class="mainpage2_buttom">
    	<!--以旧换新表单流程-->
        <!--確認換購-->
<div class="gift" >
    <!-- 用户兑换信息 -->
     
    <div class="flow" id="orderMacAdd" <c:if test="${not empty daccountMac}">style="display:none;"</c:if>>
        <form action="" class="" method="post" id="exForm">
            <input name="loginstyle" type="hidden" id="loginstyle" value="0"/>
            <input name="productCode" type="hidden" value="${productCode }"/>
            <input name="sourceUrl" type="hidden" value="${sourceUrl }"/>
            <input name="shopFlag"  type="hidden"  value="${lvStore.storeFlag }" />
            <fieldset>
                <div class="flow_mail">
                    <label>
                    	<div class="tel_tit"><span>*</span>郵  箱:</div>
                    	<input type="text" name="lvAccount.email" id="userEmail" onblur="subOrderEmail()"/>
                    	<p id="remind_email">我們將會發送訂單狀態和物流資訊到您的郵箱，請務必填寫有效郵箱</p>
                        <p id="remind_email_es" class="tel_red tel_tish" style="display:none">您的郵箱已註冊，請輸入密碼</p>
                	</label>
                </div>

                <div class="flow_mima" style="display:none" id="divPwd">
	                <label>
	                    <div class="tel_tit"><span>*</span>密  碼:</div>
	                    <input type="password" name="lvAccount.pwd" id="pwd"/>
	                    <p id="remind_pwd"></p><a class="yhxx_xg4" href="${storeDomain}/web/userCenterManage!toFindPassword.action" target="_blank">忘記密碼</a>
	                </label>
                </div>
                <div class="flow_tel">
                    <label>
                    	<div class="tel_tit"><span>*</span>電  話:</div>
                        <input type="text"  class="input_txt1" id="phone" name="phone"  onblur="checkContact()" onkeypress="onlyNumber(event)" dvalue="移動電話"/>
                    </label>
                    <%--
                    <label>
                        <input type="text"  class="input_txt2" id="tel" name="tel" onblur="checkContact()" dvalue="固定電話"/>
                    </label>
                     --%>
                    <p id="remind_phone">電話可能用於確認訂單和快遞派件，建議您填寫移動電話號碼</p>
                </div>
                <div class="flow_mac">
                    <label>
                    	<div class="tel_tit"><span>*</span>舊機mac碼:</div>
                    	<input type="text"  maxlength="12" name="mac" id="mac" onblur="subOrderMac()"/>
                    	<p id="remind_mac">mac碼為12位，每個mac只可換購一次</p><p><em class="chak_em">怎麼查看mac碼？</em></p>
                	</label>
                </div>
                <div class="chak">333</div>
            </fieldset>
       
        <div class="flow_sub">
             <input type="submit" class="sale" name="" value="立減$130换购"/>
        </div>
        </form>
    </div>
</div>

<!-- 产品信息 -->
<div class="product_tv4_2 clearfix" id="productInfo">
    <div class="product_img"><img src="${resDomain}/www/res/images/ermai.jpg" alt="tvpad4"/></div>
    <div class="product_tet">
        <p>${product.productName }</p>
    </div>
    <div class="product_cost product_cost1"><p><em>售價</em><span>USD</span><strong>${product.price }</strong><br/>憑舊機mac可獲得優惠價USD${product.price-activityMac.amount }</p></div>
</div>

    
<div class="indent" style="display:none"> 
    <!--用户信息-->
    <ul class="yhxx yhxx1" id="defaultOrderMac" <c:if test="${empty daccountMac}">style="display:none;"</c:if>>
        <li><span>郵  箱:</span><p id="txtEmail">${daccountMac.userEmail }</p></li>
        <li><span>電  話:</span><p id="txtPhone">
	        <c:if test="${not empty daccountMac.contactPhone }">${daccountMac.contactPhone }</c:if>
	        <c:if test="${empty daccountMac.contactPhone and not empty daccountMac.contactTel }">${daccountMac.contactTel }</c:if>
        </p>
        </li>
        <li><span>舊機mac碼:</span><p id="txtMac">${daccountMac.mac }<a id="changeOrderMac" class="yhxx_xg yhxx_xg1">[修改]</a></p></li>
    </ul>
    <%--   --%>
    <ul class="yhxx yhxx2" id="defaultAddrUsed" style="display:none;">
        <li><span>收貨人姓名:</span><p>${dAddress.relName}</p></li>
        <li><span>收貨地址:</span><p><c:if test="${dAddress.contryId==100023}">
										${dAddress.contryName }&nbsp;${dAddress.provinceName}&nbsp;${dAddress.cityName }&nbsp;${dAddress.adress }
									</c:if>
									<c:if test="${dAddress.contryId!=100023}">
										${dAddress.adress }&nbsp;${dAddress.cityName }&nbsp;${dAddress.provinceName}&nbsp;${dAddress.contryName }
									</c:if></p></li>
        <li><span>郵  編:</span><p>${dAddress.postCode}<em class="yhxx_xg yhxx_xg2" id="changeAddressUsed">使用此地址</em></p></li>
    </ul>
  
     
    <ul class="yhxx yhxx4"  id="defaultAddr" <c:if test="${empty dAddress}">style="display:none;"</c:if> >
        <li><span>收貨人姓名:</span><p>${dAddress.relName}</p></li>
        <li><span>收貨地址:</span><p><c:if test="${dAddress.contryId==100023}">
										${dAddress.contryName }&nbsp;${dAddress.provinceName}&nbsp;${dAddress.cityName }&nbsp;${dAddress.adress }
									</c:if>
									<c:if test="${dAddress.contryId!=100023}">
										${dAddress.adress }&nbsp;${dAddress.cityName }&nbsp;${dAddress.provinceName}&nbsp;${dAddress.contryName }
									</c:if></p></li>
        <li><span>郵  編:</span><p>${dAddress.postCode}<em class="yhxx_xg yhxx_xg4" id="changeAddress" addrCode="${dAddress.code }">[修改]</em></p></li>
    </ul>
    <!--用户信息--end-->

    
    
    
     <!-- 收货地址表单 -->    
     <div class="address"  id="addressDiv" <c:if test="${not empty dAddress}">style="display:none;"</c:if>>
         <form id="addressForm" action="/web/shopCart!addAddressQuick.action" method="post" >
          <input type="hidden" name="productCode"  value="${productCode }"/>
          <input type="hidden" name="sourceUrl"  value="${sourceUrl }"/>
          <input type="hidden" name="shopFlag" value="${lvStore.storeFlag }" />
          <input type="hidden" name="lvAccountAddress.mobile" id="mobile" value="${daccountMac.contactPhone }"/>
		  <input type="hidden" name="lvAccountAddress.tel" id="tel" value="${daccountMac.contactTel }"/>
		  
            <fieldset>
                <div class="address_receipt">
                    <label>
                        <div class="address_tit"><span>*</span>收貨人姓名:</div>
                        <input type="text" name="lvAccountAddress.relName"/>
                    </label>
                    <p>姓名和地址必須為英文</p>
                </div>
                <div class="address_site">		
								<div class="address_tit"><font class="redfont">*</font>收貨地址：</div>
								<input type="text" class="input_txt3" name="lvAccountAddress.adress" id="adress" dvalue="詳細街道地址"/>
								
								<div style="position:absolute; top:0px; left:215px;">
									<span style="padding:0px 5px; float:left">-</span>
									<div id="citySelect" class="searchSelect" style="float:left">
										<input class="searchSelectInput"></input><span class="searchSelectDown"></span>
									</div>	
									<input type="hidden" name="lvAccountAddress.cityId" id="cityId"/>
									<input type="hidden" name="lvAccountAddress.cityName" id="cityName"/>	
									<span style="padding:0px 5px; float:left">-</span>
									<div id="provinceSelect" class="searchSelect" style="float:left">
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
									<p id="addressInfo">請填寫真實地址（英文）</p>
									<!--填写后的提示语段<p class="tel_red">請選擇國家</p>-->							
				</div>
                
                <div class="address_zip ">
                    <label>
                        <div class="address_tit" ><span>*</span>郵  編:</div>
                        <input type="text" id="mac_tet" name="lvAccountAddress.postCode" maxlength="12"/>
                    </label>
                     <!--填写后的提示语段<p class="tel_red">請填写邮编</p>-->
                </div>
             </fieldset>
        <div class="address_sub">
             <!--<p>請完整填寫表格，打"*"的項目為必填內容</p>-->
             <input id="addrBtn" type="submit" class="ship_add" name="" value="確認收貨地址"/>
        </div>
        </form>
    </div>

    
    
    <!-- 支付方式  -->
    <div class="Pay clearfix" id="payMethodList" <c:if test="${not empty dPayment}">style="display:none"</c:if>>
    	<span><em>*</em>支付方式:</span>
        <form action="" class="">
        	<fieldset>
            <ul>
                <c:foreach items="${paymentList }" var="pay">
            	<li class="clearfix">
            		<input name="payCode" type="radio" value="${pay.payValue }" <c:if test="${pay.payValue==dPayment.payValue }">checked="checked"</c:if> />
                	<label for="jcb"><p>${pay.payName }</p></label>
            	</li>
            	</c:foreach>
            </ul>
           </fieldset>
        </form>
         <div class="Pay_sub">
         	<!--<p>請完整填寫表格，打"*"的項目為必填內容</p>-->
            <input type="submit" class="pay_mode" name="" value="確認支付方式" id="setPaymentBtn"/>
         </div>
    </div>

    <ul class="yhxx yhxx5" id="defaultPayMethod" <c:if test="${empty dPayment}">style="display:none;"</c:if>>
        <li><span>支付方式:</span><p id="defaultPayMethodTxt">${dPayment.payName}</p><p><em class="yhxx_xg yhxx_xg5" id="changePayMethod">[修改]</em></p></li>
    </ul>
    
    <!-- 产品信息 -->
    <div class="product_tv4 clearfix">
    	<div class="product_img"><img src="${resDomain}/www/res/images/ermai.jpg" alt="tvpad4"/></div>
        <div class="product_tet">
        	<p>${product.productName }</p>
        </div>
        
        <div class="product_cost"><hr/><p><em>以舊換新</em><span>USD${product.price }</span><br/><span class="usd_tet">USD</span><strong>${product.price-activityMac.amount }</strong><br/>已獲得USD${activityMac.amount }優惠</p></div>
    </div>
    
    
    <!-- 保存订单信息 -->
    <form action="/web/shopCart!saveOrder.action" method="post" id="cartInfoForm">
  		<input type="hidden" id="shopFlag" name="shopFlag" value="${lvStore.storeFlag }" />
		<input type="hidden" id="addressCode" name="addressCode" value="${dAddress.code }"/>
		<input type="hidden" id="paymethod" name="lvOrder.paymethod" value="${dPayment.payValue}"/>
		<input type="hidden" name="totalPrice" value="${totalAmount }"/>
		<input type="hidden" id="couponCode" name="couponCode" value="" />
		<input type="hidden" id="userCarriage" name="carriage" value="${carriage }" />
		<input type="hidden" id="userProdAmount" name="prodAmount" value="" />
		<input type="hidden" id="mac" name="mac" value="${daccountMac.mac  }"/>
		<input type="hidden" id="macAmount" name="macAmount" value="${activityMac.amount  }"/>
		<input type="hidden" id="bestDeliveryValue" name="bestDeliveryValue" value="1" />
	    <div class="mit">
	    	<p>請確認以上信息填寫正確</p>
	        <input id="subOrderBtn" type="submit" name="" value="提交訂單"/>
	    </div>
    </form>
</div>


</div>
</div>

<!--我要諮詢-->
<div class="mainpage7">
	<div class="lianxi clearfix">
    	 <h1>若仍有疑問，請聯繫7×24小時客服服務中心</h1>
        <div class="dianhua">
        	<img src="${resDomain}/www/res/images/dianhua.png"/><p>24小時電話客服:<br/><span>(00852)21349910<br/>(00852)21349920<br/>(00852)21349901</span></p>
        </div>
        <div class="qq">
        	<img src="${resDomain}/www/res/images/QQ.png" /><p>客服QQ:<br/><span>2389075307<br/>2276814617</span></p>
        </div>
        <div class="youjian">
        	<img src="${resDomain}/www/res/images/youjian.png" class="youjian_img"/><p>Email:<br/><span>service@mtvpad.com</span></p>
        </div>
    </div>
    <div class="TV_weixin"></div>
    <p class="jieshiq">*TVpad官方在法律範圍內擁有本活動的最終解釋權</p>
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
		$("#remind_email").html('我們將會發送訂單狀態和物流資訊到您的郵箱，請務必填寫有效郵箱！');
		$("#remind_email").attr("style","");
		$("#remind_email").attr("class","");
		$("#remind_email").show();
	});
	$("#pwd").bind("focus", function(){
		$("#remind_pwd").html('6-16位字符，可使用字母、數字或符號的組合，不建議使用純字母、純數字、純符號！');
		$("#remind_pwd").attr("style","");
		$("#remind_pwd").attr("class","");
		$("#remind_pwd").show();
	});
	$("#mac").bind("focus", function(){
		$("#remind_mac").html('mac碼為12位，每個mac只可換購一次');
		$("#remind_mac").attr("style","");
		$("#remind_mac").attr("class","");
		$("#remind_mac").show();
	});
	$("#phone").bind("focus", function(){
		$("#remind_phone").html('電話可能用於確認訂單和快遞派件，建議您填寫移動電話號碼');
		$("#remind_phone").attr("style","");
		$("#remind_phone").attr("class","");
		$("#remind_phone").show();
	});
	$("#tel").bind("focus", function(){
		$("#remind_phone").html('電話可能用於確認訂單和快遞派件，建議您填寫移動電話號碼');
		$("#remind_phone").attr("style","");
		$("#remind_phone").attr("class","");
		$("#remind_phone").show();
	});
	
}

function checkContact(){
	if (js.assert.isNull("phone", '請輸入联系电话！', "remind_phone")) {
		$("#remind_phone").html('請輸入联系电话！');
		$("#remind_phone").show();
		return false;
	}
	return true;
}

function checkOrderEmail() {
	if (js.assert.isNull("userEmail", '請輸入賬號！', "remind_email")) {
		$("#remind_email").html('請輸入賬號！');
		$("#remind_email").attr("class","er");
		$("#remind_email").show();
		return false;
	}
	if (js.assert.isNotEmail("userEmail", '賬號格式錯誤！', "remind_email")) {
		$("#remind_email").html('賬號格式錯誤！');
		$("#remind_email").attr("class","er");
		$("#remind_email").show();
		return false;
	}
	if (js.assert.isIllegalLength("userEmail", "<2||>32", '賬號只能是2至32位字符！', "remind_email")) {
		$("#remind_email").html('賬號只能是2至32位字符！');
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
				  $("#remind_email").html('我們將會發送訂單狀態和物流資訊到您的郵箱，請務必填寫有效郵箱');
				  $("#remind_email").show();
				  $("#remind_email_es").show();
				  $("#divPwd").show();
				  isPass = false;
				  $("#loginstyle").val(1);
			   }else if(num==-1){
				   $("#remind_email").html('<p class="er">Email格式不正确！</p>');
				   $("#remind_email").show();
				   isPass = false;
			   } else if(num == 0) {				   
				   $("#remind_email_es").hide();
				   $("#divPwd").hide();
				   $("#remind_email").attr("class","");
				   $("#remind_email").html('我們將會發送訂單狀態和物流資訊到您的郵箱，請務必填寫有效郵箱');
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
	if (js.assert.isNull("mac", '請輸入MAC！', "remind_mac")) {
		$("#remind_mac").attr("class","tel_red");
		$("#remind_mac").html('請輸入MAC！');
		$("#remind_mac").show();
		return false;
	}
	var mac=$("#mac").val();
	//验证码格式是否正确
	if (!/^ac867e[a-fA-F0-9]{6}$/.test(mac.toLowerCase())) {
		$("#remind_mac").attr("class","tel_red");
		$("#remind_mac").html('請輸入正确的MAC码！');
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
	                $("#remind_mac").html('該MAC已經使用！');
	     		    $("#remind_mac").show();
				    isPass = false;
			   }else if(num == 2){
				    $("#remind_mac").attr("class","tel_red");
		            $("#remind_mac").html('該MAC已經使用！');
		     		$("#remind_mac").show();
					isPass = false;
			   }else if(num == -1){
				    $("#remind_mac").attr("class","tel_red");
		            $("#remind_mac").html('MAC兌換活動已經結束！');
		     		$("#remind_mac").show();
					isPass = false;
			   }else if(num == 0){
				    $("#remind_mac").html('mac碼為12位，每個mac只可換購一次');
		     		$("#remind_mac").show();
		     		isPass = true;
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
		if(phone=="移動電話"){
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
		                $("#remind_mac").html('該MAC已經使用！');
		     		    $("#remind_mac").show();
				}else if(f == 12){
					    $("#remind_mac").attr("class","tel_red");
			            $("#remind_mac").html('該MAC已經使用！');
			     		$("#remind_mac").show();
						isPass = false;
				}else if(f == -11){
					    $("#remind_mac").attr("class","tel_red");
			            $("#remind_mac").html('MAC兌換活動已經結束！');
			     		$("#remind_mac").show();
				}
				
				var loginstyle=$("#loginstyle").val();			
				if(loginstyle==0){
					if(data.mark==0){//注册成功
						var gotourl = "web/shopCart!toQuickOrder.action?shopFlag=${shopFlag}&productCode=${productCode}&sourceUrl=${sourceUrl}#defaultOrderMac";
						change2LoginStatu(gotourl);
						return;
					}else if(data.mark==2){
						$("#remind_email").html("註冊失敗");
						$("#userEmail").focus();
					}
				}else if(loginstyle==1){
					if(f==1){
						var gotourl = "web/shopCart!toQuickOrder.action?shopFlag=${shopFlag}&productCode=${productCode}&sourceUrl=${sourceUrl}#defaultOrderMac";
						change2LoginStatu(gotourl);
						return;
					}else if(f==-1){
						$("#remind_email_es").html("賬號不存在");
						$("#userEmail").focus();
					}else if(f==-2){
						$("#remind_email_es").html("賬號已停用");
						$("#userEmail").focus();
					}else if(f==-3){
						$("#remind_email_es").html("賬號未激活");
						$("#userEmail").focus();
					}else if(f==-4){
						$("#remind_email_es").html("密碼錯誤");
						$("#pwd").val("");
						$("#pwd").focus();
					}else if(f==-5){
						$("#remind_email_es").html("驗證碼錯誤");
					}else if(f==0){
						$("#remind_email_es").html("系統錯誤");
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


//设置用户支付方式
$('#setPaymentBtn').click(function(e){
	var payment = $('input[name="payCode"]:checked').val();
	var shopFlag = $('#shopFlag').val();
	if(payment==null||payment.length<=0){
		$('#payMethodList').show();
		$('#defaultPayMethod').hide();
		alert("您需要先保存支付方式，再保存訂單！");
	}else{
		$.post('/web/shopCart!setDefaultPayment.action', {payValue: payment, shopFlag: shopFlag}, function(data){
			$('#defaultPayMethodTxt').append(data.payName);
			$('#paymethod').val(payment);
			$('#payMethodList').hide();
			$('#defaultPayMethod').show();
		});
	}	
});


//支付方式
$('#changePayMethod').click(function(e){
	$('#payMethodList').show();
	$('#defaultPayMethod').hide();
});

//提交订单
$('#subOrderBtn').click(function(e){
	//check
	var isSubmit = true;
	var msg = [];
	if(!$('#defaultOrderMac').is(':visible')){
		msg.push('您需要先保存MAC兑换信息，再保存訂單');
		isSubmit = false;
	}
	if(!$('#defaultAddr').is(':visible')){
		msg.push('您需要先保存收貨人信息，再保存訂單');
		isSubmit = false;
	}
	if(!$('#defaultPayMethod').is(':visible')){
		msg.push('您需要先保存支付方式，再保存訂單');
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
