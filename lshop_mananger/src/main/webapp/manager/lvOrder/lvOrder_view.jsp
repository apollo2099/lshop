<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="gv" uri="/WEB-INF/tld/gv.tld"%>


<div class="page">
	<div class="pageContent">
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
				<s:property value="@com.lshop.common.util.Constants@PAY_METHODS.get(lvOrder.paymethod)"/>
					</dd>				  
				</dl>
				<dl>
					<dt>支付成功时间：</dt>
					<dd>
						<s:date name="lvOrder.overtime" format="yyyy-MM-dd HH:mm"/>
					</dd>				  
				</dl>
				<dl>
					<dt>所属商家：</dt>
					<dd>
						<c:foreach items="${storeList}" var="store">
							 <c:if test="${lvOrder.storeId==store.storeFlag }">${store.name }</c:if>
						</c:foreach>
					</dd>				  
				</dl>
				<dl>
					<dt>第三方支付单号：</dt>
					<dd>
						${lvOrder.thirdPartyOrder}
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
				<dl>
					<dt>发货备注：</dt>
					<dd>
						<textarea rows="3" cols="30" name="lvOrder.sendRemark" readonly="readonly">${lvOrder.sendRemark }</textarea>
					</dd>				  
				</dl>
				<dl style="height: 50px;">
					<dt>退货备注：</dt>
					<dd>
						<textarea rows="3" cols="30" name="lvOrder.breakRemark" readonly="readonly">${lvOrder.breakRemark }</textarea>
					</dd>				  
				</dl>
				<dl class="nowrap">
					<dt>收货地址：</dt>
					<dd>
					${lvOrderAddress.adress}&nbsp;${lvOrderAddress.cityName}&nbsp;${lvOrderAddress.provinceName}&nbsp;${lvOrderAddress.contryName}
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
					<dt>邮编：</dt>
					<dd>
						${lvOrderAddress.postCode}
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
				<dl>
					<dt>客服审核状态：</dt>
					<dd>
				 <c:if test="${lvOrder.isServiceAudit==0}">待审核</c:if>
				 <c:if test="${lvOrder.isServiceAudit==1}">审核通过</c:if>
				 <c:if test="${lvOrder.isServiceAudit==-1}">审核未通过</c:if>
					</dd>				  
				</dl>
				<dl>
					<dt>客服审核备注：</dt>
					<dd>
						${lvOrder.serviceAuditContent}
					</dd>				  
				</dl>
					<dl>
					<dt>财务审核状态：</dt>
					<dd>
				 <c:if test="${lvOrder.isFinanceAudit==0}">待审核</c:if>
				 <c:if test="${lvOrder.isFinanceAudit==1}">审核通过</c:if>
				 <c:if test="${lvOrder.isFinanceAudit==-1}">审核未通过</c:if>
					</dd>				  
				</dl>
				<dl>
					<dt>财务审核备注：</dt>
					<dd>
						${lvOrder.financeAuditContent}
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
			  <div  > 
		<table class="table" width="100%" >
		<thead>
			<tr>
				<th width="5%" orderField="id" class="${orderDirection}">ID</th>
				<th width="50%">产品名称</th>
				<th width="20%">产品单价</th>
				<th width="25%">产品数量</th>
				
				<!-- 
				<th >优惠券</th>
				<th >优惠金额</th>
				 -->
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
		</div>
		<div class="formBar">
			<ul>
				<li><div class="button"><div class="buttonContent"><button class="close" type="button">关闭</button></div></div></li>
			</ul>
		</div>
	</div>
</div>

