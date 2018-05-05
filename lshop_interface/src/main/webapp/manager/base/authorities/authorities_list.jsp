<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<jsp:include page="../common/pagerForm.jsp">
<jsp:param value="baseAuthorities!list.action" name="pagerFormAction"/>
</jsp:include>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="baseAuthorities!list.action" method="post">
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
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
		<sec:authorize url="authorityEdit">
			<li><a class="add" href="baseAuthorities!bfAdd.action?json.navTabId=${json.navTabId }" target="dialog" mask="true" title="添加用户"><span>添加</span></a></li>
			<li><a title="确实要删除这些记录吗?该操作请谨慎!" target="selectedTodo" rel="ids" href="baseAuthorities!delete.action?json.navTabId=${json.navTabId }" class="delete"><span>批量删除</span></a></li>
		</sec:authorize>
			<li><a title="刷新缓存" target="ajaxTodo" href="baseAuthorities!refreshCache.action?json.navTabId=${json.navTabId }" class="delete"><span>刷新缓存</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="5%" orderField="id" class="${orderDirection}">ID</th>
				<th width="20%" >名称</th>
				<th width="25%" >描述</th>
				<th width="20%" orderField="createdDate" class="${orderDirection}">创建时间</th>
				<th width="30%" >操作</th>
			</tr>
		</thead>
		<tbody>
		<c:foreach items="${page.list}" var="baseAuthorities">
			<tr>
				<td><input name="ids" value="${baseAuthorities.id}" type="checkbox"></td>
				<td>${baseAuthorities.id}</td>
				<td>${baseAuthorities.authorityName}</td>
				<td>${baseAuthorities.authorityDesc}</td>
				<td><fmt:formatDate value="${baseAuthorities.createdDate}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
				<td>
				<sec:authorize url="authorityEdit">
					<a title="确实要删除这些记录吗?该操作请谨慎!" target="ajaxTodo" href="baseAuthorities!delete.action?ids=${baseAuthorities.id}&json.navTabId=${json.navTabId }" class="btnDel">删除</a>
					<a title="编辑" target="dialog" mask="true" href="baseAuthorities!bfEdit.action?authoritiesCode=${baseAuthorities.authoritiesCode}&json.navTabId=${json.navTabId }" class="btnEdit" title="编辑">编辑</a>
				</sec:authorize>	
					<a title="查看明细" target="navTab" href="baseAuthoritiesResources!list.action?authoritiesCode=${baseAuthorities.authoritiesCode}&json.navTabId=bas_Item" rel="bas_Item"  class="btnLook">查看明细</a>
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
