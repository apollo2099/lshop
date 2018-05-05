<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<form id="pagerForm" method="post" action="lvActivityLogsAction!list.action?json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
	
</form>

<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="lvActivityLogsAction!list.action?json.navTabId=${json.navTabId}" method="post" rel="pagerForm">
		<div class="searchBar">
			<table class="searchContent" layoutH="650">
				<tr>
						</tr><tr>
						<td><label>id</label></td><td><input name="lvActivityLogs.id" type="text" size="20" value="${lvActivityLogs.id}"/></td>
						<td><label>活动code</label></td><td><input name="lvActivityLogs.activityCode" type="text" size="20" value="${lvActivityLogs.activityCode}"/></td>
						<td><label>活动参与订单号</label></td><td><input name="lvActivityLogs.orderId" type="text" size="20" value="${lvActivityLogs.orderId}"/></td>
						</tr><tr>
						<td><label>活动物品信息</label></td><td><input name="lvActivityLogs.productInfo" type="text" size="20" value="${lvActivityLogs.productInfo}"/></td>
						<td><label>活动参与人</label></td><td><input name="lvActivityLogs.actors" type="text" size="20" value="${lvActivityLogs.actors}"/></td>
						<td><label>活动参与人ip</label></td><td><input name="lvActivityLogs.actorsIp" type="text" size="20" value="${lvActivityLogs.actorsIp}"/></td>
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
				<li><a class="add" href="lvActivityLogsAction!befSave.action?json.navTabId=${json.navTabId}" target="dialog" navTabId="lvActivityLogs" rel="lvActivityLogs" width="850" height="500" mask="true"><span>添加</span></a></li>
				<li class="line">line</li>
				<li><a class="delete" href="lvActivityLogsAction!delList.action?json.navTabId=${json.navTabId}" target="selectedTodo" title="确实要删除这些记录吗?"><span>批量删除</span></a></li>
			</ul>
		</div>
		<table class="table" width="100%" layoutH="170">
			<thead>
				<tr>
					<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
						<!-- 生成表单 -->
							<th orderField="id" class="${orderField eq 'id' ? orderDirection : ''}">id</th>
							<th orderField="activityCode" class="${orderField eq 'activityCode' ? orderDirection : ''}">活动code</th>
							<th orderField="orderId" class="${orderField eq 'orderId' ? orderDirection : ''}">活动参与订单号</th>
							<th orderField="productInfo" class="${orderField eq 'productInfo' ? orderDirection : ''}">活动物品信息</th>
							<th orderField="actors" class="${orderField eq 'actors' ? orderDirection : ''}">活动参与人</th>
							<th orderField="actorsIp" class="${orderField eq 'actorsIp' ? orderDirection : ''}">活动参与人ip</th>
							<th orderField="actorsTime" class="${orderField eq 'actorsTime' ? orderDirection : ''}">活动参与时间</th>
					<th width="90">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="page.list" status="stat" id="item">
					<td><input name="ids" value="${item.id}" type="checkbox"></td>
					<!-- 生成表单 -->
								<td>${item.id }</td>
								<td>${item.activityCode }</td>
								<td>${item.orderId }</td>
								<td>${item.productInfo }</td>
								<td>${item.actors }</td>
								<td>${item.actorsIp }</td>
								<td><s:date name="actorsTime" format="yyyy-MM-dd HH:mm"/></td>
					<td>
						<a class="btnDel" href="lvActivityLogsAction!del.action?lvActivityLogs.id=${item.id}&json.navTabId=${json.navTabId}" target="ajaxTodo" title="删除">删除</a>
						<a class="btnEdit" href="lvActivityLogsAction!befEdit.action?lvActivityLogs.id=${item.id}&json.navTabId=${json.navTabId}" target="dialog" navTabId="lvActivityLogs" rel="lvActivityLogs" title="编辑" width="850" height="500" mask="true">编辑</a>
						<a class="btnView" href="lvActivityLogsAction!view.action?lvActivityLogs.id=${item.id}" target="dialog" navTabId="lvActivityLogs" rel="lvActivityLogs" title="查看" width="850" height="500">查看</a>
					</td>
				</tr>
				</s:iterator>
				
			</tbody>
		</table>
		<%@ include file="../common/panelBar.jsp"%>
	</div>
</div>