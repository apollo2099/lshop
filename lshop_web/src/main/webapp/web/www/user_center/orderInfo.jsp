<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="cus" uri="/WEB-INF/tld/cus.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>用戶中心_TVpad商城</title>
		<link href="${resDomain}/www/res/css/user_center.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<%@include file="/web/www/common/top.jsp"%>
	</head>
	
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/www/common/header.jsp"%>
			
		<div class="content_main">
		
			<!-- left_frame -->
			<%@include file="/web/www/user_center/leftFrame.jsp" %>
			 
			 <div class="right_frame">
			 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/www/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">首頁</a>--><a href="/web/userCenterManage!getAccount.action">用戶中心</a>--><a>我的訂單</a></font> </span></h1> 
				
				<table width="760" border="0" cellpadding="1"  cellspacing="1" bgcolor="#E3E2E2" >
		        <tr>
		          <td height="40" colspan="4" bgcolor="#F2F1F1">
		          	<font class="bfont fontwz">商家名稱：<span class="rd">${lvStore.name }&nbsp;</span> </font>
		          	<font class="bfont fontwz">訂單當前狀態：</font> 
		          	<s:if test="#request.lvOrder.payStatus==0">
		          		<font class="redfont">待付款</font>&nbsp; <img src="${resDomain}/www/res/images/personel_center_icon03.gif" width="16" height="16" /> 
		          		<s:if test="#request.lvOrder.paymethod!=4">
		          			<a href="/web/userOrder!toConfirmPay.action?oid=${lvOrder.oid }&shopFlag=${lvOrder.storeId }">現在支付</a>
		          		</s:if>
		          		<s:if test="#request.lvOrder.paymethod==4">
		          			<a href="/web/userOrder!toOrderWestern.action?oid=${lvOrder.oid }">提交西聯信息</a>
		          		</s:if>
		          	</s:if>
		          	<s:elseif test="#request.lvOrder.payStatus==2">
		          		<font class="redfont">已付款,未確認</font> 
		          	</s:elseif>
		          	<s:elseif test="#request.lvOrder.payStatus==1">
		          		<s:if test="#request.lvOrder.status==0">
		          			<font class="redfont">已付款,待發貨</font> 
		          		</s:if>
		          		<s:elseif test="#request.lvOrder.status==1">
		          			<font class="redfont">已付款,已發貨</font> 
		          		</s:elseif>
		          		<s:elseif test="#request.lvOrder.status==2">
		          			<font class="redfont">待評價</font> 
		          		</s:elseif>
		          		<s:elseif test="#request.lvOrder.status==4">
		          			<font class="redfont">已完成</font> 
		          		</s:elseif>
		          		<s:elseif test="#request.lvOrder.status==3">
		          			<font class="redfont">已退貨</font> 
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
		          <td  width="133" height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>訂單金額：<br />
		          </strong></td>
		          <td  width="209" height="30" bgcolor="#FFFFFF" style="text-align:center">${moneyMark} ${lvOrder.totalPrice}</td>
		          <td width="133" height="30" bordercolor="#F6D0C9" bgcolor="#FFFFFF" style="text-align:right"><strong>付款方式：<br />
		          </strong></td>
		          <td width="209" height="30" bordercolor="#F6D0C9" bgcolor="#FFFFFF" style="text-align:center">
          			    <s:if test="#request.lvOrder.paymethod==1">
		            		PAYPAL支付
		            	</s:if>
		            	<s:if test="#request.lvOrder.paymethod==2">
		            		国际信用卡
		            	</s:if>
		            	<s:if test="#request.lvOrder.paymethod==3 or #request.lvOrder.paymethod==18">
		            		支付寶
		            	</s:if>
		            	<s:if test="#request.lvOrder.paymethod==4">
		            		西聯匯款
		            	</s:if>
		            	<s:if test="#request.lvOrder.paymethod==6">
		            		快錢支付
		            	</s:if>
		            	<s:if test="#request.lvOrder.paymethod==8">
		            		雙乾支付
		            	</s:if>
		            	<s:if test="#request.lvOrder.paymethod==11 or #request.lvOrder.paymethod==15">
		            		Visa國際信用卡或借記卡
		            	</s:if>
		            	<s:if test="#request.lvOrder.paymethod==12 or #request.lvOrder.paymethod==16">
		            		Master國際信用卡或借記卡
		            	</s:if>
		            	<s:if test="#request.lvOrder.paymethod==13 or #request.lvOrder.paymethod==17">
		            		JCB國際信用卡或借記卡
		            	</s:if>
		            	<s:if test="#request.lvOrder.paymethod==14 or #request.lvOrder.paymethod==20">
		            		VISA/MASTER
		            	</s:if>
		            	
		          </td>
		        </tr>
		        <c:if test="${not empty coupon}">
		        <tr>
		          <td width="117"  height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>使用優惠券：<br />
		          </strong></td>
		          <td width="273" height="30" bgcolor="#FFFFFF" style="text-align:center">${coupon.couponName}<br /></td>
		          <td width="133" height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>優惠券面值：<br />
		          </strong></td>
		          <td width="209" height="30" bgcolor="#FFFFFF" style="text-align:center">${lvOrder.currency} ${lvOrder.couponTotalPrice}</td>
		        </tr>
		        </c:if>
		        <tr>
		          <td width="117" height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>收貨人：<br />
		          </strong></td>
		          <td  width="273" height="30" bgcolor="#FFFFFF" style="text-align:center">${lvOrderAdress.relName }<br /></td>
		          <td width="117" height="30" bordercolor="#F6D0C9" bgcolor="#FFFFFF" style="text-align:right"><strong>E-mail：<br />
		          </strong></td>
		          <td  width="273" height="30" bordercolor="#F6D0C9" bgcolor="#FFFFFF" style="text-align:center">${lvOrder.userEmail }<br /></td>
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
		          <!--<td  width="117" height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>最佳收貨時間：：<br />
		          </strong></td>
		          <td  width="273" height="30" bgcolor="#FFFFFF" style="text-align:center">
		          	  <s:if test="#request.lvOrderAdress.bestDeliveryTime==1">任何时间均可以送货</s:if>
			          <s:if test="#request.lvOrderAdress.bestDeliveryTime==2">周一至周五送货（周末没人无法签收）</s:if>
			          <s:if test="#request.lvOrderAdress.bestDeliveryTime==3">周末送货（工作日没人无法签收）</s:if>
			          <s:if test="#request.lvOrderAdress.bestDeliveryTime==4">晚上送货（白天没人无法签收）</s:if>
		          </td>  -->
		          <td  width="133" height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>郵政編碼：<br />
		          </strong></td>
		          <td  colspan="3" height="30" bgcolor="#FFFFFF" style="text-align:center">${lvOrderAdress.postCode }</td>
		        </tr>
		        <tr>
		          <td  width="117" height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>收貨地址：<br />
		          </strong></td>
		          <td height="30" colspan="3" bgcolor="#FFFFFF" style="text-align:center;word-break:break-all">${lvOrderAdress.adress }&nbsp;${lvOrderAdress.cityName }&nbsp;${lvOrderAdress.provinceName }&nbsp;${lvOrderAdress.contryName }</td>
		        </tr>
		        <tr>
		          <td  width="117" height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>其他要求：<br />
		          </strong></td>
		          <td width="643" height="30" colspan="3" bgcolor="#FFFFFF" style="text-align:center;word-break:break-all">${lvOrder.orderRemark }<br /></td>
		        </tr>
		       <s:if test="#request.lvOrder.status!=0">
				    <tr>
			          <td height="40" colspan="4" bgcolor="#F2F1F1"><font class="bfont fontwz">物流信息</font><a href="/web/userOrder!doLogisticsTrackingInfo.action?oid=${oid}&shopFlag=${lvOrder.storeId }"><font class="redfont">(查看物流動態)</font></a></td>
			        </tr>
			        <tr>
			          <td bgcolor="#FFFFFF" style="text-align:right"><strong>快遞公司：<br /> </strong></td>
			          <td height="30" bgcolor="#FFFFFF" style="text-align:center">${lvOrder.expressCompany  }</td>
			          <td bgcolor="#FFFFFF" style="text-align:right"><strong>快遞單號：<br /></strong></td>
			          <td height="30" bgcolor="#FFFFFF" style="text-align:center"><a href="/web/userOrder!doLogisticsTrackingInfo.action?oid=${lvOrder.oid}&shopFlag=${lvOrder.storeId }" title="查看物流動態">${lvOrder.expressNum }</a></td>
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
											<p><font class="redfont fontwz">${moneyMark}${obj[2] }</font></p>
										</li>
									</c:foreach>
								</ul>
							
							    <!-- 赠品信息 -->	
							    <c:if test="${not empty  giftList}">
								<ul class="zp_list">
								<li><img src="${resDomain}/www/res/images/zp.jpg" width="43" height="17" />
								<c:foreach items="${giftList }" var="g">
	                             ${g.giftName }<font class="redfont"> ×${g.giftNum  }</font>&nbsp;&nbsp;&nbsp;
	                            </c:foreach>
	                            </li>
	                            </ul>
	                            </c:if>
								
								<ul class="sum">商品總金額：${moneyMark}${allAmount} <c:if test="${not empty orderMac}">- MAC活動減免:${moneyMark}${orderMac.discountAmount }</c:if><c:if test="${not empty coupon}">- 優惠券減免：${lvOrder.currency} ${lvOrder.couponTotalPrice} </c:if>+ 運費:${moneyMark}${lvOrder.postprice }</a></ul>
								<ul class="sum01">應付金額：<font class="redfont">${moneyMark}${lvOrder.totalPrice } </font></a></ul>
								<ul class="btn">
									<s:if test="#request.lvOrder.payStatus==0">
										<s:if test="#request.lvOrder.paymethod==4">
											<input type="button" onclick="javascript:location.href='/web/userOrder!toOrderWestern.action?oid=${lvOrder.oid }';" value="提交西聯信息" class="user_center_bt" />
										</s:if>
										<s:elseif test="#request.lvOrder.paymethod==7"></s:elseif>
										<s:else>
											<input type="button" onclick="javascript:location.href='/web/userOrder!toConfirmPay.action?oid=${lvOrder.oid }&shopFlag=${lvOrder.storeId }';" value="現在支付" class="user_center_bt" />
										</s:else>
									</s:if>
								  	<input type="button" onclick="javascript:location.href='/web/userOrder!getOrderlist.action';" value="返回" class="user_center_bt" />					  
								</ul>
				  </div>
				</div>
			 <!--End right_Frame-->
		</div>
		
		
		<!-- 底部广告位-->
		<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
			
		<!-- footer-->
		<%@include file="/web/www/common/footer.jsp" %>
	
	</body>
</html> 