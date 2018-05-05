<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<form id="pagerForm" method="post" action="lvPubProductAction!selectSingleProduct.action?lvPubProduct.pubProductCode=${lvPubProduct.pubProductCode }&json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" method="post" action="lvPubProductAction!selectSingleProduct.action?lvPubProduct.pubProductCode=${lvPubProduct.pubProductCode }&json.navTabId=${json.navTabId}" onsubmit="return dwzSearch(this, 'dialog');">
	<div class="searchBar">
			<table class="searchContent" >
				<tr>
						</tr><tr>
						<td><label>商品名称</label></td><td><input name="lvPubProduct.productName" type="text" size="20" value="${lvPubProduct.productName}"/></td>
						<td><label>商品型号</label></td><td><input name="lvPubProduct.productModel" type="text" size="20" value="${lvPubProduct.productModel }"/></td>
						<td><label>商务对接code</label></td><td><input name="lvPubProduct.pcode" type="text" size="20" value="${lvPubProduct.pcode}"/></td>
						</tr><tr> 
				</tr>
			</table>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">

	<table class="table" layoutH="155" targetType="dialog" width="100%">
		<thead>
			<tr>
				   <!-- 生成表单 -->
				   <th width="5%" orderField="id" class="${orderField eq 'id' ? orderDirection : ''}">ID</th>
				   <th width="45%">商品名称</th>
				   <th width="15%">商品型号</th>
				   <th width="35%">商务对接code</th>
				   <th width="80">查找带回</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="page.list" status="stat" id="item">
			<tr>
					<!-- 生成表单 -->
					<td>${item.id}</td>
					<td>${item.productName}</td>
					<td>${item.productModel}</td>
					<td>${item.pcode }</td>
				<td>
				<a class="btnSelect" href="javascript:$.bringBack({pubProductCode:'${item.code }', productName:'${item.productName }',pcode:'${item.pcode }',pmodel:'${item.productModel }'})" title="查找带回">选择</a>
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
