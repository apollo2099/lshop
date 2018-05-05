<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<form id="pagerForm" method="post" action="lvEmailTplMngAction!list.action?json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="page.orderField" value="${orderField}" />
	<input type="hidden" name="page.orderDirection" value="${orderDirection}" />	
</form>

<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="lvEmailTplMngAction!list.action?json.navTabId=${json.navTabId}" method="post" rel="pagerForm">
		<div class="searchBar">
			<table class="searchContent" >
				<tr>
						</tr><tr>
						<td><label>key</label></td><td><input name="lvEmailTpl.tplKey" type="text" size="20" value="${lvEmailTpl.tplKey}"/></td>
						<td><label>所属关系</label></td><td>
						<select name="lvEmailTpl.storeId">
						<option value="">==请选择==</option>
						<c:foreach items="${storeList}" var="store">
						<option value="${store.storeFlag }" <c:if test="${lvEmailTpl.storeId==store.storeFlag }">selected="selected"</c:if>>${store.name }</option>
						</c:foreach>
						</select>
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
				<li><a class="add" href="lvEmailTplMngAction!befSave.action?json.navTabId=${json.navTabId}" target="dialog" navTabId="lvEmailTpl" rel="lvEmailTpl" width="1020" height="650" mask="true"><span>添加</span></a></li>
				<li class="line">line</li>
			</ul>
		</div>
		<table class="table" width="100%" layoutH="170">
			<thead>
				<tr>
					<th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
						<!-- 生成表单 -->
						    <th width="10%">所属关系</th>
							<th width="10%">模板名称</th>
							<th width="34%" orderField="tplKey">key</th>
							<th width="35%" orderField="en">标题</th>
							<th width="10%">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="#request.page.list" status="stat" id="item">
				<tr>
					<td><input name="ids" value="${item.id}" type="checkbox"></td>
					<!-- 生成表单 -->
					<td>
					    <c:foreach items="${storeList}" var="store">
						<c:if test="${item.storeId==store.storeFlag }">${store.name }</c:if>
						</c:foreach>
					</td>
					<td>${item.tplDescribe }</td>
					<td>${item.tplKey }</td>
					<td>${item.enTitle }</td>
					<td>
						<a class="btnDel" href="lvEmailTplMngAction!del.action?lvEmailTpl.id=${item.id}" target="ajaxTodo" title="删除">删除</a>
						<a class="btnEdit" href="lvEmailTplMngAction!befEdit.action?lvEmailTpl.id=${item.id}&json.navTabId=${json.navTabId}" target="dialog" navTabId="lvEmailTpl" rel="lvEmailTpl" title="编辑" width="1020" height="650" mask="true">编辑</a>
						<a class="btnView" href="lvEmailTplMngAction!view.action?lvEmailTpl.id=${item.id}" target="dialog" navTabId="lvEmailTpl" rel="lvEmailTpl" title="查看" width="1020" height="650">查看</a>
					</td>
				</tr>
				</s:iterator>
				
			</tbody>
		</table>
		<%@ include file="../common/panelBar.jsp"%>
	</div>
</div>