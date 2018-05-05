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
		<script type="text/javascript" src="${resDomain}/en/res/js/page/register.js"></script>
	</head>
	
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/en/common/header.jsp" %>
		<div class="sc_main">
		<div class="sc_main_left">
		    <div class="sc_product">
			  <h2 class="bt3">
			    <p class="home"><img src="${resDomain}/en/res/images/icon02.gif" /></p><p><a href="/index.html">Home</a> > User Register</p>
			  </h2>
			  
			        <div class="login_tips"><img src="${resDomain}/en/res/images/pos_icon.gif" width="19" height="19" /> Register account on TVpad Mall <br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;The accounts you register on TVpad Mall and TVpad device are bound with each other, and both of the accounts can &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;equally enjoy our related services.  <br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;If you already have a TVpad account, <a href="/web/en/noLoginInfo.jsp?jumpurl=http://en.mtvpad.com/index.html">please log in here</a></div>
			  <div class="login">
			  <form action="${storeDomain}/web/regeditAccount.action" method="post" id="regedit"
			  onsubmit="">
			  <input type="hidden" name="jumpurl" value="${param.jumpurl }"/>
			  	<ul>
		          <li id="erro_msg0" style="display: none;">
		            <p class="text"></p>
		            <p class="input"><span class="redfont" id="msgg"></span></p>
		          </li>
		        </ul>
			   <ul>
				<li>
				  <p class="text"><font class="redfont">*</font>Account ：</p>
				  <p class="input"><input name="lvAccount.email" id="email"  onblur="checkEmail()" value="${lvUser.email}" dvalue="Please enter your Email Address!" class="input02" type="text" /></p>
				</li>
				<li id="remind_email" class="prompt"></li>
			  </ul>
			  <ul>
				<li>
				  <p class="text"><font class="redfont">*</font>Password ：</p>
				  <p class="input"><input name="lvAccount.pwd" id="pwd" onblur="checkPassword()" class="input02" type="password" /></p>
				</li>
				<li id="remind_pwd" class="prompt"></li>
			  </ul>
			  <ul>
				<li>
				  <p class="text"><font class="redfont">*</font>Confirm password：</p>
				  <p class="input"><input name="truePwd" id="truePwd" onblur="checkTruePassword()" class="input02" type="password" /></p>
				</li>
				<li id="remind_truePwd" class="prompt"></li>
			  </ul>
			  <ul>
				<li>
				  <p class="text"><font class="redfont">*</font>Captcha ：</p>
				  <p><input name="code" id="vcode" onblur="checkVcode()" class="input22" type="text"/></p>
				  <p><img src="/web/imager.jsp" width="47" height="21" id="rcId" onclick="javascript:this.src=this.src+'?'+new Date()"/></p>
				  <p><font class="kbqc">Unclear? </font><a href="#" class="ljh" onclick="javascript:document.getElementById('rcId').src=document.getElementById('rcId').src+'?'+new Date()">Refresh.</a></p>
				</li>
				<li id="remind_vcode" class="prompt"></li>
			  </ul>
			  <ul>
				<li><p class="text">&nbsp;</p>
				           <input type="submit" value="Register" class="user_reg_bt" id="registerbut"/>
				</li>
		      </ul>
		     </form>
			 </div><!-- end of login -->
		     <div id="c_regist_tip" class="add_tips"></div>
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
		<script type="text/javascript">
		$.post('/web/activity!getRegistActivity.action', function(data){
			$('#c_regist_tip').append(data);
		});
		</script>
	</body>
</html>