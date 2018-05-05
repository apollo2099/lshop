<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!doctype html>
<html>
<head>
<%@include file="/web/mtmcn/user_center/c_meta.jsp"%>
<title>TVpad商城_订单详情</title>
<style type="text/css">
table td{word-break: break-all;}
</style>
</head>
<body>
	<%@include file="/web/mtmcn/user_center/c_top.jsp"%>

	<article>
		<div class="myorder">
			<div class="odderdetial1">
				<table width="100%" border="0" align="center">
					<tr>
						<td width="27%" height="20" valign="top"></td>
						<td width="48%" height="20" valign="top"></td>
						<td width="25%" height="20" valign="top"></td>
					</tr>
					<tr>
						<td height="30" colspan="3" align="left" valign="top"
							class="batitil">${lvStore.name }</td>
					</tr>
					<tr>
						<td height="25" align="left" valign="top" width="27%">订&nbsp;&nbsp;单&nbsp;&nbsp;号：</td>
						<td height="25" valign="top" width="48%">${lvOrder.oid}</td>
						<td width="25%" height="20" valign="top"></td>
					</tr>

					<tr>
						<td height="25" align="left" valign="top" width="27%">订单状态：</td>
						<td width="48%" height="25" valign="top" class="action"><s:if
								test="#request.lvOrder.payStatus==0">待付款</s:if> <s:elseif
								test="#request.lvOrder.payStatus==2">已付款,未确认</s:elseif> <s:elseif
								test="#request.lvOrder.payStatus==1">
								<s:if test="#request.lvOrder.status==0">已付款,待发货</s:if>
								<s:elseif test="#request.lvOrder.status==1">已付款,已发货</s:elseif>
								<s:elseif test="#request.lvOrder.status==2">待评价</s:elseif>
								<s:elseif test="#request.lvOrder.status==4">已完成</s:elseif>
								<s:elseif test="#request.lvOrder.status==3">已退货</s:elseif>
							</s:elseif></td>
						<td width="25%" align="right" valign="top">
								<s:if test="#request.lvOrder.payStatus==0">
									<s:if test="#request.lvOrder.paymethod==4">
										<a
											href="${MallPath}/web/userOrder!toOrderWestern.action?oid=${oid }"
											class="bttip">前往支付</a>
									</s:if>
									<s:else>
										<a href="${MallPath}/web/userOrder!toPay.action?oid=${oid }&shopFlag=${lvOrder.storeId }" class="bttip">前往支付</a>
									</s:else>
								</s:if>
								<s:if test="#request.lvOrder.payStatus==1">
									<s:if test="#request.lvOrder.status==0">
										<a href="javascript:void(0);" onclick="showRemind('${oid }','${lvOrder.storeId }')" class="bttip">提醒发货</a>
									</s:if>
									<s:elseif test="#request.lvOrder.status==1">
										<a href="javascript:void(0);" onclick="confirmShouhuo('${lvOrder.code }');" class="bttip">确认收货</a>
									</s:elseif>
									<s:elseif test="#request.lvOrder.status==2">
										<a
											href="${MallPath}/web/userOrder!toOrderComment.action?oid=${oid }&shopFlag=${lvOrder.storeId }"
											class="bttip">评 价</a>
									</s:elseif>
								</s:if>
							</td>
					</tr>

					<tr>
						<td height="25" align="left" valign="top" width="27%">订单金额：</td>
						<td height="25" valign="top" class="jg" width="48%">${moneyMark}
							${lvOrder.totalPrice}</td>
						<td width="25%" height="20" valign="top"></td>
					</tr>


					<tr>
						<td width="27%" height="20" valign="top"></td>
						<td width="48%" height="20" valign="top"></td>
						<td width="25%" height="20" valign="top"></td>
					</tr>
				</table>

			</div>
			<div class="odderfu">
				<div class="totaljia">
					商品总金额：${lvOrder.currency} ${allAmount} + 运费：${lvOrder.currency} ${lvOrder.postprice	}<c:if test="${not empty coupon}"> - 优惠券：${lvOrder.currency} ${lvOrder.couponTotalPrice} </c:if><br />
					应付总额：<span class="usd">${lvOrder.currency} ${lvOrder.totalPrice}</span>
				</div>
			</div>
		</div>
	</article>

	<article>
		<div class="prodlist">
			<a id="c_prod_btn" href="javascript:void(0)">查看商品清单</a>
		</div>
	</article>

	<article>
		<div class="prodetal">
			<h2>基本信息</h2>
			<div class="protable">
				<table width="94%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td width="30%" height="20" valign="top"></td>
						<td width="70%" height="20" valign="top"></td>
					</tr>
					<tr>
						<td height="25" align="left" valign="top">下单时间：</td>
						<td height="25" valign="top"><s:date name="#request.lvOrder.createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
					</tr>
					<tr>
						<td height="25" align="left" valign="top">支付方式：</td>
						<td height="25" valign="top">
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
		            	<s:if test="#request.lvOrder.paymethod==14 or #request.lvOrder.paymethod==20">
		            		VISA/MASTER
		            	</s:if>
						</td>
					</tr>
					<tr>
						<td height="25" align="left" valign="top">支付时间：</td>
						<td height="25" valign="top">
						<c:if test="${not empty lvOrder.overtime}"><s:date name="#request.lvOrder.overtime" format="yyyy-MM-dd HH:mm:ss"/></c:if>
			  			<c:if test="${empty lvOrder.overtime}">--</c:if>
						</td>
					</tr>
					<tr>
						<td height="25" align="left" valign="top">订单备注：</td>
						<td height="25" valign="top">${lvOrder.orderRemark }</td>
					</tr>
					<c:if test="${not empty coupon}">
					<tr>
						<td height="25" align="left" valign="top">使用优惠券：</td>
						<td height="25" valign="top">${coupon.couponName }</td>
					</tr>
					<tr>
						<td height="25" align="left" valign="top">优惠券面值：</td>
						<td height="25" valign="top">${lvOrder.currency} ${lvOrder.couponTotalPrice }</td>
					</tr>
					</c:if>
					<tr>
						<td width="25%" height="20" valign="top"></td>
						<td width="75%" height="20" valign="top"></td>
					</tr>
				</table>
			</div>
		</div>

		<div class="prodetal">
			<h2>收货人信息</h2>
			<div class="protable">
				<table width="94%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td width="30%" height="20" valign="top"></td>
						<td width="70%" height="20" valign="top"></td>
					</tr>
					<tr>
						<td height="25" align="left" valign="top">收&nbsp;货&nbsp;人：</td>
						<td height="25" valign="top">${lvOrderAdress.relName }</td>
					</tr>
					<tr>
						<td height="25" align="left" valign="top">手&nbsp;&nbsp;&nbsp;&nbsp;机：</td>
						<td height="25" valign="top"><c:out value="${lvOrderAdress.mobile}" default="--"></c:out></td>
					</tr>
					<tr>
						<td height="25" align="left" valign="top">固定电话：</td>
						<td height="25" valign="top"><c:out value="${lvOrderAdress.tel}" default="--"></c:out></td>
					</tr>
					<tr>
						<td height="25" align="left" valign="top">收货地址：</td>
						<td height="25" valign="top">${lvOrderAdress.adress }&nbsp;${lvOrderAdress.cityName }&nbsp;${lvOrderAdress.provinceName }&nbsp;${lvOrderAdress.contryName }</td>
					</tr>
					<tr>
						<td height="25" align="left" valign="top">邮&nbsp;&nbsp;&nbsp;&nbsp;编：</td>
						<td height="25" valign="top">${lvOrderAdress.postCode }</td>
					</tr>
					<tr>
						<td width="25%" height="20" valign="top"></td>
						<td width="75%" height="20" valign="top"></td>
					</tr>
				</table>
			</div>
		</div>
		
		<s:if test="#request.lvOrder.payStatus!=0 and #request.lvOrder.paymethod==4">
		<div class="prodetal">
	     <h2>西联信息</h2>
	     <div class="protable"> 
	       <table width="94%" border="0" align="center" cellpadding="0" cellspacing="0">
			<tr>
			   <td width="25%" height="20" valign="top"></td>
			   <td width="82%" height="20" valign="top"></td>
			</tr>
	 
			<tr>
			  <td width="25%" height="25" align="left" valign="top"> 汇&nbsp;&nbsp;款&nbsp;&nbsp;人：</td>
			  <td height="25" valign="top">${lvWesternInfo.remitter }</td>
			</tr>
			
			<tr>
			  <td width="25%" height="25" align="left" valign="top">汇款金额：</td>
			  <td height="25" valign="top">USD ${lvWesternInfo.remittance  }</td>
			</tr>
			
			 <tr>
			  <td width="25%" height="25" align="left" valign="top">汇款国家：</td>
			  <td height="25" valign="top">${lvWesternInfo.contryName }</td>
			</tr>
			
			 <tr>
			  <td width="25%" height="25" align="left" valign="top">汇款城市：</td>
			  <td height="25" valign="top" >${lvWesternInfo.adress }</td>
			</tr>
			  <tr>
			  <td width="25%" height="25" align="left" valign="top">汇款时间：</td>
			  <td height="25" valign="top">
			  <c:if test="${not empty lvWesternInfo.transferTime}">${lvWesternInfo.transferTime}</c:if>
			  <c:if test="${empty lvWesternInfo.transferTime}">--</c:if>
			  </td>
			</tr>
			
			 <tr>
			  <td width="25%" height="25" align="left" valign="top">&nbsp;&nbsp;&nbsp;&nbsp;MTCN：</td>
			  <td height="25" valign="top" >${lvWesternInfo.mtcn }</td>
			</tr>
			<tr>
			    <td width="25%" height="20" valign="top"></td>
			    <td width="82%" height="20" valign="top"></td>
			</tr>
			</table>
	     </div> 
	   </div>
		</s:if>
		
		<s:if test="#request.lvOrder.status!=0">
		<div class="prodetal">
			<h2>物流信息 ( <a href="${MallPath}/web/userOrder!doLogisticsTrackingInfo.action?oid=${oid}&shopFlag=${lvOrder.storeId }" style="font-weight:normal; color:#0099ff; text-decoration:underline; font-size:14px">跟踪信息</a>）</h2>
			<div class="protable"> 
				<table width="94%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td width="25%" height="20" valign="top"></td>
					<td width="82%" height="20" valign="top"></td>
				</tr>
				<tr>
					<td width="25%" height="25" align="left" valign="top"> 快递公司：</td>
					<td height="25" valign="top">${lvOrder.expressCompany  }</td>
				</tr>
				
				<tr>
					<td width="25%" height="25" align="left" valign="top">快递单号：</td>
					<td height="25" valign="top">${lvOrder.expressNum }</td>
				</tr>
				
				 <tr>
					<td width="25%" height="25" align="left" valign="top">发货时间：</td>
					<td height="25" valign="top"><s:date name="#request.lvOrder.shipTime" format="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>
				
				<tr>
					<td width="25%" height="25" align="left" valign="top">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
					<td height="25" valign="top" >${lvOrder.sendRemark }</td>
				</tr>
				<tr>
					<td width="25%" height="20" valign="top"></td>
					<td width="82%" height="20" valign="top"></td>
				</tr>
				</table>
			</div> 
		</div>
		</s:if>
	</article>
	<!-- 商品清单 -->
	<header class="pls" style="display:none;">
		<div class="top">
			<div class="title1">
				<h1>商品清单</h1>
			</div>
		</div>
	</header>
	<article class="pls" style="display:none;">
		<div class="fanhui">
			<div class="fanhui_inner">
				<a id="c_order_btn" href="javascript:void(0);">返回</a>
			</div>
		</div>
	</article>
	<article class="pls " style="display:none;">
		<div class="mycart">
			<div class="cartlist">
			<c:foreach items="${objList}" var="obj">
				<ul>
					<li>
						<div class="imgchek">
							<table width="100%" border="0">
								<tr>
									<td><a href="http://${lvStore.domainName }/products/${obj[0].code}.html">
									<img src="${obj[0].pimage }" width="90%"/>
									</a></td>
								</tr>
							</table>
						</div>
						<div class="totalconts">
							<a href="http://${lvStore.domainName }/products/${obj[0].code}.html">
								<div class="imgname">
									<p>${obj[0].productName }</p>
								</div>
							</a>
							<div>
								<table width="100%" border="0">
									<tr>
										<td valign="middle" class="imgprcont">数量：X ${obj[1].pnum }</td>
										<td valign="middle" class="imgjiag">${moneyMark}${obj[2] }</td>
									</tr>
								</table>
							</div>
						</div>
						<div class="clear"></div>
					</li>
				</ul>
			</c:foreach>
			</div>
			<!-- 赠品信息 -->	
			<c:if test="${not empty  giftList}">
			<div class="addcoupons" id="order_activity_tip">
				<img src="${resDomain}/mtmcn/res/images/zp1.jpg" />
				 	<c:foreach items="${ giftList}" var="g">
				       <span>${g.giftName }<i class="usd">x${g.giftNum }</i></span>&nbsp;&nbsp;&nbsp;
				 	</c:foreach>
			
			</div>
			</c:if>
		</div>

		
	</article>
	<%@include file="/web/mtmcn/user_center/c_bottom.jsp"%>
	<!-- 对话框 -->
	<div id="c_receiv_area" class="mark_tip">
	   <div class="mark_tip_titile"><h2>确认已收货？</h2></div>
	   <div class="tipdetail"></div>
	   <input type="hidden" id="c_receiv_val" />
	   <div class="bt_marktip">
	     <a href="javascript:void(0);" id="c_receiv_btn">确定</a>
	     <a href="javascript:void(0);" class="c_cancle_btn">取消</a>
	   </div>
	</div>
	<div id="c_remind_area" class="mark_tip">
	   <div class="mark_tip_titile"><h2>提醒发货</h2></div>
	   <div class="tipdetail">已成功发送提醒，请耐心等待！或联系客服！</div>
	   <div class="bt_marktip">
	     <a id="c_remind_btn" href="javascript:void(0);" style="margin:0 auto; float:none">确定</a>
	   </div>
	</div>
	<script type="text/javascript">
	document.getElementById('c_title').innerHTML = '订单详情';
	//订单商品列表
	$('#c_prod_btn').click(function(e){
		$('header, article').hide();
		$('.pls').show();
	});
	$('#c_order_btn').click(function(e){
		$('header, article').show();
		$('.pls').hide();
	});
	//对话框
	$('.c_cancle_btn').click(function(e){
		uncenter($('.mark_tip'));
	});
	//确定收货
	$('#c_receiv_btn').click(function(e){
		var oid = $('#c_receiv_val').val();
		window.location.href="/web/userOrder!confirmShouhuo.action?code="+oid;
	});
	function confirmShouhuo(oid){
		$('#c_receiv_val').val(oid);
		center($('#c_receiv_area'));
	}
	//提醒卖家发货
	function showRemind(oid,shopFlag){
		$.ajax({
		   type: "POST",
		   url: "/web/userOrder!remindOrder.action",
		   data: "oid="+oid+"&shopFlag="+shopFlag,
		   success: function(){
				center($('#c_remind_area'));
		   }
		});
	}
	$('#c_remind_btn').click(function(e){
		uncenter($('#c_remind_area'));
	});
	</script>
</body>
</html>
