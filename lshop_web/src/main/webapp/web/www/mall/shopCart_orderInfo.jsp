<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld" %> 
<%@ taglib prefix="cus" uri="/WEB-INF/tld/cus.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%    
  response.setHeader("Pragma","No-cache");    
  response.setHeader("Cache-Control","no-cache");    
  response.setDateHeader("Expires",   0);    
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>填寫訂單信息_TVpad商城</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<!-- 加载公共JS -->
		<%@include file="/web/www/common/top.jsp"%>
		<link href="${resDomain}/www/res/css/buy.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${resDomain}/www/res/js/num_cal.js"></script>
		<script type="text/javascript" src="${resDomain}/www/res/js/shopCartOrderInfo.js"></script>
		<script type="text/javascript" src="${resDomain}/www/res/js/searchSelect.js"></script>
		<script type="text/javascript" src="${resDomain}/www/res/js/address.js"></script>
	</head>
	
	<body>
		<input type="hidden" value="${lvStore.storeFlag}" id="shopFlag"/>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/www/common/shopcart_header.jsp"%>
		
		<div class="buy">
			<div class="buy_lc02"></div>

			<div class="fill_orders">
				<div class="fill_orders01">
					<c:if test="${not empty errorMsg}">
						<div class="fill_orders_tips"><img src="${resDomain}/www/res/images/pos_icon.gif" width="19" height="19"/>${errorMsg}</div>
					</c:if>
					<!-- 收货人信息 -->
					<div id="defaultAddr" class="info_people" <c:if test="${empty dAddress}">style="display:none;"</c:if> >
						<h3>收貨人信息 <a id="changDefaultAddr" href="javascript:void(0);">[修改]</a></h3>
						<c:if test="${not empty dAddress}">
						<ul class="info_people1">
						<li class="wd1">${dAddress.relName}</li>
						<li class="wd2"><c:if test="${not empty dAddress.mobile}">${dAddress.mobile}</c:if>
										<c:if test="${empty dAddress.mobile}">${dAddress.tel}</c:if></li>
						</ul>
						<ul>
						<li class="wd3"><c:if test="${dAddress.contryId==100023}">
										${dAddress.contryName }&nbsp;${dAddress.provinceName}&nbsp;${dAddress.cityName }&nbsp;${dAddress.adress }
									</c:if>
									<c:if test="${dAddress.contryId!=100023}">
										${dAddress.adress }&nbsp;${dAddress.cityName }&nbsp;${dAddress.provinceName}&nbsp;${dAddress.contryName }
									</c:if></li>
						<li class="wd4">${dAddress.postCode}</li>
						</ul>
						</c:if>
					</div>
					
					<div class="fill_orders_add_bj" id="addressDiv" <c:if test="${not empty dAddress}">style="display:none;"</c:if> >
						<h3>收貨人信息</h3>
						<c:if test="${not empty addressList}">
						<c:foreach items="${addressList}" var="address" varStatus="sta">
							<c:choose>
							<c:when test="${not empty dAddress && address.code==dAddress.code}">
							<ul class="choose" id="address${sta.count }" deliveryId="${address.contryId }" >
								<li class="add1">
									<input name="addressCode" class="addrItem" type="radio" value="${address.code }" checked="checked" />${address.relName}
								</li>
							</c:when>
							<c:otherwise>
							<ul id="address${sta.count }" deliveryId="${address.contryId }">
								<li class="add1">
									<input name="addressCode" class="addrItem" type="radio" value="${address.code }" />${address.relName}
								</li>
							</c:otherwise>
							</c:choose>
								<li class="add2">
								<c:if test="${address.contryId==100023}">
									${address.contryName }&nbsp;${address.provinceName}&nbsp;${address.cityName }&nbsp;${address.adress }
								</c:if>
								<c:if test="${address.contryId!=100023}">
									${address.adress }&nbsp;${address.cityName }&nbsp;${address.provinceName}&nbsp;${address.contryName }
								</c:if>
								</li>
								<li class="add3">${address.postCode}</li>
								<li class="add4"><c:if test="${not empty address.mobile}">${address.mobile}</c:if>
									<c:if test="${empty address.mobile}">${address.tel}</c:if></li>
								<li class="de">
									<a href="javascript:void(0)" addrCode="${address.code }" class="addrEditBtn">編輯</a>
								</li>
								<li class="de">
									<a href="javascript:void(0)"
										onclick="delAddress('${address.code }','${sta.count }');">刪除</a>
								</li>
							</ul>
						</c:foreach>
					</c:if>
					<ul id="UladdAddrArea" <c:if test="${not empty addressList && fn:length(addressList)>=5}">style="display:none;"</c:if> ><li class="add1" >
						<input id="addAddrBtn" name="addressCode" type="radio" <c:if test="${empty addressList}">checked="checked"</c:if> value="" /> 使用新地址</li>
					</ul>
					</div>

					<div id="addAddrArea" class="receive_info_bj" <c:if test="${not empty dAddress}">style="display:none;"</c:if> >
						<form id="addressForm" action="<c:if test="${empty addressList}">/web/shopCart!addAddress.action</c:if>" method="post" onsubmit="return beforeAddressSubmit();">
							<input type="hidden" name="shopFlag" value="${lvStore.storeFlag}"/>
							<input type="hidden" name="lvAccountAddress.code" value="" id="editAddrCode"/>
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
									<input name="lvAccountAddress.adress" id="adress" type="text" style="float:left;"
										class="input2" value="街道詳細地址"
										onfocus="if(this.value=='街道詳細地址')this.value=''"
										onblur="if(this.value=='')this.value='街道詳細地址'" />
									<span style="float:left;padding:0px 5px;">-</span>
									<div id="citySelect" class="searchSelect"  style="float:left;">
										<input class="searchSelectInput"></input><span class="searchSelectDown"></span>
									</div>	
									<input type="hidden" name="lvAccountAddress.cityId" id="cityId"/>
									<input type="hidden" name="lvAccountAddress.cityName" id="cityName"/>	
									<span style="float:left;padding:0px 5px;">-</span>
									<div id="provinceSelect" class="searchSelect"  style="float:left;">
										<input class="searchSelectInput"></input><span class="searchSelectDown"></span>
									</div>
									<input type="hidden" name="lvAccountAddress.provinceId" id="provinceId"/>
									<input type="hidden" name="lvAccountAddress.provinceName" id="provinceName"/>								 
									<span style="float:left;padding:0px 5px;">-</span>
									<div id="countrySelect" class="searchSelect"  style="float:left;">
										<input class="searchSelectInput"></input><span class="searchSelectDown"></span>
									</div>									
									<input type="hidden" name="lvAccountAddress.contryName"
										id="contryName" value="" />
									<input type="hidden" name="lvAccountAddress.contryId"
										id="contryId" value="" />										
									<span id="addressInfo">請填寫真實地址（英文）</span>
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
								<li class="wd2"><input id="addrBtn" class="btn07" type="button" value="確認收貨地址" /></li>
							</ul>
						</form>
					</div>
					
		<!-- 支付方式 -->
		<div id="defaultPayMethod" class="pay" <c:if test="${empty dPayment}">style="display:none;"</c:if> >
		<h3>支付方式 <a id="changePayMethod" href="javascript:void(0)">[修改]</a></h3>
		<ul id="defaultPayMethodTxt">
		<c:if test="${not empty dPayment}">
		<li>${dPayment.payName}</li>
		</c:if>
		</ul>							  
		</div>
		<div id="payMethodList" class="pay_bj" <c:if test="${not empty dPayment}">style="display:none"</c:if> >
			<h3>支付方式<a href="/help3.html#M_14" target="_blank"><font class="redfont" size=2>（支付提醒 ）</font></a> </h3>
			<ul>
				<cus:paymethod storeflag="${lvStore.storeFlag}"></cus:paymethod>
				<li><input id="setPaymentBtn" class="btn07" type="button" value="確認支付方式" /></li>
			</ul>
		</div>
		
		<!-- 商品清單 -->
		<div class="pay">
			<h3>商品清單</h3>
			<ul>商家：${lvStore.name}</ul>
			<ul class="right">
				<a href="web/mall!getShopCartList.action">返回修改購物車</a>
			</ul>
			<table width="980" height="353" border="0" cellpadding="0" cellspacing="1" bgcolor="#eeeeee" style=" margin-top:10px;">
			<tr>
              <td width="591" height="37" align="center" bgcolor="#eeeeee"><span class="STYLE1">商品信息</span></td>
              <td width="152" align="center" bgcolor="#eeeeee"><span class="STYLE1">單價</span></td>
              <td width="116" align="center" bgcolor="#eeeeee"><span class="STYLE1">購買數量</span></td>
              <td width="121" align="center" bgcolor="#eeeeee"><span class="STYLE1">小計</span></td>
            </tr>
            <c:foreach items="${objList}" var="obj" varStatus="status">
            <input type="hidden" value="${obj[0].productCode}" class="product"></input>
			<tr>
               <td  height="82" align="center" valign="middle" bgcolor="#ffffff"><table width="100%" height="82" border="0" cellpadding="0" cellspacing="0">
                 <tr>
                   <td width="200" align="center" valign="middle"><img src="${obj[1].pimage }" width="70px" height="60px" /></td>
                   <td width="391" align="left">${obj[1].productName }</td>
                 </tr>
               </table></td>
               <td width="152" height="82" align="center" bgcolor="#ffffff"><span class="price1 redfont"><strong>${lvStore.currency} ${obj[0].shopPrice }</strong></span></td>
               <td width="116" height="82" align="center" bgcolor="#ffffff">${obj[0].shopNum }</td>
               <td width="121" height="82" align="center" bgcolor="#ffffff"><span class="price1 redfont"><strong>${lvStore.currency} ${obj[2] }</strong></span></td>
             </tr>
             </c:foreach>
             <tr id="order_activity_tip" style="display:none;"></tr>
   			<tr>
   				<td height="32" colspan="4" align="right" bgcolor="#F5F5F5" style="; padding:0 30px 0 0;">
   					<p style="display:inline; padding-right:20px;font-size:14px; font-weight:bold"><span class="price1  redfont">${shopcartNum}</span> 件商品</p>
   					<p style="display:inline; font-size:14px; font-weight:bold">總商品金額： <span class="price1  redfont">${lvStore.currency} ${allAmount }</span></span></p>
   				</td>
   			</tr>
			</table>
		</div>
					
		<!-- 优惠券 -->
		<div class="coupons" id="editCoupon" style="display:none;">
			<p><a href="javascript:void(0);" id="editCouponBtn">(+) 使用優惠券抵消部分總額，單筆訂單僅可只用一次</a> </p>
		</div>
		<div class="coupons" id="listCoupon" >
			<p><a href="javascript:void(0);" id="listCouponBtn">(-) 使用優惠券抵消部分總額，單筆訂單僅可只用一次  </a><a href="javascript:void(0);" id="couponCancelBtn"><font class="bluewz">取消使用</font></a></p>
			<ul>
				<li class="gray">使用賬戶中當前訂單可用的優惠券</li>
				<c:if test="${not empty couponList}">
				<c:foreach items="${couponList}" var="coupon" varStatus="vs">
				<li><input class="myCoupon" name="coupon" type="radio" value="${coupon.code}"
					couponVal="${coupon.amount}"  />${coupon.name}<p class="amount">-${coupon.currency} ${coupon.amount}</p>
				</li>
				</c:foreach>
				</c:if>
				<c:if test="${empty couponList}">
				<li>無</li>
				</c:if>
				</ul>
				<ul>
				<li class="gray">使用優惠券碼</li>
				<li><input id="useCouponCodeBtn" name="coupon" type="radio" value="" couponval="0.0" <c:if test="${empty couponList}">checked="checked"</c:if> />輸入您手中的優惠券碼
					<span id="couponCodeInput" <c:if test="${not empty couponList}">style="display:none;"</c:if>>
					<input id="couponCodeVal" name="textfield" type="text" class="input3"
					 maxlength="12"/> <input type="button" name="button" id="couponCodeBtn" class="btn08" value="确定" />	<font id="couponCodeTip" class="redfont"></font></span>
					<p id="couponCodeUse" class="use" style="display:none;"><span id="couponCodeName"></span><a href="javascript:void(0);" id="couponCodeCancelBtn">取消</a></p>
				</li>
				</li>
			</ul>
		</div>
		
		<form id="myForm" action="/web/shopCart!saveOrder.action" method="post" >
		<input type="hidden"  name="shopFlag" value="${lvStore.storeFlag}" />
		<input type="hidden" id="myAddress" name="addressCode" value="<c:if test="${not empty dAddress}">${dAddress.code}</c:if>" />
		<input type="hidden" id="payValue" name="lvOrder.paymethod" value="<c:if test="${not empty dPayment}">${dPayment.payValue}</c:if>" />
		<input type="hidden" id="bestDeliveryValue" name="bestDeliveryValue" value="1" />
		<input type="hidden" id="couponCode" name="couponCode" value="" />
		<input type="hidden" id="userCarriage" name="carriage" value="" />
		<input type="hidden" id="userProdAmount" name="prodAmount" value="" />
		
		<!-- 备注 -->
		<div class="pay">
			訂單備註<input type="text" id="orderRemark" name="orderRemark" value="${orderRemark }" maxlength="160" />
		</div>
		
		<div class="total_pay" id="totalPayArea">
        	<ul>
            	<li class="wd1">商品總金額：<font class="redfont">${lvStore.currency}<span id="prodAmount">${allAmount }</span></font>
            	 +  運費：<font class="redfont">${lvStore.currency} <span id="allCarriage">${carriage}</span></font>
            	<span id="couponAmountArea"> - 優惠券：<font class="redfont">${lvStore.currency} <span id="couponAmount">0.0</span></font></span></li>             
                <li class="wd2">應付金額：<font class="redfont" style="font-size:20px;">${lvStore.currency} <span id="amount">${totalAmount }</span></font> </li>
                <li class="wd3">
					<input id="subOrderBtn" class="btn04" type="submit" value="提交訂單"  /></li>
            </ul>
        </div>

		</form>
				</div>
			</div><!-- end of fill_orders -->
		</div>
		<!--End 购物车-->
		
		<!-- 底部广告位-->
		<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>

		<!-- footer-->
		<%@include file="/web/www/common/footer.jsp"%>
