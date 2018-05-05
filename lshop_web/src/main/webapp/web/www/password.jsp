<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib prefix="app" uri="/WEB-INF/tld/gv-app.tld"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>找回密碼_TVpad商城</title>
<!-- 加载公共JS -->
<%@include file="/web/www/common/top.jsp"%>
<link href="${resDomain}/www/res/css/user_center.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${resDomain}/www/res/js/myjs.js"></script>
<script type="text/javascript" src="${resDomain}/www/res/js/page/password.js"></script>
</head>
<%String erromsg = (String)request.getAttribute("erromsg");
String msg = "";
if(null != erromsg){
	if(erromsg.equals("0")){
		msg = "賬號未激活！";
	}
	if(erromsg.equals("-1")){
		msg = "賬號不存在！";
	}
	if(erromsg.equals("-2")){
		msg = "驗證碼錯誤！";
	}
}
%>
<body>
<!-- 获取商城头部文件 -->
<%@include file="/web/www/common/header.jsp"%>
<div  class="xlhk_main">
  <h3>找回密碼</h3>
  <div class="password_bz"></div>
  <ul>
    <form action="/web/userCenterManage!password.action" method="post" onsubmit="return subForm();">
      <li>
        <p class="wd3">請輸入您的E-mail：</p>
        <p><input type="text" class="input02" name="lvAccount.email" id="emailId"/></p>
      </li>
      <li>
        <p class="wd3">驗證碼：</p>
        <p>
          <input name="code" id="rcode"  type="text" class="input22" style="width: 40px" maxlength="4"/>
          <img src="/web/imager.jsp" width="47" height="21" id="rcId" style="cursor:pointer;" onclick="javascript:this.src=this.src+'?'+new Date()"/> 看不清楚？
          <a href="javascript:void(0);" class="ljh" onclick="javascript:document.getElementById('rcId').src=document.getElementById('rcId').src+'?'+new Date()">換一張</a>
        </p>
      </li>
      <li>
        <p class="btn_password"><font id="remindbox" class="redfont"><%=msg %></font></p>
      </li>
      <li>
        <p class="btn_password">
          <input type="submit" value="下一步"  class="user_center_bt" />
        </p>
      </li>
      <div class="cb"></div>
    </form>
  </ul>
</div>
<!-- footer -->
<%@include file="/web/www/common/footer.jsp" %>
</body>
</html>
