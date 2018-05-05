<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>TVpad商城</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- 加载公共JS -->
<%@include file="/web/www/common/top.jsp" %>
<script type="text/javascript" src="${resDomain}/www/res/js/myjs.js"></script>
<script type="text/javascript" src="${resDomain}/www/res/js/page/noLoginInfo.js"></script>
</head>
<body>
<!-- 获取商城头部文件 -->
<%@include file="/web/www/common/header.jsp" %>
<div class="sc_main">
<div class="sc_main">
  <div class="sc_main_left">
    <div class="sc_product">
      <h2 class="bt3">
        <p class="home"><img src="${resDomain}/www/res/images/icon02.gif" /></p>
        <p><a href="/index.html">首頁</a> > 用戶登錄</span> </p>
      </h2>
       <div class="box_login">
      <div class="login">
        <form action="${storeDomain}/web/userCenterManage!login.action" id="login"
        method="post" onsubmit="">
          <input type="hidden" name="loginstyle" value="1"/>
          <input type="hidden" name="jumpurl" value="${param.jumpurl }"/>
          <ul>
            <li>
              <p class="text"></p>
              <p class="input"><font class="redfont" id="msg1"></font></p>
            </li>
          </ul>
          <ul>
            <li>
              <p class="text"><font class="redfont">*</font>帳號：</p>
              <p class="input"><input name="uname" id="loginname" class="input02 w_input02" type="text"  value="${uname}" dvalue="Email或者暱稱" onblur="checkEmail();" /></p>
              <p><a href="${storeDomain}/web/userCenterManage!toRegister.action?jumpurl=${param.jumpurl }" class="ljl">註冊</a></p>
            </li>
            <li id="remind_loginname" class="prompt"></li>
          </ul>
          <ul>
            <li>
              <p class="text"><font class="redfont">*</font>密碼：</p>
              <p class="input"><input name="pwd" id="loginpwd" class="input02 w_input02" type="password" onblur="checkPassword();" /></p>
              <p><a href="${storeDomain}/web/userCenterManage!toFindPassword.action" class="ljh">忘記密碼？</a></p>
            </li>
            <li id="remind_loginpwd" class="prompt"></li>
          </ul>
          <ul>
            <li>
              <p class="text"><font class="redfont">*</font>驗證碼：</p>
              <p><input name="code" id="logincode" class="input22" type="text" onblur="checkVcode();" /></p>
              <p><img src="/web/imager.jsp" width="47" height="21" id="rcId" onclick="javascript:this.src=this.src+'?'+new Date()"/></p>
              <p><font class="kbqc">看不清楚？</font><a href="javascript:void(0);" onclick="javascript:document.getElementById('rcId').src=document.getElementById('rcId').src+'?'+new Date()" class="ljh">換一張</a></p>
            </li>
            <li id="remind_logincode" class="prompt"></li>
          </ul>
          <ul class="btn">
            <li>
              <input type="submit" value="登錄" class="user_center_bt" />
            </li>
          </ul>
        </form>
      </div>
      
       <div class="hezou_zh">
          <h3>合作网站账号登陆</h3>
          <div class="hz_tubao">
            <ul>
              <li><a href="/web/threeauth!qq.action"><img src="${resDomain}/www/res/images/QQ.png" alt="QQ登陆"/> QQ</a></li>
           
              <li><a href="/web/threeauth!facebook.action"><img src="${resDomain}/www/res/images/login_facebook.jpg" alt="Facebook"/> Facebook</a></li>              
              <%--
              
              <li><a href="/web/threeauth!twitter.action"><img src="${resDomain}/www/res/images/login_twitter.jpg" alt="Twitter"/> Twitter</a></li>
              <li><a href="#"><img src="${resDomain}/www/res/images/wx.png" alt="微信登陆"/> 微信</a></li>
              <li><a href="#"><img src="${resDomain}/www/res/images/zfb.png" alt="支付宝"/> 支付宝</a></li>
               --%>
              <div class="clear"></div>
            </ul>
          </div>
        </div>
        <div class="clear"></div>
      </div>
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
<%@include file="/web/www/common/footer.jsp"%>
</body>
</html>
