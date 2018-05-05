<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用戶中心_TVpad商城</title>
<link href="${resDomain}/www/res/css/user_center.css" rel="stylesheet" type="text/css" />
<!-- 加载公共JS -->
<%@include file="/web/www/common/top.jsp"%>

<script type="text/javascript" src="${resDomain}/www/res/js/myjs.js"></script>
<script type="text/javascript">

// 表单提交
function subForm() {
	var msgbox = $("#msgbox");
	var type = $("input[name='type']:checked").val();
	if (js.assert.isNull("accounts", "請輸入賬號！")) {return false;}
	if (js.assert.isNotEmail("accounts", "賬號格式不正確！")) {return false;}
	if (js.assert.isNull("rcardNum", "請輸入充值卡號！")) {return false;}
	if (js.assert.isNull("cardPwd", "請輸入充值卡密碼！")) {return false;}
	if (js.assert.isNull("validCode", "請輸入驗證碼！")) {return false;}
	var result;
		$.ajax({
			url:"/web/recharge!userStatus.action",
			type: 'post',
			dataType:"text",
			cache:false,
			async:false,
			success:function(data){
				result = data;
			}
		});
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
			msgbox.html("賬號未激活！");
			return false;
		} else if (result == 2) {
			msgbox.html("賬號已凍結！");
			return false;
		} else if (result == "") {
			msgbox.html("賬號不存在！");
			return false;
		} else if (result == 0) {
			msgbox.html("賬號已停用！");
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
<%@include file="/web/www/common/header.jsp"%>
<div class="content_main">
  <!-- left_frame -->
  <%@include file="/web/www/user_center/leftFrame.jsp" %>
  <div class="right_frame">
    <h1><font class="bfont"><img src="${resDomain}/www/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">首頁</a> --><a href="/web/userCenterManage!getAccount.action"> 用戶中心</a> --><a href="/web/recharge!list.action"> 賬戶餘額</a> --><a>充值卡充值</a></font> </h1>
    <div class="usercenter_box">
      <form action="/web/docard.action" method="post" onsubmit="return subForm()">
      <div class="user_center_right">
        <ul id="item_account">
          <li>
            <p class="text"><font class="redfont">*</font> 賬號：</p>
            <p><input id="accounts" name="recharge.accounts" value="${requestScope.email}" maxlength="60" type="text" class="input02" /></p>
          </li>
          <!--<li class="prompt">
              <span class="rec">請輸入您的賬號！</span>
          </li>-->
        </ul>
        <!--帮人充值则出现账号一栏-->
        <ul>
          <li>
            <p class="text"><font class="redfont">*</font> 充值卡卡號：</p>
            <p>
              <input value="${requestScope.recharge.rcardNum}" id="temp_rcardNum" type="hidden" />
              <input id="rcardNum" name="recharge.rcardNum" value="${requestScope.recharge.rcardNum}" maxlength="20" type="text" class="input02"/>
            </p>
          </li>
          <!--<li class="prompt">
              <span class="rec">請輸入您的充值卡卡號！</span>
          </li>-->
        </ul>
        <ul>
          <li>
            <p class="text"><font class="redfont">*</font> 充值卡密碼：</p>
            <p><input id="cardPwd" name="cardPwd" maxlength="30" type="password" class="input02"/></p>
          </li>
          <!--<li class="prompt">
              <span class="rec">請輸入您的充值卡密碼！</span>
          </li>-->
        </ul>
        <ul>
          <li>
            <p class="text"><font class="redfont">*</font> 驗證碼：</p>
            <p><input id="validCode" name="validCode" maxlength="4" type="text" class="input22"/></p>
            <p><img src="/web/imager.jsp" id="codeId" style="vertical-align: middle;" /> 看不清楚？<a href="javascript:void(0)" onclick="changeCodeImg()">換一張</a></p>
          </li>
          <!--<li class="prompt">
              <span class="rec">請輸入驗證碼！</span>
          </li>-->
        </ul>
        <ul>
          <li class="center"><div id="msgbox" style="color:#f40000;">${requestScope.resultMsg}</div></li>
          <li class="center"><input id="regbtn" class="user_center_bt" type="submit" value="提交" /></li>
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
<%@include file="/web/www/common/footer.jsp" %>
</body>
</html>
