<%@ page language="java" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta content="width=device-width,user-scalable=no" name="viewport">
<title>banana商城_找回密码</title>
<!-- 加载公共JS -->
<%@include file="/web/mbmcn/common/top.jsp" %>
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
        <h1>找回密码</h1>
       
      </div>
   </div>
</header>

<article>
  <table width="94%" border="0" align="center" style="margin-top:20px">
   <tr>
    <td height="80" align="left"></td>
  </tr>
  <tr>
    <td height="80" align="left"  class="fonsi" style="text-align:center">你的找回密码申请已经成功提交，<br>请在<span style="color:#ff4d03">24小时</span>内登录你的邮箱${param.email}进行密码修改</td>
  </tr>
  
   <tr>
    <td height="80" align="left"></td>
  </tr>
  
  
  
  <tr>
    <td height="80"><input onclick="javascript:location.href='/index.html'" type="button" value="返 回"  class="return"/></td>
    </tr>
    
      <tr>
    <td height="80" align="left"></td>
  </tr>
</table>
  
</article>

<!-- 分享 -->
<%@include file="/web/mbmcn/common/share.jsp" %>

<!-- footer -->
<%@include file="/web/mbmcn/common/footer.jsp" %>

</body>
</html>
	