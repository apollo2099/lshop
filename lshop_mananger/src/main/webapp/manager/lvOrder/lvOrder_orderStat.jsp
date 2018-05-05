<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<jsp:include page="../common/pagerForm.jsp">

<jsp:param value="lvOrderAction!orderStat.action" name="pagerFormAction"/>
</jsp:include>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="lvOrderAction!orderStat.action?json.navTabId=${json.navTabId }" method="post">
	<div class="searchBar">
			<table class="searchContent" >
				<tr>
						</tr><tr>
						<!-- 
						<c:if test="${fn:length(page.list)>1&&!empty lvStore}">
						</c:if>
						 -->
						
						<td>
						<label>商家名称</label><select name="lvStore.storeFlag">
							<option value="">==请选择==</option>
							<c:foreach items="${storeList}" var="store">
							<option value="${store.storeFlag }" <c:if test="${lvStore.storeFlag==store.storeFlag }">selected="selected"</c:if>>${store.name }</option>
							</c:foreach>
							</select>
						 </td>
						
						<td><label>下单时间</label><input type="text" name="lvOrder.startTime" value="${lvOrder.startTime}" class="date"/>
						-<input type="text" class="date" name="lvOrder.endTime" value="${lvOrder.endTime}"></td>
				</tr><tr>
				</tr>
			</table>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
		<sec:authorize url="userEdit">
			<li><a class="icon" href="lvOrderAction!exportOrderStat.action?lvOrder.startTime=${lvOrder.startTime }&lvOrder.endTime=${lvOrder.endTime }&json.navTabId=${json.navTabId}"  target="dwzExportString" targettype="dialog" title="实要导出这些记录吗?"  rel="ids"><span>导出Excel</span></a></li>
			<li class="line">line</li>
		</sec:authorize>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="145">
		<thead>
			<tr>
			    <th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="10%">商家名称</th>
				<th >总下单数量</th>
				<th >已付款订单数</th>
				<th >未付款订单数</th>
				<th >销售额(USD)</th>
				<th >已完成订单数</th>
				<th >已删除订单数</th>
				<th >已退货订单数</th>
				<th >操作</th>
			</tr>
		</thead>
		<tbody>
			
						<!-- <tr>
			    <td><input name="ids" value="1" type="checkbox"></td>
				<td>${lvStore.name }</td>
				<td>${requestScope.countAll}</td>
				<td>${requestScope.countPay}</td>
				<td>${requestScope.countUnPay}</td>
				<td>${requestScope.totalPrice}</td>
				<td>${requestScope.countFinish}</td>
				<td>${requestScope.countDelete}</td>
				<td>${requestScope.countBack}</td>
				<td>
				</td>
				</tr> -->
			<s:iterator value="page.list" status="stat" id="item">
			<tr>
		    <td><input name="ids" value="${item.storeFlag }" type="checkbox"></td>
				<td>${item.name }</td>
				<td>${item.countAll}</td>
				<td>${item.countPay}</td>
				<td>${item.countUnPay}</td>
				<td>${item.totalPrice}</td>
				<td>${item.countFinish}</td>
				<td>${item.countDelete}</td>
				<td>${item.countBack}</td>
				<td>
				</td>
			</tr>
			</s:iterator>
			
		</tbody>
	</table>
	<jsp:include page="../common/panelBar.jsp">
		<jsp:param value="navTab" name="targetType"/>
		<jsp:param value="" name="rel"/>
	</jsp:include>
</div>
