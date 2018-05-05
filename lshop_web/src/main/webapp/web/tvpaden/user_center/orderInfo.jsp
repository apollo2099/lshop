<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="cus" uri="/WEB-INF/tld/cus.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>User Center- HUA YANG MALL</title>
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
	 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/tvpaden/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">Home</a>--><a href="/web/userCenterManage!getAccount.action">User Center</a>--></font> My Order</span></h1> 
		
		<table width="760" border="0" cellpadding="1"  cellspacing="1" bgcolor="#E3E2E2" >
        <tr>
          <td height="40" colspan="4" bgcolor="#F2F1F1"><font class="bfont fontwz">Current Order Status：</font> 
          	<s:if test="#request.lvOrder.payStatus==0">
          		<font class="redfont"> Awaiting payment</font> <img src="${resDomain}/tvpaden/res/images/personel_center_icon03.gif" width="16" height="16" /> 
          		<s:if test="#request.lvOrder.paymethod!=4">
          			<a href="/web/userOrder!toConfirmPay.action?oid=${lvOrder.oid }">Pay now</a>
          		</s:if>
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
          </td>
        </tr>
        <tr>
          <td width="117"  height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>Order   No.：<br />
          </strong></td>
          <td width="273" height="30" bgcolor="#FFFFFF" style="text-align:center">${lvOrder.oid}<br /></td>
          <td width="133" height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>Order   Date：<br />
          </strong></td>
          <td width="209" height="30" bgcolor="#FFFFFF" style="text-align:center"><s:date name="#request.lvOrder.createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
        </tr>
        <tr>
          <td width="117" height="30" bordercolor="#F6D0C9" bgcolor="#FFFFFF" style="text-align:right"><strong>E-mail：<br />
          </strong></td>
          <td  width="273" height="30" bordercolor="#F6D0C9" bgcolor="#FFFFFF" style="text-align:center">${lvOrder.userEmail }<br /></td>
          <td width="133" height="30" bordercolor="#F6D0C9" bgcolor="#FFFFFF" style="text-align:right"><strong>Payment   Methods：<br />
          </strong></td>
          <td width="209" height="30" bordercolor="#F6D0C9" bgcolor="#FFFFFF" style="text-align:center"><cus:payName showLength="" payValue="${lvOrder.paymethod}"></cus:payName></td>
        </tr>
        <tr>
          <td width="117" height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>Name：<br />
          </strong></td>
          <td  width="273" height="30" bgcolor="#FFFFFF" style="text-align:center">${lvOrderAdress.relName }<br /></td>
          <td  width="133" height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>Amount：<br />
          </strong></td>
          <td  width="209" height="30" bgcolor="#FFFFFF" style="text-align:center">${moneyMark} ${lvOrder.totalPrice}</td>
        </tr>
        <tr>
          <td  width="117" height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>Mobile No.：<br />
          </strong></td>
          <td  width="273" height="30" bgcolor="#FFFFFF" style="text-align:center">${lvOrderAdress.mobile }<br /></td>
          <td  width="133" height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>Tel.：<br />
          </strong></td>
          <td  width="209" height="30" bgcolor="#FFFFFF" style="text-align:center">${lvOrderAdress.tel }</td>
        </tr>
        <tr>
          <td  width="117" height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>Receiving methods：<br />
          </strong></td>
          <td  width="273" height="30" bgcolor="#FFFFFF" style="text-align:center">International Express</td>
          <td  width="133" height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>Zip Code：<br />
          </strong></td>
          <td  width="209" height="30" bgcolor="#FFFFFF" style="text-align:center">${lvOrderAdress.postCode }</td>
        </tr>
        <tr>
          <td  width="117" height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>Shipping Address：<br />
          </strong></td>
          <td height="30" colspan="3" bgcolor="#FFFFFF" style="text-align:center;word-break:break-all">${lvOrderAdress.adress }&nbsp;${lvOrderAdress.cityName }&nbsp;${lvOrderAdress.provinceName }&nbsp;${lvOrderAdress.contryName }</td>
        </tr>
        <tr>
          <td width="117" height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>Best receiving time：<br />
          </strong></td>
          <td height="30" colspan="3" bgcolor="#FFFFFF" style="text-align:center">
	          <s:if test="#request.lvOrderAdress.bestDeliveryTime==1">Anytime</s:if>
	          <s:if test="#request.lvOrderAdress.bestDeliveryTime==2">Monday to Friday</s:if>
	          <s:if test="#request.lvOrderAdress.bestDeliveryTime==3">Weekend</s:if>
	          <s:if test="#request.lvOrderAdress.bestDeliveryTime==4">Night Delivery</s:if>
          </td>
        </tr>
        <tr>
          <td  width="117" height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>Other Request：<br />
          </strong></td>
          <td width="643" height="30" colspan="3" bgcolor="#FFFFFF" style="text-align:center;word-break:break-all">${lvOrder.orderRemark }<br /></td>
        </tr>
       <s:if test="#request.lvOrder.status!=0">
		    <tr>
	          <td height="40" colspan="4" bgcolor="#F2F1F1"><font class="bfont fontwz">Track My Order </font><a href="/web/userOrder!doLogisticsTrackingInfo.action?oid=${oid}"><font class="redfont">(Check logistics status)</font></a></td>
	        </tr>
	        <tr>
	          <td bgcolor="#FFFFFF" style="text-align:right"><strong>Express   company：<br /> </strong></td>
	          <td height="30" bgcolor="#FFFFFF" style="text-align:center">${lvOrder.expressCompany  }</td>
	          <td bgcolor="#FFFFFF" style="text-align:right"><strong>Express   No.：<br /></strong></td>
	          <td height="30" bgcolor="#FFFFFF" style="text-align:center"><a href="/web/userOrder!doLogisticsTrackingInfo.action?oid=${lvOrder.oid}" title="Check logistics status">${lvOrder.expressNum }</a></td>
	        </tr>
	        <tr>
	          <td bgcolor="#FFFFFF" style="text-align:right"><strong>Delivery   Date：</strong></td>
	          <td height="30" bgcolor="#FFFFFF" style="text-align:center">${lvOrder.shipTime }</td>
	          <td bgcolor="#FFFFFF" style="text-align:right"><strong>Remarks：</strong></td>
	          <td height="30" bgcolor="#FFFFFF" style="text-align:center" >${lvOrder.sendRemark }</td>
	        </tr>
        </s:if>
        <s:if test="#request.lvOrder.payStatus!=0 and #request.lvOrder.paymethod==4">
	        <tr>
	           <td height="40" colspan="4" bgcolor="#F2F1F1"><font class="bfont fontwz">Western Union information</font></td>
	        </tr>
		    <tr>
	          <td bgcolor="#FFFFFF" style="text-align:right"><strong>Payer：<br /></strong></td>
	          <td height="30" bgcolor="#FFFFFF" style="text-align:center">${lvWesternInfo.remitter }</td>
	          <td bgcolor="#FFFFFF" style="text-align:right"><strong>Amount：<br /></strong></td>
	          <td height="30" bgcolor="#FFFFFF" style="text-align:center">USD ${lvWesternInfo.remittance  }</td>
	        </tr>
	        <tr>
	          <td bgcolor="#FFFFFF" style="text-align:right"><strong>MTCN：</strong></td>
	          <td height="30" bgcolor="#FFFFFF" style="text-align:center">${lvWesternInfo.mtcn }</td>
	          <td bgcolor="#FFFFFF" style="text-align:right"><strong>Country/Region：</strong></td>
	          <td height="30" bgcolor="#FFFFFF" style="text-align:center">${lvWesternInfo.adress }&nbsp;${lvWesternInfo.contryName }</td>
	        </tr>
        </s:if>
      </table>	
			
			<div class="buy_order">
						<ul>
							<li class="buy_order01">
								<p>&nbsp;</p>
								<p class="title">Item Name</p>
								<p>Price</p>
								<p>Quantity</p>
								<p>Subtotal</p>
							</li>
							<c:foreach items="${objList}" var="obj">
								<li>
									<p><img src="${obj[0].pimage }" width="70px" height="60px"/></p>
									<p class="title">${obj[0].productName }</p>
									<p><font class="redfont fontwz">${moneyMark}${obj[1].oprice }</font></p>
									<p>${obj[1].pnum }</p>
									<p><font class="redfont fontwz">${moneyMark}<u:formatnumber value="${obj[1].pnum*obj[1].oprice }"  type="number" groupingUsed="false" maxFractionDigits="2"/></font></p>
								</li>
							</c:foreach>
						</ul>
						<ul class="sum">Total：${moneyMark}<u:formatnumber value="${allAmount}"  type="number" groupingUsed="false" maxFractionDigits="2"/> - Coupon：${moneyMark}<u:formatnumber value="${allCouponPrice }"  type="number" groupingUsed="false" maxFractionDigits="2"/>+ Shipping Cost: ${moneyMark}<u:formatnumber value="${lvOrder.postprice }"  type="number" groupingUsed="false" maxFractionDigits="2"/></a></ul>
						<ul class="sum01">Merchandise Subtotal：<font class="redfont">${moneyMark}<u:formatnumber value="${lvOrder.totalPrice }"  type="number" groupingUsed="false" maxFractionDigits="2"/> </font></a></ul>
						<div class="bby">
							<s:if test="#request.lvOrder.payStatus==0">
								<s:if test="#request.lvOrder.paymethod==4">
									<p class="user_bt"><input name="" type="submit" onclick="window.location.href='/web/userOrder!toOrderWestern?oid=${lvOrder.oid }'" value="Pay now"  class="qrtj" /></p>
								</s:if>
								<s:elseif test="#request.lvOrder.paymethod==7"></s:elseif>
								<s:else>
									<p class="user_bt"><input name="" type="submit" onclick="window.location.href='/web/userOrder!toConfirmPay.action?oid=${lvOrder.oid }'" value="Pay now"  class="qrtj" /></p>
								</s:else>
							</s:if>
						  	<p class="user_bt"><input name="" type="submit" onclick="window.location.href='/web/userOrder!getOrderlist.action'" value="Back"  class="qrtj" /></p>
						  					  
						</div>
		  </div>
		
	 <!--End right_Frame-->
</div>
	
<!-- footer-->
<%@include file="/web/tvpaden/common/footer.jsp" %>

</body>
</html> 