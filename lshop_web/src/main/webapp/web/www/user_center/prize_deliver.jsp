<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld" %> 
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<input type="hidden" value="${prizeCode}" id="prizeCode"/>
<div class="my_prize_details_box1">
<div class="fill_orders_add_bj" id="addressDiv">
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

<form id="addressForm" action="<c:if test="${empty addressList}">/web/address!addAddress.action</c:if>" method="post">
<div id="addAddrArea" class="receive_info_bj" <c:if test="${not empty dAddress}">style="display:none;"</c:if> >
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
				<span id="addressInfo">為確保及時收到我們的產品，請填寫真實地址（英文）</span>
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
</div><!-- end of addAddrArea -->
</form>
<ul>
	<li class="btn"><input id="addrBtn" class="btn07" type="button" value="確認收貨地址" />確認之後無法更改，請謹慎填寫</li>
</ul>
</div>
<script>
$('#addrBtn').click(function(e){
	var action = $('#addressForm').attr('action');
	if(!action){
		var addr = $('.addrItem:checked').val();
		if(!addr){
			alert('請先選擇收貨人信息!');
			return false;
		}
		$(this).trigger('subDeliver', addr);
	} else {
		//异步提交表单
		$('#addressForm').submit();
	}
}).bind('subDeliver', function(e, addr){
	//提交选中的收货地址
	$.post('/web/prize!addDeliver.action', {addressCode: addr, prizeCode: $('#prizeCode').val()}, function(data){
		//可提示成功
		window.location.reload();
	});
});
$('#addressDiv').delegate('.addrEditBtn', 'click', function(e){
	var eaddr = $(this).attr('addrCode');
	$('#editAddrCode').val(eaddr);
	$('#addressForm').attr('action', '/web/address!editAddress.action');
	$('#addressDiv .choose').removeClass('choose');
	$('input[name="addressCode"]').attr('checked', false);
	$(this).parents('ul').addClass('choose').find('.addrItem').attr('checked',true);
	$.post('/web/address!getAddressJson.action', {addressCode: eaddr}, function(data){
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
		//显示表单
		$('#addAddrArea').show();
		$('#mask_content').trigger('heightChange');
	});
}).delegate('.addrItem', 'click', function(e){
	var $f = $('#addressForm');
	$f[0].reset();
	$f.attr('action', '');
	$('.choose').removeClass('choose');
	$(this).parents('ul').addClass('choose');
	$('#addAddrArea').hide();
	$('#mask_content').trigger('heightChange');
});
//使用新地址
$('#addAddrBtn').click(function(e){
	var $f = $('#addressForm');
	$f[0].reset();
	$('#editAddrCode').val('');
	$f.attr('action', '/web/address!addAddress.action');
	$('#addressDiv .choose').removeClass('choose');
	$(this).parents('ul').addClass('choose');
	$('#addAddrArea').show();
	$('#mask_content').trigger('heightChange');
});
//绑定收货地址校验器
$("#addressForm").validate($.extend({}, AddressFormValidate, {
	submitHandler: function(form){
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
		var $f = $('#addressForm');
		//新增或者编辑收货地址
		$.post($f.attr('action'), $f.serialize(), function(data){
			$('#addrBtn').trigger('subDeliver', data.addressCode);
		});
	}
}));
</script>