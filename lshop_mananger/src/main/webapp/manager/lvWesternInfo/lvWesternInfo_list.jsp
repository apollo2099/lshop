<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<form id="pagerForm" method="post" action="lvWesternInfoAction!list.action" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
	
</form>

<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="lvWesternInfoAction!list.action" method="post" rel="pagerForm">
		<div class="searchBar">		
			<table class="searchContent" >
				<tr>
						</tr><tr>
						<td><label>订单号</label>
						<input name="lvWesternInfo.oid" type="text" size="20" value="${lvWesternInfo.oid}"/>
						</td>
						<td><label>是否确认付款</label><s:select list="#{'':'','0':'未确认付款','1':'已确认付款'}" name="lvWesternInfo.status"></s:select></td>
				</tr><tr>
				</tr><tr>
						<td><label>汇款时间</label><input name="lvWesternInfo.transferTime" type="text" size="20" value="${lvWesternInfo.transferTime}"/></td>
						<td><label>MTCN</label><input name="lvWesternInfo.mtcn" type="text" size="20" value="${lvWesternInfo.mtcn}"/></td>
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
		<table class="table" width="100%" layoutH="148">
			<thead>
				<tr>
						<!-- 生成表单 -->
						    <th width="10%">所属关系</th>
						    <th width="15%">订单号</th>
							<th width="10%">汇款人</th>
							<th width="10%">汇款金额</th>
							<th width="10%">地址</th>
							<th width="10%">MTCN</th>
							<th width="10%">汇款时间</th>
							<th width="10%" class="${orderField eq 'status' ? orderDirection : ''}">付款状态</th>
					        <th width="15%">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="#request.page.list" status="stat" id="item">
				<tr>
				    <td>
					<c:foreach items="${storeList}" var="store">
					<c:if test="${store.storeFlag==item.storeId}">${store.name}</c:if>
					</c:foreach>
				    </td>
				    <td>${item.oid }</td>
					<td>${item.remitter }</td>
					<td>USD${item.remittance }</td>
					<td>${item.contryName } ${item.adress}</td>
					<td>${item.mtcn }</td>
					<td>${item.transferTime}</td>
					<td><s:if test="#item.status==1">已确认付款</s:if>
					<s:else>未确认付款</s:else></td>
					<td>
					<s:if test="#item.status==0">
						<a style="color:blue;"  href="lvWesternInfoAction!toPay.action?lvWesternInfo.id=${item.id}&lvWesternInfo.oid=${item.oid}&json.navTabId=${json.navTabId}"  title="确认付款?" target="ajaxTodo" mask="true">确认付款</a>
					</s:if>
					&nbsp;&nbsp;&nbsp;
					<a style="color:blue;"  href="lvOrderAction!view.action?lvOrder.oid=${item.oid}" target="dialog" navTabId="lvOrder" rel="lvOrder" title="查看订单详情" width="900" height="730">查看订单详情</a>
					</td>
				</tr>
				</s:iterator>
			</tbody>
		</table>
		<%@ include file="../common/panelBar.jsp"%>
	</div>
</div>