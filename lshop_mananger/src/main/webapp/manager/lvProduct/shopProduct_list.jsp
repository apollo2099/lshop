<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="../common/pagerForm.jsp">
<jsp:param value="lvProductAction!shopProductList.action?json.navTabId=${json.navTabId }" name="pagerFormAction"/>
</jsp:include>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="lvProductAction!shopProductList.action?json.navTabId=${json.navTabId }" method="post">
		<div class="searchBar">
		    <table class="searchContent" >
				<tr>
						</tr><tr>
						<td><label>商家名称</label>
							<select name="lvProduct.storeId">
							<option value="">==请选择==</option>
							<c:foreach items="${storeList}" var="store">
							<option value="${store.storeFlag }" <c:if test="${lvProduct.storeId==store.storeFlag }">selected="selected"</c:if>>${store.name }</option>
							</c:foreach>
							</select>
						</td>
						<td><label>商品名称</label><input name="lvProduct.productName" type="text" size="20" maxlength="64" value="${lvProduct.productName}"/></td>
						<td><label>商品分类</label>
							<select name="lvProduct.shopProductType">
							<option value="">==请选择==</option>
							<c:foreach items="${productTypList}" var="type">
							<option value="${type.code }" <c:if test="${lvProduct.shopProductType==type.code }">selected="selected"</c:if>>${type.typeName }</option>
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
		<sec:authorize url="userEdit">
		    <li><a class="add" href="lvProductAction!befChangeType.action?json.navTabId=${json.navTabId }" target="dwzDialog" width="450" height="300" mask="true" title="批量修改分类" rel="ids"><span>批量修改分类</span></a></li>
			<li><a class="delete"  href="lvProductAction!befUnSupport.action?json.navTabId=${json.navTabId }" target="dwzDialog" width="450" height="300" mask="true" title="批量下架"  rel="ids"><span>批量下架</span></a></li>
		</sec:authorize>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="145">
		<thead>
			<tr>
				<th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="5%" orderField="id" class="${orderDirection}">ID</th>
				<th width="10%">商家名称</th>
				<th width="20%">商品名称</th>
				<th width="10%">商品型号</th>
				<th width="10%">商品分类</th>
				<th width="10%">价格</th>
			    <th width="5%">商品图片</th>
				<th width="25%">操作</th>
			</tr>
		</thead>
		<tbody>

		<s:iterator value="page.list"  id="product">
			<tr>
				<td><input name="ids" value="${product.id}" type="checkbox"></td>
				<td>${product.id }</td>
				<td>${product.storeName }</td>
				<td>${product.productName }</td>
				<td>${product.pmodel}</td>
				<td>
				<c:foreach items="${productTypList}" var="type">
					<c:if test="${product.shopProductType==type.code }">${type.typeName }</c:if>
				</c:foreach>
				</td>
				<td>${product.price }</td>
				<td>
					<c:if test="${product.pimage!=null}">
						<a href="lvShopProductAction!showPic.action?imgSrc=${product.pimage}&json.navTabId=${json.navTabId}" target="dialog" mask="true" title="浏览图片" width="800" height="600"><img src="${product.pimage}" width="20px" height="20px"/></a>
					</c:if>
					
				</td>
				<td>
				<sec:authorize url="userEdit">
				    <a class="btnView" href="lvProductAction!view.action?lvProduct.id=${product.id}" target="dialog" navTabId="lvAccount" rel="lvAccount" title="查看" width="850" height="600" mask="true">查看</a>
					<a class="btnEdit" href="lvProductAction!befShopEdit.action?lvProduct.id=${product.id}&json.navTabId=${json.navTabId }"  title="编辑" target="dialog" width="500" height="500" navTabId="lvProduct" rel="lvProduct"  mask="true">编辑</a>
					<a href="lvProductAction!befToNotice.action?lvProduct.storeId=${product.storeId}&json.navTabId=${json.navTabId }" target="dialog" title="通知" width="1020" height="600" mask="true">通知</a>
					<c:if test="${product.isSupport!=0}">
					   <a href="lvProductAction!befUnSupport.action?ids=${product.id }&json.navTabId=${json.navTabId }"  title="商品下架" width="450" height="300" target="dialog">下架</a>
					</c:if>
					<a href="lvProductAction!getProductProperty.action?lvProduct.code=${product.productCode}&lvProduct.storeId=${product.storeId }&json.navTabId=lvShopSubject_1"  rel="lvShopSubject_1" target="navTab">扩展属性</a>
					<a href="lvProductAction!getProductImage.action?lvProduct.code=${product.productCode}&lvProduct.storeId=${product.storeId }&json.navTabId=lvShopSubject_2"  rel="lvShopSubject_2" target="navTab">效果图</a>
				</sec:authorize>	
				</td>
			</tr>
		</s:iterator>
		</tbody>
	</table>
	
	<jsp:include page="../common/panelBar.jsp">
		<jsp:param value="navTab" name="targetType"/>
		<jsp:param value="" name="rel"/>
	</jsp:include>
</div>
