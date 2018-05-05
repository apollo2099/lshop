<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<form id="pagerForm" method="post" action="lvStoreAreaAction!list.action?json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
	
</form>

<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="lvStoreAreaAction!list.action?json.navTabId=${json.navTabId}" method="post" rel="pagerForm">
		<div class="searchBar">
			<table class="searchContent" layoutH="650">
				<tr>
						</tr><tr>
						<td><label>所属洲</label></td><td>
						<select name="lvStoreArea.code">
	                       <option value="">==请选择==</option>
	                       <c:foreach items="${continentList}" var="area">
						   <option value="${area.code }" <c:if test="${lvStoreArea.code==area.code }">selected="selected"</c:if>>${area.areaName }</option>
						   </c:foreach>
						</select>
						</td>
						<td><label>国家名称</label></td><td><input name="lvStoreArea.areaName" type="text" size="20" value="${lvStoreArea.areaName}"/></td>
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
				<li><a class="add" href="lvStoreAreaAction!befSave.action?json.navTabId=${json.navTabId}" target="dialog" navTabId="lvStoreArea" rel="lvStoreArea" width="500" height="300" mask="true" title="添加国家"><span>添加</span></a></li>
				<li class="line">line</li>
				<li><a class="delete" href="lvStoreAreaAction!delList.action?json.navTabId=${json.navTabId}" target="selectedTodo" title="确实要删除这些记录吗?请谨慎操作!"><span>批量删除</span></a></li>
			</ul>
		</div>
		<table class="table" width="100%" layoutH="170">
			<thead>
				<tr>
					        <th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
						    <!-- 生成表单 -->
							<th width="4%" orderField="id" class="${orderField eq 'id' ? orderDirection : ''}">序号</th>
							<th width="10%">所属商城</th>
							<th width="20%" orderField="id" >所属洲</th>
							<th width="20%" orderField="areaName" class="${orderField eq 'areaId' ? orderDirection : ''}">国家名称</th>
							<th width="10%" orderField="orderValue" class="${orderField eq 'orderValue' ? orderDirection : ''}">排序值</th>
							<th width="10%" orderField="createTime" class="${orderField eq 'createTime' ? orderDirection : ''}">创建时间</th>
							<th width="10%" orderField="modifyTime" class="${orderField eq 'modifyTime' ? orderDirection : ''}">修改时间</th>
					        <th width="15">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="page.list" status="stat" id="item">
				<tr>
					<td><input name="ids" value="${item.id}" type="checkbox"></td>
					<!-- 生成表单 -->
								<td>${item.id }</td>
								<td>
								<c:foreach items="${shopList}" var="shop">
								    <c:if test="${item.storeId == shop.storeFlag}"> ${shop.name }</c:if>
								   </c:foreach>
								</td>
								<td>
								 <c:foreach items="${continentList}" var="area">
								    <c:if test="${item.parentCode==area.code }">${area.areaName }</c:if>
								   </c:foreach>
								</td>
								<td>${item.areaName }</td>
								<td>${item.orderValue }</td>
								<td><s:date name="createTime" format="yyyy-MM-dd HH:mm"/></td>
								<td><s:date name="modifyTime" format="yyyy-MM-dd HH:mm"/></td>
					<td>
						<a class="btnDel" href="lvStoreAreaAction!del.action?lvStoreArea.id=${item.id}&lvStoreArea.parentCode=${item.code }&json.navTabId=${json.navTabId}" target="ajaxTodo" title="你确定要删除国家信息吗？请谨慎操作!">删除</a>
						<a class="btnEdit" href="lvStoreAreaAction!befEdit.action?lvStoreArea.id=${item.id}&json.navTabId=${json.navTabId}" target="dialog" navTabId="lvStoreArea" rel="lvStoreArea" title="编辑国家" width="500" height="300" mask="true">编辑</a>
						<a href="lvStoreAreaAction!cityList.action?lvStoreArea.parentCode=${item.code }&json.navTabId=lvStoreArea_1"  rel="lvStoreArea_1" target="navTab">城市</a>
					</td>
				</tr>
				</s:iterator>
				
			</tbody>
		</table>
		<%@ include file="../common/panelBar.jsp"%>
	</div>
</div>