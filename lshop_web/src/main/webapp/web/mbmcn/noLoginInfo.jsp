<%@ page language="java" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta content="width=device-width,user-scalable=no" name="viewport">
<title>banana商城_登录</title>
<!-- 加载公共JS -->
<%@include file="/web/mbmcn/common/top.jsp" %>
<script type="text/javascript" src="${resDomain}/mbmcn/res/js/checkForm.js"></script>
<script type="text/javascript" src="${resDomain}/mbmcn/res/js/login.js"></script>
</head>

<body>
<header>
   <div class="top">
      
      <div class="shopping">
        <div class="shoplebg1">
            <div class="shopicon1"><a href="/"></a></div>
         </div>
      </div>
      <div class="title">
        <h1>登录</h1>
       
      </div>
   </div>
</header>

<article style="z-index:0">
  <form id="loginPage"  action="${storeDomain}/web/userCenterManage!login.action" method="post">
  <input type="hidden" name="jumpurl" value="${param.jumpurl }"/>
  <table width="94%" border="0" align="center" style="margin-top:20px;">
  <tr>
    <td width="20%" height="80" align="left" class="fonsi">账 号：</td>
    <td height="80" colspan="2">
     <div  class="inputd">
       <input type="text"  class="inpu" value="请输入Email地址或者昵称" name="uname" defalt="请输入Email地址或者昵称"/>
        <div class="tip">
          <em></em>
          <span class="errInfo"></span>
          <i></i>
          <b></b>
         </div>
    </div>
    </td>
  </tr>
  <tr>
    <td height="80" align="left" class="fonsi">密 码：</td>
     <td height="80" colspan="2">
       <div  class="inputd">
         <input type="password"  class="inpu" value="" name="pwd" />
         <div class="tip">
          <em></em>
          <span class="errInfo"></span>
          <i></i>
          <b></b>
         </div>
       </div>
       <font id="msgg" color="red"></font>
    </td>
  </tr>
  <tr>
    <td height="80">&nbsp;</td>
    <td width="32%" height="80" class="md"><input type="checkbox" name="checkbox" id="checkbox" style="vertical-align:middle">
      两周内免登</td>
    <td width="48%" align="right" class="asty"><a href="${storeDomain}/web/userCenterManage!toRegister.action?jumpurl=${param.jumpurl }">立即注册</a> ｜ <a href="${storeDomain}/web/userCenterManage!toFindPassword.action">忘记密码？</a></td>
  </tr>
  <tr>
    <td height="80" colspan="3"><input type="button" onclick="submitLogin()"  class="logins" value="登 录"/></td>
    </tr>
</table>
</form>

  
</article>



<!-- 分享 -->
<%@include file="/web/mbmcn/common/share.jsp" %>

<!-- footer -->
<%@include file="/web/mbmcn/common/footer.jsp" %>

</body>
</html>

