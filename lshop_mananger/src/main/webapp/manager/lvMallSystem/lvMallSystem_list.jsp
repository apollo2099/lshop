<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<form id="pagerForm" method="post" action="lvMallSystemAction!list.action?json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
	
</form>

<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="lvMallSystemAction!list.action?json.navTabId=${json.navTabId}" method="post" rel="pagerForm">
		<div class="searchBar">
			<table class="searchContent" layoutH="650">
				<tr>
						</tr><tr>
						<td><label>商城体系名称</label></td><td><input name="lvMallSystem.mallSystemName" type="text" size="20" value="${lvMallSystem.mallSystemName}"/></td>
						<td><label>商城体系标示</label></td><td><input name="lvMallSystem.mallSystemFlag" type="text" size="20" value="${lvMallSystem.mallSystemFlag}"/></td>
						<td><label>商城体系域名后缀</label></td><td><input name="lvMallSystem.domainPostfix" type="text" size="20" value="${lvMallSystem.domainPostfix}"/></td>
						</tr><tr>
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
				<li><a class="add" href="lvMallSystemAction!befSave.action?json.navTabId=${json.navTabId}" target="dialog" navTabId="lvMallSystem" rel="lvMallSystem" width="450" height="300" mask="true"><span>添加</span></a></li>
				<li class="line">line</li>
				<li><a class="delete" href="lvMallSystemAction!delList.action?json.navTabId=${json.navTabId}" target="selectedTodo" title="确实要删除这些记录吗?"><span>批量删除</span></a></li>
				<li class="line">line</li>
				<li><a class="add" href="lvMallSystemAction!initMallSystem.action?json.navTabId=${json.navTabId}" target="ajaxTodo" navTabId="lvMallSystem" rel="lvMallSystem"  title="初始化数据"><span>初始化数据</span></a></li>
			</ul>
		</div>
		<table class="table" width="100%" layoutH="170">
			<thead>
				<tr>
					<th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
						<!-- 生成表单 -->
							<th width="4%" >id</th>
							<th width="20%" >商城体系名称</th>
							<th width="15%" >商城体系标示</th>
							<th width="15%" >商城体系域名后缀</th>
							<th width="10%" >创建时间</th>
							<th width="10%" >修改时间</th>
							<th width="25%">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="page.list" status="stat" id="item">
				<tr>
					<td><input name="ids" value="${item.id}" type="checkbox"></td>
					<!-- 生成表单 -->
								<td>${item.id }</td>
								<td>${item.mallSystemName }</td>
								<td>${item.mallSystemFlag }</td>
								<td>${item.domainPostfix }</td>
								<td><s:date name="createTime" format="yyyy-MM-dd HH:mm"/></td>
								<td><s:date name="modifyTime" format="yyyy-MM-dd HH:mm"/></td>

					<td>
						<a class="btnDel" href="lvMallSystemAction!del.action?lvMallSystem.id=${item.id}&json.navTabId=${json.navTabId}" target="ajaxTodo" title="删除">删除</a>
						<a class="btnEdit" href="lvMallSystemAction!befEdit.action?lvMallSystem.id=${item.id}&json.navTabId=${json.navTabId}" target="dialog" navTabId="lvMallSystem" rel="lvMallSystem" title="编辑" width="450" height="300" mask="true">编辑</a>
						<a class="btnView" href="lvMallSystemAction!view.action?lvMallSystem.id=${item.id}" target="dialog" navTabId="lvMallSystem" rel="lvMallSystem" title="查看" width="450" height="350"  mask="true">查看</a>
					</td>
				</tr>
				</s:iterator>
				
			</tbody>
		</table>
		<%@ include file="../common/panelBar.jsp"%>
	</div>
</div>