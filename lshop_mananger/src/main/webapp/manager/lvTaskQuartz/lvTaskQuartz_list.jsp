<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<form id="pagerForm" method="post" action="lvTaskQuartzMngAction!list.action?json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
	
</form>

<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="lvTaskQuartzMngAction!list.action?json.navTabId=${json.navTabId}" method="post" rel="pagerForm">
		<div class="searchBar">
			<table class="searchContent" layoutH="750">
				<tr>
						<td><label>任务名称</label></td><td><input name="ttaskQuartz.taskName" type="text" size="20" value="${lvTaskQuartz.taskName}"/></td>
						<td><label>目标实例</label></td><td><input name="ttaskQuartz.targetObject" type="text" size="20" value="${lvTaskQuartz.targetObject}"/></td>
						<td><label>目标方法</label></td><td><input name="ttaskQuartz.targetMethod" type="text" size="20" value="${lvTaskQuartz.targetMethod}"/></td>
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
				<li><a class="add" href="lvTaskQuartzMngAction!befSave.action?json.navTabId=${json.navTabId}" target="dialog" navTabId="ttaskQuartz" rel="ttaskQuartz" width="400" height="300" mask="true"><span>添加</span></a></li>
				<li class="line">line</li>
				<li><a class="delete" href="lvTaskQuartzMngAction!delList.action?json.navTabId=${json.navTabId}" target="selectedTodo" title="确实要删除这些记录吗?"><span>批量删除</span></a></li>
			</ul>
		</div>
		<table class="table" width="100%" layoutH="180">
			<thead>
				<tr>
					<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
						<!-- 生成表单 -->
							<th width="35">编号</th>
							<th>任务名称</th>
							<th>目标实例</th>
							<th>目标方法</th>
							<th>定时时间</th>
							<th>创建时间</th>
							<th>描述</th>
					<th width="130">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="page.list" status="stat" id="item">
					<td><input name="ids" value="${item.id}" type="checkbox"></td>
					<!-- 生成表单 -->
								<td>${stat.count }</td>
								<td>${item.taskName }</td>
								<td>${item.targetObject }</td>
								<td>${item.targetMethod }</td>
								<td>${item.targetTime }</td>
								<td><s:date name="createTime" format="yyyy-MM-dd HH:mm"/></td>
								<td>${item.description}</td>
					<td>
					   
						<a class="btnDel" href="lvTaskQuartzMngAction!del.action?lvTaskQuartz.id=${item.id}&json.navTabId=${json.navTabId}" target="ajaxTodo" title="删除">删除</a>
						<a class="btnEdit" href="lvTaskQuartzMngAction!befEdit.action?lvTaskQuartz.id=${item.id}&json.navTabId=${json.navTabId}" target="dialog" navTabId="ttaskQuartz" rel="ttaskQuartz" title="编辑"  width="500" height="400" mask="true">编辑</a>
						<a class="btnView" href="lvTaskQuartzMngAction!view.action?lvTaskQuartz.id=${item.id}" target="dialog" navTabId="ttaskQuartz" rel="ttaskQuartz" title="查看" width="850" height="500">查看</a>
					    <s:if test="#item.status==1">
					    <a  style="color:blue;" href="lvTaskQuartzMngAction!stop.action?lvTaskQuartz.id=${item.id}&json.navTabId=${json.navTabId}" target="ajaxTodo" title="确定暂停任务?">暂停</a>
					    </s:if>
					    <s:else>
					    <a style="color:blue;" href="lvTaskQuartzMngAction!start.action?lvTaskQuartz.id=${item.id}&json.navTabId=${json.navTabId}" target="ajaxTodo" title="确定恢复任务?">恢复</a>
					    </s:else>
					</td>
				</tr>
				</s:iterator>
				
			</tbody>
		</table>
		<%@ include file="../common/panelBar.jsp"%>
	</div>
</div>