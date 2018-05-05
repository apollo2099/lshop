<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld" %> 
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<input type="hidden" value="${prizeCode}" id="prizeCode"/>
<div class="my_prize_details_box1">
<div class="fill_orders_add_bj" id="addressDiv" >
	<h3>收货人信息</h3>
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
					<a href="#" addrCode="${address.code }" class="addrEditBtn">编辑</a>
				</li>
				<li class="de">
					<a href="#"
						onclick="delAddress('${address.code }','${sta.count }');">删除</a>
				</li>
			</ul>
		</c:foreach>
	</c:if>
	<ul id="UladdAddrArea" <c:if test="${not empty addressList && fn:length(addressList)>=5}">style="display:none;"</c:if> ><li class="add1" >
		<input id="addAddrBtn" name="addressCode" type="radio" <c:if test="${empty addressList}">checked="checked"</c:if> value="" /> 使用新地址</li>
	</ul>
</div>
<div id="addAddrArea" class="receive_info_bj" <c:if test="${not empty dAddress}">style="display:none;"</c:if> >
	<form id="addressForm" action="<c:if test="${empty addressList}">/web/address!addAddress.action</c:if>" method="post">
		<input type="hidden" name="shopFlag" value="${lvStore.storeFlag}"/>
		<input type="hidden" name="lvAccountAddress.code" value="" id="editAddrCode"/>
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
				<input type="text" class="input04" name="lvAccountAddress.provinceName" id="provinceName"  value="洲/省"  onfocus="if(this.value=='洲/省')this.value=''" onblur="if(this.value=='')this.value='洲/省'"/> 
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
	</form>
</div><!-- end of addAddrArea -->
<ul>
	<li class="btn">
	<input id="addrBtn" class="btn07" type="button" value="确认收货地址" />
		确认之后无法更改，请谨慎填写
	</li>
</ul>
</div>
<script>
$('#addrBtn').click(function(e){
	var action = $('#addressForm').attr('action');
	if(!action){
		var addr = $('.addrItem:checked').val();
		if(!addr){
			alert('请先选择收货人地址!');
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
		  $("#mobileInfo").html("<font color='red'>电话和手机必须填写其中一个</font>");
		  $("#telInfo").html("<font color='red'>电话和手机必须填写其中一个</font>");
		  mobile.focus();
		  return ;
	    }else if($.trim(adress.val())==''||$.trim(adress.val())=='街道详细地址'){
		  $("#addressInfo").html("<font color='red'>街道详细地址不能为空！</font>");
		  $("#infoUl").show();
		  adress.focus();
		  return ;
	    }else if($.trim(cityName.val())==''||$.trim(cityName.val())=='县/市'){
	      $("#addressInfo").html("<font color='red'>县/市不能为空！</font>");
	      $("#infoUl").show();
		  cityName.focus();
		  return ;
	    }else if($.trim(provinceName.val())==''||$.trim(provinceName.val())=='洲/省'){
	      $("#addressInfo").html("<font color='red'>洲/省不能为空！</font>");
	      $("#infoUl").show();
		  provinceName.focus();
		  return ;
	    }else if($.trim($("#contryId").find("option:selected").val())==''){
	      $("#addressInfo").html("<font color='red'>国家不能为空！</font>");
	      $("#infoUl").show();
		  contryId.focus();
		  return ;
	    }else if(adress.val().length>128){
	      $("#addressInfo").html("<font color='red'>街道详细地址不能超过128位字符！</font>");
	      $("#infoUl").show();
		  adress.focus();
		  return ;
	    }else if(cityName.val().length>32){
	      $("#addressInfo").html("<font color='red'>县/市不能超过32位字符！</font>");
	      $("#infoUl").show();
		  cityName.focus();
		  return ;
	    }else if(provinceName.val().length>32){
	      $("#addressInfo").html("<font color='red'>洲/省不能超过32位字符！</font>");
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