<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<form id="pagerForm" method="post" action="wxMenuMngAction!list.action?json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
	
</form>

<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="wxMenuMngAction!list.action?json.navTabId=${json.navTabId}" method="post" rel="pagerForm">
		<div class="searchBar">
			<table class="searchContent" layoutH="750">
<tr>
						<td><label>菜单名称</label></td><td><input name="wxMenu.name" type="text" size="20" value="${wxMenu.name}"/></td>
						<td><label>菜单类型：</label></td><td>
						<select name="wxMenu.menuType">
						<option value="">
						==请选择==
						</option>
						<option value="click" <c:if test="${wxMenu.menuType=='click' }">selected="selected"</c:if>>
					发送信息
						</option>
						<option value="view" <c:if test="${wxMenu.menuType=='view' }">selected="selected"</c:if>>
					跳转到网页
						</option>
						</select>
						</td>
						<td><label>公众号名称</label></td><td>
						<select name="wxMenu.wxhConfigId">
						<option value="">==请选择==</option>
						<c:foreach items="${wxhConfigList}" var="wxhConfig">
						<option  value="${wxhConfig.id }" <c:if test="${wxhConfig.id==wxMenu.wxhConfigId}">selected="selected"</c:if>>${wxhConfig.wxhName}</option>
					    </c:foreach>
					    </select>
						
						</td>
						</tr>
						<tr>
						<td><label>URL地址</label></td><td><input name="wxMenu.menuUrl" type="text" size="20" value="${wxMenu.menuUrl}"/></td>
						<td><label>素材类型：</label></td><td>
							<select name="wxMenu.materialType">
						<option value="">
						==请选择==
						</option>
						<option value="1" <c:if test="${wxMenu.materialType==1 }">selected="selected"</c:if>>
					  文本
						</option>
						<option value="6" <c:if test="${wxMenu.materialType==2 }">selected="selected"</c:if>>
					  图文
						</option>
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
				<a class="add" href="wxMenuMngAction!befSave.action?json.navTabId=${json.navTabId}" target="dialog" navTabId="wxMenu" rel="wxMenu" width="420" height="400" mask="true"><span>添加</span>
					
		</li>
		
				<li class="line">line</li>
				<li><a class="delete" href="wxMenuMngAction!delList.action?json.navTabId=${json.navTabId}" target="selectedTodo" title="确实要删除这些记录吗?"><span>批量删除</span></a></li>
			<li class="line">line</li>
		<li><a class="add" href="wxMenuMngAction!toDefineMenusToWeixin.action?json.navTabId=${json.navTabId}" target="dialog" navTabId="wxMenu" rel="wxMenu" width="400" height="200" mask="true"><span>推送菜单到公众号</span></a>
		</li></ul>
		</div>
		<table class="table" width="100%" layoutH="170">
			<thead>
				<tr>
					<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
						<!-- 生成表单 -->
							<th width="4%" orderField="id" class="${orderField eq 'id' ? orderDirection : ''}">id</th>
							<th orderField="name" class="${orderField eq 'name' ? orderDirection : ''}">菜单名称</th>
							<th orderField="orderValue" class="${orderField eq 'orderValue' ? orderDirection : ''}">排序值</th>
							<th orderField="menuType" class="${orderField eq 'menuType' ? orderDirection : ''}">菜单类型</th>
							<th orderField="menuUrl" class="${orderField eq 'menuUrl' ? orderDirection : ''}">URL地址</th>
							<th orderField="materialType" class="${orderField eq 'materialType' ? orderDirection : ''}">素材类型</th>
							<th orderField="materialId" class="${orderField eq 'materialId' ? orderDirection : ''}">对应素材名称</th>
							<th orderField="menuParent" class="${orderField eq 'menuParent' ? orderDirection : ''}">父菜单名称</th>
							<th orderField="wxhConfigId" class="${orderField eq 'wxhConfigId' ? orderDirection : ''}">公众号名称</th>
							<th orderField="createTime" class="${orderField eq 'createTime' ? orderDirection : ''}">创建时间</th>
					<th width="90">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="page.list" status="stat" id="item">
				<tr>
					<td><input name="ids" value="${item.id}" type="checkbox"></td>
					<!-- 生成表单 -->
								<td>${item.id }</td>
								<td>${item.name }</td>
								<td>${item.orderValue }</td>
								<td>	<c:if test="${item.menuType == 'click'}">
								发送信息
								</c:if>
								<c:if test="${item.menuType =='view'}">
								跳转到网页
								</c:if></td>
								<td>${item.menuUrl }</td>
								<td>
								<c:if test="${item.materialType == 1}">
								文本
								</c:if>
								<c:if test="${item.materialType == 6}">
								图文
								</c:if>
								</td>
								<td><c:choose>
								<c:when test="${item.materialType == 1 }">
							<c:foreach items="${textMaterials}" var="textMaterial">
						   <c:if test="${textMaterial.id == item.materialId}">${textMaterial.name}</c:if>
					    </c:foreach>
								</c:when>
								
								<c:when test="${item.materialType == 6 }">
								<c:foreach items="${newsMaterials}" var="newsMaterial">
						<c:if test="${newsMaterial.id == item.materialId  }">${newsMaterial.name}</c:if>
					    </c:foreach>
								</c:when>
								</c:choose></td>
								<td>
								<c:if test="${item.menuParent == 0 }">无父菜单</c:if>
								<c:foreach items="${menus}" var="menu">
							<c:if test="${menu.id == item.menuParent}">${menu.name}</c:if>
						</c:foreach>
								
								</td>
								<td><c:foreach items="${wxhConfigList}" var="wxhConfig">
							<c:if test="${wxhConfig.id == item.wxhConfigId}">${wxhConfig.wxhName}</c:if>
						</c:foreach></td>
								<td><s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
					<td>
						<a class="btnDel" href="wxMenuMngAction!del.action?wxMenu.id=${item.id}&json.navTabId=${json.navTabId}" target="ajaxTodo" title="删除">删除</a>
						<a class="btnEdit" href="wxMenuMngAction!befEdit.action?wxMenu.id=${item.id}&json.navTabId=${json.navTabId}" target="dialog" navTabId="wxMenu" rel="wxMenu" title="编辑" width="400" height="380" mask="true">编辑</a>
						<a class="btnView" href="wxMenuMngAction!view.action?wxMenu.id=${item.id}" target="dialog" navTabId="wxMenu" rel="wxMenu" title="查看" width="430" height="450">查看</a>
					</td>
				</tr>
				</s:iterator>
				
			</tbody>
		</table>
		<%@ include file="/manager/base/common/panelBar.jsp"%>
	</div>
</div>