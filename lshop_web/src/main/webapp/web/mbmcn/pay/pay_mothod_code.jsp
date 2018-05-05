<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="width=device-width,user-scalable=no" name="viewport">
<title>訂單支付</title>
<!-- 加载公共JS -->
<jsp:include page="/web/${flag}/common/top.jsp"/>
</head>
<body>
<header>
   <div class="top">
      <div class="title1">
        <h1>订单支付</h1>
      </div>
   </div>
</header>

<article>
  <div class="westerh2">
  <s:if test="#request.lvOrder.paymethod==11||#request.lvOrder.paymethod==12||#request.lvOrder.paymethod==13||#request.lvOrder.paymethod==15||#request.lvOrder.paymethod==16||#request.lvOrder.paymethod==17">
     <h2><s:if test="#request.lvOrder.paymethod==11||#request.lvOrder.paymethod==15">VISA</s:if>
     <s:elseif test="#request.lvOrder.paymethod==12||#request.lvOrder.paymethod==16">Master</s:elseif>
     <s:if test="#request.lvOrder.paymethod==13||#request.lvOrder.paymethod==17">JCB</s:if> Credit or Dedit card </h2>
  </div>
  <div class="westerh2" style="padding-bottom:20px">
     <h3>Order Information</h3>
     
     <table width="94%" border="0" align="center">
  <tr>
    <td height="25" valign="middle">Merchant：</td>
  </tr>
  <tr>
    <td height="25" valign="middle">BANANA JOINT STOCK LIMITED</td>
  </tr>
  <tr>
    <td height="25" valign="middle">Order No.：</td>
  </tr>
  <tr>
    <td height="25" valign="middle">${lvOrder.oid}</td>
  </tr>
  <tr>
    <td height="25" valign="middle">Amount：</td>
  </tr>
  <tr>
    <td height="25" valign="middle">${lvOrder.currency} ${lvOrder.totalPrice}</td>
  </tr>
  </table>
  </div>
  </s:if>
  
  <div class="westerh3">
		<div align="center">
			<div id="fromdiv">
					        <s:if test="#request.lvPaymentStyle.payChannel==1">
						    <s:action name="payCenter"  namespace="/web" executeResult="true">
							<s:param name="js_return"  value=""/>
							</s:action>
							</s:if>
							<s:else>
							<s:action name="payparam"  namespace="/web" executeResult="true">
							<s:param name="js_return"  value=""/>
							</s:action>
							</s:else>
			</div>
		</div>
		<div class="cb"></div>
	</div>
	
</article>

<!-- 分享 -->
<%@include file="/web/mbmcn/common/share.jsp" %>
<!-- footer -->
<jsp:include page="/web/${flag}/common/footer.jsp"/>
<script type="text/javascript">
$('#iframepage').attr('width', '94%').attr('height','320');
</script>
</body>
</html>