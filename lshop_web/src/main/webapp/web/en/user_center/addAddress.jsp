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
			 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/en/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">Home</a>--><a href="/web/userCenterManage!getAccount.action">User Center </a>--></font> Add New Address</span></h1> 
				<div class="usercenter_box">
				<font color="red">${msg}</font>
			    <% session.removeAttribute("msg"); %>
			    <font color="red">*To ensure timely receiving our products, please fill in your real address (English Only)</font>
				<form action="/web/userCenterManage!addAddress.action" method="post" id="addressForm" onsubmit="return beforeAddressSubmit();">
					<ul>
						<li class="wd1"><font class="redfont">*</font> Full Name：</li>
						<li class="wd2"><input name="lvAccountAddress.relName" class="input2"  type="text" id="relName"/><span class="kbqc"> English only</span>
						</li>
				    </ul>
				    
				  <ul>
					<li class="wd1"> Mobile No.：</li>
					<li class="wd2"><input id="mobile" name="lvAccountAddress.mobile" class="input2"  onkeypress="onlyNumber(event)" type="text" id="mobile"/><span id="mobileInfo"></span>
					</li>
				</ul>
				<ul>
					<li class="wd1">Phone No.：</li>
					<li class="wd2"><input id="tel" name="lvAccountAddress.tel" class="input2"  type="text" id="tel"/><span id="telInfo"></span>
					</li>
				</ul>
				<ul class="shdz">
					<li class="wd1"><font class="redfont">*</font> Billing Address：</li>
					<li class="wd2">
						<input  name="lvAccountAddress.adress" id="adress" type="text" style="float:left;" class="input2" value="Street address" onfocus="if(this.value=='Street address')this.value=''" onblur="if(this.value=='')this.value='Street address'"/> 
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
					<li class="wd2"><span class="kbqc">To ensure you receive your package in time, please enter a correct address (in English).</span>	</li>
				</ul>				
				<ul>
					<li class="wd1"><font class="redfont">*</font> Zip：</li>
					<li class="wd2">
					  <input name="lvAccountAddress.postCode" class="input2"  type="text" />
					</li>
				</ul>
				<ul id="infoUl" style="display:none">
					<li class="wd1">&nbsp;</li>
					<li class="wd2"><span id="addressInfo"></span></li>
				</ul>
				<ul class="btn">
					<li class="wd1">&nbsp;</li>
					<li class="wd2"><input type="submit"  value="Submit" class="user_center_bt" /> <input type="button" onclick="javascript:history.go(-1);" value="Back" class="user_center_bt" /></li>
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
initArea();
</script>	
	</body>
</html> 