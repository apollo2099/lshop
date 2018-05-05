<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<form id="pagerForm" method="post" action="lvBlogSubscribeAction!list.action?json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="page.orderField" value="${orderField}" />
	<input type="hidden" name="page.orderDirection" value="${orderDirection}" />	
	
</form>

<div class="page">

	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<li><a class="add" href="lvBlogSubscribeAction!befSave.action?json.navTabId=${json.navTabId}" target="dialog" navTabId="lvBlogSubscribe" rel="lvBlogType" width="650" height="450"  mask="true"><span>添加</span></a></li>
				<li class="line">line</li>
				<li><a class="delete" href="lvBlogSubscribeAction!delList.action?json.navTabId=${json.navTabId}" target="selectedTodo" title="确实要删除这些记录吗?" rel="ids"><span>批量删除</span></a></li>
			</ul>
		</div>
		<table class="table" width="100%" layoutH="75">
			<thead>
				<tr>
					<th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
						<!-- 生成表单 -->
							<th width="5%" orderField="tplKey">序号</th>
							<th width="64%">订阅标题</th>
							<th width="10%">发送周期</th>
							<th width="10%">发送时间</th>
							<th width="10%">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="#request.page.list" status="stat" id="item">
				<tr>
					<td><input name="ids" value="${item.id}" type="checkbox"></td>
					<!-- 生成表单 -->
								<td>${item.id }</td>
								<td>${item.title }</td>
								<td>
								<c:if test="${item.sendCycle ==0}">永不发送</c:if>
								<c:if test="${item.sendCycle ==1}">每天</c:if>
								<c:if test="${item.sendCycle ==2}">每周一</c:if>
								<c:if test="${item.sendCycle ==3}">每周三</c:if>
								<c:if test="${item.sendCycle ==4}">每个月</c:if>
								</td>
								<td>${item.sendTime }</td>
					<td>
						<a class="btnEdit" href="lvBlogSubscribeAction!befEdit.action?lvBlogSubscribe.id=${item.id}&json.navTabId=${json.navTabId}" target="dialog" navTabId="lvBlogSubscribe" rel="lvBlogSubscribe" title="修改" width="650" height="450" mask="true">修改</a>
						<a class="btnDel" href="lvBlogSubscribeAction!del.action?lvBlogSubscribe.id=${item.id}&json.navTabId=${json.navTabId}" target="ajaxTodo" title="删除">删除</a>
					</td>
				</tr>
				</s:iterator>
				
			</tbody>
		</table>
		<%@ include file="../common/panelBar.jsp"%>
	</div>
</div>