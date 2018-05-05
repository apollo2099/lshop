<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="../common/pagerForm.jsp">
<jsp:param value="lvProductLadderAction!list.action" name="pagerFormAction"/>
</jsp:include>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="lvProductLadderAction!list.action" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>产品名称：</label>
				<select name="lvProductLadder.productCode" style="width:200px;">
				 <option value="">==请选择==</option>
				 <c:foreach items="${productList}" var="product">
				    <option value="${product.code }" <c:if test="${ product.code==lvProductLadder.productCode}">selected="selected"</c:if>>${product.productName }</option>
				 </c:foreach>
				</select>
			</li>
		</ul>
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
		    <li><a class="add" href="lvProductLadderAction!befAdd.action?json.navTabId=${json.navTabId }" target="dialog" mask="true" width="800" height="400" title="添加"><span>添加</span></a></li>
			<li><a class="delete"  href="lvProductLadderAction!delList.action?json.navTabId=${json.navTabId }" target="selectedTodo" title="确实要删除这些记录吗?该操作请谨慎!"  rel="ids"><span>批量删除</span></a></li>
		</sec:authorize>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="145">
		<thead>
			<tr>
				<th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="4%" orderField="id" class="${orderDirection}">ID</th>
				<th width="10%">所属关系</th>
				<th width="30%">产品名称</th>
				<th width="10%">上区间</th>
				<th width="10%">下区间</th>
				<th width="10%">产品单价</th>
				<th width="25%">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:foreach items="${page.list}" var="item">
			<tr>
				<td><input name="ids" value="${item.id}:${item.productCode}" type="checkbox"></td>
				<td>${item.id }</td>
				<td>
					<c:foreach items="${storeList}" var="store">
					<c:if test="${store.storeFlag==item.storeId}">${store.name}</c:if>
					</c:foreach>
				</td>
				<td>
				<c:foreach items="${productList}" var="product">
				    <c:if test="${product.code==item.productCode}">${product.productName }</c:if>
				</c:foreach>
				</td>
				<td>${item.upInterval}</td>
				<td>${item.downInterval}</td>
				<td>${item.price}</td>
				<td>
				<sec:authorize url="userEdit">
				    <a class="btnView" href="lvProductLadderAction!view.action?lvProductLadder.id=${item.id}" target="dialog" navTabId="lvAccount" rel="lvAccount" title="查看" width="800" height="400" mask="true">查看</a>
					<a class="btnEdit" href="lvProductLadderAction!befEdit.action?lvProductLadder.id=${item.id}&json.navTabId=${json.navTabId }"  title="编辑" target="dialog"  width="800" height="400" mask="true">编辑</a>
					<a class="btnDel"  href="lvProductLadderAction!del.action?lvProductLadder.id=${item.id}&json.navTabId=${json.navTabId }" title="确实要删除这些记录吗?该操作请谨慎!" target="ajaxTodo">删除</a>
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
