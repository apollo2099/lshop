<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<form id="pagerForm" method="post" action="lvSwitchOrderLogsAction!list.action?json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
	
</form>

<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="lvSwitchOrderLogsAction!list.action?json.navTabId=${json.navTabId}" method="post" rel="pagerForm">
		<div class="searchBar">
			<table class="searchContent" layoutH="550">
				<tr>
						</tr><tr>
						<td><label>订单编号</label></td><td><input name="lvSwitchOrderLogs.orderId" type="text" size="20" value="${lvSwitchOrderLogs.orderId}"/></td>
						<td><label>用户Email</label></td><td><input name="userEmail" type="text" size="20" value="${userEmail}"/></td>
						<td><label>收货人姓名</label></td><td><input name="relName" type="text" size="20" value="${relName}"/></td>
						</tr><tr>
						<td><label>原有店铺</label></td><td><input name="storeName" type="text" size="20" value="${storeName}"/></td>
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
				<li><a class="icon" href="lvSwitchOrderLogsAction!exportLogs.action?json.navTabId=${json.navTabId}"  target="dwzExport" targettype="dialog" title="实要导出这些记录吗?"  rel="ids"><span>导出Excel</span></a></li>
			    <li class="line">line</li>
			</ul>
		</div>
		<table class="table" width="100%" layoutH="170">
			<thead>
				<tr>
					<th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
						<!-- 生成表单 -->
							<th width="4%">ID</th>
							<th width="10%">原有店铺</th>
							<th width="15%">订单编号</th>
							<th width="10%">收货人姓名</th>
							<th width="10%">订单总金额</th>
							<th width="10%">转单目标店铺</th>
							<th width="10%">操作时间</th>
							<th width="10%">操作人名称</th>
                            <th width="20%">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="page.list" status="stat" id="item">
				<tr>
					<td><input name="ids" value="${item.id}" type="checkbox"></td>
					<!-- 生成表单 -->
								<td>${item.id }</td>
								<td>
								<c:foreach items="${storeList}" var="store">
								  <c:if test="${store.code eq item.oldStoreCode}">
								  <a style="color: blue" href="lvStoreMngAction!shopView.action?lvStore.storeFlag=${store.storeFlag}&json.navTabId=${json.navTabId }" target="dialog" title="查看店铺信息"  width="900" height="730" mask="true">
								  ${store.name}</a>
								  </c:if>
								</c:foreach>
								</td>
								<td>${item.orderId }</td>
								<td>${item.relName }</td>
								<td>${item.totalPrice }</td>
								<td>
								<c:foreach items="${storeList}" var="store">
								  <c:if test="${store.code eq item.targetStoreCode}"><a style="color: blue" href="lvStoreMngAction!shopView.action?lvStore.storeFlag=${store.storeFlag}&json.navTabId=${json.navTabId }" target="dialog" title="查看店铺信息"  width="900" height="730" mask="true">
								  ${store.name}</a></c:if>
								</c:foreach>
								</td>
								<td><s:date name="createTime" format="yyyy-MM-dd HH:mm"/></td>
								<td>${item.createUserName }</td>
					<td>
					<a  href="lvOrderAction!view.action?lvOrder.oid=${item.orderId }&json.navTabId=${json.navTabId }" target="dialog" navTabId="lvAccount" rel="lvAccount" title="查看"  width="900" height="730" mask="true">订单详情</a>
					</td>
				</tr>
				</s:iterator>
				
			</tbody>
		</table>
		<%@ include file="../common/panelBar.jsp"%>
	</div>
</div>