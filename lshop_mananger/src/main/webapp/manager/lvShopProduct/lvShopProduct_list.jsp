<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<form id="pagerForm" method="post" action="lvShopProductAction!list.action?lvShopProduct.subjectType=${lvShopProduct.subjectType }&lvShopProduct.exhibitType=${lvShopProduct.exhibitType }&lvShopProduct.storeFlag=${lvShopProduct.storeFlag }&json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
	
</form>

<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="lvShopProductAction!list.action?lvShopProduct.subjectType=${lvShopProduct.subjectType }&lvShopProduct.exhibitType=${lvShopProduct.exhibitType }&json.navTabId=${json.navTabId}" method="post" rel="pagerForm">
		<div class="searchBar">
			<table class="searchContent" layoutH="650">
				<tr>
						</tr><tr>
						<td><label>商品名称</label></td><td><input name="lvShopProduct.productName" type="text" size="20" maxlength="50" value="${lvShopProduct.productName}"/></td>
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
				<li><a class="add" href="lvShopProductAction!befSave.action?lvShopProduct.subjectType=${lvShopProduct.subjectType }&lvShopProduct.storeFlag=${lvShopProduct.storeFlag }&json.navTabId=${json.navTabId}" target="dialog"  width="800" height ="500" mask="true"><span>添加</span></a></li>
				<li class="line">line</li>
				<li><a class="delete" href="lvShopProductAction!delList.action?json.navTabId=${json.navTabId}" target="selectedTodo" title="确实要删除这些记录吗?"><span>批量删除</span></a></li>
			</ul>
		</div>
		<table class="table" width="100%" layoutH="170">
			<thead>
				<tr>
					<th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
						<!-- 生成表单 -->
							<th width="4%" orderField="id" class="${orderField eq 'id' ? orderDirection : ''}">id</th>
							<th width="45%" orderField="productName" class="${orderField eq 'productName' ? orderDirection : ''}">名称</th>
							<th width="10%" orderField="url" class="${orderField eq 'url' ? orderDirection : ''}">图片</th>
							<th width="10%" orderField="orderValue" class="${orderField eq 'orderValue' ? orderDirection : ''}">排序值</th>
							<th width="10%" orderField="createTime" class="${orderField eq 'createTime' ? orderDirection : ''}">创建时间</th>
							<th width="10%" orderField="modifyTime" class="${orderField eq 'modifyTime' ? orderDirection : ''}">修改时间</th>
					        <th width="10%">操作</th>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="page.list" status="stat" id="item">
				<tr>
					<td><input name="ids" value="${item.id}" type="checkbox"></td>
					<!-- 生成表单 -->
								<td>${item.id }</td>
								<td>
								<s:property escapeHtml="true" value="#item.productName"/>
								</td>
								<td>
								<c:if test="${lvShopProduct.exhibitType==2}">
								<c:if test="${item.pimage!=null}">
						             <a href="lvShopProductAction!showPic.action?imgSrc=${item.pimage}&json.navTabId=${json.navTabId}" target="dialog" mask="true" title="浏览图片" width="800" height="600"><img src="${item.pimage }" width="20px" height="20px"/></a>
					            </c:if>
					            </c:if>
					            <c:if test="${lvShopProduct.exhibitType==1}">
								<c:if test="${item.pimageAd!=null}">
						             <a href="lvShopProductAction!showPic.action?imgSrc=${item.pimageAd}&json.navTabId=${json.navTabId}" target="dialog" mask="true" title="浏览图片" width="800" height="600"><img src="${item.pimageAd }" width="20px" height="20px"/></a>
					            </c:if>
					            </c:if>
								</td>
								<td>${item.orderValue }</td>
								<td><s:date name="createTime" format="yyyy-MM-dd HH:mm"/></td>
								<td><s:date name="modifyTime" format="yyyy-MM-dd HH:mm"/></td>
					<td>
						<a class="btnDel" href="lvShopProductAction!del.action?lvShopProduct.id=${item.id}&json.navTabId=${json.navTabId}" target="ajaxTodo" title="删除">删除</a>
						<a class="btnEdit" href="lvShopProductAction!befEdit.action?lvShopProduct.id=${item.id}&json.navTabId=${json.navTabId}" target="dialog" navTabId="lvShopProduct" rel="lvShopProduct" title="编辑" width="500" height="400" mask="true">编辑</a>
					</td>
				</tr>
				</s:iterator>
				
			</tbody>
		</table>
		<%@ include file="../common/panelBar.jsp"%>
	</div>
</div>