<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户中心_banana商城</title>
<link href="${resDomain}/bmcn/res/css/user_center.css" rel="stylesheet" type="text/css" />
<!-- 加载公共JS -->
<%@include file="/web/bmcn/common/top.jsp"%>
<script type="text/javascript" src="${resDomain}/bmcn/res/js/myjs.js"></script>
<script type="text/javascript">
function subForm() {
	var vtypeObj = $("input[name='vtype']:checked");
	var type = $("input[name='type']:checked").val();
	var msgbox = $("#msgbox");
	if (js.assert.isNull("accouts", "请输入帐号！")) {return false;}
	if (js.assert.isNotEmail("accouts", "账号格式不正确！")) {return false;}
		var result;
		var email = $.trim($("#accouts").val());
		$.ajax({
			url:"/web/recharge!isExistAccout.action",
			type: 'post',
			data:{email:email},
			cache:false,
			async:false,
			dataType:"text",
			success:function(data){
				result = data;
			}
		});
		if(result==3){
	    	msgbox.html("账号未激活！");
			return false;
		}else if(result==2){
			msgbox.html("账号已冻结！");
			return false;
		}else if(result==""){
			msgbox.html("账号不存在");
			return false;
	    }else if(result==0){
			msgbox.html("账号已停用！");
			return false;
		}
	if(vtypeObj.length==1){
	    if(vtypeObj.val()==0) {
	      $("#vnumId").val($("#otherId").val());
	    } else {
	      $("#vnumId").val(vtypeObj.val());
		}
	}
	var vnum=$("#vnumId").val();
	if($.trim(vnum)==""){
		msgbox.html("请输入需要购买的V币数量！");
		$("#otherId").focus();
		return false;
	}else if(!/^\d+$/.test(vnum)||vnum<parseInt("${vbPlans.minNum}")){
		msgbox.html("购买V币数量不得低于${vbPlans.minNum}V幣！");
		return false;
	}else if("${vbPlans.isMul}"=="true"){
		if(vnum%parseInt("${vbPlans.minNum}")!=0){
			msgbox.html("请输入${vbPlans.minNum}的倍数！");	
		 return false;
		}
	}
	
	msgbox.html("");
	return true;
}
$(function(){
	clickType();
})
function clickType(){
	var vtypeObj=$("input[name='vtype']:checked");
	if(vtypeObj.length==1){
	    if(vtypeObj.val()==0){
	      $("#vnumId").val($("#otherId").val());
	    }else{
	      $("#vnumId").val(vtypeObj.val());
	    }
	}
	countMoney();
}

function countMoney() {
	var rateVbNum=parseFloat("<s:property value="@com.lshop.common.util.Constants@rateVbNum"/>");
    var currencyMallIdValue="${currency}";
	if(currencyMallIdValue=="CNY"){
	var rateVbNum=parseFloat("<s:property value="@com.lshop.common.util.Constants@rateVbNumCny"/>");
	}
	var vnum=$("#vnumId").val();
	var money=vnum*rateVbNum;
	money=Math.round(money*100)/100
	$("#moneyId").text(currencyMallIdValue+" "+money);
	var vtypeObj = $("input[name='vtype']:checked");
	if(vtypeObj.val()==0&&"${vbPlans.isMul}"=="true"){
		if($.trim(vnum)!=""&&vnum%parseInt("${vbPlans.minNum}")!=0){
			$("#vtypeId").html("请输入${vbPlans.minNum}的倍数！");	
		}else{
			$("#vtypeId").html("");
		}
	}
}
</script>

