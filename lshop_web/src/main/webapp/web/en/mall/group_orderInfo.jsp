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
		<title>Fill In Order Info_TVpad Mall</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<!-- 加载公共JS -->
		<%@include file="/web/en/common/top.jsp"%>
		<link href="${resDomain}/en/res/css/buy.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${resDomain}/en/res/js/shopCartOrderInfo.js"></script>
		<script type="text/javascript" src="${resDomain}/www/res/js/searchSelect.js"></script>
		<script type="text/javascript" src="${resDomain}/en/res/js/address.js"></script>
		<script type="text/javascript" src="${resDomain}/en/res/js/num_cal.js"></script>
		
	</head>
	
	<body>
		<input type="hidden" value="${lvStore.storeFlag}" id="shopFlag"/>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/en/common/shopcart_header.jsp"%>
		
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
						<h3>Frequently used address</h3>
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
										<a href="javascript:void(0);" addrCode="${address.code }" class="addrEditBtn">edit</a>
									</li>
									<li class="de">
										<a href="javascript:void(0);"
											onclick="delAddress('${address.code }','${sta.count }');">delete</a>
									</li>
								</ul>
							</c:foreach>
						</c:if>
						<ul id="UladdAddrArea" <c:if test="${not empty addressList && fn:length(addressList)>=5}">style="display:none;"</c:if> ><li class="add1" >
							<input id="addAddrBtn" name="addressCode" type="radio" <c:if test="${empty addressList}">checked="checked"</c:if> value="" />Add a new address</li>
						</ul>
					</div>

					<div id="addAddrArea" class="receive_info_bj" <c:if test="${not empty dAddress}">style="display:none;"</c:if> >
					<form id="addressForm" action="<c:if test="${empty addressList}">/web/group!addAddress.action</c:if>" method="post">
						<input type="hidden" name="shopFlag" value="${lvStore.storeFlag}"/>
						<input type="hidden" name="lvAccountAddress.code" value="" id="editAddrCode"/>
						<input type="hidden" name="lvGroupBuy.code" value="${groupBuy.code}"/>
						<input type="hidden" name="shopNum" value="${shopNum}"/>
							<ul>
								<li class="wd1">
									<font class="redfont">*</font>Full Name：
								</li>
								<li class="wd2">
									<input name="lvAccountAddress.relName" class="input4"
										type="text" /><span class="kbqc"><font>(English only)</font></span>
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
									<input name="lvAccountAddress.adress" id="adress" type="text" style="float:left;"
										class="input4" value="Street address"
										onfocus="if(this.value=='Street address')this.value=''"
										onblur="if(this.value=='')this.value='Street address'" />
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
		<table width="980" height="353" border="0" cellpadding="0" cellspacing="1" bgcolor="#eeeeee" style=" margin-top:10px;">
			<tr>
            	<td width="591" height="37" align="center" bgcolor="#eeeeee"><span class="STYLE1">Item Name</span></td>
	            <td width="152" align="center" bgcolor="#eeeeee"><span class="STYLE1">Price</span></td>
	            <td width="116" align="center" bgcolor="#eeeeee"><span class="STYLE1">Quantity</span></td>
	            <td width="93" align="center" bgcolor="#eeeeee"><span class="STYLE1">Subtotal</span></td>
            </tr>
			<tr>
               <td height="82" align="center" valign="middle" bgcolor="#ffffff"><table width="100%" height="82" border="0" cellpadding="0" cellspacing="0">
                 <tr>
                   <td width="180" align="center" valign="middle"><img src="${product.pimage }" width="70px" height="60px" /></td>
                   <td width="391" align="left">${product.productName }</td>
                 </tr>
               </table></td>
               <td width="152" height="82" align="center" bgcolor="#ffffff"><span class="price1 redfont"><strong>${lvStore.currency} ${groupBuy.presentPrice}</strong></span></td>
               <td width="116" height="82" align="center" bgcolor="#ffffff">${shopNum }</td>
               <td width="93" height="82" align="center" bgcolor="#ffffff"><span class="price1 redfont"><strong>${lvStore.currency} <u:formatnumber value="${groupBuy.presentPrice *shopNum }"  type="number" groupingUsed="false" maxFractionDigits="2"/></span></td>
             </tr>
   			<tr>
   				<td height="32" colspan="4" align="right" bgcolor="#F5F5F5" style="; padding:0 30px 0 0;">
   					<p style="display:inline; padding-right:20px;font-size:14px; font-weight:bold"><span class="price1  redfont">1</span> goods</p>
   					<p style="display:inline; font-size:14px; font-weight:bold">Total： <span class="price1  redfont">${lvStore.currency} <u:formatnumber value="${amount }"  type="number" groupingUsed="false" maxFractionDigits="2"/></span></p>
   				</td>
   			</tr>
		</table>
		</div>
		
		<form id="myForm" action="/web/group!saveOrderForGroup.action" method="post" >
		<input type="hidden" name="lvGroupBuy.code" value="${groupBuy.code}"/>
		<input type="hidden" name="shopNum" value="${shopNum}"/>
		<input type="hidden" id="myAddress" name="addressCode" value="<c:if test="${not empty dAddress}">${dAddress.code}</c:if>" />
		<input type="hidden" id="payValue" name="lvOrder.paymethod" value="<c:if test="${not empty dPayment}">${dPayment.payValue}</c:if>" />
		<input type="hidden" id="bestDeliveryValue" name="bestDeliveryValue" value="1" />
		
		<!-- 备注 -->
		<div class="pay">
			Leave a message<input type="text" id="orderRemark" name="lvOrder.orderRemark" class="input6" value="${orderRemark }" maxlength="160" />
		</div>
		
		<div class="total_pay" id="totalPayArea">
        	<ul>
            	<li class="wd1">Total：<font class="redfont">${lvStore.currency}<span id="prodAmount"><u:formatnumber value="${amount }"  type="number" groupingUsed="false" maxFractionDigits="2"/></span></font>
            	 + Freight：<font class="redfont">${lvStore.currency} <span id="allCarriage">${carriage}</span></font></li>             
                <li class="wd2">Amount Payable：<font class="redfont" style="font-size:20px;">${lvStore.currency} <span id="amount"><u:formatnumber value="${amount+carriage}"  type="number" groupingUsed="false" maxFractionDigits="2"/></span></font> </li>
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
		<%@include file="/web/en/common/footer.jsp"%>
<script type="text/javascript">
$(function(){
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
	$('#addressForm').attr('action', '/web/group!editAddress.action');
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
	$f.attr('action', '/web/group!addAddress.action');
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
//购物车计算
$('#totalPayArea').bind('changeOrderPay', function(e){
	var prodAmount = parseFloat($('#prodAmount').text());
	var carriage = parseFloat($('#allCarriage').text());
	var amount = NumCal.add(prodAmount, carriage);
	amount = NumCal.precise(amount);
	$('#amount').text(amount);
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
	if(!isSubmit){
		alert(msg.join('\n'));
		return false;
	}
	//submit
	return true;
});

//
var resDomainArea = '${resDomain}/www/res/area_en/';
initArea();
</script>
</body>
</html>


