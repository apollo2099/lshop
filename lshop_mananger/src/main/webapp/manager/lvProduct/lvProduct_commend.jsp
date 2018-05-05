<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="../common/pagerForm.jsp">
<jsp:param value="lvProductAction!commendList.action?lvProduct.code=${lvProduct.code}&lvProduct.storeId=${lvProduct.storeId }&json.navTabId=${json.navTabId }" name="pagerFormAction"/>
</jsp:include>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="lvProductAction!commendList.action?lvProduct.code=${lvProduct.code}&lvProduct.storeId=${lvProduct.storeId }&json.navTabId=${json.navTabId }" method="post">
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
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
		<sec:authorize url="userEdit">   
			<li><a class="icon"  href="lvProductAction!commend.action?lvProduct.code=${lvProduct.code }&lvProduct.storeId=${lvProduct.storeId }&json.navTabId=${json.navTabId }" target="selectedTodo" title="确实要设置当前产品的推荐组合吗?"  rel="ids"><span>设置推荐组合</span></a></li>
		</sec:authorize>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="145">
		<thead>
			<tr>
				<th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th width="5%" orderField="id" class="${orderDirection}">ID</th>
				<th >商品名称</th>
				<!-- 
				<th >商品code</th>
				<th >商品型号</th>
				-->
				<th >商品价格</th>
				<th orderField="creatTime" class="${orderDirection}">创建时间</th>
			    <th >商品图片</th>
			    <th >操作</th>
			</tr>
		</thead>
		<tbody>
		<s:iterator value="page.list"  id="product">
			<tr>
				<td><input name="ids" value="${product.code}" type="checkbox"
				<c:foreach items="${commendList}" var="commend">
				     <c:if test="${commend.commendCode==product.code }">checked="checked"</c:if>               
				</c:foreach>
				></td>
				<td>${product.id }</td>
				<td>${product.productName }</td>
				<!-- 
				<td>${product.pcode }</td>
				<td>${product.pmodel}</td>
				 -->
				<td>${product.price}</td>
				<td><s:date name="createTime " format="yyyy-MM-dd HH:mm"/></td>
				<td>
					<c:if test="${product.pimage!=null}">
						<a href="lvShopProductAction!showPic.action?imgSrc=${product.pimage}&json.navTabId=${json.navTabId}" target="dialog" mask="true" title="浏览图片" width="800" height="600"><img src="${product.pimage}" width="20px" height="20px"/></a>
					</c:if>
					
				</td>
				<td>
				<c:foreach items="${commendList}" var="commend">
				     <c:if test="${commend.commendCode==product.code }">
				     <a href="lvProductCommendAction!del.action?lvProductCommend.productCode=${lvProduct.code}&lvProductCommend.commendCode=${product.code }&json.navTabId=${json.navTabId}"   title="确实要删除该产品的推荐组合吗?该操作请谨慎!"  target="ajaxTodo">删除推荐</a>
				     </c:if>               
				</c:foreach>
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
