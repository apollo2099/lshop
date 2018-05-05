<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>User Center _TVpad Mall</title>
		<link href="${resDomain}/en/res/css/user_center.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<%@include file="/web/en/common/top.jsp" %>
	</head>
	
	<body>	
		<!-- 获取商城头部文件 -->
		<%@include file="/web/en/common/header.jsp" %>
		
		<div class="content_main">
	
			<!-- left_frame -->
			<%@include file="/web/en/user_center/leftFrame.jsp" %>
		 
			 <div class="right_frame">
			 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/en/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">Home</a>--><a href="/web/userCenterManage!getAccount.action">User Center </a>--></font> Tracking Shipments</span></h1> 
				<div class="express">
					<ul class="express_t">
						<li class="title">Delivery Method</li>
						<li>${lvOrder.expressName }</li>
						<li class="title">Logistics Company</li>
						<li>${lvOrder.expressCompany }</li>
						<li class="title">Tracking No.</li>
						<li>${lvOrder.expressNum }</li>
					</ul>
					<s:if test="#request.lvOrder.expressName=='DHL'">
					<ul>
						<li><font class="fontwz">Logistics tracking</font></li>
						<s:iterator value="dto.events">
							<li><s:date name="date" format="E MM'月'dd,yyyy"/>&nbsp;&nbsp;<s:date name="time" format="HH:mm"/>&nbsp;&nbsp;${serviceEvent }&nbsp;&nbsp;${serviceArea }</li>
						</s:iterator>
						<li class="tips"><img src="${resDomain}/en/res/images/pos_icon.gif" width="19" height="19" /> The above information is offered by related logistics Company. If there is no tracking information offered or you have questions, please visit <a href="#">DHL</a> official website or call the customer service.</li>
					</ul>
					</s:if>
					<s:else>
						<li class="tips"><img src="${resDomain}/en/res/images/pos_icon.gif" width="19" height="19" /> For tracked information or any inquiry, please visit our official website, we hereby show you several frequently used website.</li>
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
							<p><font class="redfont fontwz">${moneyMark}${obj[2] }</font></p>
						</li>
						</c:foreach>
					</ul>
					<ul class="sum">Total：${moneyMark}${allAmount} - Coupon：${moneyMark}${allCouponPrice } + Freight：${moneyMark}${lvOrder.postprice }</a></ul>
					<ul class="sum01">Amount Payable：<font class="redfont">${moneyMark}${lvOrder.totalPrice }</font></a></ul>
					<ul class="btn">
					  <input type="button" onclick="javascript:history.go(-1);" value="Back" class="user_center_bt" />
					</ul>
		  		</div>
			
		 	<!--End right_Frame-->
			</div>
		<!--End content-->	
		</div>	
		
		<!-- 底部广告位-->
		<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
			
		<!-- footer-->
		<%@include file="/web/en/common/footer.jsp" %>
		
	</body>
</html> 