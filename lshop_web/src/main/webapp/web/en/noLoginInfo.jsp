<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>TVpad Mall</title>
		<!-- 加载公共JS -->
		<%@include file="/web/en/common/top.jsp" %>
		<script type="text/javascript" src="${resDomain}/en/res/js/myjs.js"></script>
		<script type="text/javascript" src="${resDomain}/en/res/js/page/noLoginInfo.js"></script>
	</head>	
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/en/common/header.jsp" %>
		<div class="sc_main">
		  <div class="sc_main_left">
		    <div class="sc_product">
			  <h2 class="bt3">
			    <p class="home"><img src="${resDomain}/en/res/images/icon02.gif" /></p><p><a href="/index.html">Home</a> > User Login</span> </p>
			  </h2>
		      <div class="login">
		      <form id="login" action="${storeDomain}/web/userCenterManage!login.action" 
		      method="post" onsubmit="">
			  <input type="hidden" name="jumpurl" value="${param.jumpurl }"/>
			  <ul>
				<li>
				  <p class="text"></p>
				  <p class="input"><font class="redfont" id="msg1"></font></p>
				</li>
			  </ul>
			  <ul>
				<li>
				  <p class="text"><font class="redfont">*</font>Account：</p>
				  <p class="input">
				  	<input name="uname" id="loginname" value="${uname}" dvalue="you Email address or nickname" onblur="checkEmail()" class="input02" type="text" />
				    </p>
				  <p><a href="${storeDomain}/web/userCenterManage!toRegister.action?jumpurl=${param.jumpurl }" class="ljl">Register</a></p>
				</li>
				<li id="remind_loginname" class="prompt"></li>
			  </ul>
			  <ul>
				<li>
				  <p class="text"><font class="redfont">*</font>Password：</p>
				  <p class="input">
				  	<input name="pwd" id="loginpwd" onblur="checkPassword()" class="input02" type="password" />
				  <p><a href="${storeDomain}/web/userCenterManage!toFindPassword.action" class="ljh">Forgot password?</a></p>
				</li>
				<li id="remind_loginpwd" class="prompt"></li>
			  </ul>
			  <ul>
				<li>
				  <p class="text"><font class="redfont">*</font>Captcha：</p>
				  <p><input name="code" id="logincode" onblur="checkVcode()" class="input22" type="text"/></p>
				  <p><img src="/web/imager.jsp" width="47" height="21" id="rcId" onclick="javascript:this.src=this.src+'?'+new Date()"/></p>
				  <p><font class="kbqc">Unclear?</font><a href="#" class="ljh" onclick="javascript:document.getElementById('rcId').src=document.getElementById('rcId').src+'?'+new Date()"> Refresh</a></p>
				</li>
				<li id="remind_logincode" class="prompt"></li>
			  </ul>
			  <ul>
				<li>
				  <p class="text">&nbsp;</p><input type="submit" value="Login" class="user_center_bt" />
				</li>
		     </ul>
			  </form>
			 </div>
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
		<%@include file="/web/en/common/footer.jsp" %>
		
	</body>
</html>
