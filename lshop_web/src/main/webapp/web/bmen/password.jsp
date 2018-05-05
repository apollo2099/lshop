<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib prefix="app" uri="/WEB-INF/tld/gv-app.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Retrieve Password_banana Mall</title>
		<!-- 加载公共JS -->
		<%@include file="/web/bmen/common/top.jsp"%>
		<link href="${resDomain}/bmen/res/css/user_center.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="${resDomain}/bmen/res/js/storeIndex.js"></script>
		
	</head>
<%String erromsg = (String)request.getAttribute("erromsg");
String msg = "";
if(null != erromsg){
	if(erromsg.equals("0")){
		msg = "Account is not activated";
	}
	if(erromsg.equals("-1")){
		msg = "Account does not exist";
	}
	if(erromsg.equals("-2")){
		msg = "Verification code is incorrect";
	}
}
%>		
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/bmen/common/header.jsp"%>
		<div  class="xlhk_main">
			<h3>Retrieve Password</h3>
			<div class="password_bz"></div>
			<ul>
				<form action="/web/userCenterManage!password.action" method="post" onsubmit="return submitFindPwdValidate();">
				  <li><p class="wd3">Your email address：</p>
				  	  <p><input type="text" class="input02" name="lvAccount.email" id="emailId"/></p>
				  </li>
				  <li><p class="wd3">CAPTCHA：</p>
				  	  <p><input name="code" id="rcode"  type="text" class="input22" style="width: 40px" maxlength="4"/></p>
				  	  <p><img src="/web/imager.jsp" width="47" height="21" id="rcId" style="cursor:pointer;"
							  onclick="javascript:this.src=this.src+'?'+new Date()"/></p>
					  <p><font class="kbqc">Unclear?</font>
							  <a href="javascript:void(0);" class="ljh" 
							  onclick="javascript:document.getElementById('rcId').src=document.getElementById('rcId').src+'?'+new Date()">Refresh</a></p>
				  </li>
				  	  
				  <c:if test="${erromsg == null }">
				  	<li><p class="btn_password"><font class="redfont" id="pwd_tip"></font></p></li>
				  </c:if>
				  <c:if test="${erromsg != null }">
				  	<li><p class="btn_password"><font class="redfont" id="pwd_tip"><%=msg %></font></p></li>
				  </c:if>
				  <li>
				  
				    <p class="btn_password"><input name="" type="submit" onclick="" value="Next"  class="user_center_bt" /></p>
				  </li>
				 
				<div class="cb"></div>
				</form>
			</ul>
		</div>
		
		<!-- 
		<div  class="xlhk_main">
			<ul class="looking_pw">
				<form name="myForm" action="/web/userCenterManage!password.action"  method="post" id="">
					<li  class="looking_pw1"><span class="wd3"><b class="bt">Retrieve Password </b></span></li>
					<font color="red">${findPassFormMsg}</font>
					<li  class="looking_pw1"><span class="wd3"><font class="redfont">*</font>Your Email Address：</span><input name="lvAccount.email" id="emailId" type="text" class="input02"  onblur="checkIsExistEmail()"  onkeyup="checkIsExistEmail()"/><span id="emailInfo"></span></li>
			        <li  class="looking_pw1"><span class="qrtj_1"><input name="" type="submit" value="Submit"  class="user_center_bt" style="CURSOR: pointer; "/> <input onclick="javascript:location.href='/index.html'" type="button"  value="Back"  class="user_center_bt" style="CURSOR: pointer; "/></span></li>
					<div class="cb"></div>
				</form>
			</ul>
		</div>
		 -->
		<!-- footer -->
		<%@include file="/web/bmen/common/footer.jsp" %>
	
	</body>
</html> 
