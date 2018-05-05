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
<script type="text/javascript" src="${resDomain}/en/res/js/myjs.js"></script>
<script type="text/javascript" src="${resDomain}/en/res/js/info.js"></script>
</head>
<%Integer statu = (Integer)session.getAttribute("statu");
session.removeAttribute("statu");
String msg = "";
if(null != statu){
	if(statu == 0){
		msg = "Operation failed!";
	}
	if(statu == 1){
		msg = "Successfully changed!";
	}
	if(statu == -1){
		msg = "Operation failed,the nickname is already existed!";
	}
}
%>	
<body>
<!-- 获取商城头部文件 -->
<%@include file="/web/en/common/header.jsp" %>
<div class="content_main">
  <!-- left_frame -->
  <%@include file="/web/en/user_center/leftFrame.jsp" %>
  <div class="right_frame">
    <h1><span class="s_r"><font class="bfont"><img src="${resDomain}/en/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">Home</a>--><a href="/web/userCenterManage!getAccount.action">User Center </a>--></font> My Profile</span></h1>
    <form action="/web/userCenterManage!editInfo.action" method="post" id="myform">
      <input type="hidden" name="lvAccountInfo.id" value="${lvAccountInfo.id}"/>
      <input type="hidden" name="lvAccountInfo.userCode" value="${lvAccountInfo.userCode}"/>
      <input type="hidden" name="lvAccountInfo.lastTime" value="${lvAccountInfo.lastTime}"/>
      <input type="hidden" name="lvAccountInfo.storeId" value="${lvAccountInfo.storeId}"/>
      <input type="hidden" name="lvAccountInfo.code" value="${lvAccountInfo.code}"/>
      <input type="hidden" name="lvAccountInfo.createTime" value="${lvAccountInfo.createTime}"/>
      <input type="hidden" name="ordnickname" id="ordnickname" value="${lvAccount.nickname}"/>
      <div class="usercenter_box">
        <ul>
          <li class="wd1">&nbsp;</li>
          <li class="wd2"><font id="msgbox" color="red" ><%=msg %></font></li>
        </ul>
        <ul>
          <li class="wd1">User ID：</li>
          <li class="wd2">${lvAccount.email }</li>
          <li class="prompt"></li>
        </ul>
        <ul>
          <li class="wd1"><font class="redfont">*</font> Nickname：</li>
          <li class="wd2">
            <c:if test="${not empty lvAccount.nickname}">
				<input type="hidden" name="lvAccount.nickname" class="input4" value="${lvAccount.nickname}"/>
				${lvAccount.nickname }
			</c:if>
			<c:if test="${empty lvAccount.nickname}">
				<input type="text" name="lvAccount.nickname" class="input4" value="${lvAccount.nickname}" maxlength="32"/>
			</c:if>
          </li>
          <li class="prompt"></li>
        </ul>
        <ul>
          <li class="wd1">Gender：</li>
          <li class="wd2">
            <input name="lvAccountInfo.sex" type="radio" value="0" <s:if test="lvAccountInfo.sex==0 || lvAccountInfo.sex==null">checked="checked"</s:if> />Male 
            <input name="lvAccountInfo.sex" type="radio" value="1" <s:if test="lvAccountInfo.sex==1">checked="checked"</s:if> />Female 
            <input name="lvAccountInfo.sex" type="radio" value="2" <s:if test="lvAccountInfo.sex==2">checked="checked"</s:if> />Private
          </li>
          <li class="prompt"></li>
        </ul>
        <ul>
          <li class="wd1">QQ：</li>
          <li class="wd2">
            <input  type="text" name="lvAccountInfo.qq" id="qq" value="${lvAccountInfo.qq}" onkeyup="js.onlyNumber(this, '只能输入数字')" maxlength="20" class="input2" />
          </li>
          <li class="prompt"></li>
        </ul>
        <ul>
          <li class="wd1">MSN：</li>
          <li class="wd2">
            <input name="lvAccountInfo.msn" value="${lvAccountInfo.msn}" id="msn" maxlength="32" class="input2" type="text" />
          </li>
          <li class="prompt"></li>
        </ul>
        <ul>
          <li class="wd1"><font class="redfont"> *</font> Real Name：</li>
          <li class="wd2">
            <input name="lvAccountInfo.name" id="realName" value="${lvAccountInfo.name}" maxlength="32" class="input2"  type="text" />
          </li>
          <li class="prompt"></li>
        </ul>
        <ul>
          <li class="wd1">Tel：</li>
          <li class="wd2">
            <input name="lvAccountInfo.tel" value="${lvAccountInfo.tel}" id="tel" maxlength="20" class="input2"  type="text" />
          </li>
          <li class="prompt"></li>
        </ul>
        <ul>
          <li class="wd1">Mobile：</li>
          <li class="wd2">
            <input name="lvAccountInfo.mobile" value="${lvAccountInfo.mobile }" id="mobile" onkeyup="js.onlyNumber(this, '只能输入数字')" maxlength="32" type="text" class="input2" />
          </li>
          <li class="prompt"></li>
        </ul>
        <ul>
          <li class="wd1">Address：</li>
          <li class="wd2">
            <input  name="lvAccountInfo.address" id="adress" type="text" class="input2" value="${lvAccountInfo.address }" dvalue="Street address" maxlength="200"/> -
            <input  name="lvAccountInfo.cityName" id="cityName" type="text" class="input3" value="${lvAccountInfo.cityName }" dvalue="County/City" maxlength="50" /> -
            <input  name="lvAccountInfo.provinceName" id="provinceName" type="text" class="input3" value="${lvAccountInfo.provinceName }" dvalue="State/Province" maxlength="50"/> -
            <select name="lvAccountInfo.contryName" id="contryId" class="input2">
              <option value="">--Choose your country--</option>
              <c:foreach items="${contryList}" var="c">
              	<option value="${c.code}" <c:if test="${c.code==lvAccountInfo.contryName}">selected</c:if>>${c.nameen}</option>
              </c:foreach>
            </select>
          </li>
          <li class="prompt"></li>
        </ul>
        <ul>
          <li class="wd1"><font class="redfont"> *</font> Zip：</li>
          <li class="wd2">
            <input name="lvAccountInfo.postCode" id="postcodeId" type="text" value="${lvAccountInfo.postCode}" maxlength="16" class="input2" />
          </li>
          <li class="prompt"></li>
        </ul>
        <ul class="btn">
          <li class="wd1">&nbsp;</li>
          <li class="wd2">
            <input type="submit"  value="Save Changes" class="user_center_bt" />
          </li>
        </ul>
      </div>
    </form>
  </div>
  <!--End right_Frame-->
</div>
<!--End content-->
<!-- 底部广告位-->
<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
<!-- footer-->
<%@include file="/web/en/common/footer.jsp" %>
</body>
</html>
