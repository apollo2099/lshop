<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>User Center _TVpad Mall</title>
<link href="${resDomain}/en/res/css/user_center.css" rel="stylesheet" type="text/css" />
<!-- 加载公共JS -->
<%@include file="/web/en/common/top.jsp" %>
<script type="text/javascript" src="${resDomain}/en/res/js/userCenter.js"></script>
</head>
<body>
<!-- 获取商城头部文件 -->
<%@include file="/web/en/common/header.jsp" %>
<div class="content_main">
  <!-- left_frame -->
  <%@include file="/web/en/user_center/leftFrame.jsp"%>
  <div class="right_frame">
    <h1>
	    <span class="s_r"><font class="bfont"><img src="${resDomain}/en/res/images/icon02.gif" width="15" height="15" />
	    <a href="/index.html">Home</a>--><a href="/web/userCenterManage!getAccount.action">User Center </a>--></font> Password</span>
    </h1>
    <div class="usercenter_box">
      <form action="/web/userCenterManage!updatePassword.action" method="post" id="passForm">
        <ul>
          <li class="password">
            <p class="text"></p>
            <p><font color="red">
           	 <c:if test="${not empty mFlag}">
		        <c:if test="${mFlag==1}">The old password you entered is incorrect!</c:if>
		        <c:if test="${mFlag==2}">Successfully changed!</c:if>
		      </c:if>
            </font></p>
          </li>
        </ul>
        <ul>
          <li class="password">
            <p class="text"><font class="redfont"> *</font> Current password ：</p>
            <p>
              <input name="pwd" id="pwd" class="input2" type="password" />
            </p>
          </li>
          <li class="prompt"></li>
        </ul>
        <ul>
          <li class="password">
            <p class="text"><font class="redfont"> *</font> New password ：</p>
            <p>
              <input name="newPwd" id="newPwd" class="input2" type="password" />
            </p>
          </li>
          <li class="prompt"></li>
        </ul>
        <ul>
          <li class="password">
            <p class="text"><font class="redfont"> *</font>Confirm new password：</p>
            <p>
              <input name="truePwd" id="truePwd" class="input2" type="password" />
            </p>
          </li>
          <li class="prompt"></li>
        </ul>
        <ul class="btn">
          <li class="wd1">&nbsp;</li>
          <li class="wd2">
            <input type="submit" value="Save Changes" class="user_center_bt" />
          </li>
        </ul>
      </form>
    </div>
  </div>
  <!--End right_Frame-->
</div>
<!--End content-->
<!-- 底部广告位-->
<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
<!-- footer-->
<%@include file="/web/en/common/footer.jsp"%>
</body>
</html>
