<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="cus" uri="/WEB-INF/tld/cus.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用戶中心_華揚商城</title>
<%@include file="/web/tvpadcn/common/header.jsp" %>
<link href="${resDomain}/tvpadcn/res/css/main.css" rel="stylesheet" type="text/css" />
<link href="${resDomain}/tvpadcn/res/css/user_center.css" rel="stylesheet" type="text/css" />
</head>
<body>
<!-- top -->
<%@include file="/web/tvpadcn/common/top.jsp" %>
	
<div class="content_main">

	<!-- left_frame -->
	<%@include file="/web/tvpadcn/user_center/leftFrame.jsp" %>
	 
	 <div class="right_frame">
	 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/tvpadcn/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">首頁</a>--><a href="/web/userCenterManage!getAccount.action">用戶中心</a>--></font> 現在支付</span></h1> 
	   <div class="usercenter_box">
	     <div class="order"> <span class="order_t"><font class="fontwz">訂單確認,清您核對訂單信息</font></span>
             <ul>
               <li class="wd1">聯繫人：</li>
               <li class="wd2">${lvOrderAdress.relName }</li>
             </ul>
	       <ul>
               <li class="wd1">聯繫電話：</li>
	         <li class="wd2">${lvOrderAdress.mobile }</li>
           </ul>
	       <ul>
               <li class="wd1">付款方式：</li>
	         <li class="wd2 orangefont"><cus:payName showLength="" payValue="${lvOrder.paymethod}"></cus:payName></li>
           </ul>
	       <ul>
               <li class="wd1">E-mail：</li>
	         <li class="wd2">${lvOrder.userEmail }</li>
           </ul>
	       <ul>
               <li class="wd1">郵政編碼：</li>
	         <li class="wd2">${lvOrderAdress.postCode }</li>
           </ul>
	       <ul>
               <li class="wd1">最佳收貨時間：</li>
	         <li class="wd2">
	          <s:if test="#request.lvOrderAdress.bestDeliveryTime==1">任何时间均可以送货</s:if>
	          <s:if test="#request.lvOrderAdress.bestDeliveryTime==2">周一至周五送货（周末没人无法签收）</s:if>
	          <s:if test="#request.lvOrderAdress.bestDeliveryTime==3">周末送货（工作日没人无法签收）</s:if>
	          <s:if test="#request.lvOrderAdress.bestDeliveryTime==4">晚上送货（白天没人无法签收）</s:if>
			</li>
           </ul>
	       <ul class="long">
               <li class="wd1">收貨地址：</li>
	         <li class="wd3" style="word-break:break-all">${lvOrderAdress.adress }&nbsp;${lvOrderAdress.cityName }&nbsp;${lvOrderAdress.provinceName }&nbsp;${lvOrderAdress.contryName }</li>
           </ul>
	       <ul class="long">
               <li class="wd1">其他備註說明：</li>
	         <li class="wd3" style="word-break:break-all">${lvOrder.orderRemark }</li>
           </ul>
	       <ul class="long">
               <li class="wd1">支付狀態：</li>
	         <li class="long">
		         <s:if test="#request.lvOrder.payStatus==0">
	          		<font class="redfont">待付款</font> <img src="${resDomain}/tvpadcn/res/images/personel_center_icon03.gif" width="16" height="16" /> <a href="/web/userOrder!toConfirmPay?oid=${lvOrder.oid }">現在支付</a>
	          	</s:if>
	          	<s:elseif test="#request.lvOrder.payStatus==2">
	          		<font class="redfont">已付款,未确认</font> 
	          	</s:elseif>
	          	<s:elseif test="#request.lvOrder.payStatus==1">
	          		<s:if test="#request.lvOrder.status==0">
	          			<font class="redfont">已付款,待发货</font> 
	          		</s:if>
	          		<s:elseif test="#request.lvOrder.status==1">
	          			<font class="redfont">已付款,已发货</font> 
	          		</s:elseif>
	          		<s:elseif test="#request.lvOrder.status==2">
	          			<font class="redfont">待评价</font> 
	          		</s:elseif>
	          		<s:elseif test="#request.lvOrder.status==4">
	          			<font class="redfont">已完成</font> 
	          		</s:elseif>
	          		<s:elseif test="#request.lvOrder.status==3">
	          			<font class="redfont">已退货</font> 
	          		</s:elseif>
	          	</s:elseif>
	         </li>
           </ul>
	       <table width="710" height="188" border="0" cellpadding="0" cellspacing="1" bgcolor="#E8E5E5">
               <tr>
                 <td height="33" colspan="6" bgcolor="#F9F8F8"><font class="redfont fontwz redfont">訂單號碼：${lvOrder.oid}</font></td>
               </tr>
               <tr style="text-align:center;">
                 <td height="28" bgcolor="#F9F8F8" style="text-align:center;"></td>
                 <td bgcolor="#F9F8F8" style="text-align:center;"> 商品名稱 </td>
                 <td bgcolor="#F9F8F8" style="text-align:center;"> 價格 </td>
                 <td bgcolor="#F9F8F8" style="text-align:center;">購買數量 </td>
                 <td bgcolor="#F9F8F8" style="text-align:center;">小計金額 </td>
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
               	 <td height="42" colspan="5" bgcolor="#F9F8F8" style="padding-right:30px;"><div align="right">商品总金额：${moneyMark}<u:formatnumber value="${allAmount}"  type="number" groupingUsed="false" maxFractionDigits="2"/> - 优惠券减免：${moneyMark}<u:formatnumber value="${allCouponPrice }"  type="number" groupingUsed="false" maxFractionDigits="2"/>+ 运费:${moneyMark}<u:formatnumber value="${lvOrder.postprice }"  type="number" groupingUsed="false" maxFractionDigits="2"/></div></td>
               </tr>
               <tr style="text-align:right;">
               	 <td height="42" colspan="5" bgcolor="#F9F8F8" style="padding-right:30px;"><div align="right">應付金额：<font class="fontwz redfont">${moneyMark}<u:formatnumber value="${lvOrder.totalPrice }"  type="number" groupingUsed="false" maxFractionDigits="2"/></font></div></td>
               </tr>        
           </table>
	       <span class="close_btn">
           <a href="/web/userOrder!toPay.action?oid=${lvOrder.oid}"><img src="${resDomain}/tvpadcn/res/images/user_center_btn05.gif" width="101" height="34" border="0" /></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:history.go(-1);"><img src="${resDomain}/tvpadcn/res/images/user_center_btn04.gif" width="101" height="34" border="0" /></a> </span> </div>
	   </div>
  </div>
	 <!--End right_Frame-->
</div>
	
<!-- footer-->
<%@include file="/web/tvpadcn/common/footer.jsp" %>

</body>
</html> 