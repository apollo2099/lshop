<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户中心_banana商城</title>
<link href="${resDomain}/bmcn/res/css/user_center.css" rel="stylesheet" type="text/css" />
<style  type="text/css">
.addrOption{color:#505050;font-family:"微软雅黑";font-size:12px;}
.addrItem{float:left;height:25px;line-height:25px;width:100px;}
.addrItem label{height:25px;line-height:25px;display:inline-block;}
.addrItem input{vertical-align:middle;margin:0px;}
.addrInfo{padding-left:100px;}
.addrInfo span{height:25px;line-height:25px;display:inline-block;}
.addrList{display:none;padding-left:121px;padding-top: 10px}
</style>
<!-- 加载公共JS -->
<%@include file="/web/bmcn/common/top.jsp"%>
<script type="text/javascript" src="${resDomain}/bmcn/res/js/myjs.js"></script>
<script type="text/javascript" src="${resDomain}/bmcn/res/js/address.js"></script>
<script type="text/javascript">
function doAction(){
	var paymethod=$("input[name='paymethod']:checked");
    if(paymethod.length==0){
       $("#msgbox").html("请选择支付方式！");
       return false;
    }
    $("#msgbox").html("");
    if(paymethod.val()==3){
     var formUrl=$("#addressFormVbId").attr("action")+"?rnum=${rnum}&paymethod="+paymethod.val();
     location.href=formUrl;
     
    }
	return true;
}

function show(id){
    var W=$(document).width();
    var H=$(document).height();
    var mask=document.getElementById("mask");
    mask.style.cssText="position:absolute;z-index:5;width:"+W+"px;height:"+H+"px;background:#000;filter:alpha(opacity=30);opacity:0.3;top:0;left:0;";
    $("#mask").show();
	  var tx_b=document.getElementById(id);
	    tx_b.style.left=(window.screen.width/2-400)+"px";
	    tx_b.style.top=(150+document.documentElement.scrollTop||document.body.scrollTop)+'px';
	    if ((navigator.userAgent.indexOf('MSIE') >= 0) && (navigator.userAgent.indexOf('Opera') < 0)){
	    	//你是使用IE
	    }else if (navigator.userAgent.indexOf('Firefox') >= 0){
	    	//你是使用Firefox
	    }else if (navigator.userAgent.indexOf('Opera') >= 0){
	    	//你是使用Opera
	    }
	    $(tx_b).fadeIn("fast",function(){});
	   //	document.getElementById("#"+id).style.height=document.body.clientHeight+"px";
	 //   document.getElementById("#"+id).style.width=document.body.clientWidth+"px";
	$("#"+id).show();
}
function hide(id){
	$("#"+id).hide();
	$("#mask").hide();
}

function useDefaultAddress(){
	var jsonStr=$('input[name="address"]:checked').val();
	if(""==jsonStr){
		return;
	}
	var obj = eval('('+jsonStr+')');
	$("#relName").val(obj.relName);
	$("#mobile").val(obj.mobile);
	$("#tel").val(obj.tel);
	$("#adress").val(obj.adress);
	$("#contryId").val(obj.contryId);
	$("#cityName").val(obj.cityName);
	$("#contrynameId").val(obj.contryName);
	$("#postCode").val(obj.postCode);
	$("#provinceName").val(obj.provinceName);
}
window.onload=function(){
var objAddr=document.getElementById("objAddr");
var btnAddr=document.getElementById("btnAddr");
var addrList=document.getElementById("addrList");
btnAddr.onclick=function(){
	if($("#btnAddr").attr("src")=="${resDomain}/www/res/images/jia.gif"){
	    addrList.style.display="block";
	    $("#btnAddr").attr("src","${resDomain}/www/res/images/jian.gif");
		
	}
	else{
		addrList.style.display="none";
		$("#btnAddr").attr("src","${resDomain}/www/res/images/jia.gif");
	}
}
}
$(function(){
	$.getScript("${resDomain}/bmcn/res/js/area_cn.js");
	
})
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
          <form action="/web/recharge!toPay.action" method="post" name="payFrom" onsubmit="return doAction()" id="addressFormVbId">
            <input type="hidden" name="rnum" value="${rnum}">
            <div class="user_center_right">
              <ul>
                <li>
                  <p class="text">订单：</p>
                </li>
                <li class="choose_3">订单号：${recharge.rnum}</li>
                <li  class="choose_3">账号：${recharge.accounts}</li>
                <li class="choose_3">购买V币数量：${recharge.vbNum }</li>
                <li class="choose_3">应付金额：<span id="currency">${recharge.currency}</span> <span id="money">${recharge.money}</span></li>
              </ul>
              <div class="line"></div>
              <ul>
                <li>
                  <p class="text">支付方式：</p>
                </li>
                <c:foreach var="paymentStyle" items="${paymentStyleList}">
                	<li class="choose_3"> 
		                <input name="paymethod" type="radio" value="${paymentStyle.payValue}"  <c:if test="${paymentStyle.payValue eq recharge.rtype}">checked</c:if> /> 
		                ${paymentStyle.payName}
		                <font class="bluefont"><a href="javascript:void(0)" onclick="show('win_1')">重要提醒</a></font> 
	                 </li>
                </c:foreach>
              </ul>
              <div class="line"></div>
              <script type="text/javascript">
              $(function(){
            	var paymethod=$("input[name='paymethod']:checked");
      		    if(paymethod.length!=0){
      		    	if(paymethod.val()!=3){
      		         $("#payInfoId").show();
      		    	}
      		    }
      		   $("input[name='paymethod']").click(function(){
      			 paymethod=$("input[name='paymethod']:checked");
      			   if(paymethod.val()==3){
      				 $("#payInfoId").hide();
      			   }else{
      			      $("#payInfoId").show();
      			   }
      			});
              })
              </script>
     
             <div style="padding-left:86px;display:none;" id="payInfoId">
			 <div id="objAddr" >
			 <div>
			 <label for="btnAddr" style="margin-left:64px;float:left;">账单信息  <s:if test="#request.addressList!=null"> <span style="padding-left: 57px;color: bl;color: #B68571;">使用收货地址：</span> </s:if> </label>
			 <s:if test="#request.addressList!=null"> <img src="${resDomain}/www/res/images/jia.gif" alt=""  onclick="" id="btnAddr"/></s:if>
			 <s:else><br/></s:else>
			 </div>
            <div id="addrList" class="addrList">
            <s:if test="#request.addressList!=null"> 
               <s:iterator value="#request.addressList" id="address" status="stat">
                <div class="addrOption">
                    <span class="addrItem">
                    
                        <input type="radio" name="address" onclick="useDefaultAddress()" value="{relName:'${address.relName}',mobile:'${address.mobile}',tel:'${address.tel}',adress:'${address.adress}',provinceId:'${address.provinceId}',provinceName:'${address.provinceName}',contryId:'${address.contryId}',cityId:'${address.cityId}',cityName:'${address.cityName}',postCode:'${address.postCode}',contryName:'${address.contryName}'}"/>
                        <label for="addrOne">${address.relName}</label>
                    </span>
                    <div class="addrInfo">
                        <span>${address.contryName }&nbsp;${address.provinceName}&nbsp;${address.cityName}&nbsp;${address.adress }</span><br/>
                        <span>${address.postCode}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <span>${address.tel} &nbsp;${ address.mobile}</span>
                    </div>
                </div>
                </s:iterator>
           </s:if>
           </div>
        </div>
        <br/>
                  <ul style="margin-bottom:10px;">
                      <li class="wd1"><font class="redfont">*</font>姓名：</li>
                      <li class="wd2"><input name="lvAccountAddress.relName" class="input4 error" type="text" id="relName"><label for="relName" generated="true" class="error"></label>
                      </li>
                  </ul>
                  <ul style="margin-bottom:10px;">
                    <li class="wd1">手机号码：</li>
                    <li class="wd2"><input id="mobile" name="lvAccountAddress.mobile" class="input4 valid" onkeypress="onlyNumber(event)" type="text"><span id="mobileInfo"></span>
                    </li>
				</ul>
				<ul style="margin-bottom:10px;">
					<li class="wd1">固定电话：</li>
					<li class="wd2"><input id="tel" name="lvAccountAddress.tel" class="input4 valid" type="text"><span id="telInfo"></span>
					</li>
				</ul>
				<ul style="margin-bottom:10px;">
					<li class="wd1"><font class="redfont">*</font>账单地址：</li>
					<li class="wd2">
						<!--<input  name="lvAccountAddress.provinceId" id="provinceName" type="text" class="input4" value="州/省" onfocus="if(this.value=='州/省')this.value=''" onblur="if(this.value=='')this.value='州/省'"/>  -->
						<select name="lvAccountAddress.contryId" style="width:100px" id="contryId" onchange="contryChange()">
							<option value="">--请选择国家--</option>
							<option value="100023">中国</option>
							<c:foreach items="${contryList}" var="c">
								<c:if test="${c.id!=100023}">
									<option value="${c.id}">${c.nameen}</option>
								</c:if>
							</c:foreach>
						</select>
						<font class="redfont">*</font>
						<input type="hidden" name="lvAccountAddress.contryName" id="contrynameId" value="">
						-
						<input type="hidden" id="test">
						<input type="text" name="lvAccountAddress.provinceName" id="provinceName" class="input04" value="州/省" onfocus="if(this.value=='州/省')this.value=''" onblur="if(this.value=='')this.value='州/省'"> 
						- 
						<input type="hidden" id="cityTest">
						<input name="lvAccountAddress.cityName" id="cityName" type="text" class="input04" value="县/市" onfocus="if(this.value=='县/市')this.value=''" onblur="if(this.value=='')this.value='县/市'">
						-
						<input name="lvAccountAddress.adress" id="adress" type="text" class="input4" value="街道详细地址" onfocus="if(this.value=='街道详细地址')this.value=''" onblur="if(this.value=='')this.value='街道详细地址'"> 
					</li>
				</ul>
				<ul style="margin-bottom:10px;">
					<li class="wd1"><font class="redfont">*</font>邮政区号：</li>
					<li class="wd2">
					  <input name="lvAccountAddress.postCode" id="postCode" class="input4 error" type="text"><label for="lvAccountAddress.postCode" generated="true" class="error"></label>
					</li>
				</ul>
				<ul id="infoUl" style="display:none">
					<li class="wd1"></li>
					<li class="wd2"><span id="addressInfo"></span></li>
				</ul>
            </div>
              <ul>
                <li class="center"><div id="msgbox" style="color:#f40000;">${requestScope.resultMsg}</div></li>
                <li class="center">
                  <input id="regbtn" class="user_center_bt" type="submit"  value="提交" />
                </li>
              </ul>
            </div>
          </form>
      </div>
      <div class="cb"></div>
    </div>
  </div>

  
  <!--重要提醒 -->
  <div id="mask" style="display:none;"></div>
  <div class="im_remind"  id="win_1" style="top:150px;position: absolute;display: none;z-index:9999;">
    <p class="title">重要提醒</p>
    <p class="close"><a href="javascript:void(0)" onclick="hide('win_1')"><img src="${resDomain}/bmcn/res/images/close.gif" border="0" /></a></p>
    <ul>
        <li><font class="fontwz">启创科技（香港）有限公司网站支持非中国大陆发行的VISA，Master card标志的国际借记卡和信用卡</font><br />
          <br />
          <font class="redfont">1：支付过程中碰到任何问题，请第一时间联系客服，邮箱：<a href="mailto:pay@creatent.net">pay@creatent.net</a>为您处理；</font><br />
          <br />
          <font class="redfont">2：启创科技（香港）有限公司网站利用最先进的Extended Validation (EV) SSL Certificate加密技术及网上验证密码服务，防止不法分子入侵及盗用，令顾客购物更为放心。 </font><br />
          <br />
          <font class="redfont">3：在使用信用卡进行国际交易时可能会被发卡行或金融机构收取费用，并且可能随银行的不同而有所变化。</font><br />
          可能对国际信用卡交易收取的费用类型包括：<br />
          国际交易费(ITF)：您的银行可能增加一项国际交易费，也称为跨境或国外交易费。 <br />
          货币兑换费(FX)：您的银行可能增加一项货币兑换费，也称为外币兑换费或跨币种费用。 <br />
          <br />
          <font class="redfont">4：支付失败可能的原因</font><br />
          A	信息不完整、错误或中途终止支付。消费者在填写信用卡相关信息的时候，不完整或者错误信息，银行无法或拒绝扣款。<br />
          B	未授权交易。消费者用于支付的卡是3D卡，而在支付的过程中未填写3D验证码。所以系统会判为未授权交易，而拒绝扣款。<br />
          C	黑卡，盗卡，复制卡。消费者用于支付的卡系统甄别为黑卡，复制卡等其他非法卡，系统拒绝扣款。<br />
          D	卡余额不足或卡有效期已过。消费者用于支付的卡超出了他的信用额度或卡过了有效期。<br />
          E	风险地区支付。消费者支付的IP来自被国际信用卡组织列为高风险地区，系统会评委高风险交易，而拒绝扣款。如：委内瑞拉。<br />
          F	跨国，跨地区交易支付。消费者用于支付的卡是非本国的卡，存在盗卡消费的嫌疑，所以系统会评为高分析交易，而拒绝扣款。<br />
          G	网络问题。消费者支付的时候网络访问速度慢或其他网络问题，重复刷新支付页面导致多次提交支付申请或者系统未能接收到支付申请等。<br />
          H	同一IP短时间内，重复支付多次。消费者在同一IP短时间内重复支付多次，存在套现、洗钱、诈骗等其他非法行为嫌疑。系统拒绝扣款。<br />
          I	单笔支付限额过高。单笔支付限额超出了消费者卡的最大单笔支付限额或者超出了系统设定的最高单笔收款限额。<br />
          J	不良交易记录的卡。消费者用于支付的卡，存在不良的交易记录，如拒付等。<br />
          K	发卡行拒绝扣款。消费者所消费的发卡行拒绝扣款，具体原因需要消费者联系他的发卡行，了解拒绝原因。 <br />
          <br />
          如若碰到支付失败，请根据失败原因进行更换卡支付或者联系发卡行。 <br />
          需要任何帮助请第一时间联系客服邮箱：pay@creatent.net ，为您处理。 </li>
      <li class="btn">
        <input type="button" class="rBtn" value="确定" onclick="hide('win_1')"/>
      </li>
    </ul>
  </div>
  <!--END 重要提醒 -->
  
</div>
<!--End content-->
<!-- 底部广告位-->
<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
<!-- footer-->
<%@include file="/web/bmcn/common/footer.jsp" %>
</body>
</html>
