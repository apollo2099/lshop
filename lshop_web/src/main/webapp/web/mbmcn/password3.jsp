<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta content="width=device-width,user-scalable=no" name="viewport">
<title>banana商城_修改密码</title>
<!-- 加载公共JS -->
<%@include file="/web/mbmcn/common/top.jsp" %>
		
</head>	
<%String flag = request.getParameter("flag");
String url = "";
if(null != flag && !flag.equals("")){url = "/web/" + flag + "/password.jsp";}
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
	<table width="94%" border="0" align="center" style="margin-top:20px">
		<tr>
		<td height="80" align="left"></td>
		</tr>
		<tr>
		<td height="80" align="left"  class="fonsi" style="text-align:center">密码修改成功，请使用新密码登录</td>
		</tr>
		<tr>
		<td height="80"><input type="submit" 
			onclick="javascript:window.location.href='<%=url%>';"value="返回"  class="return"/></td>
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
