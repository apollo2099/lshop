<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="../common/pagerForm.jsp">
<jsp:param value="lvProductAction!getProductProperty.action?lvProduct.code=${lvProduct.code}" name="pagerFormAction"/>
</jsp:include>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="lvProductAction!getProductProperty.action?lvProduct.code=${lvProduct.code}" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>属性标题：</label>
				<input type="text" name="lvProductProperty.title" maxlength="100" value="${lvProductProperty.title}"/>
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
		    <li><a class="add" href="lvProductPropertyAction!befShopAdd.action?lvProductProperty.productCode=${lvProduct.code }&lvProductProperty.storeId=${lvProduct.storeId }&json.navTabId=${json.navTabId }" target="dialog" mask="true" width="900" height="600" title="添加"><span>添加</span></a></li>
			<li><a class="delete"  href="lvProductPropertyAction!delList.action?json.navTabId=${json.navTabId }" target="selectedTodo" title="确实要删除这些记录吗?该操作请谨慎!"  rel="ids"><span>批量删除</span></a></li>
		</sec:authorize>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="145">
		<thead>
			<tr>
				<th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl" ></th>
				<th width="5%" orderField="id" class="${orderDirection}">ID</th>
				<th >标题</th>
				<th orderField="sortId" class="${orderDirection}">排序</th>
				<th >操作</th>
			</tr>
		</thead>
		<tbody>
        <s:iterator value="#request.page.list" status="stat" id="item">
			<tr>
				<td><input name="ids" value="${item.id}" type="checkbox"></td>
				<td>${item.id }</td>
				<td>
				<s:property escapeHtml="true" value="#item.title"/>
				</td>
				<td>${item.sortId}</td>
				<td>
				<sec:authorize url="userEdit">
				    <a class="btnView" href="lvProductPropertyAction!shopView.action?lvProductProperty.id=${item.id}" target="dialog" navTabId="lvAccount" rel="lvAccount" title="查看" width="900" height="600" mask="true">查看</a>
					<a class="btnEdit" href="lvProductPropertyAction!befShopEdit.action?lvProductProperty.id=${item.id}&json.navTabId=${json.navTabId }"  title="编辑" target="dialog"  width="900" height="600" mask="true">编辑</a>
					<a class="btnDel"  href="lvProductPropertyAction!del.action?lvProductProperty.id=${item.id}&json.navTabId=${json.navTabId }" title="确实要删除这些记录吗?该操作请谨慎!" target="ajaxTodo">删除</a>
				</sec:authorize>	
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
