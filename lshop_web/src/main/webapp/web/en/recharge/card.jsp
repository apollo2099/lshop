<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>User Center_TVpad Mall</title>
<link href="${resDomain}/en/res/css/user_center.css" rel="stylesheet" type="text/css" />
<!-- 加载公共JS -->
<%@include file="/web/en/common/top.jsp"%>

<script type="text/javascript" src="${resDomain}/en/res/js/myjs.js"></script>
<script type="text/javascript">
// 表单提交
function subForm() {
	var msgbox = $("#msgbox");
	var type = $("input[name='type']:checked").val();
	if (type == 2) {	// 帮人充值
		if (js.assert.isNull("accounts", "Please enter your account!")) {return false;}
		if (js.assert.isNotEmail("accounts", "Account format is wrong!")) {return false;}
	}
	if (js.assert.isNull("rcardNum", "Card No.")) {return false;}
	if (js.assert.isNull("cardPwd", "Password")) {return false;}
	if (js.assert.isNull("validCode", "Auth Code")) {return false;}
		var result;
		var email = $("#accounts").val().trim();
		$.ajax({
			url:"/web/recharge!isExistAccout.action",
			type: 'post',
			dataType:"text",
			data:{email:email},
			cache:false,
			async:false,
			success:function(data){
				result = data;
			}
		});
		
		if (result == 3) {
			msgbox.html("The account you are recharging is not activated!");
			return false;
		} else if (result == 2) {
			msgbox.html("The account you are recharging is suspended!");
			return false;
		} else if (result == "") {
			msgbox.html("The account you are recharging does not exist!");
			return false;
		} else if (result == 0) {
			msgbox.html("The account you are recharging is disabled!");
			return false;
		}
	msgbox.html("");	// 清除提示消息
	return true;
}

function changeCodeImg(){
	$("#codeId").attr("src",$("#codeId").attr("src")+"?t="+new Date());
}

//处理某些电脑 出现该页面卡号和卡密码输入栏，会自动填充用户名和密码的怪问题
$(function(){
   var temp_rcardNum=$("#temp_rcardNum").val();
   var rcardNum=$("#rcardNum").val();
   if(rcardNum!=""&&temp_rcardNum!=rcardNum){
      $("#rcardNum").val("");
      $("#cardPwd").val("");
   }
});

</script>

</head>
<body>
<!-- 获取商城头部文件 -->
<%@include file="/web/en/common/header.jsp"%>
<div class="content_main">
  <!-- left_frame -->
  <%@include file="/web/en/user_center/leftFrame.jsp" %>
  <div class="right_frame">
    <h1><font class="bfont"><img src="${resDomain}/en/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">Home</a> --><a href="/web/userCenterManage!getAccount.action"> User Center</a> --></font> Account Balance</h1>
    <div class="usercenter_box">
      <form action="/web/docard.action" method="post" onsubmit="return subForm()">
      <div class="user_center_right">
        <ul id="item_account" >
          <li>
            <p class="text"><font class="redfont">*</font> Account：</p>
            <p><input id="accounts" name="recharge.accounts" value="${requestScope.email}" maxlength="60" type="text" class="input02"/></p>
          </li>
        </ul>
        <!--帮人充值则出现账号一栏-->
        <ul>
          <li>
            <p class="text"><font class="redfont">*</font> Card No.：</p>
            <p>
              <input value="${requestScope.recharge.rcardNum}" id="temp_rcardNum" type="hidden" />
              <input id="rcardNum" name="recharge.rcardNum" value="${requestScope.recharge.rcardNum}" maxlength="20" type="text" class="input02"/>
            </p>
          </li>
        </ul>
        <ul>
          <li>
            <p class="text"><font class="redfont">*</font> Password：</p>
            <p><input id="cardPwd" name="cardPwd" maxlength="30" type="password" class="input02"/></p>
          </li>
        </ul>
        <ul>
          <li>
            <p class="text"><font class="redfont">*</font> Auth Code：</p>
            <p><input id="validCode" name="validCode" maxlength="4" type="text" class="input22"/></p>
            <p><img src="/web/imager.jsp" id="codeId" style="vertical-align: middle;" /> Unclear?<a href="javascript:void(0)" onclick="changeCodeImg()">Refresh</a></p>
          </li>
        </ul>
        <ul>
          <li class="center"><div id="msgbox" style="color:#f40000;">${requestScope.resultMsg}</div></li>
          <li class="center"><input id="regbtn" class="user_center_bt" type="submit" value="Submit" /></li>
        </ul>
      </div>
      </form>
      <div class="cb"></div>
    </div>
  </div>
</div>
<!--End content-->

<!-- 底部广告位-->
<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
<!-- footer-->
<%@include file="/web/en/common/footer.jsp" %>
</body>
</html>
