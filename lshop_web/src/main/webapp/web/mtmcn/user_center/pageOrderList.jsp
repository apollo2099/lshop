<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="cus" uri="/WEB-INF/tld/cus.tld"%>
<s:iterator value="page.list" status="entry">
	<div class="myorder">
		<a href="/web/userOrder!viewOrderInfo.action?oid=${oid }">
			<div class="odderdetial">
				<table width="100%" border="0" align="center">
					<tr>
						<td width="24%" height="10" valign="top"></td>
						<td width="76%" height="10" valign="top"></td>
					</tr>
					<tr>
						<td height="30" colspan="2" align="left" valign="top"
							class="batitil"><cus:store param="storeName"
								shopFlag="${storeId}" /></td>
					</tr>
					<tr>
						<td height="25" align="left" valign="top">
							订&nbsp;&nbsp;单&nbsp;&nbsp;号：</td>
						<td height="25" valign="top">${oid }</td>
					</tr>
					<tr>
						<td height="25" align="left" valign="top">订单金额：</td>
						<td height="25" valign="top" class="jg">${currency}
							${totalPrice}</td>
					</tr>
					<tr>
						<td height="25" align="left" valign="top">下单时间：</td>
						<td height="25" valign="top"><s:date name="createTime"
								format="yyyy-MM-dd HH:mm:ss" /></td>
					</tr>
					<tr>
						<td height="25" align="left" valign="top">支付方式：</td>
						<td height="25" valign="top"><s:if test="paymethod==3">支付宝</s:if>
							<s:if test="paymethod==4">西联汇款</s:if> <s:if
								test="paymethod==11||paymethod==15">Visa国际信用卡或借记卡</s:if> <s:if
								test="paymethod==12||paymethod==16">Master国际信用卡或借记卡</s:if> <s:if
								test="paymethod==13||paymethod==17">JCB国际信用卡或借记卡</s:if> <s:if
								test="paymethod==14">VISA/MASTER</s:if></td>
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
					<span>订单状态：</span> <span class="action"> <s:if
							test="payStatus==0">待付款</s:if> <s:elseif test="payStatus==2">已付款,未确认</s:elseif>
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
					<s:if test="payStatus==0">
						<s:if test="paymethod==4">
							<a
								href="/web/userOrder!toOrderWestern.action?oid=${oid }">前往支付</a>
						</s:if>
						<s:else>
							<a
								href="/web/userOrder!toPay.action?oid=${oid }&shopFlag=${storeId }">前往支付</a>
						</s:else>
					</s:if>
					<s:if test="payStatus==2">
						<a
							href="/web/userOrder!viewOrderInfo.action?oid=${oid }">详
							情</a>
					</s:if>
					<s:if test="payStatus==1">
						<s:if test="status==0">
							<a href="javascript:void(0);"
								onclick="showRemind('${oid }','${storeId }')">提醒发货</a>
						</s:if>
						<s:elseif test="status==1">
							<a href="javascript:void(0);"
								onclick="confirmShouhuo('${code }');">确认收货</a>
						</s:elseif>
						<s:elseif test="status==2">
							<a
								href="/web/userOrder!toOrderComment.action?oid=${oid }&shopFlag=${storeId }">评
								价</a>
						</s:elseif>
						<s:elseif test="status==4">
							<a
								href="/web/userOrder!viewOrderInfo.action?oid=${oid }">详
								情</a>
						</s:elseif>
						<s:elseif test="status==3">
							<a
								href="/web/userOrder!viewOrderInfo.action?oid=${oid }">详
								情</a>
						</s:elseif>
					</s:if>
				</div>
				<div class="clear"></div>
			</div>
		</div>
	</div>
</s:iterator>