<script type="text/javascript">
$(function(){
	//先进行一次购物车计算,加载订单满就送活动
	$('#totalPayArea').trigger('changeOrderPay');
	//支付方式选中默认方式
	if($('#defaultPayMethod').is(':visible')){
		var pval = $('#payValue').val();
		$('#payMethodList').find('input[name="payCode"][value="'+pval+'"]').attr('checked', 'checked');
	}
});
//收货人信息
$('#changDefaultAddr').click(function(e){
	$('#defaultAddr').hide();
	$('#addressDiv').show();
	$('#addAddrArea').show();
});
$('#addrBtn').click(function(e){
	var action = $('#addressForm').attr('action');
	if(!action){
		var addr = $('.addrItem:checked').val();
		if(!addr){
			alert('請先選擇收貨人信息!');
			return false;
		}
		var $daddr = $('.addrItem:checked').parents('ul');
		//设置默认收货人信息
		$.post('/web/shopCart!setDefaultAddress.action', {myAddress: addr}, function(data){
			$('#myAddress').val(addr);
			var $daddrArea = $('#defaultAddr');
			$daddrArea.find('.wd1').text($daddr.find('.add1').text());
			$daddrArea.find('.wd2').text($daddr.find('.add4').text());
			$daddrArea.find('.wd3').text($daddr.find('.add2').text());
			$daddrArea.find('.wd4').text($daddr.find('.add3').text());
			$('#defaultAddr').show();
			$('#addressDiv').hide();
			$('#addAddrArea').hide();
			//修改运费
			$.post('/web/shopCart!getCarriage.action', {deliveryId: $daddr.attr('deliveryId'), shopFlag:$('#shopFlag').val()}, function(data){
				$('#allCarriage').text(data);
				$('#totalPayArea').trigger('changeOrderPay');
			});
		});
	} else {
		$('#addressForm').submit();
	}
});
$('#addressDiv').delegate('.addrEditBtn', 'click', function(e){
	var eaddr = $(this).attr('addrCode');
	$('#editAddrCode').val(eaddr);
	$('#addressForm').attr('action', '/web/shopCart!editAddress.action');
	$('#addressDiv .choose').removeClass('choose');
	$('input[name="addressCode"]').attr('checked', false);
	$(this).parents('ul').addClass('choose').find('.addrItem').attr('checked',true);
	$.post('/web/shopCart!getAddressJson.action', {myAddress: eaddr}, function(data){
		$('input[name="lvAccountAddress.relName"]').val(data.relName);
		$('input[name="lvAccountAddress.mobile"]').val(data.mobile);
		$('input[name="lvAccountAddress.tel"]').val(data.tel);
		$('input[name="lvAccountAddress.postCode"]').val(data.postCode);
		if (data.contryId > 0) {
			$('#contryId').val(data.contryId);
			countrySelect.setSelectedId(data.contryId);
		} else {
			$('#contryId').val('');
		}
		$('#contryName').val(data.contryName);
		var provinceId = data.provinceId;
		var pattern = /\d/ig;
		if (!pattern.test(provinceId)) {
			provinceId = -1;
		}
		var cityId = data.cityId;
		if (!pattern.test(cityId)) {
			cityId = -1;
		}		
		if (provinceId > 0) {
			$('#provinceId').val(provinceId);
			$.getScript(resDomainArea+data.contryId+'.js',function(){
				provinceSelect.setData(provinceData);
				provinceSelect.setSelectedId(provinceId);
				if (cityId > 0) {
					$('#cityId').val(cityId);
					$.getScript(resDomainArea+data.contryId+'/'+provinceId+'.js',function(){
						citySelect.setData(cityData);
						citySelect.setSelectedId(cityId);
					});					
				} else {
					$('#cityId').val('');
					citySelect.setData([]);
					citySelect.setText(data.cityName);
				}
				$('#cityName').val(data.cityName);				
			});			
		} else {
			$('#provinceId').val('');
			provinceSelect.setData([]);
			provinceSelect.setText(data.provinceName);
			$('#cityId').val('');
			$('#cityName').val(data.cityName);
			citySelect.setData([]);
			citySelect.setText(data.cityName);			
		}
		$('#provinceName').val(data.provinceName);

		//街道
		$('#adress').val(data.adress);
	});
}).delegate('.addrItem', 'click', function(e){
	var $f = $('#addressForm');
	$f[0].reset();
	resetArea();
	$f.attr('action', '');
	$('.choose').removeClass('choose');
	$(this).parents('ul').addClass('choose');
});
$('#addAddrBtn').click(function(e){
	var $f = $('#addressForm');
	$f[0].reset();
	resetArea();
	$('#editAddrCode').val('');
	$f.attr('action', '/web/shopCart!addAddress.action');
	$('#addressDiv .choose').removeClass('choose');
	$(this).parents('ul').addClass('choose');
});
//支付方式
$('#changePayMethod').click(function(e){
	$('#payMethodList').show();
	$('#defaultPayMethod').hide();
});
$('#setPaymentBtn').click(function(e){
	var payment = $('input[name="payCode"]:checked').val();
	var shopFlag = $('#shopFlag').val();
	$.post('/web/shopCart!setDefaultPayment.action', {payValue: payment, shopFlag: shopFlag}, function(data){
		$('#defaultPayMethodTxt').empty().append('<li>'+data.payName+'</li>');
		$('#payValue').val(payment);
		$('#payMethodList').hide();
		$('#defaultPayMethod').show();
	});
});
//优惠券
$('#editCouponBtn').click(function(e){
	$('#editCoupon').hide();
	$('#listCoupon').show();
	//没有选中优惠券,则让它选中第一个
	$('input[name="coupon"]:first').trigger('click');
});
$('#listCouponBtn').click(function(e){
	$('#editCoupon').show();
	$('#listCoupon').hide();
});
$('#couponCancelBtn').click(function(e){
	$('#editCoupon').show();
	$('#listCoupon').hide();
	$('input[name="coupon"]').attr('checked', false);
	//若使用了优惠券,则需要重新计算订单
	if($('#couponCode').val()) {
		$('#couponCode').val('');
		$('#couponAmount').text('0.0');
		$('#totalPayArea').trigger('changeOrderPay');
	}
});
$('#listCoupon').delegate('.myCoupon', 'click', function(e){
	var $t = $(this);
	$('#couponCodeInput').hide();
	if($('#useCouponCodeBtn').val()) {
		$('#couponCodeUse').show();
	}
	//购物车计算
	$('#couponCode').val($t.val());
	$('#couponAmount').text($t.attr('couponVal'));
	$('#totalPayArea').trigger('changeOrderPay');
});
$('#useCouponCodeBtn').click(function(e){
	var $t = $(this);
	$('#couponCodeTip').text('');
	if(!$t.val()) {
		$('#couponCodeInput').show();
	}
	//购物车计算
	$('#couponCode').val($t.val());
	$('#couponAmount').text($t.attr('couponVal'));
	$('#totalPayArea').trigger('changeOrderPay');
});
$('#couponCodeBtn').click(function(e){
	var code = $.trim($('#couponCodeVal').val());
	//validate
	if(!/^\d{12}$/.test(code)){
		$('#couponCodeTip').text('無效');
		return false;
	} else {
		$('#couponCodeTip').text('');
	}
	//check
	$.post('/web/shopCart!checkCouponCode.action', {myCoupon: code, shopFlag: $('#shopFlag').val()}, function(data){
		if(data.success) {
			var coupon = data.reObj;
			var couponAmount = NumCal.precise(coupon.amount);
			$('#useCouponCodeBtn').val(coupon.code);
			$('#useCouponCodeBtn').attr('couponVal', couponAmount);
			$('#couponCodeName').text(coupon.name);
			//购物车计算
			$('#couponCode').val(coupon.code);
			$('#couponAmount').text(couponAmount);
			$('#totalPayArea').trigger('changeOrderPay');
			$('#couponCodeInput').hide();
			$('#couponCodeUse').show();
		} else {
			$('#couponCodeTip').text('無效');
		}
	});
});
$('#couponCodeCancelBtn').click(function(e){
	$('#couponCodeInput').show();
	$('#couponCodeUse').hide();
	
	var $u = $('#useCouponCodeBtn');
	$u.val('');
	$u.attr('couponVal', '0.0');
	if($u.is(':checked')){
		//购物车计算
		$('#couponCode').val('');
		$('#couponAmount').text('0.0');
		$('#totalPayArea').trigger('changeOrderPay');
	}
});
//购物车计算
$('#totalPayArea').bind('changeOrderPay', function(e){
	var prodAmount = parseFloat($('#prodAmount').text());
	var carriage = parseFloat($('#allCarriage').text());
	var couponAmount = parseFloat($('#couponAmount').text());
	var amount = NumCal.add(prodAmount, carriage);
	amount = NumCal.sub(amount, couponAmount);
	amount = NumCal.precise(amount);
	$('#amount').text(amount);
	//商品信息
	var productList=$(".pay").find(".product");
	var productStr="";
	for(var i=0;i<productList.length;i++){
		if(i==productList.length-1){
			productStr+=productList[i].value;
		}else{
			productStr+=productList[i].value+",";
		}
	}
	//查看订单活动
	var shopFlag = $('#shopFlag').val();
	$.post('web/shopCart!getOrderActivityTip.action', {orderPay: parseFloat(amount), shopFlag: shopFlag,productCodeList:productStr}, function(data){
		var couponTxt = $.trim(data);
		if(couponTxt) {
			$('#order_activity_tip').empty().append(couponTxt).show();
		} else {
			$('#order_activity_tip').hide();
		}
	});
});
//提交订单
$('#subOrderBtn').click(function(e){
	//check
	var isSubmit = true;
	var msg = [];
	if(!$('#defaultAddr').is(':visible')){
		msg.push('您需要先保存收貨人信息，再保存訂單');
		isSubmit = false;
	}
	if(!$('#defaultPayMethod').is(':visible')){
		msg.push('您需要先保存支付方式，再保存訂單');
		isSubmit = false;
	}
	if($('#couponCodeInput').is(':visible')){
		msg.push('您需要“確定”使用有效優惠券碼，或“取消使用”');
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
var resDomainArea = '${resDomain}/www/res/area_en/';
initArea();
</script>



<!-- 引入google统计代码 -->
<!-- Google Code for &#36141;&#29289;&#36710; Conversion Page -->
<script type="text/javascript">
/* <![CDATA[ */
var google_conversion_id = 978626603;
var google_conversion_language = "en";
var google_conversion_format = "3";
var google_conversion_color = "ffffff";
var google_conversion_label = "SiXVCNu-3VcQq9DS0gM";
var google_remarketing_only = false;
/* ]]> */
</script>
<script type="text/javascript" src="//www.googleadservices.com/pagead/conversion.js">
</script>
<noscript>
<div style="display:inline;">
<img height="1" width="1" style="border-style:none;" alt="" src="//www.googleadservices.com/pagead/conversion/978626603/?label=SiXVCNu-3VcQq9DS0gM&amp;guid=ON&amp;script=0"/>
</div>
</noscript>





	</body>
</html>

