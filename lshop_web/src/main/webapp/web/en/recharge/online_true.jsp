<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>User Center_TVpad Mall</title>
<link href="${resDomain}/en/res/css/user_center.css" rel="stylesheet" type="text/css" />
<style  type="text/css">
.addrOption{color:#505050;font-family:"微软雅黑";font-size:12px;}
.addrItem{float:left;height:25px;line-height:25px;width:100px;}
.addrItem label{height:25px;line-height:25px;display:inline-block;}
.addrItem input{vertical-align:middle;margin:0px;}
.addrInfo{padding-left:100px;}
.addrInfo span{height:25px;line-height:25px;display:inline-block;}
.addrList{display:none;padding-left:129px;padding-top: 10px}
</style>
<!-- 加载公共JS -->
<%@include file="/web/en/common/top.jsp"%>
<script type="text/javascript" src="${resDomain}/en/res/js/myjs.js"></script>
<script type="text/javascript" src="${resDomain}/en/res/js/address.js"></script>
<script type="text/javascript">
function doAction(){
	var paymethod=$("input[name='paymethod']:checked");
    if(paymethod.length==0){
       $("#msgbox").html("Please select a mode of payment");
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
<%@include file="/web/en/common/header.jsp"%>
<div class="content_main">
  <!-- left_frame -->
  <%@include file="/web/en/user_center/leftFrame.jsp" %>
  <div class="right_frame">
    <h1><font class="bfont"><img src="${resDomain}/en/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">Home</a> --><a href="/web/userCenterManage!getAccount.action"> User Center</a> --></font> Account Balance</h1>
    <div class="usercenter_box">
          <form action="/web/recharge!toPay.action" method="post"  onsubmit="return doAction()" id="addressFormVbId">
            <input type="hidden" name="rnum" value="${rnum}">
            <div class="user_center_right">
              <ul>
                <li>
                  <p class="text">Order：</p>
                </li>
                <li class="choose_3">Order No.：${recharge.rnum}</li>
                  <li  class="choose_3">Account：${recharge.accounts}</li>
                <li class="choose_3">Purchase quantity：${recharge.vbNum }</li>
                <li class="choose_3">Amount payable：<span id="currency">${recharge.currency}</span> <span id="money">${recharge.money}</span></li>
              </ul>
              <div class="line"></div>
              <ul>
                <li>
                  <p class="text">Payment options：</p>
                </li>
                <c:foreach var="paymentStyle" items="${paymentStyleList}">
                	<li class="choose_3"> 
		                <input name="paymethod" type="radio" value="${paymentStyle.payValue}"  <c:if test="${paymentStyle.payValue eq recharge.rtype}">checked</c:if> /> 
		                ${paymentStyle.payName}
		                <font class="bluefont"><a href="javascript:void(0)" onclick="show('win_1')">Important!</a></font> 
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
            <div style="padding-left:30px;display:none;" id="payInfoId">
              <div id="objAddr">
			 <div>
			 <label for="btnAddr" style="margin-left:12px;float:left;">Billing information  <s:if test="#request.addressList!=null"> <span style="padding-left: 60px;color: bl;color: #B68571;">Billing Address：</span> </s:if> </label>
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
              <ul style="margin-bottom:10px;">
						<li class="wd1"><font class="redfont">*</font> Full Name：</li>
						<li class="wd2"><input name="lvAccountAddress.relName" class="input2"  type="text" id="relName"/>
						</li>
				    </ul>
				    
				  <ul style="margin-bottom:10px;">
					<li class="wd1"> Mobile No.：</li>
					<li class="wd2"><input id="mobile" name="lvAccountAddress.mobile" class="input2"  onkeypress="onlyNumber(event)" type="text" id="mobile"/><span id="mobileInfo"></span>
					</li>
				</ul>
				<ul style="margin-bottom:10px;">
					<li class="wd1">Phone No.：</li>
					<li class="wd2"><input id="tel" name="lvAccountAddress.tel" class="input2"  type="text" id="tel"/><span id="telInfo"></span>
					</li>
				</ul>
				<ul style="margin-bottom:10px;">
					<li class="wd1"><font class="redfont">*</font> Billing Address：</li>
					<li class="wd2">
					<input  name="lvAccountAddress.adress" id="adress" type="text" class="input2" value="Street address" onfocus="if(this.value=='Street address')this.value=''" onblur="if(this.value=='')this.value='Street address'"/> 
					- <input  name="lvAccountAddress.cityName" id="cityName" type="text" class="input3" value="County/City" onfocus="if(this.value=='County/City')this.value=''" onblur="if(this.value=='')this.value='County/City'"/>
					
					- <input type="hidden" id="test" />
					<input type="text" name="lvAccountAddress.provinceName" id="provinceName" class="input3" value="State/Province"  onfocus="if(this.value=='State/Province')this.value=''" onblur="if(this.value=='')this.value='State/Province'"/> 
						- <select name="lvAccountAddress.contryId" id="contryId" onchange="contryChange()" style="width:120px;" class="input2">
							<option value="">--Choose your country--</option>
							<c:foreach items="${contryList}" var="c">
								<option value="${c.id}"  <c:if test="${c.id==lvAccountAddress.contryId  }">selected</c:if>>${c.nameen}</option>
							</c:foreach>
						</select><br />
						<input type="hidden" name="lvAccountAddress.contryName" id="contrynameId"  value=""/>
					</li>
				</ul>
				<ul>
					<li class="wd1"><font class="redfont">*</font> Zip：</li>
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
                  <input id="regbtn" class="user_center_bt" type="submit" value="Submit" />
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
    <p class="title">Important notes</p>
    <p class="close"><a href="javascript:void(0)" onclick="hide('win_1')"><img src="${resDomain}/en/res/images/close.gif" border="0" /></a></p>
    <ul>
        <li>
<font class="fontwz">Create New Technology (HK) Limited website supports payment by credit card and debit card marked with VISA and Master card that issued beyond Chinese Mainland.</font><br />
		    <br />
		 <font class="redfont">1. If you have any problems in the course of payment, please contact our customer service at <a href="mailto:pay@creatent.net">pay@creatent.net</a> as soon as possible, and we’ll handle it for you.</font><br />
    <br />
 <font class="redfont">2. Create New Technology (HK) Limited website adopts the most advanced Extended Validation (EV) SSL Certificate encryption techniques and online password authentication service to protect your account from being attacked and embezzled and provide you with a safe and secure shopping environment.</font><br />
    <br />

 
 <font class="redfont">
3. When making payments across border with credit card, additional fees (varying from bank to bank) may be charged by your card-issuing bank or other banking institutions. <br />
</font>Followings are possible types of fees that may be charged against international credit card transaction<br />

     A.	International Transaction Fee (ITF): your card-issuing bank may charge the ITF (also called cross border transaction fee).<br />
     B.	Foreign Exchange (FX): your card-issuing bank may charge the foreign exchange fees (also called foreign currency fee).<br />
    <br />
 
<font class="redfont">
4. Followings are possible reasons accounting for most cases of credit card payment failure</font><br />
A.	The Credit Card information you entered is incomplete or incorrect, and the bank cannot or refuse to make payments. <br />
B.	If your Credit Card is a 3D card and you failed to enter the 3D security verification, the bank system defaults the transaction as unauthorized transaction and refuse to make payments.<br />
C.	Your credit card is identified as illegal card, and the bank refuse to make payments.<br />
D.	Your Credit Card is overdue or the amount of payment exceeds the credit limit. <br />
E.	Your IP location is identified as high-risk transaction location (such as Venezuela) by VISA    International Service Association, and the bank system defaults the transaction as high risk transaction and refuse to make payments.<br />
F.	Your Credit Card is being used beyond the card-issuing country which credit card fraud is suspected, and the bank system identifies the transaction as high risk transaction and refuses to make payments.<br />
G.	Your network environment is unstable and your repeated page refreshes can result in repeated submission or submission failure.<br />
H.	Your Credit Card has made multiple payments in a short period at the same IP address, which illegal operation (such as cash out, money laundering, fraud, etc.) is suspected, and the bank refuses to make payments.<br />
I.	The amount of a single payment exceeds the maximum limit of your Credit Card or the default maximum limit of the bank system.<br />
 J.	There are bad records (such as, refusing payments) on your Credit Card, and the bank refuses to make payments.<br />
 K.	Your card-issuing bank refuses to make payments. For detailed reasons, please contact your card-issuing bank.<br />
<br />
If you failed to make payments, please check the above reasons and change a credit card or contact your card-issuing bank.<br />
For any help, please contact our customer service at pay@creatent.net.<br />
</li>
      <li class="btn">
        <input type="button" class="rBtn" value="OK" onclick="hide('win_1')"/>
      </li>
    </ul>
  </div>
  <!--END 重要提醒 -->
  
</div>
<!--End content-->
<!-- 底部广告位-->
<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
<!-- footer-->
<%@include file="/web/en/common/footer.jsp" %>
</body>
</html>
