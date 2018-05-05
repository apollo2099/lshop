<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>User Center _banana Mall</title>
		<link href="${resDomain}/bmen/res/css/user_center.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<%@include file="/web/bmen/common/top.jsp"%>
		<script type="text/javascript" src="${resDomain}/bmen/res/js/address.js"></script>
		<style type="text/css">
		#addressForm input[type="text"]{color:#666;}
		</style>
	</head>
	<body>
	
		<!-- 获取商城头部文件 -->
		<%@include file="/web/bmen/common/header.jsp"%>
		
		<div class="content_main">
			<!-- left_frame -->
			<%@include file="/web/bmen/user_center/leftFrame.jsp" %>
			 
			 <div class="right_frame">
			 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/bmen/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">Home</a> --><a href="/web/userCenterManage!getAccount.action"> User Center</a> --></font> Add New Address</span></h1> 
				<div class="usercenter_box">
				<font color="red">${msg}</font>
			    <% session.removeAttribute("msg"); %>
			    <font color="red">*To ensure timely receiving our products, please fill in your real address (English Only)</font><br></br>
				<form action="/web/userCenterManage!addAddress.action" method="post" id="addressForm">
					<ul>
						<li class="wd1"><font class="redfont">*</font> Full Name：</li>
						<li class="wd2"><input name="lvAccountAddress.relName" class="input4"  type="text" id="relName"/><font class="redfont">(English only)</font>
						</li>
				    </ul>
				    
				  <ul>
					<li class="wd1"> Mobile No.：</li>
					<li class="wd2"><input id="mobile" name="lvAccountAddress.mobile" class="input4"  onkeypress="onlyNumber(event)" type="text" id="mobile"/><span id="mobileInfo"></span>
					</li>
				</ul>
				<ul>
					<li class="wd1">Phone No.：</li>
					<li class="wd2"><input id="tel" name="lvAccountAddress.tel" class="input4"  type="text" id="tel"/><span id="telInfo"></span>
					</li>
				</ul>
				<ul>
					<li class="wd1"><font class="redfont">*</font> Billing Address：</li>
					<li class="wd2">
					<input  name="lvAccountAddress.adress" id="adress" type="text" class="input4" value="Street address" onfocus="if(this.value=='Street address')this.value=''" onblur="if(this.value=='')this.value='Street address'"/> 
					- <input  name="lvAccountAddress.cityName" id="cityName" type="text" class="input04" value="County/City" onfocus="if(this.value=='County/City')this.value=''" onblur="if(this.value=='')this.value='County/City'"/>
					- <!--<input  name="lvAccountAddress.provinceId" id="provinceName" type="text" class="input4" value="State/Province" onfocus="if(this.value=='State/Province')this.value=''" onblur="if(this.value=='')this.value='State/Province'"/>  -->
					<input type="hidden" id="test" />
					<input type="text" name="lvAccountAddress.provinceName" id="provinceName" class="input04" value="State/Province"  onfocus="if(this.value=='State/Province')this.value=''" onblur="if(this.value=='')this.value='State/Province'"/> 
					</select>
						- <select name="lvAccountAddress.contryId" id="contryId" onchange="contryChange()">
							<option value="">-Choose your country-</option>
							<c:foreach items="${contryList}" var="c">
								<option value="${c.id}"  <c:if test="${c.id==lvAccountAddress.contryId  }">selected</c:if>>${c.nameen}</option>
							</c:foreach>
						</select><font class="redfont">*</font>
						<input type="hidden" name="lvAccountAddress.contryName" id="contrynameId"  value=""/>
					</li>
				</ul>
				<ul>
					<li class="wd1"><font class="redfont">*</font> Zip：</li>
					<li class="wd2">
					  <input name="lvAccountAddress.postCode" class="input4"  type="text" />
					</li>
				</ul>
				<ul id="infoUl" style="display:none">
					<li class="wd1"></li>
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
		<%@include file="/web/bmen/common/footer.jsp" %>
	
	</body>
</html> 