<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<form id="pagerForm" method="post" action="wxPassiveReplyMngAction!list.action?json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
	
</form>

<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="wxPassiveReplyMngAction!list.action?json.navTabId=${json.navTabId}" method="post" rel="pagerForm">
		<div class="searchBar">
			<table class="searchContent" layoutH="750">
			
			<tr>
						
					
						<td><label>公众号名称</label></td><td>
						<select name="wxPassiveReply.wxhConfigId">
						<option value="">==请选择==</option>
						<c:foreach items="${wxhConfigList}" var="wxhConfig">
						<option  value="${wxhConfig.id }" <c:if test="${wxhConfig.id==wxPassiveReply.wxhConfigId}">selected="selected"</c:if>>${wxhConfig.wxhName}</option>
					    </c:foreach>
					    </select>
						</td>

					
						<td><label>素材类型：</label></td><td>
						<select name="wxPassiveReply.materialType">
						<option value="">
						==请选择==
						</option>
						<option value="1" <c:if test="${wxPassiveReply.materialType==1 }">selected="selected"</c:if>>
					  文本
						</option>
						<option value="6" <c:if test="${wxPassiveReply.materialType==6 }">selected="selected"</c:if>>
					  图文
						</option>
						</select>
						</td>
							<td><label>描述</label></td><td><input name="wxPassiveReply.description" type="text" size="20" value="${wxPassiveReply.description}"/></td>
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
				<a class="add" href="wxPassiveReplyMngAction!befSave.action?json.navTabId=${json.navTabId}" target="dialog" navTabId="wxPassiveReply" rel="wxPassiveReply" width="400" height="300" mask="true"><span>添加</span>
				</li>
				<li class="line">line</li>
				<li><a class="delete" href="wxPassiveReplyMngAction!delList.action?json.navTabId=${json.navTabId}" target="selectedTodo" title="确实要删除这些记录吗?"><span>批量删除</span></a></li>
			</ul>
		</div>
		<table class="table" width="100%" layoutH="170">
			<thead>
				<tr>
					<th width="22"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
						<!-- 生成表单 -->
							<th width="4%" orderField="id" class="${orderField eq 'id' ? orderDirection : ''}">id</th>
														<th orderField="wxhConfigId" class="${orderField eq 'wxhConfigId' ? orderDirection : ''}">公众号名称</th>


							<th orderField="materialId" class="${orderField eq 'materialId' ? orderDirection : ''}">素材名称</th>
														<th orderField="materialType" class="${orderField eq 'materialType' ? orderDirection : ''}">素材类型</th>
														<th orderField="description" class="${orderField eq 'description' ? orderDirection : ''}">描述</th>

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
								<td><c:foreach items="${wxhConfigList}" var="wxhConfig">
							<c:if test="${wxhConfig.id == item.wxhConfigId}">${wxhConfig.wxhName}</c:if>
						</c:foreach></td>
								<td>
								<c:choose>
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
								</c:choose>
								
					    
								</td>
								<td>
								<c:if test="${item.materialType == 1}">
								文本
								</c:if>
								<c:if test="${item.materialType == 6}">
								图文
								</c:if>
								</td>
								
								<td>${item.description }</td>
								
								<td><s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
								<td><s:date name="modifyTime" format="yyyy-MM-dd HH:mm:ss"/></td>
					<td>
						<a class="btnDel" href="wxPassiveReplyMngAction!del.action?wxPassiveReply.id=${item.id}&json.navTabId=${json.navTabId}" target="ajaxTodo" title="删除">删除</a>
						<a class="btnEdit" href="wxPassiveReplyMngAction!befEdit.action?wxPassiveReply.id=${item.id}&json.navTabId=${json.navTabId}" target="dialog" navTabId="wxPassiveReply" rel="wxPassiveReply" title="编辑" width="400" height="300" mask="true">编辑</a>
						<a class="btnView" href="wxPassiveReplyMngAction!view.action?wxPassiveReply.id=${item.id}" target="dialog" navTabId="wxPassiveReply" rel="wxPassiveReply" title="查看" width="450" height="400">查看</a>
					</td>
				</tr>
				</s:iterator>
				
			</tbody>
		</table>
		<%@ include file="/manager/base/common/panelBar.jsp"%>
	</div>
</div>