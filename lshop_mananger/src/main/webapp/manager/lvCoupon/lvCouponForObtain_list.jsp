<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>

<form id="pagerForm" method="post" action="lvCouponAction!listForObtain.action?couponCode=${couponCode}&json.navTabId=${json.navTabId}" >
	<input type="hidden" name="keywords" value="${keywords}" />
	<input type="hidden" name="page.pageNum" value="${page.pageNum}" />
	<input type="hidden" name="page.numPerPage" value="${page.numPerPage}" />
	<input type="hidden" name="orderField" value="${orderField}" />
	<input type="hidden" name="orderDirection" value="${orderDirection}" />
	
</form>

<div class="page">
	<div class="pageHeader">
		<form onsubmit="return navTabSearch(this);" action="lvCouponAction!listForObtain.action?couponCode=${couponCode}&json.navTabId=${json.navTabId}" method="post" rel="pagerForm">
		<div class="searchBar">
			<div class="subBar">
				<ul>
				</ul>
			</div>
		</div>
		</form>
	</div>
	<div class="pageContent">
		<div class="panelBar">
			<ul class="toolBar">
				<li class="line">line</li>
			</ul>
		</div>
		<table class="table" width="100%" layoutH="200">
			<thead>
				<tr>
					<!-- 生成表单 -->
					<th width="4%">ID</th>
					<th width="5%">获取人</th>
					<th width="10%">获取时间</th>
				</tr>
			</thead>
			<tbody>
				
				<s:iterator value="page.list" status="stat" id="item">
				<tr>
					<!-- 生成表单 -->
								<td>${item.id }</td>
								<td>${item.obtainName }</td>
								<td><s:date name="obtainTime" format="yyyy-MM-dd HH:mm"/></td>
				</tr>
				</s:iterator>
				
			</tbody>
		</table>
		<%@ include file="../common/panelBar.jsp"%>
	</div>
</div>