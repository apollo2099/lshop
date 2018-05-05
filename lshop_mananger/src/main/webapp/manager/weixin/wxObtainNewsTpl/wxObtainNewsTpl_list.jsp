<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<form id="pagerForm" method="post" action="wxObtainNewsTplMngAction!list.action?json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
	
</form>

<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="wxObtainNewsTplMngAction!list.action?json.navTabId=${json.navTabId}" method="post" rel="pagerForm">
		<div class="searchBar">
			<table class="searchContent" layoutH="750">
				<tr>
						</tr><tr>
						<td><label>参加关键词</label></td><td><input name="wxObtainNewsTpl.pushKeyword" type="text" size="20" value="${wxObtainNewsTpl.pushKeyword}"/></td>
						<td><label>查询关键词</label></td><td><input name="wxObtainNewsTpl.queryKeyword" type="text" size="20" value="${wxObtainNewsTpl.queryKeyword}"/></td>
						</tr><tr>
						<td><label>图文素材名称 </label></td><td>
						<select name="wxObtainNewsTpl.newsId">
						<option value="">==请选择==</option>
						<c:foreach items="${newsMaterials}" var="newsMaterial">
						<option  value="${newsMaterial.id }" <c:if test="${newsMaterial.id==wxObtainNewsTpl.newsId}">selected="selected"</c:if>>${newsMaterial.name}</option>
					    </c:foreach>
					    </select>
						<td><label>公众号名称</label></td>
						<td>
						<select name="wxObtainNewsTpl.wxhConfigId">
						<option value="">==请选择==</option>
						<c:foreach items="${wxhConfigList}" var="config">
						<option  value="${config.id }" <c:if test="${config.id==wxObtainNewsTpl.wxhConfigId}">selected="selected"</c:if>>${config.wxhName}</option>
					    </c:foreach>
					    </select>
						<td>
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
				<li>
				<a class="add" href="wxObtainNewsTplMngAction!befSave.action?json.navTabId=${json.navTabId}" target="dialog" navTabId="wxObtainNewsTpl" rel="wxObtainNewsTpl" width="420" height="300" mask="true"><span>添加</span></a>
			</li>
				<li class="line">line</li>
				<li><a class="delete" href="wxObtainNewsTplMngAction!delList.action?json.navTabId=${json.navTabId}" target="selectedTodo" title="确实要删除这些记录吗?"><span>批量删除</span></a></li>
			</ul>
		</div>
		<table class="table" width="100%" layoutH="170">
			<thead>
				<tr>
					<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
						<!-- 生成表单 -->
							<th width="4%" orderField="id" class="${orderField eq 'id' ? orderDirection : ''}">id</th>
							<th orderField="pushKeyword" class="${orderField eq 'pushKeyword' ? orderDirection : ''}">参加关键词</th>
							<th orderField="queryKeyword" class="${orderField eq 'queryKeyword' ? orderDirection : ''}">查询关键词</th>
							<th orderField="endTime" class="${orderField eq 'endTime' ? orderDirection : ''}">活动结束时间</th>
							<th orderField="newsId" class="${orderField eq 'newsId' ? orderDirection : ''}">图文素材名称 </th>
							<th orderField="wxhConfigId" class="${orderField eq 'wxhConfigId' ? orderDirection : ''}">公众号名称</th>
							<th orderField="createTime" class="${orderField eq 'createTime' ? orderDirection : ''}">创建时间</th>
							<th orderField="modifyTime" class="${orderField eq 'modifyTime' ? orderDirection : ''}">修改时间</th>
							<th orderField="modifyUserName" class="${orderField eq 'modifyUserName' ? orderDirection : ''}">修改人名称</th>
					<th width="90">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="page.list" status="stat" id="item">
				<tr>
					<td><input name="ids" value="${item.id}" type="checkbox"></td>
					<!-- 生成表单 -->
								<td>${item.id }</td>
								<td>${item.pushKeyword }</td>
								<td>${item.queryKeyword }</td>
								<td><s:date name="endTime" format="yyyy-MM-dd HH:mm"/></td>
								<td>
						<c:foreach items="${newsMaterials}" var="newsMaterial">
						<c:if test="${newsMaterial.id==item.newsId }">${newsMaterial.name}</c:if>
					    </c:foreach></td>
					    
								<td>
								<c:foreach items="${wxhConfigList}" var="wxhConfig">
						<c:if test="${wxhConfig.id==item.wxhConfigId }">${wxhConfig.wxhName}</c:if>
					    </c:foreach></td>
								<td><s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
								<td><s:date name="modifyTime" format="yyyy-MM-dd HH:mm:ss"/></td>
								<td>${item.modifyUserName }</td>
					<td>
						<a class="btnDel" href="wxObtainNewsTplMngAction!del.action?wxObtainNewsTpl.id=${item.id}&json.navTabId=${json.navTabId}" target="ajaxTodo" title="删除">删除</a>
						<a class="btnEdit" href="wxObtainNewsTplMngAction!befEdit.action?wxObtainNewsTpl.id=${item.id}&json.navTabId=${json.navTabId}" target="dialog" navTabId="wxObtainNewsTpl" rel="wxObtainNewsTpl" title="编辑" width="400" height="300" mask="true">编辑</a>
						<a class="btnView" href="wxObtainNewsTplMngAction!view.action?wxObtainNewsTpl.id=${item.id}" target="dialog" navTabId="wxObtainNewsTpl" rel="wxObtainNewsTpl" title="查看" width="850" height="300">查看</a>
					</td>
				</tr>
				</s:iterator>
				
			</tbody>
		</table>
		<%@ include file="/manager/base/common/panelBar.jsp"%>
	</div>
</div>

