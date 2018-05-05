<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>


<form id="pagerForm" method="post" action="lvProductAction!selectSimpleProduct.action?lvProduct.isActivity=${lvProduct.isActivity }&lvProduct.storeId=${lvProduct.storeId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
</form>


<div class="pageHeader">
	<form rel="pagerForm" method="post" action="lvProductAction!selectSimpleProduct.action?lvProduct.isActivity=${lvProduct.isActivity }&lvProduct.storeId=${lvProduct.storeId}" onsubmit="return dialogSearch(this);">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>商品名称:</label>
				<input class="textInput" name="lvProduct.productName" value="${lvProduct.productName }" maxlength="64" type="text">
			</li>
			
		</ul>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">

	<table class="table" layoutH="118" targetType="dialog" width="100%">
		<thead>
			<tr>
				<th orderfield="orgName">所属关系</th>
				<th orderfield="orgNum">商品名称</th>
				<th >商品价格</th>
				<th >创建时间</th>
				<th width="80">查找带回</th>
			</tr>
		</thead>
		<tbody>
		    <s:iterator value="#request.page.list" status="stat" id="product">
			<tr>
			    <td>${product.storeName }</td>
				<td>${product.productName }</td>
				<td>${product.price }</td>
				<td>${product.creatTime }</td>
				<td>
					<a class="btnSelect" href="javascript:$.bringBack({productName:'${product.productName }', productCode:'${product.code }'})" title="查找带回">选择</a>
				</td>
			</tr>
			</s:iterator>
		</tbody>
	</table>



	<jsp:include page="../common/panelBar.jsp">
		<jsp:param value="dialog" name="targetType" />
		<jsp:param value="" name="rel" />
	</jsp:include>
</div>