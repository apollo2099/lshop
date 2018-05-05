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
function subForm() {
	var vtypeObj = $("input[name='vtype']:checked");
	var type = $("input[name='type']:checked").val();
	var msgbox = $("#msgbox");
	if (js.assert.isNull("accouts", "請輸入賬號！")) {return false;}
	if (js.assert.isNotEmail("accouts", "賬號格式不正確！")) {return false;}
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
	    	msgbox.html("賬號未激活！");
			return false;
		}else if(result==2){
			msgbox.html("賬號已凍結！");
			return false;
		}else if(result==""){
			msgbox.html("賬號不存在");
			return false;
	    }else if(result==0){
			msgbox.html("賬號已停用！");
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
		msgbox.html("請輸入需要購買的V幣數量！");
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
<%@include file="/web/www/common/header.jsp"%>
<div class="content_main">
  <!-- left_frame -->
  <%@include file="/web/www/user_center/leftFrame.jsp" %>
  <div class="right_frame">
    <h1><font class="bfont"><img src="${resDomain}/www/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">首頁</a> --><a href="/web/userCenterManage!getAccount.action"> 用戶中心</a> --><a href="/web/recharge!list.action"> 賬戶餘額</a> --><a>在線充值</a></font> </h1>
    <div class="usercenter_box">
      <div class="user_center_right">
        <form action="/web/recharge!doOrder.action" method="post" onsubmit="return subForm()">
        <input name="recharge.vbNum" id="vnumId" value="" type="hidden"/>
        <input  type="hidden" name="type" value="2"/>
        <ul>
          <li id="item_account">
            <p class="text" style="padding-top:0px;"><font class="redfont">*</font> 賬號：</p>
            <p>
              <input name="recharge.accounts" id="accouts" type="text" class="input02" maxlength="60" value="${requestScope.email}"/>
            </p>
          </li>
        </ul>
        <!--帮人充值则出现账号一栏-->
        <ul>
          <li style="height:22px;line-height:22px;">
            <p class="text"><font class="redfont">*</font> 需購買的V币幣數量：</p>
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
          <li class="choose_2" style="margin-top:8px;">應付金額：<font id="moneyId" class="redfont"></font></li>
          <li class="prompt">
            <p class="pt" style="display:none;">請輸入您的充值卡卡號</p>
            <!--输入框获得焦点状态提示语-->
            <p class="er" style="display:none;">卡號或密碼錯誤</p>
            <!--错误状态提示语-->
          </li>
        </ul>
        <ul>
          <li class="center"><span id="msgbox" style="color:#f40000;"></span></li>
          <li class="center">
            <input class="user_center_bt" type="submit" value="提交" />
          </li>
        </ul>
        <div style="clear:both;"></div>
        </form>
		<div class="tips01"><font class="redfont">V幣購買溫馨提示：</font><br />
          1. V幣充值到您的帳戶後，不能贈送給其他帳戶。<br />
          2. 若出現已成功充值的提示，但V幣未到賬，可能是網絡系統繁忙導致，請耐心等候或聯繫我們的客服專用郵箱：<font class="bluefont"><a href="mailto:pay@creatent.net">pay@creatent.net</a></font>客服人员会在24小时之内回覆。<br />
          3. 如在充值過程中遇到任何其他問題，您可以隨時聯繫我們的客服專用郵箱<font class="bluefont"><a href="mailto:pay@creatent.net">pay@creatent.net</a></font>，客服人員會在24小時之內回覆。<br />
          4. V幣是用來購買TVpad各項服務的一種虛擬貨幣，支付平台使用全球著名認證中心VeriSign提供的SSL證書，安全可靠，最終解釋權歸啟創科技所有。
         </div>
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
<%@include file="/web/www/common/footer.jsp" %>
</body>
</html>
