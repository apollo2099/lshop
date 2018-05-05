<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<form id="pagerForm" method="post" action="wxUserMngAction!list.action?json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
	
</form>

<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="wxUserMngAction!list.action?json.navTabId=${json.navTabId}" method="post" rel="pagerForm">
		<div class="searchBar">
			<table class="searchContent" layoutH="800">
			<tr>
						<td><label>用户昵称</label></td><td><input name="wxUser.nickname" type="text" size="20" value="${wxUser.nickname}"/></td>
						<td><label>公众号名称</label></td><td>
						<select name="wxUser.wxhConfigId">
						<option value="">==请选择==</option>
						<c:foreach items="${wxhConfigList}" var="wxhConfig">
						<option  value="${wxhConfig.id }" <c:if test="${wxhConfig.id==wxUser.wxhConfigId}">selected="selected"</c:if>>${wxhConfig.wxhName}</option>
					    </c:foreach>
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
			<li>
			<select id="configId" onchange="ajaxInvoke();">
			<option value="">请选择公众号并获取用户</option>
			<c:foreach items="${wxhConfigList}" var="wxhConfig">
			<option  value="${wxhConfig.id }">${wxhConfig.wxhName}</option>
		    </c:foreach>
			</select>
				<li><a class="delete" href="wxUserMngAction!delList.action?json.navTabId=${json.navTabId}" target="selectedTodo" title="确实要删除这些记录吗?"><span>批量删除</span></a></li>
			</ul>
		</div>
		<table class="table" width="100%" layoutH="170">
			<thead>
				<tr>
					<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
						<!-- 生成表单 -->
							<th width="4%" orderField="id" class="${orderField eq 'id' ? orderDirection : ''}">id</th>
							<th orderField="nickname" class="${orderField eq 'nickname' ? orderDirection : ''}">用户昵称</th>
							<th orderField="wxhConfigId" class="${orderField eq 'wxhConfigId' ? orderDirection : ''}">公众号名称</th>
							<th orderField="obtainAmount" class="${orderField eq 'obtainAmount' ? orderDirection : ''}">领取金额</th>
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
								
								<td>${item.nickname }</td>
								<td>
						<c:foreach items="${wxhConfigList}" var="wxhConfig">
					 <c:if test="${wxhConfig.id==item.wxhConfigId}">${wxhConfig.wxhName}</c:if>
					    </c:foreach>
								</td>
								<td>${item.obtainAmount }</td>
								<td><s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
								<td><s:date name="modifyTime" format="yyyy-MM-dd HH:mm:ss"/></td>
					<td>
						<a class="btnDel" href="wxUserMngAction!del.action?wxUser.id=${item.id}&json.navTabId=${json.navTabId}" target="ajaxTodo" title="删除">删除</a>
						<a class="btnView" href="wxUserMngAction!view.action?wxUser.id=${item.id}" target="dialog" navTabId="wxUser" rel="wxUser" title="查看" width="450" height="350">查看</a>
					</td>
				</tr>
				</s:iterator>
				
			</tbody>
		</table>
		<%@ include file="/manager/base/common/panelBar.jsp"%>
	</div>
</div>

<script>
function ajaxInvoke(){
	var value = $('#configId').val();
	var url = 'wxUserMngAction!getWeixinUsersById.action?json.navTabId=${json.navTabId}&wxUser.wxhConfigId='+value;
	ajaxTodo(url);
}

</script>