<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<form id="pagerForm" method="post" action="wxNewsMaterialMngAction!list.action?json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
	
</form>

<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="wxNewsMaterialMngAction!list.action?json.navTabId=${json.navTabId}" method="post" rel="pagerForm">
		<div class="searchBar">
			<table class="searchContent" layoutH="750">
				<tr>
				</tr>
				<tr>
						<td><label>公众号名称</label></td>
						<td>
						<select name="wxNewsMaterial.wxhConfigId">
						<option value="">==请选择==</option>
						<c:foreach items="${wxhConfigList}" var="wxhConfig">
						<option  value="${wxhConfig.id }" <c:if test="${wxhConfig.id==wxNewsMaterial.wxhConfigId}">selected="selected"</c:if>>${wxhConfig.wxhName}</option>
					    </c:foreach>
					    </select>
					    </td>
					    <td><label>素材名称</label></td><td><input name="wxNewsMaterial.name" type="text" size="20" value="${wxNewsMaterial.name}"/></td>
				</tr>				
				<tr>
						<td><label>图文消息类型</label></td><td>
						<select name="wxNewsMaterial.newsType">
				          <option value="">==请选择==</option>
				          <option value="1" <c:if test="${wxNewsMaterial.newsType==1 }">selected="selected"</c:if>>单图文</option>
				          <option value="2" <c:if test="${wxNewsMaterial.newsType==2 }">selected="selected"</c:if>>多图文</option>
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
				<li><a class="add" href="wxNewsMaterialMngAction!befSave.action?json.navTabId=${json.navTabId}" target="dialog" navTabId="wxNewsMaterial" rel="wxNewsMaterial" width="400" height="250" mask="true"><span>添加</span></a></li>
				<li class="line">line</li>
				<li><a class="delete" href="wxNewsMaterialMngAction!delList.action?json.navTabId=${json.navTabId}" target="selectedTodo" title="确实要删除这些记录吗?"><span>批量删除</span></a></li>
			</ul>
		</div>
		<table class="table" width="100%" layoutH="170">
			<thead>
				<tr>
					<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
						<!-- 生成表单 -->
							<th width="4%" orderField="id" class="${orderField eq 'id' ? orderDirection : ''}">id</th>
							<th orderField="name" class="${orderField eq 'name' ? orderDirection : ''}">素材名称</th>
							<th orderField="newsType" class="${orderField eq 'newsType' ? orderDirection : ''}">图文消息类型</th>
							<th orderField="wxhConfigId" class="${orderField eq 'wxhConfigId' ? orderDirection : ''}">公众号名称</th>
							<th orderField="createTime" class="${orderField eq 'createTime' ? orderDirection : ''}">创建时间</th>
							<th orderField="modifyTime" class="${orderField eq 'modifyTime' ? orderDirection : ''}">修改时间</th>
					<th width="140">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="page.list" status="stat" id="item">
				<tr>
					<td><input name="ids" value="${item.id}" type="checkbox"></td>
					<!-- 生成表单 -->
								<td>${item.id }</td>
								<td>${item.name }</td>
								<td>
								<c:if test="${item.newsType == 1}">
								单图文
								</c:if>
								<c:if test="${item.newsType == 2}">
								多图文
								</c:if>
								</td>
								<td>
								<c:foreach items="${wxhConfigList}" var="wxhConfig">
							<c:if test="${wxhConfig.id==item.wxhConfigId}">${wxhConfig.wxhName}</c:if>
						</c:foreach>
								</td>
								<td><s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
								<td><s:date name="modifyTime" format="yyyy-MM-dd HH:mm:ss"/></td>
					<td>
					   
						<a class="btnDel" href="wxNewsMaterialMngAction!del.action?wxNewsMaterial.id=${item.id}&json.navTabId=${json.navTabId}" target="ajaxTodo" title="删除">删除</a>
						<a class="btnEdit" href="wxNewsMaterialMngAction!befEdit.action?wxNewsMaterial.id=${item.id}&json.navTabId=${json.navTabId}" target="dialog" navTabId="wxNewsMaterial" rel="wxNewsMaterial" title="编辑" width="450" height="250" mask="true">编辑</a>
						<a class="btnView" href="wxNewsMaterialMngAction!view.action?wxNewsMaterial.id=${item.id}" target="dialog" navTabId="wxNewsMaterial" rel="wxNewsMaterial" title="查看" width="450" height="350">查看</a>
						 <a  href="wxNewsMaterialItemMngAction!list.action?json.navTabId=wxNewsMaterialItem2&wxNewsMaterialItem.newsId=${item.id}" target="navTab"  rel="wxNewsMaterialItem2" title="查看素材子项" width="450" height="250" mask="true">素材详情</a>
					</td>
				</tr>
				</s:iterator>
				
			</tbody>
		</table>
		<%@ include file="/manager/base/common/panelBar.jsp"%>
	</div>
</div>