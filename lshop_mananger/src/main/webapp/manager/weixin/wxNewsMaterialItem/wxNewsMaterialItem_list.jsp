<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<form id="pagerForm" method="post"  action="wxNewsMaterialItemMngAction!list.action?json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
	<input type="hidden" name="wxNewsMaterialItem.newsId" value="${wxNewsMaterialItem.newsId }" />
	
</form>

<div class="page">
<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="wxNewsMaterialItemMngAction!list.action?wxNewsMaterialItem.newsId=${wxNewsMaterialItem.newsId}" method="post" >
		<div class="searchBar">
			<table class="searchContent" layoutH="650">
			<tr>
			
			<td><label>标题</label></td><td><input name="wxNewsMaterialItem.title" type="text" size="20" value="${wxNewsMaterialItem.title}"/></td>
			<td><label>描述</label></td><td><input name="wxNewsMaterialItem.description" type="text" size="20" value="${wxNewsMaterialItem.description}"/></td>
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
			<c:choose>
			<c:when test="${newsMaterial.newsType == 1 && not empty page.list }">
			<li><a class="delete" href="wxNewsMaterialItemMngAction!delList.action?json.navTabId=${json.navTabId}" target="selectedTodo" title="确实要删除这些记录吗?"><span>批量删除</span></a></li>
			</c:when>
			<c:otherwise>
			<li><a class="add" href="wxNewsMaterialItemMngAction!befSave.action?json.navTabId=${json.navTabId}&wxNewsMaterialItem.newsId=${newsMaterial.id}&wxNewsMaterialItem.newsType=${newsMaterial.newsType}&wxNewsMaterialItem.wxhConfigId=${newsMaterial.wxhConfigId}" target="dialog" width="450" height="450" mask="true"><span>添加</span></a></li>
				<li class="line">line</li>
				<li><a class="delete" href="wxNewsMaterialItemMngAction!delList.action?json.navTabId=${json.navTabId}" target="selectedTodo" title="确实要删除这些记录吗?"><span>批量删除</span></a></li>
			</c:otherwise>
			</c:choose>
				
			</ul>
		</div>
		<table class="table" width="100%" layoutH="170">
			<thead>
				<tr>
					<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
						<!-- 生成表单 -->
							<th width="4%" orderField="id" class="${orderField eq 'id' ? orderDirection : ''}">id</th>
							<th orderField="newsType" class="${orderField eq 'newsType' ? orderDirection : ''}">图文消息类型</th>
							<th orderField="newsId" class="${orderField eq 'newsId' ? orderDirection : ''}">图文素材名称</th>
							<th orderField="title" class="${orderField eq 'title' ? orderDirection : ''}" width="200px">标题</th>
							<th orderField="description" class="${orderField eq 'description' ? orderDirection : ''}" width="300px">描述</th>
							<th orderField="wxhConfigId" class="${orderField eq 'wxhConfigId' ? orderDirection : ''}">公众号名称</th>
							<th orderField="picurl" class="${orderField eq 'picurl' ? orderDirection : ''}">图片链接</th>
							<th orderField="createTime" class="${orderField eq 'createTime' ? orderDirection : ''}">创建时间</th>
							<th orderField="modifyTime" class="${orderField eq 'modifyTime' ? orderDirection : ''}">修改时间</th>
					<th width="90">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="page.list" status="stat" id="item">
				<tr>
					<td><input name="ids" value="${item.id}" type="checkbox"></td>
					<!-- 生成表单 -->
								<td>${item.id }</td>
								<td>	<c:if test="${item.newsType == 1}">
								单图文
								</c:if>
								<c:if test="${item.newsType == 2}">
								多图文
								</c:if></td>
								<td>${newsMaterial.name }</td>
								<td>${item.title }</td>
								<td>${item.description }</td>
								<td>
								<c:foreach items="${wxhConfigList}" var="wxhConfig">
							<c:if test="${wxhConfig.id==item.wxhConfigId}">${wxhConfig.wxhName}</c:if>
						</c:foreach></td>
						<td>
						<c:if test="${item.picurl!=null}">
						<a href="wxNewsMaterialItemMngAction!showPic.action?imgFileName=${item.picurl}&json.navTabId=${json.navTabId}" target="dialog" mask="true" title="浏览图片" width="800" height="600"><img src="${item.picurl}" width="20px" height="20px"/></a>
					</c:if>
					</td>
								<td><s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
								<td><s:date name="modifyTime" format="yyyy-MM-dd HH:mm:ss"/></td>
					<td>
						<a class="btnDel" href="wxNewsMaterialItemMngAction!del.action?wxNewsMaterialItem.id=${item.id}&json.navTabId=${json.navTabId}" target="ajaxTodo" title="删除">删除</a>
						<a class="btnEdit" href="wxNewsMaterialItemMngAction!befEdit.action?wxNewsMaterialItem.id=${item.id}&json.navTabId=${json.navTabId}" target="dialog" navTabId="wxNewsMaterialItem" rel="wxNewsMaterialItem" title="编辑" width="450" height="450" mask="true">编辑</a>
						<a class="btnView" href="wxNewsMaterialItemMngAction!view.action?wxNewsMaterialItem.id=${item.id}" target="dialog" navTabId="wxNewsMaterialItem" rel="wxNewsMaterialItem" title="查看" width="450" height="650">查看</a>
					</td>
				</tr>
				</s:iterator>
				
			</tbody>
		</table>
		<%@ include file="/manager/base/common/panelBar.jsp"%>
	</div>
</div>