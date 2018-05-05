<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="cus" uri="/WEB-INF/tld/cus.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>User Center _User Center</title>
		
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
			 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/en/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">Home</a>--><a href="/web/userCenterManage!getAccount.action">User Center </a>--></font>  Pay now</span></h1> 
			   <div class="usercenter_box">
			     <div class="order"> <span class="order_t"><font class="fontwz">Order comfirmation, please review your order information</font></span>
		             <ul>
		               <li class="wd1">Contact：</li>
		               <li class="wd2">${lvOrderAdress.relName }</li>
		             </ul>
			       <ul>
		               <li class="wd1">Tel.：</li>
					   <c:if test="${not empty lvOrderAdress.mobile}">
			           <li class="wd2">${lvOrderAdress.mobile }</li>
			           </c:if>
			           <c:if test="${empty lvOrderAdress.mobile}">
			           <li class="wd2">${lvOrderAdress.tel}</li>
			           </c:if>
		           </ul>
			       <ul>
		               <li class="wd1">E-mail：</li>
			         <li class="wd2">${lvOrder.userEmail }</li>
		           </ul>
		           <ul>
		               <li class="wd1">Payment Method：</li>
			         <li class="wd2 orangefont">
		             	<s:if test="#request.lvOrder.paymethod==1">
		            		paypal
		            	</s:if>
		            	<s:if test="#request.lvOrder.paymethod==2">
		            		Visa/Master credit card
		            	</s:if>
		            	<s:if test="#request.lvOrder.paymethod==3">
		            		Alipay
		            	</s:if>
		            	<s:if test="#request.lvOrder.paymethod==4">
		            		Western Union
		            	</s:if>
		            	<s:if test="#request.lvOrder.paymethod==6">
		            		99bill
		            	</s:if>
		            	<s:if test="#request.lvOrder.paymethod==8">
		            		 Credit Card by VISA
		            	</s:if>
		            	<s:if test="#request.lvOrder.paymethod==11 or #request.lvOrder.paymethod==15">
		            		Credit or Debit Card by VISA
		            	</s:if>
		            	<s:if test="#request.lvOrder.paymethod==12 or #request.lvOrder.paymethod==16">
		            		Credit or Debit Card by Master 
		            	</s:if>
		            	<s:if test="#request.lvOrder.paymethod==13 or #request.lvOrder.paymethod==17">
		            		Credit or Debit Card by JCB
		            	</s:if>
		            	<s:if test="#request.lvOrder.paymethod==14 or #request.lvOrder.paymethod==20">
		            		VISA/Master credit or debit card 
		            	</s:if>  				         
			         </li>
		           </ul>
			       <ul>
		               <li class="wd1">Postcode：</li>
			         <li class="wd2">${lvOrderAdress.postCode }</li>
		           </ul>
			       
			       <!--<ul>
		               <li class="wd1">最佳收貨時間：</li>
			         <li class="wd2">
			          <s:if test="#request.lvOrderAdress.bestDeliveryTime==1">任何时间均可以送货</s:if>
			          <s:if test="#request.lvOrderAdress.bestDeliveryTime==2">周一至周五送货（周末没人无法签收）</s:if>
			          <s:if test="#request.lvOrderAdress.bestDeliveryTime==3">周末送货（工作日没人无法签收）</s:if>
			          <s:if test="#request.lvOrderAdress.bestDeliveryTime==4">晚上送货（白天没人无法签收）</s:if>
					</li>
		           </ul>-->
			       <ul class="long">
		               <li class="wd1">Billing address：</li>
			         <li class="wd3" >${lvOrderAdress.adress }&nbsp;${lvOrderAdress.cityName }&nbsp;${lvOrderAdress.provinceName }&nbsp;${lvOrderAdress.contryName }</li>
		           </ul>
			       <ul class="long">
		               <li class="wd1">Other Request：</li>
			         <li class="wd3" >${lvOrder.orderRemark }</li>
		           </ul>
			       <ul class="long">
		               <li class="wd1">Payment status：</li>
			         <li class="long">
				         <s:if test="#request.lvOrder.payStatus==0">
			          		<font class="redfont">Await payment</font> <img src="${resDomain}/en/res/images/personel_center_icon03.gif" width="16" height="16" /> <a href="/web/userOrder!toConfirmPay.action?oid=${lvOrder.oid }&shopFlag=${lvOrder.storeId }">Pay it Now</a>
			          	</s:if>
			          	<s:elseif test="#request.lvOrder.payStatus==2">
			          		<font class="redfont">Paid, Unconfirmed</font> 
			          	</s:elseif>
			          	<s:elseif test="#request.lvOrder.payStatus==1">
			          		<s:if test="#request.lvOrder.status==0">
			          			<font class="redfont">Paid, Await delivery</font> 
			          		</s:if>
			          		<s:elseif test="#request.lvOrder.status==1">
			          			<font class="redfont">Paid, Delivered</font> 
			          		</s:elseif>
			          		<s:elseif test="#request.lvOrder.status==2">
			          			<font class="redfont">Await reviews</font> 
			          		</s:elseif>
			          		<s:elseif test="#request.lvOrder.status==4">
			          			<font class="redfont">Completed</font> 
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
		                 <td bgcolor="#F9F8F8" style="text-align:center;"><font class="redfont fontwz">${moneyMark}${obj[2] }</font></td>
		               </tr>
		               </c:foreach>
		               
		               <!-- 赠品信息 -->	
	                   <c:if test="${not empty  giftList}">
	                   <tr>
	                   <td bgcolor="#F9F8F8" colspan="6"  style="padding-left:5px; line-height:30px;">
	                   <img src="${resDomain}/en/res/images/zp.jpg" width="43" height="17" />
	                           <c:foreach items="${giftList }" var="g">
	                             ${g.giftNameEn }<font class="redfont"> ×${g.giftNum  }</font>&nbsp;&nbsp;&nbsp;
	                            </c:foreach>
	                   </td>
	                   </tr>
	                   </c:if>
		               
		               <tr style="text-align:right;">
		               	 <td height="42" colspan="5" bgcolor="#F9F8F8" style="padding-right:30px;"><div align="right">Total：${moneyMark}${allAmount} <c:if test="${not empty orderMac}">- MAC Coupon:${moneyMark}${orderMac.discountAmount }</c:if><c:if test="${not empty coupon}">- Coupon：${lvOrder.currency} ${lvOrder.couponTotalPrice} </c:if> + Freight：${moneyMark}${lvOrder.postprice }</div></td>
		               </tr>
		               <tr style="text-align:right;">
		               	 <td height="42" colspan="5" bgcolor="#F9F8F8" style="padding-right:30px;"><div align="right">Amount Payable：<font class="fontwz redfont">${moneyMark}${lvOrder.totalPrice }</font></div></td>
		               </tr>        
		           </table>
		
		           <span class="close_btn">
			       		<input type="button" onclick="javascript:location.href='/web/userOrder!toPay.action?oid=${lvOrder.oid}';" value="Submit" class="user_center_bt" />
			       		<input type="button" onclick="javascript:history.go(-1);" value="Back" class="user_center_bt" />	
		           </span> 
		           </div>
			   </div>
			   
		  </div>
			 <!--End right_Frame-->
		</div>
		
		<!-- 底部广告位-->
		<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
			
		<!-- footer-->
		<%@include file="/web/en/common/footer.jsp" %>
		
	</body>
</html> 