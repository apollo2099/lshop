<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="gv" uri="/WEB-INF/tld/gv.tld"%>

<div class="page">
	<div class="pageContent">
	<form method="post" action="lvOrderAction!audit.action?json.navTabId=${json.navTabId }"
	 class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
	 <input type="hidden" name="lvOrder.oid" value="${lvOrder.oid }">
	 <input type="hidden" name="lvOrder.storeId" value="${lvOrder.storeId }">
	 <input type="hidden" name="versionTime" value="${lvOrder.modifyTime }">
	    <div class="pageFormContent" layoutH="56">
		<div class="viewInfo" >
		<!--取值 -->
		        <dl>
					<dt>用户Email：</dt>
					<dd>
						${lvOrder.userEmail}
					</dd>				  
				</dl>
				<dl>
					<dt>收货人姓名：</dt>
					<dd>
						${lvOrderAddress.relName}
					</dd>				  
				</dl>
				   <div class="divider"></div>
	
				<dl>
					<dt>订单编号：</dt>
					<dd>
						${lvOrder.oid}
					</dd>				  
				</dl>
				<dl>
					<dt>订单金额：</dt>
					<dd>
						${lvOrder.currency } ${lvOrder.totalPrice}
					</dd>				  
				</dl>
				<dl>
					<dt>订单状态：</dt>
					<dd>
				<c:if test="${lvOrder.status==0}">待发货</c:if>
				<c:if test="${lvOrder.status==1}">已发货，未确认收货</c:if>
				<c:if test="${lvOrder.status==2}">已收货 ,待评价 </c:if>
				<c:if test="${lvOrder.status==3}">已退货</c:if>
				<c:if test="${lvOrder.status==4}">已完成</c:if>
					</dd>				  
				</dl>
				<dl>
					<dt>运费：</dt>
					<dd>
						${lvOrder.currency } ${lvOrder.postprice }
					</dd>				  
				</dl>
				
				<dl>
					<dt>订单类型：</dt>
					<dd>
				<c:if test="${lvOrder.flag==0}">用户下单</c:if>
				<c:if test="${lvOrder.flag==1}">商家下单</c:if>
					</dd>				  
				</dl>
				<dl>
					<dt>下单时间：</dt>
					<dd>
						<s:date name="lvOrder.createTime" format="yyyy-MM-dd HH:mm"/>
					</dd>				  
				</dl>
				<dl>
					<dt>支付方式：</dt>
					<dd>
					<!--  
				<c:if test="${lvOrder.paymethod==1}">Paypal支付</c:if>
				<c:if test="${lvOrder.paymethod==2}">国际信用卡</c:if>
				<c:if test="${lvOrder.paymethod==3}">支付宝</c:if>
				<c:if test="${lvOrder.paymethod==4}">西联汇款</c:if>
				<c:if test="${lvOrder.paymethod==5}">优仕支付</c:if>
				<c:if test="${lvOrder.paymethod==6}">快钱支付</c:if>
				-->
				<s:property value="@com.lshop.common.util.Constants@PAY_METHODS.get(lvOrder.paymethod)"/>
					</dd>				  
				</dl>
				<dl>
					<dt>支付成功时间：</dt>
					<dd>
						<s:date name="lvOrder.overtime" format="yyyy-MM-dd HH:mm"/>
					</dd>				  
				</dl>
				 <c:if test="${lvOrder.flag==1 }">
				<dl>
					<dt>第三方订单号：</dt>
					<dd>
						${lvOrder.thirdOrderNum}
					</dd>				  
				</dl>
				
				<dl>
					<dt>第三方支付单号：</dt>
					<dd>
						${lvOrder.thirdPartyOrder}
					</dd>				  
				</dl>
				
				<dl>
					<dt>第三方订单来源：</dt>
					<dd>
					    <c:if test="${not empty  lvOrder.thirdOrderSource}">
						<gv:dictionary type="text" code="THIRD_ORDER_SOURCE"  name="lvOrder.thirdOrderSource" value="${lvOrder.thirdOrderSource }"/>
						</c:if>
					</dd>				  
				</dl>
				</c:if>
				
				
			  
				
				<c:if test="${not empty lvOrderCoupon.couponCode }">
				<div class="divider"></div>
				<dl>
					<dt>优惠券信息：</dt>
					<dd>
						${lvOrderCoupon.couponName } 
					</dd>				  
				</dl>
								<dl>
					<dt>优惠券金额：</dt>
					<dd>
						${lvOrderCoupon.couponPrice }
					</dd>				  
				</dl>
								<dl>
					<dt>优惠码：</dt>
					<dd>
						${lvOrderCoupon.couponCode }
					</dd>				  
				</dl>
				<div class="divider"></div>
				</c:if>
				<dl class="nowrap">
					<dt>订单备注：</dt>
					<dd>
						<textarea rows="3" cols="85" name="lvOrder.orderRemark" readonly="readonly">${lvOrder.orderRemark }</textarea>
					</dd>				  
				</dl>
				<dl class="nowrap">
					<dt>收货地址：</dt>
					<dd>
					${lvOrderAddress.adress}&nbsp;${lvOrderAddress.cityName}&nbsp;${lvOrderAddress.provinceName}&nbsp;${lvOrderAddress.contryName}
					</dd>				  
				</dl>
				<dl>
					<dt>邮编：</dt>
					<dd>
						${lvOrderAddress.postCode}
					</dd>				  
				</dl>
				<dl>
				    <dt>手机：</dt>
					<dd>
						${lvOrderAddress.mobile}
					</dd>				  
				</dl>
				<dl>
					<dt>联系电话：</dt>
					<dd>
						${lvOrderAddress.tel}
					</dd>				  
				</dl>
								
				<dl>
					<dt>物流公司：</dt>
					<dd>
						${lvOrder.expressCompany}
					</dd>				  
				</dl>
				<dl>
					<dt>快递名称：</dt>
					<dd>
						${lvOrder.expressName}
					</dd>				  
				</dl>
				<dl>
					<dt>快递单号：</dt>
					<dd>
						${lvOrder.expressNum}
					</dd>				  
				</dl>
				<c:if test="${not empty lvOrderMac }">
				<dl>
					<dt>Mac：</dt>
					<dd>
						${lvOrderMac.mac}
					</dd>				  
				</dl>
				<dl>
					<dt>优惠金额：</dt>
					<dd>
						${lvOrder.currency } ${lvOrderMac.discountAmount}
					</dd>				  
				</dl>
				
				</c:if>
				
			    </div>	
			    

			    <div class="divider"></div>
			  <div > 
		<table class="table" width="100%" >
		<thead>
			<tr>
				<th width="5%" orderField="id" class="${orderDirection}">ID</th>
		        <th width="50%">产品名称</th>
				<th width="20%">产品单价</th>
				<th width="25%">产品数量</th>
		   </tr>
		</thead>
		<tbody>
		<c:foreach items="${orderDetailsList}" var="orderDetails">
			<tr>
				<td>${orderDetails.id }</td>
				<td>
				<c:foreach items="${productList}" var="product">
				  <c:if test="${product.code == orderDetails.productCode}">${product.productName}</c:if>
				</c:foreach>
				</td>
				<td>${orderDetails.currency } ${orderDetails.oprice}</td>
				<td>${orderDetails.pnum }</td>
			</tr>
		</c:foreach>
		<c:foreach items="${orderGiftList }" var="gift">
		<tr>
		        <td>${gift.id }</td>
				<td>${gift.giftName }</td>
				<td></td>
				<td>${gift.giftNum }</td>
		</tr>
		</c:foreach>
		</tbody>
	</table>
	
	        </div>	
			      <div class="divider"></div>
			    <div>
			    <sec:authorize url="updateServiceAudit">
			    <div > 
					<dl>
						<dt>客服审核状态：</dt>
						<dd>
							<s:select list="#{'0':'未审核','1':'审核通过','-1':'审核不通过'}"
								name="lvOrder.isServiceAudit" cssStyle="width:180px;"></s:select>
						</dd>
					</dl>
					<dl>
						<dt>客服审核说明：</dt>
						<dd>
							<textarea rows="2" cols="40" name="lvOrder.serviceAuditContent" >${lvOrder.serviceAuditContent }</textarea>
						</dd>
					</dl>
					</div>
					</sec:authorize>
					<sec:authorize url="updateFinanceAudit">
					<div>
					<dl>
						<dt>财务审核状态：</dt>
						<dd>
							<s:select list="#{'0':'未审核','1':'审核通过','-1':'审核不通过'}"
								name="lvOrder.isFinanceAudit" cssStyle="width:180px;"></s:select>
						</dd>
					</dl>
					<dl>
						<dt>财务审核说明：</dt>
						<dd>
							<textarea rows="2" cols="40" name="lvOrder.financeAuditContent" >${lvOrder.financeAuditContent }</textarea>
						</dd>
					</dl>
					</div>
					</sec:authorize>

		</div>
		</div>
           <div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">审核</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
		</form>
	</div>
</div>

