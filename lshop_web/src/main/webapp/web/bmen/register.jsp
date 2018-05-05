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
	</head>
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/bmen/common/header.jsp" %>
		<div class="sc_main">
	  		<div class="sc_main_left">
	    		<div class="sc_product">
		  			<h2 class="bt3">
		    			<p class="home"><img src="${resDomain}/bmen/res/images/icon02.gif" /></p><p><a href="/index.html">Home</a> > User Register</span> </p>
		  			</h2>
		  			<form action="${storeDomain}/web/regeditAccount.action" method="post" id="regedit_en">
		  					<input type="hidden" name="jumpurl" value="${param.jumpurl }"/>
		  			<div class="login">
			  					<ul>
			  					<li id="erro_msg0" style="display: none;">
								  <p style="padding-left: 130px;"><span id="msgg" class="redfont"></span></p>
								</li>
			  					</ul>
						  		<ul>
								<li>
								  <p class="text"><font class="redfont">*</font>Account：</p>
								  <p class="input">
								    <input type="text" class="input02" id="email" onblur="validateEmail();" onfocus="textFocus('email');"
		    						name="lvAccount.email" maxlength="32" value="your email address"  dvalue="your email address"/></p>
								</li>
								<li class="prompt">
								  <p class="pt" style="display:none;" id="email_tip1">Please enter your E-mail address</p>
								  <!--输入框获得焦点状态提示语-->
								  <p class="er" style="display:none;" id="email_tip2">Please enter your E-mail address</p>
								  <!--错误状态提示语-->
								</li>
							  </ul>
							  
							  <ul>
								<li>
								  <p class="text"><font class="redfont">*</font>Password：</p>
								  <p class="input">
								    <input name="lvAccount.pwd" id="rpwd"  type="password" class="input02" maxlength="16"
		    						onblur="validatePwd();" onfocus="textFocus('pwd');" value=""/></p>
								</li>
								<li class="prompt">
								  <p class="pt" style="display:none;" id="pwd_tip1">6-16 characters. Combination of letters, numbers or symbols is highly recommended</p>
								  <!--输入框获得焦点状态提示语-->
								  <p class="er" style="display:none;" id="pwd_tip2">Please enter your password</p>
								  <!--错误状态提示语-->
								</li>
							  </ul>
							  
							  <ul>
								<li>
								  <p class="text"><font class="redfont">*</font>Confirm password：</p>
								  <p class="input">
								    <input name="truePwd" id="truePwd"  type="password" class="input02" maxlength="16"
		    							onblur="validateTruePwd();" onfocus="textFocus('tpwd');" value=""/></p>
								</li>
								<li class="prompt">
								  <p class="pt" style="display:none;" id="tpwd_tip1">Please re-enter your password</p>
								  <!--输入框获得焦点状态提示语-->
								  <p class="er" style="display:none;" id="tpwd_tip2">Please re-enter your password</p>
								  <!--错误状态提示语-->
								</li>
							  </ul>
							  
							  
							  <ul>
								<li>
								  <p class="text"><font class="redfont">*</font>Captcha：</p>
								  <p><input name="code" id="rcode" class="input22" type="text" maxlength="4" style="width: 40px" 
								  	onblur="validateCode();" onfocus="textFocus('code');"/></p>
								  <p><img src="/web/imager.jsp" width="47" height="21" id="rcId" style="cursor:pointer;"
								  	  onclick="javascript:this.src=this.src+'?'+new Date()"/></p>
								  <p><font class="kbqc">Unclear?</font> 
								  	 <a href="javascript:void(0);" class="ljh" onclick="javascript:document.getElementById('rcId').src=document.getElementById('rcId').src+'?'+new Date()">Refresh.</a></p>
								</li>
								<li class="prompt">
								  <p class="pt" style="display:none;" id="code_tip1">Please enter the Captcha</p>
								  <!--输入框获得焦点状态提示语-->
								  <p class="er" style="display:none;" id="code_tip2">Please enter the Captcha</p>
								  <!--错误状态提示语-->
								</li>
							  </ul>
							  
							  <ul class="btn">
								<li><input type="submit" value="Register" class="user_reg_bt" id="registerbut"/></li>
							  </ul>
		  			<div id="c_regist_tip" class="add_tips"></div>
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
		<script type="text/javascript">
			$.post('/web/activity!getRegistActivity.action', function(data){
				$('#c_regist_tip').append(data);
			});
		</script>
	</body>
</html>