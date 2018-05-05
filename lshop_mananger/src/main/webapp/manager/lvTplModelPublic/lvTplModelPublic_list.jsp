<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<form id="pagerForm" method="post" action="lvTplModelPublicMngAction!list.action?json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
	
</form>

<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="lvTplModelPublicMngAction!list.action?json.navTabId=${json.navTabId}" method="post" rel="pagerForm">
		<div class="searchBar">
			<table class="searchContent" layoutH="710">
				<tr>
						</tr><tr>
						<td><label>名称</label></td><td><input name="lvTplModelPublic.modelName" type="text" size="20" value="${lvTplModelPublic.modelName}"/></td>
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
				<li><a class="add" href="lvTplModelPublicMngAction!befSave.action?json.navTabId=${json.navTabId}" target="dialog" navTabId="lvTplModelPublic" rel="lvTplModelPublic" width="400" height="200" mask="true"><span>添加</span></a></li>
				<li class="line">line</li>
			</ul>
		</div>
		<table class="table" width="100%" layoutH="170">
			<thead>
				<tr>
					<th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
						<!-- 生成表单 -->
							<th width="4%" orderField="id" class="${orderField eq 'id' ? orderDirection : ''}" width="50">编号</th>
							<th width="40%" orderField="modelName" class="${orderField eq 'modelName' ? orderDirection : ''}">名称</th>
							<th width="10%" orderField="createTime" class="${orderField eq 'createTime' ? orderDirection : ''}" width="200">创建时间</th>
							<th width="10%" orderField="modifyTime" class="${modifyTime eq 'modifyTime' ? orderDirection : ''}" width="200">修改时间</th>
					        <th width="35%">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="page.list" status="stat" id="item">
					<td><input name="ids" value="${item.id}" type="checkbox"></td>
					<!-- 生成表单 -->
								<td>${stat.count }</td>
								<td>${item.modelName }</td>
								<td><s:date name="createTime" format="yyyy-MM-dd HH:mm"/></td>
								<td><s:date name="modifyTime" format="yyyy-MM-dd HH:mm"/></td>
					<td>
						<a class="btnDel" href="lvTplModelPublicMngAction!del.action?lvTplModelPublic.id=${item.id}&json.navTabId=${json.navTabId}" target="ajaxTodo" title="删除">删除</a>
						<a class="btnEdit" href="lvTplModelPublicMngAction!befEdit.action?lvTplModelPublic.id=${item.id}&json.navTabId=${json.navTabId}" target="dialog" navTabId="lvTplModelPublic" rel="lvTplModelPublic" title="编辑" width="400" height="200" mask="true">编辑</a>
					    <a class="btnLook" href="lvTplDetailPublicMngAction!list.action?lvTplModelPublic.code=${item.code}&json.navTabId=lvTplDetailPublic" target="navTab" navTabId="lvTplDetailPublic" rel="lvTplDetailPublic" title="明细管理" width="850" height="500" mask="true">明细管理</a>
					</td>
				</tr>
				</s:iterator>
				
			</tbody>
		</table>
		<%@ include file="../common/panelBar.jsp"%>
	</div>
</div>