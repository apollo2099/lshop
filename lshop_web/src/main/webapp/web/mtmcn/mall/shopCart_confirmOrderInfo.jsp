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
		<title>确认订单信息_TVpad商城</title>
		<link href="${resDomain}/mtmcn/res/css/css.css" rel="stylesheet" type="text/css" />
		<link href="${resDomain}/mtmcn/res/css/buy.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<%@include file="/web/mtmcn/common/top.jsp" %>
	</head>
	
	<body>
		
		<div class="buy">	
			<div class="buy_top"><img src="${resDomain}/mtmcn/res/images/shopping01.gif" /></div>
			<div class="buy_lc03"></div>
			<div class="fill_orders">
				<h1>确认订单信息</h1>
				<div class="fill_orders01">
					<div class="receive_info">
						<ul>
							<li class="wd1">
								<font class="redfont">*</font>收货人姓名：
							</li>
							<li class="wd2">
								${address.relName }
							</li>
						</ul>

						<ul>
							<li class="wd1">
								手机号码：
							</li>
							<li class="wd2">
								${address.mobile }
							</li>
						</ul>
						<ul>
							<li class="wd1">
								固定电话：
							</li>
							<li class="wd2">
								${address.tel }
							</li>
						</ul>
						<ul>
							<li class="wd1">
								<font class="redfont">*</font>收货地址：
							</li>
							<li class="wd2" style="word-break: break-all">
								${address.adress }${address.cityName }${address.provinceName
								}${address.contryName }
							</li>
						</ul>
						<ul>
							<li class="wd1">
								<font class="redfont">*</font>邮编区号：
							</li>
							<li class="wd2">
								${address.postCode }
							</li>
						</ul>
					</div>

					<div class="pay">
						<h1>
							商品清单
						</h1>
						<ul class="pay_t">
							<li>
								<p class="cx">
									商家：${lvStore.name }
								</p>
								<p class="freight">
									运费：${moneyMark}${postPrice }
								</p>
							</li>
						</ul>

					</div>

					<!--订单列表-->
					<div class="buy_order">
						<ul>
							<li class="buy_order01">
								<p>&nbsp;</p>
								<p class="title">商品名称</p>
								<p>价格</p>
								<p>购买数量</p>
								<p>小计金额</p>
							</li>
							<c:foreach items="${objList}" var="obj" varStatus="status">
							<li id="cart${status.count }">
								<p><img src="${obj[1].pimage }" width="70px" height="60px"/></p>
								<p class="title" title="${obj[1].productName }">${obj[1].productName }</p>
								<p><font class="redfont fontwz">${moneyMark}${obj[0].shopPrice }</font></p>
						 	  	<p>${obj[0].shopNum }</p>
						 	  	<p><font class="redfont fontwz">${moneyMark}${obj[2] }</font></p>
							</li>
							</c:foreach>
						</ul>
						<ul class="sum"><span id="showData">商品总金额：${moneyMark}${allAmount } + 运费：${moneyMark}${postPrice } </span></ul>
						<ul class="sum01">应付金额：<font class="redfont">${moneyMark}${totalAmount }</font></a></ul>
						<c:if test="${not empty msg}">
							<div style="width:265px; background:#FFFFCB; height:25px; line-height:22px; text-align:right; padding-top:8px; color:#FF0000; padding-right:30px;padding-left:700px; border-bottom:1px #e5e5e5 solid;"><p class="cx"><img src="${resDomain}/mtmcn/res/images/pos_icon.gif" /></p> <p class="cx">订单金额已发生改变，请重新确认 ！</p></div>
						</c:if>	
					</div>	
					<div class="pay">	
						<h1>支付方式</h1>
						 	<ul class="pay_t">								
							   <li>
					            	<s:if test="payValue==3">
					            		支付宝
					            	</s:if>
					            	<s:if test="payValue==4">
					            		西联汇款
					            	</s:if>
					            	<s:if test="payValue==11||payValue==15">
					            		Visa国际信用卡或借记卡
					            	</s:if>
					            	<s:if test="payValue==12||payValue==16">
					            		Master国际信用卡或借记卡
					            	</s:if>
					            	<s:if test="payValue==13||payValue==17">
					            		JCB国际信用卡或借记卡
					            	</s:if>
					            	<s:if test="payValue==14">
					            		VISA/MASTER
					            	</s:if>
							   </li>							   
						</ul>	
					</div>
					<div class="pay">
						<h1>备注留言</h1>
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
								<li><input class="btn05" type="submit" value="确认提交"/></li>
							</form>
							<form action="/web/shopCart!backToOrderInfo.action" id="backForm" method="post">
								<input type="hidden" name="lvOrder.paymethod" value="${payValue }"/>
								<input type="hidden" name="addressCode" value="${address.code }"/>
								<input type="hidden" name="lvOrder.orderRemark" value="${orderRemark}"/>
								<input type="hidden" name="bestDeliveryValue" value="${bestDeliveryValue}"/>
								<input type="hidden" name="shopFlag" value="${lvStore.storeFlag }"/>
						  		<li><input class="btn06" type="submit" value="返回修改" /></li>
							</form>
						</ul>
					</div>	
					
				</div>				
			</div>
		</div>		
		
		<!-- 底部广告位-->
		<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>

		<!-- footer-->
		<%@include file="/web/mtmcn/common/footer.jsp" %>
	
	</body>
</html> 
