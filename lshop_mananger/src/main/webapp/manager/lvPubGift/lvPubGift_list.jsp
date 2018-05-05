<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<form id="pagerForm" method="post" action="lvPubGiftAction!list.action?json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
	
</form>

<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="lvPubGiftAction!list.action?json.navTabId=${json.navTabId}" method="post" rel="pagerForm">
		<div class="searchBar">
			<table class="searchContent" layoutH="650">
				<tr>
						</tr><tr>
						<td><label>赠品中文名称</label></td><td><input name="lvPubGift.giftName" type="text" size="20" value="${lvPubGift.giftName}"/></td>
						<td><label>赠品英文名称</label></td><td><input name="lvPubGift.giftNameEn" type="text" size="20" value="${lvPubGift.giftNameEn}"/></td>
						<td><label>SAS对接code</label></td><td><input name="lvPubGift.pcode" type="text" size="20" value="${lvPubGift.pcode}"/></td>
						<td><label>状态</label></td><td>
						<select name="lvPubGift.status">
						<option value="">=请选择=</option>
						<option value="1" <c:if test="${lvPubGift.status==1 }">selected="selected"</c:if>>启用</option>
						<option value="0" <c:if test="${lvPubGift.status==0 }">selected="selected"</c:if>>停用</option>
						</select>
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
				<li><a class="add" href="lvPubGiftAction!befSave.action?json.navTabId=${json.navTabId}" target="dialog" navTabId="lvPubGift" rel="lvPubGift" width="600" height="300" mask="true"><span>添加</span></a></li>
				<li class="line">line</li>
				<li><a class="delete" href="lvPubGiftAction!delList.action?json.navTabId=${json.navTabId}" target="selectedTodo" title="确实要删除这些记录吗?"><span>批量删除</span></a></li>
			</ul>
		</div>
		<table class="table" width="100%" layoutH="164">
			<thead>
				<tr>
					<th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
					<!-- 生成表单 -->
					<th width="5%" orderField="id" class="${orderField eq 'id' ? orderDirection : ''}">id</th>
					<th width="15%">赠品中文名称</th>
					<th width="15%">赠品英文名称</th>
					<th width="15%">SAS对接code</th>
					<th width="10%">状态</th>
					<th width="10%">创建时间</th>
					<th width="10%">修改时间</th>
					<th width="20%">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="page.list" status="stat" id="item">
				<tr>
					<td><input name="ids" value="${item.id}" type="checkbox"></td>
					<!-- 生成表单 -->
								<td>${item.id }</td>
								<td>${item.giftName }</td>
								<td>${item.giftNameEn }</td>
								<td>${item.pcode }</td>
								<td>
									<c:if test="${item.status==1 }">启用</c:if>
									<c:if test="${item.status==0 }">停用</c:if>
								</td>
								<td><s:date name="createTime" format="yyyy-MM-dd HH:mm"/></td>
								<td><s:date name="modifyTime" format="yyyy-MM-dd HH:mm"/></td>
					<td>
						<a class="btnView" href="lvPubGiftAction!view.action?lvPubGift.id=${item.id}" target="dialog" navTabId="lvPubGift" rel="lvPubGift" title="查看" width="600" height="450" mask="true">查看</a>
						<a class="btnEdit" href="lvPubGiftAction!befEdit.action?lvPubGift.id=${item.id}&json.navTabId=${json.navTabId}" target="dialog" navTabId="lvPubGift" rel="lvPubGift" title="编辑" width="600" height="300" mask="true">编辑</a>
						<a class="btnDel" href="lvPubGiftAction!del.action?lvPubGift.id=${item.id}&lvPubGift.code=${item.code }&json.navTabId=${json.navTabId}" target="ajaxTodo" title="删除">删除</a>
						<c:if test="${item.status==1}">
					    <a href="lvPubGiftAction!updateStatus.action?lvPubGift.id=${item.id}&lvPubGift.status=0&json.navTabId=${json.navTabId }"  title="确定停用该赠品吗?该操作请谨慎!" target="ajaxTodo">停用</a>
					    </c:if>
					    <c:if test="${item.status==0}">
					    <a href="lvPubGiftAction!updateStatus.action?lvPubGift.id=${item.id}&lvPubGift.status=1&json.navTabId=${json.navTabId }"  title="确定启用该赠品吗?该操作请谨慎!" target="ajaxTodo">启用</a>
					    </c:if>
					</td>
				</tr>
				</s:iterator>
				
			</tbody>
		</table>
		<%@ include file="../common/panelBar.jsp"%>
	</div>
</div>