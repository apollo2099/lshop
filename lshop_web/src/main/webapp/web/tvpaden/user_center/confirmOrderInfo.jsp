<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="cus" uri="/WEB-INF/tld/cus.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>User Center- HUA YANG MALL </title>
<%@include file="/web/tvpaden/common/header.jsp" %>
<link href="${resDomain}/tvpaden/res/css/main.css" rel="stylesheet" type="text/css" />
<link href="${resDomain}/tvpaden/res/css/user_center.css" rel="stylesheet" type="text/css" />
</head>
<body>
<!-- top -->
<%@include file="/web/tvpaden/common/top.jsp" %>
	
<div class="content_main">

	<!-- left_frame -->
	<%@include file="/web/tvpaden/user_center/leftFrame.jsp" %>
	 
	 <div class="right_frame">
	 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/tvpaden/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">Home</a>--><a href="/web/userCenterManage!getAccount.action">User Center</a>--></font> Pay it Now</span></h1> 
	   <div class="usercenter_box">
	     <div class="order"> <span class="order_t"><font class="fontwz">Order comfirmation, please review your order information</font></span>
             <ul>
               <li class="wd1">Contact：</li>
               <li class="wd2">${lvOrderAdress.relName }</li>
             </ul>
	       <ul>
               <li class="wd1">Tel.：</li>
	         <li class="wd2">${lvOrderAdress.mobile }</li>
           </ul>
	       <ul>
               <li class="wd1">Payment Method：</li>
	         <li class="wd2 orangefont"><cus:payName showLength="" payValue="${lvOrder.paymethod}"></cus:payName></li>
           </ul>
	       <ul>
               <li class="wd1">E-mail：</li>
	         <li class="wd2">${lvOrder.userEmail }</li>
           </ul>
	       <ul>
               <li class="wd1">Zip Code：</li>
	         <li class="wd2">${lvOrderAdress.postCode }</li>
           </ul>
	       <ul>
               <li class="wd1">Best receiving time：</li>
	         <li class="wd2">
	          <s:if test="#request.lvOrderAdress.bestDeliveryTime==1">Anytime</s:if>
	          <s:if test="#request.lvOrderAdress.bestDeliveryTime==2">Monday to Friday</s:if>
	          <s:if test="#request.lvOrderAdress.bestDeliveryTime==3">Weekend</s:if>
	          <s:if test="#request.lvOrderAdress.bestDeliveryTime==4">Night Delivery</s:if>
			</li>
           </ul>
	       <ul class="long">
               <li class="wd1">Shipping Address：</li>
	         <li class="wd3" style="word-break:break-all">${lvOrderAdress.adress }&nbsp;${lvOrderAdress.cityName }&nbsp;${lvOrderAdress.provinceName }&nbsp;${lvOrderAdress.contryName }</li>
           </ul>
	       <ul class="long">
               <li class="wd1">Other   Request：</li>
	         <li class="wd3">${lvOrder.orderRemark }</li>
           </ul>
	       <ul class="long">
               <li class="wd1">Payment   status：</li>
	         <li class="long">
		         <s:if test="#request.lvOrder.payStatus==0">
	          		<font class="redfont">Awaiting payment</font> <img src="${resDomain}/tvpaden/res/images/personel_center_icon03.gif" width="16" height="16" /> <a href="/web/userOrder!toConfirmPay?oid=${lvOrder.oid }">Pay it Now</a>
	          	</s:if>
	          	<s:elseif test="#request.lvOrder.payStatus==2">
	          		<font class="redfont">Paid, unconfirmed</font> 
	          	</s:elseif>
	          	<s:elseif test="#request.lvOrder.payStatus==1">
	          		<s:if test="#request.lvOrder.status==0">
	          			<font class="redfont">Paid, awaiting shipping</font> 
	          		</s:if>
	          		<s:elseif test="#request.lvOrder.status==1">
	          			<font class="redfont">Paid, deliveried</font> 
	          		</s:elseif>
	          		<s:elseif test="#request.lvOrder.status==2">
	          			<font class="redfont">Awaiting comment</font> 
	          		</s:elseif>
	          		<s:elseif test="#request.lvOrder.status==4">
	          			<font class="redfont">Finished</font> 
	          		</s:elseif>
	          		<s:elseif test="#request.lvOrder.status==3">
	          			<font class="redfont">Returned</font> 
	          		</s:elseif>
	          	</s:elseif>
	         </li>
           </ul>
	       <table width="710" height="188" border="0" cellpadding="0" cellspacing="1" bgcolor="#E8E5E5">
               <tr>
                 <td height="33" colspan="6" bgcolor="#F9F8F8"><font class="redfont fontwz redfont">Order No.：${lvOrder.oid}</font></td>
               </tr>
               <tr style="text-align:center;">
                 <td height="28" bgcolor="#F9F8F8" style="text-align:center;"></td>
                 <td bgcolor="#F9F8F8" style="text-align:center;"> Items Name </td>
                 <td bgcolor="#F9F8F8" style="text-align:center;">Price </td>
                 <td bgcolor="#F9F8F8" style="text-align:center;">Quantity </td>
                 <td bgcolor="#F9F8F8" style="text-align:center;">Subtotal</td>
               </tr>
               <c:foreach items="${objList}" var="obj">
               <tr style="text-align:center;">
                 <td height="80" bgcolor="#F9F8F8" style="text-align:center;" ><img src="${obj[0].pimage }" width="70px" height="60px"/></td>
                 <td bgcolor="#F9F8F8" style="text-align:center;"><font class="fontwz">${obj[0].productName }</font></td>
                 <td bgcolor="#F9F8F8" style="text-align:center;"><font class="fontwz">${moneyMark}${obj[1].oprice }</font></td>
                 <td bgcolor="#F9F8F8" style="text-align:center;"><font class="fontwz">${obj[1].pnum }</font></td>
                 <td bgcolor="#F9F8F8" style="text-align:center;"><font class="redfont fontwz">${moneyMark}<u:formatnumber value="${obj[1].pnum*obj[1].oprice }"  type="number" groupingUsed="false" maxFractionDigits="2"/></font></td>
               </tr>
               </c:foreach>
               <tr style="text-align:right;">
               	 <td height="42" colspan="5" bgcolor="#F9F8F8" style="padding-right:30px;"><div align="right">Total: ${moneyMark}<u:formatnumber value="${allAmount}"  type="number" groupingUsed="false" maxFractionDigits="2"/> - Coupon:${moneyMark}<u:formatnumber value="${allCouponPrice }"  type="number" groupingUsed="false" maxFractionDigits="2"/>+ Shipping Cost: ${moneyMark}<u:formatnumber value="${lvOrder.postprice }"  type="number" groupingUsed="false" maxFractionDigits="2"/></div></td>
               </tr>
               <tr style="text-align:right;">
               	 <td height="42" colspan="5" bgcolor="#F9F8F8" style="padding-right:30px;"><div align="right">Merchandise Subtotal：<font class="fontwz redfont">${moneyMark}<u:formatnumber value="${lvOrder.totalPrice }"  type="number" groupingUsed="false" maxFractionDigits="2"/></font></div></td>
               </tr>        
           </table>
	       <div class="bby">
	       		<p class="user_bt"><input name="" type="submit" onclick="window.location.href='/web/userOrder!toPay.action?oid=${lvOrder.oid}'" value="Confirm"  class="qrtj" /></p>
	       		<p class="user_bt"><input name="" type="submit" onclick="javascript:history.go(-1);" value="Back"  class="qrtj" /></p>	
	   </div>	
  </div>
	 <!--End right_Frame-->
</div>
	
<!-- footer-->
<%@include file="/web/tvpaden/common/footer.jsp" %>

</body>
</html> 