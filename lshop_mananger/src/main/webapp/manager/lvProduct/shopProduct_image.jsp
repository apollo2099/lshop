<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="../common/pagerForm.jsp">
<jsp:param value="lvProductAction!getProductImage.action?lvProduct.code=${lvProduct.code}" name="pagerFormAction"/>
</jsp:include>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="lvProductAction!getProductImage.action?lvProduct.code=${lvProduct.code}" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>ID：</label>
                <input type="text" name="lvProductImage.id" maxlength="10" value="${lvProductImage.id }">
			</li>
		</ul>
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
		    <li><a class="add" href="lvProductImageAction!befShopAdd.action?lvProductImage.productCode=${lvProduct.code }&lvProductImage.storeId=${lvProduct.storeId }&json.navTabId=${json.navTabId }" target="dialog" mask="true" width="500" height="300" title="添加"><span>添加</span></a></li>
		    <li class="line">line</li>
			<li><a class="delete"  href="lvProductImageAction!delList.action?json.navTabId=${json.navTabId }" target="selectedTodo" title="确实要删除这些记录吗?该操作请谨慎!"  rel="ids"><span>批量删除</span></a></li>
		</sec:authorize>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="145">
		<thead>
			<tr>
				<th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="5%" orderField="id" class="${orderDirection}">ID</th>
				<th >产品图片</th>
				<th >排序</th>
				<th >操作</th>
			</tr>
		</thead>
		<tbody>
		<c:foreach items="${page.list}" var="item">
			<tr>
				<td><input name="ids" value="${item.id}" type="checkbox"></td>
				<td>${item.id }</td>
				<td>
				    <c:if test="${item.productImage!=null}">
						<a href="lvShopProductAction!showPic.action?imgSrc=${item.productImage}&json.navTabId=${json.navTabId}" target="dialog" mask="true" title="浏览图片" width="800" height="600"><img src="${item.productImage}" width="20px" height="20px"/></a>
					</c:if>
				</td>
				<td>${item.sortId}</td>
				<td>
				<sec:authorize url="userEdit">
					<a class="btnEdit" href="lvProductImageAction!befShopEdit.action?lvProductImage.id=${item.id}&json.navTabId=${json.navTabId }"  title="编辑" target="dialog"  width="500" height="300" mask="true">编辑</a>
					<a class="btnDel"  href="lvProductImageAction!del.action?lvProductImage.id=${item.id}&json.navTabId=${json.navTabId }" title="确实要删除这些记录吗?该操作请谨慎!" target="ajaxTodo">删除</a>
				</sec:authorize>	
				</td>
			</tr>
		</c:foreach>
		</tbody>
	</table>
	
	<jsp:include page="../common/panelBar.jsp">
		<jsp:param value="navTab" name="targetType"/>
		<jsp:param value="" name="rel"/>
	</jsp:include>
</div>
