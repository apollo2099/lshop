<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="u" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="cus" uri="/WEB-INF/tld/cus.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>用户中心_banana商城</title>
		<link href="${resDomain}/bmcn/res/css/user_center.css" rel="stylesheet" type="text/css" />
		<!-- 加载公共JS -->
		<%@include file="/web/bmcn/common/top.jsp"%>
	</head>
	
	<body>
		<!-- 获取商城头部文件 -->
		<%@include file="/web/bmcn/common/header.jsp"%>
			
		<div class="content_main">
		
			<!-- left_frame -->
			<%@include file="/web/bmcn/user_center/leftFrame.jsp" %>
			 
			 <div class="right_frame">
			 	<h1><span class="s_r"><font class="bfont"><img src="${resDomain}/bmcn/res/images/icon02.gif" width="15" height="15" /><a href="/index.html">首页</a> --><a href="/web/userCenterManage!getAccount.action"> 用户中心</a> --></font> 我的订单</span></h1> 
				
				<table width="760" border="0" cellpadding="1"  cellspacing="1" bgcolor="#E3E2E2" >
		        <tr>
		          <td height="40" colspan="4" bgcolor="#F2F1F1">
		          	<font class="bfont fontwz">商家名称：<span class="rd">${lvStore.name }&nbsp;</span> </font>
		          	<font class="bfont fontwz">订单当前状态：</font> 
		          	<s:if test="#request.lvOrder.payStatus==0">
		          		<font class="redfont">待付款</font>&nbsp; <img src="${resDomain}/bmcn/res/images/personel_center_icon03.gif" width="16" height="16" /> 
		          		<s:if test="#request.lvOrder.paymethod!=4">
		          			<a href="/web/userOrder!toConfirmPay.action?oid=${lvOrder.oid }&shopFlag=${lvOrder.storeId }">现在支付</a>
		          		</s:if>
		          		<s:if test="#request.lvOrder.paymethod==4">
		          			<a href="/web/userOrder!toOrderWestern.action?oid=${lvOrder.oid }">提交西联信息</a>
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
		          <td width="117"  height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>订单号：<br />
		          </strong></td>
		          <td width="273" height="30" bgcolor="#FFFFFF" style="text-align:center">${lvOrder.oid}<br /></td>
		          <td width="133" height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>下单时间：<br />
		          </strong></td>
		          <td width="209" height="30" bgcolor="#FFFFFF" style="text-align:center"><s:date name="#request.lvOrder.createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
		        </tr>
		        <tr>
		          <td  width="133" height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>订单金额：<br />
		          </strong></td>
		          <td  width="209" height="30" bgcolor="#FFFFFF" style="text-align:center">${lvOrder.currency} ${lvOrder.totalPrice}</td>
		          <td width="133" height="30" bordercolor="#F6D0C9" bgcolor="#FFFFFF" style="text-align:right"><strong>付款方式：<br />
		          </strong></td>
		          <td width="209" height="30" bordercolor="#F6D0C9" bgcolor="#FFFFFF" style="text-align:center">
	          			<s:if test="#request.lvOrder.paymethod==3 or #request.lvOrder.paymethod==18">
		            		支付宝
		            	</s:if>
		            	<s:if test="#request.lvOrder.paymethod==4">
		            		西联汇款
		            	</s:if>
		            	<s:if test="#request.lvOrder.paymethod==11 or #request.lvOrder.paymethod==15">
		            		Visa国际信用卡或借记卡
		            	</s:if>
		            	<s:if test="#request.lvOrder.paymethod==12 or #request.lvOrder.paymethod==16">
		            		Master国际信用卡或借记卡
		            	</s:if>
		            	<s:if test="#request.lvOrder.paymethod==13 or #request.lvOrder.paymethod==17">
		            		JCB国际信用卡或借记卡
		            	</s:if>
		            	<s:if test="#request.lvOrder.paymethod==14">
		            		VISA/MASTER
		            	</s:if>
		          </td>
		        </tr>
		        <c:if test="${not empty coupon}">
		        <tr>
		          <td width="117"  height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>使用优惠券：<br />
		          </strong></td>
		          <td width="273" height="30" bgcolor="#FFFFFF" style="text-align:center">${coupon.couponName}<br /></td>
		          <td width="133" height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>优惠券面值：<br />
		          </strong></td>
		          <td width="209" height="30" bgcolor="#FFFFFF" style="text-align:center">${lvOrder.currency} ${lvOrder.couponTotalPrice}</td>
		        </tr>
		        </c:if>
		        <tr>
		          <td width="117" height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>收货人：<br />
		          </strong></td>
		          <td  width="273" height="30" bgcolor="#FFFFFF" style="text-align:center">${lvOrderAdress.relName }<br /></td>
		          <td width="117" height="30" bordercolor="#F6D0C9" bgcolor="#FFFFFF" style="text-align:right"><strong>E-mail：<br />
		          </strong></td>
		          <td  width="273" height="30" bordercolor="#F6D0C9" bgcolor="#FFFFFF" style="text-align:center">${lvOrder.userEmail }<br /></td>
		        </tr>
		        <tr>
		          <td  width="117" height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>手机号码：<br />
		          </strong></td>
		          <td  width="273" height="30" bgcolor="#FFFFFF" style="text-align:center">${lvOrderAdress.mobile }<br /></td>
		          <td  width="133" height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>固定电话：<br />
		          </strong></td>
		          <td  width="209" height="30" bgcolor="#FFFFFF" style="text-align:center">${lvOrderAdress.tel }</td>
		        </tr>
		        <tr>
		          <td  width="133" height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>邮政编码：<br />
		          </strong></td>
		          <td  colspan="3" height="30" bgcolor="#FFFFFF" style="text-align:center">${lvOrderAdress.postCode }</td>
		        </tr>
		        <tr>
		          <td  width="117" height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>收货地址：<br />
		          </strong></td>
		          <td height="30" colspan="3" bgcolor="#FFFFFF" style="text-align:center;word-break:break-all">
		           		<c:if test="${lvOrderAdress.contryId==100023}">
							${lvOrderAdress.contryName }&nbsp;${lvOrderAdress.provinceName}&nbsp;${lvOrderAdress.cityName }&nbsp;${lvOrderAdress.adress }
						</c:if>
						<c:if test="${lvOrderAdress.contryId!=100023}">
							${lvOrderAdress.adress }&nbsp;${lvOrderAdress.cityName }&nbsp;${lvOrderAdress.provinceName}&nbsp;${lvOrderAdress.contryName }
						</c:if>
		          </td>
		        </tr>
		        <tr>
		          <td  width="117" height="30" bgcolor="#FFFFFF" style="text-align:right"><strong>其他要求：<br />
		          </strong></td>
		          <td width="643" height="30" colspan="3" bgcolor="#FFFFFF" style="text-align:center;word-break:break-all">${lvOrder.orderRemark }<br /></td>
		        </tr>
		       <s:if test="#request.lvOrder.status!=0">
				    <tr>
			          <td height="40" colspan="4" bgcolor="#F2F1F1"><font class="bfont fontwz">物流信息</font><a href="/web/userOrder!doLogisticsTrackingInfo.action?oid=${oid}&shopFlag=${lvOrder.storeId }"><font class="redfont">(查看物流动态)</font></a></td>
			        </tr>
			        <tr>
			          <td bgcolor="#FFFFFF" style="text-align:right"><strong>快运公司：<br /> </strong></td>
			          <td height="30" bgcolor="#FFFFFF" style="text-align:center">${lvOrder.expressCompany  }</td>
			          <td bgcolor="#FFFFFF" style="text-align:right"><strong>快运单号：<br /></strong></td>
			          <td height="30" bgcolor="#FFFFFF" style="text-align:center"><a href="/web/userOrder!doLogisticsTrackingInfo.action?oid=${lvOrder.oid}&shopFlag=${lvOrder.storeId }" title="查看物流动态">${lvOrder.expressNum }</a></td>
			        </tr>
			        <tr>
			          <td bgcolor="#FFFFFF" style="text-align:right"><strong>发货时间：</strong></td>
			          <td height="30" bgcolor="#FFFFFF" style="text-align:center"><s:date name="#request.lvOrder.shipTime" format="yyyy-MM-dd HH:mm:ss"/></td>
			          <td bgcolor="#FFFFFF" style="text-align:right"><strong>备注：</strong></td>
			          <td height="30" bgcolor="#FFFFFF" style="text-align:center" >${lvOrder.sendRemark }</td>
			        </tr>
		        </s:if>
		        <s:if test="#request.lvOrder.payStatus!=0 and #request.lvOrder.paymethod==4">
			        <tr>
			           <td height="40" colspan="4" bgcolor="#F2F1F1"><font class="bfont fontwz">西联汇款信息</font></td>
			        </tr>
				    <tr>
			          <td bgcolor="#FFFFFF" style="text-align:right"><strong>汇款人姓名：<br /></strong></td>
			          <td height="30" bgcolor="#FFFFFF" style="text-align:center">${lvWesternInfo.remitter }</td>
			          <td bgcolor="#FFFFFF" style="text-align:right"><strong>汇款金额：<br /></strong></td>
			          <td height="30" bgcolor="#FFFFFF" style="text-align:center">USD ${lvWesternInfo.remittance  }</td>
			        </tr>
			        <tr>
			          <td bgcolor="#FFFFFF" style="text-align:right"><strong>汇款监控号码：</strong></td>
			          <td height="30" bgcolor="#FFFFFF" style="text-align:center">${lvWesternInfo.mtcn }</td>
			          <td bgcolor="#FFFFFF" style="text-align:right"><strong>汇款国家/地区：</strong></td>
			          <td height="30" bgcolor="#FFFFFF" style="text-align:center">${lvWesternInfo.adress }&nbsp;${lvWesternInfo.contryName }</td>
			        </tr>
		        </s:if>
		      </table>	
					
					<div class="buy_order">
								<ul>
									<li class="buy_order01">
										<p>&nbsp;</p>
										<p class="title">商品名称</p>
										<p>价格</p>
										<p>购买数量</p>
										<p>小计金额</p>
									</li>
									<c:foreach items="${objList}" var="obj">
										<li>
											<p><img src="${obj[0].pimage }" width="70px" height="60px"/></p>
											<p class="title" title="${obj[0].productName }">${obj[0].productName }</p>
											<p><font class="redfont fontwz">${lvOrder.currency} ${obj[1].oprice }</font></p>
											<p>${obj[1].pnum }</p>
											<p><font class="redfont fontwz">${lvOrder.currency} ${obj[2] }</font></p>
										</li>
									</c:foreach>
								</ul>
								<ul class="sum">商品总金额：${lvOrder.currency} ${allAmount} <c:if test="${not empty coupon}">- 优惠券减免：${lvOrder.currency} ${lvOrder.couponTotalPrice} </c:if>+ 运费:${lvOrder.currency} ${lvOrder.postprice }</a></ul>
								<ul class="sum01">应付金额：<font class="redfont">${lvOrder.currency} ${lvOrder.totalPrice } </font></a></ul>
								<ul class="btn">
									<s:if test="#request.lvOrder.payStatus==0">
										<s:if test="#request.lvOrder.paymethod==4">
											<input type="button" onclick="javascript:location.href='/web/userOrder!toOrderWestern.action?oid=${lvOrder.oid }';" value="提交西联信息" class="user_center_bt" style="CURSOR: pointer; "/>
										</s:if>
										<s:elseif test="#request.lvOrder.paymethod==7"></s:elseif>
										<s:else>
											<input type="button" onclick="javascript:location.href='/web/userOrder!toConfirmPay.action?oid=${lvOrder.oid }&shopFlag=${lvOrder.storeId }';" value="现在支付" class="user_center_bt" style="CURSOR: pointer; "/>
										</s:else>
									</s:if>
								  	<input type="button" onclick="javascript:location.href='/web/userOrder!getOrderlist.action';" value="返回" class="user_center_bt" style="CURSOR: pointer; "/>					  
								</ul>
				 </div>
			</div>
			<!--End right_Frame-->
			<div class="cb"></div>
		</div>
		
		
		<!-- 底部广告位-->
		<ad:ad adkey="AD_TVPAD_BOTTOM"></ad:ad>
			
		<!-- footer-->
		<%@include file="/web/bmcn/common/footer.jsp" %>
	
	</body>
</html> 