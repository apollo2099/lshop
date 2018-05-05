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
		<script type="text/javascript" src="${resDomain}/bmcn/res/js/info.js"></script>
	</head>
<%Integer statu = (Integer)session.getAttribute("statu");
String msg = "";
if(null != statu){
	if(statu == 0){
		msg = "修改失败！";
	}
	if(statu == 1){
		msg = "修改成功！";
	}
	if(statu == -1){
		msg = "修改失败，该昵称已存在！";
	}
}
%>	
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/bmcn/common/header.jsp"%>
		
		<div class="content_main">
		
			<!-- left_frame -->
			<%@include file="/web/bmcn/user_center/leftFrame.jsp" %>
			
			 <div class="right_frame">
			 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/bmcn/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">首页</a> --><a href="/web/userCenterManage!getAccount.action"> 用户中心</a> --></font> 账户信息</span></h1> 
				
				<form action="/web/userCenterManage!editInfo.action" method="post" id="infoForm">
					<input type="hidden" name="lvAccountInfo.id" value="${lvAccountInfo.id}"/>
					<input type="hidden" name="lvAccountInfo.userCode" value="${lvAccountInfo.userCode}"/>
					<input type="hidden" name="lvAccountInfo.lastTime" value="${lvAccountInfo.lastTime}"/>
					<input type="hidden" name="lvAccountInfo.storeId" value="${lvAccountInfo.storeId}"/>
					<input type="hidden" name="lvAccountInfo.code" value="${lvAccountInfo.code}"/>
					<input type="hidden" name="lvAccountInfo.createTime" value="${lvAccountInfo.createTime}"/>
					<input type="hidden" name="ordnickname" id="ordnickname" value="${lvAccount.nickname}"/>
					<div class="usercenter_box">
					<c:if test="${not empty statu}">
						<ul>
							<li class="password">
								<p class="text">&nbsp;</p>
								<p><font color="red" ><%=msg %></font></p>
							</li>
					    </ul>
					</c:if>
					
					<%session.removeAttribute("statu"); %>
						<ul>
							<li class="password">
								<p class="text">登录帐号：</p>
								<p>${lvAccount.email }</p>
							</li>
					    </ul>
					   <ul>
							<li class="password">
								<p class="text"><font class="redfont">*</font>昵称：</p>
								<p>
									<c:if test="${not empty lvAccount.nickname}">
										<input type="hidden" name="lvAccount.nickname" class="input4" value="${lvAccount.nickname}"/>
										${lvAccount.nickname }
									</c:if>
									<c:if test="${empty lvAccount.nickname}">
										<input type="text" name="lvAccount.nickname" class="input4" value="${lvAccount.nickname}" maxlength="32"/>
									</c:if>
								</p> 
							</li>
							<li class="prompt"></li>
					  </ul>	
					  <ul>
							<li class="password">
								<p class="text">性 别：</p>
								<p><input type="radio" name="lvAccountInfo.sex" value="0" <s:if test="lvAccountInfo.sex==0 || lvAccountInfo.sex==null">checked="checked" </s:if>/>男 
							  <input name="lvAccountInfo.sex" type="radio" <s:if test="lvAccountInfo.sex==1">checked="checked" </s:if> value="1" />女
							  <input name="lvAccountInfo.sex" type="radio" value="2" <s:if test="lvAccountInfo.sex==2">checked="checked" </s:if> />保密
							    </p>
							</li>
					  </ul>
					  <ul>
						<li class="password">
							<p class="text">QQ：</p>
							<p><input type="text" class="input4" onkeypress="onlyNumber(event)" name="lvAccountInfo.qq" id="qq"  value="${lvAccountInfo.qq}"/ maxlength="16"></p>
						</li>
						<li class="prompt"></li>
					</ul>
					<ul>
						<li class="password">
							<p class="text">MSN：</p>
							<p><input type="text" class="input4" name="lvAccountInfo.msn" value="${lvAccountInfo.msn}" id="msn" maxlength="16"/></p>
						</li>
						<li class="prompt"></li>
					</ul>
					<ul>
						<li class="password">
							<p class="text"><font class="redfont"> *</font>真实姓名：</p>
							<p><input type="text" class="input4" name="lvAccountInfo.name" id="realName"  value="${lvAccountInfo.name}" maxlength="32"/></p>
						</li>
						<li class="prompt"></li>	
					</ul>
					<ul>
						<li class="password">
							<p class="text">电话号码：</p>
							<p><input type="text" class="input4" name="lvAccountInfo.tel" value="${lvAccountInfo.tel}" id="tel" maxlength="16"/></p> 
						</li>
						<li class="prompt">
							<p class="pt" id="telInfo1">电话号码和手机号码必须输入一个</p>
							<p class="er" id="telInfo2" style="display: none;">电话号码和手机号码必须输入一个</p>
						</li>
					</ul>
					<ul>
						<li class="password">
							<p class="text">手机号码：</p>
							<p><input  type="text" class="input4" name="lvAccountInfo.mobile" onkeypress="onlyNumber(event)" value="${lvAccountInfo.mobile }" id="mobile" maxlength="16"/></p>
						</li>
						<li class="prompt">
							<p class="pt" id="mobileInfo1">电话号码和手机号码必须输入一个</p>
							<p class="er" id="mobileInfo2" style="display: none;">电话号码和手机号码必须输入一个</p>
						</li>		
					</ul>
				<ul>
					<li class="password">
						<p class="text">联系地址：</p>
						<p>
					  <input name="lvAccountInfo.address" id="adress" type="text" class="input4" value="${lvAccountInfo.address }" onfocus="if(this.value=='街道详细地址')this.value=''" onblur="if(this.value=='')this.value='街道详细地址'" maxlength="128"/> 
					- <input name="lvAccountInfo.cityName" id="cityName" type="text" class="input04" value="${lvAccountInfo.cityName }" onfocus="if(this.value=='县/市')this.value=''" onblur="if(this.value=='')this.value='县/市'" maxlength="32"/>
					- <input name="lvAccountInfo.provinceName" id="provinceName" type="text" class="input04" value="${lvAccountInfo.provinceName }" onfocus="if(this.value=='洲/省')this.value=''" onblur="if(this.value=='')this.value='洲/省'" maxlength="32"/>
						- <select name="lvAccountInfo.contryName" id="contryId"  class="input4">
							<option value="">--请选择国家--</option>
							<c:foreach items="${contryList}" var="c">
								<option value="${c.code}" <c:if test="${c.code==lvAccountInfo.contryName  }">selected</c:if>>${c.namecn}</option>
							</c:foreach>
						</select>
						</p>
					</li>
					<li class="prompt"><p class="er" id="addrInfo"></p></li>	
				</ul>
					<ul>
						<li class="password">
							<p class="text"><font class="redfont"> *</font>邮编：</p>
							<p><input name="lvAccountInfo.postCode" id="postcodeId" type="text" value="${lvAccountInfo.postCode}" class="input4" maxlength="16"/></p>
						</li>
						<li class="prompt"></li>		
					</ul>
					<ul class="btn">
						<li class="wd1">&nbsp;</li>
						<li class="wd2"><input type="button" onclick="subForm()" id="editUserInfoSubmit"  value="保存修改" class="user_center_bt" />
					</ul>	
					</div>
				</form>
				
		  </div>
			 <!--End right_Frame-->
		</div>
		<!--End content-->	
		
		<!-- 底部广告位-->
		<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
				
		<!-- footer-->
		<%@include file="/web/bmcn/common/footer.jsp" %>
	
	</body>
</html> 