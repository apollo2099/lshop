<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<jsp:include page="../common/pagerForm.jsp">
<jsp:param value="lvOrderAction!shopTypeState.action" name="pagerFormAction"/>
</jsp:include>

<div class="pageHeader">
	<form rel="pagerForm" onsubmit="return navTabSearch(this);" action="lvOrderAction!shopTypeState.action" method="post">
	<div class="searchBar">
			<table class="searchContent" >
				<tr>
						</tr><tr>
						<td>
						<label>商品分类</label><select name="lvShopProductType.code">
							<option value="">==请选择==</option>
							<c:foreach items="${typeList}" var="type">
							<option value="${type.code }" <c:if test="${lvShopProductType.code==type.code }">selected="selected"</c:if>>${type.typeName }</option>
							</c:foreach>
							</select>
						 </td>
						<td><label>下单时间</label><input type="text" name="lvOrder.startTime" value="${lvOrder.startTime}" class="date"/>
						-<input type="text" class="date" name="lvOrder.endTime" value="${lvOrder.endTime}"></td>
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
			<li><a class="icon" href="lvOrderAction!exportShopTypeState.action?lvOrder.startTime=${lvOrder.startTime }&lvOrder.endTime=${lvOrder.endTime }&json.navTabId=${json.navTabId}"  target="dwzExportString" targettype="dialog" title="实要导出这些记录吗?"  rel="ids"><span>导出Excel</span></a></li>
			<li class="line">line</li>
		</sec:authorize>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="145">
		<thead>
			<tr>
				<th width="1%"><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th >分类名称</th>
				<th >商品数量</th>
				<th >销售量</th>
				<th >销售额(USD)</th>
				<th >均价(USD)</th>
			</tr>
		</thead>
		<tbody>
		<c:foreach items="${page.list}" var="item">
			<tr>
				<td><input name="ids" value="${item.code}" type="checkbox"></td>
				<td>${item.shopTypeName }</td>
				<td>${item.shopTypeCount }</td>
				<td>${item.pnum}</td>
				<td>${item.sales }</td>
				<td>${item.oprice }</td>
			</tr>
		</c:foreach>
		</tbody>
	</table>

	<jsp:include page="../common/panelBar.jsp">
		<jsp:param value="navTab" name="targetType"/>
		<jsp:param value="" name="rel"/>
	</jsp:include>
</div>
