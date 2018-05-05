<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<form id="pagerForm" method="post" action="lvBlogTagsAction!select.action?json.navTabId=${json.navTabId}&lvBlogTags.storeId=${lvBlogTags.storeId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="page.orderField" value="${orderField}" />
	<input type="hidden" name="page.orderDirection" value="${orderDirection}" />	
	
</form>

<div class="page unitBox">
	<div class="pageHeader">
		<form onsubmit="return dwzSearch(this, 'dialog');" action="lvBlogTagsAction!select.action?json.navTabId=${json.navTabId}&lvBlogTags.storeId=${lvBlogTags.storeId}" method="post" rel="pagerForm">
		<div class="searchBar">
			<table class="searchContent" layoutH="750">
				<tr>
						</tr><tr>
						<td><label>序号</label></td><td><input name="lvBlogTags.id" type="text" size="20" value="${lvBlogTags.id}"/></td>
						<td><label>标签名称</label></td><td><input name="lvBlogTags.tagName" type="text" size="20" value="${lvBlogTags.tagName}"/></td>
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
					<li><div class="button"><div class="buttonContent"><button type="button" multLookup="tagsId" warn="请选择标签">选择带回</button></div></div></li>
				</ul>
			</div>
		</div>
		</form>
	</div>
	<div class="pageContent">
		<table class="table" width="100%" layoutH="160">
			<thead>
				<tr>
					<th width="22"><input type="checkbox" group="tagsId" class="checkboxCtrl"></th>
						<!-- 生成表单 -->
						    <th >序号</th>
							<th orderField="tagName">标签</th>
							<th >排序值</th>
				</tr>
			</thead>
			<tbody class="dateList">
				<s:iterator value="#request.page.list" status="stat" id="item">
				<tr>
					<td><input name="tagsId" value="{'keyword':'${item.tagName}'}" type="checkbox"></td>
					<!-- 生成表单 -->
					            <td>${item.id }</td>
								<td>${item.tagName }</td>
								<td>${item.orderId }</td>
				</tr>
				</s:iterator>
				
			</tbody>
		</table>
		<%@ include file="../common/panelBar.jsp"%>
	</div>
</div>