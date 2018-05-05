<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用戶中心_TVpad商城</title>
<link href="${resDomain}/www/res/css/user_center.css" rel="stylesheet" type="text/css" />
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
<%@include file="/web/www/common/top.jsp"%>
<script type="text/javascript" src="${resDomain}/www/res/js/myjs.js"></script>
<script type="text/javascript" src="${resDomain}/www/res/js/address.js"></script>
<script type="text/javascript">
function doAction(){
	var paymethod=$("input[name='paymethod']:checked");
    if(paymethod.length==0){
       $("#msgbox").html("請選擇支付方式！");
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
        <div class="usercenter_box">
          <form action="/web/recharge!toPay.action" method="post"  onsubmit="return doAction()" id="addressFormVbId">
            <input type="hidden" name="rnum" value="${rnum}">
            <input type="hidden" id="sourceCurrencyId" value="${recharge.currency}"/>
            <input type="hidden" id="sourceMoneyId" value="${recharge.money}"/>
            <div class="user_center_right">
              <ul>
                <li>
                  <p class="text">訂單：</p>
                </li>
                <li class="choose_3">訂單號：${recharge.rnum}</li>
                <li  class="choose_3">賬號：${recharge.accounts}</li>
                <li class="choose_3">購買V幣數量：${recharge.vbNum }</li>
                <li class="choose_3">應付金額：<span id="currency">${recharge.currency}</span> <span id="money">${recharge.money}</span></li>
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
			 <div id="objAddr">
			 <div>
			 <label for="btnAddr" style="margin-left:64px;float:left;">账单信息  <s:if test="#request.addressList!=null"> <span style="padding-left: 25px;color: bl;color: #B68571;">使用收货地址：</span> </s:if> </label>
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
                        <span>${address.adress }&nbsp;${address.cityName}&nbsp;${address.provinceName }&nbsp;${address.contryName }</span><br/>
                        <span>${address.postCode}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <span>${address.tel} &nbsp;${ address.mobile}</span>
                    </div>
                </div>
                </s:iterator>
           </s:if>
           </div>
        </div>
        <br/>
                  <ul>
						<li class="wd1"><font class="redfont">*</font>姓名：</li>
						<li class="wd2"><input name="lvAccountAddress.relName" class="input2"  type="text" id="relName"/><font></font>
						</li>
				  </ul>
                  <ul style="margin-bottom:10px;">
                    <li class="wd1">手機號碼：</li>
                    <li class="wd2"><input id="mobile" name="lvAccountAddress.mobile" class="input2 valid" onkeypress="onlyNumber(event)" type="text"><span id="mobileInfo"></span>
                    </li>
				</ul>
				<ul style="margin-bottom:10px;">
					<li class="wd1">固定電話：</li>
					<li class="wd2"><input id="tel" name="lvAccountAddress.tel" class="input2 valid" type="text"><span id="telInfo"></span>
					</li>
				</ul>
				<ul style="margin-bottom:10px;">
					<li class="wd1"><font class="redfont">*</font>账单地址：</li>
					<li class="wd2">
					<input  name="lvAccountAddress.adress" id="adress" type="text" class="input2" value="街道詳細地址" onfocus="if(this.value=='街道詳細地址')this.value=''" onblur="if(this.value=='')this.value='街道詳細地址'"/> 
					-<input  name="lvAccountAddress.cityName" id="cityName" type="text" class="input2" value="縣/市" onfocus="if(this.value=='縣/市')this.value=''" onblur="if(this.value=='')this.value='縣/市'"/>
					-<!--<input  name="lvAccountAddress.provinceId" id="provinceName" type="text" class="input2" value="洲/省" onfocus="if(this.value=='洲/省')this.value=''" onblur="if(this.value=='')this.value='洲/省'"/>  -->
					<input type="hidden" id="test" />
					<input type="text" name="lvAccountAddress.provinceName" id="provinceName" class="input2" value="洲/省"  onfocus="if(this.value=='洲/省')this.value=''" onblur="if(this.value=='')this.value='洲/省'"/> 
					</select>
						-<select name="lvAccountAddress.contryId" id="contryId" onchange="contryChange()" class="input2">
							<option value="">--請選擇國家--</option>
							<c:foreach items="${contryList}" var="c">
								<option value="${c.id}"  <c:if test="${c.id==lvAccountAddress.contryId  }">selected</c:if>>${c.nameen}</option>
							</c:foreach>
						</select><font class="redfont">*</font>
						<input type="hidden" name="lvAccountAddress.contryName" id="contrynameId"  value=""/>
					</li>
				</ul>
				<ul>
					<li class="wd1"><font class="redfont">*</font>郵政區號：</li>
					<li class="wd2">
					  <input name="lvAccountAddress.postCode" id="postCode" class="input2"  type="text" />
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
                  <input id="regbtn" class="user_center_bt" type="submit" value="提交" />
                </li>
              </ul>
            </div>
          </form>
          <div class="cb"></div>
        </div>
      </div>
      <div class="cb"></div>
    </div>
  </div>
  
  
  <!--重要提醒 -->
  <div id="mask" style="display:none;"></div>
  <div class="im_remind"  id="win_1" style="top:150px;position: absolute;display: none;z-index:9999;">
    <p class="title">重要提醒</p>
    <p class="close"><a href="javascript:void(0)" onclick="hide('win_1')"><img src="${resDomain}/www/res/images/close.gif" border="0" /></a></p>
    <ul>
        <li><font class="fontwz">啟創科技（香港）有限公司網站支持非中國大陸發行的VISA，Master card標誌的國際借記卡和信用卡</font><br />
          <br />
          <font class="redfont">1：支付過程中碰到任何問題，請第一時間聯繫客服，郵箱：<a href="mailto:pay@creatent.net">pay@creatent.net</a>為您處理；</font><br />
          <br />
          <font class="redfont">2：啟創科技（香港）有限公司網站利用最先進的Extended Validation (EV) SSL Certificate加密技術及網上驗證密碼服務，防止不法分子入侵及盜用，令顧客購物更為放心。 </font><br />
          <br />
          <font class="redfont">3：在使用信用卡進行國際交易時可能會被發卡行或金融機構收取費用，並且可能隨銀行的不同而有所變化。</font><br />
          可能對國際信用卡交易收取的費用類型包括：<br />
          國際交易費(ITF)：您的銀行可能增加一項國際交易費，也稱為跨境或國外交易費。 <br />
          貨幣兌換費(FX)：您的銀行可能增加一項貨幣兌換費，也稱為外幣兌換費或跨幣種費用。 <br />
          <br />
          <font class="redfont">4：支付失敗可能的原因</font><br />
          A 信息不完整、錯誤或中途終止支付。消費者在填寫信用卡相關信息的時候，不完整或者錯誤信息，銀行無法或拒絕扣款。 <br />
          B 未授權交易。消費者用於支付的卡是3D卡，而在支付的過程中未填寫3D驗證碼。所以系統會判為未授權交易，而拒絕扣款。 <br />
          C 黑卡，盜卡，複製卡。消費者用於支付的卡系統甄別為黑卡，複製卡等其他非法卡，系統拒絕扣款。 <br />
          D 卡餘額不足或卡有效期已過。消費者用於支付的卡超出了他的信用額度或卡過了有效期。 <br />
          E 風險地區支付。消費者支付的IP來自被國際信用卡組織列為高風險地區，系統會評委高風險交易，而拒絕扣款。如：委內瑞拉。 <br />
          F 跨國，跨地區交易支付。消費者用於支付的卡是非本國的卡，存在盜卡消費的嫌疑，所以系統會評為高分析交易，而拒絕扣款。 <br />
          G 網絡問題。消費者支付的時候網絡訪問速度慢或其他網絡問題，重複刷新支付頁面導致多次提交支付申請或者係統未能接收到支付申請等。 <br />
          H 同一IP短時間內，重複支付多次。消費者在同一IP短時間內重複支付多次，存在套現、洗錢、詐騙等其他非法行為嫌疑。系統拒絕扣款。 <br />
          I 單筆支付限額過高。單筆支付限額超出了消費者卡的最大單筆支付限額或者超出了系統設定的最高單筆收款限額。 <br />
          J 不良交易記錄的卡。消費者用於支付的卡，存在不良的交易記錄，如拒付等。 <br />
          K 發卡行拒絕扣款。消費者所消費的發卡行拒絕扣款，具體原因需要消費者聯繫他的發卡行，了解拒絕原因。 <br />
          <br />
       	  如若碰到支付失敗，請根據失敗原因進行更換卡支付或者聯繫發卡行。 <br />
         需要任何幫助請第一時間聯繫客服郵箱：pay@creatent.net ，為您處理。 </li>
      <li class="btn">
        <input type="button" class="rBtn" value="確定" onclick="hide('win_1')"/>
      </li>
    </ul>
  </div>
  <!--END 重要提醒 -->
  
</div>
<!--End content-->
<!-- 底部广告位-->
<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
<!-- footer-->
<%@include file="/web/www/common/footer.jsp" %>
</body>
</html>
