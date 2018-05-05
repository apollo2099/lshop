<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<form id="pagerForm" method="post" action="lvShopPartnerAction!list.action?json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
	
</form>

<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="lvShopPartnerAction!list.action?json.navTabId=${json.navTabId}" method="post" rel="pagerForm">
		<div class="searchBar">
			<table class="searchContent" layoutH="650">
				<tr>
						</tr><tr>
						<td><label>商家名称</label></td><td><input name="lvShopPartner.shopName" type="text" size="20" maxlength="20" value="${lvShopPartner.shopName}"/></td>
						<td><label>所属商城：</label></td><td>
						<select name="lvShopPartner.storeId">
						<option value="">==请选择==</option>
						<c:foreach items="${shopList}" var="shop">
						<option <c:if test="${lvShopPartner.storeId==shop.storeFlag}">selected="selected"</c:if> value="${shop.storeFlag }">${shop.name }</option>
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
				<li><a class="add" href="lvShopPartnerAction!befSave.action?json.navTabId=${json.navTabId}" target="dialog" navTabId="lvShopPartner" rel="lvShopPartner" width="500" height="350" mask="true"><span>添加</span></a></li>
				<li class="line">line</li>
				<li><a class="delete" href="lvShopPartnerAction!delList.action?json.navTabId=${json.navTabId}" target="selectedTodo" title="确实要删除这些记录吗?"><span>批量删除</span></a></li>
			</ul>
		</div>
		<table class="table" width="100%" layoutH="170">
			<thead>
				<tr>
					        <th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
						    <!-- 生成表单 -->
							<th width="4%" orderField="id" class="${orderField eq 'id' ? orderDirection : ''}">id</th>
							<th width="15%" orderField="shopName" class="${orderField eq 'shopName' ? orderDirection : ''}">商家名称</th>
							<th width="20%" orderField="shopUrl" class="${orderField eq 'shopUrl' ? orderDirection : ''}">商家URL</th>
							<th width="5%" orderField="exhibitType" class="${orderField eq 'exhibitType' ? orderDirection : ''}">展示形式</th>
							<th width="5%" orderField="shopLogo" class="${orderField eq 'shopLogo' ? orderDirection : ''}">LOGO图片</th>
							<th width="5%" orderField="orderValue" class="${orderField eq 'orderValue' ? orderDirection : ''}">排序值</th>
							<th width="10%">所属商城</th>
							<th width="10%" orderField="createTime" class="${orderField eq 'createTime' ? orderDirection : ''}">创建时间</th>
							<th width="10%" orderField="modifyTime" class="${orderField eq 'modifyTime' ? orderDirection : ''}">修改时间</th>
					        <th width="15%">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="page.list" status="stat" id="item">
				<tr>
					<td><input name="ids" value="${item.id}" type="checkbox"></td>
					<!-- 生成表单 -->
								<td>${item.id }</td>
								<td>
								<s:property escapeHtml="true" value="#item.shopName"/>
								</td>
								<td>${item.shopUrl }</td>
								<td>
								<c:if test="${item.exhibitType==1 }">图片</c:if>
								<c:if test="${item.exhibitType==2 }">文字</c:if>
								</td>
								<td>
									<c:if test="${item.shopLogo!=null}">
						             <a href="lvShopProductAction!showPic.action?imgSrc=${item.shopLogo}&json.navTabId=${json.navTabId}" target="dialog" mask="true" title="浏览图片" width="800" height="600"><img src="${item.shopLogo}" width="20px" height="20px"/></a>
					                </c:if>
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
						<a class="btnDel" href="lvShopPartnerAction!del.action?lvShopPartner.id=${item.id}&json.navTabId=${json.navTabId}" target="ajaxTodo" title="删除">删除</a>
						<a class="btnEdit" href="lvShopPartnerAction!befEdit.action?lvShopPartner.id=${item.id}&lvShopPartner.storeId=${item.storeId }&json.navTabId=${json.navTabId}" target="dialog" navTabId="lvShopPartner" rel="lvShopPartner" title="编辑" width="500" height="350" mask="true">编辑</a>
					</td>
				</tr>
				</s:iterator>
				
			</tbody>
		</table>
		<%@ include file="../common/panelBar.jsp"%>
	</div>
</div>