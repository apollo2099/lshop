<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld"%>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<jsp:include page="../common/pageBreak.jsp">
<jsp:param value="settledLogAction!list.action" name="pagerFormAction"/>
</jsp:include>
<div class="page">
	<div class="pageHeader">
		<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="settledLogAction!list.action" method="post">
			<input type="hidden" name="json.navTabId" value="${json.navTabId}" />
			<input type="hidden" name="uid" value="${param.uid}"/>
			<div class="searchBar">
				<ul class="searchContent">
				   <li>
						<label>姓名：</label>
						<input name="name" type="text" id="name" value="${name}" width="20"/>
				   </li>
				   <li>
						<label>邮箱：</label>
						<input name="email" type="text" id="email" value="${email}" width="20"/>
				   </li>
				   <li>
						<label>推广码：</label>
						<input name="couponCode" type="text" id="couponCode" value="${couponCode}" width="20"/>
				   </li>
				    <li>
						<label>结算时间：</label>
						<input name="overtime" type="text" id="overtime" value="${overtime}" width="20" class="date"/>
				   </li>
				   <li>
						<label>收款账户：</label>
						<input name="accountNumber" type="text" id="accountNumber" value="${accountNumber}" width="20"/>
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
				</ul>
			</div>
			<table class="table" layouth="138" width="100%">
				<thead>
					<tr>
					    <th width="width:5%;"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
						<th style="width:10%;">结算处理时间</th>
						<th style="width:5%;">真实姓名</th>
						<th style="width:10%;">邮箱地址</th>
						<th style="width:10%;">联系电话</th>	
						<th style="width:10%;">推广码</th>	
						<th style="width:5%;">收款账户类型</th>	
						<th style="width:10%;">收款账户</th>	
						<th style="width:5%;">结算订单数</th>	
						<th style="width:5%;">结算推广台数</th>	
						<th style="width:5%;">结算金额</th>	
				
						<th style="width:20%;">操作</th>
					</tr>
				</thead>
				<tbody>
					<c:foreach items="${objList}" var="obj">
					<tr>
						<td><input name="ids" value="" type="checkbox" class='np'></td>
						<td>${obj[0]}</td>
						<td>${obj[1]}</td>
						<td>${obj[2]}</td>					
						<td>${obj[3]}</td>
						<td>${obj[4]}</td>
						<td>
							<c:if test="${obj[5]==1}">PayPal</c:if>
			        		<c:if test="${obj[5]==2}">支付宝</c:if>
						</td>
						<td>${obj[6]}</td>					
						<td>${obj[7]}</td>
						<td>${obj[8]}</td>
						<td>${obj[9]}</td>
						<td>
						 <a class="btnLook" href="settledLogAction!getUserDetail?uid=${obj[11]}&json.navTabId=${json.navTabId}" target="dialog" rel="UserDetail" title="用户资料" width="850" height="500" mask="true">用户资料</a>
						 <a title="订单详细" target="navTab" href="settledLogAction!getOrderList?couponCode=${obj[4]}&aids=${obj[10]}&json.navTabId=${json.navTabId}" class="btnLook"><span>订单详细</span></a>
						</td>
					</tr>	
					</c:foreach>
				</tbody>
			</table>
			<%@ include file="../common/panelBar.jsp"%>
		</div>
	</form>
</div>
