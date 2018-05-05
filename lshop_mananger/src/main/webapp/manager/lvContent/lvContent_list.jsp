<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<form id="pagerForm" method="post" action="lvContentMngAction!list.action?json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
	
</form>

<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="lvContentMngAction!list.action?json.navTabId=${json.navTabId}" method="post" rel="pagerForm">
		<div class="searchBar">
			<table class="searchContent" layoutH="850">
				<tr>
						</tr><tr>
						<td><label>页面名称</label></td><td><input name="lvContent.pageName" type="text" size="20" value="${lvContent.pageName}"/></td>
						<td><label>所属关系</label></td>
						<td>
						<select name="lvContent.storeFlag">
						<option value="">==请选择==</option>
						<c:foreach items="${storeList}" var="store">
						<option  value="${store.storeFlag }" <c:if test="${store.storeFlag==lvContent.storeFlag}">selected="selected"</c:if>>${store.name}</option>
					    </c:foreach>
					    </select>
						</td>
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
				<li><a class="add" href="lvContentMngAction!befSave.action?json.navTabId=${json.navTabId}" target="navTab" navTabId="lvContent" rel="lvContent" width="850" height="500" mask="true"><span>添加</span></a></li>
				<li class="line">line</li>
				<li><a class="delete" href="lvContentMngAction!delList.action?json.navTabId=${json.navTabId}" target="selectedTodo" title="确实要删除这些记录吗?"><span>批量删除</span></a></li>
			    <li class="line">生成页面</li>
			    <li><a class="delete" href="lvContentMngAction!doBuild.action" target="selectedTodo" title="确定要生成页面吗？"><span>批量生成页面</span></a></li>
			</ul>
		</div>
		<table class="table" width="100%" layoutH="170">
			<thead>
				<tr>
					<th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
						<!-- 生成表单 -->
							<th width="4%" orderField="id" class="${orderField eq 'id' ?orderDirection : ''}" width="50px">编号</th>
							<th width="10%">所属关系</th>
							<th width="25%" orderField="pageName" class="${orderField eq 'pageName' ?orderDirection : ''}">页面名称</th>
							<th width="5%" orderField="isHasContent" class="${orderField eq 'isHasContent' ?orderDirection : ''}">是否有主体内容</th>
							<th width="25%">路径</th>
							<th width="10%" orderField="createTime" class="${orderField eq 'createTime' ?orderDirection : ''}">创建时间</th>
							<th width="10%" orderField=modifyTime class="${orderField eq 'modifyTime' ?orderDirection : ''}">修改时间</th>
					        <th width="10%">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="page.list" status="stat" id="item">
				<tr>
					<td><input name="ids" value="${item.id}" type="checkbox"></td>
					<!-- 生成表单 -->
								<td>${stat.count}</td>
								<td>
								 <c:foreach items="${storeList}" var="store">
					             <c:if test="${store.storeFlag==item.storeFlag}">${store.name}</c:if>
					              </c:foreach>
								</td>
								<td>${item.pageName }</td>
								<td><s:if test="#item.isHasContent==1">有</s:if><s:else>无</s:else></td>
								<td>${item.pagePath }</td>
								<td><s:date name="createTime" format="yyyy-MM-dd HH:mm"/></td>
								<td><s:date name="modifyTime" format="yyyy-MM-dd HH:mm"/></td>
					<td>
						<a class="btnDel" href="lvContentMngAction!del.action?lvContent.id=${item.id}&json.navTabId=${json.navTabId}" target="ajaxTodo" title="删除">删除</a>
						<a class="btnEdit" href="lvContentMngAction!befEdit.action?lvContent.id=${item.id}&lvContent.storeFlag=${item.storeFlag }&json.navTabId=${json.navTabId}" target="navTab" navTabId="lvContent" rel="lvContent" title="编辑" width="850" height="600" mask="true">编辑</a>
					</td>
				</tr>
				</s:iterator>
				
			</tbody>
		</table>
		<%@ include file="../common/panelBar.jsp"%>
	</div>
</div>