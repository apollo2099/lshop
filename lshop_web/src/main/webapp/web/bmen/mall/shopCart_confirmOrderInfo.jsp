<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="ad" uri="/WEB-INF/tld/gv-ad.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<%    
  response.setHeader("Pragma","No-cache");    
  response.setHeader("Cache-Control","no-cache");    
  response.setDateHeader("Expires",   0);    
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Confirm Order_banana Mall</title>
		<link href="${resDomain}/bmen/res/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${resDomain}/bmen/res/css/buy.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<%@include file="/web/bmen/common/top.jsp" %>
	</head>
	
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/bmen/common/header.jsp" %>
		
		<div class="buy">	
			<div class="buy_top"><img src="${resDomain}/bmen/res/images/shopping01.gif" /></div>
			<div class="buy_lc03"></div>
			<div class="fill_orders">
				<h1>Confirm Order</h1>
				<div class="fill_orders01">
					<div class="receive_info">
						<ul>
							<li class="wd1">
								<font class="redfont">*</font>Full Name：
							</li>
							<li class="wd2">
								${address.relName }
							</li>
						</ul>

						<ul>
							<li class="wd1">
								Mobile No.：
							</li>
							<li class="wd2">
								${address.mobile }
							</li>
						</ul>
						<ul>
							<li class="wd1">
								Phone No.：
							</li>
							<li class="wd2">
								${address.tel }
							</li>
						</ul>
						<ul>
							<li class="wd1">
								<font class="redfont">*</font>Shipping Address：
							</li>
							<li class="wd2" style="word-break: break-all">
								${address.adress }${address.cityName }${address.provinceName
								}${address.contryName }
							</li>
						</ul>
						<ul>
							<li class="wd1">
								<font class="redfont">*</font>Zip：
							</li>
							<li class="wd2">
								${address.postCode }
							</li>
						</ul>
					</div>

					<div class="pay">
						<h1>
							Item list
						</h1>
						<ul class="pay_t">
							<li>
								<p class="cx">
									Seller：${lvStore.name }
								</p>
								<p class="freight">
									Freight：${moneyMark} ${postPrice }
								</p>
							</li>
						</ul>

					</div>

					<!--订单列表-->
					<div class="buy_order">
						<ul>
							<li class="buy_order01">
								<p>&nbsp;</p>
								<p class="title">Item Name</p>
								<p>Price</p>
								<p>Quantity</p>
								<p>Subtotal</p>
							</li>
							<c:foreach items="${objList}" var="obj" varStatus="status">
							<li id="cart${status.count }">
								<p><img src="${obj[1].pimage }" width="70px" height="60px"/></p>
								<p class="title" title="${obj[1].productName }">${obj[1].productName }</p>
								<p><font class="redfont fontwz">${moneyMark} ${obj[0].shopPrice }</font></p>
						 	  	<p>${obj[0].shopNum }</p>
						 	  	<p><font class="redfont fontwz">${moneyMark} ${obj[2] }</font></p>
							</li>
							</c:foreach>
						</ul>
						<ul class="sum"><span id="showData">Total：${moneyMark} ${allAmount } + Freight：${moneyMark} ${postPrice } </span></ul>
						<ul class="sum01">Amount Payable：<font class="redfont">${moneyMark} ${totalAmount }</font></a></ul>
						<c:if test="${not empty msg}">
							<div style="width:950px; background:#FFFFCB; height:30px; line-height:30px;text-align:right;color:#FF0000; padding-right:30px;border-bottom:1px #e5e5e5 solid;"><img src="${resDomain}/bmen/res/images/pos_icon.gif" style="vertical-align:text-bottom;"/> The total amount of your order has been changed, please reconfirm your order!</div>
						</c:if>	
					</div>	
					<div class="pay">	
						<h1>Payment</h1>
						 	<ul class="pay_t">								
							 	 <li>
					            	<s:if test="payValue==3">
					            		Alipay
					            	</s:if>
					            	<s:if test="payValue==4">
					            		Western Union
					            	</s:if>
					            	<s:if test="payValue==11||payValue==15">
					            		Credit or Debit Card by VISA
					            	</s:if>
					            	<s:if test="payValue==12||payValue==16">
					            		Credit or Debit Card by Master 
					            	</s:if>
					            	<s:if test="payValue==13||payValue==17">
					            		Credit or Debit Card by JCB
					            	</s:if>
					            	<s:if test="payValue==14">
					            		VISA/Master credit or debit card 
					            	</s:if>
							   </li>							   
						</ul>	
					</div>
					<div class="pay">
						<h1>Leave a message</h1>
						<ul>
							<li><textarea name="" class="input1" cols="" rows="" readonly>${orderRemark}</textarea></li>
						</ul>
						<ul class="btn">
							<form action="/web/shopCart!saveOrder.action" id="myForm" method="post">
								<input type="hidden" name="shopFlag" value="${lvStore.storeFlag }"/>
								<input type="hidden" name="addressCode" value="${address.code }"/>
								<input type="hidden" name="lvOrder.paymethod" value="${payValue }"/>
								<input type="hidden" name="bestDeliveryValue" value="${bestDeliveryValue }"/>
								<input type="hidden" name="totalPrice" value="${totalAmount }"/>
								<input type="hidden" name="lvOrder.orderRemark" value="${orderRemark }"/>
								<input type="hidden" name="presentPrice" value="${groupBuy.presentPrice }"/>
								<li><input class="btn05" type="submit" value="Submit"/></li>
							</form>
							<form action="/web/shopCart!backToOrderInfo.action" id="backForm" method="post">
								<input type="hidden" name="lvOrder.paymethod" value="${payValue }"/>
								<input type="hidden" name="addressCode" value="${address.code }"/>
								<input type="hidden" name="lvOrder.orderRemark" value="${orderRemark}"/>
								<input type="hidden" name="bestDeliveryValue" value="${bestDeliveryValue}"/>
								<input type="hidden" name="shopFlag" value="${lvStore.storeFlag }"/>
						  		<li><input class="btn06" type="submit" value="Back to Edit" /></li>
							</form>
						</ul>
					</div>	
					
				</div>				
			</div>
		</div>		
		
		<!-- 底部广告位-->
		<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>

		<!-- footer-->
		<%@include file="/web/bmen/common/footer.jsp" %>
	
	</body>
</html> 
