<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="cus" uri="/WEB-INF/tld/cus.tld"%>
<!doctype html>
<html>
<head>
<%@include file="/web/mtmcn/user_center/c_meta.jsp"%>
<title>TVpad商城_我的订单</title>
</head>
<body>
	<%@include file="/web/mtmcn/user_center/c_top.jsp"%>

	<article class="c-pager" totalpage="${page.totalPage}">
		<s:iterator value="page.list" status="entry">
			<div class="myorder">
				<a href="${MallPath}/web/userOrder!viewOrderInfo.action?oid=${oid }">
					<div class="odderdetial">
						<table width="100%" border="0" align="center">
							<tr>
								<td width="24%" height="10" valign="top"></td>
								<td width="76%" height="10" valign="top"></td>
							</tr>
							<tr>
								<td height="30" colspan="2" align="left" valign="top"
									class="batitil"><cus:store param="storeName" shopFlag="${storeId}"/></td>
							</tr>
							<tr>
								<td height="25" align="left" valign="top">
									订&nbsp;&nbsp;单&nbsp;&nbsp;号：</td>
								<td height="25" valign="top">${oid }</td>
							</tr>
							<tr>
								<td height="25" align="left" valign="top">订单金额：</td>
								<td height="25" valign="top" class="jg">${currency} ${totalPrice}</td>
							</tr>
							<tr>
								<td height="25" align="left" valign="top">下单时间：</td>
								<td height="25" valign="top"><s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
							</tr>
							<tr>
								<td height="25" align="left" valign="top">支付方式：</td>
								<td height="25" valign="top">
									<s:if test="paymethod==3||paymethod==18">支付宝</s:if>
					            	<s:if test="paymethod==4">西联汇款</s:if>
					            	<s:if test="paymethod==11||paymethod==15">Visa国际信用卡或借记卡</s:if>
					            	<s:if test="paymethod==12||paymethod==16">Master国际信用卡或借记卡</s:if>
					            	<s:if test="paymethod==13||paymethod==17">JCB国际信用卡或借记卡</s:if>
					            	<s:if test="paymethod==14||paymethod==20">VISA/MASTER</s:if>
				            	</td>
							</tr>

							<tr>
								<td width="24%" height="10" valign="top"></td>
								<td width="76%" height="10" valign="top"></td>
							</tr>
						</table>
					</div>
				</a>
				<div class="odderfu">
					<div class="odderfubox">
						<div class="oderat">
							<span>订单状态：</span>
							<span class="action">
							<s:if test="payStatus==0">待付款</s:if>
							<s:elseif test="payStatus==2">已付款,未确认</s:elseif>
							<s:elseif test="payStatus==1">
								<s:if test="status==0">已付款,待发货</s:if>
								<s:elseif test="status==1">已付款,已发货</s:elseif>
								<s:elseif test="status==2">待评价</s:elseif>
								<s:elseif test="status==4">已完成</s:elseif>
								<s:elseif test="status==3">已退货</s:elseif>
							</s:elseif>
							</span>
						</div>
						<div class="zhifu">
						<c:choose>
						<c:when test="${payStatus==0}">
							<s:if test="paymethod==4">
							<a href="${MallPath}/web/userOrder!toOrderWestern.action?oid=${oid }" >前往支付</a>
							</s:if><s:else>
							<a href="${MallPath}/web/userOrder!toPay.action?oid=${oid }&shopFlag=${storeId }">前往支付</a>
							</s:else>
						</c:when>
						<c:when test="${payStatus==2}">
							<a href="${MallPath}/web/userOrder!viewOrderInfo.action?oid=${oid }" >详 情</a>
						</c:when>
						<c:when test="${payStatus==1}">
							<s:if test="status==0"><a href="javascript:void(0);" onclick="showRemind('${oid }','${storeId }')">提醒发货</a></s:if>
							<s:elseif test="status==1"><a href="javascript:void(0);" onclick="confirmShouhuo('${code }');">确认收货</a></s:elseif>
							<s:elseif test="status==2"><a href="${MallPath}/web/userOrder!toOrderComment.action?oid=${oid }&shopFlag=${storeId }">评 价</a></s:elseif>
							<s:elseif test="status==4"><a href="${MallPath}/web/userOrder!viewOrderInfo.action?oid=${oid }">详  情</a></s:elseif>
							<s:elseif test="status==3"><a href="${MallPath}/web/userOrder!viewOrderInfo.action?oid=${oid }">详  情</a></s:elseif>
						</c:when>
						</c:choose>
						</div>
						<div class="clear"></div>
					</div>
				</div>
			</div>
		</s:iterator>
		<c:if test="${page.totalPage>1}">
		<div nextpage="2" build="buildOrder" class="more"><span>查看更多</span></div>
		</c:if>
		<c:if test="${empty page.list}">
		<div class="myorder" style="border:none; height:150px; font-size:14px; text-align:center; line-height:120px">
		订单为空
	   </div>
		</c:if>
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
		document.getElementById('c_title').innerHTML = '我的订单';
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
		//查看更多
		function buildOrder(pageNo){
			//取得订单
			var htm = '';
			var url = '/web/userOrder!toOrderInfo.action';
			url += '?payStatus=${payStatus}&status=${status}&orderId=${orderId }';
			url += '&storeName=${storeName }&startTime=${startTime }&endTime=${endTime }';
			url += '&oidStatus=${oidStatus }&page.pageNum='+pageNo;
			$.ajax({
			url: url,
			async: false,
			dataType: 'html',
			success: function(da){
					htm = da;
				}
			});
			
			return htm;
		}
	</script>
</body>
</html>
									