</head>
<body>
<!-- 获取商城头部文件 -->
<%@include file="/web/bmcn/common/header.jsp"%>
<div class="content_main">
  <!-- left_frame -->
  <%@include file="/web/bmcn/user_center/leftFrame.jsp" %>
  <div class="right_frame">
    <h1><font class="bfont"><img src="${resDomain}/bmcn/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">首页</a> --><a href="/web/userCenterManage!getAccount.action"> 用户中心</a> --><a href="/web/recharge!list.action"> 账户余额</a> --></font> 在线充值</h1>
    <div class="usercenter_box">
      <div class="user_center_right">
        <form action="/web/recharge!doOrder.action" method="post" onsubmit="return subForm()">
        <input name="recharge.vbNum" id="vnumId" value="" type="hidden"/>
        <ul>
          <li id="item_account">
            <p class="text"><font class="redfont">*</font> 账号：</p>
            <p>
              <input name="recharge.accounts" id="accouts" type="text" value="${requestScope.email}" maxlength="60" class="input02"/>
            </p>
          </li>
        </ul>
        <!--帮人充值则出现账号一栏-->
        <ul>
          <li>
            <p class="text"><font class="redfont">*</font> 需购买的V币数量：</p>
          </li>
           <s:iterator value="#request.vbPlansList" id="p" status="stat">
          <s:if test="#p.ptype==false">
          <li class="choose_2"><input name="vtype" type="radio" value="${p.vbNum}" onclick="clickType()" <s:if test="#stat.index==0">checked="checked" </s:if> /> ${p.title}</li>
          </s:if>
          <s:if test="#p.ptype==true">
          <c:set value="true" var="vtypeFlag"/>
          <li class="choose_2">
            <input name="vtype" type="radio" value="0" onclick="clickType()"/> ${p.title} <input id="otherId" type="text" onblur="clickType()" onchange="countMoney()" onkeyup="this.value=this.value.replace(/\D/g,'');clickType();" onafterpaste="this.value=this.value.replace(/\D/g,'')" maxlength="5" class="input22" style="float: none;" value=""/>
           <span id="vtypeId" style="color:red;"></span>
          </li>
          </s:if>
         </s:iterator>
          <li class="choose_3"><font class="redfont">(注：1VB=
           <s:if test="#request.currency=='USD'">${rateVbNum}美元</s:if>
          <s:if test="#request.currency=='CNY'">${rateVbNumCny}人民币</s:if>
          <c:if test="${vtypeFlag==true}">，每次购买数量不得低于${vbPlans.minNum}个</c:if>)</font></li>
          <li class="choose_2">应付金额：<font id="moneyId" class="redfont">0</font></li>
          <li class="prompt">
            <p class="pt" style="display:none;">请输入您的充值卡卡号</p>
            <!--输入框获得焦点状态提示语-->
            <p class="er" style="display:none;">卡号或密码错误</p>
            <!--错误状态提示语-->
          </li>
        </ul>
        <ul>
          <li class="center"><span id="msgbox" style="color:#f40000;"></span></li>
          <li class="center">
            <input class="user_center_bt" type="submit" value="提交" />
          </li>
        </ul>
        </form>
        <div class="tips01"><font class="redfont">V币购买温馨提示：</font><br />
          1. V币充值到您的帐户后，不能赠送给其他帐户。<br />
          2. 若出现已成功充值的提示，但V币未到账，可能是网络系统繁忙导致，请耐心等候或联系我们的客服专用邮箱：<font class="bluefont"><a href="mailto:pay@creatent.net">pay@creatent.net</a></font><br />
          &nbsp;&nbsp; 客服人员会在24小时之内回覆。<br />
          3. 如在充值过程中遇到任何其他问题，您可以随时联系我们的客服专用邮箱 <font class="bluefont"><a href="mailto:pay@creatent.net">pay@creatent.net</a></font>，客服人员会在24小时之内回覆。<br />
          4. V币是用来购买TVpad各项服务的一种虚拟货币，支付平台使用全球著名认证中心VeriSign提供的SSL证书，安全可靠，最终解释<br />
          &nbsp;&nbsp;权归启创科技所有。 </div>
      </div>
      <div class="cb"></div>
    </div>
  </div>
</div>
<!--End content-->
<!-- 底部广告位-->
<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
<!-- footer-->
<%@include file="/web/bmcn/common/footer.jsp" %>
</body>
</html>
