<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<form id="pagerForm" method="post" action="lvBlogTagsAction!list.action?json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="page.orderField" value="${orderField}" />
	<input type="hidden" name="page.orderDirection" value="${orderDirection}" />	
	
</form>

<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="lvBlogTagsAction!list.action?json.navTabId=${json.navTabId}" method="post" rel="pagerForm">
		<div class="searchBar">
			<table class="searchContent" layoutH="750">
				<tr>
						</tr><tr>
						<td><label>序号</label></td><td><input name="lvBlogTags.id" type="text" size="20" value="${lvBlogTags.id}"/></td>
						<td><label>标签名称</label></td><td><input name="lvBlogTags.tagName" type="text" size="20" value="${lvBlogTags.tagName}"/></td>
						<td><label>所属关系</label></td>
						<td>
						<select name="lvBlogTags.storeId">
						<option value="">==请选择==</option>
						<c:foreach items="${storeList}" var="store">
						<option  value="${store.storeFlag }" <c:if test="${store.storeFlag==lvBlogTags.storeId}">selected="selected"</c:if>>${store.name}</option>
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
				<li><a class="add" href="lvBlogTagsAction!befSave.action?json.navTabId=${json.navTabId}" target="dialog" navTabId="lvBlogType" rel="lvBlogType" width="600" height="300"  mask="true"><span>添加</span></a></li>
				<li class="line">line</li>
				<li><a class="delete" href="lvBlogTagsAction!delList.action?json.navTabId=${json.navTabId}" target="selectedTodo" title="确实要删除这些记录吗?" rel="ids"><span>批量删除</span></a></li>
			</ul>
		</div>
		<table class="table" width="100%" layoutH="160">
			<thead>
				<tr>
					<th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
						<!-- 生成表单 -->
						    <th width="5%">序号</th>
							<th width="64%" orderField="tplKey">标签</th>
							<th width="10%">排序值</th>
							<th width="10%">所属关系</th>
							<th width="10%">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="#request.page.list" status="stat" id="item">
				<tr>
					<td><input name="ids" value="${item.id}" type="checkbox"></td>
					<!-- 生成表单 -->
					            <td>${item.id }</td>
								<td>${item.tagName }</td>
								<td>${item.orderId }</td>
								<td>
									<c:foreach items="${storeList}" var="store">
										<c:if test="${store.storeFlag==item.storeId}">${store.name}</c:if>
									</c:foreach>
								</td>
					<td>
						<a class="btnEdit" href="lvBlogTagsAction!befEdit.action?lvBlogTags.id=${item.id}&json.navTabId=${json.navTabId}" target="dialog" navTabId="lvBlogTags" rel="lvBlogTags" title="修改" width="600" height="300" mask="true">修改</a>
						<a class="btnDel" href="lvBlogTagsAction!del.action?lvBlogTags.id=${item.id}&json.navTabId=${json.navTabId}" target="ajaxTodo" title="删除">删除</a>
					</td>
				</tr>
				</s:iterator>
				
			</tbody>
		</table>
		<%@ include file="../common/panelBar.jsp"%>
	</div>
</div>