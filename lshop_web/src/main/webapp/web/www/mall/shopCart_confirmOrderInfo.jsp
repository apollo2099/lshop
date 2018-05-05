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
		<title>確認訂單信息_TVpad商城</title>
		<link href="${resDomain}/www/res/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${resDomain}/www/res/css/buy.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<%@include file="/web/www/common/top.jsp" %>
		<script type="text/javascript">
			function toSubOrder(){
				$("#orderForm").submit();
			}
			function toBackOrder(){
				$("#backForm").submit();
			}
		</script>
	</head>
	
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/www/common/header.jsp" %>
		
		<div class="buy">	
			<div class="buy_top"><img src="${resDomain}/www/res/images/shopping01.gif" /></div>
			<div class="buy_lc03"></div>
			<div class="fill_orders">
				<h1>確認訂單信息</h1>
				<div class="fill_orders01">
					<div class="receive_info">
						<ul>
							<li class="wd1">
								<font class="redfont">*</font>收貨人姓名：
							</li>
							<li class="wd2">
								${address.relName }
							</li>
						</ul>

						<ul>
							<li class="wd1">
								手機號碼：
							</li>
							<li class="wd2">
								${address.mobile }
							</li>
						</ul>
						<ul>
							<li class="wd1">
								固定電話：
							</li>
							<li class="wd2">
								${address.tel }
							</li>
						</ul>
						<ul>
							<li class="wd1">
								<font class="redfont">*</font>收貨地址：
							</li>
							<li class="wd2" style="word-break: break-all">
								${address.adress }${address.cityName }${address.provinceName
								}${address.contryName }
							</li>
						</ul>
						<ul>
							<li class="wd1">
								<font class="redfont">*</font>郵編區號：
							</li>
							<li class="wd2">
								${address.postCode }
							</li>
						</ul>
					</div>

			<!--	<div class="pay">	
					  <h1>收貨時間</h1>
					 	<ul class="pay_t">								
						   <li>	
								<c:if test="${bestDeliveryValue==1}">任何时间均可以送货</c:if>
          						<c:if test="${bestDeliveryValue==2}">周一至周五送货（周末没人无法签收）</c:if>
          						<c:if test="${bestDeliveryValue==3}">周末送货（工作日没人无法签收）</c:if>
          						<c:if test="${bestDeliveryValue==4}">晚上送货（白天没人无法签收）</c:if>
          					</li>							   
						</ul>	
				    </div>  -->

					<div class="pay">
						<h1>
							商品清單
						</h1>
						<ul class="pay_t">
							<li>
								<p class="cx">
									商家：${lvStore.name }
								</p>
								<p class="freight">
									運費：${moneyMark}${postPrice }
								</p>
							</li>
						</ul>

					</div>

					<!--订单列表-->
					<div class="buy_order2">
						<ul>
							<li class="buy_order01">
								<p>&nbsp;</p>
								<p class="title">商品名稱</p>
								<p>價格</p>
								<p>購買數量</p>
								<p>小計金額</p>
							</li>
							<c:foreach items="${objList}" var="obj" varStatus="status">
							<li id="cart${status.count }">
								<p><img src="${obj[1].pimage }" width="70px" height="60px"/></p>
								<p class="title">${obj[1].productName }</p>
								<p><font class="redfont fontwz">${moneyMark}${obj[0].shopPrice }</font></p>
						 	  	<p>${obj[0].shopNum }</p>
						 	  	<p><font class="redfont fontwz">${moneyMark}${obj[2] }</font></p>
							</li>
							</c:foreach>
						</ul>
						<ul class="sum"><span id="showData">商品總金額：${moneyMark}${allAmount }- 優惠券減免：${moneyMark}${allCouponPrice } + 運費：${moneyMark}${postPrice } </span></ul>
						<ul class="sum01">應付金額：<font class="redfont">${moneyMark}${totalAmount }</font></a></ul>
						<c:if test="${not empty msg}">
							<div style="width:265px; background:#FFFFCB; height:25px; line-height:22px; text-align:right; padding-top:8px; color:#FF0000; padding-right:30px;padding-left:700px; border-bottom:1px #e5e5e5 solid;"><p class="cx"><img src="${resDomain}/www/res/images/pos_icon.gif" /></p> <p class="cx">訂單價格已發生變動，請重新確認 ！</p></div>
						</c:if>	
					</div>	
					<div class="pay">	
						<h1>支付方式</h1>
						 	<ul class="pay_t">								
							 	 <li>
					            	<s:if test="payValue==3">
					            		支付寶
					            	</s:if>
					            	<s:if test="payValue==4">
					            		西聯匯款
					            	</s:if>
					            	<s:if test="payValue==11||payValue==15">
					            		Visa國際信用卡或借記卡
					            	</s:if>
					            	<s:if test="payValue==12||payValue==16">
					            		Master國際信用卡或借記卡
					            	</s:if>
					            	<s:if test="payValue==13||payValue==17">
					            		JCB國際信用卡或借記卡
					            	</s:if>
					            	<s:if test="payValue==14">
					            		VISA/MASTER
					            	</s:if>
							   </li>							   
						</ul>	
					</div>
					<div class="pay">
						<h1>備註留言</h1>
						<ul>
							<li><textarea name="" class="input1" cols="" rows="" readonly>${orderRemark}</textarea></li>
						</ul>
						<ul class="btn">
							<form action="/web/shopCart!saveOrder.action" id="orderForm" method="post">
								<input type="hidden" name="shopFlag" value="${lvStore.storeFlag }"/>
								<input type="hidden" name="addressCode" value="${address.code }"/>
								<input type="hidden" name="lvOrder.paymethod" value="${payValue }"/>
								<input type="hidden" name="bestDeliveryValue" value="${bestDeliveryValue }"/>
								<input type="hidden" name="totalPrice" value="${totalAmount }"/>
								<input type="hidden" name="couponCode" value="${couponCode}"/>
								<input type="hidden" name="lvOrder.orderRemark" value="${orderRemark }"/>
								<input type="hidden" name="presentPrice" value="${groupBuy.presentPrice }"/>
								<!--<input type="hidden" name="lvOrder.couponNum" value="${couponNum }"/>  -->
								<!--<input type="hidden" name="potPrice" value="${postPrice }"/>  -->
								<!--<input type="hidden" name="couponPrice" value="${couponPrice }"/>  -->
							</form>
							<form action="/web/shopCart!backToOrderInfo.action" id="backForm" method="post">
								<input type="hidden" name="lvOrder.paymethod" value="${payValue }"/>
								<input type="hidden" name="couponCode" value="${couponCode }"/>
								<input type="hidden" name="addressCode" value="${address.code }"/>
								<input type="hidden" name="lvOrder.orderRemark" value="${orderRemark}"/>
								<input type="hidden" name="bestDeliveryValue" value="${bestDeliveryValue}"/>
								<input type="hidden" name="shopFlag" value="${lvStore.storeFlag }"/>
						  		<a href="javascript:toSubOrder();"><img  src="${resDomain}/www/res/images/sub01.gif" width="123" height="46" border="0" /></a>
								<a href="javascript:toBackOrder();"><img src="${resDomain}/www/res/images/modi.gif" width="123" height="45" border="0" /></a>
							</form>
						</ul>
					</div>	
					
				</div>				
			</div>
		</div>		
		
		<!-- 底部广告位-->
		<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>

		<!-- footer-->
		<%@include file="/web/www/common/footer.jsp" %>
	
	</body>
</html> 
