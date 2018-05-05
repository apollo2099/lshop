<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<form id="pagerForm" method="post" action="lvShopSubjectAction!list.action?json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
	
</form>

<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="lvShopSubjectAction!list.action?json.navTabId=${json.navTabId}" method="post" rel="pagerForm">
		<div class="searchBar">
			<table class="searchContent" layoutH="650">
				<tr>
						</tr><tr>
						<td><label>栏目名称</label></td><td><input name="lvShopSubject.subjectName" type="text" size="20" maxlength="20" value="${lvShopSubject.subjectName}"/></td>
						<td><label>所属商城：</label></td><td>
						<select name="lvShopSubject.storeId">
						<option value="">==请选择==</option>
						<c:foreach items="${shopList}" var="shop">
						<option <c:if test="${lvShopSubject.storeId==shop.storeFlag}">selected="selected"</c:if> value="${shop.storeFlag }">${shop.name }</option>
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
				<li><a class="add" href="lvShopSubjectAction!befSave.action?json.navTabId=${json.navTabId}" target="dialog" navTabId="lvShopSubject" rel="lvShopSubject" width="500" height="350" mask="true"><span>添加</span></a></li>
				<li class="line">line</li>
				<li><a class="delete" href="lvShopSubjectAction!delList.action?json.navTabId=${json.navTabId}" target="selectedTodo" title="确实要删除这些记录吗?"><span>批量删除</span></a></li>
			</ul>
		</div>
		<table class="table" width="100%" layoutH="170">
			<thead>
				<tr>
					        <th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
						    <!-- 生成表单 -->
							<th width="4%" orderField="id" >id</th>
							<th width="25%" orderField="subjectName">栏目名称</th>
							<th width="10%" orderField="exhibitType">展现形式</th>
							<th width="10%" orderField="orderValue" >排序值</th>
							<th width="10%">所属商城</th>
							<th width="10%" orderField="createTime" >创建时间</th>
							<th width="10%" orderField="modifyTime" >修改时间</th>
					        <th width="20%" width="90">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="page.list" status="stat" id="item">
				<tr>
					<td><input name="ids" value="${item.id}" type="checkbox"></td>
					<!-- 生成表单 -->
								<td>${item.id }</td>
								<td>${item.subjectName }</td>
								<td>
								<c:if test="${item.exhibitType==1 }">广告图</c:if>
								<c:if test="${item.exhibitType==2 }">商品信息</c:if>
								</td>
								<td>${item.orderValue }</td>
								<td>
								<c:foreach items="${shopList}" var="shop">
								    <c:if test="${item.storeId == shop.storeFlag}"> ${shop.name }</c:if>
								   </c:foreach>
								</td>
								<td><s:date name="createTime" format="yyyy-MM-dd HH:mm"/></td>
								<td><s:date name="modifyTime" format="yyyy-MM-dd HH:mm"/></td>
					<td>
						<a class="btnEdit" href="lvShopSubjectAction!befEdit.action?lvShopSubject.id=${item.id}&json.navTabId=${json.navTabId}" target="dialog" navTabId="lvShopSubject" rel="lvShopSubject" title="编辑" width="500" height="350" mask="true">编辑</a>
						<a class="btnDel" href="lvShopSubjectAction!del.action?lvShopSubject.id=${item.id}&json.navTabId=${json.navTabId}" target="ajaxTodo" title="删除">删除</a>
						<a href="lvShopProductAction!list.action?lvShopProduct.subjectType=${item.code}&lvShopProduct.exhibitType=${item.exhibitType }&lvShopProduct.storeFlag=${item.storeId }&json.navTabId=lvShopSubject_1"  rel="lvShopSubject_1" target="navTab">商品列表</a>
					</td>
				</tr>
				</s:iterator>
			</tbody>
		</table>
		<%@ include file="../common/panelBar.jsp"%>
	</div>
</div>