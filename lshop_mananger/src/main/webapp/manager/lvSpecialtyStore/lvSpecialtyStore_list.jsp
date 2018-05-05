<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<form id="pagerForm" method="post" action="lvSpecialtyStoreAction!list.action?json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
	
</form>

<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="lvSpecialtyStoreAction!list.action?json.navTabId=${json.navTabId}" method="post" rel="pagerForm">
		<div class="searchBar">
			<table class="searchContent" layoutH="650">
				<tr>
						</tr><tr>
						<td><label>专卖店名称</label></td><td>
							<select name="lvSpecialtyStore.parentCode">
							<option value="">==请选择==</option>
							<c:foreach items="${ptypeList}" var="temp">
							<option value="${temp.code }" <c:if test="${temp.code==lvSpecialtyStore.parentCode }">selected="selected"</c:if>>${temp.storeTypeName }</option>
							</c:foreach>
							</select>
						</td>
						<td><label>店铺名称</label></td><td><input name="lvSpecialtyStore.storeName" type="text" size="20" maxlength="50" value="${lvSpecialtyStore.storeName}"/></td>
						<td><label>分类名称</label></td><td>
							<select name="lvSpecialtyStore.storeTypeCode">
							<option value="">==请选择==</option>
							<c:foreach items="${typeList}" var="temp">
							<option value="${temp.code }" <c:if test="${temp.code==lvSpecialtyStore.storeTypeCode }">selected="selected"</c:if>>${temp.storeTypeName }</option>
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
				<li><a class="add" href="lvSpecialtyStoreAction!befSave.action?json.navTabId=${json.navTabId}" target="dialog" navTabId="lvSpecialtyStore" rel="lvSpecialtyStore" width="500" height="350" mask="true"><span>添加</span></a></li>
				<li class="line">line</li>
				<li><a class="delete" href="lvSpecialtyStoreAction!delList.action?json.navTabId=${json.navTabId}" target="selectedTodo" title="确实要删除这些记录吗?"><span>批量删除</span></a></li>
			</ul>
		</div>
		<table class="table" width="100%" layoutH="170">
			<thead>
				<tr>
					<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
						<!-- 生成表单 -->
							<th orderField="id" class="${orderField eq 'id' ? orderDirection : ''}">id</th>
							<th orderField="storeTypeCode" class="${orderField eq 'storeTypeCode' ? orderDirection : ''}">专卖店</th>
							<th orderField="storeTypeCode" class="${orderField eq 'storeTypeCode' ? orderDirection : ''}">分类名称</th>
							<th orderField="storeName" class="${orderField eq 'storeName' ? orderDirection : ''}">店铺名称</th>
							<th orderField="storeUrl" class="${orderField eq 'storeUrl' ? orderDirection : ''}">店铺URL</th>
							<th orderField="storeLogo" class="${orderField eq 'storeLogo' ? orderDirection : ''}">LOGO图片</th>
							<th orderField="orderValue" class="${orderField eq 'orderValue' ? orderDirection : ''}">排序值</th>
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
								<td>
								<c:foreach items="${ptypeList}" var="temp">
								<c:if test="${temp.code==item.parentCode }">${temp.storeTypeName }</c:if>
								</c:foreach>
								</td>
								<td>
								<c:foreach items="${typeList}" var="temp">
							    <c:if test="${temp.code==item.storeTypeCode }">${temp.storeTypeName }</c:if>
							    </c:foreach>
							    </td>
								<td>
								<s:property escapeHtml="true" value="#item.storeName"/>
								</td>
								<td>${item.storeUrl }</td>
								<td>
								<c:if test="${item.storeLogo!=null}">
						             <a href="lvProductAction!showPic.action?imgSrc=${item.storeLogo}&json.navTabId=${json.navTabId}" target="dialog" mask="true" title="浏览图片" width="800" height="600"><img src="${item.storeLogo}" width="20px" height="20px"/></a>
					            </c:if>
								</td>
								<td>${item.orderValue }</td>
								<td><s:date name="createTime" format="yyyy-MM-dd HH:mm"/></td>
								<td><s:date name="modifyTime" format="yyyy-MM-dd HH:mm"/></td>
					<td>
						<a class="btnDel" href="lvSpecialtyStoreAction!del.action?lvSpecialtyStore.id=${item.id}&json.navTabId=${json.navTabId}" target="ajaxTodo" title="删除">删除</a>
						<a class="btnEdit" href="lvSpecialtyStoreAction!befEdit.action?lvSpecialtyStore.id=${item.id}&json.navTabId=${json.navTabId}" target="dialog" navTabId="lvSpecialtyStore" rel="lvSpecialtyStore" title="编辑" width="500" height="400" mask="true">编辑</a>
					</td>
				</tr>
				</s:iterator>
				
			</tbody>
		</table>
		<%@ include file="../common/panelBar.jsp"%>
	</div>
</div>