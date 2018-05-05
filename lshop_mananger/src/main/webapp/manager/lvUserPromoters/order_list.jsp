<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="../common/pageBreak.jsp">
<jsp:param value="settledLogAction!getOrderList.action" name="pagerFormAction"/>
</jsp:include>
<div class="page">
	<div class="pageHeader">
		<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="lvUserPromotersMngAction!getOrderList.action" method="post">
			<input type="hidden" name="json.navTabId" value="${json.navTabId}" />
			<input type="hidden" name="uid" value="${param.uid}"/>
			<input type="hidden" name="aids" value="${param.aids}"/>
			<div class="searchBar">
				<ul class="searchContent">
				   <li>
						<label>订单号：</label>
						<input name="oid" type="text" id="oid" value="${oid}" width="20"/>
				   </li>
				   <li>
						<label>优惠码：</label>
						<input name="ccode" type="text" id="ccode" value="${ccode}" width="20"/>
				   </li>
				 </ul>
				 <div class="subBar">
				         <ul>
							<li>
								<div class="buttonActive">
									<div class="buttonContent"><button type="submit">检索</button></div>
								</div>
							</li>
						</ul>
				</div>
			</div>
		</form>
	</div>
	<form action="#" method=post name="selform" id="selform">
		<div class="pageContent">
			<div class="panelBar">
				<ul class="toolBar">
				  <li><a class="edit" href="lvUserPromotersMngAction!exportOrders.action?json.navTabId=${json.navTabId}" target="dwzExportString" title="导出excel?" rel="ids"><span>导出EXCEL</span></a></li>  
				</ul>
			</div>
			<table class="table" layouth="138" width="100%">
				<thead>
					<tr>
					    <th width="width:9%;"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
						<th style="width:13%;">订单号</th>
						<th style="width:13%;">下单时间</th>
						<th style="width:13%;">支付成功时间</th>
						<th style="width:13%;">套餐数目</th>	
						<th style="width:13%;">购买产品数量</th>	
						<th style="width:13%;">邮寄费用</th>	
						<th style="width:13%;">订单总金额</th>	
						<th style="width:13%;"> 优惠码</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="page.list" status="stat" id="item">
					<tr>
						<td><input name="ids" value="${item.oid}" type="checkbox" class='np'></td>
						<td>${item.oid}</td>
						<td><s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"></s:date></td>
						<td><s:date name="overtime" format="yyyy-MM-dd HH:mm:ss"></s:date></td>					
						<td><c:if test="${item.isPackage==1}">${item.pnum}</c:if></td>
						<td><c:if test="${item.isPackage!=1}">${item.pnum}</c:if><c:if test="${item.isPackage==1}">${item.MealPnum}</c:if></td>
						<td>${item.postprice }</td>
						<td>${item.totalPrice }</td>
						<td>${item.couponCode }</td>
					</tr>	
					</s:iterator>
				</tbody>
			</table>
			<%@ include file="../common/panelBar.jsp"%>
		</div>
	</form>
</div>
