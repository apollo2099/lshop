<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta content="width=device-width,user-scalable=no" name="viewport">
<title>TVpad商城_修改密码</title>
<!-- 加载公共JS -->
<%@include file="/web/mtmcn/common/top.jsp" %>
<script type="text/javascript" src="${resDomain}/mtmcn/res/js/checkForm.js"></script>
<script type="text/javascript" src="${resDomain}/mtmcn/res/js/findPass.js"></script>		
</head>	
<%
String account = request.getParameter("account"); 
String code = request.getParameter("code");
String lang = request.getParameter("lang");
%>	
		
<body>
<header>
   <div class="top">
      <div class="shopping">
        <div class="shoplebg1">
            <div class="shopicon1"><a href="/"></a></div>
         </div>
      </div>
      <div class="title">
        <h1>修改密码</h1>
      </div>
   </div>
</header>
		
<article>
  <form action="/web/userCenterManage!reSetPwd.action" method="post" onsubmit="return submitResetPwdValidate();">
  <input type="hidden" name="account" value="<%=account%>"/>
  <input type="hidden" name="code" value="<%=code%>"/>
  <input type="hidden" name="lang" value="<%=lang%>"/>
  <table width="94%" border="0" align="center" style="margin-top:20px">
  <tr>
    <td width="32%" height="80" align="right"  class="fonsi">新密码：</td>
     <td width="68%" height="80" colspan="2">
       <div  class="inputd">
         <input type="text"  class="inpu" value="" name="lvAccount.pwd" 
         	defalt="" id="rpwd" maxlength="16" onblur="validatePwd();"/>
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
    <td height="80" align="right"  class="fonsi">确认密码：</td>
     <td height="80" colspan="2">
       <div  class="inputd">
         <input type="text"  class="inpu" value="" name="newPwd" id="truePwd" maxlength="16" onblur="validateTruePwd();"/>
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
    <td height="125" colspan="3"><input type="submit" value="保存修改"  class="logins"/></td>
    </tr>
</table>
</form>
  
</article>		
		
<!-- 分享 -->
<%@include file="/web/mtmcn/common/share.jsp" %>

<!-- footer -->
<%@include file="/web/mtmcn/common/footer.jsp" %>

</body>
</html>
