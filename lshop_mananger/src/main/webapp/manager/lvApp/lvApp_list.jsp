<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="../common/pagerForm.jsp">
<jsp:param value="lvAppAction!list.action" name="pagerFormAction"/>
</jsp:include>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="lvAppAction!list.action" method="post">
    <div class="searchBar">
		    <table class="searchContent" >
				<tr>
						</tr><tr>
						<td><label>ID</label><input name="lvApp.id" type="text" size="20" value="${lvApp.id}"/></td>
						<td><label>应用名称</label><input name="lvApp.name" type="text" size="20" value="${lvApp.name}"/></td>
						<td><label>版本</label><input name="lvApp.version" type="text" size="20" value="${lvApp.version}"/></td>
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
		    <li><a class="add" href="lvAppAction!befAdd.action?json.navTabId=${json.navTabId }" target="dialog" mask="true" width="900" height="600"  title="添加"><span>添加</span></a></li>
			<li><a class="delete"  href="lvAppAction!delList.action?json.navTabId=${json.navTabId }" target="selectedTodo" title="确实要删除这些记录吗?该操作请谨慎!"  rel="ids"><span>批量删除</span></a></li>
		</sec:authorize>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="145">
		<thead>
			<tr>
				<th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="4%" orderField="id" class="${orderDirection}">ID</th>
				<th width="10%">所属关系</th>
				<th width="25%">应用名称</th>
				<th width="10%">版本</th>
				<th width="10%">适用机型</th>
				<th width="10%">适用版本</th>
				<th width="10%">评分</th>
				<th width="20%">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:foreach items="${page.list}" var="item">
			<tr>
				<td><input name="ids" value="${item.id}" type="checkbox"></td>
				<td>${item.id }</td>
				<td>
					<c:foreach items="${storeList}" var="store">
					<c:if test="${store.storeFlag==item.storeId}">${store.name}</c:if>
					</c:foreach>
				</td>
				<td>${item.name }</td>
				<td>${item.version }</td>
				<td>${item.applyModel}</td>
				<td>${item.applyVersion}</td>
				<td>${item.grade}</td>
				<td>
				<sec:authorize url="userEdit">
				    <a class="btnView" href="lvAppAction!view.action?lvApp.id=${item.id}" target="dialog" navTabId="lvAccount" rel="lvAccount" title="查看" width="1020" height="650" mask="true">查看</a>
					<a class="btnEdit" href="lvAppAction!befEdit.action?lvApp.id=${item.id}&lvApp.storeId=${item.storeId }&json.navTabId=${json.navTabId }"  title="编辑" target="dialog"  width="1020" height="650"  mask="true">编辑</a>
					<a class="btnDel"  href="lvAppAction!del.action?lvApp.id=${item.id}&json.navTabId=${json.navTabId }" title="确实要删除这些记录吗?该操作请谨慎!" target="ajaxTodo">删除</a>
				</sec:authorize>	
				</td>
			</tr>
		</c:foreach>
		</tbody>
	</table>
	
	<jsp:include page="../common/panelBar.jsp">
		<jsp:param value="navTab" name="targetType"/>
		<jsp:param value="" name="rel"/>
	</jsp:include>
</div>
