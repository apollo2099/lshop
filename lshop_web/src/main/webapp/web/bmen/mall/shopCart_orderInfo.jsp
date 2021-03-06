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
		<title>Fill In Order Info_banana Mall</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<!-- 加载公共JS -->
		<%@include file="/web/bmen/common/top.jsp"%>
		<link href="${resDomain}/bmen/res/css/buy.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${resDomain}/bmen/res/js/shopCartOrderInfo.js"></script>
		<script type="text/javascript" src="${resDomain}/bmen/res/js/address.js"></script>
		<script type="text/javascript" src="${resDomain}/bmen/res/js/num_cal.js"></script>
		<style type="text/css">
		#addressForm input[type="text"]{color:#666;}
		</style>
	</head>
	
	<body>
		<input type="hidden" value="${lvStore.storeFlag}" id="shopFlag"/>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/bmen/common/shopcart_header.jsp"%>
		
		<div class="buy">
			<div class="buy_lc02"></div>

			<div class="fill_orders">
				<div class="fill_orders01">
					<!-- 收货人信息 -->
					<div id="defaultAddr" class="info_people" <c:if test="${empty dAddress}">style="display:none;"</c:if> >
						<h3>Consignee Details <a id="changDefaultAddr" href="javascript:void(0);">[Modify]</a></h3>
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
						<h3>Frequently used address <span>（Select your shipping address. If you haven't set frequently used address, please add one first. ）</span></h3>
						<c:if test="${not empty addressList}">
							<c:foreach items="${addressList}" var="address" varStatus="sta">
								<c:set var="deliverId" value="${address.contryId}"></c:set>
								<c:if test="${not empty address.provinceId}">
								<c:set var="deliverId" value="${address.provinceId}"></c:set>
								</c:if>
								<c:if test="${not empty address.cityId}">
								<c:set var="deliverId" value="${address.cityId}"></c:set>
								</c:if>
								<c:choose>
								<c:when test="${not empty dAddress && address.code==dAddress.code}">
								<ul class="choose" id="address${sta.count }" deliveryId="${deliverId}" >
									<li class="add1">
										<input name="addressCode" class="addrItem" type="radio" value="${address.code }" checked="checked" />${address.relName}
									</li>
								</c:when>
								<c:otherwise>
								<ul id="address${sta.count }" deliveryId="${deliverId}">
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
										<a href="javascript:void(0)" addrCode="${address.code }" class="addrEditBtn">edit</a>
									</li>
									<li class="de">
										<a href="javascript:void(0)"
											onclick="delAddress('${address.code }','${sta.count }');">delete</a>
									</li>
								</ul>
							</c:foreach>
						</c:if>
						<ul id="UladdAddrArea" <c:if test="${not empty addressList && fn:length(addressList)>=5}">style="display:none;"</c:if> ><li class="add1" >
							<input id="addAddrBtn" name="addressCode" type="radio" <c:if test="${empty addressList}">checked="checked"</c:if> value="" /> Add a new address</li>
						</ul>
					</div>

					<div id="addAddrArea" class="receive_info_bj" <c:if test="${not empty dAddress}">style="display:none;"</c:if> >
					<form id="addressForm" action="<c:if test="${empty addressList}">/web/shopCart!addAddress.action</c:if>" method="post">
						<input type="hidden" name="shopFlag" value="${lvStore.storeFlag}"/>
						<input type="hidden" name="lvAccountAddress.code" value="" id="editAddrCode"/>
							<ul>
								<li class="wd1">
									<font class="redfont">*</font>Full Name：
								</li>
								<li class="wd2">
									<input name="lvAccountAddress.relName" class="input4"
										type="text" /><span class="kbqc"><span>(English only)</span></span>
								</li>
							</ul>

							<ul>
								<li class="wd1">
									Mobile No.：
								</li>
								<li class="wd2">
									<input name="lvAccountAddress.mobile"  onkeypress="onlyNumber(event)" class="input4"
										type="text" id="mobile" />
									<span id="mobileInfo"><span class="kbqc">Either the Mobile No. or Phone No.is required.</span></span>
								</li>
							</ul>
							<ul>
								<li class="wd1">
									Phone No.：
								</li>
								<li class="wd2">
									<input name="lvAccountAddress.tel" class="input4" type="text"
										id="tel" />
									<span id="telInfo"><span class="kbqc">Either the Mobile No. or Phone No.is required.</span></span>
								</li>
							</ul>
							<ul>
								<li class="wd1">
									<font class="redfont">*</font>Shipping Address：
								</li>
								<li class="wd2">
									<input name="lvAccountAddress.adress" id="adress" type="text"
										class="input4" value="Street address"
										onfocus="if(this.value=='Street address')this.value=''"
										onblur="if(this.value=='')this.value='Street address'" />
									-
									<input name="lvAccountAddress.cityName" id="cityName"
										type="text" class="input04" value="County/City"
										onfocus="if(this.value=='County/City')this.value=''"
										onblur="if(this.value=='')this.value='County/City'" />
									-
									<input type="hidden" id="test" />
									<input type="text" class="input04" name="lvAccountAddress.provinceName" id="provinceName"  value="State/Province"  onfocus="if(this.value=='State/Province')this.value=''" onblur="if(this.value=='')this.value='State/Province'"/> 
									-
									<select name="lvAccountAddress.contryId" id="contryId"
										onchange="contryChange()">
										<option value="">
											-Choose your country-
										</option>
										<c:foreach items="${contryList}" var="c">
											<option value="${c.id}">${c.nameen}</option>
										</c:foreach>
									</select>
									<input type="hidden" name="lvAccountAddress.contryName"
										id="contrynameId" value="" />
									<span id="addressInfo"><span class="kbqc">To ensure you receive your package in time, please enter a correct address (in English).</span></span>
								</li>
							</ul>
							<ul>
								<li class="wd1">
									<font class="redfont">*</font>Zip：
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
								<li class="wd2"><input id="addrBtn" class="btn07" type="button" value="Save" /></li>
							</ul>
						</form>
					</div>
					
		<!-- 支付方式 -->
		<div id="defaultPayMethod" class="pay" <c:if test="${empty dPayment}">style="display:none;"</c:if> >
		<h3>Payment Options <a id="changePayMethod" href="javascript:void(0)">[Modify]</a></h3>
		<ul id="defaultPayMethodTxt">
		<c:if test="${not empty dPayment}">
		<li>${dPayment.payName}</li>
		</c:if>
		</ul>							  
		</div>
		<div id="payMethodList" class="pay_bj" <c:if test="${not empty dPayment}">style="display:none"</c:if> >
			<h3>Payment Options</h3>
			<ul>
				<cus:paymethod storeflag="${lvStore.storeFlag}"></cus:paymethod>
				<li><input id="setPaymentBtn" class="btn07" type="button" value="Save" /></li>
			</ul>
		</div>	
					
		<!-- 商品清單 -->
		<div class="pay">
		<h3>Item list</h3>
		<ul>Seller ：${lvStore.name}</ul>
		<ul class="right">
			<a href="web/mall!getShopCartList.action">Modify cart</a>
		</ul>
		<table width="980" height="353" border="0" cellpadding="0" cellspacing="1" bgcolor="#eeeeee" style=" margin-top:10px;">
			<tr>
            	<td width="591" height="37" align="center" bgcolor="#eeeeee"><span class="STYLE1">Item Name</span></td>
	            <td width="152" align="center" bgcolor="#eeeeee"><span class="STYLE1">Price</span></td>
	            <td width="116" align="center" bgcolor="#eeeeee"><span class="STYLE1">Quantity</span></td>
	            <td width="121" align="center" bgcolor="#eeeeee"><span class="STYLE1">Subtotal</span></td>
            </tr>
            <c:foreach items="${objList}" var="obj" varStatus="status">
			<tr>
               <td height="82" align="center" valign="middle" bgcolor="#ffffff"><table width="100%" height="82" border="0" cellpadding="0" cellspacing="0">
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
   					<p style="display:inline; padding-right:20px;font-size:14px; font-weight:bold"><span class="price1  redfont">${shopcartNum}</span> goods</p>
   					<p style="display:inline; font-size:14px; font-weight:bold">Total： <span class="price1  redfont">${lvStore.currency} ${allAmount }</span></span></p>
   				</td>
   			</tr>
		</table>
		</div>
					
		<!-- 优惠券 -->
		<div class="coupons" id="editCoupon">
			<p><a href="javascript:void(0);" id="editCouponBtn">(+)Use coupons</a> </p>
		</div>
		<div class="coupons" id="listCoupon" style="display:none;">
			<p><a href="javascript:void(0);" id="listCouponBtn">(-) Use coupons </a><a href="javascript:void(0);" id="couponCancelBtn"><font class="blue2">Cancel</font></a></p>
			<ul>
				<c:if test="${not empty couponList}">
				<li class="gray">Available coupons for your current order</li>
				<c:foreach items="${couponList}" var="coupon" varStatus="vs">
				<li><input class="myCoupon" name="coupon" type="radio" value="${coupon.code}"
					couponVal="${coupon.amount}"  />${coupon.name}<p class="amount">-${coupon.currency} ${coupon.amount}</p>
				</li>
				</c:foreach>
				</c:if>
				<c:if test="${empty couponList}">
				<li class="gray">No available coupons for the order</li>
				</c:if>
				</ul>
				<ul>
				<li>Or use coupon code</li>
				<li><input id="useCouponCodeBtn" name="coupon" type="radio" value="" couponval="0.0" <c:if test="${empty couponList}">checked="checked"</c:if> />Use coupon code
					<span id="couponCodeInput" <c:if test="${not empty couponList}">style="display:none;"</c:if>>
					<input id="couponCodeVal" name="textfield" type="text" class="input3"
					placeholder="Enter your coupon code" maxlength="12"/> <input type="button" name="button" id="couponCodeBtn" class="btn08" value="Confirm" />	<font id="couponCodeTip" class="redfont"></font></span>
					<p id="couponCodeUse" class="use" style="display:none;"><span id="couponCodeName">TVpad 迎春红包抢不停活动满300减100优惠券（限机顶盒商品使用）</span><a href="javascript:void(0);" id="couponCodeCancelBtn">Cancel</a></p>
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
		
		<!-- 备注 -->
		<div class="pay">
			Leave a message<input type="text" id="orderRemark" name="orderRemark" class="input6" value="${orderRemark }" maxlength="160" />
		</div>
		
		<div class="total_pay" id="totalPayArea">
        	<ul>
            	<li class="wd1">Total：<font class="redfont">${lvStore.currency}<span id="prodAmount">${allAmount }</span></font>
            	 + Freight：<font class="redfont">${lvStore.currency} <span id="allCarriage">${carriage}</span></font>
            	<span id="couponAmountArea"> - Coupon：<font class="redfont">${lvStore.currency} <span id="couponAmount">0.0</span></font></span></li>             
                <li class="wd2">Amount Payable：<font class="redfont" style="font-size:20px;">${lvStore.currency} <span id="amount">${totalAmount }</span></font> </li>
                <li class="wd3">
					<input id="subOrderBtn" class="btn04" type="submit" value="Submit Order"  /></li>
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
		<%@include file="/web/bmen/common/footer.jsp"%>
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
			alert('请先选择收货人信息!');
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
		$('#contryId').val(data.contryId);
		$('#contrynameId').val(data.contryName);
		contryChange();
		//省
		var $pv = $('#provinceName');
		if($pv.is('select')){
			$pv.val(data.provinceId);
			$('#provinceNameId').val(data.provinceName);
			provinceChange();
		} else {
			$pv.val(data.provinceName);
		}
		//市
		var $ct = $('#cityName');
		if($ct.is('select')){
			$ct.val(data.cityId);
			$('#cityNameId').val(data.cityName);
			cityChange();
		} else {
			$ct.val(data.cityName);
		}
		//街道
		$('#adress').val(data.adress);
	});
}).delegate('.addrItem', 'click', function(e){
	var $f = $('#addressForm');
	$f[0].reset();
	$f.attr('action', '');
	$('.choose').removeClass('choose');
	$(this).parents('ul').addClass('choose');
});
$('#addAddrBtn').click(function(e){
	var $f = $('#addressForm');
	$f[0].reset();
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
		$('#couponCodeTip').text('Invalid');
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
			$('#couponCodeTip').text('Invalid');
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
	//查看订单活动
	var shopFlag = $('#shopFlag').val();
	$.post('web/shopCart!getOrderActivityTip.action', {orderPay: parseFloat(amount), shopFlag: shopFlag}, function(data){
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
		msg.push('save Consignee Details first');
		isSubmit = false;
	}
	if(!$('#defaultPayMethod').is(':visible')){
		msg.push('save Payment Options first');
		isSubmit = false;
	}
	if($('#couponCodeInput').is(':visible')){
		msg.push('confirm coupon code, or cancel use coupons');
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
</script>
	</body>
</html>

