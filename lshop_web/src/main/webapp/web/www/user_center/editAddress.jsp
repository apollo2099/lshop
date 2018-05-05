<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>用戶中心_TVpad商城</title>
		<link href="${resDomain}/www/res/css/user_center.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<%@include file="/web/www/common/top.jsp"%>
		<script type="text/javascript" src="${resDomain}/www/res/js/searchSelect.js"></script>
		<script type="text/javascript" src="${resDomain}/www/res/js/address.js"></script>
	</head>
	
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/www/common/header.jsp"%>
		
		<div class="content_main">
			<!-- left_frame -->
			<%@include file="/web/www/user_center/leftFrame.jsp" %>
			 
			 <div class="right_frame">
			 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/www/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">首頁</a>--><a href="/web/userCenterManage!getAccount.action">用戶中心</a>--><a>編輯收貨地址</a></font> </span></h1> 
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
						<li class="wd1"><font class="redfont">*</font>收貨人姓名：</li>
						<li class="wd2"><input name="lvAccountAddress.relName"  type="text" class="input2" value="${lvAccountAddress.relName }" /><font class="redfont">(姓名必須為英文)</font>
						</li>
				    </ul>
				    
				  <ul>
					<li class="wd1">手機號碼：</li>
					<li class="wd2">
					  <input name="lvAccountAddress.mobile" type="text" class="input2" onkeypress="onlyNumber(event)" value="${lvAccountAddress.mobile }" id="mobile"/><span id="mobileInfo"></span>
					</li>
				</ul>
				<ul>
					<li class="wd1">固定電話：</li>
					<li class="wd2"><input name="lvAccountAddress.tel"  type="text" class="input2" value="${lvAccountAddress.tel }" id="tel"/><span id="telInfo"></span>
					</li>
				</ul>
				<ul>
					<li class="wd1"><font class="redfont">*</font>收貨地址：</li>
					<li class="wd2">
						<input  name="lvAccountAddress.adress" id="adress" type="text" style="float:left;" class="input2" value="${lvAccountAddress.adress }" onfocus="if(this.value=='街道詳細地址')this.value=''" onblur="if(this.value=='')this.value='街道詳細地址'"/> 
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
				<ul id="infoUl" style="display:none">
					<li class="wd1">&nbsp;</li>
					<li class="wd2"><span id="addressInfo"></span></li>
				</ul>
				<ul>
					<li class="wd1"><font class="redfont">*</font>郵政區號：</li>
					<li class="wd2">
					  <input name="lvAccountAddress.postCode"  type="text" class="input2" value="${lvAccountAddress.postCode }" />
					</li>
				</ul>
				<ul class="btn">
					<li class="wd1">&nbsp;</li>
					<li class="wd2"><input type="submit"  value="保存修改" class="user_center_bt" /></li>
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
		<%@include file="/web/www/common/footer.jsp" %>
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