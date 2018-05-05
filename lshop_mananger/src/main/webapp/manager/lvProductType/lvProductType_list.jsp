<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="../common/pagerForm.jsp">
<jsp:param value="lvProductTypeAction!list.action" name="pagerFormAction"/>
</jsp:include>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="lvProductTypeAction!list.action" method="post">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>ID：</label>
				<input type="text" name="lvProductType.id" value="${lvProductType.id}"/>
			</li>
			<li>
				<label>类别名称：</label>
				<input type="text" name="lvProductType.typeName" value="${lvProductType.typeName}"/>
			</li>
			<li>
				<label>类别标示：</label>
				<select name="lvProductType.typeFlag">
				   <option>==请选择==</option>
				   <option value="1" <c:if test="${lvProductType.typeFlag==1 }">selected="selected"</c:if>>产品</option>
				   <option value="2" <c:if test="${lvProductType.typeFlag==2 }">selected="selected"</c:if>>应用</option>
				</select>
			</li>
			<li>
			<label>所属关系</label>
			<select name="lvProductType.storeId">
						<option value="">==请选择==</option>
						<c:foreach items="${storeList}" var="store">
						<option  value="${store.storeFlag }" <c:if test="${store.storeFlag==lvProductType.storeId}">selected="selected"</c:if>>${store.name}</option>
					    </c:foreach>
					    </select>
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
		    <li><a class="add" href="lvProductTypeAction!befAdd.action?json.navTabId=${json.navTabId }" target="dialog" mask="true" title="添加产品类型"><span>添加</span></a></li>
			<li><a class="delete"  href="lvProductTypeAction!delList.action?json.navTabId=${json.navTabId }" target="selectedTodo" title="确实要删除这些记录吗?该操作请谨慎!"  rel="ids"><span>批量删除</span></a></li>
		</sec:authorize>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="1%" ><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="4%" orderField="id" class="${orderDirection}">ID</th>
				<th width="10%" >所属关系</th>
				<th width="35%" >类别名称</th>
				<th width="10%" >类别标示</th>
				<th width="10%" >排序值</th>
				<th width="30%" >操作</th>
			</tr>
		</thead>
		<tbody>
		<c:foreach items="${page.list}" var="productType">
			<tr>
				<td><input name="ids" value="${productType.id}" type="checkbox"></td>
				<td>${productType.id }</td>
				<td>
						<c:foreach items="${storeList}" var="store">
							<c:if test="${store.storeFlag==productType.storeId}">${store.name}</c:if>
						</c:foreach>
				</td>
				<td>${productType.typeName}</td>
				<td>
				<c:if test="${productType.typeFlag==1}">产品</c:if>
				<c:if test="${productType.typeFlag==2}">应用</c:if>
				</td>
				<td>${productType.orderId}</td>
				<td>
				<sec:authorize url="userEdit">
				    <a class="btnView" href="lvProductTypeAction!view.action?lvProductType.id=${productType.id}" target="dialog" navTabId="lvAccount" rel="lvAccount" title="查看"  mask="true">查看</a>
					<a class="btnEdit" href="lvProductTypeAction!befEdit.action?lvProductType.id=${productType.id}&json.navTabId=${json.navTabId }"  title="编辑" target="dialog"  mask="true">编辑</a>
					<a class="btnDel"  href="lvProductTypeAction!del.action?lvProductType.id=${productType.id}&json.navTabId=${json.navTabId }" title="确实要删除这些记录吗?该操作请谨慎!" target="ajaxTodo">删除</a>
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
