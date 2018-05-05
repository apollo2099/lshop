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
		<script type="text/javascript" src="${resDomain}/bmen/res/js/info.js"></script>
	</head>
<%Integer statu = (Integer)session.getAttribute("statu");
String msg = "";
if(null != statu){
	if(statu == 0){
		msg = "Operation failed!";
	}
	if(statu == 1){
		msg = "Successfully changed!";
	}
	if(statu == -1){
		msg = "Operation failed,the nickname is already existed!";
	}
}
%>		
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/bmen/common/header.jsp"%>
		
		<div class="content_main">
		
			<!-- left_frame -->
			<%@include file="/web/bmen/user_center/leftFrame.jsp" %>
			
			 <div class="right_frame">
			 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/bmen/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">Home</a> --><a href="/web/userCenterManage!getAccount.action"> User Center</a> --></font> Account Info</span></h1>
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
				    <% session.removeAttribute("statu"); %>
						<ul>
							<li class="password">
								<p class="text">Account：</p>
								<p>${lvAccount.email }</p>
							</li>
					    </ul>
					   <ul>
							<li class="password">
								<p class="text"><font class="redfont">*</font>Nickname：</p>
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
								<p class="text">Gender：</p>
								<p><input type="radio" name="lvAccountInfo.sex" value="0" <s:if test="lvAccountInfo.sex==0 || lvAccountInfo.sex==null">checked="checked" </s:if>/>Male 
							  <input name="lvAccountInfo.sex" type="radio" <s:if test="lvAccountInfo.sex==1">checked="checked" </s:if> value="1" />Female
							  <input name="lvAccountInfo.sex" type="radio" value="2" <s:if test="lvAccountInfo.sex==2">checked="checked" </s:if> />Private
							    </p>
							</li>
					  </ul>
					  <ul>
						<li class="password">
							<p class="text">QQ：</p>
							<p><input type="text" class="input4" onkeypress="onlyNumber(event)" name="lvAccountInfo.qq" id="qq"  value="${lvAccountInfo.qq}" maxlength="16"/></p>
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
							<p class="text"><font class="redfont"> *</font> Real name：</p>
							<p><input type="text" class="input4" name="lvAccountInfo.name" id="realName"  value="${lvAccountInfo.name}" maxlength="32"/></p>
						</li>
						<li class="prompt"></li>	
					</ul>
					<ul>
						<li class="password">
							<p class="text">Tel. ：</p>
							<p><input type="text" class="input4" name="lvAccountInfo.tel" value="${lvAccountInfo.tel}" id="tel" maxlength="16"/></p> 
						</li>
						<li class="prompt">
							<p class="pt" id="telInfo1">You must fill in a best contact number we can reach you at least.</p>
							<p class="er" id="telInfo2" style="display: none;">You must fill in a best contact number we can reach you at least.</p>
						</li>
					</ul>
					<ul>
						<li class="password">
							<p class="text">Mobile：</p>
							<p><input  type="text" class="input4" name="lvAccountInfo.mobile" onkeypress="onlyNumber(event)" value="${lvAccountInfo.mobile }" id="mobile" maxlength="16"/></p>
						</li>
						<li class="prompt">
							<p class="pt" id="mobileInfo1">You must fill in a best contact number we can reach you at least.</p>
							<p class="er" id="mobileInfo2" style="display: none;">You must fill in a best contact number we can reach you at least.</p>
						</li>		
					</ul>
				<ul>
					<li class="password">
						<p class="text">Address：</p>
						<p>
					  <input name="lvAccountInfo.address" id="adress" type="text" class="input4" value="${lvAccountInfo.address }" onfocus="if(this.value=='Street address')this.value=''" onblur="if(this.value=='')this.value='Street address'" maxlength="128"/> 
					- <input name="lvAccountInfo.cityName" id="cityName" type="text" class="input04" value="${lvAccountInfo.cityName }" onfocus="if(this.value=='County/City')this.value=''" onblur="if(this.value=='')this.value='County/City'" maxlength="32"/>
					- <input name="lvAccountInfo.provinceName" id="provinceName" type="text" class="input04" value="${lvAccountInfo.provinceName }" onfocus="if(this.value=='State/Province')this.value=''" onblur="if(this.value=='')this.value='State/Province'" maxlength="32"/>
						- <select name="lvAccountInfo.contryName" id="contryId"  class="input4">
							<option value="">--Choose your country--</option>
							<c:foreach items="${contryList}" var="c">
								<option value="${c.code}" <c:if test="${c.code==lvAccountInfo.contryName  }">selected</c:if>>${c.nameen}</option>
							</c:foreach>
						</select>
						</p>
					</li>
					<li class="prompt"><p class="er" id="addrInfo"></p></li>	
				</ul>
					<ul>
						<li class="password">
							<p class="text"><font class="redfont"> *</font>Zip code：</p>
							<p><input name="lvAccountInfo.postCode" id="postcodeId" type="text" value="${lvAccountInfo.postCode}" class="input4" maxlength="16"/></p>
						</li>
						<li class="prompt"></li>		
					</ul>
					<ul class="btn">
						<li class="wd1">&nbsp;</li>
						<li class="wd2"><input type="button" onclick="subForm()" id="editUserInfoSubmit"  value="Save" class="user_center_bt" />
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
		<%@include file="/web/bmen/common/footer.jsp" %>
	
	</body>
</html> 