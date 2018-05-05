<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="gv" uri="/WEB-INF/tld/gv.tld"%>
<jsp:include page="../common/pagerForm.jsp">
	<jsp:param value="baseRolesAuthorities!list.action?roleCode=${param.roleCode}" name="pagerFormAction"/>
</jsp:include>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="baseRolesAuthorities!list.action?roleCode=${param.roleCode}" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>名称：</label>
				<input type="text" name="keyword" value="${keyword}"/>
			</li>
		</ul>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit" >检索</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
		<sec:authorize url="roleAuthorityEdit">
			<li><a class="add" href="baseRolesAuthorities!bfAdd.action?roleCode=${param.roleCode}&json.navTabId=${json.navTabId }" target="dialog" mask="true" width="800" height ="500" title="添加"><span>添加</span></a></li>
			<li><a title="确实要删除这些记录吗?该操作请谨慎!" target="selectedTodo" rel="ids" href="baseRolesAuthorities!delete.action?json.navTabId=${json.navTabId }" class="delete"><span>批量删除</span></a></li>
		</sec:authorize>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="5%" orderField="id" class="${orderDirection}">ID</th>
				<th width="40%" >权限名称</th>
				<th width="25%" orderField="createdDate" class="${orderDirection}">创建时间</th>
				<th width="30%" >操作</th>
			</tr>
		</thead>
		<tbody>
		<c:foreach items="${page.list}" var="baseRolesAuthorities">
			<tr>
				<td><input name="ids" value="${baseRolesAuthorities.id}" type="checkbox"></td>
				<td>${baseRolesAuthorities.id}</td>
				<td><gv:dictionary type="text" code="BASE_AUTHORITIES" value="${baseRolesAuthorities.authorityCode}"/></td>
				<td><fmt:formatDate value="${baseRolesAuthorities.createdDate}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
				<td>
				<sec:authorize url="roleAuthorityEdit">
					<a title="确实要删除这些记录吗?该操作请谨慎!" target="ajaxTodo" href="baseRolesAuthorities!delete.action?ids=${baseRolesAuthorities.id}&json.navTabId=${json.navTabId }" class="btnDel">删除</a>
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
