<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="../common/pagerForm.jsp">
<jsp:param value="lvGroupPropertyAction!list.action?lvGroupProperty.groupCode=${lvGroupProperty.groupCode }" name="pagerFormAction"/>
</jsp:include>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="lvGroupPropertyAction!list.action?lvGroupProperty.groupCode=${lvGroupProperty.groupCode }" method="post">
	<div class="searchBar">
			<table class="searchContent" layoutH="750">
				<tr>
				</tr><tr>
				<%--
						<td><label>团购标题</label></td><td>
						<select name="lvGroupProperty.groupCode">
				 <option value="">==请选择==</option>
				 <c:foreach items="${groupList}" var="group">
				    <option value="${group.code }" <c:if test="${group.code==lvGroupProperty.groupCode}">selected="selected"</c:if>>${group.title }</option>
				 </c:foreach>
				</select>
				 
				</td>
				--%>
						<td><label>属性标题</label></td><td><input type="text" name="lvGroupProperty.title" value="${lvGroupProperty.title}"/></td>
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
		    <li><a class="add" href="lvGroupPropertyAction!befAdd.action?lvGroupProperty.groupCode=${lvGroupProperty.groupCode }&json.navTabId=${json.navTabId }" target="dialog" mask="true" width="900" height="600" title="添加"><span>添加</span></a></li>
			<li><a class="delete"  href="lvGroupPropertyAction!delList.action?json.navTabId=${json.navTabId }" target="selectedTodo" title="确实要删除这些记录吗?该操作请谨慎!"  rel="ids"><span>批量删除</span></a></li>
		</sec:authorize>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="165">
		<thead>
			<tr>
				<th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="4%" orderField="id" class="${orderDirection}">ID</th>
				<th width="45%">团购名称</th>
				<th width="35%">属性名称</th>
				<th width="5%">排序值</th>
				<th width="10%">操作</th>
			</tr>
		</thead>
		<tbody>
		<c:foreach items="${page.list}" var="item">
			<tr>
				<td><input name="ids" value="${item.id}" type="checkbox"></td>
				<td>${item.id }</td>
				<td>
				<c:foreach items="${groupList}" var="group">
				    <c:if test="${group.code==item.groupCode}">${group.title }</c:if>
				</c:foreach>
				</td>
				<td>${item.title }</td>
				<td>${item.sortId }</td>
				<td>
				<sec:authorize url="userEdit">
				    <a class="btnView" href="lvGroupPropertyAction!view.action?lvGroupProperty.id=${item.id}" target="dialog" navTabId="lvAccount" rel="lvAccount" title="查看" width="1020" height="700" mask="true">查看</a>
					<a class="btnEdit" href="lvGroupPropertyAction!befEdit.action?lvGroupProperty.id=${item.id}&json.navTabId=${json.navTabId }"  title="编辑" target="dialog"  width="900" height="600" mask="true">编辑</a>
					<a class="btnDel"  href="lvGroupPropertyAction!del.action?lvGroupProperty.id=${item.id}&json.navTabId=${json.navTabId }" title="确实要删除这些记录吗?该操作请谨慎!" target="ajaxTodo">删除</a>
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
