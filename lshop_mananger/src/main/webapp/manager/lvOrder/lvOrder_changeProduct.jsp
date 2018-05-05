<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<jsp:include page="../common/pagerForm.jsp">
	<jsp:param value="lvOrderAction!befChangeStore.action?lvOrder.oid=${lvOrder.oid}&lvOrder.storeId=${lvOrder.storeId }&lvStore.countryId=${lvStore.countryId }&lvStore.status=1&json.navTabId=${json.navTabId}"
		name="pagerFormAction" />
</jsp:include>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
		<sec:authorize url="userEdit">
			<li><span><font color="red">${store.name }</font>订单号${lvOrder.oid }的产品信息转向<font color="red">${targetStore.name }</font>选择对应的匹配产品信息:</span></li>
		</sec:authorize>
		</ul>
	</div>
<form method="post" action="lvOrderAction!changeOrder.action?lvOrder.oid=${lvOrder.oid}&lvOrder.storeId=${lvOrder.storeId }&lvStore.countryId=${lvStore.countryId }&lvStore.status=1&targetFlag=${targetFlag}"
	      class="pageForm required-validate" 
	      onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="json.navTabId" value="${json.navTabId }" />	
<div class="pageFormContent" style="padding: 0px">
			<table class="table" width="100%" layoutH="160">
				<thead>
					<tr>
						<th width="5%">ID</th>
						<th width="10%">原产品名称</th>
						<th width="10%">转向目标产品名称</th>
					</tr>
				</thead>
				<tbody>
					<c:foreach items="${page.list}" var="item" varStatus="index">
					
						<tr>
							<td>${item.id}</td>
							<td>${item.productName}
							<input type="hidden" name="lvOrder.list[${index.index }].id" class="provinceDate" value="${item.id}">
							<input type="hidden" name="lvOrder.list[${index.index }].productCode" class="provinceDate" value="${item.productCode}">  	
							</td>
							<td>                                                                    
							<select style="width:200px;" name="lvOrder.list[${index.index }].targetProductCode" class="required">
							<option></option>
							<c:foreach items="${productList}" var="p">
							<option value="${p.code }">${p.productName }</option>
							</c:foreach>
							</select>
							</td>
						</tr>
					</c:foreach>
				</tbody>
			</table>
			<jsp:include page="../common/panelBar.jsp">
				<jsp:param value="dialog" name="targetType" />
				<jsp:param value="" name="rel" />
			</jsp:include>
	
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
		</div>
			</form>
</div>
<!-- 
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
		<sec:authorize url="userEdit">
			<li><span>选择匹配的目标店铺下的产品信息</span></li>
		</sec:authorize>
		</ul>
	</div>
	<form method="post" 
	      action="lvOrderAction!changeOrder.action?lvOrder.oid=${lvOrder.oid}&lvOrder.storeId=${lvOrder.storeId }&lvStore.countryId=${lvStore.countryId }&lvStore.status=1&targetFlag=${targetFlag}"
	      class="pageForm required-validate" 
	      onsubmit="return validateCallback(this, dialogAjaxDone);">
		<input type="hidden" name="json.navTabId" value="${json.navTabId }" />
		<div class="pageFormContent" style="padding: 0px">
			<table class="table" width="100%" layoutH="380">
				<thead>
					<tr>
					    <th width="1%"><input type="hidden" group="ids" class="checkboxCtrl"></th>
						<th width="5%">ID</th>
						<th width="10%">店铺名称</th>
						<th width="10%">产品名称</th>
						
					</tr>
				</thead>
				<tbody>
					<c:foreach items="${page.list}" var="item">
						<tr>
						    <td><input name="ids" value="${item.id}" type="checkbox"></td>
							<td>${item.id}</td>
							<td>${item.storeId}</td>
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
-->

