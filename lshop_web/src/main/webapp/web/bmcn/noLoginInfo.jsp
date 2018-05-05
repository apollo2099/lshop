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
		<script type="text/javascript" src="${resDomain}/bmcn/res/js/storeIndex.js"></script>
	</head>
<%
String uname="";
Object ouname = request.getAttribute("uname");
if(null == ouname){
	uname = "请输入Email或者昵称";
}
%>	
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/bmcn/common/header.jsp" %>
		<div class="sc_main">
		  	<div class="sc_main_left">
		    	<div class="sc_product">
			  		<h2 class="bt3">
			     		<p class="home"><img src="${resDomain}/bmcn/res/images/icon02.gif" /></p><p><a href="/index.html">首页</a> > 用户登录</span> </p>
			  		</h2>
			  		<div class="box_login">
			  		<div class="login">
			  			<form id="login"  action="${storeDomain}/web/userCenterManage!login.action" method="post" onsubmit="">
						  <input type="hidden" name="loginstyle" value="1"/>
						  <input type="hidden" name="jumpurl" value="${param.jumpurl }"/>
						  <ul>
								<li><p style="margin-left: 100px;">
								<font class="redfont" id="msg1">请先登录，如果没有账号，请先注册！</font>
							    </p>
								</li>
						  </ul>
						  
						  <ul>
							<li>
							  <p class="text"><font class="redfont">*</font>账号：</p>
							  <p class="input">
							    <input name="uname" id="name"  type="text" class="input02" maxlength="60"
							    onfocus="textFocus('name');" onblur="validateLogAccount();" value="<%=uname%>"  dvalue="<%=uname%>"/></p>
							  <p><a href="${storeDomain}/web/userCenterManage!toRegister.action?jumpurl=${param.jumpurl }" class="ljl">注册</a></p>
							</li>
							<li class="prompt">
							  <p class="pt" style="display:none;" id="name_tip1">请输入您的Email地址或昵称</p>
							  <!--输入框获得焦点状态提示语-->
							  <p class="er" style="display:none;" id="name_tip2"></p>
							  <!--错误状态提示语-->
							</li>
						  </ul>
						  
						  <ul>
							<li>
							  <p class="text"><font class="redfont">*</font>密码：</p>
							  <p class="input">
							    <input name="pwd" id="log_pwd"  type="password" class="input02" maxlength="16"
							    onfocus="textFocus('pwd');" onblur="validateLogPwd();"/></p>
							  <p><a href="${storeDomain}/web/userCenterManage!toFindPassword.action" class="ljh">忘记密码？</a></p>
							</li>
							<li class="prompt">
							  <p class="pt" style="display:none;" id="pwd_tip1">请输入您的密码</p>
							  <!--输入框获得焦点状态提示语-->
							  <p class="er" style="display:none;" id="pwd_tip2" ></p>
							  <!--错误状态提示语-->
							</li>
						  </ul>
						  
						  
						  <ul>
							<li>
							  <p class="text"><font class="redfont">*</font>验证码：</p>
							  <p><input name="code" id="rcode" onfocus="textFocus('code');" onblur="validateLogCode();"
							  type="text" class="input22" style="width: 40px" maxlength="4"/></p>
							  <p><img src="/web/imager.jsp" width="47" height="21" id="rcId" style="cursor:pointer;"
							  onclick="javascript:this.src=this.src+'?'+new Date()"/></p>
							  <p><font class="kbqc">看不清楚？</font>
							  <a href="javascript:void(0);" class="ljh" 
							  onclick="javascript:document.getElementById('rcId').src=document.getElementById('rcId').src+'?'+new Date()">换一张</a></p>
							</li>
							<li class="prompt">
							  <p class="pt" style="display:none;" id="code_tip1">请输入验证码！</p>
							  <!--输入框获得焦点状态提示语-->
							  <p class="er" style="display:none;" id="code_tip2"></p>
							  <!--错误状态提示语-->
							</li>
						  </ul>
						 
						  <ul class="btn">
							<li><input type="submit" value="登录" class="user_center_bt"/></li>
						  </ul>
						  </form>
						  </div>
		<div class="hezou_zh">
          <h3>合作网站账号登陆</h3>
          <div class="hz_tubao">
            <ul>
              <li><a href="/web/threeauth!qq.action"><img src="${resDomain}/bmcn/res/images/QQ.png" alt="QQ登陆"/> QQ</a></li>
              <li><a href="/web/threeauth!weibo.action"><img src="${resDomain}/bmcn/res/images/xl.png" alt="微博登陆"/> 微博</a></li>
              <!--  
              <li><a href="#"><img src="${resDomain}/bmcn/res/images/wx.png" alt="微信登陆"/> 微信</a></li>
              <li><a href="#"><img src="${resDomain}/bmcn/res/images/zfb.png" alt="支付宝"/> 支付宝</a></li>
              -->
              <div class="clear"></div>
            </ul>
          </div>
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
	
	</body>
</html>
