<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<jsp:include page="../common/pagerForm.jsp">
	<jsp:param value="baseRoles!list.action?code=${param.code}" name="pagerFormAction"/>
	<jsp:param value="${json.navTabId }" name="rel"/>
</jsp:include>

<div class="pageHeader" style="border:1px #B8D0D6 solid">
	<form rel="pagerForm" onsubmit="return divSearch(this, '${json.navTabId }');" action="baseRoles!list.action?code=${param.code}" method="post">
	<input type="hidden" name="json.navTabId" value="${json.navTabId }" />
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>名称：</label>
				<input type="text" name="keyword" value="${keyword}"/>
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
<div class="pageContent" style="border-left:1px #B8D0D6 solid;border-right:1px #B8D0D6 solid">
	<div class="panelBar">
		<ul class="toolBar">
		<sec:authorize url="roleEdit">
			<li><a class="add" href="baseRoles!bfAdd.action?json.navTabId=${json.navTabId }&departmentCode=${param.code}" target="dialog" mask="true" title="添加资源"><span>添加</span></a></li>
			<li><a title="确实要删除这些记录吗?该操作请谨慎!" target="selectedTodo" rel="ids" href="baseRoles!delete.action?json.rel=${json.navTabId }" class="delete"><span>批量删除</span></a></li>
		</sec:authorize>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="149" rel="${json.navTabId }">
		<thead>
			<tr>
				<th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="5%" orderField="id" class="${orderDirection}">ID</th>
				<th width="15%" >角色名称</th>
				<th width="15%" >角色描述</th>
				<th width="15%" >所属店铺</th>
				<th width="15%" orderField="createdBy" class="${orderDirection}">创建时间</th>
				<th width="25%" >操作</th>
			</tr>
		</thead>
		<tbody>
		<c:foreach items="${page.list}" var="baseRoles">
			<tr>
				<td><input name="ids" value="${baseRoles.id}" type="checkbox"></td>
				<td>${baseRoles.id}</td>
				<td>${baseRoles.roleName}</td>
				<td>${baseRoles.roleDesc}</td>
				<td>
				<c:foreach items="${storeList}" var="store">
				<c:if test="${store.storeFlag==baseRoles.storeFlag}">${store.name}</c:if>
				</c:foreach>
				</td>
				<td><fmt:formatDate value="${baseRoles.createdDate}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
				<td>
				<sec:authorize url="roleEdit">
					<a title="确实要删除这些记录吗?该操作请谨慎!" target="ajaxTodo" rel="jbsxBox" href="baseRoles!delete.action?ids=${baseRoles.id}&json.rel=${json.navTabId }" class="btnDel">删除</a>
					<a title="编辑" target="dialog" mask="true" href="baseRoles!bfEdit.action?roleCode=${baseRoles.roleCode}&json.navTabId=${json.navTabId }" class="btnEdit" title="编辑">编辑</a>
				</sec:authorize>
					<a title="权限明细" target="navTab" href="baseRolesAuthorities!list.action?roleCode=${baseRoles.roleCode}&json.navTabId=${json.navTabId }_Item" rel="${json.navTabId }_Item"  class="btnLook">权限明细</a>
				</td>
			</tr>
		</c:foreach>
		</tbody>
	</table>
	<jsp:include page="../common/panelBar.jsp">
	<jsp:param value="navTab" name="targetType"/>
	<jsp:param value="${json.navTabId }" name="rel"/>
	</jsp:include>
</div>
