<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<form id="pagerForm" method="post" action="wxGzhConfigMngAction!list.action?json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
	
</form>

<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="wxGzhConfigMngAction!list.action?json.navTabId=${json.navTabId}" method="post" rel="pagerForm">
		<div class="searchBar">
			<table class="searchContent" layoutH="750">
                        <tr>
						<td><label>公众号名称</label></td><td><input name="wxGzhConfig.wxhName" type="text" size="20" value="${wxGzhConfig.wxhName}"/></td>
						<td><label>URL(服务器地址)</label></td><td><input name="wxGzhConfig.preUrl" type="text" size="20" value="${wxGzhConfig.preUrl}"/></td>
						<td><label>应用ID</label></td><td><input name="wxGzhConfig.appId" type="text" size="20" value="${wxGzhConfig.appId}"/></td>
						</tr>
						<tr>
						<td><label>应用密钥</label></td><td><input name="wxGzhConfig.appSecret" type="text" size="20" value="${wxGzhConfig.appSecret}"/></td>
						<td><label>access_token凭证</label></td><td><input name="wxGzhConfig.accessToken" type="text" size="20" value="${wxGzhConfig.accessToken}"/></td>
						<td><label>修改人名称</label></td><td><input name="wxGzhConfig.modifyUserName" type="text" size="20" value="${wxGzhConfig.modifyUserName}"/></td>
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
				<li><a class="add" href="wxGzhConfigMngAction!befSave.action?json.navTabId=${json.navTabId}" target="dialog" navTabId="wxGzhConfig" rel="wxGzhConfig" width="420" height="320" mask="true"><span>添加</span></a></li>
			</ul>
		</div>
		<table class="table" width="100%" layoutH="170">
			<thead>
				<tr>
					<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
						<!-- 生成表单 -->
							<th width="4%" orderField="id" class="${orderField eq 'id' ? orderDirection : ''}">id</th>
							<th orderField="storeId" class="${orderField eq 'storeId' ? orderDirection : ''}">所属商城</th>
							<th orderField="wxhName" class="${orderField eq 'wxhName' ? orderDirection : ''}">公众号名称</th>
							<th orderField="preUrl" class="${orderField eq 'preUrl' ? orderDirection : ''}">URL(服务器地址)</th>
							<th orderField="appId" class="${orderField eq 'appId' ? orderDirection : ''}">应用ID</th>
							<th orderField="accessTokenExpires" class="${orderField eq 'accessTokenExpires' ? orderDirection : ''}">失效时间(秒)</th>
							<th orderField="accessTokenTime" class="${orderField eq 'accessTokenTime' ? orderDirection : ''}">凭证获取时间</th>
							<th orderField="modifyUserName" class="${orderField eq 'modifyUserName' ? orderDirection : ''}">修改人名称</th>
					<th width="90">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="page.list" status="stat" id="item">
					<td><input name="ids" value="${item.id}" type="checkbox"></td>
					<!-- 生成表单 -->
								<td>${item.id }</td>
								<td>
								 <c:foreach items="${storeList}" var="store">
							    <c:if test="${item.storeId==store.storeFlag }">${store.name }</c:if>
							   </c:foreach>
</td>
								<td>${item.wxhName }</td>
								<td>${item.preUrl }</td>
								<td>${item.appId }</td>
								<td>${item.accessTokenExpires }</td>
								<td><s:date name="accessTokenTime" format="yyyy-MM-dd HH:mm:ss"/></td>
								<td>${item.modifyUserName }</td>
					<td>
						<a class="btnDel" href="wxGzhConfigMngAction!del.action?wxGzhConfig.id=${item.id}&json.navTabId=${json.navTabId}" target="ajaxTodo" title="删除">删除</a>
						<a class="btnEdit" href="wxGzhConfigMngAction!befEdit.action?wxGzhConfig.id=${item.id}&json.navTabId=${json.navTabId}" target="dialog" navTabId="wxGzhConfig" rel="wxGzhConfig" title="编辑" width="800" height="350" mask="true">编辑</a>
						<a class="btnView" href="wxGzhConfigMngAction!view.action?wxGzhConfig.id=${item.id}" target="dialog" navTabId="wxGzhConfig" rel="wxGzhConfig" title="查看" width="850" height="400">查看</a>
					</td>
				</tr>
				</s:iterator>
				
			</tbody>
		</table>
		<%@ include file="/manager/base/common/panelBar.jsp"%>
	</div>
</div>