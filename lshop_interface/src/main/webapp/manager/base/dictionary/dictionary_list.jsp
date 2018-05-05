<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<jsp:include page="../common/pagerForm.jsp">
	<jsp:param value="baseDictionarys!list.action?dictionaryCode=${param.dictionaryCode}&level=${param.level}" name="pagerFormAction"/>
	<jsp:param value="${json.navTabId }" name="rel"/>
</jsp:include>

<div class="pageHeader" style="border:1px #B8D0D6 solid">
	<form rel="pagerForm" onsubmit="return divSearch(this, '${json.navTabId }');" action="baseDictionarys!list.action?dictionaryCode=${param.dictionaryCode}&level=${param.level}" method="post">
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
		<sec:authorize url="DictionaryEdit">
			<li><a class="add" href="baseDictionarys!bfAdd.action?json.navTabId=${json.navTabId }&dictionaryCode=${param.dictionaryCode}&level=${param.level}" target="dialog" mask="true" title="添加字典"><span>添加</span></a></li>
			<li><a title="确实要删除这些记录吗?该操作请谨慎!" target="selectedTodo" rel="ids" href="baseDictionarys!delete.action?json.rel=${json.navTabId }" class="delete"><span>批量删除</span></a></li>
		</sec:authorize>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="149" rel="${json.navTabId }">
		<thead>
			<tr>
				<th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="3%" orderField="id" class="${orderDirection}">ID</th>
				<th width="10%" >编码</th>
				<th width="10%" >父编码</th>
				<th width="10%" >名称</th>
				<th width="10%" >描述</th>
				<th width="10%" orderField="createdBy" class="${orderDirection}">创建时间</th>
				<th width="30%" >操作</th>
			</tr>
		</thead>
		<tbody>
		<c:foreach items="${page.list}" var="baseDictionarys">
			<tr>
				<td><input name="ids" value="${baseDictionarys.id}" type="checkbox"></td>
				<td>${baseDictionarys.id}</td>
				<td>${baseDictionarys.dictionaryCode}</td>
				<td>${baseDictionarys.parentCode}</td>
				<td>${baseDictionarys.codeName}</td>
				<td>${baseDictionarys.description}</td>
				<td><fmt:formatDate value="${baseDictionarys.createdDate}" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
				<td>
				<sec:authorize url="ResourcesEdit">
					<a title="确实要删除这些记录吗?该操作请谨慎!" target="ajaxTodo" rel="dictionaryBox" href="baseDictionarys!delete.action?ids=${baseDictionarys.id}&json.rel=${json.navTabId }" class="btnDel">删除</a>
					<a title="编辑" target="dialog" mask="true" href="baseDictionarys!bfEdit.action?dictionaryCode=${baseDictionarys.dictionaryCode}&level=${baseDictionarys.levelNo}&parentCode=${baseDictionarys.parentCode }&json.navTabId=${json.navTabId }" class="btnEdit" title="编辑">编辑</a>
				</sec:authorize>
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
