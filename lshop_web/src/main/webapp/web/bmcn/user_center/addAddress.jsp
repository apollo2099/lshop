<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>用户中心_banana商城</title>
		<link href="${resDomain}/bmcn/res/css/user_center.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<%@include file="/web/bmcn/common/top.jsp"%>
		<script type="text/javascript" src="${resDomain}/bmcn/res/js/area_cn.js" async="async"></script>
		<script type="text/javascript" src="${resDomain}/bmcn/res/js/address.js"></script>
	</head>
	<body>
	
		<!-- 获取商城头部文件 -->
		<%@include file="/web/bmcn/common/header.jsp"%>
		
		<div class="content_main">
			<!-- left_frame -->
			<%@include file="/web/bmcn/user_center/leftFrame.jsp" %>
			 
			 <div class="right_frame">
			 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/bmcn/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">首页</a> --><a href="/web/userCenterManage!getAccount.action"> 用户中心</a> --></font> 新增收货地址</span></h1> 
				<div class="usercenter_box">
				<font color="red">${msg}</font>
			    <% session.removeAttribute("msg"); %>
			    <font color="red">*请填写真实有效的地址，非中国地址请填写英文</font><br></br>
				<form action="/web/userCenterManage!addAddress.action" method="post" id="addressForm">
					<ul>
						<li class="wd1"><font class="redfont">*</font>收货人姓名：</li>
						<li class="wd2"><input name="lvAccountAddress.relName" class="input4"  type="text" id="relName"/><font class="redfont">（非中国地区请填写英文）</font>
						</li>
				    </ul>
				    
				  <ul>
					<li class="wd1">手机号码：</li>
					<li class="wd2"><input id="mobile" name="lvAccountAddress.mobile" class="input4"  onkeypress="onlyNumber(event)" type="text" id="mobile"/><span id="mobileInfo"></span>
					</li>
				</ul>
				<ul>
					<li class="wd1">固定电话：</li>
					<li class="wd2"><input id="tel" name="lvAccountAddress.tel" class="input4"  type="text" id="tel"/><span id="telInfo"></span>
					</li>
				</ul>
				<ul>
					<li class="wd1"><font class="redfont">*</font>收货地址：</li>
					<li class="wd2">
						<!--<input  name="lvAccountAddress.provinceId" id="provinceName" type="text" class="input4" value="洲/省" onfocus="if(this.value=='洲/省')this.value=''" onblur="if(this.value=='')this.value='洲/省'"/>  -->
						<select name="lvAccountAddress.contryId" id="contryId" onchange="contryChange()">
							<option value="">--请选择国家--</option>
							<option value="100023">中国</option>
							<c:foreach items="${contryList}" var="c">
								<c:if test="${c.id!=100023}">
									<option value="${c.id}">${c.nameen}</option>
								</c:if>
							</c:foreach>
						</select><font class="redfont">*</font>
						<input type="hidden" name="lvAccountAddress.contryName" id="contrynameId"  value=""/>
						-
						<input type="hidden" id="test" />
						<input type="text" name="lvAccountAddress.provinceName" id="provinceName" class="input04" value="洲/省"  onfocus="if(this.value=='洲/省')this.value=''" onblur="if(this.value=='')this.value='洲/省'"/> 
						- 
						<input type="hidden" id="cityTest" />
						<input  name="lvAccountAddress.cityName" id="cityName" type="text" class="input04" value="县/市" onfocus="if(this.value=='县/市')this.value=''" onblur="if(this.value=='')this.value='县/市'"/>
						-
						<input  name="lvAccountAddress.adress" id="adress" type="text" class="input4" value="街道详细地址" onfocus="if(this.value=='街道详细地址')this.value=''" onblur="if(this.value=='')this.value='街道详细地址'"/> 
					</li>
				</ul>
				<ul>
					<li class="wd1"><font class="redfont">*</font>邮政区号：</li>
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
					<li class="wd2"><input type="submit"  value="确认提交" class="user_center_bt" /> <input type="button" onclick="javascript:history.go(-1);" value="返回" class="user_center_bt" /></li>
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