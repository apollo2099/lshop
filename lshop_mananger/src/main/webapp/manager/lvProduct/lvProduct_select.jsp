<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<jsp:include page="../common/pagerForm.jsp">
<jsp:param value="lvProductAction!list.action" name="pagerFormAction"/>
</jsp:include>

<div class="page unitBox">
	<div class="pageHeader">
		<form onsubmit="return dwzSearch(this, 'dialog');" action="lvProductAction!select.action?json.navTabId=${json.navTabId}" method="post" rel="pagerForm">
		<div class="searchBar">
		    <table class="searchContent" >
				<tr>
						</tr><tr>
						<td><label>ID</label>
						<input name="lvProduct.id" type="text" size="20" value="${lvProduct.id}"/>
						</td>
						<td><label>产品名称</label><input name="lvProduct.productName" type="text" size="20" value="${lvProduct.productName}"/></td>
						<td><label>产品型号</label><input name="lvProduct.pmodel" type="text" size="20" value="${lvProduct.pmodel}"/></td>
				</tr><tr>
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
					<li><div class="button"><div class="buttonContent"><button type="button" multLookup="tagsId" warn="请选择标签">选择带回</button></div></div></li>
				</ul>
			</div>
		</div>
		</form>
	</div>
	<div class="pageContent">
		<table class="table" width="100%" layoutH="160">
			<thead>
				<tr>
				<th width="2%"><input type="checkbox" group="tagsId" class="checkboxCtrl"></th>
				<th width="5%" orderField="id" class="${orderDirection}">ID</th>
				<th >产品名称</th>
				<th >产品code</th>
				<th >产品型号</th>
				<th >产品价格(人民币)</th>
				<th >产品价格(美元)</th>
				<th orderField="createTime" class="${orderDirection}">创建时间</th>
			    <th >产品图片</th>
				</tr>
			</thead>
			<tbody class="dateList">
				<s:iterator value="#request.page.list" status="stat" id="product">
				<tr>
				<td><input name="tagsId" value="{'mealCode':'${product.code }','mealName':'${product.productName}'}" type="checkbox"></td>
				<!-- 生成表单 -->
				<td>${product.id }</td>
				<td>${product.productName }</td>
				<td>${product.pcode }</td>
				<td>${product.pmodel}</td>
				<td>${product.priceRmb }</td>
				<td>${product.priceUsd }</td>
				<td>${product.createTime }</td>
				<td>
					<c:if test="${product.pimage!=null}">
						<a href="lvProductAction!showPic.action?imgSrc=${product.pimage}&json.navTabId=${json.navTabId}" target="dialog" mask="true" title="浏览图片" width="800" height="600"><img src="${product.pimage}" width="20px" height="20px"/></a>
					</c:if>
					
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
</div>