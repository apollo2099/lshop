<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>User Center _TVpad Mall</title>
		<link href="${resDomain}/en/res/css/user_center.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<%@include file="/web/en/common/top.jsp" %>
		<script type="text/javascript" src="${resDomain}/www/res/js/searchSelect.js"></script>
		<script type="text/javascript" src="${resDomain}/en/res/js/address.js"></script>
		<style type="text/css">
		#addressForm input[type="text"]{color:#666;}
		</style>
	</head>
	
	<body>		
		<!-- 获取商城头部文件 -->
		<%@include file="/web/en/common/header.jsp" %>
		
		<div class="content_main">
			<!-- left_frame -->
			<%@include file="/web/en/user_center/leftFrame.jsp" %>
			 
			 <div class="right_frame">
			 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/en/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">Home</a>--><a href="/web/userCenterManage!getAccount.action">User Center </a>--></font> Edit Billing Address</span></h1>  
				<div class="usercenter_box">
				<font color="red">${msg}</font>
			    <% session.removeAttribute("msg"); %>
				<form action="/web/userCenterManage!editAddress.action" method="post" id="addressForm" onsubmit="return beforeAddressSubmit();">
					<input type="hidden" name="lvAccountAddress.code" value="${lvAccountAddress.code }"/>
					<input type="hidden" name="lvAccountAddress.id" value="${lvAccountAddress.id }"/>
					<input type="hidden" name="lvAccountAddress.relCode" value="${lvAccountAddress.relCode }"/>
					<input type="hidden" name="lvAccountAddress.storeId" value="${lvAccountAddress.storeId }"/>
					<input type="hidden" name="lvAccountAddress.isDefault" value="${lvAccountAddress.isDefault }"/>
					<input type="hidden" name="lvAccountAddress.createTime" value="${lvAccountAddress.createTime }"/>
					<ul>
						<li class="wd1"><font class="redfont"> *</font> Full Name：</li>
						<li class="wd2"><input name="lvAccountAddress.relName"  type="text" class="input2" value="${lvAccountAddress.relName }" /><span class="kbqc"> English only</span>
						</li>
				    </ul>
				    
				  <ul>
					<li class="wd1"><font class="redfont"> *</font> Mobile No.：</li>
					<li class="wd2">
					  <input name="lvAccountAddress.mobile" type="text" class="input2" onkeypress="onlyNumber(event)" value="${lvAccountAddress.mobile }" id="mobile"/><span id="mobileInfo"></span>
					</li>
				</ul>
				<ul>
					<li class="wd1"><font class="redfont"> *</font> Phone No.：</li>
					<li class="wd2"><input name="lvAccountAddress.tel"  type="text" class="input2" value="${lvAccountAddress.tel }" id="tel"/><span id="telInfo"></span>
					</li>
				</ul>
				<ul class="shdz">
					<li class="wd1"><font class="redfont"> *</font> Billing Address：</li>
					<li class="wd2">
						<input  name="lvAccountAddress.adress" id="adress" type="text" style="float:left;" class="input2" value="${lvAccountAddress.adress }" onfocus="if(this.value=='Street address')this.value=''" onblur="if(this.value=='')this.value='Street address'"/> 
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
					</li>
				</ul>
				<ul>
					<li class="wd1">&nbsp;</li>
					<li class="wd2"><span class="kbqc">To ensure you receive your package in time, please enter a correct address (in English).</span></li>
				</ul>				
				<ul id="infoUl" style="display:none">
					<li class="wd1">&nbsp;</li>
					<li class="wd2"><span id="addressInfo"></span></li>
				</ul>
				<ul>
					<li class="wd1"><font class="redfont">*</font> Zip：</li>
					<li class="wd2">
					  <input name="lvAccountAddress.postCode"  type="text" class="input2" value="${lvAccountAddress.postCode }" />
					</li>
				</ul>
				<ul class="btn">
					<li class="wd1">&nbsp;</li>
					<li class="wd2"><input type="submit"   value="Save Changes" class="user_center_bt" /></li>
				</ul>
				</form>
				
		  	    </div>
			 <!--End right_Frame-->
		</div>
		<!--End content-->	
		</div>		
		
		<!-- 底部广告位-->
		<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
		
		<!-- footer-->
		<%@include file="/web/en/common/footer.jsp" %>
<script>
var resDomainArea = '${resDomain}/www/res/area_en/';

$(document).ready(function(){
	initArea();
	var contryId = ${lvAccountAddress.contryId};
	var contryName = '${lvAccountAddress.contryName}';
	$.getScript(resDomainArea+'country.js',function(){
		if (contryId > 0) {
			$('#contryId').val(contryId);
			countrySelect.setData(countryData);
			countrySelect.setSelectedId(contryId);
		} else {
			$('#contryId').val('');
		}
		$('#contryName').val(contryName);
		var provinceId = '${lvAccountAddress.provinceId}';
		var pattern = /\d/ig;
		if (!pattern.test(provinceId)) {
			provinceId = -1;
		}		
		var provinceName = '${lvAccountAddress.provinceName}';
		var cityId = '${lvAccountAddress.cityId}';
		if (!pattern.test(cityId)) {
			cityId = -1;
		}
		var cityName = '${lvAccountAddress.cityName}';
		if (provinceId > 0) {
			$('#provinceId').val(provinceId);
			$.getScript(resDomainArea+contryId+'.js',function(){
				provinceSelect.setData(provinceData);
				provinceSelect.setSelectedId(provinceId);
				if (cityId > 0) {
					$('#cityId').val(cityId);
					$.getScript(resDomainArea+contryId+'/'+provinceId+'.js',function(){
						citySelect.setData(cityData);
						citySelect.setSelectedId(cityId);
					});					
				} else {
					$('#cityId').val('');
					citySelect.setData([]);					
					citySelect.setText(cityName);
				}
				$('#cityName').val(cityName);				
			});			
		} else {
			$('#provinceId').val('');
			provinceSelect.setData([]);			
			provinceSelect.setText(provinceName);
			$('#cityId').val('');
			$('#cityName').val(cityName);
			citySelect.setData([]);
			citySelect.setText(cityName);						
		}
		$('#provinceName').val(provinceName);		
	});
});

</script>	
	</body>
</html> 