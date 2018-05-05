<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="cus" uri="/WEB-INF/tld/cus.tld"%>
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
			 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/en/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">Home</a>--><a href="/web/userCenterManage!getAccount.action">User Center </a>--></font> My Order</span></h1>  
				
				<table width="760" border="0" cellpadding="1"  cellspacing="1" bgcolor="#E3E2E2" >
		        <tr>
		          <td height="40" colspan="4" bgcolor="#F2F1F1">
		          	<font class="bfont fontwz">Seller：<span class="rd">${lvStore.name }&nbsp;</span> </font>
		          	<font class="bfont fontwz">Current Status：</font> 
		          	<s:if test="#request.lvOrder.payStatus==0">
		          		<font class="redfont">Await payment</font>&nbsp; <img src="${resDomain}/en/res/images/personel_center_icon03.gif" width="16" height="16" /> 
		          		<s:if test="#request.lvOrder.paymethod!=4">
		          			<a href="/web/userOrder!toConfirmPay.action?oid=${lvOrder.oid }&shopFlag=${lvOrder.storeId }">Pay now</a>
		          		</s:if>
		          		<s:if test="#request.lvOrder.paymethod==4">
		          			<a href="/web/userOrder!toOrderWestern.action?oid=${lvOrder.oid }">Submit Western Union Info</a>
		          		</s:if>
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
		          </td>
		        </tr>
		        <tr>
		          <td width="137" height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>Order No.：<br />
		          </strong></td>
		          <td width="263" height="30" bgcolor="#FFFFFF" style="text-align:center">${lvOrder.oid}<br /></td>
		          <td width="133" height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>Time of Order：<br />
		          </strong></td>
		          <td width="199" height="30" bgcolor="#FFFFFF" style="text-align:center"><s:date name="#request.lvOrder.createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
		        </tr>
		        <tr>
		          <td width="137" height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>Amount：<br />
		          </strong></td>
		          <td  width="263" height="30" bgcolor="#FFFFFF" style="text-align:center">${moneyMark} ${lvOrder.totalPrice}</td>
		          <td width="133" height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>Payment：<br />
		          </strong></td>
		          <td width="199" height="30" bgcolor="#FFFFFF" style="text-align:center">
		          		<s:if test="#request.lvOrder.paymethod==1">
		            		paypal
		            	</s:if>
		            	<s:if test="#request.lvOrder.paymethod==2">
		            		Visa/Master credit card
		            	</s:if>
		            	<s:if test="#request.lvOrder.paymethod==3 or #request.lvOrder.paymethod==18">
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
		          </td>
		        </tr>
		        <c:if test="${not empty coupon}">
		        <tr>
		          <td width="117"  height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>Use coupon：<br />
		          </strong></td>
		          <td width="273" height="30" bgcolor="#FFFFFF" style="text-align:center">${coupon.couponName}<br /></td>
		          <td width="133" height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>Value of Coupon：<br />
		          </strong></td>
		          <td width="209" height="30" bgcolor="#FFFFFF" style="text-align:center">${lvOrder.currency} ${lvOrder.couponTotalPrice}</td>
		        </tr>
		        </c:if>
		        <tr>
		          <td width="137" height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>Full Name：<br />
		          </strong></td>
		          <td  width="263" height="30" bgcolor="#FFFFFF" style="text-align:center">${lvOrderAdress.relName }<br /></td>
		          <td width="133" height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>Email：<br />
		          </strong></td>
		          <td  width="199" height="30" bgcolor="#FFFFFF" style="text-align:center">${lvOrder.userEmail }<br /></td>
		        </tr>
		        <tr>
		          <td width="137" height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>Mobile No.：<br />
		          </strong></td>
		          <td  width="263" height="30" bgcolor="#FFFFFF" style="text-align:center">${lvOrderAdress.mobile }<br /></td>
		          <td width="133" height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>Phone No.：<br />
		          </strong></td>
		          <td  width="199" height="30" bgcolor="#FFFFFF" style="text-align:center">${lvOrderAdress.tel }</td>
		        </tr>
		        <tr>
		          <!--<td  width="117" height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>最佳收貨時間：：<br />
		          </strong></td>
		          <td  width="273" height="30" bgcolor="#FFFFFF" style="text-align:center">
		          	  <s:if test="#request.lvOrderAdress.bestDeliveryTime==1">任何时间均可以送货</s:if>
			          <s:if test="#request.lvOrderAdress.bestDeliveryTime==2">周一至周五送货（周末没人无法签收）</s:if>
			          <s:if test="#request.lvOrderAdress.bestDeliveryTime==3">周末送货（工作日没人无法签收）</s:if>
			          <s:if test="#request.lvOrderAdress.bestDeliveryTime==4">晚上送货（白天没人无法签收）</s:if>
		          </td>  -->
		          <td width="133" height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>Zip：<br />
		          </strong></td>
		          <td  colspan="3" height="30" bgcolor="#FFFFFF" style="text-align:center">${lvOrderAdress.postCode }</td>
		        </tr>
		        <tr>
		          <td width="137" height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>Billing Address：<br />
		          </strong></td>
		          <td height="30" colspan="3" bgcolor="#FFFFFF" style="text-align:center;word-break:break-all">${lvOrderAdress.adress }&nbsp;${lvOrderAdress.cityName }&nbsp;${lvOrderAdress.provinceName }&nbsp;${lvOrderAdress.contryName }</td>
		        </tr>
		        <tr>
		          <td width="137" height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>Other requirements：<br />
		          </strong></td>
		          <td width="643" height="30" colspan="3" bgcolor="#FFFFFF" style="text-align:center;word-break:break-all">${lvOrder.orderRemark }<br /></td>
		        </tr>
		       <s:if test="#request.lvOrder.status!=0">
				    <tr>
			          <td height="40" colspan="4" bgcolor="#F2F1F1"><font class="bfont fontwz">Logistics Details</font><a href="/web/userOrder!doLogisticsTrackingInfo.action?oid=${oid}&shopFlag=${lvOrder.storeId }"><font class="redfont">(View Logistics status)</font></a></td>
			        </tr>
			        <tr>
			          <td bgcolor="#FFFFFF" style="text-align:right"><strong>Express Company：<br /> </strong></td>
			          <td height="30" bgcolor="#FFFFFF" style="text-align:center">${lvOrder.expressCompany  }</td>
			          <td bgcolor="#FFFFFF" style="text-align:right"><strong>Tracking No.：<br /></strong></td>
			          <td height="30" bgcolor="#FFFFFF" style="text-align:center"><a href="/web/userOrder!doLogisticsTrackingInfo.action?oid=${lvOrder.oid}&shopFlag=${lvOrder.storeId }" title="View Logistics status">${lvOrder.expressNum }</a></td>
			        </tr>
			        <tr>
			          <td bgcolor="#FFFFFF" style="text-align:right"><strong>Delivery time：</strong></td>
			          <td height="30" bgcolor="#FFFFFF" style="text-align:center">${lvOrder.shipTime }</td>
			          <td bgcolor="#FFFFFF" style="text-align:right"><strong>Remark ：</strong></td>
			          <td height="30" bgcolor="#FFFFFF" style="text-align:center" >${lvOrder.sendRemark }</td>
			        </tr>
		        </s:if>
		        <s:if test="#request.lvOrder.payStatus!=0 and #request.lvOrder.paymethod==4">
			        <tr>
			           <td height="40" colspan="4" bgcolor="#F2F1F1"><font class="bfont fontwz">Western Union Information</font></td>
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
											<p><font class="redfont fontwz">${moneyMark}${obj[2] }</font></p>
										</li>
									</c:foreach>
								</ul>
								
								<!-- 赠品信息 -->	
							    <c:if test="${not empty  giftList}">
								<ul class="zp_list">
								<li><img src="${resDomain}/en/res/images/zp.jpg" width="43" height="17" />
								<c:foreach items="${giftList }" var="g">
	                             ${g.giftNameEn }<font class="redfont"> ×${g.giftNum  }</font>&nbsp;&nbsp;&nbsp;
	                            </c:foreach>
	                            </li>
	                            </ul>
	                            </c:if>
								
								<ul class="sum">Total：${moneyMark}${allAmount} <c:if test="${not empty orderMac}">- MAC Coupon:${moneyMark}${orderMac.discountAmount }</c:if><c:if test="${not empty coupon}">- Coupon：${lvOrder.currency} ${lvOrder.couponTotalPrice} </c:if> + Freight:${moneyMark}${lvOrder.postprice }</a></ul>
								<ul class="sum01">Amount Payable：<font class="redfont">${moneyMark}${lvOrder.totalPrice } </font></a></ul>
								<ul class="btn">
									<s:if test="#request.lvOrder.payStatus==0">
										<s:if test="#request.lvOrder.paymethod==4">
											<input type="button" onclick="javascript:location.href='/web/userOrder!toOrderWestern.action?oid=${lvOrder.oid }';" value="Pay Now" class="user_center_bt" />
										</s:if>
										<s:elseif test="#request.lvOrder.paymethod==7"></s:elseif>
										<s:else>
											<input type="button" onclick="javascript:location.href='/web/userOrder!toConfirmPay.action?oid=${lvOrder.oid }&shopFlag=${lvOrder.storeId }';" value="Pay Now" class="user_center_bt" />
										</s:else>
									</s:if>
								  	<input type="button" onclick="javascript:location.href='/web/userOrder!getOrderlist.action';" value="Back" class="user_center_bt" />					  
								</ul>
				  </div>
				
			 <!--End right_Frame-->
		</div>
		
		
		<!-- 底部广告位-->
		<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
			
		<!-- footer-->
		<%@include file="/web/en/common/footer.jsp" %>
		
	</body>
</html> 