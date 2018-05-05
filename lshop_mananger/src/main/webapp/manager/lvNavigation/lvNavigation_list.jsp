<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<form id="pagerForm" method="post" action="lvNavigationMngAction!list.action?json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
	
</form>

<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="lvNavigationMngAction!list.action?json.navTabId=${json.navTabId}" method="post" rel="pagerForm">
		<div class="searchBar">
			<table class="searchContent" layoutH="750">
				<tr>
						</tr><tr>
						<td><label>导航名称</label></td><td><input name="lvNavigation.navName" type="text" size="20" maxlength="20" value="${lvNavigation.navName}"/></td>
					<td><label>所属关系</label></td>
						<td>
						<select name="lvNavigation.storeId">
						<option value="">==请选择==</option>
						<c:foreach items="${storeList}" var="store">
						<option  value="${store.storeFlag }" <c:if test="${store.storeFlag==lvNavigation.storeId}">selected="selected"</c:if>>${store.name}</option>
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
				<li><a class="add" href="lvNavigationMngAction!befSave.action?json.navTabId=${json.navTabId}" target="dialog" navTabId="lvNavigation" rel="lvNavigation" width="500" height="350" mask="true"><span>添加</span></a></li>
				<li class="line">line</li>
				<li><a class="delete" href="lvNavigationMngAction!delList.action?json.navTabId=${json.navTabId}" target="selectedTodo" title="确实要删除这些记录吗?"><span>批量删除</span></a></li>
			</ul>
		</div>
		<table class="table" width="100%" layoutH="170">
			<thead>
				<tr>
					<th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
						<!-- 生成表单 -->
							<th width="4%">编号</th>
							<th width="7%">所属关系</th>
							<th width="13%">导航名称</th>
							<th width="25%">导航链接</th>
							<th width="10%">打开方式</th>
							<th width="5%">导航图片</th>
							<th width="5%">排序值</th>
							<th width="10%" orderField="createTime" class="${orderField eq 'createTime' ? orderDirection : ''}">创建时间</th>
					        <th width="10%" orderField="modifyTime" class="${modifyTime eq 'modifyTime' ? orderDirection : ''}" width="200">修改时间</th>
					        <th width="10%">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="page.list" status="stat" id="item">
					<td><input name="ids" value="${item.id}" type="checkbox"></td>
					<!-- 生成表单 -->
								<td>${stat.count }</td>
								<td>
									<c:foreach items="${storeList}" var="store">
										<c:if test="${store.storeFlag==item.storeId}">${store.name}</c:if>
									</c:foreach>
								</td>
								<td>${item.navName }</td>
								<td>${item.navUrl }</td>
								<td><s:if test="#item.openTarget==1">新窗口打开</s:if><s:else>本窗口打开</s:else></td>
								<td>
								<c:if test="${item.navImage!=null}">
						        <a href="lvShopProductAction!showPic.action?imgSrc=${item.navImage}&json.navTabId=${json.navTabId}" target="dialog" mask="true" title="浏览图片" width="800" height="600"><img src="${item.navImage}" width="20px" height="20px"/></a>
					            </c:if>
								</td>
								<td>${item.orderValue}</td>
								<td><s:date name="createTime" format="yyyy-MM-dd HH:mm"/></td>
								<td><s:date name="modifyTime" format="yyyy-MM-dd HH:mm"/></td>
					<td>
						<a class="btnDel" href="lvNavigationMngAction!del.action?lvNavigation.id=${item.id}&json.navTabId=${json.navTabId}" target="ajaxTodo" title="删除">删除</a>
						<a class="btnEdit" href="lvNavigationMngAction!befEdit.action?lvNavigation.id=${item.id}&json.navTabId=${json.navTabId}" target="dialog" navTabId="lvNavigation" rel="lvNavigation" title="编辑" width="500" height="350" mask="true">编辑</a>
						<!-- <a  class="btnLook" href="lvNavigationMngAction!childlist.action?lvNavigation.navParentId=${item.id}&json.navTabId=lvNavigationChild" target="navTab" navTabId="lvNavigationChild" rel="lvNavigationChild" title="子菜单明细管理" width="850" height="500" mask="true">明细管理</a> -->
					</td>
				</tr>
				</s:iterator>
			</tbody>
		</table>
		<%@ include file="../common/panelBar.jsp"%>
	</div>
</div>