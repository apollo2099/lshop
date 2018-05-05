<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<form id="pagerForm" method="post" action="lvCouponTypeAction!selectSimpleCouponType.action?mallSysFlag=${mallSysFlag }" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
</form>


<div class="pageHeader">
	<form rel="pagerForm" method="post" action="lvCouponTypeAction!selectSimpleCouponType.action?mallSysFlag=${mallSysFlag }" onsubmit="return dialogSearch(this);">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>优惠券名称:</label>
				<input class="textInput" name="lvCouponType.name" value="${lvCouponType.name }" type="text">
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
				<tr>
				<th >优惠券名称</th>
				<th >有效期</th>
				<th >优惠券金额</th>
				<th >创建时间</th>
				<th width="80">查找带回</th>
			</tr>
			</tr>
		</thead>
		<tbody>
		    <s:iterator value="#request.page.list" status="stat" id="item">
			<tr>
			    <td>${item.name }</td>
				<td><s:date name="startTime" format="yyyy-MM-dd"/>至<s:date name="endTime" format="yyyy-MM-dd"/></td>
				<td>${item.amount }</td>
				<td><s:date name="createTime" format="yyyy-MM-dd"/></td>
				<td>
					<a class="btnSelect" href="javascript:$.bringBack({givenProductCode:'${item.code}', givenTypeName:'${item.name }'})" title="查找带回">选择</a>
				</td>
			</tr>
			</s:iterator>
		</tbody>
	</table>

	<jsp:include page="../common/panelBar.jsp">
		<jsp:param value="dialog" name="targetType"/>
		<jsp:param value="" name="rel"/>
	</jsp:include>
</div>