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
	 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/tvpadcn/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">首頁</a>--><a href="/web/userCenterManage!getAccount.action">用戶中心</a>--></font> 我的訂單</span></h1> 
		
		<table width="760" border="0" cellpadding="1"  cellspacing="1" bgcolor="#E3E2E2" >
        <tr>
          <td height="40" colspan="4" bgcolor="#F2F1F1"><font class="bfont fontwz">訂單當前狀態：</font> 
          	<s:if test="#request.lvOrder.payStatus==0">
          		<font class="redfont">待付款</font> <img src="${resDomain}/tvpadcn/res/images/personel_center_icon03.gif" width="16" height="16" /> 
          		<s:if test="#request.lvOrder.paymethod!=4">
          			<a href="/web/userOrder!toConfirmPay.action?oid=${lvOrder.oid }">現在支付</a>
          		</s:if>
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
          </td>
        </tr>
        <tr>
          <td width="117"  height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>訂單號：<br />
          </strong></td>
          <td width="273" height="30" bgcolor="#FFFFFF" style="text-align:center">${lvOrder.oid}<br /></td>
          <td width="133" height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>下單時間：<br />
          </strong></td>
          <td width="209" height="30" bgcolor="#FFFFFF" style="text-align:center"><s:date name="#request.lvOrder.createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
        </tr>
        <tr>
          <td width="117" height="30" bordercolor="#F6D0C9" bgcolor="#FFFFFF" style="text-align:right"><strong>E-mail：<br />
          </strong></td>
          <td  width="273" height="30" bordercolor="#F6D0C9" bgcolor="#FFFFFF" style="text-align:center">${lvOrder.userEmail }<br /></td>
          <td width="133" height="30" bordercolor="#F6D0C9" bgcolor="#FFFFFF" style="text-align:right"><strong>付款方式：<br />
          </strong></td>
          <td width="209" height="30" bordercolor="#F6D0C9" bgcolor="#FFFFFF" style="text-align:center"><cus:payName showLength="" payValue="${lvOrder.paymethod}"></cus:payName></td>
        </tr>
        <tr>
          <td width="117" height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>收貨人姓名：<br />
          </strong></td>
          <td  width="273" height="30" bgcolor="#FFFFFF" style="text-align:center">${lvOrderAdress.relName }<br /></td>
          <td  width="133" height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>訂單金額：<br />
          </strong></td>
          <td  width="209" height="30" bgcolor="#FFFFFF" style="text-align:center">${moneyMark} ${lvOrder.totalPrice}</td>
        </tr>
        <tr>
          <td  width="117" height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>手機號碼：<br />
          </strong></td>
          <td  width="273" height="30" bgcolor="#FFFFFF" style="text-align:center">${lvOrderAdress.mobile }<br /></td>
          <td  width="133" height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>固定電話：<br />
          </strong></td>
          <td  width="209" height="30" bgcolor="#FFFFFF" style="text-align:center">${lvOrderAdress.tel }</td>
        </tr>
        <tr>
          <td  width="117" height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>收貨方式：<br />
          </strong></td>
          <td  width="273" height="30" bgcolor="#FFFFFF" style="text-align:center">国际快递</td>
          <td  width="133" height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>郵政編碼：<br />
          </strong></td>
          <td  width="209" height="30" bgcolor="#FFFFFF" style="text-align:center">${lvOrderAdress.postCode }</td>
        </tr>
        <tr>
          <td  width="117" height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>收貨地址：<br />
          </strong></td>
          <td height="30" colspan="3" bgcolor="#FFFFFF" style="text-align:center;word-break:break-all">${lvOrderAdress.adress }&nbsp;${lvOrderAdress.cityName }&nbsp;${lvOrderAdress.provinceName }&nbsp;${lvOrderAdress.contryName }</td>
        </tr>
        <tr>
          <td width="117" height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>最佳收貨時間：<br />
          </strong></td>
          <td height="30" colspan="3" bgcolor="#FFFFFF" style="text-align:center">
	          <s:if test="#request.lvOrderAdress.bestDeliveryTime==1">任何时间均可以送货</s:if>
	          <s:if test="#request.lvOrderAdress.bestDeliveryTime==2">周一至周五送货（周末没人无法签收）</s:if>
	          <s:if test="#request.lvOrderAdress.bestDeliveryTime==3">周末送货（工作日没人无法签收）</s:if>
	          <s:if test="#request.lvOrderAdress.bestDeliveryTime==4">晚上送货（白天没人无法签收）</s:if>
          </td>
        </tr>
        <tr>
          <td  width="117" height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>其他要求：<br />
          </strong></td>
          <td width="643" height="30" colspan="3" bgcolor="#FFFFFF" style="text-align:center;word-break:break-all">${lvOrder.orderRemark }<br /></td>
        </tr>
       <s:if test="#request.lvOrder.status!=0">
		    <tr>
	          <td height="40" colspan="4" bgcolor="#F2F1F1"><font class="bfont fontwz">物流信息</font><a href="/web/userOrder!doLogisticsTrackingInfo.action?oid=${oid}"><font class="redfont">(查看物流動態)</font></a></td>
	        </tr>
	        <tr>
	          <td bgcolor="#FFFFFF" style="text-align:right"><strong>快遞公司：<br /> </strong></td>
	          <td height="30" bgcolor="#FFFFFF" style="text-align:center">${lvOrder.expressCompany  }</td>
	          <td bgcolor="#FFFFFF" style="text-align:right"><strong>快遞單號：<br /></strong></td>
	          <td height="30" bgcolor="#FFFFFF" style="text-align:center"><a href="/web/userOrder!doLogisticsTrackingInfo.action?oid=${lvOrder.oid}" title="查看物流動態">${lvOrder.expressNum }</a></td>
	        </tr>
	        <tr>
	          <td bgcolor="#FFFFFF" style="text-align:right"><strong>發貨時間：</strong></td>
	          <td height="30" bgcolor="#FFFFFF" style="text-align:center">${lvOrder.shipTime }</td>
	          <td bgcolor="#FFFFFF" style="text-align:right"><strong>備註：</strong></td>
	          <td height="30" bgcolor="#FFFFFF" style="text-align:center" >${lvOrder.sendRemark }</td>
	        </tr>
        </s:if>
        <s:if test="#request.lvOrder.payStatus!=0 and #request.lvOrder.paymethod==4">
	        <tr>
	           <td height="40" colspan="4" bgcolor="#F2F1F1"><font class="bfont fontwz">西聯匯款信息</font></td>
	        </tr>
		    <tr>
	          <td bgcolor="#FFFFFF" style="text-align:right"><strong>匯款人姓名：<br /></strong></td>
	          <td height="30" bgcolor="#FFFFFF" style="text-align:center">${lvWesternInfo.remitter }</td>
	          <td bgcolor="#FFFFFF" style="text-align:right"><strong>匯款金額：<br /></strong></td>
	          <td height="30" bgcolor="#FFFFFF" style="text-align:center">USD ${lvWesternInfo.remittance  }</td>
	        </tr>
	        <tr>
	          <td bgcolor="#FFFFFF" style="text-align:right"><strong>匯款監控號碼：</strong></td>
	          <td height="30" bgcolor="#FFFFFF" style="text-align:center">${lvWesternInfo.mtcn }</td>
	          <td bgcolor="#FFFFFF" style="text-align:right"><strong>匯款國家/地區：</strong></td>
	          <td height="30" bgcolor="#FFFFFF" style="text-align:center">${lvWesternInfo.adress }&nbsp;${lvWesternInfo.contryName }</td>
	        </tr>
        </s:if>
      </table>	
			
			<div class="buy_order">
						<ul>
							<li class="buy_order01">
								<p>&nbsp;</p>
								<p class="title">商品名稱</p>
								<p>價格</p>
								<p>購買數量</p>
								<p>小計金額</p>
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
						<ul class="sum">商品总金额：${moneyMark}<u:formatnumber value="${allAmount}"  type="number" groupingUsed="false" maxFractionDigits="2"/> - 优惠券减免：${moneyMark}<u:formatnumber value="${allCouponPrice }"  type="number" groupingUsed="false" maxFractionDigits="2"/>+ 运费:${moneyMark}<u:formatnumber value="${lvOrder.postprice }"  type="number" groupingUsed="false" maxFractionDigits="2"/></a></ul>
						<ul class="sum01">應付金额：<font class="redfont">${moneyMark}<u:formatnumber value="${lvOrder.totalPrice }"  type="number" groupingUsed="false" maxFractionDigits="2"/> </font></a></ul>
						<ul class="btn">
							<s:if test="#request.lvOrder.payStatus==0">
								<s:if test="#request.lvOrder.paymethod==4">
									<a href="/web/userOrder!toOrderWestern?oid=${lvOrder.oid }"><img src="${resDomain}/tvpadcn/res/images/tjxlxx.png" width="101" height="34" border="0"></a>
								</s:if>
								<s:elseif test="#request.lvOrder.paymethod==7"></s:elseif>
								<s:else>
									<a href="/web/userOrder!toConfirmPay.action?oid=${lvOrder.oid }"><img src="${resDomain}/tvpadcn/res/images/user_center_btn07.gif" width="101" height="34" border="0" /></a>
								</s:else>
							</s:if>
						  	<a href="/web/userOrder!getOrderlist.action"><img src="${resDomain}/tvpadcn/res/images/user_center_btn04.gif" width="101" height="34" border="0" /></a>						  
						</ul>
		  </div>
		
	 <!--End right_Frame-->
</div>
	
<!-- footer-->
<%@include file="/web/tvpadcn/common/footer.jsp" %>

</body>
</html> 