<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<jsp:include page="../common/pagerForm.jsp">
	<jsp:param value="lvShopProductAction!befSave.action?lvShopProduct.subjectType=${lvShopProduct.subjectType }&lvShopProduct.storeFlag=${lvShopProduct.storeFlag }&json.navTabId=${json.navTabId}"
		name="pagerFormAction" />
</jsp:include>
<div class="pageHeader" style="border: 1px #B8D0D6 solid">
	<form rel="pagerForm" onsubmit="return dialogSearch(this);"
		action="lvShopProductAction!befSave.action?lvShopProduct.subjectType=${lvShopProduct.subjectType }&lvShopProduct.storeFlag=${lvShopProduct.storeFlag }&json.navTabId=${json.navTabId}" method="post">
		<div class="searchBar">
			<table class="searchContent" layoutH="650">
				<tr>
				</tr><tr>
						</tr><tr>
						<td>商家名称</td><td>
						<select name="lvProduct.storeId">
						<option value="">==请选择==</option>
						<c:foreach items="${storeList}" var="store">
						<option value="${store.storeFlag }" <c:if test="${lvProduct.storeId==store.storeFlag }">selected="selected"</c:if>>${store.name }</option>
						</c:foreach>
						</select>
						</td>
						
						<td>商品分类</td><td>
						<select name="lvProduct.shopProductType">
						<option value="">==请选择==</option>
						<c:foreach items="${productTypList}" var="type">
						<option value="${type.code }" <c:if test="${lvProduct.shopProductType==type.code }">selected="selected"</c:if>>${type.typeName }</option>
						</c:foreach>
						</select>
						</td>
						<td>商品名称</td><td><input name="lvProduct.productName" type="text" size="20" value="${lvProduct.productName}"/></td>
						
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li>
						<div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div>
					</li>
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">
	<form method="post" action="lvShopProductAction!save.action?lvShopProduct.subjectType=${lvShopProduct.subjectType }&lvShopProduct.storeFlag=${lvShopProduct.storeFlag }" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="json.navTabId" value="${json.navTabId }" />
		<div class="panelBar">
			<ul class="toolBar">
			<!-- 
				<li><a class="delete" href="lvShopProductAction!save.action?lvShopProduct.subjectType=${lvShopProduct.subjectType }" target="dwzSelectedTodo" title="确实要选择这些商品记录吗?"><span>选择商品</span></a></li>
				<li class="line">line</li>
				 -->
			</ul>
		</div>
		<div class="pageFormContent" style="padding: 0px">
			<table class="table" width="100%" layoutH="200">
				<thead>
					<tr>
						<th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
						<th width="5%">ID</th>
						<th width="10%">商家名称</th>
						<th width="10%">商品分类</th>
						<th width="60%">商品名称</th>
					</tr>
				</thead>
				<tbody>
					<c:foreach items="${page.list}" var="item">
						<tr>
							<td><input name="ids" value="${item.productCode}" type="checkbox"></td>
							<td>${item.id}</td>
							<td>${item.storeName}</td>
							<td>
							<c:foreach items="${productTypList}" var="type">
						      <c:if test="${type.code==item.shopProductType }">${type.typeName }</c:if>
						    </c:foreach>
							</td>
							<td>${item.productName}</td>
						</tr>
					</c:foreach>
				</tbody>
			</table>

			<jsp:include page="../common/panelBar.jsp">
				<jsp:param value="dialog" name="targetType" />
				<jsp:param value="" name="rel" />
			</jsp:include>
		</div>
		
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div>
				</li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	
	</form>
</div>