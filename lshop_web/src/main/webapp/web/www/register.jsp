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
<script type="text/javascript" src="${resDomain}/www/res/js/page/register.js"></script>
</head>
<body>
<!-- 获取商城头部文件 -->
<%@include file="/web/www/common/header.jsp" %>
<div class="sc_main">
  <div class="sc_main_left">
    <div class="sc_product">
      <h2 class="bt3">
        <p class="home"><img src="${resDomain}/www/res/images/icon02.gif" /></p>
        <p><a href="/index.html">首頁</a> > 用戶註冊</span> </p>
      </h2>
       <div class="login_tips"><img src="${resDomain}/www/res/images/pos_icon.gif" width="19" height="19" /> 註冊TVpad官網帳號<br />&nbsp;&nbsp;&nbsp;&nbsp;
TVpad官方帳號能使用TVpad官方商城，TVpad 機頂盒用戶服務。
如果您已擁有TVpad帳號，則可<a href="/web/www/noLoginInfo.jsp?jumpurl=http://www.mtvpad.com/index.html">点此登陸</a></div>
      <div class="login">
        <form action="${storeDomain}/web/regeditAccount.action" id="regedit" method="post" onsubmit="">
        <input type="hidden" name="jumpurl" value="${param.jumpurl }"/>
        <ul>
          <li id="erro_msg0" style="display: none;">
            <p class="text"></p>
            <p class="input"><span class="redfont" id="msgg"></span></p>
          </li>
        </ul>
        <ul>
          <li>
            <p class="text"><font class="redfont">*</font>賬號：</p>
            <p class="input"><input name="lvAccount.email" id="email" value="${lvUser.email}" dvalue="請輸入您的Email地址!" onblur="checkEmail();" class="input02" type="text" /></p>
          </li>
          <li id="remind_email" class="prompt"></li>
        </ul>
        <ul>
          <li>
            <p class="text"><font class="redfont">*</font>密碼：</p>
            <p class="input"><input name="lvAccount.pwd" id="pwd" onblur="checkPassword();" class="input02" type="password" /></p>
          </li>
          <li id="remind_pwd" class="prompt"></li>
        </ul>
        <ul>
          <li>
            <p class="text"><font class="redfont">*</font>確認密碼：</p>
            <p class="input"><input name="truePwd" onblur="checkTruePassword();" id="truePwd" class="input02" type="password" /></p>
          </li>
          <li id="remind_truePwd" class="prompt"></li>
        </ul>
        <ul>
          <li>
            <p class="text"><font class="redfont">*</font>驗證碼：</p>
            <p><input name="code" id="vcode" onblur="checkVcode();" class="input22" type="text" /></p>
            <p><img src="/web/imager.jsp" width="47" height="21" id="rcId" onclick="javascript:this.src=this.src+'?'+new Date()"/></p>
            <p><font class="kbqc">看不清楚？</font><a href="javascript:void(0);" onclick="javascript:document.getElementById('rcId').src=document.getElementById('rcId').src+'?'+new Date()" class="ljh">換一張</a></p>
          </li>
          <li id="remind_vcode" class="prompt"></li>
        </ul>
        <ul class="btn">
          <li>
           <input type="submit" value="註冊" class="user_reg_bt" id="registerbut"/>
          </li>
        </ul>
        </form>
      </div><!-- end login -->
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
<%@include file="/web/www/common/footer.jsp" %>
<script type="text/javascript">
$.post('/web/activity!getRegistActivity.action', function(data){
	$('#c_regist_tip').append(data);
});
</script>
</body>
</html>
