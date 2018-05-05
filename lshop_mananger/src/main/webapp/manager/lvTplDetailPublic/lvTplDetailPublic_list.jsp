<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<form id="pagerForm" method="post" action="lvTplDetailPublicMngAction!list.action?lvTplModelPublic.code=${lvTplModelPublic.code}&json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
	
</form>

<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="lvTplDetailPublicMngAction!list.action?lvTplModelPublic.code=${lvTplModelPublic.code}&json.navTabId=${json.navTabId}" method="post" rel="pagerForm">
		<div class="searchBar">
			<table class="searchContent" layoutH="750">
				<tr>
						</tr><tr>
						<td><label>模板名称</label></td><td><input name="lvTplDetailPublic.name" type="text" size="20" value="${lvTplDetailPublic.name}"/></td>
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
				<li><a class="add" href="lvTplDetailPublicMngAction!befSave.action?lvTplModelPublic.code=${lvTplModelPublic.code}&json.navTabId=${json.navTabId}" target="dialog" navTabId="lvTplDetailPublic" rel="lvTplDetailPublic" width="850" height="500" mask="true"><span>添加</span></a></li>
			    <li class="line">line</li>
				<li><a class="delete" href="lvTplDetailPublicMngAction!delList.action?lvTplModelPublic.code=${llvTplModelPublic.code}&json.navTabId=${json.navTabId}" target="selectedTodo" title="确实要删除这些记录吗?"><span>批量删除</span></a></li>
			</ul>
		</div>
		<table class="table" width="100%" layoutH="170">
			<thead>
				<tr>
					<th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
						<!-- 生成表单 -->
							<th width="4%" orderField="id" class="${orderField eq 'id' ? orderDirection : ''}" width="50">编号</th>
							<th width="30%" orderField="name" class="${orderField eq 'name' ? orderDirection : ''}">模板名称</th>
							<th width="25%" orderField="pagePath" class="${orderField eq 'pagePath' ? orderDirection : ''}">模版文件路径</th>
							<th width="10%" orderField="createTime" class="${orderField eq 'createTime' ? orderDirection : ''}">创建时间</th>
							<th width="10%" orderField="modifyTime" class="${orderField eq 'modifyTime' ? orderDirection : ''}">修改时间</th>
					        <th width="10%">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="page.list" status="stat" id="item">
					<td><input name="ids" value="${item.id}" type="checkbox"></td>
					<!-- 生成表单 -->
								<td>${stat.count}</td>
								<td>${item.name }</td>
								<td>${item.pagePath }</td>
								<td><s:date name="createTime" format="yyyy-MM-dd HH:mm"/></td>
								<td><s:date name="modifyTime" format="yyyy-MM-dd HH:mm"/></td>
					<td>
						<a class="btnDel" href="lvTplDetailPublicMngAction!del.action?lvTplDetailPublic.id=${item.id}&json.navTabId=${json.navTabId}" target="ajaxTodo" title="删除">删除</a>
						<a class="btnEdit" href="lvTplDetailPublicMngAction!befEdit.action?lvTplDetailPublic.id=${item.id}&json.navTabId=${json.navTabId}" target="dialog" navTabId="lvTplDetailPublic" rel="lvTplDetailPublic" title="编辑" width="850" height="500" mask="true">编辑</a>
					</td>
				</tr>
				</s:iterator>
				
			</tbody>
		</table>
		<%@ include file="../common/panelBar.jsp"%>
	</div>
</div>