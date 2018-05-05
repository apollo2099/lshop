<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!doctype html>
<html>
<head>
<%@include file="/web/mbmcn/user_center/c_meta.jsp"%>	
<title>用户中心_在线充值</title>

<script type="text/javascript" src="${resDomain}/mbmcn/res/js/checkForm.js"></script>
<script type="text/javascript" src="${resDomain}/mbmcn/res/js/myjs.js"></script>
<!-- 
<script type="text/javascript" src="${resDomain}/mbmcn/res/js/recharge.js"></script>
 -->
<script type="text/javascript">
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

//在线充值
function subFormOnline() {
	var vtypeObj = $("input[name='vtype']:checked");
	var type = $("input[name='type']:checked").val();
		if(!(checkEmail($("input[name='recharge.accounts']")))) return false;
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
			showInfo($("input[name='recharge.accounts']"),'账号未激活');
			return false;
		}else if(result==2){
			showInfo($("input[name='recharge.accounts']"),'账号已冻结');
			return false;
		}else if(result==""){
			showInfo($("input[name='recharge.accounts']"),'账号不存在');
			return false;
	    }else if(result==0){
	    	showInfo($("input[name='recharge.accounts']"),'账号已停用');
			return false;
		}
		if(vtypeObj.length==1){
		    if(vtypeObj.val()==0) {
		      $("#vnumId").val($("#otherId").val());
		    } else {
		      $("#vnumId").val(vtypeObj.val());
			}
		}
	var vnum =$("#vnumId").val();
    if(vnum==''||vnum==0) {
    	showInfo($("input[name='otherId']"),'请输入需要购买的V币数量');
    	return false;
    }else if(!/^\d+$/.test(vnum)||vnum<parseInt("${vbPlans.minNum}")){
    	showInfo($("input[name='otherId']"),"购买V币数量不得低于${vbPlans.minNum}V幣！");
		return false;
	}else if("${vbPlans.isMul}"=="true"){
		if(vnum%parseInt("${vbPlans.minNum}")!=0){
			showInfo($("input[name='otherId']"),"请输入${vbPlans.minNum}的倍数！");	
		 return false;
		}
	}
    $("#doorderform").submit();
	return true;
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
	$("#rechargeAmount").text(currencyMallIdValue+" "+money);
	var vtypeObj = $("input[name='vtype']:checked");
	if(vtypeObj.val()==0&&"${vbPlans.isMul}"=="true"){
		if($.trim(vnum)!=""&&vnum%parseInt("${vbPlans.minNum}")!=0){
			showInfo($("input[name='otherId']"),"请输入${vbPlans.minNum}的倍数！");	
		}else{
			$("input[name='otherId']").next(".tip").hide();
		}
	}
}

function showInfo(obj,info){
	obj.next(".tip").children(".errInfo").html(info);
	obj.next(".tip").show();
}
</script>
</head>
<body>
<%@include file="/web/mbmcn/user_center/c_top.jsp"%>

<article>
<form action="/web/recharge!doOrder.action" method="post" id="doorderform" >
  <div class="choserechrge">
  </div>
 <div class="vb_box">  
	<table width="246" align="center" cellpadding="0" cellspacing="0" class="help_recharges" style="display:block;" id="item_account">
	<tr>
	<td width="18%" height="60" align="left" class="fonsi"  style="color:#52555B;font-family:'宋体'">账号：</td>
    <td width="82%" height="60" colspan="3">
	<div  class="inputd" >
		<input type="text" class="inpu inpu1" value="${requestScope.email}" maxlength="60" name="recharge.accounts" id="accouts"/>  
		<div class="tip">
	     	<em></em>
	        <span class="errInfo"></span>
	        <i></i>
	        <b></b>
	    </div>
	</div>
	</td>
	</tr>
	</table>
  
  <div class="shosevb" style="margin-top:10px">
     <span>需购买的V币数量：</span>
      <s:iterator value="#request.vbPlansList" id="p" status="stat">
          <s:if test="#p.ptype==false">
          <label for="vbcont">
           <input name="vtype" type="radio" value="${p.vbNum}" onclick="clickType()"  <s:if test="#stat.index==0">checked="checked" </s:if>>
           ${p.title}
          </label>
          </s:if>
          <s:if test="#p.ptype==true">
           <c:set value="true" var="vtypeFlag"/>
          <label for="vbcont">
     <div  class="inputd">
        <input name="vtype" onclick="clickType()" type="radio" value="0" style="margin-top:12px"> ${p.title}
        <input type="text" name="otherId" id="otherId" value="" class="intsho inpu1"
     onkeyup="this.value=this.value.replace(/\D/g,'');clickType();" 
     onafterpaste="this.value=this.value.replace(/\D/g,'')" maxlength="6">
        <div class="tip">
          <em style="right:10px"></em>
          <span class="errInfo"></span>
	      <i></i>
	      <b></b>
        </div>
    </div>
    </label>
          </s:if>
          </s:iterator>
     
    
   <!-- 
     <label for="vbcont">
     <input name="vtype" type="radio" value="0">
     其他
     <input type="text" name="otherId" id="otherId" value="" class="intsho" 
     onkeyup="this.value=this.value.replace(/\D/g,'');getEnterAmount();" 
     onafterpaste="this.value=this.value.replace(/\D/g,'')" maxlength="5">
     </label>
 -->  
	
     
  </div>
  
		<div class="vb_detia">
		(注：1VB=
           <s:if test="#request.currency=='USD'">${rateVbNum}美元</s:if>
          <s:if test="#request.currency=='CNY'">${rateVbNumCny}人民币</s:if><c:if test="${vtypeFlag==true}">，每次购买数量不得低于${vbPlans.minNum}个</c:if>)
		</div>
		<div class="vb_cont">
		 应付金额: <span><label id="rechargeAmount"></label></span>
		<input name="recharge.vbNum" id="vnumId" value="" type="hidden"/>
		</div>
  
		<div class="vb_submint">
			<input type="button" value="提 交" onclick="subFormOnline();"/>
		</div>

 </div>
</form>

 
 <div class="vb_tip">
<strong>V币购买温馨提示：</strong><br>   
1.V币充值到您的帐户后，不能赠送给其他帐户。<br>
2.若出现已成功充值的提示，但V币未到账，可能是网络系统繁忙导致，请耐心等候或联系我们的客服专用邮箱：pay@bananatv.com，
客服人员会在24小时之内回覆。<br>
3.如在充值过程中遇到任何其他问题，您可以随时联系我们的客服专用邮箱 pay@bananatv.com，客服人员会在24小时之内回覆。<br>
4.V币是用来购买TVpad各项服务的一种虚拟货币，支付平台使用全球著名认证中心VeriSign提供的SSL证书，安全可靠，最终解释权归启创科技所有。
 </div>
</article>

<%@include file="/web/mbmcn/user_center/c_bottom.jsp"%>
<script type="text/javascript">
	document.getElementById('c_title').innerHTML = '在线充值';
</script>
</body>
</html>
