<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib prefix="app" uri="/WEB-INF/tld/gv-app.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Retrieve Password_TVpad Mall</title>
<!-- 加载公共JS -->
<%@include file="/web/en/common/top.jsp" %>
<link href="${resDomain}/en/res/css/user_center.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${resDomain}/en/res/js/myjs.js"></script>
<script type="text/javascript" src="${resDomain}/en/res/js/page/password.js"></script>
</head>

<%String erromsg = (String)request.getAttribute("erromsg");
String msg = "";
if(null != erromsg){
	if(erromsg.equals("0")){
		msg = "Account is not activated";
	}
	if(erromsg.equals("-1")){
		msg = "Account does not exist";
	}
	if(erromsg.equals("-2")){
		msg = "Verification code is incorrect";
	}
}
%>

<body>
<!-- 获取商城头部文件 -->
<%@include file="/web/en/common/header.jsp" %>

<div  class="xlhk_main">
  <h3>Retrieve Password</h3>
  <div class="password_bz"></div>
  <ul>
    <form action="/web/userCenterManage!password.action" method="post" onsubmit="return subForm();">
      <li>
        <p class="wd3">Your email address : </p>
        <p><input type="text" class="input02" name="lvAccount.email" id="emailId"/></p>
      </li>
      <li>
        <p class="wd3">CAPTCHA：</p>
        <p>
          <input name="code" id="rcode"  type="text" class="input4" style="width: 40px" maxlength="4"/>
          <img src="/web/imager.jsp" width="47" height="21" id="rcId" style="cursor:pointer;" onclick="javascript:this.src=this.src+'?'+new Date()"/>
          Unclear? <a href="#" onclick="javascript:document.getElementById('rcId').src=document.getElementById('rcId').src+'?'+new Date()">Refresh </a>
        </p>
      </li>
      <li>
        <p class="btn_password">
        	<font id="remindbox" class="redfont"><c:if test="${erromsg != null }"><%=msg %></c:if></font>
        </p>
      </li>
      <li>
        <p class="btn_password">
          <input name="" type="submit" value="Next"  class="user_center_bt" />
        </p>
      </li>
      <div class="cb"></div>
    </form>
  </ul>
</div>
<!-- footer -->
<%@include file="/web/en/common/footer.jsp" %>
</body>
</html>
