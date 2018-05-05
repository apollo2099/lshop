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
function subForm() {
	var vtypeObj = $("input[name='vtype']:checked");
	var type = $("input[name='type']:checked").val();
	var msgbox = $("#msgbox");

		if (js.assert.isNull("accouts", "Please enter your account!")) {return false;}
		if (js.assert.isNotEmail("accouts", "Account format is wrong!")) {return false;}
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
	    	msgbox.html("The account you are recharging is not activated!");
			return false;
		}else if(result==2){
			msgbox.html("The account you are recharging is suspended!");
			return false;
		}else if(result==""){
			msgbox.html("The account you are recharging does not exist!");
			return false;
	    }else if(result==0){
			msgbox.html("The account you are recharging is disabled!");
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
		msgbox.html("Please select your recharge amount!");
		$("#otherId").focus();
		return false;
	}else if(!/^\d+$/.test(vnum)||vnum<parseInt("${vbPlans.minNum}")){
		msgbox.html("The purchase quantity should not be less than ${vbPlans.minNum}V!");
		return false;
	}else if("${vbPlans.isMul}"=="true"){
		if(vnum%parseInt("${vbPlans.minNum}")!=0){
			msgbox.html("Please enter a multiple of${vbPlans.minNum}!");	
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
			$("#vtypeId").html("Please enter a multiple of${vbPlans.minNum}!");	
		}else{
			$("#vtypeId").html("");
		}
	}
}
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
      <div class="user_center_right">
        <form action="/web/recharge!doOrder.action" method="post" onsubmit="return subForm()">
        <input name="recharge.vbNum" id="vnumId" value="" type="hidden"/>
        <ul>
          <li id="item_account">
            <p class="text"><font class="redfont">*</font> Account：</p>
            <p>
              <input name="recharge.accounts" id="accouts" type="text" class="input02" maxlength="60" value="${requestScope.email}"/>
            </p>
          </li>
        </ul>
        <!--帮人充值则出现账号一栏-->
        <ul>
          <li>
            <p class="text"><font class="redfont">*</font> Purchase quantity ：</p>
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
          <li class="choose_3"><font class="redfont">(Note: 1V=<s:if test="#request.currency=='CNY'">${rateVbNumCny}</s:if><s:if test="#request.currency=='USD'">${rateVbNum}</s:if>${requestScope.currency}<c:if test="${vtypeFlag==true}">, each purchase should not be less than ${vbPlans.minNum}V.</c:if>)</font></li>
          <li class="choose_2">Amount payable：<font id="moneyId" class="redfont">0</font></li>
          <li class="prompt">
            <p class="pt" style="display:none;">Card No.</p>
            <!--输入框获得焦点状态提示语-->
            <p class="er" style="display:none;">Wrong card number or password.</p>
            <!--错误状态提示语-->
          </li>
        </ul>
        <ul>
          <li class="center"><span id="msgbox" style="color:#f40000;"></span></li>
          <li class="center">
            <input class="user_center_bt" type="submit" value="Submit" />
          </li>
        </ul>
        </form>
        <div class="tips01"><font class="redfont">Tips on purchase of V coin：</font><br />
          1. The V coins in your account shall not be transferred to other account, if you want to recharge V coin for your friends, please choose “Recharge Other Account”.<br />
          2. If the system prompts you a recharge succeeded massage without arrival of V coins, it may be resulted from network busy, you can wait patiently or contact our customer service at <font class="bluefont"><a href="mailto:pay@creatent.net">pay@creatent.net</a></font>, and our customer service will reply you within 24 hours.<br />
          3. If you have any other questions on payment, please contact our customer service at <font class="bluefont"><a href="mailto:pay@creatent.net">pay@creatent.net</a></font>, and our customer service will reply you within 24 hours. <br />
          4. V coin is a kind of virtual currency used to purchase TVpad service. The payment platform adopts SSL from global famous AC – VeriSign to keep payment safe and reliable. Create New Technology (HK) Limited owns the final interpretation.<br />
           </div>
      </div>
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
