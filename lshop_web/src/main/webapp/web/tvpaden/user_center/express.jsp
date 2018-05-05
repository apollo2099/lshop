<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
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
	 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/tvpaden/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">Home</a>--><a href="/web/userCenterManage!getAccount.action">User Center</a>--></font> Track My Order</span></h1> 
			<div class="express">
				<ul class="express_t">
					<li class="title">Delivery Methods</li>
					<li>${lvOrder.expressName }</li>
					<li class="title">Logistics company</li>
					<li>${lvOrder.expressCompany }</li>
					<li class="title">Express No.</li> 
					<li>${lvOrder.expressNum }</li>
				</ul>
				<s:if test="#request.lvOrder.expressName=='DHL'">
				<ul>
					<li><font class="fontwz">>Track My Order</font></li>
					<s:iterator value="dto.events">
						<li><s:date name="date" format="E MM'月'dd,yyyy"/>&nbsp;&nbsp;<s:date name="time" format="HH:mm"/>&nbsp;&nbsp;${serviceEvent }&nbsp;&nbsp;${serviceArea }</li>
					</s:iterator>
					<li class="tips"><img src="${resDomain}/tvpaden/res/images/pos_icon.gif" width="19" height="19" /> The above information is provided by Logistics Company, if the tracking information is not provided or you<br /> 
				    have any questions, please visit <a href="http://www.cn.dhl.com" target="_blank">DHL Express </a>official website or or call their customer .</li>
				</ul>
				</s:if>
				<s:else>
					<li class="tips"><img src="${resDomain}/tvpaden/res/images/pos_icon.gif" width="19" height="19" /> For tracked information or any inquiry, please visit our official website, we hereby show you several frequently used website.</li>
					<li>TVpad default to use <font class="redfont">UPS/DHL/EMS</font>for free shipping globally except for remote areas.</li>
					<li>Check the scope of remote areas:<a href="http://remoteareas.hhl.com/jsp/first.jsp" target="_blank">http://remoteareas.dhl.com/jsp/first.jsp</a>(Inquiry by entering Country and Zip Code)</li>
					<li>UPS waybill inquiry address:<a href="http://www.ups.com/cn" target="_blank">http://www.ups.com/cn</a></li>
					<li>DHL waybill inquiry address:<a href="http://www.cn.dhl.com" target="_blank">http://www.cn.dhl.com</a></li>
					<li>EMS waybill inquiry address:<a href="http://www.shenzhenpost.com.cn" target="_blank">http://www.shenzhenpost.com.cn</a></li>
				</s:else>
			
			</div>
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
						<ul class="sum">Total: ${moneyMark}<u:formatnumber value="${allAmount}"  type="number" groupingUsed="false" maxFractionDigits="2"/> - Coupon: ${moneyMark}<u:formatnumber value="${allCouponPrice }"  type="number" groupingUsed="false" maxFractionDigits="2"/> + Shipping Cost: ${moneyMark}<u:formatnumber value="${lvOrder.postprice }"  type="number" groupingUsed="false" maxFractionDigits="2"/></a></ul>
						<ul class="sum01"> Merchandise Subtotal: <font class="redfont">${moneyMark}<u:formatnumber value="${lvOrder.totalPrice }"  type="number" groupingUsed="false" maxFractionDigits="2"/></font></a></ul>
						<ul class="btn">
						  <p class="user_bt1"><input name="Input" type="submit" onclick="javascript:history.go(-1);" value="Back"  class="qrtj" /></p>
						</ul>
		  </div>
		
	 <!--End right_Frame-->
</div>
<!--End content-->	
</div>		
<!-- footer-->
<%@include file="/web/tvpaden/common/footer.jsp" %>

</body>
</html> 