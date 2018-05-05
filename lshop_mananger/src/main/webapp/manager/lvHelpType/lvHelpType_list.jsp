<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<form id="pagerForm" method="post" action="lvHelpTypeMngAction!list.action?json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
</form>
<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="lvHelpTypeMngAction!list.action?json.navTabId=${json.navTabId}" method="post" rel="pagerForm">
		<div class="searchBar">
			<table class="searchContent" layoutH="800">
				<tr>
						</tr><tr>
						<td><label>类别名称</label></td><td><input name="lvHelpType.name" type="text" size="20" value="${lvHelpType.name}"/></td>
						<td><label>所属关系</label></td>
						<td>
						<select name="lvHelpType.storeId">
						<option value="">==请选择==</option>
						<c:foreach items="${storeList}" var="store">
						<option  value="${store.storeFlag }" <c:if test="${store.storeFlag==lvHelpType.storeId}">selected="selected"</c:if>>${store.name}</option>
					    </c:foreach>
					    </select>
						</td>
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
				<li><a class="add" href="lvHelpTypeMngAction!befSave.action?json.navTabId=${json.navTabId}" target="dialog" navTabId="lvHelpType" rel="lvHelpType" width="500" height="500" mask="true"><span>添加</span></a></li>
				<li class="line">line</li>
				<li><a class="delete" href="lvHelpTypeMngAction!delList.action?json.navTabId=${json.navTabId}" target="selectedTodo" title="确实要删除这些记录吗?该操作请谨慎!"><span>批量删除</span></a></li>
			</ul>
		</div>
		<table class="table" width="100%" layoutH="165">
			<thead>
				<tr>
					        <th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
						    <!-- 生成表单 -->
							<th width="5%" orderField="id" class="${orderDirection}"}">编号</th>
							<th width="10%">所属关系</th>
							<th width="34%" orderField="name" class="${orderDirection}"}">类别名称</th>
							<th width="10%">排序值</th>
							<th width="10%">语言</th>
					        <th width="30%">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="page.list" status="stat" id="item">
				<tr>
					<td><input name="ids" value="${item.id}" type="checkbox"></td>
					<!-- 生成表单 -->
					<td>${stat.index+1}</td>
					<td>
					<c:foreach items="${storeList}" var="store">
					<c:if test="${store.storeFlag==item.storeId}">${store.name}</c:if>
					</c:foreach>
					</td>
					<td>${item.name}</td>
					<td>${item.orderValue}</td>
					<td>
					<s:if test="#item.webLanguage=='cn'">中文简体</s:if>
					<s:if test="#item.webLanguage=='tw'">中文繁体</s:if>
					<s:if test="#item.webLanguage=='en'">英文</s:if>
					<s:if test="#item.webLanguage=='kn'">韩文</s:if>
					<s:if test="#item.webLanguage=='ja'">日文</s:if>
					</td>
					<td>
						<a class="btnDel" href="lvHelpTypeMngAction!del.action?lvHelpType.id=${item.id}&json.navTabId=${json.navTabId}" title="确实要删除这些记录吗?该操作请谨慎!" target="ajaxTodo">删除</a>
						<a class="btnEdit" href="lvHelpTypeMngAction!befEdit.action?lvHelpType.id=${item.id}&json.navTabId=${json.navTabId}" target="dialog" navTabId="lvHelpType" rel="lvHelpType" title="编辑" width="500" height="500" mask="true">编辑</a>
					</td>
				</tr>
				</s:iterator>
				
			</tbody>
		</table>
		<%@ include file="../common/panelBar.jsp"%>
	</div>
</div>