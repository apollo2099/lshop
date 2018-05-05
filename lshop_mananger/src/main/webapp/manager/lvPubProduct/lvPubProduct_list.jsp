<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<form id="pagerForm" method="post" action="lvPubProductAction!list.action?json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
	
</form>

<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="lvPubProductAction!list.action?json.navTabId=${json.navTabId}" method="post" rel="pagerForm">
		<div class="searchBar">
			<table class="searchContent" layoutH="650">
				<tr>
						</tr><tr>
						<td><label>商品名称</label></td><td><input name="lvPubProduct.productName" type="text" size="20" value="${lvPubProduct.productName}"/></td>
						<td><label>商品型号</label></td><td><input name="lvPubProduct.productModel" type="text" size="20" value="${lvPubProduct.productModel}"/></td>
						<td><label>SAS对接code</label></td><td><input name="lvPubProduct.pcode" type="text" size="20" value="${lvPubProduct.pcode}"/></td>
						<td><label>商品code</label></td><td><input name="lvPubProduct.code" type="text" size="20" value="${lvPubProduct.code}"/></td>
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
				<li><a class="add" href="lvPubProductAction!befSave.action?json.navTabId=${json.navTabId}" target="dialog" navTabId="lvPubProduct" rel="lvPubProduct" width="500" height="300" mask="true"><span>添加</span></a></li>
				<li class="line">line</li>
				<li><a class="delete" href="lvPubProductAction!delList.action?json.navTabId=${json.navTabId}" target="selectedTodo" title="确实要删除这些记录吗?"><span>批量删除</span></a></li>
			</ul>
		</div>
		<table class="table" width="100%" layoutH="165">
			<thead>
				<tr>
					<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
						<!-- 生成表单 -->
							<th width="5%" orderField="id" class="${orderField eq 'id' ? orderDirection : ''}">ID</th>
							<th width="15%">商品名称</th>
							<th width="10%">商品型号</th>
							<th width="20%">SAS对接code</th>
							<th width="20%">商品code</th>
							<th width="10%">创建时间</th>
							<th width="10%">修改时间</th>
					        <th width="20%">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="page.list" status="stat" id="item">
				<tr>
					<td><input name="ids" value="${item.id}" type="checkbox"></td>
					<!-- 生成表单 -->
								<td>${item.id }</td>
								<td>${item.productName }</td>
								<td>${item.productModel }</td>
								<td>${item.pcode }</td>
								<td>${item.code }</td>
								<td><s:date name="createTime" format="yyyy-MM-dd HH:mm"/></td>
								<td><s:date name="modifyTime" format="yyyy-MM-dd HH:mm"/></td>
					<td>
						<a class="btnView" href="lvPubProductAction!view.action?lvPubProduct.id=${item.id}" target="dialog" navTabId="lvPubProduct" rel="lvPubProduct" title="查看" width="600" height="400" mask="true">查看</a>
						<a class="btnEdit" href="lvPubProductAction!befEdit.action?lvPubProduct.id=${item.id}&json.navTabId=${json.navTabId}" target="dialog" navTabId="lvPubProduct" rel="lvPubProduct" title="编辑" width="500" height="300" mask="true">编辑</a>
						<a class="btnDel" href="lvPubProductAction!del.action?lvPubProduct.id=${item.id}&json.navTabId=${json.navTabId}" target="ajaxTodo" title="删除">删除</a>
					</td>
				</tr>
				</s:iterator>
				
			</tbody>
		</table>
		<%@ include file="../common/panelBar.jsp"%>
	</div>
</div>