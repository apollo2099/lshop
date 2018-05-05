<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用戶中心_TVpad商城</title>
<link href="${resDomain}/www/res/css/user_center.css" rel="stylesheet" type="text/css" />
<!-- 加载公共JS -->
<%@include file="/web/www/common/top.jsp"%>
<script type="text/javascript" src="${resDomain}/www/res/js/myjs.js"></script>
<script type="text/javascript" src="${resDomain}/www/res/js/info.js"></script>
</head>
<%Integer statu = (Integer)session.getAttribute("statu");
session.removeAttribute("statu");
String msg = "";
if(null != statu){
	if(statu == 0){
		msg = "修改失败！";
	}
	if(statu == 1){
		msg = "修改成功！";
	}
	if(statu == -1){
		msg = "修改失败，该昵称已存在！";
	}
}
%>	
<body>
<!-- 获取商城头部文件 -->
<%@include file="/web/www/common/header.jsp"%>
<div class="content_main">
  <!-- left_frame -->
  <%@include file="/web/www/user_center/leftFrame.jsp" %>
  <div class="right_frame">
    <h1><font class="bfont"><img src="${resDomain}/www/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">首页</a> --> <a href="user_index.html">用户中心</a> --><a>账户信息</a> </font></h1>
    <form action="/web/userCenterManage!editInfo.action" method="post" id="infoForm">
      <input type="hidden" name="lvAccountInfo.id" value="${lvAccountInfo.id}"/>
      <input type="hidden" name="lvAccountInfo.userCode" value="${lvAccountInfo.userCode}"/>
      <input type="hidden" name="lvAccountInfo.lastTime" value="${lvAccountInfo.lastTime}"/>
      <input type="hidden" name="lvAccountInfo.storeId" value="${lvAccountInfo.storeId}"/>
      <input type="hidden" name="lvAccountInfo.code" value="${lvAccountInfo.code}"/>
      <input type="hidden" name="lvAccountInfo.createTime" value="${lvAccountInfo.createTime}"/>
      <input type="hidden" name="ordnickname" id="ordnickname" value="${lvAccount.nickname}"/>
      
	  <div class="usercenter_box">
	      <ul>
	        <li class="password">
	          <p class="text">&nbsp;</p>
	          <p><font id="msgbox" color="red" ><%=msg %></font></p>
	        </li>
	      </ul>
	      <ul>
	        <li class="password">
	          <p class="text">登錄帳號：</p>
	          <p>${lvAccount.email}</p>
	        </li>
	        <li class="prompt"></li>
	      </ul>
	      <ul>
	        <li class="password">
	          <p class="text"><font class="redfont">*</font> 昵稱：</p>
	          <p>
	          		<c:if test="${not empty lvAccount.nickname}">
						<input type="hidden" name="lvAccount.nickname" class="input4" value="${lvAccount.nickname}"/>
						${lvAccount.nickname }
					</c:if>
					<c:if test="${empty lvAccount.nickname}">
						<input type="text" name="lvAccount.nickname" class="input4" value="${lvAccount.nickname}" maxlength="32"/>
					</c:if>
	          </p>
	        </li>
	        <li class="prompt"></li>
	      </ul>
	      <ul>
	        <li class="password">
	          <p class="text">性別：</p>
	          <p>
	            <input name="lvAccountInfo.sex" type="radio" value="0" <s:if test="lvAccountInfo.sex==0 || lvAccountInfo.sex==null">checked="checked"</s:if> />男
		        <input name="lvAccountInfo.sex" type="radio" value="1" <s:if test="lvAccountInfo.sex==1">checked="checked"</s:if> />女 
		        <input name="lvAccountInfo.sex" type="radio" value="2" <s:if test="lvAccountInfo.sex==2">checked="checked"</s:if> />保密
	          </p>
	        </li>
	        <li class="prompt"></li>
	      </ul>
	      <ul>
	        <li class="password">
	          <P class="text">QQ：</P>
	          <p><input type="text" name="lvAccountInfo.qq" id="qq" value="${lvAccountInfo.qq}" onkeyup="js.onlyNumber(this, '只能输入数字')" maxlength="20" class="input3" /></p>
	        </li>
	        <li class="prompt"></li>
	      </ul>
	      <ul>
	        <li class="password">
	          <P class="text">MSN：</P>
	          <p><input name="lvAccountInfo.msn" value="${lvAccountInfo.msn}" id="msn" maxlength="32" class="input3" type="text" /></p>
	        </li>
	        <li class="prompt"></li>
	      </ul>
	      <ul>
	        <li class="password">
	          <p class="text"><font class="redfont"> *</font> 真實姓名：</p>
	          <p><input name="lvAccountInfo.name" id="realName" value="${lvAccountInfo.name}" maxlength="32" class="input3" type="text" /></p>
	        </li>
	        <li class="prompt"></li>
	      </ul>
	      <ul>
	        <li class="password">
	          <p class="text">電話號碼：</p>
	          <p><input name="lvAccountInfo.tel" value="${lvAccountInfo.tel}" id="tel" maxlength="20" class="input3" type="text" /></p>
	        </li>
	        <li class="prompt"></li>
	      </ul>
	      <ul>
	        <li class="password">
	          <p class="text">手機號碼：</p>
	          <p><input name="lvAccountInfo.mobile" id="mobile" onkeyup="js.onlyNumber(this, '只能输入数字')" value="${lvAccountInfo.mobile}" maxlength="32" type="text" class="input3" /></p>
	        </li>
	        <li class="prompt"></li>
	      </ul>
	      <ul>
	        <li class="password">
	          <p class="text">联繫地址：</p>
	          <p>
	            <input  name="lvAccountInfo.address" id="adress" type="text" class="input3" value="${lvAccountInfo.address}" dvalue="街道詳細地址" maxlength="200" /> -
	            <input  name="lvAccountInfo.cityName" id="cityName" type="text" class="input4" value="${lvAccountInfo.cityName}" dvalue="縣/市" maxlength="50" /> -
	            <input  name="lvAccountInfo.provinceName" id="provinceName" type="text" class="input4" value="${lvAccountInfo.provinceName}" dvalue="洲/省" maxlength="50" /> -
	            <select name="lvAccountInfo.contryName" id="contryId" class="input3">
	              <option value="">--請選擇國家--</option>
	              <c:foreach items="${contryList}" var="c">
	              	<option value="${c.code}" <c:if test="${c.code==lvAccountInfo.contryName}">selected</c:if>>${c.nameen}</option>
	              </c:foreach>
	            </select>
	          </p>
	        </li>
	        <li class="prompt"></li>
	      </ul>
	      <ul>
	        <li class="password">
	          <p class="text"><font class="redfont"> *</font> 郵編：</p>
	          <p><input name="lvAccountInfo.postCode" id="postcodeId" type="text" value="${lvAccountInfo.postCode}" maxlength="16" class="input3" /></p>
	        </li>
	        <li class="prompt"></li>
	      </ul>
	      <ul class="btn">
	        <li class="wd1">&nbsp;</li>
	        <li class="wd2"><input  type="submit" value="保存修改" class="user_center_bt" /></li>
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
<%@include file="/web/www/common/footer.jsp" %>
</body>
</html>
