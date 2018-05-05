<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>用户中心_banana商城</title>
		<link href="${resDomain}/bmcn/res/css/user_center.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<script type="text/javascript" src="${resDomain}/bmcn/res/js/area_cn.js"></script>
		<%@include file="/web/bmcn/common/top.jsp"%>
		<script type="text/javascript" src="${resDomain}/bmcn/res/js/address.js"></script>
	</head>
	
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/bmcn/common/header.jsp"%>
		
		<div class="content_main">
			<!-- left_frame -->
			<%@include file="/web/bmcn/user_center/leftFrame.jsp" %>
			 
			 <div class="right_frame">
			 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/bmcn/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">首页</a> --><a href="/web/userCenterManage!getAccount.action"> 用户中心</a> --></font> 编辑收货地址</span></h1> 
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
						<li class="wd1"><font class="redfont">*</font>收货人姓名：</li>
						<li class="wd2"><input name="lvAccountAddress.relName"  type="text" class="input4" value="${lvAccountAddress.relName }" /><font class="redfont">（非中国地区请填写英文）</font>
						</li>
				    </ul>
				    
				  <ul>
					<li class="wd1">手机号码：</li>
					<li class="wd2">
					  <input name="lvAccountAddress.mobile" type="text" class="input4" onkeypress="onlyNumber(event)" value="${lvAccountAddress.mobile }" id="mobile"/><span id="mobileInfo"></span>
					</li>
				</ul>
				<ul>
					<li class="wd1">固定电话：</li>
					<li class="wd2"><input name="lvAccountAddress.tel"  type="text" class="input4" value="${lvAccountAddress.tel }" id="tel"/><span id="telInfo"></span>
					</li>
				</ul>
				<ul>
					<li class="wd1"><font class="redfont">*</font>收货地址：</li>
					<li class="wd2">
						<select name="lvAccountAddress.contryId" id="contryId" onchange="contryChange()">
							<option value="">--请选择国家--</option>
							<option value="100023" <c:if test="${lvAccountAddress.contryId==100023}">selected</c:if>>中国</option>
							<c:foreach items="${contryList}" var="c">
								<c:if test="${c.id!=100023}">
									<option value="${c.id}"  <c:if test="${c.id==lvAccountAddress.contryId  }">selected</c:if>>${c.nameen}</option>
								</c:if>
							</c:foreach>
						</select>
						<input type="hidden" name="lvAccountAddress.contryName" id="contrynameId"  value="${lvAccountAddress.contryName }"/>
						-<!--<input  name="lvAccountAddress.provinceName" id="provinceName" type="text" class="input4" value="${lvAccountAddress.provinceName }" onfocus="if(this.value=='洲/省')this.value=''" onblur="if(this.value=='')this.value='洲/省'"/>  -->
						<input type="hidden" id="test" />
						<c:if test="${not empty provinceList}">
							<select name="lvAccountAddress.provinceId" id="provinceName" onchange="provinceChange()" >
								<option value="">--请选择洲/省--</option>
								<c:foreach items="${provinceList}" var="p">
									<option value="${p.id}" <c:if test="${p.id==lvAccountAddress.provinceId  }">selected</c:if>><c:if test="${lvAccountAddress.contryId==100023}">${p.namecn}</c:if><c:if test="${lvAccountAddress.contryId!=100023}">${p.nameen}</c:if></option>
								</c:foreach>
							</select>
							<input type="hidden" name="lvAccountAddress.provinceName" id="provinceNameId"  value="${lvAccountAddress.provinceName }"/>
						</c:if>
						<c:if test="${empty provinceList}">
							<input type="text" name="lvAccountAddress.provinceName" id="provinceName" class="input04" value="${lvAccountAddress.provinceName }"  onfocus="if(this.value=='洲/省')this.value=''" onblur="if(this.value=='')this.value='洲/省'"/> 
						</c:if>
						-<input type="hidden" id="cityTest" />
						<c:if test="${not empty cityList}">
							<select name="lvAccountAddress.cityId" id="cityName" onchange="cityChange()" >
								<option value="">--请选择县/市--</option>
								<c:foreach items="${cityList}" var="p">
									<option value="${p.id}" <c:if test="${p.id==lvAccountAddress.cityId  }">selected</c:if>><c:if test="${lvAccountAddress.contryId==100023}">${p.namecn}</c:if><c:if test="${lvAccountAddress.contryId!=100023}">${p.nameen}</c:if></option>
								</c:foreach>
							</select>
							<input type="hidden" name="lvAccountAddress.cityName" id="cityNameId"  value="${lvAccountAddress.cityName }"/>
						</c:if>
						<c:if test="${empty cityList}">
							<input type="text" name="lvAccountAddress.cityName" id="cityName" class="input04" value="${lvAccountAddress.cityName }"  onfocus="if(this.value=='洲/省')this.value=''" onblur="if(this.value=='')this.value='洲/省'"/> 
						</c:if>
						-<input  name="lvAccountAddress.adress" id="adress" type="text" class="input4" value="${lvAccountAddress.adress }" onfocus="if(this.value=='街道详细地址')this.value=''" onblur="if(this.value=='')this.value='街道详细地址'"/> 
					</li>
				</ul>
				<ul id="infoUl" style="display:none">
					<li class="wd1"></li>
					<li class="wd2"><span id="addressInfo"></span></li>
				</ul>
				<ul>
					<li class="wd1"><font class="redfont">*</font>邮政区号：</li>
					<li class="wd2">
					  <input name="lvAccountAddress.postCode"  type="text" class="input4" value="${lvAccountAddress.postCode }" />
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
		<%@include file="/web/bmcn/common/footer.jsp" %>
	
	</body>
</html> 