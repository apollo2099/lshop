<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>banana Mall</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<!-- 加载公共JS -->
		<%@include file="/web/bmen/common/top.jsp" %>
		<script type="text/javascript" src="${resDomain}/bmen/res/js/storeIndex.js"></script>
	</head>
<%
String uname="";
Object ouname = request.getAttribute("uname");
if(null == ouname){
	uname = "your email or nickname";
}
%>	
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/bmen/common/header.jsp" %>
		<div class="sc_main">
		  	<div class="sc_main_left">
		    	<div class="sc_product">
			  		<h2 class="bt3">
			     		<p class="home"><img src="${resDomain}/bmen/res/images/icon02.gif" /></p><p><a href="/index.html">Home</a> > User Login</span> </p>
			  		</h2>
			  			<form id="login_en"  action="${storeDomain}/web/userCenterManage!login.action" method="post">
			  				<input type="hidden" name="loginstyle" value="1"/>
						    <input type="hidden" name="jumpurl" value="${param.jumpurl }"/>
			  		<div class="login">
						  <ul>
								<li><p style="margin-left: 130px;">
								<font class="redfont" id="msg1">Please login first. If you don't have an account yet, please register!</font>
							    </p>
								</li>
						  </ul>
						  
						  <ul>
							<li>
							  <p class="text"><font class="redfont">*</font>Account：</p>
							  <p class="input">
							    <input name="uname" id="name" type="text" class="input02" maxlength="60"
							    onfocus="textFocus('name');" onblur="validateLogAccount();" value="<%=uname%>" dvalue="<%=uname%>"/></p>
							  <p><a href="${storeDomain}/web/userCenterManage!toRegister.action?jumpurl=${param.jumpurl }" class="ljl">Register</a></p>
							</li>
							<li class="prompt">
							  <p class="pt" style="display:none;" id="name_tip1">Please enter your email or nickname</p>
							  <!--输入框获得焦点状态提示语-->
							  <p class="er" style="display:none;" id="name_tip2"></p>
							  <!--错误状态提示语-->
							</li>
						  </ul>
						  
						  <ul>
							<li>
							  <p class="text"><font class="redfont">*</font>Password：</p>
							  <p class="input">
							    <input name="pwd" id="log_pwd"  type="password" class="input02" maxlength="16"
							    onfocus="textFocus('pwd');" onblur="validateLogPwd();"/></p>
							  <p><a href="${storeDomain}/web/userCenterManage!toFindPassword.action" class="ljh">Forgot password?</a></p>
							</li>
							<li class="prompt">
							  <p class="pt" style="display:none;" id="pwd_tip1">Please enter your password</p>
							  <!--输入框获得焦点状态提示语-->
							  <p class="er" style="display:none;" id="pwd_tip2" ></p>
							  <!--错误状态提示语-->
							</li>
						  </ul>
						  
						  
						  <ul>
							<li>
							  <p class="text"><font class="redfont">*</font>Captcha：</p>
							  <p><input name="code" id="rcode"  type="text" class="input22" style="width: 40px" 
							  onfocus="textFocus('code');" onblur="validateLogCode();" maxlength="4"/></p>
							  <p><img src="/web/imager.jsp" width="47" height="21" id="rcId" style="cursor:pointer;"
							  onclick="javascript:this.src=this.src+'?'+new Date()"/></p>
							  <p><font class="kbqc">Unclear?</font>
							  <a href="javascript:void(0);" class="ljh" 
							  onclick="javascript:document.getElementById('rcId').src=document.getElementById('rcId').src+'?'+new Date()">Refresh.</a></p>
							</li>
							<li class="prompt">
							  <p class="pt" style="display:none;" id="code_tip1">Please enter the Captcha</p>
							  <!--输入框获得焦点状态提示语-->
							  <p class="er" style="display:none;" id="code_tip2"></p>
							  <!--错误状态提示语-->
							</li>
						  </ul>
						 
						  <ul class="btn">
							<li><input type="submit" value="Login" class="user_center_bt" /></li>
						  </ul>
			 		</div>
			  			</form>
		    	</div>
		  	</div>
		  	<!--End left-->
		  
		  	<div class="sc_main_right">
			    <!-- 右侧第一个广告 -->
				<ad:ad adkey="AD_TVPAD_RIGHT1"></ad:ad>
				<!-- 右侧第二个广告 -->
				<ad:ad adkey="AD_TVPAD_RIGHT2"></ad:ad>
		  	</div>
		  	<!--End right-->
		  
			<div class="cb"></div>  
		</div>
		
		<!-- 底部广告位-->
		<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
		<!-- footer -->
		<%@include file="/web/bmen/common/footer.jsp" %>
	
	</body>
</html>
