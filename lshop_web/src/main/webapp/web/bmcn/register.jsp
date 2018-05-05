<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>banana商城</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<!-- 加载公共JS -->
		<%@include file="/web/bmcn/common/top.jsp" %>
	</head>
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/bmcn/common/header.jsp" %>
		<div class="sc_main">
	  		<div class="sc_main_left">
	    		<div class="sc_product">
		  			<h2 class="bt3">
		    			<p class="home"><img src="${resDomain}/bmcn/res/images/icon02.gif" /></p><p><a href="/index.html">首页</a> > 用户注册 </p>
		  			</h2>
	  <div class="box_login">
        <div class="login regist">		  				
        <form action="${storeDomain}/web/regeditAccount.action" id="regedit" method="post" onsubmit="">
		  					<input type="hidden" name="jumpurl" value="${param.jumpurl }"/>
						  	
	<ul>
		<li id="erro_msg0" style="display: none;">
		  <p style="padding-left: 100px;"><span id="msgg" class="redfont"></span></p>
		</li>

		
		<li>
		  <p class="text"><font class="redfont">*</font>账号：</p>
		  <p class="input">
		    <input type="text" class="input02" id="email" onblur="validateEmail();" onfocus="textFocus('email');"
		    name="lvAccount.email" maxlength="32" value="请输入Email地址"  dvalue="请输入Email地址"/></p>
		</li>
		<li class="prompt">
		  <p class="pt" style="display:none;" id="email_tip1">请输入Email地址，该地址将成为登录账户及系统邮件接收地址，请确认可以使用！</p>
		  <!--输入框获得焦点状态提示语-->
		  <p class="er" style="display:none;" id="email_tip2"></p>
		  <!--错误状态提示语-->
		</li>
	  </ul>
	  
	  <ul>
		<li>
		  <p class="text"><font class="redfont">*</font>密码：</p>
		  <p class="input">
		    <input name="lvAccount.pwd" id="rpwd"  type="password" class="input02" maxlength="16"
		    onfocus="textFocus('pwd')" onblur="validatePwd();" value=""/></p>
		</li>
		<li class="prompt">
		  <p class="pt" style="display:none;" id="pwd_tip1">6-16位字符，可使用字母、数字或符号的组合，不建议使用纯字母、纯数字、纯符号！</p>
		  <!--输入框获得焦点状态提示语-->
		  <p class="er" style="display:none;" id="pwd_tip2"></p>
		  <!--错误状态提示语-->
		</li>
	  </ul>
	  
	  <ul>
		<li>
		  <p class="text"><font class="redfont">*</font>确认密码：</p>
		  <p class="input">
		    <input name="truePwd" id="truePwd"  type="password" class="input02" maxlength="16"
		    onfocus="textFocus('tpwd')" onblur="validateTruePwd();" value=""/></p>
		</li>
		<li class="prompt">
		  <p class="pt" style="display:none;" id="tpwd_tip1">请再次输入密码！</p>
		  <!--输入框获得焦点状态提示语-->
		  <p class="er" style="display:none;" id="tpwd_tip2">两次密码输入不一致！</p>
		  <!--错误状态提示语-->
		</li>
	  </ul>
	  
	  
	  <ul>
		<li>
		  <p class="text"><font class="redfont">*</font>验证码：</p>
		  <p><input name="code" id="rcode"  type="text" class="input22" maxlength="4" onfocus="textFocus('code')"
		  style="width: 40px" onblur="validateCode();"/></p>
		  <p><img src="/web/imager.jsp" width="47" height="21" id="rcId" style="cursor:pointer;"
		  onclick="javascript:this.src=this.src+'?'+new Date()"/></p>
		  <p><font class="kbqc">看不清楚？</font><a href="javascript:void(0);" 
		  onclick="javascript:document.getElementById('rcId').src=document.getElementById('rcId').src+'?'+new Date()"class="ljh">换一张</a></p>
		</li>
		<li class="prompt" >
		  <p class="pt" style="display:none;" id="code_tip1">请输入验证码！</p>
		  <!--输入框获得焦点状态提示语-->
		  <p class="er" style="display:none;" id="code_tip2">验证码错误！</p>
		  <!--错误状态提示语-->
		</li>
	  </ul>
	  
	  <ul class="btn">
		<li><input type="submit" value="注册" class="user_reg_bt" id="registerbut"/></li>
	  </ul>
		  				</form>
		  				<div id="c_regist_tip" class="add_tips"></div>
		 			</div>
		 			<div class="clear"></div>
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
		<%@include file="/web/bmcn/common/footer.jsp" %>
		<script type="text/javascript">
		$.post('/web/activity!getRegistActivity.action', function(data){
			$('#c_regist_tip').append(data);
		});
		</script>
	</body>
</html>