<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>User Center_banana Mall</title>
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
			 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/bmen/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">Home</a> --><a href="/web/userCenterManage!getAccount.action"> User Center</a> --></font> Edit Billing Address</span></h1>
				<div class="usercenter_box">
				<font color="red">${msg}</font>
			    <% session.removeAttribute("msg"); %>
				<form action="/web/userCenterManage!editAddress.action" method="post" id="addressForm">
					<input type="hidden" name="lvAccountAddress.code" value="${lvAccountAddress.code }"/>
					<input type="hidden" name="lvAccountAddress.id" value="${lvAccountAddress.id }"/>
					<input type="hidden" name="lvAccountAddress.relCode" value="${lvAccountAddress.relCode }"/>
					<input type="hidden" name="lvAccountAddress.storeId" value="${lvAccountAddress.storeId }"/>
					<input type="hidden" name="lvAccountAddress.isDefault" value="${lvAccountAddress.isDefault }"/>
					<input type="hidden" name="lvAccountAddress.createTime" value="${lvAccountAddress.createTime }"/>
					<ul>
						<li class="wd1"><font class="redfont">*</font>Full Name：</li>
						<li class="wd2"><input name="lvAccountAddress.relName"  type="text" class="input4" value="${lvAccountAddress.relName }" /><font class="redfont">(English only)</font>
						</li>
				    </ul>
				    
				  <ul>
					<li class="wd1">Mobile No.：</li>
					<li class="wd2">
					  <input name="lvAccountAddress.mobile" type="text" class="input4" onkeypress="onlyNumber(event)" value="${lvAccountAddress.mobile }" id="mobile"/><span id="mobileInfo"></span>
					</li>
				</ul>
				<ul>
					<li class="wd1"> Phone No.：</li>
					<li class="wd2"><input name="lvAccountAddress.tel"  type="text" class="input4" value="${lvAccountAddress.tel }" id="tel"/><span id="telInfo"></span>
					</li>
				</ul>
				<ul>
					<li class="wd1"><font class="redfont">*</font>Billing Address：</li>
					<li class="wd2">
					<input  name="lvAccountAddress.adress" id="adress" type="text" class="input4" value="${lvAccountAddress.adress }" onfocus="if(this.value=='Street address')this.value=''" onblur="if(this.value=='')this.value='Street address'"/> 
					-<input  name="lvAccountAddress.cityName" id="cityName" type="text" class="input04" value="${lvAccountAddress.cityName }" onfocus="if(this.value=='County/City')this.value=''" onblur="if(this.value=='')this.value='County/City'"/>
					-<!--<input  name="lvAccountAddress.provinceName" id="provinceName" type="text" class="input4" value="${lvAccountAddress.provinceName }" onfocus="if(this.value=='State/Province')this.value=''" onblur="if(this.value=='')this.value='State/Province'"/>  -->
					<input type="hidden" id="test" />
					<c:if test="${not empty provinceList}">
						<select name="lvAccountAddress.provinceId" id="provinceName" onchange="provinceChange()" >
							<option value="">--State/Province--</option>
							<c:foreach items="${provinceList}" var="p">
								<option value="${p.id}" <c:if test="${p.id==lvAccountAddress.provinceId  }">selected</c:if>>${p.nameen}</option>
							</c:foreach>
						</select>
						<input type="hidden" name="lvAccountAddress.provinceName" id="provinceNameId"  value="${lvAccountAddress.provinceName }"/>
					</c:if>
					<c:if test="${empty provinceList}">
						<input type="text" name="lvAccountAddress.provinceName" id="provinceName" class="input04" value="${lvAccountAddress.provinceName }"  onfocus="if(this.value=='State/Province')this.value=''" onblur="if(this.value=='')this.value='State/Province'"/> 
					</c:if>
					-<select name="lvAccountAddress.contryId" id="contryId" onchange="contryChange()">
							<option value="">--Choose your country--</option>
							<c:foreach items="${contryList}" var="c">
								<option value="${c.id}" <c:if test="${c.id==lvAccountAddress.contryId  }">selected</c:if>>${c.nameen}</option>
							</c:foreach>
						</select>
						<input type="hidden" name="lvAccountAddress.contryName" id="contrynameId"  value="${lvAccountAddress.contryName }"/>
					</li>
				</ul>
				<ul id="infoUl" style="display:none">
					<li class="wd1"></li>
					<li class="wd2"><span id="addressInfo"></span></li>
				</ul>
				<ul>
					<li class="wd1"><font class="redfont">*</font>Zip：</li>
					<li class="wd2">
					  <input name="lvAccountAddress.postCode"  type="text" class="input4" value="${lvAccountAddress.postCode }" />
					</li>
				</ul>
				<ul class="btn">
					<li class="wd1">&nbsp;</li>
					<li class="wd2"><input type="submit"  value="Save Changes" class="user_center_bt" /></li>
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