<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<form id="pagerForm" method="post" action="lvOrderMacAction!list.action?json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
	
</form>

<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="lvOrderMacAction!list.action?json.navTabId=${json.navTabId}" method="post" rel="pagerForm">
		<div class="searchBar">
			<table class="searchContent" layoutH="650">
				<tr>
						</tr><tr>
						<td><label>订单号</label></td><td><input name="lvOrderMac.orderId" type="text" size="20" value="${lvOrderMac.orderId}"/></td>
						<td><label>mac</label></td><td><input name="lvOrderMac.mac" type="text" size="20" value="${lvOrderMac.mac}"/></td>
						<td><label>邮箱</label></td><td><input name="lvOrderMac.userEmail" type="text" size="20" value="${lvOrderMac.userEmail}"/></td>
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
				<li><a class="icon" href="lvOrderMacAction!exportOrderMac.action?json.navTabId=${json.navTabId}"  target="dwzExport" targettype="dialog" title="实要导出这些记录吗?"  rel="ids"><span>导出Excel</span></a></li>
				<li class="line">line</li>
				<li><a class="delete" href="lvOrderMacAction!delList.action?json.navTabId=${json.navTabId}" target="selectedTodo" title="确实要删除这些记录吗?"><span>批量删除</span></a></li>
			</ul>
		</div>
		<table class="table" width="100%" layoutH="170">
			<thead>
				<tr>
					        <th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
						    <!-- 生成表单 -->
							<th width="4%">id</th>
							<th width="15%">订单号</th>
							<th width="15%">邮箱</th>
							<th width="15%">mac</th>
							<th width="15%">兑换来源</th>
							<th width="15%">创建时间</th>
					        <th width="20%">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="page.list" status="stat" id="item">
				<tr>
					<td><input name="ids" value="${item.id}" type="checkbox"></td>
					<!-- 生成表单 -->
								<td>${item.id }</td>
								<td>${item.orderId }</td>
								<td>${item.userEmail }</td>
								<td>${item.mac }</td>
								<td>${item.sourceUrl }</td>
								<td><s:date name="createTime" format="yyyy-MM-dd HH:mm"/></td>
					<td>
						<a class="btnView" href="lvOrderMacAction!view.action?lvOrderMac.id=${item.id}" target="dialog" navTabId="lvOrderMac" rel="lvOrderMac" title="查看" width="850" height="500" mask="true">查看</a>
						<a class="btnDel" href="lvOrderMacAction!del.action?lvOrderMac.id=${item.id}&json.navTabId=${json.navTabId}" target="ajaxTodo" title="删除">删除</a>
						<a href="lvOrderAction!view.action?lvOrder.oid=${item.orderId}&json.navTabId=${json.navTabId }" target="dialog" navTabId="lvAccount" rel="lvAccount" title="查看"  width="900" height="700" mask="true">订单详情</a>
					</td>
				</tr>
				</s:iterator>
				
			</tbody>
		</table>
		<%@ include file="../common/panelBar.jsp"%>
	</div>
</div>