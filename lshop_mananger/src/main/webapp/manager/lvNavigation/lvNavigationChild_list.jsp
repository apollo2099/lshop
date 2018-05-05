<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<form id="pagerForm" method="post" action="lvNavigationMngAction!list.action?json.navTabId=${json.navTabId}&lvNavigation.navParentId=${lvNavigation.navParentId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
	
</form>

<div class="page">
	<div class="pageHeader">
	
		<div class="searchBar">
			<table class="searchContent" layoutH="750">
				
			</table>
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="lvNavigationMngAction!befSave.action?lvNavigation.navParentId=${lvNavigation.navParentId}&json.navTabId=${json.navTabId}" target="dialog" navTabId="lvNavigationChild" rel="lvNavigationChild" width="850" height="500" mask="true"><span>添加</span></a></li>
				<li class="line">line</li>
				<li><a class="delete" href="lvNavigationMngAction!delList.action?lvNavigation.navParentId=${lvNavigation.navParentId}&json.navTabId=${json.navTabId}" target="selectedTodo" title="确实要删除这些记录吗?"><span>批量删除</span></a></li>
			</ul>
		</div>
		<table class="table" width="100%" layoutH="180">
			<thead>
				<tr>
					<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
						<!-- 生成表单 -->
							<th width="50">编号</th>
							<th>导航名称</th>
							<th>导航链接</th>
							<th>打开方式</th>
							<th>排序值</th>
							<th orderField="createTime" class="${orderField eq 'createTime' ? orderDirection : ''}">创建时间</th>
					        <th orderField="modifyTime" class="${modifyTime eq 'modifyTime' ? orderDirection : ''}" width="200">修改时间</th>
							
					<th width="90">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="page.list" status="stat" id="item">
					<td><input name="ids" value="${item.id}" type="checkbox"></td>
					<!-- 生成表单 -->
								<td>${stat.count }</td>
								<td>${item.navName }</td>
								<td>${item.navUrl }</td>
								<td><s:if test="#item.openTarget==1">新窗口打开</s:if><s:else>本窗口打开</s:else></td>
								<td>${item.orderValue}</td>
								<td><s:date name="createTime" format="yyyy-MM-dd HH:mm"/></td>
								<td><s:date name="modifyTime" format="yyyy-MM-dd HH:mm"/></td>
					<td>
						<a class="btnDel" href="lvNavigationMngAction!del.action?lvNavigation.id=${item.id}&lvNavigation.navParentId=${lvNavigation.navParentId}&json.navTabId=${json.navTabId}" target="ajaxTodo" title="删除">删除</a>
						<a class="btnEdit" href="lvNavigationMngAction!befEdit.action?lvNavigation.id=${item.id}&json.navTabId=${json.navTabId}" target="dialog" navTabId="lvNavigationChild" rel="lvNavigationChild" title="编辑" width="850" height="500" mask="true">编辑</a>
					</td>
				</tr>
				</s:iterator>
			</tbody>
		</table>
		<%@ include file="../common/panelBar.jsp"%>
	</div>
</div>